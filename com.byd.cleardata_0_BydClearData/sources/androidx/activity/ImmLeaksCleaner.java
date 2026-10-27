package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f662a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Field f663b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Field f664c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Field f665d;
    private Activity e;

    ImmLeaksCleaner(Activity activity) {
        this.e = activity;
    }

    private static void h() {
        try {
            f662a = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f664c = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f665d = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f663b = declaredField3;
            declaredField3.setAccessible(true);
            f662a = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, d.a aVar) {
        if (aVar != d.a.ON_DESTROY) {
            return;
        }
        if (f662a == 0) {
            h();
        }
        if (f662a == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.e.getSystemService("input_method");
            try {
                Object obj = f663b.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f664c.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f665d.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (ClassCastException unused2) {
                    } catch (IllegalAccessException unused3) {
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }
}
