package com.byd.digitalkey.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import b.b.a.j.b.a;
import com.byd.base.view.BaseTitleActivity;
import com.byd.base.view.BaseWebViewActivity;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.R$string;
import com.bydux.api.common.views.window.ToastUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DigitalKeySettingActivity extends BaseTitleActivity implements View.OnClickListener {
    public b.b.j.c j;
    public View k;
    public ImageView l;
    public LinearLayout m;
    public b.b.a.j.b.a n;
    public b.b.a.j.b.a o;
    public b.b.a.j.b.a p;
    public b.b.a.j.b.a q;
    public b.b.a.j.b.a r;
    public final String i = getClass().getSimpleName();
    public final b.b.j.a s = new a();
    public final b.b.j.b t = new b();

    public class a extends b.b.j.a {
        public a() {
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onKeyFindingResults(int i, int i2) {
            super.onKeyFindingResults(i, i2);
            if (i != 0 && i2 == 0) {
                b.b.a.f.f.b(DigitalKeySettingActivity.this.i, "onKeyFindingResults-removeMessages", new Object[0]);
                b.b.j.e.b(250004);
                DigitalKeySettingActivity.this.K(i);
            }
        }
    }

    public class b extends b.b.j.b {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3526a;

            public a(int i) {
                this.f3526a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f3526a != 1) {
                    DigitalKeySettingActivity.this.J();
                    return;
                }
                DigitalKeySettingActivity digitalKeySettingActivity = DigitalKeySettingActivity.this;
                ToastUtils.show(digitalKeySettingActivity, digitalKeySettingActivity.getString(R$string.nfc_key_delete_ok));
                DigitalKeySettingActivity.this.finish();
                DigitalKeySettingActivity.this.startActivity(new Intent(DigitalKeySettingActivity.this, (Class<?>) DigitalKeyActivity.class));
            }
        }

        public b() {
        }

        @Override // b.b.j.b, android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
        public void onDeleteAllNfcKeyResult(int i, int i2) {
            super.onDeleteAllNfcKeyResult(i, i2);
            System.out.println("云端反馈删所有钥匙结果：" + i2);
            b.b.j.e.b(25007);
            DigitalKeySettingActivity.this.runOnUiThread(new a(i2));
        }
    }

    public class c implements Runnable {

        public class a implements a.g {
            public a() {
            }

            @Override // b.b.a.j.b.a.g
            public void a(View view) {
                DigitalKeySettingActivity.this.Q();
            }
        }

        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DigitalKeySettingActivity.this.n == null) {
                DigitalKeySettingActivity digitalKeySettingActivity = DigitalKeySettingActivity.this;
                digitalKeySettingActivity.n = new b.b.a.j.b.a(digitalKeySettingActivity);
            }
            DigitalKeySettingActivity.this.n.setTitle(R$string.nfc_tip);
            DigitalKeySettingActivity.this.n.t(R$string.nfc_key_delete_all_fail);
            DigitalKeySettingActivity.this.n.o(R$string.nfc_sure_retry);
            DigitalKeySettingActivity.this.n.q(new a());
            DigitalKeySettingActivity.this.n.h().setTag(5);
            if (DigitalKeySettingActivity.this.n.isShowing()) {
                b.b.a.f.f.b(DigitalKeySettingActivity.this.i, "deleteFailDialog isShowing", new Object[0]);
            } else {
                DigitalKeySettingActivity.this.n.show();
            }
            DigitalKeySettingActivity digitalKeySettingActivity2 = DigitalKeySettingActivity.this;
            digitalKeySettingActivity2.r = digitalKeySettingActivity2.n;
        }
    }

    public class d implements a.g {
        public d() {
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            if (DigitalKeySettingActivity.this.j.g() != 1) {
                ToastUtils.show(DigitalKeySettingActivity.this.getApplication(), DigitalKeySettingActivity.this.getString(R$string.nfc_yun_cloud_net_error));
            } else if (DigitalKeySettingActivity.this.j.q(DigitalKeySettingActivity.this.getApplication())) {
                DigitalKeySettingActivity.this.Q();
            } else {
                DigitalKeySettingActivity.this.P();
            }
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DigitalKeySettingActivity.this.m.setEnabled(false);
            DigitalKeySettingActivity.this.k.setVisibility(0);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(1500L);
            rotateAnimation.setRepeatCount(-1);
            DigitalKeySettingActivity.this.l.startAnimation(rotateAnimation);
        }
    }

    public class f implements Runnable {

        public class a implements a.g {
            public a() {
            }

            @Override // b.b.a.j.b.a.g
            public void a(View view) {
                DigitalKeySettingActivity.this.Q();
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DigitalKeySettingActivity.this.o == null) {
                DigitalKeySettingActivity digitalKeySettingActivity = DigitalKeySettingActivity.this;
                digitalKeySettingActivity.o = new b.b.a.j.b.a(digitalKeySettingActivity);
            }
            DigitalKeySettingActivity.this.o.setTitle(R$string.nfc_tip);
            DigitalKeySettingActivity.this.o.t(R$string.nfc_key_not_in_car);
            DigitalKeySettingActivity.this.o.o(R$string.nfc_sure_retry);
            DigitalKeySettingActivity.this.o.q(new a());
            DigitalKeySettingActivity.this.o.h().setTag(2);
            if (DigitalKeySettingActivity.this.o.isShowing()) {
                b.b.a.f.f.b(DigitalKeySettingActivity.this.i, "mKeyNotInCarDialog isShowing", new Object[0]);
            } else {
                DigitalKeySettingActivity.this.o.show();
            }
            DigitalKeySettingActivity digitalKeySettingActivity2 = DigitalKeySettingActivity.this;
            digitalKeySettingActivity2.r = digitalKeySettingActivity2.o;
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DigitalKeySettingActivity.this.l.clearAnimation();
            DigitalKeySettingActivity.this.k.setVisibility(8);
            DigitalKeySettingActivity.this.m.setEnabled(true);
        }
    }

    public final void J() {
        N();
        L();
    }

    public final void K(int i) {
        b.b.a.f.f.b(this.i, "handleKeyFindResult :" + i, new Object[0]);
        if (1 == i) {
            this.j.c(2);
        } else {
            O();
            L();
        }
    }

    public final void L() {
        runOnUiThread(new g());
    }

    public final void M() {
        if (this.p == null) {
            this.p = new b.b.a.j.b.a(this);
        }
        this.p.setTitle(R$string.nfc_tip);
        this.p.t(R$string.nfc_delete_all_key_message);
        this.p.o(R$string.base_common_dialog_btn_ok);
        this.p.q(new d());
        this.p.h().setTag(4);
        if (this.p.isShowing()) {
            b.b.a.f.f.b(this.i, "deleteAllKeyDialog isShowing", new Object[0]);
        } else {
            this.p.show();
        }
        this.r = this.p;
    }

    public final void N() {
        runOnUiThread(new c());
    }

    public final void O() {
        runOnUiThread(new f());
    }

    public final void P() {
        if (this.q == null) {
            this.q = new b.b.a.j.b.a(this);
        }
        this.q.setTitle(R$string.nfc_tip);
        this.q.t(R$string.nfc_safe_diviver_tips);
        this.q.l();
        this.q.o(R$string.digital_key_kown);
        this.q.h().setTag(1);
        if (this.q.isShowing()) {
            b.b.a.f.f.b(this.i, "mNotSafeGearDialog isShowing", new Object[0]);
        } else {
            this.q.show();
        }
        this.r = this.q;
    }

    public final void Q() {
        this.j.x(4);
        runOnUiThread(new e());
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public int f() {
        return R$string.nfc_setting;
    }

    @Override // com.byd.base.view.BaseTitleActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R$id.no_key_start) {
            return;
        }
        if (id == R$id.ll_remove_all_key) {
            M();
        } else if (id == R$id.ll_key_user_report) {
            BaseWebViewActivity.v(this, b.b.j.h.a.a(this), getResources().getString(R$string.nfc_user_agreement));
        }
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.activity_digital_key_setting);
        findViewById(R$id.no_key_start).setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.ll_remove_all_key);
        this.m = linearLayout;
        linearLayout.setOnClickListener(this);
        findViewById(R$id.ll_key_user_report).setOnClickListener(this);
        this.k = findViewById(R$id.loading_ll);
        this.l = (ImageView) findViewById(R$id.iv_loading);
        this.k.setVisibility(8);
        b.b.j.c cVarI = b.b.j.c.i(this);
        this.j = cVarI;
        cVarI.v(this.s);
        this.j.w(this.t);
        EventBus.getDefault().register(this);
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.j.C(this.s);
        this.j.D(this.t);
        EventBus.getDefault().unregister(this);
        b.b.j.e.b(25007);
        b.b.a.j.b.a aVar = this.r;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        this.r.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.j.f fVar) {
        int iA = fVar.a();
        if (iA == 25007) {
            J();
            b.b.a.f.f.b(this.i, "删所有钥匙20s超时", new Object[0]);
        } else {
            if (iA != 250004) {
                return;
            }
            K(this.j.n());
            b.b.a.f.f.b(this.i, "删钥匙寻实体钥匙6s超时", new Object[0]);
        }
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt("dialog_restore_bundle_key", 0);
        b.b.a.f.f.b(this.i, "onActivityCreated dialog：%d:", Integer.valueOf(i));
        if (i == 2) {
            O();
            return;
        }
        if (i == 5) {
            N();
        } else if (i == 4) {
            M();
        } else if (i == 1) {
            P();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.b.a.j.b.a aVar = this.r;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        this.r.dismiss();
        bundle.putInt("dialog_restore_bundle_key", ((Integer) this.r.h().getTag()).intValue());
    }
}
