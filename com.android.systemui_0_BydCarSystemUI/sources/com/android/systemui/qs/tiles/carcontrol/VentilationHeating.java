package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class VentilationHeating extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController mCarController;
    private boolean mIsSupportSeatHeat;
    private boolean mIsSupportSeatVent;
    private final StatusBar statusBar;
    private String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public VentilationHeating(Context context) {
        super(context);
        BydLogHelper.logD("new VentilationHeating");
        this.tileLabel = this.mContext.getString(R.string.car_title_ventilation_heating);
        this.imgRes = QSIconConstants.icon.get(this.tileLabel).intValue();
        this.mCarController = (CarController) Dependency.get(CarController.class);
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        initSeatName();
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
        boolean z = this.mIsSupportSeatHeat;
        if (z && !this.mIsSupportSeatVent) {
            updateTileLabel(this.mContext.getString(R.string.seat_heat));
            updateImgRes(R.mipmap.icon_seat_heating);
        } else if (!z && this.mIsSupportSeatVent) {
            updateTileLabel(this.mContext.getString(R.string.seat_ventilation));
            updateImgRes(R.mipmap.icon_seat_ventilation);
        } else {
            updateTileLabel(this.mContext.getString(R.string.car_title_ventilation_heating));
            updateImgRes(R.mipmap.icon_ventilation_heating);
        }
        updateItemSelected(false);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        BydLogHelper.logD("VentilationHeating click");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CAR");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        if (this.mIsSupportSeatHeat && !this.mIsSupportSeatVent) {
            intent.setData(Uri.parse("mycar://byd.car.seat?type=1"));
        } else {
            intent.setData(Uri.parse("mycar://byd.car.seat?type=2"));
        }
        this.mContext.startActivity(intent);
        if (this.statusBar.isShow()) {
            SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
        }
        this.statusBar.closePanelsIfNeed();
        updateItemSelected(false);
    }

    private void initSeatName() {
        VentilationHeating ventilationHeating;
        boolean z;
        int i = this.mCarController.get5LevelSeatHeatingState(1);
        int i2 = this.mCarController.get5LevelSeatHeatingState(2);
        int i3 = this.mCarController.get5LevelSeatHeatingState(3);
        int i4 = this.mCarController.get5LevelSeatHeatingState(4);
        int i5 = this.mCarController.get5LevelSeatVentilatingState(1);
        int i6 = this.mCarController.get5LevelSeatVentilatingState(2);
        int i7 = this.mCarController.get5LevelSeatVentilatingState(3);
        int i8 = this.mCarController.get5LevelSeatVentilatingState(4);
        int seatVentilatingState = this.mCarController.getSeatVentilatingState(1);
        int seatVentilatingState2 = this.mCarController.getSeatVentilatingState(2);
        int seatVentilatingState3 = this.mCarController.getSeatVentilatingState(3);
        int seatVentilatingState4 = this.mCarController.getSeatVentilatingState(4);
        int seatVentilatingState22 = this.mCarController.getSeatVentilatingState2(3);
        int seatVentilatingState23 = this.mCarController.getSeatVentilatingState2(4);
        int seatVentilatingNotL1PState = this.mCarController.getSeatVentilatingNotL1PState();
        int seatHeatingState = this.mCarController.getSeatHeatingState(1);
        int seatHeatingState2 = this.mCarController.getSeatHeatingState(2);
        int seatHeatingState3 = this.mCarController.getSeatHeatingState(3);
        int seatHeatingState4 = this.mCarController.getSeatHeatingState(4);
        int seatHeatingState22 = this.mCarController.getSeatHeatingState2(3);
        int seatHeatingState23 = this.mCarController.getSeatHeatingState2(4);
        int seatHeatingNotL1PState = this.mCarController.getSeatHeatingNotL1PState();
        BydLogHelper.logD("initSeatName m5LevelDriverSeatHeatingState:" + i + " -- m5LevelPassengerSeatHeatingState:" + i2 + " -- m5LevelRearLeftSeatHeatingState:" + i3 + " -- m5LevelRearRightSeatHeatingState:" + i4 + " -- mSeatWarmMainState:" + seatHeatingState + " -- mSeatWarmDeputyState:" + seatHeatingState2 + " -- mSeatWarmLeftRearState:" + seatHeatingState3 + " -- mSeatWarmRightRearState:" + seatHeatingState4 + " -- mSeatWarmLeftRearState2:" + seatHeatingState22 + " -- mSeatWarmRightRearState2:" + seatHeatingState23 + " -- mCopilotWarmNotL1PState:" + seatHeatingNotL1PState);
        BydLogHelper.logD("initSeatName  m5LevelDriverSeatVentilatingState:" + i5 + " -- m5LevelPassengerSeatVentilatingState:" + i6 + " -- m5LevelRearLeftSeatVentilatingState:" + i7 + " -- m5LevelRearRightSeatVentilatingState:" + i8 + " -- mSeatVentilationMainState:" + seatVentilatingState + " -- mSeatVentilationDeputyState:" + seatVentilatingState2 + " -- mSeatVentilationLeftRearState:" + seatVentilatingState3 + " -- mSeatVentilationRightRearState:" + seatVentilatingState4 + " -- mSeatVentilationLeftRearState2:" + seatVentilatingState22 + " -- mSeatVentilationRightRearState2:" + seatVentilatingState23 + " -- mCopilotVentilationNotL1PState:" + seatVentilatingNotL1PState);
        if (i + i + i2 + i2 + i3 + i3 + i4 + i4 + seatHeatingState + seatHeatingState + seatHeatingState + seatHeatingState2 + seatHeatingState2 + seatHeatingState2 + seatHeatingNotL1PState + seatHeatingNotL1PState + seatHeatingNotL1PState + seatHeatingState3 + seatHeatingState3 + seatHeatingState3 + seatHeatingState4 + seatHeatingState4 + seatHeatingState4 + seatHeatingState22 + seatHeatingState23 >= 1) {
            z = true;
            ventilationHeating = this;
        } else {
            ventilationHeating = this;
            z = false;
        }
        ventilationHeating.mIsSupportSeatHeat = z;
        ventilationHeating.mIsSupportSeatVent = (((((((((((((((((((((((i5 + i5) + i6) + i6) + i7) + i7) + i8) + i8) + seatVentilatingState) + seatVentilatingState) + seatVentilatingState) + seatVentilatingState2) + seatVentilatingState2) + seatVentilatingState2) + seatVentilatingNotL1PState) + seatVentilatingNotL1PState) + seatVentilatingNotL1PState) + seatVentilatingState3) + seatVentilatingState3) + seatVentilatingState3) + seatVentilatingState4) + seatVentilatingState4) + seatVentilatingState4) + seatVentilatingState22) + seatVentilatingState23 >= 1;
    }
}
