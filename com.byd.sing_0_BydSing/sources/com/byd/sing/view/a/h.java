package com.byd.sing.view.a;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import com.byd.sing.R;
import com.byd.sing.SingApplication;
import com.byd.sing.l;
import com.byd.sing.view.a.f;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MicFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends com.byd.sing.view.a.c {
    public static final int q = SingApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_10);
    private int A;
    private int B;
    private Handler C = new Handler();
    private boolean r;
    private com.byd.sing.view.a.a s;
    private View t;
    private View u;
    private View v;
    private View w;
    private boolean x;
    private boolean y;
    private int z;

    /* JADX INFO: compiled from: MicFrame.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.Q(true);
        }
    }

    /* JADX INFO: compiled from: MicFrame.java */
    class b implements f.c {
        b() {
        }

        @Override // com.byd.sing.view.a.f.c
        public void a() {
            h.this.r = false;
            if (h.this.t != null) {
                h.this.t.setSelected(false);
            }
            if (h.this.u != null) {
                h.this.u.setSelected(false);
            }
            if (h.this.v != null) {
                h.this.v.setSelected(false);
            }
            if (h.this.w != null) {
                h.this.w.setSelected(false);
            }
        }
    }

    /* JADX INFO: compiled from: MicFrame.java */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.byd.sing.c.j(SingApplication.a())) {
                int iH = com.byd.sing.c.h() / 2;
                WindowManager.LayoutParams layoutParams = h.this.k;
                int i = layoutParams.x;
                if (i >= iH) {
                    int i2 = i - iH;
                    layoutParams.x = i2;
                    layoutParams.x = Math.max(i2, 0);
                } else {
                    int i3 = i + iH;
                    layoutParams.x = i3;
                    if (i3 + layoutParams.width > com.byd.sing.c.h()) {
                        h.this.k.x = com.byd.sing.c.h() - h.this.k.width;
                    }
                }
            } else {
                int iF = com.byd.sing.c.f() / 2;
                WindowManager.LayoutParams layoutParams2 = h.this.k;
                int i4 = layoutParams2.y;
                if (i4 > iF) {
                    int i5 = i4 - iF;
                    layoutParams2.y = i5;
                    layoutParams2.y = Math.max(i5, 0);
                } else {
                    int i6 = i4 + iF;
                    layoutParams2.y = i6;
                    layoutParams2.y = Math.min(i6, (com.byd.sing.c.f() - h.this.k.height) - com.byd.sing.c.e());
                }
            }
            h.this.C();
            h.this.R();
            h.this.m = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q(boolean z) {
        com.byd.sing.a.d("changeOrientation", Boolean.valueOf(z), Boolean.valueOf(this.r));
        if (z) {
            R();
            E();
            if (this.r) {
                com.byd.sing.view.a.a aVar = this.s;
                if (aVar != null) {
                    aVar.x(null);
                    this.s.a();
                }
                this.s = new com.byd.sing.view.a.a();
                T();
                if (this.x) {
                    this.s.K(this.f3309b);
                } else {
                    this.s.J(this.f3309b);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        if (j(SingApplication.a())) {
            this.x = this.k.x + (this.f3310c / 2) > com.byd.sing.c.h() / 2;
            this.t.setVisibility(8);
            this.u.setVisibility(8);
            if (this.x) {
                this.v.setSelected(this.r);
                this.v.setVisibility(0);
                this.w.setVisibility(8);
                return;
            } else {
                this.v.setVisibility(8);
                this.w.setSelected(this.r);
                this.w.setVisibility(0);
                return;
            }
        }
        this.x = this.k.y + (this.f3311d / 2) > com.byd.sing.c.g() / 2;
        this.v.setVisibility(8);
        this.w.setVisibility(8);
        if (this.x) {
            this.t.setSelected(this.r);
            this.t.setVisibility(0);
            this.u.setVisibility(8);
        } else {
            this.t.setVisibility(8);
            this.u.setSelected(this.r);
            this.u.setVisibility(0);
        }
    }

    private void S() {
        if (this.r) {
            this.r = false;
            com.byd.sing.view.a.a aVar = this.s;
            if (aVar != null) {
                aVar.a();
                this.s = null;
                return;
            }
            return;
        }
        this.r = true;
        this.s = new com.byd.sing.view.a.a();
        T();
        if (this.x) {
            this.s.K(this.f3309b);
        } else {
            this.s.J(this.f3309b);
        }
    }

    private void T() {
        com.byd.sing.view.a.a aVar = this.s;
        if (aVar != null) {
            aVar.x(new b());
        }
    }

    public static void U() {
        if (com.byd.sing.k.e()) {
            com.byd.sing.j.f().T();
        }
    }

    private void W() {
        if (!com.byd.sing.j.f().r()) {
            com.byd.sing.j.f().S(new e(0));
        } else if (com.byd.sing.j.f().o()) {
            com.byd.sing.j.f().S(new j());
        } else {
            com.byd.sing.j.f().S(new i(1));
            a();
        }
    }

    @Override // com.byd.sing.view.a.f
    public void C() {
        super.C();
        if (this.k != null) {
            com.byd.sing.j jVarF = com.byd.sing.j.f();
            WindowManager.LayoutParams layoutParams = this.k;
            jVarF.E(layoutParams.x, layoutParams.y);
        }
    }

    @Override // com.byd.sing.view.a.f
    public void E() {
        if (this.k.y < com.byd.sing.c.i()) {
            this.k.y = com.byd.sing.c.i();
        }
        int i = this.k.y;
        int iG = com.byd.sing.c.g();
        WindowManager.LayoutParams layoutParams = this.k;
        if (i > iG - layoutParams.height) {
            layoutParams.y = com.byd.sing.c.g() - this.k.height;
        }
        WindowManager.LayoutParams layoutParams2 = this.k;
        if (layoutParams2.x < 0) {
            layoutParams2.x = 0;
        }
        int i2 = layoutParams2.x;
        int iH = com.byd.sing.c.h();
        WindowManager.LayoutParams layoutParams3 = this.k;
        if (i2 > iH - layoutParams3.width) {
            layoutParams3.x = com.byd.sing.c.h() - this.k.width;
        }
        super.E();
    }

    @Override // com.byd.sing.view.a.c
    public void F() {
        if (!com.byd.sing.k.e()) {
            super.F();
        }
    }

    @Override // com.byd.sing.view.a.c
    public boolean H() {
        boolean z = !com.byd.sing.k.e();
        com.byd.sing.a.a("isAutoCloseable = " + z);
        return z;
    }

    public void V() {
        int iR;
        int iS;
        int[] iArrG = com.byd.sing.j.f().g();
        if (iArrG != null) {
            iR = iArrG[0];
            iS = iArrG[1];
            com.byd.sing.a.a("showSide location width = " + iR + " , height = " + iS);
        } else {
            iR = r();
            iS = s();
            com.byd.sing.a.a("showSide location is null");
        }
        A(iR, iS);
        R();
    }

    public void X() {
        this.m = false;
        com.byd.sing.view.a.a aVar = this.s;
        if (aVar != null) {
            aVar.a();
        }
        View view = this.f3309b;
        if (view != null) {
            view.postDelayed(new c(), 150L);
        }
    }

    @Override // com.byd.sing.view.a.c, com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void a() {
        com.byd.sing.a.a("dismiss");
        org.greenrobot.eventbus.c.c().q(this);
        com.byd.sing.view.a.a aVar = this.s;
        if (aVar != null) {
            aVar.a();
            this.s = null;
        }
        super.a();
    }

    @Override // com.byd.sing.view.a.g
    protected void b(View view) {
        this.x = true;
        this.t = view.findViewById(R.id.iv_up);
        this.u = view.findViewById(R.id.iv_down);
        this.v = view.findViewById(R.id.iv_left);
        this.w = view.findViewById(R.id.iv_right);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.z = ViewConfiguration.get(SingApplication.a()).getScaledTouchSlop();
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return com.byd.sing.j.f().q() ? R.layout.layout_toolbar : R.layout.layout_toolbar_denza;
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
            iArr[0] = R.dimen.dp_144;
            iArr[1] = R.dimen.dp_96;
        } else {
            iArr[0] = R.dimen.dp_96;
            iArr[1] = R.dimen.dp_144;
        }
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void m() {
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (com.byd.sing.j.f().p()) {
            l.a(R.string.call_active);
            return;
        }
        I();
        int id = view.getId();
        if (id == R.id.iv_mic) {
            W();
            a();
        } else if (id == R.id.iv_up || id == R.id.iv_down || id == R.id.iv_left || id == R.id.iv_right) {
            S();
            R();
        }
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onOrientationEvent(com.byd.sing.service.b bVar) {
        u();
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void p() {
        org.greenrobot.eventbus.c.c().o(this);
        V();
    }

    @Override // com.byd.sing.view.a.f
    protected void q() {
    }

    @Override // com.byd.sing.view.a.f
    protected void t(int i, int i2) {
        com.byd.sing.j jVarF = com.byd.sing.j.f();
        WindowManager.LayoutParams layoutParams = this.k;
        jVarF.E(layoutParams.x, layoutParams.y);
        com.byd.sing.view.a.a aVar = this.s;
        if (aVar == null || !aVar.k()) {
            return;
        }
        if (i2 < 0 && com.byd.sing.c.d(this.f3309b) + i2 < 0) {
            i2 = com.byd.sing.c.d(this.f3309b);
        }
        this.s.D(i, i2);
    }

    @Override // com.byd.sing.view.a.f
    protected void u() {
        super.u();
        com.byd.sing.view.a.a aVar = this.s;
        if (aVar != null) {
            aVar.x(null);
            this.s.a();
        }
        this.C.removeCallbacksAndMessages(null);
        this.C.postDelayed(new a(), 500L);
    }

    @Override // com.byd.sing.view.a.f
    protected void v(MotionEvent motionEvent) {
        if (this.m) {
            I();
            int action = motionEvent.getAction();
            if (action == 0) {
                this.y = false;
                this.A = (int) motionEvent.getRawX();
                this.B = (int) motionEvent.getRawY();
            } else {
                if (action != 1) {
                    if (action != 2) {
                        return;
                    }
                    this.y = true;
                    Q(this.x != (j(SingApplication.a()) ? ((int) motionEvent.getRawX()) > com.byd.sing.c.h() / 2 : ((int) motionEvent.getRawY()) > com.byd.sing.c.g() / 2));
                    return;
                }
                if (Math.abs(this.A - ((int) motionEvent.getRawX())) - this.z > 0 || Math.abs(this.B - ((int) motionEvent.getRawY())) - this.z > 0) {
                    return;
                }
                if (com.byd.sing.j.f().p()) {
                    l.a(R.string.call_active);
                } else {
                    W();
                    a();
                }
            }
        }
    }

    @Override // com.byd.sing.view.a.f
    protected void w() {
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return true;
    }

    @Override // com.byd.sing.view.a.f
    protected int z() {
        return 2038;
    }
}
