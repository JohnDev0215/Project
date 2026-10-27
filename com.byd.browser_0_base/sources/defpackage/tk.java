package defpackage;

import android.util.Log;

/* JADX INFO: compiled from: CachedFlagsSafeMode.java */
/* JADX INFO: loaded from: classes.dex */
public final class tk extends i9<Void> {
    public final /* synthetic */ wk3 h;
    public final /* synthetic */ uk i;

    public tk(uk ukVar, wk3 wk3Var) {
        this.i = ukVar;
        this.h = wk3Var;
    }

    @Override // defpackage.i9
    public final Void b() {
        try {
            uk.a(this.i, this.h);
            return null;
        } catch (Exception e) {
            Log.e("cr_Flags", "Exception writing safe values.", e);
            a(true);
            return null;
        }
    }

    @Override // defpackage.i9
    public final void l(Void r2) {
        oj2.U(this.i.b.get(), 5, "Variations.SafeModeCachedFlags.Cached");
    }
}
