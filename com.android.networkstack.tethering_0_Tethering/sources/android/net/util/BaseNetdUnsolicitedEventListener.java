package android.net.util;

import android.net.INetdUnsolicitedEventListener;

/* JADX INFO: loaded from: classes.dex */
public class BaseNetdUnsolicitedEventListener extends INetdUnsolicitedEventListener.Stub {
    @Override // android.net.INetdUnsolicitedEventListener
    public String getInterfaceHash() {
        return "63adaa5098e4d8621e90c5a84f7cb93505c79311";
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public int getInterfaceVersion() {
        return 4;
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceAddressRemoved(String str, String str2, int i, int i2) {
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceAddressUpdated(String str, String str2, int i, int i2) {
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceClassActivityChanged(boolean z, int i, long j, int i2) {
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onInterfaceDnsServerInfo(String str, long j, String[] strArr) {
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onQuotaLimitReached(String str, String str2) {
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onRouteChanged(boolean z, String str, String str2, String str3) {
    }

    @Override // android.net.INetdUnsolicitedEventListener
    public void onStrictCleartextDetected(int i, String str) {
    }
}
