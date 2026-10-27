package com.byd.secretcode.activity;

import android.content.Intent;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import defpackage.e7;
import defpackage.mg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LocationActivity extends AppCompatActivity {
    public TextView b;
    public TextView c;
    public TextView d;
    public TextView e;
    public LocationManager f;
    public LocationListener g = new a();
    public int h = 0;
    public int i = 0;
    public int j = 0;
    public List<?> k = new ArrayList();
    public GnssStatus.Callback l = new b();
    public GpsStatus.Listener m = new c();

    public class a implements LocationListener {
        public a() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            LocationActivity.this.b.setText(LocationActivity.this.getString(R.string.location_text_2) + location.getLatitude());
            LocationActivity.this.c.setText(LocationActivity.this.getString(R.string.location_text_3) + location.getLongitude());
            LocationActivity.this.d.setText(LocationActivity.this.getString(R.string.location_text_4) + location.getAltitude());
            LocationActivity.this.e.setText(LocationActivity.this.getString(R.string.location_text_5) + location.getSpeed());
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
    }

    public class b extends GnssStatus.Callback {
        public b() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            super.onSatelliteStatusChanged(gnssStatus);
            LocationActivity.this.h = gnssStatus.getSatelliteCount();
            LocationActivity.this.d.setText(LocationActivity.this.getString(R.string.location_text_6) + LocationActivity.this.h + LocationActivity.this.getString(R.string.location_text_7));
            if (LocationActivity.this.h > 0) {
                for (int i = 0; i < LocationActivity.this.h; i++) {
                    int constellationType = gnssStatus.getConstellationType(i);
                    if (5 == constellationType) {
                        LocationActivity.this.i++;
                    } else if (constellationType != 0 && 1 != constellationType && 3 != constellationType && 4 == constellationType) {
                    }
                    if (gnssStatus.usedInFix(i)) {
                        LocationActivity.this.j++;
                    }
                    gnssStatus.getAzimuthDegrees(i);
                    gnssStatus.getCn0DbHz(i);
                    gnssStatus.getSvid(i);
                    gnssStatus.getElevationDegrees(i);
                    gnssStatus.hasAlmanacData(i);
                    gnssStatus.hasEphemerisData(i);
                }
            }
            LocationActivity.this.d.setText(LocationActivity.this.getString(R.string.location_text_10) + LocationActivity.this.j + LocationActivity.this.getString(R.string.location_text_7));
        }
    }

    public class c implements GpsStatus.Listener {
        public c() {
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            if (i != 4) {
                return;
            }
            GpsStatus gpsStatus = LocationActivity.this.f.getGpsStatus(null);
            int maxSatellites = gpsStatus.getMaxSatellites();
            Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
            int i2 = 0;
            StringBuilder sb = new StringBuilder();
            while (it.hasNext() && i2 <= maxSatellites) {
                i2++;
                float snr = it.next().getSnr();
                sb.append(LocationActivity.this.getString(R.string.location_text_8));
                sb.append(i2);
                sb.append(LocationActivity.this.getString(R.string.location_text_9));
                sb.append("：");
                sb.append(snr);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
        }
    }

    public void f(LocationManager locationManager) {
        if (locationManager.isProviderEnabled("gps")) {
            return;
        }
        mg.b(getApplicationContext(), getString(R.string.location_text_1));
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_location);
        if (e7.a(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.m(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0);
            return;
        }
        this.b = (TextView) findViewById(R.id.tv_1);
        this.c = (TextView) findViewById(R.id.tv_2);
        this.d = (TextView) findViewById(R.id.tv_3);
        this.e = (TextView) findViewById(R.id.tv_4);
        LocationManager locationManager = (LocationManager) getSystemService("location");
        this.f = locationManager;
        locationManager.registerGnssStatusCallback(this.l);
        this.f.requestLocationUpdates("gps", 1000L, 0.0f, this.g);
        this.f.addGpsStatusListener(this.m);
        f(this.f);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f.removeGpsStatusListener(this.m);
    }
}
