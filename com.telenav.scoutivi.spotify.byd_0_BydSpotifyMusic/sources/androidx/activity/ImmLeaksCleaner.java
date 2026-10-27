package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements m {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f313g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static Field f314h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static Field f315i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static Field f316j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Activity f317e;

    private static void h() {
        try {
            f313g = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            f315i = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f316j = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            f314h = declaredField3;
            declaredField3.setAccessible(true);
            f313g = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.m
    public void d(o oVar, i.b bVar) {
        if (bVar != i.b.ON_DESTROY) {
            return;
        }
        if (f313g == 0) {
            h();
        }
        if (f313g == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f317e.getSystemService("input_method");
            try {
                Object obj = f314h.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f315i.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f316j.set(inputMethodManager, null);
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
