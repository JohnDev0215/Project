package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import b.n.e;
import b.n.g;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f198b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f199c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f200d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f201e;

    public ImmLeaksCleaner(Activity activity) {
        this.f201e = activity;
    }

    public static void h() {
        try {
            f197a = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f199c = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f200d = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f198b = declaredField3;
            declaredField3.setAccessible(true);
            f197a = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // b.n.e
    public void d(g gVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f197a == 0) {
            h();
        }
        if (f197a == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f201e.getSystemService("input_method");
            try {
                Object obj = f198b.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f199c.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f200d.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (ClassCastException unused2) {
                        } catch (IllegalAccessException unused3) {
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }
}
