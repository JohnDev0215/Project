package com.byd.smarttravel;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import c.a.b.a;
import c.a.b.b;
import c.a.d.h;
import c.a.d.i;
import c.a.d.j;
import c.a.d.k;
import com.byd.widget.PlanChargeView;
import com.byd.widget.PlanUseCarView;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydActivity;
import com.bydux.api.common.views.window.ToastUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends BydActivity implements View.OnClickListener, c.a.e.b, c.a.a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f1952b = true;
    public transient PlanChargeView g;
    public transient PlanUseCarView h;
    public transient f j;
    public transient ViewGroup k;
    public transient TextView l;
    public transient c.a.b.b m;
    public transient g n;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient boolean f1953c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient boolean f1954d = true;
    public transient boolean e = true;
    public transient boolean f = false;
    public transient boolean i = true;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            MainActivity.this.k.removeAllViews();
            if (MainActivity.this.e) {
                if (MainActivity.this.g == null) {
                    MainActivity mainActivity = MainActivity.this;
                    MainActivity mainActivity2 = MainActivity.this;
                    mainActivity.g = new PlanChargeView(mainActivity2, mainActivity2);
                }
                MainActivity.this.g.h();
                MainActivity.this.k.addView(MainActivity.this.g);
            } else {
                if (MainActivity.this.h == null) {
                    MainActivity mainActivity3 = MainActivity.this;
                    MainActivity mainActivity4 = MainActivity.this;
                    mainActivity3.h = new PlanUseCarView(mainActivity4, mainActivity4);
                }
                MainActivity.this.h.m();
                MainActivity.this.k.addView(MainActivity.this.h);
            }
            MainActivity.this.l.setText(MainActivity.this.getResources().getString(MainActivity.this.e ? R.string.mode_use_car : R.string.mode_time));
            MainActivity.this.v();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            if (MainActivity.this.h != null) {
                MainActivity.this.h.l();
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (MainActivity.this.m != null) {
                    PlanBean planBeanA = h.a(MainActivity.this);
                    planBeanA.f1963b = "TYPE_ALARM_MODE";
                    MainActivity.this.m.e(planBeanA);
                }
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
            }
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f1958b;

        public d(String str) {
            this.f1958b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (MainActivity.this.m != null) {
                    PlanBean planBeanA = h.a(MainActivity.this);
                    planBeanA.f1963b = this.f1958b;
                    MainActivity.this.m.e(planBeanA);
                }
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
            }
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public static class f extends a.AbstractBinderC0044a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public transient WeakReference<MainActivity> f1961a;

        public f(MainActivity mainActivity) {
            this.f1961a = new WeakReference<>(mainActivity);
        }

        @Override // c.a.b.a
        public void b(int i, PlanBean planBean) throws Throwable {
            try {
                if (this.f1961a.get() == null) {
                    return;
                }
                h.b(this.f1961a.get(), planBean);
                if (i == 1) {
                    MainActivity.u(this.f1961a.get());
                } else {
                    MainActivity.t(this.f1961a.get());
                }
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
                k.b("[ICallbackInterface]----" + e.getMessage());
            }
        }
    }

    public class g implements ServiceConnection {
        public g() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws Throwable {
            try {
                MainActivity.this.m = b.a.f(iBinder);
                if (MainActivity.this.m != null) {
                    MainActivity.this.m.a(MainActivity.this.j);
                    h.b(MainActivity.this.getApplicationContext(), MainActivity.this.m.d());
                    MainActivity.t(MainActivity.this);
                }
            } catch (Exception e) {
                k.b(e.getMessage());
                Log.e("smart_charge", e.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                if (MainActivity.this.m != null) {
                    MainActivity.this.m.c(MainActivity.this.j);
                }
                MainActivity.this.j = null;
                MainActivity.this.m = null;
            } catch (Exception e) {
                Log.e("smart_charge", e.getMessage());
            }
        }

        public /* synthetic */ g(MainActivity mainActivity, a aVar) {
            this();
        }
    }

    public static void t(MainActivity mainActivity) throws Throwable {
        boolean zC = i.c(mainActivity, "KEY_MODE", true);
        mainActivity.e = zC;
        if (!zC && !mainActivity.f) {
            k.b("[界面广播]----[预约用车不支持]");
            mainActivity.e = true;
            i.g(mainActivity, "KEY_MODE", true);
        }
        new Handler(Looper.getMainLooper()).post(mainActivity.new a());
    }

    public static void u(MainActivity mainActivity) {
        new Handler(Looper.getMainLooper()).post(mainActivity.new b());
    }

    @Override // c.a.a.a
    public int a() {
        return R.layout.activity_charge_main_byd;
    }

    @Override // c.a.e.b
    public void b(int i, String str) {
        if (i == 0) {
            new Thread(new d(str)).start();
        } else {
            if (i != 1) {
                return;
            }
            w();
        }
    }

    @Override // c.a.a.a
    public int d() {
        return R.layout.activity_charge_main;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) throws Throwable {
        if (c.a.d.b.a()) {
            return;
        }
        int id = view.getId();
        if (id != R.id.tv_mode) {
            if (id != R.id.tv_setting) {
                return;
            }
            startActivity(new Intent(this, (Class<?>) SetInfoActivity.class));
            return;
        }
        boolean z = !this.e;
        this.e = z;
        i.g(this, "KEY_MODE", z);
        this.l.setText(getResources().getString(this.e ? R.string.mode_use_car : R.string.mode_time));
        this.k.removeAllViews();
        if (this.e) {
            if (this.g == null) {
                this.g = new PlanChargeView(this, this);
            }
            this.g.h();
            this.k.addView(this.g);
            if (i.b(this, "KEY_USECAR_SWITCH")) {
                ToastUtils.show(this, R.string.tips_to_plan);
            }
        } else {
            if (this.h == null) {
                this.h = new PlanUseCarView(this, this);
            }
            this.h.m();
            this.k.addView(this.h);
            if (i.b(this, "KEY_CHARGE_SWITCH")) {
                ToastUtils.show(this, R.string.tips_to_use_car);
            }
        }
        new Thread(new c()).start();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Throwable {
        c.a.a.b.f(this);
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
        c.a.d.d.d(this);
        setContentView(c.a.a.b.b(this, this));
        this.j = new f(this);
        this.k = (ViewGroup) findViewById(R.id.fr_content);
        this.l = (TextView) findViewById(R.id.tv_mode);
        boolean zC = c.a.d.d.c(this);
        this.f1954d = zC;
        if (zC) {
            boolean z = BYDAutoBodyworkDevice.getInstance(this).hasMessage(151) == 1;
            int carPlanSupport = BYDAutoStudy.getInstance(this).getCarPlanSupport();
            this.f = (carPlanSupport == 0) && z;
            k.b("[预约用车配置]----纯电：" + carPlanSupport);
        } else {
            int hevCarPlanSupport = BYDAutoStudy.getInstance(this).getHevCarPlanSupport();
            this.f = hevCarPlanSupport == 2;
            k.b("[预约用车配置]----混动：" + hevCarPlanSupport);
        }
        k.b("[预约用车配置]----" + this.f);
        this.l.setVisibility(this.f ? 0 : 8);
        boolean zC2 = i.c(this, "KEY_MODE", true);
        this.e = zC2;
        if (!zC2 && !this.f) {
            this.e = true;
            i.g(this, "KEY_MODE", true);
        }
        this.l.setText(getResources().getString(this.e ? R.string.mode_use_car : R.string.mode_time));
        findViewById(R.id.tv_setting).setOnClickListener(this);
        this.l.setOnClickListener(this);
        s();
        boolean zEquals = "1".equals(j.a("persist.sys.vehicle_privacy_status", "1"));
        this.i = zEquals;
        if (!zEquals && f1952b && this.f1954d) {
            x();
        }
        f1952b = false;
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        c.a.d.f.d();
        PlanChargeView planChargeView = this.g;
        if (planChargeView != null) {
            planChargeView.m();
            this.g = null;
        }
        PlanUseCarView planUseCarView = this.h;
        if (planUseCarView != null) {
            planUseCarView.u();
            this.h = null;
        }
        this.k.removeAllViews();
        y();
        try {
            c.a.b.b bVar = this.m;
            if (bVar != null) {
                bVar.c(null);
            }
            this.j = null;
            this.m = null;
        } catch (Exception e2) {
            Log.e("smart_charge", e2.getMessage());
        }
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        c.a.d.f.s();
    }

    public final void s() throws Throwable {
        try {
            this.n = new g(this, null);
            Intent intent = new Intent();
            intent.setAction("com.byd.charge.service.action");
            intent.setPackage("com.byd.uereport");
            if (bindService(intent, this.n, 1)) {
                return;
            }
            k.b("[服务异常]----aidl绑定失败");
            t(this);
        } catch (Exception e2) {
            Log.e("smart_charge", e2.getMessage());
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v() {
        /*
            r9 = this;
            boolean r0 = r9.f1953c
            if (r0 != 0) goto L5
            return
        L5:
            java.util.List<java.lang.String> r0 = c.a.d.f.f1597b     // Catch: java.lang.Exception -> L8a
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L8a
        Lb:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Exception -> L8a
            r2 = 0
            if (r1 == 0) goto L82
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Exception -> L8a
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L8a
            r3 = -1
            int r4 = r1.hashCode()     // Catch: java.lang.Exception -> L8a
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            switch(r4) {
                case -1047794334: goto L4d;
                case -864939319: goto L43;
                case -314498168: goto L39;
                case 750509235: goto L2f;
                case 2102327979: goto L25;
                default: goto L24;
            }     // Catch: java.lang.Exception -> L8a
        L24:
            goto L56
        L25:
            java.lang.String r2 = "plan_more"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto L56
            r2 = r8
            goto L57
        L2f:
            java.lang.String r2 = "plan_price"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto L56
            r2 = r7
            goto L57
        L39:
            java.lang.String r2 = "privacy"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto L56
            r2 = r5
            goto L57
        L43:
            java.lang.String r2 = "plan_usecar_time"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto L56
            r2 = r6
            goto L57
        L4d:
            java.lang.String r4 = "plan_charge_time"
            boolean r1 = r1.equals(r4)     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto L56
            goto L57
        L56:
            r2 = r3
        L57:
            if (r2 == 0) goto L7a
            if (r2 == r8) goto L76
            if (r2 == r7) goto L6e
            if (r2 == r6) goto L66
            if (r2 == r5) goto L62
            goto Lb
        L62:
            r9.x()     // Catch: java.lang.Exception -> L8a
            goto Lb
        L66:
            com.byd.widget.PlanUseCarView r1 = r9.h     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto Lb
            r1.w(r8)     // Catch: java.lang.Exception -> L8a
            goto Lb
        L6e:
            com.byd.widget.PlanUseCarView r1 = r9.h     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto Lb
            r1.v(r8)     // Catch: java.lang.Exception -> L8a
            goto Lb
        L76:
            r9.w()     // Catch: java.lang.Exception -> L8a
            goto Lb
        L7a:
            com.byd.widget.PlanChargeView r1 = r9.g     // Catch: java.lang.Exception -> L8a
            if (r1 == 0) goto Lb
            r1.n(r8)     // Catch: java.lang.Exception -> L8a
            goto Lb
        L82:
            java.util.List<java.lang.String> r0 = c.a.d.f.f1597b     // Catch: java.lang.Exception -> L8a
            r0.clear()     // Catch: java.lang.Exception -> L8a
            r9.f1953c = r2     // Catch: java.lang.Exception -> L8a
            goto L94
        L8a:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            java.lang.String r1 = "smart_charge"
            android.util.Log.e(r1, r0)
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.smarttravel.MainActivity.v():void");
    }

    public void w() {
        c.a.d.f.g(this, "plan_more", getResources().getString(R.string.function_declaration), getResources().getString(this.f ? c.a.d.d.c(this) ? R.string.learn_more_usecar_content_ev : R.string.learn_more_content_dm : R.string.learn_more_content_ev), getResources().getString(R.string.sure), null, new e(), null).w();
    }

    public final void x() {
        String string = getResources().getString(R.string.smarttravel_privacy);
        c.a.d.f.g(this, "privacy", getResources().getString(R.string.title_tips), c.a.d.a.a(BYDAutoBodyworkDevice.getInstance(this).getAutoType(), this, string), getResources().getString(R.string.sure), null, null, null).w();
    }

    public final void y() {
        try {
            unbindService(this.n);
            this.m = null;
            this.n = null;
        } catch (Exception e2) {
            Log.e("smart_charge", e2.getMessage());
        }
    }
}
