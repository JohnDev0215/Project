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
public class Bsd extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public Bsd(Context context) {
        super(context);
        BydLogHelper.logD("new Bsd");
        String string = this.mContext.getString(R.string.car_title_bsd);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.Bsd.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onBlindMonitorStateChanged(int type, int value) {
                BydLogHelper.logD("onBlindMonitorStateChanged", "hasMessage432 : " + Bsd.this.mCarController.hasMessage(10));
                if (Bsd.this.mCarController.hasMessage(10) == 1) {
                    BydLogHelper.logD("onBlindMonitorStateChanged", "value : " + value);
                    Bsd.this.updateBlindMonitorState(type, value);
                } else {
                    BydLogHelper.logD("onBlindMonitorStateChanged", "0x432 offline");
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onMessage5sOnlineStateChanged(int id, int state) {
                BydLogHelper.logD("onMessage5sOnlineStateChanged", "id : " + id + ", state : " + state);
                if (id == 10) {
                    int blindFault = Bsd.this.mCarController.getBlindFault();
                    boolean z = state == 1 && blindFault != 1;
                    BydLogHelper.logD("onMessage5sOnlineStateChanged hasMessage432 : " + state + " -- blindFault:" + blindFault);
                    Bsd.this.refreshCarItem(z);
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHasMessageChanged(int id, int state) {
                BydLogHelper.logD("onHasMessageChanged", "id : " + id + ", state : " + state);
                if (id == 10) {
                    int blindFault = Bsd.this.mCarController.getBlindFault();
                    boolean z = state == 1 && blindFault != 1;
                    BydLogHelper.logD("onHasMessageChanged hasMessage432 : " + state + " -- blindFault:" + blindFault);
                    Bsd.this.refreshCarItem(z);
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onBlindFault(byte fault) {
                int iHasMessage = Bsd.this.mCarController.hasMessage(10);
                boolean z = iHasMessage == 1 && fault != 1;
                BydLogHelper.logD("onBlindFault hasMessage432 : " + iHasMessage + " -- fault:" + ((int) fault));
                Bsd.this.refreshCarItem(z);
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
        int blindFault = this.mCarController.getBlindFault();
        BydLogHelper.logD("onBindViewHolder BSD hasMessage432 : " + iHasMessage + " -- blindFault:" + blindFault);
        if (iHasMessage != 1 || blindFault == 1) {
            updateItemEnabled(false);
            return;
        }
        int blindMonitorState = this.mCarController.getBlindMonitorState();
        BydLogHelper.logD("onBindViewHolder", "getBlindMonitorState : " + blindMonitorState);
        updateBlindMonitorState(this.mCarController.getBlindMonitorType(), blindMonitorState);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int blindMonitorState = this.mCarController.getBlindMonitorState();
        BydLogHelper.logD("handlerClick", "getBlindMonitorState : " + blindMonitorState);
        if (blindMonitorState == 1) {
            this.mCarController.setBlindMonitorState(1);
        } else if (blindMonitorState == 0) {
            this.mCarController.setBlindMonitorState(2);
        }
    }

    public void updateBlindMonitorState(final int type, final int value) {
        BydLogHelper.logD("updateBlindMonitorState", "type : " + type + " , value : " + value);
        final int iHasMessage = this.mCarController.hasMessage(10);
        final int blindFault = this.mCarController.getBlindFault();
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$Bsd$BzNKzy7LtUhvtqCcMnSJJIawLX4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateBlindMonitorState$0$Bsd(iHasMessage, blindFault, value, type);
            }
        });
    }

    public /* synthetic */ void lambda$updateBlindMonitorState$0$Bsd(int i, int i2, int i3, int i4) {
        BydLogHelper.logD("updateBlindMonitorState hasMessage432 : " + i + " -- blindFault:" + i2);
        if (i != 1 || i2 == 1) {
            return;
        }
        if (i3 == 0) {
            updateItemSelected(false);
            return;
        }
        if (i3 == 1 || i3 == 2) {
            updateItemSelected(true);
        } else if (i4 == 1 && i3 == 3) {
            updateItemEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarItem(final boolean enable) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$Bsd$K2Rp3kphK_n8krQrCVVYHqgLhTU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshCarItem$1$Bsd(enable);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCarItem$1$Bsd(boolean z) {
        BydLogHelper.logD("refreshCarItem enable : " + z);
        if (z) {
            int blindMonitorState = this.mCarController.getBlindMonitorState();
            BydLogHelper.logD("refreshCarItem getBlindMonitorState : " + blindMonitorState);
            updateBlindMonitorState(this.mCarController.getBlindMonitorType(), blindMonitorState);
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
