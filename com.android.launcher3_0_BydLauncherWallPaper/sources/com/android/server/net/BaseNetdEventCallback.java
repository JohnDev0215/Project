package com.android.server.net;

import android.net.INetdEventCallback;

/* JADX INFO: loaded from: classes4.dex */
public class BaseNetdEventCallback extends INetdEventCallback.Stub {
    @Override // android.net.INetdEventCallback
    public void onConnectEvent(String str, int i, long j, int i2) {
    }

    @Override // android.net.INetdEventCallback
    public void onDnsEvent(int i, int i2, int i3, String str, String[] strArr, int i4, long j, int i5) {
    }

    @Override // android.net.INetdEventCallback
    public void onNat64PrefixEvent(int i, boolean z, String str, int i2) {
    }

    @Override // android.net.INetdEventCallback
    public void onPrivateDnsValidationEvent(int i, String str, String str2, boolean z) {
    }
}
