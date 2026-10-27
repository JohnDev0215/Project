package com.byd.overseaappstore.ui.view.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentTopicDetailBinding;
import com.byd.overseaappstore.databinding.IncludeTopBarMergeBinding;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.LoadingUiState;
import com.byd.overseaappstore.module.ability.data.uistate.TopicDetailUiState;
import com.byd.overseaappstore.module.ability.data.uistate.TopicTitleUiState;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.view.adapter.TopicDetailAdapter;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.vm.FeatureViewModel;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.byd.overseaappstore.ui.vm.TopicDetailViewModel;
import java.util.List;
import java.util.Map;
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
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ct;
import spi.e;
import spi.gk;
import spi.jt;
import spi.lt;
import spi.nt;
import spi.ot;
import spi.ro;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/TopicDetailFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentTopicDetailBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class TopicDetailFragment extends Hilt_TopicDetailFragment<FragmentTopicDetailBinding> {
    public static final /* synthetic */ int n = 0;

    @Nullable
    public IncludeTopBarMergeBinding h;

    @NotNull
    public final Lazy i;

    @NotNull
    public final Lazy j;

    @NotNull
    public final Lazy k;

    @NotNull
    public final NavArgsLazy l;

    @NotNull
    public final TopicDetailAdapter m;

    public static final class a extends Lambda implements Function3<Software, Integer, Integer, Unit> {
        public a() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, Integer num, Integer num2) {
            Software software2 = software;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            TopicDetailFragment topicDetailFragment = TopicDetailFragment.this;
            jt.b bVar = jt.a;
            int id = software2.getId();
            Objects.requireNonNull(bVar);
            spi.b.h(topicDetailFragment, new jt.a(id));
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
            TopicDetailFragment topicDetailFragment = TopicDetailFragment.this;
            int i = TopicDetailFragment.n;
            TopicDetailViewModel topicDetailViewModelJ = topicDetailFragment.j();
            Objects.requireNonNull(topicDetailViewModelJ);
            Intrinsics.checkNotNullParameter(software2, "software");
            topicDetailViewModelJ.d.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), topicDetailViewModelJ.g.parseToSoftwareOptParam(software2));
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function0<ViewModelStoreOwner> {
        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStoreOwner invoke() {
            FragmentActivity fragmentActivityRequireActivity = TopicDetailFragment.this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            return fragmentActivityRequireActivity;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$1", f = "TopicDetailFragment.kt", i = {}, l = {115}, m = "invokeSuspend", n = {}, s = {})
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$1$1", f = "TopicDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ TopicDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$d$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$1$1$1", f = "TopicDetailFragment.kt", i = {}, l = {117}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0083a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ TopicDetailFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$d$a$a$a, reason: collision with other inner class name */
                public static final class C0084a<T> implements FlowCollector {
                    public final /* synthetic */ TopicDetailFragment a;

                    public C0084a(TopicDetailFragment topicDetailFragment) {
                        this.a = topicDetailFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        TopicDetailUiState topicDetailUiState = (TopicDetailUiState) obj;
                        TopicDetailFragment topicDetailFragment = this.a;
                        int i = TopicDetailFragment.n;
                        FragmentTopicDetailBinding fragmentTopicDetailBinding = (FragmentTopicDetailBinding) topicDetailFragment.a;
                        if (fragmentTopicDetailBinding != null) {
                            if (topicDetailUiState.getVisible()) {
                                fragmentTopicDetailBinding.d.setVisibility(0);
                                topicDetailFragment.m.submitList(topicDetailUiState.getData());
                            } else {
                                fragmentTopicDetailBinding.d.setVisibility(8);
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0083a(TopicDetailFragment topicDetailFragment, Continuation<? super C0083a> continuation) {
                    super(2, continuation);
                    this.b = topicDetailFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0083a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0083a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        TopicDetailFragment topicDetailFragment = this.b;
                        int i2 = TopicDetailFragment.n;
                        StateFlow<TopicDetailUiState> stateFlow = topicDetailFragment.j().j;
                        C0084a c0084a = new C0084a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0084a, this) == coroutine_suspended) {
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
            public a(TopicDetailFragment topicDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = topicDetailFragment;
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
                BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.a, null, null, new C0083a(this.b, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return TopicDetailFragment.this.new d(continuation);
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
                TopicDetailFragment topicDetailFragment = TopicDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(topicDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(topicDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$2", f = "TopicDetailFragment.kt", i = {}, l = {139}, m = "invokeSuspend", n = {}, s = {})
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$2$1", f = "TopicDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ TopicDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$e$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$2$1$1", f = "TopicDetailFragment.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0085a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ TopicDetailFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$e$a$a$a, reason: collision with other inner class name */
                public static final class C0086a<T> implements FlowCollector {
                    public final /* synthetic */ TopicDetailFragment a;

                    public C0086a(TopicDetailFragment topicDetailFragment) {
                        this.a = topicDetailFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        TopicTitleUiState topicTitleUiState = (TopicTitleUiState) obj;
                        IncludeTopBarMergeBinding includeTopBarMergeBinding = this.a.h;
                        SkinTextView skinTextView = includeTopBarMergeBinding != null ? includeTopBarMergeBinding.c : null;
                        if (skinTextView != null) {
                            skinTextView.setText(topicTitleUiState.getData());
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0085a(TopicDetailFragment topicDetailFragment, Continuation<? super C0085a> continuation) {
                    super(2, continuation);
                    this.b = topicDetailFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0085a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0085a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        TopicDetailFragment topicDetailFragment = this.b;
                        int i2 = TopicDetailFragment.n;
                        StateFlow<TopicTitleUiState> stateFlow = topicDetailFragment.j().l;
                        C0086a c0086a = new C0086a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0086a, this) == coroutine_suspended) {
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
            public a(TopicDetailFragment topicDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = topicDetailFragment;
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
                BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.a, null, null, new C0085a(this.b, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return TopicDetailFragment.this.new e(continuation);
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
                TopicDetailFragment topicDetailFragment = TopicDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(topicDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(topicDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$3", f = "TopicDetailFragment.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$3$1", f = "TopicDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ TopicDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$f$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$3$1$1", f = "TopicDetailFragment.kt", i = {}, l = {154}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0087a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ TopicDetailFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$f$a$a$a, reason: collision with other inner class name */
                public static final class C0088a<T> implements FlowCollector {
                    public final /* synthetic */ TopicDetailFragment a;

                    public C0088a(TopicDetailFragment topicDetailFragment) {
                        this.a = topicDetailFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        SkinAppCompatImageView skinAppCompatImageView;
                        int i;
                        ErrorEmptyUiState errorEmptyUiState = (ErrorEmptyUiState) obj;
                        TopicDetailFragment topicDetailFragment = this.a;
                        int i2 = TopicDetailFragment.n;
                        FragmentTopicDetailBinding fragmentTopicDetailBinding = (FragmentTopicDetailBinding) topicDetailFragment.a;
                        if (fragmentTopicDetailBinding != null) {
                            if (errorEmptyUiState.getVisible()) {
                                fragmentTopicDetailBinding.b.setImageResource(errorEmptyUiState.getPicId());
                                fragmentTopicDetailBinding.f.setText(errorEmptyUiState.getTipId());
                                skinAppCompatImageView = fragmentTopicDetailBinding.b;
                                i = 0;
                            } else {
                                skinAppCompatImageView = fragmentTopicDetailBinding.b;
                                i = 8;
                            }
                            skinAppCompatImageView.setVisibility(i);
                            fragmentTopicDetailBinding.f.setVisibility(i);
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0087a(TopicDetailFragment topicDetailFragment, Continuation<? super C0087a> continuation) {
                    super(2, continuation);
                    this.b = topicDetailFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0087a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0087a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        TopicDetailFragment topicDetailFragment = this.b;
                        int i2 = TopicDetailFragment.n;
                        StateFlow<ErrorEmptyUiState> stateFlow = topicDetailFragment.j().n;
                        C0088a c0088a = new C0088a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0088a, this) == coroutine_suspended) {
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
            public a(TopicDetailFragment topicDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = topicDetailFragment;
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
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0087a(this.b, null), 3, null);
                TopicDetailFragment topicDetailFragment = this.b;
                int i = TopicDetailFragment.n;
                TopicDetailViewModel topicDetailViewModelJ = topicDetailFragment.j();
                Objects.requireNonNull(topicDetailViewModelJ);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                for (Map.Entry<String, MutableStateFlow<SoftwareStateData>> entry : topicDetailViewModelJ.f.b.entrySet()) {
                    String key = entry.getKey();
                    MutableStateFlow<SoftwareStateData> value = entry.getValue();
                    StringBuilder sbL = spi.e.l("operate vm forEach state:", value.getValue().getState(), " percentCount:", value.getValue().getPercentCount(), " pkn:");
                    sbL.append(key);
                    ct.a(sbL.toString(), new Object[0]);
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new lt(value, topicDetailViewModelJ, key, null), 3, null);
                }
                TopicDetailViewModel topicDetailViewModelJ2 = this.b.j();
                Objects.requireNonNull(topicDetailViewModelJ2);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new nt(topicDetailViewModelJ2, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public f(Continuation<? super f> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return TopicDetailFragment.this.new f(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TopicDetailFragment topicDetailFragment = TopicDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(topicDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(topicDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$4", f = "TopicDetailFragment.kt", i = {}, l = {180}, m = "invokeSuspend", n = {}, s = {})
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$4$1", f = "TopicDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ TopicDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$g$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$4$1$1", f = "TopicDetailFragment.kt", i = {}, l = {182}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0089a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ TopicDetailFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$g$a$a$a, reason: collision with other inner class name */
                public static final class C0090a<T> implements FlowCollector {
                    public final /* synthetic */ TopicDetailFragment a;

                    public C0090a(TopicDetailFragment topicDetailFragment) {
                        this.a = topicDetailFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        SkinProgressBar skinProgressBar;
                        int i;
                        LoadingUiState loadingUiState = (LoadingUiState) obj;
                        TopicDetailFragment topicDetailFragment = this.a;
                        int i2 = TopicDetailFragment.n;
                        FragmentTopicDetailBinding fragmentTopicDetailBinding = (FragmentTopicDetailBinding) topicDetailFragment.a;
                        if (fragmentTopicDetailBinding != null) {
                            if (loadingUiState.getVisible()) {
                                skinProgressBar = fragmentTopicDetailBinding.c;
                                i = 0;
                            } else {
                                skinProgressBar = fragmentTopicDetailBinding.c;
                                i = 8;
                            }
                            skinProgressBar.setVisibility(i);
                            fragmentTopicDetailBinding.e.setVisibility(i);
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0089a(TopicDetailFragment topicDetailFragment, Continuation<? super C0089a> continuation) {
                    super(2, continuation);
                    this.b = topicDetailFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0089a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0089a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        TopicDetailFragment topicDetailFragment = this.b;
                        int i2 = TopicDetailFragment.n;
                        StateFlow<LoadingUiState> stateFlow = topicDetailFragment.j().p;
                        C0090a c0090a = new C0090a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0090a, this) == coroutine_suspended) {
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
            public a(TopicDetailFragment topicDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = topicDetailFragment;
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
                BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.a, null, null, new C0089a(this.b, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return TopicDetailFragment.this.new g(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TopicDetailFragment topicDetailFragment = TopicDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(topicDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(topicDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$5", f = "TopicDetailFragment.kt", i = {}, l = {204}, m = "invokeSuspend", n = {}, s = {})
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$observeData$5$1", f = "TopicDetailFragment.kt", i = {}, l = {205}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public /* synthetic */ Object b;
            public final /* synthetic */ TopicDetailFragment c;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$h$a$a, reason: collision with other inner class name */
            public static final class C0091a<T> implements FlowCollector {
                public final /* synthetic */ CoroutineScope a;
                public final /* synthetic */ TopicDetailFragment b;

                public C0091a(CoroutineScope coroutineScope, TopicDetailFragment topicDetailFragment) {
                    this.a = coroutineScope;
                    this.b = topicDetailFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    ct.a(spi.d.g("languageChange ", this.a.getClass().getSimpleName(), " collect it:", (String) obj), new Object[0]);
                    TopicDetailFragment topicDetailFragment = this.b;
                    int i = TopicDetailFragment.n;
                    TopicDetailViewModel topicDetailViewModelJ = topicDetailFragment.j();
                    CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
                    Context context = this.b.requireContext();
                    Intrinsics.checkNotNullExpressionValue(context, "requireContext()");
                    int i2 = this.b.i().topicGroupId;
                    String topicGroupTitle = this.b.i().topicGroupTitle;
                    List<FeatureThemTopicGroup> data = ((FeatureViewModel) this.b.k.getValue()).p.getValue().getData();
                    Objects.requireNonNull(topicDetailViewModelJ);
                    Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(topicGroupTitle, "topicGroupTitle");
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ot(topicDetailViewModelJ, data, i2, topicGroupTitle, context, null), 3, null);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(TopicDetailFragment topicDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.c = topicDetailFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                a aVar = new a(this.c, continuation);
                aVar.b = obj;
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
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.a;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.b;
                    TopicDetailFragment topicDetailFragment = this.c;
                    int i2 = TopicDetailFragment.n;
                    SharedFlow<String> languageDataFlow = topicDetailFragment.j().h.getLanguageDataFlow();
                    C0091a c0091a = new C0091a(coroutineScope, this.c);
                    this.a = 1;
                    if (languageDataFlow.collect(c0091a, this) == coroutine_suspended) {
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

        public h(Continuation<? super h> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return TopicDetailFragment.this.new h(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TopicDetailFragment topicDetailFragment = TopicDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(topicDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(topicDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    public TopicDetailFragment() {
        final Function0 function0 = null;
        this.i = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$activityViewModels$default$3
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
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$1
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
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        final Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$2
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
        this.j = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(TopicDetailViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$5
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
        final c cVar = new c();
        final Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) cVar.invoke();
            }
        });
        this.k = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(FeatureViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$7
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy2).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$8
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
                ViewModelStoreOwner viewModelStoreOwnerM19viewModels$lambda1 = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy2);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM19viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM19viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$viewModels$default$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM19viewModels$lambda1 = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy2);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM19viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM19viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    defaultViewModelProviderFactory = this.getDefaultViewModelProviderFactory();
                }
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "(owner as? HasDefaultVie…tViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.l = new NavArgsLazy(Reflection.getOrCreateKotlinClass(TopicDetailFragmentArgs.class), new Function0<Bundle>() { // from class: com.byd.overseaappstore.ui.view.fragment.TopicDetailFragment$special$$inlined$navArgs$1
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
        TopicDetailAdapter topicDetailAdapter = new TopicDetailAdapter(null, 1, null);
        topicDetailAdapter.a(R.id.v_topic_detail_app_click_hot_spot, new a());
        topicDetailAdapter.a(R.id.pb_topic_detail_app_button, new b());
        this.m = topicDetailAdapter;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentTopicDetailBinding fragmentTopicDetailBindingA = FragmentTopicDetailBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentTopicDetailBindingA, "inflate(layoutInflater)");
        this.h = IncludeTopBarMergeBinding.a(fragmentTopicDetailBindingA.a);
        return fragmentTopicDetailBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        SkinImageView skinImageView;
        FragmentTopicDetailBinding fragmentTopicDetailBinding = (FragmentTopicDetailBinding) this.a;
        if (fragmentTopicDetailBinding != null) {
            IncludeTopBarMergeBinding includeTopBarMergeBinding = this.h;
            if (includeTopBarMergeBinding != null && (skinImageView = includeTopBarMergeBinding.b) != null) {
                skinImageView.setOnClickListener(new gk(this, 10));
            }
            fragmentTopicDetailBinding.b.setOnClickListener(new ro(fragmentTopicDetailBinding, this, 3));
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        RecyclerView recyclerView;
        IncludeTopBarMergeBinding includeTopBarMergeBinding = this.h;
        SkinTextView skinTextView = includeTopBarMergeBinding != null ? includeTopBarMergeBinding.c : null;
        if (skinTextView != null) {
            skinTextView.setText(i().topicGroupTitle);
        }
        FragmentTopicDetailBinding fragmentTopicDetailBinding = (FragmentTopicDetailBinding) this.a;
        if (fragmentTopicDetailBinding == null || (recyclerView = fragmentTopicDetailBinding.d) == null) {
            return;
        }
        recyclerView.setLayoutManager(recyclerView.getResources().getConfiguration().orientation == 2 ? new GridLayoutManager(requireContext(), 2, 1, false) : new GridLayoutManager(requireContext(), 1, 1, false));
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(this.m);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new d(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new e(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new f(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new g(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new h(null), 3, null);
        j().b(i().topicGroupId, i().topicGroupTitle, LifecycleOwnerKt.getLifecycleScope(this));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final TopicDetailFragmentArgs i() {
        return (TopicDetailFragmentArgs) this.l.getValue();
    }

    public final TopicDetailViewModel j() {
        return (TopicDetailViewModel) this.j.getValue();
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
