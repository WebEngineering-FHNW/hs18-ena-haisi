package webec

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/admin"(view:"/admin")
        "/"(controller: "grid")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
