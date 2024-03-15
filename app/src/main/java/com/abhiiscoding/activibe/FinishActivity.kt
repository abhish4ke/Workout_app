package com.abhiiscoding.activibe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.abhiiscoding.activibe.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)
        //to add a back button to the action bar, we have to first check if it is supported or not.
        if (supportActionBar!=null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        //Adding a back button to the action bar.
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener{
            finish()
        }

        val dao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)

    }

    private fun addDateToDatabase(historyDao: HistoryDao){
        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date: ", ""+dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HHH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.e("Formatted Date: ", ""+date)


        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e("Date : ",
                "Added...")
        }
    }

}