package com.byd.automultipletheme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.AppUtils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.activity.CommonActivity;
import com.byd.automultipletheme.adapter.ThemeAdapter;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LimitedClickUtils;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.adapter.AutoBaseAdapter;
import com.byd.automultipletheme.common.adapter.GridSpaceItemDecoration;
import com.byd.automultipletheme.databinding.FragmentMultipleThemeBinding;
import com.byd.automultipletheme.fragment.ApplyingWindowFragment;
import com.byd.automultipletheme.vm.MultipleThemeViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MultipleThemeFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u001a\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/byd/automultipletheme/fragment/MultipleThemeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/byd/automultipletheme/adapter/ThemeAdapter;", "binding", "Lcom/byd/automultipletheme/databinding/FragmentMultipleThemeBinding;", "viewModel", "Lcom/byd/automultipletheme/vm/MultipleThemeViewModel;", "getViewModel", "()Lcom/byd/automultipletheme/vm/MultipleThemeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "onViewCreated", "view", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MultipleThemeFragment extends Fragment {
    private ThemeAdapter adapter;
    private FragmentMultipleThemeBinding binding;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public MultipleThemeFragment() {
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.byd.automultipletheme.fragment.MultipleThemeFragment$viewModel$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MultipleThemeViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.automultipletheme.fragment.MultipleThemeFragment$special$$inlined$viewModels$default$1
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

    private final MultipleThemeViewModel getViewModel() {
        return (MultipleThemeViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMultipleThemeBinding fragmentMultipleThemeBindingInflate = FragmentMultipleThemeBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentMultipleThemeBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentMultipleThemeBindingInflate;
        if (fragmentMultipleThemeBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMultipleThemeBindingInflate = null;
        }
        ConstraintLayout root = fragmentMultipleThemeBindingInflate.getRoot();
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
        FragmentMultipleThemeBinding fragmentMultipleThemeBinding = this.binding;
        FragmentMultipleThemeBinding fragmentMultipleThemeBinding2 = null;
        if (fragmentMultipleThemeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMultipleThemeBinding = null;
        }
        fragmentMultipleThemeBinding.recyclerView.setItemAnimator(null);
        FragmentMultipleThemeBinding fragmentMultipleThemeBinding3 = this.binding;
        if (fragmentMultipleThemeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMultipleThemeBinding3 = null;
        }
        RecyclerView recyclerView = fragmentMultipleThemeBinding3.recyclerView;
        this.adapter = new ThemeAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ThemeAdapter themeAdapter = this.adapter;
        if (themeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            themeAdapter = null;
        }
        recyclerView.setAdapter(themeAdapter);
        recyclerView.addItemDecoration(new GridSpaceItemDecoration(2, getResources().getDimensionPixelSize(R.dimen.dp_24), getResources().getDimensionPixelSize(R.dimen.dp_24), getResources().getDimensionPixelSize(R.dimen.dp_36)));
        getViewModel().getLocalAutoThemeLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.byd.automultipletheme.fragment.MultipleThemeFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MultipleThemeFragment.m97onViewCreated$lambda2$lambda1(this.f$0, (List) obj);
            }
        });
        ThemeAdapter themeAdapter2 = this.adapter;
        if (themeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            themeAdapter2 = null;
        }
        themeAdapter2.addOnItemClickListener(new Function2<View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.MultipleThemeFragment$onViewCreated$1$2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(View view2, Integer num) {
                invoke(view2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(View view2, int i) {
                Intrinsics.checkNotNullParameter(view2, "view");
                try {
                    LogUtils.INSTANCE.d(view2, Integer.valueOf(i));
                    Intent intent = new Intent(this.this$0.getActivity(), (Class<?>) CommonActivity.class);
                    ThemeAdapter themeAdapter3 = this.this$0.adapter;
                    if (themeAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        themeAdapter3 = null;
                    }
                    intent.putExtra(IntentString.DATA_STR, themeAdapter3.getItem(i));
                    intent.putExtra(IntentString.DATA_TYPE, 0);
                    this.this$0.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ThemeAdapter themeAdapter3 = this.adapter;
        if (themeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            themeAdapter3 = null;
        }
        themeAdapter3.addOnItemChildClickListener(new Function3<AutoBaseAdapter<AutoTheme>, View, Integer, Unit>() { // from class: com.byd.automultipletheme.fragment.MultipleThemeFragment$onViewCreated$1$3
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(AutoBaseAdapter<AutoTheme> autoBaseAdapter, View view2, Integer num) {
                invoke(autoBaseAdapter, view2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(AutoBaseAdapter<AutoTheme> adapter, View view2, int i) {
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view2, "<anonymous parameter 1>");
                if (AutoUtils.INSTANCE.isPauseGear()) {
                    if (LimitedClickUtils.INSTANCE.isEnoughMem()) {
                        AutoTheme item = adapter.getItem(i);
                        if (item != null) {
                            MultipleThemeFragment multipleThemeFragment = this.this$0;
                            if (item.getRsId() == AutoUtils.INSTANCE.getLocalThemeId()) {
                                ToastUtils.INSTANCE.showShort(R.string.str_using);
                                return;
                            }
                            ApplyingWindowFragment.Companion companion = ApplyingWindowFragment.INSTANCE;
                            FragmentManager childFragmentManager = multipleThemeFragment.getChildFragmentManager();
                            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
                            companion.showDialog(childFragmentManager, item);
                            return;
                        }
                        return;
                    }
                    ToastUtils.INSTANCE.showShort(R.string.str_storage_full);
                    return;
                }
                ToastUtils.INSTANCE.showShort(R.string.str_safety_reminder);
            }
        });
        FragmentMultipleThemeBinding fragmentMultipleThemeBinding4 = this.binding;
        if (fragmentMultipleThemeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMultipleThemeBinding2 = fragmentMultipleThemeBinding4;
        }
        fragmentMultipleThemeBinding2.appVersion.setText(AppUtils.getAppVersionName());
        getViewModel().loadAutoThemeLiveData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2$lambda-1, reason: not valid java name */
    public static final void m97onViewCreated$lambda2$lambda1(MultipleThemeFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            ThemeAdapter themeAdapter = this$0.adapter;
            FragmentMultipleThemeBinding fragmentMultipleThemeBinding = null;
            if (themeAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                themeAdapter = null;
            }
            themeAdapter.setData((ArrayList) list);
            FragmentMultipleThemeBinding fragmentMultipleThemeBinding2 = this$0.binding;
            if (fragmentMultipleThemeBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMultipleThemeBinding2 = null;
            }
            fragmentMultipleThemeBinding2.appVersion.setVisibility(0);
            FragmentMultipleThemeBinding fragmentMultipleThemeBinding3 = this$0.binding;
            if (fragmentMultipleThemeBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentMultipleThemeBinding = fragmentMultipleThemeBinding3;
            }
            fragmentMultipleThemeBinding.appCopyright.setVisibility(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(EventBusBean event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.areEqual(event.getEventType(), EventString.SUCCESS);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
