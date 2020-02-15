(ns com.model.Checking
  "Abstraction for checking for correctness in words and letters"
  (:require [com.model.Drawing :as letters]
            [com.model.Converting :as conversation]
            [com.model.Dictionary :as dictionary]
            [clojure.data :as d]))

;;;;;;;;;;;;;;;Letter Controller;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:atom correctinsertedletters (atom []))

(def ^:atom wronginsertedletters (atom []))


(defn isEmptyWrongInsertedWord
  "Return boolean if wrong letters are passed as input by user."
  []
  (empty? @wronginsertedletters)
  )

(defn makeWrongInsertedWord
  "Making word from wrong inserted letters."
  []
  (apply str @wronginsertedletters)
  )

(defn getInsertedWord
  "Inserted word Getter."
  []
  (letters/makeWrongInsertedWord)
  )

(defn fillInputWord
  "Function to make vector of word letters from inserted letters."
  [word]
  (letters/fillSeqWithInsertedWord word)
  )

(defn makeWrongInsertedWord
  "Making concatenated word of wrong inserted letters. It is used for message view."
  []
  (apply str (interpose  ", " @wronginsertedletters))
  )

(defn checkWord
  "Checking for correctness of the letters entries entered by player."
  []
  (reset! correctinsertedletters [])
  (reset! wronginsertedletters [])
  (let [input (letters/getInsertedLetters)
        inputmap (frequencies (letters/getInsertedLetters))
        exitmap (frequencies (letters/getDrawnLetters))
        difference (nth (d/diff inputmap exitmap) 0)]
    (if (nil? difference)
        (swap!
         correctinsertedletters
         (fn [state]
           (into [] (flatten (conj @correctinsertedletters input)))
         ))
        (swap!
          wronginsertedletters
          (fn [state]
            (into [] (flatten (conj @wronginsertedletters (into [] (keys difference)))))
            ))
    )
  ))

;;;;;;;;;;;;;;;Word Controller;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:atom isFoundWordInDictionary (atom false))


(defn findWord
  "Checking for semantic correctness of the word entries entered by player with available dictionary
   and returning result as boolean."
  []
  (let [word (conversation/convertorSmallWord "dictionary" letters/makeWrongInsertedWord)]
    (if (nil? (some #(= word %) (dictionary/getDictionary)))
      (reset! isFoundWordInDictionary true)

      (reset! isFoundWordInDictionary false)
      )
    )
  )



