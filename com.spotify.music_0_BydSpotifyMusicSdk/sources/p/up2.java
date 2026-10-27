package p;

import com.spotify.automotive.ubi.LoggerPlaybackError;
import com.spotify.automotive.ubi.Referrer;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public final class up2 {
    public final ur2 a;

    public up2(ur2 ur2Var) {
        v03.j(ur2Var, "logger");
        this.a = ur2Var;
    }

    public static cp2 a(np2 np2Var) {
        Referrer referrer = np2Var.b;
        int i = referrer == null ? -1 : sp2.a[referrer.ordinal()];
        String str = np2Var.a;
        switch (i) {
            case -1:
            case 6:
            case 7:
                return null;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                return new gh2(new gh2(new dj()), (Integer) null, str).e();
            case 2:
                return new gh2(new zi(), (Integer) null, str).e();
            case 3:
                return new gh2(new ej(), (Integer) null, str).e();
            case 4:
                return new gh2(new wj(), (Integer) null, str).e();
            case 5:
                return new gh2(new xj(), (Integer) null, str).e();
        }
    }

    public final String b(f60 f60Var) {
        pp2 pp2Var;
        v03.j(f60Var, "params");
        if (f60Var instanceof aq2) {
            cp2 cp2Var = cp2.b;
            v03.j(cp2Var, "parentAbsoluteLocation");
            ip2 ip2Var = ip2.i;
            hp2 hp2VarM = qo.m();
            hp2VarM.h = "automotive";
            hp2VarM.a = "automotive-voice";
            hp2VarM.f = "1.0.1";
            hp2VarM.g = "15.3.0";
            ip2 ip2VarA = hp2VarM.a();
            op2 op2Var = new op2();
            op2Var.a = ip2VarA;
            op2Var.b = cp2Var;
            ep2 ep2Var = ep2.e;
            dp2 dp2VarR = u11.r();
            dp2VarR.a = "search";
            dp2VarR.c = "speech";
            dp2VarR.b = 1;
            op2Var.d = dp2VarR.a();
            pp2Var = (pp2) op2Var.a();
        } else if (f60Var instanceof bq2) {
            cp2 cp2Var2 = cp2.b;
            v03.j(cp2Var2, "parentAbsoluteLocation");
            ip2 ip2Var2 = ip2.i;
            hp2 hp2VarM2 = qo.m();
            hp2VarM2.h = "automotive";
            hp2VarM2.a = "automotive-voice";
            hp2VarM2.f = "1.0.1";
            hp2VarM2.g = "15.3.0";
            ip2 ip2VarA2 = hp2VarM2.a();
            op2 op2Var2 = new op2();
            op2Var2.a = ip2VarA2;
            op2Var2.b = cp2Var2;
            ep2 ep2Var2 = ep2.e;
            dp2 dp2VarR2 = u11.r();
            dp2VarR2.a = "play";
            dp2VarR2.c = "speech";
            dp2VarR2.b = 1;
            dp2VarR2.b(((bq2) f60Var).h, "item_to_be_played");
            op2Var2.d = dp2VarR2.a();
            pp2Var = (pp2) op2Var2.a();
        } else if (f60Var instanceof cq2) {
            cp2 cp2Var3 = cp2.b;
            v03.j(cp2Var3, "parentAbsoluteLocation");
            ip2 ip2Var3 = ip2.i;
            hp2 hp2VarM3 = qo.m();
            hp2VarM3.h = "automotive";
            hp2VarM3.a = "automotive-voice";
            hp2VarM3.f = "1.0.1";
            hp2VarM3.g = "15.3.0";
            ip2 ip2VarA3 = hp2VarM3.a();
            op2 op2Var3 = new op2();
            op2Var3.a = ip2VarA3;
            op2Var3.b = cp2Var3;
            ep2 ep2Var3 = ep2.e;
            dp2 dp2VarR3 = u11.r();
            dp2VarR3.a = "search";
            dp2VarR3.c = "speech";
            dp2VarR3.b = 1;
            op2Var3.d = dp2VarR3.a();
            pp2Var = (pp2) op2Var3.a();
        } else {
            if (!(f60Var instanceof dq2)) {
                throw new NoWhenBranchMatchedException();
            }
            cp2 cp2Var4 = cp2.b;
            v03.j(cp2Var4, "parentAbsoluteLocation");
            ip2 ip2Var4 = ip2.i;
            hp2 hp2VarM4 = qo.m();
            hp2VarM4.h = "automotive";
            hp2VarM4.a = "automotive-voice";
            hp2VarM4.f = "1.0.1";
            hp2VarM4.g = "15.3.0";
            ip2 ip2VarA4 = hp2VarM4.a();
            op2 op2Var4 = new op2();
            op2Var4.a = ip2VarA4;
            op2Var4.b = cp2Var4;
            ep2 ep2Var4 = ep2.e;
            dp2 dp2VarR4 = u11.r();
            dp2VarR4.a = "ui_navigate";
            dp2VarR4.c = "speech";
            dp2VarR4.b = 1;
            dp2VarR4.b(((dq2) f60Var).h, "destination");
            op2Var4.d = dp2VarR4.a();
            pp2Var = (pp2) op2Var4.a();
        }
        return ((tp2) this.a).b(pp2Var).a().a();
    }

    public final void c(np2 np2Var) {
        mp2 mp2Var;
        String str = np2Var.a;
        if (str == null) {
            Referrer referrer = np2Var.b;
            mp2Var = null;
            switch (referrer == null ? -1 : sp2.a[referrer.ordinal()]) {
                case -1:
                case 6:
                case 7:
                    break;
                case 0:
                default:
                    throw new NoWhenBranchMatchedException();
                case 1:
                    cp2 cp2Var = cp2.b;
                    v03.j(cp2Var, "parentAbsoluteLocation");
                    ip2 ip2Var = ip2.i;
                    hp2 hp2VarM = qo.m();
                    hp2VarM.h = "automotive";
                    hp2VarM.a = "automotive-home-tab";
                    hp2VarM.f = "1.0.0";
                    hp2VarM.g = "15.3.0";
                    ip2 ip2VarA = hp2VarM.a();
                    lp2 lp2Var = new lp2();
                    lp2Var.a = ip2VarA;
                    lp2Var.b = cp2Var;
                    mp2Var = (mp2) lp2Var.a();
                    break;
                case 2:
                    cp2 cp2Var2 = cp2.b;
                    v03.j(cp2Var2, "parentAbsoluteLocation");
                    ip2 ip2Var2 = ip2.i;
                    hp2 hp2VarM2 = qo.m();
                    hp2VarM2.h = "automotive";
                    hp2VarM2.a = "automotive-browse-tab";
                    hp2VarM2.f = "1.0.0";
                    hp2VarM2.g = "15.3.0";
                    ip2 ip2VarA2 = hp2VarM2.a();
                    lp2 lp2Var2 = new lp2();
                    lp2Var2.a = ip2VarA2;
                    lp2Var2.b = cp2Var2;
                    mp2Var = (mp2) lp2Var2.a();
                    break;
                case 3:
                    cp2 cp2Var3 = cp2.b;
                    v03.j(cp2Var3, "parentAbsoluteLocation");
                    ip2 ip2Var3 = ip2.i;
                    hp2 hp2VarM3 = qo.m();
                    hp2VarM3.h = "automotive";
                    hp2VarM3.a = "automotive-library-tab";
                    hp2VarM3.f = "1.0.0";
                    hp2VarM3.g = "15.3.0";
                    ip2 ip2VarA3 = hp2VarM3.a();
                    lp2 lp2Var3 = new lp2();
                    lp2Var3.a = ip2VarA3;
                    lp2Var3.b = cp2Var3;
                    mp2Var = (mp2) lp2Var3.a();
                    break;
                case 4:
                    cp2 cp2Var4 = cp2.b;
                    v03.j(cp2Var4, "parentAbsoluteLocation");
                    ip2 ip2Var4 = ip2.i;
                    hp2 hp2VarM4 = qo.m();
                    hp2VarM4.h = "automotive";
                    hp2VarM4.a = "automotive-recently-played-tab";
                    hp2VarM4.f = "1.0.0";
                    hp2VarM4.g = "15.3.0";
                    ip2 ip2VarA4 = hp2VarM4.a();
                    lp2 lp2Var4 = new lp2();
                    lp2Var4.a = ip2VarA4;
                    lp2Var4.b = cp2Var4;
                    mp2Var = (mp2) lp2Var4.a();
                    break;
                case 5:
                    cp2 cp2Var5 = cp2.b;
                    v03.j(cp2Var5, "parentAbsoluteLocation");
                    ip2 ip2Var5 = ip2.i;
                    hp2 hp2VarM5 = qo.m();
                    hp2VarM5.h = "automotive";
                    hp2VarM5.a = "automotive-search-result-list";
                    hp2VarM5.f = "1.0.0";
                    hp2VarM5.g = "15.3.0";
                    ip2 ip2VarA5 = hp2VarM5.a();
                    lp2 lp2Var5 = new lp2();
                    lp2Var5.a = ip2VarA5;
                    lp2Var5.b = cp2Var5;
                    mp2Var = (mp2) lp2Var5.a();
                    break;
            }
        } else {
            cp2 cp2VarA = a(np2Var);
            if (cp2VarA == null) {
                return;
            }
            ip2 ip2Var6 = ip2.i;
            hp2 hp2VarM6 = qo.m();
            hp2VarM6.h = "automotive";
            hp2VarM6.a = "automotive-content-list";
            hp2VarM6.f = "1.0.1";
            hp2VarM6.g = "15.3.0";
            hp2VarM6.d = str;
            ip2 ip2VarA6 = hp2VarM6.a();
            lp2 lp2Var6 = new lp2();
            lp2Var6.a = ip2VarA6;
            lp2Var6.b = cp2VarA;
            mp2Var = (mp2) lp2Var6.a();
        }
        if (mp2Var == null) {
            return;
        }
        ((tp2) this.a).a(mp2Var);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0148 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0149  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(p.rp2 r7) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.up2.d(p.rp2):void");
    }

    public final void e(LoggerPlaybackError loggerPlaybackError) {
        ((tp2) this.a).a(new kk0(new tj(), loggerPlaybackError.a()).u());
    }

    public final void f(rp2 rp2Var) {
        Referrer referrer = rp2Var.d;
        int i = referrer == null ? -1 : sp2.a[referrer.ordinal()];
        pp2 pp2VarS = null;
        String str = rp2Var.a;
        Integer num = rp2Var.b;
        switch (i) {
            case -1:
            case 6:
            case 7:
                break;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                pp2VarS = new gh2(new gh2(new dj()), num, str).s(str);
                break;
            case 2:
                pp2VarS = new gh2(new zi(), num, str).s(str);
                break;
            case 3:
                pp2VarS = new gh2(new ej(), num, str).s(str);
                break;
            case 4:
                pp2VarS = new gh2(new wj(), num, str).s(str);
                break;
            case 5:
                pp2VarS = new gh2(new xj(), num, str).s(str);
                break;
        }
        if (pp2VarS == null) {
            return;
        }
        ((tp2) this.a).b(pp2VarS);
    }
}
