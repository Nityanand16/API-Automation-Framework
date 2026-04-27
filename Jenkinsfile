pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build & Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'allure generate target/allure-results --clean -o allure-report'
            }
        }

    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.xml', allowEmptyArchive: true
        }

        success {
            echo 'API Tests Passed Successfully '
        }

        failure {
            echo 'API Tests Failed Check logs'
        }
    }
}