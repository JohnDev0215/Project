package org.chromium.content.browser;

import android.view.ActionMode;
import android.view.View;
import android.view.ViewConfiguration;
import org.chromium.base.Log;

/* JADX INFO: loaded from: classes3.dex */
public class WebActionMode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int SHOW_DELAY_MS = 300;
    private static final String TAG = "cr.WebActionMode";
    protected final ActionMode mActionMode;
    private boolean mHidden;
    private boolean mPendingInvalidateContentRect;
    private final Runnable mRepeatingHideRunnable = new Runnable() { // from class: org.chromium.content.browser.WebActionMode.1
        static final /* synthetic */ boolean $assertionsDisabled = false;

        @Override // java.lang.Runnable
        public void run() {
            long defaultHideDuration = WebActionMode.this.getDefaultHideDuration();
            WebActionMode.this.mView.postDelayed(WebActionMode.this.mRepeatingHideRunnable, defaultHideDuration - 1);
            WebActionMode.this.hideTemporarily(defaultHideDuration);
        }
    };
    private final View mView;

    public WebActionMode(ActionMode actionMode, View view) {
        this.mActionMode = actionMode;
        this.mView = view;
    }

    public void finish() {
        this.mActionMode.finish();
    }

    public void invalidate() {
        if (this.mHidden) {
            this.mHidden = false;
            this.mView.removeCallbacks(this.mRepeatingHideRunnable);
            this.mPendingInvalidateContentRect = false;
        }
        try {
            this.mActionMode.invalidate();
        } catch (NullPointerException e) {
            Log.w(TAG, "Ignoring NPE from ActionMode.invalidate() as workaround for L", e);
        }
    }

    public void invalidateContentRect() {
        if (this.mHidden) {
            this.mPendingInvalidateContentRect = true;
        } else {
            this.mPendingInvalidateContentRect = false;
            this.mActionMode.invalidateContentRect();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        this.mActionMode.onWindowFocusChanged(z);
    }

    public void hide(boolean z) {
        if (canHide() && this.mHidden != z) {
            this.mHidden = z;
            if (z) {
                this.mRepeatingHideRunnable.run();
                return;
            }
            this.mHidden = false;
            this.mView.removeCallbacks(this.mRepeatingHideRunnable);
            hideTemporarily(300L);
            if (this.mPendingInvalidateContentRect) {
                this.mPendingInvalidateContentRect = false;
                invalidateContentRect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTemporarily(long j) {
        this.mActionMode.hide(j);
    }

    private boolean canHide() {
        return this.mActionMode.getType() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getDefaultHideDuration() {
        return ViewConfiguration.getDefaultActionModeHideDuration();
    }
}
