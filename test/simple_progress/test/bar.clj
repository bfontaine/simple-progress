(ns simple-progress.test.bar
  (:require [simple-progress.bar :as b])
  (:use     [clojure.test]))

(deftest test-mk-progress-bar

  ;; mk-progress-bar should return a function
  (is (fn? (b/mk-progress-bar)))
  (is (fn? (b/mk-progress-bar 42)))

  (let [bar100 (b/mk-progress-bar)
       bar3    (b/mk-progress-bar 3)]

    ;; :inc & :dec should (inc|dec)rement the bar value
    (is (= 1 (bar100 :inc)))
    (is (= 0 (bar100 :dec)))

    ;; the bar value should not go below 0
    (is (= 1 (bar3 :inc)))
    (is (= 0 (bar3 :dec)))
    (is (= 0 (bar3 :dec)))

    ;; nor above its maximal value
    (is (= 1 (bar3 :inc)))
    (is (= 2 (bar3 :inc)))
    (is (= 3 (bar3 :inc)))
    (is (= 3 (bar3 :inc)))

    ;; it should not affect other bars
    (is (= 1 (bar100 :inc)))
  
    ;; it should be able to be called without argument
    (is (= 2 (bar100)))

    ;; it should reset its value with :reset
    (is (= 0 (bar100 :reset)))))

