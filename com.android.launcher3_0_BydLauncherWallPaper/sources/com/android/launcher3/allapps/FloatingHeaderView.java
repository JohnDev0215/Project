package com.android.launcher3.allapps;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Insettable;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.anim.PropertySetter;
import com.android.launcher3.uioverrides.plugins.PluginManagerWrapper;
import com.android.systemui.plugins.AllAppsRow;
import com.android.systemui.plugins.PluginListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class FloatingHeaderView extends LinearLayout implements ValueAnimator.AnimatorUpdateListener, PluginListener<AllAppsRow>, Insettable, AllAppsRow.OnHeightUpdatedListener {
    private FloatingHeaderRow[] mAllRows;
    private boolean mAllowTouchForwarding;
    private final ValueAnimator mAnimator;
    private final Rect mClip;
    private boolean mCollapsed;
    private AllAppsRecyclerView mCurrentRV;
    private FloatingHeaderRow[] mFixedRows;
    private boolean mForwardToRecyclerView;
    private boolean mHeaderCollapsed;
    private final int mHeaderTopPadding;
    private AllAppsRecyclerView mMainRV;
    private boolean mMainRVActive;
    protected int mMaxTranslation;
    private final RecyclerView.OnScrollListener mOnScrollListener;
    private ViewGroup mParent;
    protected final Map<AllAppsRow, PluginHeaderRow> mPluginRows;
    private int mSnappedScrolledY;
    protected ViewGroup mTabLayout;
    protected boolean mTabsHidden;
    private final Point mTempOffset;
    private int mTranslationY;
    private AllAppsRecyclerView mWorkRV;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public FloatingHeaderView(Context context) {
        this(context, null);
    }

    public FloatingHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mClip = new Rect(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.mAnimator = ValueAnimator.ofInt(0, 0);
        this.mTempOffset = new Point();
        this.mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.android.launcher3.allapps.FloatingHeaderView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                if (rv != FloatingHeaderView.this.mCurrentRV) {
                    return;
                }
                if (FloatingHeaderView.this.mAnimator.isStarted()) {
                    FloatingHeaderView.this.mAnimator.cancel();
                }
                FloatingHeaderView.this.moved(-FloatingHeaderView.this.mCurrentRV.getCurrentScrollY());
                FloatingHeaderView.this.applyVerticalMove();
            }
        };
        this.mPluginRows = new ArrayMap();
        this.mMainRVActive = true;
        this.mCollapsed = false;
        this.mFixedRows = FloatingHeaderRow.NO_ROWS;
        this.mAllRows = FloatingHeaderRow.NO_ROWS;
        this.mHeaderTopPadding = context.getResources().getDimensionPixelSize(R.dimen.all_apps_header_top_padding);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTabLayout = (ViewGroup) findViewById(R.id.tabs);
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            KeyEvent.Callback childAt = getChildAt(i);
            if (childAt instanceof FloatingHeaderRow) {
                arrayList.add((FloatingHeaderRow) childAt);
            }
        }
        FloatingHeaderRow[] floatingHeaderRowArr = (FloatingHeaderRow[]) arrayList.toArray(new FloatingHeaderRow[arrayList.size()]);
        this.mFixedRows = floatingHeaderRowArr;
        this.mAllRows = floatingHeaderRowArr;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        PluginManagerWrapper.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext()).addPluginListener(this, AllAppsRow.class, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        PluginManagerWrapper.INSTANCE.lambda$get$0$MainThreadInitializedObject(getContext()).removePluginListener(this);
    }

    private void recreateAllRowsArray() {
        int size = this.mPluginRows.size();
        if (size == 0) {
            this.mAllRows = this.mFixedRows;
            return;
        }
        int length = this.mFixedRows.length;
        this.mAllRows = new FloatingHeaderRow[size + length];
        for (int i = 0; i < length; i++) {
            this.mAllRows[i] = this.mFixedRows[i];
        }
        Iterator<PluginHeaderRow> it = this.mPluginRows.values().iterator();
        while (it.hasNext()) {
            this.mAllRows[length] = it.next();
            length++;
        }
    }

    @Override // com.android.systemui.plugins.PluginListener
    public void onPluginConnected(AllAppsRow allAppsRowPlugin, Context context) {
        PluginHeaderRow pluginHeaderRow = new PluginHeaderRow(allAppsRowPlugin, this);
        addView(pluginHeaderRow.mView, indexOfChild(this.mTabLayout));
        this.mPluginRows.put(allAppsRowPlugin, pluginHeaderRow);
        recreateAllRowsArray();
        allAppsRowPlugin.setOnHeightUpdatedListener(this);
    }

    @Override // com.android.systemui.plugins.AllAppsRow.OnHeightUpdatedListener
    public void onHeightUpdated() {
        AllAppsContainerView allAppsContainerView;
        int i = this.mMaxTranslation;
        updateExpectedHeight();
        if (this.mMaxTranslation == i || (allAppsContainerView = (AllAppsContainerView) getParent()) == null) {
            return;
        }
        allAppsContainerView.setupHeader();
    }

    @Override // com.android.systemui.plugins.PluginListener
    public void onPluginDisconnected(AllAppsRow plugin) {
        removeView(this.mPluginRows.get(plugin).mView);
        this.mPluginRows.remove(plugin);
        recreateAllRowsArray();
        onHeightUpdated();
    }

    public void setup(AllAppsContainerView.AdapterHolder[] mAH, boolean tabsHidden) {
        for (FloatingHeaderRow floatingHeaderRow : this.mAllRows) {
            floatingHeaderRow.setup(this, this.mAllRows, tabsHidden);
        }
        updateExpectedHeight();
        this.mTabsHidden = tabsHidden;
        this.mTabLayout.setVisibility(tabsHidden ? 8 : 0);
        this.mMainRV = setupRV(this.mMainRV, mAH[0].recyclerView);
        boolean z = true;
        this.mWorkRV = setupRV(this.mWorkRV, mAH[1].recyclerView);
        this.mParent = (ViewGroup) this.mMainRV.getParent();
        if (!this.mMainRVActive && this.mWorkRV != null) {
            z = false;
        }
        setMainActive(z);
        reset(false);
    }

    private AllAppsRecyclerView setupRV(AllAppsRecyclerView old, AllAppsRecyclerView updated) {
        if (old != updated && updated != null) {
            updated.addOnScrollListener(this.mOnScrollListener);
        }
        return updated;
    }

    private void updateExpectedHeight() {
        this.mMaxTranslation = 0;
        if (this.mCollapsed) {
            return;
        }
        for (FloatingHeaderRow floatingHeaderRow : this.mAllRows) {
            this.mMaxTranslation += floatingHeaderRow.getExpectedHeight();
        }
    }

    public void setMainActive(boolean active) {
        this.mCurrentRV = active ? this.mMainRV : this.mWorkRV;
        this.mMainRVActive = active;
    }

    public int getMaxTranslation() {
        int i = this.mMaxTranslation;
        if (i == 0 && this.mTabsHidden) {
            return getResources().getDimensionPixelSize(R.dimen.all_apps_search_bar_bottom_padding);
        }
        return (i <= 0 || !this.mTabsHidden) ? i : i + getPaddingTop();
    }

    private boolean canSnapAt(int currentScrollY) {
        return Math.abs(currentScrollY) <= this.mMaxTranslation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moved(final int currentScrollY) {
        boolean z = this.mHeaderCollapsed;
        if (z) {
            if (currentScrollY <= this.mSnappedScrolledY) {
                if (canSnapAt(currentScrollY)) {
                    this.mSnappedScrolledY = currentScrollY;
                }
            } else {
                this.mHeaderCollapsed = false;
            }
            this.mTranslationY = currentScrollY;
            return;
        }
        if (z) {
            return;
        }
        int i = currentScrollY - this.mSnappedScrolledY;
        int i2 = this.mMaxTranslation;
        int i3 = i - i2;
        this.mTranslationY = i3;
        if (i3 >= 0) {
            this.mTranslationY = 0;
            this.mSnappedScrolledY = currentScrollY - i2;
        } else if (i3 <= (-i2)) {
            this.mHeaderCollapsed = true;
            this.mSnappedScrolledY = -i2;
        }
    }

    protected void applyVerticalMove() {
        int i = this.mTranslationY;
        int iMax = Math.max(i, -this.mMaxTranslation);
        this.mTranslationY = iMax;
        if (this.mCollapsed || i < iMax - this.mHeaderTopPadding) {
            for (FloatingHeaderRow floatingHeaderRow : this.mAllRows) {
                floatingHeaderRow.setVerticalScroll(0, true);
            }
        } else {
            for (FloatingHeaderRow floatingHeaderRow2 : this.mAllRows) {
                floatingHeaderRow2.setVerticalScroll(i, false);
            }
        }
        this.mTabLayout.setTranslationY(this.mTranslationY);
        this.mClip.top = this.mMaxTranslation + this.mTranslationY;
        this.mMainRV.setClipBounds(this.mClip);
        AllAppsRecyclerView allAppsRecyclerView = this.mWorkRV;
        if (allAppsRecyclerView != null) {
            allAppsRecyclerView.setClipBounds(this.mClip);
        }
    }

    public void setCollapsed(boolean collapse) {
        if (this.mCollapsed == collapse) {
            return;
        }
        this.mCollapsed = collapse;
        onHeightUpdated();
    }

    public void reset(boolean animate) {
        if (this.mAnimator.isStarted()) {
            this.mAnimator.cancel();
        }
        if (animate) {
            this.mAnimator.setIntValues(this.mTranslationY, 0);
            this.mAnimator.addUpdateListener(this);
            this.mAnimator.setDuration(150L);
            this.mAnimator.start();
        } else {
            this.mTranslationY = 0;
            applyVerticalMove();
        }
        this.mHeaderCollapsed = false;
        this.mSnappedScrolledY = -this.mMaxTranslation;
        this.mCurrentRV.scrollToTop();
    }

    public boolean isExpanded() {
        return !this.mHeaderCollapsed;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator animation) {
        this.mTranslationY = ((Integer) animation.getAnimatedValue()).intValue();
        applyVerticalMove();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.mAllowTouchForwarding) {
            this.mForwardToRecyclerView = false;
            return super.onInterceptTouchEvent(ev);
        }
        calcOffset(this.mTempOffset);
        ev.offsetLocation(this.mTempOffset.x, this.mTempOffset.y);
        this.mForwardToRecyclerView = this.mCurrentRV.onInterceptTouchEvent(ev);
        ev.offsetLocation(-this.mTempOffset.x, -this.mTempOffset.y);
        return this.mForwardToRecyclerView || super.onInterceptTouchEvent(ev);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mForwardToRecyclerView) {
            calcOffset(this.mTempOffset);
            event.offsetLocation(this.mTempOffset.x, this.mTempOffset.y);
            try {
                return this.mCurrentRV.onTouchEvent(event);
            } finally {
                event.offsetLocation(-this.mTempOffset.x, -this.mTempOffset.y);
            }
        }
        return super.onTouchEvent(event);
    }

    private void calcOffset(Point p) {
        p.x = (getLeft() - this.mCurrentRV.getLeft()) - this.mParent.getLeft();
        p.y = (getTop() - this.mCurrentRV.getTop()) - this.mParent.getTop();
    }

    public void setContentVisibility(boolean hasHeader, boolean hasContent, PropertySetter setter, Interpolator fadeInterpolator) {
        for (FloatingHeaderRow floatingHeaderRow : this.mAllRows) {
            floatingHeaderRow.setContentVisibility(hasHeader, hasContent, setter, fadeInterpolator);
        }
        allowTouchForwarding(hasContent);
        setter.setFloat(this.mTabLayout, ALPHA, hasContent ? 1.0f : 0.0f, fadeInterpolator);
    }

    protected void allowTouchForwarding(boolean allow) {
        this.mAllowTouchForwarding = allow;
    }

    public boolean hasVisibleContent() {
        for (FloatingHeaderRow floatingHeaderRow : this.mAllRows) {
            if (floatingHeaderRow.hasVisibleContent()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        for (FloatingHeaderRow floatingHeaderRow : this.mAllRows) {
            floatingHeaderRow.setInsets(insets, deviceProfile);
        }
    }

    public <T extends FloatingHeaderRow> T findFixedRowByType(Class<T> cls) {
        for (FloatingHeaderRow floatingHeaderRow : this.mAllRows) {
            T t = (T) floatingHeaderRow;
            if (t.getTypeClass() == cls) {
                return t;
            }
        }
        return null;
    }
}
