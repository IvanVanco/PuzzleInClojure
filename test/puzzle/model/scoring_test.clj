(ns puzzle.model.scoring-test
  (:require [clojure.test :refer :all]
            [com.model.Scoring :as score]))


(deftest scoring-shorter-word-check
  (testing "Checking score result when client found shorter word compare to computer's."
    (is (= 14 (score/scoring "HELPING" "HELPINGS")))
    (is (= 16 (score/scoring "HELPINGS" "EXISTENCES")))
    (is (= 20 (score/scoring "EXISTENCES" "availability")))
    )
  )

(deftest scoring-same-word-as-computer-check
  (testing "Checking score result when client found same word as computer's."
    (is (= 19 (score/scoring "HELPING" "HELPING")))
    (is (= 25 (score/scoring "EXISTENCES" "EXISTENCES")))
    (is (= 29 (score/scoring "availability" "availability")))
    )
  )

(deftest scoring-different-word-check
  (testing "Checking score result when client found different word which size is same as computer's."
    (is (= 21 (score/scoring "ability" "HELPING")))
    (is (= 30 (score/scoring "absolutely" "EXISTENCES")))
    (is (= 36 (score/scoring "compensation" "availability")))
    )
  )

(deftest scoring-longer-word-check
  (testing "Checking score result when client found longer word compare to computer's."
    (is (= 16 (score/scoring "accurate" "HELPING")))
    (is (= 18 (score/scoring "adventure" "accurate")))
    (is (= 20 (score/scoring "EXISTENCES" "adventure")))
    )
  )