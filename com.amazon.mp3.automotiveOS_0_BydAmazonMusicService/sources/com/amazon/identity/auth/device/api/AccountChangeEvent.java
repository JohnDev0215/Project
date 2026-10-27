package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AccountChangeEvent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f194a;
    public final String b;

    public AccountChangeEvent(String str, String str2) {
        this.f194a = str;
        this.b = str2;
    }

    @FireOsSdk
    public String getCurrentAccount() {
        return this.b;
    }

    @FireOsSdk
    public String getPreviousAccount() {
        return this.f194a;
    }
}
