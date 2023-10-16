const util = require("../../utils/util")
const app = getApp()

Page({
  data: {
    api: app.globalData.api,
    user: '',
    username: '',
    photo: '',
  },
  onLoad() {
    // 根据uid获取用户信息
    var uid = wx.getStorageSync('uid')
    util.http('/userinfo/vx?uid=' + uid, resp => {
      console.log(resp)
      this.data.user = resp.userBean.user
      this.data.username = resp.userBean.username
      this.data.photo = resp.userBean.logo
      this.setData(this.data)
    })
  },
  openComment() {
    util.redirect('commentInfo')
  },
  openStar() {
    util.redirect('star')
  },
  openOrder() {
    util.redirect('order')
  },
  logout() {
    wx.removeStorageSync('uid')
    wx.redirectTo({
      url: '../login/login',
    })
  }
})
