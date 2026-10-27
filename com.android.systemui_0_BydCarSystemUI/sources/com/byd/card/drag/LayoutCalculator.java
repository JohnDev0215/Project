package com.byd.card.drag;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import com.android.systemui.R;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.DensityUtil;

/* JADX INFO: loaded from: classes.dex */
public class LayoutCalculator {
    public static int APP_COLUMNS = 5;
    public static int APP_COLUMNS_LAND = 5;
    public static int APP_COLUMNS_PORT = 4;
    public static int APP_ROWS = 3;
    public static final int BLACKCIRCLE_RADIUS = 13;
    public static int FOLDER_COLUMNS = 3;
    private static final int FOLDER_GAP = 3;
    private static final int FOLDER_ICON_SIZE = 13;
    public static int FOLDER_ROWS = 3;
    public static final int ICON_HEIGHT = 50;
    public static final int ICON_WIDTH = 60;
    public static final int PAGER_GAP = 10;
    public static final int PAGER_HEIGHT = 8;
    public static final int REDCIRCLE_MARGIN_TOP = 8;
    public static final int REDCIRCLE_RADIUS = 12;
    private static final float TABLET_RECOMMENDED_SIZE = 4.8f;
    private static final float TABLET_SIZE = 4.5f;
    public static final int WALLPAPER_SHADOW_ADJUSTMENT = 16;
    private static final float WIDE_SIZE = 3.8f;
    public static int iconsPerPage = 5 * 3;
    public int bcMarginLeft;
    public int bcMarginTop;
    public int cMarginTop;
    public int folderGap;
    public int folderIconSize;
    public int folderMargin;
    public int folderMaxHeight;
    public int fullMarginLeft;
    public int fullMarginLeftBlackCircle;
    public int fullMarginRight;
    public int gapH;
    public int gapV;
    public int height;
    public int iconHeight;
    public int iconLastMarginRight;
    public int iconMarginLeft;
    public int iconMarginLeftBlackCircle;
    public int iconWidth;
    public boolean isHighResolution;
    public int itemHeight;
    public int itemMarginLeft;
    public int itemMarginRight;
    public int itemWidth;
    private Context mContext;
    public int mFolderGapH;
    public int mFolderGapV;
    private int mSearchLayoutWidth;
    public int marginLeft;
    public int marginTop;
    public int pageHeight;
    public int pagerGap;
    public int rcMarginRight;
    public int rcMarginTop;
    private float scale;
    public int searchIconHeight;
    public int searchIconWidth;
    public int shadowMarginBottom;
    public int shadowMarginLeft;
    public int shadowMarginRight;
    public int textTop;
    public int width;
    private String TAG = "LayoutCalculator";
    public boolean canBeTablet = false;
    public boolean isPortrait = true;
    public boolean isTablet = false;
    public boolean isWide = false;
    public int mFolderLeft = 0;
    public int mFolderRight = 0;
    private int mFolderTop = 0;
    private int mMoreAppLeft = 0;
    private int mMoreAppRight = 0;
    private int mFolderHeight = 0;
    private int mFolderWidth = 0;

    public LayoutCalculator(Context context) {
        this.mContext = null;
        this.mContext = context;
        if (AppUtils.isLandSpace(context)) {
            APP_COLUMNS = 5;
        } else {
            APP_COLUMNS = 4;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.scale = displayMetrics.density;
        int i = displayMetrics.densityDpi;
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_72);
        this.iconHeight = dimension;
        this.iconWidth = dimension;
        this.textTop = this.iconHeight + DensityUtil.dip2px(this.mContext, 18.0f) + ((int) context.getResources().getDimension(R.dimen.dp_6));
        this.itemHeight = this.iconHeight + DensityUtil.dip2px(this.mContext, 24.0f);
        this.itemWidth = DensityUtil.dip2px(this.mContext, 128.0f);
        int iDip2px = DensityUtil.dip2px(this.mContext, 13.0f) - DensityUtil.dip2px(this.mContext, 1.0f);
        this.bcMarginLeft = iDip2px;
        this.bcMarginTop = iDip2px;
        int iDip2px2 = DensityUtil.dip2px(this.mContext, 8.0f);
        this.rcMarginTop = iDip2px2;
        this.rcMarginRight = iDip2px2;
        this.cMarginTop = Math.max(this.bcMarginTop, iDip2px2);
        this.pagerGap = DensityUtil.dip2px(this.mContext, 10.0f);
        this.folderIconSize = expandWidth(13);
        this.folderGap = (this.iconWidth * 3) / 60;
        this.folderMargin = DensityUtil.dip2px(this.mContext, 20.0f);
    }

    public int getIconWidth() {
        return this.iconWidth;
    }

    public int getItemWidth() {
        return this.iconMarginLeft + this.iconWidth + this.iconLastMarginRight;
    }

    public int expandWidth(int dp) {
        return (dp * this.iconWidth) / 60;
    }

