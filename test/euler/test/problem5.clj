(ns euler.test.problem5
  (:use euler.problem5 :reload)
  (:use clojure.test))

(deftest test-result
  (are [algorithm upper-bound minimum-product]
       (= (algorithm upper-bound)
          minimum-product)
       smallest-common-product 10 2520
       smallest-common-product 20 232792560

       smallest-common-product* 10 2520
       smallest-common-product* 20 232792560))
