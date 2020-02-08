(ns com.view.poruke.PorukeView)
(import '(javax.swing JOptionPane Icon))

;;;;;Recnik view;;;;;;
(defn recnikPoruka [uslov korisnikduzina brojbodova]
  (let [opcija ["Da" "Ne"]]
    (if (uslov)
      (JOptionPane/showMessageDialog nil (str "Bravo, uneta reč je pronađena u rečniku.\nVaša reč ima dužinu " korisnikduzina " slova i iznosi "
                                                       brojbodova " bodova.") "Kraj igre" JOptionPane/INFORMATION_MESSAGE
                                     )

      (JOptionPane/showOptionDialog nil "Nazalost, Vaša reč ne postoji u bazi rečnika.\nDa li želite da pošaljete reč na superviziju i da završite igru?"
                                    "Greška" JOptionPane/YES_NO_OPTION JOptionPane/ERROR_MESSAGE
                                    (Icon.) opcija (first opcija))
      )
    )
  )


(defn kompjuterRec [kompjuterrec  kompjuterduzina]
  (JOptionPane/showMessageDialog nil (str "Kompjuter je pronašao reč " kompjuterrec " sa dužinom " kompjuterduzina ".")
                                 "Komjuter reč" JOptionPane/INFORMATION_MESSAGE
  )
  )

;;;;;PotvrdaReci view;;;;;;
(defn potvrdaReci [uslov  unesenarec  razlika]
  (let [opcija ["Da" "Ne"]]
    (if (uslov)
      (if (not= empty? unesenarec)
        (JOptionPane/showOptionDialog nil (str "Bravo, sva uneta slova se poklapaju sa izvučenim slovima.\nDa li želite da potvrdite reč "
                                                        unesenarec " ili da pokušate ponovo?") "Upitnik" JOptionPane/YES_NO_OPTION JOptionPane/QUESTION_MESSAGE
                                      (Icon.) opcija (first opcija))
        (JOptionPane/showMessageDialog nil "Molim Vas unesite reč i pokušajte ponovo." "Poruka" JOptionPane/ERROR_MESSAGE)
        )

      (JOptionPane/showMessageDialog nil (str "Nazalost, ne poklapaju se slova: [" razlika "]. Pokušajte ponovo.")
                                     "Poruka" JOptionPane/ERROR_MESSAGE
                                     )
      )
    )
  )

;;;;;IstekVremena view;;;;;;
(def rec (atom ""))
(def pitanje (atom 1))

(defn vratiPitanje []
  @pitanje
  )

(defn vratiRec []
  @rec
  )


(defn postaviIstekVremenaKraj []
  (JOptionPane/showMessageDialog nil "Niste uneli nijednu reč. Više sreće sledeći put."
                                 "Kraj igre" JOptionPane/INFORMATION_MESSAGE
                                 )
  )


(defn postaviIstekVremenaRec []
  (JOptionPane/showInputDialog nil  "Isteklo je vreme.\nMolimo Vas unesite reč."
                               "Kraj igre" JOptionPane/WARNING_MESSAGE
                               )
  )


(defn istekVremena [uslov razlika]
  (let [opcija  ["Da" "Ne"]
        tekucarec     (postaviIstekVremenaRec)
        tekucepitanje (JOptionPane/showOptionDialog nil (str "Bravo, sva uneta slova se poklapaju sa izvučenim slovima.\nDa li želite da potvrdite reč "
                                                                 tekucarec " ili da pokušate ponovo?") "Upitnik" JOptionPane/YES_NO_OPTION JOptionPane/QUESTION_MESSAGE
                                              (Icon.) opcija (first opcija))]
    (if (empty? @rec)
      (postaviIstekVremenaKraj)
      (if (uslov)
        (do (tekucepitanje)
            (reset! rec tekucarec)
            (reset! pitanje tekucepitanje)
            (if (= tekucepitanje 1)
              (postaviIstekVremenaKraj)
              )
            )
        (JOptionPane/showMessageDialog nil (str "Nazalost, ne poklapaju se slova: [" razlika "]. Više sreće sledeći put.")
                                       "Poruka" JOptionPane/ERROR_MESSAGE
                                       )
        )
      )
    )
  )