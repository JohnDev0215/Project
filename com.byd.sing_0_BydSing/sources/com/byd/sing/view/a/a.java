package com.byd.sing.view.a;

import android.view.MotionEvent;
import android.view.View;
import com.byd.sing.R;
import com.byd.sing.SingApplication;

/* JADX INFO: compiled from: AtmosphereListFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {
    @Override // com.byd.sing.view.a.f
    public void D(int i, int i2) {
        g gVarE = com.byd.sing.j.f().e(b.class.getSimpleName());
        if (gVarE != null && gVarE.k()) {
            gVarE.a();
        }
        super.D(i, i2);
    }

    @Override // com.byd.sing.view.a.c
    public boolean H() {
        return true;
    }

    public void J(View view) {
        if (j(SingApplication.a())) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            A(iArr[0] + view.getWidth() + SingApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_20), (iArr[1] + (view.getHeight() / 2)) - (this.f3311d / 2));
        } else {
            int[] iArr2 = new int[2];
            view.getLocationOnScreen(iArr2);
            A((iArr2[0] + (view.getWidth() / 2)) - (this.f3310c / 2), iArr2[1] + view.getHeight() + SingApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_20));
        }
    }

    public void K(View view) {
        if (j(SingApplication.a())) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            A((iArr[0] - this.f3310c) - SingApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_20), (iArr[1] + (view.getHeight() / 2)) - (this.f3311d / 2));
        } else {
            int[] iArr2 = new int[2];
            view.getLocationOnScreen(iArr2);
            A((iArr2[0] + (view.getWidth() / 2)) - (this.f3310c / 2), (iArr2[1] - this.f3311d) - SingApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_20));
        }
    }

    @Override // com.byd.sing.view.a.c, com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void a() {
        super.a();
    }

    @Override // com.byd.sing.view.a.g
    protected void b(View view) {
        view.findViewById(R.id.iv_1).setOnClickListener(this);
        view.findViewById(R.id.iv_2).setOnClickListener(this);
        view.findViewById(R.id.iv_3).setOnClickListener(this);
        view.findViewById(R.id.iv_4).setOnClickListener(this);
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return com.byd.sing.j.f().q() ? R.layout.layout_atmosphere_list : R.layout.layout_atmosphere_list_denza;
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
        if (j(SingApplication.a())) {
            iArr[0] = R.dimen.dp_360;
            iArr[1] = R.dimen.dp_72;
        } else {
            iArr[0] = R.dimen.dp_72;
            iArr[1] = R.dimen.dp_360;
        }
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        I();
        int id = view.getId();
        int i = 0;
        if (id != R.id.iv_1) {
            if (id == R.id.iv_2) {
                i = 1;
            } else if (id == R.id.iv_3) {
                i = 2;
            } else if (id == R.id.iv_4) {
                i = 3;
            }
        }
        g gVarE = com.byd.sing.j.f().e(b.class.getSimpleName());
        if (gVarE != null && gVarE.k()) {
            gVarE.a();
        }
        com.byd.sing.j.f().S(new b(i));
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
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return false;
    }

    @Override // com.byd.sing.view.a.f
    protected int z() {
        return 2038;
    }
}
