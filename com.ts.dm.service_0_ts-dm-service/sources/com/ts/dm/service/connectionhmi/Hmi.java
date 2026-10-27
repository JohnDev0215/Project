package com.ts.dm.service.connectionhmi;

import android.content.Context;
import android.content.Intent;
import com.ts.car.core.ServiceProvider;
import com.ts.dm.service.R;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.view.CommonDialog;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.carplay.ICarPlayAppManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class Hmi {
    private static final int MAX_SCREENCOUNT = 10;
    private static final String TAG = "Hmi";
    public Context mContext;
    public IHmiFactory mFactory;
    private IAndroidAutoAppManager mIAndroidAutoAppManager;
    private ICarPlayAppManager mICarPlayAppManager;

    public abstract ConnectionInfoBean getTag();

    public abstract void hideView(ConnectionInfoBean connectionInfoBean);

    public abstract void showView(ConnectionInfoBean connectionInfoBean);

    public abstract void updateView(ConnectionInfoBean connectionInfoBean);

    public Hmi(Context context, IHmiFactory factory) {
        this.mContext = context;
        this.mFactory = factory;
        this.mIAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, context);
        this.mICarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, context);
    }

    public CommonDialog getDialog(CommonDialog commonDialog, ConnectionInfoBean tag, String title, String content) {
        CommonDialog commonDialog2 = new CommonDialog(this.mContext);
        commonDialog2.setDialogTag(tag);
        if (title != null) {
            commonDialog2.setDialogTitle(title);
        }
        if (content != null) {
            commonDialog2.setDialogContent(content);
        }
        return commonDialog2;
    }

    public boolean closeDialog(ConnectionInfoBean infoBean, CommonDialog dialog) {
        if (dialog == null) {
            return false;
        }
        if (!dialog.isShowing()) {
            dialog.setDialogTag(null);
            return false;
        }
        if (!infoBean.isSameDevice(dialog.getDialogTag().getConnectRequest().getDevice())) {
            return false;
        }
        dialog.setDialogTag(null);
        dialog.dismiss();
        return true;
    }

    public String getCapabilityStr(int capability) {
        if (capability == 1) {
            return this.mContext.getString(R.string.carplay);
        }
        if (capability == 2) {
            return this.mContext.getString(R.string.androidauto);
        }
        if (capability == 3) {
            return this.mContext.getString(R.string.carlife);
        }
        if (capability == 4) {
            return this.mContext.getString(R.string.hicar);
        }
        return "";
    }

    public boolean isInterconnectedScreen() {
        return isDisplayAndroidAutoProjection() || isDisplayCarPlayProjection();
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isDisplayAndroidAutoProjection() {
        /*
            r3 = this;
            r0 = 0
            ts.car.service.androidauto.IAndroidAutoAppManager r1 = r3.mIAndroidAutoAppManager     // Catch: java.lang.Exception -> L12
            r2 = 1
            if (r1 == 0) goto Lf
            ts.car.service.androidauto.IAndroidAutoAppManager r1 = r3.mIAndroidAutoAppManager     // Catch: java.lang.Exception -> L12
            int r1 = r1.getVideoFocusStatus()     // Catch: java.lang.Exception -> L12
            if (r1 != r2) goto Lf
            goto L10
        Lf:
            r2 = 0
        L10:
            r0 = r2
            goto L16
        L12:
            r1 = move-exception
            r1.printStackTrace()
        L16:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "isDisplayAndroidAutoProjection:"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "Hmi"
            com.ts.dm.service.util.LogUtil.info(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.dm.service.connectionhmi.Hmi.isDisplayAndroidAutoProjection():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isDisplayCarPlayProjection() {
        /*
            r3 = this;
            r0 = 0
            ts.car.service.carplay.ICarPlayAppManager r1 = r3.mICarPlayAppManager     // Catch: java.lang.Exception -> L12
            if (r1 == 0) goto Lf
            ts.car.service.carplay.ICarPlayAppManager r1 = r3.mICarPlayAppManager     // Catch: java.lang.Exception -> L12
            boolean r1 = r1.getCarPlayDisplayStatus()     // Catch: java.lang.Exception -> L12
            if (r1 == 0) goto Lf
            r1 = 1
            goto L10
        Lf:
            r1 = 0
        L10:
            r0 = r1
            goto L16
        L12:
            r1 = move-exception
            r1.printStackTrace()
        L16:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "isDisplayCarPlayProjection:"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "Hmi"
            com.ts.dm.service.util.LogUtil.info(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.dm.service.connectionhmi.Hmi.isDisplayCarPlayProjection():boolean");
    }

    public void notifyUi(String action, boolean isShowing) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("isShowing", isShowing);
        this.mContext.sendBroadcast(intent);
    }

    public void connection(ConnectionInfoBean infoBean) {
        this.mFactory.onClickHmiForConnect(infoBean);
    }
}
