(ns day08
  "Solutions for day 8 of the AOC2022"
  (:require [utils]))

(defn readline [line]
  (map (fn [c] (Character/digit c 10)) line))

(defn create-canopy-vec [lines]
  (vec (reduce concat (map readline lines))))

(defn checkbigger [item checkrange forest]
  ; trivial case first. if checkrange is empty (should not be) return false
  (if (= 0 (count checkrange))
    false
    (some #(= 1 %) (map (fn [n] 
                  (if (>= (get forest n) item)
                    1
                    0)) checkrange)
    )))

(defn checktree 
  "Check if tree is visible. Return 1 if so, 0 if not"
  [ind item forest ln]
  ; literal edge cases first
  (cond 
    (< ind ln) 1 ; upper row
    (> ind (* ln (- ln 1))) 1; lowest row
    (= 0 (mod ind ln)) 1 ; left column
    (= (- ln 1) (mod ind ln)) 1 ; right column
    ; we are sure it is not on edge
    (and 
     (and 
      (checkbigger item (range (* (int (/ ind ln)) ln) ind) forest) ; trees to the left
      (checkbigger item (range (+ ind 1) (* (+ (int (/ ind ln)) 1) ln)) forest)) ; trees to the right
     (and (checkbigger item (range (mod ind ln) ind ln) forest); everything above our index 
          (checkbigger item (range (+ ind ln) (* ln ln) ln) forest))) 0 ; everything below our index) ; trees to the right
    :else 1)
  )

(defn solvepart1 [lines ln]
  (let [forest (create-canopy-vec lines)]
    (println (reduce + (map-indexed (fn [ind item] (checktree ind item forest ln)) forest)))))

(defn calcscore [item treerange forest score]
  ; We assume treerange is not empty on first call)
  (if (= 0 (count treerange))
    score
    (if (>= (get forest (first treerange)) item)
      (+ score 1)
      (recur item (rest treerange) forest (+ score 1)))))

(defn scoretree [ind item forest ln]
  (cond 
    (< ind ln) 0 ; upper row
    (> ind (* ln (- ln 1))) 0; lowest row
    (= 0 (mod ind ln)) 0 ; left column
    (= (- ln 1) (mod ind ln)) 0 ; right column
    :else 
    (*
     (calcscore item (reverse (range (* (int (/ ind ln)) ln) ind)) forest 0);trees to the left
     (calcscore item (range (+ ind 1) (* (+ (int (/ ind ln)) 1) ln)) forest 0) ; trees to the right
     (calcscore item (reverse (range (mod ind ln) ind ln)) forest 0) ; trees above
     (calcscore item (range (+ ind ln) (* ln ln) ln) forest 0) ; trees below
     ) 
  ))

(defn solvepart2 [lines ln]
  (let [forest (create-canopy-vec lines)]
    (println (reduce max (map-indexed (fn [ind item] (scoretree ind item forest ln)) forest)))))

(defn solve-test [opts]
  (let [lines (utils/readlines "./data/day8test.txt")]
    (solvepart1 lines 5)
    (solvepart2 lines 5)))

(defn solve [opts]
  (let [lines (utils/readlines "./data/day8.txt")]
    (solvepart1 lines 99)
    (solvepart2 lines 99)))





