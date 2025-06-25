<template>
  <div class="login-body">
    <div class="login-container">
      <div class="head">
        <img :src="logo" alt="logo">
        <div class="name">
          <div class="title">东软睿够</div>
          <div class="tips">Vue3.0 后台管理系统</div>
        </div>
      </div>
      <el-form 
        label-position="top" 
        :rules="state.rules" 
        :model="state.ruleForm" 
        ref="loginForm" 
        class="login-form"
      >
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model.trim="state.ruleForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model.trim="state.ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <div style="color: #333">登录表示您已同意<a>《服务条款》</a></div>
          <el-button 
            style="width: 100%" 
            type="primary" 
            @click="submitForm"
            :loading="state.loading"
          >
            立即登录
          </el-button>
          <el-checkbox v-model="state.checked">下次自动登录</el-checkbox>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import axios from '@/utils/axios'
import md5 from 'js-md5'
import { reactive, ref } from 'vue'
import { localSet } from '@/utils'
import logo from '@/assets/商品活动.png'
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus'

const router = useRouter();
const loginForm = ref(null)
const state = reactive({
  ruleForm: {
    username: '',
    password: ''
  },
  checked: true,
  loading: false,
  rules: {
    username: [
      { required: true, message: '账户不能为空', trigger: 'blur' }
    ],
    password: [
      { required: true, message: '密码不能为空', trigger: 'blur' }
    ]
  }
})

const submitForm = async () => {
  state.loading = true
  try {
    // 验证表单
    const valid = await loginForm.value.validate()
    if (!valid) return
    
    // 准备请求数据
    const encryptedPassword = md5(state.ruleForm.password)
    
    // 确保使用正确的请求路径 - 8090端口，无/api前缀
    const response = await axios.post('http://localhost:8080/user/login', {
      username: state.ruleForm.username || '',
      password: encryptedPassword
    })
    
    // 检查响应状态
    if (response.status !== 200) {
      throw new Error(`请求失败，状态码: ${response.status}`)
    }
    
    // 解析响应数据
    const responseData = response.data
    console.log('Login response:', responseData)
    
    // 检查业务状态码
    if (responseData.code === 200) {
      // 保存token
      localSet('token', responseData.data)
      console.log('Token saved:', responseData.data)
      
      // 跳转到首页
      await router.replace('/')
      ElMessage.success('登录成功')
    } else {
      ElMessage.error(responseData.message || '登录失败')
    }
  } catch (error) {
    console.error('Login error:', error)
    
    // 错误处理
    if (error.response && error.response.data) {
      const errorData = error.response.data
      ElMessage.error(errorData.message || '服务器错误')
    } else {
      ElMessage.error(error.message || '登录失败')
    }
  } finally {
    state.loading = false
  }
}
</script>

<style scoped>
  .login-body {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    background-color: #fff;
  }
  .login-container {
    width: 420px;
    height: 500px;
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0px 21px 41px 0px rgba(0, 0, 0, 0.2);
  }
  .head {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 40px 0 20px 0;
  }
  .head img {
    width: 100px;
    height: 100px;
    margin-right: 20px;
  }
  .head .title {
    font-size: 28px;
    color: #1BAEAE;
    font-weight: bold;
  }
  .head .tips {
    font-size: 12px;
    color: #999;
  }
  .login-form {
    width: 70%;
    margin: 0 auto;
  }
  .login-form >>> .el-form--label-top .el-form-item__label {
    padding: 0;
  }
  .login-form >>> .el-form-item {
    margin-bottom: 0;
  }
</style>