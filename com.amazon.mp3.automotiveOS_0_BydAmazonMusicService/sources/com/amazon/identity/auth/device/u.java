package com.amazon.identity.auth.device;

import android.content.Context;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f596a;
    public final u7 b;

    public u(Context context) {
        t5 t5VarA = t5.a(context.getApplicationContext());
        this.f596a = t5VarA;
        this.b = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
    }
}
