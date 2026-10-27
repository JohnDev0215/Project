package com.ts.carplay.app.service.proxy.navigation;

import android.R;
import android.app.Presentation;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.ts.carplay.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ClusterDisplayManager {
    private static final int CLUSTER_HEIGHT_PIXELS = 720;
    private static final int CLUSTER_WIDTH_PIXELS = 1920;
    private static final int INIT_DELAY = 1000;
    private static final int INIT_DELAY_DEFAULT = 0;
    private static final String TAG = "ClusterDisplayManager";
    private Context mContext;
    private Handler mHandler;
    private boolean mIsInit;
    private Presentation mPresentation;
    private View mRootView;
    private boolean mShowing;
    private long mInitDelay = 1000;
    private DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: com.ts.carplay.app.service.proxy.navigation.ClusterDisplayManager.1
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialog) {
            ClusterDisplayManager.this.mPresentation = null;
            ClusterDisplayManager.this.mShowing = false;
            LogUtil.info(ClusterDisplayManager.TAG, "Display gone!");
        }
    };

    public ClusterDisplayManager(Context context, View contentView, Handler handler) {
        this.mHandler = null;
        this.mContext = context;
        this.mRootView = contentView;
        this.mHandler = handler;
    }

    public void init() {
        if (this.mIsInit) {
            LogUtil.info(TAG, "do not init twice");
            return;
        }
        Display presentationDisplay = getSecondDisplay();
        if (presentationDisplay != null) {
            showPresentation(presentationDisplay);
        } else {
            LogUtil.info(TAG, "presentationDisplay SecondDisplay == null");
        }
        updateDisplays(false);
        this.mIsInit = true;
        this.mInitDelay = 0L;
        this.mShowing = false;
    }

    public void show() {
        LogUtil.info(TAG, "show mShowing:" + this.mShowing);
        if (this.mShowing) {
            return;
        }
        updateDisplays(true);
        this.mShowing = true;
    }

    public void hide() {
        LogUtil.info(TAG, "hide mShowing:" + this.mShowing);
        if (!this.mShowing) {
            return;
        }
        updateDisplays(false);
        this.mShowing = false;
    }

    public void destroy() {
        LogUtil.info(TAG, "destroy mShowing:" + this.mShowing);
        Presentation presentation = this.mPresentation;
        if (presentation != null) {
            presentation.dismiss();
            LogUtil.info(TAG, "mPresentation dismiss ");
        } else {
            LogUtil.info(TAG, "mPresentation == null ");
        }
        this.mIsInit = false;
        this.mShowing = false;
    }

    private Display getSecondDisplay() {
        DisplayManager dm = (DisplayManager) this.mContext.getSystemService("display");
        Display[] displays = dm.getDisplays("android.hardware.display.category.PRESENTATION");
        if (displays != null) {
            return displays[0];
        }
        return null;
    }

    public long getInitDelay() {
        return this.mInitDelay;
    }

    private void updateDisplays(boolean showing) {
        Presentation originalPresentation = this.mPresentation;
        if (this.mPresentation == null) {
            LogUtil.info(TAG, "mPresentation is null, need init");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.ClusterDisplayManager.2
                @Override // java.lang.Runnable
                public void run() {
                    ClusterDisplayManager.this.init();
                    LogUtil.info(ClusterDisplayManager.TAG, "init delay 1 seconds");
                }
            }, this.mInitDelay);
        }
        LogUtil.info(TAG, "updateDisplays showing:" + showing);
        if (showing) {
            LogUtil.info(TAG, "second show,change alpha");
            this.mRootView.setAlpha(1.0f);
            setDisplayParams(1920, 720);
        } else {
            View view = this.mRootView;
            if (view != null) {
                view.setAlpha(0.0f);
                LogUtil.info(TAG, "mRootView setAlpha 0");
                setDisplayParams(1, 1);
            } else {
                LogUtil.info(TAG, "mRootView == null ");
            }
        }
        if (this.mPresentation != originalPresentation) {
            LogUtil.info(TAG, "Display changed!");
        }
    }

    private void showPresentation(Display presentationDisplay) {
        Presentation presentation = this.mPresentation;
        if (presentation != null && presentation.getDisplay() != presentationDisplay) {
            LogUtil.info(TAG, "Display gone: " + this.mPresentation.getDisplay());
            this.mPresentation.dismiss();
            this.mPresentation = null;
        }
        if (this.mPresentation == null && presentationDisplay != null) {
            LogUtil.info(TAG, "Cluster enabled on display: " + presentationDisplay);
            ClusterPresentation clusterPresentation = new ClusterPresentation(this.mContext, presentationDisplay, this.mRootView);
            this.mPresentation = clusterPresentation;
            clusterPresentation.setOnDismissListener(this.mOnDismissListener);
            try {
                this.mPresentation.show();
            } catch (WindowManager.InvalidDisplayException e) {
                LogUtil.info(TAG, "Invalid display: WindowManager InvalidDisplayException");
                this.mPresentation = null;
            }
        }
    }

    private static final class ClusterPresentation extends Presentation {
        private View mContentView;

        public ClusterPresentation(Context outerContext, Display display, View contentView) {
            super(outerContext, display, R.style.Theme.Translucent.NoTitleBar.Fullscreen);
            this.mContentView = contentView;
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ViewParent viewParent = this.mContentView.getParent();
            if (viewParent != null) {
                ((ViewGroup) viewParent).removeView(this.mContentView);
            }
            setContentView(this.mContentView);
            setCancelable(false);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.gravity = 8388659;
            lp.x = 628;
            lp.y = 226;
            lp.width = 1920;
            lp.height = 720;
            lp.type = 2010;
            lp.flags = 8;
            getWindow().setAttributes(lp);
            getWindow().setBackgroundDrawable(new ColorDrawable());
        }

        @Override // android.app.Presentation, android.app.Dialog
        public void show() {
            super.show();
        }

        @Override // android.app.Dialog
        public void hide() {
            super.hide();
            LogUtil.info(ClusterDisplayManager.TAG, "hide");
        }

        @Override // android.app.Dialog, android.content.DialogInterface
        public void dismiss() {
            super.dismiss();
            LogUtil.info(ClusterDisplayManager.TAG, "dismiss");
        }

        @Override // android.app.Presentation, android.app.Dialog
        protected void onStop() {
            super.onStop();
            LogUtil.info(ClusterDisplayManager.TAG, "onStop");
        }

        @Override // android.app.Presentation
        public void onDisplayChanged() {
            super.onDisplayChanged();
            LogUtil.info(ClusterDisplayManager.TAG, "onDisplayChanged");
        }

        @Override // android.app.Presentation
        public void onDisplayRemoved() {
            super.onDisplayRemoved();
            LogUtil.info(ClusterDisplayManager.TAG, "onDisplayRemoved");
        }
    }

    private void setDisplayParams(int width, int height) {
        LogUtil.info(TAG, "setDisplayParams enter.");
        View view = this.mRootView;
        if (view == null) {
            LogUtil.info(TAG, "mRootView == null ");
            return;
        }
        FrameLayout.LayoutParams displayParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (displayParams == null) {
            LogUtil.info(TAG, "displayParams == null ");
            return;
        }
        displayParams.width = width;
        displayParams.height = height;
        displayParams.setMargins(0, 0, 0, 0);
        this.mRootView.setLayoutParams(displayParams);
    }
}
