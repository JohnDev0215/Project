package com.android.inputmethod.latin;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.FrameLayout;
import com.android.inputmethod.accessibility.AccessibilityUtils;
import com.android.inputmethod.keyboard.MainKeyboardView;
import com.android.inputmethod.latin.suggestions.SuggestionStripView;

/* JADX INFO: loaded from: classes2.dex */
public final class InputView extends FrameLayout {
    private MotionEventForwarder<?, ?> mActiveForwarder;
    private final Rect mInputViewRect;
    private KeyboardTopPaddingForwarder mKeyboardTopPaddingForwarder;
    private MainKeyboardView mMainKeyboardView;
    private MoreSuggestionsViewCanceler mMoreSuggestionsViewCanceler;

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public InputView(final Context context, final AttributeSet attrs) {
        super(context, attrs, 0);
        this.mInputViewRect = new Rect();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        SuggestionStripView suggestionStripView = (SuggestionStripView) findViewById(R.id.suggestion_strip_view);
        this.mMainKeyboardView = (MainKeyboardView) findViewById(R.id.keyboard_view);
        this.mKeyboardTopPaddingForwarder = new KeyboardTopPaddingForwarder(this.mMainKeyboardView, suggestionStripView);
        this.mMoreSuggestionsViewCanceler = new MoreSuggestionsViewCanceler(this.mMainKeyboardView, suggestionStripView);
    }

    public void setKeyboardTopPadding(final int keyboardTopPadding) {
        this.mKeyboardTopPaddingForwarder.setKeyboardTopPadding(keyboardTopPadding);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected boolean dispatchHoverEvent(final MotionEvent event) {
        if (AccessibilityUtils.getInstance().isTouchExplorationEnabled() && this.mMainKeyboardView.isShowingMoreKeysPanel()) {
            return true;
        }
        return super.dispatchHoverEvent(event);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(final MotionEvent me) {
        Rect rect = this.mInputViewRect;
        getGlobalVisibleRect(rect);
        int actionIndex = me.getActionIndex();
        int x = ((int) me.getX(actionIndex)) + rect.left;
        int y = ((int) me.getY(actionIndex)) + rect.top;
        if (this.mKeyboardTopPaddingForwarder.onInterceptTouchEvent(x, y, me)) {
            this.mActiveForwarder = this.mKeyboardTopPaddingForwarder;
            return true;
        }
        if (this.mMoreSuggestionsViewCanceler.onInterceptTouchEvent(x, y, me)) {
            this.mActiveForwarder = this.mMoreSuggestionsViewCanceler;
            return true;
        }
        this.mActiveForwarder = null;
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(final MotionEvent me) {
        if (this.mActiveForwarder == null) {
            return super.onTouchEvent(me);
        }
        Rect rect = this.mInputViewRect;
        getGlobalVisibleRect(rect);
        int actionIndex = me.getActionIndex();
        return this.mActiveForwarder.onTouchEvent(((int) me.getX(actionIndex)) + rect.left, ((int) me.getY(actionIndex)) + rect.top, me);
    }

    private static abstract class MotionEventForwarder<SenderView extends View, ReceiverView extends View> {
        protected final ReceiverView mReceiverView;
        protected final SenderView mSenderView;
        protected final Rect mEventSendingRect = new Rect();
        protected final Rect mEventReceivingRect = new Rect();

        protected abstract boolean needsToForward(final int x, final int y);

        protected void onForwardingEvent(final MotionEvent me) {
        }

        public MotionEventForwarder(final SenderView senderView, final ReceiverView receiverView) {
            this.mSenderView = senderView;
            this.mReceiverView = receiverView;
        }

        protected int translateX(final int x) {
            return x - this.mEventReceivingRect.left;
        }

        protected int translateY(final int y) {
            return y - this.mEventReceivingRect.top;
        }

        public boolean onInterceptTouchEvent(final int x, final int y, final MotionEvent me) {
            if (this.mSenderView.getVisibility() == 0 && this.mReceiverView.getVisibility() == 0) {
                this.mSenderView.getGlobalVisibleRect(this.mEventSendingRect);
                if (this.mEventSendingRect.contains(x, y) && me.getActionMasked() == 0 && needsToForward(x, y)) {
                    return true;
                }
            }
            return false;
        }

        public boolean onTouchEvent(final int x, final int y, final MotionEvent me) {
            this.mReceiverView.getGlobalVisibleRect(this.mEventReceivingRect);
            me.setLocation(translateX(x), translateY(y));
            this.mReceiverView.dispatchTouchEvent(me);
            onForwardingEvent(me);
            return true;
        }
    }

    private static class KeyboardTopPaddingForwarder extends MotionEventForwarder<MainKeyboardView, SuggestionStripView> {
        private int mKeyboardTopPadding;

        public KeyboardTopPaddingForwarder(final MainKeyboardView mainKeyboardView, final SuggestionStripView suggestionStripView) {
            super(mainKeyboardView, suggestionStripView);
        }

        public void setKeyboardTopPadding(final int keyboardTopPadding) {
            this.mKeyboardTopPadding = keyboardTopPadding;
        }

        private boolean isInKeyboardTopPadding(final int y) {
            return y < this.mEventSendingRect.top + this.mKeyboardTopPadding;
        }

        @Override // com.android.inputmethod.latin.InputView.MotionEventForwarder
        protected boolean needsToForward(final int x, final int y) {
            return ((View) ((MainKeyboardView) this.mSenderView).getParent()).getVisibility() == 0 && isInKeyboardTopPadding(y);
        }

        @Override // com.android.inputmethod.latin.InputView.MotionEventForwarder
        protected int translateY(final int y) {
            int iTranslateY = super.translateY(y);
            return isInKeyboardTopPadding(y) ? Math.min(iTranslateY, this.mEventReceivingRect.height() - 1) : iTranslateY;
        }
    }

    private static class MoreSuggestionsViewCanceler extends MotionEventForwarder<MainKeyboardView, SuggestionStripView> {
        public MoreSuggestionsViewCanceler(final MainKeyboardView mainKeyboardView, final SuggestionStripView suggestionStripView) {
            super(mainKeyboardView, suggestionStripView);
        }

        @Override // com.android.inputmethod.latin.InputView.MotionEventForwarder
        protected boolean needsToForward(final int x, final int y) {
            return ((SuggestionStripView) this.mReceiverView).isShowingMoreSuggestionPanel() && this.mEventSendingRect.contains(x, y);
        }

        @Override // com.android.inputmethod.latin.InputView.MotionEventForwarder
        protected void onForwardingEvent(final MotionEvent me) {
            if (me.getActionMasked() == 0) {
                ((SuggestionStripView) this.mReceiverView).dismissMoreSuggestionsPanel();
            }
        }
    }
}
