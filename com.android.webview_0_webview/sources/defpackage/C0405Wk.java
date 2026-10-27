package defpackage;

import java.util.NoSuchElementException;

/* JADX INFO: renamed from: Wk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0405Wk extends AbstractC0423Xk {
    public int s = 0;
    public final int t;
    public final /* synthetic */ AbstractC0731el u;

    public C0405Wk(AbstractC0731el abstractC0731el) {
        this.u = abstractC0731el;
        this.t = this.u.size();
    }

    public byte a() {
        int i = this.s;
        if (i >= this.t) {
            throw new NoSuchElementException();
        }
        this.s = i + 1;
        return this.u.k(i);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.s < this.t;
    }
}
