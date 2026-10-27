package b.k.a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f<E> extends c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Activity f1218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f1219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f1220d;
    public final int e;
    public final h f;

    public f(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // b.k.a.c
    public View b(int i) {
        return null;
    }

    @Override // b.k.a.c
    public boolean c() {
        return true;
    }

    public Activity d() {
        return this.f1218b;
    }

    public Context e() {
        return this.f1219c;
    }

    public Handler f() {
        return this.f1220d;
    }

    public void g(Fragment fragment) {
    }

    public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E i();

    public LayoutInflater j() {
        return LayoutInflater.from(this.f1219c);
    }

    public int k() {
        return this.e;
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
        this.f1219c.startActivity(intent);
    }

    public void q(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        b.g.a.a.r(this.f1218b, intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void r() {
    }

    public f(Activity activity, Context context, Handler handler, int i) {
        this.f = new h();
        this.f1218b = activity;
        this.f1219c = (Context) b.g.i.h.d(context, "context == null");
        this.f1220d = (Handler) b.g.i.h.d(handler, "handler == null");
        this.e = i;
    }
}
