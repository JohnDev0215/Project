package X5;

import S8.AbstractC0727y;
import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import java.util.LinkedHashMap;
import java.util.Map;
import n6.C1966G;
import n6.C1967H;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0856e0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D0 f10234m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j6.n f10235n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0856e0(D0 d02, j6.n nVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10234m = d02;
        this.f10235n = nVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0856e0 c0856e0 = new C0856e0(this.f10234m, this.f10235n, interfaceC2189c);
        c0856e0.l = ((Boolean) obj).booleanValue();
        return c0856e0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        C0856e0 c0856e0 = (C0856e0) create(bool, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        c0856e0.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        KaraokeSongResponseV2 karaokeSongResponseV2;
        String str;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        if (this.l) {
            N5.f0 f0Var = (N5.f0) ((V8.f0) this.f10234m.f10158x.f4637P.l).getValue();
            if (f0Var != null && (karaokeSongResponseV2 = f0Var.f4691a) != null && (str = karaokeSongResponseV2.f13136a) != null) {
                j6.n nVar = this.f10235n;
                D0 d02 = this.f10234m;
                int iIntValue = ((Number) ((V8.f0) d02.B.f20010j.l).getValue()).intValue();
                float fFloatValue = ((Number) ((V8.f0) d02.B.f20013n.l).getValue()).floatValue();
                int iIntValue2 = ((Number) ((V8.f0) d02.B.f20016r.l).getValue()).intValue();
                int iIntValue3 = ((Number) ((V8.f0) d02.B.l.l).getValue()).intValue();
                synchronized (nVar) {
                    float f10 = iIntValue / iIntValue3;
                    C1966G c1966g = (C1966G) ((Map) nVar.f17060f.getValue()).get(str);
                    if (iIntValue > (c1966g != null ? c1966g.f20000a : 0)) {
                        LinkedHashMap linkedHashMapZ = n7.z.z((Map) nVar.f17060f.getValue());
                        linkedHashMapZ.put(str, new C1966G(iIntValue, fFloatValue, iIntValue2, f10));
                        V8.f0 f0Var2 = nVar.f17060f;
                        f0Var2.getClass();
                        f0Var2.m(null, linkedHashMapZ);
                        AbstractC0727y.w(nVar.f17058b, null, null, new j6.m(nVar, str, iIntValue, fFloatValue, iIntValue2, f10, null), 3);
                    }
                }
                C1967H c1967h = d02.B;
                boolean z6 = ((V8.f0) d02.f10158x.f4661h0.l).getValue() == LeadVocalState.LeadVocalOn;
                ActionOwner actionOwner = d02.f10158x.o;
                c1967h.getClass();
                A7.m.f(actionOwner, "actionOwner");
                V8.f0 f0Var3 = c1967h.f20011k;
                int iIntValue4 = ((Number) f0Var3.getValue()).intValue();
                V8.f0 f0Var4 = c1967h.f20009i;
                float fIntValue = iIntValue4 > 0 ? ((Number) f0Var4.getValue()).intValue() / ((Number) f0Var3.getValue()).floatValue() : 0.0f;
                int iIntValue5 = ((Number) f0Var4.getValue()).intValue();
                float fFloatValue2 = ((Number) c1967h.f20012m.getValue()).floatValue();
                int iIntValue6 = ((Number) c1967h.f20015q.getValue()).intValue();
                E6.b bVar = c1967h.f20004b;
                bVar.getClass();
                B5.z zVar = new B5.z();
                zVar.k(Integer.valueOf(iIntValue5), "points");
                zVar.k(Integer.valueOf(iIntValue6), "best_streak");
                zVar.k(Float.valueOf(fIntValue), "score_percentage");
                zVar.k(Float.valueOf(fFloatValue2), "hit_rate");
                zVar.k(str, "asset_id");
                zVar.k(Boolean.valueOf(z6), "lead_vocals");
                zVar.k(actionOwner, "action_owner");
                Z8.e eVar = S8.E.f8084a;
                AbstractC0727y.w(AbstractC0727y.a(Z8.d.f11010n), null, null, new E6.a(zVar, bVar, null), 3);
            }
            N5.Z z10 = this.f10234m.f10158x;
            Boolean bool = Boolean.TRUE;
            V8.f0 f0Var5 = z10.f4646Y;
            f0Var5.getClass();
            f0Var5.m(null, bool);
            V8.f0 f0Var6 = this.f10234m.f10117M;
            float fIntValue2 = ((Number) ((V8.f0) r1.B.f20010j.l).getValue()).intValue() / ((Number) ((V8.f0) this.f10234m.B.l.l).getValue()).floatValue();
            Float f11 = new Float(fIntValue2 >= 0.8f ? 5.0f : fIntValue2 >= 0.6f ? 4.0f : fIntValue2 >= 0.4f ? 3.0f : fIntValue2 >= 0.2f ? 2.0f : fIntValue2 > 0.0f ? 1.0f : 0.0f);
            f0Var6.getClass();
            f0Var6.m(null, f11);
            D0 d03 = this.f10234m;
            C1967H c1967h2 = d03.B;
            Float f12 = (Float) d03.f10117M.getValue();
            Integer num = f12 != null ? new Integer((int) f12.floatValue()) : null;
            c1967h2.f20002A.l(C1967H.a(c1967h2.f20005e, c1967h2.f20007g, num != null ? num.intValue() : 0));
        }
        return m7.q.f19267a;
    }
}
