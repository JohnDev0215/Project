package org.chromium.chrome.browser.share.long_screenshots.bitmap_generation;

import J.N;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Size;
import defpackage.na1;
import defpackage.pa1;
import defpackage.re2;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;

/* JADX INFO: compiled from: EntryManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public final ArrayList a;
    public final ArrayList b;
    public org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a c;
    public final org.chromium.base.b<a> d;
    public int e;
    public final re2 f;

    /* JADX INFO: compiled from: EntryManager.java */
    public interface a {
        void a(int i);

        void b(Size size, Point point);
    }

    public c(Context context, Tab tab, boolean z) {
        re2 re2Var = new re2(context, tab);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.d = new org.chromium.base.b<>();
        this.f = re2Var;
        org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a aVar = new org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a(tab, re2Var, new b(this));
        this.c = aVar;
        if (aVar.b == null) {
            aVar.b = (LongScreenshotsTabService) N.Mhh7FcTw();
        }
        LongScreenshotsTabService longScreenshotsTabService = aVar.b;
        longScreenshotsTabService.a = aVar;
        Rect rect = re2Var.b;
        if (longScreenshotsTabService.b == 0) {
            longScreenshotsTabService.processCaptureTabStatus(9);
        } else if (tab.c() == null) {
            longScreenshotsTabService.processCaptureTabStatus(5);
        } else {
            N.M61U97rf(longScreenshotsTabService.b, tab.getId(), tab.getUrl(), tab.c(), rect.left, rect.top, rect.width(), rect.height(), z);
        }
        aVar.g = 0.0f;
        d(5);
    }

    public final void a(a aVar) {
        this.d.a(aVar);
        aVar.a(this.e);
        if (this.e == 4) {
            na1 na1Var = this.c.a;
            aVar.b(na1Var == null ? null : na1Var.c, na1Var != null ? na1Var.d : null);
        }
    }

    public final void b() {
        org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a aVar = this.c;
        if (aVar != null) {
            na1 na1Var = aVar.a;
            if (na1Var != null) {
                PlayerCompositorDelegateImpl playerCompositorDelegateImpl = na1Var.a;
                if (playerCompositorDelegateImpl != null) {
                    long j = playerCompositorDelegateImpl.b;
                    if (j != 0) {
                        N.MIRVkfIx(j);
                        playerCompositorDelegateImpl.b = 0L;
                    }
                    na1Var.a = null;
                }
                aVar.a = null;
            }
            LongScreenshotsTabService longScreenshotsTabService = aVar.b;
            if (longScreenshotsTabService != null) {
                long j2 = longScreenshotsTabService.b;
                if (j2 != 0) {
                    N.MDdcn7jl(j2);
                }
            }
            this.c = null;
        }
    }

    public final void c(pa1 pa1Var, boolean z) {
        int i = this.e;
        if (i == 4) {
            pa1Var.a();
        } else if (i == 5) {
            this.b.add(pa1Var);
        } else {
            pa1Var.b(i);
        }
        if (z) {
            return;
        }
        this.a.add(pa1Var);
    }

    public final void d(int i) {
        this.e = i;
        ArrayList arrayList = this.b;
        if (i == 4) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((pa1) it.next()).a();
            }
            arrayList.clear();
        } else {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((pa1) it2.next()).b(i);
            }
        }
        Iterator<a> it3 = this.d.iterator();
        while (true) {
            b.a aVar = (b.a) it3;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(this.e);
            }
        }
    }
}
