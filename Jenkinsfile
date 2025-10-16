pipeline {
    agent any

    environment {
        APP_NAME = "zero-to-hero"
        IMAGE_NAME = "zero-to-hero-app"
        IMAGE_TAG  = "v1"
        DOCKERFILE_PATH = "Dockerfile"  // 🔸 Change this if your Dockerfile is in another folder (e.g., "app/Dockerfile")
        BUILD_CONTEXT   = "."           // 🔸 Change to "app" if the app files are inside /app folder
    }

    stages {

        stage('Checkout Repository') {
            steps {
                echo "📦 Checking out repository from GitHub..."
                git branch: 'main', url: 'https://github.com/Aisalkyn85/Jenkins-Zero-To-Hero.git'
                sh 'ls -l'
            }
        }

        stage('Validate Dockerfile Path') {
            steps {
                script {
                    echo "🔍 Checking if Dockerfile exists at: ${DOCKERFILE_PATH}"
                    def exists = fileExists("${DOCKERFILE_PATH}")
                    if (!exists) {
                        error "❌ Dockerfile not found at ${DOCKERFILE_PATH}. Please verify the path or move it to the repo root."
                    } else {
                        echo "✅ Dockerfile found!"
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "🐳 Building Docker image..."
                    sh '''
                        echo "Current working directory: $(pwd)"
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -f ${DOCKERFILE_PATH} ${BUILD_CONTEXT}
                    '''
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    echo "🚀 Running the Docker container..."
                    sh '''
                        docker rm -f ${APP_NAME} || true
                        docker run -d -p 8080:8080 --name ${APP_NAME} ${IMAGE_NAME}:${IMAGE_TAG}
                        echo "Container started at http://localhost:8080"
                        docker ps
                    '''
                }
            }
        }

        stage('Verify Application') {
            steps {
                script {
                    echo "🔎 Verifying if the container is running..."
                    sh 'docker ps | grep ${APP_NAME} || (echo "❌ Container not running!" && exit 1)'
                    echo "✅ Application is up and running!"
                }
            }
        }
    }

    post {
        success {
            echo "🎉 Jenkins Zero-to-Hero pipeline executed successfully!"
        }
        failure {
            echo "❌ Build failed. Please check Jenkins console logs for details."
        }
    }
}
