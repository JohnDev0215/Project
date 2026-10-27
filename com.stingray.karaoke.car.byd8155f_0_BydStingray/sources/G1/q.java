package g1;

import java.util.ArrayList;
import java.util.HashMap;
import l1.C1731b;
import m1.C1793a;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public G2.a f14745a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f14746b = true;
    public final HashMap c;
    public final HashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap f14747e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1731b f14748f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f14749g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f14750h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f14751i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f14752j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a1.b f14753k;
    public long l;

    public q(a1.b bVar) {
        HashMap map = new HashMap();
        this.c = map;
        this.d = new HashMap();
        this.f14747e = new HashMap();
        C1731b c1731b = new C1731b(this);
        this.f14748f = c1731b;
        this.f14749g = 0;
        this.f14750h = new ArrayList();
        this.f14751i = new ArrayList();
        this.f14752j = true;
        c1731b.f18384a = 0;
        map.put(0, c1731b);
        this.f14753k = bVar;
        this.l = io.sentry.config.a.f(0, 0, 15);
        this.f14745a = new G2.a(this, 8);
    }

    public final void a(Object obj) {
        this.f14750h.add(obj);
        this.f14752j = true;
    }

    public final C1731b b(Object obj) {
        HashMap map = this.c;
        l1.h hVar = (l1.h) map.get(obj);
        Object obj2 = hVar;
        if (hVar == null) {
            C1731b c1731b = new C1731b(this);
            map.put(obj, c1731b);
            c1731b.f18384a = obj;
            obj2 = c1731b;
        }
        if (obj2 instanceof C1731b) {
            return (C1731b) obj2;
        }
        return null;
    }

    public final int c(Float f10) {
        return Math.round(f10.floatValue());
    }

    public final m1.g d(int i7, String str) {
        C1731b c1731bB = b(str);
        Object obj = c1731bB.c;
        if (obj == null || !(obj instanceof m1.g)) {
            m1.g gVar = new m1.g(this);
            gVar.f18941b = i7;
            gVar.f18944g = str;
            c1731bB.c = gVar;
            c1731bB.c(gVar.b());
        }
        return (m1.g) c1731bB.c;
    }

    public final l1.g e(int i7) {
        l1.g hVar;
        l1.g eVar;
        StringBuilder sb = new StringBuilder("__HELPER_KEY_");
        int i10 = this.f14749g;
        this.f14749g = i10 + 1;
        String strH = M5.b.h(sb, i10, "__");
        HashMap map = this.d;
        l1.g gVar = (l1.g) map.get(strH);
        l1.g gVar2 = gVar;
        if (gVar == null) {
            int i11 = 4;
            switch (AbstractC2641j.c(i7)) {
                case 0:
                    hVar = new m1.h(this, 1);
                    eVar = hVar;
                    break;
                case 1:
                    hVar = new m1.i(this, 2);
                    eVar = hVar;
                    break;
                case 2:
                    C1793a c1793a = new C1793a(this, i11, 0);
                    c1793a.f18892o0 = 0.5f;
                    hVar = c1793a;
                    eVar = hVar;
                    break;
                case 3:
                    C1793a c1793a2 = new C1793a(this, i11, 1);
                    c1793a2.f18892o0 = 0.5f;
                    hVar = c1793a2;
                    eVar = hVar;
                    break;
                case 4:
                    hVar = new m1.b(this, 5);
                    eVar = hVar;
                    break;
                case 5:
                default:
                    eVar = new l1.g(this, i7);
                    break;
                case 6:
                case 7:
                    eVar = new m1.e(this, i7);
                    break;
                case 8:
                case 9:
                case 10:
                    eVar = new m1.f(this, i7);
                    break;
            }
            eVar.f18384a = strH;
            map.put(strH, eVar);
            gVar2 = eVar;
        }
        return gVar2;
    }
}
