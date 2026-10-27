package com.byd.overseaappstore.ui.view.fragment.manage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentManagementBinding;
import com.byd.overseaappstore.databinding.IncludeTopBarMergeBinding;
import com.byd.overseaappstore.ui.view.adapter.ManageVpAdapter;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.byd.overseaappstore.ui.vm.ManagementViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Objects;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.fj;
import spi.gk;
import spi.l;
import spi.zh;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/manage/ManagementFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentManagementBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ManagementFragment extends Hilt_ManagementFragment<FragmentManagementBinding> {
    public static final /* synthetic */ int l = 0;

    @Nullable
    public IncludeTopBarMergeBinding h;

    @NotNull
    public final Lazy i;

    @NotNull
    public final Lazy j;
    public boolean k;

    public static final class a implements TabLayout.OnTabSelectedListener {
        public a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public final void onTabReselected(@Nullable TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public final void onTabSelected(@Nullable TabLayout.Tab tab) {
            ImageView imageView;
            int i;
            TabLayout.TabView tabView;
            TabLayout.TabView tabView2;
            SkinAppCompatTextView skinAppCompatTextView;
            TabLayout.TabView tabView3;
            TextView textView;
            if (tab != null && (tabView3 = tab.view) != null && (textView = (TextView) tabView3.findViewById(R.id.tv_management_title_text)) != null) {
                textView.setTextAppearance(R.style.ManagementTabTextSelected);
            }
            if (tab != null && (tabView2 = tab.view) != null && (skinAppCompatTextView = (SkinAppCompatTextView) tabView2.findViewById(R.id.tv_management_title_text)) != null) {
                skinAppCompatTextView.setStyleTextColor(R.color.management_tab_selected);
            }
            ImageView imageView2 = (tab == null || (tabView = tab.view) == null) ? null : (ImageView) tabView.findViewById(R.id.iv_management_title_indicator);
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            Integer numValueOf = tab != null ? Integer.valueOf(tab.getPosition()) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                imageView = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon);
                if (imageView == null) {
                    return;
                } else {
                    i = R.drawable.ic_download_selected;
                }
            } else {
                if (numValueOf != null && numValueOf.intValue() == 1) {
                    ImageView imageView3 = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon);
                    if (imageView3 != null) {
                        imageView3.setImageResource(R.drawable.ic_update_selected);
                    }
                    View viewFindViewById = tab.view.findViewById(R.id.v_management_red_point);
                    if (viewFindViewById != null) {
                        viewFindViewById.setVisibility(4);
                    }
                    ManagementFragment.this.k = true;
                    return;
                }
                if (numValueOf == null || numValueOf.intValue() != 2 || (imageView = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon)) == null) {
                    return;
                } else {
                    i = R.drawable.ic_uninstall_selected;
                }
            }
            imageView.setImageResource(i);
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public final void onTabUnselected(@Nullable TabLayout.Tab tab) {
            ImageView imageView;
            int i;
            TabLayout.TabView tabView;
            TabLayout.TabView tabView2;
            SkinAppCompatTextView skinAppCompatTextView;
            TabLayout.TabView tabView3;
            TextView textView;
            if (tab != null && (tabView3 = tab.view) != null && (textView = (TextView) tabView3.findViewById(R.id.tv_management_title_text)) != null) {
                textView.setTextAppearance(R.style.ManagementTabTextUnSelected);
            }
            if (tab != null && (tabView2 = tab.view) != null && (skinAppCompatTextView = (SkinAppCompatTextView) tabView2.findViewById(R.id.tv_management_title_text)) != null) {
                skinAppCompatTextView.setStyleTextColor(R.color.management_tab_unselected);
            }
            ImageView imageView2 = (tab == null || (tabView = tab.view) == null) ? null : (ImageView) tabView.findViewById(R.id.iv_management_title_indicator);
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            Integer numValueOf = tab != null ? Integer.valueOf(tab.getPosition()) : null;
            if (numValueOf != null && numValueOf.intValue() == 0) {
                imageView = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon);
                if (imageView == null) {
                    return;
                } else {
                    i = R.drawable.ic_download_unselected;
                }
            } else if (numValueOf != null && numValueOf.intValue() == 1) {
                imageView = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon);
                if (imageView == null) {
                    return;
                } else {
                    i = R.drawable.ic_update_unselected;
                }
            } else if (numValueOf == null || numValueOf.intValue() != 2 || (imageView = (ImageView) tab.view.findViewById(R.id.iv_management_title_icon)) == null) {
                return;
            } else {
                i = R.drawable.ic_uninstall_unselected;
            }
            imageView.setImageResource(i);
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$observeData$1", f = "ManagementFragment.kt", i = {}, l = {165}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$observeData$1$1", f = "ManagementFragment.kt", i = {}, l = {167}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ ManagementFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$b$a$a, reason: collision with other inner class name */
            public static final class C0106a<T> implements FlowCollector {
                public final /* synthetic */ ManagementFragment a;

                public C0106a(ManagementFragment managementFragment) {
                    this.a = managementFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    int i;
                    TabLayout tabLayout;
                    TabLayout.Tab tabAt;
                    TabLayout.TabView tabView;
                    TabLayout tabLayout2;
                    TabLayout.Tab tabAt2;
                    TabLayout.TabView tabView2;
                    boolean zBooleanValue = ((Boolean) obj).booleanValue();
                    ManagementFragment managementFragment = this.a;
                    if (!managementFragment.k) {
                        View viewFindViewById = null;
                        if (zBooleanValue) {
                            FragmentManagementBinding fragmentManagementBinding = (FragmentManagementBinding) managementFragment.a;
                            if (fragmentManagementBinding != null && (tabLayout2 = fragmentManagementBinding.d) != null && (tabAt2 = tabLayout2.getTabAt(1)) != null && (tabView2 = tabAt2.view) != null) {
                                viewFindViewById = tabView2.findViewById(R.id.v_management_red_point);
                            }
                            if (viewFindViewById != null) {
                                i = 0;
                                viewFindViewById.setVisibility(i);
                            }
                        } else {
                            FragmentManagementBinding fragmentManagementBinding2 = (FragmentManagementBinding) managementFragment.a;
                            if (fragmentManagementBinding2 != null && (tabLayout = fragmentManagementBinding2.d) != null && (tabAt = tabLayout.getTabAt(1)) != null && (tabView = tabAt.view) != null) {
                                viewFindViewById = tabView.findViewById(R.id.v_management_red_point);
                            }
                            if (viewFindViewById != null) {
                                i = 4;
                                viewFindViewById.setVisibility(i);
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ManagementFragment managementFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = managementFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.b, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.a;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ct.a(spi.e.c("updateFinishTag managementViewModel.hashCode:", ((ManagementViewModel) this.b.j.getValue()).hashCode()), new Object[0]);
                    StateFlow<Boolean> stateFlow = ((ManagementViewModel) this.b.j.getValue()).g;
                    C0106a c0106a = new C0106a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0106a, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }
        }

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return ManagementFragment.this.new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ManagementFragment managementFragment = ManagementFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(managementFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(managementFragment, state, aVar, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public ManagementFragment() {
        final Function0 function0 = null;
        this.i = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = function0;
                if (function02 != null && (creationExtras = (CreationExtras) function02.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = this.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function02.invoke();
            }
        });
        this.j = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(ManagementViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function03 = function0;
                if (function03 != null && (creationExtras = (CreationExtras) function03.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM19viewModels$lambda1 = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM19viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM19viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.ManagementFragment$special$$inlined$viewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM19viewModels$lambda1 = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM19viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM19viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    defaultViewModelProviderFactory = this.getDefaultViewModelProviderFactory();
                }
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "(owner as? HasDefaultVie…tViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentManagementBinding fragmentManagementBindingA = FragmentManagementBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentManagementBindingA, "inflate(layoutInflater)");
        this.h = IncludeTopBarMergeBinding.a(fragmentManagementBindingA.a);
        return fragmentManagementBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        TabLayout tabLayout;
        SkinImageView skinImageView;
        IncludeTopBarMergeBinding includeTopBarMergeBinding = this.h;
        if (includeTopBarMergeBinding != null && (skinImageView = includeTopBarMergeBinding.b) != null) {
            skinImageView.setOnClickListener(new fj(this, 15));
        }
        FragmentManagementBinding fragmentManagementBinding = (FragmentManagementBinding) this.a;
        if (fragmentManagementBinding == null || (tabLayout = fragmentManagementBinding.d) == null) {
            return;
        }
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new a());
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    @SuppressLint({"WrongConstant"})
    public final void d() {
        IncludeTopBarMergeBinding includeTopBarMergeBinding = this.h;
        SkinTextView skinTextView = includeTopBarMergeBinding != null ? includeTopBarMergeBinding.c : null;
        if (skinTextView != null) {
            skinTextView.setText(getString(R.string.management_fragment_manager_title));
        }
        FragmentManagementBinding fragmentManagementBinding = (FragmentManagementBinding) this.a;
        if (fragmentManagementBinding != null) {
            fragmentManagementBinding.e.setOffscreenPageLimit(2);
            fragmentManagementBinding.e.setAdapter(new ManageVpAdapter(this, CollectionsKt.listOf((Object[]) new Class[]{DownloadListFragment.class, UpdateListFragment.class, InstalledListFragment.class})));
            new TabLayoutMediator(fragmentManagementBinding.d, fragmentManagementBinding.e, new l(this)).attach();
            fragmentManagementBinding.b.setOnClickListener(new gk(this, 12));
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new b(null), 3, null);
        ManagementViewModel managementViewModel = (ManagementViewModel) this.j.getValue();
        LifecycleCoroutineScope coroutineScope = LifecycleOwnerKt.getLifecycleScope(this);
        Objects.requireNonNull(managementViewModel);
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new zh(managementViewModel, null), 3, null);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
        ConstraintLayout constraintLayout;
        FragmentManagementBinding fragmentManagementBinding;
        TabLayout tabLayout;
        FragmentManagementBinding fragmentManagementBinding2;
        SkinImageView skinImageView;
        VB vb = this.a;
        FragmentManagementBinding fragmentManagementBinding3 = (FragmentManagementBinding) vb;
        if (fragmentManagementBinding3 == null || (constraintLayout = fragmentManagementBinding3.c) == null || (fragmentManagementBinding = (FragmentManagementBinding) vb) == null || (tabLayout = fragmentManagementBinding.d) == null || (fragmentManagementBinding2 = (FragmentManagementBinding) vb) == null || (skinImageView = fragmentManagementBinding2.b) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(tabLayout.getId(), 6);
        constraintSet.clear(tabLayout.getId(), 3);
        constraintSet.connect(tabLayout.getId(), 7, skinImageView.getId(), 7, getResources().getDimensionPixelSize(R.dimen.dp_20));
        constraintSet.connect(tabLayout.getId(), 3, skinImageView.getId(), 4, getResources().getDimensionPixelSize(R.dimen.dp_20));
        constraintSet.applyTo(constraintLayout);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        MainViewModel mainViewModel = (MainViewModel) this.i.getValue();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        mainViewModel.b(contextRequireContext);
    }
}
