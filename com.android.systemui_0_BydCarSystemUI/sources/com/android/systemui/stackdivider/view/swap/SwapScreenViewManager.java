package com.android.systemui.stackdivider.view.swap;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;
import com.android.systemui.stackdivider.Divider;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SwapScreenViewManager {
    private static volatile SwapScreenViewManager mSwapScreenViewManager;
    private final Divider mDivider;
    private SwapScreenView mSwapScreenView;
    private WindowManager mWindowManager;

    private SwapScreenViewManager(Context context, Divider divider) {
        this.mWindowManager = getWindowManager(context);
        this.mDivider = divider;
    }

    public static SwapScreenViewManager getInstance(Context context, Divider divider) {
        if (mSwapScreenViewManager == null) {
            synchronized (SwapScreenViewManager.class) {
                if (mSwapScreenViewManager == null) {
                    mSwapScreenViewManager = new SwapScreenViewManager(context, divider);
                }
            }
        }
        return mSwapScreenViewManager;
    }

    private WindowManager getWindowManager(Context context) {
        if (this.mWindowManager == null) {
            this.mWindowManager = (WindowManager) context.getSystemService("window");
        }
        return this.mWindowManager;
    }

    public void createSwapView(Context context, Bitmap primaryLayerTwoBitmap, Bitmap secondLayerTwoBitmap) {
        if (this.mSwapScreenView == null) {
            SwapScreenView swapScreenView = new SwapScreenView(context, this.mDivider);
            this.mSwapScreenView = swapScreenView;
            swapScreenView.changeImageView(primaryLayerTwoBitmap, secondLayerTwoBitmap);
            if (this.mWindowManager == null) {
                this.mWindowManager = getWindowManager(context);
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.type = 2010;
            layoutParams.format = -2;
            layoutParams.flags = 262144;
            layoutParams.setTitle("SwapScreenView");
            this.mWindowManager.addView(this.mSwapScreenView, layoutParams);
            this.mSwapScreenView.addSwapAnimation();
        }
    }

    public void removeSwapView() {
        try {
            SwapScreenView swapScreenView = this.mSwapScreenView;
            if (swapScreenView != null) {
                this.mWindowManager.removeView(swapScreenView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.mSwapScreenView = null;
            throw th;
        }
        this.mSwapScreenView = null;
    }

    public void refreshView(Context context) {
        SwapScreenView swapScreenView = this.mSwapScreenView;
        if (swapScreenView != null) {
            swapScreenView.initView();
        }
    }

    public boolean isSwapping() {
        SwapScreenView swapScreenView = this.mSwapScreenView;
        if (swapScreenView == null) {
            BydLogHelper.logD("mSwapScreenView == null isSwapping return false");
            return false;
        }
        return swapScreenView.isSwapping();
    }
}
