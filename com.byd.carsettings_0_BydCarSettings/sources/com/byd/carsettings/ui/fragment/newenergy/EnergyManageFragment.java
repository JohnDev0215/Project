package com.byd.carsettings.ui.fragment.newenergy;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import b.b.g.r.z;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.seekbar.SocSeekbar;
import com.byd.carsettings.ui.widget.viewanim.EnergyAnimView;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class EnergyManageFragment extends XFragment<b.b.g.n.n.c> implements SegmentedButtonGroup.f, SegmentedButtonGroup.e, BydSwitch.a, View.OnClickListener, b.b.o.b.a.a {
    public TextView A;
    public DampingScrollView J;
    public EnergyAnimView n;
    public SocSeekbar o;
    public BydSwitch p;
    public BydSwitch q;
    public BydSwitch r;
    public BydSwitch s;
    public BydSwitch t;
    public SegmentedButtonGroup u;
    public SegmentedButtonGroup v;
    public SegmentedButtonGroup w;
    public SegmentedButtonGroup x;
    public SegmentedButtonGroup y;
    public TextView z;
    public String m = getClass().getSimpleName();
    public boolean B = false;
    public boolean D = false;
    public boolean E = false;
    public int[] F = new int[2];
    public boolean G = false;
    public int[] H = {R.id.ll_soc, R.id.ll_energy, R.id.ll_energy, R.id.ll_energy, R.id.ll_energy, R.id.ll_memory, R.id.ll_memory, R.id.ll_soc, R.id.ll_epedal, R.id.ll_situ, R.id.ll_soc};
    public int[] I = {R.id.tv_display_mode, R.id.tv_cruising_range_mode, R.id.ll_cruising_range_standard};
    public Handler K = null;
    public final String L = "EnergyManageFragment_label_soc";
    public final String M = "EnergyManageFragment_label_cruising";
    public final String N = "EnergyManageFragment_label_situ";
    public int O = 0;
    public int P = 0;
    public int Q = 0;
    public int R = 0;

    public class a implements View.OnClickListener {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.c) EnergyManageFragment.this.mo7getP()).O(2);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.u0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).q());
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.g.r.e.c(150013)) {
                return;
            }
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.u0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).q());
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.c) EnergyManageFragment.this.mo7getP()).N(2);
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.t0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).o());
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.g.r.e.c(150014)) {
                return;
            }
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.t0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).o());
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.c) EnergyManageFragment.this.mo7getP()).M(2);
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.s0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).n());
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.g.r.e.c(150019)) {
                return;
            }
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.s0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).n());
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    public class k implements SocSeekbar.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3243a = 0;

        public k() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.carsettings.ui.view.seekbar.SocSeekbar.a
        public void a(float f2) {
            EnergyManageFragment.this.G = false;
            int iRound = Math.round(((EnergyManageFragment.this.F[1] - EnergyManageFragment.this.F[0]) * f2) + EnergyManageFragment.this.F[0]);
            ((b.b.g.n.n.c) EnergyManageFragment.this.mo7getP()).P(iRound, this.f3243a);
            EnergyManageFragment.this.K.removeMessages(0);
            EnergyManageFragment.this.K.sendEmptyMessageDelayed(0, 2000L);
            BydLogHelper.logI(EnergyManageFragment.this.m, "----onStopTrackingTouch----progress:" + f2 + ",textProgress:" + iRound);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.carsettings.ui.view.seekbar.SocSeekbar.a
        public void b(float f2) {
            EnergyManageFragment.this.G = true;
            EnergyManageFragment.this.K.removeMessages(0);
            EnergyManageFragment.this.K.sendEmptyMessage(1);
            int iRound = Math.round(((EnergyManageFragment.this.F[1] - EnergyManageFragment.this.F[0]) * f2) + EnergyManageFragment.this.F[0]);
            EnergyManageFragment.this.A.setText(iRound + "%");
            EnergyManageFragment.this.n.setLineTipsView((float) iRound);
            this.f3243a = iRound;
            if (!EnergyManageFragment.this.t.isChecked()) {
                ((b.b.g.n.n.c) EnergyManageFragment.this.mo7getP()).L(2);
                EnergyManageFragment.this.t.setChecked(true, false);
                EnergyManageFragment.this.C0(true, iRound);
            }
            BydLogHelper.logI(EnergyManageFragment.this.m, "----onStartTrackingTouch----progress:" + f2 + ",textProgress:" + iRound);
        }

        @Override // com.byd.carsettings.ui.view.seekbar.SocSeekbar.a
        public void c() {
            if (EnergyManageFragment.this.K.hasMessages(4)) {
                return;
            }
            z.b(null, R.string.newenergy_soc_disabled_message);
            EnergyManageFragment.this.K.sendEmptyMessageDelayed(4, 2000L);
        }

        @Override // com.byd.carsettings.ui.view.seekbar.SocSeekbar.a
        public void d(float f2) {
            EnergyManageFragment.this.n.setLineTipsView(Math.round(((EnergyManageFragment.this.F[1] - EnergyManageFragment.this.F[0]) * f2) + EnergyManageFragment.this.F[0]));
            EnergyManageFragment.this.A.setText(Math.round(((EnergyManageFragment.this.F[1] - EnergyManageFragment.this.F[0]) * f2) + EnergyManageFragment.this.F[0]) + "%");
        }
    }

    public class l implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3245a;

        public l(int i) {
            this.f3245a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            float y;
            if (EnergyManageFragment.this.f3717c == null || EnergyManageFragment.this.J == null || this.f3245a >= EnergyManageFragment.this.H.length) {
                return;
            }
            if (this.f3245a == 0) {
                EnergyManageFragment.this.J.scrollTo(0, 0);
                return;
            }
            b.b.m.c.a.d(EnergyManageFragment.this.m, "[scrollToPosition]----getY:" + EnergyManageFragment.this.f3717c.findViewById(EnergyManageFragment.this.H[this.f3245a]).getY());
            if (EnergyManageFragment.this.H[this.f3245a] != R.id.ll_energy) {
                EnergyManageFragment.this.J.scrollTo(0, Math.round(EnergyManageFragment.this.f3717c.findViewById(EnergyManageFragment.this.H[this.f3245a]).getY()));
                return;
            }
            float y2 = EnergyManageFragment.this.f3717c.findViewById(EnergyManageFragment.this.H[this.f3245a]).getY();
            int i = this.f3245a;
            if (i == 2) {
                y = EnergyManageFragment.this.f3717c.findViewById(EnergyManageFragment.this.I[0]).getY();
            } else {
                if (i != 3) {
                    if (i == 4) {
                        y = EnergyManageFragment.this.f3717c.findViewById(EnergyManageFragment.this.I[2]).getY();
                    }
                    EnergyManageFragment.this.J.scrollTo(0, Math.round(y2));
                }
                y = EnergyManageFragment.this.f3717c.findViewById(EnergyManageFragment.this.I[1]).getY();
            }
            y2 += y;
            EnergyManageFragment.this.J.scrollTo(0, Math.round(y2));
        }
    }

    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment.this.P++;
        }
    }

    public class n implements View.OnClickListener {
        public n() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment.this.Q++;
        }
    }

    public class o implements View.OnClickListener {
        public o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((TextView) EnergyManageFragment.this.f3717c.findViewById(R.id.tv_test_msg)).setText("车型：" + EnergyManageFragment.this.P + "**车型：0x" + Integer.toHexString(EnergyManageFragment.this.Q));
            EnergyManageFragment.this.n.d();
            EnergyAnimView energyAnimView = EnergyManageFragment.this.n;
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyAnimView.setEnergyStatus(false, energyManageFragment.P, energyManageFragment.Q);
            EnergyManageFragment.this.n.setTypeTurn(1);
            EnergyManageFragment.this.n.setCarBg();
            EnergyManageFragment.this.n.setBatteryProgress(60);
            EnergyManageFragment.this.n.setAlphaAnimation();
        }
    }

    public class p implements View.OnClickListener {
        public p() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.p0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).j(), true);
        }
    }

    public class q implements View.OnClickListener {
        public q() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.p0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).j(), true);
        }
    }

    public class r implements View.OnClickListener {
        public r() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.c) EnergyManageFragment.this.mo7getP()).I(1);
        }
    }

    public class s implements View.OnClickListener {
        public s() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.m0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).g());
        }
    }

    public class t implements View.OnClickListener {
        public t() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.g.r.e.c(150009)) {
                return;
            }
            EnergyManageFragment energyManageFragment = EnergyManageFragment.this;
            energyManageFragment.m0(((b.b.g.n.n.c) energyManageFragment.mo7getP()).g());
        }
    }

    public static class u extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<EnergyManageFragment> f3255a;

        public u(EnergyManageFragment energyManageFragment) {
            this.f3255a = new WeakReference<>(energyManageFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (this.f3255a.get() == null || this.f3255a.get().n == null) {
                return;
            }
            try {
                int i = message.what;
                if (i == 0) {
                    this.f3255a.get().n.setLineTipsViewGone();
                } else if (i == 1) {
                    this.f3255a.get().n.setLineTipsViewVisible();
                } else if (i == 2) {
                    if (this.f3255a.get().f3717c != null && this.f3255a.get().f3717c.findViewById(R.id.ll_keepbattery) != null && this.f3255a.get().f3717c.findViewById(R.id.ll_keepbattery).getVisibility() == 0) {
                        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                        alphaAnimation.setDuration(1000L);
                        this.f3255a.get().f3717c.findViewById(R.id.ll_keepbattery).startAnimation(alphaAnimation);
                        this.f3255a.get().f3717c.findViewById(R.id.ll_keepbattery).setVisibility(8);
                    }
                } else if (i == 3 && this.f3255a.get().f3717c != null && this.f3255a.get().f3717c.findViewById(R.id.btn_test_show) != null) {
                    ((TextView) this.f3255a.get().f3717c.findViewById(R.id.btn_test_show)).setText(System.currentTimeMillis() + "**能量状态：0x" + Integer.toHexString(b.b.g.n.q.e.j().g()) + "**轮胎转向:" + b.b.g.n.q.r.b().o());
                    this.f3255a.get().K.sendEmptyMessageDelayed(3, 500L);
                }
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.K = new u(this);
        DampingScrollView dampingScrollView = (DampingScrollView) this.f3717c.findViewById(R.id.scrollView);
        this.J = dampingScrollView;
        dampingScrollView.setOverScrollMode(2);
        this.n = (EnergyAnimView) this.f3717c.findViewById(R.id.energyAnimView);
        this.o = (SocSeekbar) this.f3717c.findViewById(R.id.seekbar_soc);
        this.p = (BydSwitch) this.f3717c.findViewById(R.id.switch_epedal);
        this.q = (BydSwitch) this.f3717c.findViewById(R.id.switch_sport);
        this.r = (BydSwitch) this.f3717c.findViewById(R.id.switch_ev);
        this.s = (BydSwitch) this.f3717c.findViewById(R.id.switch_electricity);
        this.u = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.switch_feedback_strength);
        this.v = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.switch_feedback_strength2);
        this.t = (BydSwitch) this.f3717c.findViewById(R.id.switch_soc);
        this.y = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.switch_cruising_range_mode);
        this.w = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.switch_cruising_range_standard);
        this.x = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.switch_display_mode);
        this.z = (TextView) this.f3717c.findViewById(R.id.btn_situ);
        this.A = (TextView) this.f3717c.findViewById(R.id.tv_soc_value);
    }

    public void A0(int i2) {
        this.R = i2;
        if (this.f3717c != null) {
            ((TextView) this.f3717c.findViewById(R.id.tv_test_msg)).setText("车型：" + this.P + "**车型：0x" + Integer.toHexString(this.Q) + "**轮胎转向:" + this.R);
        }
        EnergyAnimView energyAnimView = this.n;
        if (energyAnimView != null) {
            energyAnimView.setTypeTurn(i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void B0() {
        this.n.setEnergyStatus(((b.b.g.n.n.c) mo7getP()).B(), ((b.b.g.n.n.c) mo7getP()).d(), ((b.b.g.n.n.c) mo7getP()).l());
        this.n.setTypeTurn(((b.b.g.n.n.c) mo7getP()).w());
        this.n.setCarBg();
        this.n.setBatteryProgress(((b.b.g.n.n.c) mo7getP()).h());
        if (this.f3720f) {
            return;
        }
        this.n.setAlphaAnimation();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void C0(boolean z, int i2) {
        if (z && this.f3717c != null) {
            ((ImageView) this.f3717c.findViewById(R.id.iv_keepbattery)).setImageDrawable(ContextCompat.getDrawable(getContext(), z ? R.drawable.icon_dark_carsetting_keepbattery_24 : R.drawable.icon_dark_carsetting_smartbattery_24));
            ((TextView) this.f3717c.findViewById(R.id.tv_keepbattery)).setText(getContext().getResources().getString(z ? R.string.newenergy_power_protect_force : R.string.newenergy_power_protect_smart));
            this.f3717c.findViewById(R.id.ll_keepbattery).setVisibility(0);
        }
        E0(z, this.F[0], ((b.b.g.n.n.c) mo7getP()).u(), i2, this.F[1]);
    }

    public final void D0(boolean z) {
        if (this.f3717c == null) {
            return;
        }
        this.o.setOnSeekBarChangeListener(z ? new k() : null);
        this.f3717c.findViewById(R.id.iv_soc_hint).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_cruising_hint).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.iv_situ_hint).setOnClickListener(z ? this : null);
        this.z.setOnClickListener(z ? this : null);
        this.p.setOnInterceptTouchListener(z ? this : null);
        this.q.setOnInterceptTouchListener(z ? this : null);
        this.r.setOnInterceptTouchListener(z ? this : null);
        this.s.setOnInterceptTouchListener(z ? this : null);
        this.u.setOnPositionChangedListener(z ? this : null);
        this.v.setOnPositionChangedListener(z ? this : null);
        this.t.setOnInterceptTouchListener(z ? this : null);
        this.x.setOnPositionChangedListener(z ? this : null);
        this.y.setOnMyClickListener(z ? this : null);
        this.w.setOnPositionChangedListener(z ? this : null);
        this.P = 0;
        this.Q = 0;
        this.f3717c.findViewById(R.id.btn_test_config).setOnClickListener(new m());
        this.f3717c.findViewById(R.id.btn_test_status).setOnClickListener(new n());
        this.f3717c.findViewById(R.id.btn_test_show).setOnClickListener(new o());
    }

    public final void E0(boolean z, int i2, int i3, int i4, int i5) {
        int i6 = i3;
        int iMax = i4;
        int i7 = 0;
        if (this.f3717c != null) {
            this.A.setVisibility(z ? 0 : 4);
            b.b.a.h.k.f(this.f3717c.findViewById(R.id.rl_soc_seekbar), !z);
        }
        BydLogHelper.logI(this.m, "[initProgress]----original data: min = " + i2 + ",lowLimit = " + i6 + ",target = " + iMax + ",max = " + i5);
        if (i6 < i2) {
            i6 = i2;
        }
        if (i6 > i5) {
            i6 = i5;
        }
        if (iMax < i2) {
            iMax = i2;
        }
        if (iMax > i5) {
            iMax = i5;
        }
        if (z) {
            iMax = Math.max(i6, iMax);
        } else {
            if (iMax < i6) {
                i6 = iMax;
            }
            i7 = 2;
        }
        int i8 = i7;
        BydLogHelper.logI(this.m, "[initProgress]----handle data: min = " + i2 + ",lowLimit = " + i6 + ",target = " + iMax + ",max = " + i5);
        int i9 = i5 - i2;
        float f2 = (float) i9;
        float f3 = (((float) (i6 - i2)) * 1.0f) / f2;
        float f4 = (((float) (iMax - i6)) * 1.0f) / f2;
        this.o.setProgress(i8, f3, f4, (1.0f - f3) - f4, i9);
        this.o.postInvalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void F0() {
        B0();
        this.E = true;
        this.f3717c.findViewById(R.id.ll_soc).setVisibility(((b.b.g.n.n.c) mo7getP()).D() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_situ).setVisibility(((b.b.g.n.n.c) mo7getP()).E() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_epedal).setVisibility(((b.b.g.n.n.c) mo7getP()).A() ? 0 : 8);
        this.f3717c.findViewById(R.id.tv_cruising_range_mode).setVisibility(((b.b.g.n.n.c) mo7getP()).y() ? 0 : 8);
        this.f3717c.findViewById(R.id.switch_cruising_range_mode).setVisibility(((b.b.g.n.n.c) mo7getP()).y() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_cruising_range_standard).setVisibility(((b.b.g.n.n.c) mo7getP()).z() ? 0 : 8);
        this.w.setVisibility(((b.b.g.n.n.c) mo7getP()).z() ? 0 : 8);
        this.f3717c.findViewById(R.id.tv_display_mode).setVisibility(((b.b.g.n.n.c) mo7getP()).C() ? 0 : 8);
        this.x.setVisibility(((b.b.g.n.n.c) mo7getP()).C() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_memory).setVisibility(((b.b.g.n.n.c) mo7getP()).B() ? 8 : 0);
        this.u.setVisibility(((b.b.g.n.n.c) mo7getP()).F() ? 8 : 0);
        this.v.setVisibility(((b.b.g.n.n.c) mo7getP()).F() ? 0 : 8);
        this.F = ((b.b.g.n.n.c) mo7getP()).r();
        ((TextView) this.f3717c.findViewById(R.id.tv_soc_start)).setText(this.F[0] + "%");
        ((TextView) this.f3717c.findViewById(R.id.tv_soc_end)).setText(this.F[1] + "%");
    }

    public boolean G0() {
        return isVisible();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: H0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.n.c newP() {
        return new b.b.g.n.n.c();
    }

    public final void I0() {
        b.b.g.q.d.m.i(getContext(), "EnergyManageFragment_label_cruising", getContext().getResources().getString(R.string.newenergy_ev_mileage_display_standard), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null, true, LayoutInflater.from(getContext()).inflate(R.layout.dialog_cruising_hint, (ViewGroup) null)).C();
    }

    public final void J0() {
        b.b.g.r.e.h(150014);
        b.b.g.q.d.l lVarH = b.b.g.q.d.m.h(getContext(), "EnergyManageFragment_label_ev", getContext().getResources().getString(R.string.newenergy_memory_mode_max_ev), getContext().getResources().getString(R.string.newenergy_memory_mode_max_ev_hint_message), getContext().getResources().getString(R.string.newenergy_memory_mode_open), getContext().getResources().getString(R.string.dialog_btn_cancel), new d(), new e());
        lVarH.setDismissListener(new f());
        lVarH.C();
    }

    public final void K0() {
        b.b.g.r.e.h(150019);
        b.b.g.q.d.l lVarH = b.b.g.q.d.m.h(getContext(), "EnergyManageFragment_label_electricity", getContext().getResources().getString(R.string.energy_save_memory), getContext().getResources().getString(R.string.newenergy_memory_mode_save_hint_message), getContext().getResources().getString(R.string.newenergy_memory_mode_open), getContext().getResources().getString(R.string.dialog_btn_cancel), new g(), new h());
        lVarH.setDismissListener(new i());
        lVarH.C();
    }

    public final void L0() {
        b.b.g.r.e.h(150009);
        b.b.g.q.d.l lVarH = b.b.g.q.d.m.h(getContext(), "EnergyManageFragment_label_epedal", getContext().getResources().getString(R.string.vehiclehealth_tips), getContext().getResources().getString(R.string.newenergy_e_pedal_open_dialog_message), getContext().getResources().getString(R.string.newenergy_memory_mode_open), getContext().getResources().getString(R.string.dialog_btn_cancel), new r(), new s());
        lVarH.setDismissListener(new t());
        lVarH.C();
    }

    public final void M0(int i2) {
        b.b.g.q.d.m.f1481c.put("EnergyManageFragment_label_feedback_strength", Integer.valueOf(i2));
        b.b.g.q.d.m.h(getContext(), "EnergyManageFragment_label_feedback_strength", getContext().getResources().getString(R.string.vehiclehealth_tips), getContext().getResources().getString(R.string.newenergy_e_cannot_set_energy_recycle_dialog_message), getContext().getResources().getString(R.string.dialog_btn_ok), null, new p(), new q()).C();
    }

    public final void N0() {
        b.b.g.q.d.m.g(getContext(), "EnergyManageFragment_label_situ", getContext().getResources().getString(R.string.energy_situ_elec), getContext().getResources().getString(R.string.energy_situ_elec_desc), GravityCompat.START, getContext().getResources().getString(R.string.dialog_btn_ok), getContext().getResources().getString(R.string.dialog_btn_cancel), new j(), null).C();
    }

    public final void O0() {
        b.b.g.q.d.m.f(getContext(), "EnergyManageFragment_label_soc", getContext().getResources().getString(R.string.newenergy_soc_save_title), getContext().getResources().getString(R.string.newenergy_soc_desc), GravityCompat.START, getResources().getDimensionPixelSize(R.dimen.sp_18), getContext().getResources().getString(R.string.dialog_btn_ok), null, null, null).C();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.BydSwitch.a
    public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
        if (view.isEnabled()) {
            ((BydSwitch) view).setChecked(z, false);
            b.b.g.o.f.d(view, 0);
            switch (view.getId()) {
                case R.id.switch_electricity /* 2131298236 */:
                    if (!z) {
                        ((b.b.g.n.n.c) mo7getP()).M(1);
                    } else {
                        K0();
                    }
                    break;
                case R.id.switch_epedal /* 2131298239 */:
                    if (!z) {
                        ((b.b.g.n.n.c) mo7getP()).I(2);
                    } else {
                        L0();
                    }
                    break;
                case R.id.switch_ev /* 2131298240 */:
                    if (!z) {
                        ((b.b.g.n.n.c) mo7getP()).N(1);
                    } else {
                        J0();
                    }
                    break;
                case R.id.switch_soc /* 2131298277 */:
                    C0(z, ((b.b.g.n.n.c) mo7getP()).s());
                    ((b.b.g.n.n.c) mo7getP()).L(z ? 2 : 1);
                    break;
                case R.id.switch_sport /* 2131298278 */:
                    if (!z) {
                        ((b.b.g.n.n.c) mo7getP()).O(1);
                    } else {
                        P0();
                    }
                    break;
            }
        }
    }

    public final void P0() {
        b.b.g.r.e.h(150013);
        b.b.g.q.d.l lVarH = b.b.g.q.d.m.h(getContext(), "EnergyManageFragment_label_sport", getContext().getResources().getString(R.string.newenergy_memory_mode_sport), getContext().getResources().getString(R.string.newenergy_memory_mode_sport_hint_msg), getContext().getResources().getString(R.string.newenergy_memory_mode_open), getContext().getString(R.string.dialog_btn_cancel), new a(), new b());
        lVarH.setDismissListener(new c());
        lVarH.C();
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_energy_manage;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_energy_manage;
    }

    public void k0(int i2, boolean z) {
        BydLogHelper.logI(this.m, "freshDriveDistanceShowMode----" + i2 + "----" + z);
        if (i2 == 1) {
            this.y.setPosition(0, z);
        } else if (i2 == 2) {
            this.y.setPosition(1, z);
        }
    }

    public void l0(int i2, boolean z) {
        if (i2 == 1) {
            this.w.setPosition(1, z);
        } else if (i2 == 2) {
            this.w.setPosition(0, z);
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_energy_manage_byd;
    }

    public void m0(int i2) {
        if (i2 == 1) {
            this.p.setChecked(true, false);
        } else if (i2 == 2) {
            this.p.setChecked(false, false);
        }
    }

    public void n0(int i2) {
        EnergyAnimView energyAnimView = this.n;
        if (energyAnimView != null) {
            energyAnimView.setBatteryProgress(i2);
        }
    }

    public void o0(int i2, boolean z) {
        if (i2 == 1 || i2 == 2) {
            this.x.setPosition(0, z);
            this.x.setPosition(i2 == 1 ? 0 : 1, z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : b.b.g.q.d.m.f1480b) {
            str.hashCode();
            switch (str) {
                case "EnergyManageFragment_label_feedback_strength":
                    int iIntValue = b.b.g.q.d.m.f1481c.get("EnergyManageFragment_label_feedback_strength") != null ? ((Integer) b.b.g.q.d.m.f1481c.get("EnergyManageFragment_label_feedback_strength")).intValue() : 0;
                    if (this.u.getVisibility() == 0) {
                        this.u.setPosition(iIntValue, false);
                    }
                    if (this.v.getVisibility() == 0) {
                        this.v.setPosition(iIntValue, false);
                    }
                    M0(iIntValue != 0 ? 0 : 1);
                    break;
                case "EnergyManageFragment_label_epedal":
                    this.p.setChecked(true, false);
                    L0();
                    break;
                case "EnergyManageFragment_label_sport":
                    this.q.setChecked(true, false);
                    P0();
                    break;
                case "EnergyManageFragment_label_electricity":
                    this.s.setChecked(true, false);
                    K0();
                    break;
                case "EnergyManageFragment_label_cruising":
                    I0();
                    break;
                case "EnergyManageFragment_label_situ":
                    N0();
                    break;
                case "EnergyManageFragment_label_soc":
                    O0();
                    break;
                case "EnergyManageFragment_label_ev":
                    this.r.setChecked(true, false);
                    J0();
                    break;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (b.b.m.g.c.a(300L)) {
        }
        switch (view.getId()) {
            case R.id.btn_situ /* 2131296543 */:
                this.B = !this.B;
                ((b.b.g.n.n.c) mo7getP()).Q(this.B ? 1 : 2);
                this.z.setText(getResources().getString(this.B ? R.string.energy_situ_elec_stop : R.string.energy_situ_elec_start));
                break;
            case R.id.iv_cruising_hint /* 2131297228 */:
                I0();
                break;
            case R.id.iv_situ_hint /* 2131297352 */:
                N0();
                break;
            case R.id.iv_soc_hint /* 2131297353 */:
                O0();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        D0(false);
        Handler handler = this.K;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        EnergyAnimView energyAnimView = this.n;
        if (energyAnimView != null) {
            energyAnimView.d();
        }
        super.onDestroyView();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        b.b.m.c.a.d(this.m, "[Memory]----[Anim]----[EnergyManagePager]----onPause");
        Handler handler = this.K;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        EnergyAnimView energyAnimView = this.n;
        if (energyAnimView != null) {
            this.E = false;
            energyAnimView.d();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i2) {
        switch (view.getId()) {
            case R.id.switch_cruising_range_standard /* 2131298231 */:
                ((b.b.g.n.n.c) mo7getP()).H(i2 == 0 ? 2 : 1);
                break;
            case R.id.switch_display_mode /* 2131298233 */:
                ((b.b.g.n.n.c) mo7getP()).J(i2 != 0 ? 2 : 1);
                break;
            case R.id.switch_feedback_strength /* 2131298242 */:
            case R.id.switch_feedback_strength2 /* 2131298243 */:
                if (this.f3717c.findViewById(R.id.ll_epedal).getVisibility() == 0 && this.p.isChecked()) {
                    M0(i2);
                } else if (i2 == 0) {
                    ((b.b.g.n.n.c) mo7getP()).K(2);
                } else if (i2 == 1) {
                    ((b.b.g.n.n.c) mo7getP()).K(3);
                } else if (i2 == 2) {
                    ((b.b.g.n.n.c) mo7getP()).K(4);
                }
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        String str = this.m;
        String[] strArr = new String[1];
        StringBuilder sb = new StringBuilder();
        sb.append("[Memory]----[Anim]----[EnergyManagePager]----resume----mEnergyAnimView != null:");
        sb.append(this.n != null);
        strArr[0] = sb.toString();
        b.b.m.c.a.d(str, strArr);
        EnergyAnimView energyAnimView = this.n;
        if (energyAnimView == null || this.E) {
            return;
        }
        energyAnimView.d();
        B0();
    }

    public void p0(int i2, boolean z) {
        if (i2 == 2) {
            if (this.u.getVisibility() == 0) {
                this.u.setPosition(0, z);
            }
            if (this.v.getVisibility() == 0) {
                this.v.setPosition(0, z);
                return;
            }
            return;
        }
        if (i2 != 3) {
            if (i2 == 4 && this.v.getVisibility() == 0) {
                this.v.setPosition(2, z);
                return;
            }
            return;
        }
        if (this.u.getVisibility() == 0) {
            this.u.setPosition(1, z);
        }
        if (this.v.getVisibility() == 0) {
            this.v.setPosition(1, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void q0(int i2) {
        if (this.f3717c != null) {
            ((TextView) this.f3717c.findViewById(R.id.tv_test_msg)).setText("车型：" + this.P + "**车型：0x" + Integer.toHexString(this.Q) + "**轮胎转向:" + this.R);
        }
        EnergyAnimView energyAnimView = this.n;
        if (energyAnimView != null) {
            energyAnimView.setEnergyStatus(((b.b.g.n.n.c) mo7getP()).B(), ((b.b.g.n.n.c) mo7getP()).d(), i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void r0(int i2, boolean z, boolean z2) {
        if (i2 == 1 || i2 == 2) {
            this.t.setChecked(i2 == 2, false);
            if (this.G || !z || z2) {
                BydLogHelper.logI(this.m, "intercept----freshMandatoryElectricState----mode:" + i2 + ",anim:" + z);
            } else {
                E0(this.t.isChecked(), this.F[0], ((b.b.g.n.n.c) mo7getP()).u(), ((b.b.g.n.n.c) mo7getP()).s(), this.F[1]);
            }
            if (z) {
                this.K.sendEmptyMessage(2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.e
    public void s(View view, int i2) {
        if (view.getId() != R.id.switch_cruising_range_mode) {
            return;
        }
        if (b.b.a.h.k.a(this.y)) {
            z.b(getContext(), R.string.energy_trailer_range_display);
        } else {
            this.y.setPosition(i2);
            ((b.b.g.n.n.c) mo7getP()).G(i2 == 0 ? 1 : 2);
        }
    }

    public void s0(int i2) {
        int i3 = 0;
        if (i2 == 2) {
            this.s.setChecked(true, false);
        } else if (i2 == 1) {
            this.s.setChecked(false, false);
        }
        if (this.f3717c != null) {
            View viewFindViewById = this.f3717c.findViewById(R.id.ll_electricity);
            if (i2 != 2 && i2 != 1) {
                i3 = 8;
            }
            viewFindViewById.setVisibility(i3);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        super.t(i2);
        DampingScrollView dampingScrollView = this.J;
        if (dampingScrollView != null) {
            dampingScrollView.post(new l(i2));
        }
    }

    public void t0(int i2) {
        int i3 = 0;
        if (i2 == 2) {
            this.r.setChecked(true, false);
        } else if (i2 == 1) {
            this.r.setChecked(false, false);
        }
        if (this.f3717c != null) {
            View viewFindViewById = this.f3717c.findViewById(R.id.ll_maxev);
            if (i2 != 2 && i2 != 1) {
                i3 = 8;
            }
            viewFindViewById.setVisibility(i3);
        }
    }

    public void u0(int i2) {
        int i3 = 0;
        if (i2 == 2) {
            this.q.setChecked(true, false);
        } else if (i2 == 1) {
            this.q.setChecked(false, false);
        }
        if (this.f3717c != null) {
            View viewFindViewById = this.f3717c.findViewById(R.id.ll_sport);
            if (i2 != 2 && i2 != 1) {
                i3 = 8;
            }
            viewFindViewById.setVisibility(i3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void v0(int i2, boolean z) {
        if (this.G) {
            BydLogHelper.logI(this.m, "intercept----freshSOCTarget----progress:" + i2);
            return;
        }
        E0(this.t.isChecked(), this.F[0], ((b.b.g.n.n.c) mo7getP()).u(), i2, this.F[1]);
        this.A.setText(i2 + "%");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void w0(int i2) {
        int i3 = 0;
        if (i2 == 1) {
            this.B = true;
            this.z.setText(getResources().getString(R.string.energy_situ_elec_stop));
        } else if (i2 == 2) {
            this.B = false;
            this.z.setText(getResources().getString(R.string.energy_situ_elec_start));
        }
        if (this.f3717c != null) {
            View viewFindViewById = this.f3717c.findViewById(R.id.ll_situ);
            if (((b.b.g.n.n.c) mo7getP()).B() || (i2 != 1 && i2 != 2)) {
                i3 = 8;
            }
            viewFindViewById.setVisibility(i3);
        }
    }

    public void x0(int i2) {
        if (i2 == 1 || i2 == 2) {
            this.z.setEnabled(i2 == 1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        ((b.b.g.n.n.c) mo7getP()).x();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void y0(int i2) {
        E0(this.t.isChecked(), this.F[0], i2, ((b.b.g.n.n.c) mo7getP()).s(), this.F[1]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        D0(true);
        ((b.b.g.n.n.c) mo7getP()).R();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void z0(int i2) {
        if (((b.b.g.n.n.c) mo7getP()).B()) {
            if (i2 == 0) {
                b.b.a.h.k.b(this.y);
            } else if (i2 == 1) {
                b.b.a.h.k.c(this.y);
            }
        }
    }
}
