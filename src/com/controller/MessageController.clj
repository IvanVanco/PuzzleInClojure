(ns com.controller.MessageController
  (:require [com.view.message.MessageView :as message]
            [com.model.Checking :as check]))


;;;;;;;;;;;;;;;Dictionary;;;;;;;;;;;;;;;;;;;;;;;;;
(defn dictionaryMessage [computerword point]
  (check/findWord)
  (message/recnikPoruka (deref (check/isFoundWordDictionary))
                       (.length (check/getInsertedWord))
                       (point)

  ))
;;;;;;;;;;;;;;;ConfirmWord;;;;;;;;;;;;;;;;;;;;;;;;;
(defn confirmWord []
  (check/checkWord)
  (message/confirmWord check/isEmptyWrongInsertedWord
                      check/getInsertedWord
                      check/makeWrongInsertedWord
   )
)

;;;;;;;;;;;;;;;ExpiredTime;;;;;;;;;;;;;;;;;;;;;;;;;
(defn expiredTime []
  (message/setExpiredTimeWord)

  (if (empty? message/getWord)
    (do (message/setExpiredTimeEnd)
        (System/exit 0))
    )
  (check/fillInputWord message/getWord)
  (check/checkWord)
  (message/expiredTime check/isEmptyWrongInsertedWord
                       check/makeWrongInsertedWord)

  (if (= message/getQuestion 1)
    (System/exit 0)
    )
  )

