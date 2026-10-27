package com.byd.logtools.apkinstall;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import com.byd.logtools.R;
import com.byd.logtools.a;
import com.byd.logtools.apkinstall.c.e;
import com.byd.logtools.apkinstall.c.f;
import com.byd.logtools.apkinstall.ui.ApkInstallActivity;
import com.byd.logtools.apkinstall.ui.PasswordActivity;
import com.byd.logtools.b;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ApkInstallService extends Service implements ServiceConnection, com.byd.logtools.apkinstall.a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f3041b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.logtools.a f3042c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private e f3043d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3044e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private f f3045f;

    class a extends b.a {
        a() {
        }

        @Override // com.byd.logtools.b
        public void d(int i, int i2, String str) {
            BydLogHelper.logD("businessType = " + i + ", code = " + i2 + ", message = " + str);
            ApkInstallService.this.f3044e = false;
            if (i == 1) {
                if (i2 == 1 || i2 == 2) {
                    ApkInstallService.this.f3043d.w(i2 != 1 ? 2 : 1);
                    Intent intent = new Intent(ApkInstallService.this, (Class<?>) ApkInstallActivity.class);
                    intent.putExtra("verifyType", 2);
                    intent.addFlags(268435456);
                    ApkInstallService.this.startActivity(intent);
                }
            }
        }
    }

    public class b extends Binder {
        public b() {
        }
    }

    private void d() {
        BydLogHelper.logD("cancelAuth, mAuthBinder=" + this.f3042c);
        com.byd.logtools.a aVar = this.f3042c;
        if (aVar != null) {
            try {
                aVar.c(1);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void e() {
        if (this.f3043d.n() && h(1)) {
            i();
        } else if (this.f3043d.o() && h(2)) {
            j();
        }
    }

    private void f() {
        BydLogHelper.logD("initData");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.logtools", "com.byd.logtools.auth.AuthService"));
        bindService(intent, this, 1);
        this.f3043d = e.f(this);
        f fVarE = f.e(this);
        this.f3045f = fVarE;
        fVarE.h();
        this.f3045f.d(this);
    }

    private boolean h(int i) {
        List<com.byd.logtools.apkinstall.b.a> listD = this.f3043d.d(i);
        return listD != null && listD.size() > 0;
    }

    private void i() {
        BydLogHelper.logD("requestPasswordAuth");
        Intent intent = new Intent(this, (Class<?>) PasswordActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    @Override // com.byd.logtools.apkinstall.a.b
    public void c() {
        if (this.f3044e) {
            d();
        }
    }

    @Override // com.byd.logtools.apkinstall.a.b
    public void g() {
        e();
    }

    public void j() {
        BydLogHelper.logD("requestQrCodeAuth, mAuthBinder=" + this.f3042c);
        com.byd.logtools.a aVar = this.f3042c;
        if (aVar != null) {
            try {
                aVar.b(1, getString(R.string.install), new a());
                this.f3044e = true;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                this.f3044e = false;
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f3041b;
    }

    @Override // android.app.Service
    public void onCreate() {
        BydLogHelper.logD("onCreate");
        super.onCreate();
        this.f3041b = new b();
        com.byd.logtools.apkinstall.d.a.c(new File(getFilesDir() + File.separator + "Application Installation "));
        f();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.f3045f.j();
        this.f3045f.i(this);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f3042c = a.AbstractBinderC0053a.h(iBinder);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
