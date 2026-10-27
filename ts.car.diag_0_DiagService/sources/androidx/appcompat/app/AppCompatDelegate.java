package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AppCompatDelegate {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f115c = -100;
    private static final b.a.b<WeakReference<AppCompatDelegate>> d = new b.a.b<>();
    private static final Object e = new Object();

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface NightMode {
    }

    AppCompatDelegate() {
    }

    static void c(@NonNull AppCompatDelegate appCompatDelegate) {
        synchronized (e) {
            y(appCompatDelegate);
            d.add(new WeakReference<>(appCompatDelegate));
        }
    }

    @NonNull
    public static AppCompatDelegate g(@NonNull Activity activity, @Nullable b bVar) {
        return new AppCompatDelegateImpl(activity, bVar);
    }

    @NonNull
    public static AppCompatDelegate h(@NonNull Dialog dialog, @Nullable b bVar) {
        return new AppCompatDelegateImpl(dialog, bVar);
    }

    public static int j() {
        return f115c;
    }

    static void x(@NonNull AppCompatDelegate appCompatDelegate) {
        synchronized (e) {
            y(appCompatDelegate);
        }
    }

    private static void y(@NonNull AppCompatDelegate appCompatDelegate) {
        synchronized (e) {
            Iterator<WeakReference<AppCompatDelegate>> it = d.iterator();
            while (it.hasNext()) {
                AppCompatDelegate appCompatDelegate2 = it.next().get();
                if (appCompatDelegate2 == appCompatDelegate || appCompatDelegate2 == null) {
                    it.remove();
                }
            }
        }
    }

    public abstract void A(@LayoutRes int i);

    public abstract void B(View view);

    public abstract void C(View view, ViewGroup.LayoutParams layoutParams);

    public void D(@StyleRes int i) {
    }

    public abstract void E(@Nullable CharSequence charSequence);

    public abstract void d(View view, ViewGroup.LayoutParams layoutParams);

    @Deprecated
    public void e(Context context) {
    }

    @NonNull
    @CallSuper
    public Context f(@NonNull Context context) {
        e(context);
        return context;
    }

    @Nullable
    public abstract <T extends View> T i(@IdRes int i);

    public int k() {
        return -100;
    }

    public abstract MenuInflater l();

    @Nullable
    public abstract ActionBar m();

    public abstract void n();

    public abstract void o();

    public abstract void p(Configuration configuration);

    public abstract void q(Bundle bundle);

    public abstract void r();

    public abstract void s(Bundle bundle);

    public abstract void t();

    public abstract void u(Bundle bundle);

    public abstract void v();

    public abstract void w();

    public abstract boolean z(int i);
}
