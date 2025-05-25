# 学生管理系统

一个基于 Spring Boot 和 Vue.js 的全栈学生管理系统，提供课程管理、成绩管理、好友管理、消息管理和转账等功能。

## 功能特性

### 管理员功能
- 课程管理
  - 添加新课程
  - 查看课程列表
- 成绩管理
  - 录入学生成绩
  - 查看成绩列表

### 学生功能
- 选课管理
  - 选择课程
  - 查看已选课程
- 好友管理
  - 添加好友
  - 查看好友列表
- 消息管理
  - 发送消息
  - 查看消息记录
- 转账管理
  - 学生间转账
  - 查看转账记录

## 技术栈

### 后端
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

### 前端
- Vue.js
- Axios
- CSS3

## 项目结构
```
├── backend/                # 后端项目目录
│   ├── src/               # 源代码
│   │   ├── main/
│   │   │   ├── java/     # Java源代码
│   │   │   └── resources/ # 配置文件
│   └── pom.xml           # Maven配置文件
│
└── frontend/              # 前端项目目录
    └── ui/               # Vue.js项目
        ├── src/          # 源代码
        │   ├── components/ # Vue组件
        │   └── App.vue   # 主应用组件
        └── package.json  # 项目依赖配置
```

## 安装步骤

### 后端设置
1. 确保已安装 Java 8+ 和 Maven
2.安装依赖：
   ```bash
   mvn install
   ```
3.运行应用：
   ```bash
   mvn spring-boot:run
   ```

### 前端设置
1. 确保已安装 Node.js
2. 进入前端项目目录：
   ```bash
   cd frontend/ui
   ```
3. 安装依赖：
   ```bash
   npm install
   ```
4. 运行开发服务器：
   ```bash
   npm run serve
   ```

## 数据库配置
1. 创建 MySQL 数据库
2. 在 `backend/src/main/resources/application.properties` 中配置数据库连接信息：
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## 使用说明

### 管理员登录
- 访问系统首页
- 使用管理员账号登录
- 进入管理员界面进行课程和成绩管理

### 学生登录
- 访问系统首页
- 使用学生账号登录
- 进入学生界面进行选课、好友管理、消息发送和转账操作

## 界面预览
- 管理员界面：课程管理和成绩管理
- 学生界面：选课、好友、消息和转账管理
- 采用黑白简约风格设计
- 响应式布局，适配不同设备

## 开发团队
- 后端开发：[开发者姓名]
- 前端开发：[开发者姓名]

## 许可证
MIT License 