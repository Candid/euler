(ns euler.problems
  (:use euler.core))

;; Problem 1
;; 05 October 2001

;; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
;; The sum of these multiples is 23.

;; Find the sum of all the multiples of 3 or 5 below 1000.

(sum (for [i (range 1 1000) :when (or (divides? 3 i) (divides? 5 i))] i))


;; Problem 2
;; 19 October 2001

;; Each new term in the Fibonacci sequence is generated by adding the previous two terms.
;; By starting with 1 and 2, the first 10 terms will be:

;; 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

;; By considering the terms in the Fibonacci sequence whose values do not exceed four million,
;; find the sum of the even-valued terms.

(sum (for [i fibs :while (<= i 4000000) :when (even? i)] i))


;; Problem 3
;; 02 November 2001

;; The prime factors of 13195 are 5, 7, 13 and 29.

;; What is the largest prime factor of the number 600851475143 ?

(last (factors 600851475143))


;; Problem 4
;; 16 November 2001

;; A palindromic number reads the same both ways. The largest palindrome made from
;; the product of two 2-digit numbers is 9009 = 91  99.

;; Find the largest palindrome made from the product of two 3-digit numbers.

(let [r (range 100 1000)]
  (reduce max (for [i r j r :let [p (* i j)] :when (palindrome? p)] p)))


;; Problem 5
;; 30 November 2001

;; 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

;; What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

(reduce * (for [[k v] (apply (partial merge-with max)
                             (for [i (range 2 21)] (factorize i)))]
                (pow k v)))


;; Problem 6
;; The sum of the squares of the first ten natural numbers is,

;; 1^2 + 2^2 + ... + 10^2 = 385
;; The square of the sum of the first ten natural numbers is,

;; (1 + 2 + ... + 10)^2 = 55^2 = 3025
;; Hence the difference between the sum of the squares of the first ten natural numbers
;; and the square of the sum is 3025 - 385 = 2640.

;; Find the difference between the sum of the squares of the first one hundred natural
;; numbers and the square of the sum.

(- (sqr (sum (range 1 101))) (sum (map sqr (range 1 101))))


;; Problem 7
;; By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

;; What is the 10 001st prime number?

(last (take 10001 (primes)))

;; Problem 8
;; Find the greatest product of five consecutive digits in the 1000-digit number.

;; 73167176531330624919225119674426574742355349194934
;; 96983520312774506326239578318016984801869478851843
;; 85861560789112949495459501737958331952853208805511
;; 12540698747158523863050715693290963295227443043557
;; 66896648950445244523161731856403098711121722383113
;; 62229893423380308135336276614282806444486645238749
;; 30358907296290491560440772390713810515859307960866
;; 70172427121883998797908792274921901699720888093776
;; 65727333001053367881220235421809751254540594752243
;; 52584907711670556013604839586446706324415722155397
;; 53697817977846174064955149290862569321978468622482
;; 83972241375657056057490261407972968652414535100474
;; 82166370484403199890008895243450658541227588666881
;; 16427171479924442928230863465674813919123162824586
;; 17866458359124566529476545682848912883142607690042
;; 24219022671055626321111109370544217506941658960408
;; 07198403850962455444362981230987879927244284909188
;; 84580156166097919133875499200524063689912560717606
;; 05886116467109405077541002256983155200055935729725
;; 71636269561882670428252483600823257530420752963450

(let [input (.replace "73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450" "\n" "")
      to-digit (fn [c] (- (int c) 48))
      numbers (into [] (map to-digit input))
      products (for [i (range 995)] (reduce * (subvec numbers i (+ i 5))))]
  (reduce max products))

