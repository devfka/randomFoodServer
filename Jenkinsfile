pipeline {
    agent any
    stages {
        stage('Build') {
            steps {

                script {
                    def os = System.properties['os.name'].toLowerCase()
                    echo "OS: ${os}"
                      sh "mvn clean install"
            }
        }
    }
}