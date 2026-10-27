package p8;

import java.io.IOException;

/* JADX INFO: renamed from: p8.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2142q extends IOException {
    public AbstractC2126a l;

    public C2142q(String str) {
        super(str);
        this.l = null;
    }

    public static C2142q a() {
        return new C2142q("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }
}
