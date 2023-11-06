
### build-handler.api

Functional documentation of the build-handler.api Clojure namespace

---

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > build-handler.api

### Index

- [get-current-build-version](#get-current-build-version)

- [update-build-version!](#update-build-version)

---

### get-current-build-version

```
@description
- Returns the actual build version stored in the EDN file found on the given or the default filepath.
- If the file does not contain a build version, it returns the 'INITIAL-BUILD-VERSION' value.
```

```
@param (map)(opt) options
{:filepath (string)(opt)
  Default: build-handler/DEFAULT-BUILD-VERSION-FILEPATH}
```

```
@usage
(get-current-build-version)
```

```
@usage
(get-current-build-version {:filepath "my-build-version.edn"})
```

```
@example
(get-current-build-version)
=>
"0.4.2.0"
```

```
@return (string)
```

<details>
<summary>Source code</summary>

```
(defn get-current-build-version
  ([]
   (get-current-build-version {}))

  ([{:keys [filepath] :or {filepath config/DEFAULT-BUILD-VERSION-FILEPATH}}]
   (-> filepath io/read-edn-file (get :build-version config/INITIAL-BUILD-VERSION))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [build-handler.api :refer [get-current-build-version]]))

(build-handler.api/get-current-build-version ...)
(get-current-build-version                   ...)
```

</details>

---

### update-build-version!

```
@description
- Updates the build version stored in the EDN file found on the given or the default filepath.
- If the given version is ':auto', it updates the current build version in the file by increasing it.
- If the given version is ':auto', and the file does not contain an increasable build version,
  it stores the 'INITIAL-BUILD-VERSION' value in the file.
- If the given version is a specific value, it stores the value in the file.
- An increasable build version is a string that only contain digits separated by dots.
```

```
@param (keyword or string) build-version
:auto
@param (map)(opt) options
{:filepath (string)(opt)
  Default: build-handler/DEFAULT-BUILD-VERSION-FILEPATH}
```

```
@usage
(update-build-version! :auto)
```

```
@usage
(update-build-version! "0.4.2.0")
```

```
@usage
(update-build-version! "0.4.2.0" {:filepath "my-build-version.edn"})
```

```
@example
(update-build-version! :auto)
=>
"0.4.2.0"
```

```
@example
(update-build-version! "0.4.2.0")
=>
"0.4.2.0"
```

```
@return (string)
```

<details>
<summary>Source code</summary>

```
(defn update-build-version!
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
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [build-handler.api :refer [update-build-version!]]))

(build-handler.api/update-build-version! ...)
(update-build-version!                   ...)
```

</details>

---

<sub>This documentation is generated with the [clj-docs-generator](https://github.com/bithandshake/clj-docs-generator) engine.</sub>

