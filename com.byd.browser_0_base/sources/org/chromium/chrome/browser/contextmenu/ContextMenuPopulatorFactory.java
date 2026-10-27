package org.chromium.chrome.browser.contextmenu;

import android.content.Context;
import defpackage.uz;
import defpackage.vz;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;

/* JADX INFO: loaded from: classes.dex */
public interface ContextMenuPopulatorFactory {
    vz a(Context context, ContextMenuParams contextMenuParams, uz uzVar);

    void onDestroy();
}
