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

When any files along the paths are changed, `(reloaded.repl/reset)` gets sent to the currently running repl.

## TODO

* Currently finds the repl to connect to by `.nrepl-port` in the project root. May be other connection schemes in the future.
* Redo namespaces? Align under a better package structure.
* Perhaps abstract the component so any command can be sent on change, not just `reloaded-repl.`

## License

Copyright © 2017 Jeff Wong

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
