package com.android.car.developeroptions.wifi.dpp;

import android.app.Application;
import android.net.wifi.EasyConnectStatusCallback;
import android.net.wifi.WifiManager;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/* JADX INFO: loaded from: classes.dex */
public class WifiDppInitiatorViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> mEnrolleeSuccessNetworkId;
    private boolean mIsGoingInitiator;
    private MutableLiveData<Integer> mStatusCode;

    public WifiDppInitiatorViewModel(Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getEnrolleeSuccessNetworkId() {
        if (this.mEnrolleeSuccessNetworkId == null) {
            this.mEnrolleeSuccessNetworkId = new MutableLiveData<>();
        }
        return this.mEnrolleeSuccessNetworkId;
    }

    public MutableLiveData<Integer> getStatusCode() {
        if (this.mStatusCode == null) {
            this.mStatusCode = new MutableLiveData<>();
        }
        return this.mStatusCode;
    }

    public boolean isGoingInitiator() {
        return this.mIsGoingInitiator;
    }

    public void startEasyConnectAsConfiguratorInitiator(String str, int i) {
        this.mIsGoingInitiator = true;
        ((WifiManager) getApplication().getSystemService(WifiManager.class)).startEasyConnectAsConfiguratorInitiator(str, i, 0, getApplication().getMainExecutor(), new EasyConnectDelegateCallback());
    }

    public void startEasyConnectAsEnrolleeInitiator(String str) {
        this.mIsGoingInitiator = true;
        ((WifiManager) getApplication().getSystemService(WifiManager.class)).startEasyConnectAsEnrolleeInitiator(str, getApplication().getMainExecutor(), new EasyConnectDelegateCallback());
    }

    private class EasyConnectDelegateCallback extends EasyConnectStatusCallback {
        public void onProgress(int i) {
        }

        private EasyConnectDelegateCallback() {
        }

        public void onEnrolleeSuccess(int i) {
            WifiDppInitiatorViewModel.this.mIsGoingInitiator = false;
            WifiDppInitiatorViewModel.this.mEnrolleeSuccessNetworkId.setValue(Integer.valueOf(i));
        }

        public void onConfiguratorSuccess(int i) {
            WifiDppInitiatorViewModel.this.mIsGoingInitiator = false;
            WifiDppInitiatorViewModel.this.mStatusCode.setValue(1);
        }

        public void onFailure(int i) {
            WifiDppInitiatorViewModel.this.mIsGoingInitiator = false;
            WifiDppInitiatorViewModel.this.mStatusCode.setValue(Integer.valueOf(i));
        }
    }
}
