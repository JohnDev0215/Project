package com.android.car.ui.core;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.res.TypedArray;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.activity.result.ActivityResultCaller;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentActivity;
import com.android.car.ui.R;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.baselayout.InsetsChangedListener;
import com.android.car.ui.toolbar.ToolbarController;
import com.android.car.ui.toolbar.ToolbarControllerImpl;
import com.android.car.ui.utils.CarUiUtils;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes6.dex */
final class BaseLayoutController {
    private static final Map<Activity, BaseLayoutController> sBaseLayoutMap = new WeakHashMap();
    private InsetsUpdater mInsetsUpdater;
    private ToolbarController mToolbarController;

    static BaseLayoutController getBaseLayout(Activity activity) {
        return sBaseLayoutMap.get(activity);
    }

    private BaseLayoutController(Activity activity) {
        installBaseLayout(activity);
    }

    static void build(Activity activity) {
        if (getThemeBoolean(activity, R.attr.carUiBaseLayout)) {
            sBaseLayoutMap.put(activity, new BaseLayoutController(activity));
        }
    }

    static void destroy(Activity activity) {
        sBaseLayoutMap.remove(activity);
    }

    ToolbarController getToolbarController() {
        return this.mToolbarController;
    }

    Insets getInsets() {
        return this.mInsetsUpdater.getInsets();
    }

    void dispatchNewInsets(Insets insets) {
        this.mInsetsUpdater.dispatchNewInsets(insets);
    }

    void replaceInsetsChangedListenerWith(InsetsChangedListener listener) {
        this.mInsetsUpdater.replaceInsetsChangedListenerWith(listener);
    }

    private void installBaseLayout(Activity activity) {
        boolean toolbarEnabled = getThemeBoolean(activity, R.attr.carUiToolbar);
        Pair<ToolbarController, InsetsUpdater> results = installBaseLayoutAround(activity, CarUiUtils.requireViewByRefId(activity.getWindow().getDecorView(), android.R.id.content), toolbarEnabled);
        this.mToolbarController = results.first;
        this.mInsetsUpdater = results.second;
    }

    public static Pair<ToolbarController, InsetsUpdater> installBaseLayoutAround(Activity activity, View contentView, boolean toolbarEnabled) {
        int baseLayoutRes;
        boolean legacyToolbar = Build.VERSION.SDK_INT <= 29;
        if (toolbarEnabled) {
            if (legacyToolbar) {
                baseLayoutRes = R.layout.car_ui_base_layout_toolbar_legacy;
            } else {
                baseLayoutRes = R.layout.car_ui_base_layout_toolbar;
            }
        } else {
            baseLayoutRes = R.layout.car_ui_base_layout;
        }
        View baseLayout = LayoutInflater.from(contentView.getContext()).inflate(baseLayoutRes, (ViewGroup) null, false);
        ViewGroup contentViewParent = (ViewGroup) contentView.getParent();
        int contentIndex = contentViewParent.indexOfChild(contentView);
        contentViewParent.removeView(contentView);
        contentViewParent.addView(baseLayout, contentIndex, contentView.getLayoutParams());
        FrameLayout contentViewContainer = (FrameLayout) CarUiUtils.requireViewByRefId(baseLayout, R.id.car_ui_base_layout_content_container);
        contentViewContainer.addView(contentView, new FrameLayout.LayoutParams(-1, -1));
        ToolbarController toolbarController = null;
        if (toolbarEnabled) {
            if (legacyToolbar) {
                toolbarController = (ToolbarController) CarUiUtils.requireViewByRefId(baseLayout, R.id.car_ui_toolbar);
            } else {
                toolbarController = new ToolbarControllerImpl(baseLayout);
            }
        }
        InsetsUpdater insetsUpdater = new InsetsUpdater(activity, baseLayout, contentView);
        return Pair.create(toolbarController, insetsUpdater);
    }

