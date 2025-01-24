# 管理端前端

## 技术栈
- Vue 3 + TypeScript
- Element Plus
- Vue Router 4
- Pinia 状态管理
- Axios 请求库

## 项目结构
├── public
├── src
│   ├── api        # 接口定义
│   ├── assets     # 静态资源
│   ├── components # 公共组件
│   ├── router     # 路由配置
│   ├── stores     # Pinia状态管理
│   ├── types      # TS类型定义
│   ├── utils      # 工具函数
│   └── views      # 页面组件
│       ├── pet-management    # 宠物管理
│       ├── user-management   # 用户管理
│       ├── content-audit     # 内容审核
│       └── data-analysis     # 数据分析
├── .env.development  # 开发环境配置
└── vue.config.js     # Vue配置 