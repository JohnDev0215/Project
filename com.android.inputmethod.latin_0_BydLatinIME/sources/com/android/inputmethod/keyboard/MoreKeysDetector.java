package com.android.inputmethod.keyboard;

/* JADX INFO: loaded from: classes2.dex */
public final class MoreKeysDetector extends KeyDetector {
    private final int mSlideAllowanceSquare;
    private final int mSlideAllowanceSquareTop;

    @Override // com.android.inputmethod.keyboard.KeyDetector
    public boolean alwaysAllowsKeySelectionByDraggingFinger() {
        return true;
    }

    public MoreKeysDetector(float slideAllowance) {
        int i = (int) (slideAllowance * slideAllowance);
        this.mSlideAllowanceSquare = i;
        this.mSlideAllowanceSquareTop = i * 2;
    }

    @Override // com.android.inputmethod.keyboard.KeyDetector
    public Key detectHitKey(final int x, final int y) {
        Keyboard keyboard = getKeyboard();
        Key key = null;
        if (keyboard == null) {
            return null;
        }
        int touchX = getTouchX(x);
        int touchY = getTouchY(y);
        int i = y < 0 ? this.mSlideAllowanceSquareTop : this.mSlideAllowanceSquare;
        for (Key key2 : keyboard.getSortedKeys()) {
            int iSquaredDistanceToEdge = key2.squaredDistanceToEdge(touchX, touchY);
            if (iSquaredDistanceToEdge < i) {
                key = key2;
                i = iSquaredDistanceToEdge;
            }
        }
        return key;
    }
}
