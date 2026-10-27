package com.byd.filter2.h;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.util.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.byd.filter2.d f2841a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.byd.filter2.f.d f2842b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f2843c;
    private b d;
    private g e;
    private com.byd.filter2.f.c f;
    private c g;
    private long h;
    private com.byd.filter2.e.b i;
    private e j;

    class a extends b {
        long l;

        a(int i, int i2) {
            super(i, i2);
        }

        @Override // com.byd.filter2.h.b
        public void l(EGLContext eGLContext, GL10 gl10, EGLConfig eGLConfig) {
            Log.e("m_tag", "GLRender onCreate");
            h.this.f2842b.a();
            h.this.g.a();
            h.this.e.a();
            h.this.f.a();
            h.this.f.z(1920, 1080);
            h.this.e.z(1920, 1080);
            h.this.f2842b.z(1920, 1080);
            h.this.g.z(1920, 1080);
            if (h.this.f2841a != null) {
                h.this.f2841a.onSurfaceCreated(gl10, eGLConfig);
            }
        }

        @Override // com.byd.filter2.h.b
        public void m() {
            if (h.this.f2841a != null) {
                h.this.f2841a.t();
            }
        }

        @Override // com.byd.filter2.h.b
        public void n(GL10 gl10, long j) {
            h.this.h = j;
            h.this.f2842b.c();
            this.l = h.this.f2842b.H();
            h.this.g.B(h.this.f2842b.h());
            h.this.g.H(j);
            h.this.g.c();
            int iH = h.this.g.h();
            h.this.e.B(iH);
            h.this.e.R(this.l);
            h.this.f.B(iH);
            h.this.f.O();
        }

        @Override // com.byd.filter2.h.b
        public void o() {
        }
    }

    public h(Context context) {
        this.f2843c = context;
    }

    public void g(com.byd.filter2.e.b bVar) {
        if (bVar != null) {
            bVar.w();
            this.g.E(bVar);
        }
    }

    public void h(int i) {
        this.e.Q(i);
    }

    public com.byd.filter2.e.b i() {
        return this.i;
    }

    public SurfaceTexture j() {
        return this.f2842b.G();
    }

    public boolean k() {
        return this.e.T();
    }

    public void l() {
        b bVar = this.d;
        if (bVar != null) {
            bVar.p();
            this.d = null;
        }
        com.byd.filter2.f.c cVar = this.f;
        if (cVar != null) {
            cVar.Q();
            this.f.v();
            this.f = null;
        }
        g gVar = this.e;
        if (gVar != null) {
            gVar.V();
            this.e.v();
            this.e = null;
        }
        com.byd.filter2.f.d dVar = this.f2842b;
        if (dVar != null) {
            dVar.v();
            this.f2842b = null;
        }
        c cVar2 = this.g;
        if (cVar2 != null) {
            cVar2.v();
            this.g = null;
        }
    }

    public void m(com.byd.filter2.e.b bVar) {
        com.byd.filter2.e.b bVar2 = this.i;
        if (bVar2 != null) {
            if (bVar2.equals(bVar)) {
                return;
            } else {
                this.g.G(this.i);
            }
        } else if (bVar == null) {
            return;
        }
        this.i = bVar;
        g(bVar);
    }

    public void n() {
        o(this.h);
    }

    public void o(long j) {
        this.d.q(j);
    }

    public void p(e eVar) {
        g gVar = this.e;
        if (gVar != null) {
            gVar.X(eVar);
        }
        this.j = eVar;
    }

    public void q(com.byd.filter2.d dVar) {
        this.f2841a = dVar;
    }

    public void r() {
        Resources resources = this.f2843c.getResources();
        this.f2842b = new com.byd.filter2.f.d(resources);
        this.g = new c(resources);
        g gVar = new g(this.f2843c);
        this.e = gVar;
        e eVar = this.j;
        if (eVar != null) {
            gVar.X(eVar);
        }
        this.f = new com.byd.filter2.f.c(resources);
        a aVar = new a(1920, 1080);
        this.d = aVar;
        aVar.r();
    }

    public void s(int i, int i2, String str, Rect rect) {
        if (this.e.l() != i || this.e.f() != i2) {
            this.e.z(i, i2);
        }
        this.e.Y(i, i2, str, rect);
    }

    public void t() {
        this.e.Z();
    }

    public void u(Object obj) {
        this.f.M(obj);
    }

    public void v() {
        com.byd.filter2.f.c cVar = this.f;
        if (cVar == null) {
            return;
        }
        cVar.R();
    }

    public void w(int i, int i2) {
        if (this.f == null) {
            return;
        }
        if (1920 == i && 1080 == i2) {
            return;
        }
        float[] fArr = new float[16];
        com.byd.filter2.c.b(fArr, 0, 1920, 1080, i, i2);
        this.f.z(i, i2);
        this.f.y(fArr);
    }
}
