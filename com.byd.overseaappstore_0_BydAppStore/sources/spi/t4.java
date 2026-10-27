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
public final class t4 {
    public final long a;
    public Function1<? super Long, Unit> b;

    @Nullable
    public Timer c;

    @Nullable
    public a d;

    public static final class a extends TimerTask {

        /* JADX INFO: renamed from: spi.t4$a$a, reason: collision with other inner class name */
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.CountDownTimerUtil$start$1$run$1", f = "CountDownTimerUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C0141a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ t4 a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0141a(t4 t4Var, Continuation<? super C0141a> continuation) {
                super(2, continuation);
                this.a = t4Var;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0141a(this.a, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C0141a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                Function1<? super Long, Unit> function1 = this.a.b;
                if (function1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onTickListener");
                    function1 = null;
                }
                function1.invoke(Boxing.boxLong(this.a.a));
                return Unit.INSTANCE;
            }
        }

        public a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            ct.a("bannerTimer tick threadId:" + Thread.currentThread().getId() + " threadName:" + Thread.currentThread().getName(), new Object[0]);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new C0141a(t4.this, null), 3, null);
        }
    }

    public t4(long j) {
        this.a = j;
    }

    public final void a() {
        Timer timer = this.c;
        if (timer != null) {
            timer.cancel();
        }
        this.c = null;
        a aVar = this.d;
        if (aVar != null) {
            aVar.cancel();
        }
        this.d = null;
        ct.a("bannerTimer cancel", new Object[0]);
    }

    public final void b() {
        ct.a("bannerTimer start", new Object[0]);
        this.c = new Timer();
        a aVar = new a();
        this.d = aVar;
        Timer timer = this.c;
        if (timer != null) {
            long j = this.a;
            timer.schedule(aVar, j, j);
        }
    }
}
