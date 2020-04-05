pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }
        stage('Test') {
                    steps {
                        sh "mvn test"
                        echo 'sudo chmod 777 /var/run/docker.sock'
                        sh 'sudo -i chmod 777 /var/run/docker.sock'
                    }
        }
        stage('Docker Build') {
            agent any
            steps {
                sh 'docker image build -t dockerfka/food .'
            }
        }
        stage('Docker Push') {
            agent any
            steps {
                withCredentials([usernamePassword(credentialsId: 'myDocker', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                sh "docker login -u ${env.USERNAME} -p ${env.PASSWORD}"
                sh 'docker push dockerfka/food:latest'
                }
            }
        }
    }
}