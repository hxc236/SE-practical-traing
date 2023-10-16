const util = require("../../utils/util")
const app = getApp() // 导入app.js文件

// pages/detail/detail.js
Page({
  data: {
    commentWithUsers: [],
    send: {}, // 加入购物车
    item: {}, // 存储商品信息
    item_num: 1,
    api: app.globalData.api,
    post: {}, // 发送评论
    collected : false,
  },
  onLoad() {
    this.data.item = wx.getStorageSync('商品信息')
    this.starRefresh()
    this.commentRefresh()
  },
  starRefresh() {
    var url = '/collect/collected/vx?uid=' + wx.getStorageSync('uid') + '&pid=' + this.data.item.id;
    util.http(url, resp => {
      console.log('star', resp)
      this.data.collected = resp.collected
      this.setData(this.data)
    })
    this.setData(this.data)
  },
  commentRefresh() {
    util.http('/comment/vx', {
      pid: this.data.item.id
    }, resp => {
      console.log('comment', resp)
      for (let i = 0; i < resp.commentWithUsers.length; i++) {
        let temp = {
          username: '匿名用户',
          // username: resp.comments[i].username,
          comment: resp.commentWithUsers[i].comment,
          time: resp.commentWithUsers[i].ctime.substring(0, 10)
        }
        this.data.commentWithUsers.push(temp)
      }
      this.setData(this.data)
    })
    // 当全局变量中的数据更新，更新到新页面
    this.setData(this.data)
  },
  onCountInput(e) {
    this.setData({
      item_num: e.detail.value
    })
    // console.log(this.data)
  },
  onCommentInput(e) {
    this.data.post.comment = e.detail.value
    // console.log(e.detail.value)
  },
  postComment() {
    this.data.post.pid = this.data.item.id
    this.data.post.uid = wx.getStorageSync('uid')
    console.log(this.data.post)
    if (this.data.post.comment != "") {
      util.http('/comment/add', this.data.post, resp => {
        console.log(resp)
        // if (resp.code == 1) {
          // util.alert('发布成功')
          this.data.comment = ''
          this.setData(this.data)
          this.commentRefresh()
        // }
      })
    } else {
      util.alert('你还没有评论哦~')
    }

  },
  onAddCartButton() {
    this.data.send = {
      uid: wx.getStorageSync('uid'),
      pid: this.data.item.id,
      count: this.data.item_num
    }
    if (this.data.send.count == null || this.data.send.count == "") {
      util.alert('商品数量不能为空！')

    } else {
      console.log(this.data.send)
      util.http('/cart/add/vx', this.data.send, resp => {
        console.log(resp)
        if (resp.code == 1) {
          util.alert('添加购物车成功！')
        } else {
          util.alert(resp.msg)
        }
      })
    }

  },
  countRemove() {
    console.log(this.data.item_num)
    this.data.item_num -= 1
    if (this.data.item_num < 0) {
      this.data.item_num = 0
    }
    this.setData(this.data)
  },
  countAdd() {
    this.data.item_num += 1
    this.setData(this.data)
  },
  onStarButton() {
    var url = '/collect/change/vx?uid=' + wx.getStorageSync('uid') + '&pid=' + this.data.item.id;
    util.http(url, resp => {
      console.log(resp)
      this.starRefresh()
    })
  },
  onPullDownRefresh() {

  },
})