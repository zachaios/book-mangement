import { http } from "@/utils";
import { type ResType } from "./shared";

export type BookDataType = {
    key: string|null
    bookId: string|null
    bookName: string
    bookDesc: string
    authorName: string
    publishTime: Date

}

export function allBookOldApi(){
    return http.request<ResType<BookDataType>>({
        url:`/findAll`
    })
}

export function allBookApi(){
    return http.request<BookDataType[]>({
        url:`/findAll`
    })
}

export function featchBookByIdApi(id:string){
    return http.request<BookDataType>({
        url:`/findById/${id}`
    })
}

export function saveAndUpdateBookApi(book:BookDataType){
    return http.request<BookDataType>({
        url:`/save`,
        data:book,
        method:'post'
    })
}

export function deleteBookApi(id:string){
    return http.request<BookDataType>({
        url:`/delete/${id}`,
        method:'delete'
    })
}
