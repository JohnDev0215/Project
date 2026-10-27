package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import org.chromium.chrome.browser.feed.c;

/* JADX INFO: compiled from: NewTabPageUma.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tm1 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ long e;
    public final /* synthetic */ View f;

    public tm1(long j, c.a aVar) {
        this.e = j;
        this.f = aVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        oj2.b0((System.nanoTime() - this.e) / 1000000, "NewTabPage.TimeToFirstDraw2");
        this.f.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }
}
