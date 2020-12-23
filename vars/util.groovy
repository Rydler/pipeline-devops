
def validarStages(validStage,arrayStage){
    
    def check = false
    def aux = 0
    println(aux)
    for (String validValues: validStage){
        for (String values: arrayStage){
            if (values == validValues){
                aux += 1
                println(aux)
            }
        }
    }
    //if (aux == arrayStage.size){
    //    check = true
    //}
    return check
}


