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
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SteeringWheelHeating extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public SteeringWheelHeating(Context context) {
        super(context);
        BydLogHelper.logD("new SteeringWheelHeating");
        String string = this.mContext.getString(R.string.car_title_steering_wheel_heating);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.SteeringWheelHeating.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onSteeringWheelWarmStateChanged(final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.SteeringWheelHeating.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onSteeringWheelWarmStateChanged", "value : " + value);
                        SteeringWheelHeating.this.updateItemSelected(value == 2);
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
        int steeringWheelHeatingState = this.mCarController.getSteeringWheelHeatingState();
        BydLogHelper.logD("onBindViewHolder", "getSteeringWheelWarmState : " + steeringWheelHeatingState);
        updateItemSelected(steeringWheelHeatingState == 2);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int steeringWheelHeatingState = this.mCarController.getSteeringWheelHeatingState();
        BydLogHelper.logD("handlerClick", "getSteeringWheelHeatingState : " + steeringWheelHeatingState);
        this.mCarController.setSteeringWheelHeatingState(steeringWheelHeatingState == 1 ? 2 : 1);
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
