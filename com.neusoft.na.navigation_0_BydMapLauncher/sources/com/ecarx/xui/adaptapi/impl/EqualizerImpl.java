package com.ecarx.xui.adaptapi.impl;

import android.content.Context;
import android.provider.Settings;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer;
import com.google.android.gms.common.util.GmsVersion;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes2.dex */
public class EqualizerImpl implements IEqualizer {
    private final String TAG = "EqualizerImpl";
    private Context mContext;

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public int[] getBandFreqRange(short s) {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public short getNumberOfBands() {
        return (short) 6;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public short getNumberOfPresets() {
        return (short) 7;
    }

    public EqualizerImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public short[] getBandLevelRange() {
        return new short[]{-9, 9};
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public void setBandLevel(short s, short s2) {
        short s3 = (short) (s2 + 9);
        LogTool.d("EqualizerImpl", "band " + ((int) s));
        if (s == 0) {
            Settings.System.putInt(this.mContext.getContentResolver(), "EQHZ", s3);
            return;
        }
        byte[] bArr = new byte[25];
        bArr[0] = 24;
        bArr[1] = (byte) (s - 1);
        bArr[2] = (byte) s3;
        LogTool.d("EqualizerImpl", "setBandLevel() result " + RpcManager.getInstance().sendMessage(825, bArr) + "  " + Arrays.toString(bArr));
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public short getBandLevel(short s) {
        int i;
        LogTool.d("EqualizerImpl", "requestEQHz");
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        byte[] message = RpcManager.getInstance().getMessage(827, bArr);
        if (s == 0) {
            i = Settings.System.getInt(this.mContext.getContentResolver(), "EQHZ", 6);
        } else {
            i = message[s] & 255;
        }
        return (short) (i - 9);
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public int getCenterFreq(short s) {
        LogTool.d("EqualizerImpl", "getCenterFreq = " + ((int) s));
        if (s == 0) {
            return 40000;
        }
        if (s == 1) {
            return 80000;
        }
        if (s != 2) {
            return s != 3 ? s != 4 ? s != 5 ? 0 : 16000000 : GmsVersion.VERSION_LONGHORN : DurationKt.NANOS_IN_MILLIS;
        }
        return 500000;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public short getBand(int i) {
        LogTool.d("EqualizerImpl", "getBand: " + i);
        switch (i) {
            case 40000:
                return (short) 0;
            case 80000:
                return (short) 1;
            case 500000:
                return (short) 2;
            case DurationKt.NANOS_IN_MILLIS /* 1000000 */:
                return (short) 3;
            case GmsVersion.VERSION_LONGHORN /* 5000000 */:
                return (short) 4;
            case 16000000:
                return (short) 5;
            default:
                return (short) 100;
        }
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public short getCurrentPreset() {
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        byte[] message = RpcManager.getInstance().getMessage(812, bArr);
        LogTool.d("EqualizerImpl", "getCurrentEqualizer() array: " + Arrays.toString(bArr));
        if (message != null) {
            LogTool.d("EqualizerImpl", "getCurrentEqualizer = " + Arrays.toString(message));
            return message[1];
        }
        LogTool.d("EqualizerImpl", "getCurrentEqualizer datas is null,return -1.");
        return (short) -1;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public void usePreset(short s) {
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        bArr[1] = (byte) s;
        LogTool.d("EqualizerImpl", "sendCurrentEQ() result " + RpcManager.getInstance().sendMessage(782, bArr) + "  " + Arrays.toString(bArr));
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public String getPresetName(short s) {
        LogTool.d("EqualizerImpl", "getPresetName: " + ((int) s));
        switch (s) {
            case 0:
                return "Standard";
            case 1:
                return "Popular";
            case 2:
                return "Rock";
            case 3:
                return "Jazz";
            case 4:
                return "Classical";
            case 5:
                return "Voices";
            case 6:
                return "Custom";
            default:
                LogTool.d("EqualizerImpl", "getPresetName is failure");
                return null;
        }
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IEqualizer
    public FunctionStatus isEqualizerSupported() {
        FunctionStatus functionStatus = FunctionStatus.active;
        LogTool.d("EqualizerImpl", "isEqualizerSupported() result " + functionStatus);
        return functionStatus;
    }
}
