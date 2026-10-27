package defpackage;

import java.util.Iterator;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: h1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0860h1 implements Iterable {
    public WeakHashMap s = new WeakHashMap();
    public int t = 0;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0860h1)) {
            return false;
        }
        C0860h1 c0860h1 = (C0860h1) obj;
        Objects.requireNonNull(c0860h1);
        iterator();
        c0860h1.iterator();
        return true;
    }

    public int hashCode() {
        iterator();
        return 0;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        C0637d1 c0637d1 = new C0637d1();
        this.s.put(c0637d1, Boolean.FALSE);
        return c0637d1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        iterator();
        sb.append("]");
        return sb.toString();
    }
}
