package com.ecarx.xui.adaptapi.impl;

import android.util.Log;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class FaderBalanceImpl implements IFaderBalance {
    private final String TAG = "FaderBalanceImpl";
    short balance = 9;
    short fader = 9;

    public int changeLevel(int i) {
        return (i < -9 || i > 9) ? i : i + 9;
    }

    public int levelChange(int i) {
        return (i < 0 || i > 18) ? i : i - 9;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public short[] getFaderLevelRange() {
        return new short[]{-9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public void setFaderLevel(short s) {
        changeLevel(s);
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        bArr[1] = (byte) s;
        bArr[2] = (byte) this.balance;
        Log.d("FaderBalanceImpl", "setBalanceLevel() result " + RpcManager.getInstance().sendMessage(777, bArr) + " array: " + Arrays.toString(bArr));
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public short[] getBalanceLevelRange() {
        return new short[]{-9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public short getBalanceLevel() {
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        short s = (short) (RpcManager.getInstance().getMessage(805, bArr)[2] & 255);
        this.balance = s;
        levelChange(s);
        return this.balance;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public short getFaderLevel() {
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        short s = (short) (RpcManager.getInstance().getMessage(805, bArr)[1] & 255);
        this.fader = s;
        levelChange(s);
        return this.fader;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public void setBalanceLevel(short s) {
        changeLevel(s);
        byte[] bArr = new byte[25];
        bArr[0] = 1;
        bArr[1] = (byte) this.fader;
        bArr[2] = (byte) s;
        Log.d("FaderBalanceImpl", "setBalanceLevel() result " + RpcManager.getInstance().sendMessage(777, bArr) + " array: " + Arrays.toString(bArr));
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public FunctionStatus isFaderSupported() {
        FunctionStatus functionStatus = FunctionStatus.active;
        Log.d("FaderBalanceImpl", "isFaderSupported() result " + functionStatus);
        return functionStatus;
    }

    @Override // com.ecarx.xui.adaptapi.audio.audiofx.IFaderBalance
    public FunctionStatus isBalanceSupported() {
        FunctionStatus functionStatus = FunctionStatus.active;
        Log.d("FaderBalanceImpl", "isBalanceSupported() result " + functionStatus);
        return functionStatus;
    }
}
