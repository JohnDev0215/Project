package com.ts.dm.service.connectionhmi;

import android.content.Context;
import android.view.View;
import com.ts.dm.service.R;
import com.ts.dm.service.data.ConnectionInfoBean;
import com.ts.dm.service.util.LogUtil;
import com.ts.dm.service.view.CommonDialog;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionCarPlaySwitchClosedHmi extends Hmi {
    private static final String TAG = "ConnectionCarPlaySwitchClosedHmi";
    private CommonDialog mDialog;

    public ConnectionCarPlaySwitchClosedHmi(Context context, IHmiFactory factory) {
        super(context, factory);
    }

    @Override // com.ts.dm.service.connectionhmi.Hmi
    public void showView(ConnectionInfoBean infoBean) {
        LogUtil.info(TAG, "showView info: " + infoBean);
        String content = this.mContext.getString(R.string.promptNotOpenCarPlay);
        CommonDialog dialog = getDialog(this.mDialog, infoBean, null, content);
        this.mDialog = dialog;
        dialog.setDialogRecommend(this.mContext.getString(R.string.ok), new View.OnClickListener() { // from class: com.ts.dm.service.connectionhmi.-$$Lambda$ConnectionCarPlaySwitchClosedHmi$BScyj4xvyEnfWhNfyr2NnrM3Boc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ConnectionCarPlaySwitchClosedHmi.lambda$showView$0(view);
            }
        });
        this.mDialog.show();
    }

    static /* synthetic */ void lambda$showView$0(View v) {
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
