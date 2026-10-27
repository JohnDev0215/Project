package N5;

import S8.InterfaceC0725w;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class F extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ KaraokeSongResponseV2 f4603m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4604n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(Z z6, KaraokeSongResponseV2 karaokeSongResponseV2, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
        this.f4603m = karaokeSongResponseV2;
        this.f4604n = actionOwner;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new F(this.l, this.f4603m, this.f4604n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        F f10 = (F) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        f10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        if (z6.f4654e.f1212e) {
            D6.b bVarB = z6.b(false);
            D6.b bVarB2 = z6.b(true);
            D6.a aVarA = z6.a(this.f4603m);
            D6.e eVar = z6.f4654e;
            boolean z10 = eVar.f1213f;
            ActionOwner actionOwner = this.f4604n;
            if (z10) {
                eVar.d(bVarB, aVarA, actionOwner);
            }
            eVar.getClass();
            A7.m.f(actionOwner, "actionOwner");
            if (!eVar.f1214g && eVar.c("Karaoke Content Started", D6.e.a(bVarB2, aVarA, actionOwner), eVar.f1212e)) {
                eVar.d = aVarA.f1194a;
                eVar.f1214g = true;
            }
        }
        return m7.q.f19267a;
    }
}
