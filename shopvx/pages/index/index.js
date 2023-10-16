const util = require('../../utils/util')
const app = getApp()  // 导入app.js文件
Page({
  data: { // 全局变量
      api: app.globalData.api,
      hots: [],
      categorys: [],
      cid: 0,
      products: [],
  },
  detail(e) {
    // var 定义局部变量
    var item = e.currentTarget.dataset.item
    console.log(item)
    // 存入小程序内部
    wx.setStorageSync('商品信息', item)
    // 打开新的小程序页面
    util.redirect('detail')
  },
  onLoad() {  // 页面主函数
    util.http('/product/index/vx', resp => {
     // console.log(resp)
      this.data.hots = resp.hots
      this.data.categorys = resp.categorys
      this.data.products = resp.products
      if(resp.categorys.length > 0) { // 刚进入时选中第一个
        this.data.cid = resp.categorys[0].id;
      }
      // 将变化的数据更新到数据中
      this.setData(this.data)
    })
  },
  onPullDownRefresh() { // 下拉时会调用 更新当前的商品列表
    util.http('/product/index/vx?cid='+this.data.cid, resp => {
      this.data.products = resp.products
      //this.setData(this.data) // 这句被封装到下一句了
      util.stopPullSetData(this) // 停止下拉 缩回去
    })
  },
  cateClick(e) { // 绑定类别的点击事件
    // 点击时把cid带过来，根据 data-cid="{{item.id}}"
    this.data.cid = e.currentTarget.dataset.cid
    util.http('/product/index/vx?cid='+this.data.cid, resp => {
      // resp 为 Java 返回的数据
      // console.log(resp)
      this.data.products = resp.products
      this.setData(this.data) // 这一句更新到页面才能把cid带回wxml
    })
    
  }
})