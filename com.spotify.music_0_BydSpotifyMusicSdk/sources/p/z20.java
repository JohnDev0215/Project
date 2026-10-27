package p;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

/* JADX INFO: loaded from: classes.dex */
public final class z20 implements zc1 {
    public static final ge2 g = ge2.b.o("crashlytics_partner_id");
    public final FirebaseCrashlytics b;
    public final gs1 c;
    public final he2 d;
    public String e;
    public final io.reactivex.rxjava3.disposables.a f;

    public z20(FirebaseCrashlytics firebaseCrashlytics, gs1 gs1Var, he2 he2Var) {
        v03.j(firebaseCrashlytics, "firebaseCrashlytics");
        v03.j(gs1Var, "partnerUserIdEndpoint");
        v03.j(he2Var, "sharedPreferences");
        this.b = firebaseCrashlytics;
        this.c = gs1Var;
        this.d = he2Var;
        this.f = new io.reactivex.rxjava3.disposables.a();
    }

    @Override // p.zc1
    public final void a() {
        this.f.d();
    }
}
