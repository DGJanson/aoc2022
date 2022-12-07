(ns day04
    "Solutions for day 4 of AOC2022" 
    (:require 
        [utils]
        [clojure.string :as str])
    )

( defn checkOverlap [line]
    ( def splitline ( str/split line #"[\,-]" ) )
    ( def splitline ( vec (map #(Integer/parseInt %) splitline)))
    ( cond
        (> (get splitline 0) (get splitline 2)) (if (<= (get splitline 1) (get splitline 3)) 1 0)
        (< (get splitline 0) (get splitline 2)) (if (>= (get splitline 1) (get splitline 3)) 1 0)
        (= (get splitline 0) (get splitline 2)) 1
        :else 0
    )
)

( defn checkOverlapPart2 [line]
    ( def splitline ( str/split line #"[\,-]" ) )
    ( def splitline ( vec (map #(Integer/parseInt %) splitline)))
    ( cond
        (> (get splitline 0) (get splitline 2)) (if (<= (get splitline 0) (get splitline 3)) 1 0)
        (< (get splitline 0) (get splitline 2)) (if (>= (get splitline 1) (get splitline 2)) 1 0)
        (= (get splitline 0) (get splitline 2)) 1
        :else 0
    )
)

( defn solvePart1 [lines]
    (println (reduce + ( map checkOverlap lines)))
)

( defn solvePart2 [lines]
    (println (reduce + (map checkOverlapPart2 lines)))
)

( defn solve [opts]
    ( def lines ( utils/readlines "./data/day4.txt" ))
    ( solvePart1 lines)
    ( solvePart2 lines)
    )

( defn solve-test [opts]
    ( def lines ( utils/readlines "./data/day4test.txt" ))
    ( solvePart1 lines)
    ( solvePart2 lines)
    )
