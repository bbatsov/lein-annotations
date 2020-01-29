(ns leiningen.annotations
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def default-annotations ["TODO" "FIXME" "OPTIMIZE" "REFACTOR"])

(defn project-files
  "Obtain a list of all Clojure files in the current project."
  [project-dir]
  (filter #(re-matches #".+\.clj(|s|c)$" (.getName %))
          (file-seq (io/file project-dir))))

(defn relative-path
  "Obtain relative path to file."
  [root file]
  (str/replace-first (.getAbsolutePath file) (str root java.io.File/separator) ""))

(defn search-re [targets]
  (re-pattern (format ";+\\s*(%s)" (str/join "|" targets))))

(defn display-annotation
  [path line-num line]
  (println (format "%s:%3d: %s" path (inc line-num) line)))

(defn process-file
  "Looks for comment annotations in file."
  [root file targets]
  (doseq [[line-num line] (map-indexed vector (line-seq (io/reader file)))]
    (when (re-find (search-re targets) line)
      (display-annotation (relative-path root file) (inc line-num) line))))

(defn target-annotations [target]
  (or target
      default-annotations))

(defn annotations
  "Display comment annotations in the current project."
  [project & args]
  (let [project-root (:root project)
        targets (target-annotations args)]
    (doseq [file (project-files project-root)]
      (process-file project-root file targets))))
