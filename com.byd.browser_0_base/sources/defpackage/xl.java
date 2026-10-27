package defpackage;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class xl extends vd1 {
    public final Typeface k;
    public final a l;
    public boolean m;

    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface a {
        void a(Typeface typeface);
    }

    public xl(a aVar, Typeface typeface) {
        this.k = typeface;
        this.l = aVar;
    }

    @Override // defpackage.vd1
    public final void s(int i) {
        if (this.m) {
            return;
        }
        this.l.a(this.k);
    }

    @Override // defpackage.vd1
    public final void w(Typeface typeface, boolean z) {
        if (this.m) {
            return;
        }
        this.l.a(typeface);
    }
}
