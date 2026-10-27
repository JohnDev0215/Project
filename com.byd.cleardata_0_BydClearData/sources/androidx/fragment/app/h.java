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

/* JADX INFO: loaded from: classes.dex */
public abstract class h<E> extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Activity f1287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1288c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f1289d;
    private final int e;
    final j f;

    h(Activity activity, Context context, Handler handler, int i) {
        this.f = new j();
        this.f1287b = activity;
        this.f1288c = (Context) a.g.j.h.d(context, "context == null");
        this.f1289d = (Handler) a.g.j.h.d(handler, "handler == null");
        this.e = i;
    }

    h(d dVar) {
        this(dVar, dVar, new Handler(), 0);
    }

    @Override // androidx.fragment.app.e
    public View e(int i) {
        return null;
    }

    @Override // androidx.fragment.app.e
    public boolean f() {
        return true;
    }

    Activity g() {
        return this.f1287b;
    }

    Context h() {
        return this.f1288c;
    }

    Handler i() {
        return this.f1289d;
    }

    void j(Fragment fragment) {
    }

    public void k(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E l();

    public LayoutInflater m() {
        return LayoutInflater.from(this.f1288c);
    }

    public int n() {
        return this.e;
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
        this.f1288c.startActivity(intent);
    }

    public void s() {
    }
}
