pipeline {
    agent any

    environment {
        APP_NAME        = "spring-boot-app"
        IMAGE_NAME      = "spring-boot-app"
        IMAGE_TAG       = "v1"
        DOCKERFILE_PATH = "java-maven-sonar-argocd-helm-k8s/spring-boot-app/Dockerfile"
        BUILD_CONTEXT   = "java-maven-sonar-argocd-helm-k8s/spring-boot-app"
    }

    stages {
        stage('Checkout Repository') {
            steps {
                echo "📦 Checking out repository..."
                git branch: 'main', url: 'https://github.com/Aisalkyn85/Jenkins-Zero-To-Hero.git'
                sh 'ls -R'
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('java-maven-sonar-argocd-helm-k8s/spring-boot-app') {
                    echo "⚙️ Building with Maven..."
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Validate Dockerfile') {
            steps {
                script {
                    echo "🔍 Checking if Dockerfile exists at: ${DOCKERFILE_PATH}"
                    if (!fileExists("${DOCKERFILE_PATH}")) {
                        error "❌ Dockerfile not found at ${DOCKERFILE_PATH}"
                    } else {
                        echo "✅ Dockerfile found successfully!"
                    }
                }
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
                    echo "🚀 Running container..."
                    sh '''
                        docker rm -f ${APP_NAME} || true
                        docker run -d -p 8080:8080 --name ${APP_NAME} ${IMAGE_NAME}:${IMAGE_TAG}
                        echo "Application running at http://localhost:8080"
                        docker ps
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "🎉 Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Please check Jenkins logs."
        }
    }
}
