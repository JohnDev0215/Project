package com.android.launcher3;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes4.dex */
public class FirstFrameAnimatorHelper implements ViewTreeObserver.OnDrawListener, View.OnAttachStateChangeListener {
    private static final boolean DEBUG = false;
    private static final int MAX_DELAY = 1000;
    private static final String TAG = "FirstFrameAnimatorHlpr";
    private long mGlobalFrameCount;
    private View mRootView;

    public FirstFrameAnimatorHelper(View target) {
        target.addOnAttachStateChangeListener(this);
        if (target.isAttachedToWindow()) {
            onViewAttachedToWindow(target);
        }
    }

    public <T extends ValueAnimator> T addTo(T anim) {
        anim.addUpdateListener(new MyListener());
        return anim;
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public void onDraw() {
        this.mGlobalFrameCount++;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        View rootView = view.getRootView();
        this.mRootView = rootView;
        rootView.getViewTreeObserver().addOnDrawListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        View view2 = this.mRootView;
        if (view2 != null) {
            view2.getViewTreeObserver().removeOnDrawListener(this);
            this.mRootView = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class MyListener implements ValueAnimator.AnimatorUpdateListener {
        private boolean mAdjustedSecondFrameTime;
        private boolean mHandlingOnAnimationUpdate;
        private long mStartFrame;
        private long mStartTime;

        private MyListener() {
            this.mStartTime = -1L;
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x009d  */
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onAnimationUpdate(final android.animation.ValueAnimator r16) {
            /*
                r15 = this;
                r0 = r15
                r1 = r16
                long r2 = java.lang.System.currentTimeMillis()
                long r4 = r0.mStartTime
                r6 = -1
                int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r4 != 0) goto L19
                com.android.launcher3.FirstFrameAnimatorHelper r4 = com.android.launcher3.FirstFrameAnimatorHelper.this
                long r4 = com.android.launcher3.FirstFrameAnimatorHelper.access$100(r4)
                r0.mStartFrame = r4
                r0.mStartTime = r2
            L19:
                long r4 = r16.getCurrentPlayTime()
                r6 = 1065353216(0x3f800000, float:1.0)
                float r7 = r16.getAnimatedFraction()
                int r6 = java.lang.Float.compare(r6, r7)
                r8 = 1
                if (r6 != 0) goto L2c
                r6 = r8
                goto L2d
            L2c:
                r6 = 0
            L2d:
                boolean r9 = r0.mHandlingOnAnimationUpdate
                if (r9 != 0) goto Lb0
                com.android.launcher3.FirstFrameAnimatorHelper r9 = com.android.launcher3.FirstFrameAnimatorHelper.this
                android.view.View r9 = com.android.launcher3.FirstFrameAnimatorHelper.access$200(r9)
                if (r9 == 0) goto Lb0
                com.android.launcher3.FirstFrameAnimatorHelper r9 = com.android.launcher3.FirstFrameAnimatorHelper.this
                android.view.View r9 = com.android.launcher3.FirstFrameAnimatorHelper.access$200(r9)
                int r9 = r9.getWindowVisibility()
                if (r9 != 0) goto Lb0
                long r9 = r16.getDuration()
                int r9 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
                if (r9 >= 0) goto Lb0
                if (r6 != 0) goto Lb0
                r0.mHandlingOnAnimationUpdate = r8
                com.android.launcher3.FirstFrameAnimatorHelper r6 = com.android.launcher3.FirstFrameAnimatorHelper.this
                long r9 = com.android.launcher3.FirstFrameAnimatorHelper.access$100(r6)
                long r11 = r0.mStartFrame
                long r9 = r9 - r11
                r11 = 0
                int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                r13 = 1000(0x3e8, double:4.94E-321)
                if (r6 != 0) goto L7a
                long r7 = r0.mStartTime
                long r7 = r7 + r13
                int r7 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r7 >= 0) goto L7a
                int r7 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
                if (r7 <= 0) goto L7a
                com.android.launcher3.FirstFrameAnimatorHelper r2 = com.android.launcher3.FirstFrameAnimatorHelper.this
                android.view.View r2 = com.android.launcher3.FirstFrameAnimatorHelper.access$200(r2)
                r2.invalidate()
                r1.setCurrentPlayTime(r11)
                goto Lad
            L7a:
                r7 = 1
                int r7 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r7 != 0) goto L9d
                long r8 = r0.mStartTime
                long r13 = r13 + r8
                int r10 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
                if (r10 >= 0) goto L9d
                boolean r10 = r0.mAdjustedSecondFrameTime
                if (r10 != 0) goto L9d
                r10 = 16
                long r8 = r8 + r10
                int r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
                if (r2 <= 0) goto L9d
                int r2 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
                if (r2 <= 0) goto L9d
                r1.setCurrentPlayTime(r10)
                r1 = 1
                r0.mAdjustedSecondFrameTime = r1
                goto Lad
            L9d:
                if (r7 <= 0) goto Lad
                com.android.launcher3.FirstFrameAnimatorHelper r2 = com.android.launcher3.FirstFrameAnimatorHelper.this
                android.view.View r2 = com.android.launcher3.FirstFrameAnimatorHelper.access$200(r2)
                com.android.launcher3.-$$Lambda$FirstFrameAnimatorHelper$MyListener$7PIWiJXH7s7cnnqG7u-lljZ4aj0 r3 = new com.android.launcher3.-$$Lambda$FirstFrameAnimatorHelper$MyListener$7PIWiJXH7s7cnnqG7u-lljZ4aj0
                r3.<init>()
                r2.post(r3)
            Lad:
                r1 = 0
                r0.mHandlingOnAnimationUpdate = r1
            Lb0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.FirstFrameAnimatorHelper.MyListener.onAnimationUpdate(android.animation.ValueAnimator):void");
        }

        public /* synthetic */ void lambda$onAnimationUpdate$0$FirstFrameAnimatorHelper$MyListener(ValueAnimator valueAnimator) {
            valueAnimator.removeUpdateListener(this);
        }

        public void print(ValueAnimator animation) {
            Log.d(FirstFrameAnimatorHelper.TAG, FirstFrameAnimatorHelper.this.mGlobalFrameCount + "(" + (FirstFrameAnimatorHelper.this.mGlobalFrameCount - this.mStartFrame) + ") " + FirstFrameAnimatorHelper.this.mRootView + " dirty? " + FirstFrameAnimatorHelper.this.mRootView.isDirty() + " " + (animation.getCurrentPlayTime() / animation.getDuration()) + " " + this + " " + animation);
        }
    }
}
