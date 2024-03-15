package com.abhiiscoding.activibe

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            1, "Jumping Jacks", R.drawable.jumping_jacks,false, false
        )
        exerciseList.add(jumpingJacks)
        val abCrunches = ExerciseModel(
            2, "Ab Crunches", R.drawable.crunches,false, false
        )
        exerciseList.add(abCrunches)
        val russianTwists = ExerciseModel(
            3, "Russian Twists", R.drawable.russian_twist,false, false
        )
        exerciseList.add(russianTwists)
        val mountainClimbers = ExerciseModel(
            4, "Mountain Climbers", R.drawable.mountain_climbers,false, false
        )
        exerciseList.add(mountainClimbers)
        val plank = ExerciseModel(
            5, "Plank", R.drawable.plank,false, false
        )
        exerciseList.add(plank)
        val lunges = ExerciseModel(
            6, "Lunges", R.drawable.lunges,false, false
        )
        exerciseList.add(lunges)

        val pushUps = ExerciseModel(
            7, "Push Ups", R.drawable.push_ups,false, false
        )
        exerciseList.add(pushUps)
        val reverseCrunches = ExerciseModel(
            8, "Reverse Crunches", R.drawable.reverse_crunches,false, false
        )
        exerciseList.add(reverseCrunches)
        val flutterKicks = ExerciseModel(
            9, "Flutter Kicks", R.drawable.flutter_kicks,false, false
        )
        exerciseList.add(flutterKicks)
        val squats = ExerciseModel(
            10, "Squats", R.drawable.squats,false, false
        )
        exerciseList.add(squats)
        return exerciseList
    }
}