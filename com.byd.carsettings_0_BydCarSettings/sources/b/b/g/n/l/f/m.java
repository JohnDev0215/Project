package b.b.g.n.l.f;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import b.b.g.r.z;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.inter.IStudyConfig;
import com.byd.carsettings.R;
import com.byd.dipilot.model.DriverAssistModel;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;
import com.byd.dipilot.viewmodel.NoConfigDialogModel;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: DriverAssistViewPresent.java */
/* JADX INFO: loaded from: classes2.dex */
public class m extends b.b.k.c.a<b.b.g.n.l.f.n.a> implements b.b.k.d.a.a<DriverAssistModel> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.g.n.l.d f1201c = new b.b.g.n.l.d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BaseAdasViewModel<DriverAssistModel, b.b.k.b.a> f1202d;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q(int i, boolean z) {
        m(null, i, z);
    }

    @Override // b.b.k.c.a, b.b.k.b.a
    public void a() {
        super.a();
        this.f1201c = null;
        this.f1202d = null;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final void n(LottieAnimationView lottieAnimationView, int i, boolean z) {
        FragmentActivity fragmentActivityD = ((b.b.g.n.l.f.n.a) getV()).d();
        if (this.f1202d == null || fragmentActivityD == null) {
            return;
        }
        NoConfigDialogModel noConfigDialogModel = (NoConfigDialogModel) new ViewModelProvider(fragmentActivityD, new ViewModelProvider.AndroidViewModelFactory(fragmentActivityD.getApplication())).get(NoConfigDialogModel.class);
        DriverAssistModel driverAssistModelF = noConfigDialogModel.f();
        if (!z) {
            if (noConfigDialogModel.k()) {
                driverAssistModelF.setAssistState(driverAssistModelF.getAssistState());
            }
        } else if (!noConfigDialogModel.k()) {
            if (noConfigDialogModel.c() == R.string.dipilot_intelligent_navigation_toast) {
                driverAssistModelF.setAssistState(driverAssistModelF.getAssistState());
            }
        } else {
            e(driverAssistModelF, true, i);
            if (lottieAnimationView != null) {
                lottieAnimationView.playAnimation();
            }
        }
    }

    @SuppressLint({"NonConstantResourceId"})
    public void e(DriverAssistModel driverAssistModel, boolean z, int i) {
        if (this.f1201c == null) {
            return;
        }
        switch (driverAssistModel.getTag()) {
            case R.string.dipilot_end_to_end_navigation_title /* 2131755344 */:
                this.f1201c.q(z ? 2 : 1);
                break;
            case R.string.dipilot_highway_navigation_title /* 2131755353 */:
                this.f1201c.r(z ? 2 : 1);
                break;
            case R.string.dipilot_interactive_lane_title /* 2131755364 */:
                this.f1201c.s(z ? 2 : 1);
                break;
            case R.string.dipilot_iop_title /* 2131755368 */:
                this.f1201c.t(z ? 2 : 1);
                break;
            case R.string.dipilot_smart_navigation_title /* 2131755423 */:
                this.f1201c.n(z ? 2 : 1);
                break;
            case R.string.dipilot_urban_navigation_title /* 2131755478 */:
                this.f1201c.u(z ? 2 : 1);
                break;
        }
        driverAssistModel.setAssistState(z);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @SuppressLint({"NonConstantResourceId"})
    public final int[] f(DriverAssistModel driverAssistModel, boolean z) {
        int[] iArr = new int[z ? 1 : 2];
        switch (driverAssistModel.getTag()) {
            case R.string.dipilot_acc_title /* 2131755264 */:
                iArr[0] = R.string.dipilot_acc_tip;
                if (!z) {
                    iArr[1] = R.string.dipilot_acc_notice;
                }
                return iArr;
            case R.string.dipilot_end_to_end_navigation_title /* 2131755344 */:
                iArr[0] = z ? R.string.dipilot_switch_end_to_end_navigation_content : R.string.dipilot_tips_end_to_end_navigation_content;
                if (!z) {
                    iArr[1] = R.string.dipilot_tips_end_to_end_navigation_notice;
                }
                return iArr;
            case R.string.dipilot_highway_navigation_title /* 2131755353 */:
                iArr[0] = z ? R.string.dipilot_switch_high_speed_intelligent_assistance_content : R.string.dipilot_highway_navigation_hint_message;
                if (!z) {
                    iArr[1] = R.string.dipilot_highway_navigation_note_message;
                }
                return iArr;
            case R.string.dipilot_interactive_lane_title /* 2131755364 */:
                iArr[0] = z ? R.string.dipilot_interactive_check_dialog : R.string.dipilot_interactive_lane_hint_message;
                if (!z) {
                    iArr[1] = R.string.dipilot_interactive_lane_note_message;
                }
                return iArr;
            case R.string.dipilot_iop_title /* 2131755368 */:
                iArr[0] = z ? R.string.dipilot_iop_confirm : R.string.dipilot_iop_tip;
                if (!z) {
                    iArr[1] = R.string.dipilot_iop_notice;
                }
                return iArr;
            case R.string.dipilot_smart_navigation_title /* 2131755423 */:
                iArr[0] = z ? R.string.dipilot_switch_contents_intelligent_navigation : R.string.dipilot_smart_navigation_hint_message;
                if (!z) {
                    iArr[1] = R.string.dipilot_smart_navigation_note_message;
                }
                return iArr;
            case R.string.dipilot_urban_navigation_title /* 2131755478 */:
                iArr[0] = z ? R.string.dipilot_switch_urban_navigation_assistant_content : R.string.dipilot_tips_urban_navigation_assistant_content;
                if (!z) {
                    iArr[1] = R.string.dipilot_tips_urban_navigation_assistant_notice;
                }
                return iArr;
            default:
                return iArr;
        }
    }

    public boolean g(int i) {
        return i > 0 && i < 6;
    }

    public void h(@NonNull BaseAdasViewModel<DriverAssistModel, b.b.k.b.a> baseAdasViewModel) {
        this.f1202d = baseAdasViewModel;
        IStudyConfig iStudyConfigF = Router.b().f();
        if (!ListUtils.isEmpty(this.f1202d.d())) {
            BydLogHelper.logD(this.f1952a, " viewMode has init data unnecessary to");
            return;
        }
        baseAdasViewModel.m(new ArrayList());
        if (iStudyConfigF.c(64)) {
            byte bJ = (byte) this.f1201c.j();
            baseAdasViewModel.a(new DriverAssistModel(1 == bJ, i(bJ, 64), R.string.dipilot_highway_navigation_title, 0, bJ));
        }
        if (iStudyConfigF.c(65)) {
            byte bG = this.f1201c.g();
            baseAdasViewModel.a(new DriverAssistModel(1 == bG, i(bG, 65), R.string.dipilot_urban_navigation_title, 0, bG));
        }
        if (iStudyConfigF.c(66)) {
            byte bI = (byte) this.f1201c.i();
            baseAdasViewModel.a(new DriverAssistModel(1 == bI, i(bI, 66), R.string.dipilot_end_to_end_navigation_title, 0, bI));
        }
        if (iStudyConfigF.c(68)) {
            byte bC = (byte) this.f1201c.c();
            baseAdasViewModel.a(new DriverAssistModel(this.f1201c.e(bC), i(bC, 68), R.string.dipilot_smart_navigation_title, 0, bC));
        }
        if (iStudyConfigF.c(69)) {
            byte bM = (byte) this.f1201c.m();
            baseAdasViewModel.a(new DriverAssistModel(2 == bM, true, R.string.dipilot_iop_title, 0, bM));
        }
        if (iStudyConfigF.c(67)) {
            byte bL = (byte) this.f1201c.l();
            baseAdasViewModel.a(new DriverAssistModel(g(bL), i(bL, 67), R.string.dipilot_interactive_lane_title, 0, bL));
        }
        if (iStudyConfigF.c(70)) {
            byte bH = (byte) this.f1201c.h();
            baseAdasViewModel.a(new DriverAssistModel(2 == bH, true, R.string.dipilot_acc_title, 0, bH));
        }
        ((b.b.g.n.l.f.n.a) getV()).g(baseAdasViewModel.d());
        BydLogHelper.logI(this.f1952a, " initData size =   " + baseAdasViewModel.d().size());
        if (this.f1953b) {
            return;
        }
        EventBus.getDefault().register(this);
        this.f1953b = true;
    }

    public final boolean i(int i, int i2) {
        return i2 == 68 ? i != 4 : i2 == 67 ? i != 6 : i != 2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.b.a.c.a aVar) {
        BaseAdasViewModel<DriverAssistModel, b.b.k.b.a> baseAdasViewModel;
        b.b.g.n.l.d dVar;
        b.b.g.n.l.d dVar2;
        b.b.g.n.l.d dVar3;
        b.b.g.n.l.d dVar4;
        b.b.g.n.l.f.n.a aVar2 = (b.b.g.n.l.f.n.a) getV();
        if (aVar2 == null || aVar2.i() || this.f1201c == null || (baseAdasViewModel = this.f1202d) == null || ListUtils.isEmpty(baseAdasViewModel.d())) {
            return;
        }
        int iC = aVar.c();
        boolean z = iC == 0;
        switch (aVar.h()) {
            case 210009:
                BydLogHelper.logD(this.f1952a, "HighSpeed 高快领航 = " + iC);
                DriverAssistModel driverAssistModelC = this.f1202d.c(R.string.dipilot_highway_navigation_title);
                if (driverAssistModelC != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        iC = this.f1201c.j();
                    }
                    driverAssistModelC.setBtnEnable(iC == 1, i(iC, 64));
                    driverAssistModelC.pos = (byte) iC;
                    break;
                }
                break;
            case 210010:
                BydLogHelper.logD(this.f1952a, "Urban 城市领航 = " + iC);
                DriverAssistModel driverAssistModelC2 = this.f1202d.c(R.string.dipilot_urban_navigation_title);
                if (driverAssistModelC2 != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        iC = this.f1201c.g();
                    }
                    driverAssistModelC2.setBtnEnable(iC == 1, i(iC, 65));
                    driverAssistModelC2.pos = (byte) iC;
                    break;
                }
                break;
            case 210011:
                BydLogHelper.logD(this.f1952a, "E2E_AI E2E智驾领航 = " + iC);
                DriverAssistModel driverAssistModelC3 = this.f1202d.c(R.string.dipilot_end_to_end_navigation_title);
                if (driverAssistModelC3 != null) {
                    if (b.b.g.r.e.e(aVar)) {
                        iC = this.f1201c.i();
                    }
                    driverAssistModelC3.setBtnEnable(iC == 1, i(iC, 66));
                    driverAssistModelC3.pos = (byte) iC;
                    break;
                }
                break;
            case 210012:
                BydLogHelper.logD(this.f1952a, "Interactive Lane Change Assist 拨杆变道状态变化 = " + iC);
                DriverAssistModel driverAssistModelC4 = this.f1202d.c(R.string.dipilot_interactive_lane_title);
                if (driverAssistModelC4 != null) {
                    if (b.b.g.r.e.e(aVar) && (dVar = this.f1201c) != null) {
                        iC = dVar.l();
                    }
                    driverAssistModelC4.setBtnEnable(g(iC), i(iC, 67));
                    driverAssistModelC4.pos = (byte) iC;
                    break;
                }
                break;
            case 210013:
                BydLogHelper.logD(this.f1952a, "AINavigation 车道领航 = " + iC);
                DriverAssistModel driverAssistModelC5 = this.f1202d.c(R.string.dipilot_smart_navigation_title);
                if (driverAssistModelC5 != null && iC <= 4) {
                    if (b.b.g.r.e.e(aVar) && (dVar2 = this.f1201c) != null) {
                        iC = dVar2.c();
                    }
                    driverAssistModelC5.setBtnEnable(this.f1201c.e(iC), i(iC, 68));
                    driverAssistModelC5.pos = (byte) iC;
                    break;
                }
                break;
            case 210090:
                DriverAssistModel driverAssistModelC6 = this.f1202d.c(R.string.dipilot_urban_navigation_title);
                if (driverAssistModelC6 != null) {
                    driverAssistModelC6.setNormal(z);
                    break;
                }
                break;
            case 210091:
                DriverAssistModel driverAssistModelC7 = this.f1202d.c(R.string.dipilot_highway_navigation_title);
                if (driverAssistModelC7 != null) {
                    driverAssistModelC7.setNormal(z);
                    break;
                }
                break;
            case 210103:
                DriverAssistModel driverAssistModelC8 = this.f1202d.c(R.string.dipilot_interactive_lane_title);
                if (driverAssistModelC8 != null) {
                    driverAssistModelC8.setNormal(z);
                    break;
                }
                break;
            case 210108:
                DriverAssistModel driverAssistModelC9 = this.f1202d.c(R.string.dipilot_smart_navigation_title);
                if (driverAssistModelC9 != null) {
                    driverAssistModelC9.setNormal(z);
                    break;
                }
                break;
            case 210110:
                BydLogHelper.logD(this.f1952a, "iop change, state =  " + iC);
                DriverAssistModel driverAssistModelC10 = this.f1202d.c(R.string.dipilot_iop_title);
                if (driverAssistModelC10 != null) {
                    if (b.b.g.r.e.e(aVar) && (dVar3 = this.f1201c) != null) {
                        iC = dVar3.m();
                    }
                    driverAssistModelC10.setBtnEnable(2 == iC, true);
                } else {
                    BydLogHelper.logW(this.f1952a, "iop null!");
                }
                break;
            case 210113:
                BydLogHelper.logD(this.f1952a, "acc change, state =  " + iC);
                DriverAssistModel driverAssistModelC11 = this.f1202d.c(R.string.dipilot_acc_title);
                if (driverAssistModelC11 != null) {
                    if (b.b.g.r.e.e(aVar) && (dVar4 = this.f1201c) != null) {
                        iC = dVar4.h();
                    }
                    driverAssistModelC11.setBtnEnable(2 == iC, true);
                } else {
                    BydLogHelper.logW(this.f1952a, "acc null!");
                }
                break;
        }
    }

    @Override // b.b.k.d.a.a
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public void p(DriverAssistModel driverAssistModel, int i) {
        b.b.g.n.l.f.n.a aVar = (b.b.g.n.l.f.n.a) getV();
        if (aVar == null || aVar.i()) {
            return;
        }
        int[] iArrF = f(driverAssistModel, false);
        aVar.j(driverAssistModel, iArrF[0], iArrF.length > 1 ? iArrF[1] : 0, i, null);
    }

    @Override // b.b.k.d.a.a
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public void b(final LottieAnimationView lottieAnimationView, DriverAssistModel driverAssistModel, boolean z, final int i) {
        DriverAssistModel driverAssistModelC;
        b.b.g.n.l.d dVar;
        b.b.g.n.l.f.n.a aVar = (b.b.g.n.l.f.n.a) getV();
        if (aVar == null || aVar.i()) {
            return;
        }
        BydLogHelper.logD(this.f1952a, "onSwitchChange =" + driverAssistModel.getAssistState() + ",switch:" + z);
        if (driverAssistModel.getTag() == R.string.dipilot_acc_title) {
            this.f1201c.o(z ? 2 : 1);
            if (!z || lottieAnimationView == null) {
                return;
            }
            lottieAnimationView.playAnimation();
            return;
        }
        if (z) {
            int[] iArrF = f(driverAssistModel, true);
            if (driverAssistModel.getTag() == R.string.dipilot_interactive_lane_title) {
                aVar.o(driverAssistModel, iArrF[0], iArrF.length > 1 ? iArrF[1] : 0, i, 4, new b.b.k.a.a() { // from class: b.b.g.n.l.f.j
                    @Override // b.b.k.a.a
                    public final void f(boolean z2) {
                        this.f1192a.l(lottieAnimationView, i, z2);
                    }
                });
                return;
            } else {
                aVar.j(driverAssistModel, iArrF[0], iArrF.length > 1 ? iArrF[1] : 0, i, new b.b.k.a.a() { // from class: b.b.g.n.l.f.i
                    @Override // b.b.k.a.a
                    public final void f(boolean z2) {
                        this.f1189a.n(lottieAnimationView, i, z2);
                    }
                });
                return;
            }
        }
        if (driverAssistModel.getTag() != R.string.dipilot_iop_title && (dVar = this.f1201c) != null && dVar.f() != 1) {
            z.b(((b.b.g.n.l.f.n.a) getV()).getContext(), R.string.dipilot_disable_toast);
            driverAssistModel.setAssistState(driverAssistModel.getAssistState());
        } else if (driverAssistModel.getTag() == R.string.dipilot_interactive_lane_title && (driverAssistModelC = this.f1202d.c(R.string.dipilot_smart_navigation_title)) != null && driverAssistModelC.assistState) {
            aVar.j(driverAssistModel, R.string.dipilot_intelligent_navigation_toast, 0, i, new b.b.k.a.a() { // from class: b.b.g.n.l.f.k
                @Override // b.b.k.a.a
                public final void f(boolean z2) {
                    this.f1195a.q(i, z2);
                }
            });
        } else {
            e(driverAssistModel, false, i);
        }
    }
}
