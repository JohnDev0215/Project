package spi;

import android.view.ViewPropertyAnimator;
import com.byd.overseaappstore.ui.view.custom.PullToRefreshView;
import com.byd.overseaappstore.ui.view.custom.skin.SkinImageView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.ui.view.custom.PullToRefreshView$onHeaderRefreshComplete$1", f = "PullToRefreshView.kt", i = {}, l = {553, 554}, m = "invokeSuspend", n = {}, s = {})
public final class nl extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int a;
    public final /* synthetic */ PullToRefreshView b;

    @DebugMetadata(c = "com.byd.overseaappstore.ui.view.custom.PullToRefreshView$onHeaderRefreshComplete$1$1", f = "PullToRefreshView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            SkinImageView skinImageView = this.a.j;
            SkinImageView skinImageView2 = null;
            if (skinImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderImageView");
                skinImageView = null;
            }
            skinImageView.setVisibility(0);
            SkinImageView skinImageView3 = this.a.j;
            if (skinImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderImageView");
            } else {
                skinImageView2 = skinImageView3;
            }
            ViewPropertyAnimator viewPropertyAnimatorAnimate = skinImageView2.animate();
            Float PIC_ROTATE_START = j4.d;
            Intrinsics.checkNotNullExpressionValue(PIC_ROTATE_START, "PIC_ROTATE_START");
            viewPropertyAnimatorAnimate.rotation(PIC_ROTATE_START.floatValue());
            this.a.q = 2;
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nl(PullToRefreshView pullToRefreshView, Continuation<? super nl> continuation) {
        super(2, continuation);
        this.b = pullToRefreshView;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new nl(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((nl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        a aVar = new a(this.b, null);
        this.a = 2;
        if (BuildersKt.withContext(main, aVar, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
