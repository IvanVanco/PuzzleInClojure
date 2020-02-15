(ns com.model.Drawing
  "Abstraction of letter drawing process in game"
  (:require [com.model.Converting :as convertor :refer [convertorSmallWord]]
            [com.data.Repository :as letters]
            [clojure.string :as s]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; Letter position;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:atom position (atom 0))

(defn increasePosition
  "Increase current drawing letter index."
  []
  (swap! position + 1)
  )

(defn decreasePosition
  "Decrease current drawing letter index."
  []
  (swap! position - 1)
  )

(defn setPosition
  "Set current drawing letter index."
  [pos]
  (compare-and-set! position @position pos)
  )

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; Inserted letter;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:atom insertedletters (atom []))

(defn getInsertedLetters
  "Inserted letters Getter."
  []
  @insertedletters
  )

(defn clearAllInsertedLettersSeq
  "Clearing inserted vector of letters. It is used when we click on Clear button."
  [index]
  (if (< index 12 )
    (reset! insertedletters [])
    )
  )

(defn fillInsertedLettersSeq
  "Inserting drawn letter at index."
  [index letter]
  (swap!
    insertedletters
    assoc index letter)
  )

(defn clearInsertedLettersSeq
  "Clearing drawn letter from vector at index parameter."
  [index]
  (swap!
    insertedletters
    assoc index "")
  )

(defn makeWrongInsertedWord
  "Returning String word from builted inserted letters."
  []
  (let [conversion (mapv convertor/convertorBigWord (first @insertedletters))]
    (apply str conversion)
    )
  )

(defn fillSeqWithInsertedWord
  "Used to fill inserted letters vector, when letters are drawn.
  It is also using converting namespace function convertorBigWord."
  [word]
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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; TODO Drawn letters;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def ^:atom drawnletters (atom []))

(def ^:atom drawnlettersmap (atom [{}]))


(defn initializationLettersMap
  "Used to create starter map of letter and number of appearance."
  []
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

(defn getDrawnLetterMap
  "Drawn letter map Getter."
  []
  @drawnlettersmap
  )

(defn getDrawnLetters
  "Drawn letters Getter."
  []
  @drawnletters
  )

(defn clearDrawnLettersSeq
  "Additional function call when we trigger Clear button."
  []
  (initializationLettersMap)
  (reset! drawnletters [])
  )


(defn fillDrawnLettersSeq
  "Filling letters of word using tail optimised recursion call."
  [fields]
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



;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;; TODO Generator;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;