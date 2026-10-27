package C1;

import E0.F;
import F0.L;
import d5.AbstractC1102y;
import d5.C1100w;
import d5.b0;
import e2.C1208p;
import g3.C1294c;
import io.sentry.U;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import m3.C1798D;
import w2.C2591e;
import w2.C2592f;
import w2.C2597k;
import w2.o;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Comparator {
    public final /* synthetic */ int l;

    public /* synthetic */ b(int i7) {
        this.l = i7;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int i7 = 19;
        int i10 = 20;
        switch (this.l) {
            case 0:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i11 = 0; i11 < bArr.length; i11++) {
                    byte b10 = bArr[i11];
                    byte b11 = bArr2[i11];
                    if (b10 != b11) {
                        return b10 - b11;
                    }
                }
                return 0;
            case 1:
                F f10 = (F) obj;
                F f11 = (F) obj2;
                return f10.J() == f11.J() ? A7.m.g(f10.H(), f11.H()) : Float.compare(f10.J(), f11.J());
            case 2:
                return ((Number) L.f2047n.invoke(obj, obj2)).intValue();
            case 3:
                m7.i iVar = (m7.i) obj;
                m7.i iVar2 = (m7.i) obj2;
                return (((Number) iVar.f19259m).intValue() - ((Number) iVar.l).intValue()) - (((Number) iVar2.f19259m).intValue() - ((Number) iVar2.l).intValue());
            case 4:
                return A7.m.g(((T.L) obj).f8200b, ((T.L) obj2).f8200b);
            case 5:
                return Integer.compare(((Z2.d) obj2).f10844b, ((Z2.d) obj).f10844b);
            case 6:
                return Integer.compare(((g3.d) obj).f14830a.f14833b, ((g3.d) obj2).f14830a.f14833b);
            case 7:
                return Long.compare(((C1294c) obj).f14829b, ((C1294c) obj2).f14829b);
            case 8:
                U u4 = (U) obj;
                U u10 = (U) obj2;
                int iCompareTo = u4.B().compareTo(u10.B());
                return iCompareTo != 0 ? iCompareTo : u4.p().f15957m.l.compareTo(u10.p().f15957m.l);
            case 9:
                return Long.compare(((File) obj).lastModified(), ((File) obj2).lastModified());
            case 10:
                C1798D c1798d = (C1798D) obj;
                C1798D c1798d2 = (C1798D) obj2;
                int iCompare = Integer.compare(c1798d2.f18965b, c1798d.f18965b);
                if (iCompare != 0) {
                    return iCompare;
                }
                int iCompareTo2 = c1798d.c.compareTo(c1798d2.c);
                return iCompareTo2 != 0 ? iCompareTo2 : c1798d.d.compareTo(c1798d2.d);
            case 11:
                C1798D c1798d3 = (C1798D) obj;
                C1798D c1798d4 = (C1798D) obj2;
                int iCompare2 = Integer.compare(c1798d4.f18964a, c1798d3.f18964a);
                if (iCompare2 != 0) {
                    return iCompare2;
                }
                int iCompareTo3 = c1798d4.c.compareTo(c1798d3.c);
                return iCompareTo3 != 0 ? iCompareTo3 : c1798d4.d.compareTo(c1798d3.d);
            case 12:
                return Integer.parseInt(((String) obj).split(":")[0]) - Integer.parseInt(((String) obj2).split(":")[0]);
            case 13:
                return ((C1208p) obj2).f14313j - ((C1208p) obj).f14313j;
            case 14:
                Integer num = (Integer) obj;
                Integer num2 = (Integer) obj2;
                if (num.intValue() == -1) {
                    return num2.intValue() == -1 ? 0 : -1;
                }
                if (num2.intValue() == -1) {
                    return 1;
                }
                return num.intValue() - num2.intValue();
            case 15:
                return Integer.compare(((C2592f) ((List) obj).get(0)).f23198q, ((C2592f) ((List) obj2).get(0)).f23198q);
            case 16:
                return ((C2591e) Collections.max((List) obj)).compareTo((C2591e) Collections.max((List) obj2));
            case 17:
                List list = (List) obj;
                List list2 = (List) obj2;
                return C1100w.f(w2.n.c((w2.n) Collections.max(list, new b(i7)), (w2.n) Collections.max(list2, new b(i7)))).a(list.size(), list2.size()).b((w2.n) Collections.max(list, new b(i10)), (w2.n) Collections.max(list2, new b(i10)), new b(i10)).e();
            case 18:
                return ((C2597k) ((List) obj).get(0)).compareTo((C2597k) ((List) obj2).get(0));
            case 19:
                return w2.n.c((w2.n) obj, (w2.n) obj2);
            case 20:
                w2.n nVar = (w2.n) obj;
                w2.n nVar2 = (w2.n) obj2;
                b0 b0VarA = (nVar.f23250p && nVar.f23253s) ? o.f23261j : o.f23261j.a();
                AbstractC1102y abstractC1102yB = AbstractC1102y.f13464a;
                boolean z6 = nVar.f23251q.f14215y;
                int i12 = nVar.f23255u;
                if (z6) {
                    abstractC1102yB = abstractC1102yB.b(Integer.valueOf(i12), Integer.valueOf(nVar2.f23255u), o.f23261j.a());
                }
                return abstractC1102yB.b(Integer.valueOf(nVar.f23256v), Integer.valueOf(nVar2.f23256v), b0VarA).b(Integer.valueOf(i12), Integer.valueOf(nVar2.f23255u), b0VarA).e();
            case 21:
                return ((x2.o) obj).f23759a - ((x2.o) obj2).f23759a;
            default:
                return Float.compare(((x2.o) obj).c, ((x2.o) obj2).c);
        }
    }
}
