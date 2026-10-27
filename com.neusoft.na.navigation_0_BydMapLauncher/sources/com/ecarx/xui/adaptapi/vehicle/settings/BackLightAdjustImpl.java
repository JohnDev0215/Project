package com.ecarx.xui.adaptapi.vehicle.settings;

import com.ecarx.xui.adaptapi.vehicle.settings.IBackLightAdjust;

/* JADX INFO: loaded from: classes2.dex */
public class BackLightAdjustImpl implements IBackLightAdjust {
    private static final String TAG = "AmbientLightAdjustImpl";

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IBackLightAdjust
    public int getLevel() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IBackLightAdjust
    public void registerCallBack(IBackLightAdjust.CallBack callBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IBackLightAdjust
    public void setLevel(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IBackLightAdjust
    public void unregisterCallBack(IBackLightAdjust.CallBack callBack) {
    }
}
