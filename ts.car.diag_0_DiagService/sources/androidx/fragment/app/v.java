package androidx.fragment.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class v implements androidx.savedstate.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private androidx.lifecycle.g f740c = null;
    private androidx.savedstate.a d = null;

    v() {
    }

    @Override // androidx.lifecycle.f
    @NonNull
    public Lifecycle a() {
        e();
        return this.f740c;
    }

    @Override // androidx.savedstate.b
    @NonNull
    public SavedStateRegistry c() {
        return this.d.b();
    }

    void d(@NonNull Lifecycle.Event event) {
        this.f740c.g(event);
    }

    void e() {
        if (this.f740c == null) {
            this.f740c = new androidx.lifecycle.g(this);
            this.d = androidx.savedstate.a.a(this);
        }
    }

    boolean f() {
        return this.f740c != null;
    }

    void g(@Nullable Bundle bundle) {
        this.d.c(bundle);
    }

    void h(@NonNull Bundle bundle) {
        this.d.d(bundle);
    }

    void i(@NonNull Lifecycle.State state) {
        this.f740c.n(state);
    }
}
