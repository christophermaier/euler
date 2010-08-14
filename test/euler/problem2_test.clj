(ns euler.problem2-test
  (:use [euler.problem2] :reload-all)
  (:use [clojure.test]))

(defmacro solve-with [f params solution]
  `(is (= (apply ~f ~params) ~solution)))

(deftest solution
  (let [params [4000000]
        solution 4613732]
    (solve-with problem2 params solution)
    (solve-with problem2-no-evens params solution)))

