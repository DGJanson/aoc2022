( ns utils
    "Utility methods"
    (:require
        [clojure.string :as str]))

( defn readlines [filename]
    (str/split-lines (slurp filename)))



