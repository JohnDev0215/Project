package N5;

import S8.InterfaceC0725w;
import android.net.Uri;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ e0 f4683m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f4684n;
    public final /* synthetic */ A7.z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ k2.m f4685p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(e0 e0Var, String str, A7.z zVar, k2.m mVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4683m = e0Var;
        this.f4684n = str;
        this.o = zVar;
        this.f4685p = mVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d0(this.f4683m, this.f4684n, this.o, this.f4685p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            c0 c0Var = this.f4683m.f4688m;
            this.l = 1;
            obj = c0Var.invoke(this.f4684n, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        Uri uri = (Uri) obj;
        if (uri != null) {
            k2.l lVarA = this.f4685p.a();
            lVarA.f17667a = uri;
            this.o.l = lVarA.a();
        }
        return m7.q.f19267a;
    }
}
