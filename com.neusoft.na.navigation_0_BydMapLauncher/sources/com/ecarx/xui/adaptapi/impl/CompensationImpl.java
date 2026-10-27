package com.ecarx.xui.adaptapi.impl;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.audio.audiofx.ICompensation;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class CompensationImpl implements ICompensation {
    private final String TAG = "CompensationImpl";
    private Context mContext;

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public int getEffectEnhanceMode() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public boolean isLoudnessEnabled() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public boolean registerCompensationSettingListener(ICompensation.ICompensationSettingListener iCompensationSettingListener) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public boolean setEffectEnhanceMode(int i) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public void setLoudnessEnable(boolean z) {
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public boolean unregisterCompensationSettingListener(ICompensation.ICompensationSettingListener iCompensationSettingListener) {
        return false;
    }

    public CompensationImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public boolean isSpeedCompensatedVolumeEnabled() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "speedModeDriveSwitch", 1) == 1;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public void setSpeedCompensatedVolumeEnable(boolean z) {
        Log.d("CompensationImpl", "setSpeedCompensatedVolumeEnable() enable is " + z);
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "speedModeDrive", 2);
        int i2 = i == 1 ? 99 : i == 2 ? 100 : i == 3 ? 101 : 0;
        if (z) {
            setCompensationLevelOfSpeedCompensatedVolume(i2);
        } else {
            setCompensationLevelOfSpeedCompensatedVolume(0);
        }
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public int getCompensationLevelOfSpeedCompensatedVolume() {
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        byte[] message = RpcManager.getInstance().getMessage(822, bArr);
        if (message != null) {
            Log.d("CompensationImpl", "getCompensationLevelOfSpeedCompensatedVolume() data is " + Arrays.toString(message));
            byte b = message[11];
            if (b == 1) {
                return 99;
            }
            if (b == 2) {
                return 100;
            }
            return b == 3 ? 101 : 0;
        }
        Log.d("CompensationImpl", "getCompensationLevelOfSpeedCompensatedVolume() data is null.return -1");
        return -1;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public void setCompensationLevelOfSpeedCompensatedVolume(int i) {
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        if (i != 0) {
            switch (i) {
                case 99:
                    bArr[1] = (byte) 1;
                    Log.d("CompensationImpl", "setCompensationLevelOfSpeedCompensatedVolume() result " + RpcManager.getInstance().sendMessage(910, bArr) + "  " + Arrays.toString(bArr));
                    Settings.System.putInt(this.mContext.getContentResolver(), "speedModeDriveSwitch", 1);
                    break;
                case 100:
                    bArr[1] = (byte) 2;
                    Log.d("CompensationImpl", "setCompensationLevelOfSpeedCompensatedVolume() result " + RpcManager.getInstance().sendMessage(910, bArr) + "  " + Arrays.toString(bArr));
                    Settings.System.putInt(this.mContext.getContentResolver(), "speedModeDriveSwitch", 1);
                    break;
                case 101:
                    bArr[1] = (byte) 3;
                    Log.d("CompensationImpl", "setCompensationLevelOfSpeedCompensatedVolume() result " + RpcManager.getInstance().sendMessage(910, bArr) + "  " + Arrays.toString(bArr));
                    Settings.System.putInt(this.mContext.getContentResolver(), "speedModeDriveSwitch", 1);
                    break;
            }
        }
        bArr[1] = (byte) 0;
        Log.d("CompensationImpl", "setCompensationLevelOfSpeedCompensatedVolume() result " + RpcManager.getInstance().sendMessage(910, bArr) + "  " + Arrays.toString(bArr));
        Settings.System.putInt(this.mContext.getContentResolver(), "speedModeDriveSwitch", 0);
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public int[] getSupportedEffectEnhanceMode() {
        return new int[]{1, 4, 5};
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.ICompensation
    public FunctionStatus isCompensationSettingSupported(int i) {
        if (2 == i || 3 == i) {
            FunctionStatus functionStatus = FunctionStatus.active;
            Log.d("CompensationImpl", "isCompensationSettingSupported() result " + functionStatus);
            return functionStatus;
        }
        return FunctionStatus.notavailable;
    }
}
