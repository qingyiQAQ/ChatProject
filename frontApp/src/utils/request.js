//定制请求示例

//导入axios npm install axios
import axios from 'axios';
//定义一个变量，记录公共的前缀
//const baseURL = 'http://localhost:8080';
import { ElMessage } from 'element-plus'
const baseURL = '/api';
const instance = axios.create({baseURL});

import { useTokenStore } from '@/stores/token.js';

//添加请求拦截器
instance.interceptors.request.use(
    (config)=>{
        //请求前的回调
        //添加token
        const tokenStore = useTokenStore();
        //判断有没有token
        if(tokenStore.token){
            config.headers.Authorization = tokenStore.token
        }
        return config;
    },
    (err)=>{
        //请求错误的回调
        Promise.reject(err);
    }
)

// import { useRouter } from 'vue-router';
// const router = useRouter();
import router from '@/router'
//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        //判断业务状态码
        if(result.data.code===0){
            return result.data;
        }
        
        //操作失败
        ElMessage.error(result.data.message ? result.data.message : '服务异常');

        //异步操作的状态转化为失败
        return Promise.reject(result.data);
    },
    err=>{
        //判断响应状态码，如果为401，则证明未登录，提示请先登录，并跳转到登录界面
        if(err.response.status===401){
            ElMessage.error('请先登录');
            router.push('/login');
        }else{
        ElMessage.error('服务异常');
        return Promise.reject(err);//异步状态转化为失败的状态
        }
    }
)

export default instance;