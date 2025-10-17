pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        sh 'echo "✅ Checked out repository"; ls -la'
      }
    }

    stage('Build (no Docker)') {
      steps {
        sh '''
          echo "▶️ Fake build running"
          mkdir -p build
          echo "Build complete at $(date)" > build/info.txt
          ls -la build
        '''
      }
    }

    stage('Archive') {
      steps {
        archiveArtifacts artifacts: 'build/**', fingerprint: true
      }
    }
  }

  post {
    success { echo '✅ Pipeline completed successfully!' }
    failure { echo '❌ Pipeline failed. Please check Jenkins logs.' }
  }
}
