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
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class LDA extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    public CommonDialog mLDADialog;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public LDA(Context context) {
        super(context);
        BydLogHelper.logD("new LA");
        String string = this.mContext.getString(R.string.car_title_la);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.LDA.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onLaneAssistTypeChanged(final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.LDA.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (LDA.this.mCarController != null) {
                            int supportEmergencyLaneKeepAssist = LDA.this.mCarController.getSupportEmergencyLaneKeepAssist();
                            BydLogHelper.logD("onLaneAssistTypeChanged", "value : " + value + " -- laneKeepAssist : " + supportEmergencyLaneKeepAssist);
                            if (supportEmergencyLaneKeepAssist == 0) {
                                int emergencyLaneAssistance32FState = LDA.this.mCarController.getEmergencyLaneAssistance32FState();
                                LDA lda = LDA.this;
                                int i = value;
                                lda.updateItemSelected(i == 1 || i == 2 || i == 3 || emergencyLaneAssistance32FState == 1 || emergencyLaneAssistance32FState == 2 || emergencyLaneAssistance32FState == 3);
                                return;
                            }
                            LDA lda2 = LDA.this;
                            int i2 = value;
                            lda2.updateItemSelected(i2 == 1 || i2 == 2 || i2 == 3);
                            return;
                        }
                        BydLogHelper.logD("onLaneAssistTypeChanged", "mCarController = null ");
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onEmergencyLaneAssistance32FStateChanged(final int laneAssistance32FState) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.LDA.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        int i;
                        BydLogHelper.logD("onEmergencyLaneAssistance32FStateChanged", "laneAssistance32FState : " + laneAssistance32FState);
                        if (LDA.this.mCarController != null) {
                            int laneAssistType = LDA.this.mCarController.getLaneAssistType();
                            int supportEmergencyLaneKeepAssist = LDA.this.mCarController.getSupportEmergencyLaneKeepAssist();
                            BydLogHelper.logD("onBindViewHolder", "getLaneAssistType : " + laneAssistType + " -- laneKeepAssist : " + supportEmergencyLaneKeepAssist);
                            if (supportEmergencyLaneKeepAssist != 0) {
                                LDA.this.updateItemSelected(laneAssistType == 1 || laneAssistType == 2 || laneAssistType == 3);
                                return;
                            } else {
                                LDA.this.updateItemSelected(laneAssistType == 1 || laneAssistType == 2 || laneAssistType == 3 || (i = laneAssistance32FState) == 1 || i == 2 || i == 3);
                                return;
                            }
                        }
                        BydLogHelper.logD("onEmergencyLaneAssistance32FStateChanged", "mCarController = null ");
                    }
                });
            }
        };
        this.mCallback = callback;
        carController.addCallback(callback);
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
        int laneAssistType = this.mCarController.getLaneAssistType();
        int supportEmergencyLaneKeepAssist = this.mCarController.getSupportEmergencyLaneKeepAssist();
        BydLogHelper.logD("onBindViewHolder", "getLaneAssistType : " + laneAssistType + " -- getSupportEmergencyLaneKeepAssist : " + supportEmergencyLaneKeepAssist);
        if (supportEmergencyLaneKeepAssist != 0) {
            updateItemSelected(laneAssistType == 1 || laneAssistType == 2 || laneAssistType == 3);
        } else {
            int emergencyLaneAssistance32FState = this.mCarController.getEmergencyLaneAssistance32FState();
            updateItemSelected(laneAssistType == 1 || laneAssistType == 2 || laneAssistType == 3 || emergencyLaneAssistance32FState == 1 || emergencyLaneAssistance32FState == 2 || emergencyLaneAssistance32FState == 3);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int laneAssistType = this.mCarController.getLaneAssistType();
        int supportDoubleConfirm = this.mCarController.getSupportDoubleConfirm();
        int supportEmergencyLaneKeepAssist = this.mCarController.getSupportEmergencyLaneKeepAssist();
        BydLogHelper.logD("handlerClick state: " + laneAssistType + " -- displayDoubleConfirm2: " + supportDoubleConfirm + " -- laneKeepAssist: " + supportEmergencyLaneKeepAssist);
        if (supportEmergencyLaneKeepAssist != 0) {
            if (laneAssistType != 1 && laneAssistType != 2 && laneAssistType != 3) {
                this.mCarController.setLaneAssistType(4);
                return;
            } else if (supportDoubleConfirm == 1) {
                updateItemSelected(true);
                showLDADialog(this.mContext, this.mContext.getResources().getString(R.string.car_title_la), this.mContext.getResources().getString(R.string.car_title_la_close_tips));
                return;
            } else {
                this.mCarController.setLaneAssistType(1);
                return;
            }
        }
        int emergencyLaneAssistance32FState = this.mCarController.getEmergencyLaneAssistance32FState();
        if (laneAssistType != 1 && laneAssistType != 2 && laneAssistType != 3 && emergencyLaneAssistance32FState != 1 && emergencyLaneAssistance32FState != 2 && emergencyLaneAssistance32FState != 3) {
            this.mCarController.setEmergencyLaneAssistanceState(2);
            this.mCarController.setLaneAssistType(4);
        } else if (supportDoubleConfirm == 1) {
            updateItemSelected(true);
            showLDADialog(this.mContext, this.mContext.getResources().getString(R.string.car_title_la), this.mContext.getResources().getString(R.string.car_title_la_close_tips));
        } else {
            this.mCarController.setEmergencyLaneAssistanceState(1);
            this.mCarController.setLaneAssistType(1);
        }
    }

    public void showLDADialog(Context context, String title, String message) {
        if (this.mLDADialog == null) {
            this.mLDADialog = new CommonDialog(context);
        }
        BydLogHelper.logD("isShowing  : " + this.mLDADialog.isShowing());
        if (this.mLDADialog.isShowing()) {
            return;
        }
        this.mLDADialog.setMessage(message).setTitle(title).setBottomStyle(2).setBtnLeft(context.getResources().getString(R.string.cancel)).setBtnRight(context.getResources().getString(R.string.confirm)).setBtnRightBgColor(true).setBtnRightTextColor(context.getColor(R.color.white)).setDoubleButtonStyle(1).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.carcontrol.LDA.2
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                LDA.this.mLDADialog.dismiss();
                LDA.this.updateItemSelected(false);
                int supportEmergencyLaneKeepAssist = ((CarController) Dependency.get(CarController.class)).getSupportEmergencyLaneKeepAssist();
                BydLogHelper.logD("onPositiveClick supportEmergencyLaneKeepAssist : " + supportEmergencyLaneKeepAssist);
                if (supportEmergencyLaneKeepAssist == 0) {
                    ((CarController) Dependency.get(CarController.class)).setEmergencyLaneAssistanceState(1);
                }
                ((CarController) Dependency.get(CarController.class)).setLaneAssistType(1);
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                LDA.this.mLDADialog.dismiss();
            }
        }).show();
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
}
