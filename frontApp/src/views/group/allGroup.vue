<script setup>
import { readAllGroupService,joinGroupService,quitGroupService } from '@/api/group.js'
import { ElMessage } from 'element-plus'
import { ref } from 'vue'
const groupData = ref([{
    id: '',
    name: '',
    message: '',
    memberNum: '',
    ownerId: '',
    ownerName: '',
    isJoined: ''
}])
const readAllGroup = async () => {
    let result = await readAllGroupService();
    groupData.value = result.data;
}
readAllGroup();
const joinGroup = async (row) => {
    let result = await joinGroupService(row.id);
    ElMessage.success('加入成功');
    readAllGroup();
}
const quitGroup = async (row) => {
    let result = await quitGroupService(row.id);
    ElMessage.success('退出成功');
    readAllGroup();
}
</script>

<template>
    <el-table :data="groupData" style="width: 100%">
        <el-table-column prop="id" label="ID" width="180" />
        <el-table-column prop="name" label="名称" width="180" />
        <el-table-column prop="message" label="描述" width="480" />
        <el-table-column prop="memberNum" label="成员数量" width="120" />
        <el-table-column prop="ownerName" label="群主" width="120" />
        <el-table-column label="操作">
            <template #default="{ row }">
                <el-button type="primary" v-if="!row.isJoined" @click="joinGroup(row)">
                    加入
                </el-button>
                <el-button type="danger" v-else @click="quitGroup(row)">
                    退出
                </el-button>
            </template>
        </el-table-column>
    </el-table>
</template>