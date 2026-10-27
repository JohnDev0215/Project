package coil.request;

import coil.target.GenericViewTarget;
import kotlin.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.av2;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.l91;
import p.lq2;
import p.r91;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "coil.request.ViewTargetRequestManager$dispose$1", f = "ViewTargetRequestManager.kt", l = {}, m = "invokeSuspend")
final class ViewTargetRequestManager$dispose$1 extends SuspendLambda implements fz0 {
    int label;
    final /* synthetic */ av2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewTargetRequestManager$dispose$1(av2 av2Var, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = av2Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new ViewTargetRequestManager$dispose$1(this.this$0, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        ViewTargetRequestManager$dispose$1 viewTargetRequestManager$dispose$1 = (ViewTargetRequestManager$dispose$1) a((e20) obj, (c00) obj2);
        lq2 lq2Var = lq2.a;
        viewTargetRequestManager$dispose$1.i(lq2Var);
        return lq2Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        a.d(obj);
        av2 av2Var = this.this$0;
        ViewTargetRequestDelegate viewTargetRequestDelegate = av2Var.k;
        if (viewTargetRequestDelegate != null) {
            viewTargetRequestDelegate.m.i(null);
            GenericViewTarget genericViewTarget = viewTargetRequestDelegate.k;
            boolean z = genericViewTarget instanceof r91;
            l91 l91Var = viewTargetRequestDelegate.l;
            if (z) {
                l91Var.c(genericViewTarget);
            }
            l91Var.c(viewTargetRequestDelegate);
        }
        av2Var.k = null;
        return lq2.a;
    }
}
