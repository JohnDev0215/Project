package p;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class fz implements gz {
    public final ContentInfo.Builder c;

    public fz(ClipData clipData, int i) {
        this.c = ez.d(clipData, i);
    }

    @Override // p.gz
    public final void a(Bundle bundle) {
        this.c.setExtras(bundle);
    }

    @Override // p.gz
    public final void b(Uri uri) {
        this.c.setLinkUri(uri);
    }

    @Override // p.gz
    public final jz build() {
        return new jz(new p01(this.c.build()));
    }

    @Override // p.gz
    public final void c(int i) {
        this.c.setFlags(i);
    }
}
