(ns puzzle.data.repository-test
  (:require [clojure.test :refer :all]
            [com.data.Repository :as repo]))


(deftest exist-file-dictionary-check
  (testing "Checking is file with dictionary word exist on program's location."
    (is (= true (.exists (clojure.java.io/file ".\\resources\\Recnik cirilica.txt"))))
    )
  )

(deftest exist-file-supervison-check
  (testing "Checking is file with supervison word exist on program's location."
    (is (= true (.exists (clojure.java.io/file ".\\resources\\Supervizija.txt"))))
    )
  )


(deftest inicialization-dictionary-check
  (testing "Testing to see if dictionary inicialization function is working properly."
    (is (= false (empty? (repo/dictionaryInitialization))))
    )
  )

(defn reading-last-save-word []
  (with-open [rdr (clojure.java.io/reader ".\\resources\\Supervizija.txt" :encoding "UTF-16")]
    (last (reduce conj [] (line-seq rdr))) )
  )

(defn deleting-last-save-word []
  (with-open [w (clojure.java.io/writer ".\\resources\\Supervizija.txt" :append true :encoding "UTF-16" )]
      (.write w (.replaceAll (reading-last-save-word) "TEST" ""))
    )
  )


(deftest save-word-in-supervision-file-check
  (testing "Testing to see if saving word in supervisor file is working."
    (repo/save "TEST")
    (is (= "TEST" (reading-last-save-word)))
    (deleting-last-save-word)
    )
  )