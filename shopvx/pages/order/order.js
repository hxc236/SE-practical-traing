const util = require("../../utils/util")
const app = getApp()

Page({
  data: {
    orders: [],
    api: app.globalData.api,
    hasOrderItem: false,
  },
  onLoad() {
    util.http('/order/index/vx?uid=' + wx.getStorageSync('uid'), resp => {
      console.log('订单信息', resp)

      // ---test----
      // for(let i = 0; i < 10; i ++ ) {
      //   this.data.orders.push({
      //     product : 'aaa',
      //     price : 10,
      //     ctime : "111",
      //   })
        
      // }

      // ---test----

      if(resp.code == 1) {
        if(resp.buyer_orders.length > 0) {
          this.data.hasOrderItem = true;
          this.data.orders = resp.buyer_orders;
          
        }
      } else {
        this.data.hasOrderItem = false;
      }
      this.setData(this.data)
    })
    this.setData(this.data)
  },
})