package com.ecarx.xui.adaptapi.vehicle.newenergy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy;
import com.neusoft.optimus.utility.offlineconfig.OfflineConfig;

/* JADX INFO: loaded from: classes2.dex */
public class NewEnergy implements INewEnergy {
    public static final String RESERVATIONACTION = "com.neusoft.shockwave.reservationChargeForAccOff";
    public static final String RESERVATIONTIME = "startTime";
    public static final String TAG = "NewEnergy";
    private INewEnergy.IBatteryStatusObserver mBatteryObserver;
    private Context mContext;
    private final String mVehicleModel = "bibibibbi";
    private BroadcastReceiver BatteryReceiver = new BroadcastReceiver() { // from class: com.ecarx.xui.adaptapi.vehicle.newenergy.NewEnergy.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals(NewEnergy.RESERVATIONACTION) || NewEnergy.this.mBatteryObserver == null) {
                return;
            }
            Log.d(NewEnergy.TAG, "BatteryObserver.onBatteryReminder Battery_REMIND_PREPARE_CHARGE");
            NewEnergy.this.mBatteryObserver.onBatteryReminder(1);
        }
    };

    @Override // com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy
    public ICharge getChargeManager() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy
    public int getCurrentBatteryStatus() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy
    public IEpt getIEpt() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy
    public IPHEV getPHEV() {
        return null;
    }

    public NewEnergy(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy
    public int getNewEnergyType() {
        if (1 != OfflineConfig.getVehicleOfflineConfig(this.mContext, 284)) {
            return 2147483646;
        }
        Log.d(TAG, "NEV_TYPE_PHEV");
        return 2;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy
    public boolean attachBatteryStatusObserver(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
        if (iBatteryStatusObserver != null) {
            Log.d(TAG, "attachBatteryStatusObserver");
            this.mBatteryObserver = iBatteryStatusObserver;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(RESERVATIONACTION);
            this.mContext.registerReceiver(this.BatteryReceiver, intentFilter);
            return true;
        }
        Log.d(TAG, "attachBatteryStatusObserver observer = null");
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.newenergy.INewEnergy
    public void detachBatteryStatusObserver(INewEnergy.IBatteryStatusObserver iBatteryStatusObserver) {
        Log.d(TAG, "detachBatteryStatusObserver");
        this.mBatteryObserver = null;
        this.mContext.unregisterReceiver(this.BatteryReceiver);
    }
}
