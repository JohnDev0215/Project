package V4;

import N1.C0420a;
import android.graphics.Typeface;
import com.bumptech.glide.d;

/* JADX INFO: loaded from: classes.dex */
public final class a extends d {
    public final Typeface d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0420a f9461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f9462f;

    public a(C0420a c0420a, Typeface typeface) {
        this.d = typeface;
        this.f9461e = c0420a;
    }

    @Override // com.bumptech.glide.d
    public final void I(int i7) {
        if (this.f9462f) {
            return;
        }
        U4.b bVar = (U4.b) this.f9461e.l;
        a aVar = bVar.f9048v;
        if (aVar != null) {
            aVar.f9462f = true;
        }
        Typeface typeface = bVar.f9045s;
        Typeface typeface2 = this.d;
        if (typeface != typeface2) {
            bVar.f9045s = typeface2;
            bVar.g();
        }
    }

    @Override // com.bumptech.glide.d
    public final void J(Typeface typeface, boolean z6) {
        if (this.f9462f) {
            return;
        }
        U4.b bVar = (U4.b) this.f9461e.l;
        a aVar = bVar.f9048v;
        if (aVar != null) {
            aVar.f9462f = true;
        }
        if (bVar.f9045s != typeface) {
            bVar.f9045s = typeface;
            bVar.g();
        }
    }
}
