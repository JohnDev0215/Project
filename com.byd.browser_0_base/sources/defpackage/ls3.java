package defpackage;

import android.text.TextUtils;
import defpackage.cp3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: WebappInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class ls3 {
    public final oj a;
    public cp3 b;

    public ls3(oj ojVar) {
        this.a = ojVar;
    }

    public static ls3 c(oj ojVar) {
        if (ojVar == null) {
            return null;
        }
        return new ls3(ojVar);
    }

    public String a() {
        return e().h;
    }

    public final long b() {
        if (f().j != null) {
            return r2.intValue();
        }
        return 2147483648L;
    }

    public final int d() {
        return f().g;
    }

    public final cp3 e() {
        cp3 cp3Var = this.b;
        if (cp3Var != null) {
            return cp3Var;
        }
        cp3 cp3VarL = this.a.L();
        this.b = cp3VarL;
        if (cp3VarL == null) {
            this.b = new cp3(null, new ks3(), false, 0, null, null, null, null, 2, new HashMap(), null, false, new ArrayList(), 0);
        }
        return this.b;
    }

    public final js3 f() {
        return this.a.M();
    }

    public ks3 g() {
        return f().d;
    }

    public Map<String, String> h() {
        return e().j;
    }

    public final String i() {
        return f().a;
    }

    public final boolean j() {
        return !TextUtils.isEmpty(u());
    }

    public boolean k() {
        return f().m;
    }

    public boolean l() {
        return f().l;
    }

    public final String m() {
        return e().g;
    }

    public final String n() {
        return e().f;
    }

    public final String o() {
        return e().e;
    }

    public String p() {
        return f().e;
    }

    public final String q() {
        return f().c;
    }

    public String r() {
        return f().f;
    }

    public final List<cp3.a> s() {
        return e().m;
    }

    public final long t() {
        if (this.a.m().f()) {
            return r2.m().d();
        }
        return 2147483648L;
    }

    public final String u() {
        return e().a;
    }
}
