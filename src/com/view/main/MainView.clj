(ns com.view.main.MainView
  "Main window and starting position in game"
  (:gen-class))
(import '(javax.swing JFrame JButton JTextField JLabel JPanel SwingConstants)
        '(java.awt BorderLayout Color Dimension FlowLayout Font GridLayout)
        '(java.awt.event ActionListener KeyListener MouseListener))

(defn swing []
  (let [fields  [(JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.)
                (JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.) (JTextField.)]
        stopButton (JButton. "STOP")
        clearButton (JButton. "CLEAR")
        confirmButton (JButton. "CONFIRM")
        timeField (JTextField.)
        insertField (JTextField. "Insert serbian word in cyrillic")
        timeLabel (JLabel. "Time:               " SwingConstants/RIGHT)
        frame (JFrame. "Puzzle")
        center (BorderLayout. 10 10)
        upper (JPanel. (BorderLayout. 10 10))
        left (JPanel. (BorderLayout. 10 10))
        right (JPanel. (BorderLayout. 10 10))
        down (JPanel. (BorderLayout. 10 10))

        flow (JPanel. (FlowLayout. FlowLayout/RIGHT 10 5))
        buttons (JPanel. (GridLayout. 1 2 10 10))
        texts (JPanel. (GridLayout. 0 12 10 10))]


    ;;**************************************GENERICS**************************************

    (defn setCustomFont
      "Setting defined font on defined component."
      [component fontname fonttype size]
      (.setFont component (Font. fontname fonttype size))
      )


    (defn setComponentSize
      "Setting component's size."
      [component i j]
      (.setPreferredSize component (Dimension. i j))
      )

    ;;**************************************LETTERS**************************************

    (defn lockTextField
      "Locking input text field for inserting text."
      []
      (doseq [x (range 0 12)]
        (.setEditable (.get fields x) false)
        )
      )

    (defn addFieldToPanel
      "Helper function for adding text fields in Grid layout."
      []
      (doseq [x (range 0 12)]
        (.add texts (.get fields x))
       )
    )

    (defn setFieldsSize
      "Setting prefixed size for text fields."
      []
      (doseq [x (range 0 12)]
        (setComponentSize (.get fields x) 90 90)
        )
      )

    (defn addTextToField
      "Setting text value to specific text field at position index."
      [index text]
      (.setText (.get fields index) text)
      )

    (defn inspectTextPreviousField
      "Method used for Puzzle Engine to inspect text on previous field
      for probability reasons."
      [currentIndex]
      (if (<= currentIndex 0)
        ""
        (.getText (.get fields (- currentIndex 1)))
        )
      )

    (defn setFormatField
      "Format Setter."
      []
      (doseq [x (range 0 12)]
        (doto (.get fields x)
          (.setFont (Font. Font/SERIF, Font/BOLD, 25))
          (.setForeground (Color. 0 0 0))
          (.setHorizontalAlignment JTextField/CENTER)
          (.setBackground Color/WHITE)
          )
        )
      )

    (defn setColorField
      "Color Setter."
      [color]
      (doseq [x (range 0 12)]
        (.setBackground (.get fields x) color)
        )
      )

    ;;**************************************TIME**************************************

    (defn clearTime
      "Clearing time label."
      []
      (.setText timeField "")
      )

    (defn setTime
      "Setter."
      [text]
      (.setText timeField text)
      )

    (defn lockTextTime
      "Locking Time for text insertions."
      []
      (.setEditable timeField false)
      )

    (defn setFormatTime
      "Format Setter."
      []
      (doto timeField
        (.setFont (Font. Font/SERIF, Font/BOLD, 25))
        (.setForeground (Color. 0 0 0))
        (.setHorizontalAlignment JTextField/CENTER)
        (.setBackground Color/WHITE)
        )
      )

    ;;**************************************INSERT**************************************

    (defn getInsertedText
      "Inserted text Getter."
      []
      (.getText insertField)
      )

    (defn setInsertedText
      "Inserted text Setter."
      [text]
      (.setText insertField text)
      )

    (defn setInsertedFormat
      "Format setter."
      []
      (doto insertField
        (.setFont (Font. Font/SERIF, Font/ITALIC, 20))
        (.setHorizontalAlignment JTextField/CENTER)
        )
      )

    (defn setInsertedFormat2
      "Format setter."
      []
      (doto insertField
        (.setFont (Font. Font/SERIF, Font/PLAIN, 20))
        (.setHorizontalAlignment JTextField/CENTER)
        )
      )

    ;;**************************************LISTENERS**************************************

    (defn addStopListener
      "Test method only."
      []
      (.addActionListener stopButton
         (proxy [ActionListener] []
           (actionPerformed [e] (println "Hello")
             )
          )
       )
    )

    (defn setStopListener
      "ActionListener for Stop button."
      [^ActionListener stopListener]
      (.addActionListener stopButton stopListener)
      )

    (defn setClearListener
      "ActionListener for Clear button."
      [^ActionListener clearListener]
      (.addActionListener clearButton clearListener)
      )

    (defn setConfirmListener
      "ActionListener for Confirm button."
      [^ActionListener confirmListener]
      (.addActionListener confirmButton confirmListener)
      )

    (defn setConfirmListener
      "MouseListener for InsertField button."
      [^MouseListener insertMouseListener]
      (.addMouseListener insertField insertMouseListener)
      )

    (defn setConfirmListener
      "KeyListener for InsertField button."
      [^KeyListener insertKeyListener]
      (.addKeyListener insertField insertKeyListener)
      )


    (doto frame
      (.setSize 700 300)
      (.setLocationRelativeTo nil)
      (.setLayout center)
      (-> .getContentPane (.setSize 222 222))
      )
    ;;****************LETTERS**********************
    (setFieldsSize)
    (lockTextField)
    (setFormatField)

    ;;****************SIZE**********************
    (setComponentSize stopButton 90 90)
    (setComponentSize clearButton 90 90)
    (setComponentSize timeField 90 90)
    (setComponentSize insertField 560 27)

    ;;****************FONT********************/
    (setCustomFont stopButton Font/SERIF Font/BOLD 17)
    (setCustomFont clearButton Font/SERIF Font/BOLD 17)

    ;;****************FORMATS********************
    (lockTextTime)
    (setFormatTime)
    (setInsertedFormat)
    (.setEnabled confirmButton false)
    (.setEnabled insertField false)
    ;;forbidding copy and paste
    (.setTransferHandler insertField nil)

    (doto buttons
      (.add stopButton)
      (.add clearButton))

    (addFieldToPanel)

    (.add right timeField)
    (.add upper timeLabel)

    (doto flow
      (.add insertField)
      (.add confirmButton))

    (doto down
      (.add texts)
      (.add flow BorderLayout/SOUTH)
      )

    (doto frame
      (.setSize 700 300)
      (.setLocationRelativeTo nil)
      (.setLayout center)
      (-> .getContentPane (.setSize 222 222))

      (.add buttons BorderLayout/CENTER)
      (.add down BorderLayout/SOUTH)
      (.add upper BorderLayout/NORTH)
      (.add left BorderLayout/WEST)
      (.add right BorderLayout/EAST)

      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setVisible true))

    )
  )


