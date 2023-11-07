
(ns build-handler.side-effects
    (:require [build-handler.config :as config]
              [format.api           :as format]
              [io.api               :as io]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn update-build-version!
  ; @description
  ; - Updates the build version stored in the EDN file found on the given or the default filepath.
  ; - If the given version is a specific value, it stores the value in the file.
  ; - If the given version is ':auto', it updates the current build version in the file by increasing it.
  ; - If the given version is ':auto', and the file does not contain an increasable build version,
  ;   it stores the 'INITIAL-BUILD-VERSION' value in the file.
  ; - An increasable build version is a string that only contain digits separated by dots.
  ; - The EDN file must contain a map with a key ':build-version'.
  ;
  ; @param (keyword or string) build-version
  ; :auto
  ; @param (map)(opt) options
  ; {:filepath (string)(opt)
  ;   Default: build-handler/DEFAULT-BUILD-VERSION-FILEPATH}
  ;
  ; @usage
  ; (update-build-version! :auto)
  ;
  ; @usage
  ; (update-build-version! "0.4.2.0")
  ;
  ; @usage
  ; (update-build-version! "0.4.2.0" {:filepath "my-build-version.edn"})
  ;
  ; @example
  ; (update-build-version! :auto)
  ; =>
  ; "0.4.2.0"
  ;
  ; @example
  ; (update-build-version! "0.4.2.0")
  ; =>
  ; "0.4.2.0"
  ;
  ; @return (string)
  ([build-version]
   (update-build-version! build-version {}))

  ([build-version {:keys [filepath] :or {filepath config/DEFAULT-BUILD-VERSION-FILEPATH}}]
   (letfn [(get-auto-version  [] (if-let [{:keys [build-version]} (io/read-edn-file filepath)]
                                         (format/inc-version build-version)
                                         (-> config/INITIAL-BUILD-VERSION)))
           (get-build-version [] (cond (string? build-version) (-> build-version)
                                       (= :auto build-version) (get-auto-version)))]
          (let [build-version (get-build-version)]
               (io/write-edn-file! filepath {:build-version build-version} {:create? true})
               (-> build-version)))))
