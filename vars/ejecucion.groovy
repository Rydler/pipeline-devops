def call(){

    
    pipeline {
        agent any
        //parameters { 
            //choice(name: 'eleccion', choices: ['gradle', 'maven'], description: 'Invocacion ')
            //string(name: 'stage', defaultValue: '', description: 'Stage a ejecutar') 
        //}
            
        stages {

            stage('Branch feature') {
                when {
                    branch 'feature-*'
                }    
                steps{
                    script{

                        echo "NOMBRE RAMA: ${BRANCH_NAME}"
                        // INTEGRACION CONTINUA
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

                        echo "NOMBRE RAMA: ${BRANCH_NAME}"
                        // INTEGRACION CONTINUA
                        ci.call() 
                    }
                }
            }

            stage('Branch release') {
                when {
                    branch 'release-*'
                }    
                steps{
                    script{

                        echo "NOMBRE RAMA: ${BRANCH_NAME}"
                        // INTEGRACION CONTINUA
                        //ci.call() 
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