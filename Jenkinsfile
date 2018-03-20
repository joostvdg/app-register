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
        stage('Pre-Test') {
            agent { label 'docker' }
            steps {
                sh 'docker-compose up -d --build backend mongodb'
            }
        }
        stage('JMeter') {
//            agent {
//                docker {
//                    image 'justb4/jmeter'
//                    label 'docker'
//                    args  "--network=appregister_default --name ${env.JOB_BASE_NAME}-jmeter -v ${PWD}:${PWD} -w ${PWD}"
//                }
//            }
            agent {
                dockerfile {
                    filename 'Dockerfile-jmetertest'
                    dir '.'
                    label 'docker'
                }
            }
            steps {
                sh 'ls -lath'
            }
        }
        stage('Tests') {
            agent { label 'docker' }
            // docker run --rm --name jmeter --network appregister_default -v ${PWD}:${PWD} -w ${PWD} justb4/jmeter -n -t src/main/resources/jmeter.jmx  -l src/main/resources/JMeter.jtl
//            agent {
//                docker {
//                    image 'justb4/jmeter'
//                    label 'docker'
//                    args  "--network=appregister_default --name ${env.JOB_BASE_NAME}-qa"
//                }
//            }
            //sh 'ls -lath'
            //sh 'docker run --rm --name jmeter --network appregister_default -v ${PWD}:${PWD} -w ${PWD} justb4/jmeter -n -t src/main/resources/jmeter.jmx  -l src/main/resources/JMeter.jtl'
//            JMeter: {
//                sh 'docker-compose up --build backend-jmetertest'
//                sh 'ls -lath src/main/resources/'
//            },
            steps {
                parallel(
                        Integration: {
                            sh 'docker-compose up --build backend-integrationtest'
                        },
                        TestDockerfile: {
                            script {
                                def lintResult = sh returnStdout: true, script: 'docker run --rm -i lukasmartinelli/hadolint < Dockerfile'
                                if (lintResult.trim() == '') {
                                    println 'Lint finished with no errors'
                                } else {
                                    println 'Error found in Lint'
                                    println "${lintResult}"
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        }, // end test dockerfile

                )
            }
        }
        stage('Post-Test') {
            agent { label 'docker' }
            steps {
                sh 'docker-compose stop'
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