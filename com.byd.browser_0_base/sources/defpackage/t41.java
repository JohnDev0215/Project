package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: KeyboardVisibilityDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public class t41 {
    public static t41 f = new t41();
    public final org.chromium.base.b<b> e = new org.chromium.base.b<>();

    /* JADX INFO: compiled from: KeyboardVisibilityDelegate.java */
    public class a implements Runnable {
        public final /* synthetic */ View e;
        public final /* synthetic */ AtomicInteger f;
        public final /* synthetic */ Handler g;

        public a(View view, AtomicInteger atomicInteger, Handler handler) {
            this.e = view;
            this.f = atomicInteger;
            this.g = handler;
        }

        @Override // java.lang.Runnable
        public final void run() {
            View view = this.e;
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            try {
                try {
                    inputMethodManager.showSoftInput(view, 0);
                } catch (IllegalArgumentException e) {
                    if (this.f.incrementAndGet() <= 10) {
                        this.g.postDelayed(this, 100L);
                    } else {
                        Log.e("cr_KeyboardVisibility", "Unable to open keyboard.  Giving up.", e);
                    }
                }
            } finally {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
            }
        }
    }

    /* JADX INFO: compiled from: KeyboardVisibilityDelegate.java */
    public interface b {
        void a(boolean z);
    }

    public static boolean d(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void a(b bVar) {
        org.chromium.base.b<b> bVar2 = this.e;
        if (bVar2.isEmpty()) {
            g();
        }
        bVar2.a(bVar);
    }

    public int b(View view) {
        TraceEvent traceEventH0 = TraceEvent.H0("KeyboardVisibilityDelegate.calculateKeyboardHeight", null);
        try {
            Rect rect = new Rect();
            view.getWindowVisibleDisplayFrame(rect);
            int i = rect.top;
            int height = view.getHeight() - (rect.height() + i);
            if (height <= 0) {
                if (traceEventH0 == null) {
                    return 0;
                }
                traceEventH0.close();
                return 0;
            }
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets != null) {
                height -= rootWindowInsets.getStableInsetBottom();
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return height;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public int c(View view) {
        return b(view);
    }

    public boolean e(View view) {
        return d(view);
    }

    public boolean f(Context context, View view) {
        View rootView = view.getRootView();
        return rootView != null && b(rootView) > 0;
    }

    public void h(b bVar) {
        org.chromium.base.b<b> bVar2 = this.e;
        bVar2.c(bVar);
        if (bVar2.isEmpty()) {
            j();
        }
    }

    @SuppressLint({"NewApi"})
    public void i(View view) {
        new a(view, new AtomicInteger(), new Handler()).run();
    }

    public void g() {
    }

    public void j() {
    }
}
