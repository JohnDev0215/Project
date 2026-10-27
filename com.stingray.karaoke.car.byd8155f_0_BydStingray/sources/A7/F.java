package A7;

import F0.V;
import T.C0732b0;
import a6.C0928a;
import c6.C1034a;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import java.util.Map;
import l0.InterfaceC1716g;
import n3.C1927B;
import n3.C1930E;
import n3.C1934I;
import n3.C1937b;
import n7.AbstractC1986a;
import n7.AbstractC1990e;
import y0.AbstractC2737o;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class F implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f444m;

    public /* synthetic */ F(Object obj, int i7) {
        this.l = i7;
        this.f444m = obj;
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [A7.o, z7.a] */
    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        String strValueOf;
        m7.q qVar = m7.q.f19267a;
        Object obj2 = this.f444m;
        switch (this.l) {
            case 0:
                H7.A a10 = (H7.A) obj;
                m.f(a10, "it");
                ((H) obj2).getClass();
                H7.B b10 = a10.f2839a;
                if (b10 == null) {
                    return "*";
                }
                H7.x xVar = a10.f2840b;
                H h10 = xVar instanceof H ? (H) xVar : null;
                if (h10 == null || (strValueOf = h10.d(true)) == null) {
                    strValueOf = String.valueOf(xVar);
                }
                int i7 = G.f445a[b10.ordinal()];
                if (i7 == 1) {
                    return strValueOf;
                }
                if (i7 == 2) {
                    return "in ".concat(strValueOf);
                }
                if (i7 == 3) {
                    return "out ".concat(strValueOf);
                }
                throw new A9.k();
            case 1:
                ((C3.e) obj2).f988v = true;
                return qVar;
            case 2:
                int i10 = MainActivity.f12974j0;
                m.f((h.p) obj, "$this$addCallback");
                io.sentry.hints.i iVar = k6.o.f18284a;
                if (!io.sentry.hints.i.h()) {
                    MainActivity mainActivity = (MainActivity) obj2;
                    if (!mainActivity.n().f()) {
                        mainActivity.moveTaskToBack(true);
                    }
                }
                return qVar;
            case 3:
                y0.q qVar2 = (y0.q) obj;
                long jI = AbstractC2737o.i(qVar2, false);
                qVar2.a();
                ((P5.l) obj2).f5373m.r((int) m0.c.d(jI), (int) m0.c.e(jI), true);
                qVar2.a();
                return qVar;
            case 4:
                m.f(obj, "it");
                return ((o) obj2).a();
            case 5:
                KaraokeItem karaokeItem = (KaraokeItem) obj;
                m.f(karaokeItem, "it");
                C0928a c0928a = (C0928a) obj2;
                String str = c0928a.f12250s;
                c0928a.n(karaokeItem, str != null ? new F6.b(SourceType.PAGE, str) : null);
                return qVar;
            case 6:
                ((C0732b0) obj2).j((int) (((a1.j) obj).f11037a >> 32));
                return qVar;
            case 7:
                InterfaceC1716g.a((InterfaceC1716g) obj2);
                return qVar;
            case 8:
                m.f((T.G) obj, "$this$DisposableEffect");
                return new V((C1034a) obj2, 6);
            case 9:
                C1934I c1934i = (C1934I) obj;
                int i11 = C1927B.f19789y;
                c1934i.d = ((n3.y) P8.m.f0(P8.m.d0(((C1930E) obj2).j(), C1937b.f19853v))).f19917q;
                c1934i.f19834e = false;
                c1934i.f19834e = true;
                c1934i.f19835f = false;
                return qVar;
            case 10:
                n6.w wVar = (n6.w) obj2;
                return Long.valueOf(C7.a.Y((((wVar != null ? wVar.f20140e : 0) * (wVar != null ? wVar.f20141f : 0.0f)) / 60.0f) * (((Integer) obj).intValue() / 1000.0f)));
            case 11:
                return obj == ((AbstractC1986a) obj2) ? "(this Collection)" : String.valueOf(obj);
            default:
                Map.Entry entry = (Map.Entry) obj;
                m.f(entry, "it");
                AbstractC1990e abstractC1990e = (AbstractC1990e) obj2;
                abstractC1990e.getClass();
                StringBuilder sb = new StringBuilder();
                Object key = entry.getKey();
                sb.append(key == abstractC1990e ? "(this Map)" : String.valueOf(key));
                sb.append('=');
                Object value = entry.getValue();
                sb.append(value != abstractC1990e ? String.valueOf(value) : "(this Map)");
                return sb.toString();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ F(InterfaceC2772a interfaceC2772a) {
        this.l = 4;
        this.f444m = (o) interfaceC2772a;
    }
}
