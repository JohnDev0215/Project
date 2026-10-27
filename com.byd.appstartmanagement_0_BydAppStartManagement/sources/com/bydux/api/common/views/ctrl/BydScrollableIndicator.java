package com.bydux.api.common.views.ctrl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BydScrollableIndicator extends LinearLayout {
    private int mAnimDuration;
    private Rect mBounds;
    private Context mContext;
    private Rect mCurRect;
    private Drawable mDesDrawable;
    private Rect mDownRect;
    private long mDownTime;
    private int mDownTouchTimeout;
    private float mDownX;
    private Rect mFinalRect;
    private Rect mFirstRect;
    private IDisableListener mIdisableListener;
    private boolean mIsAnimating;
    private OnItemClickListener mItemClickListener;
    private int mItemWidth;
    private OnDisableItemListener mOnDisableItemListener;
    private int mPosition;
    private List<Rect> mRectList;
    private float mScrollDisX;
    private Drawable mSelectedDrawable;
    private float mTouchMoveX;
    private boolean mTouchedInSelectItem;
    private Handler mUiHandler;
    private Drawable mUnSelectedDrawable;

    public interface IDisableListener {
        void onDisableTouchListener();
    }

    public interface OnDisableItemListener {
        boolean onDisableItemListener(int i, int i2);
    }

    public interface OnItemClickListener {
        void onItemClick(int i, int i2);
    }

    public BydScrollableIndicator(Context context) {
        this(context, null);
    }

    private int findIndexByTouchPoint(MotionEvent motionEvent) {
        if (this.mRectList.size() == 0) {
            return -1;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        for (int i = 0; i < this.mRectList.size(); i++) {
            if (this.mRectList.get(i).contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    private Rect findRectByCenterPoint() {
        int i = (this.mDesDrawable.getBounds().left + this.mDesDrawable.getBounds().right) / 2;
        int i2 = (this.mDesDrawable.getBounds().top + this.mDesDrawable.getBounds().bottom) / 2;
        for (Rect rect : this.mRectList) {
            if (rect.contains(i, i2)) {
                return rect;
            }
        }
        return null;
    }

    private Rect findRectByTouchPoint(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        for (Rect rect : this.mRectList) {
            if (rect.contains(x, y)) {
                return rect;
            }
        }
        return null;
    }

    private void handleActionDown(MotionEvent motionEvent) {
        this.mTouchedInSelectItem = this.mCurRect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        Rect rectFindRectByTouchPoint = findRectByTouchPoint(motionEvent);
        this.mDownRect = rectFindRectByTouchPoint;
        if (rectFindRectByTouchPoint == null) {
            BydLogHelper.logE("mDownRect == null");
            return;
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, rectFindRectByTouchPoint.left - this.mCurRect.left);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.bydux.api.common.views.ctrl.BydScrollableIndicator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                int i = BydScrollableIndicator.this.mCurRect.left + iIntValue;
                int i2 = BydScrollableIndicator.this.mCurRect.top;
                int i3 = BydScrollableIndicator.this.mItemWidth + i;
                int i4 = BydScrollableIndicator.this.mCurRect.bottom;
                if ((iIntValue < 0 && i < BydScrollableIndicator.this.mItemWidth * BydScrollableIndicator.this.mPosition) || (iIntValue > 0 && i > BydScrollableIndicator.this.mItemWidth * BydScrollableIndicator.this.mPosition)) {
                    return;
                }
                BydScrollableIndicator.this.mBounds.set(i, i2, i3, i4);
                BydScrollableIndicator.this.mDesDrawable.setBounds(BydScrollableIndicator.this.mBounds);
                BydScrollableIndicator.this.invalidate();
            }
        });
        valueAnimatorOfInt.addListener(new AnimatorListenerAdapter() { // from class: com.bydux.api.common.views.ctrl.BydScrollableIndicator.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BydScrollableIndicator bydScrollableIndicator = BydScrollableIndicator.this;
                bydScrollableIndicator.mCurRect = bydScrollableIndicator.mDownRect;
                BydScrollableIndicator.this.mIsAnimating = false;
                BydScrollableIndicator bydScrollableIndicator2 = BydScrollableIndicator.this;
                bydScrollableIndicator2.setSelection(bydScrollableIndicator2.mPosition);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                BydScrollableIndicator.this.mIsAnimating = true;
            }
        });
        valueAnimatorOfInt.setDuration(this.mAnimDuration);
        valueAnimatorOfInt.start();
    }

    private boolean handleDisbableItem(MotionEvent motionEvent) {
        OnDisableItemListener onDisableItemListener;
        int iFindIndexByTouchPoint = findIndexByTouchPoint(motionEvent);
        if (iFindIndexByTouchPoint == -1 || iFindIndexByTouchPoint >= getChildCount() || (onDisableItemListener = this.mOnDisableItemListener) == null) {
            return false;
        }
        return onDisableItemListener.onDisableItemListener(getId(), iFindIndexByTouchPoint);
    }

    private void handlerActionUp(MotionEvent motionEvent) {
        if (!this.mTouchedInSelectItem) {
            if (this.mItemClickListener != null) {
                final Rect rect = this.mDownRect;
                refreshPosition(rect);
                setChildSelected(this.mPosition);
                this.mUiHandler.postDelayed(new Runnable() { // from class: com.bydux.api.common.views.ctrl.BydScrollableIndicator.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (BydScrollableIndicator.this.mTouchedInSelectItem) {
                            return;
                        }
                        BydScrollableIndicator.this.mItemClickListener.onItemClick(BydScrollableIndicator.this.getId(), BydScrollableIndicator.this.mPosition);
                        BydScrollableIndicator.this.mDesDrawable.setBounds(rect);
                        BydScrollableIndicator.this.invalidate();
                    }
                }, 100L);
                return;
            }
            return;
        }
        final Rect rectFindRectByCenterPoint = findRectByCenterPoint();
        if (outOfBehindBounds()) {
            rectFindRectByCenterPoint = this.mFinalRect;
        }
        if (outOfFrontBounds()) {
            rectFindRectByCenterPoint = this.mFirstRect;
        }
        if (rectFindRectByCenterPoint == null) {
            return;
        }
        float f = this.mTouchMoveX;
        int i = this.mItemWidth;
        float f2 = f % i;
        if (f > 0.0f) {
            if (f2 > i / 2.0f) {
                f2 = -(i - f2);
            }
        } else if ((-f2) > i / 2.0f) {
            f2 += i;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, 0.0f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.bydux.api.common.views.ctrl.BydScrollableIndicator.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                int i2 = (int) (r0.left + fFloatValue);
                int i3 = rectFindRectByCenterPoint.top;
                int i4 = BydScrollableIndicator.this.mItemWidth + i2;
                int i5 = rectFindRectByCenterPoint.bottom;
                if ((fFloatValue < 0.0f && i2 < BydScrollableIndicator.this.mItemWidth * BydScrollableIndicator.this.mPosition) || (fFloatValue > 0.0f && i2 > BydScrollableIndicator.this.mItemWidth * BydScrollableIndicator.this.mPosition)) {
                    return;
                }
                BydScrollableIndicator.this.mBounds.set(i2, i3, i4, i5);
                BydScrollableIndicator.this.mDesDrawable.setBounds(BydScrollableIndicator.this.mBounds);
                BydScrollableIndicator.this.invalidate();
            }
        });
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.bydux.api.common.views.ctrl.BydScrollableIndicator.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BydScrollableIndicator.this.mCurRect = rectFindRectByCenterPoint;
                BydScrollableIndicator.this.mIsAnimating = false;
                BydScrollableIndicator bydScrollableIndicator = BydScrollableIndicator.this;
                bydScrollableIndicator.setSelection(bydScrollableIndicator.mPosition);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                BydScrollableIndicator.this.mIsAnimating = true;
            }
        });
        valueAnimatorOfFloat.setDuration(this.mAnimDuration);
        valueAnimatorOfFloat.start();
        refreshPosition(rectFindRectByCenterPoint);
        setChildSelected(this.mPosition);
        OnItemClickListener onItemClickListener = this.mItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(getId(), this.mPosition);
        }
    }

    private boolean isDownTouchTooShort() {
        if (this.mDownTime == 0 || System.currentTimeMillis() - this.mDownTime >= this.mDownTouchTimeout) {
            this.mDownTime = System.currentTimeMillis();
            return false;
        }
        this.mDownTime = System.currentTimeMillis();
        return true;
    }

    private boolean outOfBehindBounds() {
        return this.mDesDrawable.getBounds().right >= getWidth();
    }

    private boolean outOfFrontBounds() {
        return this.mDesDrawable.getBounds().left <= 0;
    }

    private void refreshPosition(Rect rect) {
        for (int i = 0; i < this.mRectList.size(); i++) {
            if (rect.left == this.mRectList.get(i).left) {
                this.mPosition = i;
                return;
            }
        }
    }

    private void setChildSelected(int i) {
        if (i < 0) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                if (!(getChildAt(i2) instanceof FrameLayout)) {
                    throw new IllegalArgumentException("item should be in FrameLayout");
                }
                ((FrameLayout) getChildAt(i2)).getChildAt(0).setSelected(false);
            }
            return;
        }
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (!(getChildAt(i3) instanceof FrameLayout)) {
                throw new IllegalArgumentException("item should be in FrameLayout");
            }
            if (i != i3) {
                ((FrameLayout) getChildAt(i3)).getChildAt(0).setSelected(false);
            }
        }
        ((FrameLayout) getChildAt(i)).getChildAt(0).setSelected(true);
    }

    public void addItem(View view) {
        if (view == null) {
            return;
        }
        FrameLayout frameLayout = new FrameLayout(this.mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 17;
        frameLayout.setLayoutParams(layoutParams);
        if (frameLayout.getParent() != null) {
            ((ViewGroup) frameLayout.getParent()).removeView(frameLayout);
        }
        addView(frameLayout);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view, layoutParams2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        this.mDesDrawable.draw(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getSelection() {
        return this.mPosition;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mItemWidth = getWidth() / getChildCount();
        int childCount = getChildCount();
        this.mRectList.clear();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            int left = childAt.getLeft();
            int top = childAt.getTop();
            int right = childAt.getRight();
            int bottom = childAt.getBottom();
            Rect rect = new Rect(left, top, right, bottom);
            this.mRectList.add(rect);
            if (i5 == this.mPosition) {
                if (!this.mTouchedInSelectItem) {
                    this.mBounds.set(left, top, right, bottom);
                    this.mDesDrawable.setBounds(this.mBounds);
                }
                this.mCurRect = rect;
            }
            if (i5 == 0) {
                this.mFirstRect = rect;
            }
            if (i5 == childCount - 1) {
                this.mFinalRect = rect;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            boolean r0 = r6.isEnabled()
            r1 = 0
            if (r0 != 0) goto Lf
            com.bydux.api.common.views.ctrl.BydScrollableIndicator$IDisableListener r7 = r6.mIdisableListener
            if (r7 == 0) goto Le
            r7.onDisableTouchListener()
        Le:
            return r1
        Lf:
            int r0 = r7.getAction()
            r2 = 1
            if (r0 == 0) goto L73
            if (r0 == r2) goto L6f
            r1 = 2
            if (r0 == r1) goto L20
            r1 = 3
            if (r0 == r1) goto L6f
            goto L9a
        L20:
            boolean r0 = r6.mTouchedInSelectItem
            if (r0 != 0) goto L25
            return r2
        L25:
            float r7 = r7.getX()
            float r0 = r6.mDownX
            float r7 = r7 - r0
            r6.mScrollDisX = r7
            r6.mTouchMoveX = r7
            android.graphics.Rect r0 = r6.mCurRect
            int r1 = r0.left
            float r1 = (float) r1
            float r1 = r1 + r7
            int r7 = (int) r1
            int r1 = r6.mItemWidth
            int r3 = r7 + r1
            android.graphics.Rect r4 = r6.mFinalRect
            int r4 = r4.left
            r5 = 0
            if (r7 < r4) goto L47
            int r3 = r4 + r1
            r6.mTouchMoveX = r5
            r7 = r4
        L47:
            android.graphics.Rect r4 = r6.mFirstRect
            int r4 = r4.left
            if (r7 > r4) goto L52
            int r3 = r4 + r1
            r6.mTouchMoveX = r5
            r7 = r4
        L52:
            android.graphics.Rect r1 = r6.mBounds
            int r4 = r1.left
            if (r4 == r7) goto L9a
            int r4 = r1.right
            if (r4 != r3) goto L5d
            goto L9a
        L5d:
            int r4 = r0.top
            int r0 = r0.bottom
            r1.set(r7, r4, r3, r0)
            android.graphics.drawable.Drawable r7 = r6.mDesDrawable
            android.graphics.Rect r0 = r6.mBounds
            r7.setBounds(r0)
            r6.invalidate()
            goto L9a
        L6f:
            r6.handlerActionUp(r7)
            goto L9a
        L73:
            boolean r0 = r6.mIsAnimating
            if (r0 != 0) goto L9b
            boolean r0 = r6.isDownTouchTooShort()
            if (r0 != 0) goto L9b
            boolean r0 = r6.handleDisbableItem(r7)
            if (r0 == 0) goto L84
            goto L9b
        L84:
            r0 = -1
            r6.setChildSelected(r0)
            android.graphics.drawable.Drawable r0 = r6.mSelectedDrawable
            r6.mDesDrawable = r0
            android.graphics.Rect r1 = r6.mBounds
            r0.setBounds(r1)
            float r0 = r7.getX()
            r6.mDownX = r0
            r6.handleActionDown(r7)
        L9a:
            return r2
        L9b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.ctrl.BydScrollableIndicator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBackground(int i) {
        setBackgroundResource(i);
    }

    public void setDisableItemListener(OnDisableItemListener onDisableItemListener) {
        this.mOnDisableItemListener = onDisableItemListener;
    }

    public void setDisableListener(IDisableListener iDisableListener) {
        this.mIdisableListener = iDisableListener;
    }

    public void setDownTouchTimeout(int i) {
        if (this.mAnimDuration >= i) {
            throw new IllegalArgumentException("ScrollableIndicator timeout should be bigger than animDuration");
        }
        this.mDownTouchTimeout = i;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setSelectedBackground(Drawable drawable) {
        this.mDesDrawable = drawable;
        this.mSelectedDrawable = drawable;
        drawable.setBounds(this.mBounds);
    }

    public void setSelection(int i) {
        BydLogHelper.logD("setSelection position = " + i);
        if (i > getChildCount() - 1 || i < 0) {
            BydLogHelper.logD("setSelection: set unSelected item!");
            return;
        }
        this.mPosition = i;
        if (!(getChildAt(i) instanceof FrameLayout)) {
            throw new IllegalArgumentException("item should be in FrameLayout");
        }
        if (this.mDesDrawable == this.mUnSelectedDrawable) {
            this.mDesDrawable = this.mSelectedDrawable;
        }
        int childCount = getChildCount();
        this.mRectList.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            Rect rect = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            this.mRectList.add(rect);
            if (i2 == this.mPosition) {
                this.mBounds.set(rect);
                this.mDesDrawable.setBounds(this.mBounds);
                ((FrameLayout) getChildAt(i2)).getChildAt(0).setSelected(true);
                this.mCurRect = rect;
            } else {
                ((FrameLayout) getChildAt(i2)).getChildAt(0).setSelected(false);
            }
        }
        invalidate();
    }

    public void setmAnimDuration(int i) {
        this.mAnimDuration = i;
    }

    public BydScrollableIndicator(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydScrollableIndicator(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRectList = new ArrayList();
        this.mBounds = new Rect();
        this.mAnimDuration = 220;
        this.mDownTime = 0L;
        this.mDownTouchTimeout = 200;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BydScrollableIndicator, i, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.BydScrollableIndicator_byd_unselected_bg);
        typedArrayObtainStyledAttributes.recycle();
        setBackgroundDrawable(drawable);
        this.mUnSelectedDrawable = drawable;
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(R.styleable.BydScrollableIndicator_byd_selected_bg);
        if (drawable2 == null) {
            this.mDesDrawable = drawable2;
            this.mSelectedDrawable = drawable2;
        } else {
            this.mDesDrawable = drawable2;
            this.mSelectedDrawable = drawable2;
        }
        this.mContext = context;
        this.mDesDrawable.setBounds(this.mBounds);
        this.mUiHandler = new Handler(Looper.getMainLooper());
    }
}
