package j6;

import S8.InterfaceC0725w;
import com.stingray.kca.model.AssetTypeV2;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f17040m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AssetTypeV2 f17041n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(n nVar, AssetTypeV2 assetTypeV2, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f17040m = nVar;
        this.f17041n = assetTypeV2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new e(this.f17040m, this.f17041n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((e) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        AssetTypeV2 assetTypeV2 = this.f17041n;
        n nVar = this.f17040m;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                Integer num = r6.d.c(nVar.f17057a.b(), assetTypeV2, null, 30).f13081a;
                if (num == null || num.intValue() == 0) {
                    B9.a aVar = Timber.f22577a;
                    aVar.e("ProfileManager");
                    aVar.b("Failed to clear from favorites", new Object[0]);
                } else {
                    a aVar2 = new a(assetTypeV2, null, false);
                    this.l = 1;
                    if (n.a(nVar, aVar2, this) == enumC2303a) {
                        return enumC2303a;
                    }
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
        } catch (Exception e10) {
            B9.a aVar3 = Timber.f22577a;
            aVar3.e("ProfileManager");
            aVar3.a(e10);
        }
        return m7.q.f19267a;
    }
}
