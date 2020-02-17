(ns puzzle.view.message.dictionary-message-test
  (:require [clojure.test :refer :all]
            [com.view.message.MessageView :as message :refer :all]))


(deftest dictionary-message-found-word-check
  (testing "Dictionary message when user enters word that is found in dictionary."
    (is (= (message/dictionaryMessage true 8 16)))
    )
  )

(deftest dictionary-message-not-found-word-check
  (testing "Dictionary message when user enters word that is not found in dictionary."
    (is (= (message/dictionaryMessage false 8 16)))
    )
  )