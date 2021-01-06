def call(){

    stage('downloadNexus') {
        sh  "curl -X GET -u admin:Meracbeta236890 http://localhost:8081/repository/test-repo/com/devopsusach2020/DevOpsUsach2020/1.0.2.gradle/DevOpsUsach2020-1.0.2.gradle.jar -O"    
    }

    stage('runDownloadedJar') {
        sh "nohup java -jar DevOpsUsach2020-1.0.2.gradle.jar & >/dev/null"
        sh "sleep 10"
    }
    
    stage('rest') {
        sh "curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'"
    }

    stage('nexusCD') {
        nexusPublisher nexusInstanceId: 'Nexus_server_local', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/var/lib/jenkins/workspace/ci-cd_pipeline-ci_develop/build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.2.release']]]

    } 
                        
}

return this;