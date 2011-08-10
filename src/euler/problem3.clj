(ns euler.problem3
  "Project Euler: Problem 3
http://projecteuler.net/index.php?section=problems&id=3

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143?"
  (:use (clojure.contrib (math :only [sqrt]))))

(defn primes
  "A lazy sequence of prime numbers"
  []
  (letfn [(seive [[next-prime & others]]
            (lazy-cat [next-prime]
                      (if (seq? others)
                        (seive (filter #(> (mod % next-prime) 0)
                                       (drop-while #(< % next-prime) others)))
                        )))]
    (seive (drop-while #(< % 2) (range)))))

(defn primes-less-than
  "Returns a lazy sequence of all prime numbers less than `n`."
  [n]
  (take-while (partial >= n)
              (primes)))

(defn prime-factors
  "Returns a lazy sequence of all the prime factors of `n`, from smallest
  to largest."
  [n]
  (if-let [smallest-prime-factor (first (filter #(= 0 (mod n %))
                                             (primes-less-than n)))]
    (lazy-cat [smallest-prime-factor]
              (prime-factors (/ n smallest-prime-factor)))))

(defn largest-prime-factor
  [n]
  (last (prime-factors n)))

;; ## Another Approach
;; This is the more conventional approach.  It doesn't use lazy sequences,
;; or computations of prime numbers at all, and so is much faster.

(defn largest-prime-factor* [n]
  (loop [num n factor 2]
    (if (= 1 num)
      factor
      (if (= (mod num factor) 0)
        (recur (/ num factor) factor)
        (recur num (inc factor))))))

;; ## Completely Factor Out The Twos
;; If you completely factor out all the twos first, you can go ahead and
;; skip testing all the even numbers.  This is faster still.

(defn largest-prime-factor** [n]
  (let [without-twos (loop [num n]
                       (if (= (mod num 2) 0)
                         (recur (/ num 2))
                         num))]
    (loop [num without-twos factor 3]
      (if (= 1 num)
        factor
        (if (= (mod num factor) 0)
          (recur (/ num factor) factor)
          (recur num (+ factor 2)))))))


;; ## Square Root Optimization
;; A number can only have one prime factor greater than its square root.

(defn largest-prime-factor***
  [n]
  (let [without-twos (loop [num n]
                       (if (= (mod num 2) 0)
                         (recur (/ num 2))
                         num))]
    (loop [num without-twos factor 3]
      (if (>= factor (sqrt num))
        (if (= 1 num)
          factor
          num)
        (if (= (mod num factor) 0)
          (recur (/ num factor) factor)
          (recur num (+ factor 2)))))))
