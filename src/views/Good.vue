<template>
  <el-card class="good-container">
    <template #header>
      <div class="header">
        <el-button type="primary" :icon="Plus" @click="handleAdd">新增商品</el-button>
      </div>
    </template>

    <el-table
      :key="tableKey"
      v-loading="state.loading"
      :data="state.tableData"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <!-- ...columns remain unchanged... -->
    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      :total="state.total"
      :page-size="state.pageSize"
      :current-page="state.currentPage"
      @current-change="changePage"
    />
  </el-card>
</template>

<script setup>
import { onMounted, reactive, ref, getCurrentInstance } from 'vue'
import axios from '@/utils/axios'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const app = getCurrentInstance()
const { goTop } = app.appContext.config.globalProperties
const router = useRouter()

const state = reactive({
  loading: false,
  tableData: [],
  total: 0,
  currentPage: 1,
  pageSize: 10
})
const tableKey = ref(0)

function getGoodList() {
  state.loading = true
  axios.get('/goods/list', {
    params: {
      pageNumber: state.currentPage,
      pageSize: state.pageSize
    }
  })
  .then(res => {
    state.tableData = res.list
    state.total = res.totalCount
    state.currentPage = res.currPage
  })
  .catch(err => {
    ElMessage.error(err.message || '加载失败')
  })
  .finally(() => {
    state.loading = false
    goTop?.()
  })
}

onMounted(getGoodList)

function handleAdd() {
  router.push({ path: '/add' })
}
function handleEdit(id) {
  router.push({ path: '/add', query: { id } })
}
function changePage(val) {
  state.currentPage = val
  getGoodList()
}
function handleStatus(id, status) {
  state.loading = true
  axios.put(`/goods/status/${status}`, {
    ids: [id]
  })
  .then(() => {
    ElMessage.success('修改成功')
    tableKey.value++  // 强制刷新组件
    getGoodList()
  })
  .catch(err => {
    ElMessage.error(err.message || '状态修改失败')
    state.loading = false
  })
}
</script>

<style scoped>
.good-container { min-height:100%; }
.el-card.is-always-shadow { min-height:100%!important; }
</style>
