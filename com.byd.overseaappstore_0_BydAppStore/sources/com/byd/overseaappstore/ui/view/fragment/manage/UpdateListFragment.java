package com.byd.overseaappstore.ui.view.fragment.manage;

import android.content.Context;
import android.widget.Space;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.byd.overseaappstore.databinding.FragmentUpdateListBinding;
import com.byd.overseaappstore.databinding.ItemUpdateAppBinding;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.UpgradeUiState;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.ui.view.adapter.UpdateAdapter;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatTextView;
import com.byd.overseaappstore.ui.view.dialog.UpdateDeleteDialog;
import com.byd.overseaappstore.ui.vm.UpgradeViewModel;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
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
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ct;
import spi.fj;
import spi.hu;
import spi.kg;
import spi.mu;
import spi.vm;
import spi.xh;
import spi.xi;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/manage/UpdateListFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentUpdateListBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class UpdateListFragment extends Hilt_UpdateListFragment<FragmentUpdateListBinding> {
    public static final /* synthetic */ int j = 0;

    @NotNull
    public final Lazy h;

    @NotNull
    public final UpdateAdapter i;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1", f = "UpdateListFragment.kt", i = {}, l = {143}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a, reason: collision with other inner class name */
        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1$1", f = "UpdateListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0107a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ UpdateListFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1$1$1", f = "UpdateListFragment.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0108a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ UpdateListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$a$a, reason: collision with other inner class name */
                public static final class C0109a<T> implements FlowCollector {
                    public final /* synthetic */ UpdateListFragment a;

                    public C0109a(UpdateListFragment updateListFragment) {
                        this.a = updateListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        Group group;
                        int i;
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        UpdateListFragment updateListFragment = this.a;
                        int i2 = UpdateListFragment.j;
                        FragmentUpdateListBinding fragmentUpdateListBinding = (FragmentUpdateListBinding) updateListFragment.a;
                        if (fragmentUpdateListBinding != null) {
                            if (zBooleanValue) {
                                group = fragmentUpdateListBinding.c;
                                i = 0;
                            } else {
                                group = fragmentUpdateListBinding.c;
                                i = 8;
                            }
                            group.setVisibility(i);
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0108a(UpdateListFragment updateListFragment, Continuation<? super C0108a> continuation) {
                    super(2, continuation);
                    this.b = updateListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0108a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0108a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        UpdateListFragment updateListFragment = this.b;
                        int i2 = UpdateListFragment.j;
                        StateFlow<Boolean> stateFlow = updateListFragment.i().k;
                        C0109a c0109a = new C0109a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0109a, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$b */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1$1$2", f = "UpdateListFragment.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {})
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ UpdateListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$b$a, reason: collision with other inner class name */
                public static final class C0110a<T> implements FlowCollector {
                    public final /* synthetic */ UpdateListFragment a;

                    public C0110a(UpdateListFragment updateListFragment) {
                        this.a = updateListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        ErrorEmptyUiState errorEmptyUiState = (ErrorEmptyUiState) obj;
                        UpdateListFragment updateListFragment = this.a;
                        int i = UpdateListFragment.j;
                        FragmentUpdateListBinding fragmentUpdateListBinding = (FragmentUpdateListBinding) updateListFragment.a;
                        if (fragmentUpdateListBinding != null) {
                            if (errorEmptyUiState.getVisible()) {
                                fragmentUpdateListBinding.b.setVisibility(0);
                                fragmentUpdateListBinding.f.setImageResource(errorEmptyUiState.getPicId());
                                fragmentUpdateListBinding.l.setText(errorEmptyUiState.getTipId());
                            } else {
                                fragmentUpdateListBinding.b.setVisibility(8);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(UpdateListFragment updateListFragment, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.b = updateListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.b, continuation);
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
                        UpdateListFragment updateListFragment = this.b;
                        int i2 = UpdateListFragment.j;
                        StateFlow<ErrorEmptyUiState> stateFlow = updateListFragment.i().o;
                        C0110a c0110a = new C0110a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0110a, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$c */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1$1$3", f = "UpdateListFragment.kt", i = {}, l = {179}, m = "invokeSuspend", n = {}, s = {})
            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ UpdateListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$c$a, reason: collision with other inner class name */
                public static final class C0111a<T> implements FlowCollector {
                    public final /* synthetic */ UpdateListFragment a;

                    public C0111a(UpdateListFragment updateListFragment) {
                        this.a = updateListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        UpgradeUiState upgradeUiState = (UpgradeUiState) obj;
                        UpdateListFragment updateListFragment = this.a;
                        int i = UpdateListFragment.j;
                        FragmentUpdateListBinding fragmentUpdateListBinding = (FragmentUpdateListBinding) updateListFragment.a;
                        if (fragmentUpdateListBinding != null) {
                            if (upgradeUiState.getVisible()) {
                                fragmentUpdateListBinding.h.setVisibility(0);
                                if (fragmentUpdateListBinding.h.isAnimating()) {
                                    ct.a("the ItemMovingAnimations is running not submitList", new Object[0]);
                                } else {
                                    updateListFragment.i.submitList((List) upgradeUiState.getData());
                                }
                            } else {
                                fragmentUpdateListBinding.h.setVisibility(8);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public c(UpdateListFragment updateListFragment, Continuation<? super c> continuation) {
                    super(2, continuation);
                    this.b = updateListFragment;
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
                        UpdateListFragment updateListFragment = this.b;
                        int i2 = UpdateListFragment.j;
                        StateFlow<UpgradeUiState> stateFlow = updateListFragment.i().m;
                        C0111a c0111a = new C0111a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0111a, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$d */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1$1$4", f = "UpdateListFragment.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {})
            public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ UpdateListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$d$a, reason: collision with other inner class name */
                @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1$1$4$1", f = "UpdateListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                public static final class C0112a extends SuspendLambda implements Function2<IUUState, Continuation<? super Unit>, Object> {
                    public /* synthetic */ Object a;
                    public final /* synthetic */ UpdateListFragment b;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0112a(UpdateListFragment updateListFragment, Continuation<? super C0112a> continuation) {
                        super(2, continuation);
                        this.b = updateListFragment;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        C0112a c0112a = new C0112a(this.b, continuation);
                        c0112a.a = obj;
                        return c0112a;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public final Object mo6invoke(IUUState iUUState, Continuation<? super Unit> continuation) {
                        return ((C0112a) create(iUUState, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        ResultKt.throwOnFailure(obj);
                        IUUState iuuState = (IUUState) this.a;
                        UpdateListFragment updateListFragment = this.b;
                        int i = UpdateListFragment.j;
                        UpgradeViewModel upgradeViewModelI = updateListFragment.i();
                        LifecycleCoroutineScope coroutineScope = LifecycleOwnerKt.getLifecycleScope(this.b);
                        Objects.requireNonNull(upgradeViewModelI);
                        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                        Intrinsics.checkNotNullParameter(iuuState, "iuuState");
                        int state = iuuState.getState();
                        if (state == 6 || state == 7) {
                            ct.a(xi.f("fetchUpgradeList iuu INSTALLED & UNINSTALLED fetch operateState:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                            upgradeViewModelI.c(coroutineScope);
                        } else {
                            ct.a(xi.f("fetchUpgradeList iuu other state skip it operateState:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public d(UpdateListFragment updateListFragment, Continuation<? super d> continuation) {
                    super(2, continuation);
                    this.b = updateListFragment;
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
                        UpdateListFragment updateListFragment = this.b;
                        int i2 = UpdateListFragment.j;
                        SharedFlow<IUUState> sharedFlow = updateListFragment.i().i;
                        C0112a c0112a = new C0112a(this.b, null);
                        this.a = 1;
                        if (FlowKt.collectLatest(sharedFlow, c0112a, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$e */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$observeData$1$1$5", f = "UpdateListFragment.kt", i = {}, l = {208}, m = "invokeSuspend", n = {}, s = {})
            public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ UpdateListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$a$a$e$a, reason: collision with other inner class name */
                public static final class C0113a<T> implements FlowCollector {
                    public final /* synthetic */ UpdateListFragment a;

                    public C0113a(UpdateListFragment updateListFragment) {
                        this.a = updateListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        UpdateListFragment updateListFragment = this.a;
                        int i = UpdateListFragment.j;
                        UpgradeViewModel upgradeViewModelI = updateListFragment.i();
                        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
                        Context contextRequireContext = this.a.requireContext();
                        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                        Objects.requireNonNull(upgradeViewModelI);
                        Object objLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new hu(upgradeViewModelI, contextRequireContext, null), 3, null);
                        if (objLaunch$default != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            objLaunch$default = Unit.INSTANCE;
                        }
                        return objLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLaunch$default : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public e(UpdateListFragment updateListFragment, Continuation<? super e> continuation) {
                    super(2, continuation);
                    this.b = updateListFragment;
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
                        UpdateListFragment updateListFragment = this.b;
                        int i2 = UpdateListFragment.j;
                        SharedFlow<String> languageDataFlow = updateListFragment.i().h.getLanguageDataFlow();
                        C0113a c0113a = new C0113a(this.b);
                        this.a = 1;
                        if (languageDataFlow.collect(c0113a, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0107a(UpdateListFragment updateListFragment, Continuation<? super C0107a> continuation) {
                super(2, continuation);
                this.b = updateListFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                C0107a c0107a = new C0107a(this.b, continuation);
                c0107a.a = obj;
                return c0107a;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C0107a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.a;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0108a(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new b(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new c(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new d(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new e(this.b, null), 3, null);
                UpdateListFragment updateListFragment = this.b;
                int i = UpdateListFragment.j;
                final UpgradeViewModel upgradeViewModelI = updateListFragment.i();
                Objects.requireNonNull(upgradeViewModelI);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                upgradeViewModelI.e.b.forEach(new BiConsumer() { // from class: spi.gu
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj2, Object obj3) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        UpgradeViewModel this$0 = upgradeViewModelI;
                        String pkn = (String) obj2;
                        MutableStateFlow stateFlow = (MutableStateFlow) obj3;
                        Intrinsics.checkNotNullParameter(coroutineScope2, "$coroutineScope");
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        Intrinsics.checkNotNullParameter(pkn, "pkn");
                        Intrinsics.checkNotNullParameter(stateFlow, "stateFlow");
                        StringBuilder sbL = e.l("operate vm forEach state:", ((SoftwareStateData) stateFlow.getValue()).getState(), " percentCount:", ((SoftwareStateData) stateFlow.getValue()).getPercentCount(), " pkn:");
                        sbL.append(pkn);
                        ct.a(sbL.toString(), new Object[0]);
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new ku(stateFlow, pkn, this$0, null), 3, null);
                    }
                });
                UpgradeViewModel upgradeViewModelI2 = this.b.i();
                Objects.requireNonNull(upgradeViewModelI2);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new mu(upgradeViewModelI2, null), 3, null);
                this.b.i().c(coroutineScope);
                return Unit.INSTANCE;
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return UpdateListFragment.this.new a(continuation);
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
                UpdateListFragment updateListFragment = UpdateListFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                C0107a c0107a = new C0107a(updateListFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(updateListFragment, state, c0107a, this) == coroutine_suspended) {
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

    public static final class b extends Lambda implements Function3<Software, Integer, Integer, Unit> {
        public b() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, Integer num, Integer num2) {
            Software software2 = software;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            UpdateListFragment updateListFragment = UpdateListFragment.this;
            int i = UpdateListFragment.j;
            UpgradeViewModel upgradeViewModelI = updateListFragment.i();
            Objects.requireNonNull(upgradeViewModelI);
            Intrinsics.checkNotNullParameter(software2, "software");
            upgradeViewModelI.d.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), upgradeViewModelI.g.parseToSoftwareOptParam(software2));
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function3<Software, ItemUpdateAppBinding, Integer, Unit> {
        public c() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, ItemUpdateAppBinding itemUpdateAppBinding, Integer num) {
            Software software2 = software;
            ItemUpdateAppBinding itemViewBinding = itemUpdateAppBinding;
            num.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            Intrinsics.checkNotNullParameter(itemViewBinding, "itemViewBinding");
            UpdateListFragment updateListFragment = UpdateListFragment.this;
            xh.b bVar = xh.a;
            int id = software2.getId();
            Objects.requireNonNull(bVar);
            spi.b.h(updateListFragment, new xh.a(id));
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function3<Software, Integer, Integer, Unit> {
        public d() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, Integer num, Integer num2) {
            Pair pair;
            Software software2 = software;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            UpdateListFragment updateListFragment = UpdateListFragment.this;
            int i = UpdateListFragment.j;
            UpgradeViewModel upgradeViewModelI = updateListFragment.i();
            Objects.requireNonNull(upgradeViewModelI);
            Intrinsics.checkNotNullParameter(software2, "software");
            int iB = upgradeViewModelI.e.b(software2.getPackageName());
            ct.a(spi.e.c("clickCancelWorkCheckAlert stateCurrent:", iB), new Object[0]);
            if (iB == 8) {
                pair = new Pair(Boolean.FALSE, Integer.valueOf(iB));
            } else if (iB == 10) {
                upgradeViewModelI.d.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), upgradeViewModelI.g.parseToSoftwareOptParam(software2));
                pair = new Pair(Boolean.TRUE, Integer.valueOf(iB));
            } else if (iB != 11) {
                pair = new Pair(Boolean.FALSE, Integer.valueOf(iB));
                ct.b(spi.e.c("clickCancelWorkCheckAlert error state here stateCurrent:", iB), new Object[0]);
            } else {
                pair = new Pair(Boolean.TRUE, Integer.valueOf(iB));
            }
            if (((Boolean) pair.getFirst()).booleanValue()) {
                UpdateDeleteDialog updateDeleteDialog = new UpdateDeleteDialog();
                updateDeleteDialog.d = false;
                vm vmVar = vm.a;
                Context contextRequireContext = UpdateListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                String title = vmVar.e(contextRequireContext, R.string.management_fragment_dialog_title);
                Intrinsics.checkNotNullParameter(title, "title");
                updateDeleteDialog.g = title;
                Context contextRequireContext2 = UpdateListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                String normalText = vmVar.e(contextRequireContext2, R.string.management_fragment_dialog_update_text);
                Intrinsics.checkNotNullParameter(normalText, "normalText");
                updateDeleteDialog.h = normalText;
                Context contextRequireContext3 = UpdateListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                String startButtonText = vmVar.e(contextRequireContext3, R.string.management_fragment_dialog_button_confirm);
                Intrinsics.checkNotNullParameter(startButtonText, "startButtonText");
                updateDeleteDialog.i = startButtonText;
                Context contextRequireContext4 = UpdateListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
                String endButtonText = vmVar.e(contextRequireContext4, R.string.management_fragment_dialog_button_cancel);
                Intrinsics.checkNotNullParameter(endButtonText, "endButtonText");
                updateDeleteDialog.j = endButtonText;
                f block = new f(UpdateListFragment.this, software2);
                Intrinsics.checkNotNullParameter(block, "block");
                updateDeleteDialog.e = block;
                g block2 = new g(UpdateListFragment.this, software2, pair);
                Intrinsics.checkNotNullParameter(block2, "block");
                updateDeleteDialog.f = block2;
                FragmentManager supportFragmentManager = UpdateListFragment.this.requireActivity().getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
                updateDeleteDialog.show(supportFragmentManager, UpdateDeleteDialog.class.getName());
            } else {
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(UpdateListFragment.this), null, null, new h(UpdateListFragment.this, software2, null), 3, null);
            }
            return Unit.INSTANCE;
        }
    }

    public UpdateListFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$special$$inlined$viewModels$default$2
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
        this.h = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(UpgradeViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.UpdateListFragment$special$$inlined$viewModels$default$5
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
        UpdateAdapter updateAdapter = new UpdateAdapter();
        updateAdapter.a(R.id.tv_update_button, new b());
        c block = new c();
        Intrinsics.checkNotNullParameter(block, "block");
        updateAdapter.a = block;
        updateAdapter.a(R.id.iv_update_cancel, new d());
        this.i = updateAdapter;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentUpdateListBinding fragmentUpdateListBindingA = FragmentUpdateListBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentUpdateListBindingA, "inflate(layoutInflater)");
        return fragmentUpdateListBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        SkinAppCompatTextView skinAppCompatTextView;
        SkinAppCompatTextView skinAppCompatTextView2;
        FragmentUpdateListBinding fragmentUpdateListBinding = (FragmentUpdateListBinding) this.a;
        if (fragmentUpdateListBinding != null && (skinAppCompatTextView2 = fragmentUpdateListBinding.j) != null) {
            skinAppCompatTextView2.setOnClickListener(new fj(this, 16));
        }
        FragmentUpdateListBinding fragmentUpdateListBinding2 = (FragmentUpdateListBinding) this.a;
        if (fragmentUpdateListBinding2 == null || (skinAppCompatTextView = fragmentUpdateListBinding2.k) == null) {
            return;
        }
        skinAppCompatTextView.setOnClickListener(new y6(this, 9));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        FragmentUpdateListBinding fragmentUpdateListBinding;
        ConstraintLayout constraintLayout;
        Space space;
        SkinAppCompatTextView skinAppCompatTextView;
        SkinAppCompatTextView skinAppCompatTextView2;
        SkinAppCompatImageView skinAppCompatImageView;
        SkinAppCompatImageView skinAppCompatImageView2;
        FragmentUpdateListBinding fragmentUpdateListBinding2 = (FragmentUpdateListBinding) this.a;
        if (fragmentUpdateListBinding2 != null) {
            RecyclerView recyclerView = fragmentUpdateListBinding2.h;
            recyclerView.setLayoutManager(recyclerView.getResources().getConfiguration().orientation == 1 ? new LinearLayoutManager(requireContext()) : new GridLayoutManager(requireContext(), 2));
            recyclerView.setAdapter(this.i);
            kg kgVar = kg.a;
            if ((!kgVar.f() && !kgVar.g()) || (fragmentUpdateListBinding = (FragmentUpdateListBinding) this.a) == null || (constraintLayout = fragmentUpdateListBinding.g) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding?.rootFgUpdateList ?: return");
            FragmentUpdateListBinding fragmentUpdateListBinding3 = (FragmentUpdateListBinding) this.a;
            if (fragmentUpdateListBinding3 == null || (space = fragmentUpdateListBinding3.i) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(space, "binding?.sUpdateEndTop ?: return");
            FragmentUpdateListBinding fragmentUpdateListBinding4 = (FragmentUpdateListBinding) this.a;
            if (fragmentUpdateListBinding4 == null || (skinAppCompatTextView = fragmentUpdateListBinding4.j) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(skinAppCompatTextView, "binding?.tvUpdateAllStart ?: return");
            FragmentUpdateListBinding fragmentUpdateListBinding5 = (FragmentUpdateListBinding) this.a;
            if (fragmentUpdateListBinding5 == null || (skinAppCompatTextView2 = fragmentUpdateListBinding5.k) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(skinAppCompatTextView2, "binding?.tvUpdateAllStop ?: return");
            FragmentUpdateListBinding fragmentUpdateListBinding6 = (FragmentUpdateListBinding) this.a;
            if (fragmentUpdateListBinding6 == null || (skinAppCompatImageView = fragmentUpdateListBinding6.d) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(skinAppCompatImageView, "binding?.ivUpdateAllStart ?: return");
            FragmentUpdateListBinding fragmentUpdateListBinding7 = (FragmentUpdateListBinding) this.a;
            if (fragmentUpdateListBinding7 == null || (skinAppCompatImageView2 = fragmentUpdateListBinding7.e) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(skinAppCompatImageView2, "binding?.ivUpdateAllStop ?: return");
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(skinAppCompatTextView.getId(), 7, space.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_5));
            constraintSet.connect(skinAppCompatImageView.getId(), 7, skinAppCompatTextView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
            constraintSet.connect(skinAppCompatTextView2.getId(), 7, skinAppCompatImageView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_44));
            constraintSet.connect(skinAppCompatImageView2.getId(), 7, skinAppCompatTextView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new a(null), 3, null);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
        ConstraintLayout constraintLayout;
        FragmentUpdateListBinding fragmentUpdateListBinding;
        Space space;
        FragmentUpdateListBinding fragmentUpdateListBinding2;
        SkinAppCompatTextView skinAppCompatTextView;
        FragmentUpdateListBinding fragmentUpdateListBinding3;
        SkinAppCompatTextView skinAppCompatTextView2;
        FragmentUpdateListBinding fragmentUpdateListBinding4;
        SkinAppCompatImageView skinAppCompatImageView;
        FragmentUpdateListBinding fragmentUpdateListBinding5;
        SkinAppCompatImageView skinAppCompatImageView2;
        VB vb = this.a;
        FragmentUpdateListBinding fragmentUpdateListBinding6 = (FragmentUpdateListBinding) vb;
        if (fragmentUpdateListBinding6 == null || (constraintLayout = fragmentUpdateListBinding6.g) == null || (fragmentUpdateListBinding = (FragmentUpdateListBinding) vb) == null || (space = fragmentUpdateListBinding.i) == null || (fragmentUpdateListBinding2 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatTextView = fragmentUpdateListBinding2.j) == null || (fragmentUpdateListBinding3 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatTextView2 = fragmentUpdateListBinding3.k) == null || (fragmentUpdateListBinding4 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatImageView = fragmentUpdateListBinding4.d) == null || (fragmentUpdateListBinding5 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatImageView2 = fragmentUpdateListBinding5.e) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinAppCompatTextView.getId(), 6);
        constraintSet.clear(skinAppCompatTextView2.getId(), 6);
        constraintSet.connect(skinAppCompatTextView2.getId(), 7, space.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_5));
        constraintSet.connect(skinAppCompatImageView2.getId(), 7, skinAppCompatTextView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.connect(skinAppCompatTextView.getId(), 7, skinAppCompatImageView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_44));
        constraintSet.connect(skinAppCompatImageView.getId(), 7, skinAppCompatTextView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.applyTo(constraintLayout);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
        ConstraintLayout constraintLayout;
        FragmentUpdateListBinding fragmentUpdateListBinding;
        Space space;
        FragmentUpdateListBinding fragmentUpdateListBinding2;
        SkinAppCompatTextView skinAppCompatTextView;
        FragmentUpdateListBinding fragmentUpdateListBinding3;
        SkinAppCompatTextView skinAppCompatTextView2;
        FragmentUpdateListBinding fragmentUpdateListBinding4;
        SkinAppCompatImageView skinAppCompatImageView;
        FragmentUpdateListBinding fragmentUpdateListBinding5;
        SkinAppCompatImageView skinAppCompatImageView2;
        VB vb = this.a;
        FragmentUpdateListBinding fragmentUpdateListBinding6 = (FragmentUpdateListBinding) vb;
        if (fragmentUpdateListBinding6 == null || (constraintLayout = fragmentUpdateListBinding6.g) == null || (fragmentUpdateListBinding = (FragmentUpdateListBinding) vb) == null || (space = fragmentUpdateListBinding.i) == null || (fragmentUpdateListBinding2 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatTextView = fragmentUpdateListBinding2.j) == null || (fragmentUpdateListBinding3 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatTextView2 = fragmentUpdateListBinding3.k) == null || (fragmentUpdateListBinding4 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatImageView = fragmentUpdateListBinding4.d) == null || (fragmentUpdateListBinding5 = (FragmentUpdateListBinding) vb) == null || (skinAppCompatImageView2 = fragmentUpdateListBinding5.e) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinAppCompatTextView.getId(), 6);
        constraintSet.clear(skinAppCompatTextView2.getId(), 6);
        constraintSet.connect(skinAppCompatTextView2.getId(), 7, space.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_5));
        constraintSet.connect(skinAppCompatImageView2.getId(), 7, skinAppCompatTextView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.connect(skinAppCompatTextView.getId(), 7, skinAppCompatImageView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_44));
        constraintSet.connect(skinAppCompatImageView.getId(), 7, skinAppCompatTextView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.applyTo(constraintLayout);
    }

    public final UpgradeViewModel i() {
        return (UpgradeViewModel) this.h.getValue();
    }
}
