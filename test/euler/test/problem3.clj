(ns euler.test.problem3
  (:use euler.problem3 :reload)
  (:use clojure.test))

(deftest sample-problem
  (let [sample-number 13195]
    (is (= (prime-factors sample-number)
           (seq [5 7 13 29])))
    (is (= (largest-prime-factor sample-number)
           29))))

(deftest real-problem
  (let [real-number 600851475143]
    (is (= (largest-prime-factor real-number)
           6857))))
