package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.q0;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ p f559a;
    public final /* synthetic */ p.b b;
    public final /* synthetic */ String c;
    public final /* synthetic */ d3 d;
    public final /* synthetic */ q0.d e;

    public t0(q0.d dVar, p pVar, p.b bVar, String str, d3 d3Var) {
        this.e = dVar;
        this.f559a = pVar;
        this.b = bVar;
        this.c = str;
        this.d = d3Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        p pVar = this.f559a;
        p.b bVar = this.b;
        String str = this.c;
        q0.d dVar = this.e;
        pVar.a(bVar, str, dVar.f, this.d, true, dVar.k, dVar.h, (Bundle) null);
    }
}
