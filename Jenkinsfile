pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Aisalkyn85/Jenkins-Zero-To-Hero.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "🐳 Building Docker image..."
                    sh '''
                        echo "Current directory: $(pwd)"
                        docker build -t zero-to-hero-app:v1 -f Dockerfile .
                    '''
                }
            }
        }

        stage('Run Container Locally') {
            steps {
                script {
                    echo "🚀 Running container..."
                    sh '''
                        docker run -d -p 8080:8080 --name zero-to-hero zero-to-hero-app:v1 || true
                        docker ps
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build and container run completed successfully!"
        }
        failure {
            echo "❌ Build failed. Check console output for errors."
        }
    }
}
