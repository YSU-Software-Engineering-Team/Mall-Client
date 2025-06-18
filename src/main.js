import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus' // 提前导入ElementPlus
import 'element-plus/dist/index.css'   // 提前导入样式

import App from './App.vue'
import router from './router'

const app = createApp(App)

// 统一注册所有插件
app.use(createPinia())
app.use(router)
app.use(ElementPlus) // 在挂载前注册ElementPlus

// 只挂载一次
app.mount('#app')
