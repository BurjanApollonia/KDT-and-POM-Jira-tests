pipeline {
  agent any
  stages {
    stage('Setup') {
      steps {
        sh 'pwd'
      }
    }

    stage('Chrome tests') {
      parallel {
        stage('Chrome tests') {
          steps {
            sh '''export BROWSER="Chrome"
export NODE_URL="https://selenium:CCAutoTest19.@seleniumhub.codecool.metastage.net/wd/hub"
mvn  -Dtest=com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test.*Test test'''
          }
        }

        stage('Firefox tests') {
          steps {
            sh '''export BROWSER="Firefox"
export NODE_URL="https://selenium:CCAutoTest19.@seleniumhub.codecool.metastage.net/wd/hub"
mvn  -Dtest=com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test.*Test test'''
          }
        }

      }
    }

    stage('Finish') {
      steps {
        cleanWs(cleanWhenFailure: true, cleanWhenNotBuilt: true, cleanWhenAborted: true, cleanWhenSuccess: true, cleanWhenUnstable: true, cleanupMatrixParent: true)
      }
    }

  }
}