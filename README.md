# lein-annotations

A Leiningen plugin to display all comment annotations(`TODO`, `FIXME`,
`OPTIMIZE`, etc) in your Clojure project.

It's inspired by the similar `rake notes` functionality available in Ruby on Rails.

## Usage

Put `[lein-annotations "0.1.0"]` into the `:plugins` vector
of your `project.clj`.

Afterwards just run:

    $ lein annotations

You'll see a summary like:

```
test.clj:  8: ;; FIXME this blows up from time to time
test.clj: 11: ;; OPTIMIZE this is doing a lot of useless computations right now
```

You can also specify exactly what annotations to display:

    $ lein annotations FIXME REFACTOR HACK

## License

Copyright © 2013 Bozhidar Batsov

Distributed under the Eclipse Public License, the same as Clojure.
