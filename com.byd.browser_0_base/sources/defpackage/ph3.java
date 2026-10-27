package defpackage;

import J.N;
import java.net.URISyntaxException;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: URI.java */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class ph3 extends GURL {
    public ph3(String str) throws URISyntaxException {
        super(str);
        if (!this.b) {
            throw new URISyntaxException(str, "Uri could not be parsed as a valid GURL");
        }
    }

    @Override // org.chromium.url.GURL
    public final GURL f() {
        ph3 ph3Var = new ph3();
        N.MNBd3mFA(this.a, this.b, this.c.c(), ph3Var);
        return ph3Var;
    }

    public final String toString() {
        return this.a;
    }

    public ph3() {
    }
}
