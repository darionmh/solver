package dictionary
/**
 * Created by Darion Higgins on 5/20/2019
 * TSO2438
 */
class DictionaryProject {
    static void main(String[] args) {
        Greg greg = new Greg()

        println greg.autoComplete("appl")
        println greg.retrieveSuggestion("a__le")

        println "Done"
    }
}
