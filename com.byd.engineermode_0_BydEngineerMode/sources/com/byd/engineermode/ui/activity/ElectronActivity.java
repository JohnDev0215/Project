package com.byd.engineermode.ui.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.tools.AbsAutoDiagnoseListener;
import android.hardware.bydauto.tools.BYDAutoDiagnoseToolDevice;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.DiagnoseApplication;
import com.byd.engineermode.bean.ElectricBean;
import com.byd.engineermode.bean.State;
import com.byd.engineermode.db.TestDatabase;
import com.byd.engineermode.utils.StringArrayToList;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class ElectronActivity extends BaseActivity {
    private AbsAutoDiagnoseListener E;
    BYDAutoDiagnoseToolDevice F;
    private RecyclerView v;
    private List<com.byd.engineermode.bean.a> w;
    private com.byd.engineermode.a.a.b x;
    private Handler y;
    private ExecutorService z = Executors.newSingleThreadExecutor();
    private String A = "byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth";
    private String B = "byd_pad://byd_car_settings:2933/launcher?activity=WiFi";
    private String C = "byd_pad://secretcode:9525/satelliteactivity";
    BluetoothAdapter D = BluetoothAdapter.getDefaultAdapter();

    class a implements com.byd.engineermode.a.b.a<ElectricBean> {
        a() {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(ElectricBean electricBean, int i, int i2) {
            ElectronActivity.this.R(electricBean, i, i2);
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(ElectricBean electricBean, int i, int i2) {
            ElectronActivity.this.W(electricBean, i, i2);
        }
    }

    class b extends AbsAutoDiagnoseListener {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.engineermode.ui.dialog.a.a(ElectronActivity.this).b(R.string.sync_data_mesg);
            }
        }

        b() {
        }

        @Override // android.hardware.bydauto.tools.AbsAutoDiagnoseListener
        public void onSynchronousQNXData(int i) {
            super.onSynchronousQNXData(i);
            com.byd.engineermode.utils.a.a("onSynchronousQNXData state = " + i);
            ElectronActivity.this.runOnUiThread(new a());
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            TestDatabase testDatabaseW = TestDatabase.w(ElectronActivity.this);
            List<com.byd.engineermode.bean.a> listA = testDatabaseW.t().a();
            if (listA != null) {
                str = "list size = " + listA.size();
            } else {
                str = "list is null";
            }
            com.byd.engineermode.utils.a.a(str);
            if (listA == null || listA.isEmpty()) {
                com.byd.engineermode.utils.a.a("init list");
                listA = new ArrayList<>();
                listA.add(com.byd.engineermode.bean.a.c(ElectronActivity.this, R.array.video, 0));
                listA.add(com.byd.engineermode.bean.a.c(ElectronActivity.this, R.array.audio, 1));
                listA.add(com.byd.engineermode.bean.a.c(ElectronActivity.this, R.array.radio, 2));
                listA.add(com.byd.engineermode.bean.a.c(ElectronActivity.this, R.array.communicate, 3));
                Iterator<com.byd.engineermode.bean.a> it = listA.iterator();
                while (it.hasNext()) {
                    testDatabaseW.t().b(testDatabaseW, it.next());
                }
            }
            ElectronActivity.this.w.addAll(listA);
            ElectronActivity.this.y.sendEmptyMessage(0);
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ElectricBean f2323b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2324c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f2325d;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.engineermode.a.a.b bVar = ElectronActivity.this.x;
                d dVar = d.this;
                bVar.w(dVar.f2324c, dVar.f2325d);
            }
        }

        d(ElectricBean electricBean, int i, int i2) {
            this.f2323b = electricBean;
            this.f2324c = i;
            this.f2325d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            TestDatabase.w(ElectronActivity.this).s().b(this.f2323b);
            ElectronActivity.this.y.post(new a());
        }
    }

    class e implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ElectricBean f2327b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2328c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f2329d;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.engineermode.a.a.b bVar = ElectronActivity.this.x;
                e eVar = e.this;
                bVar.w(eVar.f2328c, eVar.f2329d);
            }
        }

        e(ElectricBean electricBean, int i, int i2) {
            this.f2327b = electricBean;
            this.f2328c = i;
            this.f2329d = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            TestDatabase.w(ElectronActivity.this).s().b(this.f2327b);
            ElectronActivity.this.y.post(new a());
        }
    }

    class f implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2331b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2332c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ElectricBean f2333d;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.engineermode.a.a.b bVar = ElectronActivity.this.x;
                f fVar = f.this;
                bVar.w(fVar.f2332c, fVar.f2331b);
            }
        }

        f(int i, int i2, ElectricBean electricBean) {
            this.f2331b = i;
            this.f2332c = i2;
            this.f2333d = electricBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.byd.engineermode.utils.f fVarG = StringArrayToList.h().g(this.f2331b, this.f2332c);
            this.f2333d.p(fVarG.b());
            this.f2333d.l(fVarG.a());
            this.f2333d.q(fVarG.c());
            this.f2333d.r(State.PASSED);
            TestDatabase.w(ElectronActivity.this).s().b(this.f2333d);
            com.byd.engineermode.utils.a.a("updateElectric");
            ElectronActivity.this.y.post(new a());
        }
    }

    private static class g extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<ElectronActivity> f2335a;

        public g(ElectronActivity electronActivity) {
            this.f2335a = new WeakReference<>(electronActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.byd.engineermode.utils.a.a("handleMessage");
            ElectronActivity electronActivity = this.f2335a.get();
            if (electronActivity != null) {
                electronActivity.x.h();
                electronActivity.H();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R(ElectricBean electricBean, int i, int i2) {
        com.byd.engineermode.utils.a.a("onFunctionItemClick position = " + i + ", parentPosition = " + i2 + ", position % 2 = " + (i % 2));
        if (i2 != 2) {
            if (i2 == 3 && i == 8) {
                com.byd.engineermode.utils.a.a("onFunctionItemClick position = 4G/5G 当前信号强度");
                return;
            }
            return;
        }
        if (i == 0) {
            com.byd.engineermode.utils.a.a("onFunctionItemClick position = WIFI");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.B));
            intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
            if (getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
                return;
            }
            startService(intent);
            return;
        }
        if (i == 1) {
            com.byd.engineermode.utils.a.a("onFunctionItemClick position = BT");
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(this.A));
            intent2.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
            if (getPackageManager().queryIntentServices(intent2, 0).isEmpty()) {
                return;
            }
            startService(intent2);
            return;
        }
        if (i == 2) {
            com.byd.engineermode.utils.a.a("onFunctionItemClick position = 收音天线");
            startActivity(new Intent(getApplicationContext(), (Class<?>) RadioTestActivity.class));
        } else {
            if (i != 4) {
                return;
            }
            com.byd.engineermode.utils.a.a("onFunctionItemClick position = GNSS");
            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(this.C));
            if (getPackageManager().queryIntentActivities(intent3, 0).isEmpty()) {
                return;
            }
            startActivity(intent3);
        }
    }

    public static boolean S(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
    }

    private void T() {
        L();
        BYDAutoDiagnoseToolDevice bYDAutoDiagnoseToolDevice = BYDAutoDiagnoseToolDevice.getInstance(DiagnoseApplication.e());
        this.F = bYDAutoDiagnoseToolDevice;
        bYDAutoDiagnoseToolDevice.registerListener(this.E);
        this.F.synchronousQNXData();
        this.z.execute(new c());
    }

    private boolean U() {
        try {
            Method declaredMethod = this.D.getClass().getDeclaredMethod("getConnectionState", null);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(this.D, null)).intValue() == 2;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return false;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return false;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return false;
        }
    }

    private boolean V() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void W(com.byd.engineermode.bean.ElectricBean r7, int r8, int r9) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.engineermode.ui.activity.ElectronActivity.W(com.byd.engineermode.bean.ElectricBean, int, int):void");
    }

    public void OnTestClick(View view) {
        if (view.getId() == R.id.btn_test) {
            BYDAutoDiagnoseToolDevice.getInstance(DiagnoseApplication.e()).synchronousQNXData();
            com.byd.engineermode.ui.dialog.a.a(this).b(R.string.start_sync_data_mesg);
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_electric);
        this.v = (RecyclerView) findViewById(R.id.recyclerView);
        this.w = new ArrayList();
        this.v.setLayoutManager(new LinearLayoutManager(this));
        com.byd.engineermode.a.a.b bVar = new com.byd.engineermode.a.a.b(this, this.w);
        this.x = bVar;
        this.v.setAdapter(bVar);
        this.x.z(new a());
        this.y = new g(this);
        this.E = new b();
        T();
        com.byd.engineermode.utils.b.a().e("ModuleCircuitTest");
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.z.shutdown();
        this.F.unregisterListener(this.E);
    }
}
