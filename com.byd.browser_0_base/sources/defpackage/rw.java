package defpackage;

import J.N;
import android.content.Context;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.chrome.browser.compositor.CompositorView;

/* JADX INFO: compiled from: CompositorSurfaceManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class rw implements SurfaceHolder.Callback2 {
    public final b e;
    public final b f;
    public b g;
    public b h;
    public final ow i;
    public final ViewGroup j;

    /* JADX INFO: compiled from: CompositorSurfaceManagerImpl.java */
    public class a implements Runnable {
        public final /* synthetic */ b e;

        public a(b bVar) {
            this.e = bVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            rw.this.c(this.e);
        }
    }

    /* JADX INFO: compiled from: CompositorSurfaceManagerImpl.java */
    public static class b {
        public final SurfaceView a;
        public boolean b;
        public boolean c;
        public int d;
        public int e;
        public int f;
        public ViewGroup g;

        public b(Context context, int i, SurfaceHolder.Callback2 callback2) {
            SurfaceView surfaceView = new SurfaceView(context);
            this.a = surfaceView;
            if (i == -3) {
                surfaceView.setZOrderMediaOverlay(true);
            }
            surfaceView.setVisibility(4);
            b().setFormat(i);
            b().addCallback(callback2);
            this.d = 0;
        }

        public final boolean a() {
            return this.g != null;
        }

        public final SurfaceHolder b() {
            return this.a.getHolder();
        }
    }

    public rw(ViewGroup viewGroup, ow owVar) {
        this.j = viewGroup;
        this.i = owVar;
        this.e = new b(viewGroup.getContext(), -3, this);
        this.f = new b(viewGroup.getContext(), -1, this);
    }

    public final void a(b bVar) {
        if (bVar.a() || bVar.c) {
            return;
        }
        bVar.b = true;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ViewGroup viewGroup = this.j;
        bVar.g = viewGroup;
        SurfaceView surfaceView = bVar.a;
        viewGroup.addView(surfaceView, layoutParams);
        viewGroup.bringChildToFront(surfaceView);
        viewGroup.postInvalidateOnAnimation();
    }

    public final void b(b bVar) {
        if (bVar.a()) {
            bVar.c = true;
            this.j.post(new a(bVar));
        }
    }

    public final void c(b bVar) {
        if (bVar.a()) {
            Surface surface = bVar.b().getSurface();
            boolean zIsValid = surface == null ? false : surface.isValid();
            bVar.c = zIsValid;
            fa1.g("CompositorSurfaceMgr", "SurfaceState : detach from parent : %d", Integer.valueOf(bVar.d));
            ViewGroup viewGroup = bVar.g;
            bVar.g = null;
            viewGroup.removeView(bVar.a);
            if (zIsValid) {
                return;
            }
        }
        d(bVar, false);
        b bVar2 = this.h;
        if (bVar == bVar2) {
            a(bVar2);
        }
    }

    public final void d(b bVar, boolean z) {
        b bVar2 = this.g;
        if (bVar2 != bVar || bVar == null) {
            return;
        }
        bVar2.b().getSurface();
        ((CompositorView) this.i).h(z);
        this.g = null;
    }

    public final b e(SurfaceHolder surfaceHolder) {
        b bVar = this.e;
        if (bVar.b() == surfaceHolder) {
            return bVar;
        }
        b bVar2 = this.f;
        if (bVar2.b() == surfaceHolder) {
            return bVar2;
        }
        return null;
    }

    public final void f(int i) {
        fa1.g("CompositorSurfaceMgr", "Transitioning to surface with format: %d", Integer.valueOf(i));
        b bVar = i == -3 ? this.e : this.f;
        this.h = bVar;
        if (bVar.c) {
            return;
        }
        if (!bVar.a()) {
            a(this.h);
            return;
        }
        if (this.h.b) {
            return;
        }
        d(this.g, false);
        b bVar2 = this.h;
        if (bVar2 == null) {
            return;
        }
        this.g = bVar2;
        bVar2.b().getSurface();
        CompositorView compositorView = (CompositorView) this.i;
        compositorView.g();
        b bVar3 = this.g;
        if (bVar3.d != 0) {
            Surface surface = bVar3.b().getSurface();
            b bVar4 = this.g;
            compositorView.f(surface, bVar4.d, bVar4.e, bVar4.f);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        b bVarE = e(surfaceHolder);
        if (bVarE == this.g && bVarE == this.h) {
            bVarE.e = i2;
            bVarE.f = i3;
            bVarE.d = i;
            ((CompositorView) this.i).f(surfaceHolder.getSurface(), i, i2, i3);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        b bVarE = e(surfaceHolder);
        fa1.g("CompositorSurfaceMgr", "surfaceCreated format: %d", Integer.valueOf(bVarE.d));
        if (bVarE != this.h) {
            b(bVarE);
            return;
        }
        bVarE.b = false;
        bVarE.d = 0;
        d(this.g, false);
        b bVar = this.h;
        this.g = bVar;
        bVar.b().getSurface();
        ((CompositorView) this.i).g();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        b bVarE = e(surfaceHolder);
        Log.e("cr_CompositorSurfaceMgr", "surfaceDestroyed format : " + bVarE.d);
        if (!bVarE.c) {
            bVarE.b = true;
        } else if (!bVarE.a()) {
            bVarE.c = false;
        }
        bVarE.d = 0;
        b bVar = this.g;
        if (bVarE == bVar) {
            d(bVar, true);
            return;
        }
        CompositorView compositorView = (CompositorView) this.i;
        N.MVesqb5U(compositorView.j, compositorView);
        if (bVarE == this.h && !bVarE.a()) {
            bVarE.b = true;
            this.j.post(new qw(this, bVarE));
        } else {
            if (bVarE == this.h || !bVarE.a()) {
                return;
            }
            b(bVarE);
        }
    }

    @Override // android.view.SurfaceHolder.Callback2
    public final void surfaceRedrawNeededAsync(SurfaceHolder surfaceHolder, Runnable runnable) {
        ((CompositorView) this.i).i(runnable);
    }

    @Override // android.view.SurfaceHolder.Callback2
    public final void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }
}
