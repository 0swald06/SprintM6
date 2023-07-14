package com.example.sprintm6.Modelo.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sprintm6.Modelo.Local.PhonesDao

@Database(entities = [PhonesEntity::class, PhonesDetailEntity::class], version =  1,
    exportSchema = false)
abstract class PhonesDatabase:RoomDatabase(){

    abstract fun getPhonesDao():PhonesDao


    companion object{

        private var INSTANCE : PhonesDatabase?=null

        fun getDataBase(context: Context): PhonesDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    PhonesDatabase::class.java,"phone_db")
                    .build()
                INSTANCE =instance
                return instance
            }

        }

    }


}