package com.android.car.apps.common;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.android.car.apps.common.ExpandableControlBar;
import com.android.car.apps.common.util.ViewUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class ControlBar extends RelativeLayout implements ExpandableControlBar {
    private static final int DEFAULT_COLUMNS = 3;
    private static final float SPACERS_WEIGHT = 1.0f;
    private static final String TAG = "ControlBar";
    private View mDefaultExpandCollapseView;
    private ExpandableControlBar.ExpandCollapseCallback mExpandCollapseCallback;
    private View mExpandCollapseView;
    private boolean mExpandEnabled;
    private FrameLayout mFirstCreatedSlot;
    private final SparseArray<View> mFixedViews;
    private final ViewTreeObserver.OnGlobalFocusChangeListener mFocusChangeListener;
    private boolean mHasFocus;
    private boolean mIsExpanded;
    private int mNumColumns;
    private int mNumExtraRowsInUse;
    private int mNumRows;
    private ViewGroup mRowsContainer;
    private FrameLayout[] mSlots;
    private ViewGroup mTransitionRoot;
    private View[] mViews;

    public /* synthetic */ void lambda$new$0$ControlBar(View oldFocus, View newFocus) {
        boolean hasFocus = hasFocus();
        if (this.mHasFocus && !hasFocus && this.mIsExpanded) {
            onExpandCollapse();
        }
        this.mHasFocus = hasFocus;
    }

    public ControlBar(Context context) {
        super(context);
        this.mFixedViews = new SparseArray<>();
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.apps.common.-$$Lambda$ControlBar$8X8m3icUj_2w2AGB8YuvLk1EyhY
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$ControlBar(view, view2);
            }
        };
        init(context, null, 0, 0);
    }

    public ControlBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFixedViews = new SparseArray<>();
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.apps.common.-$$Lambda$ControlBar$8X8m3icUj_2w2AGB8YuvLk1EyhY
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$ControlBar(view, view2);
            }
        };
        init(context, attrs, 0, 0);
    }

    public ControlBar(Context context, AttributeSet attrs, int defStyleAttrs) {
        super(context, attrs, defStyleAttrs);
        this.mFixedViews = new SparseArray<>();
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.apps.common.-$$Lambda$ControlBar$8X8m3icUj_2w2AGB8YuvLk1EyhY
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$ControlBar(view, view2);
            }
        };
        init(context, attrs, defStyleAttrs, 0);
    }

    public ControlBar(Context context, AttributeSet attrs, int defStyleAttrs, int defStyleRes) {
        super(context, attrs, defStyleAttrs, defStyleRes);
        this.mFixedViews = new SparseArray<>();
        this.mFocusChangeListener = new ViewTreeObserver.OnGlobalFocusChangeListener() { // from class: com.android.car.apps.common.-$$Lambda$ControlBar$8X8m3icUj_2w2AGB8YuvLk1EyhY
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                this.f$0.lambda$new$0$ControlBar(view, view2);
            }
        };
        init(context, attrs, defStyleAttrs, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttrs, int defStyleRes) {
        TypedArray ta;
        inflate(context, R.layout.control_bar, this);
        TypedArray ta2 = context.obtainStyledAttributes(attrs, R.styleable.ControlBar, defStyleAttrs, defStyleRes);
        this.mNumColumns = ta2.getInteger(R.styleable.ControlBar_columns, 3);
        int i = 1;
        this.mExpandEnabled = ta2.getBoolean(R.styleable.ControlBar_enableOverflow, true);
        ta2.recycle();
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.rows_container);
        this.mRowsContainer = viewGroup;
        int childCount = viewGroup.getChildCount();
        this.mNumRows = childCount;
        Preconditions.checkState(childCount > 0, "Must have at least 1 row");
        this.mSlots = new FrameLayout[this.mNumColumns * this.mNumRows];
        LayoutInflater inflater = LayoutInflater.from(context);
        int i2 = 0;
        while (true) {
            int i3 = this.mNumRows;
            if (i2 < i3) {
                ViewGroup row = (ViewGroup) this.mRowsContainer.getChildAt((i3 - i2) - i);
                int j = 0;
                while (true) {
                    int i4 = this.mNumColumns;
                    if (j < i4) {
                        int pos = (i4 * i2) + j;
                        this.mSlots[pos] = (FrameLayout) inflater.inflate(R.layout.control_bar_slot, row, false);
                        if (this.mFirstCreatedSlot == null) {
                            this.mFirstCreatedSlot = this.mSlots[pos];
                        }
                        if (j <= 0) {
                            ta = ta2;
                        } else {
                            Space space = new Space(context);
                            row.addView(space);
                            ta = ta2;
                            space.setLayoutParams(new LinearLayout.LayoutParams(0, -1, SPACERS_WEIGHT));
                        }
                        row.addView(this.mSlots[pos]);
                        j++;
                        ta2 = ta;
                    }
                }
                i2++;
                i = 1;
            } else {
                ImageButton imageButtonCreateIconButton = createIconButton(context.getDrawable(R.drawable.ic_overflow_button));
                this.mDefaultExpandCollapseView = imageButtonCreateIconButton;
                imageButtonCreateIconButton.setContentDescription(context.getString(R.string.control_bar_expand_collapse_button));
                this.mDefaultExpandCollapseView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.apps.common.-$$Lambda$ControlBar$492hbGyDl1feskhH5Xdqq6M43jQ
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$init$1$ControlBar(view);
                    }
                });
                return;
            }
        }
    }

    public /* synthetic */ void lambda$init$1$ControlBar(View v) {
        onExpandCollapse();
    }

    private int getSlotIndex(int slotPosition) {
        return CarControlBar.getSlotIndex(slotPosition, this.mNumColumns);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.mFocusChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.mFocusChangeListener);
        super.onDetachedFromWindow();
    }

    @Override // com.android.car.apps.common.CarControlBar
    public void setView(View view, int slotPosition) {
        if (view != null) {
            this.mFixedViews.put(slotPosition, view);
        } else {
            this.mFixedViews.remove(slotPosition);
        }
        updateViewsLayout();
    }

    public void setExpandCollapseView(View view) {
        this.mExpandCollapseView = view;
        view.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.apps.common.-$$Lambda$ControlBar$SbBcagRFOrI7voklvDJNSzva9JM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$setExpandCollapseView$2$ControlBar(view2);
            }
        });
        updateViewsLayout();
    }

    public /* synthetic */ void lambda$setExpandCollapseView$2$ControlBar(View v) {
        onExpandCollapse();
    }

    private View getExpandCollapseView() {
        View view = this.mExpandCollapseView;
        return view != null ? view : this.mDefaultExpandCollapseView;
    }

    @Override // com.android.car.apps.common.CarControlBar
    public ImageButton createIconButton(Drawable icon) {
        return createIconButton(icon, R.layout.control_bar_button);
    }

    @Override // com.android.car.apps.common.CarControlBar
    public ImageButton createIconButton(Drawable icon, int viewId) {
        LayoutInflater inflater = LayoutInflater.from(this.mFirstCreatedSlot.getContext());
        ImageButton button = (ImageButton) inflater.inflate(viewId, (ViewGroup) this.mFirstCreatedSlot, false);
        button.setImageDrawable(icon);
        return button;
    }

    @Override // com.android.car.apps.common.ExpandableControlBar
    public void registerExpandCollapseCallback(ExpandableControlBar.ExpandCollapseCallback callback) {
        this.mExpandCollapseCallback = callback;
    }

    @Override // com.android.car.apps.common.ExpandableControlBar
    public void close() {
        if (this.mIsExpanded) {
            onExpandCollapse();
        }
    }

    @Override // com.android.car.apps.common.CarControlBar
    public void setViews(View[] views) {
        this.mViews = views;
        updateViewsLayout();
    }

    private void updateViewsLayout() {
        View[] viewArr;
        int totalSlots = this.mSlots.length;
        View[] slotViews = new View[totalSlots];
        for (int i = 0; i < this.mFixedViews.size(); i++) {
            int index = getSlotIndex(this.mFixedViews.keyAt(i));
            if (index >= 0 && index < slotViews.length) {
                slotViews[index] = this.mFixedViews.valueAt(i);
            }
        }
        int expandCollapseIndex = getSlotIndex(3);
        int lastUsedIndex = 0;
        int viewsIndex = 0;
        for (int i2 = 0; i2 < totalSlots; i2++) {
            View viewToUse = null;
            if (slotViews[i2] != null) {
                viewToUse = slotViews[i2];
            } else if (this.mExpandEnabled && i2 == expandCollapseIndex && (viewArr = this.mViews) != null && viewsIndex < viewArr.length - 1) {
                viewToUse = getExpandCollapseView();
                Log.d(TAG, "" + this + "Setting expand control");
            } else {
                View[] viewArr2 = this.mViews;
                if (viewArr2 != null && viewsIndex < viewArr2.length) {
                    viewToUse = viewArr2[viewsIndex];
                    viewsIndex++;
                }
            }
            ViewUtils.setView(viewToUse, this.mSlots[i2]);
            if (viewToUse != null) {
                lastUsedIndex = i2;
            }
        }
        int i3 = this.mNumColumns;
        this.mNumExtraRowsInUse = lastUsedIndex / i3;
        final int lastIndex = lastUsedIndex;
        if (this.mNumRows > 1) {
            this.mRowsContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.car.apps.common.-$$Lambda$ControlBar$AdCrB78yJz9q1NdEpJmPr1X7B64
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    this.f$0.lambda$updateViewsLayout$3$ControlBar(lastIndex);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateViewsLayout$3$ControlBar(int lastIndex) {
        int i = 1;
        while (true) {
            int i2 = this.mNumRows;
            if (i < i2) {
                int rowIndex = (i2 - 1) - i;
                if (lastIndex < (i + 1) * this.mNumColumns) {
                    float lastRowX = this.mSlots[lastIndex].getX();
                    float firstRowX = this.mSlots[this.mNumColumns - 1].getX();
                    this.mRowsContainer.getChildAt(rowIndex).setTranslationX((firstRowX - lastRowX) / 2.0f);
                } else {
                    this.mRowsContainer.getChildAt(rowIndex).setTranslationX(0.0f);
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void onExpandCollapse() {
        int i;
        boolean z = !this.mIsExpanded;
        this.mIsExpanded = z;
        View view = this.mExpandCollapseView;
        if (view != null) {
            view.setSelected(z);
        }
        ExpandableControlBar.ExpandCollapseCallback expandCollapseCallback = this.mExpandCollapseCallback;
        if (expandCollapseCallback != null) {
            expandCollapseCallback.onExpandCollapse(this.mIsExpanded);
        }
        this.mSlots[getSlotIndex(3)].setActivated(this.mIsExpanded);
        Resources resources = getContext().getResources();
        if (this.mIsExpanded) {
            i = R.integer.control_bar_expand_anim_duration;
        } else {
            i = R.integer.control_bar_collapse_anim_duration;
        }
        int animationDuration = resources.getInteger(i);
        TransitionSet set = new TransitionSet().addTransition(new ChangeBounds()).addTransition(new Fade()).setDuration(animationDuration).setInterpolator((TimeInterpolator) new FastOutSlowInInterpolator());
        maybeInitTransitionRoot();
        TransitionManager.beginDelayedTransition(this.mTransitionRoot, set);
        for (int i2 = 0; i2 < this.mNumExtraRowsInUse; i2++) {
            this.mRowsContainer.getChildAt(i2).setVisibility(this.mIsExpanded ? 0 : 8);
        }
    }

    private void maybeInitTransitionRoot() {
        if (this.mTransitionRoot != null) {
            return;
        }
        this.mTransitionRoot = this;
        ViewParent viewParent = getParent();
        while (viewParent != null && (viewParent instanceof ViewGroup)) {
            ViewGroup parent = (ViewGroup) viewParent;
            if (parent.getLayoutParams().height == -2) {
                this.mTransitionRoot = parent;
                viewParent = parent.getParent();
            } else {
                return;
            }
        }
    }

    View getViewAt(int rowIdx, int colIdx) {
        if (rowIdx < 0 || rowIdx > this.mRowsContainer.getChildCount()) {
            throw new IllegalArgumentException(String.format((Locale) null, "Row index out of range (requested: %d, max: %d)", Integer.valueOf(rowIdx), Integer.valueOf(this.mRowsContainer.getChildCount())));
        }
        if (colIdx < 0 || colIdx > this.mNumColumns) {
            throw new IllegalArgumentException(String.format((Locale) null, "Column index out of range (requested: %d, max: %d)", Integer.valueOf(colIdx), Integer.valueOf(this.mNumColumns)));
        }
        FrameLayout slot = (FrameLayout) ((LinearLayout) this.mRowsContainer.getChildAt(rowIdx)).getChildAt(colIdx + 1);
        if (slot.getChildCount() > 0) {
            return slot.getChildAt(0);
        }
        return null;
    }
}
