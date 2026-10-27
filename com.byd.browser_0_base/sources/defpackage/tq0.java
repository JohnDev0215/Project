package defpackage;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import defpackage.ba0;
import defpackage.qq0;
import java.util.function.BooleanSupplier;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: FullscreenToast.java */
/* JADX INFO: loaded from: classes.dex */
public interface tq0 {

    /* JADX INFO: compiled from: FullscreenToast.java */
    public static class b implements tq0 {
        public final Activity a;
        public final Handler b;
        public final nw2<Tab> c;
        public final nw2<Tab> d;
        public final nw2<View> e;
        public boolean g;
        public View h;
        public ViewPropertyAnimator i;
        public final ba0 j;
        public int k;
        public final a l = new a();
        public final ql0 f = new ql0(5, this);

        /* JADX INFO: compiled from: FullscreenToast.java */
        public class a implements ViewTreeObserver.OnGlobalLayoutListener {
            public a() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                b bVar = b.this;
                if (bVar.e.get() == null || bVar.h == null) {
                    return;
                }
                Rect rect = new Rect();
                bVar.e.get().getWindowVisibleDisplayFrame(rect);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) bVar.h.getLayoutParams();
                int height = bVar.e.get().getHeight() - rect.height();
                if (height == 0) {
                    height = bVar.k;
                }
                marginLayoutParams.setMargins(0, 0, 0, height);
                bVar.h.requestLayout();
            }
        }

        public b(Activity activity, qq0.a aVar, hs hsVar, yq2 yq2Var, p11 p11Var) {
            this.a = activity;
            this.b = aVar;
            this.c = hsVar;
            this.e = yq2Var;
            this.d = p11Var;
            this.j = Build.VERSION.SDK_INT < 30 ? new ba0.a(activity) : new ba0.b(activity);
        }

        @Override // defpackage.tq0
        public final void a() {
            f();
            this.g = false;
        }

        @Override // defpackage.tq0
        public final void b() {
            this.k = this.j.a();
            this.a.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.l);
        }

        @Override // defpackage.tq0
        public final void c() {
            f();
            this.g = false;
        }

        @Override // defpackage.tq0
        public final void d() {
            if (this.h != null) {
                return;
            }
            this.g = true;
            e();
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void e() {
            /*
                r6 = this;
                android.view.View r0 = r6.h
                if (r0 == 0) goto L5
                return
            L5:
                boolean r0 = r6.g
                r1 = 1
                r2 = 0
                if (r0 != 0) goto Lc
                goto L31
            Lc:
                nw2<org.chromium.chrome.browser.tab.Tab> r0 = r6.d
                java.lang.Object r0 = r0.get()
                if (r0 != 0) goto L15
                goto L31
            L15:
                nw2<org.chromium.chrome.browser.tab.Tab> r0 = r6.c
                java.lang.Object r3 = r0.get()
                if (r3 != 0) goto L1e
                goto L31
            L1e:
                java.lang.Object r0 = r0.get()
                org.chromium.chrome.browser.tab.Tab r0 = (org.chromium.chrome.browser.tab.Tab) r0
                org.chromium.components.embedder_support.view.ContentView r0 = r0.d()
                if (r0 != 0) goto L2b
                goto L31
            L2b:
                boolean r0 = r0.hasWindowFocus()
                if (r0 != 0) goto L33
            L31:
                r0 = r2
                goto L34
            L33:
                r0 = r1
            L34:
                if (r0 != 0) goto L37
                return
            L37:
                android.app.Activity r0 = r6.a
                android.view.Window r3 = r0.getWindow()
                int r4 = org.chromium.chrome.R$id.fullscreen_notification
                android.view.View r3 = r3.findViewById(r4)
                r6.h = r3
                if (r3 != 0) goto L55
                android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r0)
                int r4 = org.chromium.chrome.R$layout.fullscreen_notification
                r5 = 0
                android.view.View r3 = r3.inflate(r4, r5)
                r6.h = r3
                goto L56
            L55:
                r1 = r2
            L56:
                android.view.View r3 = r6.h
                r4 = 0
                r3.setAlpha(r4)
                android.view.View r3 = r6.h
                android.view.ViewPropertyAnimator r3 = r3.animate()
                r6.i = r3
                if (r1 == 0) goto L82
                android.view.View r1 = r6.h
                android.view.ViewGroup$LayoutParams r2 = new android.view.ViewGroup$LayoutParams
                r3 = -1
                r2.<init>(r3, r3)
                r0.addContentView(r1, r2)
                android.view.View r1 = r6.h
                android.content.res.Resources r0 = r0.getResources()
                int r2 = org.chromium.chrome.R$dimen.fullscreen_toast_elevation
                int r0 = r0.getDimensionPixelSize(r2)
                float r0 = (float) r0
                r1.setElevation(r0)
                goto L87
            L82:
                android.view.View r0 = r6.h
                r0.setVisibility(r2)
            L87:
                android.view.ViewPropertyAnimator r0 = r6.i
                r1 = 1065353216(0x3f800000, float:1.0)
                android.view.ViewPropertyAnimator r0 = r0.alpha(r1)
                r1 = 500(0x1f4, double:2.47E-321)
                android.view.ViewPropertyAnimator r0 = r0.setDuration(r1)
                r0.start()
                r0 = 5000(0x1388, double:2.4703E-320)
                android.os.Handler r2 = r6.b
                ql0 r6 = r6.f
                r2.postDelayed(r6, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: tq0.b.e():void");
        }

        public final void f() {
            if (this.h == null) {
                return;
            }
            this.b.removeCallbacks(this.f);
            this.i.cancel();
            this.i = null;
            this.a.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this.l);
            this.h.setVisibility(8);
            this.h = null;
        }

        @Override // defpackage.tq0
        public final void onWindowFocusChanged(boolean z) {
            if (z) {
                e();
            } else {
                f();
            }
        }
    }

    void a();

    void b();

    void c();

    void d();

    void onWindowFocusChanged(boolean z);

    /* JADX INFO: compiled from: FullscreenToast.java */
    public static class a implements tq0 {
        public final Activity a;
        public final BooleanSupplier b;
        public Toast c;

        public a(Activity activity, t20 t20Var) {
            this.a = activity;
            this.b = t20Var;
        }

        @Override // defpackage.tq0
        public final void c() {
            Toast toast = this.c;
            if (toast != null) {
                toast.a();
                this.c = null;
            }
        }

        @Override // defpackage.tq0
        public final void d() {
            e();
        }

        public final void e() {
            Toast toast = this.c;
            if (toast != null) {
                toast.a();
                this.c = null;
            }
            int i = R$string.immersive_fullscreen_api_notification;
            Toast.Builder builder = new Toast.Builder(this.a);
            builder.b = builder.a.getResources().getText(i);
            builder.f = 1;
            builder.g = 0;
            Toast toastA = builder.a();
            this.c = toastA;
            toastA.d(81, 0, 0);
            this.c.e();
        }

        @Override // defpackage.tq0
        public final void onWindowFocusChanged(boolean z) {
            if (z && this.b.getAsBoolean()) {
                e();
                return;
            }
            Toast toast = this.c;
            if (toast != null) {
                toast.a();
                this.c = null;
            }
        }

        @Override // defpackage.tq0
        public final void a() {
        }

        @Override // defpackage.tq0
        public final void b() {
        }
    }
}
