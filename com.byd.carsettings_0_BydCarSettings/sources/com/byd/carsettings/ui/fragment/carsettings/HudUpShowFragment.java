package com.byd.carsettings.ui.fragment.carsettings;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import b.b.a.h.l;
import b.b.g.n.h.j;
import b.b.g.r.e;
import b.b.g.r.i;
import b.b.g.r.s;
import b.b.o.a.b.d;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class HudUpShowFragment extends XFragment<j> implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, SegmentedButtonGroup.f, b.b.o.b.a.a {
    public DampingScrollView A;
    public RelativeLayout B;
    public int D;
    public CheckBox E;
    public CheckBox F;
    public ImageView G;
    public Handler H;
    public b.b.o.a.b.a I;
    public int J;
    public Context m;
    public BydSwitch n;
    public SeekBar o;
    public SeekBar p;
    public SeekBar q;
    public TextView r;
    public TextView s;
    public TextView t;
    public RadioGroup u;
    public SegmentedButtonGroup v;
    public SegmentedButtonGroup w;
    public LinearLayout x;
    public RelativeLayout y;
    public RelativeLayout z;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 250005:
                    if (HudUpShowFragment.this.o != null) {
                        ((j) HudUpShowFragment.this.mo7getP()).n(HudUpShowFragment.this.o.getProgress());
                    }
                    e.i(60001);
                    break;
                case 250006:
                    if (HudUpShowFragment.this.p != null) {
                        ((j) HudUpShowFragment.this.mo7getP()).l(HudUpShowFragment.this.p.getProgress());
                    }
                    e.i(60002);
                    break;
                case 250007:
                    if (HudUpShowFragment.this.q != null) {
                        ((j) HudUpShowFragment.this.mo7getP()).q(HudUpShowFragment.this.q.getProgress());
                    }
                    e.i(60003);
                    break;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = context;
        int iG = ((j) mo7getP()).g();
        Q();
        V();
        S();
        T();
        R(iG == 1);
        W(iG);
        U(iG);
        this.A = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        EventBus.getDefault().register(this);
    }

    public final void N() {
        l.y(null, this.n);
        l.s(null, this.o, this.p, this.q);
        l.t(null, this.v);
        l.E(null, this.F, this.E);
    }

    public int O(int i, int i2) {
        if (this.I == null) {
            this.I = d.a("hud");
        }
        return this.I.a(i, i2);
    }

    public final String P(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(i.b((i - 6) * 0.4f, (i == 6 || i == 11 || i == 1) ? 0 : 1));
        sb.append((char) 176);
        return sb.toString();
    }

    public final void Q() {
        this.x = (LinearLayout) this.f3717c.findViewById(R.id.uphead_display_params_container);
        this.z = (RelativeLayout) this.f3717c.findViewById(R.id.uphead_display_visible_container);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void R(boolean z) {
        if (z) {
            this.t = (TextView) this.f3717c.findViewById(R.id.uphead_display_angle_unit);
            this.q = (SeekBar) this.f3717c.findViewById(R.id.uphead_display_angle_sb);
            this.B = (RelativeLayout) this.f3717c.findViewById(R.id.hud_angle_container);
            l.B(this.t, P(((j) mo7getP()).h()));
            l.q(this.q, ((j) mo7getP()).h());
            l.s(this, this.q);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void S() {
        this.r = (TextView) this.f3717c.findViewById(R.id.uphead_display_height_level);
        this.o = (SeekBar) this.f3717c.findViewById(R.id.uphead_display_height_sb);
        l.B(this.r, String.valueOf(((j) mo7getP()).e() - 11));
        l.q(this.o, ((j) mo7getP()).e());
        l.s(this, this.o);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void T() {
        this.p = (SeekBar) this.f3717c.findViewById(R.id.uphead_display_light_sb);
        TextView textView = (TextView) this.f3717c.findViewById(R.id.uphead_display_light_level);
        this.s = textView;
        l.B(textView, String.valueOf(((j) mo7getP()).c()));
        l.q(this.p, ((j) mo7getP()).c());
        l.s(this, this.p);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void U(int i) {
        this.y = (RelativeLayout) this.f3717c.findViewById(R.id.upheader_display_mode_container);
        if (i == 1) {
            SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.uphead_display_mode_sbg_hud);
            this.w = segmentedButtonGroup;
            l.t(this, segmentedButtonGroup);
        }
        if (i == 2) {
            SegmentedButtonGroup segmentedButtonGroup2 = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.uphead_display_mode_sbg);
            this.v = segmentedButtonGroup2;
            l.t(this, segmentedButtonGroup2);
        }
        b0(i, ((j) mo7getP()).i());
        Z(((j) mo7getP()).f(), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V() {
        BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.uphead_display_sw);
        this.n = bydSwitch;
        bydSwitch.setChecked(((j) mo7getP()).i(), false);
        l.y(this, this.n);
    }

    public final void W(int i) {
        this.u = (RadioGroup) this.f3717c.findViewById(R.id.headup_display_rg);
        this.E = (CheckBox) this.f3717c.findViewById(R.id.headup_display_drive_help);
        if (i == 1) {
            CheckBox checkBox = (CheckBox) this.f3717c.findViewById(R.id.headup_display_lead);
            this.F = checkBox;
            l.E(this, checkBox);
        }
        l.E(this, this.E);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public j newP() {
        return new j();
    }

    public void Y(Context context, int i, boolean z, boolean z2) {
        if (z) {
            l.l(this.G, i, context, true, false);
        }
    }

    public final void Z(int i, int i2) {
        if (i2 == 1) {
            this.w.setPositionWithOutCallback(i - 1, false);
            Y(this.m, O(27, i), true, false);
        } else {
            if (i2 != 2) {
                return;
            }
            this.v.setPositionWithOutCallback(i - 1, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a0() {
        this.G = (ImageView) this.f3717c.findViewById(R.id.uphead_display_image);
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        this.J = iA;
        Y(this.m, O(iA, ((j) mo7getP()).i() ? ((j) mo7getP()).f() : 4), true, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(int i, boolean z) {
        boolean z2;
        if (i == 1) {
            l.J(z ? 0 : 8, this.B, this.y, this.z, this.x);
            l.K(false, this.v);
            l.K(true, this.w);
            boolean z3 = ((j) mo7getP()).d(1) == 1;
            z2 = ((j) mo7getP()).d(2) == 1;
            l.h(this.E, z3);
            l.i(this.E, z3 ? this.m.getColor(R.color.btn_only_selected) : this.m.getColor(R.color.btn_only_unselected));
            if (((j) mo7getP()).j()) {
                l.h(this.F, z2);
                CheckBox checkBox = this.F;
                Context context = this.m;
                l.i(checkBox, z2 ? context.getColor(R.color.btn_only_selected) : context.getColor(R.color.btn_only_unselected));
                return;
            }
            View childAt = this.u.getChildAt(1);
            if (childAt != null) {
                childAt.setVisibility(4);
                return;
            }
            return;
        }
        if (i != 2) {
            l.J(8, this.B, this.y, this.z, this.x);
            l.J(z ? 0 : 8, this.x);
            l.K(false, this.v);
            l.K(false, this.w);
            return;
        }
        l.J(z ? 0 : 8, this.y, this.z, this.x);
        l.J(8, this.B);
        l.K(true, this.v);
        l.K(false, this.w);
        View childAt2 = this.u.getChildAt(1);
        if (childAt2 != null) {
            childAt2.setVisibility(8);
        }
        z2 = ((j) mo7getP()).d(1) == 1;
        l.h(this.E, z2);
        CheckBox checkBox2 = this.E;
        Context context2 = this.m;
        l.i(checkBox2, z2 ? context2.getColor(R.color.btn_only_selected) : context2.getColor(R.color.btn_only_unselected));
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_uphead_display;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_uphead_display;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_uphead_display;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.headup_display_drive_help) {
            ((j) mo7getP()).m(1, z ? 1 : 2);
            this.E.setTextColor(z ? this.m.getColor(R.color.btn_only_selected) : this.m.getColor(R.color.btn_only_unselected));
            e.i(60005);
            return;
        }
        if (id == R.id.headup_display_lead) {
            ((j) mo7getP()).m(2, z ? 1 : 2);
            this.F.setTextColor(z ? this.m.getColor(R.color.btn_only_selected) : this.m.getColor(R.color.btn_only_unselected));
            e.i(60005);
        } else {
            if (id != R.id.uphead_display_sw) {
                return;
            }
            if (!l.a(this.n)) {
                BydLogHelper.logD("upHeadDisplaySw", "BydSwitch isEverTouch = false");
                return;
            }
            BydLogHelper.logD("HudSW", "isChecked = " + z);
            ((j) mo7getP()).r(z ? 1 : 2);
            Y(this.m, O(27, z ? ((j) mo7getP()).f() : 4), true, false);
            b0(((j) mo7getP()).g(), z);
            e.i(60000);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        e.a(this.H);
        N();
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 60000:
                BydLogHelper.logD("message : HUD_TOGGLE_STATE_CHANGE_MESSAGE " + aVar.c());
                int iC = aVar.c();
                if (e.e(aVar)) {
                    iC = ((j) mo7getP()).i() ? 1 : 2;
                }
                if (iC == 1 || iC == 2) {
                    boolean z = iC == 1;
                    this.n.setChecked(z, false);
                    Y(this.m, O(27, z ? ((j) mo7getP()).f() : 4), true, false);
                    b0(((j) mo7getP()).g(), z);
                }
                break;
            case 60001:
                BydLogHelper.logD("message : HUD_HEIGHT_CHANGE_MESSAGE " + aVar.c());
                int iC2 = aVar.c();
                if (e.e(aVar) && (iC2 = ((j) mo7getP()).e()) == 0) {
                    iC2 = 11;
                }
                if (iC2 >= 1 && iC2 <= 21) {
                    this.o.setProgress(iC2);
                    l.B(this.r, String.valueOf(iC2 - 11));
                    break;
                }
                break;
            case 60002:
                BydLogHelper.logD("message : HUD_BRIGHTNESS_CHANGE_MESSAGE " + aVar.c());
                int iC3 = aVar.c();
                if (e.e(aVar) && (iC3 = ((j) mo7getP()).c()) == 0) {
                    iC3 = 1;
                }
                if (iC3 >= 1 && iC3 <= 11) {
                    this.p.setProgress(iC3);
                    l.B(this.s, String.valueOf(iC3));
                    break;
                }
                break;
            case 60003:
                BydLogHelper.logD("message : HUD_ANGLE_CHANGE_MESSAGE " + aVar.c());
                int iC4 = aVar.c();
                if (e.e(aVar) && (iC4 = ((j) mo7getP()).h()) == 0) {
                    iC4 = 1;
                }
                if (iC4 >= 1 && iC4 <= 11) {
                    this.q.setProgress(iC4);
                    l.B(this.t, P(iC4));
                    break;
                }
                break;
            case 60004:
                int iG = ((j) mo7getP()).g();
                if (iG == 1 || iG == 2) {
                    BydLogHelper.logD("message : HUD_MODE_CHANGE_MESSAGE " + aVar.c());
                    int iC5 = aVar.c();
                    int i = iG == 1 ? 2 : 3;
                    if (e.e(aVar)) {
                        iC5 = ((j) mo7getP()).f();
                    }
                    if (iC5 > 0 && iC5 <= i) {
                        Z(iC5, iG);
                    }
                    this.u.getChildAt(0).setSelected(((j) mo7getP()).d(1) == 1);
                    if (this.D == 2) {
                        this.u.getChildAt(1).setSelected(((j) mo7getP()).d(2) == 1);
                    }
                }
                break;
            case 60005:
                BydLogHelper.logD("message : HUD_MODE_DISPLAY_CHANGE_MESSAGE " + aVar.c() + "args2 " + aVar.d());
                int iD = aVar.d();
                if (e.e(aVar)) {
                    this.E.setChecked(((j) mo7getP()).d(1) == 1);
                    this.F.setChecked(((j) mo7getP()).d(2) == 1);
                } else if (iD == 1 || iD == 2) {
                    if (aVar.c() == 1) {
                        this.E.setChecked(iD == 1);
                    }
                    if (aVar.c() == 2) {
                        this.F.setChecked(iD == 1);
                    }
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        ((j) mo7getP()).o(i);
        Y(this.m, O(27, i + 1), true, false);
        e.i(60004);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            l.r(false, this.A);
            int id = seekBar.getId();
            if (id == R.id.uphead_display_angle_sb) {
                l.B(this.t, P(i));
                e.i(60003);
                e.n(250007, this.H);
            } else if (id == R.id.uphead_display_height_sb) {
                l.B(this.r, String.valueOf(i - 11));
                e.i(60001);
                e.n(250005, this.H);
            } else {
                if (id != R.id.uphead_display_light_sb) {
                    return;
                }
                l.B(this.s, String.valueOf(i));
                e.i(60002);
                e.n(250006, this.H);
            }
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        int id = seekBar.getId();
        if (id == R.id.uphead_display_angle_sb) {
            e.i(60003);
        } else if (id == R.id.uphead_display_height_sb) {
            e.i(60001);
        } else {
            if (id != R.id.uphead_display_light_sb) {
                return;
            }
            e.i(60002);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        l.r(true, this.A);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        s.a(this.A, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        this.H = new a(Looper.myLooper());
        a0();
    }
}
