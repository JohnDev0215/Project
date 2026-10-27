package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private int animState;
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> behavior;
    private Animator currentCollapseExpandAnimator;
    private Animator currentShowHideAnimator;
    private MotionSpec defaultExtendMotionSpec;
    private MotionSpec defaultHideMotionSpec;
    private MotionSpec defaultShowMotionSpec;
    private MotionSpec defaultShrinkMotionSpec;
    private ArrayList<Animator.AnimatorListener> extendListeners;
    private MotionSpec extendMotionSpec;
    private ArrayList<Animator.AnimatorListener> hideListeners;
    private MotionSpec hideMotionSpec;
    private boolean isExtended;
    private final Rect shadowPadding;
    private ArrayList<Animator.AnimatorListener> showListeners;
    private MotionSpec showMotionSpec;
    private ArrayList<Animator.AnimatorListener> shrinkListeners;
    private MotionSpec shrinkMotionSpec;
    private int userSetVisibility;
    private static final Property<View, Float> WIDTH = new Property<View, Float>(Float.class, "width") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.4
        @Override // android.util.Property
        public void set(View object, Float value) {
            object.getLayoutParams().width = value.intValue();
            object.requestLayout();
        }

        @Override // android.util.Property
        public Float get(View object) {
            return Float.valueOf(object.getLayoutParams().width);
        }
    };
    private static final Property<View, Float> HEIGHT = new Property<View, Float>(Float.class, "height") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.5
        @Override // android.util.Property
        public void set(View object, Float value) {
            object.getLayoutParams().height = value.intValue();
            object.requestLayout();
        }

        @Override // android.util.Property
        public Float get(View object) {
            return Float.valueOf(object.getLayoutParams().height);
        }
    };
    private static final Property<View, Float> CORNER_RADIUS = new Property<View, Float>(Float.class, "cornerRadius") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.6
        @Override // android.util.Property
        public void set(View object, Float value) {
            ((ExtendedFloatingActionButton) object).setCornerRadius(value.intValue());
        }

        @Override // android.util.Property
        public Float get(View object) {
            return Float.valueOf(((ExtendedFloatingActionButton) object).getCornerRadius());
        }
    };

    public static abstract class OnChangedListener {
        public void onShown(ExtendedFloatingActionButton extendedFab) {
        }

        public void onHidden(ExtendedFloatingActionButton extendedFab) {
        }

        public void onExtended(ExtendedFloatingActionButton extendedFab) {
        }

        public void onShrunken(ExtendedFloatingActionButton extendedFab) {
        }
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.extendedFloatingActionButtonStyle);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.shadowPadding = new Rect();
        this.animState = 0;
        this.isExtended = true;
        this.behavior = new ExtendedFloatingActionButtonBehavior(context, attrs);
        this.userSetVisibility = getVisibility();
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context, attrs, R.styleable.ExtendedFloatingActionButton, defStyleAttr, R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon, new int[0]);
        this.showMotionSpec = MotionSpec.createFromAttribute(context, a, R.styleable.ExtendedFloatingActionButton_showMotionSpec);
        this.hideMotionSpec = MotionSpec.createFromAttribute(context, a, R.styleable.ExtendedFloatingActionButton_hideMotionSpec);
        this.extendMotionSpec = MotionSpec.createFromAttribute(context, a, R.styleable.ExtendedFloatingActionButton_extendMotionSpec);
        this.shrinkMotionSpec = MotionSpec.createFromAttribute(context, a, R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        a.recycle();
        setHorizontallyScrolling(true);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            shrinkNow();
        }
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setCornerRadius(getAdjustedRadius(getMeasuredHeight()));
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        internalSetVisibility(visibility, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalSetVisibility(int visibility, boolean fromUser) {
        super.setVisibility(visibility);
        if (fromUser) {
            this.userSetVisibility = visibility;
        }
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    public void addOnShowAnimationListener(Animator.AnimatorListener listener) {
        if (this.showListeners == null) {
            this.showListeners = new ArrayList<>();
        }
        this.showListeners.add(listener);
    }

    public void removeOnShowAnimationListener(Animator.AnimatorListener listener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.showListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(listener);
    }

    public void addOnHideAnimationListener(Animator.AnimatorListener listener) {
        if (this.hideListeners == null) {
            this.hideListeners = new ArrayList<>();
        }
        this.hideListeners.add(listener);
    }

    public void removeOnHideAnimationListener(Animator.AnimatorListener listener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.hideListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(listener);
    }

    public void addOnShrinkAnimationListener(Animator.AnimatorListener listener) {
        if (this.shrinkListeners == null) {
            this.shrinkListeners = new ArrayList<>();
        }
        this.shrinkListeners.add(listener);
    }

    public void removeOnShrinkAnimationListener(Animator.AnimatorListener listener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.shrinkListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(listener);
    }

    public void addOnExtendAnimationListener(Animator.AnimatorListener listener) {
        if (this.extendListeners == null) {
            this.extendListeners = new ArrayList<>();
        }
        this.extendListeners.add(listener);
    }

    public void removeOnExtendAnimationListener(Animator.AnimatorListener listener) {
        ArrayList<Animator.AnimatorListener> arrayList = this.extendListeners;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(listener);
    }

    public void hide() {
        hide(true);
    }

    public void hide(boolean animate) {
        hide(true, animate, null);
    }

    public void hide(OnChangedListener listener) {
        hide(true, true, listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hide(final boolean fromUser, boolean animate, final OnChangedListener listener) {
        if (isOrWillBeHidden()) {
            return;
        }
        Animator animator = this.currentShowHideAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (animate && shouldAnimateVisibilityChange()) {
            Animator hideAnimation = createAnimator(getCurrentHideMotionSpec());
            hideAnimation.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.1
                private boolean cancelled;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    ExtendedFloatingActionButton.this.internalSetVisibility(0, fromUser);
                    ExtendedFloatingActionButton.this.animState = 1;
                    ExtendedFloatingActionButton.this.currentShowHideAnimator = animation;
                    this.cancelled = false;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    this.cancelled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    ExtendedFloatingActionButton.this.animState = 0;
                    ExtendedFloatingActionButton.this.currentShowHideAnimator = null;
                    if (!this.cancelled) {
                        ExtendedFloatingActionButton.this.internalSetVisibility(fromUser ? 8 : 4, fromUser);
                        OnChangedListener onChangedListener = listener;
                        if (onChangedListener != null) {
                            onChangedListener.onHidden(ExtendedFloatingActionButton.this);
                        }
                    }
                }
            });
            ArrayList<Animator.AnimatorListener> arrayList = this.hideListeners;
            if (arrayList != null) {
                for (Animator.AnimatorListener l : arrayList) {
                    hideAnimation.addListener(l);
                }
            }
            hideAnimation.start();
            return;
        }
        internalSetVisibility(fromUser ? 8 : 4, fromUser);
        if (listener != null) {
            listener.onHidden(this);
        }
    }

    public void show() {
        show(true);
    }

    public void show(boolean animate) {
        show(true, animate, null);
    }

    public void show(OnChangedListener listener) {
        show(true, true, listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show(final boolean fromUser, boolean animate, final OnChangedListener listener) {
        if (isOrWillBeShown()) {
            return;
        }
        Animator animator = this.currentShowHideAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (animate && shouldAnimateVisibilityChange()) {
            Animator showAnimation = createAnimator(getCurrentShowMotionSpec());
            showAnimation.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    ExtendedFloatingActionButton.this.internalSetVisibility(0, fromUser);
                    ExtendedFloatingActionButton.this.animState = 2;
                    ExtendedFloatingActionButton.this.currentShowHideAnimator = animation;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    ExtendedFloatingActionButton.this.animState = 0;
                    ExtendedFloatingActionButton.this.currentShowHideAnimator = null;
                    OnChangedListener onChangedListener = listener;
                    if (onChangedListener != null) {
                        onChangedListener.onShown(ExtendedFloatingActionButton.this);
                    }
                }
            });
            ArrayList<Animator.AnimatorListener> arrayList = this.showListeners;
            if (arrayList != null) {
                for (Animator.AnimatorListener l : arrayList) {
                    showAnimation.addListener(l);
                }
            }
            showAnimation.start();
            return;
        }
        internalSetVisibility(0, fromUser);
        setAlpha(1.0f);
        setScaleY(1.0f);
        setScaleX(1.0f);
        if (listener != null) {
            listener.onShown(this);
        }
    }

    public void extend() {
        extend(true);
    }

    public void extend(boolean animate) {
        setExtended(true, animate, null);
    }

    public void extend(OnChangedListener listener) {
        setExtended(true, true, listener);
    }

    public void shrink() {
        shrink(true);
    }

    public void shrink(boolean animate) {
        setExtended(false, animate, null);
    }

    public void shrink(OnChangedListener listener) {
        setExtended(false, true, listener);
    }

    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public void setShowMotionSpec(MotionSpec spec) {
        this.showMotionSpec = spec;
    }

    public void setShowMotionSpecResource(int id) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public void setHideMotionSpec(MotionSpec spec) {
        this.hideMotionSpec = spec;
    }

    public void setHideMotionSpecResource(int id) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getExtendMotionSpec() {
        return this.extendMotionSpec;
    }

    public void setExtendMotionSpec(MotionSpec spec) {
        this.extendMotionSpec = spec;
    }

    public void setExtendMotionSpecResource(int id) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkMotionSpec;
    }

    public void setShrinkMotionSpec(MotionSpec spec) {
        this.shrinkMotionSpec = spec;
    }

    public void setShrinkMotionSpecResource(int id) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), id));
    }

    private void setExtended(final boolean extended, boolean animate, final OnChangedListener listener) {
        if (extended == this.isExtended || getIcon() == null || TextUtils.isEmpty(getText())) {
            return;
        }
        this.isExtended = extended;
        Animator animator = this.currentCollapseExpandAnimator;
        if (animator != null) {
            animator.cancel();
        }
        if (animate && shouldAnimateVisibilityChange()) {
            measure(0, 0);
            Animator collapseExpandAnimator = createShrinkExtendAnimator(this.isExtended ? getCurrentExtendMotionSpec() : getCurrentShrinkMotionSpec(), !this.isExtended);
            collapseExpandAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.3
                private boolean cancelled;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    ExtendedFloatingActionButton.this.currentCollapseExpandAnimator = animation;
                    this.cancelled = false;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    this.cancelled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    OnChangedListener onChangedListener;
                    ExtendedFloatingActionButton.this.currentCollapseExpandAnimator = null;
                    if (this.cancelled || (onChangedListener = listener) == null) {
                        return;
                    }
                    if (extended) {
                        onChangedListener.onExtended(ExtendedFloatingActionButton.this);
                    } else {
                        onChangedListener.onShrunken(ExtendedFloatingActionButton.this);
                    }
                }
            });
            ArrayList<Animator.AnimatorListener> listeners = extended ? this.extendListeners : this.shrinkListeners;
            if (listeners != null) {
                for (Animator.AnimatorListener l : listeners) {
                    collapseExpandAnimator.addListener(l);
                }
            }
            collapseExpandAnimator.start();
            return;
        }
        if (extended) {
            extendNow();
            if (listener != null) {
                listener.onExtended(this);
                return;
            }
            return;
        }
        shrinkNow();
        if (listener != null) {
            listener.onShrunken(this);
        }
    }

    private AnimatorSet createAnimator(MotionSpec spec) {
        List<Animator> animators = new ArrayList<>();
        if (spec.hasPropertyValues("opacity")) {
            animators.add(spec.getAnimator("opacity", this, View.ALPHA));
        }
        if (spec.hasPropertyValues("scale")) {
            animators.add(spec.getAnimator("scale", this, View.SCALE_Y));
            animators.add(spec.getAnimator("scale", this, View.SCALE_X));
        }
        if (spec.hasPropertyValues("width")) {
            animators.add(spec.getAnimator("width", this, WIDTH));
        }
        if (spec.hasPropertyValues("height")) {
            animators.add(spec.getAnimator("height", this, HEIGHT));
        }
        if (spec.hasPropertyValues("cornerRadius")) {
            animators.add(spec.getAnimator("cornerRadius", this, CORNER_RADIUS));
        }
        AnimatorSet set = new AnimatorSet();
        AnimatorSetCompat.playTogether(set, animators);
        return set;
    }

    private AnimatorSet createShrinkExtendAnimator(MotionSpec spec, boolean shrinking) {
        int collapsedSize = (ViewCompat.getPaddingStart(this) * 2) + getIconSize();
        if (spec.hasPropertyValues("width")) {
            PropertyValuesHolder[] widthValues = spec.getPropertyValues("width");
            if (shrinking) {
                widthValues[0].setFloatValues(getMeasuredWidth(), collapsedSize);
            } else {
                widthValues[0].setFloatValues(getWidth(), getMeasuredWidth());
            }
            spec.setPropertyValues("width", widthValues);
        }
        if (spec.hasPropertyValues("height")) {
            PropertyValuesHolder[] heightValues = spec.getPropertyValues("height");
            if (shrinking) {
                heightValues[0].setFloatValues(getMeasuredHeight(), collapsedSize);
            } else {
                heightValues[0].setFloatValues(getHeight(), getMeasuredHeight());
            }
            spec.setPropertyValues("height", heightValues);
        }
        if (spec.hasPropertyValues("cornerRadius")) {
            PropertyValuesHolder[] cornerRadiusValues = spec.getPropertyValues("cornerRadius");
            if (shrinking) {
                cornerRadiusValues[0].setFloatValues(getCornerRadius(), getAdjustedRadius(collapsedSize));
            } else {
                cornerRadiusValues[0].setFloatValues(getCornerRadius(), getAdjustedRadius(getHeight()));
            }
            spec.setPropertyValues("cornerRadius", cornerRadiusValues);
        }
        return createAnimator(spec);
    }

    private boolean isOrWillBeShown() {
        return getVisibility() != 0 ? this.animState == 2 : this.animState != 1;
    }

    private boolean isOrWillBeHidden() {
        return getVisibility() == 0 ? this.animState == 1 : this.animState != 2;
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this) && !isInEditMode();
    }

    private void shrinkNow() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        int collapsedSize = (ViewCompat.getPaddingStart(this) * 2) + getIconSize();
        layoutParams.width = collapsedSize;
        layoutParams.height = collapsedSize;
        requestLayout();
    }

    private void extendNow() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        measure(0, 0);
        layoutParams.width = getMeasuredWidth();
        layoutParams.height = getMeasuredHeight();
        requestLayout();
    }

    private MotionSpec getCurrentShowMotionSpec() {
        MotionSpec motionSpec = this.showMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultShowMotionSpec == null) {
            this.defaultShowMotionSpec = MotionSpec.createFromResource(getContext(), R.animator.mtrl_extended_fab_show_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultShowMotionSpec);
    }

    private MotionSpec getCurrentHideMotionSpec() {
        MotionSpec motionSpec = this.hideMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultHideMotionSpec == null) {
            this.defaultHideMotionSpec = MotionSpec.createFromResource(getContext(), R.animator.mtrl_extended_fab_hide_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultHideMotionSpec);
    }

    private MotionSpec getCurrentExtendMotionSpec() {
        MotionSpec motionSpec = this.extendMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultExtendMotionSpec == null) {
            this.defaultExtendMotionSpec = MotionSpec.createFromResource(getContext(), R.animator.mtrl_extended_fab_extend_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultExtendMotionSpec);
    }

    private MotionSpec getCurrentShrinkMotionSpec() {
        MotionSpec motionSpec = this.shrinkMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultShrinkMotionSpec == null) {
            this.defaultShrinkMotionSpec = MotionSpec.createFromResource(getContext(), R.animator.mtrl_extended_fab_shrink_motion_spec);
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultShrinkMotionSpec);
    }

    private int getAdjustedRadius(int value) {
        return (value - 1) / 2;
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = false;
        private static final boolean AUTO_SHRINK_DEFAULT = true;
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private OnChangedListener internalAutoHideListener;
        private OnChangedListener internalAutoShrinkListener;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = AUTO_SHRINK_DEFAULT;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = a.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = a.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, AUTO_SHRINK_DEFAULT);
            a.recycle();
        }

        public void setAutoHideEnabled(boolean autoHide) {
            this.autoHideEnabled = autoHide;
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public void setAutoShrinkEnabled(boolean autoShrink) {
            this.autoShrinkEnabled = autoShrink;
        }

        public boolean isAutoShrinkEnabled() {
            return this.autoShrinkEnabled;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams lp) {
            if (lp.dodgeInsetEdges == 0) {
                lp.dodgeInsetEdges = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout parent, ExtendedFloatingActionButton child, View dependency) {
            if (dependency instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child);
                return false;
            }
            if (isBottomSheet(dependency)) {
                updateFabVisibilityForBottomSheet(dependency, child);
                return false;
            }
            return false;
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) lp).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        public void setInternalAutoHideListener(OnChangedListener listener) {
            this.internalAutoHideListener = listener;
        }

        public void setInternalAutoShrinkListener(OnChangedListener listener) {
            this.internalAutoShrinkListener = listener;
        }

        private boolean shouldUpdateVisibility(View dependency, ExtendedFloatingActionButton child) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            if ((this.autoHideEnabled || this.autoShrinkEnabled) && lp.getAnchorId() == dependency.getId() && child.getUserSetVisibility() == 0) {
                return AUTO_SHRINK_DEFAULT;
            }
            return false;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout parent, AppBarLayout appBarLayout, ExtendedFloatingActionButton child) {
            if (!shouldUpdateVisibility(appBarLayout, child)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(parent, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(child);
                return AUTO_SHRINK_DEFAULT;
            }
            extendOrShow(child);
            return AUTO_SHRINK_DEFAULT;
        }

        private boolean updateFabVisibilityForBottomSheet(View bottomSheet, ExtendedFloatingActionButton child) {
            if (!shouldUpdateVisibility(bottomSheet, child)) {
                return false;
            }
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            if (bottomSheet.getTop() < (child.getHeight() / 2) + lp.topMargin) {
                shrinkOrHide(child);
                return AUTO_SHRINK_DEFAULT;
            }
            extendOrShow(child);
            return AUTO_SHRINK_DEFAULT;
        }

        protected void shrinkOrHide(ExtendedFloatingActionButton fab) {
            if (this.autoShrinkEnabled) {
                fab.shrink(this.internalAutoShrinkListener);
            } else if (this.autoHideEnabled) {
                fab.hide(false, AUTO_SHRINK_DEFAULT, this.internalAutoHideListener);
            }
        }

        protected void extendOrShow(ExtendedFloatingActionButton fab) {
            if (this.autoShrinkEnabled) {
                fab.extend(this.internalAutoShrinkListener);
            } else if (this.autoHideEnabled) {
                fab.show(false, AUTO_SHRINK_DEFAULT, this.internalAutoHideListener);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onLayoutChild(CoordinatorLayout parent, ExtendedFloatingActionButton child, int layoutDirection) {
            List<View> dependencies = parent.getDependencies(child);
            int count = dependencies.size();
            for (int i = 0; i < count; i++) {
                View dependency = dependencies.get(i);
                if (dependency instanceof AppBarLayout) {
                    if (updateFabVisibilityForAppBarLayout(parent, (AppBarLayout) dependency, child)) {
                        break;
                    }
                } else {
                    if (isBottomSheet(dependency) && updateFabVisibilityForBottomSheet(dependency, child)) {
                        break;
                    }
                }
            }
            parent.onLayoutChild(child, layoutDirection);
            offsetIfNeeded(parent, child);
            return AUTO_SHRINK_DEFAULT;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean getInsetDodgeRect(CoordinatorLayout parent, ExtendedFloatingActionButton child, Rect rect) {
            Rect shadowPadding = child.shadowPadding;
            rect.set(child.getLeft() + shadowPadding.left, child.getTop() + shadowPadding.top, child.getRight() - shadowPadding.right, child.getBottom() - shadowPadding.bottom);
            return AUTO_SHRINK_DEFAULT;
        }

        private void offsetIfNeeded(CoordinatorLayout parent, ExtendedFloatingActionButton fab) {
            Rect padding = fab.shadowPadding;
            if (padding != null && padding.centerX() > 0 && padding.centerY() > 0) {
                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                int offsetTB = 0;
                int offsetLR = 0;
                if (fab.getRight() >= parent.getWidth() - lp.rightMargin) {
                    offsetLR = padding.right;
                } else if (fab.getLeft() <= lp.leftMargin) {
                    offsetLR = -padding.left;
                }
                if (fab.getBottom() >= parent.getHeight() - lp.bottomMargin) {
                    offsetTB = padding.bottom;
                } else if (fab.getTop() <= lp.topMargin) {
                    offsetTB = -padding.top;
                }
                if (offsetTB != 0) {
                    ViewCompat.offsetTopAndBottom(fab, offsetTB);
                }
                if (offsetLR != 0) {
                    ViewCompat.offsetLeftAndRight(fab, offsetLR);
                }
            }
        }
    }
}
