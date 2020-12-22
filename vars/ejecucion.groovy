def call(){

    pipeline {
        agent any
        parameters { 
            choice(name: 'eleccion', choices: ['gradle', 'maven'], description: 'Invocacion ')
           // string(name: 'stage', defaultValue: 'prueba', description: 'Stage a ejecutar') 
        }
            

        stages {
            stage('Pipeline') {
                steps{
                    script{

                       // println 'Stage a ejecutar': + params.stage
                        println 'Herramienta de ejecución:' + params.eleccion
                        
                     //   if (params.stage == 'prueba'){
                     //       println 'INICIO'
                     //       //params.eleccion.call()
                     //       println 'FIN'
                     //   }

                      /*  
                        if (params.eleccion == 'gradle'){
                            gradle.call()
                        } else{
                            maven.call()
                        }
                    */
                    }
                }
            }
        }

        post {
            failure {
                slackSend channel: 'U01DRJVPRTK', color: 'good', message: "Build Failure: [Daniel Morales][${env.JOB_NAME}][${params.CHOICES}] Ejecución fallida en stage [${env.STAGE_NAME}]", teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'Slack_integration'
            }
            success {
                slackSend channel: 'U01DRJVPRTK', color: 'good', message: "Build Success: [Daniel Morales][${env.JOB_NAME}][${params.CHOICES}] Ejecución exitosa.", teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'Slack_integration'

            }
        }
    }
}

return this;