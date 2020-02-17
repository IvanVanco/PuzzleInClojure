(ns puzzle.view.message.confirm-word-test
  (:require [clojure.test :refer :all]
            [com.view.message.MessageView :as message :refer :all]))

(deftest confirm-word-message-true-word-check
  (testing "Confirming word - correct word."
    (is (= (message/confirmWord true "CORRECTWORD" "")))
    )
  )

(deftest confirm-word-message-no-word-check
  (testing "Confirming word - when user inserts no word."
    (is (= (message/confirmWord true "" "")))
    )
  )

(deftest confirm-word-message-wrong-word-check
  (testing "Confirming word - when user inserts word that does not match drawn letters."
    (is (= (message/confirmWord false "TRUELETTERS" "WRONGLETTERS")))
    )
  )









