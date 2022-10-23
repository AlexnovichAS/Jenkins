@Library('example_shared_library@master') _

def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven_3.8.6/bin/mvn"

node {
    stage('Checkout SCM') {
        checkOut(params.BRANCH)
    }
    stage('Build') {
        build(mvn)
    }
    stage('Run Tests') {
        try {
            runTestsByTag(mvn, params.TAG)
        }
        catch (Exception e) {
            echo "Test run was broken"
            throw e
        }
        finally {
            stage('Allure Report Generation') {
                generateAllureReport()
            }
            cleanWs notFailBuild: true
        }
    }
}