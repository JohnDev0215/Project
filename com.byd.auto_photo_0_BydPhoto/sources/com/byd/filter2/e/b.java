package com.byd.filter2.e;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2822a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[] f2823b = com.byd.filter2.c.c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int f2824c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected Resources h;
    protected FloatBuffer i;
    protected FloatBuffer j;
    protected float[] k = Arrays.copyOf(f2823b, 16);
    protected int l = 0;
    private int m = 0;
    private float[] n = {-1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f};
    private float[] o = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    protected int p;
    protected int q;
    protected long r;
    protected long s;
    private int t;

    public b(Resources resources) {
        this.h = resources;
    }

    public static int C(String str, String str2) {
        int iD;
        int iD2 = D(35633, str);
        if (iD2 == 0 || (iD = D(35632, str2)) == 0) {
            return 0;
        }
        int iGlCreateProgram = GLES20.glCreateProgram();
        if (iGlCreateProgram != 0) {
            GLES20.glAttachShader(iGlCreateProgram, iD2);
            GLES20.glAttachShader(iGlCreateProgram, iD);
            GLES20.glLinkProgram(iGlCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
            if (iArr[0] != 1) {
                m(1, "Could not link program:" + GLES20.glGetProgramInfoLog(iGlCreateProgram));
                GLES20.glDeleteProgram(iGlCreateProgram);
                return 0;
            }
        }
        return iGlCreateProgram;
    }

    public static int D(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        if (iGlCreateShader == 0) {
            return iGlCreateShader;
        }
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        m(1, "Could not compile shader:" + i);
        m(1, "GLES20 Error:" + GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        return 0;
    }

    public static void m(int i, Object obj) {
        if (!f2822a || i == 0) {
            return;
        }
        Log.e("m_tag", "glError:" + i + "---" + obj);
    }

    public b A(int i) {
        this.t = i;
        return this;
    }

    public final void B(int i) {
        this.m = i;
    }

    public final void a() {
        q();
        n();
    }

    protected final void b(String str, String str2) {
        int iC = C(str, str2);
        this.f2824c = iC;
        this.d = GLES20.glGetAttribLocation(iC, "vPosition");
        this.e = GLES20.glGetAttribLocation(this.f2824c, "vCoord");
        this.f = GLES20.glGetUniformLocation(this.f2824c, "vMatrix");
        this.g = GLES20.glGetUniformLocation(this.f2824c, "vTexture");
    }

    public void c() {
        p();
        u();
        s();
        o();
        r();
    }

    public long d() {
        return this.s;
    }

    public long e() {
        return this.r;
    }

    public int f() {
        return this.q;
    }

    public float[] g() {
        return this.k;
    }

    public int h() {
        return this.m;
    }

    public int i() {
        return this.t;
    }

    public final int j() {
        return this.m;
    }

    public final int k() {
        return this.l;
    }

    public int l() {
        return this.p;
    }

    protected void n() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(32);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
        this.i = floatBufferAsFloatBuffer;
        floatBufferAsFloatBuffer.put(this.n);
        this.i.position(0);
        ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(32);
        byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
        FloatBuffer floatBufferAsFloatBuffer2 = byteBufferAllocateDirect2.asFloatBuffer();
        this.j = floatBufferAsFloatBuffer2;
        floatBufferAsFloatBuffer2.put(this.o);
        this.j.position(0);
    }

    protected void o() {
        GLES20.glActiveTexture(this.l + 33984);
        GLES20.glBindTexture(3553, j());
        GLES20.glUniform1i(this.g, this.l);
    }

    protected void p() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16640);
    }

    protected abstract void q();

    protected void r() {
        GLES20.glEnableVertexAttribArray(this.d);
        GLES20.glVertexAttribPointer(this.d, 2, 5126, false, 0, (Buffer) this.i);
        GLES20.glEnableVertexAttribArray(this.e);
        GLES20.glVertexAttribPointer(this.e, 2, 5126, false, 0, (Buffer) this.j);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.d);
        GLES20.glDisableVertexAttribArray(this.e);
    }

    protected void s() {
        GLES20.glUniformMatrix4fv(this.f, 1, false, this.k, 0);
    }

    protected abstract void t(int i, int i2);

    protected void u() {
        GLES20.glUseProgram(this.f2824c);
    }

    public void v() {
    }

    public void w() {
        this.k = Arrays.copyOf(f2823b, 16);
    }

    public b x(long j, long j2) {
        this.r = j;
        this.s = j2;
        return this;
    }

    public void y(float[] fArr) {
        this.k = fArr;
    }

    public final void z(int i, int i2) {
        if (this.p == i && this.q == i2) {
            return;
        }
        this.p = i;
        this.q = i2;
        t(i, i2);
    }
}
