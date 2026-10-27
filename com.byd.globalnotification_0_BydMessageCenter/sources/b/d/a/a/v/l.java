package b.d.a.a.v;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class l implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f2102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f2103b;

    public l(f fVar, r rVar) {
        this.f2103b = fVar;
        this.f2102a = rVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int iM1 = this.f2103b.Q().m1() - 1;
        if (iM1 >= 0) {
            this.f2103b.S(this.f2102a.j(iM1));
        }
    }
}
