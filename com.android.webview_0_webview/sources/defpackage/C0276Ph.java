package defpackage;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: renamed from: Ph, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0276Ph implements InterfaceC0222Mh, Serializable {
    public final Object s;

    public C0276Ph(Object obj) {
        this.s = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0276Ph) {
            return AbstractC0133Hh.a(this.s, ((C0276Ph) obj).s);
        }
        return false;
    }

    @Override // defpackage.InterfaceC0222Mh
    public final Object get() {
        return this.s;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.s});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.s);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 22);
        sb.append("Suppliers.ofInstance(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }
}
