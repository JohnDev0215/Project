package com.byd.card.utils;

import android.content.Context;
import android.hardware.IBYDAutoEvent;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.util.Log;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class TransportModeManager {
    private static volatile TransportModeManager sInstance;
    private OnTransportModeChangeListener onTransportModeChangeListener;
    AbsBYDAutoVehicleHealthListener listener = new AbsBYDAutoVehicleHealthListener() { // from class: com.byd.card.utils.TransportModeManager.1
        @Override // android.hardware.IBYDAutoListener
        public void onDataChanged(IBYDAutoEvent ibydAutoEvent) {
        }

        @Override // android.hardware.IBYDAutoListener
        public void onDataEventChanged(int i, BYDAutoEventValue bydAutoEventValue) {
        }

        @Override // android.hardware.IBYDAutoListener
        public void onError(int i, String s) {
        }

        @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
        public void onTransportModeChanged(int state) {
            super.onTransportModeChanged(state);
            Log.d("AbsBYDHealthListener", "onTransportModeChanged state = " + state + ", onTransportModeChangeListener = " + TransportModeManager.this.onTransportModeChangeListener);
            if (TransportModeManager.this.onTransportModeChangeListener != null) {
                TransportModeManager.this.onTransportModeChangeListener.onTransportModeChanged(state);
            }
        }
    };
    private final AbsBYDAutoStatisticListener statisticListener = new AbsBYDAutoStatisticListener() { // from class: com.byd.card.utils.TransportModeManager.2
        @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onTotalMileageValueChanged(float value) {
            BydLogHelper.logD("AbsBYDAutoStatisticListener", "onTotalMileageValueChanged value : " + value);
            if (value < 60.0f || TransportModeManager.this.onTransportModeChangeListener == null) {
                return;
            }
            TransportModeManager.this.onTransportModeChangeListener.onTransportModeChanged(-1);
        }
    };

    public interface OnTransportModeChangeListener {
        void onTransportModeChanged(int state);
    }

    private TransportModeManager() {
    }

    public static TransportModeManager getInstance() {
        if (sInstance == null) {
            synchronized (TransportModeManager.class) {
                if (sInstance == null) {
                    sInstance = new TransportModeManager();
                }
            }
        }
        return sInstance;
    }

    public void registerTransportModeListener(Context context, OnTransportModeChangeListener onTransportModeChangeListener) {
        BYDAutoVehicleHealthDevice.getInstance(context).registerListener(this.listener);
        BYDAutoStatisticDevice.getInstance(context).registerListener(this.statisticListener);
        this.onTransportModeChangeListener = onTransportModeChangeListener;
    }

    public int getTransportMode(Context context) {
        return BYDAutoVehicleHealthDevice.getInstance(context).getTransportMode();
    }

    public void unregisterTransportModeListener(Context context) {
        BYDAutoVehicleHealthDevice.getInstance(context).unregisterListener(this.listener);
        BYDAutoStatisticDevice.getInstance(context).unregisterListener(this.statisticListener);
    }
}
