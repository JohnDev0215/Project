package com.neusoft.optimus.utility.offlineconfig;

import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsVehicleConfig {
    private final String cTag = "AbsVehicleConfig";
    private byte[] configByte;

    abstract String[] getArray();

    public byte[] getVehicleCfg(int i) {
        String[] array = getArray();
        LogTool.d("AbsVehicleConfig", "vehicleConfigArray =" + array);
        if (i >= array.length) {
            LogTool.e("AbsVehicleConfig", "车型配置错误！！找不到车型配置码，默认取第0位车型码");
            LogTool.e("AbsVehicleConfig", "vehicleType Error！！set default is 0");
            i = 0;
        }
        String str = array[i];
        LogTool.d("AbsVehicleConfig", "getVehicleCfg cfg=" + str);
        byte[] bArrConvHexStringToByte = convHexStringToByte(str);
        this.configByte = bArrConvHexStringToByte;
        return bArrConvHexStringToByte;
    }

    private byte[] convHexStringToByte(String str) {
        int i;
        byte[] bArr = new byte[25];
        int i2 = 0;
        bArr[0] = 0;
        while (i2 <= str.length() - 2 && (i = i2 / 2) < 24) {
            int i3 = i2 + 2;
            String strSubstring = str.substring(i2, i3);
            LogTool.d("AbsVehicleConfig", "convHexStringToByte tmp=" + strSubstring);
            bArr[i + 1] = (byte) Integer.decode("0x" + strSubstring).intValue();
            i2 = i3;
        }
        return bArr;
    }
}
