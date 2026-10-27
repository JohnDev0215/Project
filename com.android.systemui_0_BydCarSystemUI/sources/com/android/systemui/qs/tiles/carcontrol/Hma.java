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
import com.android.systemui.widget.CommonDialog;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class Hma extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public Hma(Context context) {
        super(context);
        BydLogHelper.logD("new Hma");
        String string = this.mContext.getString(R.string.car_title_hma);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.Hma.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onAiDistanceLightStateChanged(final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.Hma.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onAiDistanceLightStateChanged", "hasMessage432 : " + Hma.this.mCarController.hasMessage(10));
                        if (Hma.this.mCarController.hasMessage(10) == 1) {
                            BydLogHelper.logD("onAiDistanceLightStateChanged", "value : " + value);
                            Hma.this.updateAiDistanceLightState(value);
                        } else {
                            BydLogHelper.logD("onAiDistanceLightStateChanged", "0x432 offline");
                        }
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onMessage5sOnlineStateChanged(int id, int state) {
                BydLogHelper.logD("onMessage5sOnlineStateChanged", "id : " + id + ", state : " + state);
                if (id == 10) {
                    int adaptiveFrontLightFault = Hma.this.mCarController.getAdaptiveFrontLightFault();
                    boolean z = state == 1 && adaptiveFrontLightFault != 1;
                    BydLogHelper.logD("onMessage5sOnlineStateChanged hasMessage432 : " + state + " -- adaptiveFrontLightFault:" + adaptiveFrontLightFault);
                    Hma.this.refreshCarItem(z);
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHasMessageChanged(int id, int state) {
                BydLogHelper.logD("onHasMessageChanged id : " + id + ", state : " + state);
                if (id == 10) {
                    int adaptiveFrontLightFault = Hma.this.mCarController.getAdaptiveFrontLightFault();
                    boolean z = state == 1 && adaptiveFrontLightFault != 1;
                    BydLogHelper.logD("onHasMessageChanged hasMessage432 : " + state + " -- adaptiveFrontLightFault:" + adaptiveFrontLightFault);
                    Hma.this.refreshCarItem(z);
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onAdaptiveFrontLightFault(byte fault) {
                int iHasMessage = Hma.this.mCarController.hasMessage(10);
                boolean z = iHasMessage == 1 && fault != 1;
                BydLogHelper.logD("onAdaptiveFrontLightFault hasMessage432 : " + iHasMessage + " -- fault:" + ((int) fault));
                Hma.this.refreshCarItem(z);
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
        int adaptiveFrontLightFault = this.mCarController.getAdaptiveFrontLightFault();
        BydLogHelper.logD("onBindViewHolder Hma hasMessage432 : " + iHasMessage + " -- adaptiveFrontLightFault:" + adaptiveFrontLightFault);
        if (iHasMessage != 1 || adaptiveFrontLightFault == 1) {
            updateItemEnabled(false);
            return;
        }
        int aiDistanceLightState = this.mCarController.getAiDistanceLightState();
        BydLogHelper.logD("onBindViewHolder", "getAiDistanceLightState : " + aiDistanceLightState);
        updateAiDistanceLightState(aiDistanceLightState);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void click(boolean isSelected) {
        int aiDistanceLightState = this.mCarController.getAiDistanceLightState();
        BydLogHelper.logD("handlerClick", "getAiDistanceLightState : " + aiDistanceLightState);
        if (aiDistanceLightState == 0) {
            this.mCarController.setAiDistanceLightState(2);
            return;
        }
        if (aiDistanceLightState == 1 || aiDistanceLightState == 2 || aiDistanceLightState == 3) {
            this.mCarController.setAiDistanceLightState(1);
        } else if (aiDistanceLightState == 4 || aiDistanceLightState == 5) {
            updateItemDisabled();
            showDialog(this.mContext.getResources().getString(R.string.function_failure));
        }
    }

    public void updateAiDistanceLightState(int value) {
        BydLogHelper.logD("updateAiDistanceLightState", "value : " + value);
        int iHasMessage = this.mCarController.hasMessage(10);
        int adaptiveFrontLightFault = this.mCarController.getAdaptiveFrontLightFault();
        BydLogHelper.logD("updateAiDistanceLightState hasMessage432 : " + iHasMessage + " -- adaptiveFrontLightFault:" + adaptiveFrontLightFault);
        if (iHasMessage != 1 || adaptiveFrontLightFault == 1) {
            return;
        }
        if (value == 0) {
            updateItemSelected(false);
            return;
        }
        if (value == 1 || value == 2 || value == 3) {
            updateItemEnabled(true);
        } else if (value == 4 || value == 5) {
            updateItemDisabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarItem(final boolean enable) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$Hma$Kk5_eeqXrltFtS_hg0NKExigLtE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshCarItem$0$Hma(enable);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCarItem$0$Hma(boolean z) {
        if (z) {
            int aiDistanceLightState = this.mCarController.getAiDistanceLightState();
            BydLogHelper.logD("refreshCarItem aiDistanceLightState : " + aiDistanceLightState);
            updateAiDistanceLightState(aiDistanceLightState);
            return;
        }
        updateItemEnabled(false);
    }

    private void showDialog(String message) {
        final CommonDialog commonDialog = new CommonDialog(this.mContext);
        commonDialog.setMessage(message).setTitle((String) null).setBottomStyle(3).setBtnLeft(this.mContext.getString(R.string.know)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.qs.tiles.carcontrol.Hma.2
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                commonDialog.dismiss();
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                commonDialog.dismiss();
            }
        });
        commonDialog.show();
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
