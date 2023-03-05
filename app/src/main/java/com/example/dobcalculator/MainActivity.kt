package com.example.dobcalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvDateSelected: TextView? = null
    private var tvMinutesOld: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerButton = findViewById<Button>(R.id.btnDatePicker)
        tvDateSelected = findViewById(R.id.tvDate)
        tvMinutesOld = findViewById(R.id.tvAgeInMinutes)

        datePickerButton.setOnClickListener {
            Toast.makeText(this, "Button was clicked", Toast.LENGTH_SHORT).show()
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "The year is $selectedYear, the month is ${selectedMonth + 1} and the day is $selectedDayOfMonth",
                    Toast.LENGTH_SHORT
                ).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvDateSelected?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate!!.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateToMinutes = currentDate!!.time / 60000
                        val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                        tvMinutesOld?.text = "$differenceInMinutes"
                    }
                }


            },
            year, month, day
        )
        //max date pick limitation
        dpd.datePicker.maxDate = Date().time - 86400000

        dpd.show()


    }
}