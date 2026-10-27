package com.ecarx.xui.adaptapi.input;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ScreenApi {
    private static Screen _screen;

    public abstract void sendDataToScreen(byte[] bArr, int i);

    public abstract void setRecvDataFromScreenCallback(IScreenCallback iScreenCallback);

    public static ScreenApi create() {
        if (_screen == null) {
            _screen = new Screen();
        }
        return _screen;
    }
}
