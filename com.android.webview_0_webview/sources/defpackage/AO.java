package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AO extends GO {
    public boolean a;

    public AO(AbstractC1876zO abstractC1876zO) {
        super(null);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof AO) && ((AO) obj).a == this.a;
    }

    public String toString() {
        return this.a + " in " + super.toString();
    }
}
