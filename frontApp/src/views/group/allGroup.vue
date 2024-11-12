<script setup>
import {readAllGroupService} from '@/api/group.js'
import { userfindByIdService } from '@/api/user';
import { ref } from 'vue'
const groupData = ref([{
    id:'',
    name:'',
    message:'',
    owner:''
}])
const readAllGroup = async () => {
    let result = await readAllGroupService();
    groupData.value = result.data;
}
readAllGroup();
</script>

<template>
    <el-table :data="groupData" style="width: 100%">
        <el-table-column prop="id" label="ID" width="180" />
        <el-table-column prop="name" label="名称" width="180" />
        <el-table-column prop="message" label="描述" width="480"/>
        <el-table-column prop="owner" label="群主" width="180"/>
        <el-table-column label="操作">
            <el-buttom></el-buttom>
        </el-table-column>
    </el-table>
</template>