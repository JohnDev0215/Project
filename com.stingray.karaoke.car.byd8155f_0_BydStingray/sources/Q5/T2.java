package Q5;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class T2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6573b;
    public final b0.b c;

    public T2(String str, int i7, b0.b bVar) {
        A7.m.f(str, "id");
        this.f6572a = str;
        this.f6573b = i7;
        this.c = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof T2)) {
            return false;
        }
        T2 t22 = (T2) obj;
        return A7.m.a(this.f6572a, t22.f6572a) && this.f6573b == t22.f6573b && this.c.equals(t22.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + AbstractC2641j.b(this.f6573b, this.f6572a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "TileData(id=" + this.f6572a + ", nbOfTileSpace=" + this.f6573b + ", item=" + this.c + ")";
    }
}
