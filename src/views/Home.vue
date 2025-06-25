<template>
  <div>
    <!-- 头部导航 -->
    <header class="home-header wrap" :class="{'active': state.headerScroll}">
      <router-link tag="i" to="./category">
        <i class="nbicon nbmenu2"></i>
      </router-link>
      
      <div class="header-search">
        <span class="app-name">东软睿购</span>
        <i class="iconfont icon-search"></i>
        <router-link tag="span" class="search-title" to="./product-list?from=home">
          练习两年半，只为呈现最好
        </router-link>
      </div>
      
      <router-link v-if="!state.isLogin" class="login" tag="span" to="./login">
        登录
      </router-link>
      <router-link v-else class="login" tag="span" to="./user">
        <van-icon name="manager-o" />
      </router-link>
    </header>
    
    <!-- 导航栏组件 -->
    <nav-bar />
    
    <!-- 轮播图组件 -->
    <swiper :list="state.swiperList"></swiper>
    
    <!-- 分类列表 -->
    <div class="category-list">
      <div 
        v-for="item in state.categoryList" 
        :key="item.categoryId" 
        @click="tips"
      >
        <img :src="item.imgUrl">
        <span>{{ item.name }}</span>
      </div>
    </div>
    
    <!-- 商品区域 - 新品上线 -->
    <div class="good">
      <header class="good-header">新品上线</header>
      <van-skeleton title :row="3" :loading="state.loading">
        <div class="good-box">
          <div 
            class="good-item" 
            v-for="item in state.newGoodses" 
            :key="item.goodsId" 
            @click="goToDetail(item)"
          >
            <img :src="$filters.prefix(item.goodsCoverImg)" alt="">
            <div class="good-desc">
              <div class="title">{{ item.goodsName }}</div>
              <div class="price">¥ {{ item.sellingPrice }}</div>
            </div>
          </div>
        </div>
      </van-skeleton>
    </div>
    
    <!-- 商品区域 - 热门商品 -->
    <div class="good">
      <header class="good-header">热门商品</header>
      <van-skeleton title :row="3" :loading="state.loading">
        <div class="good-box">
          <div 
            class="good-item" 
            v-for="item in state.hots" 
            :key="item.goodsId" 
            @click="goToDetail(item)"
          >
            <img :src="$filters.prefix(item.goodsCoverImg)" alt="">
            <div class="good-desc">
              <div class="title">{{ item.goodsName }}</div>
              <div class="price">¥ {{ item.sellingPrice }}</div>
            </div>
          </div>
        </div>
      </van-skeleton>
    </div>
    
    <!-- 商品区域 - 最新推荐 -->
    <div class="good" :style="{ paddingBottom: '100px' }">
      <header class="good-header">最新推荐</header>
      <van-skeleton title :row="3" :loading="state.loading">
        <div class="good-box">
          <div 
            class="good-item" 
            v-for="item in state.recommends" 
            :key="item.goodsId" 
            @click="goToDetail(item)"
          >
            <img :src="$filters.prefix(item.goodsCoverImg)" alt="">
            <div class="good-desc">
              <div class="title">{{ item.goodsName }}</div>
              <div class="price">¥ {{ item.sellingPrice }}</div>
            </div>
          </div>
        </div>
      </van-skeleton>
    </div>
  </div>
</template>
<script setup>
import { reactive, onMounted, nextTick } from 'vue'
import { useRouter } from 'vue-router'

// 组件导入
import swiper from '@/components/Swiper.vue'
import navBar from '@/components/NavBar.vue'

// 服务与工具导入
import { getHome } from '@/service/home'
import { getLocal } from '@/common/js/utils'
import { showLoadingToast, closeToast, showToast } from 'vant'
import { useCartStore } from '@/stores/cart'

const cart = useCartStore()
const router = useRouter()

const state = reactive({
  // 轮播图与登录状态
  swiperList: [],         // 轮播图列表
  isLogin: false,         // 是否已登录
  headerScroll: false,    // 滚动透明判断
  
  // 商品列表
  hots: [],
  newGoodses: [],
  recommends: [],
  
  // 分类列表（已优化缩进）
  categoryList: [
  {
    name: '东软超市',
    imgUrl: 'https://cdn-icons-png.flaticon.com/512/1170/1170576.png',
    categoryId: 100001
  },
  {
    name: '东软服饰',
    imgUrl: 'https://img.icons8.com/color/96/t-shirt.png',
    categoryId: 100003
  },
  {
    name: '全球购',
    imgUrl: 'https://cdn-icons-png.flaticon.com/512/684/684908.png',
    categoryId: 100002
  },
  {
    name: '东软生鲜',
    imgUrl: 'https://img.icons8.com/color/96/vegetarian-food.png',
    categoryId: 100004
  },
  {
    name: '东软到家',
    imgUrl: 'https://img.icons8.com/color/96/home.png',
    categoryId: 100005
  },
  {
    name: '充值缴费',
    imgUrl: 'https://img.icons8.com/color/96/money-transfer.png',
    categoryId: 100006
  },
  {
    name: '9.9元拼',
    imgUrl: 'https://img.icons8.com/color/96/discount--v1.png',
    categoryId: 100007
  },
  {
    name: '品质外卖',
    imgUrl: 'https://cdn-icons-png.flaticon.com/512/1046/1046784.png',
    categoryId: 100008
  },
  {
    name: '同城闪购',
    imgUrl: 'https://cdn-icons-png.flaticon.com/512/892/892458.png',
    categoryId: 100009
  },
  {
    name: '全部',
    imgUrl: 'https://img.icons8.com/color/96/product.png',
    categoryId: 100010
  }
],
  
  loading: true
})

