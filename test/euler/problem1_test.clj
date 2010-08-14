(ns euler.problem1-test
  (:use [euler.problem1] :reload-all)
  (:use [clojure.test]))

(deftest brute-force
  (is (= (problem1-brute 10)
         23))
  (is (= (problem1-brute 1000)
         233168)))

