package com.android.systemui.util;

import android.content.Context;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.widget.CommonDialog;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class DialogUtils {
    public static CommonDialog mEspOffDialog;
    public static CommonDialog mLDADialog;

    public static void showEspOffDialog(Context context, String title, String message) {
        if (mEspOffDialog == null) {
            mEspOffDialog = new CommonDialog(context);
        }
        BydLogHelper.logD("isShowing  : " + mEspOffDialog.isShowing());
        if (mEspOffDialog.isShowing()) {
            return;
        }
        mEspOffDialog.setMessage(message).setTitle(title).setBottomStyle(2).setBtnLeft(context.getResources().getString(R.string.cancel)).setBtnRight(context.getResources().getString(R.string.confirm)).setBtnRightBgColor(true).setBtnRightTextColor(context.getColor(R.color.white)).setDoubleButtonStyle(1).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.util.DialogUtils.1
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                DialogUtils.mEspOffDialog.dismiss();
                BydLogHelper.logD("onPositiveClick setElecCarBodyStablySysState SET_OFF : ");
                ((CarController) Dependency.get(CarController.class)).setElecCarBodyStablySysState(1);
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                DialogUtils.mEspOffDialog.dismiss();
            }
        }).show();
    }

    public static void showLDADialog(Context context, String title, String message) {
        if (mLDADialog == null) {
            mLDADialog = new CommonDialog(context);
        }
        BydLogHelper.logD("isShowing  : " + mLDADialog.isShowing());
        if (mLDADialog.isShowing()) {
            return;
        }
        mLDADialog.setMessage(message).setTitle(title).setBottomStyle(2).setBtnLeft(context.getResources().getString(R.string.cancel)).setBtnRight(context.getResources().getString(R.string.confirm)).setBtnRightBgColor(true).setBtnRightTextColor(context.getColor(R.color.white)).setDoubleButtonStyle(1).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.util.DialogUtils.2
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                DialogUtils.mLDADialog.dismiss();
                int supportEmergencyLaneKeepAssist = ((CarController) Dependency.get(CarController.class)).getSupportEmergencyLaneKeepAssist();
                BydLogHelper.logD("onPositiveClick supportEmergencyLaneKeepAssist : " + supportEmergencyLaneKeepAssist);
                if (supportEmergencyLaneKeepAssist == 0) {
                    ((CarController) Dependency.get(CarController.class)).setEmergencyLaneAssistanceState(1);
                }
                ((CarController) Dependency.get(CarController.class)).setLaneAssistType(1);
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                DialogUtils.mLDADialog.dismiss();
            }
        }).show();
    }

    public static void release() {
        CommonDialog commonDialog = mEspOffDialog;
        if (commonDialog != null) {
            commonDialog.dismiss();
            mEspOffDialog = null;
        }
        CommonDialog commonDialog2 = mLDADialog;
        if (commonDialog2 != null) {
            commonDialog2.dismiss();
            mLDADialog = null;
        }
    }
}
