package com.byd.hvac.view.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseActivity;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class DemoMainActivity extends BaseActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Button f1041b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Button f1042c;
    public Button d;
    public Button e;
    public Button f;
    public Button g;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B0(View view) {
        startActivity(new Intent(this, (Class<?>) DemoDiPilotActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void r0(View view) {
        startActivity(new Intent(this, (Class<?>) DemoHvacActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void t0(View view) {
        startActivity(new Intent(this, (Class<?>) DemoSeatActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: u0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void v0(View view) {
        startActivity(new Intent(this, (Class<?>) DemoGreenActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: w0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void x0(View view) {
        startActivity(new Intent(this, (Class<?>) DemoFragranceActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void z0(View view) {
        startActivity(new Intent(this, (Class<?>) DemoBodyWorkActivity.class));
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.demo_activity_main;
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void V() {
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void f0() {
        this.f1041b = (Button) findViewById(R.id.btn_fragrance);
        this.f1042c = (Button) findViewById(R.id.btn_hvac);
        this.d = (Button) findViewById(R.id.btn_green);
        this.e = (Button) findViewById(R.id.btn_seat);
        this.f = (Button) findViewById(R.id.btn_body_work);
        this.g = (Button) findViewById(R.id.btn_dipilot);
        this.f1042c.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.s9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f759a.r0(view);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.r9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f735a.t0(view);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.t9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f779a.v0(view);
            }
        });
        this.f1041b.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.w9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f842a.x0(view);
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.v9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f822a.z0(view);
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.h.u9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f802a.B0(view);
            }
        });
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
