pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Using e-commerce project source code'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ecommerce:jenkins .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker rm -f ecommerce-app || true
                    docker run -d \
                      --name ecommerce-app \
                      -p 8080:8080 \
                      ecommerce:jenkins
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
            echo 'E-commerce deployment successful!'
        }
        failure {
            echo 'Pipeline failed. Check console output.'
        }
    }
}
