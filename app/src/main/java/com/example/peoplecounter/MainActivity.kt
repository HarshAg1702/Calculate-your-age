package com.example.peoplecounter

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var tvSelecteddate: TextView?=null
    var tvageInMinutes : TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelecteddate =findViewById(R.id.tvSelecteddate)
        tvageInMinutes=findViewById(R.id.tvageInMinutes)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
            Toast.makeText(this,
                "btnDatePicker pressed",Toast.LENGTH_LONG).show()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,selectedyear,selectedmonth,selecteddayOfMonth ->
                Toast.makeText(this,
                    "Year was $year, month was ${selectedmonth+1},date was $selecteddayOfMonth",Toast.LENGTH_LONG).show()
                val selectedDate="$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                tvSelecteddate?.text=selectedDate
                val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                val selectedDateInMinutes= theDate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes= currentDate.time/60000
                val differenceInMinutes= currentDateInMinutes-selectedDateInMinutes
                tvageInMinutes?.text=differenceInMinutes.toString()
            },
            year,
            month,
            day
        ).show()
    }
}
