package r0;

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
public abstract class f<E> extends c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Activity f4679b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f4680c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f4681d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4682e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h f4683f;

    public f(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // r0.c
    public View b(int i3) {
        return null;
    }

    @Override // r0.c
    public boolean c() {
        return true;
    }

    public Activity d() {
        return this.f4679b;
    }

    public Context e() {
        return this.f4680c;
    }

    public Handler f() {
        return this.f4681d;
    }

    public void g(Fragment fragment) {
    }

    public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E i();

    public LayoutInflater j() {
        return LayoutInflater.from(this.f4680c);
    }

    public int k() {
        return this.f4682e;
    }

    public boolean l() {
        return true;
    }

    public void m(Fragment fragment, String[] strArr, int i3) {
    }

    public boolean n(Fragment fragment) {
        return true;
    }

    public boolean o(String str) {
        return false;
    }

    public void p(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i3, Bundle bundle) {
        if (i3 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.f4680c.startActivity(intent);
    }

    public void q(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i3, Intent intent, int i4, int i5, int i6, Bundle bundle) throws IntentSender.SendIntentException {
        if (i3 != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.r(this.f4679b, intentSender, i3, intent, i4, i5, i6, bundle);
    }

    public void r() {
    }

    public f(Activity activity, Context context, Handler handler, int i3) {
        this.f4683f = new h();
        this.f4679b = activity;
        this.f4680c = (Context) k0.h.d(context, "context == null");
        this.f4681d = (Handler) k0.h.d(handler, "handler == null");
        this.f4682e = i3;
    }
}
