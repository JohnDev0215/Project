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
public class ActiveSuspensionMode extends QSTileImpl<QSTile.BooleanState> {
    private int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public ActiveSuspensionMode(Context context) {
        super(context);
        BydLogHelper.logD("new ActiveSuspensionMode");
        String string = this.mContext.getString(R.string.car_title_active_suspension_mode);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.ActiveSuspensionMode.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onDynamicSuspensionModeChanged(int value) {
                BydLogHelper.logD("onDynamicSuspensionModeChanged", "value : " + value);
                ActiveSuspensionMode.this.updateDynamicSuspensionMode(value);
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
        int dynamicSuspensionMode = this.mCarController.getDynamicSuspensionMode();
        int diSusMode = this.mCarController.getDiSusMode();
        BydLogHelper.logD("onBindViewHolder mode : " + diSusMode);
        if (diSusMode == 0) {
            updateTileLabel(this.mContext.getString(R.string.car_title_suspension_c_mode));
        } else if (diSusMode == 1) {
            updateTileLabel(this.mContext.getString(R.string.car_title_suspension_p_mode));
        } else {
            updateTileLabel(this.mContext.getString(R.string.car_title_suspension_a_mode));
        }
        BydLogHelper.logD("onBindViewHolder", "onBindViewHolder : " + dynamicSuspensionMode);
        updateDynamicSuspensionMode(dynamicSuspensionMode);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int dynamicSuspensionMode = this.mCarController.getDynamicSuspensionMode();
        BydLogHelper.logD("handlerClick", "getDynamicSuspensionMode : " + dynamicSuspensionMode);
        if (dynamicSuspensionMode == 1) {
            dynamicSuspensionMode = 2;
        } else if (dynamicSuspensionMode == 2) {
            dynamicSuspensionMode = 1;
        }
        updateDynamicSuspensionMode(dynamicSuspensionMode);
        this.mCarController.setDynamicSuspensionMode(dynamicSuspensionMode);
    }

    public void updateDynamicSuspensionMode(final int value) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.ActiveSuspensionMode.2
            @Override // java.lang.Runnable
            public void run() {
                BydLogHelper.logD("updateDynamicSuspensionMode", "value : " + value);
                int i = value;
                if (i == 1) {
                    ActiveSuspensionMode.this.imgRes = R.mipmap.icon_control_suspension_mode_comfort;
                    QSIconConstants.icon.put(ActiveSuspensionMode.this.tileLabel, Integer.valueOf(ActiveSuspensionMode.this.imgRes));
                    ActiveSuspensionMode.this.updateImgRes(R.mipmap.icon_control_suspension_mode_comfort);
                } else if (i == 2) {
                    ActiveSuspensionMode.this.imgRes = R.mipmap.icon_control_suspension_mode_sport;
                    QSIconConstants.icon.put(ActiveSuspensionMode.this.tileLabel, Integer.valueOf(ActiveSuspensionMode.this.imgRes));
                    ActiveSuspensionMode.this.updateImgRes(R.mipmap.icon_control_suspension_mode_sport);
                }
                ActiveSuspensionMode.this.updateItemSelected(true);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        CarController.Callback callback;
        CarController carController = this.mCarController;
        if (carController == null || (callback = this.mCallback) == null) {
            return;
        }
        carController.removeCallback(callback);
    }
}
