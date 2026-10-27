package com.android.inputmethod.keyboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.inputmethod.accessibility.AccessibilityUtils;
import com.android.inputmethod.accessibility.MoreKeysKeyboardAccessibilityDelegate;
import com.android.inputmethod.keyboard.MoreKeysKeyboard;
import com.android.inputmethod.keyboard.MoreKeysPanel;
import com.android.inputmethod.keyboard.internal.KeyDrawParams;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.CoordinateUtils;

/* JADX INFO: loaded from: classes2.dex */
public class MoreKeysKeyboardView extends KeyboardView implements MoreKeysPanel {
    protected MoreKeysKeyboardAccessibilityDelegate mAccessibilityDelegate;
    private int mActivePointerId;
    private MoreKeysPanel.Controller mController;
    private final int[] mCoordinates;
    private Key mCurrentKey;
    private final Drawable mDivider;
    private Key mKey;
    protected final KeyDetector mKeyDetector;
    protected KeyboardActionListener mListener;
    private int mOriginX;
    private int mOriginY;
    private PointerTracker mTracker;

    public MoreKeysKeyboardView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.moreKeysKeyboardViewStyle);
    }

    public MoreKeysKeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        this.mCoordinates = CoordinateUtils.newInstance();
        this.mController = EMPTY_CONTROLLER;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MoreKeysKeyboardView, defStyle, R.style.MoreKeysKeyboardView);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        this.mDivider = drawable;
        if (drawable != null) {
            drawable.setAlpha(128);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mKeyDetector = new MoreKeysDetector(getResources().getDimension(R.dimen.config_more_keys_keyboard_slide_allowance));
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView, android.view.View
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        Keyboard keyboard = getKeyboard();
        if (keyboard != null) {
            setMeasuredDimension(keyboard.mOccupiedWidth + getPaddingLeft() + getPaddingRight(), keyboard.mOccupiedHeight + getPaddingTop() + getPaddingBottom());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView
    protected void onDrawKeyTopVisuals(final Key key, final Canvas canvas, final Paint paint, final KeyDrawParams params) {
        if (!key.isSpacer() || !(key instanceof MoreKeysKeyboard.MoreKeyDivider) || this.mDivider == null) {
            super.onDrawKeyTopVisuals(key, canvas, paint, params);
            return;
        }
        int drawWidth = key.getDrawWidth();
        int height = key.getHeight();
        int iMin = Math.min(this.mDivider.getIntrinsicWidth(), drawWidth);
        int intrinsicHeight = this.mDivider.getIntrinsicHeight();
        drawIcon(canvas, this.mDivider, (drawWidth - iMin) / 2, (height - intrinsicHeight) / 2, iMin, intrinsicHeight);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView
    public void setKeyboard(final Keyboard keyboard) {
        super.setKeyboard(keyboard);
        this.mKeyDetector.setKeyboard(keyboard, -getPaddingLeft(), (-getPaddingTop()) + getVerticalCorrection());
        if (AccessibilityUtils.getInstance().isAccessibilityEnabled()) {
            if (this.mAccessibilityDelegate == null) {
                MoreKeysKeyboardAccessibilityDelegate moreKeysKeyboardAccessibilityDelegate = new MoreKeysKeyboardAccessibilityDelegate(this, this.mKeyDetector);
                this.mAccessibilityDelegate = moreKeysKeyboardAccessibilityDelegate;
                moreKeysKeyboardAccessibilityDelegate.setOpenAnnounce(R.string.spoken_open_more_keys_keyboard);
                this.mAccessibilityDelegate.setCloseAnnounce(R.string.spoken_close_more_keys_keyboard);
            }
            this.mAccessibilityDelegate.setKeyboard(keyboard);
            return;
        }
        this.mAccessibilityDelegate = null;
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void showMoreKeysPanel(final View parentView, final MoreKeysPanel.Controller controller, final int pointX, final int pointY, final KeyboardActionListener listener) {
        this.mController = controller;
        this.mListener = listener;
        View containerView = getContainerView();
        int defaultCoordX = ((pointX - getDefaultCoordX()) - containerView.getPaddingLeft()) - getPaddingLeft();
        int measuredHeight = (pointY - containerView.getMeasuredHeight()) + containerView.getPaddingBottom() + getPaddingBottom();
        parentView.getLocationInWindow(this.mCoordinates);
        int iMax = Math.max(0, Math.min(parentView.getMeasuredWidth() - containerView.getMeasuredWidth(), defaultCoordX)) + CoordinateUtils.x(this.mCoordinates);
        int iY = CoordinateUtils.y(this.mCoordinates) + measuredHeight;
        containerView.setX(iMax);
        containerView.setY(iY);
        this.mOriginX = defaultCoordX + containerView.getPaddingLeft();
        this.mOriginY = measuredHeight + containerView.getPaddingTop();
        controller.onShowMoreKeysPanel(this);
        MoreKeysKeyboardAccessibilityDelegate moreKeysKeyboardAccessibilityDelegate = this.mAccessibilityDelegate;
        if (moreKeysKeyboardAccessibilityDelegate == null || !AccessibilityUtils.getInstance().isAccessibilityEnabled()) {
            return;
        }
        moreKeysKeyboardAccessibilityDelegate.onShowMoreKeysKeyboard();
    }

    protected int getDefaultCoordX() {
        return ((MoreKeysKeyboard) getKeyboard()).getDefaultCoordX();
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void onDownEvent(final int x, final int y, final int pointerId, final long eventTime) {
        this.mActivePointerId = pointerId;
        this.mCurrentKey = detectKey(x, y);
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void onDownEvent(final Key key, final int x, final int y, final int pointerId, final long eventTime) {
        this.mActivePointerId = pointerId;
        this.mCurrentKey = detectKey(x, y);
        this.mKey = key;
        this.mTracker = PointerTracker.getPointerTracker(pointerId);
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void onMoveEvent(final int x, final int y, final int pointerId, final long eventTime) {
        if (this.mActivePointerId != pointerId) {
            return;
        }
        boolean z = this.mCurrentKey != null;
        Key keyDetectKey = detectKey(x, y);
        this.mCurrentKey = keyDetectKey;
        if (z && keyDetectKey == null) {
            this.mController.onCancelMoreKeysPanel();
        }
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void onUpEvent(final int x, final int y, final int pointerId, final long eventTime) {
        if (this.mActivePointerId != pointerId) {
            return;
        }
        Key keyDetectKey = detectKey(x, y);
        this.mCurrentKey = keyDetectKey;
        if (keyDetectKey != null) {
            if (this.mKey != null) {
                PointerTracker.sDrawingProxy.onKeyReleased(this.mKey, false);
            }
            updateReleaseKeyGraphics(this.mCurrentKey);
            onKeyInput(this.mCurrentKey, x, y);
            this.mCurrentKey = null;
        }
    }

    protected void onKeyInput(final Key key, final int x, final int y) {
        int code = key.getCode();
        if (code == -4) {
            this.mListener.onTextInput(this.mCurrentKey.getOutputText());
        } else if (code != -15) {
            if (getKeyboard().hasProximityCharsCorrection(code)) {
                this.mListener.onCodeInput(code, x, y, false);
            } else {
                this.mListener.onCodeInput(code, -1, -1, false);
            }
        }
    }

    private Key detectKey(int x, int y) {
        Key key = this.mCurrentKey;
        Key keyDetectHitKey = this.mKeyDetector.detectHitKey(x, y);
        if (keyDetectHitKey == key) {
            return keyDetectHitKey;
        }
        if (key != null) {
            updateReleaseKeyGraphics(key);
            invalidateKey(key);
        }
        if (keyDetectHitKey != null) {
            updatePressKeyGraphics(keyDetectHitKey);
            invalidateKey(keyDetectHitKey);
        }
        return keyDetectHitKey;
    }

    private void updateReleaseKeyGraphics(final Key key) {
        key.onReleased();
        invalidateKey(key);
    }

    private void updatePressKeyGraphics(final Key key) {
        key.onPressed();
        invalidateKey(key);
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void dismissMoreKeysPanel() {
        if (isShowingInParent()) {
            MoreKeysKeyboardAccessibilityDelegate moreKeysKeyboardAccessibilityDelegate = this.mAccessibilityDelegate;
            if (moreKeysKeyboardAccessibilityDelegate != null && AccessibilityUtils.getInstance().isAccessibilityEnabled()) {
                moreKeysKeyboardAccessibilityDelegate.onDismissMoreKeysKeyboard();
            }
            this.mController.onDismissMoreKeysPanel();
        }
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public int translateX(final int x) {
        return x - this.mOriginX;
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public int translateY(final int y) {
        return y - this.mOriginY;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(final android.view.MotionEvent r8) {
        /*
            r7 = this;
            int r0 = r8.getActionMasked()
            long r5 = r8.getEventTime()
            int r1 = r8.getActionIndex()
            float r2 = r8.getX(r1)
            int r2 = (int) r2
            float r3 = r8.getY(r1)
            int r3 = (int) r3
            int r4 = r8.getPointerId(r1)
            r8 = 1
            if (r0 == 0) goto L33
            if (r0 == r8) goto L2e
            r1 = 2
            if (r0 == r1) goto L29
            r1 = 5
            if (r0 == r1) goto L33
            r1 = 6
            if (r0 == r1) goto L2e
            goto L37
        L29:
            r1 = r7
            r1.onMoveEvent(r2, r3, r4, r5)
            goto L37
        L2e:
            r1 = r7
            r1.onUpEvent(r2, r3, r4, r5)
            goto L37
        L33:
            r1 = r7
            r1.onDownEvent(r2, r3, r4, r5)
        L37:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.keyboard.MoreKeysKeyboardView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean onHoverEvent(final MotionEvent event) {
        MoreKeysKeyboardAccessibilityDelegate moreKeysKeyboardAccessibilityDelegate = this.mAccessibilityDelegate;
        if (moreKeysKeyboardAccessibilityDelegate != null && AccessibilityUtils.getInstance().isTouchExplorationEnabled()) {
            return moreKeysKeyboardAccessibilityDelegate.onHoverEvent(event);
        }
        return super.onHoverEvent(event);
    }

    private View getContainerView() {
        return (View) getParent();
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void showInParent(final ViewGroup parentView) {
        removeFromParent();
        parentView.addView(getContainerView());
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public void removeFromParent() {
        View containerView = getContainerView();
        ViewGroup viewGroup = (ViewGroup) containerView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(containerView);
        }
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel
    public boolean isShowingInParent() {
        return getContainerView().getParent() != null;
    }
}
