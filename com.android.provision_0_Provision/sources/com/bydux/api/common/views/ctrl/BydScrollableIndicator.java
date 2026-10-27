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
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
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
        boolean onDisableItemListener(int viewId, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(int viewId, int position);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    public BydScrollableIndicator(Context context) {
        this(context, null);
    }

    public BydScrollableIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BydScrollableIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mRectList = new ArrayList();
        this.mBounds = new Rect();
        this.mAnimDuration = 220;
        this.mDownTime = 0L;
        this.mDownTouchTimeout = 200;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.BydScrollableIndicator, defStyle, 0);
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

    public void setDownTouchTimeout(int timeout) {
        if (this.mAnimDuration >= timeout) {
            throw new IllegalArgumentException("ScrollableIndicator timeout should be bigger than animDuration");
        }
        this.mDownTouchTimeout = timeout;
    }

    public void setmAnimDuration(int duration) {
        this.mAnimDuration = duration;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setDisableItemListener(OnDisableItemListener listener) {
        this.mOnDisableItemListener = listener;
    }

    public void setSelection(int position) {
        BydLogHelper.logD("setSelection position = " + position);
        if (position > getChildCount() - 1 || position < 0) {
            BydLogHelper.logD("setSelection: set unSelected item!");
            return;
        }
        this.mPosition = position;
        if (!(getChildAt(position) instanceof FrameLayout)) {
            throw new IllegalArgumentException("item should be in FrameLayout");
        }
        if (this.mDesDrawable == this.mUnSelectedDrawable) {
            this.mDesDrawable = this.mSelectedDrawable;
        }
        int childCount = getChildCount();
        this.mRectList.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Rect rect = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            this.mRectList.add(rect);
            if (i == this.mPosition) {
                this.mBounds.set(rect);
                this.mDesDrawable.setBounds(this.mBounds);
                ((FrameLayout) getChildAt(i)).getChildAt(0).setSelected(true);
                this.mCurRect = rect;
            } else {
                ((FrameLayout) getChildAt(i)).getChildAt(0).setSelected(false);
            }
        }
        invalidate();
    }

    public int getSelection() {
        return this.mPosition;
    }

    public void setBackground(int resId) {
        setBackgroundResource(resId);
    }

    public void setSelectedBackground(Drawable drawable) {
        this.mDesDrawable = drawable;
        this.mSelectedDrawable = drawable;
        drawable.setBounds(this.mBounds);
    }

    public void addItem(View item) {
        if (item == null) {
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
        if (item.getParent() != null) {
            ((ViewGroup) item.getParent()).removeView(item);
        }
        frameLayout.addView(item, layoutParams2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.mItemWidth = getWidth() / getChildCount();
        int childCount = getChildCount();
        this.mRectList.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int left2 = childAt.getLeft();
            int top2 = childAt.getTop();
            int right2 = childAt.getRight();
            int bottom2 = childAt.getBottom();
            Rect rect = new Rect(left2, top2, right2, bottom2);
            this.mRectList.add(rect);
            if (i == this.mPosition) {
                if (!this.mTouchedInSelectItem) {
                    this.mBounds.set(left2, top2, right2, bottom2);
                    this.mDesDrawable.setBounds(this.mBounds);
                }
                this.mCurRect = rect;
            }
            if (i == 0) {
                this.mFirstRect = rect;
            }
            if (i == childCount - 1) {
                this.mFinalRect = rect;
            }
        }
    }

    private boolean outOfFrontBounds() {
        return this.mDesDrawable.getBounds().left <= 0;
    }

    private boolean outOfBehindBounds() {
        return this.mDesDrawable.getBounds().right >= getWidth();
    }

    public void setDisableListener(IDisableListener listener) {
        this.mIdisableListener = listener;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private boolean isDownTouchTooShort() {
        if (this.mDownTime != 0 && System.currentTimeMillis() - this.mDownTime < this.mDownTouchTimeout) {
            this.mDownTime = System.currentTimeMillis();
            return true;
        }
        this.mDownTime = System.currentTimeMillis();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0080  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isEnabled()
            r1 = 0
            if (r0 != 0) goto Lf
            com.bydux.api.common.views.ctrl.BydScrollableIndicator$IDisableListener r6 = r5.mIdisableListener
            if (r6 == 0) goto Le
            r6.onDisableTouchListener()
        Le:
            return r1
        Lf:
            int r0 = r6.getAction()
            r2 = 1
            if (r0 == 0) goto L84
            if (r0 == r2) goto L80
            r1 = 2
            if (r0 == r1) goto L20
            r1 = 3
            if (r0 == r1) goto L80
            goto Lab
        L20:
            boolean r0 = r5.mTouchedInSelectItem
            if (r0 != 0) goto L25
            return r2
        L25:
            float r6 = r6.getX()
            float r0 = r5.mDownX
            float r6 = r6 - r0
            r5.mScrollDisX = r6
            r5.mTouchMoveX = r6
            android.graphics.Rect r6 = r5.mCurRect
            int r6 = r6.left
            float r6 = (float) r6
            float r0 = r5.mScrollDisX
            float r6 = r6 + r0
            int r6 = (int) r6
            int r0 = r5.mItemWidth
            int r0 = r0 + r6
            android.graphics.Rect r1 = r5.mFinalRect
            int r1 = r1.left
            r3 = 0
            if (r6 < r1) goto L4c
            android.graphics.Rect r6 = r5.mFinalRect
            int r6 = r6.left
            int r0 = r5.mItemWidth
            int r0 = r0 + r6
            r5.mTouchMoveX = r3
        L4c:
            android.graphics.Rect r1 = r5.mFirstRect
            int r1 = r1.left
            if (r6 > r1) goto L5b
            android.graphics.Rect r6 = r5.mFirstRect
            int r6 = r6.left
            int r0 = r5.mItemWidth
            int r0 = r0 + r6
            r5.mTouchMoveX = r3
        L5b:
            android.graphics.Rect r1 = r5.mBounds
            int r1 = r1.left
            if (r1 == r6) goto Lab
            android.graphics.Rect r1 = r5.mBounds
            int r1 = r1.right
            if (r1 != r0) goto L68
            goto Lab
        L68:
            android.graphics.Rect r1 = r5.mCurRect
            int r1 = r1.top
            android.graphics.Rect r3 = r5.mCurRect
            int r3 = r3.bottom
            android.graphics.Rect r4 = r5.mBounds
            r4.set(r6, r1, r0, r3)
            android.graphics.drawable.Drawable r6 = r5.mDesDrawable
            android.graphics.Rect r0 = r5.mBounds
            r6.setBounds(r0)
            r5.invalidate()
            goto Lab
        L80:
            r5.handlerActionUp(r6)
            goto Lab
        L84:
            boolean r0 = r5.mIsAnimating
            if (r0 != 0) goto Lac
            boolean r0 = r5.isDownTouchTooShort()
            if (r0 != 0) goto Lac
            boolean r0 = r5.handleDisbableItem(r6)
            if (r0 == 0) goto L95
            goto Lac
        L95:
            r0 = -1
            r5.setChildSelected(r0)
            android.graphics.drawable.Drawable r0 = r5.mSelectedDrawable
            r5.mDesDrawable = r0
            android.graphics.Rect r1 = r5.mBounds
            r0.setBounds(r1)
            float r0 = r6.getX()
            r5.mDownX = r0
            r5.handleActionDown(r6)
        Lab:
            return r2
        Lac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bydux.api.common.views.ctrl.BydScrollableIndicator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean handleDisbableItem(MotionEvent ev) {
        OnDisableItemListener onDisableItemListener;
        int iFindIndexByTouchPoint = findIndexByTouchPoint(ev);
        if (iFindIndexByTouchPoint == -1 || iFindIndexByTouchPoint >= getChildCount() || (onDisableItemListener = this.mOnDisableItemListener) == null) {
            return false;
        }
        return onDisableItemListener.onDisableItemListener(getId(), iFindIndexByTouchPoint);
    }

    private void handleActionDown(MotionEvent event) {
        this.mTouchedInSelectItem = this.mCurRect.contains((int) event.getX(), (int) event.getY());
        Rect rectFindRectByTouchPoint = findRectByTouchPoint(event);
        this.mDownRect = rectFindRectByTouchPoint;
        if (rectFindRectByTouchPoint != null) {
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
                public void onAnimationEnd(Animator animation) {
                    BydScrollableIndicator bydScrollableIndicator = BydScrollableIndicator.this;
                    bydScrollableIndicator.mCurRect = bydScrollableIndicator.mDownRect;
                    BydScrollableIndicator.this.mIsAnimating = false;
                    BydScrollableIndicator bydScrollableIndicator2 = BydScrollableIndicator.this;
                    bydScrollableIndicator2.setSelection(bydScrollableIndicator2.mPosition);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    BydScrollableIndicator.this.mIsAnimating = true;
                }
            });
            valueAnimatorOfInt.setDuration(this.mAnimDuration);
            valueAnimatorOfInt.start();
            return;
        }
        BydLogHelper.logE("mDownRect == null");
    }

    private void handlerActionUp(MotionEvent ev) {
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
            public void onAnimationUpdate(ValueAnimator animation) {
                float fFloatValue = ((Float) animation.getAnimatedValue()).floatValue();
                int i2 = (int) (rectFindRectByCenterPoint.left + fFloatValue);
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
            public void onAnimationEnd(Animator animation) {
                BydScrollableIndicator.this.mCurRect = rectFindRectByCenterPoint;
                BydScrollableIndicator.this.mIsAnimating = false;
                BydScrollableIndicator bydScrollableIndicator = BydScrollableIndicator.this;
                bydScrollableIndicator.setSelection(bydScrollableIndicator.mPosition);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
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

    private void setChildSelected(int position) {
        if (position < 0) {
            for (int i = 0; i < getChildCount(); i++) {
                if (!(getChildAt(i) instanceof FrameLayout)) {
                    throw new IllegalArgumentException("item should be in FrameLayout");
                }
                ((FrameLayout) getChildAt(i)).getChildAt(0).setSelected(false);
            }
            return;
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (!(getChildAt(i2) instanceof FrameLayout)) {
                throw new IllegalArgumentException("item should be in FrameLayout");
            }
            if (position != i2) {
                ((FrameLayout) getChildAt(i2)).getChildAt(0).setSelected(false);
            }
        }
        ((FrameLayout) getChildAt(position)).getChildAt(0).setSelected(true);
    }

    private void refreshPosition(Rect r) {
        for (int i = 0; i < this.mRectList.size(); i++) {
            if (r.left == this.mRectList.get(i).left) {
                this.mPosition = i;
                return;
            }
        }
    }

    private Rect findRectByTouchPoint(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (Rect rect : this.mRectList) {
            if (rect.contains(x, y)) {
                return rect;
            }
        }
        return null;
    }

    private int findIndexByTouchPoint(MotionEvent event) {
        if (this.mRectList.size() == 0) {
            return -1;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
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

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.mDesDrawable.draw(canvas);
        super.dispatchDraw(canvas);
    }
}
