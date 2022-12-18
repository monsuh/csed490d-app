package com.example.termproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "userTime", primaryKeys = ["appName", "startTime"])
data class UserTime (
    val appName: String,
    val startTime: Long,
    val duration: Long
)