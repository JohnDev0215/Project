package defpackage;

import java.net.URISyntaxException;
import org.chromium.url.GURL;

/* JADX INFO: renamed from: yP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class C1822yP extends GURL {
    public C1822yP(String str) throws URISyntaxException {
        super(str);
        if (!this.b) {
            throw new URISyntaxException(str, "Uri could not be parsed as a valid GURL");
        }
    }

    public String toString() {
        return this.a;
    }
}
