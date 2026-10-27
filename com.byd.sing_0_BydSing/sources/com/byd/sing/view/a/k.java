package com.byd.sing.view.a;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.byd.sing.R;

/* JADX INFO: compiled from: SettingSystemFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends c implements CompoundButton.OnCheckedChangeListener {
    @Override // com.byd.sing.view.a.c
    public void F() {
        super.F();
        h.U();
    }

    @Override // com.byd.sing.view.a.c
    public boolean H() {
        return true;
    }

    @Override // com.byd.sing.view.a.g
    @SuppressLint({"UseSwitchCompatOrMaterialCode"})
    protected void b(View view) {
        view.findViewById(R.id.tv_about).setOnClickListener(this);
        Switch r0 = (Switch) view.findViewById(R.id.sc_system);
        Switch r6 = (Switch) view.findViewById(R.id.sc_start_app);
        r0.setChecked(com.byd.sing.j.f().k() == 8 && com.byd.sing.j.f().l() == 4 && com.byd.sing.j.f().j() == 1);
        r6.setChecked(com.byd.sing.i.b("key_start", false));
        r0.setOnCheckedChangeListener(this);
        r6.setOnCheckedChangeListener(this);
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return com.byd.sing.j.f().q() ? com.byd.sing.j.f().w() ? R.layout.layout_setting_system_right_rudder : R.layout.layout_setting_system : com.byd.sing.j.f().w() ? R.layout.layout_setting_system_denza_right_rudder : R.layout.layout_setting_system_denza;
    }

    @Override // com.byd.sing.view.a.g
    protected int d() {
        return R.drawable.icon_public_close_48;
    }

    @Override // com.byd.sing.view.a.g
    protected int e() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected int g() {
        return R.string.title_setting;
    }

    @Override // com.byd.sing.view.a.g
    protected void i(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_482;
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return false;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id != R.id.sc_system) {
            if (id == R.id.sc_start_app) {
                com.byd.sing.i.f("key_start", z);
            }
        } else {
            com.byd.sing.i.f("key_reset", z);
            if (z) {
                com.byd.sing.j.f().P(8);
                com.byd.sing.j.f().Q(4);
                com.byd.sing.j.f().O(1);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_left) {
            w();
        } else if (id == R.id.tv_about) {
            com.byd.sing.j.f().S(new i(0));
            a();
        }
    }

    @Override // com.byd.sing.view.a.f
    protected void q() {
    }

    @Override // com.byd.sing.view.a.f
    protected void t(int i, int i2) {
    }

    @Override // com.byd.sing.view.a.f
    protected void v(MotionEvent motionEvent) {
    }

    @Override // com.byd.sing.view.a.f
    protected void w() {
        com.byd.sing.j.f().S(new j());
        a();
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return true;
    }
}
