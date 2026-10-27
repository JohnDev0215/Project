package com.byd.sing.view.a;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.byd.sing.R;
import com.byd.sing.view.CustomScrollView;
import com.byd.sing.view.a.g;

/* JADX INFO: compiled from: NoticeFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends c {
    private CustomScrollView q;
    private Button r;
    private Handler s;

    /* JADX INFO: compiled from: NoticeFrame.java */
    class a implements CustomScrollView.a {
        a() {
        }

        @Override // com.byd.sing.view.CustomScrollView.a
        public void a() {
            i iVar = i.this;
            if (iVar.e == 1) {
                iVar.r.setEnabled(true);
            }
        }

        @Override // com.byd.sing.view.CustomScrollView.a
        public void b() {
        }
    }

    /* JADX INFO: compiled from: NoticeFrame.java */
    class b implements View.OnTouchListener {
        b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            i.this.I();
            return false;
        }
    }

    public i(int i) {
        super(i);
        this.s = new Handler();
    }

    private int K() {
        return com.byd.sing.j.f().q() ? com.byd.sing.j.f().w() ? R.layout.layout_notice_right_rudder : R.layout.layout_notice : com.byd.sing.j.f().w() ? R.layout.layout_notice_denza_right_rudder : R.layout.layout_notice_denza;
    }

    private int L() {
        return com.byd.sing.j.f().q() ? R.layout.layout_notice_button : R.layout.layout_notice_button_denza;
    }

    @Override // com.byd.sing.view.a.c
    public void F() {
        super.F();
        h.U();
    }

    @Override // com.byd.sing.view.a.c
    public boolean H() {
        return this.e != 1;
    }

    @Override // com.byd.sing.view.a.c, com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void a() {
        this.s.removeCallbacksAndMessages(null);
        super.a();
    }

    @Override // com.byd.sing.view.a.g
    protected void b(View view) {
        CustomScrollView customScrollView = (CustomScrollView) view.findViewById(R.id.scrollView);
        this.q = customScrollView;
        customScrollView.setScanScrollChangedListener(new a());
        this.q.setOnTouchListener(new b());
        if (this.e == 1) {
            view.findViewById(R.id.iv_left).setVisibility(8);
            Button button = (Button) view.findViewById(R.id.btn_agree);
            this.r = button;
            button.setOnClickListener(this);
            boolean zO = com.byd.sing.j.f().o();
            com.byd.sing.a.a("isAgreed = " + zO);
            if (zO) {
                com.byd.sing.j.f().S(new j());
                a();
            }
        }
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return this.e == 1 ? L() : K();
    }

    @Override // com.byd.sing.view.a.g
    protected int d() {
        return R.drawable.icon_public_close_x;
    }

    @Override // com.byd.sing.view.a.g
    protected int e() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected int g() {
        return R.string.title_notice;
    }

    @Override // com.byd.sing.view.a.g
    protected void i(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_544;
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_left) {
            w();
            return;
        }
        if (id == R.id.btn_agree) {
            g.a aVar = this.h;
            if (aVar != null) {
                aVar.a(R.id.btn_agree);
            }
            com.byd.sing.j.f().C();
            com.byd.sing.j.f().S(new j());
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
        if (this.e != 0) {
            F();
        } else {
            com.byd.sing.j.f().S(new k());
            a();
        }
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return false;
    }
}
