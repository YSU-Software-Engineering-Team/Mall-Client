

<template>
<div class="nav-bar vertical-nav-bar van-hairline--top">
  <ul class="nav-list">
    <router-link class="nav-list-item active" to="home">
      <i class="nbicon nblvsefenkaicankaoxianban-1"></i>
      <span>首页</span>
    </router-link>
    <router-link class="nav-list-item" to="category">
      <i class="nbicon nbfenlei"></i>
      <span>分类</span>
    </router-link>
    <router-link class="nav-list-item" to="cart">
      <i><van-icon name="shopping-cart-o" :badge="!cart.count ? '' : cart.count" /></i>
      <span>购物车</span>
    </router-link>
    <router-link class="nav-list-item" to="user">
      <i class="nbicon nblvsefenkaicankaoxianban-"></i>
      <span>我的</span>
    </router-link>
  </ul>
</div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useCartStore } from '@/stores/cart'
import { getLocal } from '@/common/js/utils'
const route = useRoute()
const cart = useCartStore()
onMounted(() => {
  const token = getLocal('token')
  const path = route.path
  if (token && !['/home', '/category'].includes(path)) {
    cart.updateCart()
  }
})
</script>

<style lang="less" scoped >
    @import '../common/style/mixin';
    .nav-bar {
  position: fixed;
  top: 50px; // 与页面顶端的距离
  left: 0;
  width: 70px;
  height: calc(100vh - 50px); // 高度减去顶部空隙
  padding: 10px 0;
  z-index: 1000;
  background: #fff;
  border-right: 1px solid #eee;

  .nav-list {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100%;

    .nav-list-item {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      margin: 10px 0;
      color: #666;
      text-decoration: none;

      &.router-link-active {
        color: @primary;
      }

      i {
        font-size: 22px;
      }

      span {
        font-size: 12px;
        margin-top: 5px;
      }

      .van-icon-shopping-cart-o {
        margin: 0 auto;
        margin-bottom: 2px;
      }
    }
  }
}

</style>
