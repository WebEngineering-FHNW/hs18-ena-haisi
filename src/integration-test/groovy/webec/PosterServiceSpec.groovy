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
        // TODO: Populate valid domain instances and return a valid ID
        //new Poster(...).save(flush: true, failOnError: true)
        //new Poster(...).save(flush: true, failOnError: true)
        //Poster poster = new Poster(...).save(flush: true, failOnError: true)
        //new Poster(...).save(flush: true, failOnError: true)
        //new Poster(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //poster.id
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
        assert false, "TODO: Verify the correct instances are returned"
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
        assert false, "TODO: Provide a valid instance to save"
        Poster poster = new Poster()
        posterService.save(poster)

        then:
        poster.id != null
    }
}
