package defpackage;

import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: renamed from: f1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0748f1 implements Iterator {
    public AbstractC0692e1 s;
    public boolean t = true;
    public final /* synthetic */ C0860h1 u;

    public C0748f1(C0860h1 c0860h1) {
        this.u = c0860h1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.t) {
            Objects.requireNonNull(this.u);
        }
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.t) {
            this.t = false;
            Objects.requireNonNull(this.u);
            this.s = null;
        } else {
            this.s = null;
        }
        return null;
    }
}
