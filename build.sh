#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail

dir_root="$(dirname "$(readlink -f -- "$0")")"
dir_out="${dir_root}/out"
dir_static="${dir_root}/static"

# sdkman use java 8
set +u
sdkman_dir="${SDKMAN_DIR:-"${HOME}/.sdkman"}"
if [[ -n $sdkman_dir ]]; then
    source "${sdkman_dir}/bin/sdkman-init.sh"
    sdk use java "$(find "${sdkman_dir}/candidates/java/" -maxdepth 1 -type d -regex ".*\/java\/8.*" -exec basename {} \; | sort -n -r | head -n 1)"
else
    echo >&2 "sdkman not found, make sure to use java 8!"
fi
set -u

# dir layout
mkdir -p "${dir_out}" "${dir_static}"

# clean
rm -rf "${dir_out:?}"/*
gradle clean

# build
gradle wasm

# bundle
cp build/distributions/*.wasm "${dir_out}/app.wasm"
cp build/distributions/*.js "${dir_out}/imports.js"
for asset in "${dir_static}"/*; do
    cp "${asset}" "${dir_out}"
done
