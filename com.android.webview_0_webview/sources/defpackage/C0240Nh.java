package defpackage;

/* JADX INFO: renamed from: Nh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0240Nh implements InterfaceC0222Mh {
    public volatile InterfaceC0222Mh s;
    public volatile boolean t;
    public Object u;

    public C0240Nh(InterfaceC0222Mh interfaceC0222Mh) {
        if (interfaceC0222Mh == null) {
            throw null;
        }
        this.s = interfaceC0222Mh;
    }

    @Override // defpackage.InterfaceC0222Mh
    public final Object get() {
        if (!this.t) {
            synchronized (this) {
                if (!this.t) {
                    Object obj = this.s.get();
                    this.u = obj;
                    this.t = true;
                    this.s = null;
                    return obj;
                }
            }
        }
        return this.u;
    }

    public final String toString() {
        Object string = this.s;
        if (string == null) {
            String strValueOf = String.valueOf(this.u);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 25);
            sb.append("<supplier that returned ");
            sb.append(strValueOf);
            sb.append(">");
            string = sb.toString();
        }
        String strValueOf2 = String.valueOf(string);
        StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(strValueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
