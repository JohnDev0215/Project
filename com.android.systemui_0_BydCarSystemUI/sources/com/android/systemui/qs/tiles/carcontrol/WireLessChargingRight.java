package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.widget.CommonDialog;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class WireLessChargingRight extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final DeviceManager mDeviceManager;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public WireLessChargingRight(Context context) {
        super(context);
        BydLogHelper.logD("new WireLessChargingRight");
        String string = this.mContext.getString(R.string.car_title_wireless_charging_r);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        this.mDeviceManager = DeviceManager.getInstance();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.WireLessChargingRight.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onWirelessChargingFRSwitchStateChanged(final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.WireLessChargingRight.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int showCarModelType = WireLessChargingRight.this.mCarController.getShowCarModelType();
                        BydLogHelper.logD("onWirelessChargingFRSwitchStateChanged", "showCarModelType : " + showCarModelType);
                        if (showCarModelType == 1 || showCarModelType == 2) {
                            return;
                        }
                        if (WireLessChargingRight.this.mDeviceManager.getCarControlList().contains(WireLessChargingRight.this.mContext.getString(R.string.car_title_wireless_charging_r))) {
                            WireLessChargingRight.this.updateItemSelected(value == 1);
                        } else {
                            BydLogHelper.logD("onWirelessChargingFRSwitchStateChanged no wireless charging r");
                        }
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
        int wirelessChargingFRSwitchState = this.mCarController.getWirelessChargingFRSwitchState();
        BydLogHelper.logD("onBindViewHolder", "getWirelessChargingFRSwitchState : " + wirelessChargingFRSwitchState);
        updateItemSelected(wirelessChargingFRSwitchState == 1);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int showCarModelType = this.mCarController.getShowCarModelType();
        BydLogHelper.logD("handlerClick", "showCarModelType : " + showCarModelType);
        if (showCarModelType != 1) {
            if (showCarModelType != 2) {
                int wirelessChargingFRSwitchState = this.mCarController.getWirelessChargingFRSwitchState();
                BydLogHelper.logD("handlerClick", "getWirelessChargingFRSwitchState : " + wirelessChargingFRSwitchState);
                this.mCarController.setWirelessChargingFRSwitchState(wirelessChargingFRSwitchState != 1 ? 1 : 2);
                updateItemSelected(wirelessChargingFRSwitchState != 1);
                return;
            }
        }
        updateItemSelected(!isSelected);
        showDialog(this.mContext.getResources().getString(R.string.in_display_mode_limit_wireless_charging));
    }

    private void showDialog(String message) {
        final CommonDialog commonDialog = new CommonDialog(this.mContext);
        commonDialog.setMessage(message).setTitle((String) null).setBottomStyle(3).setBtnLeft(this.mContext.getString(R.string.know)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.carcontrol.WireLessChargingRight.2
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                commonDialog.dismiss();
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
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
