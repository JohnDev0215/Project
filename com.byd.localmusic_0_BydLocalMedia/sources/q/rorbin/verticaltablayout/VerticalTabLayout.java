package q.rorbin.verticaltablayout;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.util.DisplayUtil;
import q.rorbin.verticaltablayout.util.TabFragmentManager;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/* JADX INFO: loaded from: classes5.dex */
public class VerticalTabLayout extends NestedScrollView {
    public static int TAB_MODE_FIXED = 10;
    public static int TAB_MODE_SCROLLABLE = 11;
    private int mColorIndicator;
    private Context mContext;
    private float mIndicatorCorners;
    private int mIndicatorGravity;
    private int mIndicatorWidth;
    private float mLastPositionOffset;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private TabView mSelectedTab;
    private TabAdapter mTabAdapter;
    private TabFragmentManager mTabFragmentManager;
    private int mTabHeight;
    private int mTabMargin;
    private int mTabMode;
    private OnTabPageChangeListener mTabPageChangeListener;
    private List<OnTabSelectedListener> mTabSelectedListeners;
    private TabStrip mTabStrip;
    private ViewPager mViewPager;

    public interface OnTabSelectedListener {
        void onTabReselected(TabView tabView, int i);

        void onTabSelected(TabView tabView, int i);
    }

    public VerticalTabLayout(Context context) {
        this(context, null);
    }

