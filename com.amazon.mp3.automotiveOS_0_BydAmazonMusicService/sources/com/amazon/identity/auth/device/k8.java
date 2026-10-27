package com.amazon.identity.auth.device;

import android.content.Context;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class k8 {
    public static long e = TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);
    public static final String f = k8.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f407a = new Object[0];
    public final t5 b;
    public final h4 c;
    public final w5 d;

    public k8(Context context) {
        t5 t5VarA = t5.a(context);
        this.b = t5VarA;
        this.c = (h4) t5VarA.getSystemService("sso_alarm_maanger");
        this.d = (w5) t5VarA.getSystemService("dcp_system");
    }

    public final void a(Long l) {
        f8 f8Var = new f8(this.b, "sync_dirty_data_store", 0);
        if (l != null) {
            f8Var.a("sync_dirty_data_store_time", l.longValue());
        } else {
            f8Var.c("sync_dirty_data_store_time");
        }
    }
}
