package androidx.activity;

import a.n.g;
import a.n.i;
import a.n.k;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f1373b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f1374c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f1375d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f1376e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f1377a;

    @Override // a.n.i
    public void g(k kVar, g.a aVar) {
        if (aVar != g.a.ON_DESTROY) {
            return;
        }
        if (f1373b == 0) {
            try {
                f1373b = 2;
                Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
                f1375d = declaredField;
                declaredField.setAccessible(true);
                Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
                f1376e = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
                f1374c = declaredField3;
                declaredField3.setAccessible(true);
                f1373b = 1;
            } catch (NoSuchFieldException unused) {
            }
        }
        if (f1373b == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f1377a.getSystemService("input_method");
            try {
                Object obj = f1374c.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f1375d.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f1376e.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused2) {
                            }
                        } catch (ClassCastException unused3) {
                        } catch (IllegalAccessException unused4) {
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (IllegalAccessException unused5) {
            }
        }
    }
}
