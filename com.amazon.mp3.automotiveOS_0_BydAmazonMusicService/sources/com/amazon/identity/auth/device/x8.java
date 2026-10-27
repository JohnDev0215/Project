package com.amazon.identity.auth.device;

import android.content.Context;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class x8 {
    public static final long c = wa.b(1, TimeUnit.MILLISECONDS);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c8 f660a;
    public final i5 b;

    public x8(Context context, c8 c8Var) {
        this.b = (i5) t5.a(context).getSystemService("sso_platform");
        this.f660a = c8Var;
    }
}
