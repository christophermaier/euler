(ns euler.problem2-test
  (:use [euler.problem2] :reload-all)
  (:use [clojure.test]))

(deftest solution
  (is (= (problem2 4000000))
      4613732))
