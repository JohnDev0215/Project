package h;

import java.lang.reflect.Method;
import kotlin.KotlinNullPointerException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: KotlinExtensions.kt */
/* JADX INFO: loaded from: classes.dex */
@JvmName(name = "KotlinExtensions")
public final class m {

    /* JADX INFO: compiled from: KotlinExtensions.kt */
    static final class a extends Lambda implements Function1<Throwable, Unit> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ h.d f5579a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(h.d dVar) {
            super(1);
            this.f5579a = dVar;
        }
    }

    /* JADX INFO: compiled from: KotlinExtensions.kt */
    static final class b extends Lambda implements Function1<Throwable, Unit> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ h.d f5580a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(h.d dVar) {
            super(1);
            this.f5580a = dVar;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: KotlinExtensions.kt */
    public static final class c<T> implements h.f<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ CancellableContinuation f5581b;

        c(CancellableContinuation cancellableContinuation) {
            this.f5581b = cancellableContinuation;
        }

        @Override // h.f
        public void A(@NotNull h.d<T> dVar, @NotNull t<T> tVar) {
            Intrinsics.checkParameterIsNotNull(dVar, "call");
            Intrinsics.checkParameterIsNotNull(tVar, "response");
            if (!tVar.d()) {
                Continuation continuation = this.f5581b;
                j jVar = new j(tVar);
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(jVar)));
                return;
            }
            T tA = tVar.a();
            if (tA != null) {
                Continuation continuation2 = this.f5581b;
                Result.Companion companion2 = Result.Companion;
                continuation2.resumeWith(Result.constructor-impl(tA));
                return;
            }
            Object objH = dVar.a().h(l.class);
            if (objH == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(objH, "call.request().tag(Invocation::class.java)!!");
            Method methodA = ((l) objH).a();
            StringBuilder sb = new StringBuilder();
            sb.append("Response from ");
            Intrinsics.checkExpressionValueIsNotNull(methodA, "method");
            Class<?> declaringClass = methodA.getDeclaringClass();
            Intrinsics.checkExpressionValueIsNotNull(declaringClass, "method.declaringClass");
            sb.append(declaringClass.getName());
            sb.append('.');
            sb.append(methodA.getName());
            sb.append(" was null but response body type was declared as non-null");
            Throwable kotlinNullPointerException = new KotlinNullPointerException(sb.toString());
            Continuation continuation3 = this.f5581b;
            Result.Companion companion3 = Result.Companion;
            continuation3.resumeWith(Result.constructor-impl(ResultKt.createFailure(kotlinNullPointerException)));
        }

        @Override // h.f
        public void k(@NotNull h.d<T> dVar, @NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(dVar, "call");
            Intrinsics.checkParameterIsNotNull(th, "t");
            Continuation continuation = this.f5581b;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: KotlinExtensions.kt */
    public static final class d<T> implements h.f<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ CancellableContinuation f5582b;

        d(CancellableContinuation cancellableContinuation) {
            this.f5582b = cancellableContinuation;
        }

        @Override // h.f
        public void A(@NotNull h.d<T> dVar, @NotNull t<T> tVar) {
            Intrinsics.checkParameterIsNotNull(dVar, "call");
            Intrinsics.checkParameterIsNotNull(tVar, "response");
            if (tVar.d()) {
                Continuation continuation = this.f5582b;
                T tA = tVar.a();
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.constructor-impl(tA));
                return;
            }
            Continuation continuation2 = this.f5582b;
            j jVar = new j(tVar);
            Result.Companion companion2 = Result.Companion;
            continuation2.resumeWith(Result.constructor-impl(ResultKt.createFailure(jVar)));
        }

        @Override // h.f
        public void k(@NotNull h.d<T> dVar, @NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(dVar, "call");
            Intrinsics.checkParameterIsNotNull(th, "t");
            Continuation continuation = this.f5582b;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
        }
    }

    /* JADX INFO: compiled from: KotlinExtensions.kt */
    static final class e extends Lambda implements Function1<Throwable, Unit> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ h.d f5583a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(h.d dVar) {
            super(1);
            this.f5583a = dVar;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: KotlinExtensions.kt */
    public static final class f<T> implements h.f<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ CancellableContinuation f5584b;

        f(CancellableContinuation cancellableContinuation) {
            this.f5584b = cancellableContinuation;
        }

        @Override // h.f
        public void A(@NotNull h.d<T> dVar, @NotNull t<T> tVar) {
            Intrinsics.checkParameterIsNotNull(dVar, "call");
            Intrinsics.checkParameterIsNotNull(tVar, "response");
            Continuation continuation = this.f5584b;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(tVar));
        }

        @Override // h.f
        public void k(@NotNull h.d<T> dVar, @NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(dVar, "call");
            Intrinsics.checkParameterIsNotNull(th, "t");
            Continuation continuation = this.f5584b;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(th)));
        }
    }

    /* JADX INFO: compiled from: KotlinExtensions.kt */
    static final class g implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Continuation f5585b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Exception f5586c;

        g(Continuation continuation, Exception exc) {
            this.f5585b = continuation;
            this.f5586c = exc;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Continuation continuationIntercepted = IntrinsicsKt.intercepted(this.f5585b);
            Exception exc = this.f5586c;
            Result.Companion companion = Result.Companion;
            continuationIntercepted.resumeWith(Result.constructor-impl(ResultKt.createFailure(exc)));
        }
    }

    /* JADX INFO: compiled from: KotlinExtensions.kt */
    @DebugMetadata(c = "retrofit2.KotlinExtensions", f = "KotlinExtensions.kt", i = {0}, l = {113}, m = "suspendAndThrow", n = {"$this$suspendAndThrow"}, s = {"L$0"})
    static final class h extends ContinuationImpl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        /* synthetic */ Object f5587a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f5588b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object f5589c;

        h(Continuation continuation) {
            super(continuation);
        }
    }

    @Nullable
    public static final <T> Object a(@NotNull h.d<T> dVar, @NotNull Continuation<? super T> continuation) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new a(dVar));
        dVar.C(new c(cancellableContinuation));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @JvmName(name = "awaitNullable")
    @Nullable
    public static final <T> Object b(@NotNull h.d<T> dVar, @NotNull Continuation<? super T> continuation) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new b(dVar));
        dVar.C(new d(cancellableContinuation));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Nullable
    public static final <T> Object c(@NotNull h.d<T> dVar, @NotNull Continuation<? super t<T>> continuation) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new e(dVar));
        dVar.C(new f(cancellableContinuation));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object d(@org.jetbrains.annotations.NotNull java.lang.Exception r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<?> r5) {
        /*
            boolean r0 = r5 instanceof h.m.h
            if (r0 == 0) goto L13
            r0 = r5
            h.m$h r0 = (h.m.h) r0
            int r1 = r0.f5588b
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f5588b = r1
            goto L18
        L13:
            h.m$h r0 = new h.m$h
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.f5587a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f5588b
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.f5589c
            java.lang.Exception r4 = (java.lang.Exception) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L5e
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.f5589c = r4
            r0.f5588b = r3
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r2 = r0.getContext()
            h.m$g r3 = new h.m$g
            r3.<init>(r0, r4)
            r5.dispatch(r2, r3)
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r4 != r5) goto L5b
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L5b:
            if (r4 != r1) goto L5e
            return r1
        L5e:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: h.m.d(java.lang.Exception, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
