import { createRouter, createWebHistory } from 'vue-router'

//导入组件
import LoginVue from '@/views/login.vue'
import LayoutVue from '@/views/layout.vue'
import AllGroupVue from '@/views/group/allGroup.vue'
import ManageGroupVue from '@/views/group/manageGroup.vue'

//定义路由关系
const routes = [
    {
        path: '/login',
        component: LoginVue
    },
    {
        path: '/',
        redirect:'/group/all',
        component: LayoutVue,
        children: [
            {path:'/group/all',component:AllGroupVue},
            {path:'/group/manage',component:ManageGroupVue}
        ]
    }
]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes,
})

//导出路由
export default router