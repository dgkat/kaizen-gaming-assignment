package com.dgkatoudis.kaizengamingassignment.presentation.sports

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    fun format(epoch: Long): String {
        val formatter = SimpleDateFormat("hh:mm:ss")
        val date = Date(epoch)
        return formatter.format(date)
    }

}
