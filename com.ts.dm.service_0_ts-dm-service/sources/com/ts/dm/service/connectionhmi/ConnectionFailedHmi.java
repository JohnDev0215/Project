package com.ts.dm.service.connectionhmi;

import android.content.Context;
import android.view.View;
import com.ts.dm.service.R;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;
import com.ts.dm.service.view.CommonDialog;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionFailedHmi extends Hmi {
    private static final String TAG = "ConnectionFailedHmi";
    private CommonDialog mDialog;
    private final Object mLock;

    public ConnectionFailedHmi(Context context, IHmiFactory factory) {
        super(context, factory);
        this.mLock = new Object();
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void showView(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "showView info: " + infoBean);
        String connectionCapability = getCapabilityStr(infoBean.getConnectRequest().getCapabilityType());
        String content = connectionCapability + " " + this.mContext.getString(R.string.connectionFailed);
        synchronized (this.mLock) {
            if (this.mDialog != null) {
                this.mDialog.dismiss();
                this.mDialog = null;
            }
            CommonDialog dialog = getDialog(this.mDialog, infoBean, connectionCapability, content);
            this.mDialog = dialog;
            dialog.setDialogRecommend(this.mContext.getString(R.string.ok), new View.OnClickListener() { // from class: com.ts.dm.service.connectionhmi.-$$Lambda$ConnectionFailedHmi$IZG3DNyISvOe30PkpDliCagxgqs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showView$0$ConnectionFailedHmi(view);
                }
            });
            this.mDialog.show();
        }
    }

    public /* synthetic */ void lambda$showView$0$ConnectionFailedHmi(View v) {
        this.mDialog.dismiss();
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void hideView(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "hideView info: " + infoBean);
        synchronized (this.mLock) {
            if (this.mDialog != null) {
                this.mDialog.dismiss();
            }
        }
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void updateView(ConnectionInfoBean infoBean) {
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public ConnectionInfoBean getTag() {
        CommonDialog commonDialog = this.mDialog;
        if (commonDialog == null) {
            return null;
        }
        return commonDialog.getDialogTag();
    }
}
