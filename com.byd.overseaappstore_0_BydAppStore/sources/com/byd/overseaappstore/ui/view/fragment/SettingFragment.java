package com.byd.overseaappstore.ui.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Space;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentSettingBinding;
import com.byd.overseaappstore.databinding.IncludeTopBarMergeBinding;
import com.byd.overseaappstore.module.ability.util.notify.router.net.NetContextState;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.byd.overseaappstore.ui.vm.SettingViewModel;
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
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.dp;
import spi.fj;
import spi.gk;
import spi.hi;
import spi.o5;
import spi.sr;
import spi.u5;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/SettingFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentSettingBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SettingFragment extends Hilt_SettingFragment<FragmentSettingBinding> {
    public static final /* synthetic */ int k = 0;

    @Nullable
    public IncludeTopBarMergeBinding h;

    @NotNull
    public final Lazy i;

    @NotNull
    public final Lazy j;

    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(View view) {
            View it = view;
            Intrinsics.checkNotNullParameter(it, "it");
            SettingFragment settingFragment = SettingFragment.this;
            Objects.requireNonNull(dp.a);
            Intrinsics.checkNotNullParameter("PROTOCOL_FRAG_TYPE_SETTING", "protocolType");
            spi.b.h(settingFragment, new dp.a("PROTOCOL_FRAG_TYPE_SETTING"));
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$1", f = "SettingFragment.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$1$1", f = "SettingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ SettingFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SettingFragment$b$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$1$1$1", f = "SettingFragment.kt", i = {}, l = {108}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0072a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ SettingFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SettingFragment$b$a$a$a, reason: collision with other inner class name */
                public static final class C0073a<T> implements FlowCollector {
                    public final /* synthetic */ SettingFragment a;

                    public C0073a(SettingFragment settingFragment) {
                        this.a = settingFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        SkinImageView skinImageView;
                        NetContextState netContextState = (NetContextState) obj;
                        if (netContextState.getWorkWifiOnly()) {
                            ct.a("toggle collect outer workWifiOnly:" + netContextState.getWorkWifiOnly() + " wifi:" + netContextState.getWifiConnected() + " cellular:" + netContextState.getCellularConnected() + " no intervene", new Object[0]);
                        } else {
                            ct.a("toggle collect outer workWifiOnly:" + netContextState.getWorkWifiOnly() + " wifi:" + netContextState.getWifiConnected() + " cellular:" + netContextState.getCellularConnected() + " update switch", new Object[0]);
                            SettingFragment settingFragment = this.a;
                            int i = SettingFragment.k;
                            FragmentSettingBinding fragmentSettingBinding = (FragmentSettingBinding) settingFragment.a;
                            if (fragmentSettingBinding == null || (skinImageView = fragmentSettingBinding.c) == null) {
                                unit = null;
                            } else {
                                skinImageView.setImageResource(R.drawable.setting_switch_off);
                                unit = Unit.INSTANCE;
                            }
                            if (unit == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                return unit;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0072a(SettingFragment settingFragment, Continuation<? super C0072a> continuation) {
                    super(2, continuation);
                    this.b = settingFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0072a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0072a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow<NetContextState> stateFlow = SettingFragment.i(this.b).f;
                        C0073a c0073a = new C0073a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0073a, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SettingFragment$b$a$b, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$1$1$2", f = "SettingFragment.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0074b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ SettingFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SettingFragment$b$a$b$a, reason: collision with other inner class name */
                @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$1$1$2$1", f = "SettingFragment.kt", i = {}, l = {123}, m = "invokeSuspend", n = {}, s = {})
                public static final class C0075a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int a;
                    public final /* synthetic */ SettingFragment b;

                    /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SettingFragment$b$a$b$a$a, reason: collision with other inner class name */
                    public static final class C0076a<T> implements FlowCollector {
                        public final /* synthetic */ SettingFragment a;

                        public C0076a(SettingFragment settingFragment) {
                            this.a = settingFragment;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj, Continuation continuation) {
                            SkinImageView skinImageView;
                            SkinImageView skinImageView2;
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            ct.a(hi.e("toggle collect view:", zBooleanValue), new Object[0]);
                            Unit unit = null;
                            if (zBooleanValue) {
                                SettingFragment settingFragment = this.a;
                                int i = SettingFragment.k;
                                FragmentSettingBinding fragmentSettingBinding = (FragmentSettingBinding) settingFragment.a;
                                if (fragmentSettingBinding != null && (skinImageView2 = fragmentSettingBinding.c) != null) {
                                    skinImageView2.setImageResource(R.drawable.setting_switch_open);
                                    unit = Unit.INSTANCE;
                                }
                                if (unit == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return unit;
                                }
                            } else {
                                SettingFragment settingFragment2 = this.a;
                                int i2 = SettingFragment.k;
                                FragmentSettingBinding fragmentSettingBinding2 = (FragmentSettingBinding) settingFragment2.a;
                                if (fragmentSettingBinding2 != null && (skinImageView = fragmentSettingBinding2.c) != null) {
                                    skinImageView.setImageResource(R.drawable.setting_switch_off);
                                    unit = Unit.INSTANCE;
                                }
                                if (unit == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return unit;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0075a(SettingFragment settingFragment, Continuation<? super C0075a> continuation) {
                        super(2, continuation);
                        this.b = settingFragment;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new C0075a(this.b, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C0075a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.a;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            StateFlow<Boolean> stateFlow = SettingFragment.i(this.b).h;
                            C0076a c0076a = new C0076a(this.b);
                            this.a = 1;
                            if (stateFlow.collect(c0076a, this) == coroutine_suspended) {
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
                public C0074b(SettingFragment settingFragment, Continuation<? super C0074b> continuation) {
                    super(2, continuation);
                    this.b = settingFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0074b(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0074b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SettingFragment settingFragment = this.b;
                        Lifecycle.State state = Lifecycle.State.STARTED;
                        C0075a c0075a = new C0075a(settingFragment, null);
                        this.a = 1;
                        if (RepeatOnLifecycleKt.repeatOnLifecycle(settingFragment, state, c0075a, this) == coroutine_suspended) {
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
            public a(SettingFragment settingFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = settingFragment;
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
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0072a(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0074b(this.b, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SettingFragment.this.new b(continuation);
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
                SettingFragment settingFragment = SettingFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(settingFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(settingFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$2", f = "SettingFragment.kt", i = {}, l = {136}, m = "invokeSuspend", n = {}, s = {})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$2$1", f = "SettingFragment.kt", i = {}, l = {137}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SettingFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SettingFragment$c$a$a, reason: collision with other inner class name */
            public static final class C0077a<T> implements FlowCollector {
                public final /* synthetic */ SettingFragment a;

                public C0077a(SettingFragment settingFragment) {
                    this.a = settingFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    long jLongValue = ((Number) obj).longValue();
                    SettingFragment settingFragment = this.a;
                    int i = SettingFragment.k;
                    FragmentSettingBinding fragmentSettingBinding = (FragmentSettingBinding) settingFragment.a;
                    SkinTextView skinTextView = fragmentSettingBinding != null ? fragmentSettingBinding.g : null;
                    if (skinTextView != null) {
                        skinTextView.setText(o5.a.a(Boxing.boxLong(jLongValue)));
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SettingFragment settingFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = settingFragment;
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
                    StateFlow<Long> stateFlow = SettingFragment.i(this.b).j;
                    C0077a c0077a = new C0077a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0077a, this) == coroutine_suspended) {
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

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SettingFragment.this.new c(continuation);
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
                SettingFragment settingFragment = SettingFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(settingFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(settingFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$3", f = "SettingFragment.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SettingFragment.this.new d(continuation);
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
                SettingViewModel settingViewModelI = SettingFragment.i(SettingFragment.this);
                this.a = 1;
                if (settingViewModelI.b(this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SettingFragment$observeData$4", f = "SettingFragment.kt", i = {}, l = {153}, m = "invokeSuspend", n = {}, s = {})
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SettingFragment.this.new e(continuation);
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
                SettingViewModel settingViewModelI = SettingFragment.i(SettingFragment.this);
                this.a = 1;
                if (settingViewModelI.c(this) == coroutine_suspended) {
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

    public SettingFragment() {
        final Function0 function0 = null;
        this.i = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$activityViewModels$default$3
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
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$viewModels$default$2
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
        this.j = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SettingViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.SettingFragment$special$$inlined$viewModels$default$5
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

    public static final SettingViewModel i(SettingFragment settingFragment) {
        return (SettingViewModel) settingFragment.j.getValue();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentSettingBinding fragmentSettingBindingA = FragmentSettingBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentSettingBindingA, "inflate(layoutInflater)");
        this.h = IncludeTopBarMergeBinding.a(fragmentSettingBindingA.a);
        return fragmentSettingBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        SkinView skinView;
        SkinView skinView2;
        SkinImageView skinImageView;
        SkinImageView skinImageView2;
        FragmentSettingBinding fragmentSettingBinding = (FragmentSettingBinding) this.a;
        if (fragmentSettingBinding != null && (skinImageView2 = fragmentSettingBinding.c) != null) {
            skinImageView2.setOnClickListener(new fj(this, 12));
        }
        IncludeTopBarMergeBinding includeTopBarMergeBinding = this.h;
        if (includeTopBarMergeBinding != null && (skinImageView = includeTopBarMergeBinding.b) != null) {
            skinImageView.setOnClickListener(new y6(this, 6));
        }
        FragmentSettingBinding fragmentSettingBinding2 = (FragmentSettingBinding) this.a;
        if (fragmentSettingBinding2 != null && (skinView2 = fragmentSettingBinding2.i) != null) {
            skinView2.setOnClickListener(new gk(this, 8));
        }
        FragmentSettingBinding fragmentSettingBinding3 = (FragmentSettingBinding) this.a;
        if (fragmentSettingBinding3 == null || (skinView = fragmentSettingBinding3.k) == null) {
            return;
        }
        a listener = new a();
        Intrinsics.checkNotNullParameter(listener, "listener");
        skinView.setOnClickListener(new u5(true, 300L, listener));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        FragmentSettingBinding fragmentSettingBinding = (FragmentSettingBinding) this.a;
        SkinTextView skinTextView = fragmentSettingBinding != null ? fragmentSettingBinding.h : null;
        if (skinTextView != null) {
            sr srVar = sr.a;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            skinTextView.setText(srVar.e(contextRequireContext));
        }
        IncludeTopBarMergeBinding includeTopBarMergeBinding = this.h;
        SkinTextView skinTextView2 = includeTopBarMergeBinding != null ? includeTopBarMergeBinding.c : null;
        if (skinTextView2 == null) {
            return;
        }
        skinTextView2.setText(getString(R.string.setting_fragment_top_title));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new b(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new c(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new d(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new e(null), 3, null);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
        ConstraintLayout constraintLayout;
        FragmentSettingBinding fragmentSettingBinding;
        SkinView skinView;
        FragmentSettingBinding fragmentSettingBinding2;
        SkinImageView skinImageView;
        FragmentSettingBinding fragmentSettingBinding3;
        Space space;
        FragmentSettingBinding fragmentSettingBinding4;
        Space space2;
        VB vb = this.a;
        FragmentSettingBinding fragmentSettingBinding5 = (FragmentSettingBinding) vb;
        if (fragmentSettingBinding5 == null || (constraintLayout = fragmentSettingBinding5.d) == null || (fragmentSettingBinding = (FragmentSettingBinding) vb) == null || (skinView = fragmentSettingBinding.j) == null || (fragmentSettingBinding2 = (FragmentSettingBinding) vb) == null || (skinImageView = fragmentSettingBinding2.b) == null || (fragmentSettingBinding3 = (FragmentSettingBinding) vb) == null || (space = fragmentSettingBinding3.e) == null || (fragmentSettingBinding4 = (FragmentSettingBinding) vb) == null || (space2 = fragmentSettingBinding4.f) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinView.getId(), 6);
        constraintSet.clear(skinImageView.getId(), 7);
        constraintSet.clear(skinImageView.getId(), 6);
        constraintSet.connect(skinView.getId(), 7, space.getId(), 6);
        constraintSet.connect(skinImageView.getId(), 6, space2.getId(), 7);
        constraintSet.connect(skinImageView.getId(), 7, skinView.getId(), 6);
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
