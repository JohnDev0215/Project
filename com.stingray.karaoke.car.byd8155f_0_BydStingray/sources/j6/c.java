package j6;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.kca.model.AssetTypeV2;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class c extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f17034m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f17035n;
    public final /* synthetic */ AssetTypeV2 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f17036p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f17037q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(n nVar, String str, AssetTypeV2 assetTypeV2, boolean z6, InterfaceC2772a interfaceC2772a, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f17034m = nVar;
        this.f17035n = str;
        this.o = assetTypeV2;
        this.f17036p = z6;
        this.f17037q = interfaceC2772a;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new c(this.f17034m, this.f17035n, this.o, this.f17036p, this.f17037q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((c) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        AssetTypeV2 assetTypeV2 = this.o;
        String str = this.f17035n;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        n nVar = this.f17034m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                r6.d.b(nVar.f17057a.b(), str, assetTypeV2);
                a aVar = new a(assetTypeV2, str, true);
                this.l = 1;
                if (n.a(nVar, aVar, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            if (this.f17036p) {
                AbstractC0727y.w(nVar.c, null, null, new b(2, null), 3);
            }
        } catch (A9.d e10) {
            if (e10.l == 422) {
                this.f17037q.a();
            } else {
                B9.a aVar2 = Timber.f22577a;
                aVar2.e("ProfileManager");
                aVar2.a(e10);
            }
        } catch (Exception e11) {
            B9.a aVar3 = Timber.f22577a;
            aVar3.e("ProfileManager");
            aVar3.a(e11);
        }
        return m7.q.f19267a;
    }
}
