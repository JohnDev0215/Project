package com.amazon.mp3.card.prime;

import android.view.ContextMenu;
import android.view.MenuItem;

/* JADX INFO: loaded from: classes3.dex */
public interface ContextMenuHandler {
    boolean onContextMenuItemSelected(final MenuItem menuItem, final String sourceId);

    void onCreateContextMenu(final ContextMenu menu, final String sourceId);
}
