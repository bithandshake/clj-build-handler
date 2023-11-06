
### iso.build-handler

Functional documentation of the iso.build-handler isomorphic namespace

---

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > iso.build-handler

### Index

- [uri<-build-version](#uri-build-version)

---

### uri<-build-version

```
@description
- Appends the given build version to the given URI as a query-string.
- Using the actual build version as a query parameter could make the browser's cache handler
  updating the cached version of a file when the version changes in the URI.
```

```
@param (string) uri
@param (string) build-version
```

```
@usage
(uri<-build-version "/my-style.css" "0.0.1")
```

```
@example
(uri<-build-version "/my-style.css" "0.0.1")
=>
"/my-style.css?v=0.0.1"
```

```
@example
(uri<-build-version "/my-style.css?my-param" "0.0.1")
=>
"/my-style.css?my-param&v=0.0.1"
```

```
@return (string)
```

<details>
<summary>Source code</summary>

```
(defn uri<-build-version
  [uri]
  (uri/use-url-query-string uri (str "v=" build-version)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [iso.build-handler :refer [uri<-build-version]]))

(iso.build-handler/uri<-build-version ...)
(uri<-build-version                   ...)
```

</details>

---

<sub>This documentation is generated with the [clj-docs-generator](https://github.com/bithandshake/clj-docs-generator) engine.</sub>

