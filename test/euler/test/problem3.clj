(ns euler.test.problem3
  (:use euler.problem3 :reload)
  (:use clojure.test))

(let [sample-number 13195
      sample-max 29

      real-number 600851475143
      real-max 6857]

  (deftest sample-problem
    (is (= (prime-factors sample-number)
           (seq [5 7 13 29])))
    (is (= (largest-prime-factor sample-number)
           sample-max)))

  (deftest real-problem
    (is (= (largest-prime-factor real-number)
           real-max)))

  (deftest alternatives
    (are [algorithm] (= (algorithm sample-number) sample-max)
         largest-prime-factor*
         largest-prime-factor**
         largest-prime-factor***)
    (are [algorithm] (= (algorithm real-number) real-max)
         largest-prime-factor*
         largest-prime-factor**
         largest-prime-factor***)))
