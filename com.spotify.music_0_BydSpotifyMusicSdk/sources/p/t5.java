package p;

import com.spotify.automotive.ubi.Referrer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class t5 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ Comparable e;
    public final /* synthetic */ Comparable k;
    public final /* synthetic */ tb1 l;

    public /* synthetic */ t5(Comparable comparable, Referrer referrer, tb1 tb1Var, int i) {
        this.c = i;
        this.e = comparable;
        this.k = referrer;
        this.l = tb1Var;
    }

    public final List a(List list) {
        int i = this.c;
        tb1 tb1Var = this.l;
        Comparable comparable = this.k;
        Comparable comparable2 = this.e;
        switch (i) {
            case 0:
                v03.i(list, "albumTracks");
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (((um2) obj).h.a) {
                        arrayList.add(obj);
                    }
                }
                String str = (String) comparable2;
                Referrer referrer = (Referrer) comparable;
                u5 u5Var = (u5) tb1Var;
                ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
                int i2 = 0;
                for (Object obj2 : arrayList) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        v03.P();
                        throw null;
                    }
                    arrayList2.add(com.spotify.automotive.content.loaders.extensions.a.b((um2) obj2, str, referrer, i2, u5Var.d, null, true, true, 16));
                    i2 = i3;
                }
                return arrayList2;
            case 1:
                v03.i(list, "episodes");
                ArrayList arrayList3 = new ArrayList();
                for (Object obj3 : list) {
                    if (((pe0) obj3).g.a) {
                        arrayList3.add(obj3);
                    }
                }
                if2 if2Var = (if2) comparable;
                String str2 = (String) comparable2;
                x91 x91Var = (x91) tb1Var;
                ArrayList arrayList4 = new ArrayList(lu.W(arrayList3));
                int i4 = 0;
                for (Object obj4 : arrayList3) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        v03.P();
                        throw null;
                    }
                    arrayList4.add(f60.Q((pe0) obj4, if2Var, str2, i4, x91Var.b, x91Var.c, null, null));
                    i4 = i5;
                }
                return arrayList4;
            default:
                v03.i(list, "tracks");
                if2 if2Var2 = (if2) comparable2;
                Referrer referrer2 = (Referrer) comparable;
                aa1 aa1Var = (aa1) tb1Var;
                ArrayList arrayList5 = new ArrayList(lu.W(list));
                int i6 = 0;
                for (Object obj5 : list) {
                    int i7 = i6 + 1;
                    if (i6 < 0) {
                        v03.P();
                        throw null;
                    }
                    arrayList5.add(com.spotify.automotive.content.loaders.extensions.a.b((um2) obj5, if2Var2.toString(), referrer2, i6, aa1Var.b, null, false, false, 112));
                    i6 = i7;
                }
                ArrayList arrayList6 = new ArrayList();
                for (Object obj6 : arrayList5) {
                    if (((dg1) obj6).b == 2) {
                        arrayList6.add(obj6);
                    }
                }
                return arrayList6;
        }
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final /* bridge */ /* synthetic */ Object apply(Object obj) {
        switch (this.c) {
        }
        return a((List) obj);
    }

    public t5(if2 if2Var, String str, x91 x91Var) {
        this.c = 1;
        this.k = if2Var;
        this.e = str;
        this.l = x91Var;
    }
}
