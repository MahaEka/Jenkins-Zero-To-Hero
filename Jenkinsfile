pipeline {
    agent any

    environment {
        APP_NAME        = "java-spring-boot-app"
        IMAGE_NAME      = "java-spring-boot-app"
        IMAGE_TAG       = "v1"
        DOCKERFILE_PATH = "java-maven-sonar-argocd-helm-k8s/Dockerfile"
        BUILD_CONTEXT   = "java-maven-sonar-argocd-helm-k8s"
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
                        docker run -d -p 8080:8080 --name ${APP_NAME} ${IMAGE_NAME}:${IMAGE_TAG}
                        echo "Application is accessible at: http://localhost:8080"
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
                    echo "✅ Spring Boot container is up and running successfully!"
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
