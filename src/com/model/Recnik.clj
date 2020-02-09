(ns com.model.Recnik
  (:require [com.data.Repository :as recnik]))


;;;;;;;;;;;;;;;Rec;;;;;;;;;;;;;;;;;;;;;;;;;
(defn vratiDuzinu [rec]
  (count rec)
  )

(defn napraviMapuOdReci [rec]
  (frequencies rec)
  )

(defn inicijalizacijaIzvucenihSlovaMape [rec]
  (zipmap rec (repeat false))
  )

(defrecord Rec [rec duzina slovamapa izvucenaslovamapa])

(defn konstruktor ([rec] (->Rec rec
                               (vratiDuzinu rec)
                               (napraviMapuOdReci rec)
                               (inicijalizacijaIzvucenihSlovaMape rec))))

(defn napraviRec [string]
  (def rec (atom (konstruktor string)))
  )

(defn postaviIzvucenaSlovamapu [slovo]
  (swap!
    rec
    assoc-in [:izvucenaslovamapa slovo] true)
  )

;;;;;;;;;;;;;;;Recnik reci;;;;;;;;;;;;;;;;;;;;;;;;;

(def velicina (atom 0))

(def recnik (atom []))

(defn vratiVelicina []
  (deref velicina)
  )

(defn vratiRecnik []
  (deref recnik)
  )

(defn postaviVelicina [recnik]
    (reset! velicina (count recnik))
  )


(defn inicijalizacijaRecnika []
  (reset! recnik (into [] (map konstruktor (recnik/recnikinicijalizacija)) ))
  (postaviVelicina (recnik/recnikinicijalizacija))
  )


(defn izbaciRec [rec]
  (reset! recnik (remove #(= % rec) @recnik))
  (postaviVelicina @recnik)
  )
