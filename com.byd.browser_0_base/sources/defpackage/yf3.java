package defpackage;

/* JADX INFO: compiled from: TrustedWebActivityDisclosureController.java */
/* JADX INFO: loaded from: classes.dex */
public final class yf3 extends oa0 {
    public final pj h;
    public final jg3 i;
    public final eu j;

    public yf3(pj pjVar, ag3 ag3Var, q2 q2Var, h20 h20Var, jg3 jg3Var, eu euVar) {
        super(ag3Var, q2Var, h20Var, euVar.e);
        this.h = pjVar;
        this.i = jg3Var;
        this.j = euVar;
    }

    @Override // defpackage.oa0, ag3.a
    public final void a() {
        this.i.getClass();
        n3.X0("TrustedWebActivity.DisclosureAccepted");
        this.h.a.b("trusted_web_activity_disclosure_accepted_packages", this.j.e);
        this.e.n(ag3.c, 2);
    }

    @Override // defpackage.oa0, ag3.a
    public final void b() {
        this.i.getClass();
        n3.X0("TrustedWebActivity.DisclosureShown");
        this.h.a.b("Chrome.TrustedWebActivities.DisclosureAcceptedPackages", this.j.e);
        super.b();
    }

    @Override // defpackage.oa0
    public final boolean c() {
        return !this.h.a.h("Chrome.TrustedWebActivities.DisclosureAcceptedPackages").contains(this.j.e);
    }

    @Override // defpackage.oa0
    public final boolean d() {
        return !this.h.a.h("trusted_web_activity_disclosure_accepted_packages").contains(this.j.e);
    }
}
