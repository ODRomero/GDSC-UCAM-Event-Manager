package com.example.clase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dataClasses.Evento
import dataClasses.Facilitador
import dataClasses.Ubicacion


class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "MySQLDataBase"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val tablas = arrayOf(
            "CREATE TABLE Usuario (UID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, nombre TEXT NOT NULL, isadmin INTEGER NOT NULL DEFAULT 0, telefono TEXT NOT NULL)",
            "CREATE TABLE Facilitador (FID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT NOT NULL, nombre TEXT NOT NULL, bio TEXT, foto TEXT)",
            "CREATE TABLE Ubicacion (UBID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, direccion TEXT NOT NULL, aforo TEXT NOT NULL, nombre TEXT NOT NULL, foto TEXT)",
            "CREATE TABLE Evento (EID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT NOT NULL, fecha TEXT NOT NULL, descripcion TEXT NOT NULL, foto TEXT, FID INTEGER, UBID INTEGER, FOREIGN KEY (FID) REFERENCES Facilitador (FID),  FOREIGN KEY (UBID) REFERENCES Ubicacion (UBID))",
            "CREATE TABLE UserEvent (UEID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, EID INTEGER, UID INTEGER, FOREIGN KEY (EID) REFERENCES Evento(EID),  FOREIGN KEY (UID) REFERENCES Usuario(UID))"
        )

        for (tabla in tablas){
            db.execSQL(tabla)
        }

        val valoresUbicacion = ContentValues()
        val valoresFacilitador = ContentValues()
        val valoresEvento = ContentValues()

        var eventos = getEventos()
        var idGen = 1
        for(evento : Evento in eventos){

            valoresUbicacion.put("nombre", evento.ubicacion.nombre)
            valoresUbicacion.put("aforo", evento.ubicacion.aforo)
            valoresUbicacion.put("direccion", evento.ubicacion.direccion)

            valoresFacilitador.put("nombre", evento.facilitador.nombre)
            valoresFacilitador.put("bio", evento.facilitador.bio)
            valoresFacilitador.put("email", evento.facilitador.email)
            valoresFacilitador.put("foto", evento.facilitador.foto)

            valoresEvento.put("nombre", evento.nombre)
            valoresEvento.put("fecha", evento.fecha)
            valoresEvento.put("descripcion", evento.descripcion)
            valoresEvento.put("foto", evento.foto)
            valoresEvento.put("FID", idGen)
            valoresEvento.put("UBID", idGen)

            idGen += 1
            db.insert("Facilitador", null, valoresFacilitador)
            db.insert("Ubicacion", null, valoresUbicacion)
            db.insert("Evento",null , valoresEvento)

            valoresEvento.clear()
            valoresFacilitador.clear()
            valoresUbicacion.clear()
        }


    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is to
        // simply to discard the data and start over.
        db.execSQL("DROP TABLE IF EXISTS Usuario")
        db.execSQL("DROP TABLE IF EXISTS Facilitador")
        db.execSQL("DROP TABLE IF EXISTS Ubicacion")
        db.execSQL("DROP TABLE IF EXISTS Evento")
        db.execSQL("DROP TABLE IF EXISTS UserEvent")
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }



    @SuppressLint("Range")
    fun getEventoDetalleTabla(): MutableList<Evento>? {
        var eventos:MutableList<Evento> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT Evento.nombre as Enombre, fecha, descripcion, Evento.foto as Efoto, Facilitador.nombre as Fnombre, email, bio, Facilitador.foto as Ffoto, Ubicacion.nombre as Unombre, aforo, direccion FROM Evento INNER JOIN Facilitador ON Evento.FID = Facilitador.FID INNER JOIN Ubicacion ON Evento.EID = Ubicacion.UBID", null)

        if( cursor != null && cursor.moveToFirst() ) {
            eventos.add(Evento(
                cursor.getString(cursor.getColumnIndex("Enombre")),
                cursor.getString(cursor.getColumnIndex("fecha")),
                cursor.getString(cursor.getColumnIndex("descripcion")),
                cursor.getString(cursor.getColumnIndex("Efoto")),
                Ubicacion(
                    cursor.getString(cursor.getColumnIndex("direccion")),
                    cursor.getString(cursor.getColumnIndex("aforo")),
                    cursor.getString(cursor.getColumnIndex("Unombre"))),
                Facilitador(
                    cursor.getString(cursor.getColumnIndex("Fnombre")),
                    cursor.getString(cursor.getColumnIndex("bio")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("Ffoto")))))
            while (cursor.moveToNext()) {
                eventos.add(Evento(
                    cursor.getString(cursor.getColumnIndex("Enombre")),
                    cursor.getString(cursor.getColumnIndex("fecha")),
                    cursor.getString(cursor.getColumnIndex("descripcion")),
                    cursor.getString(cursor.getColumnIndex("Efoto")),
                    Ubicacion(
                        cursor.getString(cursor.getColumnIndex("direccion")),
                        cursor.getString(cursor.getColumnIndex("aforo")),
                        cursor.getString(cursor.getColumnIndex("Unombre"))),
                    Facilitador(
                        cursor.getString(cursor.getColumnIndex("Fnombre")),
                        cursor.getString(cursor.getColumnIndex("bio")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("Ffoto")))))
            }
        }

        return eventos
    }

    fun getEventos(): MutableList<Evento>{
        var eventos:MutableList<Evento> = ArrayList()
        //Ubicaciones
        var ubicacionHiTech: Ubicacion = Ubicacion("Ctra. Nora Guadalupe, 3A, 30107 Murcia", "50 Personas", "UCAM HiTech")
        var ubicacionUCAM: Ubicacion = Ubicacion("Av. de los Jerónimos, 135, 30107 Guadalupe de Maciascoque, Murcia", "28 Personas", "UCAM Pabellón 5 Sótano API 6")
        var ubicacionOnline: Ubicacion = Ubicacion("Plataforma GDSC UCAM", "Ilimitado", "Online")
        //Facilitadores
        var facilitadorOscar: Facilitador = Facilitador("Oscar Romero", "Team Lead del GDSC UCAM", "oromero@gdsc_ucam.com", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/IMG_20220129_120113_494.jpg")
        var facilitadorJorge: Facilitador = Facilitador("Jorge Arcas", "Core Team Member del GDSC UCAM", "jarcas@gdsc_ucam.com", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/ezgif.com-gif-maker_22hnFZ2.png")
        var facilitadorJesus: Facilitador = Facilitador("Jesus Gonzalez", "Core Team Member del GDSC UCAM", "jgonzalez@gdsc_ucam.com", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/ezgif.com-gif-maker%20%282%29.png")
        //Eventos
        eventos.add(Evento("Presentacion GDSC UCAM", "Oct 4, 2022", "Presentaremos al equipo que conforma al equipo del GDSC UCAM y los tipo de eventos que queremos realizar este año", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.38/v1/gcs/platform-data-dsc/event_banners/Event%20Promo%20-%20Presentacion_lJi3o2J.png", ubicacionHiTech, facilitadorOscar))
        eventos.add(Evento("Taller GitHub Basico", "Oct 11, 2022", "Introduccion al control de versiones e integraciones con VS Code", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.55/v1/gcs/platform-data-dsc/event_banners/GitHub%20Basico%20Promo%20-%20Green.jpg", ubicacionUCAM, facilitadorJorge))
        eventos.add(Evento("Taller Python Basico", "Oct 15, 2022", "Introduccion al lenguaje de progracion Python", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.65/v1/gcs/platform-data-dsc/event_banners/promo%20taller%20basico%20de%20python.png",  ubicacionOnline, facilitadorJesus))
        return eventos
    }

}
