package com.android.systemui.stackdivider.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import com.android.systemui.Constants;
import com.android.systemui.CurrentWindowPositionView;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.view.help.SplitScreenHelpManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: loaded from: classes.dex */
public class IndexWindowManager {
    private static final String TAG = "IndexWindowManager";
    private final float DIM_AMOUNT = 0.0f;
    private final Context mContext;
    private final CurrentWindowPositionView mCurrentWindowPositionView;
    private WindowManager.LayoutParams mSplitButtonParams;
    private final WindowManager mWindowManager;

    public IndexWindowManager(Context context) {
        BydLogHelper.logD(TAG, "new IndexWindowManager");
        WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mWindowManager = windowManager;
        this.mContext = context;
        WindowManager.LayoutParams basicLayoutParams = getBasicLayoutParams();
        this.mSplitButtonParams = basicLayoutParams;
        basicLayoutParams.height = getScreenHight(context) - getStatusBarHight(context);
        this.mSplitButtonParams.width = getMultiwindowIndexViewWidth(context);
        this.mSplitButtonParams.gravity = GravityCompat.END;
        this.mSplitButtonParams.setTitle("IndexWindow");
        CurrentWindowPositionView currentWindowPositionView = new CurrentWindowPositionView(context);
        this.mCurrentWindowPositionView = currentWindowPositionView;
        windowManager.addView(currentWindowPositionView, this.mSplitButtonParams);
    }

    public void remove() {
        updateVisibility(false);
    }

    public void updateVisibility(boolean visibility) {
        CurrentWindowPositionView currentWindowPositionView = this.mCurrentWindowPositionView;
        if (currentWindowPositionView != null) {
            currentWindowPositionView.setVisibility(visibility ? 0 : 4);
            BydLogHelper.logD("updateVisibility visibility = " + visibility);
            if (visibility) {
                sendFocusChangedBroadcast(2);
            } else {
                sendFocusChangedBroadcast(1);
            }
        }
    }

