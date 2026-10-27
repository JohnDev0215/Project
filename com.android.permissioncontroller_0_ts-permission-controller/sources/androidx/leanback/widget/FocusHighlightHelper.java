package androidx.leanback.widget;

import android.animation.TimeAnimator;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.leanback.R;
import androidx.leanback.graphics.ColorOverlayDimmer;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.RowHeaderPresenter;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class FocusHighlightHelper {
    static boolean isValidZoomIndex(int zoomIndex) {
        return zoomIndex == 0 || getResId(zoomIndex) > 0;
    }

    static int getResId(int zoomIndex) {
        if (zoomIndex == 1) {
            return R.fraction.lb_focus_zoom_factor_small;
        }
        if (zoomIndex == 2) {
            return R.fraction.lb_focus_zoom_factor_medium;
        }
        if (zoomIndex == 3) {
            return R.fraction.lb_focus_zoom_factor_large;
        }
        if (zoomIndex == 4) {
            return R.fraction.lb_focus_zoom_factor_xsmall;
        }
        return 0;
    }

    static class FocusAnimator implements TimeAnimator.TimeListener {
        private final ColorOverlayDimmer mDimmer;
        private final int mDuration;
        private float mFocusLevelDelta;
        private float mFocusLevelStart;
        private final float mScaleDiff;
        private final View mView;
        private final ShadowOverlayContainer mWrapper;
        private float mFocusLevel = 0.0f;
        private final TimeAnimator mAnimator = new TimeAnimator();
        private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

        void animateFocus(boolean select, boolean immediate) {
            endAnimation();
            float end = select ? 1.0f : 0.0f;
            if (immediate) {
                setFocusLevel(end);
                return;
            }
            float f = this.mFocusLevel;
            if (f != end) {
                this.mFocusLevelStart = f;
                this.mFocusLevelDelta = end - f;
                this.mAnimator.start();
            }
        }

        FocusAnimator(View view, float scale, boolean useDimmer, int duration) {
            this.mView = view;
            this.mDuration = duration;
            this.mScaleDiff = scale - 1.0f;
            if (view instanceof ShadowOverlayContainer) {
                this.mWrapper = (ShadowOverlayContainer) view;
            } else {
                this.mWrapper = null;
            }
            this.mAnimator.setTimeListener(this);
            if (useDimmer) {
                this.mDimmer = ColorOverlayDimmer.createDefault(view.getContext());
            } else {
                this.mDimmer = null;
            }
        }

        void setFocusLevel(float level) {
            this.mFocusLevel = level;
            float scale = (this.mScaleDiff * level) + 1.0f;
            this.mView.setScaleX(scale);
            this.mView.setScaleY(scale);
            ShadowOverlayContainer shadowOverlayContainer = this.mWrapper;
            if (shadowOverlayContainer != null) {
                shadowOverlayContainer.setShadowFocusLevel(level);
            } else {
                ShadowOverlayHelper.setNoneWrapperShadowFocusLevel(this.mView, level);
            }
            ColorOverlayDimmer colorOverlayDimmer = this.mDimmer;
            if (colorOverlayDimmer != null) {
                colorOverlayDimmer.setActiveLevel(level);
                int color = this.mDimmer.getPaint().getColor();
                ShadowOverlayContainer shadowOverlayContainer2 = this.mWrapper;
                if (shadowOverlayContainer2 != null) {
                    shadowOverlayContainer2.setOverlayColor(color);
                } else {
                    ShadowOverlayHelper.setNoneWrapperOverlayColor(this.mView, color);
                }
            }
        }

        float getFocusLevel() {
            return this.mFocusLevel;
        }

        void endAnimation() {
            this.mAnimator.end();
        }

        @Override // android.animation.TimeAnimator.TimeListener
        public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
            float fraction;
            int i = this.mDuration;
            if (totalTime >= i) {
                fraction = 1.0f;
                this.mAnimator.end();
            } else {
                fraction = (float) (totalTime / ((double) i));
            }
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                fraction = interpolator.getInterpolation(fraction);
            }
            setFocusLevel(this.mFocusLevelStart + (this.mFocusLevelDelta * fraction));
        }
    }

    static class BrowseItemFocusHighlight implements FocusHighlightHandler {
        private static final int DURATION_MS = 150;
        private int mScaleIndex;
        private final boolean mUseDimmer;

        BrowseItemFocusHighlight(int zoomIndex, boolean useDimmer) {
            if (!FocusHighlightHelper.isValidZoomIndex(zoomIndex)) {
                throw new IllegalArgumentException("Unhandled zoom index");
            }
            this.mScaleIndex = zoomIndex;
            this.mUseDimmer = useDimmer;
        }

        private float getScale(Resources res) {
            int i = this.mScaleIndex;
            if (i == 0) {
                return 1.0f;
            }
            return res.getFraction(FocusHighlightHelper.getResId(i), 1, 1);
        }

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onItemFocused(View view, boolean hasFocus) {
            view.setSelected(hasFocus);
            getOrCreateAnimator(view).animateFocus(hasFocus, false);
        }

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onInitializeView(View view) {
            getOrCreateAnimator(view).animateFocus(false, true);
        }

        private FocusAnimator getOrCreateAnimator(View view) {
            FocusAnimator animator = (FocusAnimator) view.getTag(R.id.lb_focus_animator);
            if (animator == null) {
                FocusAnimator animator2 = new FocusAnimator(view, getScale(view.getResources()), this.mUseDimmer, DURATION_MS);
                view.setTag(R.id.lb_focus_animator, animator2);
                return animator2;
            }
            return animator;
        }
    }

    public static void setupBrowseItemFocusHighlight(ItemBridgeAdapter adapter, int zoomIndex, boolean useDimmer) {
        if (zoomIndex == 0 && !useDimmer) {
            adapter.setFocusHighlight(null);
        } else {
            adapter.setFocusHighlight(new BrowseItemFocusHighlight(zoomIndex, useDimmer));
        }
    }

    @Deprecated
    public static void setupHeaderItemFocusHighlight(VerticalGridView gridView) {
        setupHeaderItemFocusHighlight(gridView, true);
    }

    @Deprecated
    public static void setupHeaderItemFocusHighlight(VerticalGridView gridView, boolean scaleEnabled) {
        if (gridView != null && (gridView.getAdapter() instanceof ItemBridgeAdapter)) {
            ((ItemBridgeAdapter) gridView.getAdapter()).setFocusHighlight(scaleEnabled ? new HeaderItemFocusHighlight() : null);
        }
    }

    public static void setupHeaderItemFocusHighlight(ItemBridgeAdapter adapter) {
        setupHeaderItemFocusHighlight(adapter, true);
    }

    public static void setupHeaderItemFocusHighlight(ItemBridgeAdapter adapter, boolean scaleEnabled) {
        adapter.setFocusHighlight(scaleEnabled ? new HeaderItemFocusHighlight() : null);
    }

    static class HeaderItemFocusHighlight implements FocusHighlightHandler {
        private int mDuration;
        private boolean mInitialized;
        private float mSelectScale;

        HeaderItemFocusHighlight() {
        }

        void lazyInit(View view) {
            if (!this.mInitialized) {
                Resources res = view.getResources();
                TypedValue value = new TypedValue();
                res.getValue(R.dimen.lb_browse_header_select_scale, value, true);
                this.mSelectScale = value.getFloat();
                res.getValue(R.dimen.lb_browse_header_select_duration, value, true);
                this.mDuration = value.data;
                this.mInitialized = true;
            }
        }

        static class HeaderFocusAnimator extends FocusAnimator {
            ItemBridgeAdapter.ViewHolder mViewHolder;

            HeaderFocusAnimator(View view, float scale, int duration) {
                super(view, scale, false, duration);
                ViewParent parent = view.getParent();
                while (parent != null && !(parent instanceof RecyclerView)) {
                    parent = parent.getParent();
                }
                if (parent != null) {
                    this.mViewHolder = (ItemBridgeAdapter.ViewHolder) ((RecyclerView) parent).getChildViewHolder(view);
                }
            }

            @Override // androidx.leanback.widget.FocusHighlightHelper.FocusAnimator
            void setFocusLevel(float level) {
                Presenter presenter = this.mViewHolder.getPresenter();
                if (presenter instanceof RowHeaderPresenter) {
                    ((RowHeaderPresenter) presenter).setSelectLevel((RowHeaderPresenter.ViewHolder) this.mViewHolder.getViewHolder(), level);
                }
                super.setFocusLevel(level);
            }
        }

        private void viewFocused(View view, boolean hasFocus) {
            lazyInit(view);
            view.setSelected(hasFocus);
            FocusAnimator animator = (FocusAnimator) view.getTag(R.id.lb_focus_animator);
            if (animator == null) {
                animator = new HeaderFocusAnimator(view, this.mSelectScale, this.mDuration);
                view.setTag(R.id.lb_focus_animator, animator);
            }
            animator.animateFocus(hasFocus, false);
        }

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onItemFocused(View view, boolean hasFocus) {
            viewFocused(view, hasFocus);
        }

        @Override // androidx.leanback.widget.FocusHighlightHandler
        public void onInitializeView(View view) {
        }
    }

    @Deprecated
    public FocusHighlightHelper() {
    }
}
