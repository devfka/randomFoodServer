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
        stage('Docker Push') {
        agent any
            steps {
                withCredentials([usernamePassword(credentialsId: 'myDocker', url:'https://hub.docker.com/repository/docker/dockerfka/food')]) {
                    sh 'docker push dockerfka/food:latest'
        }
      }
    }

    }
}