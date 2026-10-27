package spi;

import android.os.SystemClock;
import android.util.Log;
import com.bumptech.glide.load.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import spi.n5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class xr implements n5, n5.a {
    public final v5<?> a;
    public final n5.a b;
    public volatile int c;
    public volatile j5 d;
    public volatile Object e;
    public volatile ti.a<?> f;
    public volatile k5 g;

    public xr(v5<?> v5Var, n5.a aVar) {
        this.a = v5Var;
        this.b = aVar;
    }

    @Override // spi.n5.a
    public final void a(ig igVar, Object obj, m5<?> m5Var, DataSource dataSource, ig igVar2) {
        this.b.a(igVar, obj, m5Var, this.f.c.e(), igVar);
    }

    @Override // spi.n5
    public final boolean b() {
        if (this.e != null) {
            Object obj = this.e;
            this.e = null;
            try {
                if (!e(obj)) {
                    return true;
                }
            } catch (IOException e) {
                if (Log.isLoggable("SourceGenerator", 3)) {
                    Log.d("SourceGenerator", "Failed to properly rewind or write data to cache", e);
                }
            }
        }
        if (this.d != null && this.d.b()) {
            return true;
        }
        this.d = null;
        this.f = null;
        boolean z = false;
        while (!z) {
            if (!(this.c < ((ArrayList) this.a.c()).size())) {
                break;
            }
            List<ti.a<?>> listC = this.a.c();
            int i = this.c;
            this.c = i + 1;
            this.f = (ti.a) ((ArrayList) listC).get(i);
            if (this.f != null && (this.a.p.c(this.f.c.e()) || this.a.h(this.f.c.a()))) {
                this.f.c.d(this.a.o, new wr(this, this.f));
                z = true;
            }
        }
        return z;
    }

    @Override // spi.n5.a
    public final void c(ig igVar, Exception exc, m5<?> m5Var, DataSource dataSource) {
        this.b.c(igVar, exc, m5Var, this.f.c.e());
    }

    @Override // spi.n5
    public final void cancel() {
        ti.a<?> aVar = this.f;
        if (aVar != null) {
            aVar.c.cancel();
        }
    }

    @Override // spi.n5.a
    public final void d() {
        throw new UnsupportedOperationException();
    }

    public final boolean e(Object obj) throws Throwable {
        int i = ch.b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        boolean z = true;
        try {
            com.bumptech.glide.load.data.a aVarG = this.a.c.b.g(obj);
            Object objC = aVarG.c();
            f8<X> f8VarF = this.a.f(objC);
            l5 l5Var = new l5(f8VarF, objC, this.a.i);
            k5 k5Var = new k5(this.f.a, this.a.n);
            g6 g6VarB = this.a.b();
            g6VarB.a(k5Var, l5Var);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + k5Var + ", data: " + obj + ", encoder: " + f8VarF + ", duration: " + ch.a(jElapsedRealtimeNanos));
            }
            if (g6VarB.b(k5Var) != null) {
                this.g = k5Var;
                this.d = new j5(Collections.singletonList(this.f.a), this.a, this);
                this.f.c.b();
                return true;
            }
            if (Log.isLoggable("SourceGenerator", 3)) {
                Log.d("SourceGenerator", "Attempt to write: " + this.g + ", data: " + obj + " to the disk cache failed, maybe the disk cache is disabled? Trying to decode the data directly...");
            }
            try {
                this.b.a(this.f.a, aVarG.c(), this.f.c, this.f.c.e(), this.f.a);
                return false;
            } catch (Throwable th) {
                th = th;
                if (!z) {
                    this.f.c.b();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
    }
}
