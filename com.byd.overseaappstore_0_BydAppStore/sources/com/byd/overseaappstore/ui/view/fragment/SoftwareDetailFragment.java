package com.byd.overseaappstore.ui.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
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
import androidx.navigation.NavArgsLazy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.databinding.FragmentSoftwareDetailBinding;
import com.byd.overseaappstore.databinding.IncludeTopBarMergeBinding;
import com.byd.overseaappstore.databinding.ItemSoftwareDetailPreviewImgBinding;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.LoadingUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareDetailUiState;
import com.byd.overseaappstore.module.ability.data.upper.ImagePath;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetail;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetailPreviewImages;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.ui.view.adapter.SoftwareDetailPreviewImageAdapter;
import com.byd.overseaappstore.ui.view.custom.DownloadProgressButtonView;
import com.byd.overseaappstore.ui.view.custom.HorizontalRecycleView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinAppCompatImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinProgressBar;
import com.byd.overseaappstore.ui.view.custom.skin.SkinTextView;
import com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment;
import com.byd.overseaappstore.ui.vm.MainViewModel;
import com.byd.overseaappstore.ui.vm.SoftwareDetailViewModel;
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
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.e;
import spi.eq;
import spi.fj;
import spi.gk;
import spi.lq;
import spi.ro;
import spi.vd;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/byd/overseaappstore/ui/view/fragment/SoftwareDetailFragment;", "Lcom/byd/overseaappstore/ui/view/base/BaseFragment;", "Lcom/byd/overseaappstore/databinding/FragmentSoftwareDetailBinding;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class SoftwareDetailFragment extends Hilt_SoftwareDetailFragment<FragmentSoftwareDetailBinding> {
    public static final /* synthetic */ int o = 0;

    @Nullable
    public IncludeTopBarMergeBinding h;

    @NotNull
    public final Lazy i;

    @NotNull
    public final Lazy j;

    @NotNull
    public final SoftwareDetailPreviewImageAdapter k;

    @Nullable
    public ViewTreeObserver.OnPreDrawListener l;

    @Nullable
    public ViewTreeObserver.OnPreDrawListener m;

    @NotNull
    public final NavArgsLazy n;

    public static final class a implements View.OnAttachStateChangeListener {
        public final /* synthetic */ View a;
        public final /* synthetic */ SoftwareDetailFragment b;

        public a(View view, SoftwareDetailFragment softwareDetailFragment) {
            this.a = view;
            this.b = softwareDetailFragment;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "view");
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(@NotNull View view) {
            SkinTextView skinTextView;
            ViewTreeObserver viewTreeObserver;
            Intrinsics.checkNotNullParameter(view, "view");
            this.a.removeOnAttachStateChangeListener(this);
            SoftwareDetailFragment softwareDetailFragment = this.b;
            int i = SoftwareDetailFragment.o;
            ct.a("tvIntroduction doOnDetach binding:?" + ((FragmentSoftwareDetailBinding) softwareDetailFragment.a) + " introductionLineCountListener:" + softwareDetailFragment.l, new Object[0]);
            SoftwareDetailFragment softwareDetailFragment2 = this.b;
            ViewTreeObserver.OnPreDrawListener onPreDrawListener = softwareDetailFragment2.l;
            if (onPreDrawListener != null) {
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment2.a;
                if (fragmentSoftwareDetailBinding != null && (skinTextView = fragmentSoftwareDetailBinding.m) != null && (viewTreeObserver = skinTextView.getViewTreeObserver()) != null) {
                    viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
                }
                this.b.l = null;
            }
        }
    }

    public static final class b implements View.OnAttachStateChangeListener {
        public final /* synthetic */ View a;
        public final /* synthetic */ SoftwareDetailFragment b;

        public b(View view, SoftwareDetailFragment softwareDetailFragment) {
            this.a = view;
            this.b = softwareDetailFragment;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "view");
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(@NotNull View view) {
            TextView textView;
            ViewTreeObserver viewTreeObserver;
            Intrinsics.checkNotNullParameter(view, "view");
            this.a.removeOnAttachStateChangeListener(this);
            SoftwareDetailFragment softwareDetailFragment = this.b;
            int i = SoftwareDetailFragment.o;
            ct.a("tvVersionIntroduction doOnDetach binding:?" + ((FragmentSoftwareDetailBinding) softwareDetailFragment.a) + " introductionLineCountListener:" + softwareDetailFragment.m, new Object[0]);
            SoftwareDetailFragment softwareDetailFragment2 = this.b;
            ViewTreeObserver.OnPreDrawListener onPreDrawListener = softwareDetailFragment2.m;
            if (onPreDrawListener != null) {
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment2.a;
                if (fragmentSoftwareDetailBinding != null && (textView = fragmentSoftwareDetailBinding.s) != null && (viewTreeObserver = textView.getViewTreeObserver()) != null) {
                    viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
                }
                this.b.m = null;
            }
        }
    }

    public static final class c implements ViewTreeObserver.OnPreDrawListener {
        public c() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            SkinTextView skinTextView;
            ViewTreeObserver viewTreeObserver;
            SkinTextView skinTextView2;
            SkinTextView skinTextView3;
            SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
            int i = SoftwareDetailFragment.o;
            FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment.a;
            int lineCount = (fragmentSoftwareDetailBinding == null || (skinTextView3 = fragmentSoftwareDetailBinding.m) == null) ? 0 : skinTextView3.getLineCount();
            if (lineCount > 3) {
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding2 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                SkinImageView skinImageView = fragmentSoftwareDetailBinding2 != null ? fragmentSoftwareDetailBinding2.d : null;
                if (skinImageView != null) {
                    skinImageView.setVisibility(0);
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding3 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                SkinTextView skinTextView4 = fragmentSoftwareDetailBinding3 != null ? fragmentSoftwareDetailBinding3.m : null;
                if (skinTextView4 != null) {
                    skinTextView4.setMaxLines(3);
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding4 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                if (fragmentSoftwareDetailBinding4 == null || (skinTextView2 = fragmentSoftwareDetailBinding4.m) == null || (viewTreeObserver = skinTextView2.getViewTreeObserver()) == null) {
                    return true;
                }
            } else {
                if (lineCount <= 0) {
                    return true;
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding5 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                SkinImageView skinImageView2 = fragmentSoftwareDetailBinding5 != null ? fragmentSoftwareDetailBinding5.d : null;
                if (skinImageView2 != null) {
                    skinImageView2.setVisibility(4);
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding6 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                if (fragmentSoftwareDetailBinding6 == null || (skinTextView = fragmentSoftwareDetailBinding6.m) == null || (viewTreeObserver = skinTextView.getViewTreeObserver()) == null) {
                    return true;
                }
            }
            viewTreeObserver.removeOnPreDrawListener(this);
            return true;
        }
    }

    public static final class d implements ViewTreeObserver.OnPreDrawListener {
        public d() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            TextView textView;
            ViewTreeObserver viewTreeObserver;
            TextView textView2;
            TextView textView3;
            SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
            int i = SoftwareDetailFragment.o;
            FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment.a;
            int lineCount = (fragmentSoftwareDetailBinding == null || (textView3 = fragmentSoftwareDetailBinding.s) == null) ? 0 : textView3.getLineCount();
            if (lineCount > 3) {
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding2 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                ImageView imageView = fragmentSoftwareDetailBinding2 != null ? fragmentSoftwareDetailBinding2.e : null;
                if (imageView != null) {
                    imageView.setVisibility(0);
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding3 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                TextView textView4 = fragmentSoftwareDetailBinding3 != null ? fragmentSoftwareDetailBinding3.s : null;
                if (textView4 != null) {
                    textView4.setMaxLines(3);
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding4 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                if (fragmentSoftwareDetailBinding4 == null || (textView2 = fragmentSoftwareDetailBinding4.s) == null || (viewTreeObserver = textView2.getViewTreeObserver()) == null) {
                    return true;
                }
            } else {
                if (lineCount <= 0) {
                    return true;
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding5 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                ImageView imageView2 = fragmentSoftwareDetailBinding5 != null ? fragmentSoftwareDetailBinding5.e : null;
                if (imageView2 != null) {
                    imageView2.setVisibility(4);
                }
                FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding6 = (FragmentSoftwareDetailBinding) SoftwareDetailFragment.this.a;
                if (fragmentSoftwareDetailBinding6 == null || (textView = fragmentSoftwareDetailBinding6.s) == null || (viewTreeObserver = textView.getViewTreeObserver()) == null) {
                    return true;
                }
            }
            viewTreeObserver.removeOnPreDrawListener(this);
            return true;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$1", f = "SoftwareDetailFragment.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {})
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$1$1", f = "SoftwareDetailFragment.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SoftwareDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$e$a$a, reason: collision with other inner class name */
            public static final class C0078a<T> implements FlowCollector {
                public final /* synthetic */ SoftwareDetailFragment a;

                public C0078a(SoftwareDetailFragment softwareDetailFragment) {
                    this.a = softwareDetailFragment;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Software software;
                    Software software2;
                    Software software3;
                    Software software4;
                    Software software5;
                    SoftwareDetailUiState softwareDetailUiState = (SoftwareDetailUiState) obj;
                    SoftwareDetailFragment softwareDetailFragment = this.a;
                    int i = SoftwareDetailFragment.o;
                    FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment.a;
                    if (fragmentSoftwareDetailBinding != null) {
                        boolean z = true;
                        if (softwareDetailUiState.getVisible()) {
                            fragmentSoftwareDetailBinding.i.setVisibility(0);
                            vd vdVar = vd.a;
                            Context contextRequireContext = softwareDetailFragment.requireContext();
                            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                            SoftwareDetail data = softwareDetailUiState.getData();
                            String icon = (data == null || (software5 = data.getSoftware()) == null) ? null : software5.getIcon();
                            ImageView imageView = fragmentSoftwareDetailBinding.c;
                            Intrinsics.checkNotNullExpressionValue(imageView, "it.ivIcon");
                            vdVar.a(contextRequireContext, icon, imageView, SoftwareDetailFragment.class);
                            SkinTextView skinTextView = fragmentSoftwareDetailBinding.o;
                            SoftwareDetail data2 = softwareDetailUiState.getData();
                            skinTextView.setText((data2 == null || (software4 = data2.getSoftware()) == null) ? null : software4.getName());
                            SkinTextView skinTextView2 = fragmentSoftwareDetailBinding.q;
                            SoftwareDetail data3 = softwareDetailUiState.getData();
                            skinTextView2.setText((data3 == null || (software3 = data3.getSoftware()) == null) ? null : software3.getClassifyName());
                            SkinTextView skinTextView3 = fragmentSoftwareDetailBinding.p;
                            SoftwareDetail data4 = softwareDetailUiState.getData();
                            skinTextView3.setText((data4 == null || (software2 = data4.getSoftware()) == null) ? null : software2.getSizeTip());
                            SkinTextView skinTextView4 = fragmentSoftwareDetailBinding.u;
                            SoftwareDetail data5 = softwareDetailUiState.getData();
                            skinTextView4.setText((data5 == null || (software = data5.getSoftware()) == null) ? null : software.getVersionTip());
                            SkinTextView skinTextView5 = fragmentSoftwareDetailBinding.r;
                            SoftwareDetail data6 = softwareDetailUiState.getData();
                            skinTextView5.setText(data6 != null ? data6.getUpdateTimeTip() : null);
                            fragmentSoftwareDetailBinding.m.setMaxLines(3);
                            SoftwareDetail data7 = softwareDetailUiState.getData();
                            String versionDescription = data7 != null ? data7.getVersionDescription() : null;
                            if (versionDescription != null && versionDescription.length() != 0) {
                                z = false;
                            }
                            TextView textView = fragmentSoftwareDetailBinding.t;
                            if (z) {
                                textView.setVisibility(8);
                                fragmentSoftwareDetailBinding.s.setVisibility(8);
                                fragmentSoftwareDetailBinding.e.setVisibility(8);
                            } else {
                                textView.setVisibility(0);
                                fragmentSoftwareDetailBinding.s.setVisibility(0);
                                TextView textView2 = fragmentSoftwareDetailBinding.s;
                                SoftwareDetail data8 = softwareDetailUiState.getData();
                                textView2.setText(data8 != null ? data8.getVersionDescription() : null);
                            }
                            SkinTextView skinTextView6 = fragmentSoftwareDetailBinding.m;
                            SoftwareDetail data9 = softwareDetailUiState.getData();
                            skinTextView6.setText(data9 != null ? data9.getDescription() : null);
                            fragmentSoftwareDetailBinding.s.setMaxLines(3);
                            SkinTextView skinTextView7 = fragmentSoftwareDetailBinding.k;
                            SoftwareDetail data10 = softwareDetailUiState.getData();
                            skinTextView7.setText(data10 != null ? data10.getScreenCompatibilityTip() : null);
                            SoftwareDetailPreviewImageAdapter softwareDetailPreviewImageAdapter = softwareDetailFragment.k;
                            SoftwareDetail data11 = softwareDetailUiState.getData();
                            softwareDetailPreviewImageAdapter.submitList(data11 != null ? data11.getImages() : null);
                        } else {
                            fragmentSoftwareDetailBinding.i.setVisibility(8);
                        }
                        obj = Unit.INSTANCE;
                    }
                    return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SoftwareDetailFragment softwareDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = softwareDetailFragment;
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
                    SoftwareDetailFragment softwareDetailFragment = this.b;
                    int i2 = SoftwareDetailFragment.o;
                    StateFlow<SoftwareDetailUiState> stateFlow = softwareDetailFragment.j().j;
                    C0078a c0078a = new C0078a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0078a, this) == coroutine_suspended) {
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

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SoftwareDetailFragment.this.new e(continuation);
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
                SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(softwareDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(softwareDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$2", f = "SoftwareDetailFragment.kt", i = {}, l = {249}, m = "invokeSuspend", n = {}, s = {})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$2$1", f = "SoftwareDetailFragment.kt", i = {}, l = {250}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SoftwareDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$f$a$a, reason: collision with other inner class name */
            public static final class C0079a<T> implements FlowCollector {
                public final /* synthetic */ SoftwareDetailFragment a;

                public C0079a(SoftwareDetailFragment softwareDetailFragment) {
                    this.a = softwareDetailFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    SkinProgressBar skinProgressBar;
                    int i;
                    LoadingUiState loadingUiState = (LoadingUiState) obj;
                    SoftwareDetailFragment softwareDetailFragment = this.a;
                    int i2 = SoftwareDetailFragment.o;
                    FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment.a;
                    if (fragmentSoftwareDetailBinding != null) {
                        if (loadingUiState.getVisible()) {
                            skinProgressBar = fragmentSoftwareDetailBinding.h;
                            i = 0;
                        } else {
                            skinProgressBar = fragmentSoftwareDetailBinding.h;
                            i = 8;
                        }
                        skinProgressBar.setVisibility(i);
                        fragmentSoftwareDetailBinding.n.setVisibility(i);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SoftwareDetailFragment softwareDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = softwareDetailFragment;
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
                    SoftwareDetailFragment softwareDetailFragment = this.b;
                    int i2 = SoftwareDetailFragment.o;
                    StateFlow<LoadingUiState> stateFlow = softwareDetailFragment.j().l;
                    C0079a c0079a = new C0079a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0079a, this) == coroutine_suspended) {
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

        public f(Continuation<? super f> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SoftwareDetailFragment.this.new f(continuation);
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
                SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(softwareDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(softwareDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$3", f = "SoftwareDetailFragment.kt", i = {}, l = {271}, m = "invokeSuspend", n = {}, s = {})
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$3$1", f = "SoftwareDetailFragment.kt", i = {}, l = {272}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SoftwareDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$g$a$a, reason: collision with other inner class name */
            public static final class C0080a<T> implements FlowCollector {
                public final /* synthetic */ SoftwareDetailFragment a;

                public C0080a(SoftwareDetailFragment softwareDetailFragment) {
                    this.a = softwareDetailFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    SkinAppCompatImageView skinAppCompatImageView;
                    int i;
                    ErrorEmptyUiState errorEmptyUiState = (ErrorEmptyUiState) obj;
                    SoftwareDetailFragment softwareDetailFragment = this.a;
                    int i2 = SoftwareDetailFragment.o;
                    FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment.a;
                    if (fragmentSoftwareDetailBinding != null) {
                        if (errorEmptyUiState.getVisible()) {
                            fragmentSoftwareDetailBinding.b.setImageResource(errorEmptyUiState.getPicId());
                            fragmentSoftwareDetailBinding.l.setText(errorEmptyUiState.getTipId());
                            skinAppCompatImageView = fragmentSoftwareDetailBinding.b;
                            i = 0;
                        } else {
                            skinAppCompatImageView = fragmentSoftwareDetailBinding.b;
                            i = 8;
                        }
                        skinAppCompatImageView.setVisibility(i);
                        fragmentSoftwareDetailBinding.l.setVisibility(i);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SoftwareDetailFragment softwareDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = softwareDetailFragment;
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
                    SoftwareDetailFragment softwareDetailFragment = this.b;
                    int i2 = SoftwareDetailFragment.o;
                    StateFlow<ErrorEmptyUiState> stateFlow = softwareDetailFragment.j().n;
                    C0080a c0080a = new C0080a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0080a, this) == coroutine_suspended) {
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

        public g(Continuation<? super g> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SoftwareDetailFragment.this.new g(continuation);
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
                SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(softwareDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(softwareDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$4", f = "SoftwareDetailFragment.kt", i = {}, l = {295}, m = "invokeSuspend", n = {}, s = {})
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$4$1", f = "SoftwareDetailFragment.kt", i = {}, l = {296}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SoftwareDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$h$a$a, reason: collision with other inner class name */
            public static final class C0081a<T> implements FlowCollector {
                public final /* synthetic */ SoftwareDetailFragment a;

                public C0081a(SoftwareDetailFragment softwareDetailFragment) {
                    this.a = softwareDetailFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    Unit unit;
                    DownloadProgressButtonView downloadProgressButtonView;
                    DownloadProgressButtonView downloadProgressButtonView2;
                    SoftwareStateData softwareStateData = (SoftwareStateData) obj;
                    SoftwareDetailFragment softwareDetailFragment = this.a;
                    int i = SoftwareDetailFragment.o;
                    FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) softwareDetailFragment.a;
                    if (fragmentSoftwareDetailBinding != null && (downloadProgressButtonView2 = fragmentSoftwareDetailBinding.f) != null) {
                        int state = softwareStateData.getState();
                        int percentCount = softwareStateData.getPercentCount();
                        int i2 = DownloadProgressButtonView.d;
                        downloadProgressButtonView2.d(state, percentCount, true);
                    }
                    FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding2 = (FragmentSoftwareDetailBinding) this.a.a;
                    if (fragmentSoftwareDetailBinding2 == null || (downloadProgressButtonView = fragmentSoftwareDetailBinding2.g) == null) {
                        unit = null;
                    } else {
                        int state2 = softwareStateData.getState();
                        int percentCount2 = softwareStateData.getPercentCount();
                        int i3 = DownloadProgressButtonView.d;
                        downloadProgressButtonView.d(state2, percentCount2, true);
                        unit = Unit.INSTANCE;
                    }
                    return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SoftwareDetailFragment softwareDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = softwareDetailFragment;
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
                    SoftwareDetailFragment softwareDetailFragment = this.b;
                    int i2 = SoftwareDetailFragment.o;
                    StateFlow<SoftwareStateData> stateFlow = softwareDetailFragment.j().p;
                    C0081a c0081a = new C0081a(this.b);
                    this.a = 1;
                    if (stateFlow.collect(c0081a, this) == coroutine_suspended) {
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
            return SoftwareDetailFragment.this.new h(continuation);
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
                SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(softwareDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(softwareDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$5", f = "SoftwareDetailFragment.kt", i = {}, l = {313}, m = "invokeSuspend", n = {}, s = {})
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$observeData$5$1", f = "SoftwareDetailFragment.kt", i = {}, l = {314}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int a;
            public final /* synthetic */ SoftwareDetailFragment b;

            /* JADX INFO: renamed from: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$i$a$a, reason: collision with other inner class name */
            public static final class C0082a<T> implements FlowCollector {
                public final /* synthetic */ SoftwareDetailFragment a;

                public C0082a(SoftwareDetailFragment softwareDetailFragment) {
                    this.a = softwareDetailFragment;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    SoftwareDetailFragment softwareDetailFragment = this.a;
                    int i = SoftwareDetailFragment.o;
                    SoftwareDetailViewModel softwareDetailViewModelJ = softwareDetailFragment.j();
                    if (softwareDetailViewModelJ.g.getLanguageCode() != softwareDetailViewModelJ.q) {
                        this.a.j().a(this.a.i().a, LifecycleOwnerKt.getLifecycleScope(this.a));
                        SoftwareDetailViewModel softwareDetailViewModelJ2 = this.a.j();
                        softwareDetailViewModelJ2.q = softwareDetailViewModelJ2.g.getLanguageCode();
                    }
                    SoftwareDetailViewModel softwareDetailViewModelJ3 = this.a.j();
                    CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
                    Context contextRequireContext = this.a.requireContext();
                    Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                    Objects.requireNonNull(softwareDetailViewModelJ3);
                    Object objLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new lq(softwareDetailViewModelJ3, contextRequireContext, null), 3, null);
                    if (objLaunch$default != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        objLaunch$default = Unit.INSTANCE;
                    }
                    return objLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objLaunch$default : Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(SoftwareDetailFragment softwareDetailFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.b = softwareDetailFragment;
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
                    SoftwareDetailFragment softwareDetailFragment = this.b;
                    int i2 = SoftwareDetailFragment.o;
                    SharedFlow<String> languageDataFlow = softwareDetailFragment.j().h.getLanguageDataFlow();
                    C0082a c0082a = new C0082a(this.b);
                    this.a = 1;
                    if (languageDataFlow.collect(c0082a, this) == coroutine_suspended) {
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

        public i(Continuation<? super i> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return SoftwareDetailFragment.this.new i(continuation);
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
                SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
                Lifecycle.State state = Lifecycle.State.STARTED;
                a aVar = new a(softwareDetailFragment, null);
                this.a = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(softwareDetailFragment, state, aVar, this) == coroutine_suspended) {
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

    public static final class j extends Lambda implements Function3<ImagePath, ItemSoftwareDetailPreviewImgBinding, Integer, Unit> {
        public j() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Unit invoke(ImagePath imagePath, ItemSoftwareDetailPreviewImgBinding itemSoftwareDetailPreviewImgBinding, Integer num) {
            List<ImagePath> images;
            ImagePath imagePath2 = imagePath;
            ItemSoftwareDetailPreviewImgBinding itemSoftwareDetailPreviewImgBinding2 = itemSoftwareDetailPreviewImgBinding;
            int iIntValue = num.intValue();
            Intrinsics.checkNotNullParameter(imagePath2, "imagePath");
            Intrinsics.checkNotNullParameter(itemSoftwareDetailPreviewImgBinding2, "itemSoftwareDetailPreviewImgBinding");
            eq.b bVar = eq.a;
            SoftwareDetailFragment softwareDetailFragment = SoftwareDetailFragment.this;
            int i = SoftwareDetailFragment.o;
            SoftwareDetail data = softwareDetailFragment.j().j.getValue().getData();
            SoftwareDetailPreviewImages softwareDetailPreviewImages = (data == null || (images = data.getImages()) == null) ? null : new SoftwareDetailPreviewImages(images);
            Objects.requireNonNull(bVar);
            spi.b.h(SoftwareDetailFragment.this, new eq.a(iIntValue, softwareDetailPreviewImages));
            return Unit.INSTANCE;
        }
    }

    public SoftwareDetailFragment() {
        final Function0 function0 = null;
        this.i = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$activityViewModels$default$2
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$activityViewModels$default$3
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
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$viewModels$default$2
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
        this.j = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SoftwareDetailViewModel.class), new Function0<ViewModelStore>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$viewModels$default$5
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
        SoftwareDetailPreviewImageAdapter softwareDetailPreviewImageAdapter = new SoftwareDetailPreviewImageAdapter();
        j block = new j();
        Intrinsics.checkNotNullParameter(block, "block");
        softwareDetailPreviewImageAdapter.a = block;
        this.k = softwareDetailPreviewImageAdapter;
        this.n = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SoftwareDetailFragmentArgs.class), new Function0<Bundle>() { // from class: com.byd.overseaappstore.ui.view.fragment.SoftwareDetailFragment$special$$inlined$navArgs$1
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
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final ViewBinding b() {
        FragmentSoftwareDetailBinding fragmentSoftwareDetailBindingA = FragmentSoftwareDetailBinding.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentSoftwareDetailBindingA, "inflate(layoutInflater)");
        this.h = IncludeTopBarMergeBinding.a(fragmentSoftwareDetailBindingA.a);
        return fragmentSoftwareDetailBindingA;
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    @SuppressLint({"SetTextI18n"})
    public final void c() {
        SkinImageView skinImageView;
        final FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) this.a;
        if (fragmentSoftwareDetailBinding != null) {
            IncludeTopBarMergeBinding includeTopBarMergeBinding = this.h;
            if (includeTopBarMergeBinding != null && (skinImageView = includeTopBarMergeBinding.b) != null) {
                skinImageView.setOnClickListener(new fj(this, 13));
            }
            final int i2 = 0;
            fragmentSoftwareDetailBinding.f.setOnClickListener(new View.OnClickListener(this) { // from class: spi.cq
                public final /* synthetic */ SoftwareDetailFragment b;

                {
                    this.b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i2) {
                        case 0:
                            SoftwareDetailFragment this$0 = this.b;
                            int i3 = SoftwareDetailFragment.o;
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            this$0.j().b();
                            break;
                        default:
                            SoftwareDetailFragment this$02 = this.b;
                            int i4 = SoftwareDetailFragment.o;
                            Intrinsics.checkNotNullParameter(this$02, "this$0");
                            this$02.j().b();
                            break;
                    }
                }
            });
            fragmentSoftwareDetailBinding.d.setOnClickListener(new gk(fragmentSoftwareDetailBinding, 9));
            fragmentSoftwareDetailBinding.e.setOnClickListener(new fj(fragmentSoftwareDetailBinding, 14));
            final int i3 = 1;
            fragmentSoftwareDetailBinding.g.setOnClickListener(new View.OnClickListener(this) { // from class: spi.cq
                public final /* synthetic */ SoftwareDetailFragment b;

                {
                    this.b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i3) {
                        case 0:
                            SoftwareDetailFragment this$0 = this.b;
                            int i32 = SoftwareDetailFragment.o;
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            this$0.j().b();
                            break;
                        default:
                            SoftwareDetailFragment this$02 = this.b;
                            int i4 = SoftwareDetailFragment.o;
                            Intrinsics.checkNotNullParameter(this$02, "this$0");
                            this$02.j().b();
                            break;
                    }
                }
            });
            fragmentSoftwareDetailBinding.i.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: spi.dq
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public final void onScrollChange(NestedScrollView nestedScrollView, int i4, int i5, int i6, int i7) {
                    SkinTextView skinTextView;
                    CharSequence text;
                    FragmentSoftwareDetailBinding it = fragmentSoftwareDetailBinding;
                    SoftwareDetailFragment this$0 = this;
                    int i8 = SoftwareDetailFragment.o;
                    Intrinsics.checkNotNullParameter(it, "$it");
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(nestedScrollView, "<anonymous parameter 0>");
                    Rect rect = new Rect();
                    it.i.getHitRect(rect);
                    if (it.f.getLocalVisibleRect(rect)) {
                        it.g.setVisibility(4);
                        IncludeTopBarMergeBinding includeTopBarMergeBinding2 = this$0.h;
                        skinTextView = includeTopBarMergeBinding2 != null ? includeTopBarMergeBinding2.c : null;
                        if (skinTextView == null) {
                            return;
                        } else {
                            text = this$0.getString(R.string.software_detail_top_title);
                        }
                    } else {
                        it.g.setVisibility(0);
                        IncludeTopBarMergeBinding includeTopBarMergeBinding3 = this$0.h;
                        skinTextView = includeTopBarMergeBinding3 != null ? includeTopBarMergeBinding3.c : null;
                        if (skinTextView == null) {
                            return;
                        } else {
                            text = it.o.getText();
                        }
                    }
                    skinTextView.setText(text);
                }
            });
            fragmentSoftwareDetailBinding.b.setOnClickListener(new ro(fragmentSoftwareDetailBinding, this, 2));
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void d() {
        TextView textView;
        TextView textView2;
        ViewTreeObserver viewTreeObserver;
        SkinTextView skinTextView;
        SkinTextView skinTextView2;
        ViewTreeObserver viewTreeObserver2;
        TextView textView3;
        ViewTreeObserver viewTreeObserver3;
        SkinTextView skinTextView3;
        ViewTreeObserver viewTreeObserver4;
        HorizontalRecycleView horizontalRecycleView;
        FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding = (FragmentSoftwareDetailBinding) this.a;
        if (fragmentSoftwareDetailBinding != null && (horizontalRecycleView = fragmentSoftwareDetailBinding.j) != null) {
            horizontalRecycleView.setAdapter(this.k);
            horizontalRecycleView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        }
        this.l = new c();
        this.m = new d();
        FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding2 = (FragmentSoftwareDetailBinding) this.a;
        if (fragmentSoftwareDetailBinding2 != null && (skinTextView3 = fragmentSoftwareDetailBinding2.m) != null && (viewTreeObserver4 = skinTextView3.getViewTreeObserver()) != null) {
            viewTreeObserver4.addOnPreDrawListener(this.l);
        }
        FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding3 = (FragmentSoftwareDetailBinding) this.a;
        if (fragmentSoftwareDetailBinding3 != null && (textView3 = fragmentSoftwareDetailBinding3.s) != null && (viewTreeObserver3 = textView3.getViewTreeObserver()) != null) {
            viewTreeObserver3.addOnPreDrawListener(this.m);
        }
        FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding4 = (FragmentSoftwareDetailBinding) this.a;
        if (fragmentSoftwareDetailBinding4 != null && (skinTextView = fragmentSoftwareDetailBinding4.m) != null) {
            if (ViewCompat.isAttachedToWindow(skinTextView)) {
                skinTextView.addOnAttachStateChangeListener(new a(skinTextView, this));
            } else {
                ct.a("tvIntroduction doOnDetach binding:?" + ((FragmentSoftwareDetailBinding) this.a) + " introductionLineCountListener:" + this.l, new Object[0]);
                ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.l;
                if (onPreDrawListener != null) {
                    FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding5 = (FragmentSoftwareDetailBinding) this.a;
                    if (fragmentSoftwareDetailBinding5 != null && (skinTextView2 = fragmentSoftwareDetailBinding5.m) != null && (viewTreeObserver2 = skinTextView2.getViewTreeObserver()) != null) {
                        viewTreeObserver2.removeOnPreDrawListener(onPreDrawListener);
                    }
                    this.l = null;
                }
            }
        }
        FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding6 = (FragmentSoftwareDetailBinding) this.a;
        if (fragmentSoftwareDetailBinding6 == null || (textView = fragmentSoftwareDetailBinding6.s) == null) {
            return;
        }
        if (ViewCompat.isAttachedToWindow(textView)) {
            textView.addOnAttachStateChangeListener(new b(textView, this));
            return;
        }
        ct.a("tvVersionIntroduction doOnDetach binding:?" + ((FragmentSoftwareDetailBinding) this.a) + " introductionLineCountListener:" + this.m, new Object[0]);
        ViewTreeObserver.OnPreDrawListener onPreDrawListener2 = this.m;
        if (onPreDrawListener2 != null) {
            FragmentSoftwareDetailBinding fragmentSoftwareDetailBinding7 = (FragmentSoftwareDetailBinding) this.a;
            if (fragmentSoftwareDetailBinding7 != null && (textView2 = fragmentSoftwareDetailBinding7.s) != null && (viewTreeObserver = textView2.getViewTreeObserver()) != null) {
                viewTreeObserver.removeOnPreDrawListener(onPreDrawListener2);
            }
            this.m = null;
        }
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new e(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new f(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new g(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new h(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new i(null), 3, null);
        j().a(i().a, LifecycleOwnerKt.getLifecycleScope(this));
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void f() {
    }

    @Override // com.byd.overseaappstore.ui.view.base.BaseFragment
    public final void g() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SoftwareDetailFragmentArgs i() {
        return (SoftwareDetailFragmentArgs) this.n.getValue();
    }

    public final SoftwareDetailViewModel j() {
        return (SoftwareDetailViewModel) this.j.getValue();
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
