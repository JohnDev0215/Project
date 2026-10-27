package com.byd.automultipletheme.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewpager.widget.ViewPager;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.adapter.CarouselAddAdapter;
import com.byd.automultipletheme.bean.CarouselPicture;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.databinding.FragmentAddCarouselBinding;
import com.byd.automultipletheme.vm.CarouselAddViewModel;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: CarouselAddFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\tH\u0002J\b\u0010\u001a\u001a\u00020\tH\u0002J\b\u0010\u001b\u001a\u00020\tH\u0003J$\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\tH\u0016J\b\u0010%\u001a\u00020\tH\u0016J\u001a\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006)"}, d2 = {"Lcom/byd/automultipletheme/fragment/CarouselAddFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/CarouselAddAdapter;", "binding", "Lcom/byd/automultipletheme/databinding/FragmentAddCarouselBinding;", "callback", "Lkotlin/Function0;", "", "carouselAddPhotoFragment", "Lcom/byd/automultipletheme/fragment/CarouselAddPhotoFragment;", "carouselAddWallpaperFragment", "Lcom/byd/automultipletheme/fragment/CarouselAddWallpaperFragment;", "layoutDirection", "", IntentString.DATA_LIST, "", "Landroidx/fragment/app/Fragment;", "viewModel", "Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "applyRudder", "initData", "initViews", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onStart", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselAddFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "CarouselAddFragment";
    private CarouselAddAdapter adapter;
    private FragmentAddCarouselBinding binding;
    private Function0<Unit> callback;
    private CarouselAddPhotoFragment carouselAddPhotoFragment;
    private CarouselAddWallpaperFragment carouselAddWallpaperFragment;
    private boolean layoutDirection;
    private List<Fragment> list;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public CarouselAddFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarouselAddViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment$special$$inlined$viewModels$default$1
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

    private final CarouselAddViewModel getViewModel() {
        return (CarouselAddViewModel) this.viewModel.getValue();
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
        FragmentAddCarouselBinding fragmentAddCarouselBindingInflate = FragmentAddCarouselBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentAddCarouselBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentAddCarouselBindingInflate;
        this.layoutDirection = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        applyRudder();
        FragmentAddCarouselBinding fragmentAddCarouselBinding = this.binding;
        if (fragmentAddCarouselBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding = null;
        }
        ConstraintLayout root = fragmentAddCarouselBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();
    }

    private final void initViews() {
        this.carouselAddWallpaperFragment = new CarouselAddWallpaperFragment();
        this.carouselAddPhotoFragment = new CarouselAddPhotoFragment();
        List<Fragment> list = this.list;
        CarouselAddWallpaperFragment carouselAddWallpaperFragment = this.carouselAddWallpaperFragment;
        FragmentAddCarouselBinding fragmentAddCarouselBinding = null;
        if (carouselAddWallpaperFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carouselAddWallpaperFragment");
            carouselAddWallpaperFragment = null;
        }
        list.add(carouselAddWallpaperFragment);
        List<Fragment> list2 = this.list;
        CarouselAddPhotoFragment carouselAddPhotoFragment = this.carouselAddPhotoFragment;
        if (carouselAddPhotoFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carouselAddPhotoFragment");
            carouselAddPhotoFragment = null;
        }
        list2.add(carouselAddPhotoFragment);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        this.adapter = new CarouselAddAdapter(childFragmentManager, (ArrayList) this.list);
        FragmentAddCarouselBinding fragmentAddCarouselBinding2 = this.binding;
        if (fragmentAddCarouselBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding2 = null;
        }
        ViewPager viewPager = fragmentAddCarouselBinding2.viewPager;
        CarouselAddAdapter carouselAddAdapter = this.adapter;
        if (carouselAddAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            carouselAddAdapter = null;
        }
        viewPager.setAdapter(carouselAddAdapter);
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentAddCarouselBinding fragmentAddCarouselBinding3 = this.binding;
            if (fragmentAddCarouselBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding3 = null;
            }
            fragmentAddCarouselBinding3.itemEdit.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.carousel_edit_text_denza));
        }
        FragmentAddCarouselBinding fragmentAddCarouselBinding4 = this.binding;
        if (fragmentAddCarouselBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding4 = null;
        }
        fragmentAddCarouselBinding4.itemClose.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarouselAddFragment.m63initViews$lambda2(this.f$0, view);
            }
        });
        FragmentAddCarouselBinding fragmentAddCarouselBinding5 = this.binding;
        if (fragmentAddCarouselBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding5 = null;
        }
        fragmentAddCarouselBinding5.itemEdit.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarouselAddFragment.m64initViews$lambda3(this.f$0, view);
            }
        });
        FragmentAddCarouselBinding fragmentAddCarouselBinding6 = this.binding;
        if (fragmentAddCarouselBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding6 = null;
        }
        TabLayout tabLayout = fragmentAddCarouselBinding6.tabLayout;
        FragmentAddCarouselBinding fragmentAddCarouselBinding7 = this.binding;
        if (fragmentAddCarouselBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding7 = null;
        }
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.ViewPagerOnTabSelectedListener(fragmentAddCarouselBinding7.viewPager) { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment.initViews.3
            @Override // com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener, com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                super.onTabSelected(tab);
            }
        });
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment$initViews$onGlobalLayoutListener$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                FragmentAddCarouselBinding fragmentAddCarouselBinding8 = this.this$0.binding;
                if (fragmentAddCarouselBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentAddCarouselBinding8 = null;
                }
                fragmentAddCarouselBinding8.tabLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                FragmentAddCarouselBinding fragmentAddCarouselBinding9 = this.this$0.binding;
                if (fragmentAddCarouselBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentAddCarouselBinding9 = null;
                }
                int tabCount = fragmentAddCarouselBinding9.tabLayout.getTabCount();
                for (int i = 0; i < tabCount; i++) {
                    FragmentAddCarouselBinding fragmentAddCarouselBinding10 = this.this$0.binding;
                    if (fragmentAddCarouselBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAddCarouselBinding10 = null;
                    }
                    TabLayout.Tab tabAt = fragmentAddCarouselBinding10.tabLayout.getTabAt(i);
                    TabLayout.TabView tabView = tabAt != null ? tabAt.view : null;
                    if (tabView != null) {
                        tabView.setLongClickable(false);
                    }
                    FragmentAddCarouselBinding fragmentAddCarouselBinding11 = this.this$0.binding;
                    if (fragmentAddCarouselBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentAddCarouselBinding11 = null;
                    }
                    TabLayout.Tab tabAt2 = fragmentAddCarouselBinding11.tabLayout.getTabAt(i);
                    TabLayout.TabView tabView2 = tabAt2 != null ? tabAt2.view : null;
                    if (tabView2 != null) {
                        tabView2.setTooltipText(null);
                    }
                }
            }
        };
        FragmentAddCarouselBinding fragmentAddCarouselBinding8 = this.binding;
        if (fragmentAddCarouselBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding8 = null;
        }
        fragmentAddCarouselBinding8.tabLayout.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        FragmentAddCarouselBinding fragmentAddCarouselBinding9 = this.binding;
        if (fragmentAddCarouselBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding9 = null;
        }
        ViewPager viewPager2 = fragmentAddCarouselBinding9.viewPager;
        FragmentAddCarouselBinding fragmentAddCarouselBinding10 = this.binding;
        if (fragmentAddCarouselBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentAddCarouselBinding = fragmentAddCarouselBinding10;
        }
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(fragmentAddCarouselBinding.tabLayout) { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment.initViews.4
            @Override // com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        getViewModel().getCountLiveData().observe(requireActivity(), new Observer() { // from class: com.byd.automultipletheme.fragment.CarouselAddFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarouselAddFragment.m65initViews$lambda4(this.f$0, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-2, reason: not valid java name */
    public static final void m63initViews$lambda2(CarouselAddFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.initData();
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-3, reason: not valid java name */
    public static final void m64initViews$lambda3(CarouselAddFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList arrayList = new ArrayList();
        Pair<List<CarouselPicture>, List<CarouselPicture>> value = this$0.getViewModel().getWallpaperSelectedPair().getValue();
        Intrinsics.checkNotNull(value);
        List<CarouselPicture> first = value.getFirst();
        if (this$0.getViewModel().getPhotoSelectedPair().getValue() == null) {
            Iterator<CarouselPicture> it = this$0.getViewModel().getCarouselRes().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                CarouselPicture next = it.next();
                String landLight = next.getLandLight();
                if (landLight != null && StringsKt.contains$default((CharSequence) landLight, (CharSequence) "wallpaper/110000010/land", false, 2, (Object) null)) {
                    arrayList.add(next);
                }
            }
            LogUtils.INSTANCE.i("photoList: ", arrayList);
        } else {
            Pair<List<CarouselPicture>, List<CarouselPicture>> value2 = this$0.getViewModel().getPhotoSelectedPair().getValue();
            Intrinsics.checkNotNull(value2);
            arrayList = (ArrayList) value2.getFirst();
        }
        this$0.getViewModel().saveCarouselRes(first, arrayList);
        this$0.getViewModel().setCountLiveData(first.size() + arrayList.size());
        EventBus.getDefault().post(new EventBusBean(EventString.REBUILDE, 0));
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViews$lambda-4, reason: not valid java name */
    public static final void m65initViews$lambda4(CarouselAddFragment this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogUtils.INSTANCE.i(num);
        FragmentAddCarouselBinding fragmentAddCarouselBinding = null;
        if (num != null && num.intValue() == 0) {
            FragmentAddCarouselBinding fragmentAddCarouselBinding2 = this$0.binding;
            if (fragmentAddCarouselBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentAddCarouselBinding = fragmentAddCarouselBinding2;
            }
            fragmentAddCarouselBinding.selectCountText.setText("");
            return;
        }
        FragmentAddCarouselBinding fragmentAddCarouselBinding3 = this$0.binding;
        if (fragmentAddCarouselBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentAddCarouselBinding = fragmentAddCarouselBinding3;
        }
        fragmentAddCarouselBinding.selectCountText.setText('(' + num + "/10)");
    }

    private final void initData() {
        getViewModel().setCountLiveData(getViewModel().getCarouselRes().size());
    }

    private final void applyRudder() {
        FragmentAddCarouselBinding fragmentAddCarouselBinding = null;
        if (AutoUtils.INSTANCE.isRightRudder() && ScreenUtils.isLandscape()) {
            ConstraintSet constraintSet = new ConstraintSet();
            FragmentAddCarouselBinding fragmentAddCarouselBinding2 = this.binding;
            if (fragmentAddCarouselBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding2 = null;
            }
            constraintSet.clone(fragmentAddCarouselBinding2.getRoot());
            FragmentAddCarouselBinding fragmentAddCarouselBinding3 = this.binding;
            if (fragmentAddCarouselBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding3 = null;
            }
            constraintSet.clear(fragmentAddCarouselBinding3.itemEdit.getId(), 7);
            FragmentAddCarouselBinding fragmentAddCarouselBinding4 = this.binding;
            if (fragmentAddCarouselBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding4 = null;
            }
            constraintSet.connect(fragmentAddCarouselBinding4.itemEdit.getId(), 1, 0, 1, 16);
            FragmentAddCarouselBinding fragmentAddCarouselBinding5 = this.binding;
            if (fragmentAddCarouselBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding5 = null;
            }
            constraintSet.clear(fragmentAddCarouselBinding5.itemClose.getId(), 6);
            FragmentAddCarouselBinding fragmentAddCarouselBinding6 = this.binding;
            if (fragmentAddCarouselBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding6 = null;
            }
            constraintSet.connect(fragmentAddCarouselBinding6.itemClose.getId(), 2, 0, 2, 50);
            FragmentAddCarouselBinding fragmentAddCarouselBinding7 = this.binding;
            if (fragmentAddCarouselBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding7 = null;
            }
            constraintSet.applyTo(fragmentAddCarouselBinding7.getRoot());
        }
        if (this.layoutDirection && ScreenUtils.isLandscape()) {
            ConstraintSet constraintSet2 = new ConstraintSet();
            FragmentAddCarouselBinding fragmentAddCarouselBinding8 = this.binding;
            if (fragmentAddCarouselBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding8 = null;
            }
            constraintSet2.clone(fragmentAddCarouselBinding8.getRoot());
            FragmentAddCarouselBinding fragmentAddCarouselBinding9 = this.binding;
            if (fragmentAddCarouselBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding9 = null;
            }
            constraintSet2.clear(fragmentAddCarouselBinding9.itemEdit.getId(), 7);
            FragmentAddCarouselBinding fragmentAddCarouselBinding10 = this.binding;
            if (fragmentAddCarouselBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding10 = null;
            }
            constraintSet2.connect(fragmentAddCarouselBinding10.itemEdit.getId(), 2, 0, 2, 50);
            FragmentAddCarouselBinding fragmentAddCarouselBinding11 = this.binding;
            if (fragmentAddCarouselBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding11 = null;
            }
            constraintSet2.clear(fragmentAddCarouselBinding11.itemClose.getId(), 6);
            FragmentAddCarouselBinding fragmentAddCarouselBinding12 = this.binding;
            if (fragmentAddCarouselBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding12 = null;
            }
            constraintSet2.connect(fragmentAddCarouselBinding12.itemClose.getId(), 1, 0, 1, 16);
            FragmentAddCarouselBinding fragmentAddCarouselBinding13 = this.binding;
            if (fragmentAddCarouselBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding13 = null;
            }
            constraintSet2.applyTo(fragmentAddCarouselBinding13.getRoot());
        }
        if (AutoUtils.INSTANCE.isRightRudder() && !ScreenUtils.isLandscape()) {
            ConstraintSet constraintSet3 = new ConstraintSet();
            FragmentAddCarouselBinding fragmentAddCarouselBinding14 = this.binding;
            if (fragmentAddCarouselBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding14 = null;
            }
            constraintSet3.clone(fragmentAddCarouselBinding14.getRoot());
            FragmentAddCarouselBinding fragmentAddCarouselBinding15 = this.binding;
            if (fragmentAddCarouselBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding15 = null;
            }
            constraintSet3.clear(fragmentAddCarouselBinding15.itemEdit.getId(), 7);
            FragmentAddCarouselBinding fragmentAddCarouselBinding16 = this.binding;
            if (fragmentAddCarouselBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding16 = null;
            }
            constraintSet3.connect(fragmentAddCarouselBinding16.itemEdit.getId(), 1, 0, 1, 25);
            FragmentAddCarouselBinding fragmentAddCarouselBinding17 = this.binding;
            if (fragmentAddCarouselBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding17 = null;
            }
            constraintSet3.clear(fragmentAddCarouselBinding17.itemClose.getId(), 6);
            FragmentAddCarouselBinding fragmentAddCarouselBinding18 = this.binding;
            if (fragmentAddCarouselBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding18 = null;
            }
            constraintSet3.connect(fragmentAddCarouselBinding18.itemClose.getId(), 2, 0, 2, 14);
            FragmentAddCarouselBinding fragmentAddCarouselBinding19 = this.binding;
            if (fragmentAddCarouselBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentAddCarouselBinding19 = null;
            }
            constraintSet3.applyTo(fragmentAddCarouselBinding19.getRoot());
        }
        if (!this.layoutDirection || ScreenUtils.isLandscape()) {
            return;
        }
        ConstraintSet constraintSet4 = new ConstraintSet();
        FragmentAddCarouselBinding fragmentAddCarouselBinding20 = this.binding;
        if (fragmentAddCarouselBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding20 = null;
        }
        constraintSet4.clone(fragmentAddCarouselBinding20.getRoot());
        FragmentAddCarouselBinding fragmentAddCarouselBinding21 = this.binding;
        if (fragmentAddCarouselBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding21 = null;
        }
        constraintSet4.clear(fragmentAddCarouselBinding21.itemEdit.getId(), 7);
        FragmentAddCarouselBinding fragmentAddCarouselBinding22 = this.binding;
        if (fragmentAddCarouselBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding22 = null;
        }
        constraintSet4.connect(fragmentAddCarouselBinding22.itemEdit.getId(), 2, 0, 2, 25);
        FragmentAddCarouselBinding fragmentAddCarouselBinding23 = this.binding;
        if (fragmentAddCarouselBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding23 = null;
        }
        constraintSet4.clear(fragmentAddCarouselBinding23.itemClose.getId(), 6);
        FragmentAddCarouselBinding fragmentAddCarouselBinding24 = this.binding;
        if (fragmentAddCarouselBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentAddCarouselBinding24 = null;
        }
        constraintSet4.connect(fragmentAddCarouselBinding24.itemClose.getId(), 1, 0, 1, 14);
        FragmentAddCarouselBinding fragmentAddCarouselBinding25 = this.binding;
        if (fragmentAddCarouselBinding25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentAddCarouselBinding = fragmentAddCarouselBinding25;
        }
        constraintSet4.applyTo(fragmentAddCarouselBinding.getRoot());
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getViewModel().getCountLiveData().removeObservers(requireActivity());
    }

    /* JADX INFO: compiled from: CarouselAddFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/byd/automultipletheme/fragment/CarouselAddFragment$Companion;", "", "()V", "TAG", "", "showDialog", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "callback", "Lkotlin/Function0;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void showDialog(FragmentManager fragmentManager, Function0<Unit> callback) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(callback, "callback");
            CarouselAddFragment carouselAddFragment = new CarouselAddFragment();
            carouselAddFragment.callback = callback;
            carouselAddFragment.show(fragmentManager, CarouselAddFragment.TAG);
        }
    }
}
