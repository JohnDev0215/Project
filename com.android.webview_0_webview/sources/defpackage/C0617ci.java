package defpackage;

import java.util.Map;

/* JADX INFO: renamed from: ci, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0617ci extends AbstractC0438Yh {
    public final transient AbstractC0402Wh u;
    public final transient Object[] v;
    public final transient int w;

    public C0617ci(AbstractC0402Wh abstractC0402Wh, Object[] objArr, int i) {
        this.u = abstractC0402Wh;
        this.v = objArr;
        this.w = i;
    }

    @Override // defpackage.AbstractC0330Sh, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.u.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC0330Sh
    public final int e(Object[] objArr, int i) {
        return i().e(objArr, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final AbstractC0840gi iterator() {
        return (AbstractC0840gi) i().iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.w;
    }
}
