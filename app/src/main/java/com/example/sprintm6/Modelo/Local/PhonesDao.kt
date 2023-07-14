package com.example.sprintm6.Modelo.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sprintm6.Modelo.Local.Database.PhonesDetailEntity
import com.example.sprintm6.Modelo.Local.Database.PhonesEntity

@Dao
interface PhonesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(listCourses:List <PhonesEntity>)


    @Query("SELECT * FROM phones_table ORDER BY id ASC")
    fun getAllPhones(): LiveData<List<PhonesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhoneDetail(course: PhonesDetailEntity)

}