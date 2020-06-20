<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">微人事</div>
                <div>
                    <el-dropdown class="userInfo" @command="commandHandler">
  <span class="el-dropdown-link">
    {{user.obj.name}}<i><img :src="user.obj.userface" alt=""></i>
  </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                            <el-dropdown-item command="setting">设置</el-dropdown-item>
                            <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">Aside</el-aside>
                <el-main>Main</el-main>
            </el-container>
        </el-container>
    </div>

</template>

<script>
    export default {
        name: "Home",
        data(){
            return{
                user:JSON.parse(window.sessionStorage.getItem("user"))
            }
        },
        computed: {
            routes() {
                return this.$store.state.routes;
            }
        },
        methods:{
            commandHandler(cmd) {
                if (cmd == 'logout') {
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest("/logout");
                        window.sessionStorage.removeItem("user")

                        this.$router.replace("/");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消操作'
                        });
                    });
                }
            }
        }
    }
</script>

<style >
    .homeHeader{
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }
    .homeHeader .title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff
    }
    .homeHeader .userInfo{

    }
    .el-dropdown-link img{
        width: 30px;
        height: 30px;
        border-radius: 20px;

    }
    .el-dropdown-link{
        display: flex;
        align-items: center;
    }

</style>