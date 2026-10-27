package b.b.g.n.l.f;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import b.b.g.r.z;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.inter.IAdasDataRepository;
import com.byd.base.hilt.inter.IStudyConfig;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.dipilot.model.ActiveSafeModel;
import com.byd.dipilot.model.ActiveSafeStateModel;
import com.byd.dipilot.model.DriverAssistModel;
import com.byd.dipilot.viewmodel.ActiveSafeViewModel;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: ActiveSafeViewPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class l extends b.b.k.c.a<b.b.k.a.b.a<l, ActiveSafeModel>> implements b.b.k.d.a.a<ActiveSafeModel>, SegmentedButtonGroup.d, CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.g.n.l.a f1197c = new b.b.g.n.l.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActiveSafeViewModel f1198d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CountDownTimer f1199e;

    /* JADX INFO: compiled from: ActiveSafeViewPresenter.java */
    public class a extends CountDownTimer {
        public a(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            l.this.f1199e = null;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (l.this.f1197c != null) {
                l.this.f1197c.s0();
            } else if (l.this.f1199e != null) {
                l.this.f1199e.cancel();
                l.this.f1199e = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void D(CompoundButton compoundButton, boolean z) {
        I(this.f1198d.J(), z, compoundButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void F(boolean z, CompoundButton compoundButton, boolean z2) {
        I(this.f1198d.q(), z & z2, compoundButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void H(boolean z) {
        this.f1198d.L().setAssistState(this.f1198d.L().assistState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void L(ActiveSafeStateModel activeSafeStateModel, boolean z, View view, boolean z2) {
        I(activeSafeStateModel, z & z2, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void N(ActiveSafeModel activeSafeModel, boolean z) {
        if (!z) {
            activeSafeModel.setAssistState(activeSafeModel.getAssistState());
        } else {
            this.f1197c.n0(1);
            activeSafeModel.setAssistState(false);
        }
    }

    @Override // b.b.k.d.a.a
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public void p(ActiveSafeModel activeSafeModel, int i) {
        BydLogHelper.logD(this.f1952a, "`onNoticeItemClick pos= " + i);
        b.b.k.a.b.a aVar = (b.b.k.a.b.a) getV();
        if (aVar == null || aVar.i()) {
            return;
        }
        int[] iArrN = n(activeSafeModel);
        aVar.j(activeSafeModel, activeSafeModel.getTag(), iArrN[0], iArrN[1], null);
    }

    @Override // b.b.k.d.a.a
    @SuppressLint({"NonConstantResourceId"})
    /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
    public void b(final LottieAnimationView lottieAnimationView, final ActiveSafeModel activeSafeModel, boolean z, int i) {
        b.b.k.a.b.a aVar = (b.b.k.a.b.a) getV();
        if (activeSafeModel == null) {
            BydLogHelper.logW(this.f1952a, "onSwitchChange not handled! viewModel null!");
        }
        if (this.f1197c == null) {
            BydLogHelper.logW(this.f1952a, "onSwitchChange not handled! fPresent null!");
            return;
        }
        if (aVar == null) {
            BydLogHelper.logW(this.f1952a, "onSwitchChange not handled! v null!");
            return;
        }
        BydLogHelper.logD(this.f1952a, "onSwitchChange =" + activeSafeModel.getAssistState() + ",switch:" + z);
        switch (activeSafeModel.getTag()) {
            case R.string.dipilot_blind_spot_detection /* 2131755272 */:
                this.f1197c.j0(z ? 2 : 1);
                break;
            case R.string.dipilot_high_beam_title /* 2131755349 */:
                this.f1197c.e0(z ? 2 : 1);
                break;
            case R.string.dipilot_smart_matrix_light /* 2131755420 */:
                this.f1197c.g0(z ? 1 : 2);
                break;
            case R.string.dipilot_title_HDC /* 2131755447 */:
                this.f1197c.E0(z ? 1 : 2);
                break;
            case R.string.dipilot_title_mistake_gas /* 2131755456 */:
                this.f1197c.u0(z ? 1 : 2);
                break;
            case R.string.dipilot_title_open_door_warning /* 2131755457 */:
                this.f1197c.w0(z ? 2 : 1);
                break;
            case R.string.dipilot_title_steady_body_system /* 2131755464 */:
                if (z && this.f1198d.P(5)) {
                    aVar.j(activeSafeModel, activeSafeModel.getTag(), R.string.esc_double_config_tip, 0, new b.b.k.a.a() { // from class: b.b.g.n.l.f.g
                        @Override // b.b.k.a.a
                        public final void f(boolean z2) {
                            this.f1185a.P(lottieAnimationView, activeSafeModel, z2);
                        }
                    });
                } else {
                    if (z) {
                        lottieAnimationView.playAnimation();
                    }
                    this.f1197c.m0(z ? 1 : 2);
                }
                break;
            case R.string.dipilot_traffic_light_title /* 2131755468 */:
                if (!z) {
                    b.b.g.n.l.a aVar2 = this.f1197c;
                    if (aVar2.e(aVar2.c())) {
                        z.b(((b.b.k.a.b.a) getV()).getContext(), R.string.dipilot_intelligent_navigation_toast);
                        activeSafeModel.setAssistState(true);
                    }
                }
                this.f1197c.G0(z ? 2 : 1);
                break;
            case R.string.dipilot_urgent_lane_title /* 2131755482 */:
                if (z || !this.f1198d.P(0)) {
                    this.f1197c.n0(z ? 2 : 1);
                    break;
                } else {
                    final ActiveSafeModel activeSafeModelC = this.f1198d.c(R.string.dipilot_urgent_lane_title);
                    if (activeSafeModelC != null) {
                        aVar.j(activeSafeModelC, activeSafeModelC.getTag(), R.string.dipilot_urgent_lane_close_tips, R.string.dipilot_dialog_only_effect_cur_journey, new b.b.k.a.a() { // from class: b.b.g.n.l.f.d
                            @Override // b.b.k.a.a
                            public final void f(boolean z2) {
                                this.f1175a.N(activeSafeModelC, z2);
                            }
                        });
                        break;
                    }
                }
                break;
        }
    }

    public void S() {
        if (this.f1197c == null || this.f1199e != null) {
            return;
        }
        a aVar = new a(300L, 100L);
        this.f1199e = aVar;
        aVar.start();
    }

    public final void T(boolean z) {
        DriverAssistModel driverAssistModelP = this.f1198d.p();
        if (driverAssistModelP != null) {
            driverAssistModelP.setEnable(z);
        }
    }

    public final ActiveSafeModel U(byte b2, ActiveSafeModel activeSafeModel) {
        boolean z = true;
        if (2 != b2 && 1 != b2) {
            z = false;
        }
        boolean zX = x(b2);
        if (activeSafeModel == null) {
            return new ActiveSafeModel(z, zX, R.string.dipilot_urgent_lane_title, b2);
        }
        if (zX) {
            activeSafeModel.setAssistState(z);
        }
        activeSafeModel.setEnable(zX);
        activeSafeModel.pos = b2;
        return activeSafeModel;
    }

    public final ActiveSafeModel V(int i, int i2, ActiveSafeModel activeSafeModel) {
        int i3 = c(141) ? i : i2;
        boolean z = i3 == 1;
        BydLogHelper.logD(this.f1952a, "updateIntelligentSpeedAlarm ret = " + i3);
        if (activeSafeModel == null) {
            return new ActiveSafeModel(z, true, 0, i2, (byte) i);
        }
        activeSafeModel.setPos((byte) i);
        activeSafeModel.setAssistPicId(i2);
        activeSafeModel.setAssistState(z);
        return activeSafeModel;
    }

    public final ActiveSafeModel W(int i, int i2, ActiveSafeModel activeSafeModel) {
        int i3 = c(141) ? i : i2;
        BydLogHelper.logD(this.f1952a, "updateIntelligentSpeedControl ret = " + i3);
        boolean z = i3 == 1;
        if (activeSafeModel == null) {
            return new ActiveSafeModel(z, true, i, i2, (byte) 0);
        }
        activeSafeModel.updateTagSubCheck(z, i, i2);
        return activeSafeModel;
    }

    public final byte X(int i, int i2) {
        int i3 = c(112) ? i : i2;
        int i4 = i3 == 0 ? 3 : i3 - 1;
        BydLogHelper.logD("updatePredictCrashAlertMode  predictCrashAlert138Mode=" + i + " ,32DMode=" + i2 + " priority = " + i4);
        return (byte) i4;
    }

    public final ActiveSafeModel Y(int i, int i2, ActiveSafeModel activeSafeModel) {
        int i3 = (i > 0 || c(141)) ? i : i2;
        if (i3 <= 2) {
            i3 = i3 == 0 ? 2 : i3 - 1;
        }
        if (activeSafeModel == null) {
            return new ActiveSafeModel(i, i2, (byte) i3);
        }
        if (i3 != activeSafeModel.pos) {
            activeSafeModel.setPos((byte) i3);
        }
        activeSafeModel.updateTagSubState(i, i2);
        return activeSafeModel;
    }

    @Override // b.b.k.c.a, b.b.k.b.a
    public void a() {
        super.a();
        CountDownTimer countDownTimer = this.f1199e;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f1199e = null;
        }
        this.f1197c = null;
        this.f1198d = null;
    }

    public final void g(b.b.k.a.b.a<l, ActiveSafeModel> aVar, final ActiveSafeModel activeSafeModel, final View view, final int i) {
        BydLogHelper.logD("closeLaneAssist");
        if (this.f1198d.P(11)) {
            aVar.j(activeSafeModel, R.string.dipilot_lane_assist_title, R.string.dipilot_drive_lane_assist_close_content, R.string.dipilot_dialog_only_effect_cur_journey, new b.b.k.a.a() { // from class: b.b.g.n.l.f.f
                @Override // b.b.k.a.a
                public final void f(boolean z) {
                    this.f1181a.B(activeSafeModel, view, i, z);
                }
            });
        } else {
            A(activeSafeModel, true, view, i);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h(int r4, int r5, boolean r6, b.b.a.c.a r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.g.n.l.f.l.h(int, int, boolean, b.b.a.c.a, boolean):boolean");
    }

    public final void i(int i, int i2, boolean z, b.b.a.c.a aVar, boolean z2) {
        int i3 = 0;
        switch (i) {
            case 210014:
                BydLogHelper.logD(this.f1952a, "ALARM_TYPE_CHANGE " + i2);
                DriverAssistModel driverAssistModelP = this.f1198d.p();
                if (driverAssistModelP != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.n();
                    }
                    if (i2 == 0) {
                        i3 = 1;
                    } else if (1 != i2) {
                        i3 = i2;
                    }
                    driverAssistModelP.setTag(i3);
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent ALARM_TYPE_CHANGE model null!");
                }
                break;
            case 210018:
                BydLogHelper.logD(this.f1952a, "MISTAKE_GAS_STATE_CHANGE " + i2);
                ActiveSafeModel activeSafeModelC = this.f1198d.c(R.string.dipilot_title_mistake_gas);
                if (activeSafeModelC != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.G();
                    }
                    activeSafeModelC.setAssistState(4 == i2 || 2 == i2);
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent MISTAKE_GAS_STATE_CHANGE model null!");
                }
                break;
            case 210028:
                BydLogHelper.logD(this.f1952a, "AI_EMERGENCY_BRAKE_STATE_CHANGE " + i2);
                DriverAssistModel driverAssistModelQ = this.f1198d.q();
                if (driverAssistModelQ != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.j();
                    }
                    driverAssistModelQ.setAssistState(1 == i2);
                    driverAssistModelQ.pos = (byte) i2;
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent AI_EMERGENCY_BRAKE_STATE_CHANGE model null!");
                }
                break;
            case 210032:
                BydLogHelper.logD(this.f1952a, "ELEC_CAR_BODY_STABLY_SYS_STATE_CHANGE " + i2);
                ActiveSafeModel activeSafeModelC2 = this.f1198d.c(R.string.dipilot_title_steady_body_system);
                if (activeSafeModelC2 != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.v();
                    }
                    activeSafeModelC2.setBtnEnable(i2 == 1, c(18));
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent ELEC_CAR_BODY_STABLY_SYS_STATE_CHANGE model null!");
                }
                break;
            case 210033:
                BydLogHelper.logD(this.f1952a, "AUTO_PARKING_STATE_CHANGE " + i2);
                ActiveSafeModel activeSafeModelR = this.f1198d.r();
                if (activeSafeModelR != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.o();
                    }
                    activeSafeModelR.setAssistState(i2 == 2 || i2 == 1);
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent AUTO_PARKING_STATE_CHANGE model null!");
                }
                break;
            case 210034:
                BydLogHelper.logD(this.f1952a, "STEEP_DESCENT_SLOW_DOWN_STATE_CHANGE " + i2);
                ActiveSafeModel activeSafeModelC3 = this.f1198d.c(R.string.dipilot_title_HDC);
                if (activeSafeModelC3 != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.U();
                    }
                    activeSafeModelC3.setBtnEnable(activeSafeModelC3.isDefaultChecked(i2), 3 != i2);
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent STEEP_DESCENT_SLOW_DOWN_STATE_CHANGE model null!");
                }
                break;
            case 210047:
                BydLogHelper.logD(this.f1952a, "LANE_ASSIST_TYPE_CHANGE " + i2);
                ActiveSafeModel activeSafeModelB = this.f1198d.B();
                if (activeSafeModelB != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.E();
                    }
                    this.f1198d.v0(activeSafeModelB.getTag(), i2, Router.b().d().g((byte) 11), activeSafeModelB);
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent LANE_ASSIST_TYPE_CHANGE model null!");
                }
                break;
            case 220004:
                if (this.f1198d.t() != null) {
                    BydLogHelper.logD(this.f1952a, "ON_EPB_STATE_CHANGED " + i2);
                    if (b.b.g.r.e.e(aVar)) {
                        i2 = this.f1197c.u();
                    }
                    ActiveSafeViewModel activeSafeViewModel = this.f1198d;
                    activeSafeViewModel.u0(i2, activeSafeViewModel.t());
                } else {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent ON_EPB_STATE_CHANGED model null!");
                }
                break;
            default:
                j(i, i2, z, aVar, z2);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(int r7, int r8, boolean r9, b.b.a.c.a r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.g.n.l.f.l.j(int, int, boolean, b.b.a.c.a, boolean):void");
    }

    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.d
    @SuppressLint({"NonConstantResourceId"})
    public void k(final View view, View view2, int i) {
        BydLogHelper.logD(this.f1952a, "onClickedButton position = " + i);
        b.b.k.a.b.a<l, ActiveSafeModel> aVar = (b.b.k.a.b.a) getV();
        if (this.f1197c == null) {
            BydLogHelper.logW(this.f1952a, "onClickedButton not handled! fPresent null!");
        }
        if (this.f1198d == null) {
            BydLogHelper.logW(this.f1952a, "onClickedButton not handled! viewModel null!");
            return;
        }
        if (aVar == null) {
            BydLogHelper.logW(this.f1952a, "onClickedButton not handled! v null!");
            return;
        }
        if (aVar.i()) {
            BydLogHelper.logW(this.f1952a, "onClickedButton not handled! v is destroyed!");
            return;
        }
        switch (view.getId()) {
            case R.id.sbg_auto_manual_control_speed /* 2131298063 */:
                if (i < 2) {
                    this.f1197c.I0(i + 1);
                }
                break;
            case R.id.sbg_lane_assist /* 2131298066 */:
                ActiveSafeModel activeSafeModelB = this.f1198d.B();
                if (!y(activeSafeModelB, view)) {
                    if (i == 0) {
                        this.f1197c.t0(2);
                        T(true);
                        break;
                    } else if (i == 1) {
                        this.f1197c.t0(5);
                        T(false);
                        break;
                    } else if (i == 2) {
                        if (!this.f1198d.N()) {
                            g(aVar, activeSafeModelB, view, 2);
                        } else {
                            this.f1197c.t0(4);
                            T(true);
                        }
                        break;
                    } else if (i == 3) {
                        g(aVar, activeSafeModelB, view, 3);
                        break;
                    }
                }
                break;
            case R.id.sbg_lane_assist_alarm_mode /* 2131298067 */:
                if (!y(this.f1198d.p(), view) && i < 3) {
                    b.b.g.n.l.a aVar2 = this.f1197c;
                    if (i == 0) {
                        i = 2;
                    } else if (2 == i) {
                        i = 3;
                    }
                    aVar2.h0(i);
                }
                break;
            case R.id.sbg_mois_drive_area_system /* 2131298069 */:
                if (!y(this.f1198d.F(), view) && i < 2) {
                    if (i == 0 && this.f1198d.F().getTag() > 1) {
                        z.b(aVar.getContext(), R.string.dipilot_dialog_only_effect_cur_journey);
                    }
                    this.f1197c.v0(i + 2);
                }
                break;
            case R.id.sbg_predict_crash_alert /* 2131298072 */:
                if (!y(this.f1198d.u(), view) && i < 4) {
                    if (i != 3) {
                        this.f1197c.x0(i + 2);
                    } else {
                        final ActiveSafeStateModel activeSafeStateModelU = this.f1198d.u();
                        final boolean z = this.f1197c.f() == 1;
                        int i2 = z ? R.string.dipilot_new_urgent_warning_message_title : R.string.dipilot_title_dialog_hint;
                        int i3 = z ? R.string.dipilot_new_urgent_warning_message : R.string.dipilot_disable_toast;
                        int i4 = z ? R.string.dipilot_dialog_only_effect_cur_journey : 0;
                        if (z && !this.f1198d.P(15)) {
                            I(activeSafeStateModelU, true, view);
                        } else {
                            aVar.j(activeSafeStateModelU, i2, i3, i4, new b.b.k.a.a() { // from class: b.b.g.n.l.f.e
                                @Override // b.b.k.a.a
                                public final void f(boolean z2) {
                                    this.f1177a.L(activeSafeStateModelU, z, view, z2);
                                }
                            });
                        }
                    }
                }
                break;
            case R.id.sbg_speed_control_alarm_sensitivity /* 2131298076 */:
                if (i <= 2) {
                    i = i == 2 ? 0 : i + 1;
                }
                this.f1197c.D0(i);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:74:0x018d A[PHI: r4 r7
  0x018d: PHI (r4v6 com.byd.dipilot.model.DriverAssistModel) = 
  (r4v2 com.byd.dipilot.model.DriverAssistModel)
  (r4v3 com.byd.dipilot.model.DriverAssistModel)
  (r4v4 com.byd.dipilot.model.DriverAssistModel)
  (r4v7 com.byd.dipilot.model.DriverAssistModel)
 binds: [B:89:0x0211, B:84:0x01e6, B:79:0x01bb, B:73:0x018b] A[DONT_GENERATE, DONT_INLINE]
  0x018d: PHI (r7v9 int) = (r7v2 int), (r7v4 int), (r7v6 int), (r7v10 int) binds: [B:89:0x0211, B:84:0x01e6, B:79:0x01bb, B:73:0x018b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0190 A[PHI: r4 r7
  0x0190: PHI (r4v5 com.byd.dipilot.model.DriverAssistModel) = 
  (r4v2 com.byd.dipilot.model.DriverAssistModel)
  (r4v3 com.byd.dipilot.model.DriverAssistModel)
  (r4v4 com.byd.dipilot.model.DriverAssistModel)
  (r4v7 com.byd.dipilot.model.DriverAssistModel)
 binds: [B:89:0x0211, B:84:0x01e6, B:79:0x01bb, B:73:0x018b] A[DONT_GENERATE, DONT_INLINE]
  0x0190: PHI (r7v8 int) = (r7v2 int), (r7v4 int), (r7v6 int), (r7v10 int) binds: [B:89:0x0211, B:84:0x01e6, B:79:0x01bb, B:73:0x018b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0217  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(int r6, int r7, b.b.a.c.a r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.g.n.l.f.l.l(int, int, b.b.a.c.a, boolean):void");
    }

    public final boolean m(int i, int i2) {
        if ((i > 1 && i <= 5) || (i2 > 1 && i2 <= 5)) {
            return true;
        }
        if (i2 != 1 || i == 1) {
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @SuppressLint({"NonConstantResourceId"})
    public final int[] n(ActiveSafeModel activeSafeModel) {
        int[] iArr = new int[2];
        switch (activeSafeModel.getTag()) {
            case R.string.dipilot_blind_spot_detection /* 2131755272 */:
                iArr[0] = R.string.dipilot_blind_side_detect_hint_message;
                iArr[1] = R.string.dipilot_blind_side_detect_attention_message;
                return iArr;
            case R.string.dipilot_high_beam_title /* 2131755349 */:
                iArr[0] = R.string.dipilot_high_beam_hint_message;
                iArr[1] = R.string.dipilot_high_beam_attenttion_message;
                return iArr;
            case R.string.dipilot_title_auto_parking /* 2131755449 */:
                iArr[0] = R.string.dipilot_auto_parking_hint_message;
                return iArr;
            case R.string.dipilot_title_open_door_warning /* 2131755457 */:
                iArr[0] = R.string.dipilot_open_door_warning_hint_message;
                iArr[1] = R.string.dipilot_open_door_warning_attention_message;
                return iArr;
            case R.string.dipilot_title_steady_body_system /* 2131755464 */:
                iArr[0] = R.string.dipilot_esp_hint_message;
                return iArr;
            case R.string.dipilot_traffic_light_title /* 2131755468 */:
                iArr[0] = R.string.dipilot_traffic_light_hint_message;
                iArr[1] = R.string.dipilot_traffic_light_attenttion_message;
                return iArr;
            case R.string.dipilot_urgent_lane_title /* 2131755482 */:
                iArr[0] = R.string.dipilot_urgent_lane_hint_message;
                iArr[1] = R.string.dipilot_urgent_lane_attenttion_message;
                return iArr;
            default:
                return iArr;
        }
    }

    public final void o(IStudyConfig iStudyConfig, b.b.g.n.l.a aVar, ActiveSafeViewModel activeSafeViewModel) {
        boolean zG = Router.b().d().g((byte) 13);
        if (iStudyConfig.c(13)) {
            byte bJ = (byte) aVar.j();
            activeSafeViewModel.R(new DriverAssistModel(bJ == 1, zG, bJ, R.id.switch_ai_emergency_brake, bJ));
        }
        if (iStudyConfig.c(14)) {
            byte bN = (byte) aVar.N();
            activeSafeViewModel.l0(new DriverAssistModel(2 == bN || 1 == bN, x(bN), R.string.dipilot_title_rear_collision_warning, 0, bN));
        }
        if (iStudyConfig.c(15)) {
            int iV = aVar.V();
            ActiveSafeStateModel activeSafeStateModel = null;
            if (iV != 1) {
                int iK = (byte) aVar.K();
                byte bL = (byte) aVar.L();
                activeSafeStateModel = new ActiveSafeStateModel(false, zG, iV, iK, X(iK, bL), bL);
            }
            activeSafeViewModel.W(activeSafeStateModel);
        }
        if (iStudyConfig.c(16)) {
            activeSafeViewModel.Y(new DriverAssistModel(aVar.z(), R.string.dipilot_title_ahead_traffic_crossing_warning));
        }
        if (iStudyConfig.c(17)) {
            activeSafeViewModel.X(new DriverAssistModel(aVar.y(), R.string.dipilot_title_forward_traffic_crossing_brake));
        }
        if (iStudyConfig.c(18)) {
            activeSafeViewModel.m0(new DriverAssistModel(aVar.P(), R.string.dipilot_title_rear_traffic_crossing_alert));
        }
        if (iStudyConfig.c(19)) {
            activeSafeViewModel.k0(new DriverAssistModel(aVar.O(), R.string.dipilot_title_rear_traffic_crossing_brake));
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    @SuppressLint({"NonConstantResourceId"})
    public void onCheckedChanged(final CompoundButton compoundButton, boolean z) {
        b.b.k.a.b.a aVar = (b.b.k.a.b.a) getV();
        if (this.f1197c == null) {
            BydLogHelper.logW(this.f1952a, "onCheckedChanged not handled! fPresent null!");
            return;
        }
        if (this.f1198d == null) {
            BydLogHelper.logW(this.f1952a, "onCheckedChanged not handled! viewModel null!");
            return;
        }
        if (aVar == null) {
            BydLogHelper.logW(this.f1952a, "onCheckedChanged not handled! v null!");
            return;
        }
        if (aVar.i()) {
            BydLogHelper.logW(this.f1952a, "onCheckedChanged not handled! v is destroyed!");
            return;
        }
        switch (compoundButton.getId()) {
            case R.id.bs_lane_assist2 /* 2131296450 */:
                int tag = this.f1198d.B().getTag();
                if (1 == tag || 4 == tag) {
                    this.f1197c.t0(z ? 1 + tag : 1);
                }
                break;
            case R.id.saoc_switch /* 2131298021 */:
                BydLogHelper.logD("onCheckedChanged saoc switch isChecked = " + z);
                if (!z) {
                    aVar.j(this.f1198d.J(), R.string.dipilot_saoc, R.string.dipilot_saoc_close_confirm, 0, new b.b.k.a.a() { // from class: b.b.g.n.l.f.a
                        @Override // b.b.k.a.a
                        public final void f(boolean z2) {
                            this.f1167a.D(compoundButton, z2);
                        }
                    });
                } else {
                    this.f1197c.B0(2);
                }
                break;
            case R.id.slif_switch /* 2131298131 */:
                this.f1197c.C0(z ? 2 : 1);
                break;
            case R.id.switch_ai_emergency_brake /* 2131298219 */:
                if (!z) {
                    final boolean z2 = this.f1197c.f() == 1;
                    int i = z2 ? R.string.dipilot_new_ergency_braking_message_title : R.string.dipilot_title_dialog_hint;
                    int i2 = z2 ? R.string.dipilot_new_ergency_braking_message : R.string.dipilot_disable_toast;
                    int i3 = z2 ? R.string.dipilot_dialog_only_effect_cur_journey : 0;
                    if (z2 && !this.f1198d.P(13)) {
                        I(this.f1198d.q(), true, compoundButton);
                    } else {
                        aVar.j(this.f1198d.q(), i, i2, i3, new b.b.k.a.a() { // from class: b.b.g.n.l.f.c
                            @Override // b.b.k.a.a
                            public final void f(boolean z3) {
                                this.f1172a.F(z2, compoundButton, z3);
                            }
                        });
                    }
                } else {
                    this.f1197c.f0(2);
                }
                break;
            case R.id.switch_auto_parke /* 2131298222 */:
                this.f1197c.i0(z ? 1 : 2);
                break;
            case R.id.switch_bsis /* 2131298223 */:
                if (!z) {
                    z.b(aVar.getContext(), R.string.dipilot_dialog_only_effect_cur_journey);
                }
                this.f1197c.k0(z ? 2 : 1);
                break;
            case R.id.switch_epb /* 2131298238 */:
                this.f1197c.l0(z ? 1 : 2);
                break;
            case R.id.switch_front_lateral_approach_braking /* 2131298244 */:
                this.f1197c.o0(z ? 2 : 1);
                break;
            case R.id.switch_front_lateral_approach_warning /* 2131298245 */:
                this.f1197c.p0(z ? 2 : 1);
                break;
            case R.id.switch_gas_pedal /* 2131298246 */:
                b.b.g.n.q.m.p0().X2(z ? 1 : 2);
                break;
            case R.id.switch_intelligent_speed_control /* 2131298247 */:
                this.f1197c.r0(z ? 2 : 1);
                break;
            case R.id.switch_intelligent_speed_control_alarm /* 2131298248 */:
                this.f1197c.q0(z ? 2 : 1);
                if ((!z) & (this.f1198d.M().getValue().byteValue() != 3)) {
                    aVar.j(this.f1198d.y(), R.string.dipilot_intelligent_speed_limit_alert, R.string.tips_close_slw, 0, null);
                }
                break;
            case R.id.switch_mois_drive_area_system /* 2131298260 */:
                final ActiveSafeModel activeSafeModelF = this.f1198d.F();
                if (activeSafeModelF == null) {
                    return;
                }
                if ((activeSafeModelF.getTag() < 2) == z) {
                    return;
                }
                if (!z) {
                    aVar.j(activeSafeModelF, R.string.adas_move_off_close_title, R.string.dipilot_mois_close_message, R.string.dipilot_dialog_only_effect_cur_journey, new b.b.k.a.a() { // from class: b.b.g.n.l.f.b
                        @Override // b.b.k.a.a
                        public final void f(boolean z3) {
                            this.f1169a.J(activeSafeModelF, compoundButton, z3);
                        }
                    });
                } else {
                    this.f1197c.v0(3);
                }
                break;
            case R.id.switch_rear_crash_alert /* 2131298269 */:
                this.f1197c.y0(z ? 2 : 1);
                break;
            case R.id.switch_rear_lateral_approach_braking /* 2131298270 */:
                this.f1197c.z0(z ? 2 : 1);
                break;
            case R.id.switch_rear_lateral_approach_warning /* 2131298271 */:
                this.f1197c.A0(z ? 2 : 1);
                break;
            case R.id.switch_title_traffic_signs_recognition /* 2131298282 */:
                if (!z && Router.b().f().c(65) && this.f1197c.g() == 1) {
                    aVar.j(this.f1198d.L(), 0, R.string.dipilot_close_advanced_navi_aids_driving, 0, new b.b.k.a.a() { // from class: b.b.g.n.l.f.h
                        @Override // b.b.k.a.a
                        public final void f(boolean z3) {
                            this.f1188a.H(z3);
                        }
                    });
                } else {
                    this.f1197c.H0(z ? 2 : 1);
                    this.f1198d.L().setAssistState(z);
                }
                break;
            case R.id.switch_title_traffic_sound_remind_recognition /* 2131298283 */:
                this.f1197c.F0(z);
                break;
            case R.id.switch_tsr_flow_control /* 2131298285 */:
                this.f1198d.r0();
                break;
        }
        BydLogHelper.logD(this.f1952a, "onCheckedChanged =" + z);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.b.a.c.a aVar) {
        int iH = aVar.h();
        int iC = aVar.c();
        BydLogHelper.logD(this.f1952a, "onMessageEvent event code: " + iH + ", state: " + iC);
        b.b.k.a.b.a aVar2 = (b.b.k.a.b.a) getV();
        if (this.f1197c == null) {
            BydLogHelper.logW(this.f1952a, "onMessageEvent not handled! fPresent null!");
            return;
        }
        if (this.f1198d == null) {
            BydLogHelper.logW(this.f1952a, "onMessageEvent not handled! viewModel null!");
            return;
        }
        if (aVar2 == null) {
            BydLogHelper.logW(this.f1952a, "onMessageEvent not handled! v null!");
            return;
        }
        if (aVar2.i()) {
            BydLogHelper.logW(this.f1952a, "onMessageEvent not handled! v is destroyed!");
            return;
        }
        boolean z = true;
        boolean z2 = iC == 0;
        switch (iH) {
            case 210020:
                BydLogHelper.logD(this.f1952a, "AI_DISTANCE_LIGHT_CHANGE state = " + iC);
                ActiveSafeModel activeSafeModelC = this.f1198d.c(R.string.dipilot_high_beam_title);
                if (activeSafeModelC == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent AI_DISTANCE_LIGHT_316_STATE_CHANGE model null!");
                    return;
                } else {
                    this.f1198d.d0(this.f1197c.h(), this.f1197c.i(), activeSafeModelC);
                    return;
                }
            case 210023:
                ActiveSafeModel activeSafeModelC2 = this.f1198d.c(R.string.dipilot_blind_spot_detection);
                if (activeSafeModelC2 == null) {
                    return;
                }
                boolean zD = Router.b().f().d(1);
                b.b.g.n.l.a aVar3 = this.f1197c;
                int iQ = zD ? aVar3.q() : aVar3.r();
                activeSafeModelC2.setAssistState(activeSafeModelC2.isDefaultChecked(iQ));
                activeSafeModelC2.setEnable(x(iQ));
                activeSafeModelC2.pos = (byte) iQ;
                BydLogHelper.logD(this.f1952a, "onMessageEvent BLIND_MONITOR_STATE_CHANGE ! isNewType = " + zD + ", state = " + iQ);
                return;
            case 210024:
                BydLogHelper.logD(this.f1952a, "OPEN_DOOR_ALERT_STATE_CHANGE " + iC);
                ActiveSafeModel activeSafeModelC3 = this.f1198d.c(R.string.dipilot_title_open_door_warning);
                if (activeSafeModelC3 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent OPEN_DOOR_ALERT_STATE_CHANGE model null!");
                    return;
                }
                if (b.b.g.r.e.e(aVar)) {
                    iC = this.f1197c.J();
                }
                activeSafeModelC3.setBtnEnable(activeSafeModelC3.isDefaultChecked(iC), x(iC));
                activeSafeModelC3.pos = (byte) iC;
                return;
            case 210065:
                ActiveSafeModel activeSafeModelC4 = this.f1198d.c(R.string.dipilot_traffic_light_title);
                if (activeSafeModelC4 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent TRAFFIC_LIGHT_RECOGNIZE_2B4_STATE_CHANGE model null!");
                    return;
                }
                boolean zC = c(141);
                b.b.g.n.l.a aVar4 = this.f1197c;
                int iZ = zC ? aVar4.Z() : aVar4.Y();
                BydLogHelper.logD(this.f1952a, "onMessageEvent TRAFFIC_LIGHT_RECOGNIZE_STATE_CHANGE has2D5Online = " + zC + ", state = " + iZ);
                activeSafeModelC4.setBtnEnable(activeSafeModelC4.isDefaultChecked(iZ), x(iZ));
                activeSafeModelC4.pos = (byte) iZ;
                return;
            case 210067:
                ActiveSafeModel activeSafeModelC5 = this.f1198d.c(R.string.dipilot_urgent_lane_title);
                if (activeSafeModelC5 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent EMERGENCY_LANE_ASSISTANCE_STATE_CHANGE model null!");
                    return;
                }
                boolean zC2 = c(143);
                b.b.g.n.l.a aVar5 = this.f1197c;
                int iW = zC2 ? aVar5.w() : aVar5.x();
                BydLogHelper.logD(this.f1952a, "onMessageEvent EMERGENCY_LANE_ASSISTANCE_STATE_CHANGE ! hasOnline = " + zC2 + ", state = " + iW);
                U((byte) iW, activeSafeModelC5);
                return;
            case 210071:
                BydLogHelper.logD("AI_MATRIX_LIGHT_3AC_STATE_CHANGE " + iC);
                break;
            case 210072:
                z = false;
                break;
            case 210083:
                ActiveSafeModel activeSafeModelC6 = this.f1198d.c(R.string.dipilot_title_open_door_warning);
                if (activeSafeModelC6 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent OPEN_DOOR_ALERT_STATE_FAULT model null!");
                    return;
                } else {
                    activeSafeModelC6.setNormal(z2);
                    return;
                }
            case 210089:
                ActiveSafeModel activeSafeModelC7 = this.f1198d.c(R.string.dipilot_blind_spot_detection);
                if (activeSafeModelC7 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent BLIND_MONITOR_STATE_FAULT model null!");
                    return;
                } else {
                    activeSafeModelC7.setNormal(z2);
                    return;
                }
            case 210093:
                ActiveSafeModel activeSafeModelC8 = this.f1198d.c(R.string.dipilot_urgent_lane_title);
                if (activeSafeModelC8 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent EMERGENCY_LANE_KEEP_FAULT model null!");
                    return;
                } else {
                    activeSafeModelC8.setNormal(z2);
                    return;
                }
            case 210095:
            case 210096:
                this.f1198d.f0(z2);
                return;
            case 210098:
                ActiveSafeModel activeSafeModelC9 = this.f1198d.c(R.string.dipilot_high_beam_title);
                if (activeSafeModelC9 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent ADAP_TIVE_FRONT_LIGHT_FAULT model null!");
                    return;
                } else {
                    activeSafeModelC9.setNormal(z2);
                    return;
                }
            case 210099:
                ActiveSafeModel activeSafeModelC10 = this.f1198d.c(R.string.dipilot_traffic_light_title);
                if (activeSafeModelC10 == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent TRAFFIC_ATTENTION_FAULT model null!");
                    return;
                } else {
                    activeSafeModelC10.setNormal(z2);
                    return;
                }
            case R.id.tv_lane_assist /* 2131298531 */:
                ActiveSafeModel activeSafeModelB = this.f1198d.B();
                if (activeSafeModelB == null) {
                    BydLogHelper.logW(this.f1952a, "onMessageEvent tv_lane_assist model null!");
                    return;
                } else {
                    activeSafeModelB.setEnable(z2);
                    this.f1198d.s0(z2);
                    return;
                }
            default:
                i(iH, iC, false, aVar, z2);
                return;
        }
        BydLogHelper.logD("AI_MATRIX_LIGHT_3AD_STATE_CHANGE " + iC);
        ActiveSafeModel activeSafeModelC11 = this.f1198d.c(R.string.dipilot_smart_matrix_light);
        if (activeSafeModelC11 == null) {
            BydLogHelper.logW(this.f1952a, "onMessageEvent AI_MATRIX_LIGHT_3AD_STATE_CHANGE model null!");
            return;
        }
        if (b.b.g.r.e.e(aVar)) {
            b.b.g.n.l.a aVar6 = this.f1197c;
            iC = z ? aVar6.l() : aVar6.m();
        }
        activeSafeModelC11.setAssistState(m(z ? iC : activeSafeModelC11.getPos(), z ? activeSafeModelC11.getAssistPicId() : iC));
        if (z) {
            activeSafeModelC11.pos = (byte) iC;
        } else {
            activeSafeModelC11.setAssistPicId(iC);
        }
    }

    public final void q(IStudyConfig iStudyConfig, b.b.g.n.l.a aVar, ActiveSafeViewModel activeSafeViewModel) {
        int i = 2;
        if (iStudyConfig.c(26)) {
            int iT = aVar.t();
            boolean zS = aVar.s();
            BydLogHelper.logI(this.f1952a, "bsisState =" + iT + ", bsisEnable=" + zS);
            activeSafeViewModel.T(new DriverAssistModel(iT == 2, zS, zS ? 1 : 0, iT));
        }
        if (iStudyConfig.c(27)) {
            int I = aVar.I();
            boolean zH = aVar.H();
            BydLogHelper.logI(this.f1952a, "moveOffState =" + I + ", moisEnable=" + zH);
            if (I != 1 && I != 0) {
                i = I - 2;
            }
            activeSafeViewModel.j0(new ActiveSafeModel(zH, zH, 0, I, (byte) i));
        }
        if (iStudyConfig.c(28)) {
            activeSafeViewModel.q0();
        }
        if (iStudyConfig.c(29)) {
            this.f1198d.g0(aVar.F());
        }
        if (iStudyConfig.c(30)) {
            this.f1198d.Z(b.b.g.n.q.m.p0().H0());
        }
    }

    public final void r(IStudyConfig iStudyConfig, b.b.g.n.l.a aVar, ActiveSafeViewModel activeSafeViewModel) {
        activeSafeViewModel.m(new ArrayList(8));
        if (iStudyConfig.c(0)) {
            this.f1198d.a(U((byte) (c(143) ? aVar.w() : aVar.x()), null));
        }
        if (iStudyConfig.c(1)) {
            byte bQ = (byte) (iStudyConfig.d(1) ? aVar.q() : aVar.r());
            this.f1198d.a(new ActiveSafeModel(2 == bQ || 1 == bQ, x(bQ), R.string.dipilot_blind_spot_detection, bQ));
        }
        if (iStudyConfig.c(2)) {
            byte bJ = (byte) aVar.J();
            this.f1198d.a(new ActiveSafeModel(2 == bJ || 1 == bJ, x(bJ), R.string.dipilot_title_open_door_warning, bJ));
        }
        if (iStudyConfig.c(3)) {
            byte bZ = (byte) (c(141) ? aVar.Z() : aVar.Y());
            this.f1198d.a(new ActiveSafeModel(2 == bZ || 1 == bZ, x(bZ), R.string.dipilot_traffic_light_title, bZ));
        }
        if (iStudyConfig.d(4)) {
            int iL = (byte) aVar.l();
            byte bM = (byte) aVar.m();
            this.f1198d.a(new ActiveSafeModel(m(iL, bM), true, R.string.dipilot_smart_matrix_light, iL, bM));
        } else if (iStudyConfig.c(4)) {
            this.f1198d.d0(aVar.h(), aVar.i(), null);
        }
        if (iStudyConfig.c(5)) {
            byte bV = (byte) aVar.v();
            boolean zC = c(18);
            BydLogHelper.logW(this.f1952a, "initListData, 0x0123 online = " + zC);
            this.f1198d.a(new ActiveSafeModel(bV == 1, zC, R.string.dipilot_title_steady_body_system, R.raw.dipilot_st_esc, bV));
        }
        if (iStudyConfig.c(6)) {
            byte bU = (byte) aVar.U();
            this.f1198d.a(new ActiveSafeModel(bU == 1 || bU == 2, true, R.string.dipilot_title_HDC, R.raw.dipilot_st_hdc, bU));
        }
        if (iStudyConfig.c(7)) {
            byte bG = (byte) aVar.G();
            this.f1198d.a(new ActiveSafeModel(4 == bG || bG == 2, true, R.string.dipilot_title_mistake_gas, R.raw.dipilot_st_gas, bG));
        }
    }

    public void s(ActiveSafeViewModel activeSafeViewModel) {
        this.f1198d = activeSafeViewModel;
        b.b.g.n.l.a aVar = this.f1197c;
        if (!ListUtils.isEmpty(activeSafeViewModel.d())) {
            BydLogHelper.logD(this.f1952a, " viewMode has init data unnecessary to");
            return;
        }
        boolean zW = aVar.W();
        BydLogHelper.logD(this.f1952a, "initStudy isEuropeVer = " + zW);
        activeSafeViewModel.V(zW);
        IStudyConfig iStudyConfigF = Router.b().f();
        r(iStudyConfigF, aVar, activeSafeViewModel);
        t(iStudyConfigF, aVar, activeSafeViewModel);
        o(iStudyConfigF, aVar, activeSafeViewModel);
        u(iStudyConfigF, aVar, activeSafeViewModel);
        q(iStudyConfigF, aVar, activeSafeViewModel);
        if (!ListUtils.isEmpty(activeSafeViewModel.d())) {
            ((b.b.k.a.b.a) getV()).g(activeSafeViewModel.d());
        }
        if (this.f1953b) {
            return;
        }
        BydLogHelper.logD(this.f1952a, "initStudy register eventbus!");
        EventBus.getDefault().register(this);
        this.f1953b = true;
    }

    public final void t(IStudyConfig iStudyConfig, b.b.g.n.l.a aVar, ActiveSafeViewModel activeSafeViewModel) {
        if (iStudyConfig.c(9)) {
            byte bO = (byte) aVar.o();
            activeSafeViewModel.S(new ActiveSafeModel(bO == 2 || bO == 1, true, R.string.dipilot_title_auto_parking, 0, bO));
        }
        if (iStudyConfig.c(10)) {
            ActiveSafeViewModel activeSafeViewModel2 = this.f1198d;
            activeSafeViewModel2.U(activeSafeViewModel2.u0(aVar.u(), null));
        }
        byte bE = iStudyConfig.e();
        IAdasDataRepository iAdasDataRepositoryD = Router.b().d();
        iAdasDataRepositoryD.f(bE);
        boolean zG = iAdasDataRepositoryD.g((byte) 11);
        if (bE > 0) {
            activeSafeViewModel.e0(this.f1198d.v0(bE, iAdasDataRepositoryD.e(), zG, null), iAdasDataRepositoryD.g((byte) 12));
        }
        if (iStudyConfig.c(12)) {
            byte bN = (byte) aVar.n();
            if (bN == 0) {
                bN = 1;
            } else if (1 == bN) {
                bN = 0;
            }
            activeSafeViewModel.Q(new DriverAssistModel(bN));
            this.f1198d.s0(zG);
        }
        if (iStudyConfig.c(33)) {
            this.f1198d.n0(new DriverAssistModel(aVar.Q() == 2, true, 0, 0));
        }
    }

    public final void u(IStudyConfig iStudyConfig, b.b.g.n.l.a aVar, ActiveSafeViewModel activeSafeViewModel) {
        if (iStudyConfig.c(20)) {
            if (iStudyConfig.c(32)) {
                this.f1198d.o0(new DriverAssistModel(this.f1197c.R() == 2, true, 0, 0));
            }
            this.f1198d.w0((byte) aVar.b0(), Router.b().d().c(), aVar.c0(), null);
            if (iStudyConfig.c(21)) {
                this.f1198d.h0(new DriverAssistModel(this.f1197c.X(), true, 0, 0));
            }
            if (iStudyConfig.c(22)) {
                this.f1198d.a0(V(aVar.B(), aVar.A(), null));
                if (!activeSafeViewModel.N()) {
                    this.f1198d.c0(Y(aVar.T(), aVar.S(), null));
                }
            }
            if (iStudyConfig.c(24)) {
                this.f1198d.b0(W(aVar.C(), aVar.D(), null));
            }
            if (iStudyConfig.c(25)) {
                this.f1198d.i0((byte) aVar.d0());
            }
        }
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
    }

    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public void J(DriverAssistModel driverAssistModel, boolean z, View view) {
        A(driverAssistModel, z, view, 0);
    }

    @SuppressLint({"NonConstantResourceId"})
    /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
    public void B(DriverAssistModel driverAssistModel, boolean z, View view, int i) {
        if (this.f1197c == null || getV() == 0 || this.f1198d == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.saoc_switch /* 2131298021 */:
                if (!z) {
                    driverAssistModel.setAssistState(driverAssistModel.getAssistState());
                } else {
                    this.f1197c.B0(1);
                    driverAssistModel.setAssistState(false);
                }
                break;
            case R.id.sbg_lane_assist /* 2131298066 */:
                ActiveSafeModel activeSafeModel = (ActiveSafeModel) driverAssistModel;
                if (!z) {
                    activeSafeModel.setPos(activeSafeModel.getPos());
                } else {
                    activeSafeModel.setPos((byte) i);
                    this.f1197c.t0(1);
                    T(false);
                }
                break;
            case R.id.sbg_predict_crash_alert /* 2131298072 */:
                ActiveSafeStateModel activeSafeStateModelU = this.f1198d.u();
                if (z) {
                    this.f1197c.x0(1);
                    activeSafeStateModelU.setPos((byte) 3);
                } else if (activeSafeStateModelU != null) {
                    activeSafeStateModelU.setPos(activeSafeStateModelU.getPos());
                }
                break;
            case R.id.switch_ai_emergency_brake /* 2131298219 */:
                if (!z) {
                    driverAssistModel.setAssistState(driverAssistModel.getAssistState());
                } else {
                    this.f1197c.f0(1);
                    driverAssistModel.setAssistState(false);
                }
                break;
            case R.id.switch_mois_drive_area_system /* 2131298260 */:
                ActiveSafeModel activeSafeModelF = this.f1198d.F();
                if (!z) {
                    activeSafeModelF.setPos(activeSafeModelF.getPos());
                } else {
                    this.f1197c.v0(1);
                }
                break;
        }
    }

    public final boolean x(int i) {
        return i != 3;
    }

    public final boolean y(@NonNull DriverAssistModel driverAssistModel, View view) {
        if (!driverAssistModel.isTouch()) {
            return false;
        }
        this.f1198d.o(view);
        if (driverAssistModel instanceof ActiveSafeModel) {
            ((ActiveSafeModel) driverAssistModel).setPos(driverAssistModel.pos);
            return true;
        }
        if (driverAssistModel != this.f1198d.p()) {
            return true;
        }
        driverAssistModel.setTag(driverAssistModel.getTag());
        return true;
    }

    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public final void P(LottieAnimationView lottieAnimationView, ActiveSafeModel activeSafeModel, boolean z) {
        if (activeSafeModel != null && R.string.dipilot_title_steady_body_system == activeSafeModel.getTag()) {
            if (!z) {
                activeSafeModel.setAssistState(activeSafeModel.getAssistState());
                return;
            }
            this.f1197c.m0(1);
            activeSafeModel.setAssistState(true);
            if (lottieAnimationView != null) {
                lottieAnimationView.playAnimation();
            }
        }
    }
}
