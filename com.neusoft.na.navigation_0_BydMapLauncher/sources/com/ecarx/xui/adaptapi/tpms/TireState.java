package com.ecarx.xui.adaptapi.tpms;

import android.util.Log;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TireState implements ITireState {
    private int mTire;
    private final int cGetRpcOpeCode = 1881;
    private final String TAG = "TireState";
    private int TireWarningType = 0;

    public int getUnsignedByte(byte b) {
        return b & 255;
    }

    public TireState(int i) {
        this.mTire = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0048  */
    @Override // com.ecarx.xui.adaptapi.tpms.ITireState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean hasPressureWarning() {
        /*
            r7 = this;
            r0 = 25
            byte[] r0 = new byte[r0]
            r1 = 0
            r0[r1] = r1
            r2 = 1
            r3 = 3
            r0[r2] = r3
            r4 = 18
            r5 = 2
            r0[r5] = r4
            com.neusoft.alfus.RpcManager r4 = com.neusoft.alfus.RpcManager.getInstance()
            r6 = 1881(0x759, float:2.636E-42)
            byte[] r0 = r4.getMessage(r6, r0)
            int r4 = r7.mTire
            java.lang.String r6 = "TireState"
            if (r4 == r2) goto L42
            if (r4 == r5) goto L3b
            if (r4 == r3) goto L34
            r3 = 4
            if (r4 == r3) goto L2d
            java.lang.String r0 = "Tire number is undefined"
            android.util.Log.d(r6, r0)
            goto L49
        L2d:
            r3 = 11
            r0 = r0[r3]
            if (r0 != r5) goto L49
            goto L48
        L34:
            r3 = 9
            r0 = r0[r3]
            if (r0 != r5) goto L49
            goto L48
        L3b:
            r3 = 10
            r0 = r0[r3]
            if (r0 != r5) goto L49
            goto L48
        L42:
            r3 = 8
            r0 = r0[r3]
            if (r0 != r5) goto L49
        L48:
            r1 = r2
        L49:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "hasPressureWarning tire number = "
            r0.<init>(r2)
            int r2 = r7.mTire
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = ", result = "
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r6, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.tpms.TireState.hasPressureWarning():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0077  */
    @Override // com.ecarx.xui.adaptapi.tpms.ITireState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isQuickLeaking() {
        /*
            r11 = this;
            r0 = 25
            byte[] r1 = new byte[r0]
            r2 = 0
            r1[r2] = r2
            r3 = 1
            r4 = 5
            r1[r3] = r4
            r5 = 19
            r6 = 2
            r1[r6] = r5
            byte[] r5 = new byte[r0]
            r5[r2] = r2
            r5[r3] = r4
            r7 = 20
            r5[r6] = r7
            byte[] r7 = new byte[r0]
            r7[r2] = r2
            r7[r3] = r4
            r8 = 21
            r7[r6] = r8
            byte[] r0 = new byte[r0]
            r0[r2] = r2
            r0[r3] = r4
            r4 = 22
            r0[r6] = r4
            int r4 = r11.mTire
            java.lang.String r8 = "TireState"
            r9 = 1881(0x759, float:2.636E-42)
            r10 = 3
            if (r4 == r3) goto L6b
            if (r4 == r6) goto L5e
            if (r4 == r10) goto L51
            r1 = 4
            if (r4 == r1) goto L44
            java.lang.String r0 = "Tire number is undefined"
            android.util.Log.d(r8, r0)
            goto L78
        L44:
            com.neusoft.alfus.RpcManager r1 = com.neusoft.alfus.RpcManager.getInstance()
            byte[] r0 = r1.getMessage(r9, r0)
            r0 = r0[r10]
            if (r0 != r3) goto L78
            goto L77
        L51:
            com.neusoft.alfus.RpcManager r0 = com.neusoft.alfus.RpcManager.getInstance()
            byte[] r0 = r0.getMessage(r9, r5)
            r0 = r0[r10]
            if (r0 != r3) goto L78
            goto L77
        L5e:
            com.neusoft.alfus.RpcManager r0 = com.neusoft.alfus.RpcManager.getInstance()
            byte[] r0 = r0.getMessage(r9, r7)
            r0 = r0[r10]
            if (r0 != r3) goto L78
            goto L77
        L6b:
            com.neusoft.alfus.RpcManager r0 = com.neusoft.alfus.RpcManager.getInstance()
            byte[] r0 = r0.getMessage(r9, r1)
            r0 = r0[r10]
            if (r0 != r3) goto L78
        L77:
            r2 = r3
        L78:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "isQuickLeaking tire number = "
            r0.<init>(r1)
            int r1 = r11.mTire
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = ", result = "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r8, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.tpms.TireState.isQuickLeaking():boolean");
    }

    @Override // com.ecarx.xui.adaptapi.tpms.ITireState
    public int getTemperature() {
        Log.d("TireState", "getTemperature get temperature not supported return -600");
        return -600;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0088 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008e  */
    @Override // com.ecarx.xui.adaptapi.tpms.ITireState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double getDetailTemperature() {
        /*
            r7 = this;
            r0 = 25
            byte[] r0 = new byte[r0]
            r1 = 0
            r0[r1] = r1
            r1 = 1
            r2 = 8
            r0[r1] = r2
            r3 = 2
            r4 = 7
            r0[r3] = r4
            com.neusoft.alfus.RpcManager r5 = com.neusoft.alfus.RpcManager.getInstance()
            r6 = 1881(0x759, float:2.636E-42)
            byte[] r0 = r5.getMessage(r6, r0)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "getDetailTemperature bytes:%s"
            r5.<init>(r6)
            java.lang.String r6 = java.util.Arrays.toString(r0)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "TireState"
            android.util.Log.d(r6, r5)
            int r5 = r7.mTire
            if (r5 == r1) goto L5a
            if (r5 == r3) goto L51
            r1 = 3
            if (r5 == r1) goto L4a
            r1 = 4
            if (r5 == r1) goto L41
            r0 = 0
            goto L61
        L41:
            r1 = 10
            r0 = r0[r1]
            int r0 = r7.getUnsignedByte(r0)
            goto L60
        L4a:
            r0 = r0[r2]
            int r0 = r7.getUnsignedByte(r0)
            goto L60
        L51:
            r1 = 9
            r0 = r0[r1]
            int r0 = r7.getUnsignedByte(r0)
            goto L60
        L5a:
            r0 = r0[r4]
            int r0 = r7.getUnsignedByte(r0)
        L60:
            double r0 = (double) r0
        L61:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getDetailTemperature num = "
            r2.<init>(r3)
            int r4 = r7.mTire
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = ", tyreTemp = "
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r6, r2)
            r4 = 4643176031446892544(0x406fe00000000000, double:255.0)
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L8e
            r0 = -4574883165222469632(0xc082c00000000000, double:-600.0)
            return r0
        L8e:
            r4 = 4632233691727265792(0x4049000000000000, double:50.0)
            double r4 = r0 - r4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            int r3 = r7.mTire
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ", result = "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r6, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.tpms.TireState.getDetailTemperature():double");
    }

    @Override // com.ecarx.xui.adaptapi.tpms.ITireState
    public float getPressure() {
        int unsignedByte;
        float f;
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        bArr[1] = 8;
        bArr[2] = 7;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        Log.d("TireState", "getPressure _onCallback  bytes:%s" + Arrays.toString(message));
        int i = this.mTire;
        if (i == 1) {
            unsignedByte = getUnsignedByte(message[3]);
        } else if (i == 2) {
            unsignedByte = getUnsignedByte(message[5]);
        } else if (i == 3) {
            unsignedByte = getUnsignedByte(message[4]);
        } else if (i == 4) {
            unsignedByte = getUnsignedByte(message[6]);
        } else {
            f = 0.0f;
            Log.d("TireState", "getTyrePr num = " + this.mTire + ", tyrePr = " + f);
            float f2 = (float) (((double) f) * 1.373d);
            Log.d("TireState", "getTyrePr num = " + this.mTire + ", result = " + f2);
            return f2;
        }
        f = unsignedByte;
        Log.d("TireState", "getTyrePr num = " + this.mTire + ", tyrePr = " + f);
        float f22 = (float) (((double) f) * 1.373d);
        Log.d("TireState", "getTyrePr num = " + this.mTire + ", result = " + f22);
        return f22;
    }

    @Override // com.ecarx.xui.adaptapi.tpms.ITireState
    public int getTireWarning() {
        return this.TireWarningType;
    }

    public void setTireWarning(int i) {
        this.TireWarningType = i;
    }
}
