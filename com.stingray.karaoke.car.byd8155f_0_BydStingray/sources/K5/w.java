package K5;

import S8.InterfaceC0725w;
import V8.f0;
import com.stingray.kca.model.KaraokeItem;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.HashSet;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class w extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ F l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f3662m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3663n;
    public final /* synthetic */ int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(F f10, String str, int i7, int i10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = f10;
        this.f3662m = str;
        this.f3663n = i7;
        this.o = i10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new w(this.l, this.f3662m, this.f3663n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((w) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        F f10 = this.l;
        boolean z6 = !((Boolean) ((f0) f10.d.f18294e.l).getValue()).booleanValue();
        int i7 = this.f3663n;
        int i10 = this.o;
        String str = this.f3662m;
        ArrayList arrayListA = F.a(f10, str, i7, i10, z6);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : arrayListA) {
            if (hashSet.add(((KaraokeItem) obj2).f13107a)) {
                arrayList.add(obj2);
            }
        }
        f10.f3618b.c(str, arrayList);
        return arrayList;
    }
}
