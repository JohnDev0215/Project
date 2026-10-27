package com.ecarx.xui.adaptapi.diminteraction;

import android.content.Context;
import android.util.Log;
import com.neusoft.alfus.RpcManager;
import com.neusoft.optimus.utility.log.LogTool;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class MainDimInteraction extends DimInteraction {
    private byte[] _bytes = new byte[1];
    private Context mContext;
    private MediaInteraction mediaInteraction;
    private PhoneCallInteraction phoneCallInteraction;
    private VrInteraction vrInteraction;

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public int getSupportedRankingType() {
        return 0;
    }

    public MainDimInteraction(Context context) {
        this.mContext = context;
        String packageName = context.getPackageName();
        Log.d("diminteraction", "MainDimInteraction  getPackageName : " + packageName);
        if ("com.ecarx.btphone".equals(packageName)) {
            this.phoneCallInteraction = new PhoneCallInteraction(this.mContext);
        } else if ("ecarx.xsf.mediacenter".equals(packageName)) {
            this.mediaInteraction = new MediaInteraction(this.mContext);
        } else if ("ecarx.voiceservice".equals(packageName)) {
            this.vrInteraction = new VrInteraction(this.mContext);
        }
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IMediaInteraction getMediaInteraction() {
        Log.d("diminteraction", "MainDimInteraction  getMediaInteraction");
        return this.mediaInteraction;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IVrInteraction getVrInteraction() {
        Log.d("diminteraction", "MainDimInteraction  getVrInteraction");
        return this.vrInteraction;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public IPhoneCallInteraction getPhoneCallInteraction() {
        Log.d("diminteraction", "MainDimInteraction  getPhoneCallInteraction");
        return this.phoneCallInteraction;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.DimInteraction
    public void updateAvgFuleRanking(int i, String str) {
        Log.d("diminteraction", "rankingInfo : " + str + " type: " + i);
        try {
            int i2 = Integer.parseInt(str);
            if (i2 == Integer.MIN_VALUE) {
                i2 = 101;
            }
            Log.d("diminteraction", "rankNum : " + i2);
            setInfo((byte) 17, getByteArray((byte) i2));
        } catch (Exception e) {
            Log.d("diminteraction", "rankingInfo err :" + Log.getStackTraceString(e));
        }
    }

    private byte[] getByteArray(byte b) {
        byte[] bArr = this._bytes;
        bArr[0] = b;
        return (byte[]) bArr.clone();
    }

    private void setInfo(byte b, byte[] bArr) {
        RpcManager.getInstance().sendMessage(1898, messagePackaging(b, bArr));
    }

    private static byte[] messagePackaging(byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArrArray = ByteBuffer.allocate(bArr.length + 1).put(b).put(bArr).array();
            printSendArray(bArrArray);
            return bArrArray;
        }
        LogTool.d("diminteraction", "data is null");
        return null;
    }

    private static void printSendArray(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toHexString(b & 255).toUpperCase()).append(StringUtils.SPACE);
        }
        LogTool.d("diminteraction", "sendArray:%s", sb.toString());
    }
}
