package v0;

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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Activity f8359d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f8360e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Handler f8361f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f8362g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h f8363h;

    public f(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // v0.c
    public View b(int i8) {
        return null;
    }

    @Override // v0.c
    public boolean c() {
        return true;
    }

    public Activity d() {
        return this.f8359d;
    }

    public Context e() {
        return this.f8360e;
    }

    public Handler f() {
        return this.f8361f;
    }

    public void g(Fragment fragment) {
    }

    public void h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract E i();

    public LayoutInflater j() {
        return LayoutInflater.from(this.f8360e);
    }

    public int k() {
        return this.f8362g;
    }

    public boolean l() {
        return true;
    }

    public void m(Fragment fragment, String[] strArr, int i8) {
    }

    public boolean n(Fragment fragment) {
        return true;
    }

    public boolean o(String str) {
        return false;
    }

    public void p(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i8, Bundle bundle) {
        if (i8 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.f8360e.startActivity(intent);
    }

    public void q(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i8, Intent intent, int i9, int i10, int i11, Bundle bundle) throws IntentSender.SendIntentException {
        if (i8 != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.r(this.f8359d, intentSender, i8, intent, i9, i10, i11, bundle);
    }

    public void r() {
    }

    public f(Activity activity, Context context, Handler handler, int i8) {
        this.f8363h = new h();
        this.f8359d = activity;
        this.f8360e = (Context) n0.h.f(context, "context == null");
        this.f8361f = (Handler) n0.h.f(handler, "handler == null");
        this.f8362g = i8;
    }
}
