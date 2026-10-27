package defpackage;

import java.util.Iterator;

/* JADX INFO: renamed from: Xk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0423Xk implements Iterator {
    @Override // java.util.Iterator
    public Object next() {
        return Byte.valueOf(((C0405Wk) this).a());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
