package com.byd.carsettings;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import b.b.a.h.l;
import b.b.o.b.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class WadeProtectRiskActivity extends AppCompatActivity implements View.OnClickListener, a {
    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.activity_wade_protect_risk_layout;
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.activity_wade_protect_risk_layout;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.activity_right_wade_protect_risk_layout;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(b.b.o.b.b.a.a(this));
        l.F(this, (ImageView) findViewById(R.id.wade_protect_arrow));
    }
}
