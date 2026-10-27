package u2;

import h2.AbstractC1346c;
import java.io.IOException;

/* JADX INFO: renamed from: u2.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2502e extends IOException {
    public C2502e(int i7) {
        this(i7, -9223372036854775807L, -9223372036854775807L);
    }

    public C2502e(int i7, long j3, long j10) {
        String str;
        StringBuilder sb = new StringBuilder("Illegal clipping: ");
        if (i7 != 0) {
            if (i7 == 1) {
                str = "not seekable to start";
            } else if (i7 != 2) {
                str = "unknown";
            } else {
                AbstractC1346c.j((j3 == -9223372036854775807L || j10 == -9223372036854775807L) ? false : true);
                str = "start exceeds end. Start time: " + j3 + ", End time: " + j10;
            }
        } else {
            str = "invalid period count";
        }
        sb.append(str);
        super(sb.toString());
    }
}
