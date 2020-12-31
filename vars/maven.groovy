/*forma de invocación de método call:
def ejecucion = load 'maven.groovy'
ejecucion.call()
*/
def call(){

    def validStage = ['Compile', 'Test', 'Jar', 'Sonar', 'UploadNexus']
    def arrayStage =  params.stage.split(';')

    /*
    Llamar a una funcion que valide si los parametros del array son validos y retorne un boolean.
    IN: validStage, arrayStage
    Out: Boolean
    */

    def check = util.validarStages(validStage,arrayStage)

    if ("${params.stage}" == ''){

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
            nexusPublisher nexusInstanceId: 'Nexus_server_local', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/var/lib/jenkins/workspace/ejemplo-gradle-library/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.2.maven']]]
        }                     
    }
    
    else if (check == true){

        for(String values : arrayStage){

            switch(values){
                case 'Compile':
                stage('Compile') {
                    sh './mvnw clean compile -e'
                }
                break

                case 'Test':
                stage('Test') {
                    sh './mvnw clean test -e'
                }
                break

                case 'Jar':
                stage('Jar') {
                    sh './mvnw clean package -e'
                }
                break

                case 'Sonar':
                stage('Sonar'){
                    def scannerHome = tool 'sonar';
                    withSonarQubeEnv('sonar') { // If you have configured more than one global server connection, you can specify its name
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                    }
                }
                break

                case 'UploadNexus':
                stage('UploadNexus') {
                    nexusPublisher nexusInstanceId: 'Nexus_server_local', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/var/lib/jenkins/workspace/ejemplo-gradle-library/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.2.maven']]]
                }
                break
            }
        }
    }
    else{
        error "Hay al menos un Stage no valido"
    }
}

return this;