package com.ecarx.xui.adaptapi.navigation.dr;

import android.os.SystemClock;
import com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class PulseInfo implements IDrAPInfo.IPulse {
    private static final String TAG = "PulseInfo";
    public static boolean reverse_flag = false;
    private int Count = 0;
    private float mSpeed;

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IPulse
    public int getInterval() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IPulse
    public float getValue() {
        int i = this.Count;
        if (i == 10) {
            LogTool.d(TAG, "getVehicleSpeed = " + this.mSpeed);
            this.Count = 0;
        } else {
            this.Count = i + 1;
        }
        return this.mSpeed;
    }

    @Override // com.ecarx.xui.adaptapi.navigation.dr.IDrAPInfo.IPulse
    public long getTickTime() {
        return SystemClock.elapsedRealtime();
    }

    public void setReverse_flag(boolean z) {
        reverse_flag = z;
    }

    public void setmSpeed(float f) {
        this.mSpeed = f;
    }

    private boolean getRpcOK(byte[] bArr, int i) {
        if (bArr != null && bArr.length > i) {
            return true;
        }
        LogTool.d(TAG, "RPC not get");
        return false;
    }
}
