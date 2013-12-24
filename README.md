Artifactory Cleaner
===================
[![Build Status](https://travis-ci.org/pvanassen/artifactory-cleaner.png?branch=master)](https://travis-ci.org/pvanassen/artifactory-cleaner)
[![Coverage Status](https://coveralls.io/repos/pvanassen/artifactory-cleaner/badge.png)](https://coveralls.io/r/pvanassen/artifactory-cleaner)

A small utility which uses the Artifactory REST api to remove or archive old artifacts, based on their deploy date, last access date or wether there is a released version of the artifact

How it works
============
All snapshots older than (2 weeks) are looked up. 
If:
- There is a released vesion, or
- the artifact was not downloaded for (2 weeks)
The snapshot is removed

All releases older than (2 months) are looked up. 
If:
- A release has not been downloaded for 2 months
The release is archived in the specified archive reposiory. 
This is done through the 'move' command, if available or else a download, deploy and remove is done. 
