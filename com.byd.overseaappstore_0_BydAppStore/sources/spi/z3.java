package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.util.SettingUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class z3 extends h1 {

    @NotNull
    public final yu a;

    @NotNull
    public final Context b;

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.ConfigRepository$getUseByWifiState$2", f = "ConfigRepository.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        public int a;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return z3.this.new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SettingUtil settingUtil = SettingUtil.a;
                Context context = z3.this.b;
                this.a = 1;
                obj = settingUtil.a(context, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.repo.ConfigRepository$saveUseByWifiState$2", f = "ConfigRepository.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        public int a;
        public final /* synthetic */ boolean c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, Continuation<? super b> continuation) {
            super(2, continuation);
            this.c = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return z3.this.new b(this.c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public final Object mo6invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SettingUtil settingUtil = SettingUtil.a;
                Context context = z3.this.b;
                boolean z = this.c;
                this.a = 1;
                obj = settingUtil.c(context, z, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    public z3(@NotNull yu vehicleDataSource, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(vehicleDataSource, "vehicleDataSource");
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = vehicleDataSource;
        this.b = context;
    }

    @NotNull
    public final String b() {
        return this.a.getCountryCode();
    }

    public final int c() {
        this.a.f();
        return 3;
    }

    @NotNull
    public final String d() {
        return this.a.g(this.b);
    }

    @Nullable
    public final Object e(@NotNull Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new a(null), continuation);
    }

    public final int f() {
        return this.a.d(this.b);
    }

    @Nullable
    public final Object g(boolean z, @NotNull Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new b(z, null), continuation);
    }
}
