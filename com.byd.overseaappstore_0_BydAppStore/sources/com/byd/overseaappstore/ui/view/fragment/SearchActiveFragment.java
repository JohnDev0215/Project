package com.byd.overseaappstore.ui.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.Group;
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
import androidx.navigation.fragment.FragmentKt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.databinding.FragmentSearchActiveBinding;
import com.byd.overseaappstore.databinding.ItemHotSearchBinding;
import com.byd.overseaappstore.databinding.ItemSearchResultBinding;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FooterHeaderUiState;
import com.byd.overseaappstore.module.ability.data.uistate.HotSearchItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SearchResultItemUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ToastUiState;
import com.byd.overseaappstore.module.ability.data.upper.HotSearchItem;
import com.byd.overseaappstore.module.ability.data.upper.SearchResult;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.view.adapter.HotSearchTagAdapter;
import com.byd.overseaappstore.ui.view.adapter.SearchResultAdapter;
import com.byd.overseaappstore.ui.view.custom.PullToRefreshView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.byd.overseaappstore.ui.vm.SearchActiveViewModel;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
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
import spi.eo;
import spi.go;
import spi.hi;
import spi.io;
import spi.j4;
import spi.jo;
import spi.kv;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/SearchActiveFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentSearchActiveBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchActiveFragment extends Hilt_SearchActiveFragment<FragmentSearchActiveBinding> {
    public static final /* synthetic */ int q = 0;

    @NotNull
    public final Lazy h;

    @NotNull
    public final Lazy i;

    @NotNull
    public String j;
    public int k;
    public int l;

    @Nullable
    public Toast m;

    @NotNull
    public String n;

    @SuppressLint({"TimberArgCount"})
    @NotNull
    public final SearchResultAdapter o;

    @SuppressLint({"TimberArgCount"})
    @NotNull
    public final HotSearchTagAdapter p;

    public static final class a extends Lambda implements Function3<HotSearchItem, ItemHotSearchBinding, Integer, Unit> {
        public a() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(HotSearchItem hotSearchItem, ItemHotSearchBinding itemHotSearchBinding, Integer num) {
            HotSearchItem hotSearchItem2 = hotSearchItem;
            ItemHotSearchBinding itemHotSearchBinding2 = itemHotSearchBinding;
            num.intValue();
            Intrinsics.checkNotNullParameter(hotSearchItem2, "hotSearchItem");
            Intrinsics.checkNotNullParameter(itemHotSearchBinding2, "itemHotSearchBinding");
            SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
            eo.b bVar = eo.a;
            int id = hotSearchItem2.getId();
            Objects.requireNonNull(bVar);
            spi.b.h(searchActiveFragment, new eo.a(id));
            return Unit.INSTANCE;
        }
    }

    public static final class b extends Lambda implements Function1<PullToRefreshView, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(PullToRefreshView pullToRefreshView) {
            PullToRefreshView pullToRefreshView2 = pullToRefreshView;
            int size = SearchActiveFragment.this.o.getCurrentList().size();
            SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
            if (size == searchActiveFragment.l * searchActiveFragment.k) {
                SearchActiveViewModel searchActiveViewModelI = searchActiveFragment.i();
                SearchActiveFragment searchActiveFragment2 = SearchActiveFragment.this;
                String str = searchActiveFragment2.j;
                int i = searchActiveFragment2.l + 1;
                searchActiveFragment2.l = i;
                SearchActiveViewModel.c(searchActiveViewModelI, str, i * searchActiveFragment2.k);
            } else if (pullToRefreshView2 != null) {
                pullToRefreshView2.e(21);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$1", f = "SearchActiveFragment.kt", i = {}, l = {269}, m = "invokeSuspend", n = {}, s = {})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$1$1", f = "SearchActiveFragment.kt", i = {}, l = {270}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SearchActiveFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$c$a$a, reason: collision with other inner class name */
            public static final class C0064a<T> implements FlowCollector {
                public final /* synthetic */ SearchActiveFragment a;

                public C0064a(SearchActiveFragment searchActiveFragment) {
                    this.a = searchActiveFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    ErrorEmptyUiState errorEmptyUiState = (ErrorEmptyUiState) obj;
                    SearchActiveFragment searchActiveFragment = this.a;
                    int i = SearchActiveFragment.q;
                    FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) searchActiveFragment.a;
                    if (fragmentSearchActiveBinding != null) {
                        if (errorEmptyUiState.getVisible()) {
                            Group group = fragmentSearchActiveBinding.c;
                            if (group != null) {
                                group.setVisibility(0);
                            }
                            fragmentSearchActiveBinding.f.setImageResource(errorEmptyUiState.getPicId());
                            fragmentSearchActiveBinding.l.setText(errorEmptyUiState.getTipId());
                        } else {
                            Group group2 = fragmentSearchActiveBinding.c;
                            if (group2 != null) {
                                group2.setVisibility(8);
                            }
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SearchActiveFragment searchActiveFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = searchActiveFragment;
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
                    SearchActiveFragment searchActiveFragment = this.b;
                    int i2 = SearchActiveFragment.q;
                    StateFlow<ErrorEmptyUiState> stateFlow = searchActiveFragment.i().n;
                    C0064a c0064a = new C0064a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0064a, this) == coroutine_suspended) {
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
            return SearchActiveFragment.this.new c(continuation);
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
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(searchActiveFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(searchActiveFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$2", f = "SearchActiveFragment.kt", i = {}, l = {291}, m = "invokeSuspend", n = {}, s = {})
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$2$1", f = "SearchActiveFragment.kt", i = {}, l = {292}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SearchActiveFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$d$a$a, reason: collision with other inner class name */
            public static final class C0065a<T> implements FlowCollector {
                public final /* synthetic */ SearchActiveFragment a;

                public C0065a(SearchActiveFragment searchActiveFragment) {
                    this.a = searchActiveFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    FooterHeaderUiState footerHeaderUiState = (FooterHeaderUiState) obj;
                    SearchActiveFragment searchActiveFragment = this.a;
                    int i = SearchActiveFragment.q;
                    FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) searchActiveFragment.a;
                    if (fragmentSearchActiveBinding != null) {
                        if (footerHeaderUiState.getVisible()) {
                            fragmentSearchActiveBinding.i.e(footerHeaderUiState.getTipId());
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SearchActiveFragment searchActiveFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = searchActiveFragment;
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
                    SearchActiveFragment searchActiveFragment = this.b;
                    int i2 = SearchActiveFragment.q;
                    StateFlow<FooterHeaderUiState> stateFlow = searchActiveFragment.i().p;
                    C0065a c0065a = new C0065a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0065a, this) == coroutine_suspended) {
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

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveFragment.this.new d(continuation);
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
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(searchActiveFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(searchActiveFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$3", f = "SearchActiveFragment.kt", i = {}, l = {306}, m = "invokeSuspend", n = {}, s = {})
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public static final class a<T> implements FlowCollector {
            public final /* synthetic */ SearchActiveFragment a;

            public a(SearchActiveFragment searchActiveFragment) {
                this.a = searchActiveFragment;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                Unit unit;
                ToastUiState toastUiState = (ToastUiState) obj;
                if (toastUiState.getVisible()) {
                    r4.l--;
                    Toast toast = this.a.m;
                    if (toast != null) {
                        toast.cancel();
                    }
                    SearchActiveFragment searchActiveFragment = this.a;
                    searchActiveFragment.m = Toast.makeText(searchActiveFragment.requireContext(), this.a.getResources().getText(toastUiState.getTipId()), 0);
                    Toast toast2 = this.a.m;
                    if (toast2 != null) {
                        toast2.show();
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return unit;
                    }
                }
                return Unit.INSTANCE;
            }
        }

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveFragment.this.new e(continuation);
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
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                int i2 = SearchActiveFragment.q;
                StateFlow<ToastUiState> stateFlow = searchActiveFragment.i().r;
                a aVar = new a(SearchActiveFragment.this);
                this.a = 1;
                if (stateFlow.collect(aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$4", f = "SearchActiveFragment.kt", i = {}, l = {324}, m = "invokeSuspend", n = {}, s = {})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$4$1", f = "SearchActiveFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ SearchActiveFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$f$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$4$1$1", f = "SearchActiveFragment.kt", i = {}, l = {326}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0066a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ SearchActiveFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$f$a$a$a, reason: collision with other inner class name */
                @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$4$1$1$1", f = "SearchActiveFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                public static final class C0067a extends SuspendLambda implements Function2<SearchResultItemUiState, Continuation<? super Unit>, Object> {
                    public /* synthetic */ Object a;
                    public final /* synthetic */ SearchActiveFragment b;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C0067a(SearchActiveFragment searchActiveFragment, Continuation<? super C0067a> continuation) {
                        super(2, continuation);
                        this.b = searchActiveFragment;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        C0067a c0067a = new C0067a(this.b, continuation);
                        c0067a.a = obj;
                        return c0067a;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public final Object mo6invoke(SearchResultItemUiState searchResultItemUiState, Continuation<? super Unit> continuation) {
                        return ((C0067a) create(searchResultItemUiState, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        ResultKt.throwOnFailure(obj);
                        SearchResultItemUiState searchResultItemUiState = (SearchResultItemUiState) this.a;
                        SearchActiveFragment searchActiveFragment = this.b;
                        int i = SearchActiveFragment.q;
                        FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) searchActiveFragment.a;
                        if (fragmentSearchActiveBinding != null) {
                            if (searchResultItemUiState.getVisible()) {
                                fragmentSearchActiveBinding.e.setVisibility(0);
                                SkinTextView skinTextView = fragmentSearchActiveBinding.m;
                                SearchResult data = searchResultItemUiState.getData();
                                skinTextView.setText(data != null ? data.getSizeTip() : null);
                                SearchResultAdapter searchResultAdapter = searchActiveFragment.o;
                                SearchResult data2 = searchResultItemUiState.getData();
                                searchResultAdapter.submitList(data2 != null ? data2.getList() : null);
                            } else {
                                fragmentSearchActiveBinding.e.setVisibility(8);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0066a(SearchActiveFragment searchActiveFragment, Continuation<? super C0066a> continuation) {
                    super(2, continuation);
                    this.b = searchActiveFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0066a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0066a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SearchActiveFragment searchActiveFragment = this.b;
                        int i2 = SearchActiveFragment.q;
                        StateFlow<SearchResultItemUiState> stateFlow = searchActiveFragment.i().j;
                        C0067a c0067a = new C0067a(this.b, null);
                        this.a = 1;
                        if (FlowKt.collectLatest(stateFlow, c0067a, this) == coroutine_suspended) {
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
            public a(SearchActiveFragment searchActiveFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = searchActiveFragment;
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
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0066a(this.b, null), 3, null);
                SearchActiveFragment searchActiveFragment = this.b;
                int i = SearchActiveFragment.q;
                SearchActiveViewModel searchActiveViewModelI = searchActiveFragment.i();
                Objects.requireNonNull(searchActiveViewModelI);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                for (Map.Entry<String, MutableStateFlow<SoftwareStateData>> entry : searchActiveViewModelI.f.b.entrySet()) {
                    String key = entry.getKey();
                    MutableStateFlow<SoftwareStateData> value = entry.getValue();
                    StringBuilder sbL = spi.e.l("operate vm forEach state:", value.getValue().getState(), " percentCount:", value.getValue().getPercentCount(), " pkn:");
                    sbL.append(key);
                    ct.a(sbL.toString(), new Object[0]);
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new go(value, searchActiveViewModelI, key, null), 3, null);
                }
                SearchActiveViewModel searchActiveViewModelI2 = this.b.i();
                Objects.requireNonNull(searchActiveViewModelI2);
                Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new io(searchActiveViewModelI2, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public f(Continuation<? super f> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveFragment.this.new f(continuation);
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
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(searchActiveFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(searchActiveFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$5", f = "SearchActiveFragment.kt", i = {}, l = {351}, m = "invokeSuspend", n = {}, s = {})
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$5$1", f = "SearchActiveFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<HotSearchItemUiState, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ SearchActiveFragment b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SearchActiveFragment searchActiveFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = searchActiveFragment;
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
            public final Object mo6invoke(HotSearchItemUiState hotSearchItemUiState, Continuation<? super Unit> continuation) {
                return ((a) create(hotSearchItemUiState, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                HotSearchItemUiState hotSearchItemUiState = (HotSearchItemUiState) this.a;
                SearchActiveFragment searchActiveFragment = this.b;
                int i = SearchActiveFragment.q;
                FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) searchActiveFragment.a;
                if (fragmentSearchActiveBinding != null) {
                    if (hotSearchItemUiState.getVisible()) {
                        fragmentSearchActiveBinding.d.setVisibility(0);
                        searchActiveFragment.p.submitList(hotSearchItemUiState.getData());
                    } else {
                        fragmentSearchActiveBinding.d.setVisibility(8);
                    }
                }
                return Unit.INSTANCE;
            }
        }

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveFragment.this.new g(continuation);
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
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                int i2 = SearchActiveFragment.q;
                StateFlow<HotSearchItemUiState> stateFlow = searchActiveFragment.i().l;
                a aVar = new a(SearchActiveFragment.this, null);
                this.a = 1;
                if (FlowKt.collectLatest(stateFlow, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$6", f = "SearchActiveFragment.kt", i = {}, l = {370}, m = "invokeSuspend", n = {}, s = {})
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$6$1", f = "SearchActiveFragment.kt", i = {}, l = {372}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SearchActiveFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$h$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$6$1$1", f = "SearchActiveFragment.kt", i = {0}, l = {373}, m = "invokeSuspend", n = {"newText"}, s = {"L$0"})
            public static final class C0068a extends SuspendLambda implements Function2<String, Continuation<? super Unit>, Object> {
                public int a;
                public /* synthetic */ Object b;
                public final /* synthetic */ SearchActiveFragment c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0068a(SearchActiveFragment searchActiveFragment, Continuation<? super C0068a> continuation) {
                    super(2, continuation);
                    this.c = searchActiveFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    C0068a c0068a = new C0068a(this.c, continuation);
                    c0068a.b = obj;
                    return c0068a;
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(String str, Continuation<? super Unit> continuation) {
                    return ((C0068a) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    String str;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        String str2 = (String) this.b;
                        this.b = str2;
                        this.a = 1;
                        if (DelayKt.delay(300L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        str = str2;
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        str = (String) this.b;
                        ResultKt.throwOnFailure(obj);
                    }
                    if (str == null || str.length() == 0) {
                        ct.a(spi.d.f("onQueryTextChange fetchHotSearchTag() newText:", str), new Object[0]);
                        SearchActiveFragment searchActiveFragment = this.c;
                        int i2 = SearchActiveFragment.q;
                        searchActiveFragment.i().a(LifecycleOwnerKt.getLifecycleScope(this.c));
                        return Unit.INSTANCE;
                    }
                    String keyWork = StringsKt.trim((CharSequence) str).toString();
                    if (keyWork.length() == 0) {
                        ct.a(spi.d.g("onQueryTextChange no work newText:", str, " searchTip:", keyWork), new Object[0]);
                    } else {
                        ct.a(spi.d.g("onQueryTextChange search() newText:", str, " searchTip:", keyWork), new Object[0]);
                        SearchActiveFragment searchActiveFragment2 = this.c;
                        searchActiveFragment2.l = 1;
                        searchActiveFragment2.j = keyWork;
                        SearchResultAdapter searchResultAdapter = searchActiveFragment2.o;
                        Objects.requireNonNull(searchResultAdapter);
                        Intrinsics.checkNotNullParameter(keyWork, "keyWork");
                        searchResultAdapter.e = keyWork;
                        SearchActiveViewModel.c(this.c.i(), keyWork, 15);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SearchActiveFragment searchActiveFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = searchActiveFragment;
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
                    SearchActiveFragment searchActiveFragment = this.b;
                    int i2 = SearchActiveFragment.q;
                    StateFlow<String> stateFlow = searchActiveFragment.i().t;
                    C0068a c0068a = new C0068a(this.b, null);
                    this.a = 1;
                    if (FlowKt.collectLatest(stateFlow, c0068a, this) == coroutine_suspended) {
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

        public h(Continuation<? super h> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveFragment.this.new h(continuation);
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
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(searchActiveFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(searchActiveFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$7", f = "SearchActiveFragment.kt", i = {}, l = {402}, m = "invokeSuspend", n = {}, s = {})
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$7$1", f = "SearchActiveFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ SearchActiveFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$i$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$7$1$1", f = "SearchActiveFragment.kt", i = {}, l = {404}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0069a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ SearchActiveFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$i$a$a$a, reason: collision with other inner class name */
                public static final class C0070a<T> implements FlowCollector {
                    public final /* synthetic */ SearchActiveFragment a;

                    public C0070a(SearchActiveFragment searchActiveFragment) {
                        this.a = searchActiveFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        AppCompatEditText view;
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        ct.a(hi.e("keyboard keyboardUiStateFlow collect it:", zBooleanValue), new Object[0]);
                        SearchActiveFragment searchActiveFragment = this.a;
                        int i = SearchActiveFragment.q;
                        FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) searchActiveFragment.a;
                        if (fragmentSearchActiveBinding == null || (view = fragmentSearchActiveBinding.b) == null) {
                            unit = null;
                        } else {
                            if (zBooleanValue) {
                                SettingUtil settingUtil = SettingUtil.a;
                                FragmentActivity activity = searchActiveFragment.requireActivity();
                                Intrinsics.checkNotNullExpressionValue(activity, "requireActivity()");
                                Objects.requireNonNull(settingUtil);
                                Intrinsics.checkNotNullParameter(view, "view");
                                Intrinsics.checkNotNullParameter(activity, "activity");
                                Object systemService = activity.getSystemService("input_method");
                                Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                                kv kvVar = new kv(view, (InputMethodManager) systemService, 3);
                                Long KEYBOARD_POP_UP_DELAY = j4.a;
                                Intrinsics.checkNotNullExpressionValue(KEYBOARD_POP_UP_DELAY, "KEYBOARD_POP_UP_DELAY");
                                view.postDelayed(kvVar, KEYBOARD_POP_UP_DELAY.longValue());
                            } else {
                                SettingUtil settingUtil2 = SettingUtil.a;
                                FragmentActivity activity2 = searchActiveFragment.requireActivity();
                                Intrinsics.checkNotNullExpressionValue(activity2, "requireActivity()");
                                Objects.requireNonNull(settingUtil2);
                                Intrinsics.checkNotNullParameter(view, "view");
                                Intrinsics.checkNotNullParameter(activity2, "activity");
                                Object systemService2 = activity2.getSystemService("input_method");
                                Objects.requireNonNull(systemService2, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                                ((InputMethodManager) systemService2).hideSoftInputFromWindow(view.getWindowToken(), 2);
                            }
                            unit = Unit.INSTANCE;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0069a(SearchActiveFragment searchActiveFragment, Continuation<? super C0069a> continuation) {
                    super(2, continuation);
                    this.b = searchActiveFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0069a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0069a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SearchActiveFragment searchActiveFragment = this.b;
                        int i2 = SearchActiveFragment.q;
                        SharedFlow<Boolean> sharedFlow = searchActiveFragment.i().v;
                        C0070a c0070a = new C0070a(this.b);
                        this.a = 1;
                        if (sharedFlow.collect(c0070a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$7$1$2", f = "SearchActiveFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ SearchActiveFragment a;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(SearchActiveFragment searchActiveFragment, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.a = searchActiveFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.a, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    ResultKt.throwOnFailure(obj);
                    SearchActiveFragment searchActiveFragment = this.a;
                    int i = SearchActiveFragment.q;
                    searchActiveFragment.i().b(LifecycleOwnerKt.getLifecycleScope(this.a), true);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SearchActiveFragment searchActiveFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = searchActiveFragment;
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
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0069a(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new b(this.b, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public i(Continuation<? super i> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveFragment.this.new i(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((i) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(searchActiveFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(searchActiveFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$8", f = "SearchActiveFragment.kt", i = {}, l = {427}, m = "invokeSuspend", n = {}, s = {})
    public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$observeData$8$1", f = "SearchActiveFragment.kt", i = {}, l = {428}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SearchActiveFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$j$a$a, reason: collision with other inner class name */
            public static final class C0071a<T> implements FlowCollector {
                public final /* synthetic */ SearchActiveFragment a;

                public C0071a(SearchActiveFragment searchActiveFragment) {
                    this.a = searchActiveFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    SearchActiveFragment searchActiveFragment = this.a;
                    int i = SearchActiveFragment.q;
                    SearchActiveViewModel searchActiveViewModelI = searchActiveFragment.i();
                    CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
                    Context contextRequireContext = this.a.requireContext();
                    Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                    Objects.requireNonNull(searchActiveViewModelI);
                    Object objLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new jo(searchActiveViewModelI, contextRequireContext, null), 3, null);
                    if (objLaunch$default != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        objLaunch$default = Unit.INSTANCE;
                    }
                    return objLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLaunch$default : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SearchActiveFragment searchActiveFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = searchActiveFragment;
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
                    SearchActiveFragment searchActiveFragment = this.b;
                    int i2 = SearchActiveFragment.q;
                    SharedFlow<String> languageDataFlow = searchActiveFragment.i().h.getLanguageDataFlow();
                    C0071a c0071a = new C0071a(this.b);
                    this.a = 1;
                    if (languageDataFlow.collect(c0071a, this) == coroutine_suspended) {
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

        public j(Continuation<? super j> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SearchActiveFragment.this.new j(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((j) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(searchActiveFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(searchActiveFragment, state, aVar, this) == coroutine_suspended) {
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

    public static final class k implements TextWatcher {
        public k() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(@Nullable Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            SkinAppCompatImageView skinAppCompatImageView;
            if (String.valueOf(charSequence).length() > 0) {
                SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
                int i4 = SearchActiveFragment.q;
                FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) searchActiveFragment.a;
                skinAppCompatImageView = fragmentSearchActiveBinding != null ? fragmentSearchActiveBinding.h : null;
                if (skinAppCompatImageView != null) {
                    skinAppCompatImageView.setVisibility(0);
                }
                SearchActiveFragment.this.n = String.valueOf(charSequence);
            } else {
                SearchActiveFragment searchActiveFragment2 = SearchActiveFragment.this;
                int i5 = SearchActiveFragment.q;
                FragmentSearchActiveBinding fragmentSearchActiveBinding2 = (FragmentSearchActiveBinding) searchActiveFragment2.a;
                skinAppCompatImageView = fragmentSearchActiveBinding2 != null ? fragmentSearchActiveBinding2.h : null;
                if (skinAppCompatImageView != null) {
                    skinAppCompatImageView.setVisibility(8);
                }
            }
            SearchActiveFragment.this.i().s.setValue(String.valueOf(charSequence));
        }
    }

    public static final class l extends Lambda implements Function3<Software, ItemSearchResultBinding, Integer, Unit> {
        public l() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, ItemSearchResultBinding itemSearchResultBinding, Integer num) {
            Software software2 = software;
            ItemSearchResultBinding itemSearchResultBinding2 = itemSearchResultBinding;
            num.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            Intrinsics.checkNotNullParameter(itemSearchResultBinding2, "itemSearchResultBinding");
            SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
            eo.b bVar = eo.a;
            int id = software2.getId();
            Objects.requireNonNull(bVar);
            spi.b.h(searchActiveFragment, new eo.a(id));
            return Unit.INSTANCE;
        }
    }

    public static final class m extends Lambda implements Function1<Software, Unit> {
        public m() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(Software software) {
            AppCompatEditText appCompatEditText;
            Software software2 = software;
            Intrinsics.checkNotNullParameter(software2, "software");
            SearchActiveFragment searchActiveFragment = SearchActiveFragment.this;
            int i = SearchActiveFragment.q;
            SearchActiveViewModel searchActiveViewModelI = searchActiveFragment.i();
            Objects.requireNonNull(searchActiveViewModelI);
            Intrinsics.checkNotNullParameter(software2, "software");
            searchActiveViewModelI.d.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), searchActiveViewModelI.g.parseToSoftwareOptParam(software2));
            FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) SearchActiveFragment.this.a;
            if (fragmentSearchActiveBinding != null && (appCompatEditText = fragmentSearchActiveBinding.b) != null) {
                appCompatEditText.clearFocus();
            }
            return Unit.INSTANCE;
        }
    }

    public SearchActiveFragment() {
        final Function0 function0 = null;
        this.h = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$activityViewModels$default$3
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
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$viewModels$default$2
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
        this.i = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SearchActiveViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$special$$inlined$viewModels$default$5
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
        this.j = "";
        this.k = 15;
        this.l = 1;
        this.n = "";
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(null, 1, null);
        l block = new l();
        Intrinsics.checkNotNullParameter(block, "block");
        searchResultAdapter.a = block;
        m block2 = new m();
        Intrinsics.checkNotNullParameter(block2, "block");
        searchResultAdapter.d = block2;
        this.o = searchResultAdapter;
        HotSearchTagAdapter hotSearchTagAdapter = new HotSearchTagAdapter();
        a block3 = new a();
        Intrinsics.checkNotNullParameter(block3, "block");
        hotSearchTagAdapter.a = block3;
        this.p = hotSearchTagAdapter;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentSearchActiveBinding fragmentSearchActiveBindingA = FragmentSearchActiveBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentSearchActiveBindingA, "inflate(layoutInflater)");
        return fragmentSearchActiveBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    @SuppressLint({"ClickableViewAccessibility"})
    public final void c() {
        SkinAppCompatImageView skinAppCompatImageView;
        AppCompatEditText appCompatEditText;
        View view;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        RecyclerView recyclerView3;
        SkinAppCompatImageView skinAppCompatImageView2;
        PullToRefreshView pullToRefreshView;
        SkinAppCompatImageView skinAppCompatImageView3;
        FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) this.a;
        final int i2 = 0;
        if (fragmentSearchActiveBinding != null && (skinAppCompatImageView3 = fragmentSearchActiveBinding.g) != null) {
            skinAppCompatImageView3.setOnClickListener(new View.OnClickListener(this) { // from class: spi.zn
                public final /* synthetic */ SearchActiveFragment b;

                {
                    this.b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AppCompatEditText appCompatEditText2;
                    switch (i2) {
                        case 0:
                            SearchActiveFragment this$0 = this.b;
                            int i3 = SearchActiveFragment.q;
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            FragmentKt.findNavController(this$0).navigateUp();
                            break;
                        default:
                            SearchActiveFragment this$02 = this.b;
                            int i4 = SearchActiveFragment.q;
                            Intrinsics.checkNotNullParameter(this$02, "this$0");
                            this$02.i().b(LifecycleOwnerKt.getLifecycleScope(this$02), true);
                            FragmentSearchActiveBinding fragmentSearchActiveBinding2 = (FragmentSearchActiveBinding) this$02.a;
                            if (fragmentSearchActiveBinding2 != null && (appCompatEditText2 = fragmentSearchActiveBinding2.b) != null) {
                                appCompatEditText2.setText("");
                                break;
                            }
                            break;
                    }
                }
            });
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding2 = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding2 != null && (pullToRefreshView = fragmentSearchActiveBinding2.i) != null) {
            pullToRefreshView.setOnFooterRefreshListener(new b());
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding3 = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding3 != null && (skinAppCompatImageView2 = fragmentSearchActiveBinding3.f) != null) {
            skinAppCompatImageView2.setOnClickListener(new View.OnClickListener(this) { // from class: spi.ao
                public final /* synthetic */ SearchActiveFragment b;

                {
                    this.b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AppCompatEditText appCompatEditText2;
                    AppCompatEditText appCompatEditText3;
                    switch (i2) {
                        case 0:
                            SearchActiveFragment this$0 = this.b;
                            int i3 = SearchActiveFragment.q;
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            FragmentSearchActiveBinding fragmentSearchActiveBinding4 = (FragmentSearchActiveBinding) this$0.a;
                            if (fragmentSearchActiveBinding4 != null && (appCompatEditText3 = fragmentSearchActiveBinding4.b) != null) {
                                appCompatEditText3.clearFocus();
                            }
                            this$0.i().b(LifecycleOwnerKt.getLifecycleScope(this$0), false);
                            if (!(this$0.j.length() == 0)) {
                                String keyWork = StringsKt.trim((CharSequence) this$0.j).toString();
                                if (!(keyWork.length() == 0)) {
                                    ct.a(d.g("icNoSearchResults search()  searchKeyword:", this$0.j, " searchTip:", keyWork), new Object[0]);
                                    this$0.l = 1;
                                    this$0.j = keyWork;
                                    SearchResultAdapter searchResultAdapter = this$0.o;
                                    Objects.requireNonNull(searchResultAdapter);
                                    Intrinsics.checkNotNullParameter(keyWork, "keyWork");
                                    searchResultAdapter.e = keyWork;
                                    SearchActiveViewModel.c(this$0.i(), keyWork, 15);
                                } else {
                                    ct.a(d.g("icNoSearchResults no work searchKeyword:", this$0.j, " searchTip:", keyWork), new Object[0]);
                                }
                            } else {
                                ct.a(d.f("icNoSearchResults  fetchHotSearchTag() searchKeyword:", this$0.j), new Object[0]);
                                this$0.i().a(LifecycleOwnerKt.getLifecycleScope(this$0));
                            }
                            break;
                        default:
                            SearchActiveFragment this$02 = this.b;
                            int i4 = SearchActiveFragment.q;
                            Intrinsics.checkNotNullParameter(this$02, "this$0");
                            FragmentSearchActiveBinding fragmentSearchActiveBinding5 = (FragmentSearchActiveBinding) this$02.a;
                            if (fragmentSearchActiveBinding5 != null && (appCompatEditText2 = fragmentSearchActiveBinding5.b) != null) {
                                appCompatEditText2.clearFocus();
                            }
                            this$02.i().b(LifecycleOwnerKt.getLifecycleScope(this$02), false);
                            break;
                    }
                }
            });
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding4 = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding4 != null && (recyclerView3 = fragmentSearchActiveBinding4.k) != null) {
            recyclerView3.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment$initListener$4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public final void onScrollStateChanged(@NotNull RecyclerView recyclerView4, int i3) {
                    AppCompatEditText appCompatEditText2;
                    Intrinsics.checkNotNullParameter(recyclerView4, "recyclerView");
                    super.onScrollStateChanged(recyclerView4, i3);
                    if (i3 == 1) {
                        SearchActiveFragment searchActiveFragment = this.a;
                        int i4 = SearchActiveFragment.q;
                        searchActiveFragment.i().b(LifecycleOwnerKt.getLifecycleScope(this.a), false);
                        FragmentSearchActiveBinding fragmentSearchActiveBinding5 = (FragmentSearchActiveBinding) this.a.a;
                        if (fragmentSearchActiveBinding5 == null || (appCompatEditText2 = fragmentSearchActiveBinding5.b) == null) {
                            return;
                        }
                        appCompatEditText2.clearFocus();
                    }
                }
            });
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding5 = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding5 != null && (recyclerView2 = fragmentSearchActiveBinding5.k) != null) {
            recyclerView2.setOnTouchListener(new View.OnTouchListener() { // from class: spi.co
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    AppCompatEditText appCompatEditText2;
                    SearchActiveFragment this$0 = this.a;
                    int i3 = SearchActiveFragment.q;
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    if (motionEvent != null && motionEvent.getAction() == 0) {
                        FragmentSearchActiveBinding fragmentSearchActiveBinding6 = (FragmentSearchActiveBinding) this$0.a;
                        if (fragmentSearchActiveBinding6 != null && (appCompatEditText2 = fragmentSearchActiveBinding6.b) != null) {
                            appCompatEditText2.clearFocus();
                        }
                        this$0.i().b(LifecycleOwnerKt.getLifecycleScope(this$0), false);
                    }
                    return false;
                }
            });
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding6 = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding6 != null && (recyclerView = fragmentSearchActiveBinding6.j) != null) {
            recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: spi.do
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    AppCompatEditText appCompatEditText2;
                    SearchActiveFragment this$0 = this.a;
                    int i3 = SearchActiveFragment.q;
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    FragmentSearchActiveBinding fragmentSearchActiveBinding7 = (FragmentSearchActiveBinding) this$0.a;
                    if (fragmentSearchActiveBinding7 != null && (appCompatEditText2 = fragmentSearchActiveBinding7.b) != null) {
                        appCompatEditText2.clearFocus();
                    }
                    this$0.i().b(LifecycleOwnerKt.getLifecycleScope(this$0), false);
                    return false;
                }
            });
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding7 = (FragmentSearchActiveBinding) this.a;
        final int i3 = 1;
        if (fragmentSearchActiveBinding7 != null && (view = fragmentSearchActiveBinding7.n) != null) {
            view.setOnClickListener(new View.OnClickListener(this) { // from class: spi.ao
                public final /* synthetic */ SearchActiveFragment b;

                {
                    this.b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AppCompatEditText appCompatEditText2;
                    AppCompatEditText appCompatEditText3;
                    switch (i3) {
                        case 0:
                            SearchActiveFragment this$0 = this.b;
                            int i32 = SearchActiveFragment.q;
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            FragmentSearchActiveBinding fragmentSearchActiveBinding42 = (FragmentSearchActiveBinding) this$0.a;
                            if (fragmentSearchActiveBinding42 != null && (appCompatEditText3 = fragmentSearchActiveBinding42.b) != null) {
                                appCompatEditText3.clearFocus();
                            }
                            this$0.i().b(LifecycleOwnerKt.getLifecycleScope(this$0), false);
                            if (!(this$0.j.length() == 0)) {
                                String keyWork = StringsKt.trim((CharSequence) this$0.j).toString();
                                if (!(keyWork.length() == 0)) {
                                    ct.a(d.g("icNoSearchResults search()  searchKeyword:", this$0.j, " searchTip:", keyWork), new Object[0]);
                                    this$0.l = 1;
                                    this$0.j = keyWork;
                                    SearchResultAdapter searchResultAdapter = this$0.o;
                                    Objects.requireNonNull(searchResultAdapter);
                                    Intrinsics.checkNotNullParameter(keyWork, "keyWork");
                                    searchResultAdapter.e = keyWork;
                                    SearchActiveViewModel.c(this$0.i(), keyWork, 15);
                                } else {
                                    ct.a(d.g("icNoSearchResults no work searchKeyword:", this$0.j, " searchTip:", keyWork), new Object[0]);
                                }
                            } else {
                                ct.a(d.f("icNoSearchResults  fetchHotSearchTag() searchKeyword:", this$0.j), new Object[0]);
                                this$0.i().a(LifecycleOwnerKt.getLifecycleScope(this$0));
                            }
                            break;
                        default:
                            SearchActiveFragment this$02 = this.b;
                            int i4 = SearchActiveFragment.q;
                            Intrinsics.checkNotNullParameter(this$02, "this$0");
                            FragmentSearchActiveBinding fragmentSearchActiveBinding52 = (FragmentSearchActiveBinding) this$02.a;
                            if (fragmentSearchActiveBinding52 != null && (appCompatEditText2 = fragmentSearchActiveBinding52.b) != null) {
                                appCompatEditText2.clearFocus();
                            }
                            this$02.i().b(LifecycleOwnerKt.getLifecycleScope(this$02), false);
                            break;
                    }
                }
            });
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding8 = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding8 != null && (appCompatEditText = fragmentSearchActiveBinding8.b) != null) {
            appCompatEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: spi.bo
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    SearchActiveFragment this$0 = this.a;
                    int i4 = SearchActiveFragment.q;
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    if (z) {
                        return;
                    }
                    this$0.i().b(LifecycleOwnerKt.getLifecycleScope(this$0), false);
                }
            });
        }
        FragmentSearchActiveBinding fragmentSearchActiveBinding9 = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding9 == null || (skinAppCompatImageView = fragmentSearchActiveBinding9.h) == null) {
            return;
        }
        skinAppCompatImageView.setOnClickListener(new View.OnClickListener(this) { // from class: spi.zn
            public final /* synthetic */ SearchActiveFragment b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AppCompatEditText appCompatEditText2;
                switch (i3) {
                    case 0:
                        SearchActiveFragment this$0 = this.b;
                        int i32 = SearchActiveFragment.q;
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        FragmentKt.findNavController(this$0).navigateUp();
                        break;
                    default:
                        SearchActiveFragment this$02 = this.b;
                        int i4 = SearchActiveFragment.q;
                        Intrinsics.checkNotNullParameter(this$02, "this$0");
                        this$02.i().b(LifecycleOwnerKt.getLifecycleScope(this$02), true);
                        FragmentSearchActiveBinding fragmentSearchActiveBinding22 = (FragmentSearchActiveBinding) this$02.a;
                        if (fragmentSearchActiveBinding22 != null && (appCompatEditText2 = fragmentSearchActiveBinding22.b) != null) {
                            appCompatEditText2.setText("");
                            break;
                        }
                        break;
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.fragment.SearchActiveFragment.d():void");
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new c(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new d(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new e(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new f(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new g(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new h(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new i(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new j(null), 3, null);
        i().a(LifecycleOwnerKt.getLifecycleScope(this));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
    }

    public final SearchActiveViewModel i() {
        return (SearchActiveViewModel) this.i.getValue();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(@NotNull Configuration newConfig) {
        AppCompatEditText appCompatEditText;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) this.a;
        this.n = String.valueOf((fragmentSearchActiveBinding == null || (appCompatEditText = fragmentSearchActiveBinding.b) == null) ? null : appCompatEditText.getText());
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        AppCompatEditText appCompatEditText;
        super.onResume();
        MainViewModel mainViewModel = (MainViewModel) this.h.getValue();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        mainViewModel.b(contextRequireContext);
        FragmentSearchActiveBinding fragmentSearchActiveBinding = (FragmentSearchActiveBinding) this.a;
        if (fragmentSearchActiveBinding == null || (appCompatEditText = fragmentSearchActiveBinding.b) == null) {
            return;
        }
        appCompatEditText.addTextChangedListener(new k());
    }
}
