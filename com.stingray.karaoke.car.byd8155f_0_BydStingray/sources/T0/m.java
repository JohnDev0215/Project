package T0;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final m f8459g = new m(false, 0, true, 1, 1, U0.b.f8957n);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8460a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8461b;
    public final boolean c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8462e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final U0.b f8463f;

    public m(boolean z6, int i7, boolean z10, int i10, int i11, U0.b bVar) {
        this.f8460a = z6;
        this.f8461b = i7;
        this.c = z10;
        this.d = i10;
        this.f8462e = i11;
        this.f8463f = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.f8460a == mVar.f8460a && n.a(this.f8461b, mVar.f8461b) && this.c == mVar.c && o.a(this.d, mVar.d) && l.a(this.f8462e, mVar.f8462e) && A7.m.a(null, null) && A7.m.a(this.f8463f, mVar.f8463f);
    }

    public final int hashCode() {
        return this.f8463f.l.hashCode() + AbstractC2641j.b(this.f8462e, AbstractC2641j.b(this.d, AbstractC1347d.d(AbstractC2641j.b(this.f8461b, Boolean.hashCode(this.f8460a) * 31, 31), 31, this.c), 31), 961);
    }

    public final String toString() {
        return "ImeOptions(singleLine=" + this.f8460a + ", capitalization=" + ((Object) n.b(this.f8461b)) + ", autoCorrect=" + this.c + ", keyboardType=" + ((Object) o.b(this.d)) + ", imeAction=" + ((Object) l.b(this.f8462e)) + ", platformImeOptions=null, hintLocales=" + this.f8463f + ')';
    }
}
