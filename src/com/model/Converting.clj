(ns com.model.Converting
  (:require [com.data.Repository :as letters]))


(def mapping (zipmap (map keyword letters/latin) letters/cyrillic))


(defn letters-to-word
  ([word] (letters-to-word word ""))
  ([word acc]
   (if-let [letters (seq word)]
     (recur (next letters) (str acc (first letters)))
     acc))
  )


(defn word-to-letters
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
  ([letters] (conversion letters (vector)))
  ([letters acc]
   (if (not (empty? letters))
     (cond
       (= nil (mapping (keyword (first letters)))) (recur (rest letters) (conj acc (first letters)))
       :else (recur (rest letters) (conj acc (mapping (keyword (first letters))))))
     acc)))


(defn latinToCyrillic
  [word]
  (->> word
       (word-to-letters)
       (conversion)
       (letters-to-word)
       )
  )

(defn convertorBigWord [word]
  (latinToCyrillic (.toUpperCase word))
  )

(defn convertorSmallWord [tip word]
  (latinToCyrillic (.toLowerCase word))
  )

(defn convertorLetterWord [letter]
  (latinToCyrillic (.toUpperCase (.toString letter)))
  )