package org.chromium.chrome.browser.share.long_screenshots.bitmap_generation;

import J.N;
import defpackage.i83;
import defpackage.na1;
import defpackage.on0;
import defpackage.re2;
import org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.LongScreenshotsTabService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: BitmapGenerator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements LongScreenshotsTabService.a {
    public na1 a;
    public LongScreenshotsTabService b;
    public final Tab c;
    public final InterfaceC0111a d;
    public final i83 e = new i83(16);
    public final re2 f;
    public float g;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BitmapGenerator.java */
    public interface InterfaceC0111a {
    }

    public a(Tab tab, re2 re2Var, b bVar) {
        this.c = tab;
        this.f = re2Var;
        this.d = bVar;
    }

    public final void a(int i, long j) {
        if (i == 1 && this.a == null) {
            GURL gurlEmptyGURL = GURL.emptyGURL();
            LongScreenshotsTabService longScreenshotsTabService = this.b;
            on0 on0Var = new on0(10, this);
            this.e.getClass();
            this.a = new na1(gurlEmptyGURL, longScreenshotsTabService, j, on0Var);
            return;
        }
        this.b.getClass();
        if (j != 0) {
            N.Mj0DaLs7(j);
        }
        c cVar = ((b) this.d).a;
        if (i == 7) {
            cVar.d(1);
        } else if (i != 1) {
            cVar.d(2);
        }
    }
}