    private static boolean getThemeBoolean(Activity activity, int attr) {
        TypedArray a = activity.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getBoolean(0, false);
        } finally {
            a.recycle();
        }
    }

    static final class InsetsUpdater {
        private static final String BOTTOM_INSET_TAG = "car_ui_bottom_inset";
        private static final String LEFT_INSET_TAG = "car_ui_left_inset";
        private static final String RIGHT_INSET_TAG = "car_ui_right_inset";
        private static final String TOP_INSET_TAG = "car_ui_top_inset";
        private final Activity mActivity;
        private final View mBottomInsetView;
        private final View mContentView;
        private final View mContentViewContainer;
        private Insets mInsets = new Insets();
        private InsetsChangedListener mInsetsChangedListenerDelegate;
        private final View mLeftInsetView;
        private final View mRightInsetView;
        private final View mTopInsetView;

        InsetsUpdater(Activity activity, View baseLayout, View contentView) {
            this.mActivity = activity;
            this.mContentView = contentView;
            this.mContentViewContainer = CarUiUtils.requireViewByRefId(baseLayout, R.id.car_ui_base_layout_content_container);
            this.mLeftInsetView = baseLayout.findViewWithTag(LEFT_INSET_TAG);
            this.mRightInsetView = baseLayout.findViewWithTag(RIGHT_INSET_TAG);
            this.mTopInsetView = baseLayout.findViewWithTag(TOP_INSET_TAG);
            this.mBottomInsetView = baseLayout.findViewWithTag(BOTTOM_INSET_TAG);
            View.OnLayoutChangeListener layoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.android.car.ui.core.-$$Lambda$BaseLayoutController$InsetsUpdater$zzCIB0038f8j1iFmYePHWvmc4w4
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    this.f$0.lambda$new$0$BaseLayoutController$InsetsUpdater(view, i, i2, i3, i4, i5, i6, i7, i8);
                }
            };
            View view = this.mLeftInsetView;
            if (view != null) {
                view.addOnLayoutChangeListener(layoutChangeListener);
            }
            View view2 = this.mRightInsetView;
            if (view2 != null) {
                view2.addOnLayoutChangeListener(layoutChangeListener);
            }
            View view3 = this.mTopInsetView;
            if (view3 != null) {
                view3.addOnLayoutChangeListener(layoutChangeListener);
            }
            View view4 = this.mBottomInsetView;
            if (view4 != null) {
                view4.addOnLayoutChangeListener(layoutChangeListener);
            }
            contentView.addOnLayoutChangeListener(layoutChangeListener);
            this.mContentViewContainer.addOnLayoutChangeListener(layoutChangeListener);
        }

        public /* synthetic */ void lambda$new$0$BaseLayoutController$InsetsUpdater(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (left != oldLeft || top != oldTop || right != oldRight || bottom != oldBottom) {
                recalcInsets();
            }
        }

        Insets getInsets() {
            return this.mInsets;
        }

        public void replaceInsetsChangedListenerWith(InsetsChangedListener listener) {
            this.mInsetsChangedListenerDelegate = listener;
        }

        public void recalcInsets() {
            int top = Math.max(0, getTopOfView(this.mContentViewContainer) - getTopOfView(this.mContentView));
            int left = Math.max(0, getLeftOfView(this.mContentViewContainer) - getLeftOfView(this.mContentView));
            int right = Math.max(0, getRightOfView(this.mContentView) - getRightOfView(this.mContentViewContainer));
            int bottom = Math.max(0, getBottomOfView(this.mContentView) - getBottomOfView(this.mContentViewContainer));
            View view = this.mTopInsetView;
            if (view != null) {
                top += Math.max(0, getBottomOfView(view) - getTopOfView(this.mContentViewContainer));
            }
            if (this.mBottomInsetView != null) {
                bottom += Math.max(0, getBottomOfView(this.mContentViewContainer) - getTopOfView(this.mBottomInsetView));
            }
            View view2 = this.mLeftInsetView;
            if (view2 != null) {
                left += Math.max(0, getRightOfView(view2) - getLeftOfView(this.mContentViewContainer));
            }
            if (this.mRightInsetView != null) {
                right += Math.max(0, getRightOfView(this.mContentViewContainer) - getLeftOfView(this.mRightInsetView));
            }
            Insets insets = new Insets(left, top, right, bottom);
            if (!insets.equals(this.mInsets)) {
                this.mInsets = insets;
                dispatchNewInsets(insets);
            }
        }

        void dispatchNewInsets(Insets insets) {
            this.mInsets = insets;
            boolean handled = false;
            InsetsChangedListener insetsChangedListener = this.mInsetsChangedListenerDelegate;
            if (insetsChangedListener != null) {
                insetsChangedListener.onCarUiInsetsChanged(insets);
                handled = true;
            } else {
                ComponentCallbacks2 componentCallbacks2 = this.mActivity;
                if (componentCallbacks2 instanceof InsetsChangedListener) {
                    ((InsetsChangedListener) componentCallbacks2).onCarUiInsetsChanged(insets);
                    handled = true;
                }
                Activity activity = this.mActivity;
                if (activity instanceof FragmentActivity) {
                    for (ActivityResultCaller activityResultCaller : ((FragmentActivity) activity).getSupportFragmentManager().getFragments()) {
                        if (activityResultCaller instanceof InsetsChangedListener) {
                            ((InsetsChangedListener) activityResultCaller).onCarUiInsetsChanged(insets);
                            handled = true;
                        }
                    }
                }
            }
            if (!handled) {
                this.mContentView.setPadding(insets.getLeft(), insets.getTop(), insets.getRight(), insets.getBottom());
            }
        }

        private static int getLeftOfView(View v) {
            int[] position = new int[2];
            v.getLocationOnScreen(position);
            return position[0];
        }

        private static int getRightOfView(View v) {
            int[] position = new int[2];
            v.getLocationOnScreen(position);
            return position[0] + v.getWidth();
        }

        private static int getTopOfView(View v) {
            int[] position = new int[2];
            v.getLocationOnScreen(position);
            return position[1];
        }

        private static int getBottomOfView(View v) {
            int[] position = new int[2];
            v.getLocationOnScreen(position);
            return position[1] + v.getHeight();
        }
    }
}
