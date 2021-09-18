package com.example.mytest1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    public static final String KEY_NUM1 = "key_num1";
    public static final String KEY_NUM2 = "key_num2";
    public static final String KEY_NUM3 = "key_num3";
    public static final String KEY_SHNAME = "key_shname";

    public MyViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if(!handle.contains(KEY_NUM1)){
            load1();
        }
        if(!handle.contains(KEY_NUM2)){
            load2();
        }
    }
    public MutableLiveData<Integer> getNum1(){
        return handle.getLiveData(KEY_NUM1);
    }

    public MutableLiveData<Integer> getNum2(){
        return handle.getLiveData(KEY_NUM2);
    }

    private void load1() {
        SharedPreferences shp = getApplication().getSharedPreferences(KEY_SHNAME, Context.MODE_PRIVATE);
        int data = shp.getInt(KEY_NUM1,0);
        handle.set(KEY_NUM1,data);
    }
    private void load2() {
        SharedPreferences shp = getApplication().getSharedPreferences(KEY_SHNAME, Context.MODE_PRIVATE);
        int data = shp.getInt(KEY_NUM2,0);
        handle.set(KEY_NUM2,data);
    }

    public void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(KEY_SHNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(KEY_NUM1,getNum1().getValue());
        editor.putInt(KEY_NUM2,getNum2().getValue());
        editor.apply();
    }

    public void add(int p){
        handle.set(KEY_NUM1,getNum1().getValue() + p);
    }

    public void add1(int p){
        handle.set(KEY_NUM2,getNum2().getValue() + p);
    }


}
