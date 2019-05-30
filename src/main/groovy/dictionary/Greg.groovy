package dictionary

/**
 * Created by Darion Higgins on 5/30/2019
 * TSO2438
 */
class Greg {
    Dictionary dictionary


    Greg() {
        println "Hi I'm Greg!!"
        dictionary = new Dictionary()
    }

    String autoComplete(String s) {
        return say(dictionary.search(s))
    }

    String retrieveSuggestion(String s){
        return say(dictionary.searchWithWildcard(s))
    }

    private final String preface = "Greg says '%s'."

    private String say(String msg){
        return String.format(preface, msg)
    }

    private String say(List msg){
        return String.format(preface, msg.toListString())
    }
}
