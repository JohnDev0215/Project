package com.android.inputmethod.keyboard.emoji;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import com.android.inputmethod.accessibility.AccessibilityUtils;
import com.android.inputmethod.accessibility.KeyboardAccessibilityDelegate;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyDetector;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardView;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
final class EmojiPageKeyboardView extends KeyboardView implements GestureDetector.OnGestureListener {
    private static final OnKeyEventListener EMPTY_LISTENER = new OnKeyEventListener() { // from class: com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView.1
        @Override // com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView.OnKeyEventListener
        public void onPressKey(final Key key) {
        }

        @Override // com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView.OnKeyEventListener
        public void onReleaseKey(final Key key) {
        }
    };
    private static final long KEY_PRESS_DELAY_TIME = 250;
    private static final long KEY_RELEASE_DELAY_TIME = 30;
    private KeyboardAccessibilityDelegate<EmojiPageKeyboardView> mAccessibilityDelegate;
    private Key mCurrentKey;
    private final GestureDetector mGestureDetector;
    private final Handler mHandler;
    private final KeyDetector mKeyDetector;
    private OnKeyEventListener mListener;
    private Runnable mPendingKeyDown;

    public interface OnKeyEventListener {
        void onPressKey(Key key);

        void onReleaseKey(Key key);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent event) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(final MotionEvent e) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(final MotionEvent e) {
    }

    public EmojiPageKeyboardView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.keyboardViewStyle);
    }

    public EmojiPageKeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        this.mListener = EMPTY_LISTENER;
        this.mKeyDetector = new KeyDetector();
        GestureDetector gestureDetector = new GestureDetector(context, this);
        this.mGestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.mHandler = new Handler();
    }

    public void setOnKeyEventListener(final OnKeyEventListener listener) {
        this.mListener = listener;
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView
    public void setKeyboard(final Keyboard keyboard) {
        super.setKeyboard(keyboard);
        this.mKeyDetector.setKeyboard(keyboard, 0.0f, 0.0f);
        if (AccessibilityUtils.getInstance().isAccessibilityEnabled()) {
            if (this.mAccessibilityDelegate == null) {
                this.mAccessibilityDelegate = new KeyboardAccessibilityDelegate<>(this, this.mKeyDetector);
            }
            this.mAccessibilityDelegate.setKeyboard(keyboard);
            return;
        }
        this.mAccessibilityDelegate = null;
    }

    @Override // android.view.View
    public boolean onHoverEvent(final MotionEvent event) {
        KeyboardAccessibilityDelegate<EmojiPageKeyboardView> keyboardAccessibilityDelegate = this.mAccessibilityDelegate;
        if (keyboardAccessibilityDelegate != null && AccessibilityUtils.getInstance().isTouchExplorationEnabled()) {
            return keyboardAccessibilityDelegate.onHoverEvent(event);
        }
        return super.onHoverEvent(event);
    }

    @Override // android.view.View
    public boolean onTouchEvent(final MotionEvent e) {
        Key key;
        if (!this.mGestureDetector.onTouchEvent(e) && (key = getKey(e)) != null && key != this.mCurrentKey) {
            releaseCurrentKey(false);
        }
        return true;
    }

    private Key getKey(final MotionEvent e) {
        int actionIndex = e.getActionIndex();
        return this.mKeyDetector.detectHitKey((int) e.getX(actionIndex), (int) e.getY(actionIndex));
    }

    void callListenerOnReleaseKey(final Key releasedKey, final boolean withKeyRegistering) {
        releasedKey.onReleased();
        invalidateKey(releasedKey);
        if (withKeyRegistering) {
            this.mListener.onReleaseKey(releasedKey);
        }
    }

    void callListenerOnPressKey(final Key pressedKey) {
        this.mPendingKeyDown = null;
        pressedKey.onPressed();
        invalidateKey(pressedKey);
        this.mListener.onPressKey(pressedKey);
    }

    public void releaseCurrentKey(final boolean withKeyRegistering) {
        this.mHandler.removeCallbacks(this.mPendingKeyDown);
        this.mPendingKeyDown = null;
        Key key = this.mCurrentKey;
        if (key == null) {
            return;
        }
        callListenerOnReleaseKey(key, withKeyRegistering);
        this.mCurrentKey = null;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(final MotionEvent e) {
        final Key key = getKey(e);
        releaseCurrentKey(false);
        this.mCurrentKey = key;
        if (key == null) {
            return false;
        }
        Runnable runnable = new Runnable() { // from class: com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView.2
            @Override // java.lang.Runnable
            public void run() {
                EmojiPageKeyboardView.this.callListenerOnPressKey(key);
            }
        };
        this.mPendingKeyDown = runnable;
        this.mHandler.postDelayed(runnable, KEY_PRESS_DELAY_TIME);
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(final MotionEvent e) {
        final Key key = getKey(e);
        Runnable runnable = this.mPendingKeyDown;
        Key key2 = this.mCurrentKey;
        releaseCurrentKey(false);
        if (key == null) {
            return false;
        }
        if (key == key2 && runnable != null) {
            runnable.run();
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.inputmethod.keyboard.emoji.EmojiPageKeyboardView.3
                @Override // java.lang.Runnable
                public void run() {
                    EmojiPageKeyboardView.this.callListenerOnReleaseKey(key, true);
                }
            }, KEY_RELEASE_DELAY_TIME);
        } else {
            callListenerOnReleaseKey(key, true);
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
        releaseCurrentKey(false);
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
        releaseCurrentKey(false);
        return false;
    }
}
