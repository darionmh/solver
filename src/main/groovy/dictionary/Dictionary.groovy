package dictionary

/**
 * Created by Darion Higgins on 5/30/2019
 * TSO2438
 */
class Dictionary extends Index{

    Dictionary() {
        init()
    }

    private void init() {
        List<String> words = loadWords()
        words.each { word ->
            String key = word.substring(0, 1).toLowerCase()
            if (!inner.containsKey(key)) {
                inner.put(key, new Index(key))
            }

            inner.get(key).addValue(word.toLowerCase())
        }
    }

    private List<String> loadWords() {
        return getClass().getResource('/words.txt').readLines()
    }
}
