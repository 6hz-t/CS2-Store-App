# CS2 饰品交易平台 Android 应用
## 一、概述
CS2饰品交易平台Android应用是专为CS:GO2玩家打造的开源交易平台，提供安全、便捷、高效的饰品交易服务。支持用户注册登录、浏览饰品市场、管理个人库存与订单，为CS2饰品交易提供完整解决方案，**0手续费**的交易模式更是极大降低玩家交易成本，让每一笔交易收益都能全额到账 。

## 二、功能
### 0手续费交易
- 平台核心优势在于全流程0手续费。无论是上架商品、购买或出售饰品，玩家无需支付任何平台费用。对高频交易玩家而言，长期积累可节省大量资金，显著提升交易收益，真正实现玩家利益最大化，激发用户交易积极性。

### 便捷的用户管理
- 支持通过账号、密码、确认密码和SteamID完成注册登录，保障信息准确与账户安全。
- 个人中心展示昵称、账号、Steam交易状态等信息，集成设置、订单管理、账号信息修改、密码重置和退出登录等功能，一站式满足用户个性化管理需求。

### 全面的商品与订单管理
- **商品展示**：市场页面直观呈现商品图片、名称、价格、在售数量及卖家信息，点击即可查看详情；库存管理支持用户轻松上架商品，实现闲置饰品快速变现。
- **订单管理**：清晰划分待处理与已完成订单列表，展示订单编号、商品描述、状态及图片，便捷的交易处理功能让订单操作高效流畅。

## 三、技术架构
### 前端
- **界面设计**：基于Android的XML布局文件，打造美观且适配多设备的响应式界面。
- **图片处理**：使用Glide库实现高效图片加载与缓存，确保商品图片快速、流畅展示。
- **视图绑定**：启用Android视图绑定功能，简化代码对布局文件视图控件的引用，提升开发效率。

### 后端
- **网络交互**：采用OkHttp库进行稳定的网络请求，保障与服务器的数据交互快速、可靠。
- **数据解析**：通过Jackson库处理JSON数据，确保数据传输与解析的准确性。
- **数据库**：使用MySQL数据库存储数据，借助MySQL Connector/J驱动实现Android应用与数据库的无缝连接。
  

### 项目构建
利用Gradle进行项目构建与依赖管理，确保项目具备良好的可维护性与扩展性。

## 四、项目结构
```
CS2-Store-App/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/csapp_10/
│   │   │   │       ├── activity/
│   │   │   │       │   └── frament/
│   │   │   │       │       └── adapter/
│   │   │   │       │           └── ProductListAdapter.java
│   │   │   │       ├── DBUtils/
│   │   │   │       │   └── SQLite.java
│   │   │   │       ├── Entity/
│   │   │   │       │   ├── MarketGood.java
│   │   │   │       │   └── Product.java
│   │   │   │       ├── Mystyle/
│   │   │   │       │   └── WrapContentListView.java
│   │   │   │       └── ...
│   │   │   ├── res/
│   │   │       ├── drawable/
│   │   │       │   ├── ic_launcher_background.xml
│   │   │       │   └── ...
│   │   │       ├── layout/
│   │   │       │   ├── activity_main.xml
│   │   │       │   ├── activity_register.xml
│   │   │       │   └── ...
│   │   │       ├── mipmap-anydpi-v26/
│   │   │       │   └── ic_launcher.xml
│   │   │       ├── values/
│   │   │       │   ├── strings.xml
│   │   │       │   └── themes.xml
│   │   └── test/
│   │       └── java/
│   │           └── com/example/csapp_10/
│   │               └── ExampleUnitTest.java
│   └── build.gradle.kts
├── .idea/
│   ├── runConfigurations.xml
│   └── vcs.xml
└── ...
```

## 五、安装步骤
### 克隆项目
```bash
git clone https://github.com/6hz-t/CS2-Store-App.git
```

### 导入项目
打开Android Studio，选择 `File` -> `Open`，选中克隆的项目文件夹进行导入。

### 配置数据库
在项目代码中找到数据库连接配置部分，填写正确的MySQL数据库信息，包括主机地址、端口、用户名、密码等。

### 运行项目
连接Android设备或启动Android模拟器，点击Android Studio中的运行按钮，选择目标设备运行应用。

## 六、贡献说明
欢迎社区开发者参与项目共建，贡献步骤如下：
1. **Fork项目**：点击GitHub页面的 `Fork` 按钮，将项目复制到个人仓库。
2. **克隆仓库**：在本地终端执行以下命令，克隆个人仓库项目：
```bash
git clone https://github.com/your-username/CS2-Store-App.git
```
3. **创建分支**：切换到新分支进行开发：
```bash
git checkout -b feature/your-feature-name
```
4. **开发与提交**：完成功能开发或问题修复后，提交代码并推送到个人仓库：
```bash
git add .
git commit -m "清晰描述本次提交内容"
git push origin feature/your-feature-name
```
5. **发起Pull Request**：在GitHub上从个人仓库分支向原项目仓库发起Pull Request，等待项目维护者审核。

## 七、许可证
本项目采用[MIT许可证](https://opensource.org/licenses/MIT)，允许自由使用、修改和分发代码，但需保留许可证声明。

## 八、联系我们
使用中若遇问题或有优化建议，欢迎通过以下方式反馈：
- **GitHub Issues**：在[项目Issues页面](https://github.com/6hz-t/CS2-Store-App/issues)提交问题或建议。
- **邮件**：发送邮件至[6hz-t@outlook.com](mailto:6hz-t@outlook.com) 沟通交流。 
