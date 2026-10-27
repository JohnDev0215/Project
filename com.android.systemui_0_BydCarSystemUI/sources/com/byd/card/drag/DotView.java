package com.byd.card.drag;

import android.app.UiStyleConfig;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class DotView extends View {
    private static final int MOVE_LEFT = 1;
    private static final int MOVE_RIGHT = 2;
    private static final int NO_MOVE = 0;
    private int curPage;
    private Drawable dotActive;
    private Drawable dotDeactive;
    private int drawingBound;
    private LayoutCalculator lc;
    private float leftCenter;
    private int mAniLeft;
    private int mAniRight;
    private Context mContext;
    private SpringAnimation mLeftEdgeAnimation;
    private int mPrePage;
    private SpringAnimation mRightEdgeAnimation;
    private int moveType;
    private int pagerLeft;
    private int pagerTop;
    private int pagerWidth;
    private int pages;
    private float righttCenter;

    public DotView(Context context) {
        super(context);
        this.pages = 1;
        this.moveType = 0;
        this.mContext = context;
    }

    public DotView(Context context, AttributeSet attr) {
        super(context, attr);
        this.pages = 1;
        this.moveType = 0;
        this.mContext = context;
    }

    public DotView(Context context, AttributeSet attr, int style) {
        super(context, attr, style);
        this.pages = 1;
        this.moveType = 0;
        this.mContext = context;
    }

    public void init(LayoutCalculator lc, ObjectPool op) {
        this.lc = lc;
        Resources resources = getResources();
        int uiStyle = UiStyleConfig.getInstance(this.mContext).getUiStyle();
        if (uiStyle == 1) {
            this.dotActive = resources.getDrawable(R.mipmap.denza_dot_active);
            this.dotDeactive = resources.getDrawable(R.mipmap.denza_dot_deactive);
        } else if (uiStyle != 2) {
            this.dotActive = resources.getDrawable(R.drawable.dot_active);
            this.dotDeactive = resources.getDrawable(R.drawable.dot_deactive);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.pagerWidth = (this.pages * this.dotActive.getIntrinsicWidth()) + ((this.pages - 1) * this.lc.pagerGap);
        this.pagerLeft = (getWidth() - this.pagerWidth) / 2;
        int iDpToPixel = this.lc.dpToPixel(1);
        this.pagerTop = iDpToPixel;
        this.drawingBound = iDpToPixel + this.dotActive.getIntrinsicHeight() + this.pagerTop;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int intrinsicWidth = this.dotActive.getIntrinsicWidth();
        int intrinsicHeight = this.dotActive.getIntrinsicHeight();
        int i2 = this.pagerLeft;
        int i3 = 0;
        while (true) {
            i = this.pages;
            if (i3 >= i) {
                break;
            }
            Drawable drawable = this.dotDeactive;
            int i4 = this.pagerTop;
            drawable.setBounds(i2, i4, i2 + intrinsicWidth, intrinsicHeight + i4);
            drawable.draw(canvas);
            i2 += this.lc.pagerGap + intrinsicWidth;
            i3++;
        }
        int i5 = this.curPage;
        if (i5 >= 0 && i5 < i) {
            if (!isSwitchAnimation()) {
                int intrinsicWidth2 = this.pagerLeft + ((this.dotActive.getIntrinsicWidth() + this.lc.pagerGap) * this.curPage);
                this.mAniLeft = intrinsicWidth2;
                this.mAniRight = intrinsicWidth2 + this.dotActive.getIntrinsicWidth();
            }
            Drawable drawable2 = this.dotActive;
            int i6 = this.mAniLeft;
            int i7 = this.pagerTop;
            drawable2.setBounds(i6, i7, this.mAniRight, intrinsicHeight + i7);
            this.dotActive.draw(canvas);
            if (isSwitchAnimation()) {
                invalidatePager();
            }
        }
        super.onDraw(canvas);
    }

    private void startSwitchAnimation(int curIndex, int preIndex) {
        if (curIndex < 0 || curIndex > this.pages || curIndex == preIndex) {
            return;
        }
        cancelSwitchAnimation();
        this.moveType = curIndex > preIndex ? 2 : 1;
        this.mPrePage = preIndex;
        initLeftEdgeAnimation();
        initRightEdgeAnimation();
        int i = this.moveType;
        if (i == 2) {
            startRightEdgeAnimation();
        } else if (i == 1) {
            startLeftEdgeAnimation();
        }
    }

    private void initLeftEdgeAnimation() {
        if (this.mLeftEdgeAnimation == null) {
            SpringAnimation spring = new SpringAnimation(new FloatValueHolder()).setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(300.0f));
            this.mLeftEdgeAnimation = spring;
            spring.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() { // from class: com.byd.card.drag.DotView.1
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
                public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                    DotView.this.mAniLeft = (int) value;
                    if (DotView.this.moveType != 1 || value > DotView.this.leftCenter) {
                        return;
                    }
                    DotView.this.startRightEdgeAnimation();
                    DotView.this.moveType = 0;
                }
            });
        }
    }

    private void initRightEdgeAnimation() {
        if (this.mRightEdgeAnimation == null) {
            SpringAnimation spring = new SpringAnimation(new FloatValueHolder()).setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(300.0f));
            this.mRightEdgeAnimation = spring;
            spring.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() { // from class: com.byd.card.drag.DotView.2
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
                public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                    DotView.this.mAniRight = (int) value;
                    if (DotView.this.moveType != 2 || value < DotView.this.righttCenter) {
                        return;
                    }
                    DotView.this.startLeftEdgeAnimation();
                    DotView.this.moveType = 0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLeftEdgeAnimation() {
        int i = this.mAniLeft;
        int intrinsicWidth = this.pagerLeft + ((this.dotActive.getIntrinsicWidth() + this.lc.pagerGap) * this.curPage);
        this.leftCenter = i + ((intrinsicWidth - i) * 0.5f);
        startAnimation(this.mLeftEdgeAnimation, i, intrinsicWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRightEdgeAnimation() {
        int i = this.mAniRight;
        int intrinsicWidth = this.pagerLeft + ((this.dotActive.getIntrinsicWidth() + this.lc.pagerGap) * this.curPage) + this.dotActive.getIntrinsicWidth();
        this.righttCenter = i + ((intrinsicWidth - i) * 0.5f);
        startAnimation(this.mRightEdgeAnimation, i, intrinsicWidth);
    }

    private void startAnimation(SpringAnimation animation, int startValue, int endValue) {
        if (animation != null) {
            animation.setStartValue(startValue);
            animation.animateToFinalPosition(endValue);
        }
    }

    private void cancelSwitchAnimation() {
        this.moveType = 0;
        SpringAnimation springAnimation = this.mLeftEdgeAnimation;
        if (springAnimation != null && springAnimation.isRunning()) {
            this.mLeftEdgeAnimation.cancel();
        }
        SpringAnimation springAnimation2 = this.mRightEdgeAnimation;
        if (springAnimation2 == null || !springAnimation2.isRunning()) {
            return;
        }
        this.mRightEdgeAnimation.cancel();
    }

    private boolean isSwitchAnimation() {
        SpringAnimation springAnimation = this.mLeftEdgeAnimation;
        if (springAnimation != null && springAnimation.isRunning()) {
            return true;
        }
        SpringAnimation springAnimation2 = this.mRightEdgeAnimation;
        return springAnimation2 != null && springAnimation2.isRunning();
    }

    public void setCurrentPage(int curPage) {
        int i = this.curPage;
        this.curPage = curPage;
        startSwitchAnimation(curPage, i);
        invalidatePager();
    }

    public void setPages(int pages) {
        this.pages = Math.max(1, pages);
        invalidatePager();
    }

    private void invalidatePager() {
        invalidate(0, 0, getWidth(), this.drawingBound);
    }
}
