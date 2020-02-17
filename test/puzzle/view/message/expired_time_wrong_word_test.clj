(ns puzzle.view.message.expired-time-wrong-word-test
  (:require [clojure.test :refer :all]
  [com.view.message.MessageView :as message :refer :all]))


(deftest expired-time-message-wrong-word-check
  (testing "Expired time message when user submit wrong word at the end of game.
            Insert word TEST for testing. Letters will not match any of word's."
    (is (= (message/expiredTime false "TEST")))
    )
  )

