# auto-reloaded-repl

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.featheredtoast/auto-reloaded-repl.svg)](https://clojars.org/org.clojars.featheredtoast/auto-reloaded-repl)

A simple component to call `(reloaded.repl/reset)` on file changes.

## Usage

This component is to be used at development hand in hand with reloaded.repl.

Include it in your system, and give it the paths to watch.

```clojure
(:require [auto-reloaded-repl.core :refer [reloaded-repl-reset-component])
```

```clojure
(component/system-map
;; ...
:auto-reset (reloaded-repl-reset-component ["src/clj" "src/cljs"])
;; ...
```

## License

Copyright © 2017 Jeff Wong

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
