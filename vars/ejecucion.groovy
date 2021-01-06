def call(){

    
    pipeline {
        agent any
        parameters { 
            choice(name: 'eleccion', choices: ['gradle'], description: 'Invocacion ')
            //string(name: 'stage', defaultValue: '', description: 'Stage a ejecutar') 
        }
            
        stages {
            
            stage('Variables'){
                steps{
                    script{
                        sh 'env'
                    }    
                }
            }

            stage('Branch feature') {
                when {
                    branch 'feature-*'
                }    
                steps{
                    script{
                        figlet params.eleccion
                        figlet 'INTEGRACION CONTINUA'
                        ci.call() 
                    }
                }
            }

            stage('Branch developer') {
                when {
                    branch 'develop'
                }    
                steps{
                    script{
                        figlet params.eleccion
                        figlet 'INTEGRACION CONTINUA'
                        ci.call() 
                    }
                }
            }

            stage('Branch release') {
                when {
                    branch 'release*'
                }    
                steps{
                    script{
                        figlet params.eleccion
                        figlet 'DESPLIEGUE CONTINUO'
                        cd.call() 
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