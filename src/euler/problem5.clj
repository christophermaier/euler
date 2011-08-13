(ns euler.problem5
  "2520 is the smallest number that can be divided by each of the
  numbers from 1 to 10 without any remainder.

  What is the smallest positive number that is evenly divisible
  by all of the numbers from 1 to 20?"
  (:use (euler (problem3 :only [prime-factors]))
        (clojure.contrib (math :only [lcm]))))

(defn factor-map
  "Create a factorization map for the number `n`.  Each key will be a
  prime factor, and each value will be the number of times the factor
  appears in the prime factorization of `n`.

  Thus, `(factor-map 20)` would yield `{2 2, 5 1}`."
  [n]
  (frequencies (prime-factors n)))

(defn smallest-common-product
  "Return the smallest positive number that can be divided evenly
  by all positive integers less than or equal to `n`."
  [n]
  (int (reduce * (map (fn [[factor times]]
                        (Math/pow factor times))
                      (reduce #(merge-with max %1 %2)
                              (map factor-map
                                   (range 2 (inc n))))))))

;; ## Another, Faster Way To Do It
;;
;; We're really just finding the LCM of all these numbers, so just
;; use `reduce` and be done with it.
(defn smallest-common-product* [n]
  (reduce lcm (range 2 (inc n))))
