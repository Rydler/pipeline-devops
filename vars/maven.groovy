/*forma de invocación de método call:
def ejecucion = load 'maven.groovy'
ejecucion.call()
*/

def call(){

    stage('Compile') {
        sh './mvnw clean compile -e'
    }

    stage('Test') {
        sh './mvnw clean test -e'
    }

    stage('Jar') {
        sh './mvnw clean package -e'
    }

    stage('Sonar'){
        def scannerHome = tool 'sonar';
        withSonarQubeEnv('sonar') { // If you have configured more than one global server connection, you can specify its name
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
        }
    }

    stage('UploadNexus') {
        nexusPublisher nexusInstanceId: 'Nexus_server_local', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/var/lib/jenkins/workspace/example_choice/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.2.maven']]]
    }

}

return this;