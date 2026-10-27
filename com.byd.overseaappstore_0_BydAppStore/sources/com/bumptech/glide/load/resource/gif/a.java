package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import spi.bd;
import spi.i6;
import spi.nm;
import spi.pa;
import spi.qm;
import spi.qt;
import spi.tm;
import spi.tu;
import spi.vt;
import spi.w1;
import spi.w4;
import spi.wj;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final bd a;
    public final Handler b;
    public final List<b> c;
    public final qm d;
    public final w1 e;
    public boolean f;
    public boolean g;
    public nm<Bitmap> h;
    public C0020a i;
    public boolean j;
    public C0020a k;
    public Bitmap l;
    public qt<Bitmap> m;
    public C0020a n;

    @Nullable
    public d o;
    public int p;
    public int q;
    public int r;

    /* JADX INFO: renamed from: com.bumptech.glide.load.resource.gif.a$a, reason: collision with other inner class name */
    @VisibleForTesting
    public static class C0020a extends w4<Bitmap> {
        public final Handler d;
        public final int e;
        public final long f;
        public Bitmap g;

        public C0020a(Handler handler, int i, long j) {
            this.d = handler;
            this.e = i;
            this.f = j;
        }

        @Override // spi.rs
        public final void c(@NonNull Object obj, @Nullable vt vtVar) {
            this.g = (Bitmap) obj;
            this.d.sendMessageAtTime(this.d.obtainMessage(1, this), this.f);
        }

        @Override // spi.rs
        public final void i(@Nullable Drawable drawable) {
            this.g = null;
        }
    }

    public interface b {
        void a();
    }

    public class c implements Handler.Callback {
        public c() {
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                a.this.b((C0020a) message.obj);
                return true;
            }
            if (i != 2) {
                return false;
            }
            a.this.d.m((C0020a) message.obj);
            return false;
        }
    }

    @VisibleForTesting
    public interface d {
        void a();
    }

    public a(com.bumptech.glide.a aVar, bd bdVar, int i, int i2, qt<Bitmap> qtVar, Bitmap bitmap) {
        this(aVar.a, com.bumptech.glide.a.e(aVar.c.getBaseContext()), bdVar, null, com.bumptech.glide.a.e(aVar.c.getBaseContext()).k().a(((tm) ((tm) tm.A(i6.a).z()).u()).o(i, i2)), qtVar, bitmap);
    }

    public a(w1 w1Var, qm qmVar, bd bdVar, Handler handler, nm<Bitmap> nmVar, qt<Bitmap> qtVar, Bitmap bitmap) {
        this.c = new ArrayList();
        this.d = qmVar;
        handler = handler == null ? new Handler(Looper.getMainLooper(), new c()) : handler;
        this.e = w1Var;
        this.b = handler;
        this.h = nmVar;
        this.a = bdVar;
        c(qtVar, bitmap);
    }

    public final void a() {
        if (!this.f || this.g) {
            return;
        }
        C0020a c0020a = this.n;
        if (c0020a != null) {
            this.n = null;
            b(c0020a);
            return;
        }
        this.g = true;
        long jUptimeMillis = SystemClock.uptimeMillis() + ((long) this.a.d());
        this.a.b();
        this.k = new C0020a(this.b, this.a.e(), jUptimeMillis);
        nm<Bitmap> nmVarI = this.h.a(new tm().t(new wj(Double.valueOf(Math.random())))).I(this.a);
        C0020a c0020a2 = this.k;
        Objects.requireNonNull(nmVarI);
        nmVarI.F(c0020a2, nmVarI, pa.a);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<com.bumptech.glide.load.resource.gif.a$b>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.ArrayList, java.util.List<com.bumptech.glide.load.resource.gif.a$b>] */
    @VisibleForTesting
    public final void b(C0020a c0020a) {
        d dVar = this.o;
        if (dVar != null) {
            dVar.a();
        }
        this.g = false;
        if (this.j) {
            this.b.obtainMessage(2, c0020a).sendToTarget();
            return;
        }
        if (!this.f) {
            this.n = c0020a;
            return;
        }
        if (c0020a.g != null) {
            Bitmap bitmap = this.l;
            if (bitmap != null) {
                this.e.c(bitmap);
                this.l = null;
            }
            C0020a c0020a2 = this.i;
            this.i = c0020a;
            int size = this.c.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else {
                    ((b) this.c.get(size)).a();
                }
            }
            if (c0020a2 != null) {
                this.b.obtainMessage(2, c0020a2).sendToTarget();
            }
        }
        a();
    }

    public final void c(qt<Bitmap> qtVar, Bitmap bitmap) {
        Objects.requireNonNull(qtVar, "Argument must not be null");
        this.m = qtVar;
        Objects.requireNonNull(bitmap, "Argument must not be null");
        this.l = bitmap;
        this.h = this.h.a(new tm().x(qtVar, true));
        this.p = tu.c(bitmap);
        this.q = bitmap.getWidth();
        this.r = bitmap.getHeight();
    }

    @VisibleForTesting
    public void setOnEveryFrameReadyListener(@Nullable d dVar) {
        this.o = dVar;
    }
}
