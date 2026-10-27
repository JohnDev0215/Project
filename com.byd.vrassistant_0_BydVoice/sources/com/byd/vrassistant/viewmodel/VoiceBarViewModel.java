package com.byd.vrassistant.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.byd.vrassistant.bean.VrContent;
import com.byd.vrassistant.bean.VrExInfo;
import com.byd.vrassistant.livedata.SafeMutableLiveData;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceBarViewModel extends ViewModel {
    private static final String TAG = "NEU_VoiceBarViewModel";
    private final SafeMutableLiveData<VrContent> vrContentLiveData = new SafeMutableLiveData<>();
    private final SafeMutableLiveData<VrExInfo> vrExInfoLd = new SafeMutableLiveData<>();
    VrExInfo vrExInfo = new VrExInfo();

    public void setIsNetAvailable(boolean z) {
        this.vrExInfo.setNetAvailable(z);
        RLog.i(TAG, "setIsNetAvailable: " + this.vrExInfo);
        this.vrExInfoLd.setValue(this.vrExInfo);
    }

    public void setVehicleAreaEnum(String str) {
        this.vrExInfo.setArea(str);
        this.vrExInfo.setNetAvailable(true);
        RLog.i(TAG, "setVehicleAreaEnum: " + this.vrExInfo);
        this.vrExInfoLd.setValue(this.vrExInfo);
    }

    public void resetArea() {
        this.vrExInfo.setNetAvailable(true);
        RLog.i(TAG, "resetArea: " + this.vrExInfo);
        this.vrExInfoLd.setValue(this.vrExInfo);
    }

    public LiveData<VrContent> getVrContentLiveData() {
        return this.vrContentLiveData;
    }

    public void setVrContent(VrContent vrContent) {
        this.vrContentLiveData.setValue(vrContent);
    }

    public LiveData<VrExInfo> getVrExInfoLd() {
        return this.vrExInfoLd;
    }
}
