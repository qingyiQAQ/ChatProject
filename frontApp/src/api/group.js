import request from '@/utils/request.js'

export const readAllGroupService = () =>{
    return request.get('/group/findAll');
}

export const joinGroupService = (id) =>{
    return request.post('/group/join?groupId='+id);
}

export const quitGroupService = (id) =>{
    return request.post('/group/quit?groupId='+id);
}