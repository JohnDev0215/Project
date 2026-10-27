package com.byd.music.base;

import android.media.AudioSystem;
import android.os.SystemProperties;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.utils.OrientationUtil;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseViewModel extends ViewModel {
    protected String TAG = getClass().getSimpleName();
    public boolean isFirstInit;
    public ObservableBoolean isMultiMode;
    public ObservableBoolean isPortraitMode;
    public ObservableBoolean isRight;
    private SingleLiveEvent<Boolean> loadingEvent;
    private SingleLiveEvent<Boolean> netEvent;
    public ObservableBoolean numIsVisible;

    protected abstract void onCreate();

    protected abstract void onDestroy();

    public void onLazyInit() {
    }

    public BaseViewModel() {
        this.isRight = new ObservableBoolean(Integer.valueOf(SystemProperties.get("persist.vendor.byd.cfg.huc.steering.wheel.position", AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS)).intValue() == 1);
        this.isFirstInit = false;
        SingleLiveEvent<Boolean> singleLiveEvent = new SingleLiveEvent<>();
        this.netEvent = singleLiveEvent;
        singleLiveEvent.setValue(true);
        this.loadingEvent = new SingleLiveEvent<>();
        this.numIsVisible = new ObservableBoolean(false);
        this.isPortraitMode = new ObservableBoolean(false);
        this.isMultiMode = new ObservableBoolean(false);
        onCreate();
    }

    public int getSpanCount(int i, int i2, int i3) {
        return this.isPortraitMode.get() ? i3 : OrientationUtil.getSpanCount(i, i2);
    }

    public SingleLiveEvent<Boolean> getNetEvent() {
        return this.netEvent;
    }

    public SingleLiveEvent<Boolean> getLoadingEvent() {
        return this.loadingEvent;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        onDestroy();
    }

    public void setNumIsVisible(boolean z) {
        this.numIsVisible.set(z);
    }
}
