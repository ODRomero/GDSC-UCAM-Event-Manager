package com.example.clase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MySQLDataBase"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val tablas = arrayOf("CREATE TABLE Usuario (UID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, nombre TEXT NOT NULL, isadmin INTEGER NOT NULL DEFAULT 0, telefono TEXT NOT NULL)",
            "CREATE TABLE Facilitador (FID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, email TEXT NOT NULL, bio TEXT NOT NULL, nombre TEXT NOT NULL, foto TEXT NOT NULL)",
            "CREATE TABLE Ubicacion (UBID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, direccion TEXT NOT NULL, aforo TEXT NOT NULL, nombre TEXT NOT NULL)",
            "CREATE TABLE Evento (EID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT NOT NULL, fecha TEXT NOT NULL, descripcion TEXT NOT NULL, foto TEXT NOT NULL, FID INTEGER, UBID INTEGER, enlace TEXT NOT NULL, FOREIGN KEY (FID) REFERENCES Facilitador (FID),  FOREIGN KEY (UBID) REFERENCES Ubicacion (UBID))",
            "CREATE TABLE UserEvent (UEID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, UID INTEGER, EID INTEGER, FOREIGN KEY (UID) REFERENCES Usuario(UID),  FOREIGN KEY (EID) REFERENCES Evento(EID))")
        for (tabla in tablas){
            db.execSQL(tabla)
        }
        val tablas2 = arrayOf(
            "insert into Ubicacion (direccion, aforo, nombre) values ('Ctra. Nora Guadalupe, 3A, 30107 Murcia', '50 Personas', 'UCAM HiTech')",
            "insert into Ubicacion (direccion, aforo, nombre) values ('Plataforma GDSC UCAM', 'Ilimitado', 'Online')",
            "insert into Ubicacion (direccion, aforo, nombre) values ('Av. de los Jerónimos 135, 30107 Guadalupe de Maciascoque, Murcia','28 Personas', 'UCAM Pabellón 5 Sótano API 6')",

            "insert into Facilitador (nombre, bio, email, foto) values ('Oscar Romero', 'Team Lead del GDSC UCAM', 'oromero@gdsc_ucam.com', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/IMG_20220129_120113_494.jpg')",
            "insert into Facilitador (nombre, bio, email, foto) values('Jesus Gonzalez', 'Core Team Member del GDSC UCAM', 'jgonzalez@gdsc_ucam.com', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/ezgif.com-gif-maker%20%282%29.png')",
            "insert into Facilitador (nombre, bio, email, foto) values('Jorge Arcas', 'Core Team Member del GDSC UCAM', 'jarcas@gdsc_ucam.com', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/jorge_arcas.jfif')",
            "insert into Facilitador (nombre, bio, email, foto) values('Alejandro Illan', 'Core Team Member del GDSC UCAM', 'aillan@gdsc_ucam.com', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/alejandro_illan_marcos_BKEo4YM.jpg')",
            "insert into Facilitador (nombre, bio, email, foto) values('Daniel Rosique', 'Core Team Member del GDSC UCAM', 'drosique@gdsc_ucam.com', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/daniel_rosique_egea_5dpR5L9.jpg')",
            "insert into Facilitador (nombre, bio, email, foto) values('David Macario', 'Core Team Member del GDSC UCAM', 'dmacario@gdsc_ucam.com', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/david_macario_1vNwklj.jpg')",

            "insert into Evento (nombre, fecha, descripcion, foto, enlace, FID, UBID) values('Presentacion GDSC UCAM', 'Oct 4, 2022', 'Presentaremos al equipo que conforma al equipo del GDSC UCAM y los tipo de eventos que queremos realizar este año', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.38/v1/gcs/platform-data-dsc/event_banners/Event%20Promo%20-%20Presentacion_lJi3o2J.png','https://gdsc.community.dev/events/details/developer-student-clubs-san-antonio-catholic-university-of-murcia-presents-presentacion-gdsc-ucam/', '1', '1' )",
            "insert into Evento (nombre, fecha, descripcion, foto, enlace, FID, UBID) values('Taller Python Basico', 'Oct 15, 2022', 'Introduccion al lenguaje de progracion Python', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.65/v1/gcs/platform-data-dsc/event_banners/promo%20taller%20basico%20de%20python.png','https://gdsc.community.dev/events/details/developer-student-clubs-san-antonio-catholic-university-of-murcia-presents-taller-python-3-basico/', '2', '2')",
            "insert into Evento (nombre, fecha, descripcion, foto, enlace, FID, UBID) values('Taller GitHub Basico', 'Oct 11, 2022', 'Introduccion al control de versiones con Github', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.55/v1/gcs/platform-data-dsc/event_banners/GitHub%20Basico%20Promo%20-%20Green.jpg','https://gdsc.community.dev/events/details/developer-student-clubs-san-antonio-catholic-university-of-murcia-presents-taller-github-basico/', '3', '3')",
            "insert into Evento (nombre, fecha, descripcion, foto, enlace, FID, UBID) values('Charla Pollitos', 'Oct 7, 2022', 'Charla de estudiantes de 3ro y 4to para los de nuevo ingreso', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1440,x_w_mul_0.5,y_h_mul_0.56/v1/gcs/platform-data-dsc/event_banners/_Charla%20pollitos%20Event%20Promo%20-%20Red.png?md','https://gdsc.community.dev/events/details/developer-student-clubs-san-antonio-catholic-university-of-murcia-presents-charla-pollitos-22/', '4', '3')",
            "insert into Evento (nombre, fecha, descripcion, foto, enlace, FID, UBID) values('Taller Refuerzo C', 'Oct 25, 2022', 'Taller de refuerzo del lenguaje de progracion C para estudiantes de Programacion I', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1440,x_w_mul_0.5,y_h_mul_0.0/v1/gcs/platform-data-dsc/event_banners/promo%20refuerzo%20c%201%20%281%29.png?md','https://gdsc.community.dev/events/details/developer-student-clubs-san-antonio-catholic-university-of-murcia-presents-taller-de-refuezo-de-c/', '5', '3')",
            "insert into Evento (nombre, fecha, descripcion, foto, enlace, FID, UBID) values('Taller GitHub Intermedio', 'Oct 18, 2022', 'Taller sobre el manejo de Git y GitHub via la CLI', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1440,x_w_mul_0.5,y_h_mul_0.77/v1/gcs/platform-data-dsc/event_banners/Git_session.svg.png?md','https://gdsc.community.dev/events/details/developer-student-clubs-san-antonio-catholic-university-of-murcia-presents-taller-github-intermedio/', '4', '2')",
            "insert into Evento (nombre, fecha, descripcion, foto, enlace, FID, UBID) values('Taller Refuerzo Java', 'Oct 28, 2022', 'Taller de refuerzo del lenguaje de progracion Java para estudiantes de Programacion Orientada a Objetos', 'https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1440,x_w_mul_0.5,y_h_mul_0.0/v1/gcs/platform-data-dsc/event_banners/promo%20refuerzo%20java%201.png?md','https://gdsc.community.dev/events/details/developer-student-clubs-san-antonio-catholic-university-of-murcia-presents-taller-de-refuezo-de-java/', '6', '1')",

            "insert into Usuario (email, nombre, password, telefono) values ('oscar@gdsc.com', 'Oscar Romero', 'a', '123456789')",
            "insert into UserEvent (UID, EID) values (1,1)",
            "insert into UserEvent (UID, EID) values (1,2)"
        )
        for (tabla2 in tablas2){
            db.execSQL(tabla2)
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

}