    public void setNewFocusStatus() {
        Divider divider = (Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class);
        if (divider != null) {
            boolean zIsSplitActive = divider.isSplitActive();
            BydLogHelper.logD("setNewFocusStatus isSplitActive = " + zIsSplitActive);
            if (!zIsSplitActive) {
                remove();
                return;
            }
        }
        boolean z = this.mContext.getResources().getConfiguration().orientation == 2;
        int windowingMode = DividerUtils.getInstance().getWindowingMode();
        BydLogHelper.logD(TAG, "setNewFocusStatus land : " + z + " , orientation : " + windowingMode);
        int multiwindowIndexViewWidth = getMultiwindowIndexViewWidth(this.mContext);
        if (windowingMode == 3) {
            if (this.mSplitButtonParams == null) {
                this.mSplitButtonParams = getBasicLayoutParams();
            }
            if (z) {
                this.mSplitButtonParams.height = getScreenHight(this.mContext) - getStatusBarHight(this.mContext);
                this.mSplitButtonParams.width = multiwindowIndexViewWidth;
                this.mSplitButtonParams.gravity = GravityCompat.START;
                this.mCurrentWindowPositionView.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.mipmap.sys_split_index_left, null));
                BydLogHelper.logD(TAG, "setNewFocusStatus primary LAND");
            } else {
                this.mSplitButtonParams.width = multiwindowIndexViewWidth;
                this.mSplitButtonParams.height = (getScreenHight(this.mContext) / 2) - (getMultiwindowTaskDividerSize(this.mContext) / 2);
                this.mSplitButtonParams.gravity = BadgeDrawable.TOP_END;
                this.mCurrentWindowPositionView.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.mipmap.sys_split_index_right, null));
                BydLogHelper.logD(TAG, "setNewFocusStatus primary VERTICAL");
            }
            this.mCurrentWindowPositionView.setLayoutParams(this.mSplitButtonParams);
            this.mWindowManager.updateViewLayout(this.mCurrentWindowPositionView, this.mSplitButtonParams);
            this.mCurrentWindowPositionView.setVisibility(0);
            sendFocusChangedBroadcast(3);
        } else if (windowingMode == 4) {
            if (this.mSplitButtonParams == null) {
                this.mSplitButtonParams = getBasicLayoutParams();
            }
            if (z) {
                this.mSplitButtonParams.height = getScreenHight(this.mContext) - getStatusBarHight(this.mContext);
                this.mSplitButtonParams.width = multiwindowIndexViewWidth;
                this.mSplitButtonParams.gravity = GravityCompat.END;
                this.mCurrentWindowPositionView.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.mipmap.sys_split_index_right, null));
                BydLogHelper.logD(TAG, "setNewFocusStatus secondary LAND");
            } else {
                this.mSplitButtonParams.width = multiwindowIndexViewWidth;
                this.mSplitButtonParams.height = (getScreenHight(this.mContext) / 2) - (getMultiwindowTaskDividerSize(this.mContext) / 2);
                this.mSplitButtonParams.gravity = BadgeDrawable.BOTTOM_END;
                this.mCurrentWindowPositionView.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.mipmap.sys_split_index_right, null));
                BydLogHelper.logD(TAG, "setNewFocusStatus secondary VERTICAL");
            }
            this.mCurrentWindowPositionView.setLayoutParams(this.mSplitButtonParams);
            this.mWindowManager.updateViewLayout(this.mCurrentWindowPositionView, this.mSplitButtonParams);
            this.mCurrentWindowPositionView.setVisibility(0);
            sendFocusChangedBroadcast(4);
        } else {
            BydLogHelper.logD(TAG, "setNewFocusStatus primary is same");
        }
        if (SplitScreenHelpManager.getInstance(this.mContext).isShowHelpActivity()) {
            BydLogHelper.logD(TAG, "setNewFocusStatus isShowHelpActivity!");
            this.mCurrentWindowPositionView.setVisibility(4);
            sendFocusChangedBroadcast(1);
        }
    }

    private WindowManager.LayoutParams getBasicLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2008;
        layoutParams.format = -2;
        layoutParams.flags = 8;
        layoutParams.dimAmount = 0.0f;
        return layoutParams;
    }

    public final int getScreenHight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public final int getStatusBarHight(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.multi_window_task_divider_size);
    }

    public int getMultiwindowTaskDividerSize(Context context) {
        return (int) context.getResources().getDimension(R.dimen.multi_window_task_divider_size);
    }

    public final int getMultiwindowIndexViewWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.multi_window_indexview_width);
    }

    private void sendFocusChangedBroadcast(int value) {
        Divider divider = (Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class);
        if (divider != null) {
            boolean zIsSplitActive = divider.isSplitActive();
            BydLogHelper.logD("sendFocusChangedBroadcast isSplitActive = " + zIsSplitActive);
            if (!zIsSplitActive) {
                return;
            }
        }
        Intent intent = new Intent(Constants.SPLIT_SCREEN_FOCUS_BROADCAST);
        if (value == 3 || value == 4) {
            ActivityManager.StackInfo stackInfo = DividerUtils.getInstance().getStackInfo(value == 3);
            BydLogHelper.logD(" sendFocusChangedBroadcast stackInfo = " + stackInfo);
            if (stackInfo != null) {
                BydLogHelper.logD(" sendFocusChangedBroadcast stackInfo topActivity = " + stackInfo.topActivity);
                if (stackInfo.topActivity != null) {
                    String packageName = stackInfo.topActivity.getPackageName();
                    BydLogHelper.logD(" sendFocusChangedBroadcast pkg = " + packageName);
                    intent.putExtra(Constants.SPLIT_SCREEN_FOCUS_PACKAGE_EXTRA, packageName);
                }
            }
        }
        BydLogHelper.logD("sendFocusChangedBroadcast value = " + value);
        intent.putExtra(Constants.SPLIT_SCREEN_FOCUS_BROADCAST_EXTRA, value);
        this.mContext.sendBroadcast(intent);
    }
}
