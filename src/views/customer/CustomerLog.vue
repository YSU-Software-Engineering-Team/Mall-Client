<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>精品商城 - 用户登录</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', 'PingFang SC', 'Microsoft YaHei', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            color: #333;
        }

        .login-container {
            display: flex;
            width: 100%;
            max-width: 1000px;
            background: white;
            border-radius: 20px;
            overflow: hidden;
            box-shadow: 0 15px 50px rgba(0, 0, 0, 0.25);
            animation: fadeIn 0.8s ease-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .hero-section {
            flex: 1;
            background: linear-gradient(rgba(106, 17, 203, 0.85), rgba(37, 117, 252, 0.9)),
                        url('https://images.unsplash.com/photo-1607082350899-7e105aa886ae?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=800&q=80') center/cover;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            color: white;
            position: relative;
        }

        .hero-content {
            position: relative;
            z-index: 2;
        }

        .logo {
            font-size: 28px;
            font-weight: 700;
            margin-bottom: 40px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .logo i {
            background: white;
            color: #6a11cb;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .hero-section h1 {
            font-size: 2.8rem;
            margin-bottom: 15px;
            line-height: 1.2;
        }

        .hero-section p {
            font-size: 1.1rem;
            opacity: 0.9;
            margin-bottom: 30px;
            max-width: 90%;
        }

        .features {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            margin-top: 30px;
        }

        .feature {
            display: flex;
            align-items: center;
            gap: 10px;
            width: 45%;
        }

        .feature i {
            background: rgba(255, 255, 255, 0.2);
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 18px;
        }

        .login-section {
            flex: 1;
            padding: 50px 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .login-header {
            margin-bottom: 40px;
            text-align: center;
        }

        .login-header h2 {
            font-size: 2.2rem;
            color: #2c3e50;
            margin-bottom: 10px;
        }

        .login-header p {
            color: #7f8c8d;
            font-size: 1.1rem;
        }

        .login-form {
            width: 100%;
        }

        .form-group {
            margin-bottom: 25px;
            position: relative;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #2c3e50;
        }

        .input-with-icon {
            position: relative;
        }

        .input-with-icon i {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #7f8c8d;
            font-size: 18px;
        }

        .form-control {
            width: 100%;
            padding: 15px 15px 15px 50px;
            border: 2px solid #e0e0e0;
            border-radius: 12px;
            font-size: 16px;
            transition: all 0.3s;
        }

        .form-control:focus {
            border-color: #2575fc;
            box-shadow: 0 0 0 3px rgba(37, 117, 252, 0.2);
            outline: none;
        }

        .options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .remember {
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .remember input {
            width: 18px;
            height: 18px;
            accent-color: #2575fc;
        }

        .forgot-password {
            color: #2575fc;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.2s;
        }

        .forgot-password:hover {
            text-decoration: underline;
        }

        .btn-login {
            width: 100%;
            padding: 16px;
            background: linear-gradient(to right, #6a11cb, #2575fc);
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 18px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            box-shadow: 0 4px 15px rgba(106, 17, 203, 0.3);
        }

        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(106, 17, 203, 0.4);
        }

        .divider {
            display: flex;
            align-items: center;
            margin: 30px 0;
            color: #7f8c8d;
        }

        .divider::before,
        .divider::after {
            content: "";
            flex: 1;
            height: 1px;
            background: #e0e0e0;
        }

        .divider span {
            padding: 0 15px;
        }

        .social-login {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-bottom: 30px;
        }

        .social-btn {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            color: white;
            cursor: pointer;
            transition: all 0.3s;
        }

        .social-btn.google {
            background: linear-gradient(to right, #ff4b2b, #ff416c);
        }

        .social-btn.facebook {
            background: linear-gradient(to right, #3b5998, #4867aa);
        }

        .social-btn.apple {
            background: linear-gradient(to right, #000000, #434343);
        }

        .social-btn:hover {
            transform: translateY(-5px);
        }

        .signup-link {
            text-align: center;
            color: #7f8c8d;
        }

        .signup-link a {
            color: #2575fc;
            text-decoration: none;
            font-weight: 600;
            margin-left: 5px;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .login-container {
                flex-direction: column;
                max-width: 500px;
            }

            .hero-section {
                padding: 30px 20px;
            }

            .hero-section h1 {
                font-size: 2rem;
            }

            .feature {
                width: 100%;
            }
        }

        .floating-element {
            position: absolute;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
            backdrop-filter: blur(5px);
            z-index: 1;
        }

        .floating-1 {
            width: 120px;
            height: 120px;
            top: 20%;
            left: 10%;
            animation: float 8s infinite ease-in-out;
        }

        .floating-2 {
            width: 80px;
            height: 80px;
            bottom: 30%;
            right: 15%;
            animation: float 10s infinite ease-in-out;
        }

        .floating-3 {
            width: 60px;
            height: 60px;
            top: 60%;
            left: 25%;
            animation: float 12s infinite ease-in-out;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0) translateX(0); }
            25% { transform: translateY(-20px) translateX(15px); }
            50% { transform: translateY(10px) translateX(-20px); }
            75% { transform: translateY(-15px) translateX(-10px); }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <!-- 左侧展示区域 -->
        <div class="hero-section">
            <div class="floating-element floating-1"></div>
            <div class="floating-element floating-2"></div>
            <div class="floating-element floating-3"></div>

            <div class="hero-content">
                <div class="logo">
                    <i class="fas fa-shopping-bag"></i>
                    <span>精品商城</span>
                </div>
                <h1>欢迎回到精品商城</h1>
                <p>登录您的账户，探索全球精选商品，享受专属会员优惠和个性化购物体验。</p>

                <div class="features">
                    <div class="feature">
                        <i class="fas fa-shipping-fast"></i>
                        <span>全球快速配送</span>
                    </div>
                    <div class="feature">
                        <i class="fas fa-shield-alt"></i>
                        <span>安全支付保障</span>
                    </div>
                    <div class="feature">
                        <i class="fas fa-gift"></i>
                        <span>会员专属优惠</span>
                    </div>
                    <div class="feature">
                        <i class="fas fa-headset"></i>
                        <span>24小时客服支持</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- 右侧登录区域 -->
        <div class="login-section">
            <div class="login-header">
                <h2>用户登录</h2>
                <p>请输入您的账号信息</p>
            </div>

            <form class="login-form">
                <div class="form-group">
                    <label for="username">用户名/邮箱</label>
                    <div class="input-with-icon">
                        <i class="fas fa-user"></i>
                        <input type="text" id="username" class="form-control" placeholder="请输入用户名或邮箱">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password">密码</label>
                    <div class="input-with-icon">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="password" class="form-control" placeholder="请输入密码">
                    </div>
                </div>

                <div class="options">
                    <div class="remember">
                        <input type="checkbox" id="remember">
                        <label for="remember">记住我</label>
                    </div>
                    <a href="#" class="forgot-password">忘记密码?</a>
                </div>

                <button type="submit" class="btn-login">登 录</button>

                <div class="divider">
                    <span>或使用其他方式登录</span>
                </div>

                <div class="social-login">
                    <div class="social-btn google">
                        <i class="fab fa-google"></i>
                    </div>
                    <div class="social-btn facebook">
                        <i class="fab fa-facebook-f"></i>
                    </div>
                    <div class="social-btn apple">
                        <i class="fab fa-apple"></i>
                    </div>
                </div>

                <div class="signup-link">
                    还没有账号? <a href="#">立即注册</a>
                </div>
            </form>
        </div>
    </div>

    <script>
        // 添加简单的表单验证和交互效果
        document.addEventListener('DOMContentLoaded', function() {
            const loginForm = document.querySelector('.login-form');

            loginForm.addEventListener('submit', function(e) {
                e.preventDefault();

                const username = document.getElementById('username').value;
                const password = document.getElementById('password').value;

                if(!username || !password) {
                    alert('请输入用户名和密码');
                    return;
                }

                // 模拟登录成功
                const loginBtn = document.querySelector('.btn-login');
                loginBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> 登录中...';
                loginBtn.disabled = true;

                setTimeout(() => {
                    alert('登录成功！即将跳转到商城首页');
                    loginBtn.innerHTML = '登 录';
                    loginBtn.disabled = false;
                }, 1500);
            });

            // 添加输入框聚焦效果
            const inputs = document.querySelectorAll('.form-control');
            inputs.forEach(input => {
                input.addEventListener('focus', function() {
                    this.parentElement.parentElement.style.transform = 'translateY(-3px)';
                });

                input.addEventListener('blur', function() {
                    this.parentElement.parentElement.style.transform = 'translateY(0)';
                });
            });
        });
    </script>
</body>
</html>
