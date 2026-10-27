package com.byd.carsettings.ui.fragment.dipilot;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.f.h;
import b.b.a.h.f;
import b.b.g.n.l.f.l;
import b.b.k.a.b.a;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dipilot.BaseAssistAdapter;
import com.byd.carsettings.common.GridSpaceItemDecoration;
import com.byd.carsettings.databinding.FragmentActiveSafeBinding;
import com.byd.carsettings.ui.widget.NoScrollGridLayoutManager;
import com.byd.dipilot.BaseFragment;
import com.byd.dipilot.model.ActiveSafeModel;
import com.byd.dipilot.viewmodel.ActiveSafeViewModel;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveSafeFragment extends BaseFragment<FragmentActiveSafeBinding, l> implements a<l, ActiveSafeModel>, View.OnClickListener {
    public BaseAssistAdapter<ActiveSafeModel> i;
    public long[] j = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void A() {
        this.g = true;
        t();
    }

    public final int B(int i) {
        Log.d(this.f3613a, "mapListItemIndex adasType = " + i);
        List<ActiveSafeModel> listD = ((FragmentActiveSafeBinding) this.f3615c).b().d();
        if (listD == null) {
            return 0;
        }
        for (int i2 = 0; i2 < listD.size(); i2++) {
            if (((FragmentActiveSafeBinding) this.f3615c).b().e(listD.get(i2).getTag()) == i) {
                return i2;
            }
        }
        return 0;
    }

    public final View C(int i) {
        Log.d(this.f3613a, "mapNormalItemIndex adasType = " + i);
        switch (i) {
            case 9:
                return ((FragmentActiveSafeBinding) this.f3615c).H;
            case 10:
                return ((FragmentActiveSafeBinding) this.f3615c).I;
            case 11:
            case 12:
                return ((FragmentActiveSafeBinding) this.f3615c).f2807f;
            case 13:
                return ((FragmentActiveSafeBinding) this.f3615c).F;
            case 14:
                return ((FragmentActiveSafeBinding) this.f3615c).O;
            case 15:
                return ((FragmentActiveSafeBinding) this.f3615c).i;
            case 16:
                return ((FragmentActiveSafeBinding) this.f3615c).K;
            case 17:
                return ((FragmentActiveSafeBinding) this.f3615c).J;
            case 18:
                return ((FragmentActiveSafeBinding) this.f3615c).Q;
            case 19:
                return ((FragmentActiveSafeBinding) this.f3615c).P;
            case 20:
                return ((FragmentActiveSafeBinding) this.f3615c).S;
            case 21:
                return ((FragmentActiveSafeBinding) this.f3615c).T;
            case 22:
                return ((FragmentActiveSafeBinding) this.f3615c).L;
            case 23:
                return ((FragmentActiveSafeBinding) this.f3615c).R;
            case 24:
                return ((FragmentActiveSafeBinding) this.f3615c).M;
            case 25:
                return ((FragmentActiveSafeBinding) this.f3615c).G;
            case 26:
                return ((FragmentActiveSafeBinding) this.f3615c).f2803b;
            case 27:
                return ((FragmentActiveSafeBinding) this.f3615c).h;
            case 28:
                return ((FragmentActiveSafeBinding) this.f3615c).U;
            case 29:
            case 31:
            default:
                return null;
            case 30:
                return ((FragmentActiveSafeBinding) this.f3615c).f2805d;
            case 32:
                return ((FragmentActiveSafeBinding) this.f3615c).y0;
            case 33:
                return ((FragmentActiveSafeBinding) this.f3615c).b0;
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public l newP() {
        ActiveSafeViewModel activeSafeViewModelB = ((FragmentActiveSafeBinding) this.f3615c).b();
        if (activeSafeViewModelB != null && (activeSafeViewModelB.h() instanceof l)) {
            return (l) activeSafeViewModelB.h();
        }
        l lVar = new l();
        activeSafeViewModelB.n(lVar);
        return lVar;
    }

    public final void E() {
        long[] jArr;
        if (this.f3616d == 0 || (jArr = this.j) == null) {
            return;
        }
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.j;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.j[0] > SystemClock.uptimeMillis() - 3000) {
            ((l) this.f3616d).S();
            this.j = null;
            this.j = new long[10];
        }
    }

    @Override // b.b.k.a.b.a
    public void g(List<ActiveSafeModel> list) {
        BaseAssistAdapter<ActiveSafeModel> baseAssistAdapter = this.i;
        if (baseAssistAdapter != null) {
            baseAssistAdapter.s(list);
        }
    }

    @Override // com.byd.dipilot.BaseFragment, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.fragment_active_safe;
    }

    @Override // b.b.k.a.b.a
    public boolean i() {
        FragmentActivity activity = getActivity();
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0097  */
    @Override // b.b.k.a.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j(com.byd.dipilot.model.DriverAssistModel r18, int r19, int r20, int r21, b.b.k.a.a r22) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.ui.fragment.dipilot.ActiveSafeFragment.j(com.byd.dipilot.model.DriverAssistModel, int, int, int, b.b.k.a.a):void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.tv_lane_title == view.getId() || !f.b(view, 500L)) {
            switch (view.getId()) {
                case R.id.iv_auto_manual_control_speed_notice /* 2131297182 */:
                    j(null, 0, R.string.dipilot_traffic_sign_tip, 0, null);
                    break;
                case R.id.iv_auto_parke_notice /* 2131297183 */:
                    j(null, R.string.dipilot_title_auto_parking, R.string.dipilot_auto_parking_hint_message, 0, null);
                    break;
                case R.id.iv_bsis_collision_pre_warm_notice /* 2131297208 */:
                    j(null, R.string.adas_blind_spot_notice_title, R.string.dipilot_bsis_close_hint_message, R.string.dipilot_bsis_close_note_message, null);
                    break;
                case R.id.iv_front_lateral_approach_braking_notice /* 2131297262 */:
                    j(null, R.string.dipilot_title_forward_traffic_crossing_brake, R.string.dipilot_forward_traffic_crossing_breaking_message, R.string.dipilot_ahead_traffic_crossing_breaking_attention_message, null);
                    break;
                case R.id.iv_front_lateral_approach_warning_notice /* 2131297263 */:
                    j(null, R.string.dipilot_title_ahead_traffic_crossing_warning, R.string.dipilot_ahead_traffic_crossing_warning_message, R.string.dipilot_ahead_traffic_crossing_warning_attention_message, null);
                    break;
                case R.id.iv_gas_pedal_notice /* 2131297266 */:
                    j(null, R.string.gas_pedal_restrain_system, R.string.gas_pedal_restrain_notice, 0, null);
                    break;
                case R.id.iv_intelligent_speed_control_alarm_notice /* 2131297272 */:
                    j(null, R.string.dipilot_intelligent_speed_limit_alert, R.string.dipilot_limit_alert_hint_message, 0, null);
                    break;
                case R.id.iv_intelligent_speed_control_notice /* 2131297273 */:
                    j(null, R.string.dipilot_limit_control_title, R.string.dipilot_limit_control_hint_message, R.string.dipilot_limit_control_attenttion_message, null);
                    break;
                case R.id.iv_lane_assist_notice /* 2131297280 */:
                    j(null, R.string.dipilot_lane_assist_title, R.string.dipilot_lane_assist_hint_message, R.string.dipilot_lane_assist_attenttion_message, null);
                    break;
                case R.id.iv_ldw_sensitive_notice /* 2131297281 */:
                    j(null, R.string.adas_ldw_sensitive_notice_title, R.string.adas_tsr_sensitive_notice, 0, null);
                    break;
                case R.id.iv_mois_drive_area_system_notice /* 2131297306 */:
                    j(null, R.string.dipilot_mois_area_info_system, R.string.dipilot_mois_tips, R.string.dipilot_mois_tips_notice, null);
                    break;
                case R.id.iv_predict_crash_alert_notice /* 2131297330 */:
                    j(null, R.string.dipilot_new_urgent_warning_message_title, R.string.dipilot_new_urgent_warning_hint_message, R.string.dipilot_new_urgent_warning_hint_message_notice, null);
                    break;
                case R.id.iv_rear_crash_alert_notice /* 2131297332 */:
                    j(null, R.string.dipilot_title_rear_collision_warning, R.string.dipilot_rear_collision_warning_hint_message, R.string.dipilot_rear_collision_warning_attention_message, null);
                    break;
                case R.id.iv_rear_lateral_approach_braking_notice /* 2131297333 */:
                    j(null, R.string.dipilot_title_rear_traffic_crossing_brake, R.string.dipilot_rear_traffic_crossing_brake_hint_message, R.string.dipilot_rear_traffic_crossing_brake_attenttion_message, null);
                    break;
                case R.id.iv_rear_lateral_approach_warning /* 2131297334 */:
                    j(null, R.string.dipilot_title_rear_traffic_crossing_alert, R.string.dipilot_rear_traffic_crossing_alert_hint_message, R.string.dipilot_rear_traffic_crossing_alert_attention_message, null);
                    break;
                case R.id.iv_title_ai_emergency_brake_notice /* 2131297361 */:
                    j(null, R.string.dipilot_new_ergency_braking_message_title, R.string.dipilot_new_ergency_braking_hint_message, R.string.adas_emergency_braking_notice, null);
                    break;
                case R.id.iv_title_traffic_signs_recognition_notice /* 2131297363 */:
                    j(null, R.string.dipilot_traffic_sign_title, R.string.dipilot_traffic_sign_hint_message, R.string.dipilot_traffic_sign_attenttion_message, null);
                    break;
                case R.id.saoc_notice /* 2131298020 */:
                    j(null, R.string.dipilot_saoc, R.string.dipilot_saoc_tip, R.string.dipilot_saoc_notice, null);
                    break;
                case R.id.slif_notice /* 2131298130 */:
                    j(null, R.string.dipilot_slif, R.string.dipilot_slif_tip, R.string.dipilot_slif_notice, null);
                    break;
                case R.id.tv_lane_title /* 2131298534 */:
                    E();
                    break;
            }
        }
    }

    @Override // com.byd.dipilot.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        BydLogHelper.logD(this.f3613a, " onDestroyView is run");
        x();
        ((FragmentActiveSafeBinding) this.f3615c).Z.removeAllViews();
        BaseAssistAdapter<ActiveSafeModel> baseAssistAdapter = this.i;
        if (baseAssistAdapter != null) {
            baseAssistAdapter.a();
        }
        this.i = null;
        this.j = null;
        super.onDestroyView();
    }

    @Override // com.byd.dipilot.BaseFragment
    @RequiresApi(api = 29)
    public void t() {
        View childAt;
        int iF = b.b.m.e.i.a.f(getClass().getName());
        b.b.m.e.i.a.n(getClass().getName());
        b.b.m.c.a.a(this.f3613a, "checkItemIndex ====== " + iF);
        if (iF < 0) {
            return;
        }
        int[] iArr = new int[2];
        switch (iF) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                childAt = ((FragmentActiveSafeBinding) this.f3615c).a0.getChildAt(B(iF));
                break;
            default:
                childAt = C(iF);
                break;
        }
        if (childAt != null) {
            childAt.getLocationOnScreen(iArr);
            ((FragmentActiveSafeBinding) this.f3615c).Z.scrollBy(0, iArr[1] - 200);
            b.b.m.c.a.a(this.f3613a, "checkItemIndex location y = " + iArr[1]);
        }
    }

    @Override // com.byd.dipilot.BaseFragment
    public void v(Context context) {
        ((l) this.f3616d).s(((FragmentActiveSafeBinding) this.f3615c).b());
        h.a().d(new Runnable() { // from class: b.b.g.q.e.b.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f1510a.A();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.dipilot.BaseFragment
    public void w(Context context) {
        ActiveSafeViewModel activeSafeViewModel = (ActiveSafeViewModel) new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(ActiveSafeViewModel.class);
        ((FragmentActiveSafeBinding) this.f3615c).c(activeSafeViewModel);
        ((FragmentActiveSafeBinding) this.f3615c).setLifecycleOwner(this);
        l lVar = (l) mo7getP();
        RecyclerView recyclerView = ((FragmentActiveSafeBinding) this.f3615c).a0;
        recyclerView.setLayoutManager(new NoScrollGridLayoutManager(context, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        BaseAssistAdapter<ActiveSafeModel> baseAssistAdapter = new BaseAssistAdapter<>(context, activeSafeViewModel.d(), this);
        this.i = baseAssistAdapter;
        recyclerView.setAdapter(baseAssistAdapter);
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_16);
        ((FragmentActiveSafeBinding) this.f3615c).a0.addItemDecoration(new GridSpaceItemDecoration(2, dimension, dimension));
        this.i.t(lVar);
        y();
    }

    public final void x() {
        ((FragmentActiveSafeBinding) this.f3615c).setOnClickListener(null);
        ((FragmentActiveSafeBinding) this.f3615c).setOnCheckedChangeListener(null);
        ((FragmentActiveSafeBinding) this.f3615c).setOnItemCheck(null);
    }

    public final void y() {
        ((FragmentActiveSafeBinding) this.f3615c).setOnClickListener(this);
        ((FragmentActiveSafeBinding) this.f3615c).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this.f3616d);
        ((FragmentActiveSafeBinding) this.f3615c).setOnItemCheck((SegmentedButtonGroup.d) this.f3616d);
    }
}
