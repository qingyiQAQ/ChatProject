<script setup>
import { Document, Menu as IconMenu, Location, Setting } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
const router = useRouter();
</script>

<template>
    <el-row>
        <el-col :span="4">
            <el-menu 
            active-text-color="#ffd04b" 
            background-color="#545c64"
            default-active="/group/all"
            text-color="#fff"
            style="height: 100vh;"
            router>
                <el-menu-item index="/group/all">
                    <el-icon><icon-menu /></el-icon>
                    <span>加入群组</span>
                </el-menu-item>
                <el-menu-item index="/group/manage">
                    <el-icon>
                        <document />
                    </el-icon>
                    <span>管理群组</span>
                </el-menu-item>
                <el-menu-item index="/">
                    <el-icon><icon-menu /></el-icon>
                    <span>开始聊天</span>
                </el-menu-item>
                <el-menu-item index="/user">
                    <el-icon>
                        <setting />
                    </el-icon>
                    <span>个人信息</span>
                </el-menu-item>
            </el-menu>
        </el-col>
        <el-col :span="20">
            <el-card>
                <router-view></router-view>
            </el-card>
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100%;
}
</style>