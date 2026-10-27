package defpackage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.chrome.browser.browserservices.verification.ChromeOriginVerifier;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.content_relationship_verification.OriginVerifier;

/* JADX INFO: compiled from: TwaVerifier.java */
/* JADX INFO: loaded from: classes.dex */
public final class ch3 implements il3, v80 {
    public final oj e;
    public final ChromeOriginVerifier f;
    public HashSet g;
    public boolean h;
    public final HashSet i = new HashSet();

    public ch3(q2 q2Var, oj ojVar, a8 a8Var, a30 a30Var, eu euVar, zi0 zi0Var) {
        this.e = ojVar;
        Tab tab = a30Var.b;
        this.f = a8Var.a(euVar.e, 2, tab != null ? tab.c() : null, zi0Var);
        q2Var.f(this);
    }

    @Override // defpackage.il3
    public final String a(String str) {
        ft1 ft1VarB = ft1.b(str);
        if (ft1VarB == null) {
            return null;
        }
        return ft1VarB.toString();
    }

    @Override // defpackage.il3
    public final boolean b(String str) {
        ft1 ft1VarB = ft1.b(str);
        if (ft1VarB == null) {
            return false;
        }
        return ((HashSet) e()).contains(ft1VarB) || this.i.contains(ft1VarB);
    }

    @Override // defpackage.il3
    public final boolean c(String str) {
        ft1 ft1VarB = ft1.b(str);
        if (ft1VarB == null) {
            return false;
        }
        return this.f.g(ft1VarB);
    }

    @Override // defpackage.il3
    public final s62<Boolean> d(String str) {
        final ft1 ft1VarB = ft1.b(str);
        if (ft1VarB == null) {
            return s62.c(Boolean.FALSE);
        }
        final s62<Boolean> s62Var = new s62<>();
        boolean zContains = ((HashSet) e()).contains(ft1VarB);
        ChromeOriginVerifier chromeOriginVerifier = this.f;
        if (zContains) {
            chromeOriginVerifier.f(new OriginVerifier.a() { // from class: bh3
                @Override // org.chromium.components.content_relationship_verification.OriginVerifier.a
                public final void a(String str2, ft1 ft1Var, boolean z, Boolean bool) {
                    ch3 ch3Var = this.a;
                    if (ch3Var.h) {
                        return;
                    }
                    HashSet hashSet = (HashSet) ch3Var.e();
                    ft1 ft1Var2 = ft1VarB;
                    hashSet.remove(ft1Var2);
                    if (z) {
                        ch3Var.i.add(ft1Var2);
                    }
                    s62Var.b(Boolean.valueOf(z));
                }
            }, ft1VarB);
        } else {
            s62Var.b(Boolean.valueOf(chromeOriginVerifier.g(ft1VarB)));
        }
        return s62Var;
    }

    public final Set<ft1> e() {
        if (this.g == null) {
            this.g = new HashSet();
            oj ojVar = this.e;
            ft1 ft1VarB = ft1.b(ojVar.K());
            if (ft1VarB != null) {
                this.g.add(ft1VarB);
            }
            List<String> listG = ojVar.G();
            if (listG != null) {
                Iterator<String> it = listG.iterator();
                while (it.hasNext()) {
                    ft1 ft1VarB2 = ft1.b(it.next());
                    if (ft1VarB2 != null) {
                        this.g.add(ft1VarB2);
                    }
                }
            }
        }
        return this.g;
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.h = true;
    }
}
