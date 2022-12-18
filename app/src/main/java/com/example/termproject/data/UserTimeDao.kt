package com.example.termproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
// DB for storing procrastination-app related data
interface UserTimeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUserTimeRecord(vararg userTime: UserTime)

    @Update
    suspend fun updateUserTimeRecord(userTime : UserTime)

    @Query("DELETE FROM userTime")
    suspend fun deleteAllEntries()

    @Query("SELECT * FROM userTime")
    suspend fun getAllRelevantUserTimes(): List<UserTime>

    @Query("SELECT * FROM userTime WHERE startTime + duration > :trackingStartTime")
    suspend fun getUserTimesAfter(trackingStartTime : Long) : List<UserTime>

    @Query("SELECT startTime FROM userTime WHERE appName = :appName AND startTime > :minStartTime")
    suspend fun getPossibleAppResumeTimes(appName : String, minStartTime : Long) : List<Long>

}