package com.byd.carsettings.ui.fragment.dipilot;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.b.a.c.a;
import b.b.g.n.l.c;
import b.b.m.f.d.b;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.view.seekbar.VerticalSeekBar;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import com.google.android.material.badge.BadgeDrawable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DiTrainerFragment extends XFragment<c> implements CompoundButton.OnCheckedChangeListener, SegmentedButtonGroup.d, View.OnClickListener {
    public BydSwitch A;
    public BydSwitch B;
    public BydSwitch D;
    public BydSwitch E;
    public SegmentedButtonGroup F;
    public SegmentedButtonGroup G;
    public ImageView H;
    public Button I;
    public PopupWindow J;
    public ImageView K;
    public ImageView L;
    public ImageView M;
    public DampingScrollView N;
    public int[] O = {R.id.tv_ditrainer, R.id.cl_new_intelligent_escort_function, R.id.cl_ditrainer_dialog_driving_level, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open, R.id.cl_new_intelligent_escort_function_open};
    public Activity m;
    public ImageView n;
    public BydSwitch o;
    public ImageView p;
    public BydSwitch q;
    public ImageView r;
    public RelativeLayout s;
    public ImageView t;
    public ImageView u;
    public TextView v;
    public ConstraintLayout w;
    public BydSwitch x;
    public BydSwitch y;
    public BydSwitch z;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Q(b bVar, int i, View view) {
        bVar.c();
        if (i == 9) {
            this.q.setChecked(true, false);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = (Activity) context;
        this.n = (ImageView) this.f3717c.findViewById(R.id.iv_ditrainer_notice);
        this.N = (DampingScrollView) this.f3717c.findViewById(R.id.nested_scroll_view);
        this.o = (BydSwitch) this.f3717c.findViewById(R.id.switch_new_intelligent_escort_function);
        this.p = (ImageView) this.f3717c.findViewById(R.id.iv_new_intelligent_escort_function_notice);
        this.K = (ImageView) this.f3717c.findViewById(R.id.iv_ditrainer_cities_with_driving_tips);
        this.L = (ImageView) this.f3717c.findViewById(R.id.iv_ditrainer_high_speed_escort_tips);
        this.M = (ImageView) this.f3717c.findViewById(R.id.iv_ditrainer_car_care_tips);
        this.q = (BydSwitch) this.f3717c.findViewById(R.id.switch_ditrainer_dialog_driving_level);
        this.r = (ImageView) this.f3717c.findViewById(R.id.iv_ditrainer_dialog_driving_level_notice);
        this.s = (RelativeLayout) this.f3717c.findViewById(R.id.rl_driving_level);
        this.t = (ImageView) this.f3717c.findViewById(R.id.iv_driving_level);
        this.v = (TextView) this.f3717c.findViewById(R.id.tv_driving_level);
        this.u = (ImageView) this.f3717c.findViewById(R.id.iv_driving_level_more);
        this.w = (ConstraintLayout) this.f3717c.findViewById(R.id.cl_new_intelligent_escort_function_open);
        this.x = (BydSwitch) this.f3717c.findViewById(R.id.switch_safety_departure_warning);
        this.y = (BydSwitch) this.f3717c.findViewById(R.id.switch_car_ahead_start_warning);
        this.z = (BydSwitch) this.f3717c.findViewById(R.id.switch_fault_hand_warning);
        this.A = (BydSwitch) this.f3717c.findViewById(R.id.switch_turn_signal_assist_warning);
        this.B = (BydSwitch) this.f3717c.findViewById(R.id.switch_charge_cover_warning);
        this.D = (BydSwitch) this.f3717c.findViewById(R.id.switch_rain_fog_lamps_warning);
        this.E = (BydSwitch) this.f3717c.findViewById(R.id.switch_ditrainer_high_speed_window);
        this.F = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_notice_mode);
        this.G = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.sbg_custom_function_combinations);
        this.H = (ImageView) this.f3717c.findViewById(R.id.iv_custom_function_combinations_tips);
        this.I = (Button) this.f3717c.findViewById(R.id.btn_reset);
        L();
    }

    public final void K() {
        this.n.setOnClickListener(null);
        this.p.setOnClickListener(null);
        this.r.setOnClickListener(null);
        this.s.setOnClickListener(null);
        this.K.setOnClickListener(null);
        this.L.setOnClickListener(null);
        this.M.setOnClickListener(null);
        this.H.setOnClickListener(null);
        this.I.setOnClickListener(null);
        this.o.setOnCheckedChangeListener(null);
        this.q.setOnCheckedChangeListener(null);
        this.x.setOnCheckedChangeListener(null);
        this.y.setOnCheckedChangeListener(null);
        this.z.setOnCheckedChangeListener(null);
        this.A.setOnCheckedChangeListener(null);
        this.B.setOnCheckedChangeListener(null);
        this.D.setOnCheckedChangeListener(null);
        this.E.setOnCheckedChangeListener(null);
        this.F.setOnClickedButtonListener(null);
        this.G.setOnClickedButtonListener(null);
    }

    public final void L() {
        this.n.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.o.setOnCheckedChangeListener(this);
        this.q.setOnCheckedChangeListener(this);
        this.x.setOnCheckedChangeListener(this);
        this.y.setOnCheckedChangeListener(this);
        this.z.setOnCheckedChangeListener(this);
        this.A.setOnCheckedChangeListener(this);
        this.B.setOnCheckedChangeListener(this);
        this.D.setOnCheckedChangeListener(this);
        this.E.setOnCheckedChangeListener(this);
        this.F.setOnClickedButtonListener(this);
        this.G.setOnClickedButtonListener(this);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: T, reason: merged with bridge method [inline-methods] */
    public c newP() {
        return new c();
    }

    public final void U() {
        View viewInflate = LayoutInflater.from(this.m).inflate(R.layout.dialog_car_care, (ViewGroup) null, false);
        Button button = (Button) viewInflate.findViewById(R.id.btn_positive);
        int dimension = (int) this.m.getResources().getDimension(R.dimen.dp_612);
        int dimension2 = (int) this.m.getResources().getDimension(R.dimen.dp_560);
        final b bVar = new b();
        button.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.q.e.b.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bVar.c();
            }
        });
        bVar.C(this.m, viewInflate, dimension, dimension2, true);
    }

    public final void V() {
        View viewInflate = LayoutInflater.from(this.m).inflate(R.layout.dialog_cities_with_driving, (ViewGroup) null, false);
        Button button = (Button) viewInflate.findViewById(R.id.btn_positive);
        int dimension = (int) this.m.getResources().getDimension(R.dimen.dp_612);
        int dimension2 = (int) this.m.getResources().getDimension(R.dimen.dp_560);
        final b bVar = new b();
        button.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.q.e.b.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bVar.c();
            }
        });
        bVar.C(this.m, viewInflate, dimension, dimension2, true);
    }

    public final void W(String str, String str2, String str3, final int i) {
        View viewInflate = LayoutInflater.from(this.m).inflate(R.layout.dialog_advance_driver_assist_scrollview, (ViewGroup) null, false);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_content);
        viewInflate.findViewById(R.id.divider);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_notice);
        Button button = (Button) viewInflate.findViewById(R.id.btn_positive);
        Button button2 = (Button) viewInflate.findViewById(R.id.btn_reset);
        textView.setText(str);
        textView2.setText(str2);
        int dimension = (int) this.m.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) this.m.getResources().getDimension(R.dimen.dp_544);
        textView3.setVisibility(8);
        button2.setVisibility(8);
        final b bVar = new b();
        if (i == 9) {
            button2.setVisibility(0);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.q.e.b.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                b.b.m.f.d.b bVar2 = bVar;
                int i2 = i;
                bVar2.c();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.q.e.b.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f1515a.Q(bVar, i, view);
            }
        });
        bVar.C(this.m, viewInflate, dimension, dimension2, true);
    }

    public final void X() {
        View viewInflate = LayoutInflater.from(this.m).inflate(R.layout.dialog_high_speed_escort, (ViewGroup) null, false);
        Button button = (Button) viewInflate.findViewById(R.id.btn_positive);
        int dimension = (int) this.m.getResources().getDimension(R.dimen.dp_612);
        int dimension2 = (int) this.m.getResources().getDimension(R.dimen.dp_560);
        final b bVar = new b();
        button.setOnClickListener(new View.OnClickListener() { // from class: b.b.g.q.e.b.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                bVar.c();
            }
        });
        bVar.C(this.m, viewInflate, dimension, dimension2, true);
    }

    public final void Y() {
        View viewInflate = LayoutInflater.from(this.m).inflate(R.layout.popup_driving_level, (ViewGroup) null);
        ((VerticalSeekBar) viewInflate.findViewById(R.id.vs_driving_level)).setSeekBarProgressChangeListener(new VerticalSeekBar.a() { // from class: b.b.g.q.e.b.d
            @Override // com.byd.carsettings.ui.view.seekbar.VerticalSeekBar.a
            public final void a(int i) {
                BydLogHelper.logD("curProgress:" + i);
            }
        });
        int dimension = (int) this.m.getResources().getDimension(R.dimen.dp_240);
        int dimension2 = (int) this.m.getResources().getDimension(R.dimen.dp_300);
        int dimension3 = (int) this.m.getResources().getDimension(R.dimen.dp_8);
        PopupWindow popupWindow = new PopupWindow(viewInflate, dimension, dimension2);
        this.J = popupWindow;
        popupWindow.setFocusable(true);
        this.J.setBackgroundDrawable(new BitmapDrawable());
        this.J.showAsDropDown(this.s, 0, dimension3, BadgeDrawable.TOP_START);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_ditrainer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.d
    public void k(View view, View view2, int i) {
        if (view.getId() != R.id.sbg_custom_function_combinations) {
            return;
        }
        ((c) mo7getP()).h(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.switch_car_ahead_start_warning /* 2131298224 */:
                ((c) mo7getP()).o(z ? 1 : 0);
                break;
            case R.id.switch_charge_cover_warning /* 2131298226 */:
                ((c) mo7getP()).l(z ? 1 : 0);
                break;
            case R.id.switch_ditrainer_dialog_driving_level /* 2131298234 */:
                if (!z && isResumed()) {
                    W(this.m.getString(R.string.dipilot_title_dialog_hint), this.m.getString(R.string.dipilot_ditrainer_switch_close_dialog_driving_level_tips), null, 9);
                    break;
                }
                break;
            case R.id.switch_ditrainer_high_speed_window /* 2131298235 */:
                ((c) mo7getP()).q(z ? 1 : 0);
                break;
            case R.id.switch_fault_hand_warning /* 2131298241 */:
                ((c) mo7getP()).n(z ? 1 : 0);
                break;
            case R.id.switch_new_intelligent_escort_function /* 2131298264 */:
                if (!z) {
                    this.w.setVisibility(8);
                } else {
                    this.w.setVisibility(0);
                }
                break;
            case R.id.switch_rain_fog_lamps_warning /* 2131298268 */:
                ((c) mo7getP()).r(z ? 1 : 0);
                break;
            case R.id.switch_safety_departure_warning /* 2131298274 */:
                ((c) mo7getP()).m(z ? 1 : 0);
                break;
            case R.id.switch_turn_signal_assist_warning /* 2131298286 */:
                ((c) mo7getP()).j(z ? 1 : 0);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset /* 2131296537 */:
                ((c) mo7getP()).i(0);
                break;
            case R.id.iv_custom_function_combinations_tips /* 2131297229 */:
                W(this.m.getString(R.string.dipilot_ditrainer_dialog_custom_function_combinations_explain), this.m.getString(R.string.dipilot_ditrainer_dialog_custom_function_combinations_tips), null, 7);
                break;
            case R.id.iv_ditrainer_car_care_tips /* 2131297245 */:
                U();
                break;
            case R.id.iv_ditrainer_cities_with_driving_tips /* 2131297247 */:
                V();
                break;
            case R.id.iv_ditrainer_dialog_driving_level_notice /* 2131297248 */:
                W(this.m.getString(R.string.dipilot_ditrainer_switch_open_dialog_driving_level), this.m.getString(R.string.dipilot_ditrainer_switch_open_dialog_driving_level_tips), null, 2);
                break;
            case R.id.iv_ditrainer_high_speed_escort_tips /* 2131297250 */:
                X();
                break;
            case R.id.iv_ditrainer_notice /* 2131297251 */:
                W(this.m.getString(R.string.dipilot_ditrainer_mode_func_explain), this.m.getString(R.string.dipilot_ditrainer_dialog_tips), null, 0);
                break;
            case R.id.iv_new_intelligent_escort_function_notice /* 2131297309 */:
                W(this.m.getString(R.string.dipilot_ditrainer_dialog_new_intelligent_escort_function_explain), this.m.getString(R.string.dipilot_ditrainer_dialog_new_intelligent_escort_function_explain_tips), null, 1);
                break;
            case R.id.rl_driving_level /* 2131297957 */:
                Y();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        EventBus.getDefault().register(this);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        DampingScrollView dampingScrollView = this.N;
        if (dampingScrollView != null) {
            dampingScrollView.removeAllViews();
        }
        this.N = null;
        K();
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(a aVar) {
        aVar.h();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        this.o.setChecked(true, false);
    }
}
