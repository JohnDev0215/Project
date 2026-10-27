package com.ecarx.xui.adaptapi.vehicle.tcu;

import android.util.Log;
import com.ecarx.xui.adaptapi.Tribool;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TcuStatusImpl implements ITcuState {
    private static final int GETRPCOPECODE = 1881;
    private static final String TAG = "TcuStateImpl";

    @Override // com.ecarx.xui.adaptapi.vehicle.tcu.ITcuState
    public int getGear() {
        return -2147483647;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.tcu.ITcuState
    public int getGearSelectorPosition() {
        byte[] bArr = new byte[25];
        bArr[0] = 23;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null) {
            Log.d(TAG, "getGearSelectorPosition result = null");
            return Integer.MIN_VALUE;
        }
        Log.d(TAG, "getGearSelectorPosition result = " + Arrays.toString(message));
        switch (message[8]) {
        }
        return Integer.MIN_VALUE;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.tcu.ITcuState
    public Tribool isManualShiftMode() {
        return Tribool.indeterminate;
    }
}
