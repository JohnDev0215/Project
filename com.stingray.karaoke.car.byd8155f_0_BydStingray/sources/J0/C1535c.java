package j0;

import A7.o;
import A7.v;
import E0.r0;
import e7.f;
import y0.C2733k;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: j0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1535c extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16996m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f16997n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1535c(v vVar) {
        super(1);
        this.f16997n = vVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16996m) {
            case 0:
                if (((C1536d) obj).f14695x) {
                    v vVar = this.f16997n;
                    vVar.l = vVar.l;
                }
                break;
            default:
                if (((C2733k) obj).f24069A) {
                    this.f16997n.l = false;
                }
                break;
        }
        return r0.l;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1535c(f fVar, C1536d c1536d, v vVar) {
        super(1);
        this.f16997n = vVar;
    }
}
