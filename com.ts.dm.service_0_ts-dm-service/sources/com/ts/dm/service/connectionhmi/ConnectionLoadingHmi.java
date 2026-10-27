package com.ts.dm.service.connectionhmi;

import android.content.Context;
import android.content.Intent;
import com.ts.dm.service.R;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;
import com.ts.dm.service.view.LoadingDialog;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionLoadingHmi extends Hmi {
    private static final String TAG = "ConnectionLoadingHmi";
    private LoadingDialog mDialog;
    private final Object mLock;

    public ConnectionLoadingHmi(Context context, IHmiFactory factory) {
        super(context, factory);
        this.mLock = new Object();
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void showView(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "showView info: " + infoBean);
        synchronized (this.mLock) {
            if (this.mDialog != null) {
                this.mDialog.dismiss();
                this.mDialog = null;
            }
            LoadingDialog loadingDialog = new LoadingDialog(this.mContext);
            this.mDialog = loadingDialog;
            loadingDialog.setDialogTag(infoBean);
        }
        if (isAndroidAutoRequest(infoBean)) {
            Intent intent = new Intent("com.ts.androidauto.app.SHOW_LOADING");
            intent.putExtra("show", true);
            intent.setPackage("com.ts.androidauto.app");
            this.mContext.sendBroadcast(intent);
            return;
        }
        String content = getCapabilityStr(infoBean.getConnectRequest().getCapabilityType()) + " " + this.mContext.getString(R.string.connecting);
        this.mDialog.setDialogContent(content);
        this.mDialog.show();
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void hideView(ConnectionInfoBean infoBean) {
        synchronized (this.mLock) {
            if (this.mDialog == null) {
                return;
            }
            ConnectionInfoBean oldTag = this.mDialog.getDialogTag();
            boolean ret = closeDialog(infoBean, this.mDialog);
            if (ret) {
                LogUtil.info(TAG, "hideView info: " + infoBean);
                if (oldTag == null) {
                    return;
                } else {
                    this.mFactory.onCheckConnectionResult(oldTag, infoBean);
                }
            }
            Intent intent = new Intent("com.ts.androidauto.app.SHOW_LOADING");
            intent.putExtra("show", false);
            intent.setPackage("com.ts.androidauto.app");
            this.mContext.sendBroadcast(intent);
        }
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void updateView(ConnectionInfoBean infoBean) {
        synchronized (this.mLock) {
            if (this.mDialog != null && infoBean.isSameDevice(this.mDialog.getDialogTag().getConnectRequest().getDevice())) {
                this.mDialog.setDialogTag(infoBean);
                LogUtil.info(TAG, "updateView info: " + infoBean);
            }
        }
    }

    private boolean isAndroidAutoRequest(ConnectionInfoBean infoBean) {
        return infoBean.getConnectRequest().getCapabilityType() == 2;
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public ConnectionInfoBean getTag() {
        synchronized (this.mLock) {
            if (this.mDialog == null) {
                return null;
            }
            return this.mDialog.getDialogTag();
        }
    }
}
