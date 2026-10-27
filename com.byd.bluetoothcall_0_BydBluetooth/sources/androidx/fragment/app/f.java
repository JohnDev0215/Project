package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
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
    private final Activity f1372b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1373c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f1374d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f1375e;
    final h f;

    f(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // androidx.fragment.app.c
    public View b(int i) {
        return null;
    }

    @Override // androidx.fragment.app.c
    public boolean c() {
        return true;
    }

    Activity d() {
        return this.f1372b;
    }

    Context e() {
        return this.f1373c;
    }

    Handler f() {
        return this.f1374d;
    }

    void g(Fragment fragment) {
    }

    public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E i();

    public LayoutInflater j() {
        return LayoutInflater.from(this.f1373c);
    }

    public int k() {
        return this.f1375e;
    }

    public boolean l() {
        return true;
    }

    public void m(Fragment fragment, String[] strArr, int i) {
    }

    public boolean n(Fragment fragment) {
        return true;
    }

    public boolean o(String str) {
        return false;
    }

    public void p(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.f1373c.startActivity(intent);
    }

    public void q(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        androidx.core.app.a.r(this.f1372b, intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void r() {
    }

    f(Activity activity, Context context, Handler handler, int i) {
        this.f = new h();
        this.f1372b = activity;
        this.f1373c = (Context) b.g.j.h.d(context, "context == null");
        this.f1374d = (Handler) b.g.j.h.d(handler, "handler == null");
        this.f1375e = i;
    }
}
