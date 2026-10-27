package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class NJ {
    public int a;
    public boolean b = false;

    public NJ(int i) {
        this.a = i;
    }

    public NJ a(int i, boolean z) {
        if (this.b) {
            throw new UnsupportedOperationException("Flags is immutable.");
        }
        if (z) {
            this.a = i | this.a;
        } else {
            this.a = (~i) & this.a;
        }
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.a == ((NJ) obj).a;
    }

    public int hashCode() {
        return this.a;
    }
}
