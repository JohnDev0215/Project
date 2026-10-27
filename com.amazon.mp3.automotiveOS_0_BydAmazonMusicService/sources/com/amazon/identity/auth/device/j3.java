package com.amazon.identity.auth.device;

import android.content.Context;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class j3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f391a;
    public final o5 b;

    public j3(Context context) {
        t5 t5VarA = t5.a(context);
        this.f391a = t5VarA;
        this.b = (o5) t5VarA.getSystemService("dcp_device_info");
    }
}
