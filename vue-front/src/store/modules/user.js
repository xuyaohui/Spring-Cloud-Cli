import {getInfo, login, logout} from '@/api/login'
import {getToken, removeToken, setToken} from '@/utils/auth'
import {default as api} from '../../utils/api'
import store from '../../store'
import router from '../../router'

const user = {
  state: {
    nickname: "",
    userId: "",
    avatar: 'https://www.gravatar.com/avatar',
    role: '',
    menus: [],
    permissions: [],
  },
  mutations: {
    SET_USER: (state, userInfo) => {
      state.nickname = userInfo.userAccount;
      state.userId = userInfo.userAccount;
      state.role = userInfo.roles;
      state.menus = userInfo.menuList;
      state.permissions = userInfo.permission;
    },
    RESET_USER: (state) => {
      state.nickname = "";
      state.userId = "";
      state.role = '';
      state.menus = [];
      state.permissions = [];
    }
  },
  actions: {
    // 登录
    Login({commit, state}, loginForm) {
      return new Promise((resolve, reject) => {
        api({
          url: "login/auth",
          method: "post",
          data: loginForm
        }).then(data => {
          if (data.msg === "success") {
            //使用session存储token值
            sessionStorage.setItem('cloud-ida-token',data.data);

            //cookie中保存前端登录状态
            setToken();
          }
          resolve(data);
        }).catch(err => {
          reject(err)
        })
      })
    },
    // 获取用户信息
    GetInfo({commit, state}) {
      return new Promise((resolve, reject) => {

        api({
          url: '/login/getInfo',
          method: 'get'
        }).then(data => {
          //储存用户信息
          commit('SET_USER', data.data);
          //cookie保存登录状态,仅靠vuex保存的话,页面刷新就会丢失登录状态
          setToken();
          //生成路由
          let userPermission = data.data ;
          store.dispatch('GenerateRoutes', userPermission).then(() => {
            //生成该用户的新路由json操作完毕之后,调用vue-router的动态新增路由方法,将新路由添加
            router.addRoutes(store.getters.addRouters)
          })
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 登出
    LogOut({commit}) {
      return new Promise((resolve) => {
        commit('RESET_USER')
        removeToken()
        sessionStorage.removeItem('cloud-ida-token')
        resolve();
      })
    },
    // 前端 登出
    FedLogOut({commit}) {
      return new Promise(resolve => {
        commit('RESET_USER')
        removeToken()
        sessionStorage.removeItem('cloud-ida-token')
        resolve()
      })
    }
  }
}
export default user
