package com.amazon.identity.auth.device;

import java.util.List;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class a8 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f174a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ List d;
    public final /* synthetic */ b8 e;

    public a8(b8 b8Var, String str, String str2, String str3, List list) {
        this.e = b8Var;
        this.f174a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.e.a(this.f174a, this.b, this.c, this.d);
    }
}
