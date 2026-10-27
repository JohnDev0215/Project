package com.android.inputmethod.keyboard;

import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class KeyDetector {
    private int mCorrectionX;
    private int mCorrectionY;
    private final int mKeyHysteresisDistanceForSlidingModifierSquared;
    private final int mKeyHysteresisDistanceSquared;
    private Keyboard mKeyboard;

    public boolean alwaysAllowsKeySelectionByDraggingFinger() {
        return false;
    }

    public KeyDetector() {
        this(0.0f, 0.0f);
    }

    public KeyDetector(final float keyHysteresisDistance, final float keyHysteresisDistanceForSlidingModifier) {
        this.mKeyHysteresisDistanceSquared = (int) (keyHysteresisDistance * keyHysteresisDistance);
        this.mKeyHysteresisDistanceForSlidingModifierSquared = (int) (keyHysteresisDistanceForSlidingModifier * keyHysteresisDistanceForSlidingModifier);
    }

    public void setKeyboard(final Keyboard keyboard, final float correctionX, final float correctionY) {
        Objects.requireNonNull(keyboard);
        this.mCorrectionX = (int) correctionX;
        this.mCorrectionY = (int) correctionY;
        this.mKeyboard = keyboard;
    }

    public int getKeyHysteresisDistanceSquared(final boolean isSlidingFromModifier) {
        return isSlidingFromModifier ? this.mKeyHysteresisDistanceForSlidingModifierSquared : this.mKeyHysteresisDistanceSquared;
    }

    public int getTouchX(final int x) {
        return x + this.mCorrectionX;
    }

    public int getTouchY(final int y) {
        return y + this.mCorrectionY;
    }

    public Keyboard getKeyboard() {
        return this.mKeyboard;
    }

    public Key detectHitKey(final int x, final int y) {
        int iSquaredDistanceToEdge;
        Key key = null;
        if (this.mKeyboard == null) {
            return null;
        }
        int touchX = getTouchX(x);
        int touchY = getTouchY(y);
        int i = Integer.MAX_VALUE;
        for (Key key2 : this.mKeyboard.getNearestKeys(touchX, touchY)) {
            if (key2.isOnKey(touchX, touchY) && (iSquaredDistanceToEdge = key2.squaredDistanceToEdge(touchX, touchY)) <= i && (key == null || iSquaredDistanceToEdge < i || key2.getCode() > key.getCode())) {
                key = key2;
                i = iSquaredDistanceToEdge;
            }
        }
        return key;
    }
}
