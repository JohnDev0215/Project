package com.ts.car.timesync;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import com.ts.car.input.ConfigParser;

/* JADX INFO: loaded from: classes.dex */
public class LocationClockSource extends ClockSourceBase {
    private static final int CONNECT_INTERVAL = 1000;
    private static final int MILLISECOND = 1000;
    private static final String TAG = "LocationClockSource";
    private static final int TYPE = 4;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private LocationClockListener mListener;
    private LocationManager mLocationManager;

    public LocationClockSource(Context context) {
        super(context);
        this.mListener = new LocationClockListener();
        LogUtils.debug(TAG, "LocationClockSource start.");
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("LocationClockSource_handlerthread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        getLocationService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLocationService() {
        try {
            LocationManager locationManager = (LocationManager) this.mContext.getApplicationContext().getSystemService("location");
            this.mLocationManager = locationManager;
            locationManager.requestLocationUpdates("gps", 0L, 0.0f, this.mListener);
        } catch (Exception e) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.car.timesync.-$$Lambda$LocationClockSource$b07K3XJt7oMV5vqt9B87eFuI0HU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.getLocationService();
                }
            }, 1000L);
        }
    }

    private class LocationClockListener implements LocationListener {
        private LocationClockListener() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            synchronized (LocationClockSource.class) {
                if (location != null) {
                    if (location.getProvider().equals("gps")) {
                        LogUtils.debug(LocationClockSource.TAG, "onLocationChanged start.");
                        long locationTime = location.getTime();
                        if (0 == locationTime) {
                            LogUtils.warning(LocationClockSource.TAG, "locationTime is illegal 0.");
                            return;
                        }
                        LogUtils.info(LocationClockSource.TAG, "Get Location time:" + locationTime);
                        LocationClockSource.this.updateClockTime(locationTime);
                    }
                }
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    @Override // com.ts.car.timesync.ClockSourceBase
    public void release() {
        LogUtils.debug(TAG, "release()");
        LocationManager locationManager = this.mLocationManager;
        if (locationManager != null) {
            locationManager.removeUpdates(this.mListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateClockTime(long time) {
        LogUtils.debug(TAG, "LocationTimeSource, mPriority=" + this.mPriority + "currentPriority=" + TimeSyncJudgment.getInstance(this.mContext).getCurrentPriority());
        TimeSyncJudgment.getInstance(this.mContext).setTime(time, 4, ConfigParser.EMPTY);
        release();
    }
}
