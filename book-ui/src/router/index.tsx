import Book from "@/pages/Book";
import Home from "@/pages/home";
import Login from "@/pages/login";
import { Welcome } from "@/pages/welcome";
import { createBrowserRouter } from "react-router-dom";


const router = createBrowserRouter([
    {
        path:"/",
        element:<Home/>
    },
    {
        path:"/welcome",
        element:<Welcome/>
    },
    {
        path:"/book",
        element:<Book/>
    },
    {
        path:"/login",
        element:<Login/>
    },
])

export {router}