(ns euler.problem3
  "Project Euler: Problem 3
http://projecteuler.net/index.php?section=problems&id=3

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143?")

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
              (prime-factors-of (/ n smallest-prime-factor)))))

(defn largest-prime-factor
  [n]
  (last (prime-factors n)))
