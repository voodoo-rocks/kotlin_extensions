package rocks.voodoo.extensions

import android.app.TimePickerDialog
import android.content.Context
import java.text.DateFormat
import java.text.ParseException
import java.util.*


fun String.toDateWithFormat(dateFormat: DateFormat): Date? {
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toStringWithFormat(dateFormat: DateFormat): String {
    return dateFormat.format(this)
}

fun Date.showTimeDialog(context: Context, listener: TimePickerDialog.OnTimeSetListener) {
    val calendar = Calendar.getInstance()
    calendar.time = this
    TimePickerDialog(context, listener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false)
            .show()
}

fun getTimeZoneOffsetInMinutes(): Int {
    val timezone = TimeZone.getDefault()
    val secondsInMinute = 60
    val millisecondsInSecond = 1000
    return timezone.rawOffset / (secondsInMinute * millisecondsInSecond) +
            if (timezone.inDaylightTime(Date()))
                timezone.dstSavings / (secondsInMinute * millisecondsInSecond)
            else 0
}