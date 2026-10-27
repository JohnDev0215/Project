package androidx.navigation;

import android.os.Bundle;

/* JADX INFO: compiled from: NavAction.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f1647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private n f1648b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Bundle f1649c;

    public c(int i2) {
        this(i2, null);
    }

    public Bundle a() {
        return this.f1649c;
    }

    public int b() {
        return this.f1647a;
    }

    public n c() {
        return this.f1648b;
    }

    public void d(Bundle bundle) {
        this.f1649c = bundle;
    }

    public void e(n nVar) {
        this.f1648b = nVar;
    }

    public c(int i2, n nVar) {
        this(i2, nVar, null);
    }

    public c(int i2, n nVar, Bundle bundle) {
        this.f1647a = i2;
        this.f1648b = nVar;
        this.f1649c = bundle;
    }
}
