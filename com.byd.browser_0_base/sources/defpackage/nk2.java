package defpackage;

import android.net.Uri;
import defpackage.jk2;
import java.io.File;
import org.chromium.base.ContentUriUtils;

/* JADX INFO: compiled from: ShareImageFileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nk2 extends i9<Uri> {
    public final /* synthetic */ String h;
    public final /* synthetic */ jk2.c i;

    public nk2(jk2.c cVar, String str) {
        this.i = cVar;
        this.h = str;
    }

    @Override // defpackage.i9
    public final Uri b() {
        return ContentUriUtils.b(new File(this.h));
    }

    @Override // defpackage.i9
    public final void l(Uri uri) {
        this.i.e.onResult(uri);
    }
}
