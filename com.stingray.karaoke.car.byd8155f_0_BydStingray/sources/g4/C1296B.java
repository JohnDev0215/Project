package g4;

import android.os.SystemClock;
import android.util.Log;
import e4.InterfaceC1226b;
import i4.InterfaceC1408a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import l3.P;

/* JADX INFO: renamed from: g4.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1296B implements g, f {
    public final h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i f14846m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile int f14847n;
    public volatile d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile Object f14848p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile k4.p f14849q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile e f14850r;

    public C1296B(h hVar, i iVar) {
        this.l = hVar;
        this.f14846m = iVar;
    }

    @Override // g4.f
    public final void a(e4.e eVar, Exception exc, com.bumptech.glide.load.data.e eVar2, int i7) {
        this.f14846m.a(eVar, exc, eVar2, this.f14849q.c.e());
    }

    @Override // g4.f
    public final void b(e4.e eVar, Object obj, com.bumptech.glide.load.data.e eVar2, int i7, e4.e eVar3) {
        this.f14846m.b(eVar, obj, eVar2, this.f14849q.c.e(), eVar);
    }

    public final boolean c(Object obj) throws Throwable {
        int i7 = A4.k.f423b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        boolean z6 = false;
        try {
            com.bumptech.glide.load.data.g gVarG = this.l.c.a().g(obj);
            Object objA = gVarG.a();
            InterfaceC1226b interfaceC1226bD = this.l.d(objA);
            P p10 = new P(interfaceC1226bD, objA, this.l.f14868i, 26);
            e4.e eVar = this.f14849q.f18205a;
            h hVar = this.l;
            e eVar2 = new e(eVar, hVar.f14872n);
            InterfaceC1408a interfaceC1408aA = hVar.f14867h.a();
            interfaceC1408aA.l(eVar2, p10);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + eVar2 + ", data: " + obj + ", encoder: " + interfaceC1226bD + ", duration: " + A4.k.a(jElapsedRealtimeNanos));
            }
            if (interfaceC1408aA.d(eVar2) != null) {
                this.f14850r = eVar2;
                this.o = new d(Collections.singletonList(this.f14849q.f18205a), this.l, this);
                this.f14849q.c.b();
                return true;
            }
            if (Log.isLoggable("SourceGenerator", 3)) {
                Log.d("SourceGenerator", "Attempt to write: " + this.f14850r + ", data: " + obj + " to the disk cache failed, maybe the disk cache is disabled? Trying to decode the data directly...");
            }
            try {
                this.f14846m.b(this.f14849q.f18205a, gVarG.a(), this.f14849q.c, this.f14849q.c.e(), this.f14849q.f18205a);
                return false;
            } catch (Throwable th) {
                th = th;
                z6 = true;
                if (!z6) {
                    this.f14849q.c.b();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // g4.g
    public final void cancel() {
        k4.p pVar = this.f14849q;
        if (pVar != null) {
            pVar.c.cancel();
        }
    }

    @Override // g4.g
    public final boolean e() {
        if (this.f14848p != null) {
            Object obj = this.f14848p;
            this.f14848p = null;
            try {
                if (!c(obj)) {
                    return true;
                }
            } catch (IOException e10) {
                if (Log.isLoggable("SourceGenerator", 3)) {
                    Log.d("SourceGenerator", "Failed to properly rewind or write data to cache", e10);
                }
            }
        }
        if (this.o != null && this.o.e()) {
            return true;
        }
        this.o = null;
        this.f14849q = null;
        boolean z6 = false;
        while (!z6 && this.f14847n < this.l.b().size()) {
            ArrayList arrayListB = this.l.b();
            int i7 = this.f14847n;
            this.f14847n = i7 + 1;
            this.f14849q = (k4.p) arrayListB.get(i7);
            if (this.f14849q != null && (this.l.f14873p.a(this.f14849q.c.e()) || this.l.c(this.f14849q.c.a()) != null)) {
                this.f14849q.c.f(this.l.o, new M.t(this, this.f14849q, false));
                z6 = true;
            }
        }
        return z6;
    }
}
