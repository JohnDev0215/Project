package me.everything.android.ui.overscroll;

import android.view.MotionEvent;
import android.view.View;
import me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase;
import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class HorizontalOverScrollBounceEffectDecorator extends OverScrollBounceEffectDecoratorBase {

    protected static class MotionAttributesHorizontal extends OverScrollBounceEffectDecoratorBase.MotionAttributes {
        protected MotionAttributesHorizontal() {
        }

        @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase.MotionAttributes
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

        @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase.AnimationAttributes
        protected void init(View view) {
            this.mAbsOffset = view.getTranslationX();
            this.mMaxOffset = view.getWidth();
        }
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter) {
        this(iOverScrollDecoratorAdapter, 3.0f, 1.0f, -2.0f);
    }

    public HorizontalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, float f2, float f3) {
        super(iOverScrollDecoratorAdapter, f3, f, f2);
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.MotionAttributes createMotionAttributes() {
        return new MotionAttributesHorizontal();
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.AnimationAttributes createAnimationAttributes() {
        return new AnimationAttributesHorizontal();
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateView(View view, float f) {
        view.setTranslationX(f);
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateViewAndEvent(View view, float f, MotionEvent motionEvent) {
        view.setTranslationX(f);
        motionEvent.offsetLocation(f - motionEvent.getX(0), 0.0f);
    }
}
