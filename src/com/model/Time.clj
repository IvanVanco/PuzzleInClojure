(ns com.model.Time
  "For storing game clock model data")


(def ^:atom seconds (atom 90))

(def ^:constant LAST_SECONDS 0)

(def ^:atom isWorking (atom false))



(defn setSecond
  "Setter seconds on clock."
  [second]
  (compare-and-set! seconds @seconds second)
)

(defn setTimer
  "Setting clock to on or off."
  [issWorking]
  (compare-and-set! isWorking @isWorking issWorking)
)

(defn reduceSecond
  "Clock ticking reduction function."
  []
  (swap! seconds - 1)
)


