package com.byd.carsettings.ui.fragment.carhealthy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.b.a.h.k;
import b.b.g.q.d.l;
import b.b.g.q.d.m;
import b.b.g.r.n;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class OverhaulFragment extends XFragment<b.b.g.n.g.c> implements View.OnClickListener, BydSwitch.a, b.b.o.b.a.a {
    public static int m = 0;
    public static int n = 5;
    public BydSwitch p;
    public BydSwitch q;
    public BydSwitch r;
    public BydSwitch s;
    public LinearLayout t;
    public ImageView u;
    public l v;
    public l w;
    public l x;
    public final String o = getClass().getSimpleName();
    public final String y = "OverhaulFragment_label_tire";
    public final String z = "OverhaulFragment_label_tpms";
    public final String A = "OverhaulFragment_label_epb_hint";
    public final String B = "OverhaulFragment_label_jack";
    public final String D = "OverhaulFragment_label_jack_desc";
    public final String E = "OverhaulFragment_label_wiper";

    public class a implements View.OnClickListener {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.g.c) OverhaulFragment.this.mo7getP()).s(1);
            OverhaulFragment.this.d0(1);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class c extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3026a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(long j, long j2, String str) {
            super(j, j2);
            this.f3026a = str;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            int unused = OverhaulFragment.n = 0;
            OverhaulFragment.this.x.t(this.f3026a);
            OverhaulFragment.this.x.s(true);
            OverhaulFragment.this.x.u(true);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            float f2 = j / 1000.0f;
            if (Math.round(f2) != 0) {
                OverhaulFragment.this.x.t(this.f3026a + "(" + Math.round(f2) + "s)");
            }
            int unused = OverhaulFragment.n = Math.round(f2);
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.g.c) OverhaulFragment.this.mo7getP()).t(2);
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            OverhaulFragment overhaulFragment = OverhaulFragment.this;
            overhaulFragment.S(((b.b.g.n.g.c) overhaulFragment.mo7getP()).f());
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.g.r.e.c(200008)) {
                return;
            }
            OverhaulFragment overhaulFragment = OverhaulFragment.this;
            overhaulFragment.S(((b.b.g.n.g.c) overhaulFragment.mo7getP()).f());
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.p = (BydSwitch) this.f3717c.findViewById(R.id.sw_front_wiper);
        this.q = (BydSwitch) this.f3717c.findViewById(R.id.sw_rear_wiper);
        this.r = (BydSwitch) this.f3717c.findViewById(R.id.sw_epb);
        this.s = (BydSwitch) this.f3717c.findViewById(R.id.sw_jack);
        this.t = (LinearLayout) this.f3717c.findViewById(R.id.ll_wiper);
        this.u = (ImageView) this.f3717c.findViewById(R.id.iv_oil_result);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void N() {
        if (((b.b.g.n.g.c) mo7getP()).n()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.byd.auto_emanual", "com.byd.auto_emanual.MainActivity"));
            intent.setFlags(337641472);
            startActivity(intent);
        }
    }

    public void O(int i) {
        if (i == 1) {
            Z(5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.BydSwitch.a
    public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
        if (view.isEnabled()) {
            ((BydSwitch) view).setChecked(z, false);
            b.b.g.o.f.d(view, 0);
            int iA = b.b.g.n.q.b.c().a();
            switch (view.getId()) {
                case R.id.sw_epb /* 2131298211 */:
                    m = 0;
                    this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, z)));
                    ((b.b.g.n.g.c) mo7getP()).q(z ? 2 : 1, 2000);
                    break;
                case R.id.sw_front_wiper /* 2131298212 */:
                    m = 1;
                    this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, z)));
                    if (!k.a(this.p)) {
                        ((b.b.g.n.g.c) mo7getP()).r(z ? 1 : 2);
                    } else {
                        this.p.setChecked(false, false);
                        e0();
                    }
                    break;
                case R.id.sw_jack /* 2131298213 */:
                    m = 0;
                    this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, z)));
                    if (!z) {
                        ((b.b.g.n.g.c) mo7getP()).t(1);
                    } else {
                        b0();
                    }
                    break;
                case R.id.sw_rear_wiper /* 2131298215 */:
                    m = 2;
                    this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, z)));
                    if (!k.a(this.q)) {
                        ((b.b.g.n.g.c) mo7getP()).u(z ? 1 : 2);
                    } else {
                        this.q.setChecked(false, false);
                        e0();
                    }
                    break;
            }
        }
    }

    public void P(int i, boolean z) {
        this.r.setChecked(i == 1, false);
        if (i == 1) {
            k.e(this.r);
        } else {
            k.h(this.r);
        }
    }

    public void Q(int i, boolean z) {
        if (i == 0) {
            this.f3717c.findViewById(R.id.ll_front_wiper).setVisibility(8);
        } else {
            this.f3717c.findViewById(R.id.ll_front_wiper).setVisibility(0);
            if (i == 3) {
                k.e(this.p);
                this.p.setChecked(false, false);
                this.p.setEnabled(true);
                if (!z && isVisible()) {
                    e0();
                }
            } else if (i == 1) {
                this.p.setChecked(true, false);
                k.h(this.p);
            } else if (i == 2) {
                this.p.setChecked(false, false);
                k.h(this.p);
            }
        }
        Y(this.f3717c.findViewById(R.id.ll_front_wiper), z);
    }

    public void R(int i) {
        d0(i);
    }

    public void S(int i) {
        if (i != 1 && i != 2) {
            this.f3717c.findViewById(R.id.ll_jack).setVisibility(8);
        } else {
            this.f3717c.findViewById(R.id.ll_jack).setVisibility(0);
            this.s.setChecked(i == 2, false);
        }
    }

    public void T(int i, boolean z) {
        if (i == 0) {
            this.f3717c.findViewById(R.id.ll_rear_wiper).setVisibility(8);
        } else {
            this.f3717c.findViewById(R.id.ll_rear_wiper).setVisibility(0);
            if (i == 3) {
                k.e(this.q);
                this.q.setChecked(false, false);
                this.q.setEnabled(true);
                if (!z && isVisible()) {
                    e0();
                }
            } else if (i == 1) {
                this.q.setChecked(true, false);
                k.h(this.q);
            } else if (i == 2) {
                this.q.setChecked(false, false);
                k.h(this.q);
            }
        }
        Y(this.f3717c.findViewById(R.id.ll_rear_wiper), z);
    }

    public final void U(boolean z) {
        this.f3717c.findViewById(R.id.tv_reset).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_jack).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.ll_user_manual).setOnClickListener(z ? this : null);
        this.p.setOnInterceptTouchListener(z ? this : null);
        this.q.setOnInterceptTouchListener(z ? this : null);
        this.r.setOnInterceptTouchListener(z ? this : null);
        this.s.setOnInterceptTouchListener(z ? this : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void V() {
        this.f3717c.findViewById(R.id.ll_epb).setVisibility(((b.b.g.n.g.c) mo7getP()).i() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_tpms).setVisibility(((b.b.g.n.g.c) mo7getP()).l() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_user_manual).setVisibility(((b.b.g.n.g.c) mo7getP()).n() ? 0 : 8);
    }

    public boolean W() {
        return isVisible();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.g.c newP() {
        return new b.b.g.n.g.c();
    }

    public final void Y(View view, boolean z) {
        this.f3717c.findViewById(R.id.ll_wiper).setVisibility(this.f3717c.findViewById(R.id.ll_front_wiper).getVisibility() == 8 && this.f3717c.findViewById(R.id.ll_rear_wiper).getVisibility() == 8 ? 8 : 0);
        int iA = b.b.g.n.q.b.c().a();
        if (z) {
            int i = m;
            if (i == 1) {
                this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, true)));
                return;
            } else if (i == 2) {
                this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, true)));
                return;
            } else {
                this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, true)));
                return;
            }
        }
        if (view.getId() == R.id.ll_rear_wiper && this.q.isChecked()) {
            if (b.b.g.r.e.c(200000)) {
                BydLogHelper.logI("wiper", "setWiperVisibility swRearWiper ivPlaceHolder");
                return;
            } else {
                m = 2;
                this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, true)));
                return;
            }
        }
        if (view.getId() == R.id.ll_front_wiper && this.p.isChecked()) {
            if (b.b.g.r.e.c(200001)) {
                BydLogHelper.logI("wiper", "setWiperVisibility swFrontWiper ivPlaceHolder");
                return;
            } else {
                m = 1;
                this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, true)));
                return;
            }
        }
        if (b.b.g.r.e.c(200001) || b.b.g.r.e.c(200000)) {
            BydLogHelper.logI("wiper", "setWiperVisibility other ivPlaceHolder");
        } else {
            m = 0;
            this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, false)));
        }
    }

    public final void Z(int i) {
        l lVar = this.x;
        if (lVar == null || !lVar.p()) {
            String string = getContext().getResources().getString(R.string.carhealthy_dialog_know);
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_epb_hint, (ViewGroup) null);
            l lVarJ = m.j(getContext(), "OverhaulFragment_label_epb_hint", getContext().getResources().getString(R.string.carhealthy_epb_dialog_title), string + "(" + i + "s)", null, new b(), null, true, false, viewInflate);
            this.x = lVarJ;
            lVarJ.C();
            this.x.s(false);
            this.x.u(false);
            new c((long) ((i * 1000) + 100), 1000L, string).start();
        }
    }

    public void a0() {
        m.h(getContext(), "OverhaulFragment_label_jack_desc", getContext().getResources().getString(R.string.carhealthy_jack), getContext().getResources().getString(R.string.carhealthy_jack_open_desc), getContext().getResources().getString(R.string.dialog_btn_ok), null, new g(), null).C();
    }

    public void b0() {
        b.b.g.r.e.h(200008);
        l lVarH = m.h(getContext(), "OverhaulFragment_label_jack", getContext().getResources().getString(R.string.carhealthy_jack), getContext().getResources().getString(R.string.carhealthy_jack_open_hint), getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new d(), new e());
        lVarH.setDismissListener(new f());
        lVarH.C();
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_overhaul;
    }

    public void c0() {
        m.h(getContext(), "OverhaulFragment_label_tpms", getContext().getResources().getString(R.string.vehiclehealth_tyre_pressure), getContext().getResources().getString(R.string.carhealthy_tpms_hint), getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new a(), null).C();
    }

    public final void d0(int i) {
        if (i == 0) {
            return;
        }
        m.f1481c.put("OverhaulFragment_label_tire", Integer.valueOf(i));
        l lVar = this.v;
        if (lVar == null || !lVar.p()) {
            this.v = m.d(getContext(), "OverhaulFragment_label_tire", getContext().getResources().getString(R.string.carhealthy_tpms), null, getContext().getResources().getString(R.string.dialog_btn_cancel), null, null);
        }
        this.v.v(5000L);
        TextView textView = (TextView) this.v.i().findViewById(R.id.tv_dialog_tpms_hint);
        ImageView imageView = (ImageView) this.v.i().findViewById(R.id.iv_dialog_tpms);
        if (i == 1) {
            this.v.i().findViewById(R.id.progressbar).setVisibility(0);
            this.v.t("");
            this.v.r(getContext().getResources().getString(R.string.dialog_btn_cancel));
            imageView.setVisibility(8);
            textView.setText(getContext().getResources().getString(R.string.vehiclehealth_tyre_pressure_resetting));
            if (this.v.p()) {
                return;
            }
            this.v.C();
            return;
        }
        this.v.i().findViewById(R.id.progressbar).setVisibility(8);
        imageView.setVisibility(0);
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), i == 3 ? R.drawable.icon_dark_public_redx_center_72 : R.drawable.icon_dark_public_green_cherk_center_72));
        textView.setText(getContext().getResources().getString(i == 3 ? R.string.vehiclehealth_tyre_pressure_reset_failed : R.string.vehiclehealth_tyre_pressure_reset_success));
        this.v.t(getContext().getResources().getString(R.string.dialog_btn_ok));
        this.v.r(null);
        if (this.v.p()) {
            return;
        }
        this.v.C();
    }

    public final void e0() {
        l lVar = this.w;
        if (lVar == null || !lVar.p()) {
            l lVarC = m.c(getContext(), "OverhaulFragment_label_wiper", getContext().getResources().getString(R.string.vehiclehealth_tips), getContext().getResources().getString(R.string.vehiclehealth_open_wiper_not_allow), 17, getContext().getResources().getDimensionPixelSize(R.dimen.sp_20), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null, true, false, null);
            this.w = lVarC;
            lVarC.C();
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_overhaul;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_overhaul_byd;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : m.f1480b) {
            str.hashCode();
            switch (str) {
                case "OverhaulFragment_label_epb_hint":
                    Z(n);
                    break;
                case "OverhaulFragment_label_jack":
                    b0();
                    break;
                case "OverhaulFragment_label_tire":
                    if (m.f1481c.containsKey("OverhaulFragment_label_tire")) {
                        d0(((Integer) m.f1481c.get("OverhaulFragment_label_tire")).intValue());
                        break;
                    } else {
                        break;
                    }
                    break;
                case "OverhaulFragment_label_tpms":
                    c0();
                    break;
                case "OverhaulFragment_label_wiper":
                    e0();
                    break;
                case "OverhaulFragment_label_jack_desc":
                    a0();
                    break;
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (b.b.m.g.c.a(300L)) {
            return;
        }
        int iA = b.b.g.n.q.b.c().a();
        m = 0;
        this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.e(iA, m, false)));
        int id = view.getId();
        if (id == R.id.iv_jack) {
            a0();
        } else if (id == R.id.ll_user_manual) {
            N();
        } else {
            if (id != R.id.tv_reset) {
                return;
            }
            c0();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.u.setImageDrawable(null);
        U(false);
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        ((b.b.g.n.g.c) mo7getP()).h();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        U(true);
        ((b.b.g.n.g.c) mo7getP()).v();
    }
}
