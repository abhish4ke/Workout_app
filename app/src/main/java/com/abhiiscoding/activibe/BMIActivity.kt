package com.abhiiscoding.activibe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.abhiiscoding.activibe.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }
    private var currVisibleView: String = METRIC_UNITS_VIEW

    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarBmiActivity)
        //to add a back button to the action bar, we have to first check if it is supported or not.
        if (supportActionBar!=null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }

        //Adding a back button to the action bar.
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnits()

        binding?.rgUnits?.setOnCheckedChangeListener{ _, checkedId: Int ->
            if (checkedId==R.id.rbMetricUnits){
                makeVisibleMetricUnits()
            }else{
                makeVisibleUsUnits()
            }

        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUnits()
        }

    }

    private fun makeVisibleMetricUnits(){
        currVisibleView = METRIC_UNITS_VIEW
//        Metriic units are visible and US units are invisible
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilUsUnitWeight?.visibility = View.GONE
        binding?.tilUsUnitHeightFeet?.visibility = View.GONE
        binding?.tilUsUnitHeightInch?.visibility = View.GONE

        //clear height and weight values if already present
        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUsUnits(){
        currVisibleView = US_UNITS_VIEW
//        US units are visible and Metric units are invisible
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilUsUnitWeight?.visibility = View.VISIBLE
        binding?.tilUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilUsUnitHeightInch?.visibility = View.VISIBLE

        //clear height and weight values if already present
        binding?.etUsUnitHeightFeet?.text!!.clear()
        binding?.etUsUnitHeightInch?.text!!.clear()
        binding?.etUsUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float){

        val bmiLabel: String
        val bmiDescription: String

        if (bmi>=0 && bmi <15){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself ;) Eat more!!"
        } else if (bmi in 15f..16f){
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself ;) Eat more!!"
        }else if (bmi in 16f..18.5f){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself ;) Eat more!!"
        }else if (bmi in 18.5f..25f){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape :)"
        }else if (bmi in 25f..30f){
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take better care of yourself ;) Workout More!"
        }else if (bmi in 30f..35f){
            bmiLabel = "Obese Class 1(Moderately obese)"
            bmiDescription = "Oops! You really need to take better care of yourself ;) Workout More!"
        }else if (bmi in 35f..40f){
            bmiLabel = "Obese Class 2(Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!!"
        } else {
            bmiLabel = "Obese Class 3(Very severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true

        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun calculateUnits(){
        if (currVisibleView== METRIC_UNITS_VIEW){
            if (validateMetricUnits()){
                val heightVal: Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightVal: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightVal/(heightVal*heightVal)

                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@BMIActivity, "Enter valid inputs", Toast.LENGTH_SHORT).show()
            }
        }else{
            if (validateUsUnits()){
                val heightValueFeet: String = binding?.etUsUnitHeightFeet?.text.toString()
                val heightValueInch: String = binding?.etUsUnitHeightInch?.text.toString()
                val weightValue: Float = binding?.etUsUnitWeight?.text.toString().toFloat()

                val heightVal = heightValueInch.toFloat()+ heightValueFeet.toFloat() * 12

                val bmi = 703 * (weightValue / ( heightVal * heightVal ))
                displayBMIResult(bmi)
            }
            else{
                Toast.makeText(this@BMIActivity, "Enter valid inputs", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateUsUnits(): Boolean{
        var isValid = true

        if (binding?.etUsUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etUsUnitHeightFeet?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etUsUnitHeightInch?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

}