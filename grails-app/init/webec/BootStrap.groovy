package webec

import java.nio.file.Files

class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        def brockmann = new Artist(name: "Josef Müller-Brockmann", description: "Josef Müller-Brockmann war ein Schweizer Grafikdesigner, Typograf, Autor und Lehrer. Müller-Brockmann war führender Theoretiker und Praktiker der Schweizer Typografie.").save()
        def hamburg = new Artist(name: "Jörg Hamburg", description: "Der Zürcher Grafiker, Jahrgang 1935, schliesst seine Ausbildung in der Fachklasse der Allgemeinen Gewerbeschule in Basel bei Armin Hofmann, Emil Ruder und Donald Brun 1954 ab. Anschliessend arbeitet er bis 1958 im Werbeatelier der J.R. Geigy AG, Basel, später im Werbeatelier Josef Müller-Brockmann in Zürich (1958–1961). Ihm assistiert er ab 1958 als Leiter der Fachklasse für Grafik an der KGSZ. Seine dortige Lehrtätigkeit beginnt er mit Schriftunterricht im Vorkurs und später als Dozent für Fotografik, Typografie und Layout an der Fachklasse für Fotografie. 1961 gründet er zusammen mit Alfred Aebersold und Herbert Merz die Gruppe 3, ein Atelier für Gestaltung. Zu den herausragenden Arbeiten von Jörg Hamburger gehören die Gestaltung und Bewerbung der Ausstellung «Modellfall Citroën» im KGMZ und zahlreiche vom EDI ausgezeichnete Plakate für Ausstellungen und den Jugendsport").save()

        def kunstTag = new Tag(name: "Kunst").save()
        def politikTag = new Tag(name: "Politik").save()

        def uberholenPoster = new Poster(name: "Überholen? Im Zweifel nie!", artist: brockmann, tags: [politikTag]).save()
        setImageFromResources(uberholenPoster, "ueberholen.jpg")

        def opernhausPoster = new Poster(name: "Opernhaus Zürich", artist: brockmann, tags: [kunstTag]).save()
        setImageFromResources(opernhausPoster, "opernhaus_zurich.jpg")

    }

    def setImageFromResources(Poster poster, String fileName) {
        def resource = this.class.classLoader.getResource("data/${fileName}")
        File file = new File(resource.file)
        byte[] fileContent = Files.readAllBytes(file.toPath())
        poster.featuredImageBytes = fileContent
        poster.featuredImageContentType = file.name.take(file.name.lastIndexOf('.'))
    }

    def destroy = {
    }
}
