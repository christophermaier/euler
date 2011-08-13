(ns euler.test.problem4
  (:use euler.problem4 :reload)
  (:use clojure.test))

(deftest test-is-palindromic?
  (are [num result] (= (is-palindromic? num)
                       result)
       1234 false
       2112 true
       1 true
       121 true
       1111111111111111111 true
       123123123321321321 true))

(deftest naive-solution
  (is (= (naive 2)
         9009))
  (is (= (naive 3)
         906609)))
