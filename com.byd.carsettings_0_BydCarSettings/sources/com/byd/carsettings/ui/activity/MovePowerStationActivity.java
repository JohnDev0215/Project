package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import b.b.g.n.q.h;
import b.b.g.q.d.l;
import b.b.g.q.d.m;
import b.b.g.q.d.o;
import b.b.g.r.v;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.seekbar.SubLevelSeekbar;
import com.byd.mvp.mvp.XActivity;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class MovePowerStationActivity extends XActivity<b.b.g.n.d> implements View.OnClickListener, SegmentedButtonGroup.e, b.b.o.b.a.a {
    public l A;
    public BYDAutoBodyworkDevice B;
    public e D;
    public SegmentedButtonGroup p;
    public SubLevelSeekbar q;
    public TextView r;
    public ViewStub s;
    public ViewStub t;
    public ViewStub u;
    public ViewStub v;
    public ViewStub w;
    public l y;
    public o z;
    public int x = -1;
    public boolean E = true;
    public boolean F = true;
    public final String G = "MovePowerStationActivity_label_safe";
    public final String H = "MovePowerStationActivity_label_discharge";
    public final String I = "MovePowerStationActivity_label_low";
    public final String J = "MovePowerStationActivity_label_detail";
    public boolean K = false;
    public int L = 0;
    public boolean M = false;
    public int N = 0;
    public boolean O = false;
    public int P = 0;
    public boolean Q = false;
    public int R = 0;

    public class a implements SubLevelSeekbar.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f2917a = 0.0f;

        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.carsettings.ui.view.seekbar.SubLevelSeekbar.a
        public void a(float f2) {
            ((b.b.g.n.d) MovePowerStationActivity.this.mo7getP()).A((int) (f2 * 100.0f), (int) (this.f2917a * 100.0f));
        }

        @Override // com.byd.carsettings.ui.view.seekbar.SubLevelSeekbar.a
        public void b(float f2) {
            this.f2917a = f2;
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MovePowerStationActivity.this.M = false;
            MovePowerStationActivity.this.K = false;
        }
    }

    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CheckBox f2920a;

        public c(CheckBox checkBox) {
            this.f2920a = checkBox;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            v.d().g("KEY_SAFE_HINT", this.f2920a.isChecked());
            MovePowerStationActivity movePowerStationActivity = MovePowerStationActivity.this;
            movePowerStationActivity.l0(((b.b.g.n.d) movePowerStationActivity.mo7getP()).u());
        }
    }

    public class d implements Runnable {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            MovePowerStationActivity movePowerStationActivity = MovePowerStationActivity.this;
            movePowerStationActivity.l0(((b.b.g.n.d) movePowerStationActivity.mo7getP()).u());
        }
    }

    public static class e extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<MovePowerStationActivity> f2923a;

        public e(MovePowerStationActivity movePowerStationActivity) {
            this.f2923a = new WeakReference<>(movePowerStationActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (this.f2923a.get() == null) {
                return;
            }
            try {
                if (this.f2923a.get().E) {
                    sendEmptyMessageDelayed(0, 1000L);
                }
                boolean z = true;
                if (this.f2923a.get().K) {
                    if (this.f2923a.get().B.hasMessage(51) == 1) {
                        this.f2923a.get().L = 0;
                    } else {
                        MovePowerStationActivity.b0(this.f2923a.get());
                        if (this.f2923a.get().L >= 5) {
                            if (this.f2923a.get().A != null && this.f2923a.get().A.p()) {
                                ((TextView) this.f2923a.get().A.i().findViewById(R.id.tv_owner_desc)).setText(this.f2923a.get().p0());
                            }
                            this.f2923a.get().r0(false);
                            if (this.f2923a.get().findViewById(R.id.tv_discharge_title) != null) {
                                this.f2923a.get().findViewById(R.id.tv_discharge_title).setVisibility(8);
                            }
                            if (this.f2923a.get().findViewById(R.id.tv_discharge_value) != null) {
                                this.f2923a.get().findViewById(R.id.tv_discharge_value).setVisibility(8);
                            }
                            if (this.f2923a.get().findViewById(R.id.tv_discharge_unit) != null) {
                                this.f2923a.get().findViewById(R.id.tv_discharge_unit).setVisibility(8);
                            }
                            this.f2923a.get().L = 0;
                            this.f2923a.get().K = false;
                        }
                    }
                } else {
                    this.f2923a.get().L = 0;
                }
                if (this.f2923a.get().M) {
                    if (this.f2923a.get().B.hasMessage(198) == 1) {
                        this.f2923a.get().N = 0;
                    } else {
                        MovePowerStationActivity.h0(this.f2923a.get());
                        if (this.f2923a.get().N >= 5) {
                            if (this.f2923a.get().A != null && this.f2923a.get().A.p()) {
                                View viewI = this.f2923a.get().A.i();
                                ((TextView) viewI.findViewById(R.id.tv_owner_desc)).setText(this.f2923a.get().p0());
                                ((TextView) viewI.findViewById(R.id.tv_charge_desc)).setText(this.f2923a.get().o0());
                            }
                            if (this.f2923a.get().findViewById(R.id.tv_progress_charge_car) != null) {
                                this.f2923a.get().findViewById(R.id.tv_progress_charge_car).setVisibility(8);
                            }
                            if (this.f2923a.get().findViewById(R.id.progressbar_charge_car) != null) {
                                this.f2923a.get().findViewById(R.id.progressbar_charge_car).setVisibility(8);
                            }
                            if (this.f2923a.get().findViewById(R.id.tv_remain_time_value) != null) {
                                this.f2923a.get().findViewById(R.id.tv_remain_time_value).setVisibility(8);
                            }
                            this.f2923a.get().N = 0;
                            this.f2923a.get().M = false;
                        }
                    }
                } else {
                    this.f2923a.get().N = 0;
                }
                if (this.f2923a.get().O) {
                    if (this.f2923a.get().B.hasMessage(202) == 1) {
                        this.f2923a.get().P = 0;
                    } else {
                        MovePowerStationActivity.R(this.f2923a.get());
                        if (this.f2923a.get().P >= 5) {
                            if (this.f2923a.get().findViewById(R.id.tv_progress_own_car) != null) {
                                this.f2923a.get().findViewById(R.id.tv_progress_own_car).setVisibility(8);
                            }
                            if (this.f2923a.get().findViewById(R.id.progressbar_own_car) != null) {
                                this.f2923a.get().findViewById(R.id.progressbar_own_car).setVisibility(8);
                            }
                            this.f2923a.get().P = 0;
                            this.f2923a.get().O = false;
                        }
                    }
                } else {
                    this.f2923a.get().P = 0;
                }
                if (!this.f2923a.get().Q) {
                    this.f2923a.get().R = 0;
                    return;
                }
                if (this.f2923a.get().B.hasMessage(208) != 1) {
                    z = false;
                }
                if (z) {
                    this.f2923a.get().R = 0;
                    return;
                }
                MovePowerStationActivity.W(this.f2923a.get());
                if (this.f2923a.get().R >= 5) {
                    this.f2923a.get().r0(false);
                    this.f2923a.get().R = 0;
                    this.f2923a.get().Q = false;
                }
            } catch (Exception e2) {
                BydLogHelper.logE(e2.getMessage());
            }
        }
    }

    public static /* synthetic */ int R(MovePowerStationActivity movePowerStationActivity) {
        int i = movePowerStationActivity.P;
        movePowerStationActivity.P = i + 1;
        return i;
    }

    public static /* synthetic */ int W(MovePowerStationActivity movePowerStationActivity) {
        int i = movePowerStationActivity.R;
        movePowerStationActivity.R = i + 1;
        return i;
    }

    public static /* synthetic */ int b0(MovePowerStationActivity movePowerStationActivity) {
        int i = movePowerStationActivity.L;
        movePowerStationActivity.L = i + 1;
        return i;
    }

    public static /* synthetic */ int h0(MovePowerStationActivity movePowerStationActivity) {
        int i = movePowerStationActivity.N;
        movePowerStationActivity.N = i + 1;
        return i;
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.activity_move_power_station;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.activity_move_power_station;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void j0(int i) {
        l lVar;
        int iU = ((b.b.g.n.d) mo7getP()).u();
        if (iU == 1 || iU == 0) {
            l0(iU);
            return;
        }
        if (i == 10) {
            x0(this.s);
            y0(R.id.rl_move_station);
            z0(R.id.progressbar_wait);
            ((ImageView) findViewById(R.id.iv_status_logo)).setImageDrawable(ContextCompat.getDrawable(this.f3704b, R.drawable.img_dark_carsetting_newenergy_charge_power_station_discharge_connection_socket));
            ((TextView) findViewById(R.id.tv_status_desc)).setText(getResources().getString(R.string.newenergy_discharge_mps_discharge_connecting));
            this.K = true;
            this.Q = true;
        } else if (i == 11) {
            z0(R.id.rl_move_station);
            w0(this.s);
            r0(true);
        } else if (i == 12 || i == 14) {
            r0(false);
        } else {
            r0(false);
        }
        if (i == 11 || (lVar = this.A) == null || !lVar.p()) {
            return;
        }
        this.A.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        findViewById(R.id.ll_return).setOnClickListener(this);
        this.p.setOnMyClickListener(this);
        this.B = BYDAutoBodyworkDevice.getInstance(this);
        this.F = ((b.b.g.n.d) mo7getP()).r() == 1;
        ((b.b.g.n.d) mo7getP()).C();
        q0();
        e eVar = new e(this);
        this.D = eVar;
        eVar.sendEmptyMessage(0);
    }

    public void k0(int i, boolean z, boolean z2) {
        this.p.setPosition(i == 1 ? 0 : 1, z);
        if (z) {
            if (i == 1) {
                if (z2 || !v0()) {
                    new Handler().postDelayed(new d(), this.F ? 0L : 500L);
                    this.F = false;
                    return;
                }
                return;
            }
            r0(false);
            l lVar = this.A;
            if (lVar == null || !lVar.p()) {
                return;
            }
            this.A.f();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void l0(int i) {
        this.x = i;
        if (((b.b.g.n.d) mo7getP()).r() != 1) {
            return;
        }
        if (i == 2) {
            z0(R.id.rl_move_station);
            w0(this.s);
            j0(((b.b.g.n.d) mo7getP()).d());
        } else if (i == 1 || i == 0) {
            x0(this.s);
            y0(R.id.rl_move_station);
            y0(R.id.progressbar_wait);
            ((ImageView) findViewById(R.id.iv_status_logo)).setImageDrawable(ContextCompat.getDrawable(this.f3704b, R.drawable.img_dark_carsetting_newenergy_charge_power_station_discharge_connection_animation));
            ((TextView) findViewById(R.id.tv_status_desc)).setText(String.format(getResources().getString(R.string.newenergy_discharge_mps_connected_hint_msg), "10"));
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.activity_move_power_station_byd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void m0(int i) {
        if (i <= 0 || i > 100) {
            return;
        }
        int iZ = ((b.b.g.n.d) mo7getP()).z(this);
        int iX = ((b.b.g.n.d) mo7getP()).x();
        if (iZ == -1 || iX == -1) {
            return;
        }
        if (iX > iZ) {
            this.q.setProgress(0.0f, 0.0f, 0.0f, 1.0f);
            return;
        }
        if (i < iX) {
            b.b.m.c.a.c(this.f3705c, "[限值调整]----[下调]----当前：" + i + "----最低：" + iX);
            i = iX;
        }
        if (i > iZ) {
            b.b.m.c.a.c(this.f3705c, "[限值调整]----[下调]----当前：" + i + "----最高：" + iZ);
            i = iZ;
        }
        float f2 = iX / 100.0f;
        float f3 = 1.0f - (iZ / 100.0f);
        float f4 = (i / 100.0f) - f2;
        this.q.setProgress(f2, f4, ((1.0f - f2) - f4) - f3, f3);
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void n(Context context) {
        this.p = (SegmentedButtonGroup) findViewById(R.id.switch_move_station);
        this.s = (ViewStub) findViewById(R.id.rl_gun_status);
        this.t = (ViewStub) findViewById(R.id.rl_remain_time);
        this.u = (ViewStub) findViewById(R.id.rl_switch_on);
        this.v = (ViewStub) findViewById(R.id.rl_switch_off);
        this.w = (ViewStub) findViewById(R.id.rl_seekbar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void n0(float f2) {
        Resources resources;
        int i;
        if (h.m().C(3) == 2) {
            resources = getResources();
            i = R.string.newenergy_elec_unit_hp;
        } else {
            resources = getResources();
            i = R.string.newenergy_elec_unit_kw;
        }
        String string = resources.getString(i);
        ((TextView) findViewById(R.id.tv_discharge_unit)).setText(string + "");
        ((TextView) findViewById(R.id.tv_discharge_value)).setText(((b.b.g.n.d) mo7getP()).y() + "");
        l lVar = this.A;
        if (lVar == null || !lVar.p()) {
            return;
        }
        ((TextView) this.A.i().findViewById(R.id.tv_owner_desc)).setText(p0());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String o0() {
        boolean z = this.B.hasMessage(198) == 1;
        String str = b.b.g.n.q.a.a().b() == 1 ? "℃" : "℉";
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_battery_type));
        String[] stringArray = getResources().getStringArray(R.array.type_battery);
        int iE = ((b.b.g.n.d) mo7getP()).e();
        if (iE <= 0 || iE > stringArray.length) {
            sb.append("--\n");
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(z ? stringArray[iE - 1] : "--");
            sb2.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(sb2.toString());
        }
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_demand_volt));
        StringBuilder sb3 = new StringBuilder();
        sb3.append(z ? ((b.b.g.n.d) mo7getP()).j() : "--");
        sb3.append("V\n");
        sb.append(sb3.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_demand_ele));
        StringBuilder sb4 = new StringBuilder();
        sb4.append(z ? ((b.b.g.n.d) mo7getP()).i() : "--");
        sb4.append("A\n");
        sb.append(sb4.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_power_start));
        StringBuilder sb5 = new StringBuilder();
        sb5.append(z ? Integer.valueOf(((b.b.g.n.d) mo7getP()).f()) : "--");
        sb5.append("%\n");
        sb.append(sb5.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_power_current));
        StringBuilder sb6 = new StringBuilder();
        sb6.append(z ? Integer.valueOf(((b.b.g.n.d) mo7getP()).t()) : "--");
        sb6.append("%\n");
        sb.append(sb6.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_single_section_max_volt));
        StringBuilder sb7 = new StringBuilder();
        sb7.append(z ? ((b.b.g.n.d) mo7getP()).s() : "--");
        sb7.append("V\n");
        sb.append(sb7.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_single_section_allow_max_volt));
        StringBuilder sb8 = new StringBuilder();
        sb8.append(z ? ((b.b.g.n.d) mo7getP()).l() : "--");
        sb8.append("V\n");
        sb.append(sb8.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_single_section_max_temp));
        StringBuilder sb9 = new StringBuilder();
        sb9.append(z ? ((b.b.g.n.d) mo7getP()).h() : "--");
        sb9.append(str);
        sb9.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append(sb9.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_charge_single_section_allow_max_temp));
        StringBuilder sb10 = new StringBuilder();
        sb10.append(z ? ((b.b.g.n.d) mo7getP()).g() : "--");
        sb10.append(str);
        sb10.append("");
        sb.append(sb10.toString());
        return sb.toString();
    }

    @Override // com.byd.mvp.mvp.XActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (b.b.m.g.c.a(300L)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.ll_return) {
            finish();
        } else {
            if (id != R.id.tv_detail) {
                return;
            }
            t0();
        }
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        m.b();
        e eVar = this.D;
        if (eVar != null) {
            this.E = false;
            eVar.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    @Override // com.byd.mvp.mvp.XActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        m.o();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String p0() {
        Resources resources;
        int i;
        boolean z = this.B.hasMessage(51) == 1;
        boolean z2 = this.B.hasMessage(198) == 1;
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_discharge_degrees));
        if (h.m().C(3) == 2) {
            resources = getResources();
            i = R.string.newenergy_elec_unit_hp;
        } else {
            resources = getResources();
            i = R.string.newenergy_elec_unit_kw;
        }
        String string = resources.getString(i);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z ? ((b.b.g.n.d) mo7getP()).y() : "--");
        sb2.append(string);
        sb2.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append(sb2.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_discharge_time));
        int iO = ((b.b.g.n.d) mo7getP()).o();
        StringBuilder sb3 = new StringBuilder();
        sb3.append((!z2 || iO == 65535) ? "--" : String.format(getResources().getString(R.string.newenergy_discharge_mps_discharge_remain_time_data), Integer.valueOf(iO / 60), Integer.valueOf(iO % 60)));
        sb3.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append(sb3.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_discharge_volt));
        StringBuilder sb4 = new StringBuilder();
        sb4.append(z2 ? ((b.b.g.n.d) mo7getP()).q() : "--");
        sb4.append("V\n");
        sb.append(sb4.toString());
        sb.append(getResources().getString(R.string.newenergy_discharge_mps_discharge_ele));
        StringBuilder sb5 = new StringBuilder();
        sb5.append(z2 ? ((b.b.g.n.d) mo7getP()).m() : "--");
        sb5.append(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        sb.append(sb5.toString());
        return sb.toString();
    }

    public final void q0() {
        try {
            for (String str : m.f1480b) {
                byte b2 = -1;
                int iHashCode = str.hashCode();
                if (iHashCode != -1410236404) {
                    if (iHashCode != 201510265) {
                        if (iHashCode == 1952045576 && str.equals("MovePowerStationActivity_label_safe")) {
                            b2 = 0;
                        }
                    } else if (str.equals("MovePowerStationActivity_label_low")) {
                        b2 = 1;
                    }
                } else if (str.equals("MovePowerStationActivity_label_detail")) {
                    b2 = 2;
                }
                if (b2 == 0) {
                    v0();
                } else if (b2 == 1) {
                    u0();
                } else if (b2 == 2) {
                    t0();
                }
            }
        } catch (Exception e2) {
            b.b.m.c.a.c(this.f3705c, "label----" + e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r0(boolean z) {
        if (!z) {
            z0(R.id.rl_move_station);
            w0(this.s);
            w0(this.t, this.u, this.v, this.w);
            x0(this.v);
            ((TextView) findViewById(R.id.tv_discharge_title)).setText(getResources().getString(R.string.movestation_discharge_last));
            ((TextView) findViewById(R.id.tv_discharge_value)).setText(((b.b.g.n.d) mo7getP()).y() + "");
            String string = h.m().C(3) == 2 ? getResources().getString(R.string.newenergy_elec_unit_hp) : getResources().getString(R.string.newenergy_elec_unit_kw);
            ((TextView) findViewById(R.id.tv_discharge_unit)).setText(string + "");
            ((ImageView) findViewById(R.id.iv_transmission_state)).setImageDrawable(ContextCompat.getDrawable(this.f3704b, R.drawable.img_dark_carsetting_newenergy_charge_power_station_transmission_animation_off));
            ((TextView) findViewById(R.id.tv_content)).setMovementMethod(ScrollingMovementMethod.getInstance());
            return;
        }
        x0(this.t, this.u, this.v, this.w);
        w0(this.v);
        int iN = ((b.b.g.n.d) mo7getP()).n();
        ((TextView) findViewById(R.id.tv_remain_time_value)).setText(iN == 255 ? "--" : String.format(getResources().getString(R.string.newenergy_discharge_mps_discharge_remain_time_data), Integer.valueOf(iN / 60), Integer.valueOf(iN % 60)));
        ((TextView) findViewById(R.id.tv_discharge_title)).setText(getResources().getString(R.string.movestation_discharge_already));
        ((TextView) findViewById(R.id.tv_discharge_value)).setText(((b.b.g.n.d) mo7getP()).y() + "");
        String string2 = h.m().C(3) == 2 ? getResources().getString(R.string.newenergy_elec_unit_hp) : getResources().getString(R.string.newenergy_elec_unit_kw);
        ((TextView) findViewById(R.id.tv_discharge_unit)).setText(string2 + "");
        ((ImageView) findViewById(R.id.iv_transmission_state)).setImageDrawable(ContextCompat.getDrawable(this.f3704b, R.drawable.img_dark_carsetting_newenergy_charge_power_station_transmission_animation_on));
        int iZ = ((b.b.g.n.d) mo7getP()).z(this);
        int iT = ((b.b.g.n.d) mo7getP()).t();
        TextView textView = (TextView) findViewById(R.id.tv_progress_own_car);
        StringBuilder sb = new StringBuilder();
        sb.append(iZ == -1 ? "--" : Integer.valueOf(iZ));
        sb.append("%");
        textView.setText(sb.toString());
        TextView textView2 = (TextView) findViewById(R.id.tv_progress_charge_car);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(iT != -1 ? Integer.valueOf(iT) : "--");
        sb2.append("%");
        textView2.setText(sb2.toString());
        ((ProgressBar) findViewById(R.id.progressbar_own_car)).setProgress(iZ == -1 ? 0 : iZ);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar_charge_car);
        if (iT == -1) {
            iT = 0;
        }
        progressBar.setProgress(iT);
        ((TextView) findViewById(R.id.tv_progress_own_car)).setVisibility(0);
        ((ProgressBar) findViewById(R.id.progressbar_own_car)).setVisibility(0);
        ((TextView) findViewById(R.id.tv_progress_charge_car)).setVisibility(0);
        ((ProgressBar) findViewById(R.id.progressbar_charge_car)).setVisibility(0);
        ((TextView) findViewById(R.id.tv_remain_time_value)).setVisibility(0);
        ((TextView) findViewById(R.id.tv_discharge_title)).setVisibility(0);
        ((TextView) findViewById(R.id.tv_discharge_value)).setVisibility(0);
        ((TextView) findViewById(R.id.tv_discharge_unit)).setVisibility(0);
        if (this.r == null) {
            TextView textView3 = (TextView) findViewById(R.id.tv_detail);
            this.r = textView3;
            textView3.setText(getResources().getString(R.string.newenergy_discharge_mps_detail_info));
            this.r.setOnClickListener(this);
        }
        if (this.q == null) {
            SubLevelSeekbar subLevelSeekbar = (SubLevelSeekbar) findViewById(R.id.subLevelSeekbar);
            this.q = subLevelSeekbar;
            subLevelSeekbar.setOnSeekBarChangeListener(new a());
        }
        int iX = ((b.b.g.n.d) mo7getP()).x();
        int iV = ((b.b.g.n.d) mo7getP()).v();
        if (iZ == -1 || iX == -1 || iV == -1 || iX > iZ) {
            this.q.setProgress(0.0f, 0.0f, 0.0f, 1.0f);
        } else {
            if (iV < iX) {
                b.b.m.c.a.c(this.f3705c, "[限值调整]----[下调]----当前：" + iX + "----最低：" + iX);
                ((b.b.g.n.d) mo7getP()).A(iX, 0);
                iV = iX;
            }
            if (iV > iZ) {
                b.b.m.c.a.c(this.f3705c, "[限值调整]----[下调]----当前：" + iZ + "----最高：" + iZ);
                ((b.b.g.n.d) mo7getP()).A(iZ, 0);
                iV = iZ;
            }
            float f2 = iX / 100.0f;
            float f3 = 1.0f - (iZ / 100.0f);
            float f4 = (iV / 100.0f) - f2;
            this.q.setProgress(f2, f4, ((1.0f - f2) - f4) - f3, f3);
        }
        this.K = true;
        this.M = true;
        this.O = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.e
    public void s(View view, int i) {
        if (view.getId() != R.id.switch_move_station) {
            return;
        }
        if (i == 0 && ((b.b.g.n.d) mo7getP()).w() == 1) {
            u0();
        } else {
            this.p.setPosition(i, true);
            ((b.b.g.n.d) mo7getP()).B(i != 0 ? 2 : 1);
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: s0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.d newP() {
        return new b.b.g.n.d();
    }

    public final void t0() {
        View viewInflate = LayoutInflater.from(this.f3704b).inflate(R.layout.dialog_discharge_detail_msg, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.tv_owner_desc)).setText(p0());
        ((TextView) viewInflate.findViewById(R.id.tv_charge_desc)).setText(o0());
        l lVarI = m.i(this.f3704b, "MovePowerStationActivity_label_detail", getResources().getString(R.string.newenergy_discharge_mps_detail_info), getResources().getString(R.string.dialog_btn_ok), null, null, new b(), true, viewInflate);
        this.A = lVarI;
        lVarI.C();
        this.M = true;
        this.K = true;
    }

    public final void u0() {
        o oVar = this.z;
        if (oVar == null || !oVar.g()) {
            o oVarK = m.k(this.f3704b, "MovePowerStationActivity_label_low");
            this.z = oVarK;
            oVarK.i();
        }
    }

    public final boolean v0() {
        if (v.d().b("KEY_SAFE_HINT", false)) {
            return false;
        }
        l lVar = this.y;
        if (lVar != null && lVar.p()) {
            return true;
        }
        View viewInflate = LayoutInflater.from(this.f3704b).inflate(R.layout.dialog_discharge_safe_hint, (ViewGroup) null);
        l lVarJ = m.j(this.f3704b, "MovePowerStationActivity_label_safe", getResources().getString(R.string.newenergy_discharge_mps_safety_instructions), getResources().getString(R.string.dialog_btn_ok), null, new c((CheckBox) viewInflate.findViewById(R.id.cb_reminder)), null, true, false, viewInflate);
        this.y = lVarJ;
        lVarJ.C();
        this.y.u(false);
        return true;
    }

    public void w0(ViewStub... viewStubArr) {
        if (viewStubArr == null || viewStubArr.length <= 0) {
            return;
        }
        for (ViewStub viewStub : viewStubArr) {
            if (viewStub.getParent() == null) {
                viewStub.setVisibility(8);
            }
        }
    }

    public void x0(ViewStub... viewStubArr) {
        if (viewStubArr == null || viewStubArr.length <= 0) {
            return;
        }
        for (ViewStub viewStub : viewStubArr) {
            if (viewStub.getParent() != null) {
                viewStub.inflate();
            } else {
                viewStub.setVisibility(0);
            }
        }
    }

    public void y0(int... iArr) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        for (int i : iArr) {
            View viewFindViewById = findViewById(i);
            if (viewFindViewById != null && viewFindViewById.getVisibility() != 8) {
                findViewById(i).setVisibility(8);
            }
        }
    }

    public void z0(int... iArr) {
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        for (int i : iArr) {
            View viewFindViewById = findViewById(i);
            if (viewFindViewById != null && viewFindViewById.getVisibility() != 0) {
                findViewById(i).setVisibility(0);
            }
        }
    }
}
