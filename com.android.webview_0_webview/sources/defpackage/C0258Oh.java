package defpackage;

import java.io.Serializable;

/* JADX INFO: renamed from: Oh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0258Oh implements InterfaceC0222Mh, Serializable {
    public final InterfaceC0222Mh s;
    public volatile transient boolean t;
    public transient Object u;

    public C0258Oh(InterfaceC0222Mh interfaceC0222Mh) {
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
                    return obj;
                }
            }
        }
        return this.u;
    }

    public final String toString() {
        Object string;
        if (this.t) {
            String strValueOf = String.valueOf(this.u);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 25);
            sb.append("<supplier that returned ");
            sb.append(strValueOf);
            sb.append(">");
            string = sb.toString();
        } else {
            string = this.s;
        }
        String strValueOf2 = String.valueOf(string);
        StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(strValueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