    public void layoutReady(ViewGroup viewGroup) {
        this.width = viewGroup.getWidth();
        this.height = viewGroup.getHeight();
        int i = APP_COLUMNS;
        iconsPerPage = APP_ROWS * i;
        int i2 = this.width - (this.iconWidth * i);
        int iDip2px = DensityUtil.dip2px(this.mContext, 10.0f) * APP_COLUMNS;
        int iDip2px2 = this.height - DensityUtil.dip2px(this.mContext, 8.0f);
        int i3 = this.itemHeight;
        int i4 = APP_ROWS;
        int i5 = iDip2px2 - (i3 * i4);
        this.gapV = i5 / (i4 + 1);
        int i6 = (i2 + iDip2px) / (APP_COLUMNS + 1);
        this.gapH = i6;
        this.marginLeft = -iDip2px;
        this.marginTop = i5 % (i4 + 2);
        int iMin = Math.min(this.iconWidth / 4, i6 / 2);
        this.iconMarginLeft = iMin;
        this.iconMarginLeftBlackCircle = Math.max(iMin, this.bcMarginLeft);
        this.iconLastMarginRight = Math.max(this.iconMarginLeft, this.rcMarginRight);
        this.fullMarginLeft = Math.max(this.shadowMarginLeft, this.iconMarginLeftBlackCircle);
        this.fullMarginRight = Math.max(this.shadowMarginRight, this.iconLastMarginRight);
        this.fullMarginLeftBlackCircle = Math.max(this.fullMarginLeft, this.bcMarginLeft);
        int iDip2px3 = DensityUtil.dip2px(this.mContext, 24.0f);
        this.itemMarginRight = iDip2px3;
        this.itemMarginLeft = iDip2px3;
    }

    public Rect toItemRect(int x, int y) {
        Rect rect = new Rect();
        rect.left = x - this.iconMarginLeft;
        rect.right = x + this.iconWidth + this.iconMarginLeft + this.rcMarginRight;
        rect.top = y - this.rcMarginTop;
        rect.bottom = y + this.itemHeight + this.shadowMarginBottom;
        return rect;
    }

    public float dpToPixel(float paramFloat) {
        return paramFloat * this.scale;
    }

    public int dpToPixel(int paramInt) {
        return (int) ((paramInt * this.scale) + 0.5f);
    }

    public int getMarginLeft() {
        return this.marginLeft;
    }

    public void setSearchLayoutWidth(int width) {
        this.mSearchLayoutWidth = width;
    }

    public int getMarginTop() {
        return this.marginTop;
    }

    public int getPageHeight() {
        return this.pageHeight;
    }

    public int getVerticalGap() {
        return this.gapV;
    }

    public int getItemHeight() {
        return this.itemHeight;
    }

    public float propIconHeight(float dp) {
        return (dp * this.iconHeight) / 50.0f;
    }

    public void setFolderLeft(int left) {
        this.mFolderLeft = left;
    }

    public void setFolderRight(int right) {
        this.mFolderRight = right;
    }

    public int getFolderLeft() {
        return this.mFolderLeft;
    }

    public int getMoreAppLeft() {
        return this.mMoreAppLeft;
    }

    public void setMoreAppLeft(int left) {
        this.mMoreAppLeft = left;
    }

    public int getMoreAppRight() {
        return this.mMoreAppRight;
    }

    public void setMoreAppRight(int right) {
        this.mMoreAppRight = right;
    }

    public int getFolderRight() {
        return this.mFolderRight;
    }

    public void setFolderTop(int top) {
        this.mFolderTop = top;
    }

    public int getFolderTop() {
        return this.mFolderTop;
    }

    public void setFolderWidth(int width) {
        this.mFolderWidth = width;
    }

    public int getFolderWidth() {
        return this.mFolderWidth;
    }

    public void setFolderHeight(int height) {
        this.mFolderHeight = height;
    }

    public int getFolderGapV() {
        int iDip2px = (this.mFolderHeight - DensityUtil.dip2px(this.mContext, 8.0f)) - (this.itemHeight * FOLDER_ROWS);
        if (!AppUtils.isLandSpace(this.mContext)) {
            iDip2px -= (int) this.mContext.getResources().getDimension(R.dimen.dp_520);
        }
        int i = iDip2px / (FOLDER_ROWS + 2);
        this.mFolderGapV = i;
        return i;
    }

    public int getFolderGapH() {
        int i = this.mFolderWidth - (this.iconWidth * FOLDER_COLUMNS);
        int iDip2px = DensityUtil.dip2px(this.mContext, 10.0f);
        int i2 = FOLDER_COLUMNS;
        int i3 = (i + (iDip2px * i2)) / i2;
        this.mFolderGapH = i3;
        return i3;
    }

    public int getFullItemWidthBlackCircle() {
        return this.fullMarginLeftBlackCircle + this.iconWidth + this.fullMarginRight;
    }

    public int getFullItemHeight() {
        return this.cMarginTop + this.itemHeight + this.shadowMarginBottom;
    }

    public int getFullItemWidth() {
        return this.iconMarginLeft + this.iconWidth + this.fullMarginRight;
    }

    public int getHorizontalGap(int index) {
        return (this.width - (this.iconWidth * index)) / (index + 1);
    }

    public int getHorizontalGap() {
        return this.gapH;
    }
}
