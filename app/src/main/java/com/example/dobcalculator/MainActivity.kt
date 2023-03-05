package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val datePickerButton = findViewById<Button>(R.id.btnDatePicker)

       datePickerButton.setOnClickListener {
           clickDatePicker()
            }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            Toast.makeText(this,"Datepicker is clicked", Toast.LENGTH_SHORT).show()
        },
        year, month, day).show()
        Toast.makeText(this, "Button was clicked", Toast.LENGTH_SHORT).show()
    }
}