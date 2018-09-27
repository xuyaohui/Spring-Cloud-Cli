<template>
  <div class="dashboard-container">
    <div style="margin: 30px">
      自动从tag服务获取用户信息：<br><br>
      {{ message }}
    </div>
  </div>
</template>
<script>
  export default {
    name: 'dashboard',
    data() {
      return {
        message:''
      }
    },
    created() {
      this.getUserInfo();
    },
    methods: {
      getUserInfo(){
        this.api({
          url: "/api-tag/tag/getUserById",
          method: "get"
        }).then(data => {
          this.message = data;
        })
      }
    },
  }
</script>
