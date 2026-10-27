package com.byd.overseaappstore.ui.view.fragment.manage;

import android.content.Context;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentInstalledListBinding;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.InstalledItemUiState;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareInstalled;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat;
import com.byd.overseaappstore.ui.view.adapter.InstalledAdapter;
import com.byd.overseaappstore.ui.vm.InstalledViewModel;
import java.util.List;
import java.util.Objects;
import kotlin.KotlinNothingValueException;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ct;
import spi.hi;
import spi.q9;
import spi.s9;
import spi.t9;
import spi.xf;
import spi.xi;
import spi.y9;
import spi.yf;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/manage/InstalledListFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentInstalledListBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class InstalledListFragment extends Hilt_InstalledListFragment<FragmentInstalledListBinding> {
    public static final /* synthetic */ int j = 0;

    @NotNull
    public final Lazy h;

    @NotNull
    public final InstalledAdapter i;

    public static final class a extends Lambda implements Function3<SoftwareInstalled, Integer, Integer, Unit> {
        public a() {
            super(3);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(SoftwareInstalled softwareInstalled, Integer num, Integer num2) {
            SoftwareInstalled softwareInstalled2 = softwareInstalled;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(softwareInstalled2, "softwareInstalled");
            InstalledViewModel installedViewModelI = InstalledListFragment.i(InstalledListFragment.this);
            Objects.requireNonNull(installedViewModelI);
            Intrinsics.checkNotNullParameter(softwareInstalled2, "softwareInstalled");
            s9 s9Var = installedViewModelI.d;
            a2 bizSrc = new a2((byte) 0, (byte) 0, (byte) 0, 7, null);
            Objects.requireNonNull(s9Var);
            Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
            Intrinsics.checkNotNullParameter(softwareInstalled2, "softwareInstalled");
            q9 q9Var = s9Var.a;
            Objects.requireNonNull(q9Var);
            Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
            Intrinsics.checkNotNullParameter(softwareInstalled2, "softwareInstalled");
            y9 y9Var = q9Var.a;
            Objects.requireNonNull(y9Var);
            Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
            Intrinsics.checkNotNullParameter(softwareInstalled2, "softwareInstalled");
            ct.a("executorHciWorkLine handleUninstall pkn:" + softwareInstalled2.getPackageName() + " bizSrc:" + bizSrc, new Object[0]);
            Object carrier = y9Var.b.parseToWorkCarrier(y9Var.b.parseToSoftwareOptParam(softwareInstalled2));
            ExecutorHciWorkPreTreat executorHciWorkPreTreat = y9Var.a;
            Objects.requireNonNull(executorHciWorkPreTreat);
            Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
            Intrinsics.checkNotNullParameter(carrier, "carrier");
            t9<T> t9Var = executorHciWorkPreTreat.a;
            if (t9Var != 0) {
                t9Var.i(bizSrc, (T) carrier);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1", f = "InstalledListFragment.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1$1", f = "InstalledListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ InstalledListFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$b$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1$1$1", f = "InstalledListFragment.kt", i = {}, l = {67}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0099a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ InstalledListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$b$a$a$a, reason: collision with other inner class name */
                public static final class C0100a<T> implements FlowCollector {
                    public final /* synthetic */ InstalledListFragment a;

                    public C0100a(InstalledListFragment installedListFragment) {
                        this.a = installedListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        if (zBooleanValue) {
                            ct.a(hi.e("sequenceDispatcher updateFetchFinishCollect fetchIt tag:", zBooleanValue), new Object[0]);
                            InstalledViewModel installedViewModelI = InstalledListFragment.i(this.a);
                            LifecycleCoroutineScope coroutineScope = LifecycleOwnerKt.getLifecycleScope(this.a);
                            Objects.requireNonNull(installedViewModelI);
                            Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new xf(installedViewModelI, null), 3, null);
                        } else {
                            ct.a(hi.e("sequenceDispatcher updateFetchFinishCollect not yet tag:", zBooleanValue), new Object[0]);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0099a(InstalledListFragment installedListFragment, Continuation<? super C0099a> continuation) {
                    super(2, continuation);
                    this.b = installedListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0099a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0099a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow<Boolean> updateFetchFinishFlow = InstalledListFragment.i(this.b).e.getUpdateFetchFinishDispatcher().getUpdateFetchFinishFlow();
                        C0100a c0100a = new C0100a(this.b);
                        this.a = 1;
                        if (updateFetchFinishFlow.collect(c0100a, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$b$a$b, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1$1$2", f = "InstalledListFragment.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0101b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ InstalledListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$b$a$b$a, reason: collision with other inner class name */
                public static final class C0102a<T> implements FlowCollector {
                    public final /* synthetic */ InstalledListFragment a;

                    public C0102a(InstalledListFragment installedListFragment) {
                        this.a = installedListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        ErrorEmptyUiState errorEmptyUiState = (ErrorEmptyUiState) obj;
                        InstalledListFragment installedListFragment = this.a;
                        int i = InstalledListFragment.j;
                        FragmentInstalledListBinding fragmentInstalledListBinding = (FragmentInstalledListBinding) installedListFragment.a;
                        if (fragmentInstalledListBinding != null) {
                            if (errorEmptyUiState.getVisible()) {
                                fragmentInstalledListBinding.b.setVisibility(0);
                                fragmentInstalledListBinding.c.setImageResource(errorEmptyUiState.getPicId());
                                fragmentInstalledListBinding.e.setText(errorEmptyUiState.getTipId());
                            } else {
                                fragmentInstalledListBinding.b.setVisibility(8);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0101b(InstalledListFragment installedListFragment, Continuation<? super C0101b> continuation) {
                    super(2, continuation);
                    this.b = installedListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0101b(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0101b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow<ErrorEmptyUiState> stateFlow = InstalledListFragment.i(this.b).l;
                        C0102a c0102a = new C0102a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0102a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1$1$3", f = "InstalledListFragment.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ InstalledListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$b$a$c$a, reason: collision with other inner class name */
                public static final class C0103a<T> implements FlowCollector {
                    public final /* synthetic */ InstalledListFragment a;

                    public C0103a(InstalledListFragment installedListFragment) {
                        this.a = installedListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        InstalledItemUiState installedItemUiState = (InstalledItemUiState) obj;
                        InstalledListFragment installedListFragment = this.a;
                        int i = InstalledListFragment.j;
                        FragmentInstalledListBinding fragmentInstalledListBinding = (FragmentInstalledListBinding) installedListFragment.a;
                        if (fragmentInstalledListBinding != null) {
                            if (installedItemUiState.getVisible()) {
                                fragmentInstalledListBinding.d.setVisibility(0);
                                installedListFragment.i.submitList((List) installedItemUiState.getData());
                            } else {
                                fragmentInstalledListBinding.d.setVisibility(8);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public c(InstalledListFragment installedListFragment, Continuation<? super c> continuation) {
                    super(2, continuation);
                    this.b = installedListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new c(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow<InstalledItemUiState> stateFlow = InstalledListFragment.i(this.b).j;
                        C0103a c0103a = new C0103a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0103a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1$1$4", f = "InstalledListFragment.kt", i = {}, l = {120}, m = "invokeSuspend", n = {}, s = {})
            public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ InstalledListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$b$a$d$a, reason: collision with other inner class name */
                public static final class C0104a<T> implements FlowCollector {
                    public final /* synthetic */ InstalledListFragment a;

                    public C0104a(InstalledListFragment installedListFragment) {
                        this.a = installedListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        InstalledViewModel installedViewModelI = InstalledListFragment.i(this.a);
                        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
                        Context contextRequireContext = this.a.requireContext();
                        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                        Objects.requireNonNull(installedViewModelI);
                        Object objLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new yf(installedViewModelI, contextRequireContext, null), 3, null);
                        if (objLaunch$default != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            objLaunch$default = Unit.INSTANCE;
                        }
                        return objLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLaunch$default : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public d(InstalledListFragment installedListFragment, Continuation<? super d> continuation) {
                    super(2, continuation);
                    this.b = installedListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new d(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SharedFlow<String> languageDataFlow = InstalledListFragment.i(this.b).g.getLanguageDataFlow();
                        C0104a c0104a = new C0104a(this.b);
                        this.a = 1;
                        if (languageDataFlow.collect(c0104a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1$1$5", f = "InstalledListFragment.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {})
            public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ InstalledListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$b$a$e$a, reason: collision with other inner class name */
                @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$observeData$1$1$5$1", f = "InstalledListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                public static final class C0105a extends SuspendLambda implements Function2<IUUState, Continuation<? super Unit>, Object> {
                    public /* synthetic */ Object a;
                    public final /* synthetic */ InstalledListFragment b;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0105a(InstalledListFragment installedListFragment, Continuation<? super C0105a> continuation) {
                        super(2, continuation);
                        this.b = installedListFragment;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        C0105a c0105a = new C0105a(this.b, continuation);
                        c0105a.a = obj;
                        return c0105a;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public final Object mo6invoke(IUUState iUUState, Continuation<? super Unit> continuation) {
                        return ((C0105a) create(iUUState, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        ResultKt.throwOnFailure(obj);
                        IUUState iuuState = (IUUState) this.a;
                        InstalledViewModel installedViewModelI = InstalledListFragment.i(this.b);
                        LifecycleCoroutineScope coroutineScope = LifecycleOwnerKt.getLifecycleScope(this.b);
                        Objects.requireNonNull(installedViewModelI);
                        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                        Intrinsics.checkNotNullParameter(iuuState, "iuuState");
                        int state = iuuState.getState();
                        if (state == 6 || state == 7) {
                            ct.a(xi.f("iuu INSTALLED or UNINSTALLED fetch operateState:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                            Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new xf(installedViewModelI, null), 3, null);
                        } else {
                            ct.a(xi.f("iuu other state skip it operateState:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public e(InstalledListFragment installedListFragment, Continuation<? super e> continuation) {
                    super(2, continuation);
                    this.b = installedListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new e(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SharedFlow<IUUState> sharedFlow = InstalledListFragment.i(this.b).h;
                        C0105a c0105a = new C0105a(this.b, null);
                        this.a = 1;
                        if (FlowKt.collectLatest(sharedFlow, c0105a, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(InstalledListFragment installedListFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = installedListFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.b, continuation);
                aVar.a = obj;
                return aVar;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.a;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0099a(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0101b(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new c(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new d(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new e(this.b, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return InstalledListFragment.this.new b(continuation);
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
                InstalledListFragment installedListFragment = InstalledListFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(installedListFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(installedListFragment, state, aVar, this) == coroutine_suspended) {
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

    public InstalledListFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$special$$inlined$viewModels$default$2
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
        this.h = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(InstalledViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.InstalledListFragment$special$$inlined$viewModels$default$5
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
        InstalledAdapter installedAdapter = new InstalledAdapter();
        installedAdapter.a(R.id.tv_uninstall_app_button, new a());
        this.i = installedAdapter;
    }

    public static final InstalledViewModel i(InstalledListFragment installedListFragment) {
        return (InstalledViewModel) installedListFragment.h.getValue();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentInstalledListBinding fragmentInstalledListBindingA = FragmentInstalledListBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentInstalledListBindingA, "inflate(layoutInflater)");
        return fragmentInstalledListBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        FragmentInstalledListBinding fragmentInstalledListBinding = (FragmentInstalledListBinding) this.a;
        if (fragmentInstalledListBinding != null) {
            RecyclerView recyclerView = fragmentInstalledListBinding.d;
            recyclerView.setLayoutManager(recyclerView.getResources().getConfiguration().orientation == 1 ? new LinearLayoutManager(requireContext()) : new GridLayoutManager(requireContext(), 2));
            recyclerView.setAdapter(this.i);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new b(null), 3, null);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
    }
}
