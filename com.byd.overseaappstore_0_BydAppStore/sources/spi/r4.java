package spi;

import java.util.Timer;
import java.util.TimerTask;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.CountDownTimerTwoParamsUtil$start$1", f = "CountDownTimerTwoParamsUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class r4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ s4 a;

    public static final class a extends TimerTask {
        public final /* synthetic */ s4 a;

        /* JADX INFO: renamed from: spi.r4$a$a, reason: collision with other inner class name */
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.CountDownTimerTwoParamsUtil$start$1$1$run$1", f = "CountDownTimerTwoParamsUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0139a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ s4 a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0139a(s4 s4Var, Continuation<? super C0139a> continuation) {
                super(2, continuation);
                this.a = s4Var;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0139a(this.a, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C0139a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                Function1<? super Long, Unit> function1 = this.a.a;
                if (function1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onTickListener");
                    function1 = null;
                }
                function1.invoke(Boxing.boxLong(this.a.e));
                return Unit.INSTANCE;
            }
        }

        public a(s4 s4Var) {
            this.a = s4Var;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            ct.a("CountDownTimerTwoParams tick threadId:" + Thread.currentThread().getId() + " threadName:" + Thread.currentThread().getName(), new Object[0]);
            s4 s4Var = this.a;
            long j = s4Var.e + (-1);
            s4Var.e = j;
            if (j >= 0) {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new C0139a(this.a, null), 3, null);
            } else {
                s4Var.e = 10L;
                cancel();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r4(s4 s4Var, Continuation<? super r4> continuation) {
        super(2, continuation);
        this.a = s4Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new r4(this.a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((r4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        ct.a("CountDownTimerTwoParams start", new Object[0]);
        this.a.b = new Timer();
        this.a.c = new a(this.a);
        s4 s4Var = this.a;
        Timer timer = s4Var.b;
        if (timer != null) {
            timer.schedule(s4Var.c, 0L, s4Var.d);
        }
        return Unit.INSTANCE;
    }
}
