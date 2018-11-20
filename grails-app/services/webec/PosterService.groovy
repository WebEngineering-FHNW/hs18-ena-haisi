package webec

import grails.gorm.services.Service

@Service(Poster)
interface PosterService {

    Poster get(Serializable id)

    List<Poster> list(Map args)

    Long count()

    void delete(Serializable id)

    Poster save(Poster poster)

}