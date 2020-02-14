(ns com.model.Dictionary
  (:require [com.data.Repository :as dictionary]))


;;;;;;;;;;;;;;;Word;;;;;;;;;;;;;;;;;;;;;;;;;
(defn getLength [word]
  (count word)
  )

(defn makeWordMap [word]
  (frequencies word)
  )

(defn initializationDrawnLettersMap [word]
  (zipmap word (repeat false))
  )

(defrecord Word [word size lettersmap drawnlettersmap])

(defn constructor ([word] (->Word word
                               (getLength word)
                               (makeWordMap word)
                               (initializationDrawnLettersMap word))))

(defn makeWord [string]
  (def word (atom (constructor string)))
  )

(defn setDrawnLettersMap [letter]
  (swap!
    word
    assoc-in [:drawnlettersmap letter] true)
  )

;;;;;;;;;;;;;;;Dictionary of words;;;;;;;;;;;;;;;;;;;;;;;;;

(def size (atom 0))

(def dictionary (atom []))

(defn getSize []
  (deref size)
  )

(defn getDictionary []
  (deref dictionary)
  )

(defn setSize [dictionary]
    (reset! size (count dictionary))
  )


(defn initializationDictionary []
  (reset! dictionary (into [] (map constructor (dictionary/dictionaryInitialization)) ))
  (setSize (dictionary/dictionaryInitialization))
  )


(defn removeWord [word]
  (reset! dictionary (remove #(= % word) @dictionary))
  (setSize @dictionary)
  )
