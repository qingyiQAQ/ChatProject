<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
const userData = ref({
    username: '',
    password: ''
})
const isLogin = ref(true);
//定义表单校验规则
const rules = {
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 5, max: 16, message: '长度为5-16位非空字符', trigger: 'blur' },
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 5, max: 16, message: '长度为5-16位非空字符', trigger: 'blur' },
    ]
}
//定义函数，清空数据模型的数据
const clearUserData = () => {
    userData.value = {
        username: '',
        password: ''
    }
}
//调用后台接口完成注册
import { userRegisterService, userLoginService } from '@/api/user.js';
const register = async () => {
    //registerData是一个响应式对象，如果要获取值，需要value
    let result = await userRegisterService(userData.value);
    ElMessage.success(result.message ? result.message : '注册成功');
}

//绑定数据，复用注册表单的数据模型
//表单数据校验
//登录函数
import { useTokenStore } from '@/stores/token.js'
import { useRouter } from 'vue-router'
const router = useRouter();
const tokenStore = useTokenStore();
const login = async () => {
    //调用接口，完成登录
    let result = await userLoginService(userData.value);
    ElMessage.success(result.message ? result.message : '登录成功');
    //把得到的token存储到pinia中
    tokenStore.setToken(result.data);
    //跳转到首页    借助路由完成跳转
    router.push('/');
}
</script>

<template>
    <el-card class="center" style="width: 480px;border-radius: 20px 20px 20px 20px;">
        <!--登录表单-->
        <el-form class="form" size="large" :rules="rules" :model="userData" v-if="isLogin">
            <el-form-item>
                <h1>登录</h1>
            </el-form-item>
            <el-form-item prop="username">
                <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="userData.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码"
                    v-model="userData.password"></el-input>
            </el-form-item>
            <el-form-item class="flex">
                <div class="flex">
                    <el-checkbox>记住我</el-checkbox>
                    <el-link type="primary" :underline="false">忘记密码？</el-link>
                </div>
            </el-form-item>
            <!-- 注册按钮 -->
            <el-form-item>
                <el-button class="button" type="primary" auto-insert-space @click="login">
                    登录
                </el-button>
            </el-form-item>
            <el-form-item class="flex">
                <el-link type="info" :underline="false" @click="isLogin = false; clearUserData();">
                    注册 →
                </el-link>
            </el-form-item>
        </el-form>
        <!-- 注册表单 -->
        <el-form class="form" size="large" v-else :model="userData" :rules="rules">
            <el-form-item>
                <h1>注册</h1>
            </el-form-item>
            <el-form-item prop="username">
                <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="userData.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="userData.password"></el-input>
            </el-form-item>
            <!-- 登录按钮 -->
            <el-form-item>
                <el-button class="button" type="primary" auto-insert-space @click="register">
                    注册
                </el-button>
            </el-form-item>
            <el-form-item class="flex">
                <el-link type="info" :underline="false" @click="isLogin = true; clearUserData();">
                    ← 返回
                </el-link>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<style lang="scss" scoped>
.form {
    align-items: center;

    .title {
        margin: 0 auto;
    }

    .button {
        width: 100%;
    }

    .flex {
        width: 100%;
        display: flex;
        justify-content: space-between;
    }
}

.center {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
</style>