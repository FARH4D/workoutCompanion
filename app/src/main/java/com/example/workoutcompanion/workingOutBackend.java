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

    private void initialiseLocations() {
        exerciseGifs.put("Push ups", R.drawable.push_up);
        exerciseGifs.put("Squats", R.drawable.bodyweight_squat);
        exerciseGifs.put("Pull ups", R.drawable.neutral_grip_pull_ups_shoulder_width);
    }

    public Integer getGif(String exercise){
        return exerciseGifs.getOrDefault(exercise, null); // getOrDefault gets the value from the map by using the key and if the does not exist it will be null (this wouldnt ever happen though)
    }
}
