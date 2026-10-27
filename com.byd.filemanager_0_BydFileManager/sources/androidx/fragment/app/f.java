package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f<E> extends c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Activity f1124b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1125c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f1126d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f1127e;
    final h f;

    f(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // androidx.fragment.app.c
    public View e(int i) {
        return null;
    }

    @Override // androidx.fragment.app.c
    public boolean f() {
        return true;
    }

    Activity g() {
        return this.f1124b;
    }

    Context h() {
        return this.f1125c;
    }

    Handler i() {
        return this.f1126d;
    }

    void j(Fragment fragment) {
    }

    public void k(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E l();

    public LayoutInflater m() {
        return LayoutInflater.from(this.f1125c);
    }

    public int n() {
        return this.f1127e;
    }

    public boolean p() {
        return true;
    }

    public boolean q(Fragment fragment) {
        return true;
    }

    public void r(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.f1125c.startActivity(intent);
    }

    public void s() {
    }

    f(Activity activity, Context context, Handler handler, int i) {
        this.f = new h();
        this.f1124b = activity;
        this.f1125c = (Context) b.g.j.h.d(context, "context == null");
        this.f1126d = (Handler) b.g.j.h.d(handler, "handler == null");
        this.f1127e = i;
    }
}
