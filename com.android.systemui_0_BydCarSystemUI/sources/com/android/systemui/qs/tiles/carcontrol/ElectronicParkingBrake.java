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
public class ElectronicParkingBrake extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public ElectronicParkingBrake(Context context) {
        super(context);
        BydLogHelper.logD("new ElectronicParkingBrake");
        String string = this.mContext.getString(R.string.car_title_electronic_parking_brake);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.ElectronicParkingBrake.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onEpbStateChanged(final int state) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.ElectronicParkingBrake.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onEPBStateChanged", "state : " + state);
                        ElectronicParkingBrake.this.updateEpbState(state);
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
        int ePBState = this.mCarController.getEPBState();
        BydLogHelper.logD("onBindViewHolder", "getEPBState : " + ePBState);
        updateEpbState(ePBState);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int ePBState = this.mCarController.getEPBState();
        BydLogHelper.logD("handlerClick", "getEPBState : " + ePBState);
        if (ePBState == 1) {
            this.mCarController.setEPBState(1);
        } else if (ePBState == 3) {
            updateItemSelected(true);
            showDialog(this.mContext.getResources().getString(R.string.release_epb_by_shift_gears));
        }
    }

    public void updateEpbState(int value) {
        updateItemSelected(value == 3);
    }

    private void showDialog(String message) {
        final CommonDialog commonDialog = new CommonDialog(this.mContext);
        commonDialog.setMessage(message).setTitle((String) null).setBottomStyle(3).setBtnLeft(this.mContext.getString(R.string.know)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.carcontrol.ElectronicParkingBrake.2
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
