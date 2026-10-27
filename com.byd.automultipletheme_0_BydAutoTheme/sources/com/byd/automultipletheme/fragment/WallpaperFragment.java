package com.byd.automultipletheme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.activity.CommonActivity;
import com.byd.automultipletheme.adapter.WallpaperAdapter;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.bean.PreviewImage;
import com.byd.automultipletheme.bean.Wallpaper;
import com.byd.automultipletheme.common.AssetsUtils;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LimitedClickUtils;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.adapter.AutoBaseAdapter;
import com.byd.automultipletheme.common.adapter.GridSpaceItemDecoration;
import com.byd.automultipletheme.common.base.BaseDialogFragment;
import com.byd.automultipletheme.common.db.DBUtils;
import com.byd.automultipletheme.databinding.FragmentWallpaperBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import com.byd.automultipletheme.fragment.CarouselFragment;
import com.byd.automultipletheme.fragment.LegitimateDialogFragment;
import com.byd.automultipletheme.vm.CarouselAddViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: WallpaperFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\u001a\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001f"}, d2 = {"Lcom/byd/automultipletheme/fragment/WallpaperFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/WallpaperAdapter;", "binding", "Lcom/byd/automultipletheme/databinding/FragmentWallpaperBinding;", "viewModel", "Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/CarouselAddViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "onResume", "onViewCreated", "view", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WallpaperFragment extends Fragment {
    private WallpaperAdapter adapter;
    private FragmentWallpaperBinding binding;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public WallpaperFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.WallpaperFragment$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarouselAddViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.WallpaperFragment$special$$inlined$viewModels$default$1
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
        FragmentWallpaperBinding fragmentWallpaperBindingInflate = FragmentWallpaperBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentWallpaperBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentWallpaperBindingInflate;
        if (fragmentWallpaperBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWallpaperBindingInflate = null;
        }
        ConstraintLayout root = fragmentWallpaperBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(Const.ACTION_AUTOTHEMES_OPEN);
        intent.setPackage("com.android.launcher3");
        Utils.getApp().sendBroadcast(intent);
        initViews();
    }

    private final void initViews() {
        FragmentWallpaperBinding fragmentWallpaperBinding = null;
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            FragmentWallpaperBinding fragmentWallpaperBinding2 = this.binding;
            if (fragmentWallpaperBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWallpaperBinding2 = null;
            }
            fragmentWallpaperBinding2.ScrollViewBg.setBackground(ContextCompat.getDrawable(Utils.getApp(), R.drawable.img_main_bg_denza));
        }
        FragmentWallpaperBinding fragmentWallpaperBinding3 = this.binding;
        if (fragmentWallpaperBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWallpaperBinding3 = null;
        }
        RecyclerView recyclerView = fragmentWallpaperBinding3.recyclerView;
        this.adapter = new WallpaperAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        WallpaperAdapter wallpaperAdapter = this.adapter;
        if (wallpaperAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperAdapter = null;
        }
        recyclerView.setAdapter(wallpaperAdapter);
        if (recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration(new GridSpaceItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.dp_24), getResources().getDimensionPixelSize(R.dimen.dp_24), getResources().getDimensionPixelSize(R.dimen.dp_36)));
        }
        WallpaperAdapter wallpaperAdapter2 = this.adapter;
        if (wallpaperAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperAdapter2 = null;
        }
        wallpaperAdapter2.setData(AssetsUtils.INSTANCE.getAssetsAutoWallpaper());
        WallpaperAdapter wallpaperAdapter3 = this.adapter;
        if (wallpaperAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperAdapter3 = null;
        }
        wallpaperAdapter3.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperFragment$initViews$1$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view, Integer num) {
                invoke(view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view, int i) {
                Intrinsics.checkNotNullParameter(view, "view");
                LogUtils.INSTANCE.d(view, Integer.valueOf(i));
                WallpaperAdapter wallpaperAdapter4 = this.this$0.adapter;
                if (wallpaperAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    wallpaperAdapter4 = null;
                }
                AutoWallpaper item = wallpaperAdapter4.getItem(i);
                if (item != null) {
                    WallpaperFragment wallpaperFragment = this.this$0;
                    if (item.getRsType() == 1) {
                        CarouselFragment.Companion companion = CarouselFragment.Companion;
                        FragmentManager childFragmentManager = wallpaperFragment.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                        companion.showDialog(childFragmentManager, item);
                        return;
                    }
                    Intent intent = new Intent(wallpaperFragment.getActivity(), (Class<?>) CommonActivity.class);
                    intent.putExtra(IntentString.DATA_STR, item);
                    intent.putExtra(IntentString.DATA_TYPE, 2);
                    wallpaperFragment.startActivity(intent);
                }
            }
        });
        WallpaperAdapter wallpaperAdapter4 = this.adapter;
        if (wallpaperAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperAdapter4 = null;
        }
        wallpaperAdapter4.addOnItemChildClickListener(new Function3<AutoBaseAdapter<AutoWallpaper>, View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperFragment$initViews$1$2
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(AutoBaseAdapter<AutoWallpaper> autoBaseAdapter, View view, Integer num) {
                invoke(autoBaseAdapter, view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(AutoBaseAdapter<AutoWallpaper> autoBaseAdapter, View view, int i) {
                Intrinsics.checkNotNullParameter(autoBaseAdapter, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
                if (LimitedClickUtils.INSTANCE.isEnoughMem()) {
                    if (LimitedClickUtils.INSTANCE.canClick()) {
                        WallpaperAdapter wallpaperAdapter5 = this.this$0.adapter;
                        if (wallpaperAdapter5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            wallpaperAdapter5 = null;
                        }
                        final AutoWallpaper item = wallpaperAdapter5.getItem(i);
                        if (item != null) {
                            final WallpaperFragment wallpaperFragment = this.this$0;
                            if (item.getRsId() == DBUtils.INSTANCE.getCurrentWallpaperId()) {
                                ToastUtils.INSTANCE.showShort(R.string.str_using);
                                return;
                            }
                            if (item.getRsType() == 1) {
                                BaseDialogFragment.Companion companion = BaseDialogFragment.INSTANCE;
                                FragmentManager childFragmentManager = wallpaperFragment.getChildFragmentManager();
                                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                                String string = wallpaperFragment.getString(R.string.str_dialog_tips);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.str_dialog_tips)");
                                String string2 = wallpaperFragment.getString(R.string.str_dialog_tips_ok);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.str_dialog_tips_ok)");
                                String string3 = wallpaperFragment.getString(R.string.str_dialog_tips_cancel);
                                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.str_dialog_tips_cancel)");
                                companion.showDialog(childFragmentManager, item, string, string2, string3, new Function0<Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperFragment$initViews$1$2$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                            wallpaperFragment.getViewModel().saveCarouselResources(wallpaperFragment.getViewModel().getCarouselRes());
                                            LogUtils.INSTANCE.i("preset wallpaper ...");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        ApplyingWindowFragment.Companion companion2 = ApplyingWindowFragment.INSTANCE;
                                        FragmentManager childFragmentManager2 = wallpaperFragment.getChildFragmentManager();
                                        Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                                        companion2.showDialog(childFragmentManager2, item);
                                    }
                                });
                                return;
                            }
                            if (item.getRsType() == 15) {
                                if (Intrinsics.areEqual(Const.ALLOW_ALWAYS, SPUtils.getInstance().getString(Const.ACTION_LEGITIMATE, Const.NOT_ALLOW))) {
                                    BaseDialogFragment.Companion companion2 = BaseDialogFragment.INSTANCE;
                                    FragmentManager childFragmentManager2 = wallpaperFragment.getChildFragmentManager();
                                    Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                                    String string4 = wallpaperFragment.getString(R.string.str_dialog_tips);
                                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.str_dialog_tips)");
                                    String string5 = wallpaperFragment.getString(R.string.str_dialog_tips_ok);
                                    Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.str_dialog_tips_ok)");
                                    String string6 = wallpaperFragment.getString(R.string.str_dialog_tips_cancel);
                                    Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.str_dialog_tips_cancel)");
                                    companion2.showDialog(childFragmentManager2, item, string4, string5, string6, new Function0<Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperFragment$initViews$1$2$1$2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                            ApplyingWindowFragment.Companion companion3 = ApplyingWindowFragment.INSTANCE;
                                            FragmentManager childFragmentManager3 = wallpaperFragment.getChildFragmentManager();
                                            Intrinsics.checkNotNullExpressionValue(childFragmentManager3, "childFragmentManager");
                                            companion3.showDialog(childFragmentManager3, item);
                                        }
                                    });
                                    return;
                                }
                                LegitimateDialogFragment.Companion companion3 = LegitimateDialogFragment.INSTANCE;
                                FragmentManager childFragmentManager3 = wallpaperFragment.getChildFragmentManager();
                                Intrinsics.checkNotNullExpressionValue(childFragmentManager3, "childFragmentManager");
                                companion3.showDialog(childFragmentManager3, item);
                                return;
                            }
                            ApplyingWindowFragment.Companion companion4 = ApplyingWindowFragment.INSTANCE;
                            FragmentManager childFragmentManager4 = wallpaperFragment.getChildFragmentManager();
                            Intrinsics.checkNotNullExpressionValue(childFragmentManager4, "childFragmentManager");
                            companion4.showDialog(childFragmentManager4, item);
                            return;
                        }
                        return;
                    }
                    return;
                }
                ToastUtils.INSTANCE.showShort(R.string.str_storage_full);
            }
        });
        FragmentWallpaperBinding fragmentWallpaperBinding4 = this.binding;
        if (fragmentWallpaperBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWallpaperBinding = fragmentWallpaperBinding4;
        }
        fragmentWallpaperBinding.appVersion.setText(AppUtils.getAppVersionName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(EventBusBean event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getEventType(), EventString.LEGITIMATE_DIALOG)) {
            final AutoWallpaper autoWallpaper = new AutoWallpaper(new PreviewImage(CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper-land/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/morning.png"}), CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/morning.png "}), new Wallpaper(CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper-land/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper-land/morning.png"}), CollectionsKt.listOf((Object[]) new String[]{"file:///android_asset/res/wallpaper/110000011/wallpaper/sunrise.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/night.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/evening.png", "file:///android_asset/res/wallpaper/110000011/wallpaper/morning.png "}))), 110000011, "沙漠风暴", 15);
            BaseDialogFragment.Companion companion = BaseDialogFragment.INSTANCE;
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
            String string = getString(R.string.str_dialog_tips);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.str_dialog_tips)");
            String string2 = getString(R.string.str_dialog_tips_ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.str_dialog_tips_ok)");
            String string3 = getString(R.string.str_dialog_tips_cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.str_dialog_tips_cancel)");
            companion.showDialog(childFragmentManager, autoWallpaper, string, string2, string3, new Function0<Unit>() { // from class: com.byd.automultipletheme.fragment.WallpaperFragment.onEvent.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    ApplyingWindowFragment.Companion companion2 = ApplyingWindowFragment.INSTANCE;
                    FragmentManager childFragmentManager2 = WallpaperFragment.this.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "childFragmentManager");
                    companion2.showDialog(childFragmentManager2, autoWallpaper);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
