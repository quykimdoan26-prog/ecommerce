-- Create Database
CREATE DATABASE IF NOT EXISTS ecommerce;
USE ecommerce;

-- Table: users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    role VARCHAR(50) NOT NULL DEFAULT 'CUSTOMER',
    status BOOLEAN DEFAULT 1,
    created_at BIGINT,
    updated_at BIGINT,
    INDEX idx_username (username),
    INDEX idx_email (email)
);

-- Table: categories
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    status BOOLEAN DEFAULT 1,
    created_at BIGINT,
    INDEX idx_name (name)
);

-- Table: products
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL,
    quantity INT DEFAULT 0,
    category_id BIGINT NOT NULL,
    image_url VARCHAR(500),
    status BOOLEAN DEFAULT 1,
    created_at BIGINT,
    updated_at BIGINT,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    INDEX idx_category (category_id),
    INDEX idx_name (name)
);

-- Table: orders
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount DOUBLE DEFAULT 0,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    shipping_address TEXT,
    notes TEXT,
    created_at BIGINT,
    updated_at BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    INDEX idx_user (user_id),
    INDEX idx_status (status)
);

-- Table: order_items
CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DOUBLE NOT NULL,
    total_price DOUBLE NOT NULL,
    created_at BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    INDEX idx_order (order_id)
);

-- Table: cart_items
CREATE TABLE IF NOT EXISTS cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DOUBLE NOT NULL,
    created_at BIGINT,
    updated_at BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    UNIQUE KEY unique_user_product (user_id, product_id),
    INDEX idx_user (user_id)
);

-- Insert Sample Data: Categories
INSERT INTO categories (name, description, status, created_at) VALUES
('Điện Thoại', 'Điện thoại thông minh các thương hiệu', 1, UNIX_TIMESTAMP() * 1000),
('Laptop', 'Laptop và máy tính xách tay', 1, UNIX_TIMESTAMP() * 1000),
('Tablet', 'Máy tính bảng các loại', 1, UNIX_TIMESTAMP() * 1000),
('Phụ Kiện', 'Các phụ kiện điện tử', 1, UNIX_TIMESTAMP() * 1000),
('Âm Thanh', 'Tai nghe, loa, micro', 1, UNIX_TIMESTAMP() * 1000);

-- Insert Sample Data: Products
INSERT INTO products (name, description, price, quantity, category_id, image_url, status, created_at, updated_at) VALUES
('iPhone 15 Pro', 'Apple iPhone 15 Pro với chip A17 Pro', 25000000, 50, 1, 'https://via.placeholder.com/300?text=iPhone+15', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('Samsung Galaxy S24', 'Samsung Galaxy S24 Ultra cao cấp', 22000000, 40, 1, 'https://via.placeholder.com/300?text=Galaxy+S24', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('MacBook Pro 16"', 'Apple MacBook Pro M3 Max 16 inch', 55000000, 20, 2, 'https://via.placeholder.com/300?text=MacBook+Pro', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('Dell XPS 15', 'Dell XPS 15 với Intel Core i7', 35000000, 15, 2, 'https://via.placeholder.com/300?text=Dell+XPS', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('iPad Air', 'Apple iPad Air với chip M1', 18000000, 30, 3, 'https://via.placeholder.com/300?text=iPad+Air', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('Sony WH-1000XM5', 'Tai nghe Sony chống ồn', 8000000, 60, 5, 'https://via.placeholder.com/300?text=Sony+WH', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('Apple AirPods Pro', 'Tai nghe AirPods Pro thế hệ 2', 5500000, 80, 5, 'https://via.placeholder.com/300?text=AirPods', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('Ốp lưng iPhone', 'Ốp lưng bảo vệ cho iPhone', 500000, 200, 4, 'https://via.placeholder.com/300?text=Op+Lung', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('Cáp sạc nhanh', 'Cáp sạc USB-C 65W', 350000, 150, 4, 'https://via.placeholder.com/300?text=Cap+Sac', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('Chuột không dây', 'Chuột Logitech MX Master 3S', 2500000, 45, 4, 'https://via.placeholder.com/300?text=Chuot', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- Insert Sample Data: Users (Admin & Customer)
INSERT INTO users (username, password, email, full_name, phone, address, role, status, created_at, updated_at) VALUES
('admin', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy990dm', 'admin@shopnow.com', 'Admin User', '0987654321', '123 Đường Admin, Hà Nội', 'ADMIN', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('customer1', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy990dm', 'customer1@email.com', 'Nguyễn Văn A', '0912345678', '456 Đường Customer, HCM', 'CUSTOMER', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('customer2', '$2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy990dm', 'customer2@email.com', 'Trần Thị B', '0923456789', '789 Đường Khách, Đà Nẵng', 'CUSTOMER', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- Insert Sample Orders
INSERT INTO orders (user_id, total_amount, status, shipping_address, notes, created_at, updated_at) VALUES
(2, 30500000, 'PENDING', '456 Đường Customer, HCM', 'Giao hàng vào chiều', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
(3, 5500000, 'CONFIRMED', '789 Đường Khách, Đà Nẵng', 'Giao nhanh', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- Insert Order Items
INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_price, created_at) VALUES
(1, 1, 1, 25000000, 25000000, UNIX_TIMESTAMP() * 1000),
(1, 7, 1, 5500000, 5500000, UNIX_TIMESTAMP() * 1000),
(2, 7, 1, 5500000, 5500000, UNIX_TIMESTAMP() * 1000);

-- Ghi chú: Mật khẩu mặc định cho admin và customer là: password123
-- Mã hóa BCrypt của "password123" là: $2a$10$slYQmyNdGzin7olVN3p5Be7DlH.PKZbv5H8KnzzVgXXbVxzy990dm
