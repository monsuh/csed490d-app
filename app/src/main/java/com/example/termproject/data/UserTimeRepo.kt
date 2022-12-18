package com.example.termproject.data

import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class UserTimeRepo(
    private val userTimeDao : UserTimeDao
) {

    suspend fun addTimeRecord(userTime : UserTime) {
        withContext(Dispatchers.Default) {
            try {
                userTimeDao.insertUserTimeRecord(userTime)
            } catch(e: SQLiteConstraintException) {
                userTimeDao.updateUserTimeRecord(userTime)
            }
        }
    }

    // return the most recent app resume time
    suspend fun getAppStartTime(appName : String, minStartTime : Long) : Long?  = withContext(Dispatchers.Default) {
        val possibleTimes = async { userTimeDao.getPossibleAppResumeTimes(appName, minStartTime) }
        possibleTimes.await().maxOrNull()
    }

    suspend fun getUserTimesAfter(minStartTime: Long) : List<UserTime> {
        return userTimeDao.getUserTimesAfter(minStartTime)
    }

    suspend fun getAllRelevantUserTimes() : List<UserTime> {
        return userTimeDao.getAllRelevantUserTimes()
    }

    // Clear the table for testing purposes
    fun deleteAll() {
        runBlocking(Dispatchers.Default) {
            userTimeDao.deleteAllEntries()
        }
    }
}