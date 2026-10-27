package com.amazon.identity.auth.device;

import android.content.Context;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class l4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f418a;

    public l4(Context context) {
        this.f418a = context;
    }

    public zb a(String str, y5 y5Var) {
        d3 d3VarA;
        Context context = this.f418a;
        Context context2 = this.f418a;
        synchronized (e3.class) {
            d3VarA = e3.a(context2, str, null);
        }
        return new lb(context, new tc(d3VarA), y5Var);
    }
}
