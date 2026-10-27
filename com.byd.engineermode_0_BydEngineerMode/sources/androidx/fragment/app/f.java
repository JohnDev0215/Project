package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f<E> extends c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Activity f980b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f981c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f982d;
    private final int e;
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
        return this.f980b;
    }

    Context i() {
        return this.f981c;
    }

    Handler j() {
        return this.f982d;
    }

    void k(Fragment fragment) {
    }

    public void l(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E m();

    public LayoutInflater n() {
        return LayoutInflater.from(this.f981c);
    }

    public int o() {
        return this.e;
    }

    public boolean p() {
        return true;
    }

    public boolean q(Fragment fragment) {
        return true;
    }

    public void r() {
    }

    f(Activity activity, Context context, Handler handler, int i) {
        this.f = new h();
        this.f980b = activity;
        this.f981c = (Context) b.g.j.h.d(context, "context == null");
        this.f982d = (Handler) b.g.j.h.d(handler, "handler == null");
        this.e = i;
    }
}
