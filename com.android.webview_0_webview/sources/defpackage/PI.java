package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class PI {
    public final int a;
    public final int b;

    public PI(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || PI.class != obj.getClass()) {
            return false;
        }
        PI pi = (PI) obj;
        return this.b == pi.b && this.a == pi.a;
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + this.a;
    }
}
