package defpackage;

import J.N;
import android.app.Activity;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: CustomTabIncognitoManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class s30 implements hk1, v80 {
    public final Activity e;
    public final w20 f;
    public final oj g;
    public a h;
    public final dz0 i;
    public final wx0 j;

    /* JADX INFO: compiled from: CustomTabIncognitoManager.java */
    public class a implements cz0 {
        public a() {
        }

        @Override // defpackage.cz0
        public final void a() {
            s30.this.f.a(2);
        }

        @Override // defpackage.cz0
        public final boolean b() {
            return !s30.this.e.isFinishing();
        }

        @Override // defpackage.cz0
        public final boolean isActiveModel() {
            return true;
        }
    }

    public s30(Activity activity, oj ojVar, w20 w20Var, q2 q2Var, dz0 dz0Var, wx0 wx0Var) {
        this.e = activity;
        this.g = ojVar;
        this.f = w20Var;
        this.i = dz0Var;
        this.j = wx0Var;
        q2Var.f(this);
    }

    @Override // defpackage.hk1
    public final void G0() {
        oj ojVar = this.g;
        ojVar.getClass();
        if (ojVar instanceof xx0) {
            if (ojVar.N()) {
                a aVar = new a();
                this.h = aVar;
                this.i.a.add(aVar);
            }
            if (iv.e().g("enable-incognito-snapshots-in-android-recents")) {
                return;
            }
            new yx0(this.e.getWindow(), new yq2(7, this));
        }
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        a aVar = this.h;
        if (aVar != null) {
            this.i.a.remove(aVar);
        }
        wx0 wx0Var = this.j;
        if (wx0Var.e != null) {
            Profile profileE = Profile.d().e(wx0Var.e);
            N.MScIZBOB(profileE.b, profileE);
            wx0Var.e = null;
        }
        wx0.f.b(wx0Var);
    }
}
