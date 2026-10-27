package com.byd.bluetoothcall.view.ui;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.e;
import androidx.lifecycle.g;

/* JADX INFO: loaded from: classes.dex */
public class SoftKeyBoardListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private transient View f3474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient int f3475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient b f3476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    transient boolean f3477d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private transient ViewTreeObserver.OnGlobalLayoutListener f3478e;

    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            Rect rect = new Rect();
            SoftKeyBoardListener.this.f3474a.getWindowVisibleDisplayFrame(rect);
            SoftKeyBoardListener softKeyBoardListener = SoftKeyBoardListener.this;
            if (!softKeyBoardListener.f3477d && softKeyBoardListener.f3475b > rect.bottom) {
                SoftKeyBoardListener softKeyBoardListener2 = SoftKeyBoardListener.this;
                softKeyBoardListener2.f3477d = true;
                if (softKeyBoardListener2.f3476c != null) {
                    SoftKeyBoardListener.this.f3476c.b(SoftKeyBoardListener.this.f3475b - rect.bottom);
                    return;
                }
                return;
            }
            SoftKeyBoardListener softKeyBoardListener3 = SoftKeyBoardListener.this;
            if (!softKeyBoardListener3.f3477d || rect.bottom < softKeyBoardListener3.f3475b) {
                return;
            }
            SoftKeyBoardListener softKeyBoardListener4 = SoftKeyBoardListener.this;
            softKeyBoardListener4.f3477d = false;
            if (softKeyBoardListener4.f3476c != null) {
                SoftKeyBoardListener.this.f3476c.a();
            }
        }
    }

    public interface b {
        void a();

        void b(int i);
    }

    private SoftKeyBoardListener(Activity activity) {
        this.f3477d = false;
        int i = activity.getWindow().getAttributes().softInputMode & 15;
        if (i == 4 || i == 5) {
            this.f3477d = true;
        }
        this.f3474a = activity.getWindow().getDecorView();
        this.f3475b = activity.getWindowManager().getDefaultDisplay().getHeight();
        this.f3478e = new a();
        this.f3474a.getViewTreeObserver().addOnGlobalLayoutListener(this.f3478e);
        e(activity);
    }

    public static void f(Activity activity, b bVar) {
        new SoftKeyBoardListener(activity).g(bVar);
    }

    private void g(b bVar) {
        this.f3476c = bVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e(Activity activity) {
        if (activity instanceof g) {
            ((g) activity).getLifecycle().a(new e() { // from class: com.byd.bluetoothcall.view.ui.SoftKeyBoardListener.2
                @Override // androidx.lifecycle.e
                public void d(g gVar, Lifecycle.Event event) {
                    if (event != Lifecycle.Event.ON_DESTROY || SoftKeyBoardListener.this.f3474a == null) {
                        return;
                    }
                    SoftKeyBoardListener.this.f3474a.getViewTreeObserver().removeOnGlobalLayoutListener(SoftKeyBoardListener.this.f3478e);
                }
            });
        }
    }
}
