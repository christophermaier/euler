(ns euler.problem4
  "A palindromic number reads the same both ways. The largest
  palindrome made from the product of two 2-digit numbers is 9009 = 91 x 99.

  Find the largest palindrome made from the product of two 3-digit numbers.")

(defn is-palindromic?
  "Returns `true` if the digits of the number `n` can be read the same forwards
  and backwards.

  For example, `1234` is not palindromic, but `2112` is."
  [n]
  (let [as-string (vec (seq (str n)))]
    (= as-string (rseq as-string))))

(defn naive
  "Return the largest palindromic number that is a product of two numbers
  with `num` digits.

  This is pretty much brute force; simply multiplying all the distinct pairs
  of `num` digit numbers, removing those that are not palindromic, and returning
  the largest one."
  [num]
  (let [lower (apply * (repeat (dec num) 10))
        upper (apply * (repeat num 10))]
    (apply max (filter is-palindromic?
                       (for [m (range lower upper)
                             n (range m upper)]
                         (* m n))))))

;; ## Optimizations
;;
;; ### Do it in Reverse
;;
;; Count downward each time to find a larger number earlier.  Also,
;; keep track of the largest number found so far to short circuit the computation
;; sooner.
(defn backwards
  [num]
  (let [upper (dec (apply * (repeat num 10)))
        lower (dec (apply * (repeat (dec num) 10)))]
    (with-local-vars [largest-so-far 0
                      upper-bound upper
                      lower-bound lower]
      (loop [m1 @upper-bound m2 @upper-bound]
        (if (< m1 @lower-bound)
          ;; We've found it!
          @largest-so-far
          (let [prod (* m1 m2)
                palindrome? (is-palindromic? prod)
                bigger? (> prod @largest-so-far)]
            (if (and bigger? palindrome?)
              (var-set largest-so-far prod))
            (if (and bigger? (not palindrome?))
              ;; We could still find a bigger palindrome along this path... keep searching
              (recur m1 (dec m2))
              (do
                ;; Whether it's bigger or smaller than the biggest we've seen so far,
                ;; reducing m2 further isn't going to get us a bigger number.
                ;; In fact, the current value of m2 defines a new lower bound; if m1
                ;; ever gets that small, we know we can stop searching.  In this way,
                ;; each palindrome found allows us to further tighten the search space.
                (var-set lower-bound m2)
                ;; Similarly, we can decrement our upper bound, as we will have already
                ;; searched all products with larger multiplicands.
                (var-set upper-bound (dec @upper-bound))
                ;; Continue the search!
                (recur (dec m1) @upper-bound)))))))))
