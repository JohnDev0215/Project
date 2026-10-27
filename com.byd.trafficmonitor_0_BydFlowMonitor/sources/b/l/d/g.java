package b.l.d;

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
import androidx.fragment.app.FragmentManager;

/* JADX INFO: compiled from: FragmentHostCallback.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g<E> extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Activity f2812b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f2813c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f2814d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2815e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final FragmentManager f2816f;

    public g(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    @Override // b.l.d.d
    public View c(int i2) {
        return null;
    }

    @Override // b.l.d.d
    public boolean d() {
        return true;
    }

    public Activity e() {
        return this.f2812b;
    }

    public Context f() {
        return this.f2813c;
    }

    public Handler g() {
        return this.f2814d;
    }

    public abstract E h();

    public LayoutInflater i() {
        return LayoutInflater.from(this.f2813c);
    }

    @Deprecated
    public void j(Fragment fragment, String[] strArr, int i2) {
    }

    public boolean k(Fragment fragment) {
        return true;
    }

    public boolean l(String str) {
        return false;
    }

    public void m(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2, Bundle bundle) {
        if (i2 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        b.h.e.a.h(this.f2813c, intent, bundle);
    }

    @Deprecated
    public void n(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        if (i2 != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        b.h.d.a.r(this.f2812b, intentSender, i2, intent, i3, i4, i5, bundle);
    }

    public void o() {
    }

    public g(Activity activity, Context context, Handler handler, int i2) {
        this.f2816f = new j();
        this.f2812b = activity;
        this.f2813c = (Context) b.h.l.g.d(context, "context == null");
        this.f2814d = (Handler) b.h.l.g.d(handler, "handler == null");
        this.f2815e = i2;
    }
}
