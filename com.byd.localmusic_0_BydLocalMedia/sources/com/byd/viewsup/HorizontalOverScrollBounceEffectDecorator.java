package com.byd.viewsup;

import android.view.MotionEvent;
import android.view.View;
import com.byd.viewsup.OverScrollBounceEffectDecoratorBase;
import com.byd.viewsup.adapters.IOverScrollDecoratorAdapter;

/* JADX INFO: loaded from: classes5.dex */
public class HorizontalOverScrollBounceEffectDecorator extends OverScrollBounceEffectDecoratorBase {
    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteStart() {
        return this.mViewAdapter.isInAbsoluteStart();
    }

    @Override // com.byd.viewsup.adapters.IOverScroller
    public boolean isInAbsoluteEnd() {
        return this.mViewAdapter.isInAbsoluteEnd();
    }

    protected static class MotionAttributesHorizontal extends OverScrollBounceEffectDecoratorBase.MotionAttributes {
        protected MotionAttributesHorizontal() {
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.MotionAttributes
        public boolean init(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            float x = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
            if (Math.abs(x) < Math.abs(y)) {
                return false;
            }
            this.mAbsOffset = view.getTranslationX();
            this.mDeltaOffset = x;
            this.mDir = this.mDeltaOffset > 0.0f;
            return true;
        }
    }

    protected static class AnimationAttributesHorizontal extends OverScrollBounceEffectDecoratorBase.AnimationAttributes {
        public AnimationAttributesHorizontal() {
            this.mProperty = View.TRANSLATION_X;
        }

        @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase.AnimationAttributes
        protected void init(View view) {
            this.mAbsOffset = view.getTranslationX();
            this.mMaxOffset = view.getWidth();
        }
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter) {
        this(iOverScrollDecoratorAdapter, 3.0f, 1.0f, -72000.0f, Float.MAX_VALUE, -1L, 1.0f);
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, long j) {
        this(iOverScrollDecoratorAdapter, 3.0f, 1.0f, -72000.0f, f, j, 1.0f);
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, long j, float f2) {
        this(iOverScrollDecoratorAdapter, 3.0f, 1.0f, -72000.0f, f, j, f2);
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, float f2, float f3, float f4, long j, float f5) {
        super(iOverScrollDecoratorAdapter, f3, f, f2, f4, j, f5);
    }

    @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.MotionAttributes createMotionAttributes() {
        return new MotionAttributesHorizontal();
    }

    @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.AnimationAttributes createAnimationAttributes() {
        return new AnimationAttributesHorizontal();
    }

    @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase
    protected void translateView(View view, float f) {
        view.setTranslationX(f);
    }

    @Override // com.byd.viewsup.OverScrollBounceEffectDecoratorBase
    protected void translateViewAndEvent(View view, float f, MotionEvent motionEvent) {
        view.setTranslationX(f);
        motionEvent.offsetLocation(f - motionEvent.getX(0), 0.0f);
    }
}
