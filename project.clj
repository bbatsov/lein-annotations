(defproject lein-annotations "0.2.0"
  :description "Displays all comment annotations in the project."
  :url "http://github.com/bbatsov/lein-annotations"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_username
                                    :password :env/clojars_password
                                    :sign-releases false}]]

  :eval-in-leiningen true)
