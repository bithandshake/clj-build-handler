
(ns build-handler.api
    (:require [build-handler.config       :as config]
              [build-handler.env          :as env]
              [build-handler.side-effects :as side-effects]
              [build-handler.utils        :as utils]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (build-handler.config)
(def DEFAULT-BUILD-VERSION-FILEPATH config/DEFAULT-BUILD-VERSION-FILEPATH)
(def INITIAL-BUILD-VERSION          config/INITIAL-BUILD-VERSION)

; @redirect (build-handler.env)
(def get-actual-build-version  env/get-actual-build-version)
(def uri<-actual-build-version env/uri<-actual-build-version)

; @redirect (build-handler.side-effects)
(def update-build-version! side-effects/update-build-version!)

; @redirect (build-handler.utils)
(def uri<-build-version utils/uri<-build-version)
