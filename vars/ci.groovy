def call(){

        stage('buildAndTest'){
            sh './gradlew clean build'
        }

        stage('sonar'){
            def scannerHome = tool 'sonar';
            withSonarQubeEnv('sonar') { // If you have configured more than one global server connection, you can specify its name
                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
            }
        }

        stage('runJar'){
            sh 'nohup bash gradlew  bootRun &'
            sleep 10
        }

        stage('rest'){
            sh 'curl -X GET http://localhost:8082/rest/mscovid/test?msg=testing'
        }

        stage('nexusCI'){
            nexusPublisher nexusInstanceId: 'Nexus_server_local', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/var/lib/jenkins/workspace/ci-cd_pipeline-ci_develop/build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.2.gradle']]]
        }
    
}

return this;
