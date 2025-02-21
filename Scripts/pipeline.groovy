pipeline {
    agent any 
    environment {
        SCANNER_HOME= tool('sonar-scanner')
        BRANCH_NAME='main'
        GIT_URL='https://github.com/ANANDHAKUMAR18/AK-Feb21-NodeJs-Source-Code.git'
    }
    tools {
        nodejs 'Nodejs'
    }

    stages {
        stage('Code Checkout') {
            steps {
                git branch: "${BRANCH_NAME}" , url: "${GIT_URL}"
            }
        }

        stage("Install Dependencies & Run Tests and Coverage ") {
            steps {
                script {
                    sh '''
                    npm install
                    npm run test
                    npm run coverage
                    '''
                }
            }
        }

        stage('Static Code Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    script {
                        sh '${SCANNER_HOME}/bin/sonar-scanner'
                    }
                }
            }
        }
    }
}