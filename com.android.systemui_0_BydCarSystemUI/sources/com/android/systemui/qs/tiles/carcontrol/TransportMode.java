package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
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
public class TransportMode extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final StatusBar statusBar;
    private final String tileLabel;

    public TransportMode(Context context) {
        super(context);
        BydLogHelper.logD("new TransportMode");
        String string = this.mContext.getString(R.string.transport_mode);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.TransportMode.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onTransportModeSwitchChanged(final int state) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.TransportMode.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onTransportModeSwitchChanged", "state : " + state);
                        TransportMode.this.updateItemSelected(state == 1);
                    }
                });
            }
        };
        this.mCallback = callback;
        carController.addCallback(callback);
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
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
        int transportMode = this.mCarController.getTransportMode();
        BydLogHelper.logD("onBindViewHolder", "getTransportMode : " + transportMode);
        updateItemSelected(transportMode == 1);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        this.statusBar.closePanelsIfNeed();
        int transportMode = this.mCarController.getTransportMode();
        BydLogHelper.logD("handlerClick", "transportMode : " + transportMode);
        if (transportMode == 1) {
            updateItemSelected(true);
            showToast(this.mContext.getString(R.string.please_use_vds_operation));
        } else if (this.mCarController.getTransportMode() == 2) {
            SharedPreferencesUtil.putBoolean(this.mContext, Constants.SET_TRANSPORT_MODE_BY_USER, true);
            this.mCarController.setTransportMode(1);
            this.mCarController.setTransportModeSpringBack();
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
        BydLogHelper.logD("handlerLongClick tileLabel : " + this.tileLabel);
        if (isLongClickFlag() && TextUtils.equals(this.tileLabel, this.mContext.getString(R.string.transport_mode))) {
            this.statusBar.closePanelsIfNeed();
            showToast(this.mContext.getString(R.string.please_use_vds_operation));
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
