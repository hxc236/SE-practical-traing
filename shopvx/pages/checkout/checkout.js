const util = require("../../utils/util")
const app = getApp() // 导入app.js文件

Page({
  data: {
    carts: [],
    mobile: '',
    address: '',
    totalPrice: '',
    checkoutSend: []
  },
  onLoad() {
    this.data.carts = wx.getStorageSync('购物车信息')
    this.data.totalPrice = wx.getStorageSync('总计')

    this.setData(this.data)
  },
  onCheckoutMobile(e) {
    this.data.mobile = e.detail.value
  },
  onCheckoutAddress(e) {
    this.data.address = e.detail.value
  },
  onCheckoutButton() {
    this.data.checkoutSend = []
    
    for(let i = 0; i < this.data.carts.length; i ++ )
    {
      this.data.checkoutSend.push({
        "uid": wx.getStorageSync('uid'),
        "mobile": this.data.mobile,
        "address": this.data.address,
        "total": "" + this.data.totalPrice,
        "pid": "" + this.data.carts[i].pid,
        "count": "" + this.data.carts[i].count,
      })
      // console.log(this.data.carts[i])
    }
    console.log('send', JSON.stringify(this.data.checkoutSend))
    // util.alert(JSON.stringify(this.data.checkoutSend))

    

    wx.request({
      url: 'http://192.168.18.222:8080/order/add/vx',
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      data: JSON.stringify(this.data.checkoutSend),
      success: function(resp) {
        console.log(resp);
        // 处理成功响应
      },
      fail: function(error) {
        console.log(error);
        // 处理请求失败
      }
    });
    
    util.http('/cart/clear/vx?uid=' + wx.getStorageSync('uid'), resp => {
      console.log('delcart', resp)
    })
    wx.switchTab({
      url: '/pages/index/index'
 })
  }
})