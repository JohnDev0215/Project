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
public class Avas extends QSTileImpl<QSTile.BooleanState> {
    private static final int AVAS_MODE_OFF = 0;
    private static final int AVAS_MODE_ON = 1;
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public Avas(Context context) {
        super(context);
        BydLogHelper.logD("new Avas");
        String string = this.mContext.getString(R.string.car_title_engine_sound_simulator);
        this.tileLabel = string;
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        if (carController.getAutoType() == 143) {
            this.imgRes = R.mipmap.pa_icon_control_engine_sound_simulation;
        } else {
            this.imgRes = QSIconConstants.icon.get(string).intValue();
        }
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.Avas.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onVSPStateChanged(final int state) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.Avas.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onVSPStateChanged state : " + state);
                        Avas.this.updateItem(state);
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
        int vSPState = this.mCarController.getVSPState();
        BydLogHelper.logD("onBindViewHolder", "vspState : " + vSPState);
        updateItem(vSPState);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int vSPState = this.mCarController.getVSPState();
        BydLogHelper.logD("handlerClick", "vspState : " + vSPState);
        if (vSPState == 1) {
            this.mCarController.setVSPState(0);
        } else {
            this.mCarController.setVSPState(1);
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
        updateItemSelected(state == 0);
    }
}
