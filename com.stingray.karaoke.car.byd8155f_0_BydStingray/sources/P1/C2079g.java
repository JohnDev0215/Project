package p1;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: p1.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2079g implements InterfaceC2077e {
    public final p d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f20663f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20664g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f20660a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f20661b = false;
    public boolean c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f20662e = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20665h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C2080h f20666i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f20667j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f20668k = new ArrayList();
    public final ArrayList l = new ArrayList();

    public C2079g(p pVar) {
        this.d = pVar;
    }

    @Override // p1.InterfaceC2077e
    public final void a(InterfaceC2077e interfaceC2077e) {
        ArrayList<C2079g> arrayList = this.l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((C2079g) it.next()).f20667j) {
                return;
            }
        }
        this.c = true;
        p pVar = this.f20660a;
        if (pVar != null) {
            pVar.a(this);
        }
        if (this.f20661b) {
            this.d.a(this);
            return;
        }
        C2079g c2079g = null;
        int i7 = 0;
        for (C2079g c2079g2 : arrayList) {
            if (!(c2079g2 instanceof C2080h)) {
                i7++;
                c2079g = c2079g2;
            }
        }
        if (c2079g != null && i7 == 1 && c2079g.f20667j) {
            C2080h c2080h = this.f20666i;
            if (c2080h != null) {
                if (!c2080h.f20667j) {
                    return;
                } else {
                    this.f20663f = this.f20665h * c2080h.f20664g;
                }
            }
            d(c2079g.f20664g + this.f20663f);
        }
        p pVar2 = this.f20660a;
        if (pVar2 != null) {
            pVar2.a(this);
        }
    }

    public final void b(p pVar) {
        this.f20668k.add(pVar);
        if (this.f20667j) {
            pVar.a(pVar);
        }
    }

    public final void c() {
        this.l.clear();
        this.f20668k.clear();
        this.f20667j = false;
        this.f20664g = 0;
        this.c = false;
        this.f20661b = false;
    }

    public void d(int i7) {
        if (this.f20667j) {
            return;
        }
        this.f20667j = true;
        this.f20664g = i7;
        for (InterfaceC2077e interfaceC2077e : this.f20668k) {
            interfaceC2077e.a(interfaceC2077e);
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.f20680b.f20297i0);
        sb.append(":");
        switch (this.f20662e) {
            case 1:
                str = "UNKNOWN";
                break;
            case 2:
                str = "HORIZONTAL_DIMENSION";
                break;
            case 3:
                str = "VERTICAL_DIMENSION";
                break;
            case 4:
                str = "LEFT";
                break;
            case 5:
                str = "RIGHT";
                break;
            case 6:
                str = "TOP";
                break;
            case 7:
                str = "BOTTOM";
                break;
            case 8:
                str = "BASELINE";
                break;
            default:
                str = "null";
                break;
        }
        sb.append(str);
        sb.append("(");
        sb.append(this.f20667j ? Integer.valueOf(this.f20664g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.f20668k.size());
        sb.append(">");
        return sb.toString();
    }
}
