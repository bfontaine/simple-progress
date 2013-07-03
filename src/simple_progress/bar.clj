(ns simple-progress.bar)

(defn- print-bar
  [curr max*]

  (let [percent      (int (/ (*      curr  100) max*))
        prev-percent (int (/ (* (dec curr) 100) max*))]

    ;; don't re-print if the percentage didn't change (#1)
    (when (not= percent prev-percent)
      (let [width (int (* percent 71/100))]
        (print
          (format
            "\r[%3d%%] [%-71.71s]"
            percent
            (str (apply str (repeat width "=")) ">")))))))


(defn mk-progress-bar
  "Return a function which describe a progress bar"
  ([] (mk-progress-bar 100))
  ([max*]
    (let [curr (atom 0)
          inc* #(min (inc %) max*)
          dec* #(max (dec %) 0)]

      (fn bar
        ([] (bar :inc))
        ([action]

          (case action
            :inc   (swap! curr inc*)
            :dec   (swap! curr dec*)
            :reset (reset! curr 0))

          (print-bar @curr max*)
          (flush)
        
          @curr)))))
