package dictionary

import utils.MapUtils

/**
 * Created by Darion Higgins on 5/20/2019
 * TSO2438
 */
class Index {
    String key = ""
    List<String> words = []
    Map<String, Index> inner = [:]

    Index() {
    }

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

    List<String> search(String s){
        List<String> results = []
        if(key.startsWith(s)) {
            results.addAll(words)

            inner.values().each {
                results.addAll(it.search(s))
            }
        } else if(s.startsWith(key)){
            inner.values().each {
                results.addAll(it.search(s))
            }
        }

        return results
    }

    final String wildcard = "_"
    List<String> searchWithWildcard(String val){
        // * is the wildcard character
        List<Integer> indexes = getIndexesOfString(val, wildcard)
        List<String> keyList = this.key.toList()
        indexes.each {
            if(it < keyList.size()) {
                keyList.remove(it)
                keyList.add(it, wildcard)
            }
        }
        String key = keyList.join("")
        List<String> values = []

        if(key == val) {
            values.addAll(words)

            inner.values().each {
                values.addAll(it.searchWithWildcard(val))
            }
        } else if(val.startsWith(key)){
            inner.values().each {
                values.addAll(it.searchWithWildcard(val))
            }
        }

        return values
    }

    List<Integer> getIndexesOfString(String str, String p){
        if(!str.contains(p)) return []

        List<Integer> indexes = []
        str.toList().eachWithIndex { String s, int i -> if(s == p) indexes.add(i) }

        return indexes
    }

    Map<String, Integer> getLetterSum(){
        Map<String, Integer> sums = [:]

        words.forEach ({
            it.toList().forEach({
                if(!sums.containsKey(it)){
                    sums.put(it, 1)
                } else {
                    sums.get(it)++
                }
            })
        })

        inner.values().forEach({
            sums = MapUtils.join(sums, it.getLetterSum())
        })

        return sums
    }
}
