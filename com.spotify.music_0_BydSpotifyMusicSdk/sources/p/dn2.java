package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class dn2 implements y4 {
    public static final List c = v03.C("artists", "albumOfTrack", "contentRating", "name", "uri", "trackNumber", "discNumber", "playability", "duration");

    public static um2 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        Integer num = null;
        Integer num2 = null;
        nm2 nm2Var = null;
        mm2 mm2Var = null;
        om2 om2Var = null;
        String str = null;
        String str2 = null;
        sm2 sm2Var = null;
        qm2 qm2Var = null;
        while (true) {
            switch (u71Var.W(c)) {
                case 0:
                    wm2 wm2Var = wm2.c;
                    w1 w1Var = c5.a;
                    nm2Var = (nm2) new wo1(wm2Var, false).a(u71Var, cVar);
                    break;
                case 1:
                    mm2Var = (mm2) c5.a(new wo1(vm2.c, false)).a(u71Var, cVar);
                    break;
                case 2:
                    om2Var = (om2) c5.a(new wo1(xm2.c, true)).a(u71Var, cVar);
                    break;
                case 3:
                    str = (String) c5.a.a(u71Var, cVar);
                    break;
                case 4:
                    str2 = (String) c5.a.a(u71Var, cVar);
                    break;
                case 5:
                    num = (Integer) c5.b.a(u71Var, cVar);
                    break;
                case 6:
                    num2 = (Integer) c5.b.a(u71Var, cVar);
                    break;
                case 7:
                    bn2 bn2Var = bn2.c;
                    w1 w1Var2 = c5.a;
                    sm2Var = (sm2) new wo1(bn2Var, false).a(u71Var, cVar);
                    break;
                case 8:
                    zm2 zm2Var = zm2.c;
                    w1 w1Var3 = c5.a;
                    qm2Var = (qm2) new wo1(zm2Var, false).a(u71Var, cVar);
                    break;
                default:
                    v03.h(nm2Var);
                    v03.h(str);
                    v03.h(str2);
                    v03.h(num);
                    int iIntValue = num.intValue();
                    v03.h(num2);
                    int iIntValue2 = num2.intValue();
                    v03.h(sm2Var);
                    v03.h(qm2Var);
                    return new um2(nm2Var, mm2Var, om2Var, str, str2, iIntValue, iIntValue2, sm2Var, qm2Var);
            }
        }
    }
}
