def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven_3.8.6/bin/mvn"

pipeline {
    agent any
    parameters {
        string(name: 'BRANCH', defaultValue: 'main', description: '')
        string(name: 'TAG', defaultValue: '@firstTest', description: '')
    }
    stages {
        stage('Run Tests') {
            steps {
                sh "${mvn} test -Dcucumber.filter.tags=${params.TAG}"
            }
        }
        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}