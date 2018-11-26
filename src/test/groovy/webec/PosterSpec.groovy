package webec

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PosterSpec extends Specification implements DomainUnitTest<Poster> {

    def setup() {
    }

    def cleanup() {
    }

    void "test valid image data type"() {
        when:
        domain.featuredImageContentType = "exe"

        then:
        !domain.validate(['featuredImageContentType'])
        domain.errors['featuredImageContentType'].code == 'image content type is not supported!'
    }
}
