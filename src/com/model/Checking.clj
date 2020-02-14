(ns com.model.Checking
  (:require [com.model.Drawing :as letters]
            [com.model.Converting :as conversation]
            [com.model.Dictionary :as dictionary]
            [clojure.data :as d]))

;;;;;;;;;;;;;;;Letter Controller;;;;;;;;;;;;;;;;;;;;;;;;;
(def correctinsertedletters (atom []))

(def wronginsertedletters (atom []))


(defn isEmptyWrongInsertedWord []
  (empty? @wronginsertedletters)
  )

(defn makeWrongInsertedWord []
  (apply str @wronginsertedletters)
  )

(defn getInsertedWord []
  (letters/makeWrongInsertedWord)
  )

(defn fillInputWord [word]
  (letters/fillSeqWithInsertedWord word)
  )

(defn makeWrongInsertedWord []
  (apply str (interpose  ", " @wronginsertedletters))
  )

(defn checkWord []
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
(def isFoundWordInDictionary (atom false))


(defn findWord []
  (let [word (conversation/convertorSmallWord "dictionary" letters/makeWrongInsertedWord)]
    (if (nil? (some #(= word %) (dictionary/getDictionary)))
      (reset! isFoundWordInDictionary true)

      (reset! isFoundWordInDictionary false)
      )
    )
  )



