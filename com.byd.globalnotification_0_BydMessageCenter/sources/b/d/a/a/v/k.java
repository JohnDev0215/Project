package b.d.a.a.v;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class k implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f2100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f2101b;

    public k(f fVar, r rVar) {
        this.f2101b = fVar;
        this.f2100a = rVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int iK1 = this.f2101b.Q().k1() + 1;
        if (iK1 < this.f2101b.d0.getAdapter().a()) {
            this.f2101b.S(this.f2100a.j(iK1));
        }
    }
}
