package com.example.condoapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String[]> mText;

    public SharedViewModel() {
        mText = new MutableLiveData<>();
    }
    public void setPassedData (String[] passedData){
        mText.setValue(passedData);
    }

    public LiveData<String[]> getText() {
        return mText;
    }
}
