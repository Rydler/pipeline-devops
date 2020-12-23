
def validarStages(validStage,arrayStage){
    
    def check = false
    def aux = 0

    for (String validValues: validStage){
        for (String values: arrayStage){
            if (values == validValues){
                println "Prueba"
            }
        }
    }
    if (aux == arrayStage.size){
        check = true
    }
    return check
}


