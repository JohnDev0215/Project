package r;

import android.database.DataSetObserver;

/* JADX INFO: renamed from: r.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2243c0 extends DataSetObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ j0 f21473a;

    public C2243c0(j0 j0Var) {
        this.f21473a = j0Var;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        j0 j0Var = this.f21473a;
        if (j0Var.f21505G.isShowing()) {
            j0Var.c();
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        this.f21473a.dismiss();
    }
}
