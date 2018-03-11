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
                sh 'gradle build --info --stacktrace'
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
                    sh 'gradle --info --stacktrace sonarqube'
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
                // TODO: parameterise credentials
                sh "gradle publish -Partifactory_contextUrl=http://artifactory:8081/artifactory"
            }
        }

        stage('Docker Build') {
             agent { label 'docker' }
             steps {
                 sh 'docker-compose build backend'
             }
        }
        // TODO: mount to network so we can push to registry:5000
        stage('Docker Tag & Push') {
            agent { label 'docker' }
            steps {
                sh 'docker tag appregister-backend:latest localhost:5000/appregister-backend:latest'
                sh 'docker push localhost:5000/appregister-backend:latest'
            }
        }
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