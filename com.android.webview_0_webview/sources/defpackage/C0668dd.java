package defpackage;

/* JADX INFO: renamed from: dd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0668dd {
    public final Object a;
    public final String b;

    public C0668dd(Object obj, String str) {
        this.a = obj;
        this.b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0668dd)) {
            return false;
        }
        C0668dd c0668dd = (C0668dd) obj;
        return this.a == c0668dd.a && this.b.equals(c0668dd.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (System.identityHashCode(this.a) * 31);
    }
}
