package com.byd.ota.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import c.a.b.d.e;
import c.a.h.c.a;
import c.a.h.c.b;
import com.byd.ota.R;
import com.byd.ota.view.fragment.OtaAutoVinFragment;
import com.byd.ota.view.fragment.OtaCycleTestFragment;
import com.byd.ota.view.fragment.OtaDownloadTestFragment;
import com.byd.ota.view.fragment.OtaNetTestFragment;
import com.byd.ota.view.fragment.OtaPrintFileFragment;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class OtaToolActivity extends BaseActivity implements AdapterView.OnItemClickListener, OtaNetTestFragment.b, OtaCycleTestFragment.a, OtaDownloadTestFragment.b, OtaAutoVinFragment.a {
    public static final String R3 = OtaToolActivity.class.getSimpleName();
    public boolean S3;
    public ListView U3;
    public ArrayAdapter<String> V3;
    public FragmentManager W3;
    public FragmentTransaction X3;
    public OtaNetTestFragment Y3;
    public OtaCycleTestFragment Z3;
    public OtaDownloadTestFragment a4;
    public OtaPrintFileFragment b4;
    public OtaAutoVinFragment c4;
    public c.a.h.c.b d4;
    public ArrayList<String> T3 = new ArrayList<>();
    public ServiceConnection e4 = new a();
    public c.a.h.c.a f4 = new b();

    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OtaToolActivity.this.d4 = b.a.B(iBinder);
            try {
                OtaToolActivity.this.d4.q(OtaToolActivity.this.f4);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                OtaToolActivity.this.d4.s(OtaToolActivity.this.f4);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            OtaToolActivity.this.d4 = null;
        }
    }

    public class b extends a.AbstractBinderC0060a {
        public b() {
        }

        @Override // c.a.h.c.a
        public void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException {
        }

        @Override // c.a.h.c.a
        public void b(Bundle bundle) throws RemoteException {
        }
    }

    public final void I() {
        Intent intent = new Intent(this, (Class<?>) OtaUiService.class);
        intent.setAction("com.intent.action.OTA_TOOL_TEST");
        bindService(intent, this.e4, 1);
    }

    @Override // com.byd.ota.view.fragment.OtaNetTestFragment.b
    public void d(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("method_name");
            string.hashCode();
            if (string.equals("showHiddenItem") && !this.S3) {
                this.S3 = true;
                this.T3.add("报文测试");
                this.T3.add("刷写测试");
                this.T3.add("文件打印");
                this.V3.add("车架号设置");
                this.V3.notifyDataSetChanged();
            }
        }
    }

    @Override // com.byd.ota.view.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ota_tool);
        this.W3 = getFragmentManager();
        this.T3.add(getString(R.string.ota_tool_net_test));
        this.T3.add(getString(R.string.ota_tool_cycle_test));
        this.T3.add(getString(R.string.ota_tool_download_test));
        this.U3 = (ListView) findViewById(R.id.ota_tool_lv);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.T3);
        this.V3 = arrayAdapter;
        this.U3.setAdapter((ListAdapter) arrayAdapter);
        this.U3.setOnItemClickListener(this);
        I();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unbindService(this.e4);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
        e.c(R3, "position = " + i2 + ", id = " + j);
        switch (i2) {
            case 0:
                this.Y3 = new OtaNetTestFragment();
                FragmentTransaction fragmentTransactionBeginTransaction = this.W3.beginTransaction();
                this.X3 = fragmentTransactionBeginTransaction;
                fragmentTransactionBeginTransaction.replace(R.id.ota_tool_fl, this.Y3);
                this.X3.commit();
                break;
            case 1:
                OtaCycleTestFragment otaCycleTestFragment = new OtaCycleTestFragment();
                this.Z3 = otaCycleTestFragment;
                otaCycleTestFragment.d(this);
                FragmentTransaction fragmentTransactionBeginTransaction2 = this.W3.beginTransaction();
                this.X3 = fragmentTransactionBeginTransaction2;
                fragmentTransactionBeginTransaction2.replace(R.id.ota_tool_fl, this.Z3);
                this.X3.commit();
                break;
            case 2:
                OtaDownloadTestFragment otaDownloadTestFragment = new OtaDownloadTestFragment();
                this.a4 = otaDownloadTestFragment;
                otaDownloadTestFragment.i(this);
                FragmentTransaction fragmentTransactionBeginTransaction3 = this.W3.beginTransaction();
                this.X3 = fragmentTransactionBeginTransaction3;
                fragmentTransactionBeginTransaction3.replace(R.id.ota_tool_fl, this.a4);
                this.X3.commit();
                break;
            case 3:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.byd.ota", "com.byd.ota.view.OtaTestActivity"));
                intent.setFlags(268435456);
                startActivity(intent);
                break;
            case 4:
                Intent intent2 = new Intent();
                intent2.setComponent(new ComponentName("com.byd.ota", "com.byd.ota.view.TestActivity"));
                intent2.setFlags(268435456);
                startActivity(intent2);
                break;
            case 5:
                this.b4 = new OtaPrintFileFragment();
                FragmentTransaction fragmentTransactionBeginTransaction4 = this.W3.beginTransaction();
                this.X3 = fragmentTransactionBeginTransaction4;
                fragmentTransactionBeginTransaction4.replace(R.id.ota_tool_fl, this.b4);
                this.X3.commit();
                break;
            case 6:
                this.c4 = new OtaAutoVinFragment();
                FragmentTransaction fragmentTransactionBeginTransaction5 = this.W3.beginTransaction();
                this.X3 = fragmentTransactionBeginTransaction5;
                fragmentTransactionBeginTransaction5.replace(R.id.ota_tool_fl, this.c4);
                this.X3.commit();
                break;
        }
    }
}
