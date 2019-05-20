package dictionary

/**
 * Created by Darion Higgins on 5/20/2019
 * TSO2438
 */
class Index {
    String key
    List<String> words = []
    Map<String, Index> inner = [:]

    Index(String key) {
        this.key = key
    }

    void addValue(String val) {
        if (val == key) {
            words.add(val)
        } else {
            String sub = val.substring(0, key.length() + 1)
            if (!inner.containsKey(sub)) {
                inner.put(sub, new Index(sub))
            }

            inner.get(sub).addValue(val)
        }
    }

    List<String> autocomplete(String s){
        List<String> values = []
        if(key.startsWith(s)) {
            values.addAll(words)

            inner.values().each {
                values.addAll(it.autocomplete(s))
            }
        } else if(s.startsWith(key)){
            inner.values().each {
                values.addAll(it.autocomplete(s))
            }
        }

        return values
    }

    List<String> guess(String val){
        // * is the wildcard character

    }
}
