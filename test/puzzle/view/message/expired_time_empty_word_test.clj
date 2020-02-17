(ns puzzle.view.message.expired-time-empty-word-test
  (:require [clojure.test :refer :all]
            [com.view.message.MessageView :as message :refer :all]))


(deftest expired-time-message-wrong-word-check
  (testing "Expired time message when user submit empty word at the end of game.
            For testing, click OK or Cancel button without inserting word.
            End game message will pop up."
    (is (= (message/expiredTime false "")))
    )
  )