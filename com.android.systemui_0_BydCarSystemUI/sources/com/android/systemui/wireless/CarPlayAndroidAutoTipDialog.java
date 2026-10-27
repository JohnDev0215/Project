package com.android.systemui.wireless;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.widget.CommonDialog;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayAndroidAutoTipDialog extends CommonDialog {

    public static class DismissEvent {
    }

    @Override // com.android.systemui.base.BaseDialog
    protected boolean isFullScreen() {
        return true;
    }

    @Override // com.android.systemui.widget.CommonDialog
    protected void onRefreshView() {
    }

    @Override // com.android.systemui.base.BaseDialog
    protected int setWindowType() {
        return 2001;
    }

    public CarPlayAndroidAutoTipDialog(Context context) {
        super(context);
        setTitle(context.getString(R.string.attention));
    }

    @Override // com.android.systemui.widget.CommonDialog, android.app.Dialog
    public void show() {
        super.show();
        BydLogHelper.logD("show");
        EventBus.getDefault().register(this);
    }

    @Override // com.android.systemui.widget.CommonDialog
    protected void onInitView() {
        ((RelativeLayout) findViewById(R.id.root)).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.wireless.CarPlayAndroidAutoTipDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CarPlayAndroidAutoTipDialog.this.onClickBottomListener != null) {
                    CarPlayAndroidAutoTipDialog.this.onClickBottomListener.onPositiveClick();
                }
            }
        });
    }

    @Override // com.android.systemui.widget.CommonDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        BydLogHelper.logD("dismiss");
        EventBus.getDefault().unregister(this);
    }

    @Override // com.android.systemui.widget.CommonDialog, com.android.systemui.base.BaseDialog
    protected int getLayoutId() {
        if (AppUtils.isRightRudder()) {
            return StyleUtil.getResLayout(R.layout.dialog_carplay_androidauto_tip_right_rudder, "dialog_carplay_androidauto_tip_right_rudder");
        }
        return StyleUtil.getResLayout(R.layout.dialog_carplay_androidauto_tip, "dialog_carplay_androidauto_tip");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDismissEvent(DismissEvent event) {
        BydLogHelper.logD("onStatusBarChange");
        dismiss();
    }
}
