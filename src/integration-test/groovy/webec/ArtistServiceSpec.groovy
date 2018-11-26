package webec

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ArtistServiceSpec extends Specification {

    ArtistService artistService
    SessionFactory sessionFactory

    private Long setupData() {
        new Artist(name: "Artist 1", description: "Desc 1").save(flush: true, failOnError: true)
        new Artist(name: "Artist 2", description: "Desc 2").save(flush: true, failOnError: true)
        Artist artist = new Artist(name: "Artist 3", description: "Desc 3").save(flush: true, failOnError: true)
        new Artist(name: "Artist 4", description: "Desc 4").save(flush: true, failOnError: true)
        new Artist(name: "Artist 5", description: "Desc 5").save(flush: true, failOnError: true)
        artist.id
    }

    void "test get"() {
        setupData()

        expect:
        artistService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Artist> artistList = artistService.list(max: 2, offset: 2)

        then:
        artistList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        artistService.count() == 5
    }

    void "test delete"() {
        Long artistId = setupData()

        expect:
        artistService.count() == 5

        when:
        artistService.delete(artistId)
        sessionFactory.currentSession.flush()

        then:
        artistService.count() == 4
    }

    void "test save"() {
        when:
        Artist artist = new Artist(name: "foobar", description: "desc")
        artistService.save(artist)

        then:
        artist.id != null
    }
}
