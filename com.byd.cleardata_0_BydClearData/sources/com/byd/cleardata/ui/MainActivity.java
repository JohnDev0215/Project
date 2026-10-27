package com.byd.cleardata.ui;

import a.n.o;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.view.KeyEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.e;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.i;
import com.byd.cleardata.R;
import com.byd.cleardata.ThreadManager;
import com.byd.cleardata.e.h;
import com.byd.cleardata.service.FileScanService;
import com.byd.cleardata.ui.f.j;
import com.byd.cleardata.ui.f.k;
import com.byd.cleardata.ui.f.l;
import com.byd.cleardata.ui.f.m;
import com.byd.cleardata.ui.f.n;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends com.byd.cleardata.ui.b<com.byd.cleardata.f.c.c> {
    private boolean A;
    private n B;
    private m C;
    private Fragment D;
    private l E;
    private int F;
    private i G;
    private ConstraintLayout x;
    private final e y = new e();
    private final e z = new e();
    private int H = 0;
    private final Handler I = new a(Looper.getMainLooper());

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                MainActivity.this.A = false;
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity mainActivity = MainActivity.this;
            mainActivity.x = (ConstraintLayout) mainActivity.findViewById(R.id.main);
            MainActivity.this.y.f(MainActivity.this.x);
            boolean zD = com.byd.cleardata.i.d.d(MainActivity.this);
            MainActivity.this.z.e(MainActivity.this, com.byd.cleardata.i.a.i() ? zD ? R.layout.activity_main_right_rudder : R.layout.activity_main_land_right_rudder : zD ? R.layout.activity_main : R.layout.activity_main_land);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            o.a(MainActivity.this.x);
            MainActivity.this.z.c(MainActivity.this.x);
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            o.a(MainActivity.this.x);
            MainActivity.this.y.c(MainActivity.this.x);
        }
    }

    private void Y(int i, Fragment fragment) {
        this.G.a().i(i, fragment, fragment.getClass().getSimpleName()).e();
    }

    private boolean Z() {
        return androidx.core.content.a.a(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && androidx.core.content.a.a(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private Fragment b0(String str) {
        return this.G.c(str);
    }

    private void c0() {
        Fragment fragmentB0 = b0(j.class.getSimpleName());
        this.D = fragmentB0;
        if (fragmentB0 == null) {
            this.D = b0(k.class.getSimpleName());
        }
        if (this.D == null) {
            j0(com.byd.cleardata.b.h().j());
        }
    }

    private void d0() {
        this.B = (n) b0(n.class.getSimpleName());
        this.C = (m) b0(m.class.getSimpleName());
        this.E = (l) b0(l.class.getSimpleName());
        if (this.B == null) {
            this.B = new n();
            this.C = new m();
            this.E = new l();
            Y(R.id.fragment_menu, this.B);
            Y(R.id.fragment_header, this.C);
            Y(R.id.fragment_footer, this.E);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean f0() {
        d0();
        if (Z()) {
            c0();
            return false;
        }
        g0();
        return false;
    }

    private void g0() {
        androidx.core.app.a.k(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    private void j0(int i) {
        this.D = i != 0 ? i != 1 ? new j() : k.M1(1) : k.M1(0);
        Y(R.id.fragment_content, this.D);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.cleardata.ui.b
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    public com.byd.cleardata.f.c.c Q() {
        return null;
    }

    @Override // androidx.appcompat.app.c, androidx.core.app.d, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            if (!com.byd.cleardata.b.h().t()) {
                com.byd.cleardata.b.h().C();
                return true;
            }
            m mVar = this.C;
            if (mVar != null && mVar.V1()) {
                return true;
            }
            finish();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void h0() {
        com.byd.cleardata.i.c.b("reset");
        this.I.postDelayed(new d(), 100L);
    }

    public void i0() {
        com.byd.cleardata.i.c.b("rotate");
        this.I.postDelayed(new c(), 100L);
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            com.byd.cleardata.i.c.b("uri = " + intent.getData().toString());
        }
    }

    @Override // com.byd.cleardata.ui.b, androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        StringBuilder sb = new StringBuilder();
        sb.append("onConfigurationChanged orientation = ");
        sb.append(configuration.orientation == 1 ? "portrait" : "landscape");
        com.byd.cleardata.i.c.b(sb.toString());
        com.byd.cleardata.i.c.f("config = %s, newConfig = %s", Integer.valueOf(this.F), Integer.valueOf(configuration.orientation));
        int i = this.F;
        int i2 = configuration.orientation;
        if (i == i2) {
            return;
        }
        this.F = i2;
        if (this.H % 2 == 0) {
            i0();
        } else {
            h0();
        }
        this.H++;
    }

    @Override // com.byd.cleardata.ui.b, androidx.appcompat.app.c, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        com.byd.cleardata.i.c.b("onCreate");
        org.greenrobot.eventbus.c.c().o(this);
        this.F = com.byd.cleardata.i.d.c(this);
        boolean zD = com.byd.cleardata.i.d.d(this);
        com.byd.cleardata.i.c.b("isLandscape " + zD);
        setContentView(zD ? com.byd.cleardata.i.a.i() ? R.layout.activity_main_land_right_rudder : R.layout.activity_main_land : com.byd.cleardata.i.a.i() ? R.layout.activity_main_right_rudder : R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null && (i = extras.getInt("root_path", 0)) != 0) {
            com.byd.cleardata.b.h().D(i);
        }
        this.G = y();
        getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.byd.cleardata.ui.a
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return this.f2744a.f0();
            }
        });
        FileScanService.b();
        ThreadManager.h().k(new b());
    }

    @Override // com.byd.cleardata.ui.b, androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        org.greenrobot.eventbus.c.c().q(this);
        com.byd.cleardata.i.c.b("onDestroy");
        super.onDestroy();
    }

    @org.greenrobot.eventbus.m
    public void onMenuEvent(com.byd.cleardata.e.e eVar) {
        com.byd.cleardata.i.c.b("onMenuEvent liangsj ");
        j0(eVar.b());
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onNewIntent(Intent intent) {
        int i;
        org.greenrobot.eventbus.c cVarC;
        Object jVar;
        super.onNewIntent(intent);
        com.byd.cleardata.i.c.b("onNewIntent");
        File file = (File) intent.getSerializableExtra("extra_file");
        if (file != null) {
            com.byd.cleardata.i.c.b("path = " + file.getPath());
            com.byd.cleardata.b.h().A(2);
            com.byd.cleardata.b.h().D(com.byd.cleardata.a.f().h(file.getPath()));
            com.byd.cleardata.b.h().w(com.byd.cleardata.a.e(file.getPath()));
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.i(15));
            cVarC = org.greenrobot.eventbus.c.c();
            jVar = new h(14);
        } else {
            Bundle extras = intent.getExtras();
            if (extras == null || (i = extras.getInt("root_path", 0)) == 0) {
                return;
            }
            com.byd.cleardata.i.c.b("rootIndex = " + i);
            com.byd.cleardata.b.h().D(i);
            com.byd.cleardata.b.h().A(2);
            cVarC = org.greenrobot.eventbus.c.c();
            jVar = new com.byd.cleardata.e.j();
        }
        cVarC.k(jVar);
    }

    @Override // androidx.fragment.app.d, android.app.Activity, androidx.core.app.a.b
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                c0();
                return;
            }
            if (androidx.core.app.a.l(this, "android.permission.READ_EXTERNAL_STORAGE")) {
                return;
            }
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent);
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        com.byd.cleardata.i.c.b("onRestoreInstanceState");
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        com.byd.cleardata.i.c.b("onSaveInstanceState");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }
}
