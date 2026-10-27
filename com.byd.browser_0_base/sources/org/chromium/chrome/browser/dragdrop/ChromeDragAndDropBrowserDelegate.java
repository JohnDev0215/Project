package org.chromium.chrome.browser.dragdrop;

import J.N;
import android.content.Context;
import android.os.Bundle;
import defpackage.bq;
import defpackage.nd0;
import defpackage.sk;
import defpackage.xz;
import org.chromium.ui.dragdrop.DropDataProviderImpl;

/* JADX INFO: loaded from: classes.dex */
public class ChromeDragAndDropBrowserDelegate implements nd0 {
    public final Context a;
    public final boolean b = N.MIXz$64w("TouchDragAndContextMenu", "DropInChrome", false);
    public final boolean c;

    public ChromeDragAndDropBrowserDelegate(Context context) {
        this.a = context;
        sk skVar = bq.a;
        this.c = N.M09VlOh_("AnimatedImageDragShadow");
        int iM0MXVnOj = N.M0MXVnOj("TouchDragAndContextMenu", "ClearCacheDelayedMs", 60000);
        Bundle bundle = new Bundle();
        bundle.putInt("clearCacheDelayedMs", iM0MXVnOj);
        try {
            xz.a.getContentResolver().call(DropDataProviderImpl.n, "setClearCachedDataIntervalMs", "", bundle);
        } catch (IllegalArgumentException | NullPointerException unused) {
        }
    }
}
