(ns com.model.Dictionary
  "Dictionary model of words"
  (:require [com.data.Repository :as dictionary]))


;;;;;;;;;;;;;;;Word;;;;;;;;;;;;;;;;;;;;;;;;;
(defn getLength
  "Getting number of characters of word."
  [word]
  (count word)
  )

(defn makeWordMap
  "Making map of letters and number of them in word."
  [word]
  (frequencies word)
  )

(defn initializationDrawnLettersMap
  "Initialization function for setting inserted boolean trigger to false."
  [word]
  (zipmap word (repeat false))
  )

(defrecord Word [word size lettersmap drawnlettersmap])

(defn constructor ([word] (->Word word
                               (getLength word)
                               (makeWordMap word)
                               (initializationDrawnLettersMap word))))

(defn makeWord
  "Function for calling word constructor and creating word from string parameter."
  [string]
  (def word (atom (constructor string)))
  )

(defn setDrawnLettersMap
  "Used to set inserted boolean trigger to true, when corresponding letter is drawn."
  [letter]
  (swap!
    word
    assoc-in [:drawnlettersmap letter] true)
  )

;;;;;;;;;;;;;;;Dictionary of words;;;;;;;;;;;;;;;;;;;;;;;;;

(def ^:atom size (atom 0))

(def ^:atom dictionary (atom []))

(defn getSize
  "Size Getter."
  []
  (deref size)
  )

(defn getDictionary
  "Dictionary Getter."
  []
  (deref dictionary)
  )

(defn setSize
  "Function to set size of dictionary vector. Data is stored into size variable."
  [dictionary]
    (reset! size (count dictionary))
  )


(defn initializationDictionary
  "Using initialize function will import dictionary from file system, using Repository namespace."
  []
  (reset! dictionary (into [] (map constructor (dictionary/dictionaryInitialization))))
  (setSize (dictionary/dictionaryInitialization))
  )


(defn removeWord
  "Function for removing word from dictionary vector, not from dictionary file tho."
  [word]
  (reset! dictionary (remove #(= % word) @dictionary))
  (setSize @dictionary)
  )
