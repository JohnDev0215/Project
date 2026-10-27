package z3;

import F3.j;
import S8.InterfaceC0725w;
import android.graphics.Bitmap;
import h8.AbstractC1386c;
import java.util.ArrayList;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ K3.i f24393m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ i f24394n;
    public final /* synthetic */ L3.g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ c f24395p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Bitmap f24396q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(K3.i iVar, i iVar2, L3.g gVar, c cVar, Bitmap bitmap, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f24393m = iVar;
        this.f24394n = iVar2;
        this.o = gVar;
        this.f24395p = cVar;
        this.f24396q = bitmap;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g(this.f24393m, this.f24394n, this.o, this.f24395p, this.f24396q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            ArrayList arrayList = this.f24394n.f24402g;
            boolean z6 = this.f24396q != null;
            K3.i iVar = this.f24393m;
            j jVar = new j(iVar, arrayList, 0, iVar, this.o, this.f24395p, z6);
            this.l = 1;
            obj = jVar.b(iVar, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
