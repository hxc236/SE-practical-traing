## 微信小程序

### 相关知识点

#### 新建页面

注意page什么意思，data是啥，onLoad是啥，还可以自己写事件

```
// pages/login/login.js
Page({  // 系统函数
  data: {   // 全局变量
    username: '',
    password: '',
    
  },
  onLoad(options) {   // 页面主函数

  },
  onInput(e) {  // e指代当前事件
    // e.detail.value 即 写的内容
    // e.currentTarget.dataset.name 即 标签的data-name
    // this 指的是page
    // this.data指的是page的data
    // this.data.username <==> this.data['username']
    // this.data.password <==> this.data['password']
    this.data[e.currentTarget.dataset.name] = e.detail.value;
  },
  submit() {
    console.log(this.data.username);
    console.log(this.data.password);
  },
})
```