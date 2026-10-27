package com.google.gson.internal.m;

import com.google.gson.q;
import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: compiled from: SqlTypesSupport.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f4143a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.gson.internal.k.d<? extends Date> f4144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.gson.internal.k.d<? extends Date> f4145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final q f4146d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final q f4147e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final q f4148f;

    /* JADX INFO: compiled from: SqlTypesSupport.java */
    class a extends com.google.gson.internal.k.d<java.sql.Date> {
        a(Class cls) {
            super(cls);
        }
    }

    /* JADX INFO: compiled from: SqlTypesSupport.java */
    class b extends com.google.gson.internal.k.d<Timestamp> {
        b(Class cls) {
            super(cls);
        }
    }

    static {
        boolean z;
        try {
            Class.forName("java.sql.Date");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        f4143a = z;
        if (z) {
            f4144b = new a(java.sql.Date.class);
            f4145c = new b(Timestamp.class);
            f4146d = com.google.gson.internal.m.a.f4137a;
            f4147e = com.google.gson.internal.m.b.f4139a;
            f4148f = c.f4141a;
            return;
        }
        f4144b = null;
        f4145c = null;
        f4146d = null;
        f4147e = null;
        f4148f = null;
    }
}
