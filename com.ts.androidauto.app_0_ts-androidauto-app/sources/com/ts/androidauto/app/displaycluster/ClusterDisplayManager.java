package com.ts.androidauto.app.displaycluster;

import android.R;
import android.app.Presentation;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import androidx.core.hardware.display.DisplayManagerCompat;
import com.ts.androidauto.app.common.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ClusterDisplayManager {
    private static final int CLUSTER_VIDEO_COORDINATE_X = 558;
    private static final int CLUSTER_VIDEO_COORDINATE_X_UKE = 0;
    private static final int CLUSTER_VIDEO_COORDINATE_Y = 172;
    private static final int CLUSTER_VIDEO_COORDINATE_Y_UKE = 0;
    private static final int CLUSTER_VIDEO_HEIGHT = 480;
    private static final int CLUSTER_VIDEO_HEIGHT_UKE = 720;
    private static final int CLUSTER_VIDEO_WIDTH = 800;
    private static final int CLUSTER_VIDEO_WIDTH_UKE = 1920;
    private static final String TAG = "ClusterDisplayManager";
    private static final String VEHICLE_MODEL_TANG = "BYD 5.0F_01";
    private static final String VEHICLE_MODEL_UKE = "BYD 5.0F_02";
    private Context mContext;
    private String mCurrentVehicleModel;
    private DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: com.ts.androidauto.app.displaycluster.ClusterDisplayManager.1
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialog) {
            ClusterDisplayManager.this.mPresentation = null;
            ClusterDisplayManager.this.mShowing = false;
            LogUtil.debug(ClusterDisplayManager.TAG, "Display gone!");
        }
    };
    private Presentation mPresentation;
    private View mRootView;
    private boolean mShowing;

    public ClusterDisplayManager(Context context, View contentView, String vehicleModel) {
        this.mContext = context;
        this.mRootView = contentView;
        this.mCurrentVehicleModel = vehicleModel;
    }

    public void show() {
        if (this.mShowing) {
            return;
        }
        LogUtil.debug(TAG, "show");
        updateDisplays(true);
        this.mShowing = true;
    }

    public void hide() {
        if (!this.mShowing) {
            return;
        }
        LogUtil.debug(TAG, "hide");
        updateDisplays(false);
        this.mShowing = false;
    }

    private Display getSecondDisplay() {
        DisplayManager dm = (DisplayManager) this.mContext.getSystemService("display");
        Display[] displays = dm.getDisplays(DisplayManagerCompat.DISPLAY_CATEGORY_PRESENTATION);
        if (displays != null) {
            return displays[0];
        }
        return null;
    }

    private void updateDisplays(boolean showing) {
        Presentation originalPresentation = this.mPresentation;
        if (showing) {
            Display presentationDisplay = getSecondDisplay();
            if (presentationDisplay == null) {
                LogUtil.debug(TAG, "presentationDisplay SecondDisplay == null");
            } else {
                showPresentation(presentationDisplay);
            }
        } else {
            Presentation presentation = this.mPresentation;
            if (presentation == null) {
                LogUtil.debug(TAG, "mPresentation == null ");
            } else {
                presentation.dismiss();
                LogUtil.debug(TAG, "mPresentation dismiss ");
            }
        }
        if (this.mPresentation != originalPresentation) {
            LogUtil.debug(TAG, "Display changed!");
        }
    }

    private void showPresentation(Display presentationDisplay) {
        Presentation presentation = this.mPresentation;
        if (presentation != null && presentation.getDisplay() != presentationDisplay) {
            LogUtil.debug(TAG, "Display gone: " + this.mPresentation.getDisplay());
            this.mPresentation.dismiss();
            this.mPresentation = null;
        }
        if (this.mPresentation == null && presentationDisplay != null) {
            LogUtil.debug(TAG, "Cluster enabled on display: " + presentationDisplay);
            ClusterPresentation clusterPresentation = new ClusterPresentation(this.mContext, presentationDisplay, this.mRootView, this.mCurrentVehicleModel);
            this.mPresentation = clusterPresentation;
            clusterPresentation.setOnDismissListener(this.mOnDismissListener);
            try {
                this.mPresentation.show();
            } catch (WindowManager.InvalidDisplayException e) {
                LogUtil.debug(TAG, "Invalid display: WindowManager InvalidDisplayException");
                this.mPresentation = null;
            }
        }
    }

    private static final class ClusterPresentation extends Presentation {
        private View mContentView;
        private String mCurrentVehicleModel;

        public ClusterPresentation(Context outerContext, Display display, View contentView, String currentVehicleModel) {
            super(outerContext, display, R.style.Theme.Translucent.NoTitleBar.Fullscreen);
            this.mContentView = contentView;
            this.mCurrentVehicleModel = currentVehicleModel;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
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
            LogUtil.debug(ClusterDisplayManager.TAG, "onCreate mCurrentVehicleModel = " + this.mCurrentVehicleModel);
            lp.gravity = 8388659;
            String str = this.mCurrentVehicleModel;
            byte b = -1;
            switch (str.hashCode()) {
                case -205498498:
                    if (str.equals(ClusterDisplayManager.VEHICLE_MODEL_TANG)) {
                        b = 1;
                    }
                    break;
                case -205498497:
                    if (str.equals(ClusterDisplayManager.VEHICLE_MODEL_UKE)) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                lp.x = 0;
                lp.y = 0;
                lp.width = 1920;
                lp.height = 720;
            } else {
                lp.x = 558;
                lp.y = 172;
                lp.width = 800;
                lp.height = 480;
            }
            lp.type = 2010;
            lp.flags = 8;
            getWindow().setAttributes(lp);
        }

        @Override // android.app.Presentation, android.app.Dialog
        public void show() {
            super.show();
        }

        @Override // android.app.Dialog
        public void hide() {
            super.hide();
            LogUtil.debug(ClusterDisplayManager.TAG, "hide");
        }

        @Override // android.app.Dialog, android.content.DialogInterface
        public void dismiss() {
            super.dismiss();
            LogUtil.debug(ClusterDisplayManager.TAG, "dismiss");
        }

        @Override // android.app.Presentation, android.app.Dialog
        protected void onStop() {
            super.onStop();
            LogUtil.debug(ClusterDisplayManager.TAG, "onStop");
        }

        @Override // android.app.Presentation
        public void onDisplayChanged() {
            super.onDisplayChanged();
            LogUtil.debug(ClusterDisplayManager.TAG, "onDisplayChanged");
        }

        @Override // android.app.Presentation
        public void onDisplayRemoved() {
            super.onDisplayRemoved();
            LogUtil.debug(ClusterDisplayManager.TAG, "onDisplayRemoved");
        }
    }
}
