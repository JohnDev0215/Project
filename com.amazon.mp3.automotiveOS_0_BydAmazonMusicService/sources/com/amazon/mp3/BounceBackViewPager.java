package com.amazon.mp3;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BounceBackViewPager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u000212B\u0017\u0012\u0006\u0010,\u001a\u00020+\u0012\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b/\u00100J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0014R\u0018\u0010\u0012\u001a\u00060\u0011R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0016\u0010 \u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010\u001bR\u0014\u0010!\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\u001eR\u0016\u0010\"\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010\u001bR\u0016\u0010#\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010\u001eR\u0016\u0010$\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010\u001eR\u0014\u0010%\u001a\u00020\u001c8\u0002X\u0082D¢\u0006\u0006\n\u0004\b%\u0010\u001eR\"\u0010&\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b&\u0010(\"\u0004\b)\u0010*¨\u00063"}, d2 = {"Lcom/amazon/mp3/BounceBackViewPager;", "Landroidx/viewpager/widget/ViewPager;", "", "invalidateVisibleChildren", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnPageChangeListener", "Landroid/view/MotionEvent;", "ev", "", "onInterceptTouchEvent", "onTouchEvent", "Landroid/view/View;", "child", "Landroid/view/animation/Transformation;", "t", "getChildStaticTransformation", "Lcom/amazon/mp3/BounceBackViewPager$OverscrollEffect;", "mOverscrollEffect", "Lcom/amazon/mp3/BounceBackViewPager$OverscrollEffect;", "Landroid/graphics/Camera;", "mCamera", "Landroid/graphics/Camera;", "mScrollListener", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "", "mLastMotionX", "F", "", "mActivePointerId", "I", "mScrollPosition", "mScrollPositionOffset", "mTouchSlop", "overscrollTranslation", "overscrollAnimationDuration", "mLastPosition", "invalidPointerId", "isPageSwipeEnabled", "Z", "()Z", "setPageSwipeEnabled", "(Z)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "OverscrollEffect", "StoryViewerPageChangeListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BounceBackViewPager extends ViewPager {
    private final int invalidPointerId;
    private boolean isPageSwipeEnabled;
    private int mActivePointerId;
    private final Camera mCamera;
    private float mLastMotionX;
    private int mLastPosition;
    private final OverscrollEffect mOverscrollEffect;
    private ViewPager.OnPageChangeListener mScrollListener;
    private int mScrollPosition;
    private float mScrollPositionOffset;
    private final int mTouchSlop;
    private int overscrollAnimationDuration;
    private float overscrollTranslation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BounceBackViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mOverscrollEffect = new OverscrollEffect(this);
        this.mCamera = new Camera();
        this.invalidPointerId = -1;
        this.isPageSwipeEnabled = true;
        setStaticTransformationsEnabled(true);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
        super.addOnPageChangeListener(new StoryViewerPageChangeListener(this));
        this.overscrollTranslation = 150.0f;
        this.overscrollAnimationDuration = 300;
    }

    public final void setPageSwipeEnabled(boolean z) {
        this.isPageSwipeEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: BounceBackViewPager.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0006J\b\u0010\u0011\u001a\u00020\u000eH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/BounceBackViewPager$OverscrollEffect;", "", "(Lcom/amazon/mp3/BounceBackViewPager;)V", "mAnimator", "Landroid/animation/Animator;", "mOverscroll", "", "getMOverscroll", "()F", "setMOverscroll", "(F)V", "isOverScrolling", "", "onRelease", "", "setPull", "deltaDistance", "startAnimation", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class OverscrollEffect {
        private Animator mAnimator;
        private float mOverscroll;
        final /* synthetic */ BounceBackViewPager this$0;

        public OverscrollEffect(BounceBackViewPager this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        public final float getMOverscroll() {
            return this.mOverscroll;
        }

        public final void setMOverscroll(float f) {
            this.mOverscroll = f;
        }

        public final void setPull(float deltaDistance) {
            this.mOverscroll = deltaDistance;
            this.this$0.invalidateVisibleChildren();
        }

        public final void onRelease() {
            Animator animator = this.mAnimator;
            if (animator != null) {
                boolean z = false;
                if (animator != null && animator.isRunning()) {
                    z = true;
                }
                if (z) {
                    Animator animator2 = this.mAnimator;
                    if (animator2 != null) {
                        animator2.addListener(new Animator.AnimatorListener() { // from class: com.amazon.mp3.BounceBackViewPager$OverscrollEffect$onRelease$1
                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationCancel(Animator animation) {
                                Intrinsics.checkNotNullParameter(animation, "animation");
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationRepeat(Animator animation) {
                                Intrinsics.checkNotNullParameter(animation, "animation");
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationStart(Animator animation) {
                                Intrinsics.checkNotNullParameter(animation, "animation");
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(Animator animation) {
                                Intrinsics.checkNotNullParameter(animation, "animation");
                                this.this$0.startAnimation();
                            }
                        });
                    }
                    Animator animator3 = this.mAnimator;
                    if (animator3 == null) {
                        return;
                    }
                    animator3.cancel();
                    return;
                }
            }
            startAnimation();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void startAnimation() {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "pull", this.mOverscroll, 0.0f);
            this.mAnimator = objectAnimatorOfFloat;
            if (objectAnimatorOfFloat != null) {
                objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
            }
            float fAbs = Math.abs(-this.mOverscroll);
            Animator animator = this.mAnimator;
            if (animator != null) {
                animator.setDuration((long) (this.this$0.overscrollAnimationDuration * fAbs));
            }
            Animator animator2 = this.mAnimator;
            if (animator2 == null) {
                return;
            }
            animator2.start();
        }

        public final boolean isOverScrolling() {
            if (this.this$0.mScrollPosition == 0 && this.mOverscroll < 0.0f) {
                return true;
            }
            PagerAdapter adapter = this.this$0.getAdapter();
            return ((adapter == null ? 0 : adapter.getCount()) - 1 == this.this$0.mScrollPosition) && this.mOverscroll > 0.0f;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mScrollListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateVisibleChildren() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).invalidate();
        }
    }

    /* JADX INFO: compiled from: BounceBackViewPager.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/BounceBackViewPager$StoryViewerPageChangeListener;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "(Lcom/amazon/mp3/BounceBackViewPager;)V", "onPageScrollStateChanged", "", "state", "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class StoryViewerPageChangeListener implements ViewPager.OnPageChangeListener {
        final /* synthetic */ BounceBackViewPager this$0;

        public StoryViewerPageChangeListener(BounceBackViewPager this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            ViewPager.OnPageChangeListener onPageChangeListener;
            if (this.this$0.mScrollListener != null && (onPageChangeListener = this.this$0.mScrollListener) != null) {
                onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            this.this$0.mScrollPosition = position;
            this.this$0.mScrollPositionOffset = positionOffset;
            this.this$0.mLastPosition = position;
            this.this$0.invalidateVisibleChildren();
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            ViewPager.OnPageChangeListener onPageChangeListener;
            if (this.this$0.mScrollListener == null || (onPageChangeListener = this.this$0.mScrollListener) == null) {
                return;
            }
            onPageChangeListener.onPageSelected(position);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int state) {
            ViewPager.OnPageChangeListener onPageChangeListener;
            if (this.this$0.mScrollListener != null && (onPageChangeListener = this.this$0.mScrollListener) != null) {
                onPageChangeListener.onPageScrollStateChanged(state);
            }
            if (state == 0) {
                this.this$0.mScrollPositionOffset = 0.0f;
            }
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        try {
            int action = ev.getAction() & 255;
            if (action == 0) {
                this.mLastMotionX = ev.getX();
                this.mActivePointerId = ev.getPointerId(0);
            } else if (action == 5) {
                int actionIndex = ev.getActionIndex();
                this.mLastMotionX = ev.getX(actionIndex);
                this.mActivePointerId = ev.getPointerId(actionIndex);
            }
            if (this.isPageSwipeEnabled) {
                return super.onInterceptTouchEvent(ev);
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e7 A[Catch: Exception -> 0x00ff, TRY_ENTER, TryCatch #0 {Exception -> 0x00ff, blocks: (B:22:0x0042, B:54:0x00e7, B:55:0x00f1), top: B:75:0x000b }] */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r12) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.BounceBackViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean getChildStaticTransformation(android.view.View r8, android.view.animation.Transformation r9) {
        /*
            r7 = this;
            java.lang.String r0 = "child"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "t"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            int r0 = r8.getWidth()
            r1 = 0
            if (r0 != 0) goto L13
            return r1
        L13:
            int r0 = r8.getLeft()
            int r2 = r8.getWidth()
            int r0 = r0 / r2
            r2 = 1
            if (r0 == 0) goto L31
            androidx.viewpager.widget.PagerAdapter r3 = r7.getAdapter()
            if (r3 != 0) goto L27
            r3 = r1
            goto L2b
        L27:
            int r3 = r3.getCount()
        L2b:
            int r3 = r3 - r2
            if (r0 != r3) goto L2f
            goto L31
        L2f:
            r0 = r1
            goto L32
        L31:
            r0 = r2
        L32:
            com.amazon.mp3.BounceBackViewPager$OverscrollEffect r3 = r7.mOverscrollEffect
            boolean r3 = r3.isOverScrolling()
            if (r3 == 0) goto Lb0
            if (r0 == 0) goto Lb0
            int r0 = r7.getWidth()
            int r0 = r0 / 2
            float r0 = (float) r0
            int r1 = r7.getHeight()
            int r1 = r1 / 2
            android.graphics.Matrix r3 = r9.getMatrix()
            r3.reset()
            float r3 = r7.overscrollTranslation
            com.amazon.mp3.BounceBackViewPager$OverscrollEffect r4 = r7.mOverscrollEffect
            float r4 = r4.getMOverscroll()
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L6a
            com.amazon.mp3.BounceBackViewPager$OverscrollEffect r4 = r7.mOverscrollEffect
            float r4 = r4.getMOverscroll()
            r6 = 1065353216(0x3f800000, float:1.0)
            float r4 = kotlin.ranges.RangesKt.coerceAtMost(r4, r6)
            goto L76
        L6a:
            com.amazon.mp3.BounceBackViewPager$OverscrollEffect r4 = r7.mOverscrollEffect
            float r4 = r4.getMOverscroll()
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r4 = kotlin.ranges.RangesKt.coerceAtLeast(r4, r6)
        L76:
            float r3 = r3 * r4
            android.graphics.Camera r4 = r7.mCamera
            r4.save()
            android.graphics.Camera r4 = r7.mCamera
            float r3 = -r3
            r4.translate(r3, r5, r5)
            android.graphics.Camera r3 = r7.mCamera
            android.graphics.Matrix r4 = r9.getMatrix()
            r3.getMatrix(r4)
            android.graphics.Camera r3 = r7.mCamera
            r3.restore()
            android.graphics.Matrix r3 = r9.getMatrix()
            float r4 = -r0
            int r5 = -r1
            float r5 = (float) r5
            r3.preTranslate(r4, r5)
            android.graphics.Matrix r9 = r9.getMatrix()
            float r1 = (float) r1
            r9.postTranslate(r0, r1)
            int r9 = r7.getChildCount()
            if (r9 != r2) goto Lac
            r7.invalidate()
            goto Laf
        Lac:
            r8.invalidate()
        Laf:
            return r2
        Lb0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.BounceBackViewPager.getChildStaticTransformation(android.view.View, android.view.animation.Transformation):boolean");
    }
}
