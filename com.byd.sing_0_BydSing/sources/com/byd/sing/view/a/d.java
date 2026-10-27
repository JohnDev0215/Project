package com.byd.sing.view.a;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.sing.R;

/* JADX INFO: compiled from: ChargeFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c {
    private ImageView q;
    private TextView r;

    public d(int i) {
        super(i);
    }

    @Override // com.byd.sing.view.a.c
    public boolean H() {
        return true;
    }

    public void J(int i) {
        this.e = i;
        if (i == -1) {
            this.r.setText(R.string.no_power);
            this.q.setImageResource(R.drawable.img_ksong_microphone_low_power);
        } else if (i == 0) {
            this.r.setText(R.string.charging);
            this.q.setImageResource(R.drawable.img_ksong_charging);
        } else {
            if (i != 1) {
                return;
            }
            this.r.setText(R.string.charged);
            this.q.setImageResource(R.drawable.img_ksong_charging_complete);
        }
    }

    @Override // com.byd.sing.view.a.g
    protected void b(View view) {
        this.q = (ImageView) view.findViewById(R.id.iv_charge);
        this.r = (TextView) view.findViewById(R.id.tv_charge);
        view.findViewById(R.id.btn_i_know).setOnClickListener(this);
        J(this.e);
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return com.byd.sing.j.f().q() ? R.layout.layout_charge : R.layout.layout_charge_denza;
    }

    @Override // com.byd.sing.view.a.g
    protected int d() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected int e() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected int g() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected void i(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_312;
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_i_know) {
            w();
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
        a();
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return false;
    }
}
