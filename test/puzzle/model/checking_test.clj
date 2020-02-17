(ns puzzle.model.checking-test
  (:require [clojure.test :refer :all]
            [com.model.Checking :as check]
            [com.model.Drawing :as draw]))
(import '(javax.swing JTextField))


(defn fields-letters-fixture [f]
  (def fields  (atom [(JTextField. "А") (JTextField. "А")
                      (JTextField. "А") (JTextField. "Г") (JTextField. "У") (JTextField. "К")
                      (JTextField. "Б") (JTextField. "Т") (JTextField. "В") (JTextField. "Л")
                      (JTextField. "Р") (JTextField. "Е")]))
  (f)
  )

(defn drawn-letters-fixture [f]
  (draw/fillDrawnLettersSeq @fields)
  (f)
  )

(defn drawn-map-fixture [f]
  (draw/initializationLettersMap)
  (f)
  )

(use-fixtures :once fields-letters-fixture drawn-letters-fixture
              drawn-map-fixture)


;Search for difference between ["Т" "Е" "С" "Т"] as inserted letters
;and [\Т \С \Т \Р \Е] as drawn letters
(deftest false-word-insertion-check
  (testing "Checking for incorrectly inserted word with drawn letters."
    (draw/fillSeqWithInsertedWord "тест")
    (check/checkWord)
    (is (= ["Т" "С"] (deref check/wronginsertedletters)))
    )
  )

;Search for difference between ["Т" "Е" "С" "Т"] as inserted letters
;and [\Т \С \Т \Р \Е] as drawn letters
(deftest true-word-insertion-check
  (testing "Checking for correctly inserted word with drawn letters."
    (draw/fillSeqWithInsertedWord "vuk")
    (check/checkWord)
    (is (= [] (deref check/wronginsertedletters)))
    )
  )


(deftest true-find-word-in-dictionary-check
  (testing "Testing for true where word exists in dictionary."
    (draw/fillSeqWithInsertedWord "vuk")
    (check/findWord)
    (is (= true (deref check/isFoundWordInDictionary)))
    (reset! check/isFoundWordInDictionary false)
    )
  )

(deftest false-find-word-in-dictionary-check
  (testing "Testing for false where word does not exist in dictionary."
    (draw/fillSeqWithInsertedWord "petao")
    (check/findWord)
    (is (= false (deref check/isFoundWordInDictionary)))
    )
  )