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
import com.android.systemui.util.DialogUtils;
import com.android.systemui.widget.CommonDialog;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class EspOff extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private CommonDialog mCommonDialog;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public EspOff(Context context) {
        super(context);
        BydLogHelper.logD("new EspOff");
        String string = this.mContext.getString(R.string.car_title_esp_off);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.EspOff.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onElecCarBodyStablySysStateChanged(final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.EspOff.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onElecCarBodyStablySysStateChanged", "value : " + value);
                        EspOff.this.updateItemSelected(value == 1);
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onMessage5sOnlineStateChanged(int id, int state) {
                BydLogHelper.logD("onMessage5sOnlineStateChanged", "id : " + id + ", state : " + state);
                if (id == 18) {
                    EspOff.this.refreshCarItem(id, state);
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHasMessageChanged(int id, int state) {
                BydLogHelper.logD("onHasMessageChanged", "id : " + id + ", state : " + state);
                if (id == 18) {
                    EspOff.this.refreshCarItem(id, state);
                }
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
        int iHasMessage = this.mCarController.hasMessage(18);
        BydLogHelper.logD("onBindViewHolder", "EspOff hasMessage123 : " + iHasMessage);
        if (iHasMessage != 1) {
            updateItemEnabled(false);
            return;
        }
        int elecCarBodyStablySysState = this.mCarController.getElecCarBodyStablySysState();
        BydLogHelper.logD("onBindViewHolder", "getElecCarBodyStablySysState : " + elecCarBodyStablySysState);
        updateItemSelected(elecCarBodyStablySysState == 1);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int elecCarBodyStablySysState = this.mCarController.getElecCarBodyStablySysState();
        BydLogHelper.logD("handlerClick getElecCarBodyStablySysState : " + elecCarBodyStablySysState);
        if (elecCarBodyStablySysState == 0) {
            if (this.mCarController.getSupportDoubleConfirm() == 1) {
                updateItemSelected(false);
                DialogUtils.showEspOffDialog(this.mContext, this.mContext.getResources().getString(R.string.esp_system), this.mContext.getResources().getString(R.string.esp_system_close_tips));
                return;
            } else {
                this.mCarController.setElecCarBodyStablySysState(1);
                return;
            }
        }
        this.mCarController.setElecCarBodyStablySysState(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarItem(final int id, final int state) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$EspOff$5NNzVWDMOdhiw79Ch4xp8KUFkvg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshCarItem$0$EspOff(state, id);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCarItem$0$EspOff(int i, int i2) {
        if (i == 1) {
            BydLogHelper.logD("onHasMessageChanged", "id : " + i2 + ", state : " + i);
            int elecCarBodyStablySysState = this.mCarController.getElecCarBodyStablySysState();
            BydLogHelper.logD("onHasMessageChanged", "elecCarBodyStablySysState : " + elecCarBodyStablySysState);
            updateItemSelected(elecCarBodyStablySysState == 1);
            return;
        }
        updateItemEnabled(false);
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
