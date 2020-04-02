pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }
        stage('Docker Build') {
        agent any
            steps {
                  sh 'docker image build -t food .'
            }
        }
    }
}