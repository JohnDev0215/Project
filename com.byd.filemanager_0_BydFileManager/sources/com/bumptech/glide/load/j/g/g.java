package com.bumptech.glide.load.j.g;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.o.k;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: GifFrameLoader.java */
/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.k.a f3308a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f3309b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<b> f3310c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final com.bumptech.glide.h f3311d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3312e;
    private boolean f;
    private boolean g;
    private boolean h;
    private com.bumptech.glide.g<Bitmap> i;
    private a j;
    private boolean k;
    private a l;
    private Bitmap m;
    private com.bumptech.glide.load.h<Bitmap> n;
    private a o;
    private d p;
    private int q;
    private int r;
    private int s;

    /* JADX INFO: compiled from: GifFrameLoader.java */
    static class a extends com.bumptech.glide.request.h.a<Bitmap> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Handler f3313e;
        final int f;
        private final long g;
        private Bitmap h;

        a(Handler handler, int i, long j) {
            this.f3313e = handler;
            this.f = i;
            this.g = j;
        }

        @Override // com.bumptech.glide.request.h.e
        public void h(Drawable drawable) {
            this.h = null;
        }

        Bitmap l() {
            return this.h;
        }

        @Override // com.bumptech.glide.request.h.e
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public void d(Bitmap bitmap, com.bumptech.glide.request.i.b<? super Bitmap> bVar) {
            this.h = bitmap;
            this.f3313e.sendMessageAtTime(this.f3313e.obtainMessage(1, this), this.g);
        }
    }

    /* JADX INFO: compiled from: GifFrameLoader.java */
    public interface b {
        void a();
    }

    /* JADX INFO: compiled from: GifFrameLoader.java */
    private class c implements Handler.Callback {
        c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                g.this.m((a) message.obj);
                return true;
            }
            if (i != 2) {
                return false;
            }
            g.this.f3311d.o((a) message.obj);
            return false;
        }
    }

    /* JADX INFO: compiled from: GifFrameLoader.java */
    interface d {
        void a();
    }

    g(com.bumptech.glide.c cVar, com.bumptech.glide.k.a aVar, int i, int i2, com.bumptech.glide.load.h<Bitmap> hVar, Bitmap bitmap) {
        this(cVar.f(), com.bumptech.glide.c.t(cVar.h()), aVar, null, i(com.bumptech.glide.c.t(cVar.h()), i, i2), hVar, bitmap);
    }

    private static com.bumptech.glide.load.c g() {
        return new com.bumptech.glide.n.b(Double.valueOf(Math.random()));
    }

    private static com.bumptech.glide.g<Bitmap> i(com.bumptech.glide.h hVar, int i, int i2) {
        return hVar.m().a(com.bumptech.glide.request.e.b0(com.bumptech.glide.load.engine.h.f3022b).Z(true).U(true).M(i, i2));
    }

    private void l() {
        if (!this.f || this.g) {
            return;
        }
        if (this.h) {
            com.bumptech.glide.o.j.a(this.o == null, "Pending target must be null when starting from the first frame");
            this.f3308a.i();
            this.h = false;
        }
        a aVar = this.o;
        if (aVar != null) {
            this.o = null;
            m(aVar);
            return;
        }
        this.g = true;
        long jUptimeMillis = SystemClock.uptimeMillis() + ((long) this.f3308a.g());
        this.f3308a.e();
        this.l = new a(this.f3309b, this.f3308a.a(), jUptimeMillis);
        this.i.a(com.bumptech.glide.request.e.c0(g())).n0(this.f3308a).i0(this.l);
    }

    private void n() {
        Bitmap bitmap = this.m;
        if (bitmap != null) {
            this.f3312e.d(bitmap);
            this.m = null;
        }
    }

    private void p() {
        if (this.f) {
            return;
        }
        this.f = true;
        this.k = false;
        l();
    }

    private void q() {
        this.f = false;
    }

    void a() {
        this.f3310c.clear();
        n();
        q();
        a aVar = this.j;
        if (aVar != null) {
            this.f3311d.o(aVar);
            this.j = null;
        }
        a aVar2 = this.l;
        if (aVar2 != null) {
            this.f3311d.o(aVar2);
            this.l = null;
        }
        a aVar3 = this.o;
        if (aVar3 != null) {
            this.f3311d.o(aVar3);
            this.o = null;
        }
        this.f3308a.clear();
        this.k = true;
    }

    ByteBuffer b() {
        return this.f3308a.h().asReadOnlyBuffer();
    }

    Bitmap c() {
        a aVar = this.j;
        return aVar != null ? aVar.l() : this.m;
    }

    int d() {
        a aVar = this.j;
        if (aVar != null) {
            return aVar.f;
        }
        return -1;
    }

    Bitmap e() {
        return this.m;
    }

    int f() {
        return this.f3308a.f();
    }

    int h() {
        return this.s;
    }

    int j() {
        return this.f3308a.b() + this.q;
    }

    int k() {
        return this.r;
    }

    void m(a aVar) {
        d dVar = this.p;
        if (dVar != null) {
            dVar.a();
        }
        this.g = false;
        if (this.k) {
            this.f3309b.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        if (!this.f) {
            this.o = aVar;
            return;
        }
        if (aVar.l() != null) {
            n();
            a aVar2 = this.j;
            this.j = aVar;
            for (int size = this.f3310c.size() - 1; size >= 0; size--) {
                this.f3310c.get(size).a();
            }
            if (aVar2 != null) {
                this.f3309b.obtainMessage(2, aVar2).sendToTarget();
            }
        }
        l();
    }

    void o(com.bumptech.glide.load.h<Bitmap> hVar, Bitmap bitmap) {
        this.n = (com.bumptech.glide.load.h) com.bumptech.glide.o.j.d(hVar);
        this.m = (Bitmap) com.bumptech.glide.o.j.d(bitmap);
        this.i = this.i.a(new com.bumptech.glide.request.e().V(hVar));
        this.q = k.g(bitmap);
        this.r = bitmap.getWidth();
        this.s = bitmap.getHeight();
    }

    void r(b bVar) {
        if (this.k) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        }
        if (this.f3310c.contains(bVar)) {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        boolean zIsEmpty = this.f3310c.isEmpty();
        this.f3310c.add(bVar);
        if (zIsEmpty) {
            p();
        }
    }

    void s(b bVar) {
        this.f3310c.remove(bVar);
        if (this.f3310c.isEmpty()) {
            q();
        }
    }

    g(com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.h hVar, com.bumptech.glide.k.a aVar, Handler handler, com.bumptech.glide.g<Bitmap> gVar, com.bumptech.glide.load.h<Bitmap> hVar2, Bitmap bitmap) {
        this.f3310c = new ArrayList();
        this.f3311d = hVar;
        handler = handler == null ? new Handler(Looper.getMainLooper(), new c()) : handler;
        this.f3312e = eVar;
        this.f3309b = handler;
        this.i = gVar;
        this.f3308a = aVar;
        o(hVar2, bitmap);
    }
}
