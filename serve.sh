#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail
set -o xtrace

dir_root="$(dirname "$(readlink -f -- "$0")")"
dir_out="${dir_root}/out"

container_name="caddy-$(basename "${PWD}" | inline-detox)"
docker rm -f "${container_name}"
echo >&2 "Serving ${dir_out} at http://localhost:8080"
docker run \
    --name "${container_name}" \
    --interactive \
    --tty \
    --rm \
    --publish "8080:80" \
    --volume "${dir_out}:/usr/share/caddy/":ro \
    caddy:2-alpine
