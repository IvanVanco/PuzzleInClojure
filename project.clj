(defproject Puzzle "0.1.0-SNAPSHOT"
  :description "Simulation of the Serbian game called Slagalica"
  :url "https://github.com/IvanVanco/PuzzleInClojure"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :repl-options {:init-ns puzzle.core}
  :codeina {:sources ["src/com"]
            :reader :clojure
            :target "docs/api"
            :src-uri "http://github.com/IvanVanco/PuzzleInClojure/docs/api/"
            }
  )
