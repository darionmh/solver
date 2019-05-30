package dictionary

/**
 * Created by Darion Higgins on 5/30/2019
 * TSO2438
 */
class UnknownWord {
    private final int STRIKES = 6

    private String secretWord
    private String currentGuess
    private List<String> guessedLetters
    private int strikesLeft

    UnknownWord(String secretWord) {
        this.secretWord = secretWord
        guessedLetters = []
        currentGuess = maskSecretWord()
        strikesLeft = STRIKES
    }

    String maskSecretWord() {
        return secretWord.toList().stream().map({
            if (guessedLetters.contains(it)) return it
            else "_"
        }).collect().join("")
    }

    void guessLetter(String s) {
        if (guessedLetters.contains(s)) {
            println "You've already guessed that one."
        } else {
            guessedLetters.add(s)
            if (secretWord.contains(s)) {
                println "There is a(n) "+s+"!!"
                currentGuess = maskSecretWord()
            }else{
                println "Oof.. no "+s
                strikesLeft--
            }
        }
    }

    String getSecretWord() {
        return secretWord
    }

    void setSecretWord(String secretWord) {
        this.secretWord = secretWord
    }

    String getCurrentGuess() {
        return currentGuess
    }

    void setCurrentGuess(String currentGuess) {
        this.currentGuess = currentGuess
    }

    List<String> getGuessedLetters() {
        return guessedLetters
    }

    void setGuessedLetters(List<String> guessedLetters) {
        this.guessedLetters = guessedLetters
    }

    int getStrikesLeft() {
        return strikesLeft
    }

    void setStrikesLeft(int strikesLeft) {
        this.strikesLeft = strikesLeft
    }
}
