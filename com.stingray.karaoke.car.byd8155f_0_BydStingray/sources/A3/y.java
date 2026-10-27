package A3;

import coil.compose.AsyncImagePainter$State$Error;
import coil.compose.AsyncImagePainter$State$Loading;
import r0.AbstractC2268a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class y implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f406m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f407n;
    public final /* synthetic */ Object o;

    public /* synthetic */ y(Object obj, Object obj2, Object obj3, int i7) {
        this.l = i7;
        this.f406m = obj;
        this.f407n = obj2;
        this.o = obj3;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        AsyncImagePainter$State$Error asyncImagePainter$State$ErrorB;
        switch (this.l) {
            case 0:
                f fVar = (f) obj;
                if (fVar instanceof AsyncImagePainter$State$Loading) {
                    AbstractC2268a abstractC2268a = (AbstractC2268a) this.f406m;
                    AsyncImagePainter$State$Loading asyncImagePainter$State$Loading = (AsyncImagePainter$State$Loading) fVar;
                    if (abstractC2268a == null) {
                        return asyncImagePainter$State$Loading;
                    }
                    asyncImagePainter$State$Loading.getClass();
                    return new AsyncImagePainter$State$Loading(abstractC2268a);
                }
                if (!(fVar instanceof AsyncImagePainter$State$Error)) {
                    return fVar;
                }
                AsyncImagePainter$State$Error asyncImagePainter$State$Error = (AsyncImagePainter$State$Error) fVar;
                if (asyncImagePainter$State$Error.f12644a.c instanceof K3.l) {
                    AbstractC2268a abstractC2268a2 = (AbstractC2268a) this.f407n;
                    if (abstractC2268a2 == null) {
                        return asyncImagePainter$State$Error;
                    }
                    asyncImagePainter$State$ErrorB = AsyncImagePainter$State$Error.b(asyncImagePainter$State$Error, abstractC2268a2);
                } else {
                    AbstractC2268a abstractC2268a3 = (AbstractC2268a) this.o;
                    if (abstractC2268a3 == null) {
                        return asyncImagePainter$State$Error;
                    }
                    asyncImagePainter$State$ErrorB = AsyncImagePainter$State$Error.b(asyncImagePainter$State$Error, abstractC2268a3);
                }
                return asyncImagePainter$State$ErrorB;
            default:
                long jLongValue = ((Long) obj).longValue();
                A7.y yVar = (A7.y) this.f406m;
                long j3 = yVar.l;
                if (j3 == 0) {
                    yVar.l = jLongValue;
                } else {
                    P5.p pVar = (P5.p) this.o;
                    ((A7.w) this.f407n).l = pVar.a(pVar.f5381b, jLongValue - j3);
                }
                return m7.q.f19267a;
        }
    }
}
