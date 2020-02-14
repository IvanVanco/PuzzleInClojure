(ns com.data.Repository
  (:require [clojure.java.io :as io]))


;;Serbian Letter vector
(def cyrillic ["А" "Б" "В" "Г" "Д" "Ђ" "Ђ" "Е" "Ж" "З" "И" "Ј" "К" "Л" "Љ" "М" "Н" "Њ" "О"
               "П" "Р" "С" "Т" "Ћ" "У" "Ф" "Х" "Ц" "Ч" "Џ" "Џ" "Ш"])

(def latin    ["A" "B" "V" "G" "D" "DJ" "Đ" "E" "Ž" "Z" "I" "J" "K" "L" "LJ" "M" "N" "NJ"
               "O" "P" "R" "S" "T" "Ć" "U" "F" "H" "C" "Č" "DŽ" "DZ" "Š"])

;;Probability Map
(def probability {:А   {:weight (/ 2.0 30) :eject false}
                  :Е   {:weight (/ 2.0 30) :eject false}
                  :И   {:weight (/ 2.0 30) :eject false}
                  :О   {:weight (/ 2.0 30) :eject false}
                  :У   {:weight (/ 2.0 30) :eject false}
                  :Р   {:weight (/ 2.0 30) :eject false}
                  :Џ   {:weight (/ 0.165 30) :eject true}
                  :Ф   {:weight (/ 0.165 30) :eject true}
                  :Ч   {:weight (/ 0.19 30) :eject true}
                  :Ђ   {:weight (/ 0.19 30) :eject true}
                  :Ћ   {:weight (/ 0.19 30) :eject true}
                  :Љ   {:weight (/ 0.2 30) :eject true}
                  :Њ   {:weight (/ 0.2 30) :eject true}
                  :Ж   {:weight (/ 0.2 30) :eject true}
                  :Х   {:weight (/ 0.25 30) :eject true}
                  :Ш   {:weight (/ 0.25 30) :eject true}
                  :Ц   {:weight (/ 0.5 30) :eject false}
                  :З   {:weight (/ 0.5 30) :eject false}
                  :Д   {:weight (/ 1.0 30) :eject false}
                  :Л   {:weight (/ 1.0 30) :eject false}
                  :Г   {:weight (/ 1.0 30) :eject false}
                  :М   {:weight (/ 1.33 30) :eject false}
                  :Н   {:weight (/ 1.33 30) :eject false}
                  :С   {:weight (/ 1.33 30) :eject false}
                  :П   {:weight (/ 1.33 30) :eject false}
                  :Т   {:weight (/ 1.33 30) :eject false}
                  :Б   {:weight (/ 1.33 30) :eject false}
                  :К   {:weight (/ 1.33 30) :eject false}
                  :Ј   {:weight (/ 1.33 30) :eject false}
                  :В   {:weight (/ 1.33 30) :eject false}
                  }
  )


;;Dictionary vector
(defn dictionaryInitialization []
  (with-open [read (io/reader ".\\resources\\Recnik cirilica.txt" :encoding "UTF-16")]
    (reduce conj [] (line-seq read)))
  )


(defn save [word]
  (with-open [w (clojure.java.io/writer ".\\resources\\Supervizija.txt" :append true :encoding "UTF-16" )]

    (.write w (str "\n" word) )))
