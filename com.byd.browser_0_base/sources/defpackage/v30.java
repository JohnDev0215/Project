package defpackage;

import android.content.Context;
import android.content.Intent;
import androidx.browser.customtabs.CustomTabsSessionToken;

/* JADX INFO: compiled from: CustomTabIntentHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class v30 {
    public final a30 a;
    public final oj b;
    public final w30 c;
    public final a d;
    public final Context e;
    public Runnable f;

    /* JADX INFO: compiled from: CustomTabIntentHandler.java */
    public interface a {
        boolean a(Intent intent);
    }

    public v30(a30 a30Var, oj ojVar, w30 w30Var, a aVar, Context context) {
        this.a = a30Var;
        this.b = ojVar;
        this.c = w30Var;
        this.d = aVar;
        this.e = context;
        if (a30Var.b == null) {
            a30Var.a.a(new u30(this));
        }
        aw awVar = new aw(21, this);
        if (a30Var.b != null) {
            awVar.run();
        } else {
            this.f = awVar;
        }
    }

    public final boolean a(oj ojVar) {
        Intent intentT = ojVar.t();
        CustomTabsSessionToken customTabsSessionTokenZ = ojVar.z();
        js3 js3VarM = ojVar.M();
        if (js3VarM != null) {
            if (!js3VarM.n) {
                return false;
            }
        } else if (customTabsSessionTokenZ == null || !customTabsSessionTokenZ.equals(this.b.z())) {
            intentT.setFlags((intentT.getFlags() & (-603979777)) | 268435456);
            this.e.startActivity(intentT);
            return false;
        }
        if (this.d.a(intentT)) {
            return false;
        }
        l91 l91Var = new l91(this, 12, ojVar);
        if (this.a.b != null) {
            l91Var.run();
            return true;
        }
        this.f = l91Var;
        return true;
    }
}
