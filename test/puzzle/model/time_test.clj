(ns puzzle.model.time-test
  (:require [clojure.test :refer :all]
            [com.model.Time :as time]))



(deftest start-time-check
  (testing "Checking if timer's starting time is set to default."
    (is (= 90 (deref time/seconds)))
    )
  )

(deftest end-time-check
  (testing "Checking if timer's end time is set to default."
    (is (= 0 time/LAST_SECONDS))
    )
  )

(deftest is-working-check
  (testing "Checking if timer's working option is set to default."
    (is (= false (deref time/isWorking)))
    )
  )

(deftest is-working-changer-check
  (testing "Checking if timer's function to change clock switch is working."
    (time/setTimer true)
    (is (= true (deref time/isWorking)))
    (time/setTimer false)
    )
  )

(deftest is-working-setting-time-check
  (testing "Checking if timer's setting function is working."
    (time/setSecond 45)
    (is (= 45 (deref time/seconds)))
    (time/setSecond 90)
    )
  )

(deftest is-working-reducing-time-check
  (testing "Checking if timer's reducing function is working."
    (time/reduceSecond)
    (is (= 89 (deref time/seconds)))
    (time/setSecond 90)
    )
  )