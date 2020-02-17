(ns puzzle.view.main.mainview-test
  (:require [clojure.test :refer :all]
            [com.view.main.MainView :as main :refer :all]))


(deftest mainview
  (testing "Displaying main window..."
      (main/swing)
      (Thread/sleep 10000)
    )
  )