    public VerticalTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerticalTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mTabSelectedListeners = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VerticalTabLayout);
        this.mColorIndicator = typedArrayObtainStyledAttributes.getColor(R.styleable.VerticalTabLayout_indicator_color, context.getResources().getColor(R.color.colorAccent));
        this.mIndicatorWidth = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.VerticalTabLayout_indicator_width, DisplayUtil.dp2px(context, 3.0f));
        this.mIndicatorCorners = typedArrayObtainStyledAttributes.getDimension(R.styleable.VerticalTabLayout_indicator_corners, 0.0f);
        int integer = typedArrayObtainStyledAttributes.getInteger(R.styleable.VerticalTabLayout_indicator_gravity, 3);
        this.mIndicatorGravity = integer;
        if (integer == 3) {
            this.mIndicatorGravity = 3;
        } else if (integer == 5) {
            this.mIndicatorGravity = 5;
        } else if (integer == 119) {
            this.mIndicatorGravity = 119;
        }
        this.mTabMargin = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.VerticalTabLayout_tab_margin, 0.0f);
        this.mTabMode = typedArrayObtainStyledAttributes.getInteger(R.styleable.VerticalTabLayout_tab_mode, TAB_MODE_FIXED);
        this.mTabHeight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.VerticalTabLayout_tab_height, -2);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            removeAllViews();
        }
        initTabStrip();
    }

    private void initTabStrip() {
        TabStrip tabStrip = new TabStrip(this.mContext);
        this.mTabStrip = tabStrip;
        addView(tabStrip, new FrameLayout.LayoutParams(-1, -1));
    }

    public void removeAllTabs() {
        this.mTabStrip.removeAllViews();
        this.mSelectedTab = null;
    }

    public TabView getTabAt(int i) {
        return (TabView) this.mTabStrip.getChildAt(i);
    }

    public int getTabCount() {
        return this.mTabStrip.getChildCount();
    }

    public int getSelectedTabPosition() {
        int iIndexOfChild = this.mTabStrip.indexOfChild(this.mSelectedTab);
        if (iIndexOfChild == -1) {
            return 0;
        }
        return iIndexOfChild;
    }

    private void addTabWithMode(TabView tabView) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        initTabWithMode(layoutParams);
        this.mTabStrip.addView(tabView, layoutParams);
        if (this.mTabStrip.indexOfChild(tabView) == 0) {
            tabView.setChecked(true);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) tabView.getLayoutParams();
            layoutParams2.setMargins(0, 0, 0, 0);
            tabView.setLayoutParams(layoutParams2);
            this.mSelectedTab = tabView;
            this.mTabStrip.post(new Runnable() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.1
                @Override // java.lang.Runnable
                public void run() {
                    VerticalTabLayout.this.mTabStrip.moveIndicator(0.0f);
                }
            });
        }
    }

    private void initTabWithMode(LinearLayout.LayoutParams layoutParams) {
        int i = this.mTabMode;
        if (i == TAB_MODE_FIXED) {
            layoutParams.height = 0;
            layoutParams.weight = 1.0f;
            layoutParams.setMargins(0, 0, 0, 0);
            setFillViewport(true);
            return;
        }
        if (i == TAB_MODE_SCROLLABLE) {
            layoutParams.height = this.mTabHeight;
            layoutParams.weight = 0.0f;
            layoutParams.setMargins(0, this.mTabMargin, 0, 0);
            setFillViewport(false);
        }
    }

    private void scrollToTab(int i) {
        TabView tabAt = getTabAt(i);
        int top = (tabAt.getTop() + (tabAt.getHeight() / 2)) - getScrollY();
        int height = getHeight() / 2;
        if (top > height) {
            smoothScrollBy(0, top - height);
        } else if (top < height) {
            smoothScrollBy(0, top - height);
        }
    }

    private void scrollByTab(int i, float f) {
        TabView tabAt = getTabAt(i);
        int top = (tabAt.getTop() + (tabAt.getHeight() / 2)) - getScrollY();
        int height = getHeight() / 2;
        int height2 = tabAt.getHeight() + this.mTabMargin;
        if (f > 0.0f) {
            float f2 = f - this.mLastPositionOffset;
            if (top > height) {
                smoothScrollBy(0, (int) (height2 * f2));
            }
        }
        this.mLastPositionOffset = f;
    }

    public void addTab(TabView tabView) {
        if (tabView != null) {
            addTabWithMode(tabView);
            tabView.setOnClickListener(new View.OnClickListener() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VerticalTabLayout.this.setTabSelected(VerticalTabLayout.this.mTabStrip.indexOfChild(view));
                }
            });
            return;
        }
        throw new IllegalStateException("tabview can't be null");
    }

    public void setTabSelected(int i) {
        setTabSelected(i, true, true);
    }

    public void setTabSelected(int i, boolean z) {
        setTabSelected(i, true, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTabSelected(final int i, final boolean z, final boolean z2) {
        post(new Runnable() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.3
            @Override // java.lang.Runnable
            public void run() {
                VerticalTabLayout.this.setTabSelectedImpl(i, z, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTabSelectedImpl(int i, boolean z, boolean z2) {
        TabView tabAt = getTabAt(i);
        boolean z3 = tabAt != this.mSelectedTab;
        if (z3) {
            TabView tabView = this.mSelectedTab;
            if (tabView != null) {
                tabView.setChecked(false);
            }
            tabAt.setChecked(true);
            if (z) {
                this.mTabStrip.moveIndicatorWithAnimator(i);
            }
            this.mSelectedTab = tabAt;
            scrollToTab(i);
        }
        if (z2) {
            for (int i2 = 0; i2 < this.mTabSelectedListeners.size(); i2++) {
                OnTabSelectedListener onTabSelectedListener = this.mTabSelectedListeners.get(i2);
                if (onTabSelectedListener != null) {
                    if (z3) {
                        onTabSelectedListener.onTabSelected(tabAt, i);
                    } else {
                        onTabSelectedListener.onTabReselected(tabAt, i);
                    }
                }
            }
        }
    }

    public void setTabBadge(int i, int i2) {
        getTabAt(i).getBadgeView().setBadgeNumber(i2);
    }

    public void setTabBadge(int i, String str) {
        getTabAt(i).getBadgeView().setBadgeText(str);
    }

    public void setTabMode(int i) {
        if (i != TAB_MODE_FIXED && i != TAB_MODE_SCROLLABLE) {
            throw new IllegalStateException("only support TAB_MODE_FIXED or TAB_MODE_SCROLLABLE");
        }
        if (i == this.mTabMode) {
            return;
        }
        this.mTabMode = i;
        for (int i2 = 0; i2 < this.mTabStrip.getChildCount(); i2++) {
            View childAt = this.mTabStrip.getChildAt(i2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            initTabWithMode(layoutParams);
            if (i2 == 0) {
                layoutParams.setMargins(0, 0, 0, 0);
            }
            childAt.setLayoutParams(layoutParams);
        }
        this.mTabStrip.invalidate();
        this.mTabStrip.post(new Runnable() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.4
            @Override // java.lang.Runnable
            public void run() {
                VerticalTabLayout.this.mTabStrip.updataIndicator();
            }
        });
    }

    public void setTabMargin(int i) {
        if (i == this.mTabMargin) {
            return;
        }
        this.mTabMargin = i;
        if (this.mTabMode == TAB_MODE_FIXED) {
            return;
        }
        int i2 = 0;
        while (i2 < this.mTabStrip.getChildCount()) {
            View childAt = this.mTabStrip.getChildAt(i2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.setMargins(0, i2 == 0 ? 0 : this.mTabMargin, 0, 0);
            childAt.setLayoutParams(layoutParams);
            i2++;
        }
        this.mTabStrip.invalidate();
        this.mTabStrip.post(new Runnable() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.5
            @Override // java.lang.Runnable
            public void run() {
                VerticalTabLayout.this.mTabStrip.updataIndicator();
            }
        });
    }

    public void setTabHeight(int i) {
        if (i == this.mTabHeight) {
            return;
        }
        this.mTabHeight = i;
        if (this.mTabMode == TAB_MODE_FIXED) {
            return;
        }
        for (int i2 = 0; i2 < this.mTabStrip.getChildCount(); i2++) {
            View childAt = this.mTabStrip.getChildAt(i2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.height = this.mTabHeight;
            childAt.setLayoutParams(layoutParams);
        }
        this.mTabStrip.invalidate();
        this.mTabStrip.post(new Runnable() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.6
            @Override // java.lang.Runnable
            public void run() {
                VerticalTabLayout.this.mTabStrip.updataIndicator();
            }
        });
    }

    public void setIndicatorColor(int i) {
        this.mColorIndicator = i;
        this.mTabStrip.invalidate();
    }

    public void setIndicatorWidth(int i) {
        this.mIndicatorWidth = i;
        this.mTabStrip.setIndicatorGravity();
    }

    public void setIndicatorCorners(int i) {
        this.mIndicatorCorners = i;
        this.mTabStrip.invalidate();
    }

    public void setIndicatorGravity(int i) {
        if (i == 3 || i == 5 || 119 == i) {
            this.mIndicatorGravity = i;
            this.mTabStrip.setIndicatorGravity();
            return;
        }
        throw new IllegalStateException("only support Gravity.LEFT,Gravity.RIGHT,Gravity.FILL");
    }

    public void addOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        if (onTabSelectedListener != null) {
            this.mTabSelectedListeners.add(onTabSelectedListener);
        }
    }

    public void removeOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        if (onTabSelectedListener != null) {
            this.mTabSelectedListeners.remove(onTabSelectedListener);
        }
    }

    public void setTabAdapter(TabAdapter tabAdapter) {
        removeAllTabs();
        if (tabAdapter != null) {
            this.mTabAdapter = tabAdapter;
            for (int i = 0; i < tabAdapter.getCount(); i++) {
                addTab(new QTabView(this.mContext).setIcon(tabAdapter.getIcon(i)).setTitle(tabAdapter.getTitle(i)).setBadge(tabAdapter.getBadge(i)).setBackground(tabAdapter.getBackground(i)));
            }
        }
    }

    public void setupWithFragment(FragmentManager fragmentManager, List<Fragment> list) {
        setupWithFragment(fragmentManager, 0, list);
    }

    public void setupWithFragment(FragmentManager fragmentManager, List<Fragment> list, TabAdapter tabAdapter) {
        setupWithFragment(fragmentManager, 0, list, tabAdapter);
    }

    public void setupWithFragment(FragmentManager fragmentManager, int i, List<Fragment> list) {
        TabFragmentManager tabFragmentManager = this.mTabFragmentManager;
        if (tabFragmentManager != null) {
            tabFragmentManager.detach();
        }
        if (i != 0) {
            this.mTabFragmentManager = new TabFragmentManager(fragmentManager, i, list, this);
        } else {
            this.mTabFragmentManager = new TabFragmentManager(fragmentManager, list, this);
        }
    }

    public void setupWithFragment(FragmentManager fragmentManager, int i, List<Fragment> list, TabAdapter tabAdapter) {
        setTabAdapter(tabAdapter);
        setupWithFragment(fragmentManager, i, list);
    }

    public void setupWithViewPager(ViewPager viewPager) {
        OnTabPageChangeListener onTabPageChangeListener;
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 != null && (onTabPageChangeListener = this.mTabPageChangeListener) != null) {
            viewPager2.removeOnPageChangeListener(onTabPageChangeListener);
        }
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter == null) {
                throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
            }
            this.mViewPager = viewPager;
            if (this.mTabPageChangeListener == null) {
                this.mTabPageChangeListener = new OnTabPageChangeListener();
            }
            viewPager.addOnPageChangeListener(this.mTabPageChangeListener);
            addOnTabSelectedListener(new OnTabSelectedListener() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.7
                @Override // q.rorbin.verticaltablayout.VerticalTabLayout.OnTabSelectedListener
                public void onTabReselected(TabView tabView, int i) {
                }

                @Override // q.rorbin.verticaltablayout.VerticalTabLayout.OnTabSelectedListener
                public void onTabSelected(TabView tabView, int i) {
                    if (VerticalTabLayout.this.mViewPager == null || VerticalTabLayout.this.mViewPager.getAdapter().getCount() < i) {
                        return;
                    }
                    VerticalTabLayout.this.mViewPager.setCurrentItem(i);
                }
            });
            setPagerAdapter(adapter, true);
            return;
        }
        this.mViewPager = null;
        setPagerAdapter(null, true);
    }

    private void setPagerAdapter(PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.mPagerAdapter;
        if (pagerAdapter2 != null && (dataSetObserver = this.mPagerAdapterObserver) != null) {
            pagerAdapter2.unregisterDataSetObserver(dataSetObserver);
        }
        this.mPagerAdapter = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.mPagerAdapterObserver == null) {
                this.mPagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.registerDataSetObserver(this.mPagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateFromPagerAdapter() {
        int currentItem;
        removeAllTabs();
        PagerAdapter pagerAdapter = this.mPagerAdapter;
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            Object obj = this.mPagerAdapter;
            if (obj instanceof TabAdapter) {
                setTabAdapter((TabAdapter) obj);
            } else {
                for (int i = 0; i < count; i++) {
                    addTab(new QTabView(this.mContext).setTitle(new ITabView.TabTitle.Builder().setContent(this.mPagerAdapter.getPageTitle(i) == null ? "tab" + i : this.mPagerAdapter.getPageTitle(i).toString()).build()));
                }
            }
            ViewPager viewPager = this.mViewPager;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            setTabSelected(currentItem);
            return;
        }
        removeAllTabs();
    }

    private class TabStrip extends LinearLayout {
        private AnimatorSet mIndicatorAnimatorSet;
        private float mIndicatorBottomY;
        private Paint mIndicatorPaint;
        private RectF mIndicatorRect;
        private float mIndicatorTopY;
        private float mIndicatorX;
        private int mLastWidth;

        public TabStrip(Context context) {
            super(context);
            setWillNotDraw(false);
            setOrientation(1);
            Paint paint = new Paint();
            this.mIndicatorPaint = paint;
            paint.setAntiAlias(true);
            VerticalTabLayout.this.mIndicatorGravity = VerticalTabLayout.this.mIndicatorGravity == 0 ? 3 : VerticalTabLayout.this.mIndicatorGravity;
            this.mIndicatorRect = new RectF();
            setIndicatorGravity();
        }

        protected void setIndicatorGravity() {
            if (VerticalTabLayout.this.mIndicatorGravity != 3) {
                if (VerticalTabLayout.this.mIndicatorGravity != 5) {
                    if (VerticalTabLayout.this.mIndicatorGravity == 119) {
                        this.mIndicatorX = 0.0f;
                        setPadding(0, 0, 0, 0);
                    }
                } else {
                    int i = this.mLastWidth;
                    if (i != 0) {
                        VerticalTabLayout.this.mIndicatorWidth = i;
                    }
                    setPadding(0, 0, VerticalTabLayout.this.mIndicatorWidth, 0);
                }
            } else {
                this.mIndicatorX = 0.0f;
                int i2 = this.mLastWidth;
                if (i2 != 0) {
                    VerticalTabLayout.this.mIndicatorWidth = i2;
                }
                setPadding(VerticalTabLayout.this.mIndicatorWidth, 0, 0, 0);
            }
            post(new Runnable() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.TabStrip.1
                @Override // java.lang.Runnable
                public void run() {
                    if (VerticalTabLayout.this.mIndicatorGravity != 5) {
                        if (VerticalTabLayout.this.mIndicatorGravity == 119) {
                            TabStrip tabStrip = TabStrip.this;
                            tabStrip.mLastWidth = VerticalTabLayout.this.mIndicatorWidth;
                            VerticalTabLayout.this.mIndicatorWidth = TabStrip.this.getWidth();
                        }
                    } else {
                        TabStrip.this.mIndicatorX = r0.getWidth() - VerticalTabLayout.this.mIndicatorWidth;
                    }
                    TabStrip.this.invalidate();
                }
            });
        }

        private void calcIndicatorY(float f) {
            double d = f;
            int iFloor = (int) Math.floor(d);
            View childAt = getChildAt(iFloor);
            if (Math.floor(d) != getChildCount() - 1 && Math.ceil(d) != 0.0d) {
                View childAt2 = getChildAt(iFloor + 1);
                float f2 = f - iFloor;
                this.mIndicatorTopY = childAt.getTop() + ((childAt2.getTop() - childAt.getTop()) * f2);
                this.mIndicatorBottomY = childAt.getBottom() + ((childAt2.getBottom() - childAt.getBottom()) * f2);
                return;
            }
            this.mIndicatorTopY = childAt.getTop();
            this.mIndicatorBottomY = childAt.getBottom();
        }

        protected void updataIndicator() {
            if (getChildCount() > 0) {
                moveIndicatorWithAnimator(VerticalTabLayout.this.getSelectedTabPosition());
            }
        }

        protected void moveIndicator(float f) {
            calcIndicatorY(f);
            invalidate();
        }

        protected void moveIndicatorWithAnimator(int i) {
            final int selectedTabPosition = i - VerticalTabLayout.this.getSelectedTabPosition();
            View childAt = getChildAt(i);
            final float top = childAt.getTop();
            final float bottom = childAt.getBottom();
            if (this.mIndicatorTopY == top && this.mIndicatorBottomY == bottom) {
                return;
            }
            AnimatorSet animatorSet = this.mIndicatorAnimatorSet;
            if (animatorSet != null && animatorSet.isRunning()) {
                this.mIndicatorAnimatorSet.end();
            }
            post(new Runnable() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.TabStrip.2
                @Override // java.lang.Runnable
                public void run() {
                    ValueAnimator duration;
                    int i2 = selectedTabPosition;
                    ValueAnimator duration2 = null;
                    if (i2 > 0) {
                        duration2 = ValueAnimator.ofFloat(TabStrip.this.mIndicatorBottomY, bottom).setDuration(100L);
                        duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.TabStrip.2.1
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                TabStrip.this.mIndicatorBottomY = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                                TabStrip.this.invalidate();
                            }
                        });
                        duration = ValueAnimator.ofFloat(TabStrip.this.mIndicatorTopY, top).setDuration(100L);
                        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.TabStrip.2.2
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                TabStrip.this.mIndicatorTopY = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                                TabStrip.this.invalidate();
                            }
                        });
                    } else if (i2 < 0) {
                        duration2 = ValueAnimator.ofFloat(TabStrip.this.mIndicatorTopY, top).setDuration(100L);
                        duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.TabStrip.2.3
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                TabStrip.this.mIndicatorTopY = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                                TabStrip.this.invalidate();
                            }
                        });
                        duration = ValueAnimator.ofFloat(TabStrip.this.mIndicatorBottomY, bottom).setDuration(100L);
                        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: q.rorbin.verticaltablayout.VerticalTabLayout.TabStrip.2.4
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                TabStrip.this.mIndicatorBottomY = Float.parseFloat(valueAnimator.getAnimatedValue().toString());
                                TabStrip.this.invalidate();
                            }
                        });
                    } else {
                        duration = null;
                    }
                    if (duration2 != null) {
                        TabStrip.this.mIndicatorAnimatorSet = new AnimatorSet();
                        TabStrip.this.mIndicatorAnimatorSet.play(duration).after(duration2);
                        TabStrip.this.mIndicatorAnimatorSet.start();
                    }
                }
            });
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.mIndicatorPaint.setColor(VerticalTabLayout.this.mColorIndicator);
            this.mIndicatorRect.left = this.mIndicatorX;
            this.mIndicatorRect.top = this.mIndicatorTopY;
            this.mIndicatorRect.right = this.mIndicatorX + VerticalTabLayout.this.mIndicatorWidth;
            this.mIndicatorRect.bottom = this.mIndicatorBottomY;
            if (VerticalTabLayout.this.mIndicatorCorners != 0.0f) {
                canvas.drawRoundRect(this.mIndicatorRect, VerticalTabLayout.this.mIndicatorCorners, VerticalTabLayout.this.mIndicatorCorners, this.mIndicatorPaint);
            } else {
                canvas.drawRect(this.mIndicatorRect, this.mIndicatorPaint);
            }
        }
    }

    private class OnTabPageChangeListener implements ViewPager.OnPageChangeListener {
        private int mPreviousScrollState;
        private int mScrollState;
        boolean mUpdataIndicator;

        public OnTabPageChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            int i2 = this.mScrollState;
            this.mPreviousScrollState = i2;
            this.mScrollState = i;
            this.mUpdataIndicator = (i == 2 && i2 == 0) ? false : true;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            if (this.mUpdataIndicator) {
                VerticalTabLayout.this.mTabStrip.moveIndicator(f + i);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (i != VerticalTabLayout.this.getSelectedTabPosition()) {
                VerticalTabLayout.this.setTabSelected(i, !this.mUpdataIndicator, true);
            }
        }
    }

    private class PagerAdapterObserver extends DataSetObserver {
        private PagerAdapterObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            VerticalTabLayout.this.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            VerticalTabLayout.this.populateFromPagerAdapter();
        }
    }
}
