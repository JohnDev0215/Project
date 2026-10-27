package defpackage;

import org.chromium.base.Callback;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: LinkToTextHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v71 implements Callback<GURL> {
    public final /* synthetic */ Callback e;

    public v71(Callback callback) {
        this.e = callback;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(GURL gurl) {
        this.e.onResult(gurl.j());
    }
}
