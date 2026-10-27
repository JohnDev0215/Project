package org.chromium.android_webview.gfx;

import J.N;
import android.graphics.Canvas;
import android.view.ViewGroup;
import com.android.webview.chromium.DrawGLFunctor;
import defpackage.C1222nb;
import defpackage.InterfaceC0286Pr;
import defpackage.InterfaceC0300Qn;
import defpackage.InterfaceC0318Rn;
import java.util.Objects;
import org.chromium.android_webview.gfx.AwGLFunctor;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwGLFunctor implements InterfaceC0286Pr {
    public final long a;
    public final InterfaceC0318Rn b;
    public final ViewGroup c;
    public final Runnable d;
    public int e;

    public AwGLFunctor(InterfaceC0300Qn interfaceC0300Qn, ViewGroup viewGroup) {
        long jMe9QwrxV = N.Me9QwrxV(this);
        this.a = jMe9QwrxV;
        C1222nb c1222nb = (C1222nb) interfaceC0300Qn;
        Objects.requireNonNull(c1222nb);
        this.b = new DrawGLFunctor(jMe9QwrxV, c1222nb.a.A.d);
        this.c = viewGroup;
        if (DrawGLFunctor.c) {
            this.d = new Runnable(this) { // from class: Qr
                public final AwGLFunctor s;

                {
                    this.s = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.d();
                }
            };
        } else {
            this.d = null;
        }
        this.e++;
    }

    private void detachFunctorFromView() {
        InterfaceC0318Rn interfaceC0318Rn = this.b;
        ViewGroup viewGroup = this.c;
        DrawGLFunctor drawGLFunctor = (DrawGLFunctor) interfaceC0318Rn;
        long j = drawGLFunctor.b;
        if (j == 0) {
            throw new RuntimeException("detach on already destroyed DrawGLFunctor");
        }
        drawGLFunctor.a.b(viewGroup, j);
        this.c.invalidate();
    }

    private boolean requestInvokeGL(boolean z) {
        InterfaceC0318Rn interfaceC0318Rn = this.b;
        ViewGroup viewGroup = this.c;
        DrawGLFunctor drawGLFunctor = (DrawGLFunctor) interfaceC0318Rn;
        if (drawGLFunctor.b == 0) {
            throw new RuntimeException("requestInvokeGL on already destroyed DrawGLFunctor");
        }
        if (!DrawGLFunctor.c && !drawGLFunctor.a.m(viewGroup)) {
            return false;
        }
        drawGLFunctor.a.f(viewGroup, drawGLFunctor.b, z);
        return true;
    }

    @Override // defpackage.InterfaceC0286Pr
    public void a() {
        N.MsCe5UVl(this.a, this);
    }

    @Override // defpackage.InterfaceC0286Pr
    public long b() {
        return N.MfRoRhka(this.a, this);
    }

    @Override // defpackage.InterfaceC0286Pr
    public boolean c(Canvas canvas) {
        InterfaceC0318Rn interfaceC0318Rn = this.b;
        Runnable runnable = this.d;
        DrawGLFunctor drawGLFunctor = (DrawGLFunctor) interfaceC0318Rn;
        long j = drawGLFunctor.b;
        if (j == 0) {
            throw new RuntimeException("requestDrawGL on already destroyed DrawGLFunctor");
        }
        if (DrawGLFunctor.c) {
            drawGLFunctor.a.a(canvas, j, runnable);
        } else {
            drawGLFunctor.a.e(canvas, j);
        }
        if (this.d != null) {
            this.e++;
        }
        return true;
    }

    public final void d() {
        int i = this.e - 1;
        this.e = i;
        if (i == 0) {
            N.MsCe5UVl(this.a, this);
            DrawGLFunctor drawGLFunctor = (DrawGLFunctor) this.b;
            DrawGLFunctor.nativeDestroyGLFunctor(drawGLFunctor.b);
            drawGLFunctor.b = 0L;
            N.Ms3_fBgF(this.a);
        }
    }

    @Override // defpackage.InterfaceC0286Pr
    public void destroy() {
        N.MOiMYd0I(this.a, this);
        d();
    }
}
