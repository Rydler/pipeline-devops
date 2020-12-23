/*forma de invocación de método call:
def ejecucion = load 'gradle.groovy'
ejecucion.call()
*/
def call(){

    def arrayStage =  params.stage.split(';')
    println arrayStage[0]
    println arrayStage[1]


    stage('Prueba'){
        echo 'TESTEANDO...'
    }

    /*


    stage('Build & Test'){
        sh './gradlew clean build'
    }

    stage('Sonar'){
        def scannerHome = tool 'sonar';
        withSonarQubeEnv('sonar') { // If you have configured more than one global server connection, you can specify its name
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
        }
    }

    stage('Run'){
        sh 'nohup bash gradlew  bootRun &'
        sleep 10
    }

    stage('Test'){
        sh 'curl -X GET http://localhost:8082/rest/mscovid/test?msg=testing'
    }

    stage('Nexus'){
        nexusPublisher nexusInstanceId: 'Nexus_server_local', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/var/lib/jenkins/workspace/example_choice/build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.2.gradle']]]
    }
    */

}

return this;
