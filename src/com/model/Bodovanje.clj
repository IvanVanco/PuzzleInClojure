(ns com.model.Bodovanje)

(defn lenght [obj] (.length obj))


(defn bodovanje [korisnikrec kompjuterrec]
  (let [korisnikduzina (lenght korisnikrec)
        kompjuterduzina (lenght kompjuterrec)]
    (if (and (= korisnikduzina kompjuterduzina) (= korisnikrec kompjuterrec))
      (+ (* korisnikduzina 2) 5)
      (if (and (= korisnikduzina kompjuterduzina) (not= korisnikrec kompjuterrec))
        (* korisnikduzina 3)
        (* korisnikduzina 2)
        )
    )
  )
)


(defn -main [& args]
  (println (bodovanje "Korisnikl" "Korisnikk") ))



