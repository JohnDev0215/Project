package me.everything.android.ui.overscroll;

import android.view.MotionEvent;
import android.view.View;
import me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase;
import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class VerticalOverScrollBounceEffectDecorator extends OverScrollBounceEffectDecoratorBase {

    protected static class MotionAttributesVertical extends OverScrollBounceEffectDecoratorBase.MotionAttributes {
        protected MotionAttributesVertical() {
        }

        @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase.MotionAttributes
        public boolean init(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.mAbsOffset = view.getTranslationY();
            this.mDeltaOffset = y;
            this.mDir = this.mDeltaOffset > 0.0f;
            return true;
        }
    }

    protected static class AnimationAttributesVertical extends OverScrollBounceEffectDecoratorBase.AnimationAttributes {
        public AnimationAttributesVertical() {
            this.mProperty = View.TRANSLATION_Y;
        }

        @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase.AnimationAttributes
        protected void init(View view) {
            this.mAbsOffset = view.getTranslationY();
            this.mMaxOffset = view.getHeight();
        }
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter) {
        this(iOverScrollDecoratorAdapter, 3.0f, 1.0f, -2.0f);
    }

    public VerticalOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, float f2, float f3) {
        super(iOverScrollDecoratorAdapter, f3, f, f2);
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.MotionAttributes createMotionAttributes() {
        return new MotionAttributesVertical();
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected OverScrollBounceEffectDecoratorBase.AnimationAttributes createAnimationAttributes() {
        return new AnimationAttributesVertical();
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateView(View view, float f) {
        view.setTranslationY(f);
    }

    @Override // me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase
    protected void translateViewAndEvent(View view, float f, MotionEvent motionEvent) {
        view.setTranslationY(f);
        motionEvent.offsetLocation(f - motionEvent.getY(0), 0.0f);
    }
}
