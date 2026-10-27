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
public class Icc extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public Icc(Context context) {
        super(context);
        BydLogHelper.logD("new Icc");
        String string = this.mContext.getString(R.string.car_title_icc);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.Icc.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onAINavigationStateChanged(final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.Icc.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onAINavigationStateChanged", "hasMessage432 : " + Icc.this.mCarController.hasMessage(10));
                        if (Icc.this.mCarController.hasMessage(10) == 1) {
                            BydLogHelper.logD("onAINavigationStateChanged", "value : " + value);
                            Icc.this.updateAiNavigation(value);
                        } else {
                            BydLogHelper.logD("onAINavigationStateChanged", "0x432 offline");
                        }
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onMessage5sOnlineStateChanged(int id, int state) {
                BydLogHelper.logD("onMessage5sOnlineStateChanged", "id : " + id + ", state : " + state);
                Icc.this.refreshCarItem(id, state);
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHasMessageChanged(int id, int state) {
                BydLogHelper.logD("onHasMessageChanged", "id : " + id + ", state : " + state);
                Icc.this.refreshCarItem(id, state);
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
        BydLogHelper.logD("onBindViewHolder", "ICC hasMessage432 : " + iHasMessage);
        if (iHasMessage != 1) {
            updateItemEnabled(false);
            return;
        }
        int aINavigationState = this.mCarController.getAINavigationState();
        BydLogHelper.logD("onBindViewHolder", "getAINavigationState : " + aINavigationState);
        updateAiNavigation(aINavigationState);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int aINavigationState = this.mCarController.getAINavigationState();
        BydLogHelper.logD("handlerClick", "getAINavigationState : " + aINavigationState);
        if (aINavigationState == 0) {
            this.mCarController.setAINavigationState(2);
        } else if (aINavigationState == 1 || aINavigationState == 2 || aINavigationState == 3) {
            this.mCarController.setAINavigationState(1);
        }
    }

    public void updateAiNavigation(int value) {
        if (value == 1 || value == 2 || value == 3) {
            updateItemSelected(true);
        } else if (value == 4) {
            updateItemEnabled(false);
        } else {
            updateItemSelected(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarItem(final int id, final int state) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$Icc$rJZrSJCREudH6i8b3Ao9EAhHsIQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshCarItem$0$Icc(id, state);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCarItem$0$Icc(int i, int i2) {
        if (i == 10) {
            boolean z = i2 == 1;
            BydLogHelper.logD("onHasMessageChanged", "enable : " + z);
            if (z) {
                int aINavigationState = this.mCarController.getAINavigationState();
                BydLogHelper.logD("onHasMessageChanged", "aiNavigationState : " + aINavigationState);
                updateAiNavigation(aINavigationState);
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
