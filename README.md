# repl-watcher

[![Clojars Project](https://img.shields.io/clojars/v/featheredtoast/repl-watcher.svg)](https://clojars.org/featheredtoast/repl-watcher)

Send a command to the REPL on file changes inside a component.

## Usage

Include it in your system, and give it the paths to watch, and a command to run

```clojure
(:require [repl-watcher.core :refer [repl-watcher])
```

This example watches source files, and runs `reloaded.repl/reset` when changes are detected.

```clojure
(component/system-map
;; ...
:auto-reset (repl-watcher ["src/clj" "src/cljs"] "(reloaded.repl/reset)")
;; ...
```

When any files along the paths are changed, `(reloaded.repl/reset)` gets sent to the currently running repl.

## Rationale

I wanted to watch for source changes directly in my component, without spinning up a separate repl.

When evaluating how to run a `(reloaded.repl/reset)` programmatically, I was running into issues with resets.

The issues stem from `tools.namespace.repl`, which provide ways of hot-reloading. However, doing so programmatically leads to very strange issues, if not reset from a running repl. They work differently, which is a bit of a surprise. The issue comes down to this error:

`IllegalStateException("Can't change/establish root binding of: *ns* with set")`

See this blog for details:

http://justabloginthepark.com/2017/06/18/clojure-and-the-esoteric-mysteries-of-namespaces/

## TODO

* Currently finds the repl to connect to by `.nrepl-port` in the project root. May be other connection schemes in the future.

## Implementation details

Currently Hawk sometimes is a little loud when it comes to file watching, if a file is modified multiple times, eg in a VM. This library comes with a 2 second window where it ignores events, which is sufficient for my use cases.

## License

Copyright © 2017 Jeff Wong

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
