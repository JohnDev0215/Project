package com.byd.systemsettings.cpaa;

import android.content.Context;
import java.util.List;
import o2.a;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes2.dex */
public class DxDataService implements a {
    private Context mContext;

    public DxDataService(Context context) {
        this.mContext = context;
    }

    @Override // o2.a
    public List<AndroidAutoDevice> getAndroidAutoDevices() {
        return AndroidAutoManager.getInstance(this.mContext).getAndroidAutoDevices();
    }

    @Override // o2.a
    public List<CarPlayDevice> getCarPlayDevices() {
        return CarPlayManager.getInstance(this.mContext).getCarPlayDevices();
    }

    public n2.a getCurCountryInfo() {
        return null;
    }
}
