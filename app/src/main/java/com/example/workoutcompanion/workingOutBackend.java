package com.example.workoutcompanion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class workingOutBackend{
    private Map<String, Integer> exerciseGifs;

    public workingOutBackend(){
        exerciseGifs = new HashMap<>();
        initialiseLocations();
    }

    private void initialiseLocations() { // Adding keys to the hash map with an assigned gif so that the gifs can be retrieved depending on the exercise needed.
        exerciseGifs.put("Push ups", R.drawable.push_up);
        exerciseGifs.put("Bodyweight Squats", R.drawable.bodyweight_squat);
        exerciseGifs.put("Pull ups", R.drawable.neutral_grip_pull_ups_shoulder_width);
        exerciseGifs.put("Band Chest Press", R.drawable.band_chest_press);
        exerciseGifs.put("Barbell Bench Press", R.drawable.barbell_bench_press);
        exerciseGifs.put("Barbell Bent Over Rows", R.drawable.barbell_bent_over_row);
        exerciseGifs.put("Barbell Squats", R.drawable.barbell_full_squat);
        exerciseGifs.put("Barbell Incline Press", R.drawable.barbell_incline_bench_press);
        exerciseGifs.put("Barbell Rack Pulls", R.drawable.barbell_rack_pull);
        exerciseGifs.put("Bicycle Crunches", R.drawable.bicycle_crunch_movement);
        exerciseGifs.put("Bodyweight Lunges", R.drawable.bodyweight_forward_lunge);
        exerciseGifs.put("Burpee Push ups", R.drawable.burpee_push_up);
        exerciseGifs.put("Cable Crossovers", R.drawable.cable_crossover);
        exerciseGifs.put("Chin ups", R.drawable.chin_ups);
        exerciseGifs.put("Close Grip Band Rows", R.drawable.close_grip_band_row);
        exerciseGifs.put("Close Grip Lat Pulldowns", R.drawable.close_grip_lat_pulldown_standard_bar_attachment);
        exerciseGifs.put("Close Grip Push ups", R.drawable.close_grip_push_up);
        exerciseGifs.put("Decline Sit ups", R.drawable.decline_sit_up);
        exerciseGifs.put("Dumbbell Concentration Curls", R.drawable.dumbbell_concentration_curl);
        exerciseGifs.put("Dumbbell Squats", R.drawable.dumbbell_squat);
        exerciseGifs.put("Elbow To Knee Sit ups", R.drawable.elbow_to_knee_sit_up_movement);
        exerciseGifs.put("EZ Bar Preacher Curls", R.drawable.ez_bar_preacher_curl);
        exerciseGifs.put("Front Squats", R.drawable.front_squat);
        exerciseGifs.put("Hamstring Raises", R.drawable.glute_ham_raise_muscles);
        exerciseGifs.put("Goblet Wall Sit", R.drawable.goblet_wall_sit_muscles);
        exerciseGifs.put("Incline Dumbbell Curls", R.drawable.incline_dumbbell_curl);
        exerciseGifs.put("Inverted Rows", R.drawable.inverted_row);
        exerciseGifs.put("Kettlebell Overhead Squats", R.drawable.kettlebell_overhead_squat);
        exerciseGifs.put("Kettlebell Squat Cleans", R.drawable.kettlebell_squat_clean);
        exerciseGifs.put("Lying Leg Raises", R.drawable.lying_leg_raise);
        exerciseGifs.put("Monkey Rows", R.drawable.monkey_row);
        exerciseGifs.put("Neutral Grip Pull ups", R.drawable.neutral_grip_pull_ups_shoulder_width);
        exerciseGifs.put("Nordic Hamstring Curls", R.drawable.nordic_hamstring_curl_movement);
        exerciseGifs.put("Overhead Tricep Stretch", R.drawable.overhead_tricep_stretch);
        exerciseGifs.put("Pendlay Rows", R.drawable.pendlay_row);
        exerciseGifs.put("Ring Dips", R.drawable.ring_dip);
        exerciseGifs.put("Russian Kettlebell Swings", R.drawable.russian_kettlebell_swing);
        exerciseGifs.put("Russian Twists", R.drawable.russian_twist);
        exerciseGifs.put("Shoulder Push ups", R.drawable.shoulder_push_up);
        exerciseGifs.put("Single Leg Wall Sit", R.drawable.single_leg_wall_sit);
        exerciseGifs.put("Squat Jumps", R.drawable.squat_jump);
        exerciseGifs.put("V Sit ups", R.drawable.v_sit_ups);
        exerciseGifs.put("Weighted Dips", R.drawable.weighted_dips);
        exerciseGifs.put("Wide Arm Push ups", R.drawable.wide_arm_push_ups);
        exerciseGifs.put("Windshield Wipers", R.drawable.windshield_wipers);
        exerciseGifs.put("Zercher Squats", R.drawable.zercher_squat);
    }
    public Integer getGif(String exercise){
        return exerciseGifs.getOrDefault(exercise, null); // getOrDefault gets the value from the map by using the key and if the does not exist it will be null (this wouldnt ever happen though)
    }
}