package org.chromium.chrome.browser.download;

import android.graphics.drawable.Drawable;
import defpackage.l5;
import defpackage.p5;
import org.chromium.chrome.browser.download.e;

/* JADX INFO: compiled from: DownloadMessageUiControllerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends l5.a {
    public final /* synthetic */ e.b b;
    public final /* synthetic */ p5 c;
    public final /* synthetic */ e d;

    public d(e eVar, e.b bVar, p5 p5Var) {
        this.d = eVar;
        this.b = bVar;
        this.c = p5Var;
    }

    @Override // l5.a
    public final void b(Drawable drawable) {
        e.b bVar = this.d.n;
        if (bVar == null || bVar.e != this.b.e) {
            return;
        }
        this.c.start();
    }
}
