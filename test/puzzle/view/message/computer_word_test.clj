(ns puzzle.view.message.computer-word-test
  (:require [clojure.test :refer :all]
            [com.view.message.MessageView :as message :refer :all]
            ))


(deftest computer-word-message-check
  (testing "Computer wod message."
    (is (= (message/computerWord "TEST" 4)))
    )
  )