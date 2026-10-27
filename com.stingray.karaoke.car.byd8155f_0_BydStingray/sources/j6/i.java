package j6;

import S8.InterfaceC0725w;
import com.stingray.kca.model.AssetTypeV2;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f17046m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AssetTypeV2 f17047n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(n nVar, String str, AssetTypeV2 assetTypeV2, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = nVar;
        this.f17046m = str;
        this.f17047n = assetTypeV2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new i(this.l, this.f17046m, this.f17047n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((i) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        AssetTypeV2 assetTypeV2 = this.f17047n;
        String str = this.f17046m;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        try {
            r6.d.f(this.l.f17057a.b(), str);
            return new a(assetTypeV2, str, true);
        } catch (Exception unused) {
            return new a(assetTypeV2, str, false);
        }
    }
}
