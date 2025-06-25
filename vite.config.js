import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Components from 'unplugin-vue-components/vite'
import { VantResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    port: 8081, // 前端运行在 8081 端口
    proxy: {
      // 代理 /api 前缀的请求到后端服务器
      '/api': {
        target: 'http://localhost:8080', // 后端地址（8080端口）
        changeOrigin: true,              // 修改请求源头为目标地址
      }
    }
  },
  plugins: [
    vue(),
    Components({ resolvers: [VantResolver()] })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
