package p;

import com.spotify.player.model.PlayerQueue;

/* JADX INFO: loaded from: classes.dex */
public final class ku1 {
    public final boolean a;
    public final boolean b;
    public final String c;
    public final String d;
    public final PlayerQueue e;

    public ku1(boolean z, boolean z2, String str, String str2, PlayerQueue playerQueue) {
        v03.j(playerQueue, "playerQueue");
        this.a = z;
        this.b = z2;
        this.c = str;
        this.d = str2;
        this.e = playerQueue;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ku1)) {
            return false;
        }
        ku1 ku1Var = (ku1) obj;
        return this.a == ku1Var.a && this.b == ku1Var.b && v03.b(this.c, ku1Var.c) && v03.b(this.d, ku1Var.d) && v03.b(this.e, ku1Var.e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public final int hashCode() {
        boolean z = this.a;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = r1 * 31;
        boolean z2 = this.b;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        String str = this.c;
        int iHashCode = (i2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        return this.e.hashCode() + ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    public final String toString() {
        return "PlayerStateAndQueueWrapper(allowPeekingNext=" + this.a + ", repeatingContext=" + this.b + ", contextTitle=" + this.c + ", currentTrack=" + this.d + ", playerQueue=" + this.e + ')';
    }
}
