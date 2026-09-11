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


**Tạo bởi Quý shopTeam | Version 1.0.0**
