package webec

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TagServiceSpec extends Specification {

    TagService tagService
    SessionFactory sessionFactory

    private Long setupData() {
        new Tag(name: "tag1").save(flush: true, failOnError: true)
        new Tag(name: "tag2").save(flush: true, failOnError: true)
        Tag tag = new Tag(name:  "tag2").save(flush: true, failOnError: true)
        new Tag(name: "tag4").save(flush: true, failOnError: true)
        new Tag(name: "tag5").save(flush: true, failOnError: true)
        tag.id
    }

    void "test get"() {
        setupData()

        expect:
        tagService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Tag> tagList = tagService.list(max: 2, offset: 2)

        then:
        tagList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        tagService.count() == 5
    }

    void "test delete"() {
        Long tagId = setupData()

        expect:
        tagService.count() == 5

        when:
        tagService.delete(tagId)
        sessionFactory.currentSession.flush()

        then:
        tagService.count() == 4
    }

    void "test save"() {
        when:
        Tag tag = new Tag(name: "Foo")
        tagService.save(tag)

        then:
        tag.id != null
    }
}
