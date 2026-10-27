package defpackage;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class qj extends vj {
    public final Typeface a;
    public final a b;
    public boolean c;

    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface a {
        void a(Typeface typeface);
    }

    public qj(a aVar, Typeface typeface) {
        this.a = typeface;
        this.b = aVar;
    }

    @Override // defpackage.vj
    public void a(int i) {
        d(this.a);
    }

    @Override // defpackage.vj
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.c = true;
    }

    public final void d(Typeface typeface) {
        if (this.c) {
            return;
        }
        this.b.a(typeface);
    }
}
