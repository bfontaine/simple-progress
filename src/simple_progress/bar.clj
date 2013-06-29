(ns simple-progress.bar)

(defn bar->string
  [curr max]

  ;; TODO
  (str curr "/" max))

(defn mk-progress-bar
  "Return a function which describe a progress bar"
  [& {:keys [max percents]}]
  (let [max  (if (or (< 0 max) (not max)) 100 max)
        percents (or percents (= max 100))
        curr (atom 0)]

    (fn bar [action & options]

      (let [noprint (options :noprint)
            opt     (first options)
            times   (or (and (number? opt) opt) 1)]

        (case action
          :inc (swap! curr (partial + count))
          :dec (swap! curr (partial - count))))

      (when (not noprint)
        (print (str "\r" (bar->string @curr max))))
      
      @curr)))
