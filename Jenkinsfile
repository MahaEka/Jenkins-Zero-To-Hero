pipeline {
    agent any

    environment {
        APP_NAME        = "python-todo-app"
        IMAGE_NAME      = "python-todo-app"
        IMAGE_TAG       = "v1"
        DOCKERFILE_PATH = "python-jenkins-argocd-k8s/Dockerfile"
        BUILD_CONTEXT   = "python-jenkins-argocd-k8s"
    }

    stages {

        stage('Checkout Repository') {
            steps {
                echo "📦 Checking out repository from GitHub..."
                git branch: 'main', url: 'https://github.com/Aisalkyn85/Jenkins-Zero-To-Hero.git'
                sh 'ls -R'
            }
        }

        stage('Validate Dockerfile Path') {
            steps {
                script {
                    echo "🔍 Checking if Dockerfile exists at: ${DOCKERFILE_PATH}"
                    if (!fileExists("${DOCKERFILE_PATH}")) {
                        error "❌ Dockerfile not found at ${DOCKERFILE_PATH}. Please verify the path."
                    } else {
                        echo "✅ Dockerfile found successfully!"
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "🐳 Building Docker image from ${DOCKERFILE_PATH}..."
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
                    echo "🚀 Running container..."
                    sh '''
                        docker rm -f ${APP_NAME} || true
                        docker run -d -p 8000:8000 --name ${APP_NAME} ${IMAGE_NAME}:${IMAGE_TAG}
                        echo "Application is accessible at: http://localhost:8000"
                        docker ps
                    '''
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    echo "🔎 Verifying if container is running..."
                    sh 'docker ps | grep ${APP_NAME} || (echo "❌ Container not running!" && exit 1)'
                    echo "✅ Python Todo App container is up and running successfully!"
                }
            }
        }
    }

    post {
        success {
            echo "🎉 Jenkins Pipeline executed successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Check console logs for details."
        }
    }
}
