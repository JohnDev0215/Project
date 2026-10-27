package p;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class ch {
    public final g40 a;
    public final String b;
    public final File c;

    public ch(bh bhVar, String str, File file) {
        this.a = bhVar;
        if (str == null) {
            throw new NullPointerException("Null sessionId");
        }
        this.b = str;
        this.c = file;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ch)) {
            return false;
        }
        ch chVar = (ch) obj;
        return this.a.equals(chVar.a) && this.b.equals(chVar.b) && this.c.equals(chVar.c);
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        return "CrashlyticsReportWithSessionId{report=" + this.a + ", sessionId=" + this.b + ", reportFile=" + this.c + "}";
    }
}
