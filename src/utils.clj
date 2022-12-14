( ns utils
    "Utility methods"
    (:require
        [clojure.string :as str]))

(defn readlines 
  "Returns a vector of the input. Each line a seperate entry in the vector. All strings of course."
  [filename] 
  (str/split-lines (slurp filename)))


