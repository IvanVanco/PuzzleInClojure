(ns puzzle.view.message.expired-time-correct-word-test
  (:require [clojure.test :refer :all]
            [com.view.message.MessageView :as message :refer :all]))


(deftest expired-time-message-correct-word-check
  (testing "Expired time message when user submit correct word at the end of game.
            Insert any word for testing. Letters will match any of word's.
            Choose YES OR NO option. After NO option end game message will pop up."
    (is (= (message/expiredTime true "")))
    )
  )