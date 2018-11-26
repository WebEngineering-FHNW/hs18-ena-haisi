package webec

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

/**
 *
 * @author Hasan Kara
 */
@SuppressWarnings('MethodName')
@Integration
class DefaultHomePageSpec extends GebSpec {

    PosterService posterService

    def 'verifies there is same number of posters as in DB'() {
        when:
        go '/'

        then:

        $('.poster').size() == posterService.count().toInteger()

    }
}
