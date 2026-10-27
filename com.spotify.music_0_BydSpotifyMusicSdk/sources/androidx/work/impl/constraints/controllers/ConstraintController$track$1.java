package androidx.work.impl.constraints.controllers;

import java.util.Objects;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.channels.b;
import p.c00;
import p.e60;
import p.fz0;
import p.lq2;
import p.py0;
import p.qy;
import p.ry;
import p.sx1;
import p.v03;
import p.vc1;
import p.zx;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "androidx.work.impl.constraints.controllers.ConstraintController$track$1", f = "ContraintControllers.kt", l = {55}, m = "invokeSuspend")
final class ConstraintController$track$1 extends SuspendLambda implements fz0 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintController$track$1(a aVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = aVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        ConstraintController$track$1 constraintController$track$1 = new ConstraintController$track$1(this.this$0, c00Var);
        constraintController$track$1.L$0 = obj;
        return constraintController$track$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((ConstraintController$track$1) a((sx1) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            sx1 sx1Var = (sx1) this.L$0;
            a aVar = this.this$0;
            final zx zxVar = new zx(aVar, sx1Var);
            qy qyVar = aVar.a;
            qyVar.getClass();
            synchronized (qyVar.c) {
                try {
                    if (qyVar.d.add(zxVar)) {
                        if (qyVar.d.size() == 1) {
                            qyVar.e = qyVar.a();
                            vc1 vc1VarC = vc1.c();
                            int i2 = ry.a;
                            Objects.toString(qyVar.e);
                            vc1VarC.getClass();
                            qyVar.c();
                        }
                        zxVar.a(qyVar.e);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            final a aVar2 = this.this$0;
            py0 py0Var = new py0() { // from class: androidx.work.impl.constraints.controllers.ConstraintController$track$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    qy qyVar2 = aVar2.a;
                    zx zxVar2 = zxVar;
                    qyVar2.getClass();
                    v03.j(zxVar2, "listener");
                    synchronized (qyVar2.c) {
                        if (qyVar2.d.remove(zxVar2) && qyVar2.d.isEmpty()) {
                            qyVar2.d();
                        }
                    }
                    return lq2.a;
                }
            };
            this.label = 1;
            if (b.a(sx1Var, py0Var, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return lq2.a;
    }
}
