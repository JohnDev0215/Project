package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import defpackage.eg3;
import defpackage.wf3;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: TrustedWebActivityClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class sf3 {
    public final wf3.b a;
    public final org.chromium.chrome.browser.browserservices.permissiondelegation.a b;
    public final jg3 c;

    public sf3(eg3 eg3Var, org.chromium.chrome.browser.browserservices.permissiondelegation.a aVar, jg3 jg3Var) {
        this.a = new uf3(eg3Var);
        this.b = aVar;
        this.c = jg3Var;
    }

    public final void a(Uri uri, a aVar) {
        c91 c91VarA;
        ft1 ft1VarA = ft1.a(uri);
        if (ft1VarA == null) {
            aVar.c();
            return;
        }
        HashSet hashSetB = this.b.b(ft1VarA);
        if (hashSetB == null || hashSetB.isEmpty()) {
            aVar.c();
            return;
        }
        uf3 uf3Var = (uf3) this.a;
        uf3Var.getClass();
        f8 f8Var = i9.e;
        eg3 eg3Var = uf3Var.a;
        HashMap map = eg3Var.b;
        hx hxVar = (hx) map.get(uri);
        int i = 0;
        if (hxVar != null) {
            c91VarA = hxVar.a();
        } else {
            Context context = eg3Var.a;
            int i2 = 1;
            Intent intentA = eg3.a(context, uri, hashSetB, true);
            if (intentA == null) {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException("No service exists for scope");
                nb2 nb2Var = new nb2();
                nb2Var.h(illegalArgumentException);
                c91VarA = nb2Var;
            } else {
                hx hxVar2 = new hx(new l91(eg3Var, i2, uri));
                map.put(uri, hxVar2);
                new eg3.a(context, intentA, hxVar2).executeOnExecutor(f8Var, new Void[0]);
                c91VarA = hxVar2.a();
            }
        }
        c91VarA.a(new tf3(aVar, i, ft1VarA, c91VarA), wf3.a);
    }

    public final boolean b(Uri uri) {
        HashSet hashSetB;
        ft1 ft1VarA = ft1.a(uri);
        if (ft1VarA == null || (hashSetB = this.b.b(ft1VarA)) == null) {
            return false;
        }
        eg3 eg3Var = ((uf3) this.a).a;
        return (eg3Var.b.get(uri) == null && eg3.a(eg3Var.a, uri, hashSetB, false) == null) ? false : true;
    }

    /* JADX INFO: compiled from: TrustedWebActivityClient.java */
    public interface a {
        void f(ft1 ft1Var, vf3 vf3Var);

        default void c() {
        }
    }

    /* JADX INFO: compiled from: TrustedWebActivityClient.java */
    public interface b {
        void a(ComponentName componentName, int i);

        default void c() {
        }
    }
}
