(ns leiningen.annotations
  (:require (clojure [string :as str])
            [clojure.java.io :as io]))

(defn- project-files
  "Obtain a list of all Clojure files in the current project."
  [project-dir]
  (filter #(.endsWith (.getName %) ".clj")
          (file-seq (io/file project-dir))))

(defn- relative-path
  "Obtain relative path to file."
  [root file]
  (str/replace-first (.getAbsolutePath file) (str root java.io.File/separator) ""))

(defn- process-file
  "Looks for comment annotations in file."
  [root file]
  (doseq [[line-num line] (map-indexed vector (line-seq (io/reader file)))]
    (when (re-find #";+\s*(TODO|FIXME|OPTIMIZE)" line)
      (println (format "%s:%3d: %s" (relative-path root file) (inc line-num) line)))))

(defn annotations
  "Display comment annotations in the current project."
  [project & args]
  (doseq [file (project-files (:root project))]
    (process-file (:root project) file)))
