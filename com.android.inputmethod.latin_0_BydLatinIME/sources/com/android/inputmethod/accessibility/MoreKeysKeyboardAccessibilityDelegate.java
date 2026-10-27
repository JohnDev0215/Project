package com.android.inputmethod.accessibility;

import android.graphics.Rect;
import android.view.MotionEvent;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyDetector;
import com.android.inputmethod.keyboard.MoreKeysKeyboardView;
import com.android.inputmethod.keyboard.PointerTracker;

/* JADX INFO: loaded from: classes2.dex */
public class MoreKeysKeyboardAccessibilityDelegate extends KeyboardAccessibilityDelegate<MoreKeysKeyboardView> {
    private static final int CLOSING_INSET_IN_PIXEL = 1;
    private static final String TAG = "MoreKeysKeyboardAccessibilityDelegate";
    private int mCloseAnnounceResId;
    private final Rect mMoreKeysKeyboardValidBounds;
    private int mOpenAnnounceResId;

    public MoreKeysKeyboardAccessibilityDelegate(final MoreKeysKeyboardView moreKeysKeyboardView, final KeyDetector keyDetector) {
        super(moreKeysKeyboardView, keyDetector);
        this.mMoreKeysKeyboardValidBounds = new Rect();
    }

    public void setOpenAnnounce(final int resId) {
        this.mOpenAnnounceResId = resId;
    }

    public void setCloseAnnounce(final int resId) {
        this.mCloseAnnounceResId = resId;
    }

    public void onShowMoreKeysKeyboard() {
        sendWindowStateChanged(this.mOpenAnnounceResId);
    }

    public void onDismissMoreKeysKeyboard() {
        sendWindowStateChanged(this.mCloseAnnounceResId);
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate
    protected void onHoverEnter(final MotionEvent event) {
        super.onHoverEnter(event);
        int actionIndex = event.getActionIndex();
        ((MoreKeysKeyboardView) this.mKeyboardView).onDownEvent((int) event.getX(actionIndex), (int) event.getY(actionIndex), event.getPointerId(actionIndex), event.getEventTime());
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate
    protected void onHoverMove(final MotionEvent event) {
        super.onHoverMove(event);
        int actionIndex = event.getActionIndex();
        ((MoreKeysKeyboardView) this.mKeyboardView).onMoveEvent((int) event.getX(actionIndex), (int) event.getY(actionIndex), event.getPointerId(actionIndex), event.getEventTime());
    }

    @Override // com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate
    protected void onHoverExit(final MotionEvent event) {
        Key lastHoverKey = getLastHoverKey();
        if (lastHoverKey != null) {
            super.onHoverExitFrom(lastHoverKey);
        }
        setLastHoverKey(null);
        int actionIndex = event.getActionIndex();
        int x = (int) event.getX(actionIndex);
        int y = (int) event.getY(actionIndex);
        int pointerId = event.getPointerId(actionIndex);
        long eventTime = event.getEventTime();
        this.mMoreKeysKeyboardValidBounds.set(0, 0, ((MoreKeysKeyboardView) this.mKeyboardView).getWidth(), ((MoreKeysKeyboardView) this.mKeyboardView).getHeight());
        this.mMoreKeysKeyboardValidBounds.inset(1, 1);
        if (this.mMoreKeysKeyboardValidBounds.contains(x, y)) {
            ((MoreKeysKeyboardView) this.mKeyboardView).onUpEvent(x, y, pointerId, eventTime);
            PointerTracker.dismissAllMoreKeysPanels();
        } else {
            PointerTracker.dismissAllMoreKeysPanels();
        }
    }
}
