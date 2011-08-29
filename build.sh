#!/bin/sh

# Install modules, running tests but not integration-tests

# Environments: env-dev/env-test/env-stage/env-prod (default to env-dev)
# Clustering: cluster (default to non-clustered)
# AOP: maven.aop.skip=true/false (default to true)
# Tests: maven.test.skip=true/false (default to true)
# Integration Tests: maven.itest.skip=true/false (default to true)

# Update eclipse with maven repo reference
mvn -Declipse.workspace=/home/ggear/dev/workspace eclipse:add-maven-repo

# Install deps to local maven repo
cd clusterinfo-lib
mvn install

# Build project
cd ../clusterinfo-build
mvn -P env-dev -Dmaven.test.skip=false -Dmaven.itest.skip=true -Dmaven.ltest.skip=true -Dmaven.ltest-long.skip=true -Dmaven.ltest-short.skip=true eclipse:clean eclipse:m2eclipse clean install

# Ensure versions numbers of child modules are kept in sync
mvn versions:update-child-modules
