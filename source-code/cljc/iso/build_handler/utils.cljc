
(ns iso.build-handler.utils
    (:require [fruits.uri.api :as uri]))

;; ---------------------------------------------------------------------------
;; ---------------------------------------------------------------------------

(defn uri<-build-version
  ; @description
  ; Appends the given build version to the given URI as a query string.
  ;
  ; @info
  ; Using the actual build version as a query parameter makes the browser's cache
  ; handler updating the cached version of a file if the version changed in the URI.
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
