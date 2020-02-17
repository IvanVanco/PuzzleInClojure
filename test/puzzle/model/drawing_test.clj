(ns puzzle.model.drawing-test
  (:require [clojure.test :refer :all]
            [com.model.Drawing :as draw]))
(import '(javax.swing JTextField))

(defn inserted-letters-fixture [f]
  (draw/fillSeqWithInsertedWord "test")
  (f)
  )

(defn fields-letters-fixture [f]
  (def fields  (atom [(JTextField. "А") (JTextField. "А")
                (JTextField. "А") (JTextField. "Г") (JTextField. "У") (JTextField. "К")
                (JTextField. "Б") (JTextField. "Т") (JTextField. "В") (JTextField. "Л")
                (JTextField. "Р") (JTextField. "Е")]))
  (f)
  )

(defn drawn-map-fixture [f]
  (draw/initializationLettersMap)
  (f)
  )

(use-fixtures :once inserted-letters-fixture fields-letters-fixture
                    drawn-map-fixture)



(deftest start-position-pointer-check
  (testing "Test for starting position index, is it is zero."
    (is (= 0 (deref draw/position)))
    )
  )

(deftest set-position-pointer-check
  (testing "Test for setting position index."
    (draw/setPosition 1)
    (is (= 1 (deref draw/position)))
    )
    (draw/setPosition 0)
  )

(deftest increase-position-pointer-check
  (testing "Test for increasing position index."
    (draw/increasePosition)
    (is (= 1 (deref draw/position)))
    )
  )

(deftest decrease-position-pointer-check
  (testing "Test for decreasing position index."
    (draw/decreasePosition)
    (is (= 0 (deref draw/position)))
    )
  )

(deftest making-inserted-letters-check
  (testing "Making inserted letters vector from inserted word string."
    (draw/fillSeqWithInsertedWord "test")
    (is (= ["Т" "Е" "С" "Т"] (deref draw/insertedletters)))
    )
  )

(deftest clearing-inserted-letters-when-index-is-greater-check
  (testing "Clearing letters seq when index is greater then 11."
    (draw/clearAllInsertedLettersSeq 12)
    (is (= ["Т" "Е" "С" "Т"] (deref draw/insertedletters)))
    )
  )

(deftest clearing-inserted-letters-when-index-is-lower-check
  (testing "Clearing letters seq when index is less then 12."
    (draw/clearAllInsertedLettersSeq 0)
    (is (= [] (draw/getInsertedLetters)))
    )
  )

(deftest filling-inserted-letters-on-index-check
  (testing "Filling letters seq at position index with letter."
    (draw/fillInsertedLettersSeq 0 "С")
    (is (= ["С" "Е" "С" "Т"] (deref draw/insertedletters)))
    )
  )

(deftest filling-drawn-letters-of-textfields-check
  (testing "Filling drawn letters seq from jtextfields."
    (draw/fillDrawnLettersSeq @fields)
    (is (= [\А \А \А \Г \У \К \Б \Т \В \Л \Р \Е]
           (deref draw/drawnletters)))
    )
  )