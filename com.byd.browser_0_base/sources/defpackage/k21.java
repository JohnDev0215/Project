package defpackage;

/* JADX INFO: compiled from: IntrinsicsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k21 extends c00 {
    public int h;
    public final /* synthetic */ nr0 i;
    public final /* synthetic */ Object j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k21(b00 b00Var, m00 m00Var, nr0 nr0Var, Object obj) {
        super(b00Var, m00Var);
        this.i = nr0Var;
        this.j = obj;
        i21.c(b00Var, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
    }

    @Override // defpackage.ed
    public final Object h(Object obj) throws Throwable {
        int i = this.h;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("This coroutine had already completed".toString());
            }
            this.h = 2;
            a8.i0(obj);
            return obj;
        }
        this.h = 1;
        a8.i0(obj);
        nr0 nr0Var = this.i;
        i21.c(nr0Var, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1>, kotlin.Any?>");
        dh3.a(nr0Var);
        return nr0Var.d(this.j, this);
    }
}
