package defpackage;

import android.view.View;

/* JADX INFO: compiled from: FrozenNativePage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kq0 implements jk1 {
    public final String e;
    public final String f;
    public final String g;
    public final int h;

    public kq0(jk1 jk1Var) {
        this.f = jk1Var.k();
        this.e = jk1Var.getUrl();
        this.g = jk1Var.getTitle();
        this.h = jk1Var.r();
    }

    @Override // defpackage.jk1
    public final float f(float f) {
        return 1.0f;
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.g;
    }

    @Override // defpackage.jk1
    public final String getUrl() {
        return this.e;
    }

    @Override // defpackage.jk1
    public final View getView() {
        return null;
    }

    @Override // defpackage.jk1
    public final boolean isFrozen() {
        return true;
    }

    @Override // defpackage.jk1
    public final String k() {
        return this.f;
    }

    @Override // defpackage.jk1
    public final int r() {
        return this.h;
    }

    @Override // defpackage.jk1
    public final void destroy() {
    }

    @Override // defpackage.jk1
    public final int d(int i) {
        return i;
    }

    @Override // defpackage.jk1
    public final void e(String str) {
    }

    @Override // defpackage.jk1
    public final int g(int i) {
        return i;
    }
}
