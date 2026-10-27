package com.byd.vrassistant;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.byd.vrassistant.bean.VRPowerStateBean;
import com.byd.vrassistant.bean.VRStateBean;

/* JADX INFO: loaded from: classes.dex */
public class BYDViewModel extends ViewModel {
    private MutableLiveData<VRStateBean> bydLiveData = new MutableLiveData<>();
    private MutableLiveData<VRPowerStateBean> powerLiveData = new MutableLiveData<>();

    public void setVRState(int i) {
        VRStateBean carLoadingBean = getCarLoadingBean();
        carLoadingBean.setLocalVRState(i);
        this.bydLiveData.postValue(carLoadingBean);
    }

    public int getVRState() {
        return getCarLoadingBean().getLocalVRState();
    }

    private VRStateBean getCarLoadingBean() {
        if (this.bydLiveData.getValue() != null) {
            return this.bydLiveData.getValue();
        }
        VRStateBean vRStateBean = new VRStateBean();
        vRStateBean.setLocalVRState(-1);
        return vRStateBean;
    }

    public MutableLiveData<VRPowerStateBean> getPowerLiveData() {
        return this.powerLiveData;
    }

    public VRPowerStateBean getPowerStateBean() {
        if (this.powerLiveData.getValue() != null) {
            return this.powerLiveData.getValue();
        }
        VRPowerStateBean vRPowerStateBean = new VRPowerStateBean();
        vRPowerStateBean.setLock(false);
        return vRPowerStateBean;
    }

    public void setPowerState(boolean z) {
        VRPowerStateBean powerStateBean = getPowerStateBean();
        powerStateBean.setLock(z);
        this.powerLiveData.postValue(powerStateBean);
    }

    public boolean getPowerState() {
        return getPowerStateBean().isLock();
    }
}
