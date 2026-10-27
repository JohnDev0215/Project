package b2;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.k;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import n1.m;

/* JADX INFO: compiled from: GifFrameLoader.java */
/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l1.a f3497a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f3498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<b> f3499c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final k f3500d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final r1.d f3501e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3502f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3503g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f3504h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private com.bumptech.glide.j<Bitmap> f3505i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private a f3506j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f3507k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private a f3508l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Bitmap f3509m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private m<Bitmap> f3510n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private a f3511o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private d f3512p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f3513q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f3514r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f3515s;

    /* JADX INFO: compiled from: GifFrameLoader.java */
    static class a extends h2.c<Bitmap> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final Handler f3516i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final int f3517j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final long f3518k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private Bitmap f3519l;

        a(Handler handler, int i7, long j7) {
            this.f3516i = handler;
            this.f3517j = i7;
            this.f3518k = j7;
        }

        Bitmap b() {
            return this.f3519l;
        }

        @Override // h2.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void k(Bitmap bitmap, i2.d<? super Bitmap> dVar) {
            this.f3519l = bitmap;
            this.f3516i.sendMessageAtTime(this.f3516i.obtainMessage(1, this), this.f3518k);
        }

        @Override // h2.h
        public void i(Drawable drawable) {
            this.f3519l = null;
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
            int i7 = message.what;
            if (i7 == 1) {
                g.this.m((a) message.obj);
                return true;
            }
            if (i7 != 2) {
                return false;
            }
            g.this.f3500d.o((a) message.obj);
            return false;
        }
    }

    /* JADX INFO: compiled from: GifFrameLoader.java */
    interface d {
        void a();
    }

    g(com.bumptech.glide.c cVar, l1.a aVar, int i7, int i8, m<Bitmap> mVar, Bitmap bitmap) {
        this(cVar.f(), com.bumptech.glide.c.t(cVar.h()), aVar, null, i(com.bumptech.glide.c.t(cVar.h()), i7, i8), mVar, bitmap);
    }

    private static n1.f g() {
        return new j2.d(Double.valueOf(Math.random()));
    }

    private static com.bumptech.glide.j<Bitmap> i(k kVar, int i7, int i8) {
        return kVar.c().a(g2.f.j0(q1.j.f9022b).h0(true).Z(true).Q(i7, i8));
    }

    private void l() {
        if (!this.f3502f || this.f3503g) {
            return;
        }
        if (this.f3504h) {
            k2.j.a(this.f3511o == null, "Pending target must be null when starting from the first frame");
            this.f3497a.i();
            this.f3504h = false;
        }
        a aVar = this.f3511o;
        if (aVar != null) {
            this.f3511o = null;
            m(aVar);
            return;
        }
        this.f3503g = true;
        long jUptimeMillis = SystemClock.uptimeMillis() + ((long) this.f3497a.g());
        this.f3497a.e();
        this.f3508l = new a(this.f3498b, this.f3497a.a(), jUptimeMillis);
        this.f3505i.a(g2.f.k0(g())).x0(this.f3497a).q0(this.f3508l);
    }

    private void n() {
        Bitmap bitmap = this.f3509m;
        if (bitmap != null) {
            this.f3501e.d(bitmap);
            this.f3509m = null;
        }
    }

    private void p() {
        if (this.f3502f) {
            return;
        }
        this.f3502f = true;
        this.f3507k = false;
        l();
    }

    private void q() {
        this.f3502f = false;
    }

    void a() {
        this.f3499c.clear();
        n();
        q();
        a aVar = this.f3506j;
        if (aVar != null) {
            this.f3500d.o(aVar);
            this.f3506j = null;
        }
        a aVar2 = this.f3508l;
        if (aVar2 != null) {
            this.f3500d.o(aVar2);
            this.f3508l = null;
        }
        a aVar3 = this.f3511o;
        if (aVar3 != null) {
            this.f3500d.o(aVar3);
            this.f3511o = null;
        }
        this.f3497a.clear();
        this.f3507k = true;
    }

    ByteBuffer b() {
        return this.f3497a.h().asReadOnlyBuffer();
    }

    Bitmap c() {
        a aVar = this.f3506j;
        return aVar != null ? aVar.b() : this.f3509m;
    }

    int d() {
        a aVar = this.f3506j;
        if (aVar != null) {
            return aVar.f3517j;
        }
        return -1;
    }

    Bitmap e() {
        return this.f3509m;
    }

    int f() {
        return this.f3497a.f();
    }

    int h() {
        return this.f3515s;
    }

    int j() {
        return this.f3497a.b() + this.f3513q;
    }

    int k() {
        return this.f3514r;
    }

    void m(a aVar) {
        d dVar = this.f3512p;
        if (dVar != null) {
            dVar.a();
        }
        this.f3503g = false;
        if (this.f3507k) {
            this.f3498b.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        if (!this.f3502f) {
            this.f3511o = aVar;
            return;
        }
        if (aVar.b() != null) {
            n();
            a aVar2 = this.f3506j;
            this.f3506j = aVar;
            for (int size = this.f3499c.size() - 1; size >= 0; size--) {
                this.f3499c.get(size).a();
            }
            if (aVar2 != null) {
                this.f3498b.obtainMessage(2, aVar2).sendToTarget();
            }
        }
        l();
    }

    void o(m<Bitmap> mVar, Bitmap bitmap) {
        this.f3510n = (m) k2.j.d(mVar);
        this.f3509m = (Bitmap) k2.j.d(bitmap);
        this.f3505i = this.f3505i.a(new g2.f().d0(mVar));
        this.f3513q = k2.k.g(bitmap);
        this.f3514r = bitmap.getWidth();
        this.f3515s = bitmap.getHeight();
    }

    void r(b bVar) {
        if (this.f3507k) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        }
        if (this.f3499c.contains(bVar)) {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        boolean zIsEmpty = this.f3499c.isEmpty();
        this.f3499c.add(bVar);
        if (zIsEmpty) {
            p();
        }
    }

    void s(b bVar) {
        this.f3499c.remove(bVar);
        if (this.f3499c.isEmpty()) {
            q();
        }
    }

    g(r1.d dVar, k kVar, l1.a aVar, Handler handler, com.bumptech.glide.j<Bitmap> jVar, m<Bitmap> mVar, Bitmap bitmap) {
        this.f3499c = new ArrayList();
        this.f3500d = kVar;
        handler = handler == null ? new Handler(Looper.getMainLooper(), new c()) : handler;
        this.f3501e = dVar;
        this.f3498b = handler;
        this.f3505i = jVar;
        this.f3497a = aVar;
        o(mVar, bitmap);
    }
}
