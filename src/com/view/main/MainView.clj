(ns com.view.main.MainView
  (:gen-class))
(import '(javax.swing JFrame JButton JTextField JLabel JPanel SwingConstants)
        '(java.awt BorderLayout Color Dimension FlowLayout Font GridLayout)
        '(java.awt.event ActionListener KeyListener MouseListener))

(defn swing []
  (let [polja  [(JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.)
                (JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.)]
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


    ;;**************************************GENERICKI**************************************

    (defn postaviFont
      "Postavlja definisani font na definisanu komponentu"
      [komponenta fontname fonttype velicina]
      (.setFont komponenta (Font. fontname fonttype velicina))
      )


    (defn velicinaKomponente
      "Postavljanje velicina Komponenti"
      [komponenta i j]
      (.setPreferredSize komponenta (Dimension. i j))
      )

    ;;**************************************SLOVA**************************************

    (defn zakljucajTextPolja []
      (doseq [x (range 0 12)]
        (.setEditable (.get polja x) false)
        )
      )

    (defn dodajPoljaNaPanel []
      (doseq [x (range 0 12)]
        (.add textovi (.get polja x))
       )
    )

    (defn postaviVelicinuPolja []
      (doseq [x (range 0 12)]
        (velicinaKomponente (.get polja x) 90 90)
        )
      )

    (defn postaviTextNaPoljeSlova [index text]
      (.setText (.get polja index) text)
      )

    (defn pregledajTextPrethodnogPoljaSlova [trenutniIndex]
      (if (<= trenutniIndex 0)
        ""
        (.getText (.get polja (- trenutniIndex 1)))
        )
      )

    (defn postaviFormatTextaPolja []
      (doseq [x (range 0 12)]
        (doto (.get polja x)
          (.setFont (Font. Font/SERIF, Font/BOLD, 25))
          (.setForeground (Color. 0 0 0))
          (.setHorizontalAlignment JTextField/CENTER)
          (.setBackground Color/WHITE)

          )
        )
      )

    (defn postaviBojuPolja [boja]
      (doseq [x (range 0 12)]
        (.setBackground (.get polja x) boja)
        )
      )

    ;;**************************************VREME**************************************

    (defn ocistiVreme []
      (.setText vreme "")
      )

    (defn postaviVreme [text]
      (.setText vreme text)
      )

    (defn zakljucajTextVremena []
      (.setEditable vreme false)
      )

    (defn postaviFormatVremena []
      (doto vreme
        (.setFont (Font. Font/SERIF, Font/BOLD, 25))
        (.setForeground (Color. 0 0 0))
        (.setHorizontalAlignment JTextField/CENTER)
        (.setBackground Color/WHITE)
        )
      )

    ;;**************************************UNOSENJE**************************************

    (defn vratiUnesenText []
      (.getText unosenje)
      )

    (defn postaviUnesenText [text]
      (.setText unosenje text)
      )

    (defn postaviFormatUnosenjaReci []
      (doto unosenje
        (.setFont (Font. Font/SERIF, Font/ITALIC, 20))
        (.setHorizontalAlignment JTextField/CENTER)
        )
      )

    (defn postaviFormatUnosenjaReci2 []
      (doto unosenje
        (.setFont (Font. Font/SERIF, Font/PLAIN, 20))
        (.setHorizontalAlignment JTextField/CENTER)
        )
      )

    ;;**************************************LISTENERI**************************************

    (defn postaviZaustaviOsluskivac []
      (.addActionListener zaustavi
         (proxy [ActionListener] []
           (actionPerformed [e] (println "Hello")
             )
          )
       )
    )

    (defn postaviZaustaviOsluskivac [^ActionListener zaustaviOsluskivac]
      (.addActionListener zaustavi zaustaviOsluskivac)
      )

    (defn postaviOcistiOsluskivac [^ActionListener ocistiOsluskivac]
      (.addActionListener ocisti ocistiOsluskivac)
      )

    (defn postaviPotvrdiRecOsluskivac [^ActionListener potvrdiRecOsluskivac]
      (.addActionListener potvrdirec potvrdiRecOsluskivac)
      )

    (defn postaviPotvrdiRecOsluskivac [^MouseListener unosenjeMouseOsluskivac]
      (.addMouseListener unosenje unosenjeMouseOsluskivac)
      )

    (defn postaviPotvrdiRecOsluskivac [^KeyListener unosenjeKeyOsluskivac]
      (.addKeyListener unosenje unosenjeKeyOsluskivac)
      )


    (doto frame
      (.setSize 700 300)
      (.setLocationRelativeTo nil)
      (.setLayout centar)
      (-> .getContentPane (.setSize 222 222))
      )
    ;;****************SLOVA**********************
    (postaviVelicinuPolja)
    (zakljucajTextPolja)
    (postaviFormatTextaPolja)

    ;;****************VELICINE**********************
    (velicinaKomponente zaustavi 90 90)
    (velicinaKomponente ocisti 90 90)
    (velicinaKomponente vreme 90 90)
    (velicinaKomponente unosenje 560 27)

    ;;****************FONT********************/
    (postaviFont zaustavi Font/SERIF Font/BOLD 17)
    (postaviFont ocisti Font/SERIF Font/BOLD 17)

    ;;****************FORMATI********************
    (zakljucajTextVremena)
    (postaviFormatVremena)
    (postaviFormatUnosenjaReci)
    (.setEnabled potvrdirec false)
    (.setEnabled unosenje false)
    ;;zabranjujem copy i paste
    (.setTransferHandler unosenje nil)

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
      (.add flow BorderLayout/SOUTH)
      )

    (doto frame
      ;;(.setSize 700 300)
      ;(.setLocationRelativeTo nil)
      ;(.setLayout centar)
      ;(-> .getContentPane (.setSize 222 222))

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

