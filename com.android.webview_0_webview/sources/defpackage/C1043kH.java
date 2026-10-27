package defpackage;

import android.media.MediaCodecList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: kH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1043kH implements Iterator {
    public int s;
    public final /* synthetic */ C1099lH t;

    public C1043kH(C1099lH c1099lH, AbstractC0932iH abstractC0932iH) {
        this.t = c1099lH;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.s < C1099lH.e(this.t);
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.s == C1099lH.e(this.t)) {
            throw new NoSuchElementException();
        }
        C1099lH c1099lH = this.t;
        int i = this.s;
        this.s = i + 1;
        return c1099lH.f() ? c1099lH.s[i] : MediaCodecList.getCodecInfoAt(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
