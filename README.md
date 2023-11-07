
# clj-build-handler

### Overview

The <strong>clj-build-handler</strong> is a simple build version handler for Clojure projects.

### deps.edn

```
{:deps {bithandshake/clj-build-handler {:git/url "https://github.com/bithandshake/clj-build-handler"
                                        :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/clj-build-handler/tree/release).

### Documentation

The <strong>clj-build-handler</strong> functional documentation is [available here](documentation/COVER.md).

### Changelog

You can track the changes of the <strong>clj-build-handler</strong> library [here](CHANGES.md).

# Usage

> Some parameters of the following functions and some further functions are not discussed in this file.
  To learn more about the available functionality, check out the [functional documentation](documentation/COVER.md)!

### Index

- [How to get the actual build version?](#how-to-get-the-actual-build-version)

- [How to update the actual build version?](#how-to-update-the-actual-build-version)

### How to get the actual build version?

The [`build-handler.api/get-actual-build-version`](documentation/clj/build-handler/API.md/#get-actual-build-version)
function returns the actual build version stored in the EDN file found on the given or the default filepath.
If the file does not contain a build version, it returns the 'INITIAL-BUILD-VERSION' value.

```
(get-actual-build-version)
=>
"0.4.2.0"
```

```
(get-actual-build-version {:filepath "my-build-version.edn"})
=>
"0.4.2.0"
```

### How to update the actual build version?

The [`build-handler.api/update-build-version!`](documentation/clj/build-handler/API.md/#update-build-version)
function updates the build version stored in the EDN file found on the given or the default filepath.

```
(update-build-version! :auto)
```

```
(update-build-version! "0.4.2.0")
```

```
(update-build-version! "0.4.2.0" {:filepath "my-build-version.edn"})
```
