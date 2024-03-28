
(ns iso.build-handler.utils
    (:require [fruits.uri.api :as uri]
              [fruits.string.api :as string]
              [io.api :as io]))

;; ---------------------------------------------------------------------------
;; ---------------------------------------------------------------------------

(defn filepath<-build-version
  ; @description
  ; Appends the given build version to the given filepath.
  ;
  ; @param (string) filepath
  ; @param (string) build-version
  ;
  ; @usage
  ; (filepath<-build-version "/my-directory/my-file.ext" "0.0.1")
  ; =>
  ; "/my-directory/my-file-0-0-1.ext"
  ;
  ; @return (string)
  [filepath build-version]
  (let [parent-path   (io/filepath->parent-path filepath)
        basename      (io/filepath->basename    filepath)
        extension     (io/filepath->extension   filepath)
        build-version (string/replace-part build-version "." "-")]
       (if parent-path (str parent-path "/" basename "-" build-version (if extension ".") extension)
                       (str                 basename "-" build-version (if extension ".") extension))))

(defn uri<-build-version
  ; @description
  ; Appends the given build version as a query string to the given URI.
  ;
  ; @info
  ; Using the actual build version as a query parameter in file URIs, makes the browser's cache handler update
  ; the cached version of a file if the version changed in the URI.
  ;
  ; @param (string) uri
  ; @param (string) build-version
  ;
  ; @usage
  ; (uri<-build-version "/my-style.css" "0.0.1")
  ; =>
  ; "/my-style.css?v=0.0.1"
  ;
  ; @usage
  ; (uri<-build-version "/my-style.css?my-param" "0.0.1")
  ; =>
  ; "/my-style.css?my-param&v=0.0.1"
  ;
  ; @return (string)
  [uri build-version]
  (uri/use-url-query-string uri (str "v=" build-version)))
