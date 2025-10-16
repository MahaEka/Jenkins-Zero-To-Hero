pipeline {
    agent any

    environment {
        IMAGE_NAME = "zero-to-hero-app"
        IMAGE_TAG  = "v1"
        DOCKERFILE_PATH = "Dockerfile"      // 👈 change this if your Dockerfile is inside a folder (e.g., "app/Dockerfile")
        BUILD_CONTEXT  = "."                // 👈 change this to "app" or another folder if needed
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "📦 Checking out code..."
                git branch: 'main', url: 'https://github.com/Aisalkyn85/Jenkins-Zero-To-Hero.git'
            }
        }

        stage('List Files') {
            steps {
                echo "📂 Listing project files for confirmation..."
                sh 'ls -R'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "🐳 Building Docker image..."
                    sh '''
                        echo "Current directory: $(pwd)"
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -f ${DOCKERFILE_PATH} ${BUILD_CONTEXT}
                    '''
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    echo "🚀 Running container locally..."
                    sh '''
                        docker rm -f ${IMAGE_NAME} || true
                        docker run -d -p 8080:8080 --name ${IMAGE_NAME} ${IMAGE_NAME}:${IMAGE_TAG}
                        echo "Container started. Use: http://localhost:8080"
                        docker ps
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Jenkins pipeline completed successfully!"
        }
        failure {
            echo "❌ Build failed! Check Jenkins console output for details."
        }
    }
}
