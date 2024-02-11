
(ns build-handler.api
    (:require [build-handler.config       :as config]
              [build-handler.env          :as env]
              [build-handler.side-effects :as side-effects]
              [build-handler.utils        :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial How to get the actual build version?
;
; - The [get-actual-build-version](#get-actual-build-version) function returns the actual build version
;   stored in the EDN file at the given or at the default filepath.
; - If the file does not contain a build version, it returns the [INITIAL-BUILD-VERSION](#initial-build-version) value.
;
; @usage
; (get-actual-build-version)
; =>
; "0.4.2.0"
;
; @usage
; (get-actual-build-version {:filepath "my-build-version.edn"})
; =>
; "0.4.2.0"



; @tutorial How to update the actual build version?
;
; The [update-build-version!](#update-build-version_) function updates the build version stored in the EDN file
; at the given or at the default filepath.
;
; @usage
; (update-build-version! :auto)
;
; @usage
; (update-build-version! "0.4.2.0")
;
; @usage
; (update-build-version! "0.4.2.0" {:filepath "my-build-version.edn"})

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (build-handler.config/*)
(def DEFAULT-BUILD-VERSION-FILEPATH config/DEFAULT-BUILD-VERSION-FILEPATH)
(def INITIAL-BUILD-VERSION          config/INITIAL-BUILD-VERSION)

; @redirect (build-handler.env/*)
(def get-actual-build-version  env/get-actual-build-version)
(def uri<-actual-build-version env/uri<-actual-build-version)

; @redirect (build-handler.side-effects/*)
(def update-build-version! side-effects/update-build-version!)

; @redirect (build-handler.utils/*)
(def uri<-build-version utils/uri<-build-version)
