package com.byd.trafficmonitor;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import c.a.d.k;
import c.a.d.s.g;
import c.a.d.s.n;
import com.byd.trafficmonitor.model.TrafficInfo;
import com.byd.trafficmonitor.view.FlowDataView;
import com.byd.trafficmonitor.widget.MultiSizeTextView;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static k f4377b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public AppCompatButton f4379d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public MultiSizeTextView f4380e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public MultiSizeTextView f4381f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public MultiSizeTextView f4382g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextView f4383h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public FlowDataView f4384i;
    public d k;
    public long n;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4378c = "traffic_monitor";
    public boolean j = false;
    public ServiceConnection l = new a();
    public final int m = 300;
    public final int o = 6;
    public final long p = 1000;
    public long[] q = new long[6];

    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("traffic_monitor", "onServiceConnected...................");
            MainActivity.f4377b = k.a.K(iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("traffic_monitor", "onServiceDisconnected...........");
            MainActivity.f4377b = null;
        }
    }

    public class b implements Runnable {

        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ ArrayList f4387b;

            public a(ArrayList arrayList) {
                this.f4387b = arrayList;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (MainActivity.this.isDestroyed()) {
                    return;
                }
                c.a.d.m.a.w(this.f4387b).p(MainActivity.this.getSupportFragmentManager(), "FlowWhereDialog");
            }
        }

        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            List<TrafficInfo> listN;
            k kVar = MainActivity.f4377b;
            if (kVar != null) {
                try {
                    listN = kVar.n();
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                    listN = null;
                }
            } else {
                listN = null;
            }
            MainActivity.this.runOnUiThread(new a(MainActivity.this.e(listN)));
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k kVar = MainActivity.f4377b;
            if (kVar != null) {
                try {
                    List<TrafficInfo> listN = kVar.n();
                    for (int i2 = 0; i2 < listN.size(); i2++) {
                        BydLogHelper.logD("traffic_monitor", " in database trafficInfo :" + listN.get(i2));
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MainActivity.this.m();
        }

        public /* synthetic */ d(MainActivity mainActivity, a aVar) {
            this();
        }
    }

    public final void c() {
        Intent intent = new Intent();
        intent.setAction("com.byd.traffic.service.action");
        intent.setPackage("com.byd.uereport");
        bindService(intent, this.l, 1);
    }

    public final boolean d(int i2, long j) {
        long[] jArr = this.q;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.q;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.q[0] < SystemClock.uptimeMillis() - 1000) {
            return false;
        }
        this.q = new long[6];
        return true;
    }

    public final ArrayList<TrafficInfo> e(List<TrafficInfo> list) {
        if (list == null) {
            return null;
        }
        String[] strArr = {"com.android.webview", "com.android.launcher3", "com.ts.firewall.service", "com.ts.appservice.settings", "com.android.networkstack", "com.android.networkstack.tethering", "com.android.networkstack.permissionconfig", "com.android.phone", "com.android.bluetooth", "com.android.hotspot2.osulogin", "com.android.proxyhandler", "com.android.providers.downloads", "com.android.providers.telephony", "com.android.providers.media", "com.byd.hvac", "com.android.car.media", "com.telenav.scoutivi.spotify.byd", "com.fce.onlinemedia.deezer", "com.fce.onlinemedia.tidal", "com.fce.onlinemedia.melon", "com.fce.onlinemedia.tunein", "com.fce.onlinemedia.audiothek", "com.fce.onlinemedia.pocketcasts", "com.fce.onlinemedia.iheartradio", "com.fce.onlinemedia.audible", "com.fce.onlinemedia.libby", "com.fce.onlinemedia.radioline", "com.fce.onlinemedia.audials", "com.fce.onlinemedia.radiofrance", "com.fce.onlinemedia.sportschau", "com.fce.onlinemedia.heute", "com.fce.onlinemedia.musicmate", "com.fce.onlinemedia.bloomberg"};
        PackageManager packageManager = getPackageManager();
        ArrayList<TrafficInfo> arrayList = new ArrayList<>();
        HashMap map = new HashMap();
        for (int i2 = 0; i2 < list.size(); i2++) {
            TrafficInfo trafficInfo = list.get(i2);
            String strO = trafficInfo.o();
            int i3 = 0;
            while (true) {
                if (i3 >= 33) {
                    BydLogHelper.logD("traffic_monitor", "trafficInfo:" + trafficInfo);
                    if (!TextUtils.isEmpty(trafficInfo.k())) {
                        try {
                            ApplicationInfo applicationInfo = packageManager.getPackageInfo(strO, 0).applicationInfo;
                            if (map.containsKey(strO)) {
                                BydLogHelper.logD("traffic_monitor", "add flow trafficInfo:" + trafficInfo);
                                TrafficInfo trafficInfo2 = (TrafficInfo) map.get(strO);
                                trafficInfo2.u(trafficInfo2.n() + trafficInfo.n());
                                trafficInfo2.t(trafficInfo2.m() + trafficInfo.m());
                                if (trafficInfo.p() > trafficInfo2.p()) {
                                    trafficInfo2.w(trafficInfo.p());
                                }
                            } else {
                                map.put(strO, trafficInfo);
                            }
                        } catch (PackageManager.NameNotFoundException e2) {
                            BydLogHelper.logE("traffic_monitor", "add trafficInfo error :" + e2);
                        }
                    }
                } else {
                    if (strArr[i3].equals(strO)) {
                        break;
                    }
                    i3++;
                }
            }
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add((TrafficInfo) map.get((String) it.next()));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public final void f() {
        this.k = new d(this, null);
        b.p.a.a.b(this).c(this.k, new IntentFilter("update_ui"));
    }

    public final void g() {
        this.f4379d = (AppCompatButton) findViewById(R.id.btn_flow_usage);
        this.f4380e = (MultiSizeTextView) findViewById(R.id.current_day_used_flow_tv);
        this.f4381f = (MultiSizeTextView) findViewById(R.id.current_month_used_flow_tv);
        this.f4382g = (MultiSizeTextView) findViewById(R.id.current_total_flow);
        this.f4383h = (TextView) findViewById(R.id.tv_flow_des);
        this.f4384i = (FlowDataView) findViewById(R.id.flow_view);
        this.f4379d.setOnClickListener(this);
        this.f4383h.setOnClickListener(this);
    }

    public final boolean h() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = jCurrentTimeMillis - this.n <= 300;
        this.n = jCurrentTimeMillis;
        return z;
    }

    public final void i() {
        new Thread(new b()).start();
    }

    public final void j() {
        new Thread(new c()).start();
    }

    public final void k(String str, String[] strArr) {
        if (!str.contains(ListUtils.DEFAULT_JOIN_SEPARATOR) || str.length() <= str.indexOf(44) + 1) {
            return;
        }
        String strSubstring = str.substring(str.indexOf(44) + 1);
        if (!strSubstring.contains("-") || str.length() <= str.indexOf(44) + 1) {
            return;
        }
        strArr[0] = strSubstring.substring(0, strSubstring.indexOf(45));
        strArr[1] = strSubstring.substring(strSubstring.indexOf(45) + 1);
    }

    public final void l() {
        TrafficMonitorService.p(this);
    }

    public final void m() {
        String strA = g.a("/vehicleconfig/traffic_data/cus_tel.data");
        if (!TextUtils.isEmpty(strA)) {
            String string = new StringBuilder(strA).reverse().toString();
            if (string.length() >= 3 && string.charAt(2) == '3') {
                this.f4380e.setText("--");
                this.f4381f.setText("--");
                this.f4382g.setText("--");
                String[] strArr = new String[2];
                k(g.a("/vehicleconfig/traffic_data/goods_release_name.data"), strArr);
                this.f4384i.setRatio(1.0f);
                this.f4384i.f(strArr[0], strArr[1]);
                return;
            }
        }
        long jC = c.a.a.g.a.c(g.a("/data/traffic_data/flow_today_used.data"), -1L);
        long jC2 = c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_total_used.data"), -1L);
        long jC3 = c.a.a.g.a.c(g.a("/vehicleconfig/traffic_data/flow_total.data"), -1L);
        if (jC3 != -1) {
            BydLogHelper.logD("traffic_monitor", "tvTotalFlow:" + jC3 + ",totalUsedFlowKb:" + jC2);
            if (jC2 != -1) {
                if (jC2 >= jC3) {
                    jC2 = jC3;
                }
                this.f4381f.setText(n.d(jC2 + ""));
            }
            this.f4382g.setText(n.d(jC3 + ""));
            float f2 = jC3 == 0 ? 0.0f : 1.0f - ((jC2 * 1.0f) / jC3);
            if (f2 <= 0.1f) {
                this.f4384i.h(true);
            } else {
                this.f4384i.h(false);
            }
            BydLogHelper.logD("traffic_monitor", "ratio:" + f2);
            this.f4384i.setRatio(f2);
            this.f4384i.g(getString(R.string.flow_5g_remain), jC3);
        } else if (jC2 != -1) {
            this.f4381f.setText(n.d(jC2 + ""));
        }
        if (jC2 == -1) {
            if (jC != -1) {
                this.f4380e.setText(n.d(jC + ""));
                return;
            }
            return;
        }
        if (jC == -1) {
            this.f4380e.setText(n.d("0"));
            return;
        }
        if (jC >= jC2) {
            jC = jC2;
        }
        this.f4380e.setText(n.d(jC + ""));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_flow_usage) {
            if (h()) {
                return;
            }
            i();
        } else if (id == R.id.tv_flow_des && d(6, 1000L)) {
            j();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        c.a.d.s.c.b(this);
        super.onCreate(bundle);
        Log.i("traffic_monitor", "onCreate...................");
        setContentView(R.layout.activity_main);
        g();
        f();
        l();
        c();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.j = false;
        unbindService(this.l);
        b.p.a.a.b(this).e(this.k);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.i("traffic_monitor", "onResume...................");
        m();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }
}
