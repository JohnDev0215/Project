package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import b.m.d;
import b.m.f;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f58a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f59b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f60c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f61d;
    public Activity e;

    public ImmLeaksCleaner(Activity activity) {
        this.e = activity;
    }

    public static void h() {
        try {
            f58a = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f60c = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f61d = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f59b = declaredField3;
            declaredField3.setAccessible(true);
            f58a = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // b.m.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (f58a == 0) {
            h();
        }
        if (f58a == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.e.getSystemService("input_method");
            try {
                Object obj = f59b.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f60c.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f61d.set(inputMethodManager, null);
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
