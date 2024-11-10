#!/bin/bash

target_user="$1"
bindir="/usr/bin"

if [ -z "$target_user" ]; then
  echo "Please provide the username for which the tool is being installed as the first argument."
  exit 1
fi

if [ "$(whoami)" != "$target_user" ]; then
  echo "This script must be run by $target_user."
  exit 1
fi

echo "Creating dir"
mkdir -p ~/.sfo

if [ $? -ne 0 ]; then
  echo "Could not create directory on home"
  exit 1
fi

echo "Copying jar"
cp sfo-1.0.jar ~/.sfo

if [ $? -ne 0 ]; then
  echo "Could not copy jar on ~/.sfo"
  exit 1
fi

echo "Copying sfo -> ${bindir}"
sudo cp sfo "${bindir}/sfo"

if [ $? -ne 0 ]; then
  echo "Could not copy sfo on ${bindir}"
  exit 1
fi

echo "Managing permissions"
sudo chmod 755 "${bindir}/sfo"

echo "Done" && exit 0