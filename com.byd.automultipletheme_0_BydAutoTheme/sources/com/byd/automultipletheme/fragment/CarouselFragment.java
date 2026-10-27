package com.byd.automultipletheme.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.adapter.CarouselAdapter;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LimitedClickUtils;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.WallpaperManagerUtils;
import com.byd.automultipletheme.common.adapter.AutoBaseAdapter;
import com.byd.automultipletheme.common.adapter.RecyclerViewSpaceItemDecoration;
import com.byd.automultipletheme.common.base.BaseDialogFragment;
import com.byd.automultipletheme.common.db.DBUtils;
import com.byd.automultipletheme.databinding.CarouselPopupWindowBinding;
import com.byd.automultipletheme.databinding.FragmentCarouselBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import com.byd.automultipletheme.fragment.CarouselAddFragment;
import com.byd.automultipletheme.vm.CarouselAddViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: CarouselFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 42\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0003J\u0012\u0010\u001f\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J$\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010(\u001a\u00020\u0018H\u0016J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020+H\u0007J\b\u0010,\u001a\u00020\u0018H\u0016J\u001a\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020#2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010/\u001a\u00020\u0018H\u0002J(\u00100\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\n2\u0006\u00102\u001a\u00020\n2\u0006\u00103\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u00065"}, d2 = {"Lcom/byd/automultipletheme/fragment/CarouselFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/CarouselAdapter;", "autoWallpaper", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "binding", "Lcom/byd/automultipletheme/databinding/FragmentCarouselBinding;", "isEdit", "", "layoutDirection", IntentString.DATA_LIST, "", "Lcom/byd/automultipletheme/bean/CarouselPicture;", "popupWindow", "Landroid/widget/PopupWindow;", "viewModel", "Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyRudder", "", "createPopWindow", "initData", "initPopViews", "popBinding", "Lcom/byd/automultipletheme/databinding/CarouselPopupWindowBinding;", "initViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "onStart", "onViewCreated", "view", "showPopWindow", "updatePopView", "day", "hour", "min", "Companion", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "CarouselFragment";
    private CarouselAdapter adapter;
    private AutoWallpaper autoWallpaper;
    private FragmentCarouselBinding binding;
    private boolean isEdit;
    private boolean layoutDirection;
    private List<CarouselPicture> list;
    private PopupWindow popupWindow;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public CarouselFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarouselAddViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$special$$inlined$viewModels$default$1
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
        this.list = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CarouselAddViewModel getViewModel() {
        return (CarouselAddViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                Intrinsics.checkNotNullExpressionValue(window, "window");
                if (ScreenUtils.isLandscape()) {
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_1142);
                    dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.dp_485);
                } else {
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_560);
                    dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.dp_751);
                }
                window.setBackgroundDrawableResource(R.drawable.ic_dialog_bg);
                window.setLayout(dimensionPixelSize, dimensionPixelSize2);
            }
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentCarouselBinding fragmentCarouselBindingInflate = FragmentCarouselBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentCarouselBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentCarouselBindingInflate;
        this.layoutDirection = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        applyRudder();
        FragmentCarouselBinding fragmentCarouselBinding = this.binding;
        if (fragmentCarouselBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding = null;
        }
        ConstraintLayout root = fragmentCarouselBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable(TAG);
            Objects.requireNonNull(serializable, "null cannot be cast to non-null type com.byd.automultipletheme.bean.AutoWallpaper");
            this.autoWallpaper = (AutoWallpaper) serializable;
        }
        initViews();
        initData();
    }

    private final void initViews() {
        FragmentCarouselBinding fragmentCarouselBinding = this.binding;
        CarouselAdapter carouselAdapter = null;
        if (fragmentCarouselBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding = null;
        }
        fragmentCarouselBinding.itemClose.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarouselFragment.m79initViews$lambda3(this.f$0, view);
            }
        });
        this.adapter = new CarouselAdapter();
        int i = ScreenUtils.isLandscape() ? 5 : 4;
        FragmentCarouselBinding fragmentCarouselBinding2 = this.binding;
        if (fragmentCarouselBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding2 = null;
        }
        fragmentCarouselBinding2.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), i));
        FragmentCarouselBinding fragmentCarouselBinding3 = this.binding;
        if (fragmentCarouselBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding3 = null;
        }
        RecyclerView recyclerView = fragmentCarouselBinding3.recyclerView;
        CarouselAdapter carouselAdapter2 = this.adapter;
        if (carouselAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAdapter2 = null;
        }
        recyclerView.setAdapter(carouselAdapter2);
        FragmentCarouselBinding fragmentCarouselBinding4 = this.binding;
        if (fragmentCarouselBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding4 = null;
        }
        if (fragmentCarouselBinding4.recyclerView.getItemDecorationCount() == 0) {
            FragmentCarouselBinding fragmentCarouselBinding5 = this.binding;
            if (fragmentCarouselBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding5 = null;
            }
            fragmentCarouselBinding5.recyclerView.addItemDecoration(new RecyclerViewSpaceItemDecoration(i, getResources().getDimensionPixelSize(R.dimen.dp_12)));
        }
        CarouselAdapter carouselAdapter3 = this.adapter;
        if (carouselAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAdapter3 = null;
        }
        boolean z = this.isEdit;
        CarouselAdapter carouselAdapter4 = this.adapter;
        if (carouselAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAdapter4 = null;
        }
        carouselAdapter3.setEditStatus(z, carouselAdapter4.getItemCount());
        FragmentCarouselBinding fragmentCarouselBinding6 = this.binding;
        if (fragmentCarouselBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding6 = null;
        }
        fragmentCarouselBinding6.itemEdit.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarouselFragment.m80initViews$lambda4(this.f$0, view);
            }
        });
        FragmentCarouselBinding fragmentCarouselBinding7 = this.binding;
        if (fragmentCarouselBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding7 = null;
        }
        fragmentCarouselBinding7.itemCarouselTime.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarouselFragment.m81initViews$lambda5(this.f$0, view);
            }
        });
        FragmentCarouselBinding fragmentCarouselBinding8 = this.binding;
        if (fragmentCarouselBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding8 = null;
        }
        fragmentCarouselBinding8.itemAction.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarouselFragment.m82initViews$lambda6(this.f$0, view);
            }
        });
        CarouselAdapter carouselAdapter5 = this.adapter;
        if (carouselAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAdapter5 = null;
        }
        carouselAdapter5.addOnItemChildClickListener(new Function3<AutoBaseAdapter<CarouselPicture>, View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.CarouselFragment.initViews.5
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(AutoBaseAdapter<CarouselPicture> autoBaseAdapter, View view, Integer num) {
                invoke(autoBaseAdapter, view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(AutoBaseAdapter<CarouselPicture> autoBaseAdapter, View view, int i2) {
                Intrinsics.checkNotNullParameter(autoBaseAdapter, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(view, "view");
                LogUtils.INSTANCE.d(Integer.valueOf(view.getId()), Integer.valueOf(i2));
                Integer value = CarouselFragment.this.getViewModel().getCountLiveData().getValue();
                int iIntValue = value != null ? value.intValue() : 0;
                CarouselAdapter carouselAdapter6 = CarouselFragment.this.adapter;
                CarouselAdapter carouselAdapter7 = null;
                if (carouselAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carouselAdapter6 = null;
                }
                CarouselPicture item = carouselAdapter6.getItem(i2);
                if (item != null) {
                    CarouselFragment carouselFragment = CarouselFragment.this;
                    if (iIntValue > 2) {
                        int iDeleteCarouselRes = carouselFragment.getViewModel().deleteCarouselRes(item);
                        try {
                            String landLight = item.getLandLight();
                            Intrinsics.checkNotNull(landLight);
                            String[] strArr = {FileUtils.getFileNameNoExtension(landLight)};
                            WallpaperManagerUtils.getInstance().deleteBitmaps(strArr, 0, 4, true);
                            WallpaperManagerUtils.getInstance().deleteBitmaps(strArr, 1, 4, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LogUtils.INSTANCE.i(Integer.valueOf(iDeleteCarouselRes));
                        CarouselAdapter carouselAdapter8 = carouselFragment.adapter;
                        if (carouselAdapter8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            carouselAdapter8 = null;
                        }
                        CarouselAdapter carouselAdapter9 = carouselFragment.adapter;
                        if (carouselAdapter9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            carouselAdapter9 = null;
                        }
                        carouselAdapter8.removeItem(i2, carouselAdapter9.getItemCount());
                        CarouselAdapter carouselAdapter10 = carouselFragment.adapter;
                        if (carouselAdapter10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            carouselAdapter10 = null;
                        }
                        if (carouselAdapter10.getItemCount() < 10) {
                            CarouselAdapter carouselAdapter11 = carouselFragment.adapter;
                            if (carouselAdapter11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                carouselAdapter11 = null;
                            }
                            CarouselAdapter carouselAdapter12 = carouselFragment.adapter;
                            if (carouselAdapter12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                carouselAdapter12 = null;
                            }
                            CarouselPicture item2 = carouselAdapter11.getItem(carouselAdapter12.getItemCount() - 1);
                            if (item2 != null) {
                                if (!Intrinsics.areEqual(item2.getLandLight(), "add")) {
                                    carouselFragment.getViewModel().setCountLiveData(carouselFragment.list.size());
                                    carouselFragment.list.add(new CarouselPicture("add", "add", "add", "add", false, null, 48, null));
                                    CarouselAdapter carouselAdapter13 = carouselFragment.adapter;
                                    if (carouselAdapter13 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                    } else {
                                        carouselAdapter7 = carouselAdapter13;
                                    }
                                    carouselAdapter7.setData((ArrayList) carouselFragment.list);
                                    return;
                                }
                                carouselFragment.getViewModel().setCountLiveData(carouselFragment.list.size() - 1);
                                return;
                            }
                            return;
                        }
                        carouselFragment.getViewModel().setCountLiveData(carouselFragment.list.size());
                        return;
                    }
                    ToastUtils.INSTANCE.showShort(R.string.keep_2_wallpapers);
                }
            }
        });
        CarouselAdapter carouselAdapter6 = this.adapter;
        if (carouselAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            carouselAdapter = carouselAdapter6;
        }
        carouselAdapter.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.CarouselFragment.initViews.6
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
                CarouselAdapter carouselAdapter7 = CarouselFragment.this.adapter;
                if (carouselAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    carouselAdapter7 = null;
                }
                CarouselPicture item = carouselAdapter7.getItem(i2);
                if (Intrinsics.areEqual("add", item != null ? item.getLandLight() : null)) {
                    CarouselAddFragment.Companion companion = CarouselAddFragment.INSTANCE;
                    FragmentManager childFragmentManager = CarouselFragment.this.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                    final CarouselFragment carouselFragment = CarouselFragment.this;
                    companion.showDialog(childFragmentManager, new Function0<Unit>() { // from class: com.byd.automultipletheme.fragment.CarouselFragment.initViews.6.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() throws ExecutionException, InterruptedException {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() throws ExecutionException, InterruptedException {
                            carouselFragment.initData();
                            CarouselAdapter carouselAdapter8 = carouselFragment.adapter;
                            if (carouselAdapter8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                carouselAdapter8 = null;
                            }
                            carouselAdapter8.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        createPopWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m79initViews$lambda3(CarouselFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-4, reason: not valid java name */
    public static final void m80initViews$lambda4(CarouselFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentCarouselBinding fragmentCarouselBinding = this$0.binding;
        CarouselAdapter carouselAdapter = null;
        if (fragmentCarouselBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding = null;
        }
        if (fragmentCarouselBinding.itemEdit.isSelected()) {
            boolean z = !this$0.isEdit;
            this$0.isEdit = z;
            if (z) {
                FragmentCarouselBinding fragmentCarouselBinding2 = this$0.binding;
                if (fragmentCarouselBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding2 = null;
                }
                fragmentCarouselBinding2.itemEdit.setText(R.string.str_complete);
            } else {
                FragmentCarouselBinding fragmentCarouselBinding3 = this$0.binding;
                if (fragmentCarouselBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding3 = null;
                }
                fragmentCarouselBinding3.itemEdit.setText(R.string.str_edit);
            }
            CarouselAdapter carouselAdapter2 = this$0.adapter;
            if (carouselAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                carouselAdapter2 = null;
            }
            boolean z2 = this$0.isEdit;
            CarouselAdapter carouselAdapter3 = this$0.adapter;
            if (carouselAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                carouselAdapter = carouselAdapter3;
            }
            carouselAdapter2.setEditStatus(z2, carouselAdapter.getItemCount());
            return;
        }
        ToastUtils.INSTANCE.showShort(R.string.str_cannot_edit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-5, reason: not valid java name */
    public static final void m81initViews$lambda5(CarouselFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showPopWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-6, reason: not valid java name */
    public static final void m82initViews$lambda6(final CarouselFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LimitedClickUtils.INSTANCE.isEnoughMem()) {
            if (LimitedClickUtils.INSTANCE.canClick()) {
                BaseDialogFragment.Companion companion = BaseDialogFragment.INSTANCE;
                FragmentManager childFragmentManager = this$0.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                AutoWallpaper autoWallpaper = this$0.autoWallpaper;
                if (autoWallpaper == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("autoWallpaper");
                    autoWallpaper = null;
                }
                String string = this$0.getString(R.string.str_dialog_tips);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.str_dialog_tips)");
                String string2 = this$0.getString(R.string.str_dialog_tips_ok);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.str_dialog_tips_ok)");
                String string3 = this$0.getString(R.string.str_dialog_tips_cancel);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.str_dialog_tips_cancel)");
                companion.showDialog(childFragmentManager, autoWallpaper, string, string2, string3, new Function0<Unit>() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$initViews$4$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        try {
                            this.this$0.getViewModel().saveCarouselResources(this.this$0.getViewModel().getCarouselRes());
                            LogUtils.INSTANCE.i("preset wallpaper ...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ApplyingWindowFragment.Companion companion2 = ApplyingWindowFragment.INSTANCE;
                        FragmentManager childFragmentManager2 = this.this$0.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                        AutoWallpaper autoWallpaper2 = this.this$0.autoWallpaper;
                        if (autoWallpaper2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("autoWallpaper");
                            autoWallpaper2 = null;
                        }
                        companion2.showDialog(childFragmentManager2, autoWallpaper2);
                    }
                });
                return;
            }
            return;
        }
        ToastUtils.INSTANCE.showShort(R.string.str_storage_full);
    }

    private final void createPopWindow() {
        PopupWindow popupWindow = null;
        if (this.layoutDirection) {
            FragmentCarouselBinding fragmentCarouselBinding = this.binding;
            if (fragmentCarouselBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding = null;
            }
            fragmentCarouselBinding.itemCarouselTime.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_triangle_dowm, 0, 0, 0);
        } else {
            FragmentCarouselBinding fragmentCarouselBinding2 = this.binding;
            if (fragmentCarouselBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding2 = null;
            }
            fragmentCarouselBinding2.itemCarouselTime.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_triangle_dowm, 0);
        }
        this.popupWindow = new PopupWindow(getContext());
        CarouselPopupWindowBinding carouselPopupWindowBindingInflate = CarouselPopupWindowBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(carouselPopupWindowBindingInflate, "inflate(layoutInflater)");
        initPopViews(carouselPopupWindowBindingInflate);
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow2 = null;
        }
        popupWindow2.setContentView(carouselPopupWindowBindingInflate.getRoot());
        PopupWindow popupWindow3 = this.popupWindow;
        if (popupWindow3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow3 = null;
        }
        popupWindow3.setBackgroundDrawable(new ColorDrawable(0));
        PopupWindow popupWindow4 = this.popupWindow;
        if (popupWindow4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow4 = null;
        }
        popupWindow4.setOutsideTouchable(true);
        PopupWindow popupWindow5 = this.popupWindow;
        if (popupWindow5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow5 = null;
        }
        popupWindow5.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda7
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                CarouselFragment.m75createPopWindow$lambda7(this.f$0);
            }
        });
        PopupWindow popupWindow6 = this.popupWindow;
        if (popupWindow6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
        } else {
            popupWindow = popupWindow6;
        }
        popupWindow.setFocusable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createPopWindow$lambda-7, reason: not valid java name */
    public static final void m75createPopWindow$lambda7(CarouselFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentCarouselBinding fragmentCarouselBinding = null;
        if (this$0.layoutDirection) {
            FragmentCarouselBinding fragmentCarouselBinding2 = this$0.binding;
            if (fragmentCarouselBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentCarouselBinding = fragmentCarouselBinding2;
            }
            fragmentCarouselBinding.itemCarouselTime.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_triangle_dowm, 0, 0, 0);
            return;
        }
        FragmentCarouselBinding fragmentCarouselBinding3 = this$0.binding;
        if (fragmentCarouselBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCarouselBinding = fragmentCarouselBinding3;
        }
        fragmentCarouselBinding.itemCarouselTime.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_triangle_dowm, 0);
    }

    private final void initPopViews(final CarouselPopupWindowBinding popBinding) {
        int carouselTime = getViewModel().getCarouselTime();
        if (carouselTime == 60000) {
            updatePopView(popBinding, false, false, true);
        } else if (carouselTime == 3600000) {
            updatePopView(popBinding, false, true, false);
        } else {
            updatePopView(popBinding, true, false, false);
        }
        popBinding.layoutDay.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws ExecutionException, InterruptedException {
                CarouselFragment.m77initPopViews$lambda8(this.f$0, popBinding, view);
            }
        });
        popBinding.layoutHour.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws ExecutionException, InterruptedException {
                CarouselFragment.m78initPopViews$lambda9(this.f$0, popBinding, view);
            }
        });
        popBinding.layoutMin.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws ExecutionException, InterruptedException {
                CarouselFragment.m76initPopViews$lambda10(this.f$0, popBinding, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initPopViews$lambda-8, reason: not valid java name */
    public static final void m77initPopViews$lambda8(CarouselFragment this$0, CarouselPopupWindowBinding popBinding, View view) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(popBinding, "$popBinding");
        this$0.updatePopView(popBinding, true, false, false);
        PopupWindow popupWindow = this$0.popupWindow;
        FragmentCarouselBinding fragmentCarouselBinding = null;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow = null;
        }
        popupWindow.dismiss();
        FragmentCarouselBinding fragmentCarouselBinding2 = this$0.binding;
        if (fragmentCarouselBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCarouselBinding = fragmentCarouselBinding2;
        }
        fragmentCarouselBinding.itemCarouselTime.setText(this$0.getString(R.string.str_carousel_popup_day));
        this$0.getViewModel().setCarouselTime(86400000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initPopViews$lambda-9, reason: not valid java name */
    public static final void m78initPopViews$lambda9(CarouselFragment this$0, CarouselPopupWindowBinding popBinding, View view) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(popBinding, "$popBinding");
        this$0.updatePopView(popBinding, false, true, false);
        PopupWindow popupWindow = this$0.popupWindow;
        FragmentCarouselBinding fragmentCarouselBinding = null;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow = null;
        }
        popupWindow.dismiss();
        FragmentCarouselBinding fragmentCarouselBinding2 = this$0.binding;
        if (fragmentCarouselBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCarouselBinding = fragmentCarouselBinding2;
        }
        fragmentCarouselBinding.itemCarouselTime.setText(this$0.getString(R.string.str_carousel_popup_hour));
        this$0.getViewModel().setCarouselTime(3600000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initPopViews$lambda-10, reason: not valid java name */
    public static final void m76initPopViews$lambda10(CarouselFragment this$0, CarouselPopupWindowBinding popBinding, View view) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(popBinding, "$popBinding");
        this$0.updatePopView(popBinding, false, false, true);
        PopupWindow popupWindow = this$0.popupWindow;
        FragmentCarouselBinding fragmentCarouselBinding = null;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow = null;
        }
        popupWindow.dismiss();
        FragmentCarouselBinding fragmentCarouselBinding2 = this$0.binding;
        if (fragmentCarouselBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCarouselBinding = fragmentCarouselBinding2;
        }
        fragmentCarouselBinding.itemCarouselTime.setText(this$0.getString(R.string.str_carousel_popup_min));
        this$0.getViewModel().setCarouselTime(60000);
    }

    private final void updatePopView(CarouselPopupWindowBinding popBinding, boolean day, boolean hour, boolean min) {
        popBinding.layoutDaySelectIcon.setVisibility(day ? 0 : 8);
        popBinding.layoutDaySelectTxt.setSelected(day);
        popBinding.layoutHourSelectIcon.setVisibility(hour ? 0 : 8);
        popBinding.layoutHourSelectTxt.setSelected(hour);
        popBinding.layoutMinSelectIcon.setVisibility(min ? 0 : 8);
        popBinding.layoutMinSelectTxt.setSelected(min);
    }

    private final void showPopWindow() {
        LogUtils logUtils = LogUtils.INSTANCE;
        Object[] objArr = new Object[1];
        PopupWindow popupWindow = this.popupWindow;
        FragmentCarouselBinding fragmentCarouselBinding = null;
        PopupWindow popupWindow2 = null;
        FragmentCarouselBinding fragmentCarouselBinding2 = null;
        FragmentCarouselBinding fragmentCarouselBinding3 = null;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow = null;
        }
        objArr[0] = Boolean.valueOf(popupWindow.isShowing());
        logUtils.i(objArr);
        PopupWindow popupWindow3 = this.popupWindow;
        if (popupWindow3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow3 = null;
        }
        if (popupWindow3.isShowing()) {
            PopupWindow popupWindow4 = this.popupWindow;
            if (popupWindow4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            } else {
                popupWindow2 = popupWindow4;
            }
            popupWindow2.dismiss();
            return;
        }
        if (this.layoutDirection) {
            FragmentCarouselBinding fragmentCarouselBinding4 = this.binding;
            if (fragmentCarouselBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding4 = null;
            }
            fragmentCarouselBinding4.itemCarouselTime.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_triangle_up, 0, 0, 0);
        } else {
            FragmentCarouselBinding fragmentCarouselBinding5 = this.binding;
            if (fragmentCarouselBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding5 = null;
            }
            fragmentCarouselBinding5.itemCarouselTime.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_triangle_up, 0);
        }
        if (AutoUtils.INSTANCE.isRightRudder() && !this.layoutDirection) {
            PopupWindow popupWindow5 = this.popupWindow;
            if (popupWindow5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
                popupWindow5 = null;
            }
            FragmentCarouselBinding fragmentCarouselBinding6 = this.binding;
            if (fragmentCarouselBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentCarouselBinding2 = fragmentCarouselBinding6;
            }
            popupWindow5.showAsDropDown(fragmentCarouselBinding2.itemCarouselTime, -getResources().getDimensionPixelSize(R.dimen.dp_80), getResources().getDimensionPixelSize(R.dimen.dp_8));
            return;
        }
        if (!this.layoutDirection) {
            PopupWindow popupWindow6 = this.popupWindow;
            if (popupWindow6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
                popupWindow6 = null;
            }
            FragmentCarouselBinding fragmentCarouselBinding7 = this.binding;
            if (fragmentCarouselBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentCarouselBinding3 = fragmentCarouselBinding7;
            }
            popupWindow6.showAsDropDown(fragmentCarouselBinding3.itemCarouselTime, 0, getResources().getDimensionPixelSize(R.dimen.dp_8));
            return;
        }
        PopupWindow popupWindow7 = this.popupWindow;
        if (popupWindow7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("popupWindow");
            popupWindow7 = null;
        }
        FragmentCarouselBinding fragmentCarouselBinding8 = this.binding;
        if (fragmentCarouselBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCarouselBinding = fragmentCarouselBinding8;
        }
        popupWindow7.showAsDropDown(fragmentCarouselBinding.itemCarouselTime, -getResources().getDimensionPixelSize(R.dimen.dp_104), getResources().getDimensionPixelSize(R.dimen.dp_8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initData() throws ExecutionException, InterruptedException {
        String string;
        AutoWallpaper autoWallpaper = this.autoWallpaper;
        FragmentCarouselBinding fragmentCarouselBinding = null;
        if (autoWallpaper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autoWallpaper");
            autoWallpaper = null;
        }
        if (autoWallpaper.getRsId() == DBUtils.INSTANCE.getCurrentWallpaperId() || -1 == DBUtils.INSTANCE.getCurrentWallpaperId()) {
            FragmentCarouselBinding fragmentCarouselBinding2 = this.binding;
            if (fragmentCarouselBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding2 = null;
            }
            fragmentCarouselBinding2.itemAction.setText(R.string.str_using);
            FragmentCarouselBinding fragmentCarouselBinding3 = this.binding;
            if (fragmentCarouselBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding3 = null;
            }
            fragmentCarouselBinding3.itemAction.setEnabled(false);
            FragmentCarouselBinding fragmentCarouselBinding4 = this.binding;
            if (fragmentCarouselBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding4 = null;
            }
            fragmentCarouselBinding4.itemEdit.setSelected(false);
            if (AutoUtils.INSTANCE.getModelType() == 3) {
                FragmentCarouselBinding fragmentCarouselBinding5 = this.binding;
                if (fragmentCarouselBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding5 = null;
                }
                fragmentCarouselBinding5.itemEdit.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.carousel_edit_text_enabled_denza));
                FragmentCarouselBinding fragmentCarouselBinding6 = this.binding;
                if (fragmentCarouselBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding6 = null;
                }
                fragmentCarouselBinding6.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.carousel_edit_text_enabled_denza));
                FragmentCarouselBinding fragmentCarouselBinding7 = this.binding;
                if (fragmentCarouselBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding7 = null;
                }
                fragmentCarouselBinding7.itemAction.setBackgroundResource(R.drawable.ic_dialog_btn_ok_inuse_denza);
            } else {
                FragmentCarouselBinding fragmentCarouselBinding8 = this.binding;
                if (fragmentCarouselBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding8 = null;
                }
                fragmentCarouselBinding8.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.textColorSelected));
            }
        } else {
            FragmentCarouselBinding fragmentCarouselBinding9 = this.binding;
            if (fragmentCarouselBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding9 = null;
            }
            fragmentCarouselBinding9.itemAction.setText(R.string.str_use_now);
            FragmentCarouselBinding fragmentCarouselBinding10 = this.binding;
            if (fragmentCarouselBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding10 = null;
            }
            fragmentCarouselBinding10.itemAction.setEnabled(true);
            FragmentCarouselBinding fragmentCarouselBinding11 = this.binding;
            if (fragmentCarouselBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding11 = null;
            }
            fragmentCarouselBinding11.itemEdit.setSelected(true);
            if (AutoUtils.INSTANCE.getModelType() == 3) {
                FragmentCarouselBinding fragmentCarouselBinding12 = this.binding;
                if (fragmentCarouselBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding12 = null;
                }
                fragmentCarouselBinding12.itemEdit.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.carousel_edit_text_denza));
                FragmentCarouselBinding fragmentCarouselBinding13 = this.binding;
                if (fragmentCarouselBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding13 = null;
                }
                fragmentCarouselBinding13.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.carousel_edit_text_denza));
                FragmentCarouselBinding fragmentCarouselBinding14 = this.binding;
                if (fragmentCarouselBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding14 = null;
                }
                fragmentCarouselBinding14.itemAction.setBackgroundResource(R.drawable.ic_dialog_btn_ok_denza);
            } else {
                FragmentCarouselBinding fragmentCarouselBinding15 = this.binding;
                if (fragmentCarouselBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentCarouselBinding15 = null;
                }
                fragmentCarouselBinding15.itemAction.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.white));
            }
        }
        this.list.clear();
        ArrayList<CarouselPicture> carouselRes = getViewModel().getCarouselRes();
        this.list.addAll(carouselRes);
        AutoWallpaper autoWallpaper2 = this.autoWallpaper;
        if (autoWallpaper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autoWallpaper");
            autoWallpaper2 = null;
        }
        if (autoWallpaper2.getRsId() != DBUtils.INSTANCE.getCurrentWallpaperId() && carouselRes.size() < 10 && -1 != DBUtils.INSTANCE.getCurrentWallpaperId()) {
            this.list.add(new CarouselPicture("add", "add", "add", "add", false, null, 48, null));
        }
        CarouselAdapter carouselAdapter = this.adapter;
        if (carouselAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAdapter = null;
        }
        carouselAdapter.setData((ArrayList) this.list);
        int carouselTime = getViewModel().getCarouselTime();
        if (carouselTime == 60000) {
            string = StringUtils.getString(R.string.str_carousel_popup_min);
        } else if (carouselTime == 3600000) {
            string = StringUtils.getString(R.string.str_carousel_popup_hour);
        } else {
            string = StringUtils.getString(R.string.str_carousel_popup_day);
        }
        FragmentCarouselBinding fragmentCarouselBinding16 = this.binding;
        if (fragmentCarouselBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCarouselBinding = fragmentCarouselBinding16;
        }
        fragmentCarouselBinding.itemCarouselTime.setText(string);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(EventBusBean event) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getEventType(), EventString.REBUILDE) && event.getEventResult() == 0) {
            initViews();
            initData();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private final void applyRudder() {
        FragmentCarouselBinding fragmentCarouselBinding = null;
        if (AutoUtils.INSTANCE.isRightRudder() && ScreenUtils.isLandscape()) {
            ConstraintSet constraintSet = new ConstraintSet();
            FragmentCarouselBinding fragmentCarouselBinding2 = this.binding;
            if (fragmentCarouselBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding2 = null;
            }
            constraintSet.clone(fragmentCarouselBinding2.getRoot());
            FragmentCarouselBinding fragmentCarouselBinding3 = this.binding;
            if (fragmentCarouselBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding3 = null;
            }
            constraintSet.clear(fragmentCarouselBinding3.itemEdit.getId(), 7);
            FragmentCarouselBinding fragmentCarouselBinding4 = this.binding;
            if (fragmentCarouselBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding4 = null;
            }
            constraintSet.connect(fragmentCarouselBinding4.itemEdit.getId(), 1, 0, 1, 16);
            FragmentCarouselBinding fragmentCarouselBinding5 = this.binding;
            if (fragmentCarouselBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding5 = null;
            }
            constraintSet.clear(fragmentCarouselBinding5.itemClose.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding6 = this.binding;
            if (fragmentCarouselBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding6 = null;
            }
            constraintSet.connect(fragmentCarouselBinding6.itemClose.getId(), 2, 0, 2, 50);
            FragmentCarouselBinding fragmentCarouselBinding7 = this.binding;
            if (fragmentCarouselBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding7 = null;
            }
            constraintSet.clear(fragmentCarouselBinding7.itemCarouselTips.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding8 = this.binding;
            if (fragmentCarouselBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding8 = null;
            }
            int id = fragmentCarouselBinding8.itemCarouselTips.getId();
            FragmentCarouselBinding fragmentCarouselBinding9 = this.binding;
            if (fragmentCarouselBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding9 = null;
            }
            constraintSet.connect(id, 2, fragmentCarouselBinding9.itemCarouselTime.getId(), 1);
            FragmentCarouselBinding fragmentCarouselBinding10 = this.binding;
            if (fragmentCarouselBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding10 = null;
            }
            constraintSet.clear(fragmentCarouselBinding10.itemCarouselTime.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding11 = this.binding;
            if (fragmentCarouselBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding11 = null;
            }
            int id2 = fragmentCarouselBinding11.itemCarouselTime.getId();
            FragmentCarouselBinding fragmentCarouselBinding12 = this.binding;
            if (fragmentCarouselBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding12 = null;
            }
            constraintSet.connect(id2, 2, fragmentCarouselBinding12.itemClose.getId(), 1, 6);
            FragmentCarouselBinding fragmentCarouselBinding13 = this.binding;
            if (fragmentCarouselBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding13 = null;
            }
            constraintSet.applyTo(fragmentCarouselBinding13.getRoot());
        }
        if (AutoUtils.INSTANCE.isRightRudder() && !ScreenUtils.isLandscape()) {
            ConstraintSet constraintSet2 = new ConstraintSet();
            FragmentCarouselBinding fragmentCarouselBinding14 = this.binding;
            if (fragmentCarouselBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding14 = null;
            }
            constraintSet2.clone(fragmentCarouselBinding14.getRoot());
            FragmentCarouselBinding fragmentCarouselBinding15 = this.binding;
            if (fragmentCarouselBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding15 = null;
            }
            constraintSet2.clear(fragmentCarouselBinding15.itemEdit.getId(), 7);
            FragmentCarouselBinding fragmentCarouselBinding16 = this.binding;
            if (fragmentCarouselBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding16 = null;
            }
            constraintSet2.connect(fragmentCarouselBinding16.itemEdit.getId(), 1, 0, 1, 25);
            FragmentCarouselBinding fragmentCarouselBinding17 = this.binding;
            if (fragmentCarouselBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding17 = null;
            }
            constraintSet2.clear(fragmentCarouselBinding17.itemClose.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding18 = this.binding;
            if (fragmentCarouselBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding18 = null;
            }
            constraintSet2.connect(fragmentCarouselBinding18.itemClose.getId(), 2, 0, 2, 14);
            FragmentCarouselBinding fragmentCarouselBinding19 = this.binding;
            if (fragmentCarouselBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding19 = null;
            }
            constraintSet2.clear(fragmentCarouselBinding19.itemCarouselTips.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding20 = this.binding;
            if (fragmentCarouselBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding20 = null;
            }
            int id3 = fragmentCarouselBinding20.itemCarouselTips.getId();
            FragmentCarouselBinding fragmentCarouselBinding21 = this.binding;
            if (fragmentCarouselBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding21 = null;
            }
            constraintSet2.connect(id3, 2, fragmentCarouselBinding21.itemCarouselTime.getId(), 1);
            FragmentCarouselBinding fragmentCarouselBinding22 = this.binding;
            if (fragmentCarouselBinding22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding22 = null;
            }
            constraintSet2.clear(fragmentCarouselBinding22.itemCarouselTime.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding23 = this.binding;
            if (fragmentCarouselBinding23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding23 = null;
            }
            constraintSet2.connect(fragmentCarouselBinding23.itemCarouselTime.getId(), 2, 0, 2, 22);
            FragmentCarouselBinding fragmentCarouselBinding24 = this.binding;
            if (fragmentCarouselBinding24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding24 = null;
            }
            constraintSet2.applyTo(fragmentCarouselBinding24.getRoot());
        }
        if (ScreenUtils.isLandscape() && this.layoutDirection) {
            ConstraintSet constraintSet3 = new ConstraintSet();
            FragmentCarouselBinding fragmentCarouselBinding25 = this.binding;
            if (fragmentCarouselBinding25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding25 = null;
            }
            constraintSet3.clone(fragmentCarouselBinding25.getRoot());
            FragmentCarouselBinding fragmentCarouselBinding26 = this.binding;
            if (fragmentCarouselBinding26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding26 = null;
            }
            constraintSet3.clear(fragmentCarouselBinding26.itemEdit.getId(), 7);
            FragmentCarouselBinding fragmentCarouselBinding27 = this.binding;
            if (fragmentCarouselBinding27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding27 = null;
            }
            constraintSet3.connect(fragmentCarouselBinding27.itemEdit.getId(), 2, 0, 2, 50);
            FragmentCarouselBinding fragmentCarouselBinding28 = this.binding;
            if (fragmentCarouselBinding28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding28 = null;
            }
            constraintSet3.clear(fragmentCarouselBinding28.itemClose.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding29 = this.binding;
            if (fragmentCarouselBinding29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding29 = null;
            }
            constraintSet3.connect(fragmentCarouselBinding29.itemClose.getId(), 1, 0, 1, 16);
            FragmentCarouselBinding fragmentCarouselBinding30 = this.binding;
            if (fragmentCarouselBinding30 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding30 = null;
            }
            constraintSet3.clear(fragmentCarouselBinding30.itemCarouselTips.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding31 = this.binding;
            if (fragmentCarouselBinding31 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding31 = null;
            }
            int id4 = fragmentCarouselBinding31.itemCarouselTips.getId();
            FragmentCarouselBinding fragmentCarouselBinding32 = this.binding;
            if (fragmentCarouselBinding32 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding32 = null;
            }
            constraintSet3.connect(id4, 1, fragmentCarouselBinding32.itemCarouselTime.getId(), 2);
            FragmentCarouselBinding fragmentCarouselBinding33 = this.binding;
            if (fragmentCarouselBinding33 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding33 = null;
            }
            constraintSet3.clear(fragmentCarouselBinding33.itemCarouselTime.getId(), 6);
            FragmentCarouselBinding fragmentCarouselBinding34 = this.binding;
            if (fragmentCarouselBinding34 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding34 = null;
            }
            int id5 = fragmentCarouselBinding34.itemCarouselTime.getId();
            FragmentCarouselBinding fragmentCarouselBinding35 = this.binding;
            if (fragmentCarouselBinding35 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding35 = null;
            }
            constraintSet3.connect(id5, 1, fragmentCarouselBinding35.itemClose.getId(), 2, 10);
            FragmentCarouselBinding fragmentCarouselBinding36 = this.binding;
            if (fragmentCarouselBinding36 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentCarouselBinding36 = null;
            }
            constraintSet3.applyTo(fragmentCarouselBinding36.getRoot());
        }
        if (!this.layoutDirection || ScreenUtils.isLandscape()) {
            return;
        }
        ConstraintSet constraintSet4 = new ConstraintSet();
        FragmentCarouselBinding fragmentCarouselBinding37 = this.binding;
        if (fragmentCarouselBinding37 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding37 = null;
        }
        constraintSet4.clone(fragmentCarouselBinding37.getRoot());
        FragmentCarouselBinding fragmentCarouselBinding38 = this.binding;
        if (fragmentCarouselBinding38 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding38 = null;
        }
        constraintSet4.clear(fragmentCarouselBinding38.itemEdit.getId(), 7);
        FragmentCarouselBinding fragmentCarouselBinding39 = this.binding;
        if (fragmentCarouselBinding39 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding39 = null;
        }
        constraintSet4.connect(fragmentCarouselBinding39.itemEdit.getId(), 2, 0, 2, 25);
        FragmentCarouselBinding fragmentCarouselBinding40 = this.binding;
        if (fragmentCarouselBinding40 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding40 = null;
        }
        constraintSet4.clear(fragmentCarouselBinding40.itemClose.getId(), 6);
        FragmentCarouselBinding fragmentCarouselBinding41 = this.binding;
        if (fragmentCarouselBinding41 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding41 = null;
        }
        constraintSet4.connect(fragmentCarouselBinding41.itemClose.getId(), 1, 0, 1, 14);
        FragmentCarouselBinding fragmentCarouselBinding42 = this.binding;
        if (fragmentCarouselBinding42 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding42 = null;
        }
        constraintSet4.clear(fragmentCarouselBinding42.itemCarouselTips.getId(), 6);
        FragmentCarouselBinding fragmentCarouselBinding43 = this.binding;
        if (fragmentCarouselBinding43 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding43 = null;
        }
        int id6 = fragmentCarouselBinding43.itemCarouselTips.getId();
        FragmentCarouselBinding fragmentCarouselBinding44 = this.binding;
        if (fragmentCarouselBinding44 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding44 = null;
        }
        constraintSet4.connect(id6, 1, fragmentCarouselBinding44.itemCarouselTime.getId(), 2, 12);
        FragmentCarouselBinding fragmentCarouselBinding45 = this.binding;
        if (fragmentCarouselBinding45 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding45 = null;
        }
        constraintSet4.clear(fragmentCarouselBinding45.itemCarouselTime.getId(), 6);
        FragmentCarouselBinding fragmentCarouselBinding46 = this.binding;
        if (fragmentCarouselBinding46 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentCarouselBinding46 = null;
        }
        constraintSet4.connect(fragmentCarouselBinding46.itemCarouselTime.getId(), 1, 0, 1, 39);
        FragmentCarouselBinding fragmentCarouselBinding47 = this.binding;
        if (fragmentCarouselBinding47 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentCarouselBinding = fragmentCarouselBinding47;
        }
        constraintSet4.applyTo(fragmentCarouselBinding.getRoot());
    }

    /* JADX INFO: compiled from: CarouselFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/byd/automultipletheme/fragment/CarouselFragment$Companion;", "", "()V", "TAG", "", "showDialog", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "autoWallpaper", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void showDialog(FragmentManager fragmentManager, AutoWallpaper autoWallpaper) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(autoWallpaper, "autoWallpaper");
            CarouselFragment carouselFragment = new CarouselFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(CarouselFragment.TAG, autoWallpaper);
            carouselFragment.setArguments(bundle);
            carouselFragment.show(fragmentManager, CarouselFragment.TAG);
        }
    }
}
