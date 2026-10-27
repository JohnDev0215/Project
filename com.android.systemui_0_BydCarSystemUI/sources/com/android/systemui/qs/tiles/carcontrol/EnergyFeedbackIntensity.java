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
public class EnergyFeedbackIntensity extends QSTileImpl<QSTile.BooleanState> {
    private int imgRes;
    private final CarController.Callback mCarControlCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public EnergyFeedbackIntensity(Context context) {
        super(context);
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.EnergyFeedbackIntensity.2
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onEnergyFeedbackLevelChanged(int value) {
                BydLogHelper.logD("onEnergyFeedbackLevelChanged", "value : " + value);
                EnergyFeedbackIntensity.this.updateEnergyFeedback(value);
            }
        };
        this.mCarControlCallback = callback;
        BydLogHelper.logD("new EnergyFeedbackIntensity");
        String string = this.mContext.getString(R.string.car_title_energy_feedback);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
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
        int energyFeedback = this.mCarController.getEnergyFeedback();
        BydLogHelper.logD("onBindViewHolder", "getEnergyFeedback : " + energyFeedback);
        updateEnergyFeedback(energyFeedback);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002e  */
    @Override // com.android.systemui.qs.tileimpl.QSTile
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void click(boolean r4) {
        /*
            r3 = this;
            com.android.systemui.statusbar.policy.CarController r4 = r3.mCarController
            int r4 = r4.getEnergyFeedback()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getEnergyFeedback : "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "handlerClick"
            com.bydux.api.common.tools.log.BydLogHelper.logD(r1, r0)
            r0 = 4
            r1 = 3
            r2 = 2
            if (r4 != r1) goto L30
            com.android.systemui.statusbar.policy.CarController r4 = r3.mCarController
            int r4 = r4.getEnergyFeedbackSupport()
            r1 = 1
            if (r4 != r1) goto L2e
            r4 = r0
            goto L37
        L2e:
            r4 = r2
            goto L37
        L30:
            if (r4 != r2) goto L34
            r4 = r1
            goto L37
        L34:
            if (r4 != r0) goto L37
            goto L2e
        L37:
            r3.updateEnergyFeedback(r4)
            com.android.systemui.statusbar.policy.CarController r0 = r3.mCarController
            r0.setEnergyFeedback(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.carcontrol.EnergyFeedbackIntensity.click(boolean):void");
    }

    public void updateEnergyFeedback(final int value) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.EnergyFeedbackIntensity.1
            @Override // java.lang.Runnable
            public void run() {
                BydLogHelper.logD("updateEnergyFeedback", "value : " + value);
                int i = value;
                if (i == 1) {
                    EnergyFeedbackIntensity.this.updateItemSelected(true);
                    return;
                }
                if (i == 2) {
                    EnergyFeedbackIntensity.this.imgRes = R.mipmap.icon_control_energy_feedback_standard;
                    QSIconConstants.icon.put(EnergyFeedbackIntensity.this.tileLabel, Integer.valueOf(EnergyFeedbackIntensity.this.imgRes));
                    EnergyFeedbackIntensity.this.updateImgRes(R.mipmap.icon_control_energy_feedback_standard);
                } else if (i == 3) {
                    EnergyFeedbackIntensity.this.imgRes = R.mipmap.icon_control_energy_recovery;
                    QSIconConstants.icon.put(EnergyFeedbackIntensity.this.tileLabel, Integer.valueOf(EnergyFeedbackIntensity.this.imgRes));
                    EnergyFeedbackIntensity.this.updateImgRes(R.mipmap.icon_control_energy_recovery);
                } else if (i == 4) {
                    EnergyFeedbackIntensity.this.imgRes = R.mipmap.icon_control_energy_super;
                    QSIconConstants.icon.put(EnergyFeedbackIntensity.this.tileLabel, Integer.valueOf(EnergyFeedbackIntensity.this.imgRes));
                    EnergyFeedbackIntensity.this.updateImgRes(R.mipmap.icon_control_energy_super);
                }
                EnergyFeedbackIntensity.this.updateItemSelected(true);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.qs.tileimpl.QSTile
    public void release() {
        super.release();
        this.mCarController.removeCallback(this.mCarControlCallback);
    }
}
