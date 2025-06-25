import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router/index'
import { localGet } from './index'
import config from '~/config'

// 创建基础axios实例 - 移除baseURL配置
const instance = axios.create({
  // 移除了baseURL配置，确保请求路径不被修改
  withCredentials: true,
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json'
  }
})

// 请求拦截器：添加token
instance.interceptors.request.use(config => {
  const token = localGet('token')
  if (token) {
    config.headers.token = token
  }
  return config
}, error => {
  return Promise.reject(error)
})

// 响应拦截器：统一处理响应
instance.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error.response) {
      const { status, data } = error.response
      
      // 处理401未授权
      if (status === 401) {
        ElMessage.error('登录已过期，请重新登录')
        localStorage.removeItem('token')
        router.replace('/login')
        return Promise.reject(error)
      }
      
      // 显示错误消息
      const message = data?.message || `请求错误 (${status})`
      ElMessage.error(message)
    } else if (error.request) {
      ElMessage.error('网络错误，请检查网络连接')
    } else {
      ElMessage.error('请求失败: ' + error.message)
    }
    
    return Promise.reject(error)
  }
)

export default instance