package A3;

import S8.InterfaceC0725w;
import T.C0735d;
import U8.EnumC0810a;
import V8.AbstractC0836z;
import V8.C0819h;
import coil.compose.AsyncImagePainter;
import h8.AbstractC1386c;
import q7.C2195i;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class j extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AsyncImagePainter f391m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(AsyncImagePainter asyncImagePainter, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f391m = asyncImagePainter;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new j(this.f391m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i7 = 0;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i10 = this.l;
        if (i10 == 0) {
            AbstractC1386c.u(obj);
            AsyncImagePainter asyncImagePainter = this.f391m;
            C0819h c0819hW = C0735d.W(new g(asyncImagePainter, i7));
            h hVar = new h(asyncImagePainter, null);
            int i11 = AbstractC0836z.f9667a;
            W8.n nVar = new W8.n(new P5.v(hVar, null), c0819hW, C2195i.l, -2, EnumC0810a.l);
            i iVar = new i(asyncImagePainter, i7);
            this.l = 1;
            if (nVar.b(iVar, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
