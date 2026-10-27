package t8;

import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2057b f22497a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22498b;

    public f(C2057b c2057b, int i7) {
        this.f22497a = c2057b;
        this.f22498b = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return A7.m.a(this.f22497a, fVar.f22497a) && this.f22498b == fVar.f22498b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f22498b) + (this.f22497a.hashCode() * 31);
    }

    public final String toString() {
        int i7;
        StringBuilder sb = new StringBuilder();
        int i10 = 0;
        while (true) {
            i7 = this.f22498b;
            if (i10 >= i7) {
                break;
            }
            sb.append("kotlin/Array<");
            i10++;
        }
        sb.append(this.f22497a);
        for (int i11 = 0; i11 < i7; i11++) {
            sb.append(">");
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
