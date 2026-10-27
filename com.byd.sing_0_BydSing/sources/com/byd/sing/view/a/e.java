package com.byd.sing.view.a;

import android.view.MotionEvent;
import android.view.View;
import com.byd.sing.R;
import com.byd.sing.SingApplication;
import com.byd.sing.view.a.g;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

/* JADX INFO: compiled from: ConnectFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends c {
    public e(int i) {
        super(i);
    }

    private int J() {
        return com.byd.sing.j.f().q() ? com.byd.sing.j.f().w() ? R.layout.layout_connecting_right_rudder : R.layout.layout_connecting : com.byd.sing.j.f().w() ? R.layout.layout_connecting_denza_right_rudder : R.layout.layout_connecting_denza;
    }

    @Override // com.byd.sing.view.a.c
    public void F() {
        super.F();
        h.U();
    }

    @Override // com.byd.sing.view.a.c
    public boolean H() {
        return true;
    }

    @Override // com.byd.sing.view.a.c, com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void a() {
        super.a();
        org.greenrobot.eventbus.c.c().q(this);
    }

    @Override // com.byd.sing.view.a.g
    protected void b(View view) {
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return J();
    }

    @Override // com.byd.sing.view.a.g
    protected int d() {
        return R.drawable.icon_ktv_suoxiao;
    }

    @Override // com.byd.sing.view.a.g
    protected int e() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected int g() {
        return R.string.title_common;
    }

    @Override // com.byd.sing.view.a.g
    protected void i(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_482;
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void m() {
        org.greenrobot.eventbus.c.c().q(this);
        super.m();
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_left) {
            g.a aVar = this.h;
            if (aVar != null) {
                aVar.a(id);
            }
            w();
        }
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onOrientationEvent(com.byd.sing.service.b bVar) {
        a();
        h.U();
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void p() {
        super.p();
        org.greenrobot.eventbus.c.c().o(this);
    }

    @Override // com.byd.sing.view.a.f
    protected void q() {
        boolean zJ = com.byd.sing.c.j(SingApplication.a());
        int iB = com.byd.sing.k.b();
        if (iB == 1) {
            if (zJ) {
                this.k.x -= com.byd.sing.c.h() / 4;
                return;
            } else {
                this.k.y -= com.byd.sing.c.g() / 4;
                return;
            }
        }
        if (iB == 2) {
            if (zJ) {
                this.k.x += com.byd.sing.c.h() / 4;
            } else {
                this.k.y += com.byd.sing.c.g() / 4;
            }
        }
    }

    @Override // com.byd.sing.view.a.f
    protected void t(int i, int i2) {
    }

    @Override // com.byd.sing.view.a.f
    protected void v(MotionEvent motionEvent) {
    }

    @Override // com.byd.sing.view.a.f
    protected void w() {
        h.U();
        a();
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return true;
    }

    @Override // com.byd.sing.view.a.f
    protected int z() {
        return 2001;
    }
}
