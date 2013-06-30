# simple-progress

[![Build Status](https://travis-ci.org/bfontaine/simple-progress.png?branch=master)](https://travis-ci.org/bfontaine/simple-progress)

`simple-progress` is a Clojure library for simple progress bars display. It’s
focused on simplicity, if you want to display a progress bar for operations on
files, use [`progress`](https://github.com/tobias/progress) instead.

## Usage

```clj
(let [b (mk-progress-bar)]
  (doseq [_ (range 100)]
    (do-something)
    (b))) ; display the progress bar
```

The `simple-progress.bar` module have one public function, `mk-progress-bar`. It
takes one optional argument, which is the maximal value of the bar (the default
is 100). It returns a function which describes the bar. You can then call it to
increment its value and display the bar, with an optional argument:

- `:inc`: increment the bar value (default)
- `:dec`: decrement the bar value
- `:reset`: reset the bar value to `0`

The bar is displayed after each call, and the current value is returned.


## Install

Add the following dependency to your project:

```
[simple-progress "0.1.0"]
```

Then require/use the `simple-progress.bar` module.


## Test

```
lein test
```


## License

Copyright © 2013 Baptiste Fontaine

Distributed under the Eclipse Public License, the same as Clojure.
