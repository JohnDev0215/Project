package com.amazon.identity.auth.device;

import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class r1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f521a;
    public final /* synthetic */ y5 b;
    public final /* synthetic */ s1 c;

    public r1(s1 s1Var, String str, y5 y5Var) {
        this.c = s1Var;
        this.f521a = str;
        this.b = y5Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.c.a(this.f521a, this.b);
        } catch (Exception e) {
            Log.e(ga.a(s1.i), "Exception on refreshing COR/PFM from server side.", e);
        }
    }
}
