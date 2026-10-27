package com.android.inputmethod.keyboard.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.latin.common.CoordinateUtils;
import com.android.inputmethod.latin.utils.ViewLayoutUtils;
import java.util.ArrayDeque;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyPreviewChoreographer {
    private final KeyPreviewDrawParams mParams;
    private final ArrayDeque<KeyPreviewView> mFreeKeyPreviewViews = new ArrayDeque<>();
    private final HashMap<Key, KeyPreviewView> mShowingKeyPreviewViews = new HashMap<>();

    public KeyPreviewChoreographer(final KeyPreviewDrawParams params) {
        this.mParams = params;
    }

    public KeyPreviewView getKeyPreviewView(final Key key, final ViewGroup placerView) {
        KeyPreviewView keyPreviewViewRemove = this.mShowingKeyPreviewViews.remove(key);
        if (keyPreviewViewRemove != null) {
            return keyPreviewViewRemove;
        }
        KeyPreviewView keyPreviewViewPoll = this.mFreeKeyPreviewViews.poll();
        if (keyPreviewViewPoll != null) {
            return keyPreviewViewPoll;
        }
        KeyPreviewView keyPreviewView = new KeyPreviewView(placerView.getContext(), null);
        keyPreviewView.setBackgroundResource(this.mParams.mPreviewBackgroundResId);
        placerView.addView(keyPreviewView, ViewLayoutUtils.newLayoutParam(placerView, 0, 0));
        return keyPreviewView;
    }

    public boolean isShowingKeyPreview(final Key key) {
        return this.mShowingKeyPreviewViews.containsKey(key);
    }

    public void dismissKeyPreview(final Key key, final boolean withAnimation) {
        KeyPreviewView keyPreviewView;
        if (key == null || (keyPreviewView = this.mShowingKeyPreviewViews.get(key)) == null) {
            return;
        }
        Object tag = keyPreviewView.getTag();
        if (withAnimation && (tag instanceof KeyPreviewAnimators)) {
            ((KeyPreviewAnimators) tag).startDismiss();
            return;
        }
        this.mShowingKeyPreviewViews.remove(key);
        if (tag instanceof Animator) {
            ((Animator) tag).cancel();
        }
        keyPreviewView.setTag(null);
        keyPreviewView.setVisibility(4);
        this.mFreeKeyPreviewViews.add(keyPreviewView);
    }

    public void placeAndShowKeyPreview(final Key key, final KeyboardIconsSet iconsSet, final KeyDrawParams drawParams, final int keyboardViewWidth, final int[] keyboardOrigin, final ViewGroup placerView, final boolean withAnimation) {
        KeyPreviewView keyPreviewView = getKeyPreviewView(key, placerView);
        placeKeyPreview(key, keyPreviewView, iconsSet, drawParams, keyboardViewWidth, keyboardOrigin);
        showKeyPreview(key, keyPreviewView, withAnimation);
    }

    private void placeKeyPreview(final Key key, final KeyPreviewView keyPreviewView, final KeyboardIconsSet iconsSet, final KeyDrawParams drawParams, final int keyboardViewWidth, final int[] originCoords) {
        keyPreviewView.setPreviewVisual(key, iconsSet, drawParams);
        keyPreviewView.measure(-2, -2);
        this.mParams.setGeometry(keyPreviewView);
        int measuredWidth = keyPreviewView.getMeasuredWidth();
        int i = this.mParams.mPreviewHeight;
        int i2 = 2;
        int drawX = (key.getDrawX() - ((measuredWidth - key.getDrawWidth()) / 2)) + CoordinateUtils.x(originCoords);
        if (drawX < 0) {
            i2 = 1;
            drawX = 0;
        } else {
            int i3 = keyboardViewWidth - measuredWidth;
            if (drawX > i3) {
                drawX = i3;
            } else {
                i2 = 0;
            }
        }
        keyPreviewView.setPreviewBackground(key.getMoreKeys() != null, i2);
        ViewLayoutUtils.placeViewAt(keyPreviewView, drawX, (key.getY() - i) + this.mParams.mPreviewOffset + CoordinateUtils.y(originCoords), measuredWidth, i);
        keyPreviewView.setPivotX(measuredWidth / 2.0f);
        keyPreviewView.setPivotY(i);
    }

    void showKeyPreview(final Key key, final KeyPreviewView keyPreviewView, final boolean withAnimation) {
        if (!withAnimation) {
            keyPreviewView.setVisibility(0);
            this.mShowingKeyPreviewViews.put(key, keyPreviewView);
        } else {
            KeyPreviewAnimators keyPreviewAnimators = new KeyPreviewAnimators(createShowUpAnimator(key, keyPreviewView), createDismissAnimator(key, keyPreviewView));
            keyPreviewView.setTag(keyPreviewAnimators);
            keyPreviewAnimators.startShowUp();
        }
    }

    public Animator createShowUpAnimator(final Key key, final KeyPreviewView keyPreviewView) {
        Animator animatorCreateShowUpAnimator = this.mParams.createShowUpAnimator(keyPreviewView);
        animatorCreateShowUpAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.inputmethod.keyboard.internal.KeyPreviewChoreographer.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(final Animator animator) {
                KeyPreviewChoreographer.this.showKeyPreview(key, keyPreviewView, false);
            }
        });
        return animatorCreateShowUpAnimator;
    }

    private Animator createDismissAnimator(final Key key, final KeyPreviewView keyPreviewView) {
        Animator animatorCreateDismissAnimator = this.mParams.createDismissAnimator(keyPreviewView);
        animatorCreateDismissAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.inputmethod.keyboard.internal.KeyPreviewChoreographer.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(final Animator animator) {
                KeyPreviewChoreographer.this.dismissKeyPreview(key, false);
            }
        });
        return animatorCreateDismissAnimator;
    }

    private static class KeyPreviewAnimators extends AnimatorListenerAdapter {
        private final Animator mDismissAnimator;
        private final Animator mShowUpAnimator;

        public KeyPreviewAnimators(final Animator showUpAnimator, final Animator dismissAnimator) {
            this.mShowUpAnimator = showUpAnimator;
            this.mDismissAnimator = dismissAnimator;
        }

        public void startShowUp() {
            this.mShowUpAnimator.start();
        }

        public void startDismiss() {
            if (this.mShowUpAnimator.isRunning()) {
                this.mShowUpAnimator.addListener(this);
            } else {
                this.mDismissAnimator.start();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(final Animator animator) {
            this.mDismissAnimator.start();
        }
    }
}
