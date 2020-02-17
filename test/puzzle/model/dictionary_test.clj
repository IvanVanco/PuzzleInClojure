(ns puzzle.model.dictionary-test
  (:require [clojure.test :refer :all]
            [com.model.Dictionary :as dictionary]))


(defn constructor-fixture [f]
  (dictionary/makeWord "test")
  (f)
  )

(defn dictionary-fixture [f]
  (dictionary/initializationDictionary)
  (f)
  )

(use-fixtures :once constructor-fixture dictionary-fixture)



(deftest word-count-check
  (testing "Testing length of word."
    (is (= 4 (dictionary/getLength @dictionary/word)))
    )
  )

(deftest word-drawn-letters-check
  (testing "Testing initial boolean drawn map."
    (is (= {\t false, \e false, \s false} (:drawnlettersmap @dictionary/word)))
    )
  )

(deftest word-map-check
  (testing "Testing frequencies map of word."
    (is (= {\t 2, \e 1, \s 1} (:lettersmap @dictionary/word)))
    )
  )


(deftest make-word-check
  (testing "Testing defining word by calling constructor."
    (is (= "test" (:word (deref dictionary/word))))
    )
  )

(deftest word-set-drawn-letter-map-check
  (testing "Testing inserting map letters in word."
    (dictionary/setDrawnLettersMap \t)

    (is (= {\t true, \e false, \s false} (:drawnlettersmap @dictionary/word)))
    )
  )

(deftest dictionary-size-check
  (testing "Checking for initial dictionary size, is it bigger then 10000 words."
    (is (< 10000 (dictionary/getSize)))
    )
  )

