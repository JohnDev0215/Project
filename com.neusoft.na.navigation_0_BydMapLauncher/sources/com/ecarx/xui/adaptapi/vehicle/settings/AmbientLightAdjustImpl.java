package com.ecarx.xui.adaptapi.vehicle.settings;

import android.graphics.Color;
import android.util.Log;
import com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AmbientLightAdjustImpl implements IAmbientLightAdjust {
    private static final String TAG = "AmbientLightAdjustImpl";

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust
    public int getAmbientLightColor() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust
    public int[] getSupportedAmbientLightColor() {
        return new int[0];
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust
    public void registerCallBack(IAmbientLightAdjust.CallBack callBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust
    public void setAmbientLightColor(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust
    public void unregisterCallBack(IAmbientLightAdjust.CallBack callBack) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust
    public void setAmbientLightCustomColor(int i) {
        int iRed = Color.red(i);
        int iGreen = Color.green(i);
        int iBlue = Color.blue(i);
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        bArr[1] = 1;
        bArr[4] = (byte) iRed;
        bArr[5] = (byte) iGreen;
        bArr[6] = (byte) iBlue;
        Log.d(TAG, "setAmbientLightCustomColor() result " + RpcManager.getInstance().sendMessage(1638, bArr) + "  " + Arrays.toString(bArr));
    }

    public static byte[] getEtcCustomColor() {
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        byte[] message = RpcManager.getInstance().getMessage(1648, bArr);
        Log.d(TAG, "getEtcCustomColor: " + Arrays.toString(message));
        return message;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust
    public int getAmbientLightCustomColor() {
        byte[] etcCustomColor = getEtcCustomColor();
        if (etcCustomColor != null) {
            int iRgb = Color.rgb(etcCustomColor[6] & 255, etcCustomColor[7] & 255, etcCustomColor[8] & 255);
            Log.d(TAG, "color: " + iRgb);
            return iRgb;
        }
        Log.d(TAG, "data is null");
        return 0;
    }
}
