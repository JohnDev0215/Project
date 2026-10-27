package com.ts.dm.service.connectionhmi;

import android.content.Context;
import android.view.View;
import com.ts.dm.service.R;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;
import com.ts.dm.service.view.CommonDialog;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionWirelessHmi extends Hmi {
    private static final String TAG = "ConnectionWirelessHmi";
    private CommonDialog mDialog;

    public ConnectionWirelessHmi(Context context, IHmiFactory factory) {
        super(context, factory);
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void showView(final ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "showView info: " + infoBean);
        String capabilityString = getCapabilityStr(infoBean.getConnectRequest().getCapabilityType());
        String content = capabilityString + " " + this.mContext.getString(R.string.promptWirelessAvailable);
        CommonDialog dialog = getDialog(this.mDialog, infoBean, null, content);
        this.mDialog = dialog;
        dialog.setDialogCancel(this.mContext.getString(R.string.no), new View.OnClickListener() { // from class: com.ts.dm.service.connectionhmi.-$$Lambda$ConnectionWirelessHmi$Tie1f-OtNqnTtyFSgGLphPpMGn4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConnectionWirelessHmi.lambda$showView$0(view);
            }
        });
        this.mDialog.setDialogRecommend(capabilityString, new View.OnClickListener() { // from class: com.ts.dm.service.connectionhmi.-$$Lambda$ConnectionWirelessHmi$_AoOKtGflTIZIlrqVmozb_CQ-Es
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showView$1$ConnectionWirelessHmi(infoBean, view);
            }
        });
        this.mDialog.show();
    }

    static /* synthetic */ void lambda$showView$0(View v) {
    }

    public /* synthetic */ void lambda$showView$1$ConnectionWirelessHmi(ConnectionInfoBean infoBean, View v) {
        connection(infoBean);
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void hideView(ConnectionInfoBean infoBean) {
        boolean ret = closeDialog(infoBean, this.mDialog);
        if (ret) {
            LogUtil.info(TAG, "hideView info: " + infoBean);
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
