package com.android.systemui.qs.tiles.carcontrol;

import android.content.Context;
import android.os.Handler;
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
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class Hud extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final StatusBar statusBar;
    private final String tileLabel;

    public Hud(Context context) {
        super(context);
        BydLogHelper.logD("new Hud");
        String string = this.mContext.getString(R.string.car_title_hud);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.Hud.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHudStateChanged(final int state) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.Hud.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onHudStateChanged state : " + state);
                        Hud.this.updateItem(state);
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
        int hudState = this.mCarController.getHudState();
        BydLogHelper.logD("onBindViewHolder", "hudState : " + hudState);
        updateItem(hudState);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int hudState = this.mCarController.getHudState();
        BydLogHelper.logD("handlerClick", "hudState : " + hudState);
        if (hudState == 1) {
            this.mCarController.setHudState(2);
        } else {
            this.mCarController.setHudState(1);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
        if (isLongClickFlag()) {
            AppUtil.startCarSettings(Constants.PackageInfo.ACTION_HUD);
            if (this.statusBar.isShow()) {
                SharedPreferencesUtil.putBoolean(this.mContext, Constants.IS_START_ACTIVITY, true);
            }
            this.statusBar.closePanelsIfNeed();
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
        updateItemSelected(state == 1);
    }
}
