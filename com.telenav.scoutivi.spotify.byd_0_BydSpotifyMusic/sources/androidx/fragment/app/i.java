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
public abstract class i<E> extends f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Activity f2335e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Context f2336g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Handler f2337h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f2338i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final FragmentManager f2339j;

    i(d dVar) {
        this(dVar, dVar, new Handler(), 0);
    }

    @Override // androidx.fragment.app.f
    public View f(int i7) {
        return null;
    }

    @Override // androidx.fragment.app.f
    public boolean h() {
        return true;
    }

    Activity i() {
        return this.f2335e;
    }

    Context j() {
        return this.f2336g;
    }

    Handler l() {
        return this.f2337h;
    }

    public void m(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E n();

    public LayoutInflater o() {
        return LayoutInflater.from(this.f2336g);
    }

    public boolean p(Fragment fragment) {
        return true;
    }

    public void q() {
    }

    i(Activity activity, Context context, Handler handler, int i7) {
        this.f2339j = new l();
        this.f2335e = activity;
        this.f2336g = (Context) h0.h.g(context, "context == null");
        this.f2337h = (Handler) h0.h.g(handler, "handler == null");
        this.f2338i = i7;
    }
}
