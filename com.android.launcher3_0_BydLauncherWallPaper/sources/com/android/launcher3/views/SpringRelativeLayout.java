package com.android.launcher3.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.RelativeLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class SpringRelativeLayout extends RelativeLayout {
    private static final FloatPropertyCompat<SpringRelativeLayout> DAMPED_SCROLL = new FloatPropertyCompat<SpringRelativeLayout>("value") { // from class: com.android.launcher3.views.SpringRelativeLayout.1
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(SpringRelativeLayout object) {
            return object.mDampedScrollShift;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(SpringRelativeLayout object, float value) {
            object.setDampedScrollShift(value);
        }
    };
    private static final float DAMPING_RATIO = 0.5f;
    private static final float STIFFNESS = 850.0f;
    private static final float VELOCITY_MULTIPLIER = 0.3f;
    private SpringEdgeEffect mActiveEdge;
    private float mDampedScrollShift;
    private final SpringAnimation mSpring;
    protected final SparseBooleanArray mSpringViews;

    public int getCanvasClipTopForOverscroll() {
        return 0;
    }

    public SpringRelativeLayout(Context context) {
        this(context, null);
    }

    public SpringRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mSpringViews = new SparseBooleanArray();
        this.mDampedScrollShift = 0.0f;
        SpringAnimation springAnimation = new SpringAnimation(this, DAMPED_SCROLL, 0.0f);
        this.mSpring = springAnimation;
        springAnimation.setSpring(new SpringForce(0.0f).setStiffness(STIFFNESS).setDampingRatio(0.5f));
    }

    public void addSpringView(int id) {
        this.mSpringViews.put(id, true);
    }

    public void removeSpringView(int id) {
        this.mSpringViews.delete(id);
        invalidate();
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (this.mDampedScrollShift != 0.0f && this.mSpringViews.get(child.getId())) {
            int iSave = canvas.save();
            canvas.clipRect(0, getCanvasClipTopForOverscroll(), getWidth(), getHeight());
            canvas.translate(0.0f, this.mDampedScrollShift);
            boolean zDrawChild = super.drawChild(canvas, child, drawingTime);
            canvas.restoreToCount(iSave);
            return zDrawChild;
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActiveEdge(SpringEdgeEffect edge) {
        SpringEdgeEffect springEdgeEffect = this.mActiveEdge;
        if (springEdgeEffect != edge && springEdgeEffect != null) {
            springEdgeEffect.mDistance = 0.0f;
        }
        this.mActiveEdge = edge;
    }

    protected void setDampedScrollShift(float shift) {
        if (shift != this.mDampedScrollShift) {
            this.mDampedScrollShift = shift;
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishScrollWithVelocity(float velocity) {
        this.mSpring.setStartVelocity(velocity);
        this.mSpring.setStartValue(this.mDampedScrollShift);
        this.mSpring.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finishWithShiftAndVelocity(float shift, float velocity, DynamicAnimation.OnAnimationEndListener listener) {
        setDampedScrollShift(shift);
        this.mSpring.addEndListener(listener);
        finishScrollWithVelocity(velocity);
    }

    public RecyclerView.EdgeEffectFactory createEdgeEffectFactory() {
        return new SpringEdgeEffectFactory();
    }

    private class SpringEdgeEffectFactory extends RecyclerView.EdgeEffectFactory {
        private SpringEdgeEffectFactory() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory
        protected EdgeEffect createEdgeEffect(RecyclerView view, int direction) {
            if (direction == 1) {
                SpringRelativeLayout springRelativeLayout = SpringRelativeLayout.this;
                return springRelativeLayout.new SpringEdgeEffect(springRelativeLayout.getContext(), SpringRelativeLayout.VELOCITY_MULTIPLIER);
            }
            if (direction == 3) {
                SpringRelativeLayout springRelativeLayout2 = SpringRelativeLayout.this;
                return springRelativeLayout2.new SpringEdgeEffect(springRelativeLayout2.getContext(), -0.3f);
            }
            return super.createEdgeEffect(view, direction);
        }
    }

    private class SpringEdgeEffect extends EdgeEffect {
        private float mDistance;
        private final float mVelocityMultiplier;

        @Override // android.widget.EdgeEffect
        public boolean draw(Canvas canvas) {
            return false;
        }

        public SpringEdgeEffect(Context context, float velocityMultiplier) {
            super(context);
            this.mVelocityMultiplier = velocityMultiplier;
        }

        @Override // android.widget.EdgeEffect
        public void onAbsorb(int velocity) {
            SpringRelativeLayout.this.finishScrollWithVelocity(velocity * this.mVelocityMultiplier);
        }

        @Override // android.widget.EdgeEffect
        public void onPull(float deltaDistance, float displacement) {
            SpringRelativeLayout.this.setActiveEdge(this);
            float f = this.mDistance + (deltaDistance * (this.mVelocityMultiplier / 3.0f));
            this.mDistance = f;
            SpringRelativeLayout.this.setDampedScrollShift(f * r2.getHeight());
        }

        @Override // android.widget.EdgeEffect
        public void onRelease() {
            this.mDistance = 0.0f;
            SpringRelativeLayout.this.finishScrollWithVelocity(0.0f);
        }
    }
}
