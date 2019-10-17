#!/usr/bin/env bash

# more bash-friendly output for jq
JQ="jq --raw-output --exit-status"
CLUSTER=$1
SERVICE=$2

deploy_cluster() {

    aws ecs update-service --cluster $CLUSTER --service $SERVICE --force-new-deployment

    echo "Waiting.............."
    sleep 20
    for attempt in {1..60}; do
        if stale=$(aws ecs describe-services --cluster $CLUSTER --services $SERVICE | \
                       $JQ ".services[0] | .pendingCount != 0 or .runningCount != .desiredCount"); then
            echo "Deploying.............."
            sleep 10
        else
            echo "Deployed!"
            return 0
        fi
    done
    echo "Service update took too long."
    return 1
}

deploy_cluster