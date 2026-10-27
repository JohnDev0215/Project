package com.byd.card.widget;

import android.app.UiStyleConfig;
import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.android.systemui.R;
import com.byd.card.drag.AppContentView;
import com.byd.card.drag.LayoutCalculator;
import com.byd.card.utils.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public class IconPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private int iconSize;
    private int itemHeight;
    private IconPopClickListener listener;
    private int mAppContentHeight;
    private LinearLayout mBg;
    private RelativeLayout mDelete;
    private RelativeLayout mInfo;
    private int mSmallGap;
    private LinearLayout mView;
    private int margin;

    public interface IconPopClickListener {
        void onPopupItemClick(View view);
    }

    private boolean isSecondRow(int index) {
        return index == 4 || index == 5 || index == 6 || index == 7;
    }

    private boolean landIsSecondRow(int index) {
        switch (index) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IconPopClickListener iconPopClickListener = this.listener;
        if (iconPopClickListener != null) {
            iconPopClickListener.onPopupItemClick(view);
        }
    }

    public void setIconPopClickListener(IconPopClickListener listener) {
        this.listener = listener;
    }

    public IconPopWindow(Context context) {
        this.context = context;
        if (AppUtils.isLandSpace(context)) {
            this.mAppContentHeight = context.getResources().getDimensionPixelSize(R.dimen.dp_403);
        } else {
            this.mAppContentHeight = context.getResources().getDimensionPixelSize(R.dimen.dp_624);
        }
        this.mSmallGap = context.getResources().getDimensionPixelSize(R.dimen.dp_3);
        this.itemHeight = context.getResources().getDimensionPixelSize(R.dimen.dp_85);
        this.iconSize = context.getResources().getDimensionPixelSize(R.dimen.dp_72);
        this.margin = context.getResources().getDimensionPixelSize(R.dimen.dp_52);
        setWidth(-2);
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(false);
        setClippingEnabled(false);
        setBackgroundDrawable(null);
        int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
        if (uiStyle == 1) {
            this.mView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.denza_icon_popupwindow, (ViewGroup) null);
        } else if (uiStyle != 2) {
            this.mView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.icon_popupwindow, (ViewGroup) null);
        }
        this.mBg = (LinearLayout) this.mView.findViewById(R.id.popup_bg);
        this.mDelete = (RelativeLayout) this.mView.findViewById(R.id.delete_app);
        this.mInfo = (RelativeLayout) this.mView.findViewById(R.id.app_info);
        setContentView(this.mView);
        this.mDelete.setOnClickListener(this);
        this.mInfo.setOnClickListener(this);
    }

    public void show(View parent, View scrollView, boolean showDelete, int top, AppContentView appContentView) {
        int height;
        int i;
        int height2;
        int dimensionPixelSize;
        int viewWidht;
        adjustWidth(showDelete);
        int selectedIndex = appContentView.getSelectedIndex();
        if (AppUtils.isRtl(this.context)) {
            selectedIndex = switchRtlIndex(selectedIndex);
        }
        Point iconLocation = appContentView.getIconLocation(selectedIndex);
        int i2 = iconLocation.y;
        int i3 = iconLocation.x;
        int uiStyle = UiStyleConfig.getInstance(this.context).getUiStyle();
        if (top == 1) {
            if (uiStyle == 1) {
                this.mBg.setBackgroundResource(R.mipmap.denza_appinfo_popup_down);
            } else if (uiStyle != 2) {
                this.mBg.setBackgroundResource(R.mipmap.icon_popup_down);
            }
            height2 = (i2 - parent.getHeight()) + this.iconSize;
        } else {
            if (uiStyle == 1) {
                this.mBg.setBackgroundResource(R.mipmap.denza_appinfo_popup_up);
            } else if (uiStyle != 2) {
                this.mBg.setBackgroundResource(R.mipmap.icon_popup_up);
            }
            if (parent.getHeight() > this.mAppContentHeight) {
                height = i2 - this.iconSize;
                i = this.mSmallGap;
            } else {
                height = (i2 - parent.getHeight()) - this.iconSize;
                i = this.mSmallGap;
            }
            height2 = (height - i) - 21;
        }
        if (showDelete) {
            dimensionPixelSize = (i3 - (this.iconSize / 2)) + this.context.getResources().getDimensionPixelSize(R.dimen.dp_5);
            this.mDelete.setVisibility(0);
            viewWidht = (getViewWidht() - this.context.getResources().getDimensionPixelSize(R.dimen.dp_148)) / 2;
        } else {
            this.mDelete.setVisibility(8);
            dimensionPixelSize = i3 + this.context.getResources().getDimensionPixelSize(R.dimen.dp_2);
            viewWidht = (getViewWidht() - this.context.getResources().getDimensionPixelSize(R.dimen.dp_82)) / 2;
        }
        int dimensionPixelSize2 = dimensionPixelSize - viewWidht;
        if (AppUtils.isLandSpace(this.context)) {
            if (AppUtils.isRtl(this.context)) {
                dimensionPixelSize2 -= this.context.getResources().getDimensionPixelSize(R.dimen.dp_16);
            }
            if (scrollView.getScrollY() == 38 && landIsSecondRow(selectedIndex)) {
                showAsDropDown(parent, dimensionPixelSize2, -this.context.getResources().getDimensionPixelSize(R.dimen.dp_375), 48);
                return;
            } else {
                showAsDropDown(parent, dimensionPixelSize2, height2, 48);
                return;
            }
        }
        if (scrollView.getScrollY() > 60 && isSecondRow(selectedIndex) && appContentView.getHeight() < 1200) {
            showAsDropDown(parent, dimensionPixelSize2, (-this.context.getResources().getDimensionPixelSize(R.dimen.dp_485)) - scrollView.getScrollY(), 80);
        } else {
            showAsDropDown(parent, dimensionPixelSize2, height2, 48);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:46:0x013a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void folderShow(android.view.View r5, boolean r6, int r7, com.byd.card.drag.IPageView r8) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.widget.IconPopWindow.folderShow(android.view.View, boolean, int, com.byd.card.drag.IPageView):void");
    }

    private int getViewWidht() {
        this.mView.measure(0, 0);
        return this.mView.getMeasuredWidth();
    }

    private void adjustWidth(boolean isShowDelete) {
        int viewWidth = getViewWidth(this.mInfo);
        int viewWidth2 = getViewWidth(this.mDelete);
        if (isShowDelete) {
            int iMin = Math.min(Math.max(viewWidth, viewWidth2), this.context.getResources().getDimensionPixelSize(R.dimen.dp_96));
            setViewWidth(this.mInfo, iMin);
            setViewWidth(this.mDelete, iMin);
            return;
        }
        setViewWidth(this.mInfo, -2);
    }

    private int getViewWidth(View v) {
        if (v == null) {
            return 0;
        }
        v.measure(0, 0);
        return v.getMeasuredWidth();
    }

    private void setViewWidth(View view, int width) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = width;
            view.setLayoutParams(layoutParams);
        }
    }

    public int switchFolderRtlIndex(int index) {
        int i = index % LayoutCalculator.FOLDER_ROWS;
        if (i == 0) {
            return ((index / LayoutCalculator.FOLDER_ROWS) * LayoutCalculator.FOLDER_ROWS) + 2;
        }
        if (i == 1) {
            return ((index / LayoutCalculator.FOLDER_ROWS) * LayoutCalculator.FOLDER_ROWS) + 1;
        }
        if (i != 2) {
            return 0;
        }
        return (index / LayoutCalculator.FOLDER_ROWS) * LayoutCalculator.FOLDER_ROWS;
    }

    private int switchRtlIndex(int index) {
        int i;
        if (AppUtils.isLandSpace(this.context)) {
            int i2 = index % LayoutCalculator.APP_COLUMNS_LAND;
            if (i2 == 0) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND) + 4;
            } else if (i2 == 1) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND) + 3;
            } else if (i2 == 2) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND) + 2;
            } else if (i2 == 3) {
                i = ((index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND) + 1;
            } else {
                if (i2 != 4) {
                    return 0;
                }
                i = (index / LayoutCalculator.APP_COLUMNS_LAND) * LayoutCalculator.APP_COLUMNS_LAND;
            }
            return i;
        }
        int i3 = index % LayoutCalculator.APP_COLUMNS_PORT;
        if (i3 == 0) {
            return ((index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT) + 3;
        }
        if (i3 == 1) {
            return ((index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT) + 2;
        }
        if (i3 == 2) {
            return ((index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT) + 1;
        }
        if (i3 != 3) {
            return 0;
        }
        return (index / LayoutCalculator.APP_COLUMNS_PORT) * LayoutCalculator.APP_COLUMNS_PORT;
    }
}
