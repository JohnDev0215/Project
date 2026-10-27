package defpackage;

import android.util.Log;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: MostVisitedSitesMetadataUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oi1 extends i9<Void> {
    public final /* synthetic */ List h;
    public final /* synthetic */ Runnable i;

    public oi1(List list, f52 f52Var) {
        this.h = list;
        this.i = f52Var;
    }

    @Override // defpackage.i9
    public final Void b() {
        try {
            pi1.a(pi1.d(), pi1.b(this.h));
            return null;
        } catch (IOException unused) {
            Log.e("cr_TopSites", "Fail to save file.");
            return null;
        }
    }

    @Override // defpackage.i9
    public final void l(Void r1) {
        Runnable runnable = this.i;
        if (runnable != null) {
            runnable.run();
        }
    }
}
