package com.byd.ota.view;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.View;
import com.byd.ota.R;
import com.byd.ota.master.VehicleVersionInfo;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class TestActivity extends BaseActivity {

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SystemProperties.set("service.adb.tcp.port", "5555");
            SystemProperties.set("persist.adb.tcp.port", "5555");
            c.a.b.d.e.c("TestActivity", " into button8 ");
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SystemProperties.set("service.adb.tcp.port", "-1");
            SystemProperties.set("persist.adb.tcp.port", "-1");
            c.a.b.d.e.c("TestActivity", " into button9 ");
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(TestActivity.this, OtaMasterActivity.class);
            intent.putExtra("otaModel", true);
            intent.setFlags(268435456);
            TestActivity.this.startActivity(intent);
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(TestActivity.this, OtaMasterActivity.class);
            intent.setFlags(268435456);
            TestActivity.this.startActivity(intent);
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(TestActivity.this, ECUSelectActivity.class);
            intent.setFlags(268435456);
            TestActivity.this.startActivity(intent);
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            File file = new File(c.a.b.b.a.f2321b + "strategy.json_backup");
            if (file.exists()) {
                c.a.b.d.e.c("TestActivity", " delete file " + file.getAbsolutePath() + " ret:" + file.delete());
            }
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SystemProperties.set("persist.sys.otamaster.test.model", VehicleVersionInfo.FORCE_UPGRADE);
            c.a.b.d.e.c("TestActivity", " into test model ");
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SystemProperties.set("persist.sys.otamaster.test.model", "0");
            c.a.b.d.e.c("TestActivity", " into test model ");
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SystemProperties.set("persist.sys.otamaster.test", VehicleVersionInfo.FORCE_UPGRADE);
            c.a.b.d.e.c("TestActivity", " into test ");
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SystemProperties.set("persist.sys.otamaster.test", "0");
            c.a.b.d.e.c("TestActivity", " exit test ");
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.byd.ota", "com.byd.ota.view.OtaTestActivity"));
            intent.setFlags(268435456);
            TestActivity.this.startActivity(intent);
        }
    }

    @Override // com.byd.ota.view.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        findViewById(R.id.button1).setOnClickListener(new c());
        findViewById(R.id.button2).setOnClickListener(new d());
        findViewById(R.id.button3).setOnClickListener(new e());
        findViewById(R.id.button4).setOnClickListener(new f());
        findViewById(R.id.button100).setOnClickListener(new g());
        findViewById(R.id.button101).setOnClickListener(new h());
        findViewById(R.id.button5).setOnClickListener(new i());
        findViewById(R.id.button6).setOnClickListener(new j());
        findViewById(R.id.button7).setOnClickListener(new k());
        findViewById(R.id.button8).setOnClickListener(new a());
        findViewById(R.id.button9).setOnClickListener(new b());
    }
}
