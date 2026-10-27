package retrofit2;

import java.lang.reflect.Method;
import kotlin.KotlinNullPointerException;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* JADX INFO: compiled from: KotlinExtensions.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KotlinExtensions {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: KotlinExtensions.kt */
    public static final class a<T> implements f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CancellableContinuation f5183a;

        a(CancellableContinuation cancellableContinuation) {
            this.f5183a = cancellableContinuation;
        }

        @Override // retrofit2.f
        public void a(retrofit2.d<T> dVar, Throwable th) {
            kotlin.jvm.internal.i.c(dVar, "call");
            kotlin.jvm.internal.i.c(th, "t");
            kotlin.l.a aVar = this.f5183a;
            Result.a aVar2 = Result.Companion;
            aVar.resumeWith(Result.m2constructorimpl(kotlin.g.a(th)));
        }

        @Override // retrofit2.f
        public void b(retrofit2.d<T> dVar, r<T> rVar) {
            kotlin.jvm.internal.i.c(dVar, "call");
            kotlin.jvm.internal.i.c(rVar, "response");
            if (!rVar.d()) {
                kotlin.l.a aVar = this.f5183a;
                HttpException httpException = new HttpException(rVar);
                Result.a aVar2 = Result.Companion;
                aVar.resumeWith(Result.m2constructorimpl(kotlin.g.a(httpException)));
                return;
            }
            T tA = rVar.a();
            if (tA != null) {
                kotlin.l.a aVar3 = this.f5183a;
                Result.a aVar4 = Result.Companion;
                aVar3.resumeWith(Result.m2constructorimpl(tA));
                return;
            }
            Object objTag = dVar.request().tag(k.class);
            if (objTag == null) {
                kotlin.jvm.internal.i.g();
            }
            kotlin.jvm.internal.i.b(objTag, "call.request().tag(Invocation::class.java)!!");
            Method methodA = ((k) objTag).a();
            StringBuilder sb = new StringBuilder();
            sb.append("Response from ");
            kotlin.jvm.internal.i.b(methodA, "method");
            Class<?> declaringClass = methodA.getDeclaringClass();
            kotlin.jvm.internal.i.b(declaringClass, "method.declaringClass");
            sb.append(declaringClass.getName());
            sb.append('.');
            sb.append(methodA.getName());
            sb.append(" was null but response body type was declared as non-null");
            KotlinNullPointerException kotlinNullPointerException = new KotlinNullPointerException(sb.toString());
            kotlin.l.a aVar5 = this.f5183a;
            Result.a aVar6 = Result.Companion;
            aVar5.resumeWith(Result.m2constructorimpl(kotlin.g.a(kotlinNullPointerException)));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: KotlinExtensions.kt */
    public static final class b<T> implements f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CancellableContinuation f5184a;

        b(CancellableContinuation cancellableContinuation) {
            this.f5184a = cancellableContinuation;
        }

        @Override // retrofit2.f
        public void a(retrofit2.d<T> dVar, Throwable th) {
            kotlin.jvm.internal.i.c(dVar, "call");
            kotlin.jvm.internal.i.c(th, "t");
            kotlin.l.a aVar = this.f5184a;
            Result.a aVar2 = Result.Companion;
            aVar.resumeWith(Result.m2constructorimpl(kotlin.g.a(th)));
        }

        @Override // retrofit2.f
        public void b(retrofit2.d<T> dVar, r<T> rVar) {
            kotlin.jvm.internal.i.c(dVar, "call");
            kotlin.jvm.internal.i.c(rVar, "response");
            if (rVar.d()) {
                kotlin.l.a aVar = this.f5184a;
                T tA = rVar.a();
                Result.a aVar2 = Result.Companion;
                aVar.resumeWith(Result.m2constructorimpl(tA));
                return;
            }
            kotlin.l.a aVar3 = this.f5184a;
            HttpException httpException = new HttpException(rVar);
            Result.a aVar4 = Result.Companion;
            aVar3.resumeWith(Result.m2constructorimpl(kotlin.g.a(httpException)));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: KotlinExtensions.kt */
    public static final class c<T> implements f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CancellableContinuation f5185a;

        c(CancellableContinuation cancellableContinuation) {
            this.f5185a = cancellableContinuation;
        }

        @Override // retrofit2.f
        public void a(retrofit2.d<T> dVar, Throwable th) {
            kotlin.jvm.internal.i.c(dVar, "call");
            kotlin.jvm.internal.i.c(th, "t");
            kotlin.l.a aVar = this.f5185a;
            Result.a aVar2 = Result.Companion;
            aVar.resumeWith(Result.m2constructorimpl(kotlin.g.a(th)));
        }

        @Override // retrofit2.f
        public void b(retrofit2.d<T> dVar, r<T> rVar) {
            kotlin.jvm.internal.i.c(dVar, "call");
            kotlin.jvm.internal.i.c(rVar, "response");
            kotlin.l.a aVar = this.f5185a;
            Result.a aVar2 = Result.Companion;
            aVar.resumeWith(Result.m2constructorimpl(rVar));
        }
    }

    /* JADX INFO: compiled from: KotlinExtensions.kt */
    static final class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ kotlin.l.a f5186b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Exception f5187c;

        d(kotlin.l.a aVar, Exception exc) {
            this.f5186b = aVar;
            this.f5187c = exc;
        }

        @Override // java.lang.Runnable
        public final void run() {
            kotlin.l.a aVarA = kotlin.coroutines.intrinsics.b.a(this.f5186b);
            Exception exc = this.f5187c;
            Result.a aVar = Result.Companion;
            aVarA.resumeWith(Result.m2constructorimpl(kotlin.g.a(exc)));
        }
    }

    public static final <T> Object a(final retrofit2.d<T> dVar, kotlin.l.a<? super T> aVar) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(kotlin.coroutines.intrinsics.b.a(aVar), 1);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new kotlin.jvm.b.l<Throwable, kotlin.j>() { // from class: retrofit2.KotlinExtensions$await$$inlined$suspendCancellableCoroutine$lambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public /* bridge */ /* synthetic */ kotlin.j invoke(Throwable th) {
                invoke2(th);
                return kotlin.j.f5109a;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                dVar.cancel();
            }
        });
        dVar.e(new a(cancellableContinuation));
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.c.b()) {
            kotlin.coroutines.jvm.internal.e.b(aVar);
        }
        return result;
    }

    public static final <T> Object b(final retrofit2.d<T> dVar, kotlin.l.a<? super T> aVar) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(kotlin.coroutines.intrinsics.b.a(aVar), 1);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new kotlin.jvm.b.l<Throwable, kotlin.j>() { // from class: retrofit2.KotlinExtensions$await$$inlined$suspendCancellableCoroutine$lambda$2
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public /* bridge */ /* synthetic */ kotlin.j invoke(Throwable th) {
                invoke2(th);
                return kotlin.j.f5109a;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                dVar.cancel();
            }
        });
        dVar.e(new b(cancellableContinuation));
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.c.b()) {
            kotlin.coroutines.jvm.internal.e.b(aVar);
        }
        return result;
    }

    public static final <T> Object c(final retrofit2.d<T> dVar, kotlin.l.a<? super r<T>> aVar) {
        CancellableContinuation cancellableContinuationImpl = new CancellableContinuationImpl(kotlin.coroutines.intrinsics.b.a(aVar), 1);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        cancellableContinuation.invokeOnCancellation(new kotlin.jvm.b.l<Throwable, kotlin.j>() { // from class: retrofit2.KotlinExtensions$awaitResponse$$inlined$suspendCancellableCoroutine$lambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public /* bridge */ /* synthetic */ kotlin.j invoke(Throwable th) {
                invoke2(th);
                return kotlin.j.f5109a;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                dVar.cancel();
            }
        });
        dVar.e(new c(cancellableContinuation));
        Object result = cancellableContinuationImpl.getResult();
        if (result == kotlin.coroutines.intrinsics.c.b()) {
            kotlin.coroutines.jvm.internal.e.b(aVar);
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object d(java.lang.Exception r4, kotlin.l.a<?> r5) throws java.lang.Throwable {
        /*
            boolean r0 = r5 instanceof retrofit2.KotlinExtensions$suspendAndThrow$1
            if (r0 == 0) goto L13
            r0 = r5
            retrofit2.KotlinExtensions$suspendAndThrow$1 r0 = (retrofit2.KotlinExtensions$suspendAndThrow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            retrofit2.KotlinExtensions$suspendAndThrow$1 r0 = new retrofit2.KotlinExtensions$suspendAndThrow$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.b()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            java.lang.Exception r4 = (java.lang.Exception) r4
            kotlin.g.b(r5)
            goto L5c
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.g.b(r5)
            r0.L$0 = r4
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.l.c r2 = r0.getContext()
            retrofit2.KotlinExtensions$d r3 = new retrofit2.KotlinExtensions$d
            r3.<init>(r0, r4)
            r5.dispatch(r2, r3)
            java.lang.Object r4 = kotlin.coroutines.intrinsics.a.b()
            java.lang.Object r5 = kotlin.coroutines.intrinsics.a.b()
            if (r4 != r5) goto L59
            kotlin.coroutines.jvm.internal.e.b(r0)
        L59:
            if (r4 != r1) goto L5c
            return r1
        L5c:
            kotlin.j r4 = kotlin.j.f5109a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.KotlinExtensions.d(java.lang.Exception, kotlin.l.a):java.lang.Object");
    }
}
