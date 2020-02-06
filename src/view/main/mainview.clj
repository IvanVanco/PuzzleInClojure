(ns view.main.mainview)

(import '(javax.swing JFrame JButton JTextField JLabel JPanel JComponent SwingConstants)
        '(java.awt BorderLayout Color Dimension FlowLayout Font GridLayout)
        '(java.awt.event ActionListener KeyListener MouseListener)
        '(java.util ArrayList))


(defn swing []
  (let [polja [(JTextField.)(JTextField.)(JTextField.)(JTextField.)(JTextField.)(JTextField.)
               (JTextField.)(JTextField.)(JTextField.)(JTextField.)(JTextField.)(JTextField.)]
        zaustavi (JButton. "ZAUSTAVI")
        ocisti (JButton. "OČISTI")
        potvrdirec (JButton. "POTVRDI")
        vreme (JTextField.)
        unosenje (JTextField. "Unesite pronađenu reč na ćirilici")
        vremelabela (JLabel. "Vreme:               " SwingConstants/RIGHT)
        frame (JFrame. "Slagalica")
        centar (BorderLayout. 10 10)
        gore (JPanel. (BorderLayout. 10 10))
        levo (JPanel. (BorderLayout. 10 10))
        desno (JPanel. (BorderLayout. 10 10))
        dole (JPanel. (BorderLayout. 10 10))

        flow (JPanel. (FlowLayout. FlowLayout/RIGHT 10 5))
        dugmici (JPanel. (GridLayout. 1 2 10 10))
        textovi (JPanel. (GridLayout. 0 12 10 10))]


    ;;**************************************GENERICKI**************************************;;

    (defn postaviFont
      "Postavlja definisani font na definisanu komponentu"
      [komponenta fontname fonttype velicina]
      (.setFont komponenta (Font. fontname fonttype velicina)))


    (defn velicinaKomponente
      "Postavljanje velicina Komponenti"
      [komponenta i j]
      (.setPreferredSize komponenta (Dimension. i j))
      )

    (defn zakljucajTextPolja []
        (for [polje polja]
          (.setEditable polje false)
        )
    )

    (defn dodajPoljaNaPanel []
      (for [polje polja]
        (.add textovi polje)
      )
    )

    ;;**************************************SLOVA**************************************;;



    (defn postaviVelicinuPolja []
      (for [polje polja]
        (velicinaKomponente polje 90 90)
      )
    )

    (postaviVelicinuPolja)


    (zakljucajTextPolja)




    (defn postaviFormatTextaPolja []
      (for [polje polja]
        (doto polje
          (.setFont (Font. Font/SERIF, Font/BOLD, 25))
          (.setForeground (Color. 0 0 0))
          (.setHorizontalAlignment JTextField/CENTER)
          (.setBackground Color/WHITE)
        )
      )
    )

    (postaviFormatTextaPolja)




    (velicinaKomponente zaustavi 90 90)
    (velicinaKomponente ocisti 90 90)
    (velicinaKomponente vreme 90 90)
    (velicinaKomponente unosenje 560 27)



    (doto dugmici
      (.add zaustavi)
      (.add ocisti))

    (dodajPoljaNaPanel)

    (.add desno vreme)
    (.add gore vremelabela)

    (doto flow
      (.add unosenje)
      (.add potvrdirec))

    (doto dole
      (.add textovi)
      (.add flow BorderLayout/SOUTH))



    (doto frame
      (.setSize 700 300)
      (.setLocationRelativeTo nil)
      (.setLayout centar)
      (-> .getContentPane (.setSize 222 222))

      (.add dugmici BorderLayout/CENTER)
      (.add dole BorderLayout/SOUTH)
      (.add gore BorderLayout/NORTH)
      (.add levo BorderLayout/WEST)
      (.add desno BorderLayout/EAST)

      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setVisible true))

    )
  )





(defn -main [& args]
  (swing))

