package defpackage;

import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JO extends OO {
    public final Map b;

    public JO(Map map, AbstractC1876zO abstractC1876zO) {
        this.b = map;
    }

    public Object a(FO fo) {
        DO r2 = (DO) this.b.get(fo);
        if (r2 == null) {
            return null;
        }
        return r2.a;
    }

    public boolean b(EO eo) {
        AO ao = (AO) this.b.get(eo);
        if (ao == null) {
            return false;
        }
        return ao.a;
    }
}
