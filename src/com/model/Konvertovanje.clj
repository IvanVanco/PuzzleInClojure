(ns com.model.Konvertovanje
  (:require [com.data.Repository :as slova]))


(def mapiranje (zipmap (map keyword slova/latinica) slova/cirilica))


(defn- slova-u-rec
  ([rec] (slova-u-rec rec ""))
  ([rec acc]
   (if-let [slova (seq rec)]
     (recur (next slova) (str acc (first slova)))
     acc))
  )


(defn- rec-u-slova
  ([rec] (rec-u-slova rec (vector)))
  ([rec acc]
   (if-let [slova (seq rec)]
     (cond
       (= "L" (str (first slova))) (cond (= "J" (str (first (next slova)))) (recur (next (next slova)) (conj acc (str (first slova) "J")))
                                          :else (recur (next slova) (conj acc (str (first slova)))))
       (= "N" (str (first slova))) (cond (= "J" (str (first (next slova)))) (recur (next (next slova)) (conj acc (str (first slova) "J")))
                                          :else (recur (next slova) (conj acc (str (first slova)))))
       (= "D" (str (first slova))) (cond (= "J" (str (first (next slova)))) (recur (next (next slova)) (conj acc (str (first slova) "J")))
                                         (= "Z" (str (first (next slova)))) (recur (next (next slova)) (conj acc (str (first slova) "Z")))
                                         (= "Ž" (str (first (next slova)))) (recur (next (next slova)) (conj acc (str (first slova) "Ž")))
                                          :else (recur (next slova) (conj acc (str (first slova)))))
       :else (recur (next slova) (conj acc (str (first slova)))))
     acc)))


(defn- konverzija
  ([slova] (konverzija slova (vector)))
  ([slova acc]
   (if (not (empty? slova))
     (cond
       (= nil (mapiranje (keyword (first slova)))) (recur (rest slova) (conj acc (first slova)))
       :else (recur (rest slova) (conj acc (mapiranje (keyword (first slova))))))
     acc)))


(defn latinToCyrillic
  [rec]
  (->> rec
       (rec-u-slova)
       (konverzija)
       (slova-u-rec)
       )
  )

(defn konvertorVelikaRec [rec]
  (latinToCyrillic (.toUpperCase rec))
  )

(defn konvertorMalaRec [tip rec]
  (latinToCyrillic (.toLowerCase rec))
  )

(defn konvertorSlovaRec [slovo]
  (latinToCyrillic (.toUpperCase (.toString slovo)))
  )