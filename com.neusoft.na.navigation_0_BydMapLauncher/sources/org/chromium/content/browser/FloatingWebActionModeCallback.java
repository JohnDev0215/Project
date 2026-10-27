package org.chromium.content.browser;

import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public class FloatingWebActionModeCallback extends ActionMode.Callback2 {
    private final WebActionModeCallback mWrappedCallback;

    public FloatingWebActionModeCallback(WebActionModeCallback webActionModeCallback) {
        this.mWrappedCallback = webActionModeCallback;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (actionMode.getType() != 1) {
            return false;
        }
        return this.mWrappedCallback.onCreateActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.mWrappedCallback.onPrepareActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.mWrappedCallback.onActionItemClicked(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode actionMode) {
        this.mWrappedCallback.onDestroyActionMode(actionMode);
    }

    @Override // android.view.ActionMode.Callback2
    public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        this.mWrappedCallback.onGetContentRect(actionMode, view, rect);
    }
}
