(ns com.model.Vreme)


(def sekunde (atom 90))

(def POSLEDNJA_SEKUNDA 0)

(def daLiradi (atom false))



(defn postaviSekundu [sekunda]
  (compare-and-set! sekunde @sekunde sekunda)
)

(defn postaviRadTimera [radi]
  (compare-and-set! daLiradi @daLiradi radi)
)

(defn smanjiSekundu []
  (swap! sekunde - 1)
)


