package com.byd.cleardata.ui.e;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.byd.cleardata.R;
import com.byd.cleardata.ui.e.a;

/* JADX INFO: loaded from: classes.dex */
public class n extends com.byd.cleardata.ui.e.a<Object> {
    private TextView A0;
    private ProgressBar B0;
    private long C0;
    private boolean D0;
    private d E0;
    private final a.c<Object> F0;
    private int y0;
    private long z0;

    class a implements a.c<Object> {
        a() {
        }

        @Override // com.byd.cleardata.ui.e.a.c
        public void a(int i, Object obj) {
            if (i == 0) {
                a.c<T> cVar = n.this.k0;
                if (cVar != 0) {
                    cVar.a(0, null);
                }
                n.this.z1();
            }
        }
    }

    public n() {
        this.z0 = 0L;
        this.C0 = 100L;
        this.F0 = new a();
    }

    public n(int i, int i2) {
        this.z0 = 0L;
        this.C0 = 100L;
        this.F0 = new a();
        this.y0 = i;
        this.C0 = 10000L;
    }

    @Override // com.byd.cleardata.ui.e.a, androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        Dialog dialogB1 = B1();
        dialogB1.setCancelable(false);
        dialogB1.setCanceledOnTouchOutside(false);
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        bundle.putLong("key1", this.C0);
        bundle.putLong("key2", this.z0);
        bundle.putInt("key3", this.y0);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 1;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void I1(View view) {
        this.A0 = (TextView) view.findViewById(R.id.tv_progress);
        this.B0 = (ProgressBar) view.findViewById(R.id.progressBar);
        X1(this.z0);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        int i = this.y0;
        return i != 0 ? R(i) : "";
    }

    @Override // com.byd.cleardata.ui.e.a
    public boolean N1() {
        Dialog dialogB1 = B1();
        return dialogB1 != null && dialogB1.isShowing();
    }

    @Override // com.byd.cleardata.ui.e.a
    public void O1() {
        d dVar = this.E0;
        if (dVar == null || !dVar.N1()) {
            d dVarX1 = d.X1(this.m0);
            this.E0 = dVarX1;
            dVarX1.V1(x());
            this.E0.R1(this.F0);
        }
    }

    @Override // com.byd.cleardata.ui.e.a
    protected boolean P1(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        d dVar = this.E0;
        if (dVar != null) {
            dVar.z1();
        }
        d dVarX1 = d.X1(this.m0);
        this.E0 = dVarX1;
        dVarX1.V1(x());
        this.E0.R1(this.F0);
        return true;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_progress;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_312;
    }

    @Override // com.byd.cleardata.ui.e.a
    public void V1(androidx.fragment.app.i iVar) {
        super.V1(iVar);
    }

    public void W1() {
        long j = this.z0 + 1;
        this.z0 = j;
        X1(j);
    }

    public void X1(long j) {
        if (this.D0 || j < 0) {
            return;
        }
        long j2 = this.C0;
        if (j <= j2) {
            this.z0 = j;
            int i = j >= j2 ? 100 : (int) ((j * 100.0f) / j2);
            this.A0.setText(S(R.string.progress, Integer.valueOf(i)));
            this.B0.setProgress(i);
        }
    }

    public void Y1(long j, long j2) {
        if (this.z0 <= j) {
            this.z0 = j;
        }
        this.C0 = j2;
        long j3 = this.z0;
        if (j3 <= 0 || j2 <= 0 || j3 > j2) {
            return;
        }
        X1(j3);
    }

    @Override // com.byd.cleardata.ui.e.a, com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        if (bundle != null) {
            com.byd.cleardata.i.c.b("restore");
            this.C0 = bundle.getLong("key1");
            long j = bundle.getLong("key2");
            this.z0 = j;
            X1(j);
            int i = bundle.getInt("key3");
            this.y0 = i;
            S1(R(i));
            d dVar = (d) x().c(d.class.getSimpleName());
            this.E0 = dVar;
            if (dVar != null) {
                dVar.R1(this.F0);
            }
        }
    }

    @Override // com.byd.cleardata.ui.e.a, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_cancel) {
            d dVar = this.E0;
            if (dVar != null) {
                dVar.z1();
            }
            d dVarX1 = d.X1(this.m0);
            this.E0 = dVarX1;
            dVarX1.V1(x());
            this.E0.R1(this.F0);
        }
    }

    @Override // com.byd.cleardata.ui.e.a, com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void s0() {
        this.D0 = true;
        super.s0();
    }

    @Override // com.byd.cleardata.ui.e.h
    public void z1() {
        this.D0 = true;
        d dVar = this.E0;
        if (dVar != null) {
            dVar.z1();
        }
        super.z1();
    }
}
