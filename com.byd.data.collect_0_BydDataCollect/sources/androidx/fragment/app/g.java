package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g<E> extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Activity f1435b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f1436c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f1437d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f1438e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final j f1439f;

    g(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // androidx.fragment.app.d
    public View c(int i2) {
        return null;
    }

    @Override // androidx.fragment.app.d
    public boolean f() {
        return true;
    }

    Activity g() {
        return this.f1435b;
    }

    Context h() {
        return this.f1436c;
    }

    Handler i() {
        return this.f1437d;
    }

    void k(Fragment fragment) {
    }

    public abstract E l();

    public LayoutInflater m() {
        return LayoutInflater.from(this.f1436c);
    }

    public boolean n(Fragment fragment) {
        return true;
    }

    public void o() {
    }

    g(Activity activity, Context context, Handler handler, int i2) {
        this.f1439f = new k();
        this.f1435b = activity;
        this.f1436c = (Context) b.g.j.h.d(context, "context == null");
        this.f1437d = (Handler) b.g.j.h.d(handler, "handler == null");
        this.f1438e = i2;
    }
}
