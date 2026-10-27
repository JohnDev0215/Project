package com.byd.carsettings.ui.fragment.dipilot;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import b.b.g.n.l.f.m;
import b.b.g.n.l.f.n.a;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dipilot.BaseAssistAdapter;
import com.byd.carsettings.common.GridSpaceItemDecoration;
import com.byd.carsettings.databinding.FragmentDriverAssistBinding;
import com.byd.carsettings.ui.dialog.adas.AdasCommonDialog;
import com.byd.dipilot.BaseFragment;
import com.byd.dipilot.model.DriverAssistModel;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;
import com.byd.dipilot.viewmodel.NoConfigDialogModel;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DriverAssistFragment extends BaseFragment<FragmentDriverAssistBinding, m> implements a {
    public BaseAssistAdapter<DriverAssistModel> i;

    @Override // b.b.k.a.b.a
    public void g(List<DriverAssistModel> list) {
        BaseAssistAdapter<DriverAssistModel> baseAssistAdapter = this.i;
        if (baseAssistAdapter != null) {
            baseAssistAdapter.s(list);
        }
    }

    @Override // com.byd.dipilot.BaseFragment, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.fragment_driver_assist;
    }

    @Override // b.b.k.a.b.a
    public boolean i() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return true;
        }
        return isDetached();
    }

    @Override // b.b.k.a.b.a
    public void j(DriverAssistModel driverAssistModel, int i, int i2, int i3, b.b.k.a.a aVar) {
        o(driverAssistModel, i, i2, i3, 0, aVar);
    }

    @Override // b.b.g.n.l.f.n.a
    public void o(DriverAssistModel driverAssistModel, int i, int i2, int i3, int i4, b.b.k.a.a aVar) {
        NoConfigDialogModel noConfigDialogModel = (NoConfigDialogModel) new ViewModelProvider(requireActivity(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NoConfigDialogModel.class);
        if (noConfigDialogModel.e() != null) {
            return;
        }
        AdasCommonDialog adasCommonDialog = new AdasCommonDialog();
        boolean z = true;
        boolean z2 = i2 == 0 && i != R.string.dipilot_intelligent_navigation_toast;
        noConfigDialogModel.i(driverAssistModel.getTag(), i, i2, aVar, adasCommonDialog, driverAssistModel);
        noConfigDialogModel.q(z2);
        noConfigDialogModel.r(!z2 ? i != R.string.dipilot_intelligent_navigation_toast : !(driverAssistModel.getTag() == R.string.dipilot_interactive_lane_title || driverAssistModel.getTag() == R.string.dipilot_smart_navigation_title));
        noConfigDialogModel.s((byte) i4);
        if (i == R.string.dipilot_intelligent_navigation_toast) {
            noConfigDialogModel.p(false);
        } else {
            if (z2 && aVar != null) {
                z = false;
            }
            noConfigDialogModel.p(z);
        }
        adasCommonDialog.show(requireActivity().getSupportFragmentManager(), getClass().getCanonicalName());
    }

    @Override // com.byd.dipilot.BaseFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        BaseAssistAdapter<DriverAssistModel> baseAssistAdapter = this.i;
        if (baseAssistAdapter != null) {
            baseAssistAdapter.a();
        }
        this.i = null;
    }

    @Override // com.byd.dipilot.BaseFragment
    public void t() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.dipilot.BaseFragment
    public void v(Context context) {
        ((m) mo7getP()).h(((FragmentDriverAssistBinding) this.f3615c).b());
    }

    @Override // com.byd.dipilot.BaseFragment
    public void w(Context context) {
        BaseAdasViewModel baseAdasViewModel = (BaseAdasViewModel) new ViewModelProvider(getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(BaseAdasViewModel.class);
        ((FragmentDriverAssistBinding) this.f3615c).c(baseAdasViewModel);
        ((FragmentDriverAssistBinding) this.f3615c).setLifecycleOwner(this);
        RecyclerView recyclerView = ((FragmentDriverAssistBinding) this.f3615c).f2815a;
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        BaseAssistAdapter<DriverAssistModel> baseAssistAdapter = new BaseAssistAdapter<>(getContext(), baseAdasViewModel.d(), this);
        this.i = baseAssistAdapter;
        recyclerView.setAdapter(baseAssistAdapter);
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_16);
        ((FragmentDriverAssistBinding) this.f3615c).f2815a.addItemDecoration(new GridSpaceItemDecoration(2, dimension, dimension));
        RecyclerView.ItemAnimator itemAnimator = ((FragmentDriverAssistBinding) this.f3615c).f2815a.getItemAnimator();
        Objects.requireNonNull(itemAnimator);
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        this.i.t((b.b.k.d.a.a) mo7getP());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
    public m newP() {
        V v = this.f3615c;
        if (v != 0 && ((FragmentDriverAssistBinding) v).b() != null && (((FragmentDriverAssistBinding) this.f3615c).b().h() instanceof m)) {
            return (m) ((FragmentDriverAssistBinding) this.f3615c).b().h();
        }
        m mVar = new m();
        V v2 = this.f3615c;
        if (v2 != 0 && ((FragmentDriverAssistBinding) v2).b() != null && ((FragmentDriverAssistBinding) this.f3615c).b().h() == null) {
            ((FragmentDriverAssistBinding) this.f3615c).b().n(mVar);
        }
        return mVar;
    }
}
