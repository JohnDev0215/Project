package com.android.systemui.stackdivider;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.TypedValue;
import android.window.WindowContainerTransaction;
import com.android.internal.policy.DividerSnapAlgorithm;
import com.android.internal.policy.DockedDividerUtils;
import com.android.systemui.wm.DisplayLayout;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SplitDisplayLayout {
    private static final float ADJUSTED_STACK_FRACTION_MIN = 0.3f;
    private static final int DIVIDER_WIDTH_INACTIVE_DP = 4;
    Context mContext;
    DisplayLayout mDisplayLayout;
    int mDividerSize;
    int mDividerSizeInactive;
    SplitScreenTaskOrganizer mTiles;
    boolean mResourcesValid = false;
    private DividerSnapAlgorithm mSnapAlgorithm = null;
    private DividerSnapAlgorithm mMinimizedSnapAlgorithm = null;
    Rect mPrimary = null;
    Rect mSecondary = null;
    Rect mAdjustedPrimary = null;
    Rect mAdjustedSecondary = null;

    public SplitDisplayLayout(Context ctx, DisplayLayout dl, SplitScreenTaskOrganizer taskTiles) {
        this.mTiles = taskTiles;
        this.mDisplayLayout = dl;
        this.mContext = ctx;
    }

    void rotateTo(int newRotation) {
        BydLogHelper.logD("rotateTo newRotation : " + newRotation);
        this.mDisplayLayout.rotateTo(this.mContext.getResources(), newRotation);
        Configuration configuration = new Configuration();
        configuration.unset();
        configuration.orientation = this.mDisplayLayout.getOrientation();
        Rect rect = new Rect(0, 0, this.mDisplayLayout.width(), this.mDisplayLayout.height());
        rect.inset(this.mDisplayLayout.nonDecorInsets());
        configuration.windowConfiguration.setAppBounds(rect);
        rect.set(0, 0, this.mDisplayLayout.width(), this.mDisplayLayout.height());
        rect.inset(this.mDisplayLayout.stableInsets());
        configuration.screenWidthDp = (int) (rect.width() / this.mDisplayLayout.density());
        configuration.screenHeightDp = (int) (rect.height() / this.mDisplayLayout.density());
        this.mContext = this.mContext.createConfigurationContext(configuration);
        this.mSnapAlgorithm = null;
        this.mMinimizedSnapAlgorithm = null;
        this.mResourcesValid = false;
    }

    private void updateResources() {
        if (this.mResourcesValid) {
            return;
        }
        BydLogHelper.logD("updateResources");
        this.mResourcesValid = true;
        Resources resources = this.mContext.getResources();
        this.mDividerSize = DockedDividerUtils.getDividerSize(resources, DockedDividerUtils.getDividerInsets(resources));
        this.mDividerSizeInactive = (int) TypedValue.applyDimension(1, 4.0f, resources.getDisplayMetrics());
    }

    int getPrimarySplitSide() {
        BydLogHelper.logD("getPrimarySplitSide NavigationBarPosition : " + this.mDisplayLayout.getNavigationBarPosition(this.mContext.getResources()));
        int navigationBarPosition = this.mDisplayLayout.getNavigationBarPosition(this.mContext.getResources());
        if (navigationBarPosition == 1) {
            return 3;
        }
        if (navigationBarPosition == 2) {
            return 1;
        }
        if (navigationBarPosition != 4) {
            return -1;
        }
        return this.mDisplayLayout.isLandscape() ? 1 : 2;
    }

    DividerSnapAlgorithm getSnapAlgorithm() {
        if (this.mSnapAlgorithm == null) {
            updateResources();
            boolean z = !this.mDisplayLayout.isLandscape();
            BydLogHelper.logD("getSnapAlgorithm width : " + this.mDisplayLayout.width());
            BydLogHelper.logD("getSnapAlgorithm height : " + this.mDisplayLayout.height());
            BydLogHelper.logD("getSnapAlgorithm mDividerSize : " + this.mDividerSize);
            BydLogHelper.logD("getSnapAlgorithm isHorizontalDivision : " + z);
            BydLogHelper.logD("getSnapAlgorithm stableInsets : " + this.mDisplayLayout.stableInsets());
            BydLogHelper.logD("getSnapAlgorithm getPrimarySplitSide : " + getPrimarySplitSide());
            this.mSnapAlgorithm = new DividerSnapAlgorithm(this.mContext.getResources(), this.mDisplayLayout.width(), this.mDisplayLayout.height(), this.mDividerSize, z, this.mDisplayLayout.stableInsets(), getPrimarySplitSide());
        }
        BydLogHelper.logD("getSnapAlgorithm mSnapAlgorithm : " + this.mSnapAlgorithm);
        return this.mSnapAlgorithm;
    }

    DividerSnapAlgorithm getMinimizedSnapAlgorithm(boolean homeStackResizable) {
        if (this.mMinimizedSnapAlgorithm == null) {
            updateResources();
            this.mMinimizedSnapAlgorithm = new DividerSnapAlgorithm(this.mContext.getResources(), this.mDisplayLayout.width(), this.mDisplayLayout.height(), this.mDividerSize, !this.mDisplayLayout.isLandscape(), this.mDisplayLayout.stableInsets(), getPrimarySplitSide(), true, homeStackResizable);
            BydLogHelper.logD("bounds getMinimizedSnapAlgorithm width : " + this.mDisplayLayout.width());
            BydLogHelper.logD("bounds getMinimizedSnapAlgorithm height : " + this.mDisplayLayout.height());
            BydLogHelper.logD("bounds getMinimizedSnapAlgorithm mDividerSize : " + this.mDividerSize);
            BydLogHelper.logD("bounds getMinimizedSnapAlgorithm getPrimarySplitSide : " + getPrimarySplitSide());
            BydLogHelper.logD("bounds getMinimizedSnapAlgorithm mDisplayLayout.stableInsets() : " + this.mDisplayLayout.stableInsets());
        }
        return this.mMinimizedSnapAlgorithm;
    }

    void resizeSplits(int position) {
        BydLogHelper.logD("resizeSplits position : " + position + " , mPrimary : " + this.mPrimary + " , mSecondary : " + this.mSecondary);
        Rect rect = this.mPrimary;
        if (rect == null) {
            rect = new Rect();
        }
        this.mPrimary = rect;
        Rect rect2 = this.mSecondary;
        if (rect2 == null) {
            rect2 = new Rect();
        }
        this.mSecondary = rect2;
        calcSplitBounds(position, this.mPrimary, rect2);
    }

    void resizeSplits(int position, WindowContainerTransaction t) {
        BydLogHelper.logD("resizeSplits position : " + position + " , t : " + t + " , mPrimary : " + this.mPrimary + " , mSecondary : " + this.mSecondary);
        resizeSplits(position);
        BydLogHelper.logD("resizeSplits mTiles.mPrimary.token : " + this.mTiles.mPrimary.token + " , mPrimary : " + this.mPrimary);
        BydLogHelper.logD("resizeSplits mTiles.mSecondary.token : " + this.mTiles.mSecondary.token + " , mSecondary : " + this.mSecondary);
        t.setBounds(this.mTiles.mPrimary.token, this.mPrimary);
        t.setBounds(this.mTiles.mSecondary.token, this.mSecondary);
        t.setSmallestScreenWidthDp(this.mTiles.mPrimary.token, getSmallestWidthDpForBounds(this.mContext, this.mDisplayLayout, this.mPrimary));
        t.setSmallestScreenWidthDp(this.mTiles.mSecondary.token, getSmallestWidthDpForBounds(this.mContext, this.mDisplayLayout, this.mSecondary));
    }

    void calcSplitBounds(int position, Rect outPrimary, Rect outSecondary) {
        int primarySplitSide = getPrimarySplitSide();
        BydLogHelper.logD("calcSplitBounds dockSide : " + primarySplitSide + " , outPrimary : " + outPrimary + " , outSecondary : " + outSecondary + " , mDividerSize : " + this.mDividerSize);
        DockedDividerUtils.calculateBoundsForPosition(position, primarySplitSide, outPrimary, this.mDisplayLayout.width(), this.mDisplayLayout.height(), this.mDividerSize);
        DockedDividerUtils.calculateBoundsForPosition(position, DockedDividerUtils.invertDockSide(primarySplitSide), outSecondary, this.mDisplayLayout.width(), this.mDisplayLayout.height(), this.mDividerSize);
        BydLogHelper.logD("calcSplitBounds position : " + position + " , mDividerSize : " + this.mDividerSize);
        BydLogHelper.logD("calcSplitBounds mPrimary : " + this.mPrimary + " , mSecondary : " + this.mSecondary);
    }

    Rect calcResizableMinimizedHomeStackBounds() {
        DividerSnapAlgorithm.SnapTarget middleTarget = getMinimizedSnapAlgorithm(true).getMiddleTarget();
        Rect rect = new Rect();
        DockedDividerUtils.calculateBoundsForPosition(middleTarget.position, DockedDividerUtils.invertDockSide(getPrimarySplitSide()), rect, this.mDisplayLayout.width(), this.mDisplayLayout.height(), this.mDividerSize);
        BydLogHelper.logD("calcResizableMinimizedHomeStackBounds miniMid.position : " + middleTarget.position);
        BydLogHelper.logD("calcResizableMinimizedHomeStackBounds homeBounds : " + rect);
        BydLogHelper.logD("calcResizableMinimizedHomeStackBounds mDisplayLayout.width() : " + this.mDisplayLayout.width());
        BydLogHelper.logD("calcResizableMinimizedHomeStackBounds mDividerSize : " + this.mDividerSize);
        BydLogHelper.logD("calcResizableMinimizedHomeStackBounds mDisplayLayout.height() : " + this.mDisplayLayout.height());
        return rect;
    }

    void updateAdjustedBounds(int currImeTop, int hiddenTop, int shownTop) {
        adjustForIME(this.mDisplayLayout, currImeTop, hiddenTop, shownTop, this.mDividerSize, this.mDividerSizeInactive, this.mPrimary, this.mSecondary);
    }

    private void adjustForIME(DisplayLayout dl, int currImeTop, int hiddenTop, int shownTop, int dividerWidth, int dividerWidthInactive, Rect primaryBounds, Rect secondaryBounds) {
        if (this.mAdjustedPrimary == null) {
            this.mAdjustedPrimary = new Rect();
            this.mAdjustedSecondary = new Rect();
        }
        BydLogHelper.logD("adjustForIME mAdjustedPrimary : " + this.mAdjustedPrimary + " , mAdjustedSecondary : " + this.mAdjustedSecondary);
        Rect rect = new Rect();
        dl.getStableBounds(rect);
        float f = (currImeTop - hiddenTop) / (shownTop - hiddenTop);
        int iMax = Math.max(0, dl.height() - (currImeTop + Math.max(0, (hiddenTop - shownTop) - (this.mPrimary.bottom - (rect.top + ((int) ((this.mPrimary.bottom - rect.top) * ADJUSTED_STACK_FRACTION_MIN)))))));
        this.mAdjustedPrimary.set(primaryBounds);
        int i = -iMax;
        this.mAdjustedPrimary.offset(0, (dividerWidth - ((int) ((dividerWidthInactive * f) + (dividerWidth * (1.0f - f))))) + i);
        this.mAdjustedSecondary.set(secondaryBounds);
        this.mAdjustedSecondary.offset(0, i);
    }

    static int getSmallestWidthDpForBounds(Context context, DisplayLayout dl, Rect bounds) {
        int dividerSize = DockedDividerUtils.getDividerSize(context.getResources(), DockedDividerUtils.getDividerInsets(context.getResources()));
        BydLogHelper.logD("getSmallestWidthDpForBounds dividerSize : " + dividerSize);
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        Rect rect3 = new Rect(0, 0, dl.width(), dl.height());
        DisplayLayout displayLayout = new DisplayLayout();
        int iMin = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            displayLayout.set(dl);
            displayLayout.rotateTo(context.getResources(), i);
            DividerSnapAlgorithm dividerSnapAlgorithmInitSnapAlgorithmForRotation = initSnapAlgorithmForRotation(context, displayLayout, dividerSize);
            rect.set(bounds);
            DisplayLayout.rotateBounds(rect, rect3, i - dl.rotation());
            rect2.set(0, 0, displayLayout.width(), displayLayout.height());
            int primarySplitSide = getPrimarySplitSide(rect, rect2, displayLayout.getOrientation());
            DockedDividerUtils.calculateBoundsForPosition(dividerSnapAlgorithmInitSnapAlgorithmForRotation.calculateNonDismissingSnapTarget(DockedDividerUtils.calculatePositionForBounds(rect, primarySplitSide, dividerSize)).position, primarySplitSide, rect, displayLayout.width(), displayLayout.height(), dividerSize);
            Rect rect4 = new Rect(rect2);
            rect4.inset(displayLayout.stableInsets());
            rect.intersect(rect4);
            iMin = Math.min(rect.width(), iMin);
        }
        return (int) (iMin / dl.density());
    }

    static DividerSnapAlgorithm initSnapAlgorithmForRotation(Context context, DisplayLayout dl, int dividerSize) {
        BydLogHelper.logD("initSnapAlgorithmForRotation dividerSize : " + dividerSize);
        Configuration configuration = new Configuration();
        configuration.unset();
        configuration.orientation = dl.getOrientation();
        Rect rect = new Rect(0, 0, dl.width(), dl.height());
        rect.inset(dl.nonDecorInsets());
        configuration.windowConfiguration.setAppBounds(rect);
        rect.set(0, 0, dl.width(), dl.height());
        rect.inset(dl.stableInsets());
        configuration.screenWidthDp = (int) (rect.width() / dl.density());
        configuration.screenHeightDp = (int) (rect.height() / dl.density());
        return new DividerSnapAlgorithm(context.createConfigurationContext(configuration).getResources(), dl.width(), dl.height(), dividerSize, configuration.orientation == 1, dl.stableInsets());
    }

    static int getPrimarySplitSide(Rect bounds, Rect displayRect, int orientation) {
        BydLogHelper.logD("getPrimarySplitSide orientation : " + orientation + " , bounds : " + bounds);
        if (orientation == 1) {
            return (displayRect.bottom - bounds.bottom) - (bounds.top - displayRect.top) < 0 ? 4 : 2;
        }
        if (orientation == 2) {
            return (displayRect.right - bounds.right) - (bounds.left - displayRect.left) < 0 ? 3 : 1;
        }
        return -1;
    }
}
