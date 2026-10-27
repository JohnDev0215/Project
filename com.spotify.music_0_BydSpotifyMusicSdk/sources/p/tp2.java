package p;

import com.spotify.messages.UbiProd1Impression;
import com.spotify.messages.UbiProd1ImpressionNonAuth;
import com.spotify.messages.UbiProd1Interaction;
import com.spotify.ubi.logger.ImpressionLoggingResult;
import com.spotify.ubi.logger.InteractionLoggingResult;
import com.spotify.ubi.model.ImpressionId;
import com.spotify.ubi.model.InteractionId;
import com.spotify.ubi.model.PageInstanceId;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class tp2 implements ur2 {
    public final wi0 a;
    public final gp2 b;
    public final Set c;
    public final xr2 d;
    public final kp2 e;
    public final u6 f;
    public final qp2 g;

    public tp2(wi0 wi0Var, u6 u6Var, gp2 gp2Var, kp2 kp2Var, qp2 qp2Var, xr2 xr2Var, Set set) {
        this.a = wi0Var;
        this.b = gp2Var;
        this.c = set;
        this.d = xr2Var;
        this.e = kp2Var;
        this.f = u6Var;
        this.g = qp2Var;
    }

    public final ImpressionLoggingResult a(mp2 mp2Var) {
        v03.j(mp2Var, "impressionEvent");
        ((on1) this.e).getClass();
        fp2 fp2VarA = this.b.a();
        this.d.getClass();
        PageInstanceId pageInstanceId = fp2VarA.b;
        String strA = pageInstanceId != null ? pageInstanceId.a() : null;
        String str = fp2VarA.a;
        v03.j(str, "id");
        String str2 = fp2VarA.c;
        v03.j(str2, "playbackId");
        String str3 = fp2VarA.d;
        v03.j(str3, "pageUri");
        String str4 = fp2VarA.e;
        v03.j(str4, "playContextUri");
        wp2 wp2VarC = UbiProd1Impression.C();
        wp2VarC.z(str4);
        wp2VarC.w(str);
        ip2 ip2Var = mp2Var.a;
        wp2VarC.C(ip2Var.a);
        wp2VarC.v(ip2Var.b);
        wp2VarC.u(ip2Var.c);
        wp2VarC.A(str2);
        wp2VarC.d(mp2Var.c);
        wp2VarC.B();
        wp2VarC.t(ip2Var.f);
        wp2VarC.s(ip2Var.g);
        if (str3.length() > 0) {
            wp2VarC.y(str3);
        }
        if (strA != null) {
            wp2VarC.x(strA);
        }
        for (jp2 jp2Var : ip2Var.d) {
            wp2VarC.f(jp2Var.a);
            wp2VarC.e(q30.d(jp2Var.b));
            wp2VarC.j(q30.d(jp2Var.c));
            wp2VarC.h(q30.d(jp2Var.e));
            wp2VarC.g(q30.d(jp2Var.d));
        }
        for (ip2 ip2Var2 : mp2Var.b.a) {
            int i = 0;
            for (Object obj : ip2Var2.d) {
                int i2 = i + 1;
                if (i < 0) {
                    v03.P();
                    throw null;
                }
                jp2 jp2Var2 = (jp2) obj;
                wp2VarC.m(jp2Var2.a);
                wp2VarC.l(q30.d(jp2Var2.b));
                wp2VarC.p(q30.d(jp2Var2.c));
                wp2VarC.o(q30.d(jp2Var2.e));
                wp2VarC.n(q30.d(jp2Var2.d));
                if (i == 0) {
                    wp2VarC.r(ip2Var2.a);
                    wp2VarC.k("default");
                } else {
                    wp2VarC.r("");
                    wp2VarC.k("");
                }
                i = i2;
            }
        }
        com.google.protobuf.i iVarBuild = wp2VarC.build();
        v03.i(iVarBuild, "messageBuilder.build()");
        this.a.a((UbiProd1Impression) iVarBuild);
        return new ImpressionLoggingResult(new ImpressionId(str), pageInstanceId);
    }

    public final InteractionLoggingResult b(pp2 pp2Var) {
        v03.j(pp2Var, "interactionEvent");
        this.f.getClass();
        System.currentTimeMillis();
        ((on1) this.e).getClass();
        fp2 fp2VarA = this.b.a();
        this.d.getClass();
        String str = fp2VarA.a;
        InteractionId interactionId = new InteractionId(str);
        Iterator it = this.c.iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            throw null;
        }
        PageInstanceId pageInstanceId = fp2VarA.b;
        String strA = pageInstanceId != null ? pageInstanceId.a() : null;
        String str2 = fp2VarA.c;
        v03.j(str2, "playbackId");
        String str3 = fp2VarA.d;
        v03.j(str3, "pageUri");
        String str4 = fp2VarA.e;
        v03.j(str4, "playContextUri");
        yp2 yp2VarH = UbiProd1Interaction.H();
        yp2VarH.F(str4);
        yp2VarH.A(str);
        ip2 ip2Var = pp2Var.a;
        yp2VarH.I(ip2Var.a);
        yp2VarH.z(ip2Var.b);
        yp2VarH.y(ip2Var.c);
        yp2VarH.f(pp2Var.c);
        ep2 ep2Var = pp2Var.d;
        yp2VarH.B(ep2Var.c);
        yp2VarH.u(ep2Var.a);
        yp2VarH.v(ep2Var.b);
        yp2VarH.G(str2);
        yp2VarH.H();
        yp2VarH.x(ip2Var.f);
        yp2VarH.w(ip2Var.g);
        if (str3.length() > 0) {
            yp2VarH.D(str3);
        }
        if (strA != null) {
            yp2VarH.C(strA);
        }
        for (jp2 jp2Var : ip2Var.d) {
            yp2VarH.h(jp2Var.a);
            yp2VarH.g(q30.d(jp2Var.b));
            yp2VarH.l(q30.d(jp2Var.c));
            yp2VarH.k(q30.d(jp2Var.e));
            yp2VarH.j(q30.d(jp2Var.d));
        }
        for (Map.Entry entry : ep2Var.d.entrySet()) {
            String str5 = (String) entry.getKey();
            String str6 = (String) entry.getValue();
            yp2VarH.d(q30.d(str5));
            yp2VarH.e(q30.d(str6));
        }
        for (ip2 ip2Var2 : pp2Var.b.a) {
            int i = 0;
            for (Object obj : ip2Var2.d) {
                int i2 = i + 1;
                if (i < 0) {
                    v03.P();
                    throw null;
                }
                jp2 jp2Var2 = (jp2) obj;
                yp2VarH.o(jp2Var2.a);
                yp2VarH.n(q30.d(jp2Var2.b));
                yp2VarH.s(q30.d(jp2Var2.c));
                yp2VarH.r(q30.d(jp2Var2.e));
                yp2VarH.p(q30.d(jp2Var2.d));
                if (i == 0) {
                    yp2VarH.t(ip2Var2.a);
                    yp2VarH.m("default");
                } else {
                    yp2VarH.t("");
                    yp2VarH.m("");
                }
                i = i2;
            }
        }
        com.google.protobuf.i iVarBuild = yp2VarH.build();
        v03.i(iVarBuild, "messageBuilder.build()");
        this.a.a((UbiProd1Interaction) iVarBuild);
        this.g.getClass();
        return new InteractionLoggingResult(interactionId, pageInstanceId);
    }

    public final ImpressionLoggingResult c(mp2 mp2Var) {
        v03.j(mp2Var, "impressionEvent");
        ((on1) this.e).getClass();
        fp2 fp2VarA = this.b.a();
        this.d.getClass();
        PageInstanceId pageInstanceId = fp2VarA.b;
        String strA = pageInstanceId != null ? pageInstanceId.a() : null;
        String str = fp2VarA.a;
        v03.j(str, "id");
        String str2 = fp2VarA.c;
        v03.j(str2, "playbackId");
        String str3 = fp2VarA.d;
        v03.j(str3, "pageUri");
        String str4 = fp2VarA.e;
        v03.j(str4, "playContextUri");
        xp2 xp2VarC = UbiProd1ImpressionNonAuth.C();
        xp2VarC.w(str);
        ip2 ip2Var = mp2Var.a;
        xp2VarC.C(ip2Var.a);
        xp2VarC.v(ip2Var.b);
        xp2VarC.u(ip2Var.c);
        xp2VarC.A(str2);
        xp2VarC.z(str4);
        xp2VarC.d(mp2Var.c);
        xp2VarC.B();
        xp2VarC.t(ip2Var.f);
        xp2VarC.s(ip2Var.g);
        if (str3.length() > 0) {
            xp2VarC.y(str3);
        }
        if (strA != null) {
            xp2VarC.x(strA);
        }
        for (jp2 jp2Var : ip2Var.d) {
            xp2VarC.f(jp2Var.a);
            xp2VarC.e(q30.d(jp2Var.b));
            xp2VarC.j(q30.d(jp2Var.c));
            xp2VarC.h(q30.d(jp2Var.e));
            xp2VarC.g(q30.d(jp2Var.d));
        }
        for (ip2 ip2Var2 : mp2Var.b.a) {
            int i = 0;
            for (Object obj : ip2Var2.d) {
                int i2 = i + 1;
                if (i < 0) {
                    v03.P();
                    throw null;
                }
                jp2 jp2Var2 = (jp2) obj;
                xp2VarC.m(jp2Var2.a);
                xp2VarC.l(q30.d(jp2Var2.b));
                xp2VarC.p(q30.d(jp2Var2.c));
                xp2VarC.o(q30.d(jp2Var2.e));
                xp2VarC.n(q30.d(jp2Var2.d));
                if (i == 0) {
                    xp2VarC.r(ip2Var2.a);
                    xp2VarC.k("default");
                } else {
                    xp2VarC.r("");
                    xp2VarC.k("");
                }
                i = i2;
            }
        }
        com.google.protobuf.i iVarBuild = xp2VarC.build();
        v03.i(iVarBuild, "messageBuilder.build()");
        this.a.a((UbiProd1ImpressionNonAuth) iVarBuild);
        return new ImpressionLoggingResult(new ImpressionId(str), pageInstanceId);
    }
}
