package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
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
public class Defrosting extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public Defrosting(Context context) {
        super(context);
        BydLogHelper.logD("new Defrosting");
        String string = this.mContext.getString(R.string.car_title_electric_defrosting);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.Defrosting.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onAcDefrostStateChanged(final int area, final int state) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.Defrosting.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onAcDefrostStateChanged", "area : " + area + " , state : " + state);
                        Defrosting.this.updateItemSelected(state == 1);
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
        int acDefrostState = this.mCarController.getAcDefrostState(2);
        BydLogHelper.logD("onBindViewHolder", "getElectricDefrostState : " + acDefrostState);
        updateItemSelected(acDefrostState == 1);
        int defrostRearConfig = BYDAutoAcDevice.getInstance(this.mContext).getDefrostRearConfig();
        if (defrostRearConfig == 2) {
            updateTileLabel(this.mContext.getString(R.string.car_title_electric_defrosting));
            updateImgRes(R.mipmap.icon_electric_defrosting);
        } else if (defrostRearConfig == 1) {
            updateTileLabel(this.mContext.getString(R.string.car_title_rear_defrosting));
            updateImgRes(R.mipmap.icon_rear_defrosting);
        } else if (defrostRearConfig == 3) {
            updateTileLabel(this.mContext.getString(R.string.car_title_heat_outside_mirror));
            updateImgRes(R.mipmap.icon_exteriormirrorheating_defrost);
        } else {
            updateTileLabel(this.mContext.getString(R.string.car_title_electric_defrosting));
            updateImgRes(R.mipmap.icon_electric_defrosting);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int acDefrostState = this.mCarController.getAcDefrostState(2);
        BydLogHelper.logD("handlerClick", "getElectricDefrostState : " + acDefrostState);
        this.mCarController.setAcDefrostState(acDefrostState == 1 ? 0 : 1);
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
