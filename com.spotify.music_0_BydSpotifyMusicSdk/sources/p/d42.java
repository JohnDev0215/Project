package p;

import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.messages.ConfigurationApplied;
import com.spotify.messages.ConfigurationAppliedNonAuth;
import com.spotify.messages.DefaultConfigurationApplied;
import com.spotify.messages.DefaultConfigurationAppliedNonAuth;
import com.spotify.rcs.resolver.grpc.v0.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class d42 implements a42 {
    public final xk1 a;
    public final ki0 b;
    public final l13 c;
    public final xw d;
    public final hk0 e;
    public Integer f;

    public d42(xk1 xk1Var, cg2 cg2Var, ki0 ki0Var, b42 b42Var, int i) {
        e42 e42Var = (i & 16) != 0 ? e42.f : null;
        v03.j(e42Var, "resolverMode");
        this.a = xk1Var;
        this.b = ki0Var;
        this.c = e42Var;
        xw xwVar = new xw(cg2Var);
        this.d = xwVar;
        this.e = new hk0(xwVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [int] */
    @Override // p.a42
    public final void a() {
        Pair pair;
        xw xwVar = this.d;
        synchronized (xwVar) {
            m02 m02VarB = ((eg2) xwVar.a).c.b();
            m02 m02VarB2 = ((eg2) xwVar.a).d.b();
            v03.j(m02VarB, "fetchedConfig");
            v03.j(m02VarB2, "overriddenConfig");
            Map map = m02VarB.b;
            v03.j(map, "<this>");
            Map map2 = m02VarB2.b;
            v03.j(map2, "map");
            LinkedHashMap linkedHashMap = new LinkedHashMap(map);
            linkedHashMap.putAll(map2);
            w01 w01VarA = w01.a(m02VarB.a, ou.n0(linkedHashMap.values()), null, 27);
            m02 m02Var = m02.c;
            m02 m02VarU = zb0.u(w01VarA);
            xwVar.b = m02VarU;
            xwVar.d = Long.valueOf(m02VarB.a.d);
            xwVar.c.set(true);
            boolean zIsEmpty = m02VarB.a().isEmpty();
            Set setA = m02VarB.a();
            w01 w01Var = m02VarB.a;
            pair = new Pair(m02VarU, new n02(zIsEmpty, setA, w01Var.b, w01Var.a));
        }
        n02 n02Var = (n02) pair.b();
        if (!(this.c instanceof e42)) {
            throw new NoWhenBranchMatchedException();
        }
        boolean z = n02Var.a;
        Set set = n02Var.b;
        String str = n02Var.d;
        n02Var.getClass();
        v03.j(set, "policyGroupIds");
        v03.j(str, "configurationAssignmentId");
        ?? r4 = z ? 1 : z;
        int iHashCode = str.hashCode() + ((Long.hashCode(0L) + ((set.hashCode() + (r4 * 31)) * 31)) * 31);
        if (this.c instanceof e42) {
            Integer num = this.f;
            if (num != null && iHashCode == num.intValue()) {
                return;
            }
            d51 d51Var = (d51) this.b;
            int i = d51Var.a;
            lw1 lw1Var = d51Var.d;
            xa xaVar = d51Var.c;
            ui0 ui0Var = d51Var.b;
            switch (i) {
                case 0:
                    boolean z2 = n02Var.a;
                    String str2 = n02Var.d;
                    if (!z2) {
                        tw twVarL = ConfigurationApplied.l();
                        twVarL.e(lw1Var.b());
                        xaVar.getClass();
                        twVarL.j();
                        twVarL.k();
                        twVarL.h();
                        twVarL.d(n02Var.b);
                        twVarL.g(n02Var.c);
                        twVarL.f(str2);
                        byte[] byteArray = ((ConfigurationApplied) twVarL.build()).toByteArray();
                        v03.i(byteArray, "toByteArray()");
                        ((jw0) ((kj0) ui0Var).a).b("ConfigurationApplied", null, byteArray);
                    } else {
                        n60 n60VarJ = DefaultConfigurationApplied.j();
                        n60VarJ.d(lw1Var.b());
                        xaVar.getClass();
                        n60VarJ.g();
                        n60VarJ.h();
                        n60VarJ.f();
                        n60VarJ.e(str2);
                        byte[] byteArray2 = ((DefaultConfigurationApplied) n60VarJ.build()).toByteArray();
                        v03.i(byteArray2, "toByteArray()");
                        ((jw0) ((kj0) ui0Var).a).b("DefaultConfigurationApplied", null, byteArray2);
                    }
                    break;
                default:
                    boolean z3 = n02Var.a;
                    String str3 = n02Var.d;
                    if (!z3) {
                        vw vwVarL = ConfigurationAppliedNonAuth.l();
                        vwVarL.e(lw1Var.b());
                        xaVar.getClass();
                        vwVarL.j();
                        vwVarL.k();
                        vwVarL.h();
                        vwVarL.d(n02Var.b);
                        vwVarL.g(n02Var.c);
                        vwVarL.f(str3);
                        byte[] byteArray3 = ((ConfigurationAppliedNonAuth) vwVarL.build()).toByteArray();
                        v03.i(byteArray3, "toByteArray()");
                        ((jw0) ((kj0) ui0Var).a).b("ConfigurationAppliedNonAuth", null, byteArray3);
                    } else {
                        p60 p60VarJ = DefaultConfigurationAppliedNonAuth.j();
                        p60VarJ.d(lw1Var.b());
                        xaVar.getClass();
                        p60VarJ.g();
                        p60VarJ.h();
                        p60VarJ.f();
                        p60VarJ.e(str3);
                        byte[] byteArray4 = ((DefaultConfigurationAppliedNonAuth) p60VarJ.build()).toByteArray();
                        v03.i(byteArray4, "toByteArray()");
                        ((jw0) ((kj0) ui0Var).a).b("DefaultConfigurationAppliedNonAuth", null, byteArray4);
                    }
                    break;
            }
            this.f = Integer.valueOf(iHashCode);
        }
    }

    @Override // p.jx
    public final my1 b(bj bjVar) {
        d42 d42Var;
        String strSubstring;
        int iN0;
        String name = bj.class.getName();
        int iQ0 = kotlin.text.b.q0(name, '.', 0, 6);
        if (iQ0 < 0 || (iN0 = kotlin.text.b.n0(name, '$', iQ0, false, 4)) < 0) {
            d42Var = this;
            strSubstring = null;
        } else {
            strSubstring = name.substring(iQ0 + 1, iN0);
            v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            d42Var = this;
        }
        Map map = d42Var.d.a(strSubstring).b;
        v03.j(map, "properties");
        ia0 ia0Var = new ia0(map);
        int i = bjVar.a;
        my1 my1Var = bjVar.b;
        switch (i) {
            case 0:
                ((com.spotify.automotive.remoteconfig.a) my1Var).getClass();
                return new com.spotify.automotive.remoteconfig.a(ia0Var.b("android-connectivity-connectiontypeflags", "should_use_single_thread", false), ia0Var.b("android-connectivity-connectiontypeflags", "unauth_net_capabilities_validated_disregarded_enabled", false), null);
            case 1:
            default:
                ((com.spotify.remoteconfig.resolverimpl.a) my1Var).getClass();
                return new com.spotify.remoteconfig.resolverimpl.a(ia0Var.b("remote-config-snapshot-id", "enable_snapshot_id_sdk_api", false), null);
            case 2:
                ((com.spotify.remoteconfig.a) my1Var).getClass();
                return new com.spotify.remoteconfig.a(ia0Var.b("android-automotive-app", "enable_audiobooks_v2", false), ia0Var.b("android-automotive-app", "enable_custom_browse_actions", false), ia0Var.b("android-automotive-app", "enable_event_sender_skew_optimizations", false), ia0Var.b("android-automotive-app", "enable_full_core_player", false), ia0Var.b("android-automotive-app", "enable_partner_voice", false), ia0Var.b("android-automotive-app", "enable_personalized_recs", true), ia0Var.b("android-automotive-app", "enable_reinvent_free", false), ia0Var.b("android-automotive-app", "enable_social_connect", true), ia0Var.b("android-automotive-app", "enable_your_episodes", true), null);
        }
    }

    @Override // p.a42
    public final boolean c(byte[] bArr) {
        v03.j(bArr, "configuration");
        if (v03.b(this.a, wk1.a)) {
            throw new IllegalStateException("Not possible for UnAuthResolver".toString());
        }
        hk0 hk0Var = this.e;
        hk0Var.getClass();
        boolean zIsEmpty = ((eg2) ((xw) hk0Var.e).a).d.b().a.c.isEmpty();
        try {
            m02 m02Var = m02.c;
            Configuration configurationJ = Configuration.j(bArr);
            v03.i(configurationJ, "parseFrom(configuration)");
            m02 m02VarU = zb0.u(v01.b(configurationJ));
            if (m02VarU.b.isEmpty() && zIsEmpty) {
                return false;
            }
            if (!(bArr.length == 0)) {
                xw xwVar = (xw) hk0Var.e;
                synchronized (xwVar) {
                    ((eg2) xwVar.a).b(m02VarU);
                }
            }
            xw xwVar2 = (xw) hk0Var.e;
            synchronized (xwVar2) {
                xwVar2.b = null;
                xwVar2.d = null;
            }
            return true;
        } catch (InvalidProtocolBufferException unused) {
            qk2.a().getClass();
            hk0.v(new Object[0]);
            return false;
        }
    }

    @Override // p.a42
    public final void clear() {
        xw xwVar = this.d;
        synchronized (xwVar) {
            ((eg2) xwVar.a).a();
        }
        synchronized (xwVar) {
            xwVar.b = null;
            xwVar.d = null;
        }
    }

    @Override // p.a42
    public final pc d(String str, String str2) {
        if (v03.b(this.a, wk1.a)) {
            throw new IllegalStateException("Not possible for UnAuthResolver".toString());
        }
        m02 m02Var = m02.c;
        pc pcVar = (pc) this.d.a("esperanto").b.get(zb0.w(str, str2));
        return pcVar == null ? new pc("", "", null, null, null, 0L, 0L) : pcVar;
    }

    @Override // p.a42
    public final Long getSnapshotId() {
        if (new com.spotify.remoteconfig.resolverimpl.a(false, this).a()) {
            return this.d.d;
        }
        return null;
    }
}
