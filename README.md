# 🛍️ Quý shop- Nền Tảng Bán Hàng Online

Một dự án web bán hàng chuyên nghiệp được xây dựng với **Spring Boot 3.1.5**, **Thymeleaf**, **Bootstrap 5**, và **MySQL**.

---

## 📋 Tính Năng

✅ **Giao diện đẹp & chuyên nghiệp** - Bootstrap 5 responsive design
✅ **Quản lý sản phẩm** - CRUD sản phẩm, danh mục
✅ **Quản lý đơn hàng** - Tạo, theo dõi đơn hàng
✅ **Hệ thống tài khoản** - Đăng ký, đăng nhập, phân quyền
✅ **Phân quyền người dùng** - Admin & Customer roles
✅ **Dashboard admin** - Tổng quan thống kê
✅ **Database tích hợp** - MySQL với dữ liệu mẫu sẵn

---

## 🚀 Hướng Dẫn Cài Đặt & Chạy

### 1️⃣ **Chuẩn Bị Môi Trường**
- Java JDK 17+ 
- MySQL 8.0+
- Maven 3.6+
- IDE (Eclipse, IntelliJ IDEA hoặc VS Code)

### 2️⃣ **Import Database**
```bash
# Mở MySQL Workbench hoặc MySQL CLI
mysql -u root -p

# Chạy file SQL
source D:\KimQuy_CNPM\ecommerce\database.sql
```

### 3️⃣ **Cấu Hình Kết Nối Database**
Sửa file `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ecommerce?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: YOUR_PASSWORD_HERE  # ← Thay password của bạn
```

### 4️⃣ **Import Project Vào IDE**
- **Eclipse**: File → Import → Maven → Existing Maven Projects → Chọn D:\KimQuy_CNPM\ecommerce
- **IntelliJ**: File → Open → Chọn D:\KimQuy_CNPM\ecommerce
- **VS Code**: Open Folder → D:\KimQuy_CNPM\ecommerce

### 5️⃣ **Chạy Maven & Ứng Dụng**
```bash
# Terminal trong folder D:\KimQuy_CNPM\ecommerce

# Tải dependencies
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

**Ứng dụng sẽ chạy tại:** http://localhost:8080

---

## 👥 Tài Khoản Mẫu

### Admin (Quản Lý)
- **Username:** admin
- **Password:** password123
- **Email:** admin@shopnow.com
- **Link:** http://localhost:8080/admin/dashboard

### Customer (Khách Hàng)
- **Username:** customer1
- **Password:** password123
- **Email:** customer1@email.com

---

## 📁 Cấu Trúc Thư Mục

```
ecommerce/
├── src/
│   ├── main/
│   │   ├── java/com/ecommerce/
│   │   │   ├── config/              (SecurityConfig, UserDetailsService)
│   │   │   ├── entity/              (User, Product, Order, Category, OrderItem)
│   │   │   ├── repository/          (JPA Repositories)
│   │   │   ├── service/             (Business Logic)
│   │   │   ├── controller/          (Web Controllers)
│   │   │   └── EcommerceApplication.java
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   │   ├── auth/            (login.html, register.html)
│   │   │   │   ├── customer/        (products.html, orders.html)
│   │   │   │   ├── admin/           (dashboard.html, products.html)
│   │   │   │   └── index.html
│   │   │   ├── static/              (CSS, JS, Images)
│   │   │   └── application.yml
│   └── test/
├── pom.xml
├── database.sql                      (Schema & Sample Data)
└── README.md                         (This file)
```

---

## 🔐 Tính Năng Bảo Mật

- ✅ **Spring Security** - Xác thực & phân quyền
- ✅ **BCrypt Password Encoding** - Mã hóa mật khẩu an toàn
- ✅ **Role-Based Access Control** - Phân quyền theo role
- ✅ **CSRF Protection** - Bảo vệ CSRF attack
- ✅ **SQL Injection Prevention** - Dùng JPA Prepared Statements

---

## 📝 Các Endpoint Chính

### **Public Endpoints**
- `GET /` - Trang chủ
- `GET /products` - Danh sách sản phẩm
- `GET /auth/login` - Đăng nhập
- `GET /auth/register` - Đăng ký

### **Customer Endpoints** (Require Login)
- `GET /orders` - Đơn hàng của tôi
- `GET /orders/{id}` - Chi tiết đơn hàng
- `POST /orders/create` - Tạo đơn hàng

### **Admin Endpoints** (Require Admin Role)
- `GET /admin/dashboard` - Dashboard
- `GET /admin/products` - Quản lý sản phẩm
- `GET /admin/products/add` - Thêm sản phẩm
- `POST /admin/products/save` - Lưu sản phẩm
- `GET /admin/orders` - Quản lý đơn hàng

---

## 🔧 Mở Rộng & Phát Triển

Để thêm chức năng mới:

1. **Tạo Entity mới** → `src/main/java/com/ecommerce/entity/`
2. **Tạo Repository** → `src/main/java/com/ecommerce/repository/`
3. **Tạo Service** → `src/main/java/com/ecommerce/service/`
4. **Tạo Controller** → `src/main/java/com/ecommerce/controller/`
5. **Tạo View HTML** → `src/main/resources/templates/`

---

## 🐛 Troubleshooting

### Lỗi: "Cannot connect to MySQL"
```
Kiểm tra:
✓ MySQL service đang chạy
✓ Username & Password trong application.yml
✓ Database "ecommerce" đã tạo
```

### Lỗi: "Port 8080 already in use"
```
# Thay đổi port trong application.yml:
server:
  port: 9090
```

### Lỗi: "Dependency not found"
```bash
mvn clean install -U
```

---

## 📞 Liên Hệ & Support

- **Email:** support@shopnow.com
- **Website:** www.shopnow.com
- **GitHub:** github.com/shopnow

---

## 📄 License

MIT License - Tự do sử dụng cho mục đích thương mại & cá nhân

---

**Tạo bởi Quý shopTeam | Version 1.0.0**
