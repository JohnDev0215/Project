package defpackage;

/* JADX INFO: compiled from: IntrinsicsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j21 extends tb2 {
    public int f;
    public final /* synthetic */ nr0 g;
    public final /* synthetic */ Object h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j21(Object obj, b00 b00Var, nr0 nr0Var) {
        super(b00Var);
        this.g = nr0Var;
        this.h = obj;
        i21.c(b00Var, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
    }

    @Override // defpackage.ed
    public final Object h(Object obj) throws Throwable {
        int i = this.f;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
            this.f = 2;
            a8.i0(obj);
            return obj;
        }
        this.f = 1;
        a8.i0(obj);
        nr0 nr0Var = this.g;
        i21.c(nr0Var, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1>, kotlin.Any?>");
        dh3.a(nr0Var);
        return nr0Var.d(this.h, this);
    }
}
