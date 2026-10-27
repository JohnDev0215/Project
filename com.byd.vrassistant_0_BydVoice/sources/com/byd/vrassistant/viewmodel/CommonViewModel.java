package com.byd.vrassistant.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.bean.ScreenMode;
import com.byd.vrassistant.livedata.RxLiveData;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.ScreenUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class CommonViewModel extends ViewModel {
    private static final String TAG = "NEU_CommonViewModel";
    private final MutableLiveData<ScreenMode> screenModeLiveData;
    private final MutableLiveData<Integer> skinTypeLiveData = new MutableLiveData<>(Integer.valueOf(ScreenUtil.getSkinType()));
    private final MutableLiveData<Boolean> isPanoramicStartLiveData = new MutableLiveData<>();
    public MutableLiveData<Locale> vrLocaleLd = new MutableLiveData<>(ContextHolder.getCurrentLocale());
    public RxLiveData<Locale> sysLocaleLd = new RxLiveData<>(ContextHolder.getToastLocaleBsj());
    public MutableLiveData<Context> contextLiveData = new MutableLiveData<>();

    public CommonViewModel() {
        this.screenModeLiveData = new MutableLiveData<>(ScreenUtil.isLandscape() ? ScreenMode.LANDSCAPE : ScreenMode.PORTRAIT);
    }

    public void setScreenMode(ScreenMode screenMode) {
        this.screenModeLiveData.setValue(screenMode);
    }

    public LiveData<ScreenMode> getScreenModeLiveData() {
        return this.screenModeLiveData;
    }

    public LiveData<Integer> getSkinType() {
        return this.skinTypeLiveData;
    }

    public void setSkinType(int i) {
        RLog.i(TAG, "setSkinType: " + i);
        this.skinTypeLiveData.setValue(Integer.valueOf(i));
    }

    public LiveData<Boolean> getIsPanoramicStart() {
        return this.isPanoramicStartLiveData;
    }

    public void setIsPanoramicStart(boolean z) {
        RLog.i(TAG, "setIsPanoramicStart: " + z);
        this.isPanoramicStartLiveData.postValue(Boolean.valueOf(z));
    }

    public void setVrLocale(Locale locale) {
        this.vrLocaleLd.setValue(locale);
    }

    public LiveData<Locale> getLocaleLiveData() {
        return this.vrLocaleLd;
    }

    public LiveData<Locale> getSysLocaleLd() {
        return this.sysLocaleLd;
    }

    public void setLanguageContext(Context context) {
        this.contextLiveData.setValue(context);
    }

    public LiveData<Context> getContextLiveData() {
        return this.contextLiveData;
    }
}
