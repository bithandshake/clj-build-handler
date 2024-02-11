
(ns build-handler.config)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @description
; The default build version filepath ('environment/build-version.edn') is used when no filepath is provided.
;
; @constant (string)
(def DEFAULT-BUILD-VERSION-FILEPATH "environment/build-version.edn")

; @description
; The initial build version ('0.0.1') is used when no version is found in the build version file.
;
; @constant (string)
(def INITIAL-BUILD-VERSION "0.0.1")
