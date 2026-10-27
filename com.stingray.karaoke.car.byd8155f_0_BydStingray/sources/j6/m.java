package j6;

import S8.InterfaceC0725w;
import com.stingray.kca.model.ScoringDetail;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f17052m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f17053n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f17054p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f17055q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ float f17056r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(n nVar, String str, int i7, float f10, int i10, float f11, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f17052m = nVar;
        this.f17053n = str;
        this.o = i7;
        this.f17054p = f10;
        this.f17055q = i10;
        this.f17056r = f11;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new m(this.f17052m, this.f17053n, this.o, this.f17054p, this.f17055q, this.f17056r, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        String str = this.f17053n;
        n nVar = this.f17052m;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                r6.d.h(nVar.f17057a.b(), str, new ScoringDetail(new Integer(this.o), new Float(this.f17054p), new Integer(this.f17055q), new Float(this.f17056r)));
                this.l = 1;
                if (n.b(nVar, str, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
        } catch (Exception e10) {
            B9.a aVar = Timber.f22577a;
            aVar.e("ProfileManager");
            aVar.a(e10);
        }
        return m7.q.f19267a;
    }
}
