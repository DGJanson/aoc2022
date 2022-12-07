( ns day05
    "Solutions for day 5 of AOC2022"
    (:require
        [utils]
        )
)

( defn solvepart1 [stacksArg instructions]
    (def stacks stacksArg )
    ( doseq [inst instructions] 
        ( dotimes [ n ( get inst 0 ) ]
            ( def stacks ( assoc stacks (- (get inst 2) 1) (conj ( get stacks (- (get inst 2) 1 ) ) ( peek ( get stacks (-( get inst 1 ) 1) ) ) ) ) )
            ( def stacks ( assoc stacks (- (get inst 1) 1) ( pop (get stacks (- ( get inst 1 ) 1 ) ) ) ) )
        )
    )
    (println "Solution part 1")
    ( doseq [box stacks]
        (print (peek box) )
    )
    (println "")
)

( defn solvepart2 [stacksArg instructions]
    (def stacks stacksArg )
    ( doseq [inst instructions]
        ( def newboxes [] )
        ( dotimes [ n ( get inst 0 ) ]
            ( def newboxes (conj newboxes (peek ( get stacks ( - ( get inst 1) 1)  ) ) ) )
            ( def stacks ( assoc stacks (- (get inst 1) 1) ( pop (get stacks (- ( get inst 1 ) 1 ) ) ) ) )
        )
        ( doseq [box (reverse newboxes) ]
            ( def stacks ( assoc stacks (- (get inst 2) 1) (conj (get stacks (- (get inst 2) 1) ) box ) ) )
        )
    )
    (println "Solution part 2")
    ( doseq [box stacks]
        (print (peek box) )
    )
    (println "")

)

( defn readstack [lines nr]    
    ;; stacks will be a vector of lists (which can be use as queues in clojure)
    ( def stacks [] )
    ( doseq [n (range nr)]
        (def stacks (conj stacks (list) ) )
    )
    ( doseq [line (reverse lines)]
        ( doseq [n (range nr)]
            (do 
                ( if (clojure.string/includes? line "[") (do
                    ( def box (get line (+ 1 (* 4 n ) ) ) )
                    (if (not (= box (char 32))) (def stacks (assoc stacks n (conj (get stacks n) box ) ) ) ) 
                    )
                )
            )
        )
    )
    stacks
)

( defn readinst [lines]
    ( def inst [] )
    ( doseq [line lines] 
        ( def matcher (re-matcher #"move (\d+) from (\d+) to (\d+)" line))
        ( def inst (conj inst (vec (map #(Integer/parseInt %) (next (re-find matcher) ) ) ) ) )
    )
    inst    
)

( defn solve [opts]
    ( def stacks ( readstack ( utils/readlines "./data/day5stack.txt" ) 9 ) )
    ( def inst( readinst ( utils/readlines "./data/day5inst.txt" ) ) )
    ( solvepart1 stacks inst ) 
    ( def stacks ( readstack ( utils/readlines "./data/day5stack.txt" ) 9 ) )
    ( solvepart2 stacks inst )
)

( defn solve-test [opts]
    ( def stacks ( readstack ( utils/readlines "./data/day5stacktest.txt" ) 3 ) )
    ( def inst ( readinst ( utils/readlines "./data/day5insttest.txt" ) ) )
    ( solvepart1 stacks inst ) 
    ( def stacks ( readstack ( utils/readlines "./data/day5stacktest.txt" ) 3 ) )
    ( solvepart2 stacks inst )
)

