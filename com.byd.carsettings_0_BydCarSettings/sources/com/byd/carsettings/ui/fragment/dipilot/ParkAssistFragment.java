package com.byd.carsettings.ui.fragment.dipilot;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;
import b.b.a.h.k;
import b.b.g.n.l.e;
import b.b.g.p.d;
import b.b.k.d.a.a;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.impl.BodyDeviceImpl;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dipilot.BaseAssistAdapter;
import com.byd.carsettings.common.GridSpaceItemDecoration;
import com.byd.carsettings.databinding.FragmentParkAssistBinding;
import com.byd.dipilot.BaseFragment;
import com.byd.dipilot.model.ActiveSafeModel;
import com.byd.dipilot.model.DriverAssistModel;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import com.bydux.api.common.tools.window.BnWindowHelper;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class ParkAssistFragment extends BaseFragment<FragmentParkAssistBinding, e> implements a<DriverAssistModel> {
    public BaseAssistAdapter<DriverAssistModel> i;
    public List<DriverAssistModel> j;
    public BydSwitch k;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B(View view, MotionEvent motionEvent, boolean z) {
        if (this.k.isEnabled()) {
            view.playSoundEffect(0);
            BydLogHelper.logD(this.f3613a, "onCheckedChanged : " + z);
            if (motionEvent.getAction() != 1) {
                return;
            }
            ((e) mo7getP()).l();
            this.k.setChecked(z, false);
            if (z) {
                ((FragmentParkAssistBinding) this.f3615c).f2828d.playAnimation();
            }
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public e newP() {
        return new e();
    }

    @Override // b.b.k.d.a.a
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public void p(DriverAssistModel driverAssistModel, int i) {
        if (driverAssistModel.getTag() == R.string.dipilot_title_panoramic_images) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.byd.avm", "com.byd.avm.MainActivity"));
            BnWindowHelper.startActivity(getContext(), intent);
        }
    }

    @Override // b.b.k.d.a.a
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
    public void b(LottieAnimationView lottieAnimationView, DriverAssistModel driverAssistModel, boolean z, int i) {
    }

    public final void F() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentParkAssistBinding) this.f3615c).f2826b.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).width = 0;
        layoutParams.matchConstraintPercentWidth = 0.5f;
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) ((FragmentParkAssistBinding) this.f3615c).f2827c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = 0;
        if (b.b.a.i.a.d()) {
            ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin = getContext().getResources().getDimensionPixelSize(R.dimen.dp_7);
        } else {
            ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = getContext().getResources().getDimensionPixelSize(R.dimen.dp_7);
        }
        layoutParams2.matchConstraintPercentWidth = 0.492f;
        layoutParams2.startToStart = -1;
        layoutParams2.topToBottom = -1;
        layoutParams2.topToTop = 0;
        layoutParams2.startToEnd = ((FragmentParkAssistBinding) this.f3615c).f2826b.getId();
        ((ViewGroup.MarginLayoutParams) ((ConstraintLayout.LayoutParams) ((FragmentParkAssistBinding) this.f3615c).f2825a.getLayoutParams())).width = -1;
        ((FragmentParkAssistBinding) this.f3615c).g.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    @Override // com.byd.dipilot.BaseFragment, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.fragment_park_assist;
    }

    @Override // com.byd.dipilot.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (this.h) {
            EventBus.getDefault().unregister(this);
        }
        ((FragmentParkAssistBinding) this.f3615c).f2829e.removeAllViews();
        this.k = null;
        if (this.i != null) {
            if (!ListUtils.isEmpty(this.j)) {
                this.j.clear();
                this.j = null;
            }
            this.i.t(null);
            this.i.a();
            this.i = null;
        }
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.b.a.c.a aVar) {
        int iC = aVar.c();
        int iH = aVar.h();
        if (iH != 160000) {
            if (iH != 250001) {
                return;
            }
            BydLogHelper.logW(this.f3613a, "onMessageEvent isNotRollBenchMode = " + iC);
            k.f(this.k, iC != 1);
            return;
        }
        BydLogHelper.logD(this.f3613a, "REVERSE_RADAR_SWITCH_STATE_CHANGE " + iC);
        if (b.b.g.r.e.e(aVar)) {
            iC = ((e) mo7getP()).h();
        }
        this.k.setChecked(iC == 1, false);
        if (((FragmentParkAssistBinding) this.f3615c).f2828d.isAnimating() || iC != 1) {
            return;
        }
        ((FragmentParkAssistBinding) this.f3615c).f2828d.playAnimation();
    }

    @Override // com.byd.dipilot.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        BydLogHelper.logD(this.f3613a, "onResume");
        BodyDeviceImpl bodyDeviceImplE = Router.b().e();
        if (bodyDeviceImplE == null) {
            BydLogHelper.logW(this.f3613a, "onResume, bodyDevice null!");
            return;
        }
        MutableLiveData<Boolean> mutableLiveDataH = bodyDeviceImplE.h();
        if (mutableLiveDataH == null) {
            BydLogHelper.logW(this.f3613a, "onResume, mpcOnline null!");
            return;
        }
        boolean zBooleanValue = mutableLiveDataH.getValue().booleanValue();
        k.f(this.k, !zBooleanValue);
        BydLogHelper.logW(this.f3613a, "onResume, notRollBenchMode = " + zBooleanValue);
    }

    @Override // com.byd.dipilot.BaseFragment
    public void t() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.dipilot.BaseFragment
    public void v(Context context) {
        this.j = new ArrayList();
        if (((e) mo7getP()).i()) {
            this.j.add(new ActiveSafeModel(R.string.dipilot_title_panoramic_images, 0, (byte) 0));
        }
        if (ListUtils.isEmpty(this.j)) {
            ((FragmentParkAssistBinding) this.f3615c).j.setVisibility(8);
            ((FragmentParkAssistBinding) this.f3615c).g.setVisibility(8);
        } else {
            this.i.s(this.j);
        }
        boolean zJ = ((e) mo7getP()).j();
        ((FragmentParkAssistBinding) this.f3615c).f2825a.setVisibility(zJ ? 0 : 8);
        ((FragmentParkAssistBinding) this.f3615c).k.setVisibility(zJ ? 0 : 8);
        String string = d.g.a().v(context).toString();
        b.b.m.c.a.a(this.f3613a, "---initData--- lngTag: " + string);
        String string2 = getContext().getString(R.string.dipilot_parking_sensor_monitor);
        if (!TextUtils.isEmpty(string2) && string.startsWith("th")) {
            string2 = string2 + " ";
        }
        ((FragmentParkAssistBinding) this.f3615c).k.setText(string2);
        if (zJ) {
            this.k.setChecked(((e) mo7getP()).h() == 1, false);
            ((FragmentParkAssistBinding) this.f3615c).f2828d.setAnimation(BaseAssistAdapter.b(81));
        }
        if (!this.h) {
            EventBus.getDefault().register(this);
            this.h = true;
        }
        int iC = b.b.o.b.b.a.c();
        if (iC == 1) {
            x();
        } else if (iC == 2) {
            y();
        }
        if (zJ && this.j.size() == 1) {
            F();
        }
        b.b.m.c.a.a(this.f3613a, "initData, isSupportParkeRadar = " + zJ + " mModelList.size = " + this.j.size() + " layoutType = " + iC);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.byd.dipilot.BaseFragment
    public void w(Context context) {
        ((FragmentParkAssistBinding) this.f3615c).f2830f.setLayoutDirection(b.b.a.i.a.d() ? 1 : 0);
        this.k = ((FragmentParkAssistBinding) this.f3615c).h;
        ((FragmentParkAssistBinding) this.f3615c).g.setLayoutManager(new GridLayoutManager(context, 2));
        BaseAssistAdapter<DriverAssistModel> baseAssistAdapter = new BaseAssistAdapter<>(context, this.j, this);
        this.i = baseAssistAdapter;
        ((FragmentParkAssistBinding) this.f3615c).g.setAdapter(baseAssistAdapter);
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_16);
        ((FragmentParkAssistBinding) this.f3615c).g.addItemDecoration(new GridSpaceItemDecoration(2, dimension, dimension));
        ((SimpleItemAnimator) ((FragmentParkAssistBinding) this.f3615c).g.getItemAnimator()).setSupportsChangeAnimations(false);
        this.i.t(this);
        z();
    }

    public final void x() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentParkAssistBinding) this.f3615c).h.getLayoutParams();
        layoutParams.startToStart = -1;
        layoutParams.endToEnd = 0;
        layoutParams.setMarginEnd(getContext().getResources().getDimensionPixelSize(R.dimen.dp_16));
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) ((FragmentParkAssistBinding) this.f3615c).f2828d.getLayoutParams();
        layoutParams2.startToStart = 0;
        layoutParams2.endToEnd = -1;
    }

    public final void y() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentParkAssistBinding) this.f3615c).h.getLayoutParams();
        layoutParams.startToStart = -1;
        layoutParams.endToEnd = 0;
        layoutParams.setMarginEnd(getContext().getResources().getDimensionPixelSize(R.dimen.dp_16));
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) ((FragmentParkAssistBinding) this.f3615c).f2828d.getLayoutParams();
        layoutParams2.startToStart = 0;
        layoutParams2.endToEnd = -1;
        if (getContext().getResources().getConfiguration().orientation == 2) {
            ((FragmentParkAssistBinding) this.f3615c).f2830f.setPadding(getContext().getResources().getDimensionPixelSize(R.dimen.dp_31), ((FragmentParkAssistBinding) this.f3615c).f2830f.getPaddingTop(), 0, ((FragmentParkAssistBinding) this.f3615c).f2830f.getPaddingBottom());
        } else {
            ((FragmentParkAssistBinding) this.f3615c).f2830f.setPadding(getContext().getResources().getDimensionPixelSize(R.dimen.dp_27), ((FragmentParkAssistBinding) this.f3615c).f2830f.getPaddingTop(), getContext().getResources().getDimensionPixelSize(R.dimen.dp_27), ((FragmentParkAssistBinding) this.f3615c).f2830f.getPaddingBottom());
        }
    }

    public final void z() {
        this.k.setOnInterceptTouchListener(new BydSwitch.a() { // from class: b.b.g.q.e.b.h
            @Override // com.byd.base.view.BydSwitch.a
            public final void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
                this.f1520a.B(view, motionEvent, z);
            }
        });
    }
}
