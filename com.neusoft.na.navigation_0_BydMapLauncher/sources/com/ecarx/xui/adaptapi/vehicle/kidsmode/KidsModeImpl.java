package com.ecarx.xui.adaptapi.vehicle.kidsmode;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;

/* JADX INFO: loaded from: classes2.dex */
public class KidsModeImpl implements IKidsMode {
    private String TAG = "KidsModeImpl";
    private Context mContext;

    public KidsModeImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.kidsmode.IKidsMode
    public FunctionStatus isKidsModeSupported(int i) {
        Log.d(this.TAG, "isKidsModeSupported mVehicleModel 到时候改");
        Log.d(this.TAG, "hasBabyMode false");
        FunctionStatus functionStatus = FunctionStatus.notactive;
        Log.d(this.TAG, "functionStatus " + functionStatus);
        return functionStatus;
    }
}
