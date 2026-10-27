package q9;

import A7.m;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f21383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f21384b;
    public final boolean c;
    public final Integer d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f21385e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f21386f;

    public h(boolean z6, Integer num, boolean z10, Integer num2, boolean z11, boolean z12) {
        this.f21383a = z6;
        this.f21384b = num;
        this.c = z10;
        this.d = num2;
        this.f21385e = z11;
        this.f21386f = z12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f21383a == hVar.f21383a && m.a(this.f21384b, hVar.f21384b) && this.c == hVar.c && m.a(this.d, hVar.d) && this.f21385e == hVar.f21385e && this.f21386f == hVar.f21386f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r3v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    public final int hashCode() {
        boolean z6 = this.f21383a;
        ?? r12 = z6;
        if (z6) {
            r12 = 1;
        }
        int i7 = r12 * 31;
        Integer num = this.f21384b;
        int iHashCode = (i7 + (num == null ? 0 : num.hashCode())) * 31;
        boolean z10 = this.c;
        ?? r32 = z10;
        if (z10) {
            r32 = 1;
        }
        int i10 = (iHashCode + r32) * 31;
        Integer num2 = this.d;
        int iHashCode2 = (i10 + (num2 != null ? num2.hashCode() : 0)) * 31;
        boolean z11 = this.f21385e;
        ?? r22 = z11;
        if (z11) {
            r22 = 1;
        }
        int i11 = (iHashCode2 + r22) * 31;
        boolean z12 = this.f21386f;
        return i11 + (z12 ? 1 : z12);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WebSocketExtensions(perMessageDeflate=");
        sb.append(this.f21383a);
        sb.append(", clientMaxWindowBits=");
        sb.append(this.f21384b);
        sb.append(", clientNoContextTakeover=");
        sb.append(this.c);
        sb.append(", serverMaxWindowBits=");
        sb.append(this.d);
        sb.append(", serverNoContextTakeover=");
        sb.append(this.f21385e);
        sb.append(", unknownValues=");
        return AbstractC1347d.i(sb, this.f21386f, ')');
    }
}
