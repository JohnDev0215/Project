package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class mb<E> extends jb {
    public final Activity b;
    public final Context c;
    public final Handler d;
    public final int e;
    public final ob f;

    public mb(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // defpackage.jb
    public View b(int i) {
        return null;
    }

    @Override // defpackage.jb
    public boolean c() {
        return true;
    }

    public Activity d() {
        return this.b;
    }

    public Context e() {
        return this.c;
    }

    public Handler f() {
        return this.d;
    }

    public void g(Fragment fragment) {
    }

    public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E i();

    public LayoutInflater j() {
        return LayoutInflater.from(this.c);
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
        this.c.startActivity(intent);
    }

    public void q(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (i != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.r(this.b, intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void r() {
    }

    public mb(Activity activity, Context context, Handler handler, int i) {
        this.f = new ob();
        this.b = activity;
        this.c = (Context) z8.d(context, "context == null");
        this.d = (Handler) z8.d(handler, "handler == null");
        this.e = i;
    }
}
