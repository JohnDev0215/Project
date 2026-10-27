package defpackage;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: compiled from: SectionHeaderView.java */
/* JADX INFO: loaded from: classes.dex */
public final class bh2 implements l81 {
    public final /* synthetic */ zd e;

    public bh2(zd zdVar) {
        this.e = zdVar;
    }

    @Override // defpackage.l81
    public final z92 a(View view) {
        nn3 nn3Var = new nn3(view);
        nn3Var.d(true);
        nn3Var.e(new Rect(0, 0, 0, 0));
        return nn3Var;
    }

    @Override // defpackage.l81
    public final k81 b() {
        return this.e;
    }
}
