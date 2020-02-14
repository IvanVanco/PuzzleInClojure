(ns com.model.Time)


(def seconds (atom 90))

(def LAST_SECONDS 0)

(def isWorking (atom false))



(defn setSecond [second]
  (compare-and-set! seconds @seconds second)
)

(defn setTimer [issWorking]
  (compare-and-set! isWorking @isWorking issWorking)
)

(defn reduceSecond []
  (swap! seconds - 1)
)


