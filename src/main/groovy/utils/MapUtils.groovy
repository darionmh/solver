package utils

/**
 * Created by Darion Higgins on 5/23/2019
 * TSO2438
 */
class MapUtils {
    static Map<String, Integer> join(Map<String, Integer> left, Map<String, Integer> right){
        Map<String, Integer> joined = left.clone() as Map<String, Integer>
        right.forEach({ String key, Integer sum ->
            if(!joined.containsKey(key)){
                joined.put(key, sum)
            }else{
                joined.put(key, left.get(key) + sum)
            }
        })

        return joined
    }
}
