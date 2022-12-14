; This is a horrible solution. I have no idea why it works...
; Still a lot to learn.
; Please do not use this for anything other than to ridicule me



( ns day07
    "Solutions for day 7 of AOC2022"
    (:require
        [utils]
        [clojure.string :as string ]
        )
    )

( defn parse-commands [lines dirs curdir]
    ( let [line (first lines)]
        (cond 
            (nil? line) dirs
            :else (do
                (let [[_ cd ls filesize filename dirlisting]
                      (re-find #"\$ cd (.+)|\$ (ls)|(\d+) (.+)|dir (.+)" line)]
                    (cond 
                        (some? cd) (case cd
                                        ".." (recur (rest lines) dirs (pop curdir ) )
                                        (recur (rest lines) (assoc-in dirs (conj curdir cd) {} ) (conj curdir cd) )
                                    )
                        (some? filesize) (recur (rest lines) (assoc-in dirs (conj curdir filename) filesize) curdir)
                        :else (recur (rest lines) dirs curdir)
                    )
                )
            )
        )
    )
)

( defn calcsizes [tree]
    ( reduce +
    ( map 
        (fn [size]
            (if (map? size)
                (calcsizes (vals size))
                (Integer/parseInt size)
            )
        )
    tree )    
    )
)

(defn walktree [tree asizes] 
    ( let [sizes asizes]
    (filter some? ( map 
        (fn [[k entry]]
            (if (map? entry) (do
;                    (let [parmsizes (walktree entry asizes)]
                        (conj (vec(walktree entry sizes)) (calcsizes (vals entry)))
;                    )
                )
            )            
        )
        tree
    )
    )
    )
)

(defn flatten-coll [coll]
    ( if (coll? coll)
        (mapcat flatten-coll coll)
        [coll]
    )
)

( defn solvepart1 [lines]
    (let [
        tree (parse-commands lines {} []) 
        ]
        (println (reduce + (filter (fn [x] (<= x 100000)) (flatten-coll (walktree tree []) ) )))
    )
)

( defn solvepart2 [lines]
    (let [
        tree (parse-commands lines {} []) 
        totalsize (calcsizes (vals (get tree "/") ) )
        minimumToDelete (- 30000000 (- 70000000 totalsize))
        ]
        (println minimumToDelete)
        (println (reduce min (filter (fn [x] (>= x minimumToDelete)) (flatten-coll (walktree tree []) ) )))
    )
)

( defn solve [opts]
    ( def lines (utils/readlines "./data/day7.txt" ) )
    ( solvepart1 ( seq lines ) )
    ( solvepart2 ( seq lines ) )
)

( defn solve-test [opts]
    ( def lines (utils/readlines "./data/day7test.txt") )
    ( solvepart1 (seq lines ) )
    ( solvepart2 (seq lines ) )
)
