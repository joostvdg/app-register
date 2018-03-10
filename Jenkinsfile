pipeline {
    agent none
    options {
        timeout(time: 10, unit: 'MINUTES')
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('Checkout') {
            agent { label 'docker' }
            steps {
                git 'https://github.com/joostvdg/app-register.git'
            }
        }
        stage('Gradle Build') {
            agent {
                docker {
                    image 'gradle:4.6-jdk8'
                    label 'docker'
                }
            }
            steps {
                sh 'gradle build'
                archiveArtifacts allowEmptyArchive: false, artifacts: 'build/libs/app-register-0.0.1-SNAPSHOT.jar', fingerprint: true
            }
       
        }
        stage('Code Quality') {
            agent {
                docker {
                    image 'gradle:4.6-jdk8'
                    label 'docker'
                    args  "--network=cidc_default --name ${env.JOB_BASE_NAME}-qa"
                }
            }
            steps {
                withSonarQubeEnv('sonar') {
                    // requires SonarQube Scanner for Gradle 2.1+
                    // It's important to add --info because of SONARJNKNS-281
                    sh 'gradle --info sonarqube'
                }
            }
        }
        stage('Version & Package'){
            agent {
                docker {
                    image 'gradle:4.6-jdk8'
                    label 'docker'
                    args  "--network=cidc_default --name ${env.JOB_BASE_NAME}-package"
                }
            }
            steps {
                // TODO: gradle release to artifactory?
                sh "gradle publish -Partifactory_contextUrl=http://artifactory:8081/artifactory"
            }
        }
        // TOOD1: first have to solve the workspace problem --> artifact should be coming from the repo
        // TODO2: use docker build args so that the Dockerfile is re-usable locally and for CI
        // base structure: ${artifactoryBaseUrl}/gradle-release-local/com/github/joostvdg/app-register/
        // stage('Docker Build') {
        //     agent { label 'docker' }
        //     steps {
        //         sh 'ls -lath'
        //         sh 'ls -lath build'
        //         sh 'ls -lath build/libs'
        //         sh 'docker-compose build backend'
        //     }
        // }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'SUCCESS!'
        }
        failure {
            echo "We Failed"
        }
        unstable {
            echo "We're unstable"
        }
        changed {
            echo "Status Changed: [From: $currentBuild.previousBuild.result, To: $currentBuild.result]"
        }
    }
}