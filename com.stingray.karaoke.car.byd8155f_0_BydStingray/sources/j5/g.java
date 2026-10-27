package J5;

import A7.m;
import H7.H;
import K5.o;
import K5.p;
import K5.t;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ h f3098m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f3099n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(h hVar, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3098m = hVar;
        this.f3099n = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.f3098m, this.f3099n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        q qVar = q.f19267a;
        h hVar = this.f3098m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            t tVar = hVar.d;
            String str = this.f3099n;
            m.c(str);
            this.l = 1;
            Object objX = H.x(tVar.a(tVar.f3657a), new p(str, null), this);
            if (objX != enumC2303a) {
                objX = qVar;
            }
            if (objX == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                if (i7 == 2) {
                    AbstractC1386c.u(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        t tVar2 = hVar.d;
        long j3 = hVar.f3103f;
        this.l = 2;
        Object objX2 = H.x(tVar2.a(tVar2.f3657a), new o(j3, null), this);
        if (objX2 != enumC2303a) {
            objX2 = qVar;
        }
        return objX2 == enumC2303a ? enumC2303a : qVar;
    }
}
