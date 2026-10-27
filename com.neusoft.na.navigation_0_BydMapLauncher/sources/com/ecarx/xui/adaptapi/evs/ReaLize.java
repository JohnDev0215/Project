package com.ecarx.xui.adaptapi.evs;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.evs.EVS;

/* JADX INFO: loaded from: classes2.dex */
public class ReaLize extends EVS {
    public static final int CAMERA_ACT_STS_A = 5;
    public static final int CAMERA_ACT_STS_BR = 8;
    public static final int CAMERA_ACT_STS_M = 11;
    public static final int CAMERA_ACT_STS_MA = 13;
    public static final int CAMERA_ACT_STS_P = 9;
    public static final int CAMERA_ACT_STS_PA = 12;
    public static final int CAMERA_ACT_STS_R = 1;
    public static final int CAMERA_ACT_STS_RA = 6;
    public static final int CAMERA_ACT_STS_RR = 3;
    public static final int CAMERA_ACT_STS_T = 0;
    private boolean RVC;
    private EVS.IEvsCameraStatusObserver mObserver;

    private void exit() {
    }

    private void init() {
    }

    public ReaLize(Context context) {
        super(context);
    }

    @Override // com.ecarx.xui.adaptapi.evs.EVS
    public boolean attachEvsCameraStatusObserver(EVS.IEvsCameraStatusObserver iEvsCameraStatusObserver) {
        Log.e("ReaLize", "attachEvsCameraStatus");
        this.mObserver = iEvsCameraStatusObserver;
        init();
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.evs.EVS
    public boolean detachEvsCameraStatusObserver(EVS.IEvsCameraStatusObserver iEvsCameraStatusObserver) {
        Log.e("ReaLize", "detachEvsCameraStatus");
        this.mObserver = null;
        exit();
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.evs.EVS
    public boolean isCameraOpened(int i) {
        Log.e("ReaLize", "isCameraOpened:");
        return false;
    }
}
