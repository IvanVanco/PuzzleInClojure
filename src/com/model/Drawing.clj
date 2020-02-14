(ns com.model.Drawing
  (:require [com.model.Converting :as convertor :refer [convertorSmallWord]]
            [com.data.Repository :as letters]
            [clojure.string :as s]))


;;;;;;;;;;;;;;;Letter position;;;;;;;;;;;;;;;;;;;;;;;;;
(def position (atom 0))

(defn increasePosition []
  (swap! position + 1)
  )

(defn decreasePosition []
  (swap! position - 1)
  )

(defn setPosition [pos]
  (compare-and-set! position @position pos)
  )

;;;;;;;;;;;;;;;Inserted letter;;;;;;;;;;;;;;;;;;;;;;;;;
(def insertedletters (atom []))

(defn getInsertedLetters []
  @insertedletters
  )

(defn clearAllInsertedLettersSeq [index]
  (if (< index 12 )
    (reset! insertedletters [])
    )
  )

(defn fillInsertedLettersSeq [index letter]
  (swap!
    insertedletters
    assoc index letter)
  )

(defn clearInsertedLettersSeq [index]
  (swap!
    insertedletters
    assoc index "")
  )

(defn makeWrongInsertedWord []
  "Returning String word from builted inserted letters"
  (let [conversion (mapv convertor/convertorBigWord (first @insertedletters))]
    (apply str conversion)
    )
  )

(defn fillSeqWithInsertedWord [word]
  (reset! insertedletters [])
  (let [conversion (convertor/convertorBigWord word)]
    (swap!
      insertedletters
      (fn [state]
        (into [] (flatten (conj (s/split conversion #"") @insertedletters)))
        )
      )
    )
  )
;;;;;;;;;;;;;TODO Drawn letters;;;;;;;;;;;;;;;;;;;;;;;;

(def drawnletters (atom []))

(def drawnlettersmap (atom [{}]))


(defn initializationLettersMap []
  (loop [x 0
         acc []]
    (if (> x (- (count letters/cyrillic) 1))
      (println "All elements are inserted!")
      (recur (inc x)
             (swap!
               drawnlettersmap
               assoc-in [x] {:letter (nth letters/cyrillic x) :appearance 0}
               )
             )
      )
    )
  )

(defn getDrawnLetterMap []
  @drawnlettersmap
  )

(defn getDrawnLetters []
  @drawnletters
  )

(defn clearDrawnLettersSeq []
  (initializationLettersMap)
  (reset! drawnletters [])
  )


(defn fillDrawnLettersSeq [fields]
  (loop [x 0
         acc []]
    (if (> x (- (.length fields) 1))
      (println "All elements are inserted!")
      (recur (inc x)
             (swap!
               drawnletters
               conj (.charAt (.getText (.get fields x)) 0)
               )
             )
      )
    )
  )



;;;;;;;;;;;;;TODO Generator;;;;;;;;;;;;;;;;;;;;;;;;