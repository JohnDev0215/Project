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
public class TSR extends QSTileImpl<QSTile.BooleanState> {
    private final int imgRes;
    private final CarController.Callback mCallback;
    private final CarController mCarController;
    private final String tileLabel;

    @Override // com.android.systemui.qs.tileimpl.QSTile
    public void longClick() {
    }

    public TSR(Context context) {
        super(context);
        BydLogHelper.logD("new TSR");
        String string = this.mContext.getString(R.string.car_title_tsr);
        this.tileLabel = string;
        this.imgRes = QSIconConstants.icon.get(string).intValue();
        CarController carController = (CarController) Dependency.get(CarController.class);
        this.mCarController = carController;
        CarController.Callback callback = new CarController.Callback() { // from class: com.android.systemui.qs.tiles.carcontrol.TSR.1
            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onTrafficSignRecognizeStateChanged(final int signType, final int value) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.TSR.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BydLogHelper.logD("onTrafficSignRecognizeStateChanged", "hasMessage432 : " + TSR.this.mCarController.hasMessage(10));
                        if (TSR.this.mCarController.hasMessage(10) == 1) {
                            BydLogHelper.logD("onTrafficSignRecognizeStateChanged", "value : " + value);
                            TSR.this.updateTrafficSignRecognize(signType, value);
                        } else {
                            BydLogHelper.logD("onTrafficSignRecognizeStateChanged", "0x432 offline");
                        }
                    }
                });
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onMessage5sOnlineStateChanged(int id, int state) {
                BydLogHelper.logD("onMessage5sOnlineStateChanged", "id : " + id + ", state : " + state);
                if (id == 10) {
                    int tsrFault = TSR.this.mCarController.getTsrFault();
                    boolean z = state == 1 && tsrFault != 1;
                    BydLogHelper.logD("onMessage5sOnlineStateChanged hasMessage432 : " + state + " -- tsrFault:" + tsrFault);
                    TSR.this.refreshCarItem(z);
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onHasMessageChanged(int id, int state) {
                BydLogHelper.logD("onHasMessageChanged", "id : " + id + ", state : " + state);
                if (id == 10) {
                    int tsrFault = TSR.this.mCarController.getTsrFault();
                    boolean z = state == 1 && tsrFault != 1;
                    BydLogHelper.logD("onHasMessageChanged hasMessage432 : " + state + " -- tsrFault:" + tsrFault);
                    TSR.this.refreshCarItem(z);
                }
            }

            @Override // com.android.systemui.statusbar.policy.CarController.Callback
            public void onTsrFault(byte fault) {
                int iHasMessage = TSR.this.mCarController.hasMessage(10);
                boolean z = iHasMessage == 1 && fault != 1;
                BydLogHelper.logD("onTsrFault hasMessage432 : " + iHasMessage + " -- fault:" + ((int) fault));
                TSR.this.refreshCarItem(z);
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
        int tsrFault = this.mCarController.getTsrFault();
        BydLogHelper.logD("onBindViewHolder Hma hasMessage432 : " + iHasMessage + " -- tsrFault:" + tsrFault);
        if (iHasMessage != 1 || tsrFault == 1) {
            updateItemEnabled(false);
            return;
        }
        int trafficSignRecognizeState = this.mCarController.getTrafficSignRecognizeState();
        int cntTrafficSignRecognize = this.mCarController.getCntTrafficSignRecognize();
        BydLogHelper.logD("onBindViewHolder", "getTrafficSignRecognizeState : " + trafficSignRecognizeState + " , cntTrafficSignRecognize : " + cntTrafficSignRecognize);
        updateTrafficSignRecognize(cntTrafficSignRecognize, trafficSignRecognizeState);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0040  */
    @Override // com.android.systemui.qs.tileimpl.QSTile
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void click(boolean r5) {
        /*
            r4 = this;
            java.lang.String r5 = "TSR click"
            com.bydux.api.common.tools.log.BydLogHelper.logD(r5)
            com.android.systemui.statusbar.policy.CarController r5 = r4.mCarController
            int r5 = r5.getTrafficSignRecognizeState()
            com.android.systemui.statusbar.policy.CarController r0 = r4.mCarController
            int r0 = r0.getCntTrafficSignRecognize()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "getTrafficSignRecognizeState : "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r2 = " , cntTrafficSignRecognize : "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "handlerClick"
            com.bydux.api.common.tools.log.BydLogHelper.logD(r2, r1)
            r1 = 2
            r2 = 1
            if (r0 != r2) goto L42
            if (r5 < r2) goto L3e
            r0 = 5
            if (r5 > r0) goto L3e
        L3c:
            r5 = r1
            goto L4f
        L3e:
            if (r5 != 0) goto L4f
        L40:
            r5 = r2
            goto L4f
        L42:
            r3 = 3
            if (r0 == r1) goto L47
            if (r0 != r3) goto L4f
        L47:
            if (r5 < r2) goto L4c
            if (r5 > r3) goto L4c
            goto L3c
        L4c:
            if (r5 != 0) goto L4f
            goto L40
        L4f:
            com.android.systemui.statusbar.policy.CarController r0 = r4.mCarController
            if (r5 != r1) goto L54
            r1 = r2
        L54:
            r0.setTrafficSignRecognizeState(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.carcontrol.TSR.click(boolean):void");
    }

    public void updateTrafficSignRecognize(int signType, int value) {
        BydLogHelper.logD("updateTrafficSignRecognize", "signType : " + signType + " , value : " + value);
        int iHasMessage = this.mCarController.hasMessage(10);
        int tsrFault = this.mCarController.getTsrFault();
        BydLogHelper.logD("updateAiDistanceLightState hasMessage432 : " + iHasMessage + " -- tsrFault:" + tsrFault);
        if (iHasMessage != 1 || tsrFault == 1) {
            return;
        }
        if (signType == 1) {
            if (value >= 1 && value <= 5) {
                updateItemSelected(true);
                return;
            } else if (value == 6) {
                updateItemEnabled(false);
                return;
            } else {
                updateItemSelected(false);
                return;
            }
        }
        if (signType == 2 || signType == 3) {
            if (value >= 1 && value <= 3) {
                updateItemSelected(true);
            } else if (value == 4) {
                updateItemEnabled(false);
            } else {
                updateItemSelected(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarItem(final boolean enable) {
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.qs.tiles.carcontrol.-$$Lambda$TSR$DGtxt4ZMHAgp5LgZWEH4D30-RYc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshCarItem$0$TSR(enable);
            }
        });
    }

    public /* synthetic */ void lambda$refreshCarItem$0$TSR(boolean z) {
        if (z) {
            int trafficSignRecognizeState = this.mCarController.getTrafficSignRecognizeState();
            int cntTrafficSignRecognize = this.mCarController.getCntTrafficSignRecognize();
            BydLogHelper.logD("refreshCarItem tsrFault : " + this.mCarController.getTsrFault());
            updateTrafficSignRecognize(cntTrafficSignRecognize, trafficSignRecognizeState);
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
