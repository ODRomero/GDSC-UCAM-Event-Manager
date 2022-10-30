package dataClasses

data class Evento(
    var nombre:String,
    var fecha:String,
    var descripcion:String,
    var foto:String,
    var ubicacion: Ubicacion,
    var facilitador: Facilitador
)
