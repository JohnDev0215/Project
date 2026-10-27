package com.byd.automultipletheme.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.adapter.CarouselAddChildAdapter;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.adapter.RecyclerViewSpaceItemDecoration;
import com.byd.automultipletheme.databinding.FragmentAddCarouselChildBinding;
import com.byd.automultipletheme.vm.CarouselAddViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CarouselAddWallpaperFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\u001a\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/byd/automultipletheme/fragment/CarouselAddWallpaperFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/CarouselAddChildAdapter;", "binding", "Lcom/byd/automultipletheme/databinding/FragmentAddCarouselChildBinding;", "viewModel", "Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initData", "", "initViews", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselAddWallpaperFragment extends Fragment {
    private CarouselAddChildAdapter adapter;
    private FragmentAddCarouselChildBinding binding;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public CarouselAddWallpaperFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.CarouselAddWallpaperFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                return fragmentActivityRequireActivity;
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarouselAddViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.CarouselAddWallpaperFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CarouselAddViewModel getViewModel() {
        return (CarouselAddViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBindingInflate = FragmentAddCarouselChildBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentAddCarouselChildBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentAddCarouselChildBindingInflate;
        if (fragmentAddCarouselChildBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBindingInflate = null;
        }
        ConstraintLayout root = fragmentAddCarouselChildBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();
    }

    private final void initViews() {
        int i = ScreenUtils.isLandscape() ? 8 : 4;
        this.adapter = new CarouselAddChildAdapter(0);
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding = this.binding;
        CarouselAddChildAdapter carouselAddChildAdapter = null;
        if (fragmentAddCarouselChildBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBinding = null;
        }
        fragmentAddCarouselChildBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), i));
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding2 = this.binding;
        if (fragmentAddCarouselChildBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBinding2 = null;
        }
        RecyclerView recyclerView = fragmentAddCarouselChildBinding2.recyclerView;
        CarouselAddChildAdapter carouselAddChildAdapter2 = this.adapter;
        if (carouselAddChildAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAddChildAdapter2 = null;
        }
        recyclerView.setAdapter(carouselAddChildAdapter2);
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding3 = this.binding;
        if (fragmentAddCarouselChildBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBinding3 = null;
        }
        fragmentAddCarouselChildBinding3.recyclerView.addItemDecoration(new RecyclerViewSpaceItemDecoration(i, getResources().getDimensionPixelSize(R.dimen.dp_8)));
        getViewModel().getLocalWallpaperLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.byd.automultipletheme.fragment.CarouselAddWallpaperFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws ExecutionException, InterruptedException {
                CarouselAddWallpaperFragment.m70initViews$lambda1(this.f$0, (List) obj);
            }
        });
        getViewModel().getNumCount().observe(requireActivity(), new Observer() { // from class: com.byd.automultipletheme.fragment.CarouselAddWallpaperFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarouselAddWallpaperFragment.m71initViews$lambda3(this.f$0, (ArrayList) obj);
            }
        });
        CarouselAddChildAdapter carouselAddChildAdapter3 = this.adapter;
        if (carouselAddChildAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            carouselAddChildAdapter = carouselAddChildAdapter3;
        }
        carouselAddChildAdapter.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.CarouselAddWallpaperFragment.initViews.3
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, Integer num) {
                invoke(view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view, int i2) {
                Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
                Integer value = CarouselAddWallpaperFragment.this.getViewModel().getCountLiveData().getValue();
                int iIntValue = value != null ? value.intValue() : 0;
                CarouselAddChildAdapter carouselAddChildAdapter4 = null;
                if (iIntValue >= 10) {
                    CarouselAddChildAdapter carouselAddChildAdapter5 = CarouselAddWallpaperFragment.this.adapter;
                    if (carouselAddChildAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        carouselAddChildAdapter5 = null;
                    }
                    if (!carouselAddChildAdapter5.isSelect(i2)) {
                        ToastUtils.INSTANCE.showShort(R.string.str_display_10wallpaper);
                        return;
                    }
                }
                if (iIntValue <= 2) {
                    CarouselAddChildAdapter carouselAddChildAdapter6 = CarouselAddWallpaperFragment.this.adapter;
                    if (carouselAddChildAdapter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        carouselAddChildAdapter6 = null;
                    }
                    if (carouselAddChildAdapter6.isSelect(i2)) {
                        ToastUtils.INSTANCE.showShort(R.string.keep_2_wallpapers);
                        return;
                    }
                }
                CarouselAddChildAdapter carouselAddChildAdapter7 = CarouselAddWallpaperFragment.this.adapter;
                if (carouselAddChildAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carouselAddChildAdapter7 = null;
                }
                CarouselPicture item = carouselAddChildAdapter7.getItem(i2);
                String countName = FileUtils.getFileName(item != null ? item.getLandLight() : null);
                LogUtils.INSTANCE.i("pathname_w:", countName);
                CarouselAddChildAdapter carouselAddChildAdapter8 = CarouselAddWallpaperFragment.this.adapter;
                if (carouselAddChildAdapter8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carouselAddChildAdapter8 = null;
                }
                if (carouselAddChildAdapter8.setSelect(i2)) {
                    CarouselAddWallpaperFragment.this.getViewModel().setCountLiveData(iIntValue + 1);
                    CarouselAddViewModel viewModel = CarouselAddWallpaperFragment.this.getViewModel();
                    Intrinsics.checkNotNullExpressionValue(countName, "countName");
                    viewModel.addNumCount(countName);
                    CarouselAddChildAdapter carouselAddChildAdapter9 = CarouselAddWallpaperFragment.this.adapter;
                    if (carouselAddChildAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        carouselAddChildAdapter9 = null;
                    }
                    carouselAddChildAdapter9.setCountNum(CarouselAddWallpaperFragment.this.getViewModel().m105getNumCount());
                    LogUtils.INSTANCE.i("listViewmodelAdd: ", CarouselAddWallpaperFragment.this.getViewModel().m105getNumCount());
                } else {
                    int i3 = iIntValue - 1;
                    if (i3 >= 2) {
                        CarouselAddWallpaperFragment.this.getViewModel().setCountLiveData(i3);
                    } else {
                        CarouselAddWallpaperFragment.this.getViewModel().setCountLiveData(2);
                    }
                    CarouselAddViewModel viewModel2 = CarouselAddWallpaperFragment.this.getViewModel();
                    Intrinsics.checkNotNullExpressionValue(countName, "countName");
                    viewModel2.deleteNumCount(countName);
                    CarouselAddChildAdapter carouselAddChildAdapter10 = CarouselAddWallpaperFragment.this.adapter;
                    if (carouselAddChildAdapter10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        carouselAddChildAdapter10 = null;
                    }
                    carouselAddChildAdapter10.setCountNum(CarouselAddWallpaperFragment.this.getViewModel().m105getNumCount());
                }
                CarouselAddViewModel viewModel3 = CarouselAddWallpaperFragment.this.getViewModel();
                CarouselAddChildAdapter carouselAddChildAdapter11 = CarouselAddWallpaperFragment.this.adapter;
                if (carouselAddChildAdapter11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    carouselAddChildAdapter4 = carouselAddChildAdapter11;
                }
                viewModel3.setWallpaperSelectedPair(carouselAddChildAdapter4.getSelectList());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-1, reason: not valid java name */
    public static final void m70initViews$lambda1(CarouselAddWallpaperFragment this$0, List list) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            CarouselAddChildAdapter carouselAddChildAdapter = this$0.adapter;
            CarouselAddChildAdapter carouselAddChildAdapter2 = null;
            if (carouselAddChildAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                carouselAddChildAdapter = null;
            }
            carouselAddChildAdapter.setData((ArrayList) list);
            CarouselAddViewModel viewModel = this$0.getViewModel();
            CarouselAddChildAdapter carouselAddChildAdapter3 = this$0.adapter;
            if (carouselAddChildAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                carouselAddChildAdapter2 = carouselAddChildAdapter3;
            }
            viewModel.setWallpaperSelectedPair(carouselAddChildAdapter2.getSelectList());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m71initViews$lambda3(CarouselAddWallpaperFragment this$0, ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (arrayList != null) {
            CarouselAddChildAdapter carouselAddChildAdapter = this$0.adapter;
            if (carouselAddChildAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                carouselAddChildAdapter = null;
            }
            carouselAddChildAdapter.setCountNum(arrayList);
            LogUtils.INSTANCE.i("list=wallpaper=it", arrayList);
        }
    }

    private final void initData() throws ExecutionException, InterruptedException {
        getViewModel().loadLocalWallpaperLiveData();
        ArrayList<CarouselPicture> carouselRes = getViewModel().getCarouselRes();
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<CarouselPicture> it = carouselRes.iterator();
        while (it.hasNext()) {
            arrayList.add(FileUtils.getFileName(it.next().getLandLight()));
        }
        getViewModel().setNumCount(arrayList);
        CarouselAddChildAdapter carouselAddChildAdapter = this.adapter;
        if (carouselAddChildAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAddChildAdapter = null;
        }
        carouselAddChildAdapter.setCountNum(arrayList);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getViewModel().getCountLiveData().removeObservers(requireActivity());
        getViewModel().getLocalWallpaperLiveData().removeObservers(getViewLifecycleOwner());
        getViewModel().getNumCount().removeObservers(requireActivity());
    }
}
