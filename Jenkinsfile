pipeline {
    agent any

    tools {
        allure 'allure'
    }

    parameters {
        string(name: 'branchName', defaultValue: 'master', description: 'Branch for build')
        string(name: 'config', defaultValue: 'AllTest', description: 'TestNG suite file(without .xml extension)')
        string(name: 'browser', defaultValue: 'chrome', description: 'Browser to use for tests')
        string(name: 'threadCount', defaultValue: '2', description: 'Number of parallel threads')

    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.branchName}", url: 'https://github.com/VadimLin/Selenide_orangeHRM.git', credentialsId: 'gh'
            }
        }

        stage('build & Test') {
            steps {
                bat """
                    mvn clean test
                        -Dbrowser=${params.browser} ^
                        -Dconfig=${params.config} ^
                        -DthreadCount=${params.threadCount}
                """
            }
            post {
                always {
                    allure includeProperties:
                            false,
                            jdk:'',
                            results: [[path: 'target/allure-results']]
                }
            }
        }
    }
}