(ns simple-progress.bar)

(defn- bar->string
  [curr max*]

  (let [percent (min (int (/ (* curr 100) max*)) 100)
        width (int (* percent 71/100))]

    (format
      "[%3d%%] [%-71.71s]"
      percent
      (str (apply str (repeat width "=")) ">"))))


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

          (print (str "\r" (bar->string @curr max*)))
        
          @curr)))))
