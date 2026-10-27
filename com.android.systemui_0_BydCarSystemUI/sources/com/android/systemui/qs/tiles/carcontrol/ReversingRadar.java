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
public class ReversingRadar extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public ReversingRadar(Context context) {
        super(context);
        BydLogHelper.logD("new ReversingRadar");
        String string = this.mContext.getString(R.string.car_title_reversing_radar);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.ReversingRadar.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onReverseRadarSwitchStateChanged(final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.ReversingRadar.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onReverseRadarSwitchStateChanged", "hasMessage432 : " + ReversingRadar.this.mCarController.hasMessage(10));
                        if (ReversingRadar.this.mCarController.hasMessage(10) == 1) {
                            BydLogHelper.logD("onReverseRadarSwitchStateChanged", "value : " + value);
                            ReversingRadar.this.updateItemSelected(value == 1);
                        } else {
                            BydLogHelper.logD("onReverseRadarSwitchStateChanged", "0x432 offline");
                        }
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onMessage5sOnlineStateChanged(int id, int state) {
                BydLogHelper.logD("onMessage5sOnlineStateChanged", "id : " + id + ", state : " + state);
                ReversingRadar.this.refreshCarItem(id, state);
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHasMessageChanged(int id, int state) {
                BydLogHelper.logD("onHasMessageChanged", "id : " + id + ", state : " + state);
                ReversingRadar.this.refreshCarItem(id, state);
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
        int iHasMessage = this.mCarController.hasMessage(10);
        BydLogHelper.logD("onBindViewHolder", "ReversingRadar hasMessage432 : " + iHasMessage);
        if (iHasMessage != 1) {
            updateItemEnabled(false);
            return;
        }
        int reverseRadarSwitchState = this.mCarController.getReverseRadarSwitchState();
        BydLogHelper.logD("onBindViewHolder", "getReverseRadarSwitchState : " + reverseRadarSwitchState);
        updateItemSelected(reverseRadarSwitchState == 1);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        BydLogHelper.logD("handlerClick", "getReverseRadarSwitchState : " + this.mCarController.getReverseRadarSwitchState());
        this.mCarController.setReverseRadarSwitchState(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarItem(final int id, final int state) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$ReversingRadar$SLKyuJfsZBks2FHb3uqG9LQSTbE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshCarItem$0$ReversingRadar(id, state);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCarItem$0$ReversingRadar(int i, int i2) {
        if (i == 10) {
            boolean z = i2 == 1;
            BydLogHelper.logD("onHasMessageChanged", "enable : " + z);
            if (z) {
                int reverseRadarSwitchState = this.mCarController.getReverseRadarSwitchState();
                BydLogHelper.logD("onHasMessageChanged", "reverseRadarSwitchState : " + reverseRadarSwitchState);
                updateItemSelected(reverseRadarSwitchState == 1);
                return;
            }
            updateItemEnabled(false);
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
}
