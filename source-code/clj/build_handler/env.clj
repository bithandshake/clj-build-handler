
(ns build-handler.env
    (:require [build-handler.config :as config]
              [build-handler.utils  :as utils]
              [io.api               :as io]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-actual-build-version
  ; @description
  ; - Returns the actual build version stored in the EDN file found on the given or the default filepath.
  ; - If the file does not contain a build version, it returns the 'INITIAL-BUILD-VERSION' value.
  ;
  ; @param (map)(opt) options
  ; {:filepath (string)(opt)
  ;   Default: build-handler/DEFAULT-BUILD-VERSION-FILEPATH}
  ;
  ; @usage
  ; (get-actual-build-version)
  ;
  ; @usage
  ; (get-actual-build-version {:filepath "my-build-version.edn"})
  ;
  ; @example
  ; (get-actual-build-version)
  ; =>
  ; "0.4.2.0"
  ;
  ; @return (string)
  ([]
   (get-actual-build-version {}))

  ([{:keys [filepath] :or {filepath config/DEFAULT-BUILD-VERSION-FILEPATH}}]
   (-> filepath io/read-edn-file (get :build-version config/INITIAL-BUILD-VERSION))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn uri<-actual-build-version
  ; @description
  ; - Appends the actual build version (stored in the EDN file found on the given or the default filepath)
  ;   to the given URI as a query string.
  ; - Using the actual build version as a query parameter could make the browser's cache handler
  ;   updating the cached version of a file when the version changes in the URI.
  ;
  ; @param (string) uri
  ; @param (map)(opt) options
  ; {:filepath (string)(opt)
  ;   Default: build-handler/DEFAULT-BUILD-VERSION-FILEPATH}
  ;
  ; @usage
  ; (uri<-actual-build-version "/my-style.css")
  ;
  ; @example
  ; (uri<-actual-build-version "/my-style.css")
  ; =>
  ; "/my-style.css?v=0.0.1"
  ;
  ; @example
  ; (uri<-actual-build-version "/my-style.css?my-param")
  ; =>
  ; "/my-style.css?my-param&v=0.0.1"
  ;
  ; @return (string)
  ([uri]
   (uri<-actual-build-version uri {}))

  ([uri options]
   (let [build-version (get-actual-build-version options)]
        (utils/uri<-build-version uri build-version))))
