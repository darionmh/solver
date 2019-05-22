package dictionary
/**
 * Created by Darion Higgins on 5/20/2019
 * TSO2438
 */
class DictionaryProject {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary()
        println dict.autoComplete("appl", 100)
        println dict.guess("c_m_u_e_", 100)
    }


    static List<Integer> getIndexesOfString(String str, String p) {
        if (!str.contains(p)) return []

        List<Integer> indexes = []
        str.chars.eachWithIndex { char entry, int i -> if (entry.toString() == p) indexes.add(i) }

        return indexes

    }
}
