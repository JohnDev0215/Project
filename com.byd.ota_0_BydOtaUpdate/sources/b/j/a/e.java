package b.j.a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<E> extends b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Activity f1868c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f1869d;
    public final Handler s;
    public final int x;
    public final g y;

    public e(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // b.j.a.b
    public View e(int i2) {
        return null;
    }

    @Override // b.j.a.b
    public boolean f() {
        return true;
    }

    public Activity g() {
        return this.f1868c;
    }

    public Context i() {
        return this.f1869d;
    }

    public Handler j() {
        return this.s;
    }

    public void k(Fragment fragment) {
    }

    public void l(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E m();

    public LayoutInflater n() {
        return LayoutInflater.from(this.f1869d);
    }

    public int o() {
        return this.x;
    }

    public boolean p() {
        return true;
    }

    public boolean q(Fragment fragment) {
        return true;
    }

    public void r() {
    }

    public e(Activity activity, Context context, Handler handler, int i2) {
        this.y = new g();
        this.f1868c = activity;
        this.f1869d = (Context) b.f.l.f.c(context, "context == null");
        this.s = (Handler) b.f.l.f.c(handler, "handler == null");
        this.x = i2;
    }
}
