package com.ecarx.xui.adaptapi.dvr;

/* JADX INFO: loaded from: classes2.dex */
public class RpcNotifiter {
    public void diapatchDvrLost(byte[] bArr) {
    }

    public void dispatchDisplayMessage(byte[] bArr) {
    }

    public void dispatchMessage(byte[] bArr) {
    }

    public void dispatchPepsAccMessage(byte[] bArr) {
    }

    public void dispatchPowerOffAccMessage(byte[] bArr) {
    }

    public void dispatchPowerOnAccMessage(byte[] bArr) {
    }

    public void dispatchSpeedMessage(byte[] bArr) {
    }

    public void getSystemMessage(byte[] bArr) {
    }

    private RpcNotifiter() {
    }

    private static class RpcNotifiterHolder {
        private static final RpcNotifiter sInstance = new RpcNotifiter();

        private RpcNotifiterHolder() {
        }
    }

    public static RpcNotifiter getInstance() {
        return RpcNotifiterHolder.sInstance;
    }
}
