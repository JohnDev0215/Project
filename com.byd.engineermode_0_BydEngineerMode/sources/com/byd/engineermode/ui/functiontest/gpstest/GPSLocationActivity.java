package com.byd.engineermode.ui.functiontest.gpstest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import com.byd.engineermode.ui.functiontest.logs.GPSLogLevelConfigActivity;
import com.byd.engineermode.ui.functiontest.logs.SaveLogPasswordActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GPSLocationActivity extends BaseActivity {
    private LocationManager A;
    private e B;
    f L;
    private long M;
    private long N;
    ArrayList<Long> O;
    ArrayList<Long> P;
    ListView v;
    TextView w;
    TextView x;
    TextView y;
    private com.byd.engineermode.ui.functiontest.gpstest.a z;
    private List<String> C = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> D = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> E = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> F = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> G = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> H = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> I = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> J = new ArrayList();
    private List<com.byd.engineermode.ui.functiontest.gpstest.b> K = new ArrayList();
    LocationListener Q = new d();

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GPSLocationActivity.this.M = System.currentTimeMillis();
            GPSLocationActivity gPSLocationActivity = GPSLocationActivity.this;
            gPSLocationActivity.O.add(Long.valueOf(gPSLocationActivity.M));
            com.byd.engineermode.utils.a.b("TAG", "mTimeList.size() = " + GPSLocationActivity.this.O.size() + ", time " + (GPSLocationActivity.this.M - GPSLocationActivity.this.O.get(0).longValue()));
            if (GPSLocationActivity.this.O.size() >= 1 && GPSLocationActivity.this.M - GPSLocationActivity.this.O.get(0).longValue() < 5000 && GPSLocationActivity.this.O.size() >= 20) {
                GPSLocationActivity.this.O.clear();
                GPSLocationActivity gPSLocationActivity2 = GPSLocationActivity.this;
                gPSLocationActivity2.startActivity(new Intent(gPSLocationActivity2, (Class<?>) GPSLogLevelConfigActivity.class));
            }
            GPSLocationActivity.this.b0();
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GPSLocationActivity.this.N = System.currentTimeMillis();
            GPSLocationActivity gPSLocationActivity = GPSLocationActivity.this;
            gPSLocationActivity.P.add(Long.valueOf(gPSLocationActivity.N));
            com.byd.engineermode.utils.a.b("TAG", "saveLogTimeList.size() = " + GPSLocationActivity.this.P.size() + ", time " + (GPSLocationActivity.this.N - GPSLocationActivity.this.P.get(0).longValue()));
            if (GPSLocationActivity.this.P.size() >= 1 && GPSLocationActivity.this.N - GPSLocationActivity.this.P.get(0).longValue() < 5000 && GPSLocationActivity.this.P.size() >= 20) {
                GPSLocationActivity.this.P.clear();
                GPSLocationActivity gPSLocationActivity2 = GPSLocationActivity.this;
                gPSLocationActivity2.startActivity(new Intent(gPSLocationActivity2, (Class<?>) SaveLogPasswordActivity.class));
            }
            GPSLocationActivity.this.c0();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GPSLocationActivity.this.z.notifyDataSetChanged();
        }
    }

    class d implements LocationListener {
        d() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            StringBuilder sb = new StringBuilder();
            GPSLocationActivity gPSLocationActivity = GPSLocationActivity.this;
            TextView textView = gPSLocationActivity.w;
            sb.append(gPSLocationActivity.getResources().getString(R.string.gps_current_location));
            sb.append("\n");
            sb.append(GPSLocationActivity.this.getResources().getString(R.string.gps_longitude));
            sb.append(location.getLongitude());
            sb.append("\n");
            sb.append(GPSLocationActivity.this.getResources().getString(R.string.gps_latitude));
            sb.append(location.getLatitude());
            sb.append("\n");
            sb.append(GPSLocationActivity.this.getResources().getString(R.string.gps_altitude));
            sb.append(location.getAltitude());
            sb.append("\n");
            sb.append(GPSLocationActivity.this.getResources().getString(R.string.gps_speed));
            sb.append(location.getSpeed());
            sb.append("\n");
            sb.append(GPSLocationActivity.this.getResources().getString(R.string.gps_bearing));
            sb.append(location.getBearing());
            sb.append("\n");
            sb.append(GPSLocationActivity.this.getResources().getString(R.string.gps_location_mode));
            sb.append(GPSLocationActivity.this.X());
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

    private class e extends GnssStatus.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Handler f2452a;

        public e(Handler handler) {
            this.f2452a = handler;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i) {
            super.onFirstFix(i);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            Message.obtain(this.f2452a, 0, gnssStatus).sendToTarget();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            super.onStarted();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            super.onStopped();
        }
    }

    private static class f extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<GPSLocationActivity> f2454a;

        public f(GPSLocationActivity gPSLocationActivity) {
            this.f2454a = new WeakReference<>(gPSLocationActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            GnssStatus gnssStatus;
            GPSLocationActivity gPSLocationActivity = this.f2454a.get();
            if (gPSLocationActivity == null || message.what != 0 || (gnssStatus = (GnssStatus) message.obj) == null) {
                return;
            }
            Log.d("TAG", "update Gnss");
            gPSLocationActivity.V(gnssStatus);
        }
    }

    private void T(List<com.byd.engineermode.ui.functiontest.gpstest.b> list, boolean z) {
        Log.d("TAG", "list.size() / " + list.size());
        if (list.size() > 0) {
            if (z) {
                this.C.add("");
            }
            for (com.byd.engineermode.ui.functiontest.gpstest.b bVar : list) {
                Log.d("TAG", "addAllTypeSvs / " + bVar.f2461d);
                this.C.add(com.byd.engineermode.ui.functiontest.gpstest.c.a(bVar.f2461d, bVar.f2459b, bVar.f2460c.floatValue()));
            }
        }
    }

    private void U() {
        this.C.clear();
        this.D.clear();
        this.E.clear();
        this.F.clear();
        this.G.clear();
        this.H.clear();
        this.I.clear();
        this.J.clear();
        this.K.clear();
    }

    private void W() {
        T(this.D, false);
        T(this.E, true);
        T(this.F, true);
        T(this.H, true);
        T(this.I, true);
        T(this.J, true);
        T(this.K, true);
        T(this.G, true);
    }

    private boolean Z(String str) {
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (Character.isDigit(str.charAt(length)));
        return false;
    }

    private void a0() {
        Collections.sort(this.D);
        Collections.sort(this.E);
        Collections.sort(this.F);
        Collections.sort(this.H);
        Collections.sort(this.I);
        Collections.sort(this.J);
        Collections.sort(this.K);
        Collections.sort(this.G);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b0() {
        if (this.O.size() < 1 || this.M - this.O.get(0).longValue() <= 5000) {
            return;
        }
        this.O.remove(0);
        if (this.O.size() > 2) {
            b0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0() {
        if (this.P.size() < 1 || this.N - this.P.get(0).longValue() <= 5000) {
            return;
        }
        this.P.remove(0);
        if (this.P.size() > 2) {
            c0();
        }
    }

    public void OnButtonClick(View view) {
        if (view.getId() == R.id.test_pass_btn) {
            J();
            return;
        }
        if (view.getId() == R.id.test_retry_btn) {
            return;
        }
        if (view.getId() == R.id.test_fail_btn) {
            I();
        } else if (view.getId() == R.id.gps_open) {
            SystemProperties.set("persist.vendor.byd.gps.operation", "1");
        } else if (view.getId() == R.id.gps_close) {
            SystemProperties.set("persist.vendor.byd.gps.operation", "0");
        }
    }

    public void V(GnssStatus gnssStatus) {
        U();
        for (int i = 0; i < gnssStatus.getSatelliteCount(); i++) {
            int constellationType = gnssStatus.getConstellationType(i);
            float cn0DbHz = gnssStatus.getCn0DbHz(i);
            int svid = gnssStatus.getSvid(i);
            Log.d("dealData", ", constellationType = " + constellationType + ", cn0DbHz = " + cn0DbHz + ", svid = " + gnssStatus.getSvid(i) + ", i = " + i);
            if ("-1.0".equals(Float.valueOf(cn0DbHz).toString())) {
                Log.e("dealData", "constellationType = " + constellationType + ", cn0DbHz = -1.0  ");
            } else if (constellationType == 1) {
                this.D.add(new com.byd.engineermode.ui.functiontest.gpstest.b("GPS                 ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 3) {
                this.F.add(new com.byd.engineermode.ui.functiontest.gpstest.b("GLONASS        ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 5) {
                this.E.add(new com.byd.engineermode.ui.functiontest.gpstest.b("BEIDOU            ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 2) {
                this.H.add(new com.byd.engineermode.ui.functiontest.gpstest.b("SBAS              ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 4) {
                this.I.add(new com.byd.engineermode.ui.functiontest.gpstest.b("QZSS              ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 6) {
                this.J.add(new com.byd.engineermode.ui.functiontest.gpstest.b("GALILEO          ", svid, Float.valueOf(cn0DbHz)));
            } else if (constellationType == 7) {
                this.K.add(new com.byd.engineermode.ui.functiontest.gpstest.b("IRNSS             ", svid, Float.valueOf(cn0DbHz)));
            } else {
                this.G.add(new com.byd.engineermode.ui.functiontest.gpstest.b("OTHERS           ", svid, Float.valueOf(cn0DbHz)));
            }
        }
        a0();
        W();
        runOnUiThread(new c());
    }

    public String X() {
        List<String> allProviders = this.A.getAllProviders();
        String str = null;
        for (int i = 0; i < allProviders.size(); i++) {
            Log.d("TAG", "allProviders mList.get(i) = " + allProviders.get(i) + ", isProviderEnabled= " + this.A.isProviderEnabled(allProviders.get(i)));
            if (this.A.isProviderEnabled(allProviders.get(i))) {
                str = str == null ? allProviders.get(i) : str + ", " + allProviders.get(i);
            }
        }
        return str;
    }

    void Y(LocationManager locationManager) {
        if (locationManager.isProviderEnabled("gps")) {
            return;
        }
        com.byd.engineermode.ui.dialog.a.a(getApplicationContext()).b(R.string.enable_gps_navigation);
        startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 0);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"WrongConstant", "SetTextI18n"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gps_location_test);
        if (androidx.core.content.a.a(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            androidx.core.app.a.k(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0);
            return;
        }
        if (androidx.core.content.a.a(this, "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS") != 0) {
            androidx.core.app.a.k(this, new String[]{"android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"}, 0);
            return;
        }
        if (androidx.core.content.a.a(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            androidx.core.app.a.k(this, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, 0);
            return;
        }
        this.z = new com.byd.engineermode.ui.functiontest.gpstest.a(this.C, this);
        this.L = new f(this);
        this.v = (ListView) findViewById(R.id.id_recyclerview);
        this.w = (TextView) findViewById(R.id.tv_location);
        this.y = (TextView) findViewById(R.id.tv_location_title);
        this.x = (TextView) findViewById(R.id.gps_state);
        this.v.setAdapter((ListAdapter) this.z);
        this.B = new e(this.L);
        LocationManager locationManager = (LocationManager) getSystemService("location");
        this.A = locationManager;
        try {
            locationManager.registerGnssStatusCallback(this.B);
        } catch (SecurityException e2) {
            com.byd.engineermode.ui.dialog.a.a(this).b(R.string.grant_permissions_for_location);
            e2.printStackTrace();
        }
        this.A.requestLocationUpdates("gps", 1000L, 0.0f, this.Q);
        Y(this.A);
        String str = SystemProperties.get("persist.vendor.byd.gnss.mode", "0");
        if (str == null || !Z(str)) {
            this.x.setText(getString(R.string.gps_status) + "GPS");
        } else {
            TextView textView = this.x;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.gps_status));
            sb.append(Integer.parseInt(str) == 1 ? getString(R.string.gps_inertial_navigation) : "GPS");
            textView.setText(sb.toString());
        }
        this.O = new ArrayList<>();
        this.w.setOnClickListener(new a());
        this.P = new ArrayList<>();
        this.y.setOnClickListener(new b());
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LocationManager locationManager = this.A;
        if (locationManager != null) {
            locationManager.unregisterGnssStatusCallback(this.B);
            this.A.removeUpdates(this.Q);
            this.B = null;
            this.Q = null;
        }
    }
}
