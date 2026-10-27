package com.neusoft.na.sens;

import android.content.Context;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import com.neusoft.na.sens.jni.OneCoreSenJni;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class NmeaDataListener {
    private Context mContext = null;
    private LocationManager m_LocationManager = null;
    private OnNmeaMessageListener m_OnNmeaMessageListener = null;
    private GpsStatus.NmeaListener m_NmeaListener = null;
    private ArrayList<String> m_NmeaStringList = new ArrayList<>();
    private final LocationListener m_LocationListener = new LocationListener() { // from class: com.neusoft.na.sens.NmeaDataListener.1
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    };

    public void setNaviNmeaSensor(String str, long j) {
        OneCoreSenJni.getInstance().notifyChangeNMEA(str, j);
        this.m_NmeaStringList.add(str);
        if (this.m_NmeaStringList.size() > 4096) {
            this.m_NmeaStringList.clear();
        }
    }

    public void registerFilter(Context context) {
        this.mContext = context;
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        this.m_LocationManager = locationManager;
        if (locationManager == null) {
            return;
        }
        locationManager.getLastKnownLocation("gps");
        this.m_LocationManager.requestLocationUpdates("gps", TimeUnit.SECONDS.toMillis(1L), 0.0f, this.m_LocationListener);
        OnNmeaMessageListener onNmeaMessageListener = new OnNmeaMessageListener() { // from class: com.neusoft.na.sens.NmeaDataListener.2
            @Override // android.location.OnNmeaMessageListener
            public synchronized void onNmeaMessage(String str, long j) {
                NmeaDataListener.this.setNaviNmeaSensor(str, j);
            }
        };
        this.m_OnNmeaMessageListener = onNmeaMessageListener;
        this.m_LocationManager.addNmeaListener(onNmeaMessageListener);
    }

    /* JADX INFO: renamed from: com.neusoft.na.sens.NmeaDataListener$3, reason: invalid class name */
    class AnonymousClass3 implements GpsStatus.NmeaListener {
        AnonymousClass3() {
        }

        @Override // android.location.GpsStatus.NmeaListener
        public synchronized void onNmeaReceived(long j, String str) {
            NmeaDataListener.this.setNaviNmeaSensor(str, j);
        }
    }

    public void unregisterFilter() {
        this.m_LocationManager.removeNmeaListener(this.m_OnNmeaMessageListener);
    }

    public synchronized String getNmeaString() {
        String str;
        str = "";
        for (int i = 0; i < this.m_NmeaStringList.size(); i++) {
            str = str + this.m_NmeaStringList.get(i);
        }
        this.m_NmeaStringList.clear();
        return str;
    }
}
