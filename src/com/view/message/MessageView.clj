(ns com.view.message.MessageView
  "Used for displaying various messages to the users")
(import '(javax.swing JOptionPane))

;;;;;Dictionary view;;;;;;
(defn dictionaryMessage
  "Triggered just after Confirm view. Frame for displaying checking of
   inserted word existences in word dictionary and computer's longest word."
  [condition clientsize points]
  (let [option (object-array ["YES" "NO"])]
    (if (= true condition)
      (JOptionPane/showMessageDialog nil (str "Bravo, the word you entered was found in the dictionary.\nYour word has a length " clientsize " letters and has "
                                                       points " points.") "Game end" JOptionPane/INFORMATION_MESSAGE
                                     )

      (JOptionPane/showOptionDialog nil "Unfortunately, your word does not exist in the dictionary database.\nDo you want to send the word out for supervision and finish the game?"
                                    "Error" JOptionPane/YES_NO_OPTION JOptionPane/ERROR_MESSAGE
                                    nil option (first option))
      )
    )
  )


(defn computerWord
  "Message when computer algorithm found longest word."
  [computerword computersize]
  (JOptionPane/showMessageDialog nil (str "The computer found the word " computerword " with length " computersize ".")
                                 "Computer word" JOptionPane/INFORMATION_MESSAGE
  )
  )

;;;;;ConfirmWord view;;;;;;
(defn confirmWord
  "Showed when user click on confirm button on MainView, to perform checking of
   inserted word existences in drawn letters."
  [condition insertedword difference]
  (let [option (object-array ["YES" "NO"])]
    (if (= true condition)
      (if (= false (empty? insertedword))
        (JOptionPane/showOptionDialog nil (str "Bravo, all the letters you entered match the drawn letters.\nDo you want to confirm the word "
                                                        insertedword " or to try again?") "Question mark" JOptionPane/YES_NO_OPTION JOptionPane/QUESTION_MESSAGE
                                      nil option (first option))
        (JOptionPane/showMessageDialog nil "Please enter a word and try again." "Message" JOptionPane/ERROR_MESSAGE)
        )

      (JOptionPane/showMessageDialog nil (str "Unfortunately, the letters do not match: [" difference "]. Try again.")
                                     "Message" JOptionPane/ERROR_MESSAGE
                                     )
      )
    )
  )

;;;;;ExpiredTime view;;;;;;
(def ^:atom word (atom ""))
(def ^:atom question (atom 1))

(defn getQuestion
  "Question Getter."
  []
  @question
  )

(defn getWord
  "Word Getter."
  []
  @word
  )


(defn setExpiredTimeEnd
  "End game message."
  []
  (JOptionPane/showMessageDialog nil "You didn't enter any word. Better luck next time."
                                 "Game end" JOptionPane/INFORMATION_MESSAGE
                                 )
  )


(defn setExpiredTimeWord
  "Window message for reading input word from user."
  []
  (reset! word (JOptionPane/showInputDialog nil  "Time is up.\nPlease enter a word."
                                            "Game end" JOptionPane/WARNING_MESSAGE
                                            ))
  )


(defn expiredTime
  "Composition of other two message views, with time trigger in mind.
   I used atom for storing question answer for other application modules."
  [condition difference]
  (let [option  (object-array ["YES" "NO"])
        currentword     (setExpiredTimeWord)]
    (if (= true (empty? @word))
      (setExpiredTimeEnd)

      (if (= true condition)
        (do (reset! question (JOptionPane/showOptionDialog nil
                                          (str "Bravo, all the letters you entered match the drawn letters.\nDo you want to confirm the word "
                                               currentword " or to try again?") "Question mark" JOptionPane/YES_NO_OPTION JOptionPane/QUESTION_MESSAGE
                                          nil option (first option)))
            (reset! word currentword)
            (if (= @question 1)
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

