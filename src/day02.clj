( ns day02
    "Solutions for day 2 of AOC2022"
    (:require [utils])
    )

( defn calcScore [rps] 
    (cond 
        (= (get rps 0) \A) (if (= (get rps 2) \X ) 4 ( if (= (get rps 2) \Y) 8 3))
        (= (get rps 0) \B) (if (= (get rps 2) \X ) 1 ( if (= (get rps 2) \Y) 5 9))
        (= (get rps 0) \C) (if (= (get rps 2) \X ) 7 ( if (= (get rps 2) \Y) 2 6))
        :else 0
    )
)

( defn calcScorePart2 [rps] 
    (cond 
        (= (get rps 0) \A) (if (= (get rps 2) \X ) 3 ( if (= (get rps 2) \Y) 4 8))
        (= (get rps 0) \B) (if (= (get rps 2) \X ) 1 ( if (= (get rps 2) \Y) 5 9))
        (= (get rps 0) \C) (if (= (get rps 2) \X ) 2 ( if (= (get rps 2) \Y) 6 7))
        :else 0
    )
)

( defn solvePart1 [lines]
    (println (reduce + (map calcScore lines)))
)

( defn solvePart2 [lines]
    (println (reduce + (map calcScorePart2 lines)))
)

( defn solve [options]
    (def lines ( utils/readlines "./data/day2.txt"))
    ( solvePart1 lines )
    ( solvePart2 lines )
    )

( defn solve-test [options] 
    (def lines ( utils/readlines "./data/day2test.txt"))
    ( solvePart1 lines )
    ( solvePart2 lines )
    )

