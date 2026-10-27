package p;

import android.net.Uri;
import com.spotify.player.model.ContextTrack;

/* JADX INFO: loaded from: classes.dex */
public final class pz1 {
    public final ContextTrack a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final Uri f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final long j;
    public final long k;

    public pz1(ContextTrack contextTrack, String str, String str2, String str3, String str4, Uri uri, boolean z, boolean z2, boolean z3, long j, long j2) {
        this.a = contextTrack;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = uri;
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.j = j;
        this.k = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pz1)) {
            return false;
        }
        pz1 pz1Var = (pz1) obj;
        return v03.b(this.a, pz1Var.a) && v03.b(this.b, pz1Var.b) && v03.b(this.c, pz1Var.c) && v03.b(this.d, pz1Var.d) && v03.b(this.e, pz1Var.e) && v03.b(this.f, pz1Var.f) && this.g == pz1Var.g && this.h == pz1Var.h && this.i == pz1Var.i && this.j == pz1Var.j && this.k == pz1Var.k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [int] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v5, types: [int] */
    /* JADX WARN: Type inference failed for: r3v7, types: [int] */
    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        String str = this.c;
        int iHashCode = (iB + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        int iHashCode2 = (this.f.hashCode() + hf2.b(this.e, (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31, 31)) * 31;
        boolean z = this.g;
        ?? r3 = z;
        if (z) {
            r3 = 1;
        }
        int i = (iHashCode2 + r3) * 31;
        boolean z2 = this.h;
        ?? r32 = z2;
        if (z2) {
            r32 = 1;
        }
        int i2 = (i + r32) * 31;
        boolean z3 = this.i;
        return Long.hashCode(this.k) + ((Long.hashCode(this.j) + ((i2 + (z3 ? 1 : z3)) * 31)) * 31);
    }

    public final String toString() {
        return "QueuedTrack(playerTrack=" + this.a + ", title=" + this.b + ", subTitle=" + this.c + ", groupTitle=" + this.d + ", uri=" + this.e + ", iconUri=" + this.f + ", isExplicit=" + this.g + ", is19Plus=" + this.h + ", isUnavailable=" + this.i + ", duration=" + this.j + ", queueId=" + this.k + ')';
    }
}
