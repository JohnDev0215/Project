package defpackage;

import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DO extends GO {
    public Object a;

    public DO(AbstractC1876zO abstractC1876zO) {
        super(null);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof DO) && Objects.equals(((DO) obj).a, this.a);
    }

    public String toString() {
        return this.a + " in " + super.toString();
    }
}
