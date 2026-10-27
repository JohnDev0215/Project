package D5;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class c extends Date {
    public final long l;

    public c(long j3) {
        super(j3 / 1000000);
        this.l = j3;
    }

    @Override // java.util.Date
    public final boolean equals(Object obj) {
        boolean z6 = obj instanceof c;
        long j3 = this.l;
        if (z6) {
            return ((c) obj).l == j3;
        }
        if (obj instanceof Date) {
            return super.equals(obj) && j3 % 1000000 == 0;
        }
        return false;
    }
}
