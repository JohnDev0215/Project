package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: s1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1468s1 implements Iterator, Map.Entry {
    public int s;
    public final /* synthetic */ AbstractC1578u1 v;
    public boolean u = false;
    public int t = -1;

    public C1468s1(AbstractC1578u1 abstractC1578u1) {
        this.v = abstractC1578u1;
        this.s = abstractC1578u1.d() - 1;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (!this.u) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return AbstractC1139m1.c(entry.getKey(), this.v.b(this.t, 0)) && AbstractC1139m1.c(entry.getValue(), this.v.b(this.t, 1));
    }

    @Override // java.util.Map.Entry
    public Object getKey() {
        if (this.u) {
            return this.v.b(this.t, 0);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        if (this.u) {
            return this.v.b(this.t, 1);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.t < this.s;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        if (!this.u) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        Object objB = this.v.b(this.t, 0);
        Object objB2 = this.v.b(this.t, 1);
        return (objB == null ? 0 : objB.hashCode()) ^ (objB2 != null ? objB2.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.t++;
        this.u = true;
        return this;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.u) {
            throw new IllegalStateException();
        }
        this.v.h(this.t);
        this.t--;
        this.s--;
        this.u = false;
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        if (this.u) {
            return this.v.i(this.t, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
