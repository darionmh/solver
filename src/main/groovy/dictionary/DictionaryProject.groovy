package dictionary
/**
 * Created by Darion Higgins on 5/20/2019
 * TSO2438
 */
class DictionaryProject {
    public static void main(String[] args) {
        Dictionary dict =  new Dictionary()
        Scanner input = new Scanner(System.in)
        String val = ""
        while(val != "quit"){
            val = input.nextLine()
            println dict.autoComplete(val, 100)
        }
    }
}
