package defpackage;

import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.task.PostTask;
import org.chromium.weblayer_private.ContentViewRenderView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VP implements WP {
    public final int a;
    public final WP b;
    public final FrameLayout c;
    public final Runnable d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public VP i;
    public VP j;
    public final YP k;
    public final SurfaceView l;
    public int m;
    public final TextureView n;
    public final TextureViewSurfaceTextureListenerC0494aQ o;
    public final ArrayList p = new ArrayList();
    public final /* synthetic */ ContentViewRenderView q;

    public VP(ContentViewRenderView contentViewRenderView, int i, FrameLayout frameLayout, WP wp, int i2, Runnable runnable) {
        this.q = contentViewRenderView;
        this.a = i;
        this.b = wp;
        this.c = frameLayout;
        this.d = runnable;
        if (i == 0) {
            SurfaceView surfaceView = new SurfaceView(frameLayout.getContext());
            this.l = surfaceView;
            surfaceView.setZOrderMediaOverlay(true);
            this.l.setBackgroundColor(i2);
            this.k = new YP(this);
            this.l.getHolder().addCallback(this.k);
            this.l.setVisibility(0);
            this.l.getHolder().setFormat(-3);
            this.n = null;
            this.o = null;
        } else {
            if (i != 1) {
                throw new RuntimeException(Z7.a("Illegal mode: ", i));
            }
            this.n = new UP(this, frameLayout.getContext());
            TextureViewSurfaceTextureListenerC0494aQ textureViewSurfaceTextureListenerC0494aQ = new TextureViewSurfaceTextureListenerC0494aQ(this);
            this.o = textureViewSurfaceTextureListenerC0494aQ;
            this.n.setSurfaceTextureListener(textureViewSurfaceTextureListenerC0494aQ);
            this.n.setVisibility(0);
            this.l = null;
            this.k = null;
        }
        frameLayout.postOnAnimation(new PP(this, contentViewRenderView));
    }

    @Override // defpackage.WP
    public void a() {
        if (this.f) {
            return;
        }
        SurfaceView surfaceView = this.l;
        if (surfaceView != null) {
            surfaceView.setVisibility(surfaceView.getVisibility());
        }
        this.b.a();
        if (!this.e && this.i == null) {
            f();
        }
        this.h = true;
    }

    @Override // defpackage.WP
    public void b(Surface surface, boolean z, int i, int i2, int i3) {
        if (this.f) {
            return;
        }
        this.b.b(surface, z, i, i2, i3);
        this.m = 2;
    }

    @Override // defpackage.WP
    public void c(boolean z) {
        if (this.f) {
            return;
        }
        this.b.c(z);
        this.h = false;
    }

    public void d() {
        f();
        this.c.postOnAnimation(new SP(this));
    }

    public void e(boolean z) {
        if (this.f) {
            return;
        }
        this.f = true;
        if (this.h) {
            boolean z2 = z && this.a == 0;
            this.g = z2;
            this.b.c(z2);
            this.h = false;
        }
        int i = this.a;
        if (i == 0) {
            this.l.getHolder().removeCallback(this.k);
        } else if (i == 1) {
            this.n.setSurfaceTextureListener(null);
        }
    }

    public final void f() {
        this.e = true;
        if (this.p.isEmpty()) {
            return;
        }
        PostTask.a(AbstractC0538bE.a, new Runnable(this) { // from class: OP
            public final VP s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                VP vp = this.s;
                ArrayList arrayList = (ArrayList) vp.p.clone();
                vp.p.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((ValueCallback) it.next()).onReceiveValue(Boolean.valueOf(!vp.f));
                }
            }
        }, 0L);
    }
}
