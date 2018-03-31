#!/usr/bin/env bash
gradle clean build && docker-compose -p ar up --build backend
