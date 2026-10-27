package p;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class qs1 implements io.reactivex.rxjava3.functions.o {
    public static final qs1 e = new qs1(0);
    public static final qs1 k = new qs1(1);
    public static final qs1 l = new qs1(2);
    public static final qs1 m = new qs1(3);
    public static final qs1 n = new qs1(4);
    public static final qs1 o = new qs1(5);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final qs1 f174p = new qs1(6);
    public static final qs1 q = new qs1(7);
    public static final qs1 r = new qs1(8);
    public static final qs1 s = new qs1(9);
    public static final qs1 t = new qs1(10);
    public static final qs1 u = new qs1(11);
    public static final qs1 v = new qs1(12);
    public static final qs1 w = new qs1(13);
    public final /* synthetic */ int c;

    public /* synthetic */ qs1(int i) {
        this.c = i;
    }

    public final Optional a(o7 o7Var) {
        im0 im0Var;
        km0 km0Var;
        cn0 cn0Var;
        en0 en0Var;
        jn0 jn0Var;
        switch (this.c) {
            case 0:
                jm0 jm0Var = (jm0) o7Var.c;
                if (jm0Var != null && (im0Var = jm0Var.a) != null && (km0Var = im0Var.b) != null) {
                    obj = km0Var.b;
                }
                return Optional.b(obj);
            case 1:
            default:
                l82 l82Var = (l82) o7Var.c;
                return Optional.b(l82Var != null ? l82Var.a : null);
            case 2:
                dn0 dn0Var = (dn0) o7Var.c;
                if (dn0Var != null && (cn0Var = dn0Var.a) != null && (en0Var = cn0Var.b) != null) {
                    obj = en0Var.b;
                }
                return Optional.b(obj);
            case 3:
                kn0 kn0Var = (kn0) o7Var.c;
                if (kn0Var != null && (jn0Var = kn0Var.a) != null) {
                    obj = jn0Var.b;
                }
                return Optional.b(obj);
            case 4:
                kt0 kt0Var = (kt0) o7Var.c;
                return Optional.b(kt0Var != null ? kt0Var.a : null);
            case 5:
                ft0 ft0Var = (ft0) o7Var.c;
                return Optional.b(ft0Var != null ? ft0Var.a : null);
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final /* bridge */ /* synthetic */ Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return a((o7) obj);
            case 1:
                return b((o7) obj);
            case 2:
                return a((o7) obj);
            case 3:
                return a((o7) obj);
            case 4:
                return a((o7) obj);
            case 5:
                return a((o7) obj);
            case 6:
                return b((o7) obj);
            case 7:
                return b((o7) obj);
            case 8:
                return b((o7) obj);
            case 9:
                return b((o7) obj);
            case 10:
                return c((o7) obj);
            case 11:
                return c((o7) obj);
            case 12:
                return b((o7) obj);
            default:
                return a((o7) obj);
        }
    }

    public final List b(o7 o7Var) {
        pm0 pm0Var;
        sm0 sm0Var;
        um0 um0Var;
        List list;
        lo0 lo0Var;
        ko0 ko0Var;
        ho0 ho0Var;
        mo0 mo0Var;
        List<jo0> list2;
        no0 no0Var;
        go0 go0Var;
        z5 z5Var;
        w5 w5Var;
        cp0 cp0Var;
        bp0 bp0Var;
        yo0 yo0Var;
        dp0 dp0Var;
        List<ap0> list3;
        ep0 ep0Var;
        xo0 xo0Var;
        fc fcVar;
        cc ccVar;
        tp0 tp0Var;
        sp0 sp0Var;
        pp0 pp0Var;
        up0 up0Var;
        List<rp0> list4;
        vp0 vp0Var;
        op0 op0Var;
        kq0 kq0Var;
        jq0 jq0Var;
        hq0 hq0Var;
        lq0 lq0Var;
        List<iq0> list5;
        mq0 mq0Var;
        gq0 gq0Var;
        df0 df0Var;
        af0 af0Var;
        bf0 bf0Var;
        zq0 zq0Var;
        yq0 yq0Var;
        dr0 dr0Var;
        ar0 ar0Var;
        List<xq0> list6;
        br0 br0Var;
        cr0 cr0Var;
        hn2 hn2Var;
        en2 en2Var;
        gn2 gn2Var;
        switch (this.c) {
            case 1:
                qm0 qm0Var = (qm0) o7Var.c;
                if (qm0Var != null && (pm0Var = qm0Var.a) != null && (sm0Var = pm0Var.b) != null && (um0Var = sm0Var.a) != null && (list = um0Var.a) != null) {
                    ArrayList arrayList = new ArrayList(lu.W(list));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((rm0) it.next()).a.b);
                    }
                }
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                wq0 wq0Var = (wq0) o7Var.c;
                if (wq0Var != null && (zq0Var = wq0Var.a) != null && (yq0Var = zq0Var.a) != null && (dr0Var = yq0Var.a) != null && (ar0Var = dr0Var.b) != null && (list6 = ar0Var.a) != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (xq0 xq0Var : list6) {
                        um2 um2Var = (xq0Var == null || (br0Var = xq0Var.b) == null || (cr0Var = br0Var.a) == null || (hn2Var = cr0Var.b) == null || (en2Var = hn2Var.a) == null || (gn2Var = en2Var.c) == null) ? null : gn2Var.b;
                        if (um2Var != null) {
                            arrayList2.add(um2Var);
                        }
                    }
                }
                break;
            case 6:
                io0 io0Var = (io0) o7Var.c;
                if (io0Var != null && (lo0Var = io0Var.a) != null && (ko0Var = lo0Var.a) != null && (ho0Var = ko0Var.a) != null && (mo0Var = ho0Var.b) != null && (list2 = mo0Var.a) != null) {
                    ArrayList arrayList3 = new ArrayList();
                    for (jo0 jo0Var : list2) {
                        x5 x5Var = (jo0Var == null || (no0Var = jo0Var.b) == null || (go0Var = no0Var.a) == null || (z5Var = go0Var.b) == null || (w5Var = z5Var.a) == null) ? null : w5Var.c;
                        if (x5Var != null) {
                            arrayList3.add(x5Var);
                        }
                    }
                }
                break;
            case 7:
                zo0 zo0Var = (zo0) o7Var.c;
                if (zo0Var != null && (cp0Var = zo0Var.a) != null && (bp0Var = cp0Var.a) != null && (yo0Var = bp0Var.a) != null && (dp0Var = yo0Var.b) != null && (list3 = dp0Var.a) != null) {
                    ArrayList arrayList4 = new ArrayList();
                    for (ap0 ap0Var : list3) {
                        dc dcVar = (ap0Var == null || (ep0Var = ap0Var.b) == null || (xo0Var = ep0Var.a) == null || (fcVar = xo0Var.b) == null || (ccVar = fcVar.a) == null) ? null : ccVar.c;
                        if (dcVar != null) {
                            arrayList4.add(dcVar);
                        }
                    }
                }
                break;
            case 8:
                qp0 qp0Var = (qp0) o7Var.c;
                if (qp0Var != null && (tp0Var = qp0Var.a) != null && (sp0Var = tp0Var.a) != null && (pp0Var = sp0Var.a) != null && (up0Var = pp0Var.b) != null && (list4 = up0Var.a) != null) {
                    ArrayList arrayList5 = new ArrayList();
                    for (rp0 rp0Var : list4) {
                        me meVar = (rp0Var == null || (vp0Var = rp0Var.b) == null || (op0Var = vp0Var.a) == null) ? null : op0Var.b;
                        if (meVar != null) {
                            arrayList5.add(meVar);
                        }
                    }
                }
                break;
            case 9:
                fq0 fq0Var = (fq0) o7Var.c;
                if (fq0Var != null && (kq0Var = fq0Var.a) != null && (jq0Var = kq0Var.a) != null && (hq0Var = jq0Var.a) != null && (lq0Var = hq0Var.b) != null && (list5 = lq0Var.a) != null) {
                    ArrayList arrayList6 = new ArrayList();
                    for (iq0 iq0Var : list5) {
                        pe0 pe0Var = (iq0Var == null || (mq0Var = iq0Var.b) == null || (gq0Var = mq0Var.a) == null || (df0Var = gq0Var.b) == null || (af0Var = df0Var.a) == null || (bf0Var = af0Var.c) == null) ? null : bf0Var.b;
                        if (pe0Var != null) {
                            arrayList6.add(pe0Var);
                        }
                    }
                }
                break;
        }
        return EmptyList.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v3, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.util.ArrayList] */
    public final Pair c(o7 o7Var) {
        Object arrayList;
        sr0 sr0Var;
        qr0 qr0Var;
        wr0 wr0Var;
        ur0 ur0Var;
        sr0 sr0Var2;
        rr0 rr0Var;
        vr0 vr0Var;
        tr0 tr0Var;
        List list;
        Object arrayList2;
        ms0 ms0Var;
        ls0 ls0Var;
        js0 js0Var;
        ns0 ns0Var;
        ms0 ms0Var2;
        ls0 ls0Var2;
        rs0 rs0Var;
        os0 os0Var;
        List<ks0> list2;
        ps0 ps0Var;
        qs0 qs0Var;
        int i = 0;
        switch (this.c) {
            case 10:
                nr0 nr0Var = (nr0) o7Var.c;
                if (nr0Var == null || (sr0Var2 = nr0Var.a) == null || (rr0Var = sr0Var2.b) == null || (vr0Var = rr0Var.a) == null || (tr0Var = vr0Var.b) == null || (list = tr0Var.a) == null) {
                    arrayList = EmptyList.c;
                } else {
                    arrayList = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        av1 av1Var = ((pr0) it.next()).a.b;
                        if (av1Var != null) {
                            arrayList.add(av1Var);
                        }
                    }
                }
                nr0 nr0Var2 = (nr0) o7Var.c;
                if (nr0Var2 != null && (sr0Var = nr0Var2.a) != null && (qr0Var = sr0Var.a) != null && (wr0Var = qr0Var.a) != null && (ur0Var = wr0Var.b) != null) {
                    i = ur0Var.a;
                }
                return new Pair(arrayList, Integer.valueOf(i));
            default:
                is0 is0Var = (is0) o7Var.c;
                if (is0Var == null || (ms0Var2 = is0Var.a) == null || (ls0Var2 = ms0Var2.a) == null || (rs0Var = ls0Var2.a) == null || (os0Var = rs0Var.b) == null || (list2 = os0Var.a) == null) {
                    arrayList2 = EmptyList.c;
                } else {
                    arrayList2 = new ArrayList();
                    for (ks0 ks0Var : list2) {
                        gw1 gw1Var = (ks0Var == null || (ps0Var = ks0Var.b) == null || (qs0Var = ps0Var.a) == null) ? null : qs0Var.b;
                        if (gw1Var != null) {
                            arrayList2.add(gw1Var);
                        }
                    }
                }
                is0 is0Var2 = (is0) o7Var.c;
                if (is0Var2 != null && (ms0Var = is0Var2.a) != null && (ls0Var = ms0Var.a) != null && (js0Var = ls0Var.b) != null && (ns0Var = js0Var.b) != null) {
                    i = ns0Var.a;
                }
                return new Pair(arrayList2, Integer.valueOf(i));
        }
    }
}
