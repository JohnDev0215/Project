package com.byd.overseaappstore.ui.view.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.Space;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.FragmentKt;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentProtocolBinding;
import com.byd.overseaappstore.databinding.IncludeTopBarMergeBinding;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import com.byd.overseaappstore.ui.MainActivity;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import com.byd.overseaappstore.ui.view.dialog.ProtocolResetDialog;
import com.byd.overseaappstore.ui.view.fragment.ProtocolFragment;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.byd.overseaappstore.ui.vm.ProtocolViewModel;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.av;
import spi.e;
import spi.l;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/ProtocolFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentProtocolBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ProtocolFragment extends Hilt_ProtocolFragment<FragmentProtocolBinding> {
    public static final /* synthetic */ int n = 0;

    @NotNull
    public final NavArgsLazy h = new NavArgsLazy(Reflection.getOrCreateKotlinClass(ProtocolFragmentArgs.class), new Function0<Bundle>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$navArgs$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Bundle invoke() {
            Bundle arguments = this.getArguments();
            if (arguments != null) {
                return arguments;
            }
            StringBuilder sbK = e.k("Fragment ");
            sbK.append(this);
            sbK.append(" has null arguments");
            throw new IllegalStateException(sbK.toString());
        }
    });

    @Nullable
    public IncludeTopBarMergeBinding i;
    public int j;
    public int k;

    @NotNull
    public final Lazy l;

    @NotNull
    public final Lazy m;

    public static final class a extends WebViewClient {
        public final /* synthetic */ FragmentProtocolBinding b;

        /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$a$a, reason: collision with other inner class name */
        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$initView$1$1$1$onPageFinished$1", f = "ProtocolFragment.kt", i = {}, l = {120}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0063a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ FragmentProtocolBinding b;
            public final /* synthetic */ ProtocolFragment c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0063a(FragmentProtocolBinding fragmentProtocolBinding, ProtocolFragment protocolFragment, Continuation<? super C0063a> continuation) {
                super(2, continuation);
                this.b = fragmentProtocolBinding;
                this.c = protocolFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0063a(this.b, this.c, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C0063a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.a;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.a = 1;
                    if (DelayKt.delay(100L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                NestedScrollView nestedScrollView = this.b.f;
                ProtocolFragment protocolFragment = this.c;
                nestedScrollView.scrollTo(protocolFragment.j, protocolFragment.k);
                return Unit.INSTANCE;
            }
        }

        public a(FragmentProtocolBinding fragmentProtocolBinding) {
            this.b = fragmentProtocolBinding;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(@Nullable WebView webView, @Nullable String str) {
            if (webView != null) {
                ProtocolFragment protocolFragment = ProtocolFragment.this;
                av avVar = av.a;
                int i = protocolFragment.getResources().getConfiguration().uiMode;
                Objects.requireNonNull(avVar);
                boolean z = (i & 48) == 32;
                int i2 = ProtocolFragment.n;
                protocolFragment.j(webView, z);
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new C0063a(this.b, ProtocolFragment.this, null), 3, null);
            }
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$initView$1$1$2", f = "ProtocolFragment.kt", i = {}, l = {127}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;
        public final /* synthetic */ WebView b;
        public final /* synthetic */ ProtocolFragment c;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$initView$1$1$2$filePath$1", f = "ProtocolFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
            public final /* synthetic */ ProtocolFragment a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ProtocolFragment protocolFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.a = protocolFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.a, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:15:0x0066  */
            /* JADX WARN: Removed duplicated region for block: B:18:0x0093 A[Catch: all -> 0x00d1, TryCatch #0 {all -> 0x00d1, blocks: (B:3:0x004f, B:5:0x0057, B:16:0x0068, B:17:0x006d, B:18:0x0093, B:20:0x0097, B:23:0x00a1, B:25:0x00a6, B:31:0x00b2), top: B:46:0x004f }] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
                /*
                    Method dump skipped, instruction units count: 278
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment.b.a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(WebView webView, ProtocolFragment protocolFragment, Continuation<? super b> continuation) {
            super(2, continuation);
            this.b = webView;
            this.c = protocolFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.b, this.c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                a aVar = new a(this.c, null);
                this.a = 1;
                obj = BuildersKt.withContext(io2, aVar, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.b.loadUrl((String) obj);
            return Unit.INSTANCE;
        }
    }

    public ProtocolFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function0.invoke();
            }
        });
        final Function0 function02 = null;
        this.l = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(ProtocolViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function03 = function02;
                if (function03 != null && (creationExtras = (CreationExtras) function03.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM19viewModels$lambda1 = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM19viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM19viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$viewModels$default$5
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
        this.m = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function03 = function02;
                if (function03 != null && (creationExtras = (CreationExtras) function03.invoke()) != null) {
                    return creationExtras;
                }
                CreationExtras defaultViewModelCreationExtras = this.requireActivity().getDefaultViewModelCreationExtras();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                return defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$special$$inlined$activityViewModels$default$3
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
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentProtocolBinding fragmentProtocolBindingA = FragmentProtocolBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentProtocolBindingA, "inflate(layoutInflater)");
        this.i = IncludeTopBarMergeBinding.a(fragmentProtocolBindingA.a);
        return fragmentProtocolBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        SkinImageView skinImageView;
        final FragmentProtocolBinding fragmentProtocolBinding = (FragmentProtocolBinding) this.a;
        if (fragmentProtocolBinding != null) {
            String str = i().a;
            if (Intrinsics.areEqual(str, "PROTOCOL_FRAG_TYPE_STARTUP")) {
                final int i = 0;
                fragmentProtocolBinding.j.setOnClickListener(new View.OnClickListener(this) { // from class: spi.gl
                    public final /* synthetic */ ProtocolFragment b;

                    {
                        this.b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i) {
                            case 0:
                                ProtocolFragment this$0 = this.b;
                                int i2 = ProtocolFragment.n;
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                SettingUtil settingUtil = SettingUtil.a;
                                Context contextRequireContext = this$0.requireContext();
                                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                                settingUtil.b(contextRequireContext, true);
                                Context context = this$0.requireContext();
                                Intrinsics.checkNotNullExpressionValue(context, "requireContext()");
                                Intrinsics.checkNotNullParameter(context, "context");
                                ((Number) BuildersKt__BuildersKt.runBlocking$default(null, new fp(context, 3, null), 1, null)).intValue();
                                FragmentActivity activity = this$0.getActivity();
                                Objects.requireNonNull(activity, "null cannot be cast to non-null type com.byd.overseaappstore.ui.MainActivity");
                                ((MainActivity) activity).o("YY", false);
                                break;
                            default:
                                ProtocolFragment this$02 = this.b;
                                int i3 = ProtocolFragment.n;
                                Intrinsics.checkNotNullParameter(this$02, "this$0");
                                FragmentKt.findNavController(this$02).navigateUp();
                                break;
                        }
                    }
                });
                fragmentProtocolBinding.i.setOnClickListener(new View.OnClickListener(this) { // from class: spi.fl
                    public final /* synthetic */ ProtocolFragment b;

                    {
                        this.b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i) {
                            case 0:
                                ProtocolFragment this$0 = this.b;
                                int i2 = ProtocolFragment.n;
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                this$0.requireActivity().finish();
                                break;
                            default:
                                ProtocolFragment this$02 = this.b;
                                int i3 = ProtocolFragment.n;
                                Intrinsics.checkNotNullParameter(this$02, "this$0");
                                ProtocolResetDialog protocolResetDialog = new ProtocolResetDialog();
                                String title = this$02.getResources().getString(R.string.protocol_dialog_end_button_reset);
                                Intrinsics.checkNotNullExpressionValue(title, "resources.getString(R.st…_dialog_end_button_reset)");
                                Intrinsics.checkNotNullParameter(title, "title");
                                protocolResetDialog.f = title;
                                String normalText = this$02.getResources().getString(R.string.protocol_dialog_reset_content);
                                Intrinsics.checkNotNullExpressionValue(normalText, "resources.getString(R.st…col_dialog_reset_content)");
                                Intrinsics.checkNotNullParameter(normalText, "normalText");
                                protocolResetDialog.g = normalText;
                                String startButtonText = this$02.getResources().getString(R.string.protocol_dialog_end_button_reset);
                                Intrinsics.checkNotNullExpressionValue(startButtonText, "resources.getString(R.st…_dialog_end_button_reset)");
                                Intrinsics.checkNotNullParameter(startButtonText, "startButtonText");
                                protocolResetDialog.h = startButtonText;
                                String endButtonText = this$02.getResources().getString(R.string.tv_setting_dialog_cancel);
                                Intrinsics.checkNotNullExpressionValue(endButtonText, "resources.getString(R.st…tv_setting_dialog_cancel)");
                                Intrinsics.checkNotNullParameter(endButtonText, "endButtonText");
                                protocolResetDialog.i = endButtonText;
                                jl block = new jl(this$02);
                                Intrinsics.checkNotNullParameter(block, "block");
                                protocolResetDialog.e = block;
                                FragmentManager supportFragmentManager = this$02.requireActivity().getSupportFragmentManager();
                                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
                                protocolResetDialog.show(supportFragmentManager, ProtocolResetDialog.class.getName());
                                break;
                        }
                    }
                });
                fragmentProtocolBinding.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: spi.hl
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        FragmentProtocolBinding this_apply = fragmentProtocolBinding;
                        int i2 = ProtocolFragment.n;
                        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
                        this_apply.j.setEnabled(z);
                        this_apply.h.setEnabled(z);
                        this_apply.h.setStyleTextColor(z ? R.color.protocol_positive_button_text : R.color.protocol_positive_button_text_not_enable);
                    }
                });
                requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback() { // from class: com.byd.overseaappstore.ui.view.fragment.ProtocolFragment$initListener$1$4
                    {
                        super(true);
                    }

                    @Override // androidx.activity.OnBackPressedCallback
                    public final void handleOnBackPressed() {
                        this.a.requireActivity().finish();
                    }
                });
            } else if (Intrinsics.areEqual(str, "PROTOCOL_FRAG_TYPE_SETTING")) {
                IncludeTopBarMergeBinding includeTopBarMergeBinding = this.i;
                final int i2 = 1;
                if (includeTopBarMergeBinding != null && (skinImageView = includeTopBarMergeBinding.b) != null) {
                    skinImageView.setOnClickListener(new View.OnClickListener(this) { // from class: spi.gl
                        public final /* synthetic */ ProtocolFragment b;

                        {
                            this.b = this;
                        }

                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            switch (i2) {
                                case 0:
                                    ProtocolFragment this$0 = this.b;
                                    int i22 = ProtocolFragment.n;
                                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                                    SettingUtil settingUtil = SettingUtil.a;
                                    Context contextRequireContext = this$0.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                                    settingUtil.b(contextRequireContext, true);
                                    Context context = this$0.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(context, "requireContext()");
                                    Intrinsics.checkNotNullParameter(context, "context");
                                    ((Number) BuildersKt__BuildersKt.runBlocking$default(null, new fp(context, 3, null), 1, null)).intValue();
                                    FragmentActivity activity = this$0.getActivity();
                                    Objects.requireNonNull(activity, "null cannot be cast to non-null type com.byd.overseaappstore.ui.MainActivity");
                                    ((MainActivity) activity).o("YY", false);
                                    break;
                                default:
                                    ProtocolFragment this$02 = this.b;
                                    int i3 = ProtocolFragment.n;
                                    Intrinsics.checkNotNullParameter(this$02, "this$0");
                                    FragmentKt.findNavController(this$02).navigateUp();
                                    break;
                            }
                        }
                    });
                }
                fragmentProtocolBinding.k.setOnClickListener(new View.OnClickListener(this) { // from class: spi.fl
                    public final /* synthetic */ ProtocolFragment b;

                    {
                        this.b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i2) {
                            case 0:
                                ProtocolFragment this$0 = this.b;
                                int i22 = ProtocolFragment.n;
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                this$0.requireActivity().finish();
                                break;
                            default:
                                ProtocolFragment this$02 = this.b;
                                int i3 = ProtocolFragment.n;
                                Intrinsics.checkNotNullParameter(this$02, "this$0");
                                ProtocolResetDialog protocolResetDialog = new ProtocolResetDialog();
                                String title = this$02.getResources().getString(R.string.protocol_dialog_end_button_reset);
                                Intrinsics.checkNotNullExpressionValue(title, "resources.getString(R.st…_dialog_end_button_reset)");
                                Intrinsics.checkNotNullParameter(title, "title");
                                protocolResetDialog.f = title;
                                String normalText = this$02.getResources().getString(R.string.protocol_dialog_reset_content);
                                Intrinsics.checkNotNullExpressionValue(normalText, "resources.getString(R.st…col_dialog_reset_content)");
                                Intrinsics.checkNotNullParameter(normalText, "normalText");
                                protocolResetDialog.g = normalText;
                                String startButtonText = this$02.getResources().getString(R.string.protocol_dialog_end_button_reset);
                                Intrinsics.checkNotNullExpressionValue(startButtonText, "resources.getString(R.st…_dialog_end_button_reset)");
                                Intrinsics.checkNotNullParameter(startButtonText, "startButtonText");
                                protocolResetDialog.h = startButtonText;
                                String endButtonText = this$02.getResources().getString(R.string.tv_setting_dialog_cancel);
                                Intrinsics.checkNotNullExpressionValue(endButtonText, "resources.getString(R.st…tv_setting_dialog_cancel)");
                                Intrinsics.checkNotNullParameter(endButtonText, "endButtonText");
                                protocolResetDialog.i = endButtonText;
                                jl block = new jl(this$02);
                                Intrinsics.checkNotNullParameter(block, "block");
                                protocolResetDialog.e = block;
                                FragmentManager supportFragmentManager = this$02.requireActivity().getSupportFragmentManager();
                                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
                                protocolResetDialog.show(supportFragmentManager, ProtocolResetDialog.class.getName());
                                break;
                        }
                    }
                });
            }
            fragmentProtocolBinding.f.setOnScrollChangeListener(new l(this));
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        ConstraintLayout constraintLayout;
        SkinTextView skinTextView;
        FragmentProtocolBinding fragmentProtocolBinding = (FragmentProtocolBinding) this.a;
        if (fragmentProtocolBinding != null) {
            String str = i().a;
            if (Intrinsics.areEqual(str, "PROTOCOL_FRAG_TYPE_STARTUP")) {
                fragmentProtocolBinding.e.setVisibility(0);
                fragmentProtocolBinding.c.setVisibility(0);
                IncludeTopBarMergeBinding includeTopBarMergeBinding = this.i;
                SkinImageView skinImageView = includeTopBarMergeBinding != null ? includeTopBarMergeBinding.b : null;
                if (skinImageView != null) {
                    skinImageView.setVisibility(4);
                }
                fragmentProtocolBinding.b.setChecked(false);
                fragmentProtocolBinding.j.setEnabled(false);
                fragmentProtocolBinding.h.setEnabled(false);
                fragmentProtocolBinding.h.setStyleTextColor(R.color.protocol_positive_button_text_not_enable);
                FragmentProtocolBinding fragmentProtocolBinding2 = (FragmentProtocolBinding) this.a;
                if (fragmentProtocolBinding2 == null || (constraintLayout = fragmentProtocolBinding2.a) == null) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding?.root ?: return");
                IncludeTopBarMergeBinding includeTopBarMergeBinding2 = this.i;
                if (includeTopBarMergeBinding2 == null || (skinTextView = includeTopBarMergeBinding2.c) == null) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(skinTextView, "topBarBinding?.tvIncludeText ?: return");
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.clear(skinTextView.getId(), 6);
                constraintSet.clear(skinTextView.getId(), 7);
                constraintSet.connect(skinTextView.getId(), 6, 0, 6);
                constraintSet.connect(skinTextView.getId(), 7, 0, 7);
                constraintSet.applyTo(constraintLayout);
            } else if (Intrinsics.areEqual(str, "PROTOCOL_FRAG_TYPE_SETTING")) {
                fragmentProtocolBinding.d.setVisibility(0);
            }
            IncludeTopBarMergeBinding includeTopBarMergeBinding3 = this.i;
            SkinTextView skinTextView2 = includeTopBarMergeBinding3 != null ? includeTopBarMergeBinding3.c : null;
            if (skinTextView2 != null) {
                vm vmVar = vm.a;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                skinTextView2.setText(vmVar.e(contextRequireContext, R.string.protocol_dialog_title));
            }
            WebView webView = fragmentProtocolBinding.l;
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);
            webView.setBackgroundColor(0);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new a(fragmentProtocolBinding));
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new b(webView, this, null), 3, null);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
        k();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
        k();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ProtocolFragmentArgs i() {
        return (ProtocolFragmentArgs) this.h.getValue();
    }

    public final void j(WebView webView, boolean z) {
        av avVar = av.a;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        if (avVar.d(contextRequireContext)) {
            webView.loadUrl("javascript:load_denza();");
        } else {
            webView.loadUrl(z ? "javascript:load_night();" : "javascript:load_day();");
        }
    }

    public final void k() {
        ConstraintLayout constraintLayout;
        FragmentProtocolBinding fragmentProtocolBinding;
        SkinView skinView;
        FragmentProtocolBinding fragmentProtocolBinding2;
        SkinView skinView2;
        FragmentProtocolBinding fragmentProtocolBinding3;
        Space space;
        if (Intrinsics.areEqual(i().a, "PROTOCOL_FRAG_TYPE_STARTUP")) {
            VB vb = this.a;
            FragmentProtocolBinding fragmentProtocolBinding4 = (FragmentProtocolBinding) vb;
            if (fragmentProtocolBinding4 == null || (constraintLayout = fragmentProtocolBinding4.a) == null || (fragmentProtocolBinding = (FragmentProtocolBinding) vb) == null || (skinView = fragmentProtocolBinding.j) == null || (fragmentProtocolBinding2 = (FragmentProtocolBinding) vb) == null || (skinView2 = fragmentProtocolBinding2.i) == null || (fragmentProtocolBinding3 = (FragmentProtocolBinding) vb) == null || (space = fragmentProtocolBinding3.g) == null) {
                return;
            }
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(skinView.getId(), 1);
            constraintSet.clear(skinView.getId(), 2);
            constraintSet.connect(skinView.getId(), 1, space.getId(), 2);
            constraintSet.connect(skinView.getId(), 2, 0, 2);
            constraintSet.clear(skinView2.getId(), 1);
            constraintSet.clear(skinView2.getId(), 2);
            constraintSet.connect(skinView2.getId(), 1, 0, 1);
            constraintSet.connect(skinView2.getId(), 2, space.getId(), 1);
            constraintSet.clear(space.getId(), 1);
            constraintSet.clear(space.getId(), 2);
            constraintSet.connect(space.getId(), 1, skinView2.getId(), 2);
            constraintSet.connect(space.getId(), 2, skinView.getId(), 1);
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(@NotNull Configuration newConfig) {
        FragmentProtocolBinding fragmentProtocolBinding;
        WebView webView;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        av avVar = av.a;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        if (avVar.d(contextRequireContext) || (fragmentProtocolBinding = (FragmentProtocolBinding) this.a) == null || (webView = fragmentProtocolBinding.l) == null) {
            return;
        }
        int i = newConfig.uiMode;
        Objects.requireNonNull(avVar);
        j(webView, (i & 48) == 32);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        MainViewModel mainViewModel = (MainViewModel) this.m.getValue();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        mainViewModel.b(contextRequireContext);
    }
}
