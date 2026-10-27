package com.byd.automultipletheme.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.byd.automultipletheme.activity.CropActivity;
import com.byd.automultipletheme.adapter.CarouselAddChildAdapter;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.common.Const;
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

/* JADX INFO: compiled from: CarouselAddPhotoFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J$\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0014H\u0016J\b\u0010\"\u001a\u00020\u0014H\u0016J\u001a\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010%\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006("}, d2 = {"Lcom/byd/automultipletheme/fragment/CarouselAddPhotoFragment;", "Landroidx/fragment/app/Fragment;", "()V", "activityRebuildFlag", "", "adapter", "Lcom/byd/automultipletheme/adapter/CarouselAddChildAdapter;", "binding", "Lcom/byd/automultipletheme/databinding/FragmentAddCarouselChildBinding;", "clickPosition", "registerActivity", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "viewModel", "Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initData", "", "initViews", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "startCropActivity", "item", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselAddPhotoFragment extends Fragment {
    private int activityRebuildFlag;
    private CarouselAddChildAdapter adapter;
    private FragmentAddCarouselChildBinding binding;
    private int clickPosition;
    private ActivityResultLauncher<Intent> registerActivity;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public CarouselAddPhotoFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.CarouselAddPhotoFragment$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarouselAddViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.CarouselAddPhotoFragment$special$$inlined$viewModels$default$1
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
        this.clickPosition = -1;
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
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.byd.automultipletheme.fragment.CarouselAddPhotoFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CarouselAddPhotoFragment.m69onAttach$lambda1(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…          }\n            }");
        this.registerActivity = activityResultLauncherRegisterForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onAttach$lambda-1, reason: not valid java name */
    public static final void m69onAttach$lambda1(CarouselAddPhotoFragment this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogUtils.INSTANCE.i(activityResult.getData(), Integer.valueOf(activityResult.getResultCode()));
        Intent data = activityResult.getData();
        if (data != null && data.getBooleanExtra("isCancel", false)) {
            CarouselAddChildAdapter carouselAddChildAdapter = null;
            if (this$0.clickPosition != -1) {
                CarouselAddChildAdapter carouselAddChildAdapter2 = this$0.adapter;
                if (carouselAddChildAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carouselAddChildAdapter2 = null;
                }
                CarouselPicture item = carouselAddChildAdapter2.getItem(this$0.clickPosition);
                String countName = FileUtils.getFileName(item != null ? item.getLandLight() : null);
                CarouselAddViewModel viewModel = this$0.getViewModel();
                Intrinsics.checkNotNullExpressionValue(countName, "countName");
                viewModel.deleteNumCount(countName);
            }
            CarouselAddChildAdapter carouselAddChildAdapter3 = this$0.adapter;
            if (carouselAddChildAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                carouselAddChildAdapter3 = null;
            }
            carouselAddChildAdapter3.setSelect(this$0.clickPosition);
            CarouselAddViewModel viewModel2 = this$0.getViewModel();
            CarouselAddChildAdapter carouselAddChildAdapter4 = this$0.adapter;
            if (carouselAddChildAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                carouselAddChildAdapter = carouselAddChildAdapter4;
            }
            viewModel2.setPhotoSelectedPair(carouselAddChildAdapter.getSelectList());
        }
        Intent data2 = activityResult.getData();
        if (data2 != null && data2.getBooleanExtra("isFinish", false)) {
            Integer value = this$0.getViewModel().getCountLiveData().getValue();
            int iIntValue = value != null ? value.intValue() : 0;
            if (this$0.activityRebuildFlag == 0) {
                this$0.getViewModel().setCountLiveData(iIntValue + 1);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();
        this.activityRebuildFlag = 1;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.activityRebuildFlag = 0;
    }

    private final void initViews() {
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding = this.binding;
        CarouselAddChildAdapter carouselAddChildAdapter = null;
        if (fragmentAddCarouselChildBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBinding = null;
        }
        fragmentAddCarouselChildBinding.progressBar.setVisibility(0);
        int i = ScreenUtils.isLandscape() ? 8 : 4;
        this.adapter = new CarouselAddChildAdapter(1);
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding2 = this.binding;
        if (fragmentAddCarouselChildBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBinding2 = null;
        }
        fragmentAddCarouselChildBinding2.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), i));
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding3 = this.binding;
        if (fragmentAddCarouselChildBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBinding3 = null;
        }
        RecyclerView recyclerView = fragmentAddCarouselChildBinding3.recyclerView;
        CarouselAddChildAdapter carouselAddChildAdapter2 = this.adapter;
        if (carouselAddChildAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAddChildAdapter2 = null;
        }
        recyclerView.setAdapter(carouselAddChildAdapter2);
        FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding4 = this.binding;
        if (fragmentAddCarouselChildBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselChildBinding4 = null;
        }
        fragmentAddCarouselChildBinding4.recyclerView.addItemDecoration(new RecyclerViewSpaceItemDecoration(i, getResources().getDimensionPixelSize(R.dimen.dp_8)));
        getViewModel().getLocalPhotoLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.byd.automultipletheme.fragment.CarouselAddPhotoFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws ExecutionException, InterruptedException {
                CarouselAddPhotoFragment.m67initViews$lambda3(this.f$0, (List) obj);
            }
        });
        getViewModel().getNumCount().observe(requireActivity(), new Observer() { // from class: com.byd.automultipletheme.fragment.CarouselAddPhotoFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarouselAddPhotoFragment.m68initViews$lambda5(this.f$0, (ArrayList) obj);
            }
        });
        CarouselAddChildAdapter carouselAddChildAdapter3 = this.adapter;
        if (carouselAddChildAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            carouselAddChildAdapter = carouselAddChildAdapter3;
        }
        carouselAddChildAdapter.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.CarouselAddPhotoFragment.initViews.3
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
                Integer value = CarouselAddPhotoFragment.this.getViewModel().getCountLiveData().getValue();
                int iIntValue = value != null ? value.intValue() : 0;
                CarouselAddChildAdapter carouselAddChildAdapter4 = null;
                if (iIntValue >= 10) {
                    CarouselAddChildAdapter carouselAddChildAdapter5 = CarouselAddPhotoFragment.this.adapter;
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
                    CarouselAddChildAdapter carouselAddChildAdapter6 = CarouselAddPhotoFragment.this.adapter;
                    if (carouselAddChildAdapter6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        carouselAddChildAdapter6 = null;
                    }
                    if (carouselAddChildAdapter6.isSelect(i2)) {
                        ToastUtils.INSTANCE.showShort(R.string.keep_2_wallpapers);
                        return;
                    }
                }
                CarouselAddPhotoFragment.this.clickPosition = i2;
                LogUtils.INSTANCE.i("clickposition: ", Integer.valueOf(CarouselAddPhotoFragment.this.clickPosition));
                CarouselAddChildAdapter carouselAddChildAdapter7 = CarouselAddPhotoFragment.this.adapter;
                if (carouselAddChildAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carouselAddChildAdapter7 = null;
                }
                boolean select = carouselAddChildAdapter7.setSelect(i2);
                LogUtils.INSTANCE.i("isAdd: ", Boolean.valueOf(select));
                CarouselAddChildAdapter carouselAddChildAdapter8 = CarouselAddPhotoFragment.this.adapter;
                if (carouselAddChildAdapter8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carouselAddChildAdapter8 = null;
                }
                CarouselPicture item = carouselAddChildAdapter8.getItem(i2);
                String countName = FileUtils.getFileName(item != null ? item.getLandLight() : null);
                LogUtils.INSTANCE.i("pathname_p:", countName);
                if (!select) {
                    int i3 = iIntValue - 1;
                    if (i3 >= 2) {
                        CarouselAddPhotoFragment.this.getViewModel().setCountLiveData(i3);
                    } else {
                        CarouselAddPhotoFragment.this.getViewModel().setCountLiveData(2);
                    }
                    CarouselAddViewModel viewModel = CarouselAddPhotoFragment.this.getViewModel();
                    Intrinsics.checkNotNullExpressionValue(countName, "countName");
                    viewModel.deleteNumCount(countName);
                    CarouselAddChildAdapter carouselAddChildAdapter9 = CarouselAddPhotoFragment.this.adapter;
                    if (carouselAddChildAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        carouselAddChildAdapter9 = null;
                    }
                    carouselAddChildAdapter9.setCountNum(CarouselAddPhotoFragment.this.getViewModel().m105getNumCount());
                } else {
                    try {
                        CarouselAddPhotoFragment carouselAddPhotoFragment = CarouselAddPhotoFragment.this;
                        CarouselAddChildAdapter carouselAddChildAdapter10 = carouselAddPhotoFragment.adapter;
                        if (carouselAddChildAdapter10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            carouselAddChildAdapter10 = null;
                        }
                        carouselAddPhotoFragment.startCropActivity(carouselAddChildAdapter10.getItem(i2));
                        CarouselAddViewModel viewModel2 = CarouselAddPhotoFragment.this.getViewModel();
                        Intrinsics.checkNotNullExpressionValue(countName, "countName");
                        viewModel2.addNumCount(countName);
                        CarouselAddChildAdapter carouselAddChildAdapter11 = CarouselAddPhotoFragment.this.adapter;
                        if (carouselAddChildAdapter11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            carouselAddChildAdapter11 = null;
                        }
                        carouselAddChildAdapter11.setCountNum(CarouselAddPhotoFragment.this.getViewModel().m105getNumCount());
                        LogUtils.INSTANCE.i("listViewmodelAdd: ", CarouselAddPhotoFragment.this.getViewModel().m105getNumCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtils.INSTANCE.i("getItem() is crash");
                        CarouselAddChildAdapter carouselAddChildAdapter12 = CarouselAddPhotoFragment.this.adapter;
                        if (carouselAddChildAdapter12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            carouselAddChildAdapter12 = null;
                        }
                        carouselAddChildAdapter12.setSelect(i2);
                    }
                }
                CarouselAddViewModel viewModel3 = CarouselAddPhotoFragment.this.getViewModel();
                CarouselAddChildAdapter carouselAddChildAdapter13 = CarouselAddPhotoFragment.this.adapter;
                if (carouselAddChildAdapter13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    carouselAddChildAdapter4 = carouselAddChildAdapter13;
                }
                viewModel3.setPhotoSelectedPair(carouselAddChildAdapter4.getSelectList());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m67initViews$lambda3(CarouselAddPhotoFragment this$0, List list) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            CarouselAddChildAdapter carouselAddChildAdapter = this$0.adapter;
            FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding = null;
            if (carouselAddChildAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                carouselAddChildAdapter = null;
            }
            carouselAddChildAdapter.setData((ArrayList) list);
            CarouselAddViewModel viewModel = this$0.getViewModel();
            CarouselAddChildAdapter carouselAddChildAdapter2 = this$0.adapter;
            if (carouselAddChildAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                carouselAddChildAdapter2 = null;
            }
            viewModel.setPhotoSelectedPair(carouselAddChildAdapter2.getSelectList());
            LogUtils.INSTANCE.i("localPhotoLiveDateChanged");
            FragmentAddCarouselChildBinding fragmentAddCarouselChildBinding2 = this$0.binding;
            if (fragmentAddCarouselChildBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentAddCarouselChildBinding = fragmentAddCarouselChildBinding2;
            }
            fragmentAddCarouselChildBinding.progressBar.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-5, reason: not valid java name */
    public static final void m68initViews$lambda5(CarouselAddPhotoFragment this$0, ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (arrayList != null) {
            CarouselAddChildAdapter carouselAddChildAdapter = this$0.adapter;
            if (carouselAddChildAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                carouselAddChildAdapter = null;
            }
            carouselAddChildAdapter.setCountNum(arrayList);
            LogUtils.INSTANCE.i("list=photo=it", arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCropActivity(CarouselPicture item) {
        if (item != null) {
            Intent intent = new Intent(getActivity(), (Class<?>) CropActivity.class);
            intent.putExtra(Const.CAROUSEL_CROP, item);
            ActivityResultLauncher<Intent> activityResultLauncher = this.registerActivity;
            if (activityResultLauncher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("registerActivity");
                activityResultLauncher = null;
            }
            activityResultLauncher.launch(intent);
        }
    }

    private final void initData() throws ExecutionException, InterruptedException {
        getViewModel().loadLocalPhotoLiveData();
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
        getViewModel().getLocalPhotoLiveData().removeObservers(getViewLifecycleOwner());
        getViewModel().getNumCount().removeObservers(requireActivity());
    }
}
