const util = require("../../utils/util")
const app = getApp()

Page({
  data: {
    stars: [],
    api: app.globalData.api,
    hasStarItem: false,
  },
  onLoad() {

    util.http('/collect/index/vx?uid='+wx.getStorageSync('uid'), resp => {
      console.log('star', resp)
      if(resp.code == 1) {
        if(resp.collects.length > 0) {
          this.data.hasStarItem = true
        } else {
          this.data.hasStarItem = false
        }
        this.data.stars = resp.collects
        this.setData(this.data)
        // util.alert('success receive star')
        
      }

    })
  },
})