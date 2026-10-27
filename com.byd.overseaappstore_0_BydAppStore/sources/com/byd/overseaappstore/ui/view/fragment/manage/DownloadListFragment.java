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
import com.byd.overseaappstore.databinding.FragmentDownloadListBinding;
import com.byd.overseaappstore.databinding.ItemDownloadAppBinding;
import com.byd.overseaappstore.module.ability.data.uistate.DownloadItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.ui.view.adapter.DownloadAdapter;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.dialog.DownloadDeleteDialog;
import com.byd.overseaappstore.ui.vm.DownloadViewModel;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import spi.gk;
import spi.j7;
import spi.kg;
import spi.l7;
import spi.m7;
import spi.vm;
import spi.xh;
import spi.xi;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/manage/DownloadListFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentDownloadListBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class DownloadListFragment extends Hilt_DownloadListFragment<FragmentDownloadListBinding> {
    public static final /* synthetic */ int j = 0;

    @NotNull
    public final Lazy h;

    @NotNull
    public final DownloadAdapter i;

    public static final class a extends Lambda implements Function3<Software, Integer, Integer, Unit> {
        public a() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, Integer num, Integer num2) {
            Pair pair;
            Software software2 = software;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            DownloadViewModel downloadViewModelI = DownloadListFragment.i(DownloadListFragment.this);
            Objects.requireNonNull(downloadViewModelI);
            Intrinsics.checkNotNullParameter(software2, "software");
            int iB = downloadViewModelI.e.b(software2.getPackageName());
            ct.a(spi.e.c("clickCancelWorkCheckAlert stateCurrent:", iB), new Object[0]);
            if (iB == 1) {
                pair = new Pair(Boolean.FALSE, Integer.valueOf(iB));
            } else if (iB == 3) {
                downloadViewModelI.d.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), downloadViewModelI.g.parseToSoftwareOptParam(software2));
                pair = new Pair(Boolean.TRUE, Integer.valueOf(iB));
            } else if (iB != 4) {
                pair = new Pair(Boolean.TRUE, Integer.valueOf(iB));
                ct.b(spi.e.c("clickCancelWorkCheckAlert error state here stateCurrent:", iB), new Object[0]);
            } else {
                pair = new Pair(Boolean.TRUE, Integer.valueOf(iB));
            }
            boolean zBooleanValue = ((Boolean) pair.getFirst()).booleanValue();
            if (zBooleanValue) {
                DownloadDeleteDialog downloadDeleteDialog = new DownloadDeleteDialog();
                downloadDeleteDialog.d = false;
                vm vmVar = vm.a;
                Context contextRequireContext = DownloadListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                String title = vmVar.e(contextRequireContext, R.string.management_fragment_dialog_title);
                Intrinsics.checkNotNullParameter(title, "title");
                downloadDeleteDialog.g = title;
                Context contextRequireContext2 = DownloadListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext()");
                String normalText = vmVar.e(contextRequireContext2, R.string.management_fragment_dialog_text);
                Intrinsics.checkNotNullParameter(normalText, "normalText");
                downloadDeleteDialog.h = normalText;
                Context contextRequireContext3 = DownloadListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext()");
                String startButtonText = vmVar.e(contextRequireContext3, R.string.management_fragment_dialog_button_confirm);
                Intrinsics.checkNotNullParameter(startButtonText, "startButtonText");
                downloadDeleteDialog.i = startButtonText;
                Context contextRequireContext4 = DownloadListFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext4, "requireContext()");
                String endButtonText = vmVar.e(contextRequireContext4, R.string.management_fragment_dialog_button_cancel);
                Intrinsics.checkNotNullParameter(endButtonText, "endButtonText");
                downloadDeleteDialog.j = endButtonText;
                com.byd.overseaappstore.ui.view.fragment.manage.b block = new com.byd.overseaappstore.ui.view.fragment.manage.b(DownloadListFragment.this, software2, pair);
                Intrinsics.checkNotNullParameter(block, "block");
                downloadDeleteDialog.e = block;
                com.byd.overseaappstore.ui.view.fragment.manage.c block2 = new com.byd.overseaappstore.ui.view.fragment.manage.c(DownloadListFragment.this, software2, pair);
                Intrinsics.checkNotNullParameter(block2, "block");
                downloadDeleteDialog.f = block2;
                FragmentManager supportFragmentManager = DownloadListFragment.this.requireActivity().getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
                downloadDeleteDialog.show(supportFragmentManager, DownloadDeleteDialog.class.getName());
            } else if (!zBooleanValue) {
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(DownloadListFragment.this), null, null, new com.byd.overseaappstore.ui.view.fragment.manage.d(DownloadListFragment.this, software2, pair, null), 3, null);
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
            DownloadViewModel downloadViewModelI = DownloadListFragment.i(DownloadListFragment.this);
            Objects.requireNonNull(downloadViewModelI);
            Intrinsics.checkNotNullParameter(software2, "software");
            downloadViewModelI.d.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), downloadViewModelI.g.parseToSoftwareOptParam(software2));
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function3<Software, ItemDownloadAppBinding, Integer, Unit> {
        public c() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, ItemDownloadAppBinding itemDownloadAppBinding, Integer num) {
            Software software2 = software;
            ItemDownloadAppBinding itemViewBinding = itemDownloadAppBinding;
            num.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            Intrinsics.checkNotNullParameter(itemViewBinding, "itemViewBinding");
            DownloadListFragment downloadListFragment = DownloadListFragment.this;
            xh.b bVar = xh.a;
            int id = software2.getId();
            Objects.requireNonNull(bVar);
            spi.b.h(downloadListFragment, new xh.a(id));
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1", f = "DownloadListFragment.kt", i = {}, l = {126}, m = "invokeSuspend", n = {}, s = {})
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1$1", f = "DownloadListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ DownloadListFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$d$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1$1$1", f = "DownloadListFragment.kt", i = {}, l = {131}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0092a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ DownloadListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$d$a$a$a, reason: collision with other inner class name */
                public static final class C0093a<T> implements FlowCollector {
                    public final /* synthetic */ DownloadListFragment a;

                    public C0093a(DownloadListFragment downloadListFragment) {
                        this.a = downloadListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        Group group;
                        int i;
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        DownloadListFragment downloadListFragment = this.a;
                        int i2 = DownloadListFragment.j;
                        FragmentDownloadListBinding fragmentDownloadListBinding = (FragmentDownloadListBinding) downloadListFragment.a;
                        if (fragmentDownloadListBinding != null) {
                            if (zBooleanValue) {
                                group = fragmentDownloadListBinding.c;
                                i = 0;
                            } else {
                                group = fragmentDownloadListBinding.c;
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
                public C0092a(DownloadListFragment downloadListFragment, Continuation<? super C0092a> continuation) {
                    super(2, continuation);
                    this.b = downloadListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0092a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0092a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow<Boolean> stateFlow = DownloadListFragment.i(this.b).k;
                        C0093a c0093a = new C0093a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0093a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1$1$2", f = "DownloadListFragment.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, s = {})
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ DownloadListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$d$a$b$a, reason: collision with other inner class name */
                public static final class C0094a<T> implements FlowCollector {
                    public final /* synthetic */ DownloadListFragment a;

                    public C0094a(DownloadListFragment downloadListFragment) {
                        this.a = downloadListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        ErrorEmptyUiState errorEmptyUiState = (ErrorEmptyUiState) obj;
                        DownloadListFragment downloadListFragment = this.a;
                        int i = DownloadListFragment.j;
                        FragmentDownloadListBinding fragmentDownloadListBinding = (FragmentDownloadListBinding) downloadListFragment.a;
                        if (fragmentDownloadListBinding != null) {
                            if (errorEmptyUiState.getVisible()) {
                                fragmentDownloadListBinding.b.setVisibility(0);
                                fragmentDownloadListBinding.f.setImageResource(errorEmptyUiState.getPicId());
                                fragmentDownloadListBinding.l.setText(errorEmptyUiState.getTipId());
                            } else {
                                fragmentDownloadListBinding.b.setVisibility(8);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(DownloadListFragment downloadListFragment, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.b = downloadListFragment;
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
                        StateFlow<ErrorEmptyUiState> stateFlow = DownloadListFragment.i(this.b).o;
                        C0094a c0094a = new C0094a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0094a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1$1$3", f = "DownloadListFragment.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {})
            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ DownloadListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$d$a$c$a, reason: collision with other inner class name */
                public static final class C0095a<T> implements FlowCollector {
                    public final /* synthetic */ DownloadListFragment a;

                    public C0095a(DownloadListFragment downloadListFragment) {
                        this.a = downloadListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        DownloadItemUiState downloadItemUiState = (DownloadItemUiState) obj;
                        DownloadListFragment downloadListFragment = this.a;
                        int i = DownloadListFragment.j;
                        FragmentDownloadListBinding fragmentDownloadListBinding = (FragmentDownloadListBinding) downloadListFragment.a;
                        if (fragmentDownloadListBinding != null) {
                            if (downloadItemUiState.getVisible()) {
                                fragmentDownloadListBinding.h.setVisibility(0);
                                if (fragmentDownloadListBinding.h.isAnimating()) {
                                    ct.a("the ItemMovingAnimations is running not submitList", new Object[0]);
                                } else {
                                    downloadListFragment.i.submitList((List) downloadItemUiState.getData());
                                }
                            } else {
                                fragmentDownloadListBinding.h.setVisibility(8);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public c(DownloadListFragment downloadListFragment, Continuation<? super c> continuation) {
                    super(2, continuation);
                    this.b = downloadListFragment;
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
                        StateFlow<DownloadItemUiState> stateFlow = DownloadListFragment.i(this.b).m;
                        C0095a c0095a = new C0095a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0095a, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$d$a$d, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1$1$4", f = "DownloadListFragment.kt", i = {}, l = {182}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0096d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ DownloadListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$d$a$d$a, reason: collision with other inner class name */
                @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1$1$4$1", f = "DownloadListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                public static final class C0097a extends SuspendLambda implements Function2<IUUState, Continuation<? super Unit>, Object> {
                    public /* synthetic */ Object a;
                    public final /* synthetic */ DownloadListFragment b;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0097a(DownloadListFragment downloadListFragment, Continuation<? super C0097a> continuation) {
                        super(2, continuation);
                        this.b = downloadListFragment;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        C0097a c0097a = new C0097a(this.b, continuation);
                        c0097a.a = obj;
                        return c0097a;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public final Object mo6invoke(IUUState iUUState, Continuation<? super Unit> continuation) {
                        return ((C0097a) create(iUUState, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        ResultKt.throwOnFailure(obj);
                        IUUState iuuState = (IUUState) this.a;
                        DownloadViewModel downloadViewModelI = DownloadListFragment.i(this.b);
                        LifecycleCoroutineScope coroutineScope = LifecycleOwnerKt.getLifecycleScope(this.b);
                        Objects.requireNonNull(downloadViewModelI);
                        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                        Intrinsics.checkNotNullParameter(iuuState, "iuuState");
                        if (iuuState.getState() == 6) {
                            ct.a(xi.f("iuu INSTALLED fetch operateState:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                            downloadViewModelI.b(coroutineScope);
                        } else {
                            ct.a(xi.f("iuu other state skip it operateState:", iuuState.getState(), " pkn:", iuuState.getSoftwareOptParam().getPackageName()), new Object[0]);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0096d(DownloadListFragment downloadListFragment, Continuation<? super C0096d> continuation) {
                    super(2, continuation);
                    this.b = downloadListFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0096d(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0096d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SharedFlow<IUUState> sharedFlow = DownloadListFragment.i(this.b).i;
                        C0097a c0097a = new C0097a(this.b, null);
                        this.a = 1;
                        if (FlowKt.collectLatest(sharedFlow, c0097a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$observeData$1$1$5", f = "DownloadListFragment.kt", i = {}, l = {191}, m = "invokeSuspend", n = {}, s = {})
            public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ DownloadListFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$d$a$e$a, reason: collision with other inner class name */
                public static final class C0098a<T> implements FlowCollector {
                    public final /* synthetic */ DownloadListFragment a;

                    public C0098a(DownloadListFragment downloadListFragment) {
                        this.a = downloadListFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        DownloadViewModel downloadViewModelI = DownloadListFragment.i(this.a);
                        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
                        Context contextRequireContext = this.a.requireContext();
                        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                        Objects.requireNonNull(downloadViewModelI);
                        Object objLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new m7(downloadViewModelI, contextRequireContext, null), 3, null);
                        if (objLaunch$default != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            objLaunch$default = Unit.INSTANCE;
                        }
                        return objLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLaunch$default : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public e(DownloadListFragment downloadListFragment, Continuation<? super e> continuation) {
                    super(2, continuation);
                    this.b = downloadListFragment;
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
                        SharedFlow<String> languageDataFlow = DownloadListFragment.i(this.b).h.getLanguageDataFlow();
                        C0098a c0098a = new C0098a(this.b);
                        this.a = 1;
                        if (languageDataFlow.collect(c0098a, this) == coroutine_suspended) {
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
            public a(DownloadListFragment downloadListFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = downloadListFragment;
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
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0092a(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new b(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new c(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0096d(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new e(this.b, null), 3, null);
                DownloadViewModel downloadViewModelI = DownloadListFragment.i(this.b);
                Objects.requireNonNull(downloadViewModelI);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                for (Map.Entry<String, MutableStateFlow<SoftwareStateData>> entry : downloadViewModelI.e.b.entrySet()) {
                    String key = entry.getKey();
                    MutableStateFlow<SoftwareStateData> value = entry.getValue();
                    StringBuilder sbL = spi.e.l("operate vm forEach state:", value.getValue().getState(), " percentCount:", value.getValue().getPercentCount(), " pkn:");
                    sbL.append(key);
                    ct.a(sbL.toString(), new Object[0]);
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new j7(value, key, downloadViewModelI, null), 3, null);
                }
                DownloadViewModel downloadViewModelI2 = DownloadListFragment.i(this.b);
                Objects.requireNonNull(downloadViewModelI2);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new l7(downloadViewModelI2, null), 3, null);
                DownloadListFragment.i(this.b).b(coroutineScope);
                return Unit.INSTANCE;
            }
        }

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return DownloadListFragment.this.new d(continuation);
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
                DownloadListFragment downloadListFragment = DownloadListFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(downloadListFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(downloadListFragment, state, aVar, this) == coroutine_suspended) {
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

    public DownloadListFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$special$$inlined$viewModels$default$2
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
        this.h = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DownloadViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.manage.DownloadListFragment$special$$inlined$viewModels$default$5
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
        DownloadAdapter downloadAdapter = new DownloadAdapter();
        downloadAdapter.a(R.id.iv_download_cancel, new a());
        downloadAdapter.a(R.id.pb_manager_download_button, new b());
        c block = new c();
        Intrinsics.checkNotNullParameter(block, "block");
        downloadAdapter.a = block;
        this.i = downloadAdapter;
    }

    public static final DownloadViewModel i(DownloadListFragment downloadListFragment) {
        return (DownloadViewModel) downloadListFragment.h.getValue();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentDownloadListBinding fragmentDownloadListBindingA = FragmentDownloadListBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentDownloadListBindingA, "inflate(layoutInflater)");
        return fragmentDownloadListBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        SkinTextView skinTextView;
        SkinTextView skinTextView2;
        FragmentDownloadListBinding fragmentDownloadListBinding = (FragmentDownloadListBinding) this.a;
        if (fragmentDownloadListBinding != null && (skinTextView2 = fragmentDownloadListBinding.j) != null) {
            skinTextView2.setOnClickListener(new y6(this, 8));
        }
        FragmentDownloadListBinding fragmentDownloadListBinding2 = (FragmentDownloadListBinding) this.a;
        if (fragmentDownloadListBinding2 == null || (skinTextView = fragmentDownloadListBinding2.k) == null) {
            return;
        }
        skinTextView.setOnClickListener(new gk(this, 11));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        FragmentDownloadListBinding fragmentDownloadListBinding = (FragmentDownloadListBinding) this.a;
        if (fragmentDownloadListBinding != null) {
            RecyclerView recyclerView = fragmentDownloadListBinding.h;
            recyclerView.setLayoutManager(recyclerView.getResources().getConfiguration().orientation == 1 ? new LinearLayoutManager(requireContext()) : new GridLayoutManager(requireContext(), 2));
            recyclerView.setAdapter(this.i);
            kg kgVar = kg.a;
            if (kgVar.f() || kgVar.g()) {
                ConstraintLayout constraintLayout = fragmentDownloadListBinding.g;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "it.rootFgDownloadList");
                SkinTextView skinTextView = fragmentDownloadListBinding.j;
                Intrinsics.checkNotNullExpressionValue(skinTextView, "it.tvAllStart");
                SkinTextView skinTextView2 = fragmentDownloadListBinding.k;
                Intrinsics.checkNotNullExpressionValue(skinTextView2, "it.tvAllStop");
                SkinAppCompatImageView skinAppCompatImageView = fragmentDownloadListBinding.d;
                Intrinsics.checkNotNullExpressionValue(skinAppCompatImageView, "it.ivAllStart");
                SkinAppCompatImageView skinAppCompatImageView2 = fragmentDownloadListBinding.e;
                Intrinsics.checkNotNullExpressionValue(skinAppCompatImageView2, "it.ivAllStop");
                Space space = fragmentDownloadListBinding.i;
                Intrinsics.checkNotNullExpressionValue(space, "it.sAnchorTopEnd");
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(skinTextView.getId(), 7, space.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_5));
                constraintSet.connect(skinAppCompatImageView.getId(), 7, skinTextView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
                constraintSet.connect(skinTextView2.getId(), 7, skinAppCompatImageView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_44));
                constraintSet.connect(skinAppCompatImageView2.getId(), 7, skinTextView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
                constraintSet.applyTo(constraintLayout);
            }
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new d(null), 3, null);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
        ConstraintLayout constraintLayout;
        FragmentDownloadListBinding fragmentDownloadListBinding;
        SkinTextView skinTextView;
        FragmentDownloadListBinding fragmentDownloadListBinding2;
        SkinTextView skinTextView2;
        FragmentDownloadListBinding fragmentDownloadListBinding3;
        SkinAppCompatImageView skinAppCompatImageView;
        FragmentDownloadListBinding fragmentDownloadListBinding4;
        SkinAppCompatImageView skinAppCompatImageView2;
        FragmentDownloadListBinding fragmentDownloadListBinding5;
        Space space;
        VB vb = this.a;
        FragmentDownloadListBinding fragmentDownloadListBinding6 = (FragmentDownloadListBinding) vb;
        if (fragmentDownloadListBinding6 == null || (constraintLayout = fragmentDownloadListBinding6.g) == null || (fragmentDownloadListBinding = (FragmentDownloadListBinding) vb) == null || (skinTextView = fragmentDownloadListBinding.j) == null || (fragmentDownloadListBinding2 = (FragmentDownloadListBinding) vb) == null || (skinTextView2 = fragmentDownloadListBinding2.k) == null || (fragmentDownloadListBinding3 = (FragmentDownloadListBinding) vb) == null || (skinAppCompatImageView = fragmentDownloadListBinding3.d) == null || (fragmentDownloadListBinding4 = (FragmentDownloadListBinding) vb) == null || (skinAppCompatImageView2 = fragmentDownloadListBinding4.e) == null || (fragmentDownloadListBinding5 = (FragmentDownloadListBinding) vb) == null || (space = fragmentDownloadListBinding5.i) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinTextView.getId(), 6);
        constraintSet.clear(skinTextView2.getId(), 6);
        constraintSet.clear(skinAppCompatImageView.getId(), 6);
        constraintSet.clear(skinAppCompatImageView2.getId(), 6);
        constraintSet.connect(skinTextView2.getId(), 7, space.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_5));
        constraintSet.connect(skinAppCompatImageView2.getId(), 7, skinTextView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.connect(skinTextView.getId(), 7, skinAppCompatImageView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_44));
        constraintSet.connect(skinAppCompatImageView.getId(), 7, skinTextView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.applyTo(constraintLayout);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
        ConstraintLayout constraintLayout;
        FragmentDownloadListBinding fragmentDownloadListBinding;
        SkinTextView skinTextView;
        FragmentDownloadListBinding fragmentDownloadListBinding2;
        SkinTextView skinTextView2;
        FragmentDownloadListBinding fragmentDownloadListBinding3;
        SkinAppCompatImageView skinAppCompatImageView;
        FragmentDownloadListBinding fragmentDownloadListBinding4;
        SkinAppCompatImageView skinAppCompatImageView2;
        FragmentDownloadListBinding fragmentDownloadListBinding5;
        Space space;
        VB vb = this.a;
        FragmentDownloadListBinding fragmentDownloadListBinding6 = (FragmentDownloadListBinding) vb;
        if (fragmentDownloadListBinding6 == null || (constraintLayout = fragmentDownloadListBinding6.g) == null || (fragmentDownloadListBinding = (FragmentDownloadListBinding) vb) == null || (skinTextView = fragmentDownloadListBinding.j) == null || (fragmentDownloadListBinding2 = (FragmentDownloadListBinding) vb) == null || (skinTextView2 = fragmentDownloadListBinding2.k) == null || (fragmentDownloadListBinding3 = (FragmentDownloadListBinding) vb) == null || (skinAppCompatImageView = fragmentDownloadListBinding3.d) == null || (fragmentDownloadListBinding4 = (FragmentDownloadListBinding) vb) == null || (skinAppCompatImageView2 = fragmentDownloadListBinding4.e) == null || (fragmentDownloadListBinding5 = (FragmentDownloadListBinding) vb) == null || (space = fragmentDownloadListBinding5.i) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinTextView.getId(), 6);
        constraintSet.clear(skinTextView2.getId(), 6);
        constraintSet.connect(skinTextView2.getId(), 7, space.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_5));
        constraintSet.connect(skinAppCompatImageView2.getId(), 7, skinTextView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.connect(skinTextView.getId(), 7, skinAppCompatImageView2.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_44));
        constraintSet.connect(skinAppCompatImageView.getId(), 7, skinTextView.getId(), 6, getResources().getDimensionPixelSize(R.dimen.dp_4));
        constraintSet.applyTo(constraintLayout);
    }
}
