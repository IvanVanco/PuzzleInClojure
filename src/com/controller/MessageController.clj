(ns com.controller.MessageController
  "It is pipe between Message views and Message Engine(will be added in future)"
  (:require [com.view.message.MessageView :as message]
            [com.model.Checking :as check]))


;;;;;;;;;;;;;;;Dictionary;;;;;;;;;;;;;;;;;;;;;;;;;
(defn dictionaryMessage
  "Dictionary message logic./n
  First checks for existence of word in word dictionary,/n
  and then displays dictionaryMessage function from MessageView."
  [computerword point]
  (check/findWord)
  (message/dictionaryMessage (deref (check/isFoundWordInDictionary))
                             (.length (check/getInsertedWord))
                             (point)

                             ))
;;;;;;;;;;;;;;;ConfirmWord;;;;;;;;;;;;;;;;;;;;;;;;;
(defn confirmWord
  "Confirm Word message logic./n
  First checks for correctness of letters in inserted word,
  and then calling confirmWord message function from Message View."
  []
  (check/checkWord)
  (message/confirmWord check/isEmptyWrongInsertedWord
                      check/getInsertedWord
                      check/makeWrongInsertedWord
   )
)

;;;;;;;;;;;;;;;ExpiredTime;;;;;;;;;;;;;;;;;;;;;;;;;
(defn expiredTime
  "Full expired message logic./n
   Called when time of game end(caliing setExpiredTimeWord function in Message View).
   Then, it is using composition logic from
   dictionaryMessage and confirmWord functions."
  []
  (message/setExpiredTimeWord)

  (if (empty? message/getWord)
    (do (message/setExpiredTimeEnd)
        (System/exit 0))
    )
  (check/fillInputWord message/getWord)
  (check/checkWord)
  (message/expiredTime check/isEmptyWrongInsertedWord
                       check/makeWrongInsertedWord
                       )

  (if (= message/getQuestion 1)
    (System/exit 0)
    )
  )

