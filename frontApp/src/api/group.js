import request from '@/utils/request.js'

export const readAllGroupService = () =>{
    return request.get('/group/findAll');
}