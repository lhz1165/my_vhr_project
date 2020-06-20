<template>
    <div>
        <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
            <h3>系统登录</h3>
            <el-form-item prop="username">
                <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="输入密码"></el-input>
            </el-form-item>
            <!--记住密码-->
            <el-checkbox v-model="checked">记住我</el-checkbox>
            <!--登录按钮-->
            <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>

        </el-form>
    </div>


</template>

<script>
	import {postKeyValueRequest} from "../utils/api";
    export default {
        name: "Login",
        data(){
            return {
                loginForm:{
                    username: 'admin',
                    password:'123'
                },
                checked:true,
                rules:{
                    username:[{required:true,message:'请输入用户名',trigger:'blur'}],
                    password:[{required:true,message:'请输入密码',trigger:'blur'}]

                }
            }
        },
		methods:{
			submitLogin(){
				this.$refs.loginForm.validate((valid) => {
					if(valid){
						postKeyValueRequest('/doLogin', this.loginForm).then(resp => {
							console.log("?")
							if(resp){
								window.sessionStorage.setItem("user",JSON.stringify(resp,resp.obj));
								this.$router.replace('/home')
							}
						
						})
					}else{
						this.$message.error('请输入所有字段')
						return false;
					}
					
				});
			}
		}
    }
</script>

<style >
    .loginContainer{
        border-radius: 15px;/*圆形*/
        background-clip: padding-box;
        margin: 180px auto;/*上边距*/
        width: 400px;/*宽度*/
        padding: 20px 35px 15px 15px;
        background: #ffffff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

</style>