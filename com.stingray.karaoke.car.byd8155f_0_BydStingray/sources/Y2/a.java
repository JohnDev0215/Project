package Y2;

import d5.I;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final I f10629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f10630b;
    public final long c;
    public final long d;

    public a(List list, long j3, long j10) {
        this.f10629a = I.w(list);
        this.f10630b = j3;
        this.c = j10;
        long j11 = -9223372036854775807L;
        if (j3 != -9223372036854775807L && j10 != -9223372036854775807L) {
            j11 = j3 + j10;
        }
        this.d = j11;
    }
}
