package dictionary

import utils.MapUtils

/**
 * Created by Darion Higgins on 5/20/2019
 * TSO2438
 */
class Dictionary {
    Map<String, Index> words

    Dictionary() {
        words = indexWords()
    }

    List<String> autoComplete(String s, int count){
        List<String> suggestions = []
        words.values().each {
            suggestions.addAll(it.autocomplete(s))
        }

        return suggestions.subList(0, Math.min(suggestions.size(), count))
    }

    List<String> guess(String s, int count){
        List<String> suggestions = []
        words.values().each {
            suggestions.addAll(it.guess(s))
        }

        return suggestions.subList(0, Math.min(suggestions.size(), count))
    }

    private Map indexWords(){
        Map<String, Index> index = [:]

        List<String> words = loadWords()
        words.each { word ->
            String key = word.substring(0,1).toLowerCase()
            if(!index.containsKey(key)){
                index.put(key, new Index(key))
            }

            index.get(key).addValue(word.toLowerCase())
        }

        return index
    }

    private List<String> loadWords(){
        return getClass().getResource('/words.txt').readLines()
    }

    Map<String, Integer> getLetterSums(){
        Map<String, Integer> sums = [:]

        words.values().forEach({
            sums = MapUtils.join(sums, it.getLetterSum())
        })

        return sums
    }
}
