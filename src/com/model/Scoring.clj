(ns com.model.Scoring
  "For calculating score based on different word sizes.")

(defn ^:dynamic length [obj] (.length obj))


(defn scoring
  "Return points based on clientword and computerword sizes."
  [clientword computerword]
  (let [clientsize (length clientword)
        computersize (length computerword)]
    (if (and (= clientsize computersize) (= clientword computerword))
      (+ (* clientsize 2) 5)
      (if (and (= clientsize computersize) (not= clientword computerword))
        (* clientsize 3)
        (* clientsize 2)
        )
    )
  )
)





