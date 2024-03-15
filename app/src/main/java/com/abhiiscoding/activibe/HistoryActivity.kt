package com.abhiiscoding.activibe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhiiscoding.activibe.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding:ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        //to add a back button to the action bar, we have to first check if it is supported or not.
        if (supportActionBar!=null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        val dao = (application as WorkOutApp).db.historyDao()
        getAllCompletedDates(dao)

    }

//    private fun getAllCompletedDates(historyDao: HistoryDao){
//        lifecycleScope.launch {
//            historyDao.fetchAllDates().collect{allCompletedDates->
//                if (allCompletedDates.isNotEmpty()){
//                    binding?.tvHistory?.visibility = View.VISIBLE
//                    binding?.rvHistory?.visibility = View.VISIBLE
//                    binding?.tvNoDataAvailable?.visibility = View.GONE
//
//                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)
//
//                    val dates = ArrayList<String>()
//                    for (date in allCompletedDates){
//                        dates.add(date.date)
//                    }
//
//                    val historyAdapter = HistoryAdapter(dates)
//                    binding?.rvHistory?.adapter = historyAdapter
//
//                }else{
//                    binding?.tvHistory?.visibility = View.GONE
//                    binding?.rvHistory?.visibility = View.GONE
//                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
//                }
//            }
//        }
//    }

    private fun getAllCompletedDates(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect { allCompletedDatesList->
                // TODO(Step 3 :here the dates which were printed in log.
                //  We will pass that list to the adapter class which we have created and bind it to the recycler view.)
                // START
                if (allCompletedDatesList.isNotEmpty()) {
                    // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE

                    // Creates a vertical Layout Manager
                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    // History adapter is initialized and the list is passed in the param.
                    val dates = ArrayList<String>()
                    for (date in allCompletedDatesList){
                        dates.add(date.date)
                    }
                    val historyAdapter = HistoryAdapter(ArrayList(dates))

                    // Access the RecyclerView Adapter and load the data into it
                    binding?.rvHistory?.adapter = historyAdapter
                } else {
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
                // END
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}