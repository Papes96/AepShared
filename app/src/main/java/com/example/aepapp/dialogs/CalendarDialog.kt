package com.example.aepapp.dialogs

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.util.*

class CalendarDialog(context: Context, onDateSelection: (DatePicker, Int, Int, Int) -> Unit) :
    DatePickerDialog(
        context,
        onDateSelection,
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    ) {
    init{
        datePicker.maxDate = System.currentTimeMillis()
    }
}