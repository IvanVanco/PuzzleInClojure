(ns com.model.Proveravanje
  (:require [com.model.Izvlacenje :as slova]
            [com.model.Konvertovanje :as konverzija]
            [com.model.Recnik :as recnik]
            [clojure.data :as d]))

;;;;;;;;;;;;;;;Proveravac slovo;;;;;;;;;;;;;;;;;;;;;;;;;
(def tacnounesenaslova (atom []))

(def pogresnaunesenaslova (atom []))


(defn daLiSuPraznaPogresnoUnesenaSlova []
  (empty? @pogresnaunesenaslova)
  )

(defn napraviPogresnoUnesenuRec []
  (apply str @pogresnaunesenaslova)
  )

(defn vratiUnesenuRec []
  (slova/napraviRecOdUnesenihSlova)
  )

(defn popuniUnesenuRec [rec]
  (slova/popuniNizUnesenomRecju rec)
  )

(defn napraviPogresnoUnesenuKonkatenaciju []
  (apply str (interpose  ", " @pogresnaunesenaslova))
  )

(defn proveriSlaganje []
  (reset! tacnounesenaslova [])
  (reset! pogresnaunesenaslova [])
  (let [unos (slova/vratiUnesenaSlova)
        unosmapa (frequencies (slova/vratiUnesenaSlova))
        izlazmapa (frequencies (slova/vratiIzvucenaSlova))
        razlika (nth (d/diff unosmapa izlazmapa) 0)]
    (if (nil? razlika)
        (swap!
         tacnounesenaslova
         (fn [stanje]
           (into [] (flatten (conj @tacnounesenaslova unos)))
         ))
        (swap!
          pogresnaunesenaslova
          (fn [stanje]
            (into [] (flatten (conj @pogresnaunesenaslova (into [] (keys razlika)))))
            ))
    )
  ))

;;;;;;;;;;;;;;;Proveravac REC;;;;;;;;;;;;;;;;;;;;;;;;;
(def daLiJepronadjenaRecURecniku (atom false))


(defn pronadjiRec []
  (let [rec (konverzija/konvertorMalaRec "recnik" slova/napraviRecOdUnesenihSlova)]
    (if (nil? (some #(= rec %) (recnik/vratiRecnik)))
      (reset! daLiJepronadjenaRecURecniku true)

      (reset! daLiJepronadjenaRecURecniku false)
      )
    )
  )



