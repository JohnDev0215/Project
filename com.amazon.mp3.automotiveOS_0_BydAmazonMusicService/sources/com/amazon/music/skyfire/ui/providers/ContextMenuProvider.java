package com.amazon.music.skyfire.ui.providers;

import ExternalActionInterface.v1_0.TriggerExternalContextMenuMethod;
import android.content.Context;
import android.view.ContextMenu;
import android.view.MenuItem;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContextMenuProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/ContextMenuProvider;", "", "onMenuItemSelected", "", "menuItem", "Landroid/view/MenuItem;", "openMenu", "", "context", "Landroid/content/Context;", "menu", "Landroid/view/ContextMenu;", "method", "LExternalActionInterface/v1_0/TriggerExternalContextMenuMethod;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContextMenuProvider {
    boolean onMenuItemSelected(MenuItem menuItem);

    void openMenu(Context context, ContextMenu menu, TriggerExternalContextMenuMethod method);
}
