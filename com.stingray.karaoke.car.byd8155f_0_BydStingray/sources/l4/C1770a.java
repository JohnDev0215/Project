package l4;

import com.bumptech.glide.load.data.l;
import e4.g;
import e4.h;
import e7.f;
import java.util.ArrayDeque;
import k4.n;
import k4.o;
import k4.p;
import k4.q;

/* JADX INFO: renamed from: l4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1770a implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f18620b = g.a(2500, "com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f18621a;

    public C1770a(f fVar) {
        this.f18621a = fVar;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, h hVar) {
        k4.g gVar = (k4.g) obj;
        f fVar = this.f18621a;
        if (fVar != null) {
            o oVarA = o.a(gVar);
            n nVar = (n) fVar.f14480m;
            Object objA = nVar.a(oVarA);
            ArrayDeque arrayDeque = o.f18203b;
            synchronized (arrayDeque) {
                arrayDeque.offer(oVarA);
            }
            k4.g gVar2 = (k4.g) objA;
            if (gVar2 == null) {
                nVar.d(o.a(gVar), gVar);
            } else {
                gVar = gVar2;
            }
        }
        return new p(gVar, new l(gVar, ((Integer) hVar.c(f18620b)).intValue()));
    }

    @Override // k4.q
    public final /* bridge */ /* synthetic */ boolean b(Object obj) {
        return true;
    }
}
