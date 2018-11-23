package webec

class GridController {

    PosterService posterService

    def index() {
        respond posterService.list(), model:[posterCount: posterService.count()]
    }
}
