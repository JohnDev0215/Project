package com.ts.dm.service.connectionhmi;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.ts.dm.service.R;
import com.ts.dm.service.config.DeviceManagerConfig;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;
import com.ts.dm.service.view.CommonDialog;
import com.ts.dm.service.view.TopDialog;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionSwitchHmi extends Hmi {
    private static final String TAG = "ConnectionSwitchHmi";
    private CommonDialog mDialog;
    private boolean mShouldBackProjection;
    private TopDialog mTopDialog;

    public ConnectionSwitchHmi(Context context, IHmiFactory factory) {
        super(context, factory);
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void showView(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "showView info: " + infoBean);
        if (isInterconnectedScreen()) {
            showTopDialog(infoBean);
        } else {
            showDialog(infoBean);
        }
    }

    private void showDialog(final ConnectionInfoBean infoBean) {
        String attachInterconnetedContent = getCapabilityStr(infoBean.getConnectRequest().getCapabilityType());
        String content = this.mContext.getString(R.string.aNew) + " " + attachInterconnetedContent + " " + this.mContext.getString(R.string.promptDetectedDevice) + "?";
        CommonDialog dialog = getDialog(this.mDialog, infoBean, null, content);
        this.mDialog = dialog;
        dialog.setDialogCancel(this.mContext.getString(R.string.no), new View.OnClickListener() { // from class: com.ts.dm.service.connectionhmi.-$$Lambda$ConnectionSwitchHmi$nX9vJ3AKGUkx6GuqmgTErRYfIjI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showDialog$0$ConnectionSwitchHmi(view);
            }
        });
        this.mDialog.setDialogRecommend(this.mContext.getString(R.string.yes), new View.OnClickListener() { // from class: com.ts.dm.service.connectionhmi.-$$Lambda$ConnectionSwitchHmi$cLwhYao1UrMU7DBSon8zCxaO62E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showDialog$1$ConnectionSwitchHmi(infoBean, view);
            }
        });
        this.mDialog.show();
    }

    public /* synthetic */ void lambda$showDialog$0$ConnectionSwitchHmi(View v) {
        if (this.mShouldBackProjection) {
            this.mShouldBackProjection = false;
            backProjectionScreen();
        }
    }

    public /* synthetic */ void lambda$showDialog$1$ConnectionSwitchHmi(ConnectionInfoBean infoBean, View v) {
        connection(infoBean);
    }

    private void showTopDialog(final ConnectionInfoBean infoBean) {
        String toastContent = this.mContext.getString(R.string.promptNewDevice) + ".";
        TopDialog topDialog = new TopDialog(this.mContext);
        this.mTopDialog = topDialog;
        topDialog.setDialogTag(infoBean);
        this.mTopDialog.setDialogContent(toastContent);
        this.mTopDialog.setDialogRecommend("", new View.OnClickListener() { // from class: com.ts.dm.service.connectionhmi.-$$Lambda$ConnectionSwitchHmi$LA1OXNOx1Xw8z0JH63Nk6FcwpIg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showTopDialog$2$ConnectionSwitchHmi(infoBean, view);
            }
        });
        this.mTopDialog.setDialogListener(new CommonDialog.IDialogListener() { // from class: com.ts.dm.service.connectionhmi.ConnectionSwitchHmi.1
            @Override // com.ts.dm.service.view.CommonDialog.IDialogListener
            public void onShowChanged(boolean isShowing) {
                ConnectionSwitchHmi.this.notifyUi(DeviceManagerConfig.ActionConfig.SWITCHHMI, isShowing);
            }
        });
        this.mTopDialog.show();
    }

    public /* synthetic */ void lambda$showTopDialog$2$ConnectionSwitchHmi(ConnectionInfoBean infoBean, View v) {
        showDialog(infoBean);
        if (isInterconnectedScreen()) {
            this.mShouldBackProjection = true;
            exitProjectionScreen();
        }
    }

    private void exitProjectionScreen() {
        LogUtil.info(TAG, "exitProjectionScreen");
        Intent androidAutoIntent = new Intent(DeviceManagerConfig.EXIT_PROJECTION_SCREEN_ACTION);
        androidAutoIntent.setPackage("com.ts.androidauto.app");
        this.mContext.sendBroadcast(androidAutoIntent);
        Intent carPlayIntent = new Intent(DeviceManagerConfig.EXIT_PROJECTION_SCREEN_ACTION);
        carPlayIntent.setPackage("com.ts.carplay.app");
        this.mContext.sendBroadcast(carPlayIntent);
    }

    private void backProjectionScreen() {
        LogUtil.info(TAG, "backProjectionScreen");
        Intent androidAutoIntent = new Intent(DeviceManagerConfig.BACK_PROJECTION_SCREEN_ACTION);
        androidAutoIntent.setPackage("com.ts.androidauto.app");
        this.mContext.sendBroadcast(androidAutoIntent);
        Intent carPlayIntent = new Intent(DeviceManagerConfig.BACK_PROJECTION_SCREEN_ACTION);
        carPlayIntent.setPackage("com.ts.carplay.app");
        this.mContext.sendBroadcast(carPlayIntent);
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void hideView(ConnectionInfoBean infoBean) {
        boolean ret = closeDialog(infoBean, this.mDialog);
        if (ret) {
            LogUtil.info(TAG, "hideView info: " + infoBean);
        }
        boolean ret2 = closeDialog(infoBean, this.mTopDialog);
        if (ret2) {
            LogUtil.info(TAG, "hideTopView info: " + infoBean);
        }
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void updateView(ConnectionInfoBean infoBean) {
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public ConnectionInfoBean getTag() {
        CommonDialog commonDialog = this.mDialog;
        if (commonDialog != null) {
            return commonDialog.getDialogTag();
        }
        TopDialog topDialog = this.mTopDialog;
        if (topDialog != null) {
            return topDialog.getDialogTag();
        }
        return null;
    }
}
