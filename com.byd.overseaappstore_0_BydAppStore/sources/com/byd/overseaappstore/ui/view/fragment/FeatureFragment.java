package com.byd.overseaappstore.ui.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentFeatureBinding;
import com.byd.overseaappstore.databinding.ItemFeatureCategoryHotAppBinding;
import com.byd.overseaappstore.databinding.LayoutFeatureFragmentHeadBinding;
import com.byd.overseaappstore.module.ability.data.uistate.BannerUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FeatureThemGroupUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FooterHeaderUiState;
import com.byd.overseaappstore.module.ability.data.uistate.LoadingUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareThemeUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ToastUiState;
import com.byd.overseaappstore.module.ability.data.upper.Banner;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.view.adapter.BannerAdapter;
import com.byd.overseaappstore.ui.view.adapter.FeatureCategoryThemeAdapter;
import com.byd.overseaappstore.ui.view.adapter.FeatureCenterAdapter;
import com.byd.overseaappstore.ui.view.custom.CustomViewPager;
import com.byd.overseaappstore.ui.view.custom.PullToRefreshView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinView;
import com.byd.overseaappstore.ui.view.custom.transformer.CoverModeTransformer;
import com.byd.overseaappstore.ui.vm.FeatureViewModel;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import java.util.HashMap;
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
import kotlin.jvm.functions.Function1;
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
import spi.fj;
import spi.gk;
import spi.hi;
import spi.j3;
import spi.kb;
import spi.mb;
import spi.nl;
import spi.ob;
import spi.qb;
import spi.qn;
import spi.qt;
import spi.rb;
import spi.t4;
import spi.tm;
import spi.vm;
import spi.y6;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/FeatureFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentFeatureBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class FeatureFragment extends Hilt_FeatureFragment<FragmentFeatureBinding> {
    public static final /* synthetic */ int p = 0;

    @NotNull
    public final NavArgsLazy h = new NavArgsLazy(Reflection.getOrCreateKotlinClass(FeatureFragmentArgs.class), new Function0<Bundle>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$navArgs$1
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

    @NotNull
    public final Lazy i;

    @NotNull
    public final Lazy j;

    @NotNull
    public final BannerAdapter k;

    @Nullable
    public final t4 l;

    @NotNull
    public final FeatureCenterAdapter m;

    @Nullable
    public Toast n;

    @SuppressLint({"TimberArgCount", "BinaryOperationInTimber"})
    @NotNull
    public final FeatureCategoryThemeAdapter o;

    public static final class a extends Lambda implements Function3<FeatureThemTopicGroup, Integer, Integer, Unit> {
        public a() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(FeatureThemTopicGroup featureThemTopicGroup, Integer num, Integer num2) {
            FeatureThemTopicGroup featureThemTopicGroup2 = featureThemTopicGroup;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(featureThemTopicGroup2, "featureThemTopicGroup");
            kb.c cVar = kb.a;
            int id = featureThemTopicGroup2.getId();
            String topicGroupTitle = featureThemTopicGroup2.getTitle();
            Objects.requireNonNull(cVar);
            Intrinsics.checkNotNullParameter(topicGroupTitle, "topicGroupTitle");
            spi.b.h(FeatureFragment.this, new kb.b(id, topicGroupTitle));
            return Unit.INSTANCE;
        }
    }

    public static final class b extends Lambda implements Function3<FeatureThemTopicGroup, Integer, Integer, Unit> {
        public b() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(FeatureThemTopicGroup featureThemTopicGroup, Integer num, Integer num2) {
            FeatureThemTopicGroup featureThemTopicGroup2 = featureThemTopicGroup;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(featureThemTopicGroup2, "featureThemTopicGroup");
            FeatureFragment featureFragment = FeatureFragment.this;
            kb.c cVar = kb.a;
            int id = featureThemTopicGroup2.getId();
            String topicGroupTitle = featureThemTopicGroup2.getTitle();
            Objects.requireNonNull(cVar);
            Intrinsics.checkNotNullParameter(topicGroupTitle, "topicGroupTitle");
            spi.b.h(featureFragment, new kb.b(id, topicGroupTitle));
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function3<Software, ItemFeatureCategoryHotAppBinding, Integer, Unit> {
        public c() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, ItemFeatureCategoryHotAppBinding itemFeatureCategoryHotAppBinding, Integer num) {
            Software softwareInfo = software;
            ItemFeatureCategoryHotAppBinding itemFeatureCategoryHotAppBinding2 = itemFeatureCategoryHotAppBinding;
            num.intValue();
            Intrinsics.checkNotNullParameter(softwareInfo, "softwareInfo");
            Intrinsics.checkNotNullParameter(itemFeatureCategoryHotAppBinding2, "itemFeatureCategoryHotAppBinding");
            FeatureFragment featureFragment = FeatureFragment.this;
            kb.c cVar = kb.a;
            int id = softwareInfo.getId();
            Objects.requireNonNull(cVar);
            spi.b.h(featureFragment, new kb.a(id));
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function3<Software, Integer, Integer, Unit> {
        public d() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, Integer num, Integer num2) {
            Software software2 = software;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(software2, "data");
            FeatureFragment featureFragment = FeatureFragment.this;
            int i = FeatureFragment.p;
            FeatureViewModel featureViewModelI = featureFragment.i();
            Objects.requireNonNull(featureViewModelI);
            Intrinsics.checkNotNullParameter(software2, "software");
            featureViewModelI.e.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), featureViewModelI.h.parseToSoftwareOptParam(software2));
            return Unit.INSTANCE;
        }
    }

    public static final class e extends Lambda implements Function3<Software, Integer, Integer, Unit> {
        public e() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, Integer num, Integer num2) {
            Software software2 = software;
            num.intValue();
            num2.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            FeatureFragment featureFragment = FeatureFragment.this;
            kb.c cVar = kb.a;
            int id = software2.getId();
            Objects.requireNonNull(cVar);
            spi.b.h(featureFragment, new kb.a(id));
            return Unit.INSTANCE;
        }
    }

    public static final class f extends Lambda implements Function3<Software, Integer, Integer, Unit> {
        public f() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(Software software, Integer num, Integer num2) {
            Software software2 = software;
            int iIntValue = num.intValue();
            int iIntValue2 = num2.intValue();
            Intrinsics.checkNotNullParameter(software2, "software");
            String name = software2.getName();
            int versionCode = software2.getVersionCode();
            long totalSize = software2.getTotalSize();
            StringBuilder sbK = spi.d.k("adapter:name:", name, " id:", iIntValue, " position:");
            hi.i(sbK, iIntValue2, " versionCode:", versionCode, " totalSize:");
            sbK.append(totalSize);
            ct.a(sbK.toString(), new Object[0]);
            FeatureFragment featureFragment = FeatureFragment.this;
            int i = FeatureFragment.p;
            FeatureViewModel featureViewModelI = featureFragment.i();
            Objects.requireNonNull(featureViewModelI);
            Intrinsics.checkNotNullParameter(software2, "software");
            featureViewModelI.e.f(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), featureViewModelI.h.parseToSoftwareOptParam(software2));
            return Unit.INSTANCE;
        }
    }

    public static final class g extends Lambda implements Function0<ViewModelStoreOwner> {
        public g() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStoreOwner invoke() {
            FragmentActivity fragmentActivityRequireActivity = FeatureFragment.this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            return fragmentActivityRequireActivity;
        }
    }

    public static final class h extends Lambda implements Function1<PullToRefreshView, Unit> {
        public h() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(PullToRefreshView pullToRefreshView) {
            FeatureFragment featureFragment = FeatureFragment.this;
            int i = FeatureFragment.p;
            FeatureViewModel featureViewModelI = featureFragment.i();
            CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            Objects.requireNonNull(featureViewModelI);
            Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new rb(featureViewModelI, null), 3, null);
            return Unit.INSTANCE;
        }
    }

    public static final class i extends Lambda implements Function1<Integer, Unit> {
        public i() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(Integer num) {
            int iIntValue = num.intValue();
            ct.a(spi.e.c("action  ： ", iIntValue), new Object[0]);
            if (iIntValue == 0) {
                t4 t4Var = FeatureFragment.this.l;
                if (t4Var != null) {
                    t4Var.a();
                }
            } else {
                t4 t4Var2 = FeatureFragment.this.l;
                if (t4Var2 != null) {
                    t4Var2.a();
                }
                t4 t4Var3 = FeatureFragment.this.l;
                if (t4Var3 != null) {
                    t4Var3.b();
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class j extends Lambda implements Function2<Integer, Banner, Unit> {
        public j() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Unit mo6invoke(Integer num, Banner banner) {
            int iIntValue = num.intValue();
            Banner banner2 = banner;
            Intrinsics.checkNotNullParameter(banner2, "banner");
            FeatureFragment featureFragment = FeatureFragment.this;
            int i = FeatureFragment.p;
            FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
            if (fragmentFeatureBinding != null) {
                if (iIntValue != fragmentFeatureBinding.k.getCurrentItem()) {
                    t4 t4Var = featureFragment.l;
                    if (t4Var != null) {
                        t4Var.a();
                    }
                    fragmentFeatureBinding.k.setCurrentItem(iIntValue);
                    t4 t4Var2 = featureFragment.l;
                    if (t4Var2 != null) {
                        t4Var2.b();
                    }
                } else {
                    kb.c cVar = kb.a;
                    int id = banner2.getId();
                    Objects.requireNonNull(cVar);
                    spi.b.h(featureFragment, new kb.a(id));
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class k extends Lambda implements Function1<List<? extends Banner>, Unit> {
        public k() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(List<? extends Banner> list) {
            CoverModeTransformer coverModeTransformer;
            List<? extends Banner> list2 = list;
            ct.a("dataList change " + list2, new Object[0]);
            if (!(list2 == null || list2.isEmpty())) {
                FeatureFragment featureFragment = FeatureFragment.this;
                int i = FeatureFragment.p;
                FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                if (fragmentFeatureBinding != null) {
                    CustomViewPager customViewPager = fragmentFeatureBinding.k;
                    customViewPager.setAdapter(featureFragment.k);
                    ct.a(spi.e.c("adapter count ", featureFragment.k.getCount()), new Object[0]);
                    customViewPager.setOffscreenPageLimit(3);
                    customViewPager.setCurrentItem(list2.size() * 100, false);
                    if (customViewPager.getResources().getConfiguration().orientation == 1) {
                        Intrinsics.checkNotNullExpressionValue(customViewPager, "this");
                        coverModeTransformer = new CoverModeTransformer(customViewPager, customViewPager.getResources().getDimensionPixelSize(R.dimen.dp_422));
                    } else {
                        Intrinsics.checkNotNullExpressionValue(customViewPager, "this");
                        coverModeTransformer = new CoverModeTransformer(customViewPager, customViewPager.getResources().getDimensionPixelSize(R.dimen.dp_320));
                    }
                    customViewPager.setPageTransformer(true, coverModeTransformer);
                }
                t4 t4Var = FeatureFragment.this.l;
                if (t4Var != null) {
                    t4Var.a();
                }
                t4 t4Var2 = FeatureFragment.this.l;
                if (t4Var2 != null) {
                    t4Var2.b();
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final class l extends Lambda implements Function1<Long, Unit> {
        public l() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(Long l) {
            l.longValue();
            FeatureFragment featureFragment = FeatureFragment.this;
            int i = FeatureFragment.p;
            FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
            if (fragmentFeatureBinding != null) {
                if (fragmentFeatureBinding.k.getCurrentItem() >= 840000) {
                    fragmentFeatureBinding.k.setCurrentItem(840, false);
                }
                CustomViewPager customViewPager = fragmentFeatureBinding.k;
                customViewPager.setCurrentItem(customViewPager.getCurrentItem() + 1);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$1", f = "FeatureFragment.kt", i = {}, l = {297}, m = "invokeSuspend", n = {}, s = {})
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$1$1", f = "FeatureFragment.kt", i = {}, l = {298}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ FeatureFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$m$a$a, reason: collision with other inner class name */
            public static final class C0052a<T> implements FlowCollector {
                public final /* synthetic */ FeatureFragment a;

                public C0052a(FeatureFragment featureFragment) {
                    this.a = featureFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    SkinProgressBar skinProgressBar;
                    int i;
                    LoadingUiState loadingUiState = (LoadingUiState) obj;
                    FeatureFragment featureFragment = this.a;
                    int i2 = FeatureFragment.p;
                    FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                    if (fragmentFeatureBinding != null) {
                        if (loadingUiState.getVisible()) {
                            skinProgressBar = fragmentFeatureBinding.e;
                            i = 0;
                        } else {
                            skinProgressBar = fragmentFeatureBinding.e;
                            i = 8;
                        }
                        skinProgressBar.setVisibility(i);
                        fragmentFeatureBinding.j.setVisibility(i);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = featureFragment;
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
                    FeatureFragment featureFragment = this.b;
                    int i2 = FeatureFragment.p;
                    StateFlow<LoadingUiState> stateFlow = featureFragment.i().r;
                    C0052a c0052a = new C0052a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0052a, this) == coroutine_suspended) {
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

        public m(Continuation<? super m> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new m(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$10", f = "FeatureFragment.kt", i = {}, l = {511}, m = "invokeSuspend", n = {}, s = {})
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$10$1", f = "FeatureFragment.kt", i = {}, l = {512}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public /* synthetic */ Object b;
            public final /* synthetic */ FeatureFragment c;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$n$a$a, reason: collision with other inner class name */
            public static final class C0053a<T> implements FlowCollector {
                public final /* synthetic */ CoroutineScope a;
                public final /* synthetic */ FeatureFragment b;

                public C0053a(CoroutineScope coroutineScope, FeatureFragment featureFragment) {
                    this.a = coroutineScope;
                    this.b = featureFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    ct.a(spi.d.g("languageChange ", this.a.getClass().getSimpleName(), " collect it:", (String) obj), new Object[0]);
                    FeatureFragment featureFragment = this.b;
                    int i = FeatureFragment.p;
                    FeatureViewModel featureViewModelI = featureFragment.i();
                    CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
                    Context contextRequireContext = this.b.requireContext();
                    Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                    Objects.requireNonNull(featureViewModelI);
                    Object objLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new qb(featureViewModelI, contextRequireContext, null), 3, null);
                    if (objLaunch$default != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        objLaunch$default = Unit.INSTANCE;
                    }
                    return objLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLaunch$default : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.c = featureFragment;
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
                    FeatureFragment featureFragment = this.c;
                    int i2 = FeatureFragment.p;
                    SharedFlow<String> languageDataFlow = featureFragment.i().j.getLanguageDataFlow();
                    C0053a c0053a = new C0053a(coroutineScope, this.c);
                    this.a = 1;
                    if (languageDataFlow.collect(c0053a, this) == coroutine_suspended) {
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

        public n(Continuation<? super n> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new n(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$2", f = "FeatureFragment.kt", i = {}, l = {319}, m = "invokeSuspend", n = {}, s = {})
    public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        public static final class a<T> implements FlowCollector {
            public final /* synthetic */ FeatureFragment a;

            public a(FeatureFragment featureFragment) {
                this.a = featureFragment;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                Unit unit;
                ToastUiState toastUiState = (ToastUiState) obj;
                if (toastUiState.getVisible()) {
                    Toast toast = this.a.n;
                    if (toast != null) {
                        toast.cancel();
                    }
                    FeatureFragment featureFragment = this.a;
                    featureFragment.n = Toast.makeText(featureFragment.requireContext(), this.a.getResources().getText(toastUiState.getTipId()), 0);
                    Toast toast2 = this.a.n;
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

        public o(Continuation<? super o> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new o(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                int i2 = FeatureFragment.p;
                StateFlow<ToastUiState> stateFlow = featureFragment.i().t;
                a aVar = new a(FeatureFragment.this);
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$3", f = "FeatureFragment.kt", i = {}, l = {336}, m = "invokeSuspend", n = {}, s = {})
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$3$1", f = "FeatureFragment.kt", i = {}, l = {337}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ FeatureFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$p$a$a, reason: collision with other inner class name */
            public static final class C0054a<T> implements FlowCollector {
                public final /* synthetic */ FeatureFragment a;

                public C0054a(FeatureFragment featureFragment) {
                    this.a = featureFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    SkinAppCompatImageView skinAppCompatImageView;
                    int i;
                    ErrorEmptyUiState errorEmptyUiState = (ErrorEmptyUiState) obj;
                    FeatureFragment featureFragment = this.a;
                    int i2 = FeatureFragment.p;
                    FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                    if (fragmentFeatureBinding != null) {
                        if (errorEmptyUiState.getVisible()) {
                            fragmentFeatureBinding.c.setImageResource(errorEmptyUiState.getPicId());
                            fragmentFeatureBinding.i.setText(errorEmptyUiState.getTipId());
                            skinAppCompatImageView = fragmentFeatureBinding.c;
                            i = 0;
                        } else {
                            skinAppCompatImageView = fragmentFeatureBinding.c;
                            i = 8;
                        }
                        skinAppCompatImageView.setVisibility(i);
                        fragmentFeatureBinding.i.setVisibility(i);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = featureFragment;
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
                    FeatureFragment featureFragment = this.b;
                    int i2 = FeatureFragment.p;
                    StateFlow<ErrorEmptyUiState> stateFlow = featureFragment.i().v;
                    C0054a c0054a = new C0054a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0054a, this) == coroutine_suspended) {
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

        public p(Continuation<? super p> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new p(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$4", f = "FeatureFragment.kt", i = {}, l = {361}, m = "invokeSuspend", n = {}, s = {})
    public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$4$1", f = "FeatureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public /* synthetic */ Object a;
            public final /* synthetic */ FeatureFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$q$a$a, reason: collision with other inner class name */
            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$4$1$1", f = "FeatureFragment.kt", i = {}, l = {366}, m = "invokeSuspend", n = {}, s = {})
            public static final class C0055a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ FeatureFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$q$a$a$a, reason: collision with other inner class name */
                public static final class C0056a<T> implements FlowCollector {
                    public final /* synthetic */ FeatureFragment a;

                    public C0056a(FeatureFragment featureFragment) {
                        this.a = featureFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit;
                        PullToRefreshView pullToRefreshView;
                        int i;
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        FeatureFragment featureFragment = this.a;
                        int i2 = FeatureFragment.p;
                        FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                        if (fragmentFeatureBinding != null) {
                            if (zBooleanValue) {
                                pullToRefreshView = fragmentFeatureBinding.f;
                                i = 0;
                            } else {
                                pullToRefreshView = fragmentFeatureBinding.f;
                                i = 8;
                            }
                            pullToRefreshView.setVisibility(i);
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0055a(FeatureFragment featureFragment, Continuation<? super C0055a> continuation) {
                    super(2, continuation);
                    this.b = featureFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0055a(this.b, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke */
                public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C0055a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.a;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FeatureFragment featureFragment = this.b;
                        int i2 = FeatureFragment.p;
                        StateFlow<Boolean> stateFlow = featureFragment.i().x;
                        C0056a c0056a = new C0056a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0056a, this) == coroutine_suspended) {
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

            @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$4$1$2", f = "FeatureFragment.kt", i = {}, l = {381}, m = "invokeSuspend", n = {}, s = {})
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int a;
                public final /* synthetic */ FeatureFragment b;

                /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$q$a$b$a, reason: collision with other inner class name */
                public static final class C0057a<T> implements FlowCollector {
                    public final /* synthetic */ FeatureFragment a;

                    public C0057a(FeatureFragment featureFragment) {
                        this.a = featureFragment;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        TextView textView;
                        Resources resources;
                        int i;
                        FooterHeaderUiState footerHeaderUiState = (FooterHeaderUiState) obj;
                        FeatureFragment featureFragment = this.a;
                        int i2 = FeatureFragment.p;
                        FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                        Unit unit = null;
                        if (fragmentFeatureBinding != null) {
                            PullToRefreshView pullToRefreshView = fragmentFeatureBinding.f;
                            int tipId = footerHeaderUiState.getTipId();
                            ProgressBar progressBar = pullToRefreshView.n;
                            if (progressBar == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mHeaderProgressBar");
                                progressBar = null;
                            }
                            progressBar.setVisibility(8);
                            SkinImageView skinImageView = pullToRefreshView.j;
                            if (skinImageView == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mHeaderImageView");
                                skinImageView = null;
                            }
                            skinImageView.setVisibility(8);
                            if (tipId == 1) {
                                SkinImageView skinImageView2 = pullToRefreshView.j;
                                if (skinImageView2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderImageView");
                                    skinImageView2 = null;
                                }
                                skinImageView2.setVisibility(pullToRefreshView.getVisibility());
                                SkinImageView skinImageView3 = pullToRefreshView.j;
                                if (skinImageView3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderImageView");
                                    skinImageView3 = null;
                                }
                                skinImageView3.setBackgroundDrawableId(R.drawable.ic_refreshed_successed);
                                textView = pullToRefreshView.l;
                                if (textView == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderTextView");
                                    textView = null;
                                }
                                resources = pullToRefreshView.getResources();
                                i = R.string.pull_down_refresh_success;
                            } else if (tipId == 2) {
                                textView = pullToRefreshView.l;
                                if (textView == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderTextView");
                                    textView = null;
                                }
                                resources = pullToRefreshView.getResources();
                                i = R.string.pull_up_down_unknown_error;
                            } else if (tipId != 3) {
                                if (tipId == 4) {
                                    textView = pullToRefreshView.l;
                                    if (textView == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("mHeaderTextView");
                                        textView = null;
                                    }
                                    resources = pullToRefreshView.getResources();
                                    i = R.string.pull_up_down_un_connect;
                                }
                                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new nl(pullToRefreshView, null), 3, null);
                                unit = Unit.INSTANCE;
                            } else {
                                textView = pullToRefreshView.l;
                                if (textView == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderTextView");
                                    textView = null;
                                }
                                resources = pullToRefreshView.getResources();
                                i = R.string.pull_down_refresh_out_time;
                            }
                            textView.setText(resources.getString(i));
                            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new nl(pullToRefreshView, null), 3, null);
                            unit = Unit.INSTANCE;
                        }
                        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(FeatureFragment featureFragment, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.b = featureFragment;
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
                        FeatureFragment featureFragment = this.b;
                        int i2 = FeatureFragment.p;
                        StateFlow<FooterHeaderUiState> stateFlow = featureFragment.i().z;
                        C0057a c0057a = new C0057a(this.b);
                        this.a = 1;
                        if (stateFlow.collect(c0057a, this) == coroutine_suspended) {
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
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = featureFragment;
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
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C0055a(this.b, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new b(this.b, null), 3, null);
                return Unit.INSTANCE;
            }
        }

        public q(Continuation<? super q> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new q(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$5", f = "FeatureFragment.kt", i = {}, l = {406}, m = "invokeSuspend", n = {}, s = {})
    public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$5$1", f = "FeatureFragment.kt", i = {}, l = {407}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ FeatureFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$r$a$a, reason: collision with other inner class name */
            public static final class C0058a<T> implements FlowCollector {
                public final /* synthetic */ FeatureFragment a;

                public C0058a(FeatureFragment featureFragment) {
                    this.a = featureFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    SoftwareThemeUiState softwareThemeUiState = (SoftwareThemeUiState) obj;
                    FeatureFragment featureFragment = this.a;
                    int i = FeatureFragment.p;
                    FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                    if (fragmentFeatureBinding != null) {
                        if (softwareThemeUiState.getVisible()) {
                            fragmentFeatureBinding.d.setVisibility(0);
                            fragmentFeatureBinding.h.setVisibility(0);
                            featureFragment.m.submitList((List) softwareThemeUiState.getData());
                        } else {
                            fragmentFeatureBinding.d.setVisibility(8);
                            fragmentFeatureBinding.h.setVisibility(8);
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = featureFragment;
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
                    FeatureFragment featureFragment = this.b;
                    int i2 = FeatureFragment.p;
                    StateFlow<SoftwareThemeUiState> stateFlow = featureFragment.i().n;
                    C0058a c0058a = new C0058a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0058a, this) == coroutine_suspended) {
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

        public r(Continuation<? super r> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new r(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$6", f = "FeatureFragment.kt", i = {}, l = {430}, m = "invokeSuspend", n = {}, s = {})
    public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$6$1", f = "FeatureFragment.kt", i = {}, l = {431}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ FeatureFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$s$a$a, reason: collision with other inner class name */
            public static final class C0059a<T> implements FlowCollector {
                public final /* synthetic */ FeatureFragment a;

                public C0059a(FeatureFragment featureFragment) {
                    this.a = featureFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    BannerUiState bannerUiState = (BannerUiState) obj;
                    FeatureFragment featureFragment = this.a;
                    int i = FeatureFragment.p;
                    FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                    if (fragmentFeatureBinding != null) {
                        if (bannerUiState.getVisible()) {
                            fragmentFeatureBinding.k.setVisibility(0);
                            BannerAdapter bannerAdapter = featureFragment.k;
                            List<? extends Banner> data = bannerUiState.getData();
                            Objects.requireNonNull(bannerAdapter);
                            if (data != null) {
                                if (!data.isEmpty()) {
                                    synchronized (bannerAdapter.c) {
                                        bannerAdapter.c.clear();
                                        bannerAdapter.c.addAll(data);
                                    }
                                    Function1<? super List<Banner>, Unit> function1 = bannerAdapter.b;
                                    if (function1 != null) {
                                        function1.invoke(bannerAdapter.c);
                                    }
                                }
                                bannerAdapter.notifyDataSetChanged();
                            }
                        } else {
                            fragmentFeatureBinding.k.setVisibility(8);
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = featureFragment;
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
                    FeatureFragment featureFragment = this.b;
                    int i2 = FeatureFragment.p;
                    StateFlow<BannerUiState> stateFlow = featureFragment.i().l;
                    C0059a c0059a = new C0059a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0059a, this) == coroutine_suspended) {
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

        public s(Continuation<? super s> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new s(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((s) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$7", f = "FeatureFragment.kt", i = {}, l = {449}, m = "invokeSuspend", n = {}, s = {})
    public static final class t extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$7$1", f = "FeatureFragment.kt", i = {}, l = {450}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ FeatureFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$t$a$a, reason: collision with other inner class name */
            public static final class C0060a<T> implements FlowCollector {
                public final /* synthetic */ FeatureFragment a;

                public C0060a(FeatureFragment featureFragment) {
                    this.a = featureFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    FeatureThemGroupUiState featureThemGroupUiState = (FeatureThemGroupUiState) obj;
                    FeatureFragment featureFragment = this.a;
                    int i = FeatureFragment.p;
                    FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) featureFragment.a;
                    if (fragmentFeatureBinding != null) {
                        if (featureThemGroupUiState.getVisible()) {
                            fragmentFeatureBinding.g.setVisibility(0);
                            featureFragment.o.submitList(featureThemGroupUiState.getData());
                        } else {
                            fragmentFeatureBinding.g.setVisibility(8);
                        }
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = featureFragment;
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
                    FeatureFragment featureFragment = this.b;
                    int i2 = FeatureFragment.p;
                    StateFlow<FeatureThemGroupUiState> stateFlow = featureFragment.i().p;
                    C0060a c0060a = new C0060a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0060a, this) == coroutine_suspended) {
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

        public t(Continuation<? super t> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new t(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((t) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$8", f = "FeatureFragment.kt", i = {}, l = {470}, m = "invokeSuspend", n = {}, s = {})
    public static final class u extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$8$1", f = "FeatureFragment.kt", i = {}, l = {471}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ FeatureFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$u$a$a, reason: collision with other inner class name */
            public static final class C0061a<T> implements FlowCollector {
                public final /* synthetic */ FeatureFragment a;

                public C0061a(FeatureFragment featureFragment) {
                    this.a = featureFragment;
                }

                /* JADX WARN: Removed duplicated region for block: B:46:0x0081 A[PHI: r5
  0x0081: PHI (r5v4 com.byd.overseaappstore.ui.view.custom.skin.SkinTextView) = 
  (r5v2 com.byd.overseaappstore.ui.view.custom.skin.SkinTextView)
  (r5v5 com.byd.overseaappstore.ui.view.custom.skin.SkinTextView)
 binds: [B:44:0x007e, B:28:0x0051] A[DONT_GENERATE, DONT_INLINE]] */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object emit(java.lang.Object r4, kotlin.coroutines.Continuation r5) {
                    /*
                        r3 = this;
                        java.util.HashMap r4 = (java.util.HashMap) r4
                        int r4 = r4.size()
                        r5 = 0
                        if (r4 != 0) goto L24
                        com.byd.overseaappstore.ui.view.fragment.FeatureFragment r4 = r3.a
                        int r0 = com.byd.overseaappstore.ui.view.fragment.FeatureFragment.p
                        VB extends androidx.viewbinding.ViewBinding r4 = r4.a
                        com.byd.overseaappstore.databinding.FragmentFeatureBinding r4 = (com.byd.overseaappstore.databinding.FragmentFeatureBinding) r4
                        if (r4 == 0) goto L19
                        com.byd.overseaappstore.databinding.LayoutFeatureFragmentHeadBinding r4 = r4.b
                        if (r4 == 0) goto L19
                        com.byd.overseaappstore.ui.view.custom.skin.SkinTextView r5 = r4.f
                    L19:
                        if (r5 != 0) goto L1d
                        goto L84
                    L1d:
                        r4 = 8
                        r5.setVisibility(r4)
                        goto L84
                    L24:
                        r0 = 99
                        r1 = 0
                        if (r4 <= r0) goto L54
                        com.byd.overseaappstore.ui.view.fragment.FeatureFragment r4 = r3.a
                        int r0 = com.byd.overseaappstore.ui.view.fragment.FeatureFragment.p
                        VB extends androidx.viewbinding.ViewBinding r4 = r4.a
                        com.byd.overseaappstore.databinding.FragmentFeatureBinding r4 = (com.byd.overseaappstore.databinding.FragmentFeatureBinding) r4
                        if (r4 == 0) goto L3a
                        com.byd.overseaappstore.databinding.LayoutFeatureFragmentHeadBinding r4 = r4.b
                        if (r4 == 0) goto L3a
                        com.byd.overseaappstore.ui.view.custom.skin.SkinTextView r4 = r4.f
                        goto L3b
                    L3a:
                        r4 = r5
                    L3b:
                        if (r4 != 0) goto L3e
                        goto L43
                    L3e:
                        java.lang.String r0 = "99+"
                        r4.setText(r0)
                    L43:
                        com.byd.overseaappstore.ui.view.fragment.FeatureFragment r4 = r3.a
                        VB extends androidx.viewbinding.ViewBinding r4 = r4.a
                        com.byd.overseaappstore.databinding.FragmentFeatureBinding r4 = (com.byd.overseaappstore.databinding.FragmentFeatureBinding) r4
                        if (r4 == 0) goto L51
                        com.byd.overseaappstore.databinding.LayoutFeatureFragmentHeadBinding r4 = r4.b
                        if (r4 == 0) goto L51
                        com.byd.overseaappstore.ui.view.custom.skin.SkinTextView r5 = r4.f
                    L51:
                        if (r5 != 0) goto L81
                        goto L84
                    L54:
                        com.byd.overseaappstore.ui.view.fragment.FeatureFragment r0 = r3.a
                        int r2 = com.byd.overseaappstore.ui.view.fragment.FeatureFragment.p
                        VB extends androidx.viewbinding.ViewBinding r0 = r0.a
                        com.byd.overseaappstore.databinding.FragmentFeatureBinding r0 = (com.byd.overseaappstore.databinding.FragmentFeatureBinding) r0
                        if (r0 == 0) goto L65
                        com.byd.overseaappstore.databinding.LayoutFeatureFragmentHeadBinding r0 = r0.b
                        if (r0 == 0) goto L65
                        com.byd.overseaappstore.ui.view.custom.skin.SkinTextView r0 = r0.f
                        goto L66
                    L65:
                        r0 = r5
                    L66:
                        if (r0 != 0) goto L69
                        goto L70
                    L69:
                        java.lang.String r4 = java.lang.String.valueOf(r4)
                        r0.setText(r4)
                    L70:
                        com.byd.overseaappstore.ui.view.fragment.FeatureFragment r4 = r3.a
                        VB extends androidx.viewbinding.ViewBinding r4 = r4.a
                        com.byd.overseaappstore.databinding.FragmentFeatureBinding r4 = (com.byd.overseaappstore.databinding.FragmentFeatureBinding) r4
                        if (r4 == 0) goto L7e
                        com.byd.overseaappstore.databinding.LayoutFeatureFragmentHeadBinding r4 = r4.b
                        if (r4 == 0) goto L7e
                        com.byd.overseaappstore.ui.view.custom.skin.SkinTextView r5 = r4.f
                    L7e:
                        if (r5 != 0) goto L81
                        goto L84
                    L81:
                        r5.setVisibility(r1)
                    L84:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.fragment.FeatureFragment.u.a.C0061a.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = featureFragment;
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
                    FeatureFragment featureFragment = this.b;
                    int i2 = FeatureFragment.p;
                    StateFlow<HashMap<String, Integer>> stateFlow = featureFragment.i().B;
                    C0061a c0061a = new C0061a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0061a, this) == coroutine_suspended) {
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

        public u(Continuation<? super u> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new u(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((u) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$9", f = "FeatureFragment.kt", i = {}, l = {491}, m = "invokeSuspend", n = {}, s = {})
    public static final class v extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$observeData$9$1", f = "FeatureFragment.kt", i = {}, l = {494}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public /* synthetic */ Object b;
            public final /* synthetic */ FeatureFragment c;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$v$a$a, reason: collision with other inner class name */
            public static final class C0062a<T> implements FlowCollector {
                public final /* synthetic */ FeatureFragment a;
                public final /* synthetic */ CoroutineScope b;

                public C0062a(FeatureFragment featureFragment, CoroutineScope coroutineScope) {
                    this.a = featureFragment;
                    this.b = coroutineScope;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    boolean zBooleanValue = ((Boolean) obj).booleanValue();
                    if (zBooleanValue) {
                        ct.a(hi.e("sequenceDispatcher initSyncFinishCollect register tag:", zBooleanValue), new Object[0]);
                        FeatureFragment featureFragment = this.a;
                        int i = FeatureFragment.p;
                        FeatureViewModel featureViewModelI = featureFragment.i();
                        CoroutineScope coroutineScope = this.b;
                        Objects.requireNonNull(featureViewModelI);
                        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
                        for (Map.Entry<String, MutableStateFlow<SoftwareStateData>> entry : featureViewModelI.g.b.entrySet()) {
                            String key = entry.getKey();
                            MutableStateFlow<SoftwareStateData> value = entry.getValue();
                            StringBuilder sbL = spi.e.l("operate vm forEach state:", value.getValue().getState(), " percentCount:", value.getValue().getPercentCount(), " pkn:");
                            sbL.append(key);
                            ct.a(sbL.toString(), new Object[0]);
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new mb(value, key, featureViewModelI, null), 3, null);
                        }
                        FeatureViewModel featureViewModelI2 = this.a.i();
                        CoroutineScope coroutineScope2 = this.b;
                        Objects.requireNonNull(featureViewModelI2);
                        Intrinsics.checkNotNullParameter(coroutineScope2, "coroutineScope");
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new ob(featureViewModelI2, null), 3, null);
                    } else {
                        ct.a(hi.e("sequenceDispatcher initSyncFinishCollect not yet tag:", zBooleanValue), new Object[0]);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FeatureFragment featureFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.c = featureFragment;
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
                    FeatureFragment featureFragment = this.c;
                    int i2 = FeatureFragment.p;
                    StateFlow<Boolean> initSyncFinishFlow = featureFragment.i().i.getInitSyncFinishDispatcher().getInitSyncFinishFlow();
                    C0062a c0062a = new C0062a(this.c, coroutineScope);
                    this.a = 1;
                    if (initSyncFinishFlow.collect(c0062a, this) == coroutine_suspended) {
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

        public v(Continuation<? super v> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return FeatureFragment.this.new v(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((v) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeatureFragment featureFragment = FeatureFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(featureFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(featureFragment, state, aVar, this) == coroutine_suspended) {
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

    public FeatureFragment() {
        final g gVar = new g();
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) gVar.invoke();
            }
        });
        final Function0 function0 = null;
        this.i = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(FeatureViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$viewModels$default$2
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$viewModels$default$3
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
                ViewModelStoreOwner viewModelStoreOwnerM19viewModels$lambda1 = FragmentViewModelLazyKt.m19viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM19viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM19viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$viewModels$default$4
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
        this.j = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$special$$inlined$activityViewModels$default$3
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
        this.k = new BannerAdapter();
        this.l = new t4(4000L);
        FeatureCenterAdapter featureCenterAdapter = new FeatureCenterAdapter(null, 1, null);
        featureCenterAdapter.a(R.id.v_click_hot_spot, new e());
        featureCenterAdapter.a(R.id.iv_down_load, new f());
        this.m = featureCenterAdapter;
        FeatureCategoryThemeAdapter featureCategoryThemeAdapter = new FeatureCategoryThemeAdapter(null, 1, null);
        featureCategoryThemeAdapter.a(R.id.v_feature_more_hot_click, new a());
        featureCategoryThemeAdapter.a(R.id.iv_feature_detail_img, new b());
        c block = new c();
        Intrinsics.checkNotNullParameter(block, "block");
        featureCategoryThemeAdapter.d = block;
        d block2 = new d();
        Intrinsics.checkNotNullParameter(block2, "block");
        featureCategoryThemeAdapter.e = block2;
        this.o = featureCategoryThemeAdapter;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentFeatureBinding fragmentFeatureBindingA = FragmentFeatureBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentFeatureBindingA, "inflate(layoutInflater)");
        return fragmentFeatureBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void c() {
        final CustomViewPager customViewPager;
        CustomViewPager customViewPager2;
        FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) this.a;
        if (fragmentFeatureBinding != null) {
            fragmentFeatureBinding.b.e.setOnClickListener(new gk(this, 7));
            fragmentFeatureBinding.b.b.setOnClickListener(new fj(this, 11));
            fragmentFeatureBinding.f.setOnHeaderRefreshListener(new h());
            fragmentFeatureBinding.c.setOnClickListener(new y6(this, 5));
        }
        FragmentFeatureBinding fragmentFeatureBinding2 = (FragmentFeatureBinding) this.a;
        if (fragmentFeatureBinding2 != null && (customViewPager2 = fragmentFeatureBinding2.k) != null) {
            customViewPager2.setOnActionListener(new i());
        }
        FragmentFeatureBinding fragmentFeatureBinding3 = (FragmentFeatureBinding) this.a;
        if (fragmentFeatureBinding3 != null && (customViewPager = fragmentFeatureBinding3.k) != null) {
            customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$initListener$3$1

                @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.FeatureFragment$initListener$3$1$onPageSelected$1", f = "FeatureFragment.kt", i = {}, l = {212, 217}, m = "invokeSuspend", n = {}, s = {})
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int a;
                    public final /* synthetic */ int b;
                    public final /* synthetic */ CustomViewPager c;
                    public final /* synthetic */ FeatureFragment d;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(int i, CustomViewPager customViewPager, FeatureFragment featureFragment, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.b = i;
                        this.c = customViewPager;
                        this.d = featureFragment;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.b, this.c, this.d, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
                        /*
                            r7 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r7.a
                            r2 = 0
                            r3 = 200(0xc8, double:9.9E-322)
                            r5 = 2
                            r6 = 1
                            if (r1 == 0) goto L21
                            if (r1 == r6) goto L1d
                            if (r1 != r5) goto L15
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L51
                        L15:
                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r8.<init>(r0)
                            throw r8
                        L1d:
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L31
                        L21:
                            kotlin.ResultKt.throwOnFailure(r8)
                            int r8 = r7.b
                            if (r8 != r6) goto L41
                            r7.a = r6
                            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r3, r7)
                            if (r8 != r0) goto L31
                            return r0
                        L31:
                            com.byd.overseaappstore.ui.view.custom.CustomViewPager r8 = r7.c
                            com.byd.overseaappstore.ui.view.fragment.FeatureFragment r1 = r7.d
                            com.byd.overseaappstore.ui.view.adapter.BannerAdapter r1 = r1.k
                            r8.setAdapter(r1)
                            com.byd.overseaappstore.ui.view.custom.CustomViewPager r8 = r7.c
                            r1 = 841(0x349, float:1.178E-42)
                            r8.setCurrentItem(r1, r2)
                        L41:
                            int r8 = r7.b
                            r1 = 840000(0xcd140, float:1.177091E-39)
                            if (r8 != r1) goto L61
                            r7.a = r5
                            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r3, r7)
                            if (r8 != r0) goto L51
                            return r0
                        L51:
                            com.byd.overseaappstore.ui.view.custom.CustomViewPager r8 = r7.c
                            com.byd.overseaappstore.ui.view.fragment.FeatureFragment r0 = r7.d
                            com.byd.overseaappstore.ui.view.adapter.BannerAdapter r0 = r0.k
                            r8.setAdapter(r0)
                            com.byd.overseaappstore.ui.view.custom.CustomViewPager r8 = r7.c
                            r0 = 840(0x348, float:1.177E-42)
                            r8.setCurrentItem(r0, r2)
                        L61:
                            kotlin.Unit r8 = kotlin.Unit.INSTANCE
                            return r8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.fragment.FeatureFragment$initListener$3$1.a.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public final void onPageScrollStateChanged(int i2) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public final void onPageScrolled(int i2, float f2, int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public final void onPageSelected(int i2) {
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new a(i2, customViewPager, this, null), 3, null);
                }
            });
        }
        BannerAdapter bannerAdapter = this.k;
        j block = new j();
        Objects.requireNonNull(bannerAdapter);
        Intrinsics.checkNotNullParameter(block, "block");
        bannerAdapter.a = block;
        BannerAdapter bannerAdapter2 = this.k;
        k block2 = new k();
        Objects.requireNonNull(bannerAdapter2);
        Intrinsics.checkNotNullParameter(block2, "block");
        bannerAdapter2.b = block2;
        t4 t4Var = this.l;
        if (t4Var != null) {
            l block3 = new l();
            Intrinsics.checkNotNullParameter(block3, "block");
            t4Var.b = block3;
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        RecyclerView recyclerView;
        GridLayoutManager gridLayoutManager;
        BannerAdapter bannerAdapter = this.k;
        Context context = requireContext();
        Intrinsics.checkNotNullExpressionValue(context, "requireContext()");
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_12);
        Objects.requireNonNull(bannerAdapter);
        Intrinsics.checkNotNullParameter(context, "context");
        bannerAdapter.d = new qn(dimensionPixelSize);
        tm tmVar = new tm();
        qt<Bitmap>[] qtVarArr = new qt[2];
        qtVarArr[0] = new j3();
        qn qnVar = bannerAdapter.d;
        if (qnVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("roundedCorners");
            qnVar = null;
        }
        qtVarArr[1] = qnVar;
        tm tmVarP = tmVar.y(qtVarArr).p(vm.a.a(context, R.drawable.bg_app_detial_default_bg));
        Intrinsics.checkNotNullExpressionValue(tmVarP, "RequestOptions()\n       …          )\n            )");
        bannerAdapter.e = tmVarP;
        FragmentFeatureBinding fragmentFeatureBinding = (FragmentFeatureBinding) this.a;
        if (fragmentFeatureBinding != null) {
            if (getResources().getConfiguration().orientation == 1) {
                fragmentFeatureBinding.k.setPadding(getResources().getDimensionPixelSize(R.dimen.dp_36), getResources().getDimensionPixelSize(R.dimen.dp_0), getResources().getDimensionPixelSize(R.dimen.dp_36), getResources().getDimensionPixelSize(R.dimen.dp_0));
                CustomViewPager customViewPager = fragmentFeatureBinding.k;
                Intrinsics.checkNotNullExpressionValue(customViewPager, "it.vpBanner");
                customViewPager.setPageTransformer(true, new CoverModeTransformer(customViewPager, getResources().getDimensionPixelSize(R.dimen.dp_422)));
                RecyclerView recyclerView2 = fragmentFeatureBinding.g;
                recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView2.setAdapter(this.o);
                recyclerView = fragmentFeatureBinding.h;
                recyclerView.setItemAnimator(null);
                gridLayoutManager = new GridLayoutManager(requireContext(), 4);
            } else {
                fragmentFeatureBinding.k.setPadding(getResources().getDimensionPixelSize(R.dimen.dp_250), getResources().getDimensionPixelSize(R.dimen.dp_0), getResources().getDimensionPixelSize(R.dimen.dp_250), getResources().getDimensionPixelSize(R.dimen.dp_0));
                CustomViewPager customViewPager2 = fragmentFeatureBinding.k;
                Intrinsics.checkNotNullExpressionValue(customViewPager2, "it.vpBanner");
                customViewPager2.setPageTransformer(true, new CoverModeTransformer(customViewPager2, getResources().getDimensionPixelSize(R.dimen.dp_320)));
                RecyclerView recyclerView3 = fragmentFeatureBinding.g;
                recyclerView3.setLayoutManager(new GridLayoutManager(requireContext(), 2, 1, false));
                recyclerView3.setAdapter(this.o);
                recyclerView = fragmentFeatureBinding.h;
                recyclerView.setItemAnimator(null);
                gridLayoutManager = new GridLayoutManager(requireContext(), 8);
            }
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(this.m);
            CustomViewPager customViewPager3 = fragmentFeatureBinding.k;
            customViewPager3.setAdapter(this.k);
            ct.a(spi.e.c("adapter count ", this.k.getCount()), new Object[0]);
            customViewPager3.setOffscreenPageLimit(3);
            customViewPager3.setCurrentItem(840, false);
            fragmentFeatureBinding.f.v = false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new m(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new o(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new p(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new q(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new r(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new s(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new t(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new u(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new v(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new n(null), 3, null);
        FeatureViewModel.b(i());
        if (((FeatureFragmentArgs) this.h.getValue()).a == 0) {
            ct.a("featureFragment observeData normalIn", new Object[0]);
            return;
        }
        kb.c cVar = kb.a;
        int i2 = ((FeatureFragmentArgs) this.h.getValue()).a;
        Objects.requireNonNull(cVar);
        spi.b.h(this, new kb.a(i2));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding;
        ConstraintLayout constraintLayout;
        FragmentFeatureBinding fragmentFeatureBinding;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding2;
        SkinTextView skinTextView;
        FragmentFeatureBinding fragmentFeatureBinding2;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding3;
        SkinView skinView;
        FragmentFeatureBinding fragmentFeatureBinding3;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding4;
        Space space;
        FragmentFeatureBinding fragmentFeatureBinding4;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding5;
        Space space2;
        VB vb = this.a;
        FragmentFeatureBinding fragmentFeatureBinding5 = (FragmentFeatureBinding) vb;
        if (fragmentFeatureBinding5 == null || (layoutFeatureFragmentHeadBinding = fragmentFeatureBinding5.b) == null || (constraintLayout = layoutFeatureFragmentHeadBinding.a) == null || (fragmentFeatureBinding = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding2 = fragmentFeatureBinding.b) == null || (skinTextView = layoutFeatureFragmentHeadBinding2.e) == null || (fragmentFeatureBinding2 = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding3 = fragmentFeatureBinding2.b) == null || (skinView = layoutFeatureFragmentHeadBinding3.g) == null || (fragmentFeatureBinding3 = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding4 = fragmentFeatureBinding3.b) == null || (space = layoutFeatureFragmentHeadBinding4.d) == null || (fragmentFeatureBinding4 = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding5 = fragmentFeatureBinding4.b) == null || (space2 = layoutFeatureFragmentHeadBinding5.c) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinTextView.getId(), 1);
        constraintSet.clear(skinTextView.getId(), 2);
        constraintSet.connect(skinTextView.getId(), 1, space2.getId(), 2, 0);
        constraintSet.connect(skinTextView.getId(), 2, 0, 2, getResources().getDimensionPixelSize(R.dimen.dp_40));
        constraintSet.clear(skinView.getId(), 2);
        constraintSet.connect(skinView.getId(), 1, 0, 1, getResources().getDimensionPixelSize(R.dimen.dp_24));
        constraintSet.clear(space.getId(), 1);
        constraintSet.connect(space.getId(), 2, 0, 2, 0);
        constraintSet.clear(space2.getId(), 2);
        constraintSet.connect(space2.getId(), 1, skinView.getId(), 2, 0);
        constraintSet.applyTo(constraintLayout);
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding;
        ConstraintLayout constraintLayout;
        FragmentFeatureBinding fragmentFeatureBinding;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding2;
        SkinTextView skinTextView;
        FragmentFeatureBinding fragmentFeatureBinding2;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding3;
        SkinView skinView;
        FragmentFeatureBinding fragmentFeatureBinding3;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding4;
        Space space;
        FragmentFeatureBinding fragmentFeatureBinding4;
        LayoutFeatureFragmentHeadBinding layoutFeatureFragmentHeadBinding5;
        Space space2;
        VB vb = this.a;
        FragmentFeatureBinding fragmentFeatureBinding5 = (FragmentFeatureBinding) vb;
        if (fragmentFeatureBinding5 == null || (layoutFeatureFragmentHeadBinding = fragmentFeatureBinding5.b) == null || (constraintLayout = layoutFeatureFragmentHeadBinding.a) == null || (fragmentFeatureBinding = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding2 = fragmentFeatureBinding.b) == null || (skinTextView = layoutFeatureFragmentHeadBinding2.e) == null || (fragmentFeatureBinding2 = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding3 = fragmentFeatureBinding2.b) == null || (skinView = layoutFeatureFragmentHeadBinding3.g) == null || (fragmentFeatureBinding3 = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding4 = fragmentFeatureBinding3.b) == null || (space = layoutFeatureFragmentHeadBinding4.d) == null || (fragmentFeatureBinding4 = (FragmentFeatureBinding) vb) == null || (layoutFeatureFragmentHeadBinding5 = fragmentFeatureBinding4.b) == null || (space2 = layoutFeatureFragmentHeadBinding5.c) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(skinTextView.getId(), 1);
        constraintSet.clear(skinTextView.getId(), 2);
        constraintSet.connect(skinTextView.getId(), 1, space2.getId(), 2, 0);
        constraintSet.connect(skinTextView.getId(), 2, 0, 2, getResources().getDimensionPixelSize(R.dimen.dp_40));
        constraintSet.clear(skinView.getId(), 2);
        constraintSet.connect(skinView.getId(), 1, 0, 1, getResources().getDimensionPixelSize(R.dimen.dp_24));
        constraintSet.clear(space.getId(), 1);
        constraintSet.connect(space.getId(), 2, 0, 2, 0);
        constraintSet.clear(space2.getId(), 2);
        constraintSet.connect(space2.getId(), 1, skinView.getId(), 2, 0);
        constraintSet.applyTo(constraintLayout);
    }

    public final FeatureViewModel i() {
        return (FeatureViewModel) this.i.getValue();
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment, androidx.fragment.app.Fragment
    public final void onDestroyView() {
        t4 t4Var = this.l;
        if (t4Var != null) {
            t4Var.a();
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        MainViewModel mainViewModel = (MainViewModel) this.j.getValue();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        mainViewModel.b(contextRequireContext);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        t4 t4Var = this.l;
        if (t4Var != null) {
            t4Var.a();
        }
        super.onStop();
    }
}
