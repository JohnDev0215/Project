package com.amazon.identity.auth.device;

import android.content.Context;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class v {
    public static final String c = "com.amazon.identity.auth.device.v";
    public static final Object[] d = new Object[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f621a;
    public final f8 b;

    public v(Context context) {
        t5 t5VarA = t5.a(context);
        this.f621a = t5VarA;
        this.b = f8.a(t5VarA, "account_removed_flag_store");
    }
}
