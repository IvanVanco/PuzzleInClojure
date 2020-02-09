(ns com.controller.PorukeController
  (:require [com.view.poruke.PorukeView :as poruke]
            [com.model.Proveravanje :as provera]))


;;;;;;;;;;;;;;;Recnik;;;;;;;;;;;;;;;;;;;;;;;;;
(defn recnikPoruka [kompjuterRec bod]
  (provera/pronadjiRec)
  (poruke/recnikPoruka (deref (provera/daLiJepronadjenaRecURecniku))
                       (.length (provera/vratiUnesenuRec))
                       (bod)

  ))
;;;;;;;;;;;;;;;PotvrdaReci;;;;;;;;;;;;;;;;;;;;;;;;;
(defn potvrdaReci []
  (provera/proveriSlaganje)
  (poruke/potvrdaReci provera/daLiSuPraznaPogresnoUnesenaSlova
                      provera/vratiUnesenuRec
                      provera/napraviPogresnoUnesenuRec
   )
)

;;;;;;;;;;;;;;;IstekVremena;;;;;;;;;;;;;;;;;;;;;;;;;
(defn istekVremena []
  (poruke/postaviIstekVremenaRec)

  (if (empty? poruke/vratiRec)
    (do (poruke/postaviIstekVremenaKraj)
        (System/exit 0))
    )
  (provera/popuniUnesenuRec poruke/vratiRec)
  (provera/proveriSlaganje)
  (poruke/istekVremena provera/daLiSuPraznaPogresnoUnesenaSlova
                       provera/napraviPogresnoUnesenuRec)

  (if (= poruke/vratiPitanje 1)
    (System/exit 0)
    )
  )

