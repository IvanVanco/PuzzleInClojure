(ns com.data.Repository
  (:require [clojure.java.io :as io]))


;;Vektor slova
(def cirilica ["А" "Б" "В" "Г" "Д" "Ђ" "Ђ" "Е" "Ж" "З" "И" "Ј" "К" "Л" "Љ" "М" "Н" "Њ" "О"
               "П" "Р" "С" "Т" "Ћ" "У" "Ф" "Х" "Ц" "Ч" "Џ" "Џ" "Ш"])

(def latinica ["A" "B" "V" "G" "D" "DJ" "Đ" "E" "Ž" "Z" "I" "J" "K" "L" "LJ" "M" "N" "NJ"
               "O" "P" "R" "S" "T" "Ć" "U" "F" "H" "C" "Č" "DŽ" "DZ" "Š"])

;;Mapa Verovatnoca
(def verovatnoce {:А   {:ponder (/ 2.0 30) :izbaci false}
                  :Е   {:ponder (/ 2.0 30) :izbaci false}
                  :И   {:ponder (/ 2.0 30) :izbaci false}
                  :О   {:ponder (/ 2.0 30) :izbaci false}
                  :У   {:ponder (/ 2.0 30) :izbaci false}
                  :Р   {:ponder (/ 2.0 30) :izbaci false}
                  :Џ   {:ponder (/ 0.165 30) :izbaci true}
                  :Ф   {:ponder (/ 0.165 30) :izbaci true}
                  :Ч   {:ponder (/ 0.19 30) :izbaci true}
                  :Ђ   {:ponder (/ 0.19 30) :izbaci true}
                  :Ћ   {:ponder (/ 0.19 30) :izbaci true}
                  :Љ   {:ponder (/ 0.2 30) :izbaci true}
                  :Њ   {:ponder (/ 0.2 30) :izbaci true}
                  :Ж   {:ponder (/ 0.2 30) :izbaci true}
                  :Х   {:ponder (/ 0.25 30) :izbaci true}
                  :Ш   {:ponder (/ 0.25 30) :izbaci true}
                  :Ц   {:ponder (/ 0.5 30) :izbaci false}
                  :З   {:ponder (/ 0.5 30) :izbaci false}
                  :Д   {:ponder (/ 1.0 30) :izbaci false}
                  :Л   {:ponder (/ 1.0 30) :izbaci false}
                  :Г   {:ponder (/ 1.0 30) :izbaci false}
                  :М   {:ponder (/ 1.33 30) :izbaci false}
                  :Н   {:ponder (/ 1.33 30) :izbaci false}
                  :С   {:ponder (/ 1.33 30) :izbaci false}
                  :П   {:ponder (/ 1.33 30) :izbaci false}
                  :Т   {:ponder (/ 1.33 30) :izbaci false}
                  :Б   {:ponder (/ 1.33 30) :izbaci false}
                  :К   {:ponder (/ 1.33 30) :izbaci false}
                  :Ј   {:ponder (/ 1.33 30) :izbaci false}
                  :В   {:ponder (/ 1.33 30) :izbaci false}
                  }
  )


;;Vektor recnika
(defn recnikinicijalizacija []
  (with-open [read (io/reader ".\\resources\\Recnik cirilica.txt" :encoding "UTF-16")]
    (reduce conj [] (line-seq read)))
  )


(defn sacuvaj [rec]
  (with-open [w (clojure.java.io/writer ".\\resources\\Supervizija.txt" :append true :encoding "UTF-16" )]

    (.write w (str "\n" rec) )))
