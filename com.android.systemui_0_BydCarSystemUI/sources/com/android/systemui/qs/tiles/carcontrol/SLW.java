package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.widget.CommonDialog;
import com.byd.profile.ToastManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SLW extends QSTileImpl<QSTile.BooleanState> {
    private static final int SLW_OFF = 1;
    private static final int SLW_ON = 2;
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private CommonDialog mDialog;
    public CommonDialog mSLWDialog;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public SLW(Context context) {
        super(context);
        BydLogHelper.logD("new SLW");
        String string = this.mContext.getString(R.string.car_title_slw);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mCallback = anonymousClass1;
        carController.addCallback(anonymousClass1);
    }

    /* JADX INFO: renamed from: com.android.systemui.qs.tiles.carcontrol.SLW$1, reason: invalid class name */
    class AnonymousClass1 implements CarController.Callback {
        AnonymousClass1() {
        }

        @Override // com.android.systemui.statusbar.policy.CarController.Callback
        public void onISLIWarningLevelSwitchStatusChanged(final int state) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$SLW$1$QrqWQz-31aU3lzhsx1iBE_FtG2g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onISLIWarningLevelSwitchStatusChanged$0$SLW$1(state);
                }
            });
        }

        public /* synthetic */ void lambda$onISLIWarningLevelSwitchStatusChanged$0$SLW$1(int i) {
            BydLogHelper.logD("onISLIWarningLevelSwitchStatusChanged state : " + i);
            SLW.this.updateItem(i);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public String getTileLabel() {
        return this.tileLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public int getTileImageRes() {
        return this.imgRes;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        int iSLIWarningLevelSwitchStatus = this.mCarController.getISLIWarningLevelSwitchStatus();
        BydLogHelper.logD("onBindViewHolder", "slwState : " + iSLIWarningLevelSwitchStatus);
        updateItem(iSLIWarningLevelSwitchStatus);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int iSLIWarningLevelSwitchStatus = this.mCarController.getISLIWarningLevelSwitchStatus();
        BydLogHelper.logD("handlerClick", "state : " + iSLIWarningLevelSwitchStatus);
        if (iSLIWarningLevelSwitchStatus == 2) {
            int supportTSRFlow = this.mCarController.getSupportTSRFlow();
            BydLogHelper.logD("handlerClick", "supportTSRFlow : " + supportTSRFlow);
            if (supportTSRFlow == 2) {
                updateItem(2);
                showSLWDialog(this.mContext, this.mContext.getResources().getString(R.string.tips_close_notice), this.mContext.getResources().getString(R.string.tips_slw_close_content));
                return;
            } else {
                updateItem(1);
                this.mCarController.setISLIWarningLevelSwitchStatus(1);
                return;
            }
        }
        int trafficSignRecognize2B4State = this.mCarController.getTrafficSignRecognize2B4State();
        int intelligentSpeedLimitAlert2B4State = this.mCarController.getIntelligentSpeedLimitAlert2B4State();
        if (intelligentSpeedLimitAlert2B4State == 0 && trafficSignRecognize2B4State == 0) {
            updateItemSelectState(false);
            ToastManager.show(this.mContext, R.string.toast_open_tsr_and_isli);
        } else if (intelligentSpeedLimitAlert2B4State == 0 && (trafficSignRecognize2B4State == 1 || trafficSignRecognize2B4State == 2 || trafficSignRecognize2B4State == 3)) {
            updateItemSelectState(false);
            ToastManager.show(this.mContext, R.string.toast_open_isli);
        } else {
            BydLogHelper.logD("handlerClick  updateItem");
            updateItem(2);
            this.mCarController.setISLIWarningLevelSwitchStatus(2);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        CarController.Callback callback;
        super.release();
        CarController carController = this.mCarController;
        if (carController == null || (callback = this.mCallback) == null) {
            return;
        }
        carController.removeCallback(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateItem(int state) {
        BydLogHelper.logD("updateItem", "state : " + state);
        if (state == 2) {
            updateItemSelected(true);
            updateImgRes(R.mipmap.icon_tsr_slw_open);
        } else {
            updateItemSelected(false);
            updateImgRes(R.mipmap.icon_tsr_slw_close);
        }
    }

    public void showSLWDialog(Context context, String title, String message) {
        if (this.mSLWDialog == null) {
            this.mSLWDialog = new CommonDialog(context);
        }
        BydLogHelper.logD("isShowing  : " + this.mSLWDialog.isShowing());
        if (this.mSLWDialog.isShowing()) {
            return;
        }
        this.mSLWDialog.setMessage(message).setTitle(title).setBottomStyle(2).setBtnLeft(context.getResources().getString(R.string.cancel)).setBtnRight(context.getResources().getString(R.string.confirm)).setBtnRightBgColor(true).setBtnRightTextColor(context.getColor(R.color.white)).setDoubleButtonStyle(1).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.carcontrol.SLW.2
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                SLW.this.mSLWDialog.dismiss();
                SLW.this.updateItem(1);
                SLW.this.mCarController.setISLIWarningLevelSwitchStatus(1);
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                SLW.this.mSLWDialog.dismiss();
            }
        }).show();
    }
}
