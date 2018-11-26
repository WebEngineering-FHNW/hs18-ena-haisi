package webec

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PosterServiceSpec extends Specification {

    PosterService posterService
    SessionFactory sessionFactory

    private Long setupData() {
        def brockmann = new Artist(name: "Josef Müller-Brockmann", description: "Foo").save(flush: true, failOnError: true)

        new Poster(name: "Poster 1", artist: brockmann).save(flush: true, failOnError: true)
        new Poster(name: "Poster 2", artist: brockmann).save(flush: true, failOnError: true)
        Poster poster = new Poster(name: "Poster 3", artist: brockmann).save(flush: true, failOnError: true)
        new Poster(name: "Poster 4", artist: brockmann).save(flush: true, failOnError: true)
        new Poster(name: "Poster 5", artist: brockmann).save(flush: true, failOnError: true)
        poster.id
    }

    void "test get"() {
        setupData()

        expect:
        posterService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Poster> posterList = posterService.list(max: 2, offset: 2)

        then:
        posterList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        posterService.count() == 5
    }

    void "test delete"() {
        Long posterId = setupData()

        expect:
        posterService.count() == 5

        when:
        posterService.delete(posterId)
        sessionFactory.currentSession.flush()

        then:
        posterService.count() == 4
    }

    void "test save"() {
        when:
        def artist = new Artist(name: "Artsy").save(flush: true, failOnError: true)
        Poster poster = new Poster(name: "Foo", artist: artist)
        posterService.save(poster)

        then:
        poster.id != null
    }
}
