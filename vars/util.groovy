
def validarStages(validStage,arrayStage){
    
    def aux = false
    for (String validValues: validStage){
        for (String values: arrayStage){
            if values == validValues{
                aux = true
            }
        }
    }
    return aux
}


