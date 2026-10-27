package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(19)
final class ImmLeaksCleaner implements d {
    private static int d;
    private static Field e;
    private static Field f;
    private static Field g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Activity f75c;

    ImmLeaksCleaner(Activity activity) {
        this.f75c = activity;
    }

    @MainThread
    private static void a() {
        try {
            d = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            g = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            e = declaredField3;
            declaredField3.setAccessible(true);
            d = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.d
    public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (d == 0) {
            a();
        }
        if (d == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f75c.getSystemService("input_method");
            try {
                Object obj = e.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                g.set(inputMethodManager, null);
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
