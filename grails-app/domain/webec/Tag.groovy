package webec

class Tag {

    String name

    static constraints = {
        name minSize: 1
        name maxSize: 255
    }

    String toString() {
        name
    }
}
