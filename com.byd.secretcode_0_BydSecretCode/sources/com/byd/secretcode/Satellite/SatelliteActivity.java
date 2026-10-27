package com.byd.secretcode.Satellite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.e7;
import defpackage.mg;
import defpackage.sf;
import defpackage.tf;
import defpackage.uf;
import defpackage.vf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SatelliteActivity extends Activity {
    public ListView b;
    public TextView c;
    public sf d;
    public LocationManager e;
    public uf f;
    public List<String> g = new ArrayList();
    public List<tf> h = new ArrayList();
    public List<tf> i = new ArrayList();
    public List<tf> j = new ArrayList();
    public List<tf> k = new ArrayList();
    public List<tf> l = new ArrayList();
    public List<tf> m = new ArrayList();
    public List<tf> n = new ArrayList();
    public List<tf> o = new ArrayList();
    public Handler p = new a();
    public LocationListener q = new c();
    public GpsStatus.Listener r = new d();

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        @SuppressLint({"HandlerLeak"})
        public void handleMessage(Message message) {
            GnssStatus gnssStatus;
            if (message.what != 0 || (gnssStatus = (GnssStatus) message.obj) == null) {
                return;
            }
            SatelliteActivity.this.e(gnssStatus);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SatelliteActivity.this.d.notifyDataSetChanged();
        }
    }

    public class c implements LocationListener {
        public c() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            StringBuilder sb = new StringBuilder();
            SatelliteActivity satelliteActivity = SatelliteActivity.this;
            TextView textView = satelliteActivity.c;
            sb.append(satelliteActivity.getString(R.string.location_text_11));
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(SatelliteActivity.this.getString(R.string.location_text_2));
            sb.append(location.getLongitude());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(SatelliteActivity.this.getString(R.string.location_text_3));
            sb.append(location.getLatitude());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(SatelliteActivity.this.getString(R.string.location_text_4));
            sb.append(location.getAltitude());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(SatelliteActivity.this.getString(R.string.location_text_5));
            sb.append(location.getSpeed());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            textView.setText(sb);
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

    public class d implements GpsStatus.Listener {
        public d() {
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            if (i != 4) {
                return;
            }
            GpsStatus gpsStatus = SatelliteActivity.this.e.getGpsStatus(null);
            int maxSatellites = gpsStatus.getMaxSatellites();
            Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
            int i2 = 0;
            StringBuilder sb = new StringBuilder();
            while (it.hasNext() && i2 <= maxSatellites) {
                i2++;
                float snr = it.next().getSnr();
                sb.append(SatelliteActivity.this.getString(R.string.location_text_8));
                sb.append(i2);
                sb.append(SatelliteActivity.this.getString(R.string.location_text_9));
                sb.append("：");
                sb.append(snr);
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
            BydLogHelper.logD(sb.toString());
        }
    }

    public final void c(List<tf> list, boolean z) {
        if (list.size() > 0) {
            if (z) {
                this.g.add("");
            }
            for (tf tfVar : list) {
                this.g.add(vf.a(tfVar.d, tfVar.b, tfVar.c.floatValue()));
            }
        }
    }

    public final void d() {
        this.g.clear();
        this.h.clear();
        this.i.clear();
        this.j.clear();
        this.k.clear();
        this.l.clear();
        this.m.clear();
        this.n.clear();
        this.o.clear();
    }

    public void e(GnssStatus gnssStatus) {
        d();
        for (int i = 0; i < gnssStatus.getSatelliteCount(); i++) {
            int constellationType = gnssStatus.getConstellationType(i);
            float cn0DbHz = gnssStatus.getCn0DbHz(i);
            int svid = gnssStatus.getSvid(i);
            if (constellationType == 1) {
                this.h.add(new tf("GPS                 ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 3) {
                this.j.add(new tf("GLONASS        ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 5) {
                this.i.add(new tf("BEIDOU            ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 2) {
                this.l.add(new tf("SBAS              ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 4) {
                this.m.add(new tf("QZSS              ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 6) {
                this.n.add(new tf("GALILEO          ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 7) {
                this.o.add(new tf("IRNSS             ", svid, Float.valueOf(cn0DbHz)));
            } else {
                this.k.add(new tf("OTHERS           ", svid, Float.valueOf(cn0DbHz)));
            }
        }
        h();
        f();
        runOnUiThread(new b());
    }

    public final void f() {
        c(this.h, false);
        c(this.i, true);
        c(this.j, true);
        c(this.l, true);
        c(this.m, true);
        c(this.n, true);
        c(this.o, true);
        c(this.k, true);
    }

    public void g(LocationManager locationManager) {
        if (locationManager.isProviderEnabled("gps")) {
            return;
        }
        mg.b(getApplicationContext(), getString(R.string.location_text_1));
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
    }

    public final void h() {
        Collections.sort(this.h);
        Collections.sort(this.i);
        Collections.sort(this.j);
        Collections.sort(this.l);
        Collections.sort(this.m);
        Collections.sort(this.n);
        Collections.sort(this.o);
        Collections.sort(this.k);
    }

    @Override // android.app.Activity
    @SuppressLint({"WrongConstant"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_satellite);
        if (e7.a(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.m(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0);
            return;
        }
        if (e7.a(this, "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS") != 0) {
            ActivityCompat.m(this, new String[]{"android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"}, 0);
            return;
        }
        if (e7.a(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            ActivityCompat.m(this, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, 0);
            return;
        }
        this.d = new sf(this.g, this);
        this.b = (ListView) findViewById(R.id.id_recyclerview);
        this.c = (TextView) findViewById(R.id.tv_location);
        this.b.setAdapter((ListAdapter) this.d);
        this.f = new uf(this.p);
        LocationManager locationManager = (LocationManager) getSystemService("location");
        this.e = locationManager;
        try {
            locationManager.registerGnssStatusCallback(this.f);
        } catch (SecurityException e) {
            mg.b(this, getString(R.string.location_text_12));
            e.printStackTrace();
        }
        this.e.requestLocationUpdates("gps", 1000L, 0.0f, this.q);
        this.e.addGpsStatusListener(this.r);
        g(this.e);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.e.unregisterGnssStatusCallback(this.f);
        this.e.removeGpsStatusListener(this.r);
    }
}
