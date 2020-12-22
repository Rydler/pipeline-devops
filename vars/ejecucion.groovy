def call(){

    pipeline {
        agent any
        parameters { choice(name: 'CHOICES', choices: ['gradle', 'maven'], description: 'Invocacion ') }

        stages {
            stage('Pipeline') {
                steps{
                    script{
                        println 'Herramienta de ejecución:' + params.CHOICES
                        def eleccion = load "${params.CHOICES}.groovy"
                        eleccion.call()
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