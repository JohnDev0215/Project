package com.byd.overseaappstore.ui.view.custom;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.overseaappstore.R;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.j4;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0015B\u001d\b\u0016\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013B\u0013\b\u0016\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0012\u0010\u0014J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0002J+\u0010\f\u001a\u00020\u00042#\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00040\u0007J+\u0010\r\u001a\u00020\u00042#\u0010\u000b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00040\u0007¨\u0006\u0016"}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/PullToRefreshView;", "Landroid/widget/LinearLayout;", "", "topMargin", "", "setHeaderTopMargin", "getHeaderTopMargin", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "block", "setOnHeaderRefreshListener", "setOnFooterRefreshListener", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "a", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class PullToRefreshView extends LinearLayout {
    public int a;
    public View b;
    public View c;
    public RecyclerView d;
    public ScrollView e;
    public NestedScrollView f;
    public int g;
    public int h;
    public int i;
    public SkinImageView j;
    public ImageView k;
    public TextView l;
    public TextView m;
    public ProgressBar n;
    public ProgressBar o;
    public LayoutInflater p;
    public int q;
    public int r;
    public int s;
    public Function1<? super PullToRefreshView, Unit> t;
    public Function1<? super PullToRefreshView, Unit> u;
    public boolean v;
    public boolean w;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.custom.PullToRefreshView$onFooterRefreshComplete$1", f = "PullToRefreshView.kt", i = {}, l = {587, 588}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int a;

        @DebugMetadata(c = "com.byd.overseaappstore.ui.view.custom.PullToRefreshView$onFooterRefreshComplete$1$1", f = "PullToRefreshView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ PullToRefreshView a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(PullToRefreshView pullToRefreshView, Continuation<? super a> continuation) {
                super(2, continuation);
                this.a = pullToRefreshView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.a, continuation);
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
                PullToRefreshView pullToRefreshView = this.a;
                pullToRefreshView.setHeaderTopMargin(-pullToRefreshView.h);
                ImageView imageView = this.a.k;
                ImageView imageView2 = null;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterImageView");
                    imageView = null;
                }
                imageView.setVisibility(0);
                ImageView imageView3 = this.a.k;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterImageView");
                } else {
                    imageView2 = imageView3;
                }
                ViewPropertyAnimator viewPropertyAnimatorAnimate = imageView2.animate();
                Float PIC_ROTATE_START = j4.d;
                Intrinsics.checkNotNullExpressionValue(PIC_ROTATE_START, "PIC_ROTATE_START");
                viewPropertyAnimatorAnimate.rotation(PIC_ROTATE_START.floatValue());
                this.a.r = 2;
                return Unit.INSTANCE;
            }
        }

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return PullToRefreshView.this.new b(continuation);
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
                this.a = 1;
                if (DelayKt.delay(1500L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            a aVar = new a(PullToRefreshView.this, null);
            this.a = 2;
            if (BuildersKt.withContext(main, aVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    static {
        new a(null);
    }

    public PullToRefreshView(@Nullable Context context) {
        super(context);
        this.v = true;
        this.w = true;
        c();
    }

    public PullToRefreshView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = true;
        this.w = true;
        c();
    }

    private final int getHeaderTopMargin() {
        View view = this.b;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        return ((LinearLayout.LayoutParams) layoutParams).topMargin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHeaderTopMargin(int topMargin) {
        View view = this.b;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        layoutParams2.topMargin = topMargin;
        View view3 = this.b;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
        } else {
            view2 = view3;
        }
        view2.setLayoutParams(layoutParams2);
        invalidate();
    }

    public final int b(int i) {
        View view = this.b;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        int i2 = layoutParams2.topMargin;
        float f = (i * 0.3f) + i2;
        if (i > 0 && this.s == 0 && Math.abs(i2) <= this.h) {
            return layoutParams2.topMargin;
        }
        if (i < 0 && this.s == 1 && Math.abs(layoutParams2.topMargin) >= this.h) {
            return layoutParams2.topMargin;
        }
        layoutParams2.topMargin = (int) f;
        View view3 = this.b;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
        } else {
            view2 = view3;
        }
        view2.setLayoutParams(layoutParams2);
        invalidate();
        return layoutParams2.topMargin;
    }

    public final void c() {
        setOrientation(1);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "from(context)");
        this.p = layoutInflaterFrom;
        View view = null;
        if (layoutInflaterFrom == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mInflater");
            layoutInflaterFrom = null;
        }
        View viewInflate = layoutInflaterFrom.inflate(R.layout.item_pull_header_footer, (ViewGroup) this, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "mInflater.inflate(R.layo…ader_footer, this, false)");
        this.b = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            viewInflate = null;
        }
        View viewFindViewById = viewInflate.findViewById(R.id.iv_refresh_loading_pic);
        Objects.requireNonNull(viewFindViewById, "null cannot be cast to non-null type com.byd.overseaappstore.ui.view.custom.skin.SkinImageView");
        this.j = (SkinImageView) viewFindViewById;
        View view2 = this.b;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            view2 = null;
        }
        View viewFindViewById2 = view2.findViewById(R.id.tv_refresh_loading_text);
        Objects.requireNonNull(viewFindViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.l = (TextView) viewFindViewById2;
        View view3 = this.b;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            view3 = null;
        }
        View viewFindViewById3 = view3.findViewById(R.id.pb_refresh_loading_bar);
        Objects.requireNonNull(viewFindViewById3, "null cannot be cast to non-null type android.widget.ProgressBar");
        this.n = (ProgressBar) viewFindViewById3;
        View view4 = this.b;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            view4 = null;
        }
        d(view4);
        View view5 = this.b;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
            view5 = null;
        }
        this.h = view5.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.h);
        layoutParams.topMargin = -this.h;
        View view6 = this.b;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderView");
        } else {
            view = view6;
        }
        addView(view, layoutParams);
    }

    public final void d(View view) {
        ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        int iMakeMeasureSpec = i > 0 ? View.MeasureSpec.makeMeasureSpec(i, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0);
        if (view != null) {
            view.measure(childMeasureSpec, iMakeMeasureSpec);
        }
    }

    public final void e(int i) {
        TextView textView;
        Resources resources;
        int i2;
        ProgressBar progressBar = this.o;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterProgressBar");
            progressBar = null;
        }
        progressBar.setVisibility(8);
        ImageView imageView = this.k;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterImageView");
            imageView = null;
        }
        imageView.setVisibility(8);
        switch (i) {
            case 17:
                textView = this.m;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterTextView");
                    textView = null;
                }
                resources = getResources();
                i2 = R.string.pull_up_load_success;
                break;
            case 18:
                textView = this.m;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterTextView");
                    textView = null;
                }
                resources = getResources();
                i2 = R.string.pull_up_down_unknown_error;
                break;
            case 19:
                textView = this.m;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterTextView");
                    textView = null;
                }
                resources = getResources();
                i2 = R.string.pull_up_load_out_time;
                break;
            case 20:
                textView = this.m;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterTextView");
                    textView = null;
                }
                resources = getResources();
                i2 = R.string.pull_up_down_un_connect;
                break;
            case 21:
                textView = this.m;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterTextView");
                    textView = null;
                }
                resources = getResources();
                i2 = R.string.pull_up_load_no_more;
                break;
            default:
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new b(null), 3, null);
        }
        textView.setText(resources.getString(i2));
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new b(null), 3, null);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater layoutInflater = this.p;
        View view = null;
        if (layoutInflater == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mInflater");
            layoutInflater = null;
        }
        View viewInflate = layoutInflater.inflate(R.layout.item_pull_header_footer, (ViewGroup) this, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "mInflater.inflate(R.layo…ader_footer, this, false)");
        this.c = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterView");
            viewInflate = null;
        }
        View viewFindViewById = viewInflate.findViewById(R.id.iv_refresh_loading_pic);
        Objects.requireNonNull(viewFindViewById, "null cannot be cast to non-null type android.widget.ImageView");
        this.k = (ImageView) viewFindViewById;
        View view2 = this.c;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterView");
            view2 = null;
        }
        View viewFindViewById2 = view2.findViewById(R.id.tv_refresh_loading_text);
        Objects.requireNonNull(viewFindViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.m = (TextView) viewFindViewById2;
        View view3 = this.c;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterView");
            view3 = null;
        }
        View viewFindViewById3 = view3.findViewById(R.id.pb_refresh_loading_bar);
        Objects.requireNonNull(viewFindViewById3, "null cannot be cast to non-null type android.widget.ProgressBar");
        this.o = (ProgressBar) viewFindViewById3;
        View view4 = this.c;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterView");
            view4 = null;
        }
        d(view4);
        View view5 = this.c;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterView");
            view5 = null;
        }
        this.i = view5.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.i);
        View view6 = this.c;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterView");
        } else {
            view = view6;
        }
        addView(view, layoutParams);
        View childAt = getChildAt(1);
        if (childAt instanceof RecyclerView) {
            this.d = (RecyclerView) childAt;
            this.g = 0;
        }
        if (childAt instanceof ScrollView) {
            this.e = (ScrollView) childAt;
            this.g = 1;
        }
        if (childAt instanceof NestedScrollView) {
            this.f = (NestedScrollView) childAt;
            this.g = 2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x016c, code lost:
    
        if (r0 == r3.getScrollY()) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0107, code lost:
    
        if (r6.getScrollY() == 0) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x013a, code lost:
    
        if (r2.getScrollY() == 0) goto L97;
     */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0172 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0126  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(@org.jetbrains.annotations.NotNull android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.custom.PullToRefreshView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x011f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(@org.jetbrains.annotations.NotNull android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 479
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.ui.view.custom.PullToRefreshView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void setOnFooterRefreshListener(@NotNull Function1<? super PullToRefreshView, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.t = block;
    }

    public final void setOnHeaderRefreshListener(@NotNull Function1<? super PullToRefreshView, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.u = block;
    }
}
