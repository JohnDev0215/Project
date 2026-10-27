package com.amazon.identity.auth.device;

import android.text.TextUtils;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f237a;
    public final String b;
    public final String c;

    public b3(String str, String str2, String str3) {
        this.f237a = TextUtils.isEmpty(str) ? "Unrecognized" : str;
        this.b = TextUtils.isEmpty(str2) ? "Unrecognized" : str2;
        this.c = TextUtils.isEmpty(str3) ? "No error index" : str3;
    }
}
