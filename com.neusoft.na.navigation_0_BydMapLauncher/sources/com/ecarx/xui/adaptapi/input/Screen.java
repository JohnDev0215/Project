package com.ecarx.xui.adaptapi.input;

import com.neusoft.optimus.utility.log.LogTool;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Screen extends ScreenApi {
    public static final String cTag = "Screen";
    private IScreenCallback iScreenCallback;

    public Screen() {
        try {
            LogTool.d(cTag, "ScreenApi--registerUsbProtectMsg");
        } catch (Exception e) {
            LogTool.d(cTag, "e" + e);
        }
    }

    @Override // com.ecarx.xui.adaptapi.input.ScreenApi
    public void sendDataToScreen(byte[] bArr, int i) {
        LogTool.d(cTag, "sendDataToScreen" + Arrays.toString(bArr) + "length" + i);
    }

    @Override // com.ecarx.xui.adaptapi.input.ScreenApi
    public void setRecvDataFromScreenCallback(IScreenCallback iScreenCallback) {
        LogTool.d(cTag, "setRecvDataFromScreenCallback" + iScreenCallback);
        this.iScreenCallback = iScreenCallback;
    }
}
