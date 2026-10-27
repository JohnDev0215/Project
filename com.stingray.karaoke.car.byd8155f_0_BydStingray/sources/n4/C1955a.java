package n4;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import h4.InterfaceC1359a;
import java.io.InputStream;
import java.util.ArrayDeque;
import p4.C2103b;
import p4.C2104c;

/* JADX INFO: renamed from: n4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1955a implements e4.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19930a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19931b;
    public final Object c;

    public /* synthetic */ C1955a(Object obj, int i7, Object obj2) {
        this.f19930a = i7;
        this.f19931b = obj;
        this.c = obj2;
    }

    @Override // e4.j
    public final g4.x a(Object obj, int i7, int i10, e4.h hVar) {
        boolean z6;
        w wVar;
        A4.f fVar;
        switch (this.f19930a) {
            case 0:
                g4.x xVarA = ((e4.j) this.f19931b).a(obj, i7, i10, hVar);
                if (xVarA == null) {
                    return null;
                }
                return new C1958d((Resources) this.c, xVarA);
            case 1:
                g4.x xVarC = ((C2104c) this.f19931b).c((Uri) obj, hVar);
                if (xVarC == null) {
                    return null;
                }
                return r.a((InterfaceC1359a) this.c, (Drawable) ((C2103b) xVarC).get(), i7, i10);
            default:
                InputStream inputStream = (InputStream) obj;
                if (inputStream instanceof w) {
                    wVar = (w) inputStream;
                    z6 = false;
                } else {
                    z6 = true;
                    wVar = new w(inputStream, (F.l) this.c);
                }
                ArrayDeque arrayDeque = A4.f.f417n;
                synchronized (arrayDeque) {
                    fVar = (A4.f) arrayDeque.poll();
                    break;
                }
                if (fVar == null) {
                    fVar = new A4.f();
                }
                A4.f fVar2 = fVar;
                fVar2.l = wVar;
                A4.n nVar = new A4.n(fVar2);
                m2.b bVar = new m2.b(wVar, fVar2);
                try {
                    p pVar = (p) this.f19931b;
                    C1958d c1958dA = pVar.a(new h3.y(nVar, pVar.d, pVar.c), i7, i10, hVar, bVar);
                    fVar2.f418m = null;
                    fVar2.l = null;
                    synchronized (arrayDeque) {
                        arrayDeque.offer(fVar2);
                        break;
                    }
                    if (z6) {
                        wVar.g();
                    }
                    return c1958dA;
                } catch (Throwable th) {
                    fVar2.f418m = null;
                    fVar2.l = null;
                    ArrayDeque arrayDeque2 = A4.f.f417n;
                    synchronized (arrayDeque2) {
                        arrayDeque2.offer(fVar2);
                        if (z6) {
                            wVar.g();
                        }
                        throw th;
                    }
                }
        }
    }

    @Override // e4.j
    public final boolean b(Object obj, e4.h hVar) {
        switch (this.f19930a) {
            case 0:
                return ((e4.j) this.f19931b).b(obj, hVar);
            case 1:
                return "android.resource".equals(((Uri) obj).getScheme());
            default:
                return true;
        }
    }

    public C1955a(Resources resources, e4.j jVar) {
        this.f19930a = 0;
        this.c = resources;
        this.f19931b = jVar;
    }
}
