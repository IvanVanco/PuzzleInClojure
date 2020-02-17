(ns puzzle.model.converting-test
  (:require [clojure.test :refer :all]
            [com.model.Converting :as convert]))


(deftest word-to-letters-check
  (testing "Checking word to letters function, using serbian's laguage rules."
    (is (= ["K" "O" "NJ"] (convert/word-to-letters "KONJ")))
    (is (= ["DŽ" "I" "P"] (convert/word-to-letters "DŽIP")))
    (is (= ["LJ" "U" "LJ" "A" "Š" "K" "A"] (convert/word-to-letters "LJULJAŠKA")))
    (is (= ["DZ" "A" "M" "I" "J" "A"] (convert/word-to-letters "DZAMIJA")))
    (is (= ["DJ" "U" "B" "R" "E"] (convert/word-to-letters "DJUBRE")))
    (is (= ["Ć" "E" "V" "A" "P" "Č" "I" "Ć" "I"] (convert/word-to-letters "ĆEVAPČIĆI")))
    (is (= [] (convert/word-to-letters "")))
    )
  )

(deftest letters-to-word-check
  (testing "Checking letters to word function for serbian language rules."
    (is (= "KONJ" (convert/letters-to-word ["K" "O" "NJ"])))
    (is (= "SOMETHING" (convert/letters-to-word ["S" "O" "M" "E" "T" "H" "I" "N" "G"])))
    (is (= "wolf" (convert/letters-to-word ["w" "o" "l" "f"])))
    (is (= "" (convert/letters-to-word [])))
    )
  )

(deftest latin-to-cyrillic-check
  (testing "Converting word from latin to cyrillic standard."
    (is (= "ЏИП" (convert/latinToCyrillic "DZIP")))
    (is (= "ЏИП" (convert/latinToCyrillic "DŽIP")))
    (is (= "ЂЕВРЕК" (convert/latinToCyrillic "DJEVREK")))
    (is (= "ЋЕВАПЧИЋИ" (convert/latinToCyrillic "ĆEVAPČIĆI")))
    (is (= "ШУМА" (convert/latinToCyrillic "ŠUMA")))
    (is (= "ЉУЉАШКА" (convert/latinToCyrillic "LJULJAŠKA")))
    (is (= "ЊУШКА" (convert/latinToCyrillic "NJUŠKA")))
    (is (= "САТНИЦА" (convert/latinToCyrillic "SATNICA")))
    (is (= "ХЛЕБ" (convert/latinToCyrillic "HLEB")))
    (is (= "ЈЕЖ" (convert/latinToCyrillic "JEŽ")))
    (is (= "ФИФА" (convert/latinToCyrillic "FIFA")))
    (is (= "ЂЕВРЕК" (convert/latinToCyrillic "ĐEVREK")))
    )
  )


(deftest big-word-convertor-check
  (testing "Converting word to upper cases."
    (is (= "ТЕСТ" (convert/convertorBigWord "test")))
    )
  )

(deftest small-word-convertor-check
  (testing "Converting words to lower cases."
    (is (= "test" (convert/convertorSmallWord "convertor" "TEST")))
    )
  )
(deftest big-letter-convertor-check
  (testing "Converting letters to upper cases."
    (is (= "К" (convert/convertorLetterWord "k")))
    )
  )






