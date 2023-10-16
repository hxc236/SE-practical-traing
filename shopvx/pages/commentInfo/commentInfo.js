const util = require("../../utils/util")
const app = getApp()

Page({
  data: {
    comments: [],

  },
  onLoad() {
    util.http('/comment/vx/usercomment', {
      uid: wx.getStorageSync('uid')
    }, resp => {
      console.log(resp)
      for (let i = 0; i < resp.comments.length; i++) {
        let temp = {
          username: '我的评论',
          // comment: 'aaaaaa',
          // time: '123123'
          // username: resp.comments[i].username,
          comment: resp.comments[i].comment,
          time: resp.comments[i].ctime.substring(0, 10)
        }
        this.data.comments.push(temp)
      }
      this.setData(this.data)
    })
    this.setData(this.data)
  },
  
})