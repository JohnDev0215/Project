package H4;

import android.graphics.Path;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class s implements t, u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2819a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ w f2820b;

    public s(float f10) {
        this.f2819a = f10;
        this.f2820b = new w(f10, true, true, true, true, true);
    }

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        return this.f2820b.a(f10, cVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof s) && Float.valueOf(this.f2819a).equals(Float.valueOf(((s) obj).f2819a));
    }

    public final int hashCode() {
        return Float.hashCode(this.f2819a);
    }

    public final String toString() {
        return AbstractC1347d.h(new StringBuilder("RoundCorners(radius="), this.f2819a, ')');
    }
}
