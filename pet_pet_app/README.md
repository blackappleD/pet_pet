# 移动端应用

## 技术栈
- Flutter 3.0+
- Dart 2.17
- GetX 状态管理
- Dio 网络请求
- 集成主要功能包：
  - image_picker: ^0.8.7+3
  - qr_flutter: ^4.1.0
  - fl_chart: ^0.55.2
  - amap_flutter_map: ^3.0.0

## 项目结构
lib/
├── common/         # 公共资源
│   ├── styles/     # 样式主题
│   ├── widgets/    # 通用组件
│   └── utils/      # 工具类
├── modules/        # 功能模块
│   ├── auth/       # 认证模块
│   ├── pet/        # 宠物管理
│   ├── health/     # 健康管理
│   ├── finance/    # 花销管理
│   └── social/     # 社交模块
├── routes/         # 路由配置
├── services/       # 服务层
│   ├── api/        # 接口服务
│   └── storage/    # 本地存储
└── main.dart       # 入口文件 