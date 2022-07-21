#!/usr/bin/env bash

set -exuo pipefail

log() {
    echo >&2 "$*"
}

# ENTRYPOINT

if [[ -z $SDKMAN_DIR ]]; then
    # shellcheck disable=SC2016
    log 'Error: $SDKMAN_DIR is not set'
    exit 1
fi

# use java 8
set +u
source "${SDKMAN_DIR}/bin/sdkman-init.sh"
sdk use java "$(find "${SDKMAN_DIR}/candidates/java/" -maxdepth 1 -type d -regex ".*\/java\/8.*" -exec basename {} \; | sort -n -r | head -n 1)"
set -u

# clean
rm -rf out
gradle clean

# dir layout
mkdir -p out
mkdir -p static

# build
gradle wasm

# bundle
cp build/distributions/* out
cp static/* out

# replace token
artifact_name="$(find "build/distributions/" -type f -regex ".*\.wasm\.js" -exec basename {} \; | sed 's/\.wasm\.js//')"
sed -i "s/@REPLACE@/${artifact_name}/" "out/index.html"

# serve
log "Serving on http://localhost:8080"
docker run \
    --name "caddy-jwasm" \
    --interactive \
    --tty \
    --rm \
    --publish "8080:80" \
    --volume "${PWD}/out/:/usr/share/caddy/":ro \
    caddy:2-alpine
