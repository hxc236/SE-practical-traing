// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {
    // api: 'http://127.0.0.1:8080',  // java的Ip地址
    // api: 'http://10.8.126.39:8080', // wx后端
    // api: 'http://10.8.126.46:8080', // cch后端
    // api: 'http://10.8.126.26:8080', // lqm后端
    // api: 'http://192.168.129.140:8080',
    api: 'http://192.168.18.222:8080',
    userInfo: null
  }
})
