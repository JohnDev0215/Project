package a.b.p;

import a.b.p.w;
import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class v extends h0 {
    public final /* synthetic */ w.d j;
    public final /* synthetic */ w k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(w wVar, View view, w.d dVar) {
        super(view);
        this.k = wVar;
        this.j = dVar;
    }

    @Override // a.b.p.h0
    public a.b.o.i.p b() {
        return this.j;
    }

    @Override // a.b.p.h0
    @SuppressLint({"SyntheticAccessor"})
    public boolean c() {
        if (this.k.getInternalPopup().a()) {
            return true;
        }
        this.k.b();
        return true;
    }
}
