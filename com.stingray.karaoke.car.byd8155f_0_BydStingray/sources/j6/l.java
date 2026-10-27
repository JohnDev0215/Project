package j6;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.kca.model.AssetTypeV2;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f17049m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AssetTypeV2 f17050n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f17051p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(n nVar, AssetTypeV2 assetTypeV2, String str, boolean z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f17049m = nVar;
        this.f17050n = assetTypeV2;
        this.o = str;
        this.f17051p = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new l(this.f17049m, this.f17050n, this.o, this.f17051p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((l) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        String str = this.o;
        AssetTypeV2 assetTypeV2 = this.f17050n;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        n nVar = this.f17049m;
        try {
        } catch (Exception e10) {
            B9.a aVar = Timber.f22577a;
            aVar.e("ProfileManager");
            aVar.a(e10);
        }
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Integer num = r6.d.c(nVar.f17057a.b(), assetTypeV2, str, 26).f13081a;
            if (num != null && num.intValue() != 0) {
                a aVar2 = new a(assetTypeV2, str, false);
                this.l = 1;
                if (n.a(nVar, aVar2, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
            String str2 = "Failed to remove " + str + " from favorites";
            A7.m.f(str2, "error");
            B9.a aVar3 = Timber.f22577a;
            aVar3.e("ProfileManager");
            aVar3.b(str2, new Object[0]);
            return m7.q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AbstractC1386c.u(obj);
        if (this.f17051p) {
            AbstractC0727y.w(nVar.c, null, null, new k(2, null), 3);
        }
        return m7.q.f19267a;
    }
}
