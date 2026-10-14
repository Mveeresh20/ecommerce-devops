pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code checked out from GitHub'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ecommerce:latest .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker rm -f ecommerce-app || true
                    docker run -d \
                      --name ecommerce-app \
                      -p 8080:8080 \
                      ecommerce:latest
                '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
                    for i in 1 2 3 4 5 6 7 8 9 10; do
                        if curl -fsS http://localhost:8080/health; then
                            exit 0
                        fi
                        sleep 2
                    done
                    exit 1
                '''
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed. Check console output.'
        }
    }
}
