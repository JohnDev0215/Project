package com.byd.filter2.h;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;
import java.util.Iterator;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes2.dex */
public class c extends com.byd.filter2.e.b {
    private int[] A;
    private int[] B;
    private int C;
    protected Queue<com.byd.filter2.e.b> u;
    private Vector<com.byd.filter2.e.b> v;
    private int w;
    private long x;
    private int y;
    private int[] z;

    public c(Resources resources) {
        super(resources);
        this.w = 0;
        this.y = 2;
        this.z = new int[1];
        this.A = new int[1];
        this.B = new int[2];
        this.C = 0;
        this.v = new Vector<>();
        this.u = new ConcurrentLinkedQueue();
    }

    private void F() {
        this.w = 0;
        this.u.clear();
        this.v.clear();
    }

    private void I() {
        while (true) {
            com.byd.filter2.e.b bVarPoll = this.u.poll();
            if (bVarPoll == null) {
                return;
            }
            bVarPoll.a();
            bVarPoll.z(this.p, this.q);
            this.v.add(bVarPoll);
            this.w++;
            Log.e("m_tag", "updateFilter:" + this.w);
        }
    }

    public void E(com.byd.filter2.e.b bVar) {
        com.byd.filter2.c.a(bVar.g(), false, true);
        this.u.add(bVar);
        Log.e("m_tag", "add Filter");
    }

    public boolean G(com.byd.filter2.e.b bVar) {
        boolean zRemove = this.v.remove(bVar);
        if (zRemove) {
            this.w--;
        }
        return zRemove;
    }

    public void H(long j) {
        this.x = j;
    }

    @Override // com.byd.filter2.e.b
    public void c() {
        I();
        this.C = 0;
        if (this.w > 0) {
            for (com.byd.filter2.e.b bVar : this.v) {
                if (this.x >= bVar.e() && this.x <= bVar.d()) {
                    GLES20.glBindFramebuffer(36160, this.z[0]);
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.B[this.C % 2], 0);
                    GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, this.A[0]);
                    GLES20.glViewport(0, 0, this.p, this.q);
                    int i = this.C;
                    bVar.B(i == 0 ? j() : this.B[(i - 1) % 2]);
                    bVar.c();
                    com.byd.filter2.b.f();
                    this.C++;
                }
            }
        }
    }

    @Override // com.byd.filter2.e.b
    public int h() {
        int i;
        return (this.w == 0 || (i = this.C) == 0) ? j() : this.B[(i - 1) % 2];
    }

    @Override // com.byd.filter2.e.b
    protected void n() {
    }

    @Override // com.byd.filter2.e.b
    protected void q() {
    }

    @Override // com.byd.filter2.e.b
    protected void t(int i, int i2) {
        I();
        com.byd.filter2.b.c(this.z, this.B, this.A, i, i2);
    }

    @Override // com.byd.filter2.e.b
    public void v() {
        Vector<com.byd.filter2.e.b> vector = this.v;
        if (vector != null && vector.size() > 0) {
            Iterator<com.byd.filter2.e.b> it = this.v.iterator();
            while (it.hasNext()) {
                it.next().v();
            }
        }
        F();
    }
}
