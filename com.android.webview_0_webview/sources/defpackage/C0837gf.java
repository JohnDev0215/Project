package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: gf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0837gf {
    public final List a;
    public final Object b;

    public C0837gf(Object obj, AbstractC1226nf abstractC1226nf) {
        Cif.f(obj);
        this.b = obj;
        this.a = new ArrayList();
    }

    public final C0837gf a(String str, Object obj) {
        List list = this.a;
        Cif.f(str);
        String strValueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(strValueOf.length() + str.length() + 1);
        sb.append(str);
        sb.append("=");
        sb.append(strValueOf);
        list.add(sb.toString());
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.b.getClass().getSimpleName());
        sb.append('{');
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) this.a.get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
