package defpackage;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: Wh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0402Wh implements Serializable, Map {
    public transient AbstractC0438Yh s;
    public transient AbstractC0438Yh t;
    public transient AbstractC0330Sh u;

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return ((AbstractC0330Sh) values()).contains(obj);
    }

    @Override // java.util.Map
    public Set entrySet() {
        AbstractC0438Yh abstractC0438Yh = this.s;
        if (abstractC0438Yh != null) {
            return abstractC0438Yh;
        }
        C0456Zh c0456Zh = (C0456Zh) this;
        C0617ci c0617ci = new C0617ci(c0456Zh, c0456Zh.w, c0456Zh.x);
        this.s = c0617ci;
        return c0617ci;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public int hashCode() {
        return AbstractC0784fi.a((AbstractC0438Yh) entrySet());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public Set keySet() {
        AbstractC0438Yh abstractC0438Yh = this.t;
        if (abstractC0438Yh != null) {
            return abstractC0438Yh;
        }
        C0456Zh c0456Zh = (C0456Zh) this;
        C0728ei c0728ei = new C0728ei(c0456Zh, new C0673di(c0456Zh.w, 0, c0456Zh.x));
        this.t = c0728ei;
        return c0728ei;
    }

    @Override // java.util.Map
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        if (size < 0) {
            StringBuilder sb = new StringBuilder("size".length() + 40);
            sb.append("size");
            sb.append(" cannot be negative but was: ");
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824L));
        sb2.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb2.append(", ");
            }
            z = false;
            sb2.append(entry.getKey());
            sb2.append('=');
            sb2.append(entry.getValue());
        }
        sb2.append('}');
        return sb2.toString();
    }

    @Override // java.util.Map
    public Collection values() {
        AbstractC0330Sh abstractC0330Sh = this.u;
        if (abstractC0330Sh != null) {
            return abstractC0330Sh;
        }
        C0456Zh c0456Zh = (C0456Zh) this;
        C0673di c0673di = new C0673di(c0456Zh.w, 1, c0456Zh.x);
        this.u = c0673di;
        return c0673di;
    }
}
