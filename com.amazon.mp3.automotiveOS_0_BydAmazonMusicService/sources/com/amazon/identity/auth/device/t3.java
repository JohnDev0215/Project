package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.m3;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class t3 {
    public static volatile t3 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public o5 f562a;
    public s3 b;

    public t3(Context context) {
        boolean zA = qe.a(context, o3.d);
        if (zA && qe.j(context)) {
            this.f562a = new j4(context);
            this.b = new u3(new o3(t5.a(context)), context);
            return;
        }
        if (qe.h(context)) {
            k3 k3VarA = k3.a(context, zA);
            this.f562a = k3VarA.b();
            this.b = k3VarA;
        } else if (qe.d(context)) {
            this.f562a = new m3.a(context);
            this.b = q3.a(context);
        } else {
            this.f562a = new j4(context);
            this.b = q3.a(context);
        }
    }

    public static s3 a(Context context) {
        return b(context).b;
    }

    public static t3 b(Context context) {
        if (c == null) {
            synchronized (t3.class) {
                if (c == null) {
                    c = new t3(context);
                }
            }
        }
        return c;
    }
}
