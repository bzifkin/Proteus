#!/bin/bash
find . -maxdepth 1 -type f -iname "*CaT.xml" -exec xmllint --format '{}' --output formatted-djvu-docs/'{}' \;
