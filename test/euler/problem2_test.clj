(ns euler.problem2-test
  (:use [euler.problem2] :reload-all)
  (:use [clojure.test]))

(defmacro solve-with [f]
  `(is (= (~f 4000000)
          4613732)))

(deftest solution
  (solve-with problem2)
  (solve-with problem2-only-evens)
  (solve-with problem2-only-evens-by-sequence))

