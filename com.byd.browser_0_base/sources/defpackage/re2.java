package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Size;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: compiled from: ScreenshotBoundsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class re2 {
    public final Tab a;
    public Rect b;
    public Size c;
    public Point d;
    public int e;

    public re2(Context context, Tab tab) {
        this.a = tab;
        this.e = (int) Math.floor(ya0.a(context).c.y / ((WebContentsImpl) tab.c()).l.h);
        this.b = new Rect(0, -1, 0, (this.e * 10) - 1);
    }
}
