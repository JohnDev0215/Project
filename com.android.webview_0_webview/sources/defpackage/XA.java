package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class XA {
    public int a;
    public int b;

    public XA(int i, int i2) {
        this.a = Math.min(i, i2);
        this.b = Math.max(i, i2);
    }

    public void a(int i, int i2) {
        this.a = Math.min(Math.max(this.a, i), i2);
        this.b = Math.max(Math.min(this.b, i2), i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XA)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        XA xa = (XA) obj;
        return this.a == xa.a && this.b == xa.b;
    }

    public int hashCode() {
        return (this.b * 31) + (this.a * 11);
    }

    public String toString() {
        StringBuilder sbF = Z7.f("[ ");
        sbF.append(this.a);
        sbF.append(", ");
        sbF.append(this.b);
        sbF.append(" ]");
        return sbF.toString();
    }
}
