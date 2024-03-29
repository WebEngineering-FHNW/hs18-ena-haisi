package webec

class Artist {

    String name
    String description

    static hasMany = [posters: Poster]

    static constraints = {
        name maxSize: 255
        name minSize: 1
        description nullable: true
        description maxSize: 1024
    }

    String toString() {
        name
    }
}
