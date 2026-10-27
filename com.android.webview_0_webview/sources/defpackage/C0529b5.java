package defpackage;

/* JADX INFO: renamed from: b5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0529b5 {
    public int a;
    public int b;
    public Object c;
    public int d;

    public C0529b5(int i, int i2, int i3, Object obj) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.c = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0529b5.class != obj.getClass()) {
            return false;
        }
        C0529b5 c0529b5 = (C0529b5) obj;
        int i = this.a;
        if (i != c0529b5.a) {
            return false;
        }
        if (i == 8 && Math.abs(this.d - this.b) == 1 && this.d == c0529b5.b && this.b == c0529b5.d) {
            return true;
        }
        if (this.d != c0529b5.d || this.b != c0529b5.b) {
            return false;
        }
        Object obj2 = this.c;
        if (obj2 != null) {
            if (!obj2.equals(c0529b5.c)) {
                return false;
            }
        } else if (c0529b5.c != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.a * 31) + this.b) * 31) + this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i = this.a;
        sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb.append(",s:");
        sb.append(this.b);
        sb.append("c:");
        sb.append(this.d);
        sb.append(",p:");
        sb.append(this.c);
        sb.append("]");
        return sb.toString();
    }
}
