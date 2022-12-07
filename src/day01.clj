( ns day01
    "Solutions for day 1"
    (:require
        [utils]
        [clojure.string :as str]))

( defn solvePart1 [lines] 
    (def maxcals 0)
    (def cursum 0)
    (doseq [line lines]
        (if (str/blank? (str/trim line))
            ;; New elf check if higher than max
            ( do
                (if (> cursum maxcals) (def maxcals cursum))
                (def cursum 0)
            )
            ;; Still same elf, add to cals
            ( do
                ( def cursum (+ cursum (Integer/parseInt (str/trim line))))
            )
        )
    )
    ( println maxcals )
)

( defn solvePart2 [lines]
    (def elves [])
    (def cursum 0)
    (doseq [line lines]
        (if (str/blank? (str/trim line))
            ;; New elf
            ( do
                (def elves ( conj elves cursum ) )
                ( def cursum 0 )
            )
            ;; simply add
            ( def cursum (+ cursum (Integer/parseInt (str/trim line))) )
        )
    )
    ( println (reduce + (take 3 (sort > elves))))
)

( defn solve [opts] 
    (def lines (utils/readlines "./data/day1.txt"))
    (solvePart1 lines)
    (solvePart2 lines)    
)

