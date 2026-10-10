pipeline {
  agent any
  stages {
    stage('API and database tests') {
      steps {
        bat 'mvn -B clean test "-Dsuite=testng-api.xml"'
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'evidence/**', allowEmptyArchive: true
      junit testResults: 'target/surefire-reports/TEST-*.xml', allowEmptyResults: true
    }
  }
}