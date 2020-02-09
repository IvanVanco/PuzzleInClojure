(ns com.model.Izvlacenje
  (:require [com.model.Konvertovanje :as konvertor :refer [konvertorMalaRec]]
            [com.data.Repository :as slova]
            [clojure.string :as s]))


;;;;;;;;;;;;;;;Pozicija slovo;;;;;;;;;;;;;;;;;;;;;;;;;
(def pozicija (atom 0))

(defn povecajPoziciju []
  (swap! pozicija + 1)
  )

(defn smanjiPoziciju []
  (swap! pozicija - 1)
  )

(defn postaviPoziciju [poz]
  (compare-and-set! pozicija @pozicija poz)
  )

;;;;;;;;;;;;;;;Uneseno slovo;;;;;;;;;;;;;;;;;;;;;;;;;
(def unesenaslova (atom []))

(defn vratiUnesenaSlova []
  @unesenaslova
  )

(defn ocistiCeoNizUnesenimSlovima [index]
  (if (< index 12 )
    (reset! unesenaslova [])
    )
  )

(defn popuniNizUnesenimSlovima [index slovo]
  (swap!
    unesenaslova
    assoc index slovo)
  )

(defn ocistiNizUnesenimSlovima [index]
  (swap!
    unesenaslova
    assoc index "")
  )

(defn napraviRecOdUnesenihSlova []
  "Vracam String rec napravljenu od unesenih slova"
  (let [konverzija (mapv konvertor/konvertorVelikaRec (first @unesenaslova))]
    (apply str konverzija)
    )
  )

(defn popuniNizUnesenomRecju [rec]
  (reset! unesenaslova [])
  (let [konverzija (konvertor/konvertorVelikaRec rec)]
    (swap!
      unesenaslova
      (fn [stanje]
        (into [] (flatten (conj (s/split konverzija #"") @unesenaslova)))
        )
      )
    )
  )
;;;;;;;;;;;;;TODO Izvucena slova;;;;;;;;;;;;;;;;;;;;;;;;

(def izvucenaslova (atom ["А" "Н" "А" "Н"]))

(def izvucenaslovamapa (atom [{}]))


(defn inicijalizacijaMapeSlova []
  (loop [x 0
         acc []]
    (if (> x (- (count slova/cirilica) 1))
      (println "Uneti svi elementi")
      (recur (inc x)
             (swap!
               izvucenaslovamapa
               assoc-in [x] {:slovo (nth slova/cirilica x) :pojavljivanje 0}
               )
             )
      )
    )
  )

(defn vratiIzvucenuMapuSlova []
  @izvucenaslovamapa
  )

(defn vratiIzvucenaSlova []
  @izvucenaslova
  )

(defn ocistiCeoNizIzvucenihSlova []
  (inicijalizacijaMapeSlova)
  (reset! izvucenaslova [])
  )


(defn popuniCeoNizIzvucenimSlovima [polja]
  (loop [x 0
         acc []]
    (if (> x (- (.length polja) 1))
      (println "Uneti svi elementi")
      (recur (inc x)
             (swap!
               izvucenaslova
               conj (.charAt (.getText (.get polja x)) 0)
               )
             )
      )
    )
  )



;;;;;;;;;;;;;TODO Generator;;;;;;;;;;;;;;;;;;;;;;;;
