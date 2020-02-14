(ns com.model.Scoring)

(defn length [obj] (.length obj))


(defn scoring [clientword computerword]
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





