import axios from "axios";

const httpInstance = axios.create({
    baseURL: "http://localhost:8000/",
    timeout: 5000
})

httpInstance.interceptors.request.use(
    (config) =>{
        return config
    },
    (error) =>{
        {error}
        return Promise.reject(error)
    }
)

httpInstance.interceptors.response.use(
    (Response) =>{
        return Response.data
    },
    (error) =>{
        return Promise.reject(error)
    }
)

export {httpInstance}