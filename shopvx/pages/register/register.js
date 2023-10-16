const util = require('../../utils/util')    // 导入util.js

// pages/login/login.js
Page({  // 系统函数
  data: {   // 全局变量
    username: '',
    password: '',
    confirmedPassword: '',
  },
  onLoad() {   // 页面主函数
    
  },
  onInput(e) {  // e指代当前事件
    // e.detail.value 即 写的内容
    // e.currentTarget.dataset.name 即 标签的data-name
    // this 指的是page
    // this.data指的是page的data
    // this.data.username <==> this.data['username']
    // this.data.password <==> this.data['password']
    this.data[e.currentTarget.dataset.name] = e.detail.value;
    console.log(this.data.username);
    console.log(this.data.password);
  },
  submit() {
    // console.log(this.data.username);
    // console.log(this.data.password);

    // 小程序的数据提交到Java
    util.http('/login/vx', this.data, resp => {
      if(resp.code == 1) {
        // 把这个人的id存入小程序软件内部
        wx.setStorageSync('uid', resp.uid)
        // 打开小程序首页
        wx.switchTab({
          url: '../index/index',
        })
      } else {
        util.alert(resp.msg);
      }
    })
  },
})