// 生命周期函数
onMounted(async () => {
  const token = getLocal('token')
  if (token) {
    state.isLogin = true
    // 获取购物车数据
    cart.updateCart()
  }
  
  showLoadingToast({
    message: '加载中...',
    forbidClick: true
  })
  
  const { data } = await getHome()
  state.swiperList = data.carousels
  state.newGoodses = data.newGoodses
  state.hots = data.hotGoodses
  state.recommends = data.recommendGoodses
  state.loading = false
  closeToast()
})

nextTick(() => {
  document.body.addEventListener('scroll', () => {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
    scrollTop > 100 ? state.headerScroll = true : state.headerScroll = false
  })
})

// 方法定义
const goToDetail = (item) => {
  router.push({ path: `/product/${item.goodsId}` })
}

const tips = () => {
  showToast('敬请期待')
}
</script>
<style lang="less" scoped>
@import '../common/style/mixin';

/* 头部导航样式 */
.home-header {
  position: fixed;
  left: 0;
  top: 0;
  .wh(100%, 50px);
  .fj();
  line-height: 50px;
  padding: 0 15px;
  .boxSizing();
  font-size: 15px;
  color: #fff;
  z-index: 10000;
  
  .nbmenu2 {
    color: @primary;
  }
  
  &.active {
    background: @primary;
    
    .nbmenu2 {
      color: #fff;
    }
    
    .login {
      color: #fff;
    }
  }
  
  .header-search {
    display: flex;
    width: 74%;
    line-height: 20px;
    margin: 10px 0;
    padding: 5px 0;
    color: #232326;
    background: rgba(255, 255, 255, .7);
    border-radius: 20px;
    
    .app-name {
      padding: 0 10px;
      color: @primary;
      font-size: 20px;
      font-weight: bold;
      border-right: 1px solid #666;
    }
    
    .icon-search {
      padding: 0 10px;
      font-size: 17px;
    }
    
    .search-title {
      font-size: 12px;
      color: #666;
      line-height: 21px;
    }
  }
  
  .icon-iconyonghu {
    color: #fff;
    font-size: 22px;
  }
  
  .login {
    color: @primary;
    line-height: 52px;
    
    .van-icon-manager-o {
      font-size: 20px;
      vertical-align: -3px;
    }
  }
}

/* 分类列表样式 */
.category-list {
  display: flex;
  flex-shrink: 0;
  flex-wrap: wrap;
  width: 100%;
  padding-bottom: 13px;
  
  div {
    display: flex;
    flex-direction: column;
    width: 20%;
    text-align: center;
    
    img {
      .wh(36px, 36px);
      margin: 13px auto 8px auto;
    }
  }
}

/* 商品区域样式 */
.good {
  .good-header {
    background: #f9f9f9;
    height: 50px;
    line-height: 50px;
    text-align: center;
    color: @primary;
    font-size: 16px;
    font-weight: 500;
  }
  
  .good-box {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
    
    .good-item {
      box-sizing: border-box;
      width: 50%;
      border-bottom: 1PX solid #e9e9e9;
      padding: 10px 10px;
      
      img {
        display: block;
        width: 120px;
        margin: 0 auto;
      }
      
      .good-desc {
        text-align: center;
        font-size: 14px;
        padding: 10px 0;
        
        .title {
          color: #222333;
        }
        
        .price {
          color: @primary;
        }
      }
      
      &:nth-child(2n + 1) {
        border-right: 1PX solid #e9e9e9;
      }
    }
  }
}

/* 楼层列表样式（未使用但保留） */
.floor-list {
  width: 100%;
  padding-bottom: 50px;
  
  .floor-head {
    width: 100%;
    height: 40px;
    background: #F6F6F6;
  }
  
  .floor-content {
    display: flex;
    flex-shrink: 0;
    flex-wrap: wrap;
    width: 100%;
    .boxSizing();
    
    .floor-category {
      width: 50%;
      padding: 10px;
      border-right: 1px solid #dcdcdc;
      border-bottom: 1px solid #dcdcdc;
      .boxSizing();
      
      &:nth-child(2n) {
        border-right: none;
      }
      
      p {
        font-size: 17px;
        color: #333;
        
        &:nth-child(2) {
          padding: 5px 0;
          font-size: 13px;
          color: @primary;
        }
      }
      
      .floor-products {
        .fj();
        width: 100%;
        
        img {
          .wh(65px, 65px);
        }
      }
    }
  }
}
</style>