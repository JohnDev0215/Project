package defpackage;

/* JADX INFO: renamed from: Jh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0169Jh extends AbstractC0115Gh {
    public final Object s;

    public C0169Jh(Object obj) {
        this.s = obj;
    }

    @Override // defpackage.AbstractC0115Gh
    public final Object a() {
        return this.s;
    }

    @Override // defpackage.AbstractC0115Gh
    public final boolean b() {
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0169Jh) {
            return this.s.equals(((C0169Jh) obj).s);
        }
        return false;
    }

    public final int hashCode() {
        return this.s.hashCode() + 1502476572;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.s);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 13);
        sb.append("Optional.of(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }
}
