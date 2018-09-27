import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/tag',
    component: Layout,
    redirect: '/tag/self',
    name: '标签',
    meta: {title: '标签', icon: 'tree'},
    children: [
      {
        path: 'self',
        name: '自助取数',
        component: _import('tag/selfNum'),
        meta: {title: '自助取数', icon: 'example'},
        menu: 'tindex'
      },
      {
        path: 'cluster',
        name: '客户群',
        component: _import('tag/cluster'),
        meta: {title: '客户群', icon: 'example'},
        menu: 'tcluster'
      },
      {
        path: 'templet',
        name: '模板取数',
        component: _import('tag/templet'),
        meta: {title: '模板取数', icon: 'example'},
        menu: 'tftemplet'
      }
    ]
  },
  {
    path: '/config',
    component: Layout,
    redirect: '/config/self',
    name: '标签配置',
    meta: {title: '标签配置', icon: 'table'},
    children: [
      {
        path: 'simpleConfig', 
        name: '常规配置', 
        component: _import('tag/simpleConfig'), 
        meta: {title: '常规配置', icon: 'user'}, 
        menu: 'tsconfig'
      },
      {
        path: 'factoryConfig',
        name: '标签工厂',
        component: _import('tag/factoryConfig'),
        meta: {title: '标签工厂', icon: 'password'},
        menu: 'tfconfig'
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
