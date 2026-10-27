package A2;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import h2.AbstractC1346c;
import java.nio.Buffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public final class n implements GLSurfaceView.Renderer, d {
    public final k l;
    public final float[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float[] f349p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float[] f350q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f351r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f352s;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ o f355v;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float[] f347m = new float[16];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float[] f348n = new float[16];

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final float[] f353t = new float[16];

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final float[] f354u = new float[16];

    public n(o oVar, k kVar) {
        this.f355v = oVar;
        float[] fArr = new float[16];
        this.o = fArr;
        float[] fArr2 = new float[16];
        this.f349p = fArr2;
        float[] fArr3 = new float[16];
        this.f350q = fArr3;
        this.l = kVar;
        Matrix.setIdentityM(fArr, 0);
        Matrix.setIdentityM(fArr2, 0);
        Matrix.setIdentityM(fArr3, 0);
        this.f352s = 3.1415927f;
    }

    @Override // A2.d
    public final synchronized void a(float[] fArr, float f10) {
        float[] fArr2 = this.o;
        System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
        float f11 = -f10;
        this.f352s = f11;
        Matrix.setRotateM(this.f349p, 0, -this.f351r, (float) Math.cos(f11), (float) Math.sin(this.f352s), 0.0f);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        Object objU;
        synchronized (this) {
            Matrix.multiplyMM(this.f354u, 0, this.o, 0, this.f350q, 0);
            Matrix.multiplyMM(this.f353t, 0, this.f349p, 0, this.f354u, 0);
        }
        Matrix.multiplyMM(this.f348n, 0, this.f347m, 0, this.f353t, 0);
        k kVar = this.l;
        float[] fArr = this.f348n;
        GLES20.glClear(16384);
        try {
            AbstractC1346c.d();
        } catch (h2.j e10) {
            AbstractC1346c.q("SceneRenderer", "Failed to draw a frame", e10);
        }
        if (kVar.l.compareAndSet(true, false)) {
            SurfaceTexture surfaceTexture = kVar.f340u;
            surfaceTexture.getClass();
            surfaceTexture.updateTexImage();
            try {
                AbstractC1346c.d();
            } catch (h2.j e11) {
                AbstractC1346c.q("SceneRenderer", "Failed to draw a frame", e11);
            }
            if (kVar.f333m.compareAndSet(true, false)) {
                Matrix.setIdentityM(kVar.f337r, 0);
            }
            long timestamp = kVar.f340u.getTimestamp();
            g gVar = kVar.f335p;
            synchronized (gVar) {
                objU = gVar.u(timestamp, false);
            }
            Long l = (Long) objU;
            if (l != null) {
                c cVar = kVar.o;
                float[] fArr2 = kVar.f337r;
                float[] fArr3 = (float[]) ((g) cVar.f312e).w(l.longValue());
                if (fArr3 != null) {
                    float f10 = fArr3[0];
                    float f11 = -fArr3[1];
                    float f12 = -fArr3[2];
                    float length = Matrix.length(f10, f11, f12);
                    float[] fArr4 = (float[]) cVar.d;
                    if (length != 0.0f) {
                        Matrix.setRotateM(fArr4, 0, (float) Math.toDegrees(length), f10 / length, f11 / length, f12 / length);
                    } else {
                        Matrix.setIdentityM(fArr4, 0);
                    }
                    if (!cVar.f311b) {
                        c.e((float[]) cVar.c, (float[]) cVar.d);
                        cVar.f311b = true;
                    }
                    Matrix.multiplyMM(fArr2, 0, (float[]) cVar.c, 0, (float[]) cVar.d, 0);
                }
            }
            h hVar = (h) kVar.f336q.w(timestamp);
            if (hVar != null) {
                i iVar = kVar.f334n;
                iVar.getClass();
                if (i.b(hVar)) {
                    iVar.f327a = hVar.c;
                    iVar.f328b = new g(hVar.f322a.f318a[0]);
                    if (!hVar.d) {
                        g gVar2 = hVar.f323b.f318a[0];
                        float[] fArr5 = (float[]) gVar2.o;
                        int length2 = fArr5.length;
                        AbstractC1346c.m(fArr5);
                        AbstractC1346c.m((float[]) gVar2.f321p);
                    }
                }
            }
        }
        Matrix.multiplyMM(kVar.f338s, 0, fArr, 0, kVar.f337r, 0);
        i iVar2 = kVar.f334n;
        int i7 = kVar.f339t;
        float[] fArr6 = kVar.f338s;
        g gVar3 = iVar2.f328b;
        if (gVar3 == null) {
            return;
        }
        int i10 = iVar2.f327a;
        GLES20.glUniformMatrix3fv(iVar2.f329e, 1, false, i10 == 1 ? i.f325j : i10 == 2 ? i.f326k : i.f324i, 0);
        GLES20.glUniformMatrix4fv(iVar2.d, 1, false, fArr6, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i7);
        GLES20.glUniform1i(iVar2.f332h, 0);
        try {
            AbstractC1346c.d();
        } catch (h2.j e12) {
            h6.n.i("ProjectionRenderer", "Failed to bind uniforms", e12);
        }
        GLES20.glVertexAttribPointer(iVar2.f330f, 3, 5126, false, 12, (Buffer) gVar3.o);
        try {
            AbstractC1346c.d();
        } catch (h2.j e13) {
            h6.n.i("ProjectionRenderer", "Failed to load position data", e13);
        }
        GLES20.glVertexAttribPointer(iVar2.f331g, 2, 5126, false, 8, (Buffer) gVar3.f321p);
        try {
            AbstractC1346c.d();
        } catch (h2.j e14) {
            h6.n.i("ProjectionRenderer", "Failed to load texture data", e14);
        }
        GLES20.glDrawArrays(gVar3.f320n, 0, gVar3.f319m);
        try {
            AbstractC1346c.d();
        } catch (h2.j e15) {
            h6.n.i("ProjectionRenderer", "Failed to render", e15);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceChanged(GL10 gl10, int i7, int i10) {
        GLES20.glViewport(0, 0, i7, i10);
        float f10 = i7 / i10;
        Matrix.perspectiveM(this.f347m, 0, f10 > 1.0f ? (float) (Math.toDegrees(Math.atan(Math.tan(Math.toRadians(45.0d)) / ((double) f10))) * 2.0d) : 90.0f, f10, 0.1f, 100.0f);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        o oVar = this.f355v;
        oVar.f358p.post(new m(oVar, 0, this.l.b()));
    }
}
