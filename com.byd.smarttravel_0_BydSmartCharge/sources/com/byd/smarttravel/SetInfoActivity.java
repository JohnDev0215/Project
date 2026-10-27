package com.byd.smarttravel;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import c.a.b.b;
import c.a.d.d;
import c.a.d.f;
import c.a.d.h;
import c.a.d.i;
import c.a.d.k;
import com.byd.widget.BydSwitch;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public class SetInfoActivity extends BydActivity implements View.OnClickListener, BydSwitch.a, c.a.a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient c.a.b.b f1966b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient b f1967c;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws Throwable {
            try {
                SetInfoActivity.this.f1966b = b.a.f(iBinder);
            } catch (Exception e) {
                k.b(e.getMessage());
                Log.e("smart_charge", e.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        public /* synthetic */ b(SetInfoActivity setInfoActivity, a aVar) {
            this();
        }
    }

    @Override // c.a.a.a
    public int a() {
        return R.layout.activity_setinfo_byd;
    }

    @Override // com.byd.widget.BydSwitch.a
    public void c(View view, MotionEvent motionEvent, boolean z) {
        if (view.isEnabled()) {
            ((BydSwitch) view).b(z, false);
            c.a.c.a.d(view, 0);
            switch (view.getId()) {
                case R.id.switch_acc_off /* 2131296578 */:
                    i.g(this, "acc_off_tips", z);
                    try {
                        if (this.f1966b != null) {
                            PlanBean planBeanA = h.a(this);
                            planBeanA.f1963b = "TYPE_ALARM_NOT_INTERFACE";
                            this.f1966b.e(planBeanA);
                        }
                    } catch (Exception e) {
                        Log.e("smart_charge", e.getMessage());
                        return;
                    }
                    break;
                case R.id.switch_charge_gun /* 2131296579 */:
                    i.g(this, "gun_state_tips", z);
                    try {
                        if (this.f1966b != null) {
                            PlanBean planBeanA2 = h.a(this);
                            planBeanA2.f1963b = "TYPE_ALARM_EMPTY";
                            this.f1966b.e(planBeanA2);
                        }
                    } catch (Exception e2) {
                        Log.e("smart_charge", e2.getMessage());
                        return;
                    }
                    break;
            }
        }
    }

    @Override // c.a.a.a
    public int d() {
        return R.layout.activity_setinfo;
    }

    public final void f() {
        try {
            this.f1967c = new b(this, null);
            Intent intent = new Intent();
            intent.setAction("com.byd.charge.service.action");
            intent.setPackage("com.byd.uereport");
            bindService(intent, this.f1967c, 1);
        } catch (Exception e) {
            Log.e("smart_charge", e.getMessage());
        }
    }

    public final void g() {
        try {
            for (String str : f.f1597b) {
                byte b2 = -1;
                if (str.hashCode() == -2085148305 && str.equals("statement")) {
                    b2 = 0;
                }
                if (b2 == 0) {
                    h();
                }
            }
            f.f1597b.clear();
        } catch (Exception e) {
            Log.e("smart_charge", e.getMessage());
        }
    }

    public final void h() {
        String string = getResources().getString(R.string.statement_content);
        f.g(this, "statement", getResources().getString(R.string.statement), c.a.d.a.a(BYDAutoBodyworkDevice.getInstance(this).getAutoType(), this, string), getResources().getString(R.string.sure), null, new a(), null).w();
    }

    public final void i() {
        try {
            unbindService(this.f1967c);
            this.f1966b = null;
            this.f1967c = null;
        } catch (Exception e) {
            Log.e("smart_charge", e.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (c.a.d.b.a()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.rl_statement) {
            h();
        } else if (id == R.id.tv_back) {
            finish();
        } else {
            if (id != R.id.tv_version) {
                return;
            }
            startActivity(new Intent(this, (Class<?>) TestActivity.class));
        }
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
        setContentView(c.a.a.b.b(this, this));
        BydSwitch bydSwitch = (BydSwitch) findViewById(R.id.switch_acc_off);
        BydSwitch bydSwitch2 = (BydSwitch) findViewById(R.id.switch_charge_gun);
        bydSwitch.b(i.b(this, "acc_off_tips"), false);
        bydSwitch2.b(i.b(this, "gun_state_tips"), false);
        ((TextView) findViewById(R.id.tv_version)).setText(d.b(this));
        bydSwitch.setOnInterceptTouchListener(this);
        bydSwitch2.setOnInterceptTouchListener(this);
        findViewById(R.id.rl_statement).setOnClickListener(this);
        findViewById(R.id.tv_back).setOnClickListener(this);
        boolean z = k.f1636c;
        findViewById(R.id.view_acc_off).setVisibility(0);
        g();
        f();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        i();
        f.d();
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        f.s();
    }
}
