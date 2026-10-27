package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import b.b.a.h.l;
import b.b.g.n.h.m;
import b.b.g.q.d.i;
import b.b.g.q.d.k;
import b.b.g.q.d.q;
import b.b.g.r.s;
import b.b.g.r.z;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.CountDownTimerButton;
import com.byd.carsettings.ui.widget.AreaableSeekbar;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class SmtRemindFragment extends XFragment<m> implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener, SegmentedButtonGroup.f, b.b.o.b.a.a {
    public ImageView A;
    public b.b.g.q.d.f B;
    public b.b.g.n.o.b D;
    public RelativeLayout E;
    public SegmentedButtonGroup F;
    public RelativeLayout G;
    public BydSwitch H;
    public RelativeLayout I;
    public Handler J = new Handler(Looper.myLooper());
    public b.b.o.a.b.a K;
    public ImageView L;
    public int M;
    public RelativeLayout N;
    public ImageView O;
    public SegmentedButtonGroup P;
    public RelativeLayout Q;
    public i R;
    public Dialog S;
    public Context m;
    public BydSwitch n;
    public TextView o;
    public AreaableSeekbar p;
    public RelativeLayout q;
    public RelativeLayout r;
    public BydSwitch s;
    public DampingScrollView t;
    public ImageView u;
    public ImageView v;
    public SegmentedButtonGroup w;
    public b.b.g.q.d.g x;
    public BydSwitch y;
    public RelativeLayout z;

    public class a implements BydSwitch.a {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            SmtRemindFragment.this.s.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (!z) {
                SmtRemindFragment.this.f0();
                return;
            }
            SmtRemindFragment.this.n.setChecked(z, false);
            ((m) SmtRemindFragment.this.mo7getP()).C(1);
            b.b.g.r.e.i(80006);
        }
    }

    public class b implements BydSwitch.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3108a;

        public b(Context context) {
            this.f3108a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.base.view.BydSwitch.a
        public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
            SmtRemindFragment.this.s.playSoundEffect(0);
            if (motionEvent.getAction() != 1) {
                return;
            }
            if (!z) {
                ((m) SmtRemindFragment.this.mo7getP()).w(this.f3108a, false);
                SmtRemindFragment.this.s.setChecked(false, false);
                l.J(8, SmtRemindFragment.this.r, SmtRemindFragment.this.q, SmtRemindFragment.this.p);
            } else {
                if (SmtRemindFragment.this.W()) {
                    SmtRemindFragment.this.e0();
                }
                ((m) SmtRemindFragment.this.mo7getP()).w(this.f3108a, true);
                SmtRemindFragment.this.s.setChecked(true, false);
                l.J(0, SmtRemindFragment.this.r, SmtRemindFragment.this.q, SmtRemindFragment.this.p);
            }
        }
    }

    public class c implements b.b.g.n.o.c {
        public c() {
        }

        @Override // b.b.g.n.o.c
        public void a(String str, int i) {
            if (str == "sound_effects_enabled") {
                SmtRemindFragment.this.y.setChecked(i == 1, false);
            } else if (str == "byd_report_toggle") {
                l.J(i == 1 ? 0 : 8, SmtRemindFragment.this.r, SmtRemindFragment.this.q, SmtRemindFragment.this.p);
                SmtRemindFragment.this.s.setChecked(i == 1, false);
            }
        }
    }

    public class d implements q.b {
        public d() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            SmtRemindFragment.this.S.dismiss();
            SmtRemindFragment.this.S = null;
        }
    }

    public class e implements q.b {
        public e() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            SmtRemindFragment.this.S.dismiss();
            SmtRemindFragment.this.S = null;
        }
    }

    public class f implements q.b {
        public f() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.d.q.b
        public void a() {
            SmtRemindFragment.this.S.dismiss();
            SmtRemindFragment.this.n.setChecked(false, false);
            ((m) SmtRemindFragment.this.mo7getP()).C(2);
            b.b.g.r.e.i(80006);
        }
    }

    public class g implements q.a {
        public g() {
        }

        @Override // b.b.g.q.d.q.a
        public void a() {
            SmtRemindFragment.this.S.dismiss();
        }
    }

    public class h implements q.c {
        public h() {
        }

        @Override // b.b.g.q.d.q.c
        public void a() {
            k.e("reminder_exhibixtion_mode");
            if (SmtRemindFragment.this.S != null) {
                SmtRemindFragment.this.S.dismiss();
            }
        }

        @Override // b.b.g.q.d.q.c
        public void b() {
            k.e("reminder_exhibixtion_mode");
            if (SmtRemindFragment.this.S != null) {
                SmtRemindFragment.this.S.dismiss();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.L = (ImageView) this.f3717c.findViewById(R.id.smart_reminder_image);
        this.u = (ImageView) this.f3717c.findViewById(R.id.smart_reminder_content_imge);
        this.v = (ImageView) this.f3717c.findViewById(R.id.auto_icon);
        this.p = (AreaableSeekbar) this.f3717c.findViewById(R.id.smart_reminder_volume);
        this.o = (TextView) this.f3717c.findViewById(R.id.smart_reminder_percent);
        this.n = (BydSwitch) this.f3717c.findViewById(R.id.smart_reminder_nav_sw);
        this.q = (RelativeLayout) this.f3717c.findViewById(R.id.smart_reminder_volume_container);
        this.r = (RelativeLayout) this.f3717c.findViewById(R.id.smart_reminder_content_container);
        this.s = (BydSwitch) this.f3717c.findViewById(R.id.smart_reminder_sw);
        this.w = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.smart_tips_segmentedButtonGroup);
        this.y = (BydSwitch) this.f3717c.findViewById(R.id.system_key_voice_sw);
        this.t = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        this.E = (RelativeLayout) this.f3717c.findViewById(R.id.smart_voice_container);
        this.F = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.vehiclesettings_control_touch_sbg);
        this.G = (RelativeLayout) this.f3717c.findViewById(R.id.vehiclesettings_control_touch_rl);
        this.H = (BydSwitch) this.f3717c.findViewById(R.id.smart_reminder_safe_belt_warn_sw);
        this.I = (RelativeLayout) this.f3717c.findViewById(R.id.smart_reminder_safe_belt_warn_container);
        X(((m) mo7getP()).s());
        RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.smart_tips_container);
        l.K(((m) mo7getP()).r() || ((m) mo7getP()).s(), (RelativeLayout) this.f3717c.findViewById(R.id.smart_voice_set_container));
        l.K(((m) mo7getP()).r(), relativeLayout);
        l.K(((m) mo7getP()).o(), this.G);
        l.K(((m) mo7getP()).q(), this.I);
        l.F(this, this.u, this.v, this.z, this.A, this.r, this.E);
        l.y(this, this.s, this.n, this.y, this.H);
        l.s(this, this.p);
        l.t(this, this.w, this.F);
        this.n.setOnInterceptTouchListener(new a());
        this.s.setOnInterceptTouchListener(new b(context));
        this.m = context;
        EventBus.getDefault().register(this);
        b.b.g.n.o.b bVar = new b.b.g.n.o.b(context);
        this.D = bVar;
        bVar.a(new c(), "sound_effects_enabled", "byd_report_toggle");
    }

    public final void T() {
        l.F(null, this.u, this.v, this.n);
        l.y(null, this.s);
        l.s(null, this.p);
        l.t(null, this.w);
    }

    public int U(int i, int i2) {
        if (this.K == null) {
            this.K = b.b.o.a.b.d.a(NotificationCompat.CATEGORY_REMINDER);
        }
        return this.K.a(i, i2);
    }

    public final i V(String str) {
        if (this.R == null) {
            this.R = new i(this.m);
        }
        this.R.x(str);
        return this.R;
    }

    public boolean W() {
        int iN = b.b.g.n.q.m.p0().N();
        return iN == 1 || iN == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void X(boolean z) {
        if (z) {
            this.N = (RelativeLayout) this.f3717c.findViewById(R.id.smart_voice_set_state_container);
            this.Q = (RelativeLayout) this.f3717c.findViewById(R.id.smart_tips_state_container);
            this.O = (ImageView) this.f3717c.findViewById(R.id.auto_state_icon);
            this.P = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.smart_tips_state_segmentedButtonGroup);
            l.F(this, this.O);
            l.u(this.N, this.P, ((m) mo7getP()).e() - 1, this);
            l.K(true, this.Q);
        }
    }

    public final void Y() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity"));
        intent.setFlags(337641472);
        if (DoorLocksFragment.P(getContext(), intent)) {
            startActivity(intent);
            return;
        }
        intent.setComponent(new ComponentName("com.byd.vrassistant.xf", "com.byd.vrassistant.xf.NewSettingActivity"));
        if (DoorLocksFragment.P(getContext(), intent)) {
            startActivity(intent);
        } else {
            Context context = this.m;
            z.c(context, context.getString(R.string.vehiclesettings_smart_broadcast_uninstall_di_app));
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public m newP() {
        return new m();
    }

    public void a0(Context context, int i, boolean z) {
        if (z) {
            l.j(this.L, i, context);
        }
    }

    public void b0() {
        a0(this.m, U(0, 0), true);
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_smart_reminder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c0(int i) {
        this.w.setPositionWithOutCallback(i - 1, false);
        ((m) mo7getP()).x(i);
    }

    public final void d0() {
        if (this.x == null) {
            this.x = new b.b.g.q.d.g(this.m, "reminder_content_mode");
        }
        this.x.D();
    }

    public void e0() {
        this.S = V("reminder_exhibixtion_mode").y(R.layout.carsetting_dialog_show_mode_tips, -1, R.string.vehiclesettings_smart_broadcast_dialog_msg, -1, -1, -1, null, R.string.vehiclesettings_smart_broadcast_dialog_know, new h(), null);
    }

    public final void f0() {
        int iC = b.b.o.b.b.a.c();
        i0("reminder_nav_mode", (iC == 0 || iC == 1) ? R.layout.carsetting_dialog_app_limit : R.layout.carsetting_right_dialog_app_limit, R.string.vehiclesettings_navigation_volume, R.string.vehiclesettings_navigation_volume_tip, -1, -1, R.string.vehiclesettings_button_confirm, 1, new f(), R.string.vehiclesettings_button_cancel, new g());
    }

    public final void g0(String str) {
        long tempDuration;
        tempDuration = CountDownTimerButton.getTempDuration();
        str.hashCode();
        switch (str) {
            case "reminder_sound_mode":
                h0();
                break;
            case "reminder_content_mode":
                d0();
                break;
            case "reminder_sound_voice_state_mode":
                k0();
                break;
            case "reminder_voice_set_mode":
                j0();
                break;
            case "reminder_exhibixtion_mode":
                if (tempDuration > 0) {
                    e0();
                    break;
                }
                break;
            case "reminder_nav_mode":
                f0();
                break;
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_smart_reminder;
    }

    public final void h0() {
        i0("reminder_sound_mode", R.layout.carsetting_dialog_warm_auto, R.string.vehiclesettings_tip_sound_source, R.string.vehiclesettings_sound_source_tips_new, -1, -1, R.string.vehiclesettings_button_confirm, 1, new e(), -1, null);
    }

    public final void i0(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, q.b bVar, int i8, q.a aVar) {
        if (this.R == null) {
            this.R = new i(this.m);
        }
        this.R.x(str);
        this.S = this.R.z(i, i2, i3, i4, i5, i6, i7, bVar, i8, aVar);
    }

    public final void j0() {
        if (this.B == null) {
            this.B = new b.b.g.q.d.f(this.m, "reminder_voice_set_mode");
        }
        this.B.E();
    }

    public final void k0() {
        i0("reminder_sound_voice_state_mode", R.layout.carsetting_dialog_warm_auto, R.string.vehiclesettings_tip_sound_source_level, R.string.vehiclesettings_tip_sound_source_level_tips, -1, -1, R.string.vehiclesettings_button_confirm, 1, new d(), -1, null);
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_smart_reminder;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : k.b()) {
            if (str.startsWith("reminder_")) {
                g0(str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.smart_reminder_safe_belt_warn_sw) {
            if (l.a(this.H)) {
                ((m) mo7getP()).E(z ? 1 : 2);
            }
        } else if (id == R.id.system_key_voice_sw && l.a(this.y)) {
            ((m) mo7getP()).B(this.m, z);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.auto_icon /* 2131296410 */:
                h0();
                break;
            case R.id.auto_state_icon /* 2131296413 */:
                k0();
                break;
            case R.id.smart_reminder_content_container /* 2131298135 */:
            case R.id.smart_reminder_content_imge /* 2131298136 */:
                d0();
                break;
            case R.id.smart_voice_container /* 2131298157 */:
                Y();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        T();
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        ((m) mo7getP()).G();
        Dialog dialog = this.S;
        if (dialog != null && dialog.isShowing()) {
            this.S.dismiss();
            this.S = null;
        }
        b.b.g.q.d.g gVar = this.x;
        if (gVar != null && gVar.B()) {
            this.x.l();
            this.x = null;
        }
        b.b.g.q.d.f fVar = this.B;
        if (fVar != null && fVar.D()) {
            this.B.l();
        }
        b.b.g.n.o.b bVar = this.D;
        if (bVar != null) {
            bVar.b();
            this.D = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 80000:
                b.b.g.q.d.g gVar = this.x;
                if (gVar != null && gVar.B()) {
                    this.x.C();
                    break;
                }
                break;
            case 80001:
                BydLogHelper.logD("message : SMART_REMIND_VOLUME_CHANGE_MESSAGE " + aVar.c());
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = ((m) mo7getP()).j();
                }
                BydLogHelper.logD(getClass().getSimpleName(), " volumeValue : " + iC);
                l.z(iC, this.o, this.m, R.string.vehiclesettings_placehodlers, "");
                this.p.setProgress(iC);
                break;
            case 80003:
                BydLogHelper.logD("message : SMART_REMIND_TIPS_SOUND_CHANGE_MESSAGE " + aVar.c());
                int iC2 = aVar.c();
                if (iC2 < 1 || iC2 > 2) {
                    iC2 = ((m) mo7getP()).d();
                    Log.e("CarTipsSoundType", "message : SMART_REMIND_TIPS_SOUND_CHANGE_MESSAGE " + aVar.c() + "value  : " + iC2);
                }
                c0(iC2);
                break;
            case 80004:
                BydLogHelper.logD("message : SMART_REMIND_ENGINE_SOUND_CHANGE_MESSAGE " + aVar.c());
                int iC3 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC3 = ((m) mo7getP()).f();
                }
                BydLogHelper.logD("message : SMART_REMIND_ENGINE_SOUND_CHANGE_MESSAGE  voiceValue ： " + iC3);
                break;
            case 80005:
                BydLogHelper.logD("message : SMART_REMIND_APP_LIMIT_CHANGE_MESSAGE " + aVar.c());
                break;
            case 80006:
                BydLogHelper.logD("message : SMART_REMIND_NAV_VOLUME_CHANGE_MESSAGE " + aVar.c());
                int iC4 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC4 = ((m) mo7getP()).i() ? 1 : 2;
                }
                this.n.setChecked(iC4 == 1, false);
                break;
            case 80007:
                BydLogHelper.logD("message : SMART_REMIND_KEY_VIBRATOR_LEVEL_MESSAGE " + aVar.c());
                int iC5 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int iG = ((m) mo7getP()).g();
                    if (iG != 0) {
                        i = iG;
                    }
                } else {
                    i = iC5;
                }
                if (i >= 1 && i <= 3) {
                    this.F.setPositionWithOutCallback(i - 1, false);
                    break;
                }
                break;
            case 80008:
                BydLogHelper.logD("message : SMART_REMIND_KEY_VIBRATOR_LEVEL_MESSAGE " + aVar.c());
                int iC6 = aVar.c();
                if (iC6 == 1 || iC6 == 2) {
                    this.H.setChecked(iC6 == 1, false);
                }
                break;
            case 80009:
                BydLogHelper.logD("message : SMART_REMIND_TIPS_SOUND_STATE_CHANGE_MESSAGE " + aVar.c());
                int iC7 = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    int iE = ((m) mo7getP()).e();
                    if (iE != 0) {
                        i = iE;
                    }
                } else {
                    i = iC7;
                }
                if (i >= 1 && i <= 3) {
                    this.P.setPositionWithOutCallback(i - 1, false);
                    break;
                }
                break;
            case 80010:
                BydLogHelper.logD("message : SMART_REMIND_UE_MUTED_CHANGE_MESSAGE " + aVar.c());
                this.p.setInvalid(aVar.c() == 1);
                this.p.setProgress(((m) mo7getP()).j());
                l.z(((m) mo7getP()).j(), this.o, this.m, R.string.vehiclesettings_placehodlers, "");
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        int id = view.getId();
        if (id == R.id.smart_tips_segmentedButtonGroup) {
            m.f1020c = 0L;
            int i2 = i + 1;
            ((m) mo7getP()).y(i2);
            ((m) mo7getP()).x(i2);
            b.b.g.r.e.i(80003);
            return;
        }
        if (id == R.id.smart_tips_state_segmentedButtonGroup) {
            ((m) mo7getP()).z(i + 1);
            b.b.g.r.e.i(80009);
        } else {
            if (id != R.id.vehiclesettings_control_touch_sbg) {
                return;
            }
            ((m) mo7getP()).A(i + 1);
            b.b.g.r.e.i(80007);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            l.r(false, this.t);
            if (seekBar.getId() == R.id.smart_reminder_volume) {
                BydLogHelper.logD("onProgressChanged report volume : " + i);
                if (((m) mo7getP()).t()) {
                    ((m) mo7getP()).F(false);
                }
                l.z(i, this.o, this.m, R.string.vehiclesettings_placehodlers, "");
                ((m) mo7getP()).D(i);
                b.b.g.r.e.i(80001);
            }
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        l.r(true, this.t);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        s.a(this.t, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    @RequiresApi(api = 26)
    public void y(Context context) {
        ((m) mo7getP()).v();
        this.M = 0;
        ((m) mo7getP()).j();
        l.z(((m) mo7getP()).j(), this.o, context, R.string.vehiclesettings_placehodlers, "");
        l.J(((m) mo7getP()).c(context) ? 0 : 8, this.r, this.q, this.p);
        this.w.setPositionWithOutCallback(((m) mo7getP()).d(), false);
        this.p.setProgress(((m) mo7getP()).j());
        this.p.setInvalid(((m) mo7getP()).t());
        this.s.setChecked(((m) mo7getP()).c(context), false);
        this.n.setChecked(((m) mo7getP()).i(), false);
        this.y.setChecked(((m) mo7getP()).h(context), false);
        this.F.setPositionWithOutCallback(Math.max(0, ((m) mo7getP()).g() - 1), false);
        this.H.setChecked(((m) mo7getP()).l(), false);
        l.J(((m) mo7getP()).h(context) ? 0 : 8, new View[0]);
        c0(((m) mo7getP()).d());
        b0();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
        Dialog dialog = this.S;
        if (dialog != null && dialog.isShowing()) {
            this.S.dismiss();
            this.S = null;
        }
        b.b.g.q.d.g gVar = this.x;
        if (gVar != null && gVar.B()) {
            this.x.l();
            this.x = null;
        }
        b.b.g.q.d.f fVar = this.B;
        if (fVar == null || !fVar.D()) {
            return;
        }
        this.B.l();
    }
}
