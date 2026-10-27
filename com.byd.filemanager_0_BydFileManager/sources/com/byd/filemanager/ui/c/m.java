package com.byd.filemanager.ui.c;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.byd.filemanager.R;
import com.byd.filemanager.ui.c.a;

/* JADX INFO: compiled from: ProgressDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends com.byd.filemanager.ui.c.a<Object> {
    private TextView A0;
    private ProgressBar B0;
    private long C0;
    private boolean D0;
    private c E0;
    private final a.c<Object> F0;
    private int y0;
    private long z0;

    /* JADX INFO: compiled from: ProgressDialog.java */
    class a implements a.c<Object> {
        a() {
        }

        @Override // com.byd.filemanager.ui.c.a.c
        public void a(int i, Object obj) {
            if (i == 0) {
                a.c<T> cVar = m.this.k0;
                if (cVar != 0) {
                    cVar.a(0, null);
                }
                m.this.z1();
            }
        }
    }

    public m() {
        this.z0 = 0L;
        this.C0 = 100L;
        this.F0 = new a();
    }

    @Override // com.byd.filemanager.ui.c.a, androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        Dialog dialogC1 = C1();
        dialogC1.setCancelable(false);
        dialogC1.setCanceledOnTouchOutside(false);
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        bundle.putLong("key1", this.C0);
        bundle.putLong("key2", this.z0);
        bundle.putInt("key3", this.y0);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int N1() {
        return 1;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void O1(View view) {
        this.A0 = (TextView) view.findViewById(R.id.tv_progress);
        this.B0 = (ProgressBar) view.findViewById(R.id.progressBar);
        e2(this.z0);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        if (this.y0 == 0) {
            return "";
        }
        return Q(this.y0) + Q(R.string.special_ellipsis);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected boolean T1() {
        return true;
    }

    @Override // com.byd.filemanager.ui.c.a
    public boolean U1() {
        Dialog dialogC1 = C1();
        return dialogC1 != null && dialogC1.isShowing();
    }

    @Override // com.byd.filemanager.ui.c.a
    public void V1() {
        c cVar = this.E0;
        if (cVar == null || !cVar.U1()) {
            c cVarE2 = c.e2(this.m0);
            this.E0 = cVarE2;
            cVarE2.c2(w());
            this.E0.Y1(this.F0);
        }
    }

    @Override // com.byd.filemanager.ui.c.a
    protected boolean W1(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        c cVar = this.E0;
        if (cVar != null) {
            cVar.z1();
        }
        c cVarE2 = c.e2(this.m0);
        this.E0 = cVarE2;
        cVarE2.c2(w());
        this.E0.Y1(this.F0);
        return true;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return com.byd.filemanager.f.h().v() ? R.layout.dialog_progress : R.layout.dialog_progress_denza;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_264;
    }

    @Override // com.byd.filemanager.ui.c.a
    public void c2(androidx.fragment.app.g gVar) {
        super.c2(gVar);
    }

    public void d2() {
        long j = this.z0 + 1;
        this.z0 = j;
        e2(j);
    }

    @Override // com.byd.filemanager.ui.c.a, com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        if (bundle != null) {
            com.byd.filemanager.k.b.b("restore");
            this.C0 = bundle.getLong("key1");
            long j = bundle.getLong("key2");
            this.z0 = j;
            e2(j);
            int i = bundle.getInt("key3");
            this.y0 = i;
            Z1(Q(i));
            c cVar = (c) w().d(c.class.getSimpleName());
            this.E0 = cVar;
            if (cVar != null) {
                cVar.Y1(this.F0);
            }
        }
    }

    public void e2(long j) {
        if (this.D0 || j < 0) {
            return;
        }
        long j2 = this.C0;
        if (j <= j2) {
            this.z0 = j;
            int i = j >= j2 ? 100 : (int) ((j * 100.0f) / j2);
            this.A0.setText(R(R.string.progress, Integer.valueOf(i)));
            this.B0.setProgress(i);
        }
    }

    public void f2(long j, long j2) {
        if (this.z0 <= j) {
            this.z0 = j;
        }
        this.C0 = j2;
        long j3 = this.z0;
        if (j3 <= 0 || j2 <= 0 || j3 > j2) {
            return;
        }
        e2(j3);
    }

    @Override // com.byd.filemanager.ui.c.a, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_cancel) {
            c cVar = this.E0;
            if (cVar != null) {
                cVar.z1();
            }
            c cVarE2 = c.e2(this.m0);
            this.E0 = cVarE2;
            cVarE2.c2(w());
            this.E0.Y1(this.F0);
        }
    }

    @Override // com.byd.filemanager.ui.c.a, com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void s0() {
        this.D0 = true;
        super.s0();
    }

    @Override // com.byd.filemanager.ui.c.g
    public void z1() {
        this.D0 = true;
        c cVar = this.E0;
        if (cVar != null) {
            cVar.z1();
        }
        super.z1();
    }

    public m(int i, int i2) {
        this.z0 = 0L;
        this.C0 = 100L;
        this.F0 = new a();
        this.y0 = i;
        this.C0 = 10000L;
    }
}
