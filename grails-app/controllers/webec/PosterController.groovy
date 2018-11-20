package webec

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PosterController {

    PosterService posterService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def featuredImage(Long id) {
        Poster poster = posterService.get(id)
        if (!poster || poster.featuredImageBytes == null) {
            notFound()
            return
        }
        render file: poster.featuredImageBytes,
                contentType: poster.featuredImageContentType
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond posterService.list(params), model:[posterCount: posterService.count()]
    }

    def show(Long id) {
        respond posterService.get(id)
    }

    def create() {
        respond new Poster(params)
    }

    def save(Poster poster) {
        if (poster == null) {
            notFound()
            return
        }

        try {
            posterService.save(poster)
        } catch (ValidationException e) {
            respond poster.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'poster.label', default: 'Poster'), poster.id])
                redirect poster
            }
            '*' { respond poster, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond posterService.get(id)
    }

    def update(Poster poster) {
        if (poster == null) {
            notFound()
            return
        }

        try {
            posterService.save(poster)
        } catch (ValidationException e) {
            respond poster.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'poster.label', default: 'Poster'), poster.id])
                redirect poster
            }
            '*'{ respond poster, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        posterService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'poster.label', default: 'Poster'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'poster.label', default: 'Poster'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
