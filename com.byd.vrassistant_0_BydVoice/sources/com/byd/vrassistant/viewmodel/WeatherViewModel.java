package com.byd.vrassistant.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import cerence.ark.assistant.controller.domain.hmi.param.WeatherInformationParam;
import com.byd.vrassistant.livedata.SafeMutableLiveData;

/* JADX INFO: loaded from: classes2.dex */
public class WeatherViewModel extends ViewModel {
    SafeMutableLiveData<WeatherInformationParam> weatherInformationParamLiveData = new SafeMutableLiveData<>();

    public LiveData<WeatherInformationParam> getWeatherInfo() {
        return this.weatherInformationParamLiveData;
    }

    public void setWeatherInfo(WeatherInformationParam weatherInformationParam) {
        this.weatherInformationParamLiveData.setValue(weatherInformationParam);
    }
}
