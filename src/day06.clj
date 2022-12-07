( ns day06
    "Solutions for day 6 of AOC2022"
    (:require
        [utils]
    )
)

( defn findsetwith4 [line length n reqlength] 
    ( when ( < n (- length reqlength ) )    
        ( if ( = (count (set ( subs line n (+ n reqlength) ) ) ) reqlength )
            (+ n reqlength )
            ( recur line length (inc n ) reqlength )
        )
    )
)

( defn solvepart1and2 [line] 
    ( def length ( count line) ) 
    (println "Part 1")
    (println ( findsetwith4 line length 0 4) )
    (println "Part 2")
    (println (findsetwith4 line length 0 14 ) )
)

( defn solve [opts]
    ( def lines (utils/readlines "./data/day6.txt" ))
    ( solvepart1and2 (first lines ) ) 
)

( defn solve-test [opts]
    ( def lines (utils/readlines "./data/day6test.txt" ))
    ( solvepart1and2 ( first lines ) ) 
)
