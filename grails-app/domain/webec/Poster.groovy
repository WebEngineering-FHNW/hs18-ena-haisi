package webec

class Poster {

    String name
    String description
    byte[] featuredImageBytes
    String featuredImageContentType

    static belongsTo = [artist: Artist]

    static hasMany = [tags: Tag]

    static constraints = {
        description nullable: true
        featuredImageBytes nullable: true
        featuredImageContentType nullable: true

        // Limit upload file size to 25MB
        featuredImageBytes maxSize: 1024 * 1024 * 25
    }

    static mapping = {
        featuredImageBytes column: 'featured_image_bytes', sqlType: 'longblob'
        // Overhead is negligible
        tags lazy: false
    }

    String toString() {
        name
    }
}
