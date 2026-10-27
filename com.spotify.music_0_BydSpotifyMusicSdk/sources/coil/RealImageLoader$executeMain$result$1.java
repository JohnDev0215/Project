package coil;

import android.graphics.Bitmap;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.ii0;
import p.lq2;
import p.q31;
import p.qd2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "coil.RealImageLoader$executeMain$result$1", f = "RealImageLoader.kt", l = {191}, m = "invokeSuspend")
final class RealImageLoader$executeMain$result$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ ii0 $eventListener;
    final /* synthetic */ Bitmap $placeholderBitmap;
    final /* synthetic */ q31 $request;
    final /* synthetic */ qd2 $size;
    int label;
    final /* synthetic */ b this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealImageLoader$executeMain$result$1(q31 q31Var, b bVar, qd2 qd2Var, ii0 ii0Var, Bitmap bitmap, c00 c00Var) {
        super(2, c00Var);
        this.$request = q31Var;
        this.this$0 = bVar;
        this.$size = qd2Var;
        this.$eventListener = ii0Var;
        this.$placeholderBitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new RealImageLoader$executeMain$result$1(this.$request, this.this$0, this.$size, this.$eventListener, this.$placeholderBitmap, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((RealImageLoader$executeMain$result$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            q31 q31Var = this.$request;
            coil.intercept.b bVar = new coil.intercept.b(q31Var, this.this$0.g, 0, q31Var, this.$size, this.$eventListener, this.$placeholderBitmap != null);
            this.label = 1;
            obj = bVar.b(q31Var, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return obj;
    }
}
