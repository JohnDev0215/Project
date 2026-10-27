package androidx.navigation;

import android.os.Bundle;

/* JADX INFO: compiled from: NavAction.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f2634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private o f2635b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Bundle f2636c;

    public c(int i7) {
        this(i7, null);
    }

    public Bundle a() {
        return this.f2636c;
    }

    public int b() {
        return this.f2634a;
    }

    public o c() {
        return this.f2635b;
    }

    public void d(Bundle bundle) {
        this.f2636c = bundle;
    }

    public void e(o oVar) {
        this.f2635b = oVar;
    }

    public c(int i7, o oVar) {
        this(i7, oVar, null);
    }

    public c(int i7, o oVar, Bundle bundle) {
        this.f2634a = i7;
        this.f2635b = oVar;
        this.f2636c = bundle;
    }
}
