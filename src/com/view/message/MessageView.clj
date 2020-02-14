(ns com.view.message.MessageView)
(import '(javax.swing JOptionPane Icon))

;;;;;Dictionary view;;;;;;
(defn dictionaryMessage [condition clientsize points]
  (let [option ["Yes" "No"]]
    (if (condition)
      (JOptionPane/showMessageDialog nil (str "Bravo, the word you entered was found in the dictionary.\nYour word has a length " clientsize " letters and has "
                                                       points " points.") "Game end" JOptionPane/INFORMATION_MESSAGE
                                     )

      (JOptionPane/showOptionDialog nil "Unfortunately, your word does not exist in the dictionary database.\nDo you want to send the word out for supervision and finish the game?"
                                    "Error" JOptionPane/YES_NO_OPTION JOptionPane/ERROR_MESSAGE
                                    (Icon.) option (first option))
      )
    )
  )


(defn computerWord [computerword computersize]
  (JOptionPane/showMessageDialog nil (str "The computer found the word " computerword " with length " computersize ".")
                                 "Computer word" JOptionPane/INFORMATION_MESSAGE
  )
  )

;;;;;ConfirmWord view;;;;;;
(defn confirmWord [condition insertedword difference]
  (let [option ["Yes" "No"]]
    (if (condition)
      (if (not= empty? insertedword)
        (JOptionPane/showOptionDialog nil (str "Bravo, all the letters you entered match the drawn letters.\nDo you want to confirm the word "
                                                        insertedword " or to try again?") "Question mark" JOptionPane/YES_NO_OPTION JOptionPane/QUESTION_MESSAGE
                                      (Icon.) option (first option))
        (JOptionPane/showMessageDialog nil "Please enter a word and try again." "Message" JOptionPane/ERROR_MESSAGE)
        )

      (JOptionPane/showMessageDialog nil (str "Unfortunately, the letters do not match: [" difference "]. Try again.")
                                     "Message" JOptionPane/ERROR_MESSAGE
                                     )
      )
    )
  )

;;;;;ExpiredTime view;;;;;;
(def word (atom ""))
(def question (atom 1))

(defn getQuestion []
  @question
  )

(defn getWord []
  @word
  )


(defn setExpiredTimeEnd []
  (JOptionPane/showMessageDialog nil "You didn't enter any word. Better luck next time."
                                 "Game end" JOptionPane/INFORMATION_MESSAGE
                                 )
  )


(defn setExpiredTimeWord []
  (JOptionPane/showInputDialog nil  "Time is up.\nPlease enter a word."
                               "Game end" JOptionPane/WARNING_MESSAGE
                               )
  )


(defn expiredTime [condition difference]
  (let [option  ["Yes" "No"]
        currentword     (setExpiredTimeWord)
        currentquestion (JOptionPane/showOptionDialog nil 
			(str "Bravo, all the letters you entered match the drawn letters.\nDo you want to confirm the word "
                                                                 currentword " or to try again?") "Question mark" JOptionPane/YES_NO_OPTION JOptionPane/QUESTION_MESSAGE
                                              (Icon.) option (first option))]
    (if (empty? @word)
      (setExpiredTimeEnd)
      (if (condition)
        (do (currentquestion)
            (reset! word currentword)
            (reset! question currentquestion)
            (if (= currentquestion 1)
              (setExpiredTimeEnd)
              )
            )
        (JOptionPane/showMessageDialog nil (str "Unfortunately, the letters do not match: [" difference "]. Better luck next time.")
                                       "Message" JOptionPane/ERROR_MESSAGE
                                       )
        )
      )
    )
  )