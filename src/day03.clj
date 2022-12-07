( ns day03
    "Solutions for third day of AOC2022"
    (:require 
        [utils]
        [clojure.set]
    )
    )

(defn findLetter [line]
    ( def half (/ (count line) 2) )
    ( def charset1  (set (subvec (vec line) 0 half)))
    ( def charset2  (set (subvec (vec line) half)))
    (int (first (clojure.set/intersection charset1 charset2) ))
)

( defn findLetterPart2 [lines]
    (def charset1 (set (vec (nth lines 0) ) ) )
    (def charset2 (set (vec (nth lines 1) ) ) )
    (def charset3 (set (vec (nth lines 2) ) ) )
    (int (first (clojure.set/intersection charset1 (clojure.set/intersection charset2 charset3))) )
)   

( defn calcScore [numbers] 
    ( reduce + ( map 
        ( fn [number] ( if (> number 96 ) (- number 96 ) (- number 38)) )
        numbers )
    )
)

( defn solvepart1 [lines]
    ( println (calcScore ( map findLetter lines) ) )
)

( defn solvepart2 [lines]
    ( def partlines (vec ( partition 3 lines )))
    (println (calcScore (map findLetterPart2 partlines)))
)

( defn solve [opts]
    ( def lines (utils/readlines "./data/day3.txt" ))
    (solvepart1 lines)
    (solvepart2 lines)
)

( defn solve-test [opts]
    ( def lines (utils/readlines "./data/day3test.txt"))
    (solvepart1 lines)
    (solvepart2 lines)
)
