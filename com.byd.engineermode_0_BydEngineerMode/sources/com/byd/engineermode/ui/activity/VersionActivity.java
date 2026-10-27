package com.byd.engineermode.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.R;
import com.byd.engineermode.DiagnoseApplication;
import com.byd.engineermode.utils.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class VersionActivity extends BaseActivity {
    private ExecutorService A = Executors.newSingleThreadExecutor();
    private long B;
    ArrayList<Long> C;
    ArrayList<com.byd.engineermode.bean.c> D;
    d.a.a.a.a E;
    private RecyclerView v;
    private com.byd.engineermode.a.a.d w;
    BYDAutoVersionDevice x;
    BYDAutoOtaDevice y;
    private Handler z;

    class a extends LinearLayoutManager {
        a(Context context, int i, boolean z) {
            super(context, i, z);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
        public boolean l() {
            return true;
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VersionActivity.this.Z();
        }
    }

    class c implements com.byd.engineermode.a.b.a<com.byd.engineermode.bean.c> {
        c() {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(com.byd.engineermode.bean.c cVar, int i, int i2) {
        }

        @Override // com.byd.engineermode.a.b.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(com.byd.engineermode.bean.c cVar, int i, int i2) {
            if (i == 0) {
                VersionActivity.this.B = System.currentTimeMillis();
                VersionActivity versionActivity = VersionActivity.this;
                versionActivity.C.add(Long.valueOf(versionActivity.B));
                com.byd.engineermode.utils.a.b("TAG", "mTimeList.size() = " + VersionActivity.this.C.size() + ", time " + (VersionActivity.this.B - VersionActivity.this.C.get(0).longValue()));
                if (VersionActivity.this.C.size() >= 1 && VersionActivity.this.B - VersionActivity.this.C.get(0).longValue() < 5000 && VersionActivity.this.C.size() >= 20) {
                    VersionActivity.this.C.clear();
                    VersionActivity versionActivity2 = VersionActivity.this;
                    versionActivity2.startActivity(new Intent(versionActivity2, (Class<?>) HistoricalRecordActivity.class));
                }
                VersionActivity.this.Y();
            }
        }
    }

    private static class d extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<VersionActivity> f2359a;

        public d(VersionActivity versionActivity) {
            this.f2359a = new WeakReference<>(versionActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.byd.engineermode.utils.a.a("handleMessage");
            VersionActivity versionActivity = this.f2359a.get();
            if (versionActivity != null) {
                versionActivity.w.h();
            }
        }
    }

    private String R() {
        d.a.a.a.a aVar = this.E;
        if (aVar == null) {
            return "N/A";
        }
        try {
            return aVar.e();
        } catch (RemoteException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private String S() {
        d.a.a.a.a aVar = this.E;
        if (aVar == null) {
            return "N/A";
        }
        try {
            return aVar.d();
        } catch (RemoteException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private String T() {
        d.a.a.a.a aVar = this.E;
        if (aVar == null) {
            return "N/A";
        }
        try {
            return aVar.c();
        } catch (RemoteException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private ArrayList<com.byd.engineermode.bean.c> U() {
        ArrayList<com.byd.engineermode.bean.c> arrayList = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.versions);
        arrayList.add(new com.byd.engineermode.bean.c(stringArray[0]));
        com.byd.engineermode.bean.c cVar = new com.byd.engineermode.bean.c(stringArray[1]);
        cVar.d(false);
        arrayList.add(cVar);
        com.byd.engineermode.bean.c cVar2 = new com.byd.engineermode.bean.c(stringArray[2]);
        cVar2.d(false);
        arrayList.add(cVar2);
        com.byd.engineermode.bean.c cVar3 = new com.byd.engineermode.bean.c(stringArray[3]);
        cVar3.d(false);
        arrayList.add(cVar3);
        com.byd.engineermode.bean.c cVar4 = new com.byd.engineermode.bean.c(stringArray[4]);
        cVar4.d(false);
        arrayList.add(cVar4);
        com.byd.engineermode.bean.c cVar5 = new com.byd.engineermode.bean.c(stringArray[5]);
        cVar5.d(false);
        arrayList.add(cVar5);
        com.byd.engineermode.bean.c cVar6 = new com.byd.engineermode.bean.c(stringArray[6]);
        cVar6.d(false);
        arrayList.add(cVar6);
        com.byd.engineermode.bean.c cVar7 = new com.byd.engineermode.bean.c(stringArray[7]);
        cVar7.d(false);
        arrayList.add(cVar7);
        com.byd.engineermode.bean.c cVar8 = new com.byd.engineermode.bean.c(stringArray[8]);
        cVar8.d(false);
        arrayList.add(cVar8);
        com.byd.engineermode.bean.c cVar9 = new com.byd.engineermode.bean.c(stringArray[9]);
        cVar9.d(false);
        arrayList.add(cVar9);
        com.byd.engineermode.bean.c cVar10 = new com.byd.engineermode.bean.c(stringArray[10]);
        cVar10.d(false);
        arrayList.add(cVar10);
        com.byd.engineermode.bean.c cVar11 = new com.byd.engineermode.bean.c(stringArray[11]);
        cVar11.d(false);
        arrayList.add(cVar11);
        com.byd.engineermode.bean.c cVar12 = new com.byd.engineermode.bean.c(stringArray[12]);
        cVar12.d(false);
        arrayList.add(cVar12);
        com.byd.engineermode.bean.c cVar13 = new com.byd.engineermode.bean.c(stringArray[13]);
        cVar13.d(false);
        arrayList.add(cVar13);
        com.byd.engineermode.bean.c cVar14 = new com.byd.engineermode.bean.c(stringArray[14]);
        cVar14.d(false);
        arrayList.add(cVar14);
        return arrayList;
    }

    private String V(Context context) {
        if (this.E == null) {
            return "N/A";
        }
        try {
            com.byd.engineermode.utils.a.j("getNumber simNumber ");
            String strG = this.E.g();
            com.byd.engineermode.utils.a.j("getNumber simNumber = " + strG);
            return strG;
        } catch (RemoteException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private String W() {
        try {
            i.a("TAG", "getQNXBoschVersion System.getProperty = " + SystemProperties.get("persist.vendor.bosch.qnx.ver"));
            return SystemProperties.get("persist.vendor.bosch.qnx.ver");
        } catch (NullPointerException | SecurityException e) {
            i.b("TAG", "getQNXBoschVersion SecurityException | NullPointerException e = " + e.getMessage());
            return "N/A";
        }
    }

    private String X() {
        try {
            i.a("TAG", "getTPFWVersion System.getProperty = " + SystemProperties.get("ro.vendor.bosch.ivi.disp.tp.ver"));
            return SystemProperties.get("ro.vendor.bosch.ivi.disp.tp.ver");
        } catch (NullPointerException | SecurityException e) {
            i.b("TAG", "getTPFWVersion SecurityException | NullPointerException e = " + e.getMessage());
            return "N/A";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y() {
        if (this.C.size() < 1 || this.B - this.C.get(0).longValue() <= 5000) {
            return;
        }
        this.C.remove(0);
        if (this.C.size() > 2) {
            Y();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z() {
        String[] stringArray = getResources().getStringArray(R.array.versions);
        i.a("TAG", "mBYDAutoVersionDevice.getBydSystemVersion() = " + this.x.getBydSystemVersion());
        this.D.set(0, new com.byd.engineermode.bean.c(stringArray[0] + this.x.getBydSystemVersion()));
        this.z.sendEmptyMessage(1001);
        String[] mcuVersion = this.x.getMcuVersion();
        if (mcuVersion.length == 2) {
            com.byd.engineermode.bean.c cVar = new com.byd.engineermode.bean.c(stringArray[1] + mcuVersion[1]);
            cVar.d(false);
            this.D.set(1, cVar);
            this.z.sendEmptyMessage(1001);
            com.byd.engineermode.bean.c cVar2 = new com.byd.engineermode.bean.c(stringArray[2] + mcuVersion[0]);
            cVar2.d(false);
            this.D.set(2, cVar2);
            this.z.sendEmptyMessage(1001);
        } else {
            com.byd.engineermode.bean.c cVar3 = new com.byd.engineermode.bean.c(stringArray[1]);
            cVar3.d(false);
            this.D.set(1, cVar3);
            this.z.sendEmptyMessage(1001);
            com.byd.engineermode.bean.c cVar4 = new com.byd.engineermode.bean.c(stringArray[2]);
            cVar4.d(false);
            this.D.set(2, cVar4);
            this.z.sendEmptyMessage(1001);
        }
        com.byd.engineermode.bean.c cVar5 = new com.byd.engineermode.bean.c(stringArray[3] + W());
        cVar5.d(false);
        this.D.set(3, cVar5);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar6 = new com.byd.engineermode.bean.c(stringArray[4] + this.x.getHwVersion());
        cVar6.d(false);
        this.D.set(4, cVar6);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar7 = new com.byd.engineermode.bean.c(stringArray[5] + this.x.getInstrumentHwVersion());
        cVar7.d(false);
        this.D.set(5, cVar7);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar8 = new com.byd.engineermode.bean.c(stringArray[6] + this.x.getInstrumentSwVersion());
        cVar8.d(false);
        this.D.set(6, cVar8);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar9 = new com.byd.engineermode.bean.c(stringArray[7] + T());
        cVar9.d(false);
        this.D.set(7, cVar9);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar10 = new com.byd.engineermode.bean.c(stringArray[8] + S());
        cVar10.d(false);
        this.D.set(8, cVar10);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar11 = new com.byd.engineermode.bean.c(stringArray[9] + V(this));
        cVar11.d(false);
        this.D.set(9, cVar11);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar12 = new com.byd.engineermode.bean.c(stringArray[10] + this.y.getTBoxSoftVersion());
        cVar12.d(false);
        this.D.set(10, cVar12);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar13 = new com.byd.engineermode.bean.c(stringArray[11] + this.y.getTBoxHardwareVersion());
        cVar13.d(false);
        this.D.set(11, cVar13);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar14 = new com.byd.engineermode.bean.c(stringArray[12] + R());
        cVar14.d(false);
        this.D.set(12, cVar14);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar15 = new com.byd.engineermode.bean.c(stringArray[13] + this.y.getTBoxSerialNumber());
        cVar15.d(false);
        this.D.set(13, cVar15);
        this.z.sendEmptyMessage(1001);
        com.byd.engineermode.bean.c cVar16 = new com.byd.engineermode.bean.c(stringArray[14] + X());
        cVar16.d(false);
        this.D.set(14, cVar16);
        this.z.sendEmptyMessage(1001);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_version);
        this.v = (RecyclerView) findViewById(R.id.recyclerView);
        a aVar = new a(this, 1, false);
        this.x = BYDAutoVersionDevice.getInstance(DiagnoseApplication.e());
        this.y = BYDAutoOtaDevice.getInstance(DiagnoseApplication.e());
        this.v.setLayoutManager(aVar);
        ArrayList<com.byd.engineermode.bean.c> arrayListU = U();
        this.D = arrayListU;
        com.byd.engineermode.a.a.d dVar = new com.byd.engineermode.a.a.d(arrayListU);
        this.w = dVar;
        this.v.setAdapter(dVar);
        this.z = new d(this);
        this.C = new ArrayList<>();
        com.byd.engineermode.ui.dialog.a.a(this).b(R.string.version_loading);
        try {
            this.E = d.a.a.a.a.h();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.A.submit(new b());
        this.w.y(new c());
        com.byd.engineermode.utils.b.a().e("VersionNameShow");
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        RecyclerView recyclerView = this.v;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(null);
            this.v.setAdapter(null);
            this.v = null;
        }
        Handler handler = this.z;
        if (handler != null) {
            handler.removeMessages(1001);
            this.z = null;
        }
    }
}
