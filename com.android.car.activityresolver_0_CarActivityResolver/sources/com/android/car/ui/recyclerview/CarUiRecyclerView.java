package com.android.car.ui.recyclerview;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.recyclerview.decorations.grid.GridDividerItemDecoration;
import com.android.car.ui.recyclerview.decorations.grid.GridOffsetItemDecoration;
import com.android.car.ui.recyclerview.decorations.linear.LinearDividerItemDecoration;
import com.android.car.ui.recyclerview.decorations.linear.LinearOffsetItemDecoration;
import com.android.car.ui.utils.CarUiUtils;
import com.android.car.ui.utils.CarUxRestrictionsUtil;
import com.android.car.ui.utils.RotaryConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class CarUiRecyclerView extends RecyclerView {
    private static final String TAG = "CarUiRecyclerView";
    private int mBottomOffset;
    private GridOffsetItemDecoration mBottomOffsetItemDecorationGrid;
    private RecyclerView.ItemDecoration mBottomOffsetItemDecorationLinear;
    private final CarUxRestrictionsUtil mCarUxRestrictionsUtil;
    private ViewGroup mContainer;
    private Rect mContainerPadding;
    private Rect mContainerPaddingRelative;
    private int mContainerVisibility;
    private GridDividerItemDecoration mDividerItemDecorationGrid;
    private RecyclerView.ItemDecoration mDividerItemDecorationLinear;
    private boolean mEnableDividers;
    private boolean mHasScrolled;
    private boolean mInstallingExtScrollBar;
    private boolean mIsInitialized;
    private final CarUxRestrictionsUtil.OnUxRestrictionsChangedListener mListener;
    private int mNumOfColumns;
    private RecyclerView.OnScrollListener mOnScrollListener;
    private ScrollBar mScrollBar;
    private String mScrollBarClass;
    private boolean mScrollBarEnabled;
    private int mScrollBarPaddingBottom;
    private int mScrollBarPaddingTop;
    private int mTopOffset;
    private GridOffsetItemDecoration mTopOffsetItemDecorationGrid;
    private RecyclerView.ItemDecoration mTopOffsetItemDecorationLinear;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarUiRecyclerViewLayout {
        public static final int GRID = 1;
        public static final int LINEAR = 0;
    }

    public interface ItemCap {
        public static final int UNLIMITED = -1;

        void setMaxItems(int i);
    }

    public CarUiRecyclerView(Context context) {
        this(context, null);
    }

    public CarUiRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.carUiRecyclerViewStyle);
    }

    public CarUiRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mListener = new UxRestrictionChangedListener();
        this.mInstallingExtScrollBar = false;
        this.mContainerVisibility = 0;
        this.mHasScrolled = false;
        this.mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.android.car.ui.recyclerview.CarUiRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx > 0 || dy > 0) {
                    CarUiRecyclerView.this.mHasScrolled = true;
                    CarUiRecyclerView.this.removeOnScrollListener(this);
                }
            }
        };
        this.mCarUxRestrictionsUtil = CarUxRestrictionsUtil.getInstance(context);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        setClipToPadding(false);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CarUiRecyclerView, defStyleAttr, R.style.Widget_CarUi_CarUiRecyclerView);
        initRotaryScroll(a);
        this.mScrollBarEnabled = context.getResources().getBoolean(R.bool.car_ui_scrollbar_enable);
        this.mScrollBarPaddingTop = context.getResources().getDimensionPixelSize(R.dimen.car_ui_scrollbar_padding_top);
        this.mScrollBarPaddingBottom = context.getResources().getDimensionPixelSize(R.dimen.car_ui_scrollbar_padding_bottom);
        int carUiRecyclerViewLayout = a.getInt(R.styleable.CarUiRecyclerView_layoutStyle, 0);
        this.mNumOfColumns = a.getInt(R.styleable.CarUiRecyclerView_numOfColumns, 2);
        this.mEnableDividers = a.getBoolean(R.styleable.CarUiRecyclerView_enableDivider, false);
        this.mDividerItemDecorationLinear = new LinearDividerItemDecoration(context.getDrawable(R.drawable.car_ui_recyclerview_divider));
        this.mDividerItemDecorationGrid = new GridDividerItemDecoration(context.getDrawable(R.drawable.car_ui_divider), context.getDrawable(R.drawable.car_ui_divider), this.mNumOfColumns);
        this.mTopOffset = a.getInteger(R.styleable.CarUiRecyclerView_topOffset, 0);
        this.mBottomOffset = a.getInteger(R.styleable.CarUiRecyclerView_bottomOffset, 0);
        this.mTopOffsetItemDecorationLinear = new LinearOffsetItemDecoration(this.mTopOffset, 0);
        this.mBottomOffsetItemDecorationLinear = new LinearOffsetItemDecoration(this.mBottomOffset, 1);
        this.mTopOffsetItemDecorationGrid = new GridOffsetItemDecoration(this.mTopOffset, this.mNumOfColumns, 0);
        this.mBottomOffsetItemDecorationGrid = new GridOffsetItemDecoration(this.mBottomOffset, this.mNumOfColumns, 1);
        this.mIsInitialized = true;
        boolean isLayoutMangerSet = getLayoutManager() != null;
        if (!isLayoutMangerSet && carUiRecyclerViewLayout == 0) {
            setLayoutManager(new LinearLayoutManager(getContext()));
        } else if (!isLayoutMangerSet && carUiRecyclerViewLayout == 1) {
            setLayoutManager(new GridLayoutManager(getContext(), this.mNumOfColumns));
        }
        addOnScrollListener(this.mOnScrollListener);
        a.recycle();
        if (!this.mScrollBarEnabled) {
            return;
        }
        this.mContainer = new FrameLayout(getContext());
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        this.mScrollBarClass = context.getResources().getString(R.string.car_ui_scrollbar_component);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (this.mIsInitialized) {
            addItemDecorations(layoutManager);
        }
        super.setLayoutManager(layoutManager);
    }

    private void addItemDecorations(RecyclerView.LayoutManager layoutManager) {
        GridDividerItemDecoration gridDividerItemDecoration = this.mDividerItemDecorationGrid;
        Objects.requireNonNull(gridDividerItemDecoration);
        removeItemDecoration(gridDividerItemDecoration);
        GridOffsetItemDecoration gridOffsetItemDecoration = this.mTopOffsetItemDecorationGrid;
        Objects.requireNonNull(gridOffsetItemDecoration);
        removeItemDecoration(gridOffsetItemDecoration);
        GridOffsetItemDecoration gridOffsetItemDecoration2 = this.mBottomOffsetItemDecorationGrid;
        Objects.requireNonNull(gridOffsetItemDecoration2);
        removeItemDecoration(gridOffsetItemDecoration2);
        RecyclerView.ItemDecoration itemDecoration = this.mDividerItemDecorationLinear;
        Objects.requireNonNull(itemDecoration);
        removeItemDecoration(itemDecoration);
        RecyclerView.ItemDecoration itemDecoration2 = this.mTopOffsetItemDecorationLinear;
        Objects.requireNonNull(itemDecoration2);
        removeItemDecoration(itemDecoration2);
        RecyclerView.ItemDecoration itemDecoration3 = this.mBottomOffsetItemDecorationLinear;
        Objects.requireNonNull(itemDecoration3);
        removeItemDecoration(itemDecoration3);
        if (layoutManager instanceof GridLayoutManager) {
            if (this.mEnableDividers) {
                GridDividerItemDecoration gridDividerItemDecoration2 = this.mDividerItemDecorationGrid;
                Objects.requireNonNull(gridDividerItemDecoration2);
                addItemDecoration(gridDividerItemDecoration2);
            }
            GridOffsetItemDecoration gridOffsetItemDecoration3 = this.mTopOffsetItemDecorationGrid;
            Objects.requireNonNull(gridOffsetItemDecoration3);
            addItemDecoration(gridOffsetItemDecoration3);
            GridOffsetItemDecoration gridOffsetItemDecoration4 = this.mBottomOffsetItemDecorationGrid;
            Objects.requireNonNull(gridOffsetItemDecoration4);
            addItemDecoration(gridOffsetItemDecoration4);
            setNumOfColumns(((GridLayoutManager) layoutManager).getSpanCount());
            return;
        }
        if (this.mEnableDividers) {
            RecyclerView.ItemDecoration itemDecoration4 = this.mDividerItemDecorationLinear;
            Objects.requireNonNull(itemDecoration4);
            addItemDecoration(itemDecoration4);
        }
        RecyclerView.ItemDecoration itemDecoration5 = this.mTopOffsetItemDecorationLinear;
        Objects.requireNonNull(itemDecoration5);
        addItemDecoration(itemDecoration5);
        RecyclerView.ItemDecoration itemDecoration6 = this.mBottomOffsetItemDecorationLinear;
        Objects.requireNonNull(itemDecoration6);
        addItemDecoration(itemDecoration6);
    }

    private void initRotaryScroll(TypedArray styledAttributes) {
        boolean rotaryScrollEnabled = styledAttributes != null && styledAttributes.getBoolean(R.styleable.CarUiRecyclerView_rotaryScrollEnabled, false);
        if (rotaryScrollEnabled) {
            int orientation = styledAttributes.getInt(R.styleable.CarUiRecyclerView_android_orientation, 1);
            CarUiUtils.setRotaryScrollEnabled(this, orientation == 1);
        } else {
            CharSequence contentDescription = getContentDescription();
            if (contentDescription == null || (!RotaryConstants.ROTARY_HORIZONTALLY_SCROLLABLE.contentEquals(contentDescription) && !RotaryConstants.ROTARY_VERTICALLY_SCROLLABLE.contentEquals(contentDescription))) {
                z = false;
            }
            rotaryScrollEnabled = z;
        }
        setOnGenericMotionListener(rotaryScrollEnabled ? new View.OnGenericMotionListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$CarUiRecyclerView$cmFjW8Igf4rw1YMIiWp_bUJTGRE
            @Override // android.view.View.OnGenericMotionListener
            public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$initRotaryScroll$0$CarUiRecyclerView(view, motionEvent);
            }
        } : null);
        setFocusable(rotaryScrollEnabled);
        setDescendantFocusability(131072);
        setDefaultFocusHighlightEnabled(false);
        if (!rotaryScrollEnabled) {
            super.setContentDescription(RotaryConstants.ROTARY_CONTAINER);
        }
    }

    public /* synthetic */ boolean lambda$initRotaryScroll$0$CarUiRecyclerView(View v, MotionEvent event) {
        if (event.getAction() == 8 && event.getSource() == 4194304) {
            MotionEvent mouseEvent = MotionEvent.obtain(event);
            mouseEvent.setSource(8194);
            super.onGenericMotionEvent(mouseEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        this.mHasScrolled = true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        ScrollBar scrollBar = this.mScrollBar;
        if (scrollBar != null) {
            scrollBar.requestLayout();
        }
    }

    public void setNumOfColumns(int numberOfColumns) {
        this.mNumOfColumns = numberOfColumns;
        GridOffsetItemDecoration gridOffsetItemDecoration = this.mTopOffsetItemDecorationGrid;
        if (gridOffsetItemDecoration != null) {
            gridOffsetItemDecoration.setNumOfColumns(numberOfColumns);
        }
        GridDividerItemDecoration gridDividerItemDecoration = this.mDividerItemDecorationGrid;
        if (gridDividerItemDecoration != null) {
            gridDividerItemDecoration.setNumOfColumns(this.mNumOfColumns);
        }
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        this.mContainerVisibility = visibility;
        ViewGroup viewGroup = this.mContainer;
        if (viewGroup != null) {
            viewGroup.setVisibility(visibility);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mCarUxRestrictionsUtil.register(this.mListener);
        if (this.mInstallingExtScrollBar || !this.mScrollBarEnabled) {
            return;
        }
        this.mInstallingExtScrollBar = true;
        installExternalScrollBar();
        this.mInstallingExtScrollBar = false;
    }

    private void installExternalScrollBar() {
        if (this.mContainer.getParent() != null) {
            return;
        }
        this.mContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.car_ui_recycler_view, this.mContainer, true);
        this.mContainer.setVisibility(this.mContainerVisibility);
        Rect rect = this.mContainerPadding;
        if (rect != null) {
            this.mContainer.setPadding(rect.left, this.mContainerPadding.top, this.mContainerPadding.right, this.mContainerPadding.bottom);
        } else {
            Rect rect2 = this.mContainerPaddingRelative;
            if (rect2 != null) {
                this.mContainer.setPaddingRelative(rect2.left, this.mContainerPaddingRelative.top, this.mContainerPaddingRelative.right, this.mContainerPaddingRelative.bottom);
            } else {
                this.mContainer.setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
                setPadding(0, getPaddingTop(), 0, getPaddingBottom());
            }
        }
        this.mContainer.setLayoutParams(getLayoutParams());
        ViewGroup parent = (ViewGroup) getParent();
        int index = parent.indexOfChild(this);
        parent.removeViewInLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
        View viewFindViewByRefId = CarUiUtils.findViewByRefId(this.mContainer, R.id.car_ui_recycler_view);
        Objects.requireNonNull(viewFindViewByRefId);
        ((CarUiRecyclerViewContainer) viewFindViewByRefId).addRecyclerView(this, params);
        parent.addView(this.mContainer, index);
        createScrollBarFromConfig(CarUiUtils.findViewByRefId(this.mContainer, R.id.car_ui_scroll_bar));
    }

    private void createScrollBarFromConfig(View scrollView) {
        Class<?> cls;
        try {
            if (!TextUtils.isEmpty(this.mScrollBarClass)) {
                cls = getContext().getClassLoader().loadClass(this.mScrollBarClass);
            } else {
                cls = DefaultScrollBar.class;
            }
            try {
                ScrollBar scrollBar = (ScrollBar) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                this.mScrollBar = scrollBar;
                scrollBar.initialize(this, scrollView);
                setScrollBarPadding(this.mScrollBarPaddingTop, this.mScrollBarPaddingBottom);
            } catch (Throwable t) {
                throw andLog("Error creating scroll bar component: " + this.mScrollBarClass, t);
            }
        } catch (Throwable t2) {
            throw andLog("Error loading scroll bar component: " + this.mScrollBarClass, t2);
        }
    }

    @Override // android.view.View
    public void setAlpha(float value) {
        if (this.mScrollBarEnabled) {
            this.mContainer.setAlpha(value);
        } else {
            super.setAlpha(value);
        }
    }

    @Override // android.view.View
    public ViewPropertyAnimator animate() {
        return this.mScrollBarEnabled ? this.mContainer.animate() : super.animate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mCarUxRestrictionsUtil.unregister(this.mListener);
    }

    @Override // android.view.View
    public void setPadding(int left, int top, int right, int bottom) {
        this.mContainerPaddingRelative = null;
        if (this.mScrollBarEnabled) {
            super.setPadding(0, top, 0, bottom);
            if (!this.mHasScrolled) {
                scrollToPosition(0);
            }
            this.mContainerPadding = new Rect(left, 0, right, 0);
            ViewGroup viewGroup = this.mContainer;
            if (viewGroup != null) {
                viewGroup.setPadding(left, 0, right, 0);
            }
            setScrollBarPadding(this.mScrollBarPaddingTop, this.mScrollBarPaddingBottom);
            return;
        }
        super.setPadding(left, top, right, bottom);
    }

    @Override // android.view.View
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        this.mContainerPadding = null;
        if (this.mScrollBarEnabled) {
            super.setPaddingRelative(0, top, 0, bottom);
            if (!this.mHasScrolled) {
                scrollToPosition(0);
            }
            this.mContainerPaddingRelative = new Rect(start, 0, end, 0);
            ViewGroup viewGroup = this.mContainer;
            if (viewGroup != null) {
                viewGroup.setPaddingRelative(start, 0, end, 0);
            }
            setScrollBarPadding(this.mScrollBarPaddingTop, this.mScrollBarPaddingBottom);
            return;
        }
        super.setPaddingRelative(start, top, end, bottom);
    }

    public void setScrollBarPadding(int paddingTop, int paddingBottom) {
        if (this.mScrollBarEnabled) {
            this.mScrollBarPaddingTop = paddingTop;
            this.mScrollBarPaddingBottom = paddingBottom;
            ScrollBar scrollBar = this.mScrollBar;
            if (scrollBar != null) {
                scrollBar.setPadding(getPaddingTop() + paddingTop, getPaddingBottom() + paddingBottom);
            }
        }
    }

    public void setLinearDividerItemDecoration(boolean enableDividers) {
        if (enableDividers) {
            addItemDecoration(this.mDividerItemDecorationLinear);
        } else {
            removeItemDecoration(this.mDividerItemDecorationLinear);
        }
    }

    public void setGridDividerItemDecoration(boolean enableDividers) {
        if (enableDividers) {
            addItemDecoration(this.mDividerItemDecorationGrid);
        } else {
            removeItemDecoration(this.mDividerItemDecorationGrid);
        }
    }

    @Override // android.view.View
    public void setContentDescription(CharSequence contentDescription) {
        super.setContentDescription(contentDescription);
        initRotaryScroll(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        ScrollBar scrollBar = this.mScrollBar;
        if (scrollBar != null) {
            scrollBar.adapterChanged(adapter);
        }
        super.setAdapter(adapter);
    }

    private static RuntimeException andLog(String msg, Throwable t) {
        Log.e(TAG, msg, t);
        throw new RuntimeException(msg, t);
    }

    private class UxRestrictionChangedListener implements CarUxRestrictionsUtil.OnUxRestrictionsChangedListener {
        private UxRestrictionChangedListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.android.car.ui.utils.CarUxRestrictionsUtil.OnUxRestrictionsChangedListener
        public void onRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
            RecyclerView.Adapter adapter = CarUiRecyclerView.this.getAdapter();
            if (!(adapter instanceof ItemCap)) {
                return;
            }
            int maxItems = -1;
            if ((carUxRestrictions.getActiveRestrictions() & 32) != 0) {
                maxItems = carUxRestrictions.getMaxCumulativeContentItems();
            }
            int originalCount = adapter.getItemCount();
            ((ItemCap) adapter).setMaxItems(maxItems);
            int newCount = adapter.getItemCount();
            if (newCount == originalCount) {
                return;
            }
            if (newCount < originalCount) {
                adapter.notifyItemRangeRemoved(newCount, originalCount - newCount);
            } else {
                adapter.notifyItemRangeInserted(originalCount, newCount - originalCount);
            }
        }
    }
}
