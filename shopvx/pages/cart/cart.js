const util = require("../../utils/util")
const app = getApp()

Page({
  data: {
    api: app.globalData.api,
    cart: [],
    hasItem: false,
    totalPrice: 0,
  },
  cartDel(e) {
    let del = {
      id: e.currentTarget.dataset.id
    }
    console.log(del)
    util.http('/cart/delete/vx', del, resp => {
      console.log(resp)
      util.alert('删除成功！')
      this.onLoad()
    })
  },
  checkInfo(e) {
    // var 定义局部变量
    var item = e.currentTarget.dataset.item
    console.log(item)
    util.http('/product/selectById/vx?id='+item, resp => {
      console.log(resp)
      // 存入小程序内部
      wx.setStorageSync('商品信息', resp.product)
      // 打开新的小程序页面
      util.redirect('detail')
    })

  },
  onTabItemTap() {
    this.onLoad()
  },
  onLoad() {
    this.setData(this.data)

    util.http('/cart/index/vx?uid=' + wx.getStorageSync('uid'), resp => {
      // console.log(resp)
      this.data.cart = resp.cart
      this.data.totalPrice = 0
      if (this.data.cart != null && this.data.cart.length > 0) {
        this.data.hasItem = true
        for (let i = 0; i < this.data.cart.length; i++) {
          this.data.totalPrice += this.data.cart[i].price * this.data.cart[i].count
        }
      } else {
        this.data.hasItem = false
      }

      // 将变化的数据更新到数据中
      this.setData(this.data)
    })
    this.setData(this.data)
  },
  handleCheckout() {
    wx.setStorageSync('购物车信息', this.data.cart)
    wx.setStorageSync('总计', this.data.totalPrice)
    console.log(wx.getStorageSync('总计'))

    util.redirect('checkout')
  }
})