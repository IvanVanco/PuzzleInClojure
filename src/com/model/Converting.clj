(ns com.model.Converting
  "Used for converting latin letters/word into cyrillic"
  (:require [com.data.Repository :as letters]))


(def ^:dynamic mapping (zipmap (map keyword letters/latin) letters/cyrillic))


(defn letters-to-word
  "Reverse function for converting word to letters."
  ([word] (letters-to-word word ""))
  ([word acc]
   (if-let [letters (seq word)]
     (recur (next letters) (str acc (first letters)))
     acc))
  )


(defn word-to-letters
  "Transformation function for converting word to letters - this step have some logic built into it,
   using serbian's language rules."
  ([word] (word-to-letters word (vector)))
  ([word acc]
   (if-let [letters (seq word)]
     (cond
       (= "L" (str (first letters))) (cond (= "J" (str (first (next letters)))) (recur (next (next letters)) (conj acc (str (first letters) "J")))
                                          :else (recur (next letters) (conj acc (str (first letters)))))
       (= "N" (str (first letters))) (cond (= "J" (str (first (next letters)))) (recur (next (next letters)) (conj acc (str (first letters) "J")))
                                          :else (recur (next letters) (conj acc (str (first letters)))))
       (= "D" (str (first letters))) (cond (= "J" (str (first (next letters)))) (recur (next (next letters)) (conj acc (str (first letters) "J")))
                                         (= "Z" (str (first (next letters)))) (recur (next (next letters)) (conj acc (str (first letters) "Z")))
                                         (= "Ž" (str (first (next letters)))) (recur (next (next letters)) (conj acc (str (first letters) "Ž")))
                                          :else (recur (next letters) (conj acc (str (first letters)))))
       :else (recur (next letters) (conj acc (str (first letters)))))
     acc)))


(defn conversion
  "Main function for mapping repository letters with result."
  ([letters] (conversion letters (vector)))
  ([letters acc]
   (if (not (empty? letters))
     (cond
       (= nil (mapping (keyword (first letters)))) (recur (rest letters) (conj acc (first letters)))
       :else (recur (rest letters) (conj acc (mapping (keyword (first letters))))))
     acc)))


(defn latinToCyrillic
  "Convert function with 3 part function. This is conversation process."
  [word]
  (->> word
       (word-to-letters)
       (conversion)
       (letters-to-word)
       )
  )


(defn convertorBigWord
  "Converting function to upper case letters."
  [word]
  (latinToCyrillic (.toUpperCase word))
  )

(defn convertorSmallWord
  "Converting function to lower case letters."
  [tip word]
  (latinToCyrillic (.toLowerCase word))
  )

(defn convertorLetterWord [letter]
  (latinToCyrillic (.toUpperCase (.toString letter)))
  )