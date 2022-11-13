package dataClasses

data class Evento(
    var EID: Int,
    var nombre:String,
    var fecha:String,
    var descripcion:String,
    var foto:String,
    var ubicacion: String,
    var facilitador: String,
    var url : String = "https://gdsc.community.dev/san-antonio-catholic-university-of-murcia/"
)
