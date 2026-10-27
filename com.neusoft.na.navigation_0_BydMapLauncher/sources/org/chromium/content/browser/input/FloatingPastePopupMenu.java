package org.chromium.content.browser.input;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.View;
import org.chromium.content.browser.FloatingWebActionModeCallback;
import org.chromium.content.browser.WebActionModeCallback;
import org.chromium.content.browser.input.PastePopupMenu;

/* JADX INFO: loaded from: classes3.dex */
public class FloatingPastePopupMenu implements PastePopupMenu {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CONTENT_RECT_OFFSET_DIP = 15;
    private static final int SLOP_LENGTH_DIP = 10;
    private WebActionModeCallback.ActionHandler mActionHandler;
    private ActionMode mActionMode;
    private final int mContentRectOffset;
    private final Context mContext;
    private final PastePopupMenu.PastePopupMenuDelegate mDelegate;
    private LegacyPastePopupMenu mFallbackPastePopupMenu;
    private final View mParent;
    private int mRawPositionX;
    private int mRawPositionY;
    private final int mSlopLengthSquared;

    public FloatingPastePopupMenu(Context context, View view, PastePopupMenu.PastePopupMenuDelegate pastePopupMenuDelegate) {
        this.mParent = view;
        this.mDelegate = pastePopupMenuDelegate;
        this.mContext = context;
        this.mContentRectOffset = (int) TypedValue.applyDimension(1, 15.0f, context.getResources().getDisplayMetrics());
        int iApplyDimension = (int) TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics());
        this.mSlopLengthSquared = iApplyDimension * iApplyDimension;
    }

    @Override // org.chromium.content.browser.input.PastePopupMenu
    public void show(int i, int i2) {
        LegacyPastePopupMenu legacyPastePopupMenu = this.mFallbackPastePopupMenu;
        if (legacyPastePopupMenu != null) {
            legacyPastePopupMenu.show(i, i2);
            return;
        }
        if (isShowing()) {
            int i3 = this.mRawPositionX - i;
            int i4 = this.mRawPositionY - i2;
            if ((i3 * i3) + (i4 * i4) < this.mSlopLengthSquared) {
                return;
            }
        }
        this.mRawPositionX = i;
        this.mRawPositionY = i2;
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.invalidateContentRect();
        } else {
            ensureActionModeOrFallback();
        }
    }

    @Override // org.chromium.content.browser.input.PastePopupMenu
    public void hide() {
        LegacyPastePopupMenu legacyPastePopupMenu = this.mFallbackPastePopupMenu;
        if (legacyPastePopupMenu != null) {
            legacyPastePopupMenu.hide();
            return;
        }
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
            this.mActionMode = null;
        }
    }

    @Override // org.chromium.content.browser.input.PastePopupMenu
    public boolean isShowing() {
        LegacyPastePopupMenu legacyPastePopupMenu = this.mFallbackPastePopupMenu;
        return legacyPastePopupMenu != null ? legacyPastePopupMenu.isShowing() : this.mActionMode != null;
    }

    private void ensureActionModeOrFallback() {
        if (this.mActionMode == null && this.mFallbackPastePopupMenu == null) {
            ActionMode actionModeStartActionMode = this.mParent.startActionMode(new FloatingWebActionModeCallback(new WebActionModeCallback(this.mParent.getContext(), getActionHandler())), 1);
            if (actionModeStartActionMode != null) {
                this.mActionMode = actionModeStartActionMode;
                return;
            }
            LegacyPastePopupMenu legacyPastePopupMenu = new LegacyPastePopupMenu(this.mContext, this.mParent, this.mDelegate);
            this.mFallbackPastePopupMenu = legacyPastePopupMenu;
            legacyPastePopupMenu.show(this.mRawPositionX, this.mRawPositionY);
        }
    }

    private WebActionModeCallback.ActionHandler getActionHandler() {
        WebActionModeCallback.ActionHandler actionHandler = this.mActionHandler;
        if (actionHandler != null) {
            return actionHandler;
        }
        WebActionModeCallback.ActionHandler actionHandler2 = new WebActionModeCallback.ActionHandler() { // from class: org.chromium.content.browser.input.FloatingPastePopupMenu.1
            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void copy() {
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void cut() {
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public boolean isIncognito() {
                return false;
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public boolean isInsertion() {
                return true;
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public boolean isSelectActionModeAllowed(int i) {
                return false;
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public boolean isSelectionEditable() {
                return true;
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public boolean isSelectionPassword() {
                return false;
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void processText(Intent intent) {
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void search() {
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void selectAll() {
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void share() {
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void paste() {
                FloatingPastePopupMenu.this.mDelegate.paste();
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void onDestroyActionMode() {
                FloatingPastePopupMenu.this.mActionMode = null;
                FloatingPastePopupMenu.this.mDelegate.onDismiss();
            }

            @Override // org.chromium.content.browser.WebActionModeCallback.ActionHandler
            public void onGetContentRect(Rect rect) {
                rect.set(FloatingPastePopupMenu.this.mRawPositionX - FloatingPastePopupMenu.this.mContentRectOffset, FloatingPastePopupMenu.this.mRawPositionY - FloatingPastePopupMenu.this.mContentRectOffset, FloatingPastePopupMenu.this.mRawPositionX + FloatingPastePopupMenu.this.mContentRectOffset, FloatingPastePopupMenu.this.mRawPositionY + FloatingPastePopupMenu.this.mContentRectOffset);
            }
        };
        this.mActionHandler = actionHandler2;
        return actionHandler2;
    }
}
